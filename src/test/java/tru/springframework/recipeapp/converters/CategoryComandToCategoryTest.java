package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.CategoryCommand;
import tru.springframework.recipeapp.domain.Category;

import static org.junit.Assert.*;

public class CategoryComandToCategoryTest {
    private final static Long ID_VALUE = new Long(1L);
    private final static String DESCRIPTION = "description";

    CategoryComandToCategory converter;



    @Before
    public void setUp() throws Exception {
        converter = new CategoryComandToCategory();
    }
    @Test
    public void testNullObject() throws Exception{
    assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() throws Exception{
    assertNotNull(converter.convert(new CategoryCommand()));

    }

    @Test
    public void convert() throws Exception{
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE , category.getId());
        assertEquals(DESCRIPTION , category.getDescription());
    }
}