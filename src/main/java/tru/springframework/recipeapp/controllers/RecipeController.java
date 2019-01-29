package tru.springframework.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.services.RecipeService;
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }
    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe" , new RecipeCommand());

                return "recipe/recipeform";
    }
    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model)
    {

        model.addAttribute("recipe" , recipeService.findCommandById(new Long(id)));

        return "recipe/recipeform";
    }
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/"+ savedCommand.getId() +"/show" ;

    }
    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){

        log.debug("deleting by id" , id);
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}