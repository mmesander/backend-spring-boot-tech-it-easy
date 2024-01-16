package mesander.com.TechItEasy.security;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority {
}
