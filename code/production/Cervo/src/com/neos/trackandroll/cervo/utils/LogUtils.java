package com.neos.trackandroll.cervo.utils;

import com.neos.trackandroll.cervo.Main;

public class LogUtils {

    public static final boolean ACTIVATE_UDP_MESSAGE = false;
    public static final boolean ACTIVATE_POINT_MESSAGE = false;
    public static final boolean ACTIVATE_TOTAL_DISTANCE = false;
    public static final boolean ACTIVATE_DATA_POSITION = false;

    /**
     * Method called when the message type concern the point
     * @param message : the message to display
     */
    public static void point(String message) {
        if (Main.IS_DEBUGGING) {
            if (ACTIVATE_POINT_MESSAGE) {
                System.out.println("POINT - " + message);
            }
        }
    }

    /**
     * Method called when the message type concern the udp
     * @param message : the message to display
     */
    public static void udp(String message) {
        if (Main.IS_DEBUGGING) {
            if (ACTIVATE_UDP_MESSAGE) {
                System.out.println("UDP - " + message);
            }
        }
    }

    /**
     * Method called when the message type concern the total distance
     * @param message : the message to display
     */
    public static void totalDistance(String message) {
        if (Main.IS_DEBUGGING) {
            if (ACTIVATE_TOTAL_DISTANCE) {
                System.out.println("Total distance - " + message);
            }
        }
    }

    /**
     * Method called when the message type concern the debug
     * @param message : the message to display
     */
    public static void d(String message) {
        if (Main.IS_DEBUGGING) {
            System.out.println("DEBUG - " + message);
        }
    }

    /**
     * Method called when the message type concern an error
     * @param message : the message to display
     */
    public static void e(String message) {
        if (Main.IS_DEBUGGING) {
            System.err.println("ERROR = " + message);
        }
    }

    /**
     * Method called when the message type concern the point
     * @param message : the message to display
     * @param throwable : the exception to display
     */
    public static void e(String message, Throwable throwable) {
        if (Main.IS_DEBUGGING) {
            System.err.println("ERROR - " + message);
            throwable.printStackTrace();
        }
    }

    /**
     * Useless function that do nothing
     */
    public static void useless() {
        // Useless function
    }
}
