package tru.springframework.recipeapp.services;

import tru.springframework.recipeapp.commands.IngredientCommand;


public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long RecipeId, Long IngredientId);
}
