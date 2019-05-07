package com.lenddo.javaapi;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class LenddoConfig {

    public static final String api_version = "2.9.0";
    public static final String score_base_url = "https://scoreservice.lenddo.com";
    public static final String whitelabel_base_url = "https://networkservice.lenddo.com";
    public static final String onboarding_base_url = "https://authorize.lenddo.com";
    public static final String authorize_base_url = "https://authorize-api.partner-service.link";
    public static final String applications_base_url = "https://frodo.partner-service.link";
    public static final String ENDPOINT_SCORE_APPLICATIONSCORECARDS = "/ApplicationScorecards/";
    public static final String ENDPOINT_SCORE_APPLICATIONSCORE = "/ApplicationScore/";
    public static final String ENDPOINT_SCORE_APPLICATIONVERIFICATION = "/ApplicationVerification/";
    public static final String ENDPOINT_SCORE_APPLICATIONFEATURES = "/ApplicationFeatures/";
    public static final String ENDPOINT_SCORE_APPLICATIONMULTIPLESCORES = "/ApplicationMultipleScores/";
    public static final String ENDPOINT_WL_PARTNERTOKEN = "/PartnerToken";
    public static final String ENDPOINT_WL_COMMITPARTNERJOB = "/CommitPartnerJob";
    public static final String ENDPOINT_NETWORK_SENDEXTRADATA = "/ExtraApplicationData";
    public static final String ENDPOINT_NETWORK_MOBILEDATA = "/MobileData";
    public static final String ENDPOINT_AUTHORIZE_HEALTHCHECK = "/healthcheck";
    public static final String ENDPOINT_AUTHORIZE_ONBOARDING_PRIORITYDATA = "/onboarding/prioritydata";
    public static final String ENDPOINT_APPLICATIONS = "/applications";
    private static boolean isDebugMode = false;

    public static boolean isDebugMode() {
        return isDebugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }
}
