package tru.springframework.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand , Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final CategoryComandToCategory categoryConverter;
    private final IngredientComandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryComandToCategory categoryConverter, IngredientComandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }
    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null)
        return null;

        final Recipe recipe = new Recipe();


        recipe.setUrl(source.getUrl());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setId(source.getId());
        recipe.setNotes(notesConverter.convert(source.getNotes()));
        recipe.setDirections(source.getDirections());
        recipe.setDescription(source.getDescription());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDifficulty(source.getDifficulty());


        if(source.getCategories()!=null && source.getCategories().size()>0)
        {
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if(source.getIngredients()!= null && source.getIngredients().size()>0)
        {
            source.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));

        }

        return recipe;
    }
}
