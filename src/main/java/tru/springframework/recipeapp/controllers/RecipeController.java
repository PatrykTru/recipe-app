package tru.springframework.recipeapp.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tru.springframework.recipeapp.commands.RecipeCommand;
import tru.springframework.recipeapp.exceptions.NotFoundException;
import tru.springframework.recipeapp.services.RecipeService;

import javax.validation.Valid;

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
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        System.out.println(recipeCommand.getId());
        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError
                    -> log.debug(objectError.toString()));

            return "recipe/recipeform";
        }


        return "redirect:/recipe/"+ savedCommand.getId() +"/show" ;

    }
    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){

        log.debug("deleting by id" , id);
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("handling Not Found Exception");
        log.error(exception.getMessage());


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception" , exception);
        return modelAndView;
    }


}