package ua.com.cyberdone.devicemicroservice.validation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {
    public static final String TIMEZONE_PATTERN = "(^[^a-zA-Z].*$)|(^[0-9`~!@#$%^&*()-<>?/.,:;\"'|{}\\]\\[_+]+$)";

    public static final String UUID_PATTERN = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
    public static final String UUID_FAILED_MSG = "UUID is not valid. Cause: not a UUID";

    public static final String NUMBER_PATTERN = "^-?[0-9]{1,16}[\\.]?[0-9]{0,12}$";
    public static final String NUMBER_FAILED_MSG = "Number is not valid. Cause: not a number";

    public static final String TEXT_PATTERN = "^[^ ]+$";
    public static final String TEXT_FAILED_MSG = "Text is not valid. Cause: must not contain whitespaces";

    public static final String SWITCH_PATTERN = "^[01]$";
    public static final String SWITCH_FAILED_MSG = "Switch value is not valid. Cause: can be only 0 or 1";

    public static final String DIRECTION_PATTERN = "^-?[01]$";
    public static final String DIRECTION_FAILED_MSG = "Direction value is not valid. Cause: can be only 0, 1 or -1";


    public static final String VALUE_IS_BLANK_MSG = "Value is not valid. Cause: blank";
    public static final String VALUE_IS_NULL_MSG = "Value is not valid. Cause: null";
    public static final String VALUE_NOT_NUMBER_MSG = "Value is not valid. Cause: not number";
    public static final String VALUE_NOT_TIMEZONE_MSG = "Value is not valid. Cause: not a timezone";
    public static final String NOT_POSITIVE_MSG = "Value is not valid. Cause: not positive";
}
