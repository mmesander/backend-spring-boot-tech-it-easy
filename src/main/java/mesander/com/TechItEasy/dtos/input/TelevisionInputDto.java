package mesander.com.TechItEasy.dtos.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelevisionInputDto {
    // Variables
    @NotNull(message = "Type is required")
    private String type;
    @NotNull(message = "Brand is required")
    private String brand;
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;
    @Positive(message = "Price must be higher than zero")
    private Double price;
    private Double availableSize;
    private Double RefreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    @AssertTrue(message = "All televisions must be HDR minimum")
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    @PositiveOrZero(message = "Stock can't be negative")
    private Integer originalStock;
    private Integer sold;
}
