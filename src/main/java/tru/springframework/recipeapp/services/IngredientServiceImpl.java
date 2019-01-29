package tru.springframework.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.converters.IngredientComandToIngredient;
import tru.springframework.recipeapp.converters.IngredientToIngredientCommand;
import tru.springframework.recipeapp.domain.Ingredient;
import tru.springframework.recipeapp.domain.Recipe;
import tru.springframework.recipeapp.repositories.RecipeRepository;
import tru.springframework.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
   private final IngredientToIngredientCommand converter;
   private final RecipeRepository recipeRepository;
   private final UnitOfMeasureRepository unitOfMeasureRepository;
   private final IngredientComandToIngredient ingredientComandToIngredient;

    public IngredientServiceImpl(IngredientToIngredientCommand converter, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientComandToIngredient ingredientComandToIngredient) {
        this.converter = converter;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientComandToIngredient = ingredientComandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional= recipeRepository.findById(recipeId);


        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();


        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> converter.convert(ingredient)).findFirst();

        if(!recipeOptional.isPresent())
            log.error("ingredient ID not found" + ingredientId);

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

    if(!recipeOptional.isPresent())
    {
        log.error("Recipe not Found for id" + command.getRecipeId());

        return new IngredientCommand();
    }else
    {
        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();

        if(ingredientOptional.isPresent())
        {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(command.getDescription());
            ingredientFound.setAmount(command.getAmount());
            ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                            .findById(command.getUom().getId())
                            .orElseThrow(() -> new RuntimeException("Unit of Measure ID not found")));

        }else
        {
            Ingredient ingredient = ingredientComandToIngredient.convert(command);
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }
        Recipe savedRecipe = recipeRepository.save(recipe);

        Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                .findFirst();

        if(!savedIngredientOptional.isPresent())
        savedIngredientOptional = savedRecipe.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUom().getId()))
                .findFirst();

        return converter.convert(savedIngredientOptional.get());
    }
    }

    @Override
    public void deleteIngredientById(Long recipeId, Long idToDelete) {

        log.debug("deleting ingredient " + recipeId + ":" + idToDelete);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent())
        {
            Recipe recipe = recipeOptional.get();
            log.debug("found recipe");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();

            if(ingredientOptional.isPresent())
            {
                log.debug("found ingredient");
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientToDelete);
                recipeRepository.save(recipe);


            }
        }else
            log.debug("not found recipeId");





    }
}
