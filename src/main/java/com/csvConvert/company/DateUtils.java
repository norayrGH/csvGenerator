package com.csvConvert.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils
{

    private static final String UTC                  = "UTC";

    private static TimeZone     gmtTimeZone          = TimeZone.getTimeZone("GMT");

    /**
     * Default date formatting pattern used in method {@code DateUtils.formatGMT(date)} : {@code "yyyy-MM-dd HH:mm:ss z"}
     */
    public static final String  DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss z";

    private DateUtils()
    {
    }

    /**
     * Formats a date according to given pattern in GMT time zone. 
     * @param date
     * @return null when date argument is null, regardless of pattern value.
     * @throws IllegalArgumentException when pattern is invalid.
     */
    public static String formatGMT(Date date, String pattern)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat sdformat = new SimpleDateFormat(pattern);
        sdformat.setTimeZone(gmtTimeZone);
        return sdformat.format(date);
    }

    /**
     * Formats a date in GMT time zone using default pattern. 
     * @param date
     * @return null when date argument is null.
     */
    public static String formatGMT(Date date)
    {
        return formatGMT(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * Formats a date in a MMYY pattern
     * @param date
     * @return <code>null</code> if the given date is <code>null</code>
     */
	public static String formatMMYY(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat formatter = dateFormatter.get();
		return formatter.format(date);
	}

	/**
	 * Convert a MMYY string to a end of month date  
	 * 
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseMMYYToEndOfMonthDate(String dateString) {

		if (dateString == null) {
			return null;
		}
		Date dateToReturn = null;
		DateFormat formatter = dateFormatter1.get();
		try {
			dateToReturn = formatter.parse(dateString);
			//dateToReturn = shiftToEndOfMonth(dateToReturn);
		} catch (ParseException e) {

			return null;
		}
		return dateToReturn;
	}    

    /**
     * Formats a date in a YYYYMMDD pattern
     * @param date
     * @return <code>null</code> if the given date is <code>null</code>
     */
    public static String formatYYYYMMDD(Date date)
    {
        if (date == null)
        {
            return null;
        }
        DateFormat formatter = dateFormatter1.get();
        return formatter.format(date);
    }

    /**
     * Shifts the given date to the last day of the month just before midnight.
     * @param date
     * @return <code>null</code> if the given date is <code>null</code>
     */
    public static Date shiftToEndOfMonth(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        int maxDayInMonth;

        calendar.setTime(date);
        maxDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxDayInMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    /**
     * Returns the difference in plain months between the given dates.
     * <br>
     * This method ignores the days of each date and behaves like computing
     * the dfference in months of two dates of the given form : MM/YY.
     * <br><br>
     * The returned integer is:
     * <ul>
     * <li>positive if date1 is after date2 and both dates;
     * <li>equals to zero if both dates have same year and month values;
     * <li>negative otherwise.
     * </ul>
     * @param date1
     * @param date2
     * @return the span in months between date1 and date2
     */
    public static int getMonthSpan(Date date1, Date date2)
    {
        int span = 0;
        boolean naturalOrder;
        Calendar lBound = Calendar.getInstance();
        Calendar uBound = Calendar.getInstance();
        int maxMonths;

        naturalOrder = !(date1.after(date2));
        if (naturalOrder)
        {
            lBound.setTime(date1);
            uBound.setTime(date2);
        }
        else
        {
            lBound.setTime(date2);
            uBound.setTime(date1);
        }

        maxMonths = lBound.getActualMaximum(Calendar.MONTH);
        while (lBound.get(Calendar.YEAR) != uBound.get(Calendar.YEAR))
        {
            int tmpMonths = maxMonths - lBound.get(Calendar.MONTH) + 1;
            lBound.add(Calendar.MONTH, tmpMonths);
            span += tmpMonths;
        }

        // Add one to the resulting span to take into account the current month
        span += uBound.get(Calendar.MONTH) - lBound.get(Calendar.MONTH) + 1;

        if (!naturalOrder)
        {
            span *= -1;
        }

        return span;
    }

    /**
     * Shifts the given date to the start time i.e. @midnight.
     * @param date
     * @return <code>null</code> if the given date is <code>null</code>
     */
    public static Date shiftTimeToStartOfDay(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
    * converts date to SQL date
    * @param date
    * @return <code>null</code> if the given date is <code>null</code>
    */
    public static java.sql.Date convertToSqlDate(Date date)
    {
        java.sql.Date sqlDate = null;
        if (null != date)
        {
            sqlDate = new java.sql.Date(date.getTime());
        }
        return sqlDate;
    }

    /**
     * This method is used to add days in a given date.
     * 
     * @param date
     * @return
     */
    public static Date addDays(Date date, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +days);

        return calendar.getTime();
    }

    /**
     * This method is use to get the greatest date.
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static Date getGreatestDate(Date date1, Date date2)
    {
        Date maxDate;
        if (date1.compareTo(date2) > 0)
        {
            maxDate = date1;
        }
        else
        {
            maxDate = date2;
        }
        return maxDate;
    }

    /**
     * This method is use to get the greatest date from the given list.
     * 
     * @param dates
     * @return
     */
    public static Date getGreatestDate(List<Date> dates)
    {
        Collections.sort(dates);

        return dates.get(dates.size() - 1);
    }

    /**
     * To get UTC Date and time as date object
     * 
     * @param date
     * @return
     */
    public static Date getUTCdatetimeAsDate(Date date)
    {
        return stringDateToDate(getUTCdatetimeAsString(date));
    }

    /**
     * To get UTC Date and time as string
     * 
     * @param date
     * @return
     */
    public static String getUTCdatetimeAsString(Date date)
    {
        final SimpleDateFormat sdf = dateFormatterDefault.get();
        sdf.setTimeZone(TimeZone.getTimeZone(UTC));
        final String utcTime = sdf.format(date);
        return utcTime;
    }

    /**
     * To get string date as date value
     * 
     * @param strDate
     * @return
     */
    private static Date stringDateToDate(String strDate)
    {
        Date dateToReturn = null;
        SimpleDateFormat dateFormat = dateFormatterDefault.get();
        try
        {
            dateToReturn = (Date) dateFormat.parse(strDate);
        }
        catch (ParseException e) //Exception is replaced by ParseException for CREH
        {

            dateToReturn = null;
        }
        return dateToReturn;
    }

    /**
     * To get Local Date and time as date object
     * 
     * @param date
     * @return
     */
    public static Date getLocaldatetimeAsDate(Date date)
    {
        return stringDateToDate(getLocaldatetimeAsString(date));
    }

    /**
     * To get Local Date and time as string
     * 
     * @param date
     * @return
     */
    public static String getLocaldatetimeAsString(Date date)
    {
        final SimpleDateFormat sdf = dateFormatterDefault.get();
        Calendar cal = Calendar.getInstance();
        sdf.setTimeZone(cal.getTimeZone());
        final String utcTime = sdf.format(date);
        return utcTime;
    }

    static ThreadLocal<SimpleDateFormat> dateFormatter     = new ThreadLocal<SimpleDateFormat>()
    {
        protected SimpleDateFormat initialValue()
        {
            
         return new SimpleDateFormat("MMyy");
        }
    };
   
    static ThreadLocal<SimpleDateFormat> dateFormatter1     = new ThreadLocal<SimpleDateFormat>()
    {
        protected SimpleDateFormat initialValue()
        {
            
         return new SimpleDateFormat("yyyyMMdd");
        }
    };
    static ThreadLocal<SimpleDateFormat> dateFormatterDefault     = new ThreadLocal<SimpleDateFormat>()
    {
        protected SimpleDateFormat initialValue()
        {
            
         return new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }
    };
  
    /**
     * Shifts the given date to the end of the day.
     * @param date
     * @return <code>null</code> if the given date is <code>null</code>
     */
    public static Date shiftToEndOfDay(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }
    
    public static Date subtractDays(Date date, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    public static Date addMonths(Date date, int month)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +month);
       
        return calendar.getTime();
    }
    
 
}
