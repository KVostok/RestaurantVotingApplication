package ru.kosmos.restaurantvoting.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtil {

    public static final String DATE_TIME_PATTERN = "HH-mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public static String toString(LocalTime lt) {
        return lt == null ? "" : lt.format(DATE_TIME_FORMATTER);
    }

}
