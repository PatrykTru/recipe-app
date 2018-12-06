package tru.springframework.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
