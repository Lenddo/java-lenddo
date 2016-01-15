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

    public static final int ENDPOINT_CLIENTSCORE = 0;
    public static final int ENDPOINT_CLIENTVERIFICATION = 1;

    private static final String TAG = RequestBody.class.getName();

    private String method;
    private String body;
    private String date;
    private String path;
    private String clientId;
    private String request;

    public RequestBody(int method, String body, String date, int endpoint, String clientID) {
        request = setMethodString(method)+"\n"+setBody(body)+"\n"+setDate(date)+"\n"+setPath(endpoint,clientID);
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

    private String setPath(int endpoint, String clientId) {
        this.clientId = clientId;
        switch (endpoint) {
            case ENDPOINT_CLIENTSCORE:
                this.path = LenddoConfig.ENDPOINT_SCORE_CLIENTSCORE + clientId;
                break;
            case ENDPOINT_CLIENTVERIFICATION:
                this.path = LenddoConfig.ENDPOINT_SCORE_CLIENTVERIFICATION + clientId;
                break;
            default:
                Log.e(TAG,"setPath(endpoint, clientId) Unknown endpoint value: " + endpoint);
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

    public String getClientId() {
        return clientId;
    }

}
