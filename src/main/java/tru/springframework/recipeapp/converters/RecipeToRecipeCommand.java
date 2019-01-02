package tru.springframework.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.domain.Recipe;

@Component
public class RecipeToRecipeCommand  implements Converter<Recipe , RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryComand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryComand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }
    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null)
        return null;

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setId(source.getId());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDifficulty(source.getDifficulty());

        if(source.getCategories()!=null && source.getCategories().size()>0)
        {
            source.getCategories().forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        if(source.getIngredients()!= null && source.getIngredients().size()>0)
        {
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));

        }

        return recipeCommand;
    }
}
