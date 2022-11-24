package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@ToString
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device_details", schema = "common")
public class DeviceDetails {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "device_details_id_seq", sequenceName = "device_details_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_details_id_seq")
    private Long id;
    @Column(name = "version", columnDefinition = "text")
    private String version;
    @Column(name = "modification", columnDefinition = "text")
    private String modification;
    @Column(name = "manufactured_timestamp")
    private LocalDateTime manufacturedTimestamp;
    @Column(name = "manufactured_country", columnDefinition = "text")
    private String manufacturedCountry;
    @Column(name = "sold_timestamp")
    private LocalDateTime soldTimestamp;
    @Column(name = "sold_country", columnDefinition = "text")
    private String soldCountry;
    @Column(name = "warranty_from")
    private LocalDateTime warrantyFrom;
    @Column(name = "warranty_to")
    private LocalDateTime warrantyTo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid", columnDefinition = "text",
            foreignKey = @ForeignKey(name = "fk_device_details_device_uuid", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "uuid")
    @ToString.Exclude
    private Device device;
}
