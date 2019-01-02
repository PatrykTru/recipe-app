package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.CategoryCommand;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.commands.NotesCommand;
import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.domain.Difficulty;
import tru.springframework.recipeapp.domain.Recipe;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private final static Long ID_VALUE = 1l;
    private final static String DESCRIPTION = "description";
    private final static Integer PREPTIME = new Integer(10);
    private final static Integer COOKTIME = new Integer(12);
    private final static Integer SERVINGS = new Integer(15);
    private final static String SOURCE = "source";
    private final static String URL = "http://www.przepis.pl";
    private final static String DIRECTIONS = "directions";
    private final static Difficulty DIFFICULTY = Difficulty.MODERATE;
    private final static Long CAT_ID1 = 2l;
    private final static Long CAT_ID2 = 3l;
    private final static Long ING_ID1 = 4l;
    private final static Long ING_ID2 = 5l;
    private final static Long NOTES_ID = 15l;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe
                (new NotesCommandToNotes(),
                        new CategoryComandToCategory() ,
                        new IngredientComandToIngredient
                                (new UnitOfMeasureCommandToUnitOfMeasure()) );
    }

    @Test
    public void testNullObject()throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setCookTime(COOKTIME);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setId(ID_VALUE);
        recipeCommand.setPrepTime(PREPTIME);
        recipeCommand.setUrl(URL);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setServings(SERVINGS);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID2);

        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(ING_ID1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand1.setId(ING_ID2);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        recipeCommand.setNotes(notes);




        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(ID_VALUE , recipe.getId());
        assertEquals(DESCRIPTION , recipe.getDescription());
        assertEquals(PREPTIME, recipe.getPrepTime());
        assertEquals(COOKTIME, recipe.getCookTime());
        assertEquals(SERVINGS , recipe.getServings());
        assertEquals(SOURCE , recipe.getSource());
        assertEquals(DIRECTIONS , recipe.getDirections());
        assertEquals(DIFFICULTY , recipe.getDifficulty());
        assertEquals(URL , recipe.getUrl());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());

    }
}