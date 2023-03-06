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

public class RecipeServiceImplTest extends TestCase {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    public void testGetRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);
        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);
        Set<Recipe> recipes1 = recipeService.getRecipes();

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}