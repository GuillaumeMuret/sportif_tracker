package com.neos.trackandroll.model.constant;

public class Constant {

    // Constant use for the displayed value of session and non assigned sensor
    public static final String DEFAULT_SESSION_NAME = "GLOBAL";
    public static final String SENSOR_NON_ASSIGNED_NAME = "Non-Assigné";

    // Account default values
    public static final String ACCOUNT_LAST_NAME               = "Aubin";
    public static final String ACCOUNT_FIRST_NAME              = "Sébastien";
    public static final String ACCOUNT_EMAIL                   = "sebastien.aubin@eseo.fr";
    public static final String ACCOUNT_USERNAME                = "admin";
    public static final String ACCOUNT_PASSWORD                = "admin";

    // The different state of the sensor
    public static final String SENSOR_STATE_CONNECTED           = "CONNECTED";
    public static final String SENSOR_STATE_WARNING             = "WARNING";
    public static final String SENSOR_STATE_CONNECTION_PROGRESS = "CONNECTION_PROGRESS";
    public static final String SENSOR_STATE_DISCONNECTED        = "DISCONNECTED";

    // The different state of the app
    public static final String APP_STATE_IDLE                       = "APP_STATE_IDLE";
    public static final String APP_STATE_RUNNING_SESSION            = "APP_STATE_RUNNING_SESSION";
    public static final String APP_STATE_SET_RUNNING_SESSION_NAME   = "APP_STATE_SET_RUNNING_SESSION_NAME";
    public static final String APP_STATE_SAVING                     = "APP_STATE_SAVING";
    public static final String APP_STATE_WAIT_SESSION_NAME          = "APP_STATE_WAIT_SESSION_NAME";

    // The different time use in the application
    public static final int TIME_SPLASH_SCREEN = 2000;
    public static final int TIME_ANIMATION_FADE_OUT = 500;
    public static final int TIME_BEFORE_BEAGLEBONE_SEND_SOMETHING = 100000;


    // Constant of the communication
    public static final int SERVER_PORT = 13579;
    //public static final String SERVER_ADDRESS = "192.168.0.31";
    //public static final String SERVER_ADDRESS = "192.168.43.33";
    //public static final String SERVER_ADDRESS = "192.168.0.19";
    public static final String SERVER_ADDRESS = "192.168.43.146";
    public static final int TW_CONNECTION_SOCKET = 10000;
}
