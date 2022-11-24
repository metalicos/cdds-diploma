package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain;

import lombok.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.domain.embeddable.DispenseScheduleDispenseScheduleTemplateId;

import javax.persistence.*;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispense_schedule_dispense_schedule_template", schema = "hydroponic_v1")
public class DispenseScheduleDispenseScheduleTemplate {

    @EmbeddedId
    private DispenseScheduleDispenseScheduleTemplateId id = new DispenseScheduleDispenseScheduleTemplateId();

    @OneToOne
    @JoinColumn(name = "dispense_schedule_id",
            foreignKey = @ForeignKey(name = "fk_disp_schedule_disp_schedule_templ_dispense_schedule_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("dispenseScheduleId")
    private DispenseSchedule dispenseSchedule;

    @ManyToOne
    @JoinColumn(name = "dispense_schedule_template_id",
            foreignKey = @ForeignKey(name = "fk_disp_schedule_disp_schedule_templ_disp_schedule_template_id", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "id")
    @ToString.Exclude
    @MapsId("dispenseScheduleTemplateId")
    private DispenseScheduleTemplate dispenseScheduleTemplate;
}
