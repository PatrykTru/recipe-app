package tru.springframework.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.domain.Ingredient;

@Component
public class IngredientComandToIngredient implements Converter<IngredientCommand, Ingredient> {
  private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientComandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null)
        return null;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUom()));
        return ingredient;
    }
}
