package tru.springframework.recipeapp.services;

import org.springframework.stereotype.Service;
import tru.springframework.recipeapp.domain.Recipe;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();
}
