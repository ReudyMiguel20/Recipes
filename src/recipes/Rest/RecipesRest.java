package recipes.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.BusinessLogic.Recipe;
import recipes.BusinessLogic.User;
import recipes.DAO.RecipeService;
import recipes.DAO.User.UserServiceImpl;
//import recipes.DAO.User.UserServiceImpl;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class RecipesRest {
    private RecipeService recipeDAOImpl;
    private UserServiceImpl userService;

    @Autowired
    public RecipesRest(RecipeService recipeDAOImpl, UserServiceImpl userService) {
        this.recipeDAOImpl = recipeDAOImpl;
        this.userService = userService;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> addRecipe(Authentication auth, @Valid @RequestBody Recipe recipe) {
        //Setting actual date to the Recipe Object
        LocalDateTime dateTime = LocalDateTime.now();
        recipe.setDate(dateTime);

        //Set the auth username to the recipe
        recipe.setUser(auth.getName());

        this.recipeDAOImpl.saveRecipe(recipe);
        return ResponseEntity.ok().body("{\n \"id\": " + recipe.getId() + "\n}");
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user) {
        boolean userExists = false;
        userExists = this.userService.checkUserExists(user);

        if (userExists) {
            return ResponseEntity.badRequest().build();
        } else {
            this.userService.saveUser(user);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        Recipe checkRecipe = null;
        checkRecipe = this.recipeDAOImpl.getByID(id);

        if (checkRecipe == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.ok().body(checkRecipe);
        }
    }

    @GetMapping("/api/recipe/search")
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

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> updateRecipe(Authentication auth, @PathVariable int id, @Valid @RequestBody Recipe newRecipe) {
        //Verifying that the recipe exists
        Recipe oldRecipe = null;
        oldRecipe = this.recipeDAOImpl.getByID(id);

        if (oldRecipe != null) {
            if (oldRecipe.getUser().equals(auth.getName())) {
                this.recipeDAOImpl.updateRecipe(oldRecipe, newRecipe);
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.status(403).build();
            }
        }

        return ResponseEntity.status(404).build();
    }


    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(Authentication auth, @PathVariable int id) {
        //Verifying that the recipe exists
        Recipe checkRecipe = null;
        checkRecipe = this.recipeDAOImpl.getByID(id);

        if (checkRecipe == null) {
            return ResponseEntity.status(404).build();
        } else {
            if (checkRecipe.getUser().equals(auth.getName())) {
                this.recipeDAOImpl.delete(id);
                return ResponseEntity.status(204).build();
            }
        }
        return ResponseEntity.status(403).build();
    }

    @DeleteMapping
    public void deleteAll() {
        this.recipeDAOImpl.deleteAll();
    }

}
