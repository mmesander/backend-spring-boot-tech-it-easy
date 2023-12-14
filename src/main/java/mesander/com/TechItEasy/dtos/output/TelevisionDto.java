package mesander.com.TechItEasy.dtos.output;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class TelevisionDto {
    // Variables
    private Long id;
    private String type;
    private String brand;
    private String name;
    private Double price;
    private Double availableSize;
    private Double RefreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;


    // Relations
    private RemoteControllerDto remoteController;
    private CIModuleDto ciModule;
    private Set<WallBracketDto> wallBracket;
}
