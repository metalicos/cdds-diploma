package ua.com.cyberdone.devicemicroservice.topic.hydroponic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum HydroponicTopicEnum {
    PH_UP_PUMP("phUpPump"),
    PH_DOWN_PUMP("phDownPump"),
    TDS_PUMP("tdsPump"),
    ML_PER_MILLISECOND("mlPerMillisecond"),
    REGULATE_ERROR_PH("regulateErrorPh"),
    REGULATE_ERROR_TDS("regulateErrorTds"),
    PH_UP_DOSE_ML("phUpDoseMl"),
    PH_DOWN_DOSE_ML("phDownDoseMl"),
    TDS_DOSE_ML("tdsDoseMl"),
    RECHECK_DISPENSERS_AFTER_MS("recheckDispensersAfterMs"),
    SETUP_PH_VALUE("setupPhValue"),
    SETUP_TDS_VALUE("setupTdsValue"),
    DISPENSERS_ENABLE("dispensersEnable"),
    SENSORS_ENABLE("sensorsEnable"),
    CALIBRATE_PH_LOW("calibratePhLow"),
    CALIBRATE_PH_HIGH("calibratePhHigh"),
    CALIBRATE_PH_CLEAR("calibratePhClear"),
    CALIBRATE_TDS("calibrateTds"),
    CALIBRATE_TDS_CLEAR("calibrateTdsClear"),
    PH_CALIBRATION_POINT("phCalibrationPoint"),
    PH_CALIBRATION_VALUE1("phCalibrationValue1"),
    PH_CALIBRATION_VALUE2("phCalibrationValue2"),
    PH_CALIBRATION_ADC1("phCalibrationAdc1"),
    PH_CALIBRATION_ADC2("phCalibrationAdc2"),
    PH_CALIBRATION_SLOPE("phCalibrationSlope"),
    PH_CALIBRATION_ADC_OFFSET("phCalibrationAdcOffset"),
    PH_OVERSAMPLING("phOversampling"),
    TDS_KVALUE("tdsKValue"),
    TDS_OVERSAMPLING("tdsOversampling"),
    UNKNOWN("");

    private String val;

    public static HydroponicTopicEnum topic(String val) {
        return Arrays.stream(HydroponicTopicEnum.values()).filter(t -> t.val.equals(val)).findFirst().orElse(UNKNOWN);
    }
}
