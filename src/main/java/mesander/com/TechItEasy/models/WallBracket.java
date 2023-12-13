package mesander.com.TechItEasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "wall_brackets")
public class WallBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;


    // Relations
    @ManyToMany(mappedBy = "wallBrackets")
    private Set<Television> televisions;
}
