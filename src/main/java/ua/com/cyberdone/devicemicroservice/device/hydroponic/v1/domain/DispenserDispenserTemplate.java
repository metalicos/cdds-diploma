package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.DispenserDispenserTemplateId;

import javax.persistence.*;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispenser_dispenser_template", schema = "hydroponic_v1")
public class DispenserDispenserTemplate {

    @EmbeddedId
    private DispenserDispenserTemplateId id = new DispenserDispenserTemplateId();

    @OneToOne
    @JoinColumn(name = "dispenser_id",
            foreignKey = @ForeignKey(name = "fk_dispenser_dispenser_template_dispenser_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("dispenserId")
    private Dispenser dispenser;

    @ManyToOne
    @JoinColumn(name = "dispenser_template_id",
            foreignKey = @ForeignKey(name = "fk_dispenser_dispenser_template_dispenser_template_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("dispenserTemplateId")
    private DispenserTemplate dispenserTemplate;
}
