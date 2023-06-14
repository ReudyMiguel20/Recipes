//package recipes.BusinessLogic;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import recipes.DAO.RecipeDAOImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class RecipeListDTO {
//    private List<Recipe> recipeList;
//    private RecipeDAOImpl recipeDAOImpl;
//
//    @Autowired
//    public RecipeListDTO(RecipeDAOImpl recipeDAOImpl) {
//        this.recipeDAOImpl = recipeDAOImpl;
//    }
//
//    public void addRecipe(Recipe recipe) {
//        this.recipeList.add(recipe);
//    }
//
//    public void getAllRecipesByCategory(String category) {
//        this.recipeDAOImpl.
//    }
//
//    public int getSize() {
//        return this.recipeList.size();
//    }
//
//    public List<Recipe> getRecipeList() {
//        return recipeList;
//    }
//
//    public void setRecipeList(List<Recipe> recipeList) {
//        this.recipeList = recipeList;
//    }
//
//    @Override
//    public String toString() {
//        return this.recipeList.toString();
//    }
//}
