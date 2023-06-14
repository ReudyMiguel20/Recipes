package recipes.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.BusinessLogic.Recipe;
import recipes.DAO.RecipeService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipesRest {
    private RecipeService recipeDAOImpl;

    @Autowired
    public RecipesRest(RecipeService recipeDAOImpl) {
        this.recipeDAOImpl = recipeDAOImpl;
    }

    @PostMapping("/new")
    public ResponseEntity<?> addRecipe(@Valid @RequestBody Recipe recipe) {
        //Setting actual date to the Recipe Object
        LocalDateTime dateTime = LocalDateTime.now();
        recipe.setDate(dateTime);

        this.recipeDAOImpl.saveRecipe(recipe);
        return ResponseEntity.ok().body("{\n \"id\": " + recipe.getId() + "\n}");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        Recipe checkRecipe = null;
        checkRecipe = this.recipeDAOImpl.getByID(id);

        if (checkRecipe == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.ok().body(checkRecipe);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> recipeList (@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (category != null) {
            List<Recipe> tempRecipeList = this.recipeDAOImpl.getRecipesByCategory(category);

            if (tempRecipeList.isEmpty()) {
                return ResponseEntity.ok().body(tempRecipeList);
            }

            return ResponseEntity.ok().body(tempRecipeList);
        } else if (name != null) {
            List<Recipe> tempRecipeList = this.recipeDAOImpl.getRecipesByName(name);

            if (tempRecipeList.isEmpty()) {
                return ResponseEntity.ok().body(tempRecipeList);
            }

            return ResponseEntity.ok().body(tempRecipeList);
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe( @PathVariable int id, @Valid @RequestBody Recipe newRecipe) {
        Recipe oldRecipe = null;
        oldRecipe = this.recipeDAOImpl.getByID(id);

        if (oldRecipe != null) {
            this.recipeDAOImpl.updateRecipe(oldRecipe, newRecipe);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable int id) {
        Recipe checkRecipe = null;
        checkRecipe = this.recipeDAOImpl.getByID(id);

        if (checkRecipe == null) {
            return ResponseEntity.status(404).build();
        } else {
            this.recipeDAOImpl.delete(id);
            return ResponseEntity.status(204).build();
        }
    }

    @DeleteMapping
    public void deleteAll() {
        this.recipeDAOImpl.deleteAll();
    }

}
