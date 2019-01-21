package tru.springframework.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.converters.IngredientToIngredientCommand;
import tru.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tru.springframework.recipeapp.domain.Ingredient;
import tru.springframework.recipeapp.domain.Recipe;
import tru.springframework.recipeapp.repositories.RecipeRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand,recipeRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(2l);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(3l);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(4l);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L , 3l);

        assertEquals(Long.valueOf(3l), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L) , ingredientCommand.getRecipeId());
        verify(recipeRepository,times(1)).findById(anyLong());
    }


}