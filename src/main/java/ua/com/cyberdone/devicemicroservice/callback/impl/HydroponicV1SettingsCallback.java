package ua.com.cyberdone.devicemicroservice.callback.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.callback.Callback;
import ua.com.cyberdone.devicemicroservice.model.microcontrollers.hydroponic.HydroponicAllDataDto;
import ua.com.cyberdone.devicemicroservice.persistence.entity.DeviceSpecialInformation;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicCalibrationData;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicData;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.HydroponicSettings;
import ua.com.cyberdone.devicemicroservice.persistence.service.DeviceSpecialInformationService;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicCalibrationDataService;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicDataService;
import ua.com.cyberdone.devicemicroservice.persistence.service.HydroponicSettingsService;
import ua.com.cyberdone.devicemicroservice.service.EncDecService;

import javax.transaction.Transactional;
import java.io.UncheckedIOException;

@Slf4j
@RequiredArgsConstructor
@Service("device-microservice/hydroponic-v1")
public class HydroponicV1SettingsCallback implements Callback {
    private final HydroponicDataService hydroponicDataService;
    private final HydroponicSettingsService hydroponicSettingsService;
    private final HydroponicCalibrationDataService calibrationDataService;
    private final DeviceSpecialInformationService informationService;
    private final ObjectMapper mapper;
    private final ModelMapper modelMapper;
    private final EncDecService encDecService;
    @Value("${security.callback.show-decrypted-message.hydroponic-v1-settings}")
    private boolean showDecryptedMessage;

    @Override
    @SneakyThrows
    @Transactional
    public void execute(MqttClient client, MqttMessage message) {
        try {
            var decryptedData = encDecService.decrypt(new String(message.getPayload()));
            var allData = mapper.readValue(decryptedData, HydroponicAllDataDto.class);
            var hydroData = modelMapper.map(allData, HydroponicData.class);
            var hydroSettings = modelMapper.map(allData, HydroponicSettings.class);
            var hydroCalibrationData = modelMapper.map(allData, HydroponicCalibrationData.class);
            var hydroSpecialInfo = modelMapper.map(allData, DeviceSpecialInformation.class);
            if (showDecryptedMessage) {
                log.info("{}\n{}\n{}\n{}\n{}\n{}", decryptedData, allData, hydroData, hydroSettings, hydroCalibrationData, hydroSpecialInfo);
            }
            hydroponicDataService.saveData(hydroData);
            hydroponicSettingsService.saveSetting(hydroSettings);
            calibrationDataService.saveCalibrationData(hydroCalibrationData);
            informationService.saveSpecialInformation(hydroSpecialInfo);
        } catch (JsonProcessingException e) {
            log.error("Json Read fault ", e);
            throw new UncheckedIOException(e);
        }
    }
}
