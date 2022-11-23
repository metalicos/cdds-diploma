package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispenser", schema = "hydroponic_v1")
public class Dispenser {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "dispenser_id_seq", sequenceName = "dispenser_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispenser_id_seq")
    private Long id;
    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid", columnDefinition = "text",
            foreignKey = @ForeignKey(name = "fk_dispenser_device_uuid", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "uuid")
    @ToString.Exclude
    private Device device;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            schema = "hydroponic_v1",
            name = "dispenser_dispenser_template",
            joinColumns = @JoinColumn(name = "dispenser_id",
                    foreignKey = @ForeignKey(name = "fk_dispenser_dispenser_template_dispenser_id", value = ConstraintMode.CONSTRAINT),
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dispenser_template_id",
                    foreignKey = @ForeignKey(name = "fk_dispenser_dispenser_template_dispenser_template_id", value = ConstraintMode.CONSTRAINT),
                    referencedColumnName = "id")
    )
    @ToString.Exclude
    private DispenserTemplate dispenserTemplate;
}