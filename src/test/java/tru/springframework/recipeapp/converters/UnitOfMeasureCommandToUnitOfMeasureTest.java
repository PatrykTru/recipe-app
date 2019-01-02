package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import tru.springframework.recipeapp.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private final static Long ID_VALUE = new Long(1l);
    private final static String DESCRIPTION = "description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }
    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));

    }
    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));

    }

    @Test
    public void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand =new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID_VALUE);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        assertNotNull(unitOfMeasure);
        assertEquals(ID_VALUE , unitOfMeasure.getId());
        assertEquals(DESCRIPTION , unitOfMeasure.getDescription());
    }
}