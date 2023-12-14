package mesander.com.TechItEasy.dtos.output;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class CIModuleDto {
    // Variables
    private Long id;
    private String name;
    private String type;
    private Double price;


    // Relations
    private Set<TelevisionDto> television;
}
