package mesander.com.TechItEasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "remote_controllers")
public class RemoteController {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "compatible_with")
    private String compatibleWith;
    @Column(name = "battery_type")
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    @Column(name = "original_stock")
    private Integer originalStock;


    // Relations
    @OneToOne(mappedBy = "remoteController", fetch = FetchType.LAZY)
    private Television television;
}
