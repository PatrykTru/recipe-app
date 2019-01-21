package tru.springframework.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.converters.IngredientToIngredientCommand;
import tru.springframework.recipeapp.domain.Recipe;
import tru.springframework.recipeapp.repositories.RecipeRepository;

import java.util.Optional;
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
   private final IngredientToIngredientCommand converter;
   private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand converter, RecipeRepository recipeRepository) {
        this.converter = converter;
        this.recipeRepository = recipeRepository;
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
}
