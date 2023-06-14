package recipes.DAO;


import recipes.BusinessLogic.Recipe;

import java.util.List;


public interface RecipeRepository {
    void saveRecipe(Recipe recipe);

    Recipe getByID(Integer id);

    void delete(Integer id);

    void deleteAll();

    void updateRecipe(Recipe oldRecipe, Recipe newRecipe);

    List<Recipe> getRecipesByCategory(String category);

    List<Recipe> getRecipesByName(String name);

}
