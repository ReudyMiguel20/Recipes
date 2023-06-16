package recipes.DAO.Authorities;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.BusinessLogic.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
}
