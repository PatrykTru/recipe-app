package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.domain.Ingredient;
import tru.springframework.recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private final static Long ID_VALUE = new Long(1L);
    private final static BigDecimal AMOUNT = new BigDecimal(2);
    private final static String DESCRIPTION = "Description";
    private final static Long UOM_ID = new Long(2L);

    IngredientToIngredientCommand converter;



    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Ingredient()));

    }

    @Test
    public void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setId(ID_VALUE);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(DESCRIPTION , ingredientCommand.getDescription());
        assertEquals(ID_VALUE , ingredientCommand.getId());
        assertEquals(UOM_ID , ingredientCommand.getUnitOfMeasureCommand().getId());

    }

    @Test
    public void convertWithNullUom() {
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setId(ID_VALUE);


        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(DESCRIPTION , ingredientCommand.getDescription());
        assertEquals(ID_VALUE , ingredientCommand.getId());
        assertNull(ingredientCommand.getUnitOfMeasureCommand());
        assertNotNull(ingredientCommand);

    }

}