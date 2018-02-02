package com.neos.trackandroll.utils;

import android.util.Log;

public class LogUtils {

    public static final String DEBUG_TAG = "DEBUG_TAG";

    /**
     * Process called to display a debug message on the logcat
     * @param tag : the tag of the message
     * @param message : the message
     */
    public static void d(String tag, String message){
        Log.d(tag,message);
    }

    /**
     * Process called to display an error message on the logcat
     * @param tag : the tag of the message
     * @param message : the message
     */
    public static void e(String tag, String message){
        Log.e(tag,message);
    }

    /**
     * Process called to display an error message on the logcat with throwable exception
     * @param tag : the tag of the message
     * @param message : the message
     * @param throwable : the exception to display
     */
    public static void e(String tag, String message,Throwable throwable){
        Log.e(tag,message,throwable);
    }
}

