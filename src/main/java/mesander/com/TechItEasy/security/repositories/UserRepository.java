package mesander.com.TechItEasy.security.repositories;

import mesander.com.TechItEasy.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
