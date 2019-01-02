package tru.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.recipeapp.commands.NotesCommand;
import tru.springframework.recipeapp.domain.Notes;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private final static Long ID_VALUE = new Long(1L);
    private final static String RECIPE_NOTES = "recipe notes";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }
    @Test
    public void testNullObject() throws Exception{
     assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new NotesCommand()));
    }
    @Test
    public void convert() throws Exception{
        NotesCommand commandNotes = new NotesCommand();
        commandNotes.setId(ID_VALUE);
        commandNotes.setRecipeNotes(RECIPE_NOTES);


        Notes notes = converter.convert(commandNotes);

        assertEquals(ID_VALUE , notes.getId());
        assertEquals(RECIPE_NOTES , notes.getRecipeNotes());
        assertNotNull(notes);



    }
}