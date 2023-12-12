package mesander.com.TechItEasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


    // Constructors
    public WallBracket() {
    }

    public WallBracket(
            Long id,
            String size,
            Boolean adjustable,
            String name,
            Double price
    ) {
        this.id = id;
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }
}
