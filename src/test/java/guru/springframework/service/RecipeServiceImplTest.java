package guru.springframework.service;

import guru.springframework.entity.Recipe;
import guru.springframework.repository.RecipeRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest extends TestCase {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }
    @Test
    public void testGetRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);
        Set<Recipe> recipes1 = recipeService.getRecipes();

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
    @Test
    public void getRecipeByIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        java.util.Optional<Recipe> recipeOptional = java.util.Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturn = recipeService.findById(1l);
        assertNotNull("Null recipe return", recipeReturn);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();


    }
}