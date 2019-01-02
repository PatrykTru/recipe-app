package tru.springframework.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.converters.RecipeCommandToRecipe;
import tru.springframework.recipeapp.converters.RecipeToRecipeCommand;
import tru.springframework.recipeapp.domain.Recipe;
import tru.springframework.recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeCommandToRecipe recipeCommandConverter;
    private final RecipeToRecipeCommand recipeConverter;
    private final RecipeRepository recipeRepository;



    public RecipeServiceImpl(RecipeCommandToRecipe recipeCommandConverter, RecipeToRecipeCommand recipeConverter, RecipeRepository recipeRepository) {
        this.recipeCommandConverter = recipeCommandConverter;
        this.recipeConverter = recipeConverter;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public Set<Recipe> getRecipes() {
        log.debug("i'm the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }
    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
       Recipe detachedRecipe = recipeCommandConverter.convert(command);


       Recipe savedRecipe = recipeRepository.save(detachedRecipe);
       log.debug("Saved Recipe id:" +savedRecipe.getId());
       return recipeConverter.convert(savedRecipe);

    }
}

