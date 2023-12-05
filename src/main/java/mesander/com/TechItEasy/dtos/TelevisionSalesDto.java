package mesander.com.TechItEasy.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelevisionSalesDto {
    private Long id;
    private Double price;
    private Integer originalStock;
    private Integer sold;
    private Double earnings;
}
