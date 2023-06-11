package recipes.BusinessLogic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RecipeStorage {
    private int id;
    private HashMap<Integer, Recipe> recipeStorage;

    public RecipeStorage() {
        this.id = 0;
        recipeStorage = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recipe getRecipeById(int id) {
        return this.recipeStorage.get(id - 1);
    }

    public void addRecipe(Recipe recipe) {
        this.recipeStorage.put(this.id++, recipe);
    }

    public HashMap<Integer, Recipe> getRecipeStorage() {
        return recipeStorage;
    }

    public void setRecipeStorage(HashMap<Integer, Recipe> recipeStorage) {
        this.recipeStorage = recipeStorage;
    }

    @Override
    public String toString() {
        return "RecipeStorage{" +
                "id=" + id +
                ", recipeStorage=" + recipeStorage +
                '}';
    }
}
