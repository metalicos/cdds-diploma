package ua.com.cyberdone.devicemicroservice.mapper;

import org.modelmapper.ModelMapper;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegatedDeviceControl;

public class DelegatedDeviceControlMapper<Dto> extends AbstractMapper<DelegatedDeviceControl, Dto> {
    public DelegatedDeviceControlMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
