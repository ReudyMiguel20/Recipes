package recipes.DAO.Authorities;


import org.springframework.beans.factory.annotation.Autowired;
import recipes.BusinessLogic.Authorities;

public class AuthoritiesServiceImpl implements AuthoritiesService {

    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }


    @Override
    public void saveUserWithRole(Authorities authority) {
        this.authoritiesRepository.save(authority);
    }
}
