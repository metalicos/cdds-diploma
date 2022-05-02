package ua.com.cyberdone.devicemicroservice.service.control.hydroponic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.devicemicroservice.configuration.SendOperationConfig;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;
import ua.com.cyberdone.devicemicroservice.persistence.entity.hydroponic.DirectionEnum;
import ua.com.cyberdone.devicemicroservice.service.MqttService;
import ua.com.cyberdone.devicemicroservice.service.control.BaseOperationService;

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
import static ua.com.cyberdone.devicemicroservice.topic.hydroponic.HydroponicTopicEnum.PUMP_POLARITY;
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
public class HydroponicOneOperationService extends BaseOperationService {

    public HydroponicOneOperationService(MqttService mqttService, SendOperationConfig sendOperationConfig) {
        super(mqttService, sendOperationConfig);
    }

    public void phUpPump(String uuid, DirectionEnum dir, ValueType type) {
        log.info("Pump Ph Up; direction={} uuid={}", dir, uuid);
        sendEncodedData(uuid, PH_UP_PUMP.getVal(), dir.getVal(), type);
    }

    public void phDownPump(String uuid, DirectionEnum dir, ValueType type) {
        log.info("Pump Ph Down; direction={} uuid={}", dir, uuid);
        sendEncodedData(uuid, PH_DOWN_PUMP.getVal(), dir.getVal(), type);
    }

    public void tdsPump(String uuid, DirectionEnum dir, ValueType type) {
        log.info("Pump Tds; direction={} uuid={}", dir, uuid);
        sendEncodedData(uuid, TDS_PUMP.getVal(), dir.getVal(), type);
    }

    public void calibrateTdsSensor(String uuid, String referenceValue, ValueType type) {
        log.info("Calibrate TDS; referenceValue={} uuid={}", referenceValue, uuid);
        sendEncodedData(uuid, CALIBRATE_TDS.getVal(), referenceValue, type);
    }

    public void clearCalibrationOfTdsSensor(String uuid) {
        log.info("Calibrate TDS Clear uuid={}", uuid);
        sendEncodedData(uuid, CALIBRATE_TDS_CLEAR.getVal(), null, NONE);
    }

    public void calibratePhSensorLowPoint(String uuid, String referenceValue, ValueType type) {
        log.info("Calibrate Ph LOW; referenceValue={} uuid={}", referenceValue, uuid);
        sendEncodedData(uuid, CALIBRATE_PH_LOW.getVal(), referenceValue, type);
    }

    public void calibratePhSensorHighPoint(String uuid, String referenceValue, ValueType type) {
        log.info("Calibrate Ph HIGH; referenceValue={} uuid={}", referenceValue, uuid);
        sendEncodedData(uuid, CALIBRATE_PH_HIGH.getVal(), referenceValue, type);
    }

    public void clearCalibrationOfPhSensor(String uuid) {
        log.info("Calibrate Ph Clear uuid={}", uuid);
        sendEncodedData(uuid, CALIBRATE_PH_CLEAR.getVal(), null, NONE);
    }

    public void updateSetupPhValue(String uuid, String phValue, ValueType type) {
        log.info("Setup Ph; phValue={} uuid={}", phValue, uuid);
        sendEncodedData(uuid, SETUP_PH_VALUE.getVal(), phValue, type);
    }

    public void updateSetupTdsValue(String uuid, String tdsValue, ValueType type) {
        log.info("Setup TDS; tdsValue={} uuid={}", tdsValue, uuid);
        sendEncodedData(uuid, SETUP_TDS_VALUE.getVal(), tdsValue, type);
    }

    public void updateRecheckDispensersAfterTime(String uuid, String timeInMs, ValueType type) {
        log.info("Recheck Time; timeInMs={} uuid={}", timeInMs, uuid);
        sendEncodedData(uuid, RECHECK_DISPENSERS_AFTER_MS.getVal(), timeInMs, type);
    }

    public void updatePhUpDose(String uuid, String doseMl, ValueType type) {
        log.info("Dose Ph UP; doseMl={} uuid={}", doseMl, uuid);
        sendEncodedData(uuid, PH_UP_DOSE_ML.getVal(), doseMl, type);
    }

    public void updatePhDownDose(String uuid, String doseMl, ValueType type) {
        log.info("Dose Ph DOWN; doseMl={} uuid={}", doseMl, uuid);
        sendEncodedData(uuid, PH_DOWN_DOSE_ML.getVal(), doseMl, type);
    }

    public void updateTdsDose(String uuid, String doseMl, ValueType type) {
        log.info("Dose TDS; doseMl={} uuid={}", doseMl, uuid);
        sendEncodedData(uuid, TDS_DOSE_ML.getVal(), doseMl, type);
    }

    public void updateRegulatePhError(String uuid, String phError, ValueType type) {
        log.info("Reg Error Ph; phError={} uuid={}", phError, uuid);
        sendEncodedData(uuid, REGULATE_ERROR_PH.getVal(), phError, type);
    }

