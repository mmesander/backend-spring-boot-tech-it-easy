package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    Optional<Television> findTelevisionByName(String Name);
}
