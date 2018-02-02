package com.neos.trackandroll.cervo.utils;

import com.neos.trackandroll.cervo.Main;

public class LogUtils {

    public static boolean ACTIVATE_UDP_MESSAGE = false;
    public static boolean ACTIVATE_POINT_MESSAGE = false;

    public static void point(String message) {
        if (Main.IS_DEBUGGING) {
            if (ACTIVATE_POINT_MESSAGE) {
                System.out.println("POINT - " + message);
            }
        }
    }

    public static void udp(String message) {
        if (Main.IS_DEBUGGING) {
            if (ACTIVATE_UDP_MESSAGE) {
                System.out.println("DEBUG - " + message);
            }
        }
    }

    public static void d(String message) {
        if (Main.IS_DEBUGGING) {
            System.out.println("DEBUG - " + message);
        }
    }

    public static void e(String message) {
        if (Main.IS_DEBUGGING) {
            System.err.println("ERROR - " + message);
        }
    }

    public static void e(String message, Throwable throwable) {
        if (Main.IS_DEBUGGING) {
            System.err.println("ERROR - " + message);
            throwable.printStackTrace();
        }
    }

    public static void useless() {
        // Useless function
    }
}
