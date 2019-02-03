package tru.springframework.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tru.springframework.recipeapp.services.ImageService;
import tru.springframework.recipeapp.services.RecipeService;


@Controller
public class ImageController {

    ImageService imageService;
    RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {

        model.addAttribute("recipe" , recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile multipartFile){

        imageService.saveImageFile(Long.valueOf(id),multipartFile);

        return "redirect:/recipe/" + id +"/show" ;
    }


}
