//package recipes.BusinessLogic;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.stereotype.Component;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.util.HashMap;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//@Component
//public class RecipeStorage {
//    private int id;
//    private ConcurrentMap<Integer, Recipe> recipeStorage;
//
//    public RecipeStorage() {
//        this.id = 0;
//        recipeStorage = new ConcurrentHashMap<>();
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public Recipe getRecipeById(int id) {
//        return this.recipeStorage.get(id - 1);
//    }
//
//    public void addRecipe(Recipe recipe) {
//        this.recipeStorage.put(this.id++, recipe);
//    }
//
//    @Override
//    public String toString() {
//        return "RecipeStorage{" +
//                "id=" + id +
//                ", recipeStorage=" + recipeStorage +
//                '}';
//    }
//}
