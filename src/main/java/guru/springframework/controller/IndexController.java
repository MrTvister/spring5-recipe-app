package guru.springframework.controller;

import guru.springframework.entity.Category;
import guru.springframework.entity.UnitOfMeashure;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class IndexController {
    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String degIndexPage(Model model){
        log.debug("Getting index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
