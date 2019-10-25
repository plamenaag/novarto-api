package com.plamena.novartoapi.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public abstract class TimeUtil {

    public static Boolean datesAreEqual(Timestamp timestamp1, Timestamp timestamp2) {
        Date date1 = new Date(timestamp1.getTime());
        Date date2 = new Date(timestamp2.getTime());
        return date1.compareTo(date2) == 0;
    }

    public static Timestamp addDays(Timestamp timestamp, Integer numberOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return new Timestamp(cal.getTime().getTime());
    }
}
