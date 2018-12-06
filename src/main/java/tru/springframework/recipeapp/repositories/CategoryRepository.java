package tru.springframework.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category ,Long> {
}
