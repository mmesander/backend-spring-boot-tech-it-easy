package mesander.com.TechItEasy.dtos.output;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class WallBracketDto {
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;


    // Relations
    private Set<TelevisionDto> televisions;
}
