package ua.com.cyberdone.devicemicroservice.device.domain;

import lombok.*;

import javax.persistence.*;


@Builder
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDelegateSecret {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "device_delegate_secret_id_seq", sequenceName = "device_delegate_secret_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_delegate_secret_id_seq")
    private Long id;
    @Column(name = "secret", nullable = false, columnDefinition = "text")
    private String secret;
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid", columnDefinition = "text",
            foreignKey = @ForeignKey(name = "fk_device_delegate_secret_device_uuid", value = ConstraintMode.CONSTRAINT),
            referencedColumnName = "uuid")
    @ToString.Exclude
    private Device device;
}
