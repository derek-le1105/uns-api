package com.uns.api.backend.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
    public static String getCheckingDate() {
        LocalDate date = LocalDate.now();
        DayOfWeek day = date.getDayOfWeek();

        switch (day) {
            case SATURDAY:
                date = date.minusDays(3);
                break;
            case SUNDAY:
                date = date.minusDays(3);
                break;
            case MONDAY:
                date = date.minusDays(3);
                break;
            default:
                date = date.minusDays(1);
                break;
        }
        return date.toString();
    }
}
