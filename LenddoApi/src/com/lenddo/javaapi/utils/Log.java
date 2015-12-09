package com.lenddo.javaapi.utils;

import com.lenddo.javaapi.LenddoConfig;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class Log {

    private static final String ANSI_ESC = Character.toString((char)27);   // ANSI Escape Character
    private static final String ANSI_COLOR_BLACK = ANSI_ESC + "[30m";      // Black for Normal Log
    private static final String ANSI_COLOR_RED = ANSI_ESC + "[31m";        // Red for Error Log
    private static final String ANSI_COLOR_GREEN = ANSI_ESC + "[32m";      // Green
    private static final String ANSI_COLOR_BLUE = ANSI_ESC + "[34m";       // Blue for Debug Log
    private static final String ANSI_CLEAR = ANSI_ESC + "[0m";             // Clear formatting

    public static void i(String tag, String logmessage) {
        System.out.println(ANSI_CLEAR + tag + " -> " + logmessage);
    }

    public static void d(String tag, String logmessage) {
        if (LenddoConfig.isDebugMode()) {
            System.out.println(ANSI_COLOR_BLUE + tag + " -> " + logmessage + ANSI_CLEAR);
        }
    }

    public static void e(String tag, String logmessage) {
        System.out.println(ANSI_COLOR_RED + tag + " -> " + logmessage + ANSI_CLEAR);
    }
}
