package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
    List<CIModule> findCIModuleByNameContainsIgnoreCase(String name);
}
