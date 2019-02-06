package tru.springframework.recipeapp.services;

import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.domain.Recipe;

import java.util.Set;


public interface RecipeService {


    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long id);
}
