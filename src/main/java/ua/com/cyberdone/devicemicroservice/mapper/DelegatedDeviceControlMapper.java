package ua.com.cyberdone.devicemicroservice.mapper;

import org.modelmapper.ModelMapper;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DelegatedDeviceControl;
import ua.com.cyberdone.devicemicroservice.persistence.model.delegation.DelegatedDeviceControlDto;
import ua.com.cyberdone.devicemicroservice.util.ImageStandards;
import ua.com.cyberdone.devicemicroservice.util.ImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DelegatedDeviceControlMapper<Dto> extends AbstractMapper<DelegatedDeviceControl, Dto> {
    public DelegatedDeviceControlMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public List<Dto> toDtoList(List<DelegatedDeviceControl> list, Class<Dto> clazz) {
        if (isNull(list) || list.isEmpty()) {
            return Collections.emptyList();
        }
        var dtoList = new ArrayList<Dto>();
        for (var element : list) {
            var dto = toDto(element, clazz);
            if (dto instanceof DelegatedDeviceControlDto deviceControlDto) {
                var meta = deviceControlDto.getDeviceMetadata();
                if (nonNull(element.getDeviceMetadata().getDeviceImage())) {
                    meta.setDeviceImage(
                            ImageUtils.getScaledBase64OrElseOriginal(element.getDeviceMetadata().getDeviceImage(),
                                    ImageStandards.DEVICE_CARD));
                    ((DelegatedDeviceControlDto) dto).setDeviceMetadata(meta);
                }
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Set<Dto> toDtoSet(Set<DelegatedDeviceControl> set, Class<Dto> clazz) {
        if (isNull(set) || set.isEmpty()) {
            return Collections.emptySet();
        }
        var dtoSet = new HashSet<Dto>();
        for (var element : set) {
            var dto = toDto(element, clazz);
            if (dto instanceof DelegatedDeviceControlDto deviceControlDto) {
                var meta = deviceControlDto.getDeviceMetadata();
                meta.setDeviceImage(
                        ImageUtils.getScaledBase64OrElseOriginal(element.getDeviceMetadata().getDeviceImage(),
                                ImageStandards.DEVICE_CARD));
                ((DelegatedDeviceControlDto) dto).setDeviceMetadata(meta);
            }
            dtoSet.add(dto);
        }
        return dtoSet;
    }
}
