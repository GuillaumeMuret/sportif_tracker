package com.neos.trackandroll.cervo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String FORMAT_DATE_TRACK_AND_ROLL = "yyyy-MM-dd'T'kk:mm:ssZ";
    public static final String FORMAT_DATE_DEFAULT_SESSION = "yyyy_MM_dd_kk:mm:ss";
    public static final String FORMAT_DATE_GMT_MIDNIGHT = "yyyy-MM-dd'T'00:00:00Z";
    public static final String FRENCH_DATE_FORMAT = "ccc dd MMM yyyy";
    public static final String FORMAT_DATE_COMPARE_DAY = "yyyyMMdd";

    /**
     * Getter of the date from string
     *
     * @param strDate   : the string date
     * @param strFormat : the format to parse the date
     * @return : the date
     */
    public static Date getDateFromString(String strDate, String strFormat) {
        DateFormat format = new SimpleDateFormat(strFormat, Locale.ENGLISH);
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Process called to get the string from the date
     *
     * @param date : the date
     * @return : the string from date
     */
    public static String getStringFromDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.FORMAT_DATE_TRACK_AND_ROLL, Locale.ENGLISH);
        return dateFormat.format(date);
    }

    /**
     * Getter of the string from time in millis
     *
     * @param timeInMillis : the time in millis
     * @return : the time in millisecond
     */
    public static String getStringFromTimeInMillis(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return getStringFromDate(calendar.getTime());
    }
}