package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
    List<RemoteController> findRemoteControllerByName(String name);
    List<RemoteController> findRemoteControllerByBrand(String brand);
    List<RemoteController> findRemoteControllerByCompatibleWith(String compatibleWith);
}
