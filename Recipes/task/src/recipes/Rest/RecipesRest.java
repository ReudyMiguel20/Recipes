//Changed the name of this file to RecipeController in the Stage 2!!
//Changed the name of this file to RecipeController in the Stage 2!!
//Changed the name of this file to RecipeController in the Stage 2!!
//Changed the name of this file to RecipeController in the Stage 2!!
//Changed the name of this file to RecipeController in the Stage 2!!
package recipes.Rest;

import org.springframework.web.bind.annotation.*;
import recipes.BusinessLogic.Recipe;

@RestController
public class RecipesRest {
    Recipe mainRecipe = null;

    @PostMapping("/api/recipe")
    public void addRecipe(@RequestBody Recipe recipe) {
        mainRecipe = recipe;
    }

    @GetMapping("/api/recipe")
    public Recipe getRecipe() {
        return mainRecipe;
    }

}
