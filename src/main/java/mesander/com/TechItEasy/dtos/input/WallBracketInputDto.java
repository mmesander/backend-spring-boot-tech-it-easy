package mesander.com.TechItEasy.dtos.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WallBracketInputDto {
    @NotNull(message = "Size is required")
    private String size;
    @NotNull(message = "Adjustable is required")
    private Boolean adjustable;
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;
    @Positive(message = "Price must be higher than zero")
    private Double price;
}
