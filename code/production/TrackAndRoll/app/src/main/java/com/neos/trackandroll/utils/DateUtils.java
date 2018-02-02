package com.neos.trackandroll.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils extends android.text.format.DateUtils{

    public static final String FORMAT_DATE_TRACK_AND_ROLL = "yyyy-MM-dd'T'kk:mm:ssZ";
    public static final String FORMAT_DATE_DEFAULT_SESSION = "yyyy_MM_dd_kkmmss";
    public static final String FORMAT_DATE_GMT_MIDNIGHT = "yyyy-MM-dd'T'00:00:00Z";

    private final static String FRENCH_DATE_FORMAT = "ccc dd MMM yyyy";

    public static final String FORMAT_DATE_COMPARE_DAY = "yyyyMMdd";

    /**
     * Method that gets date from string
     * @param strDate : the date
     * @param strFormat : the format
     * @return the date in string format
     */
    public static Date getDateFromString(String strDate, String strFormat){
        DateFormat format = new SimpleDateFormat(strFormat, Locale.ENGLISH);
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method that converts a time to hour/minutes/seconds
     * @param c1 : beginning time
     * @param c2 : ending time
     * @return : the time
     */
    public static String convertTimeToHourMinSecond(Calendar c1, Calendar c2){
        long elapsedTime = c2.getTimeInMillis() - c1.getTimeInMillis();
        return convertSecondsToFormatApp((int)elapsedTime/1000);
    }

    /**
     * Method that converts time from sec to hour/minutes/seconds
     * @param timeInSec : the time in sec
     * @return : the time
     */
    public static String convertTimeToHourMinSecond(int timeInSec){
        return convertSecondsToFormatApp(timeInSec);
    }

    /**
     * Method that converts a time to app format
     * @param timeInSeconds : the time in seconds
     * @return the time in app format
     */
    public static String convertSecondsToFormatApp(int timeInSeconds){
        int hours = timeInSeconds / 3600;
        int minutes = (timeInSeconds % 3600) / 60;
        int seconds = timeInSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Method that gets a string from a date
     * @param date : the date
     * @return the string
     */
    public static String getStringFromDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.FORMAT_DATE_TRACK_AND_ROLL,Locale.ENGLISH);
        return dateFormat.format(date);
    }

    /**
     * Method that gets a string from a time in millisec
     * @param timeInMillis : the time
     * @return the string
     */
    public static String getStringFromTimeInMillis(long timeInMillis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return getStringFromDate(calendar.getTime());
    }

    /**
     * Method that gets eve from a date
     * @param date : the date
     * @return the date format
     */
    public static String getEveStringFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK,-1);
        date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.FORMAT_DATE_TRACK_AND_ROLL,Locale.ENGLISH);
        return dateFormat.format(date);
    }

    /**
     * Method that gets a string from date
     * @param date : the date
     * @return : the string
     */
    public static String getStringFromDateGMTmidnight(Date date){
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(DateUtils.FORMAT_DATE_GMT_MIDNIGHT,Locale.ENGLISH);
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormatGmt.format(date);
    }

    /**
     * Method that gets calendar from date
     * @param date : the date
     * @return the calendar
     */
    public static Calendar getCalendarFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * Method that says if a day is the same as another
     * @param date1 : the first date
     * @param date2 : the second date
     * @return boolean
     */
    public static boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Method that says if a month is the same as another
     * @param date1 : the first date
     * @param date2 : the second date
     * @return boolean
     */
    public static boolean isSameMonth(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    }

    /**
     * Method that gets hour from date
     * @param date : the date
     * @return the hour
     */
    public static Integer getHourOfTheDateGMT(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTime(date);
        // gets hour in 24h format
        calendar.get(Calendar.HOUR_OF_DAY);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Method that says the number of days between to dates
     * @param startDate : the first date
     * @param endDate : the second date
     * @return the number of days between
     */
    public static long daysBetween(Date startDate, Date endDate) {
        Calendar sDate = Calendar.getInstance();
        sDate.setTime(startDate==null ? new Date() : startDate);
        Calendar eDate = Calendar.getInstance();
        eDate.setTime(endDate);

        long daysBetween = 0;
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_YEAR, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    /**
     * Converts a Date object into a french style date like "Lun. 12 Jun 2016"
     * @param date the Date object to format
     * @return the formatted String into french style date
     */
    public static String parseDateToFrenchString(Date date) {
        String strDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE).format(date);
        String[] strDateWords = strDate.split(" ");
        strDate = "";
        for(int i=0;i<strDateWords.length;i++){
            strDateWords[i] = strDateWords[i].substring(0, 1).toUpperCase() + strDateWords[i].substring(1);
            strDate+=strDateWords[i]+" ";
        }
        return strDate;
    }

    /**
     * Method that gets string month and year from number
     * @param monthNumber : the motnth number
     * @param yearNumber : the year number
     * @return the month and year name
     */
    public static String getStringMonthAndYearFromNumbers(int monthNumber, int yearNumber){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.MONTH,monthNumber-1);
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM",Locale.FRANCE);
        String monthName = month_date.format(cal.getTime());
        return monthName.substring(0, 1).toUpperCase() + monthName.substring(1) + " " + yearNumber;
    }

    /**
     * Method that converts time default session time
     * @param endInstant : the end instant of the session
     * @return the date in app format
     */
    public static String convertTimeDefaultSessionTime(Calendar endInstant){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.FORMAT_DATE_DEFAULT_SESSION,Locale.FRANCE);
        return dateFormat.format(endInstant.getTime());
    }
}