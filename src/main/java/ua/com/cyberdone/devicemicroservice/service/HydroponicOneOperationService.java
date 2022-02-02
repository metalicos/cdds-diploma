package ua.com.cyberdone.devicemicroservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum;

import static ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType.NONE;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.CALIBRATE_PH_CLEAR;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.CALIBRATE_PH_HIGH;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.CALIBRATE_PH_LOW;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.CALIBRATE_TDS;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.CALIBRATE_TDS_CLEAR;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.DISPENSERS_ENABLE;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.ML_PER_MILLISECOND;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_CALIBRATION_ADC1;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_CALIBRATION_ADC2;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_CALIBRATION_ADC_OFFSET;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_CALIBRATION_POINT;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_CALIBRATION_SLOPE;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_CALIBRATION_VALUE1;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_CALIBRATION_VALUE2;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_DOWN_DOSE_ML;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_DOWN_PUMP;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_OVERSAMPLING;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_UP_DOSE_ML;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PH_UP_PUMP;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.RECHECK_DISPENSERS_AFTER_MS;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.REGULATE_ERROR_PH;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.REGULATE_ERROR_TDS;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.SENSORS_ENABLE;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.SETUP_PH_VALUE;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.SETUP_TDS_VALUE;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.TDS_DOSE_ML;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.TDS_KVALUE;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.TDS_OVERSAMPLING;
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.TDS_PUMP;

@Slf4j
@Service
public class HydroponicOneOperationService extends AbstractOperationService {

    public HydroponicOneOperationService(MqttService mqttService) {
        super(mqttService);
    }

    public void phUpPump(String uuid, DirectionEnum dir, ValueType type) {
        sendEncodedData(uuid, PH_UP_PUMP.getVal(), dir.getVal(), type);
    }

    public void phDownPump(String uuid, DirectionEnum dir, ValueType type) {
        sendEncodedData(uuid, PH_DOWN_PUMP.getVal(), dir.getVal(), type);
    }

    public void tdsPump(String uuid, DirectionEnum dir, ValueType type) {
        sendEncodedData(uuid, TDS_PUMP.getVal(), dir.getVal(), type);
    }

    public void calibrateTdsSensor(String uuid, String etaloneValue, ValueType type) {
        sendEncodedData(uuid, CALIBRATE_TDS.getVal(), etaloneValue, type);
    }

    public void clearCalibrationOfTdsSensor(String uuid) {
        sendEncodedData(uuid, CALIBRATE_TDS_CLEAR.getVal(), null, NONE);
    }

    public void calibratePhSensorLowPoint(String uuid, String etaloneValue, ValueType type) {
        sendEncodedData(uuid, CALIBRATE_PH_LOW.getVal(), etaloneValue, type);
    }

    public void calibratePhSensorHighPoint(String uuid, String etaloneValue, ValueType type) {
        sendEncodedData(uuid, CALIBRATE_PH_HIGH.getVal(), etaloneValue, type);
    }

    public void clearCalibrationOfPhSensor(String uuid) {
        sendEncodedData(uuid, CALIBRATE_PH_CLEAR.getVal(), null, NONE);
    }

    public void updateSetupPhValue(String uuid, String phValue, ValueType type) {
        sendEncodedData(uuid, SETUP_PH_VALUE.getVal(), phValue, type);
    }

    public void updateSetupTdsValue(String uuid, String tdsValue, ValueType type) {
        sendEncodedData(uuid, SETUP_TDS_VALUE.getVal(), tdsValue, type);
    }

    public void updateRecheckDispensersAfterTime(String uuid, String timeInMs, ValueType type) {
        sendEncodedData(uuid, RECHECK_DISPENSERS_AFTER_MS.getVal(), timeInMs, type);
    }

    public void updatePhUpDose(String uuid, String doseMl, ValueType type) {
        sendEncodedData(uuid, PH_UP_DOSE_ML.getVal(), doseMl, type);
    }

    public void updatePhDownDose(String uuid, String doseMl, ValueType type) {
        sendEncodedData(uuid, PH_DOWN_DOSE_ML.getVal(), doseMl, type);
    }

    public void updateTdsDose(String uuid, String doseMl, ValueType type) {
        sendEncodedData(uuid, TDS_DOSE_ML.getVal(), doseMl, type);
    }

    public void updateRegulatePhError(String uuid, String phError, ValueType type) {
        sendEncodedData(uuid, REGULATE_ERROR_PH.getVal(), phError, type);
    }

    public void updateRegulateTdsError(String uuid, String ppmError, ValueType type) {
        sendEncodedData(uuid, REGULATE_ERROR_TDS.getVal(), ppmError, type);
    }

    public void updatePumpSpeedMlPerMilliseconds(String uuid, String mlPerMillisecond, ValueType type) {
        sendEncodedData(uuid, ML_PER_MILLISECOND.getVal(), mlPerMillisecond, type);
    }

    public void updateSensorsEnable(String uuid, String makeEnable, ValueType type) {
        sendEncodedData(uuid, SENSORS_ENABLE.getVal(), makeEnable, type);
    }

    public void updateDispensersEnable(String uuid, String makeEnable, ValueType type) {
        sendEncodedData(uuid, DISPENSERS_ENABLE.getVal(), makeEnable, type);
    }

    public void phCalibrationPoint(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_CALIBRATION_POINT.getVal(), value, type);
    }

    public void phCalibrationValue1(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_CALIBRATION_VALUE1.getVal(), value, type);
    }

    public void phCalibrationValue2(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_CALIBRATION_VALUE2.getVal(), value, type);
    }

    public void phCalibrationAdc1(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_CALIBRATION_ADC1.getVal(), value, type);
    }

    public void phCalibrationAdc2(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_CALIBRATION_ADC2.getVal(), value, type);
    }

    public void phCalibrationSlope(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_CALIBRATION_SLOPE.getVal(), value, type);
    }

    public void phCalibrationAdcOffset(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_CALIBRATION_ADC_OFFSET.getVal(), value, type);
    }

    public void phOversampling(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, PH_OVERSAMPLING.getVal(), value, type);
    }

    public void tdsKValue(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, TDS_KVALUE.getVal(), value, type);
    }

    public void tdsOversampling(String uuid, String value, ValueType type) {
        sendEncodedData(uuid, TDS_OVERSAMPLING.getVal(), value, type);
    }

}
