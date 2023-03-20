package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exception.NotFoundException;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    public void showById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1l);

        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public  void testGetRecipeThrowException() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound());
    }
    @Test
    public  void testGetRecipeThrowBadRequestException() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        when(recipeService.findById(anyLong())).thenThrow(NumberFormatException.class);
        mockMvc.perform(get("/recipe/asdf/show"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetRecipeNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"));
    }
    @Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1l);
         when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

         mockMvc.perform(post("/recipe")
                         .contentType(APPLICATION_FORM_URLENCODED).param("id", "")
                         .param("description",""))
                 .andExpect(status().is3xxRedirection())
                 .andExpect(view().name("redirect:/recipe/"+1l+"/show"));
    }
    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1l);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

}