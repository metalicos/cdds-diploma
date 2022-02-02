package ua.com.cyberdone.devicemicroservice.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ua.com.cyberdone.devicemicroservice.persistence.entity.ValueType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@Slf4j
@UtilityClass
public class MqttVariableEncoderDecoderUtils {
    private static final String ENCODE_NUMBER_SYMBOL = "#";
    private static final String ENCODE_STRING_SYMBOL = " ";
    private static final String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

    public static String encode(LocalDateTime now) {
        return ENCODE_NUMBER_SYMBOL + now.getYear() +
                ENCODE_NUMBER_SYMBOL + now.getMonthValue() +
                ENCODE_NUMBER_SYMBOL + now.getDayOfMonth() +
                ENCODE_NUMBER_SYMBOL + now.getHour() +
                ENCODE_NUMBER_SYMBOL + now.getMinute() +
                ENCODE_NUMBER_SYMBOL + now.getSecond() +
                ENCODE_NUMBER_SYMBOL;
    }

    public static String encodeText(String variable) {
        if (variable.contains(ENCODE_STRING_SYMBOL)) {
            throw new IllegalStateException("Symbol ' ' is used for encoding and is restricted for usage");
        }
        return ENCODE_STRING_SYMBOL + variable + ENCODE_STRING_SYMBOL;
    }

    public static String encodeNumber(String variable) {
        return ENCODE_NUMBER_SYMBOL + variable.replace(',', '.') + ENCODE_NUMBER_SYMBOL;
    }


    public static Date decode(String date) {
        try {
            return new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            log.error("Current format is: '{}' but input date was: '{}'", DATE_PATTERN, date);
            throw new IllegalStateException("Parsing error. " + e);
        }
    }

    public static String encodeConsideringToValueType(String data, ValueType type) {
        return switch (type) {
            case NUMBER, SWITCH, DIRECTION -> encodeNumber(data);
            case TEXT -> encodeText(data);
            case TIME -> encode(decode(data).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            case NONE -> "";
        };
    }
}
