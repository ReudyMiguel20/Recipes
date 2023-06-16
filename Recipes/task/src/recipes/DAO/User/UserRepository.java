package recipes.DAO.User;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.BusinessLogic.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
