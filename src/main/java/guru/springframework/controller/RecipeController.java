package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exception.NotFoundException;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }
    @GetMapping("recipe/new")
    public String newrecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updaterecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",  recipeService.findCommandById(Long.parseLong(id)));
        return "recipe/recipeform";
    }
    @PostMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand saved = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/"+ saved.getId()+"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception exception){
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errer", "404 Not Found");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
