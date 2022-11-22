package ua.com.cyberdone.devicemicroservice.device.service;

public interface ModelEntityMapper<Entity, DTO> {

    DTO mapToDTO(final Entity data, final DTO dataDTO);

    Entity mapToEntity(final DTO dataDTO, final Entity data);
}
