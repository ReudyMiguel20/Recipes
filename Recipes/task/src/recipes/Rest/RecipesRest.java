package recipes.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.BusinessLogic.Recipe;
import recipes.DAO.RecipeDAOImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recipe")
public class RecipesRest {
    private RecipeDAOImpl recipeDAOImpl;

    @Autowired
    public RecipesRest(RecipeDAOImpl recipeDAOImpl) {
        this.recipeDAOImpl = recipeDAOImpl;
    }

    @PostMapping("/new")
    public ResponseEntity<?> addRecipe(@Valid @RequestBody Recipe recipe) {
        this.recipeDAOImpl.save(recipe);
        return ResponseEntity.ok().body("{\n \"id\": " + recipe.getId() + "\n}");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        Recipe checkRecipe = null;
        checkRecipe = this.recipeDAOImpl.readRecipeByID(id);

        if (checkRecipe == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.ok().body(checkRecipe);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable int id) {
        Recipe checkRecipe = null;
        checkRecipe = this.recipeDAOImpl.readRecipeByID(id);

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
