package mesander.com.TechItEasy.dtos.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CIModuleDto {
    private Long id;
    private String name;
    private String type;
    private Double price;
}
