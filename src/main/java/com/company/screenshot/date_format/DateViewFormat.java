package com.company.screenshot.date_format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateViewFormat {
    public static String getCurrentDateWithTimeByPattern(String datePatten){
        DateFormat dateFormat = new SimpleDateFormat(datePatten);
        String currentDateWithTime = dateFormat.format(new Date());
        return currentDateWithTime;
    }
}
