package com.lenddo.javaapi;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class LenddoConfig {

    public static final String api_version = "2.0.0";
    public static final String score_base_url = "https://scoreservice.lenddo.com";
    public static final String onboarding_base_url = "https://authorize.lenddo.com";
    public static final String ENDPOINT_SCORE_CLIENTSCORE = "/ClientScore/";
    public static final String ENDPOINT_SCORE_CLIENTVERIFICATION = "/ClientVerification/";
    private static boolean isDebugMode = false;

    public static boolean isDebugMode() {
        return isDebugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }
}
