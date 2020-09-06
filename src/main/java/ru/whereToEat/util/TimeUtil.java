package ru.whereToEat.util;

import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toDateFormatString(String date) {
        return date.replace(" ", "T");
    }

}
