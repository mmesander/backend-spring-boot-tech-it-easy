package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
