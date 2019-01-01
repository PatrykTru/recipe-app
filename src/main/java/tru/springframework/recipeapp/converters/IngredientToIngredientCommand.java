package tru.springframework.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null)
        return null;

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setId(source.getId());
        ingredientCommand.setUnitOfMeasureCommand(uomConverter.convert(source.getUnitOfMeasure()));

        return ingredientCommand;
    }
}
