package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.SystemSystemTemplateId;

import javax.persistence.*;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_system_template", schema = "hydroponic_v1")
public class SystemSystemTemplate {

    @EmbeddedId
    private SystemSystemTemplateId id = new SystemSystemTemplateId();

    @OneToOne
    @JoinColumn(name = "system_id", foreignKey = @ForeignKey(name = "fk_system_system_template_system_id", value = ConstraintMode.CONSTRAINT), referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("systemId")
    private System system;

    @ManyToOne
    @JoinColumn(name = "system_template_id", foreignKey = @ForeignKey(name = "fk_system_system_template_system_template_id", value = ConstraintMode.CONSTRAINT), referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("systemTemplateId")
    private SystemTemplate systemTemplate;
}
