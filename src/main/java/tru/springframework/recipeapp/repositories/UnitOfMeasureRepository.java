package tru.springframework.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure , Long> {
}
