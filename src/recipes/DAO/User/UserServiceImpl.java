package recipes.DAO.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.BusinessLogic.Authorities;
import recipes.BusinessLogic.User;
import recipes.DAO.Authorities.AuthoritiesRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }


    @Override
    public void saveUser(User user) {
        //Creating encoder for encrypting the password for the user
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //Setting up some values for the user before Saving it to H2 database
        user.setAuthority("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(1);

        //Creating new authority and saving it to the database
        Authorities tempAuthRole = new Authorities(user.getUsername(), user.getAuthority());
        this.authoritiesRepository.save(tempAuthRole);

        this.userRepository.save(user);
    }

    @Override
    public boolean checkUserExists(User user) {
        for (User x : getAllUsers()) {
            if (user.getUsername().equals(x.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
