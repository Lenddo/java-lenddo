package com.lenddo.javaapi;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class LenddoConfig {

    public static final String api_version = "0.0.1";
    public static final String base_url = "https://scoreservice.lenddo.com";
    public static final String ENDPOINT_CLIENTSCORE = "/ClientScore/";
    public static final String ENDPOINT_CLIENTVERIFICATION = "/ClientVerification/";
    private static boolean isDebugMode = false;

    public static boolean isDebugMode() {
        return isDebugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }
}
