package mesander.com.TechItEasy.dtos.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoteControllerDto {
    // Variables
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;


    // Relations
    private TelevisionDto television;
}
