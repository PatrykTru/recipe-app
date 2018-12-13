package tru.springframework.recipeapp.bootstrap;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tru.springframework.recipeapp.domain.*;
import tru.springframework.recipeapp.repositories.CategoryRepository;
import tru.springframework.recipeapp.repositories.RecipeRepository;
import tru.springframework.recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        log.debug("im a bootstrap Contructor");
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("i am onApplicationEvent");
    }

    private List<Recipe> getRecipes(){
       List<Recipe> recipes = new ArrayList<>(2);
        log.debug("I am getRecipes method");
       Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
       if(!eachUomOptional.isPresent())
           throw new RuntimeException("Expected UOM not found");

       Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
       if(!dashUomOptional.isPresent())
           throw new RuntimeException("Expected UOM not found");

       Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
       if(!teaspoonUomOptional.isPresent())
           throw new RuntimeException("Expected UOM not found");

       Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
       if(!pinchUomOptional.isPresent())
           throw new RuntimeException("Expected UOM not found");

       Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
       if(!cupUomOptional.isPresent())
           throw new RuntimeException("Expected UOM not found");

       Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
       if(!tablespoonUomOptional.isPresent())
           throw new RuntimeException("Expected UOM not found");

       Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
       if(!pintUomOptional.isPresent())
           throw new RuntimeException("Expected UOM not found");

        //get optionals
       UnitOfMeasure eachUom = eachUomOptional.get();
       UnitOfMeasure dashUom = dashUomOptional.get();
       UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
       UnitOfMeasure pinchUom = pinchUomOptional.get();
       UnitOfMeasure cupUom = cupUomOptional.get();
       UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
       UnitOfMeasure pintUom = pintUomOptional.get();


       Optional<Category> americanCategoryOptional= categoryRepository.findByDescription("American");
       if(!americanCategoryOptional.isPresent())
           throw new RuntimeException("Expected Category not found");

       Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
       if(!mexicanCategoryOptional.isPresent())
           throw new RuntimeException("Expected Category not found");

        Category mexicanCategory = mexicanCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();


        Recipe perfectGuacamoleRecipe = new Recipe();
       perfectGuacamoleRecipe.setDifficulty(Difficulty.EASY);
       perfectGuacamoleRecipe.setDescription("Perfect Guacamole");
       perfectGuacamoleRecipe.setPrepTime(10);
       perfectGuacamoleRecipe.setCookTime(0);
       perfectGuacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
       + "\n" +
       "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
       + "\n" +
       "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
        "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
        "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." + "\n" +
        "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
        "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving." + "\n" +
               "Read more at: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

       Notes guacNotes = new Notes();
       guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
               "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
               "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
               "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
               "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!"
                +"\n"
               +"\n" +
               "Read more at: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

       guacNotes.setRecipe(perfectGuacamoleRecipe);
       perfectGuacamoleRecipe.setNotes(guacNotes);

       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("Ripe Avocados" ,new BigDecimal(2),eachUom,perfectGuacamoleRecipe));
       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("Kosher Salt" , new BigDecimal(".5"), teaspoonUom,perfectGuacamoleRecipe));
       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice" , new BigDecimal(2), tablespoonUom,perfectGuacamoleRecipe));
       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion" , new BigDecimal(2), tablespoonUom,perfectGuacamoleRecipe));
       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced" , new BigDecimal(2), eachUom,perfectGuacamoleRecipe));
       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("cilantro (leaves and tender stems), finely chopped" , new BigDecimal(2), tablespoonUom,perfectGuacamoleRecipe));
       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("freshly grated black pepper" , new BigDecimal(1), dashUom,perfectGuacamoleRecipe));
       perfectGuacamoleRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped" , new BigDecimal(".5"), eachUom,perfectGuacamoleRecipe));

        perfectGuacamoleRecipe.getCategories().add(mexicanCategory);
        perfectGuacamoleRecipe.getCategories().add(americanCategory);

recipes.add(perfectGuacamoleRecipe);

    Recipe tacosRecipe = new Recipe();
    tacosRecipe.setDifficulty(Difficulty.MODERATE);
    tacosRecipe.setPrepTime(10);
    tacosRecipe.setCookTime(20);
    tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
    tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
            "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
            "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
            "Spicy Grilled Chicken Tacos\n" +
            "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
            "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
            "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
            "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");

    Notes tacoNotes = new Notes();
       tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
            "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
            "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
            "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
            "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!"
            +"\n"
            +"\n" +
            "Read more at: https: //www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoNotes.setRecipe(tacosRecipe);
       tacosRecipe.setNotes(tacoNotes);



        tacosRecipe.getIngredients().add(new Ingredient("ancho chili powder",new BigDecimal(2),tablespoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("dried oregano",new BigDecimal(1),teaspoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("dried cumin",new BigDecimal(1),teaspoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("sugar",new BigDecimal(1),teaspoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("salt",new BigDecimal(".5"),teaspoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("clove garlic, finely chopped",new BigDecimal(1),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("finely grated orange zest",new BigDecimal(1),tablespoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("fresh-squeezed orange juice",new BigDecimal(3),tablespoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient(" olive oil",new BigDecimal(2),tablespoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("skinless, boneless chicken thighs",new BigDecimal(6),eachUom,tacosRecipe));

        tacosRecipe.getIngredients().add(new Ingredient("small corn tortillas",new BigDecimal(8),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("packed baby arugula ",new BigDecimal(3),cupUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("medium ripe avocados, sliced",new BigDecimal(2),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced",new BigDecimal(4),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("cherry tomatoes, halved",new BigDecimal(".5"),pintUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced",new BigDecimal(".25"),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Roughly chopped cilantro",new BigDecimal(1),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk",new BigDecimal(".5"),cupUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges",new BigDecimal(1),eachUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges",new BigDecimal(1),eachUom,tacosRecipe));

        tacosRecipe.getCategories().add(mexicanCategory);
        tacosRecipe.getCategories().add(americanCategory);

        recipes.add(tacosRecipe);
    return recipes;
    }
}
