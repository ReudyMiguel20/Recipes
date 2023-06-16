package recipes.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import recipes.BusinessLogic.Recipe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RecipeService implements RecipeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RecipeService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveRecipe(Recipe recipe) {
        this.entityManager.persist(recipe);
    }

    @Override
    public Recipe getByID(Integer id) {
        return this.entityManager.find(Recipe.class, id);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Recipe tempRecipe = this.entityManager.find(Recipe.class, id);
        this.entityManager.remove(tempRecipe);
    }

    @Override
    @Transactional
    public void deleteAll() {
        this.entityManager.createQuery("DELETE FROM Recipes").executeUpdate();
    }

    @Override
    @Transactional
    public void updateRecipe(Recipe oldRecipe, Recipe newRecipe) {
        //Updating time to the recipe
        LocalDateTime dateTime = LocalDateTime.now();
        newRecipe.setDate(dateTime);

        //Updating the old recipe with the new info
        oldRecipe.setName(newRecipe.getName());
        oldRecipe.setCategory(newRecipe.getCategory());
        oldRecipe.setDate(newRecipe.getDate());
        oldRecipe.setDescription(newRecipe.getDescription());
        oldRecipe.setIngredients(newRecipe.getIngredients());
        oldRecipe.setDirections(newRecipe.getDirections());

    }

    public List<Recipe> getRecipesByCategory(String category) {
        TypedQuery<Recipe> findQuery = this.entityManager.createQuery("FROM Recipes r WHERE UPPER(r.category) = UPPER(:category) ORDER BY r.date DESC", Recipe.class);

        findQuery.setParameter("category", category);

        return findQuery.getResultList();
    }

    @Override
    public List<Recipe> getRecipesByName(String name) {
        TypedQuery<Recipe> findQuery = this.entityManager.createQuery("FROM Recipes r WHERE LOWER(r.name) LIKE LOWER(:name) ORDER BY r.date DESC", Recipe.class);

        findQuery.setParameter("name", "%" + name + "%");

        return findQuery.getResultList();
    }


}
