package ru.whereToEat.util;

import ru.whereToEat.model.Meal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class TimeUtil {

    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toDateFormatString(String date) {
        return date.replace(" ", "T");
    }

    public static long LocalDateTimeToLong(LocalDateTime ldt) {
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    public static Predicate<Meal> filterOnStartAndEndLocalDateTime(LocalDateTime cur, LocalDateTime start,
                                                                   LocalDateTime end) {
        return (meal)-> cur.isEqual(start) || cur.isAfter(start) && cur.isBefore(end);
    }

}
