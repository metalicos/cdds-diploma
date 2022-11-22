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
@RequiredArgsConstructor
public class DeviceDelegateSecret {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "device_delegate_secret_id_seq", sequenceName = "device_delegate_secret_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_delegate_secret_id_seq")
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    private String secret;

    @Column(nullable = false)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_uuid")
    @ToString.Exclude
    private Device device;
}
