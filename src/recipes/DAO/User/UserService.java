package recipes.DAO.User;

import recipes.BusinessLogic.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    boolean checkUserExists(User user);

    List<User> getAllUsers();
}
