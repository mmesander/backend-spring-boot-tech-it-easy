package mesander.com.TechItEasy.dtos.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoteControllerInputDto {
    private String compatibleWith;
    private String batteryType;
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;
    @NotNull(message = "Brand is required")
    private String brand;
    @Positive(message = "Price must be higher than zero")
    private Double price;
    @PositiveOrZero(message = "Stock can't be negative")
    private Integer originalStock;
}
