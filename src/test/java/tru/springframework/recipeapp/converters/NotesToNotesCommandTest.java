package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.NotesCommand;
import tru.springframework.recipeapp.domain.Notes;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private final static Long ID_VALUE  = new Long(1l);
    private final static String RECIPE_NOTES = "recipe notes";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }
    @Test
    public void testNullObject()throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject()throws Exception{
        assertNotNull(converter.convert(new Notes()));

    }

    @Test
    public void convert() {
        Notes notes = new Notes();
        notes.setRecipeNotes(RECIPE_NOTES);
        notes.setId(ID_VALUE);

        NotesCommand notesCommand = converter.convert(notes);

        assertNotNull(notesCommand);
        assertEquals(RECIPE_NOTES , notesCommand.getRecipeNotes());
        assertEquals(ID_VALUE , notesCommand.getId());

    }
}