package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.EcSensorEcSensorTemplateId;

import javax.persistence.*;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ec_sensor_ec_sensor_template", schema = "hydroponic_v1")
public class EcSensorEcSensorTemplate {

    @EmbeddedId
    private EcSensorEcSensorTemplateId id = new EcSensorEcSensorTemplateId();

    @OneToOne
    @JoinColumn(name = "ec_sensor_id",
            foreignKey = @ForeignKey(name = "fk_ec_sensor_ec_sensor_template_ec_sensor_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("ecSensorId")
    private EcSensor ecSensor;


    @ManyToOne
    @JoinColumn(name = "ec_sensor_template_id",
            foreignKey = @ForeignKey(name = "fk_ec_sensor_ec_sensor_template_ec_sensor_template_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("ecSensorTemplateId")
    private EcSensorTemplate ecSensorTemplate;
}
