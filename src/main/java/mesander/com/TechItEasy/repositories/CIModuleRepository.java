package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
}
