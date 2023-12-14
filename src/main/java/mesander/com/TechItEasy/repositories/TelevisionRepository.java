package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    List<Television> findTelevisionByName(String name);
    List<Television> findTelevisionByBrand(String brand);
    List<Television> findTelevisionsByBrandAndName(String brand, String name);
}
