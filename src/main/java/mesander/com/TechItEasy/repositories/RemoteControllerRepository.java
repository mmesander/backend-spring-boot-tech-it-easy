package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
    List<RemoteController> findRemoteControllerByNameContainsIgnoreCase(String name);
    List<RemoteController> findRemoteControllerByBrandContainsIgnoreCase(String brand);
    List<RemoteController> findRemoteControllerByCompatibleWithIgnoreCase(String compatibleWith);
}
