package recipes.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.BusinessLogic.Recipe;
import recipes.BusinessLogic.RecipeStorage;

@RestController
@RequestMapping("/api/recipe")
public class RecipesRest {

    private RecipeStorage recipeStorage;

    @Autowired
    public RecipesRest(RecipeStorage recipeStorage) {
        this.recipeStorage = recipeStorage;
    }

    @PostMapping("/new")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        this.recipeStorage.addRecipe(recipe);
        return ResponseEntity.ok().body("{\n \"id\": " + this.recipeStorage.getId() + "\n}");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        Recipe checkRecipe = null;
        checkRecipe = this.recipeStorage.getRecipeById(id);

        if (checkRecipe == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.ok().body(this.recipeStorage.getRecipeById(id));
        }
    }

}
