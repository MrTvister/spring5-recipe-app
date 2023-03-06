package guru.springframework.entity;

import junit.framework.TestCase;
import org.junit.Before;

import java.util.HashSet;
import java.util.Set;

public class CategoryTest extends TestCase {
    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    public void testGetId() {
        Long idValue = 4l;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    public void testGetDescription() {
        String descriptionValue = "Test";
        category.setDescription(descriptionValue);
        assertEquals(descriptionValue, category.getDescription());
    }

    public void testGetRecipes() {

        Set<Recipe> recipesValues = new HashSet<>();
        category.setRecipes(recipesValues);
        assertEquals(recipesValues, category.getRecipes());
    }
}