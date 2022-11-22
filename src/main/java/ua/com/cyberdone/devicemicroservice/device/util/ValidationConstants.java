package ua.com.cyberdone.devicemicroservice.device.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {
    /* Regex Patterns */
    public static final String UUID_PATTERN = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
    public static final String NUMBER_PATTERN = "^-?[0-9]{1,16}[\\.]?[0-9]{0,12}$";
    public static final String SORT_DIRECTION_PATTERN = "(ASC)|(DESC)";
    public static final String TEXT_PATTERN = "^[^ ]+$";
    public static final String SWITCH_PATTERN = "^[01]$";
    public static final String DIRECTION_PATTERN = "^[012]$";
    public static final String EMAIL_RGX = "^[A-z0-9.-]+[A-z0-9]@[A-z0-9][A-z0-9.-]+\\.[A-z]{2,}$";
    /* Constraints Fail Messages*/
    public static final String UUID_FAILED_MSG = "UUID is not valid. Cause: not a UUID";
    public static final String SORT_DIRECTION_FAILED_MSG = "Direction is invalid. Should be ASC or DESC.";
    public static final String NUMBER_FAILED_MSG = "Number is not valid. Cause: not a number";
    public static final String TEXT_FAILED_MSG = "Text is not valid. Cause: must not contain whitespaces";
    public static final String SWITCH_FAILED_MSG = "Switch value is not valid. Cause: can be only 0 or 1";
    public static final String DIRECTION_FAILED_MSG = "Direction value is not valid. Cause: can be only 0, 1 or 2";
    public static final String VALUE_IS_BLANK_MSG = "Value is not valid. Cause: blank";
    public static final String VALUE_IS_NULL_MSG = "Value is not valid. Cause: null";
    public static final String VALUE_NOT_NUMBER_MSG = "Value is not valid. Cause: not number";
    public static final String NOT_POSITIVE_MSG = "Value is not valid. Cause: not positive";
    public static final String NOT_POSITIVE_OR_ZERO_MSG = "Value is not valid. Cause: not zero or not positive number";
    public static final String EMAIL_FAIL_MESSAGE = "Email is invalid";
}
