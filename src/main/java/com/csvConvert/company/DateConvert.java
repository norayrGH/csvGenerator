package com.csvConvert.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
    public static final String  DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String  DEFAULT_DATE_PATTERN_WITHOUT_TIME = "yyyy-MM-dd";
    public static Date parseYYYYMMDDHHMMSSToDate(String dateString) {

        if (dateString == null) {
            return null;
        }
        Date dateToReturn;
        DateFormat formatter = dateFormatter2.get();
        try {
            dateToReturn = formatter.parse(dateString);

        } catch (ParseException e) {

            return null;
        }
        return dateToReturn;
    }
    static ThreadLocal<SimpleDateFormat> dateFormatterDefault     = new ThreadLocal<SimpleDateFormat>()
    {
        protected SimpleDateFormat initialValue()
        {

            return new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    };

    static ThreadLocal<SimpleDateFormat> dateFormatter2     = new ThreadLocal<SimpleDateFormat>()
    {
        protected SimpleDateFormat initialValue()
        {

            return new SimpleDateFormat(DEFAULT_DATE_PATTERN_WITHOUT_TIME);
        }
    };
}
