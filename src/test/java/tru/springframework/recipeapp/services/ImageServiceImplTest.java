package tru.springframework.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import tru.springframework.recipeapp.domain.Recipe;
import tru.springframework.recipeapp.repositories.RecipeRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(recipeRepository);
        }



    @Test
    public void saveImageFile() throws Exception {
        Long id = 1l;
        MultipartFile file = new MockMultipartFile("imagefile" , "texting.txt" , "text/plain" ,
                "Spring Framework Guru".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(recipe.getId(), file);

        verify(recipeRepository, times(1)).save(captor.capture());
        Recipe savedRecipe = captor.getValue();
        assertEquals(file.getBytes().length , savedRecipe.getImage().length);
    }
}