    public void updateRegulateTdsError(String uuid, String tdsError, ValueType type) {
        log.info("Reg Error TDS; tdsError={} uuid={}", tdsError, uuid);
        sendEncodedData(uuid, REGULATE_ERROR_TDS.getVal(), tdsError, type);
    }

    public void updatePumpSpeedMlPerMilliseconds(String uuid, String mlPerMillisecond, ValueType type) {
        log.info("Pump speed ml/ms; ml_per_ms={} uuid={}", mlPerMillisecond, uuid);
        sendEncodedData(uuid, ML_PER_MILLISECOND.getVal(), mlPerMillisecond, type);
    }

    public void updateSensorsEnable(String uuid, String makeEnable, ValueType type) {
        log.info("Enable sensors; makeEnable={} uuid={}", makeEnable, uuid);
        sendEncodedData(uuid, SENSORS_ENABLE.getVal(), makeEnable, type);
    }

    public void updateDispensersEnable(String uuid, String makeEnable, ValueType type) {
        log.info("Enable Dispensers; makeEnable={} uuid={}", makeEnable, uuid);
        sendEncodedData(uuid, DISPENSERS_ENABLE.getVal(), makeEnable, type);
    }

    public void phCalibrationPoint(String uuid, String phCalibrationPoint, ValueType type) {
        log.info("Update ph calibration; phCalibrationPoint={} uuid={}", phCalibrationPoint, uuid);
        sendEncodedData(uuid, PH_CALIBRATION_POINT.getVal(), phCalibrationPoint, type);
    }

    public void phCalibrationValue1(String uuid, String phCalibrationValue1, ValueType type) {
        log.info("Update ph calibration; phCalibrationValue1={} uuid={}", phCalibrationValue1, uuid);
        sendEncodedData(uuid, PH_CALIBRATION_VALUE1.getVal(), phCalibrationValue1, type);
    }

    public void phCalibrationValue2(String uuid, String phCalibrationValue2, ValueType type) {
        log.info("Update ph calibration; phCalibrationValue2={} uuid={}", phCalibrationValue2, uuid);
        sendEncodedData(uuid, PH_CALIBRATION_VALUE2.getVal(), phCalibrationValue2, type);
    }

    public void phCalibrationAdc1(String uuid, String phCalibrationAdc1, ValueType type) {
        log.info("Update ph calibration; phCalibrationAdc1={} uuid={}", phCalibrationAdc1, uuid);
        sendEncodedData(uuid, PH_CALIBRATION_ADC1.getVal(), phCalibrationAdc1, type);
    }

    public void phCalibrationAdc2(String uuid, String phCalibrationAdc2, ValueType type) {
        log.info("Update ph calibration; phCalibrationAdc2={} uuid={}", phCalibrationAdc2, uuid);
        sendEncodedData(uuid, PH_CALIBRATION_ADC2.getVal(), phCalibrationAdc2, type);
    }

    public void phCalibrationSlope(String uuid, String phCalibrationSlope, ValueType type) {
        log.info("Update ph calibration; phCalibrationSlope={} uuid={}", phCalibrationSlope, uuid);
        sendEncodedData(uuid, PH_CALIBRATION_SLOPE.getVal(), phCalibrationSlope, type);
    }

    public void phCalibrationAdcOffset(String uuid, String phCalibrationAdcOffset, ValueType type) {
        log.info("Update ph calibration; phCalibrationAdcOffset={} uuid={}", phCalibrationAdcOffset, uuid);
        sendEncodedData(uuid, PH_CALIBRATION_ADC_OFFSET.getVal(), phCalibrationAdcOffset, type);
    }

    public void phOversampling(String uuid, String phOversampling, ValueType type) {
        log.info("Update ph calibration; phOversampling={} uuid={}", phOversampling, uuid);
        sendEncodedData(uuid, PH_OVERSAMPLING.getVal(), phOversampling, type);
    }

    public void tdsKValue(String uuid, String tdsKValue, ValueType type) {
        log.info("Update tds calibration; tdsKValue={} uuid={}", tdsKValue, uuid);
        sendEncodedData(uuid, TDS_KVALUE.getVal(), tdsKValue, type);
    }

    public void tdsOversampling(String uuid, String tdsOversampling, ValueType type) {
        log.info("Update tds calibration; tdsKValue={} uuid={}", tdsOversampling, uuid);
        sendEncodedData(uuid, TDS_OVERSAMPLING.getVal(), tdsOversampling, type);
    }

    public void pumpPolarity(String uuid, String pumpNumber) {
        log.info("Update pump={} polarity uuid={}", pumpNumber, uuid);
        sendEncodedData(uuid, String.format(PUMP_POLARITY.getVal(), pumpNumber), null, NONE);
    }
}
