package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("HYDROPONIC_DATA_V1")
public class HydroponicData {
    @Id
    @Column("id")
    private Long id;

    @Column("device_uuid")
    private String deviceUuid;

    @Column("ec_solution")
    private double ecSolution;

    @Column("ph_solution")
    private double phSolution;

    @Column("t_solution")
    private double tSolution;

    @Column("t_air")
    private Double tAir;

    @Column("humidity_air")
    private Double humidityAir;

    @Column("atmospheric_pressure")
    private Double atmosphericPressure;

    @Column("created_timestamp")
    private LocalDateTime createdTimestamp;
}