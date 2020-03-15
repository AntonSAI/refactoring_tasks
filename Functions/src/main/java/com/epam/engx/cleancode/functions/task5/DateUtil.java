package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private Calendar calendar;

    public Date changeToMidnight(Date date, boolean isMidnight) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, isMidnight ? 1 : -1);
        setTimeParameter();
        return calendar.getTime();
    }

    private void setTimeParameter() {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
