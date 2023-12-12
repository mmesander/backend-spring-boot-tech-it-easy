package mesander.com.TechItEasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "televisions")
public class Television {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String brand;
    private String name;
    private Double price;
    @Column(name = "available_size")
    private Double availableSize;
    @Column(name = "refresh_rate")
    private Double refreshRate;
    @Column(name = "screen_type")
    private String screenType;
    @Column(name = "screen_quality")
    private String screenQuality;
    @Column(name = "smart_tv")
    private Boolean smartTv;
    private Boolean wifi;
    @Column(name = "voice_control")
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    @Column(name = "ambi_light")
    private Boolean ambiLight;
    @Column(name = "original_stock")
    private Integer originalStock;
    private Integer sold;


    // Relations
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remote_controllers")
    private RemoteController remoteController;

    @ManyToOne
    @JoinColumn(name = "ci_module_id")
    private CIModule ciModule;

    @ManyToMany
    @JoinTable(
            name = "television_wall_brackets",
            joinColumns = @JoinColumn(name = "wall_bracket_id"),
            inverseJoinColumns = @JoinColumn(name = "television_id")
    )
    private List<WallBracket> wallBrackets;


    // Constructors
    public Television() {
    }

    public Television(
            Long id,
            String type,
            String brand,
            String name,
            Double price,
            Double availableSize,
            Double refreshRate,
            String screenType,
            String screenQuality,
            Boolean smartTv,
            Boolean wifi,
            Boolean voiceControl,
            Boolean hdr,
            Boolean bluetooth,
            Boolean ambiLight,
            Integer originalStock,
            Integer sold
    ) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSize = availableSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.wifi = wifi;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.sold = sold;
    }
}
