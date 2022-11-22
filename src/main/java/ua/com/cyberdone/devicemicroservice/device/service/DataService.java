package ua.com.cyberdone.devicemicroservice.device.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.cyberdone.devicemicroservice.device.domain.Data;
import ua.com.cyberdone.devicemicroservice.device.domain.Device;
import ua.com.cyberdone.devicemicroservice.device.model.DataDTO;
import ua.com.cyberdone.devicemicroservice.device.repos.DataRepository;
import ua.com.cyberdone.devicemicroservice.device.repos.DeviceRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataService implements ModelEntityMapper<Data, DataDTO> {
    private final DataRepository dataRepository;
    private final DeviceRepository deviceRepository;

    public Page<DataDTO> findAll(Integer page, Integer size) {
        return new PageImpl<>(dataRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.asc("id"))))
                .stream()
                .map(data -> mapToDTO(data, new DataDTO()))
                .collect(Collectors.toList()));
    }

    public DataDTO get(final Long id) {
        return dataRepository.findById(id)
                .map(data -> mapToDTO(data, new DataDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DataDTO dataDTO) {
        final Data data = new Data();
        mapToEntity(dataDTO, data);
        return dataRepository.save(data).getId();
    }

    public void update(final Long id, final DataDTO dataDTO) {
        final Data data = dataRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(dataDTO, data);
        dataRepository.save(data);
    }

    public void delete(final Long id) {
        dataRepository.deleteById(id);
    }

    @Override
    public DataDTO mapToDTO(final Data data, final DataDTO dataDTO) {
        dataDTO.setId(data.getId());
        dataDTO.setEcSolution(data.getEcSolution());
        dataDTO.setPhSolution(data.getPhSolution());
        dataDTO.setTSolution(data.getTSolution());
        dataDTO.setTAir(data.getTAir());
        dataDTO.setHumidityAir(data.getHumidityAir());
        dataDTO.setAtmosphericPressure(data.getAtmosphericPressure());
        dataDTO.setCreatedTimestamp(data.getCreatedTimestamp());
        dataDTO.setUpdatedTimestamp(data.getUpdatedTimestamp());
        dataDTO.setDeviceUuid(data.getDevice() == null ? null : data.getDevice().getUuid());
        return dataDTO;
    }

    @Override
    public Data mapToEntity(final DataDTO dataDTO, final Data data) {
        data.setEcSolution(dataDTO.getEcSolution());
        data.setPhSolution(dataDTO.getPhSolution());
        data.setTSolution(dataDTO.getTSolution());
        data.setTAir(dataDTO.getTAir());
        data.setHumidityAir(dataDTO.getHumidityAir());
        data.setAtmosphericPressure(dataDTO.getAtmosphericPressure());
        data.setCreatedTimestamp(dataDTO.getCreatedTimestamp());
        data.setUpdatedTimestamp(dataDTO.getUpdatedTimestamp());
        final Device device = dataDTO.getDeviceUuid() == null ? null : deviceRepository.findByUuid(dataDTO.getDeviceUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "device not found"));
        data.setDevice(device);
        return data;
    }

}
