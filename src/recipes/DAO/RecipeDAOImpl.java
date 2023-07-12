package recipes.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import recipes.BusinessLogic.Recipe;

@Repository
public class RecipeDAOImpl implements RecipeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RecipeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Recipe recipe) {
        this.entityManager.persist(recipe);
    }

    @Override
    public Recipe readRecipeByID(Integer id) {
        return this.entityManager.find(Recipe.class, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Recipe tempRecipe = this.entityManager.find(Recipe.class, id);
        this.entityManager.remove(tempRecipe);
    }

    @Override
    @Transactional
    public void deleteAll() {
        this.entityManager.createQuery("DELETE FROM Recipes").executeUpdate();
    }

}
