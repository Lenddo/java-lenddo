package com.lenddo.javaapi.utils;

import com.lenddo.javaapi.LenddoConfig;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class RequestBody {

    public static final int GET_METHOD = 0;
    public static final int POST_METHOD = 1;
    public static final int PUT_METHOD = 2;
    public static final int DELETE_METHOD = 3;

    public static final int ENDPOINT_APPLICATIONSCORE = 0;
    public static final int ENDPOINT_APPLICATIONVERIFICATION = 1;
    public static final int ENDPOINT_WL_PARTNERTOKEN = 2;
    public static final int ENDPOINT_WL_COMMITPARTNERJOB = 3;
    public static final int ENDPOINT_APPLICATIONSCORECARDS = 4;
    public static final int ENDPOINT_APPLICATIONFEATURES = 5;
    public static final int ENDPOINT_EXTRAAPPLICATIONDATA = 6;
    public static final int ENDPOINT_APPLICATIONMULTIPLESCORES = 7;
    public static final int ENDPOINT_AUTHORIZE_HEALTHCHECK = 8;
    public static final int ENDPOINT_AUTHORIZE_ONBOARDING_PRIORITYDATA = 9;
    public static final int ENDPOINT_NETWORK_MOBILEDATA = 10;
    public static final int ENDPOINT_APPLICATIONS = 200;

    private static final String TAG = RequestBody.class.getName();

    private String method;
    private String body;
    private String date;
    private String path;
    private String applicationId;
    private String request;

    public RequestBody(int method, String body, String date, int endpoint, String applicationID) {
        request = setMethodString(method)+"\n"+setBody(body)+"\n"+setDate(date)+"\n"+setPath(endpoint,applicationID);
    }

    @Override
    public String toString() {
        return request;
    }

    private String setMethodString(int method) {
        switch (method) {
            case GET_METHOD:
                this.method = "GET";
                break;
            case POST_METHOD:
                this.method = "POST";
                break;
            default:
                Log.e(TAG,"setMethodString(method) Unknown method value: " + method);
                this.method = "";
                break;
        }
        return this.method;
    }

    private String setBody(String body) {
        if (body == null) body = "";
        this.body = body;
        return body;
    }

    private String setDate(String date) {
        this.date = date;
        return date;
    }

    private String setPath(int endpoint, String applicationId) {
        this.applicationId = applicationId;
        switch (endpoint) {
            case ENDPOINT_APPLICATIONSCORE:
                this.path = LenddoConfig.ENDPOINT_SCORE_APPLICATIONSCORE + applicationId;
                break;
            case ENDPOINT_APPLICATIONVERIFICATION:
                this.path = LenddoConfig.ENDPOINT_SCORE_APPLICATIONVERIFICATION + applicationId;
                break;
            case ENDPOINT_WL_PARTNERTOKEN:
                this.path = LenddoConfig.ENDPOINT_WL_PARTNERTOKEN;
                break;
            case ENDPOINT_WL_COMMITPARTNERJOB:
                this.path = LenddoConfig.ENDPOINT_WL_COMMITPARTNERJOB;
                break;
            case ENDPOINT_APPLICATIONSCORECARDS:
                this.path = LenddoConfig.ENDPOINT_SCORE_APPLICATIONSCORECARDS + applicationId;
                break;
            case ENDPOINT_APPLICATIONFEATURES:
                this.path = LenddoConfig.ENDPOINT_SCORE_APPLICATIONFEATURES + applicationId;
                break;
            case ENDPOINT_EXTRAAPPLICATIONDATA:
                this.path = LenddoConfig.ENDPOINT_NETWORK_SENDEXTRADATA;
                break;
            case ENDPOINT_APPLICATIONMULTIPLESCORES:
                this.path = LenddoConfig.ENDPOINT_SCORE_APPLICATIONMULTIPLESCORES + applicationId;
                break;
            case ENDPOINT_AUTHORIZE_HEALTHCHECK:
                this.path = LenddoConfig.ENDPOINT_AUTHORIZE_HEALTHCHECK;
                break;
            case ENDPOINT_AUTHORIZE_ONBOARDING_PRIORITYDATA:
                this.path = LenddoConfig.ENDPOINT_AUTHORIZE_ONBOARDING_PRIORITYDATA;
                break;
            case ENDPOINT_NETWORK_MOBILEDATA:
                this.path = LenddoConfig.ENDPOINT_NETWORK_MOBILEDATA;
                break;
            case ENDPOINT_APPLICATIONS:
                this.path = LenddoConfig.ENDPOINT_APPLICATIONS;
                break;
            default:
                Log.e(TAG,"setPath(endpoint, applicationId) Unknown endpoint value: " + endpoint);
                path = "";
                break;
        }
        return this.path;
    }

    public String getMethod() {
        return method;
    }

    public String getBody() {
        return this.body;
    }

    public String getDate() {
        return this.date;
    }

    public String getPath() {
        return path;
    }

    public String getRequest() {
        return request;
    }

    public String getApplicationId() {
        return applicationId;
    }

}
