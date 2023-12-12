package mesander.com.TechItEasy.dtos.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CIModuleInputDto {
    private String name;
    private String type;
    private Double price;
}
