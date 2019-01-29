package tru.springframework.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tru.springframework.recipeapp.commands.IngredientCommand;
import tru.springframework.recipeapp.converters.IngredientComandToIngredient;
import tru.springframework.recipeapp.converters.IngredientToIngredientCommand;
import tru.springframework.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import tru.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tru.springframework.recipeapp.domain.Ingredient;
import tru.springframework.recipeapp.domain.Recipe;
import tru.springframework.recipeapp.repositories.RecipeRepository;
import tru.springframework.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientComandToIngredient ingredientComandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;


    public IngredientServiceImplTest() {
        this.ingredientComandToIngredient =  new IngredientComandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand,recipeRepository,unitOfMeasureRepository,ingredientComandToIngredient);
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
    @Test
    public void testSaveIngredientCommand() throws Exception
    {
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(3l);
    ingredientCommand.setRecipeId(2l);

    Optional<Recipe> recipeOptional = Optional.of(new Recipe());

    Recipe savedRecipe = new Recipe();
    savedRecipe.addIngredient(new Ingredient());
    savedRecipe.getIngredients().iterator().next().setId(3l);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
    when(recipeRepository.save(any())).thenReturn(savedRecipe);

     IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

   assertEquals(Long.valueOf(3l) , savedCommand.getId());
   verify(recipeRepository, times(1)).findById(anyLong());
   verify(recipeRepository,times(1)).save(any(Recipe.class));
    }
    @Test
    public void testDeleteIngredientById() throws Exception{
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();

        ingredient.setId(3l);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ingredientService.deleteIngredientById(1L,3L);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));



    }


}