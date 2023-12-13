package mesander.com.TechItEasy.repositories;

import mesander.com.TechItEasy.models.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WallBracketRepository extends JpaRepository<WallBracket, Long> {
    List<WallBracket> findWallBracketsByAdjustable(Boolean adjustable);
}
