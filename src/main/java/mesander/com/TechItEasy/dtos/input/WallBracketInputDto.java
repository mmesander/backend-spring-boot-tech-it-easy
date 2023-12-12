package mesander.com.TechItEasy.dtos.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WallBracketInputDto {
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;
}
