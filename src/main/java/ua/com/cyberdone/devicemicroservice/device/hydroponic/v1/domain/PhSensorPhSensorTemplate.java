package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.PhSensorPhSensorTemplateId;

import javax.persistence.*;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ph_sensor_ph_sensor_template", schema = "hydroponic_v1")
public class PhSensorPhSensorTemplate {

    @EmbeddedId
    private PhSensorPhSensorTemplateId id = new PhSensorPhSensorTemplateId();

    @OneToOne
    @JoinColumn(name = "ph_sensor_id",
            foreignKey = @ForeignKey(name = "fk_ph_sensor_ph_sensor_template_ph_sensor_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("phSensorId")
    private PhSensor phSensor;

    @ManyToOne
    @JoinColumn(name = "ph_sensor_template_id",
            foreignKey = @ForeignKey(name = "fk_ph_sensor_ph_sensor_template_ph_sensor_template_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("phSensorTemplateId")
    private PhSensorTemplate phSensorTemplate;
}
