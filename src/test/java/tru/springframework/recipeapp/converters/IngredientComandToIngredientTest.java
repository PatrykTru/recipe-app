package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import tru.springframework.recipeapp.domain.Ingredient;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientComandToIngredientTest {

    private final static Long ID_VALUE = new Long(1L);
    private final static String DESCRIPTION = "description";
    private final static BigDecimal AMOUNT = new BigDecimal(2);
    private final static Long UOM_ID = new Long(2L);
    IngredientComandToIngredient converter;




    @Before
    public void setUp() throws Exception {
        converter = new IngredientComandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject(){
    assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new IngredientCommand()));

    }
    @Test
    public void convert() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUom(unitOfMeasureCommand);

        Ingredient ingredient = converter.convert(ingredientCommand);

        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION , ingredient.getDescription());
        assertEquals(AMOUNT , ingredient.getAmount());
        assertEquals(UOM_ID , ingredient.getUnitOfMeasure().getId() );
    }

    @Test
    public void convertWithNullUom() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);


        Ingredient ingredient = converter.convert(ingredientCommand);

        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION , ingredient.getDescription());
        assertEquals(AMOUNT , ingredient.getAmount());
        assertNull(ingredient.getUnitOfMeasure());
        assertNotNull(ingredient);
    }
}