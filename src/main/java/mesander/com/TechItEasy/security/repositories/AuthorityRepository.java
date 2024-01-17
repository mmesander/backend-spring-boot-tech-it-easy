package mesander.com.TechItEasy.security.repositories;

import mesander.com.TechItEasy.security.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Optional<Authority> findAuthoritiesByAuthorityContainsIgnoreCase(String authority);
}
