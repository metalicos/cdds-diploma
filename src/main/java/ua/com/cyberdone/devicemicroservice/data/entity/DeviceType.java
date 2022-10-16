package ua.com.cyberdone.devicemicroservice.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("DEVICE_TYPE")
public class DeviceType {

    @Id
    @Column("id")
    private Long id;

    @Column("type")
    private String type;
}
