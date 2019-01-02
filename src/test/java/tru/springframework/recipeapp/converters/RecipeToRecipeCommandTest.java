package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.domain.*;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand
                (new NotesToNotesCommand(),
                        new CategoryToCategoryComand() ,
                        new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void testNullObject()throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setDescription(DESCRIPTION);
        recipe.setCookTime(COOKTIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setId(ID_VALUE);
        recipe.setPrepTime(PREPTIME);
        recipe.setUrl(URL);
        recipe.setSource(SOURCE);
        recipe.setServings(SERVINGS);

        Category category1 = new Category();
        category1.setId(CAT_ID1);
        Category category2 = new Category();
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(ING_ID1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(ING_ID2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);




        RecipeCommand recipeCommand = converter.convert(recipe);

        assertNotNull(recipeCommand);
        assertEquals(ID_VALUE , recipeCommand.getId());
        assertEquals(DESCRIPTION , recipeCommand.getDescription());
        assertEquals(PREPTIME, recipeCommand.getPrepTime());
        assertEquals(COOKTIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS , recipeCommand.getServings());
        assertEquals(SOURCE , recipeCommand.getSource());
        assertEquals(DIRECTIONS , recipeCommand.getDirections());
        assertEquals(DIFFICULTY , recipeCommand.getDifficulty());
        assertEquals(URL , recipeCommand.getUrl());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());

    }
}