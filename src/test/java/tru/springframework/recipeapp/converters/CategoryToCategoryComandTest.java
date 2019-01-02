package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.CategoryCommand;
import tru.springframework.recipeapp.domain.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryComandTest {

    private final static Long ID_VALUE = new Long(1L);
    private final static String DESCRIPTION = "description";

    CategoryToCategoryComand converter;
    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryComand();
    }
    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));

    }
    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Category()));

    }
    @Test
    public void convert() {
        Category category = new Category();
        category.setDescription(DESCRIPTION);
        category.setId(ID_VALUE);

        CategoryCommand categoryCommand = converter.convert(category);

        assertEquals(ID_VALUE,categoryCommand.getId());
        assertEquals(DESCRIPTION , categoryCommand.getDescription());

    }
}