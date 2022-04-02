package ua.com.cyberdone.devicemicroservice.service.control.hydroponic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;
import ua.com.cyberdone.devicemicroservice.service.control.ScheduleControllable;
import ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum;

import static ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum.direction;

@Slf4j
@Service
@RequiredArgsConstructor
public class HydroponicScheduleControlService implements ScheduleControllable {
    private final HydroponicOneOperationService operationService;

    @Override
    public void control(ValueType type, String data, String topic, String uuid) {
        controlCommonTopics(operationService, type, data, topic, uuid, log);
        switch (HydroponicTopicEnum.topic(topic)) {
            case PH_UP_PUMP -> operationService.phUpPump(uuid, direction(data), type);
            case PH_DOWN_PUMP -> operationService.phDownPump(uuid, direction(data), type);
            case TDS_PUMP -> operationService.tdsPump(uuid, direction(data), type);
            case ML_PER_MILLISECOND -> operationService.updatePumpSpeedMlPerMilliseconds(uuid, data, type);
            case REGULATE_ERROR_PH -> operationService.updateRegulatePhError(uuid, data, type);
            case REGULATE_ERROR_TDS -> operationService.updateRegulateTdsError(uuid, data, type);
            case PH_UP_DOSE_ML -> operationService.updatePhUpDose(uuid, data, type);
            case PH_DOWN_DOSE_ML -> operationService.updatePhDownDose(uuid, data, type);
            case TDS_DOSE_ML -> operationService.updateTdsDose(uuid, data, type);
            case RECHECK_DISPENSERS_AFTER_MS -> operationService.updateRecheckDispensersAfterTime(uuid, data, type);
            case SETUP_PH_VALUE -> operationService.updateSetupPhValue(uuid, data, type);
            case SETUP_TDS_VALUE -> operationService.updateSetupTdsValue(uuid, data, type);
            case DISPENSERS_ENABLE -> operationService.updateDispensersEnable(uuid, data, type);
            case SENSORS_ENABLE -> operationService.updateSensorsEnable(uuid, data, type);
            case CALIBRATE_PH_LOW -> operationService.calibratePhSensorLowPoint(uuid, data, type);
            case CALIBRATE_PH_HIGH -> operationService.calibratePhSensorHighPoint(uuid, data, type);
            case CALIBRATE_PH_CLEAR -> operationService.clearCalibrationOfPhSensor(uuid);
            case CALIBRATE_TDS -> operationService.calibrateTdsSensor(uuid, data, type);
            case CALIBRATE_TDS_CLEAR -> operationService.clearCalibrationOfTdsSensor(uuid);
            case PH_CALIBRATION_POINT -> operationService.phCalibrationPoint(uuid, data, type);
            case PH_CALIBRATION_VALUE1 -> operationService.phCalibrationValue1(uuid, data, type);
            case PH_CALIBRATION_VALUE2 -> operationService.phCalibrationValue2(uuid, data, type);
            case PH_CALIBRATION_ADC1 -> operationService.phCalibrationAdc1(uuid, data, type);
            case PH_CALIBRATION_ADC2 -> operationService.phCalibrationAdc2(uuid, data, type);
            case PH_CALIBRATION_SLOPE -> operationService.phCalibrationSlope(uuid, data, type);
            case PH_CALIBRATION_ADC_OFFSET -> operationService.phCalibrationAdcOffset(uuid, data, type);
            case PH_OVERSAMPLING -> operationService.phOversampling(uuid, data, type);
            case TDS_KVALUE -> operationService.tdsKValue(uuid, data, type);
            case TDS_OVERSAMPLING -> operationService.tdsOversampling(uuid, data, type);
            case UNKNOWN -> log.info("Topic={} is not of type HydroponicTopic", topic);
        }
    }
}
