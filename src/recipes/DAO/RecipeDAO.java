package recipes.DAO;


import recipes.BusinessLogic.Recipe;


public interface RecipeDAO  {
    void save(Recipe recipe);

    Recipe readRecipeByID(Integer id);

    void delete(Integer id);

    void deleteAll();

}
