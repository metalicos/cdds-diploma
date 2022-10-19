package ua.com.cyberdone.devicemicroservice.device.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalConstants {
    /* Parameters */
    public static final String DEVICE_UUID_PARAMETER = "device-uuid";
    public static final String DELEGATION_STATUS_PARAMETER = "delegation-status";
    public static final String USERNAME_PARAMETER = "username";
    /* Default values */
    public static final String DEFAULT_COMMENT_FOR_DEVICE_CONTROL_DELEGATION = "Please grant permission to control the device.";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_ELEMENTS_AMOUNT = "20";
    public static final String DEFAULT_DIRECTION = "ASC";
    public static final String DEFAULT_SEARCH = "NONE";
    /* Responses */
    public static final String OK = "OK";

    public record HydroponicSettV1Constants() {
        public static final String ACTIVE_SETTINGS_TYPE = "ACTIVE";
        public static final String DISPENSERS_SETTINGS_TYPE = "DISPENSERS_SETTINGS_TEMPLATE";
        public static final String DISPENSE_SCHEDULES_SETTINGS_TYPE = "DISPENSE_SCHEDULES_SETTINGS_TEMPLATE";
        public static final String EC_SENSOR_SETTINGS_TYPE = "EC_SENSOR_SETTINGS_TEMPLATE";
        public static final String PH_SENSOR_SETTINGS_TYPE = "PH_SENSOR_SETTINGS_TEMPLATE";
        public static final String SYSTEM_SETTINGS_TYPE = "SYSTEM_SETTINGS_TEMPLATE";
        public static final String SPECIAL_SYSTEM_SETTINGS_TYPE = "SPECIAL_SYSTEM_SETTINGS_TEMPLATE";
        public static final String ALL_SETTINGS_TYPE = "ALL_SETTINGS_TEMPLATE";
    }
}
