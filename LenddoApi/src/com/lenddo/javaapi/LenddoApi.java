package com.lenddo.javaapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import com.lenddo.javaapi.utils.ApiUtils;
import com.lenddo.javaapi.utils.Log;
import com.lenddo.javaapi.utils.RequestBody;
import retrofit.*;

import java.io.IOException;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class LenddoApi {

    private String apikey;
    private String apisecret;
    private LenddoService lenddoservice;
    private static final String TAG = LenddoApi.class.getName();

    public static void debugMode(boolean debugMode) {
        LenddoConfig.setDebugMode(debugMode);
        if (debugMode == true) {
            Log.d(TAG, "Debug Mode set to ON.");
        } else {
            Log.i(TAG, "Debug Mode set to OFF.");
        }
    }

    public String getApikey() {
        return apikey;
    }

    private void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getApisecret() {
        return apisecret;
    }

    private void setApisecret(String apisecret) {
        this.apisecret = apisecret;
    }

    private void setService(LenddoService service) {
        this.lenddoservice = service;
    }

    public LenddoApi(String apiKey, String apiSecret) {
        Log.i(TAG, "Initialize LenddoApi.");
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(LenddoService.class));
    }

    public LenddoService getService() {
        return lenddoservice;
    }

    public ClientScore getClientScoreAsPojo(String clientId) {
        return null;
    }

    public JsonObject getClientScoreAsGson(String clientId) {
        Log.i(TAG,"GET /ClientScore/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTSCORE,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<JsonElement> call = getService().getClientScore(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        JsonObject json = null;
        try {
            json = call.execute().body().getAsJsonObject();
            Log.i(TAG,"ClientScore: RAW Response => " + json.toString());
        } catch (IOException e) {
            Log.e(TAG,"getClientScoreAsGson(clientId) IOException");
            e.printStackTrace();
        }

        return json;
    }

    public String getClientScoreAsString(String clientId) {
        return getClientScoreAsGson(clientId).toString();
//        call.enqueue(new Callback<ClientScore>() {
//            @Override
//            public void onResponse(Response<ClientScore> response, Retrofit retrofit) {
//                // Get result Score from response.body()
//                int statusCode = response.code();
//                Log.d(TAG, response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//
//            }
//        });
    }

    public ClientVerification getClientVerificationAsPOJO(String clientId) {
        return null;
    }

    public JsonObject getClientVerificationAsGson(String clientId) {
        Log.i(TAG,"GET /ClientVerification/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTVERIFICATION,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<JsonElement> call = getService().getClientVerification(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        JsonObject json = null;
        try {
            json = call.execute().body().getAsJsonObject();
            Log.i(TAG,"ClientVerification: RAW Response => " + json.toString());
        } catch (IOException e) {
            Log.e(TAG,"getClientVerificationAsGson(clientId) IOException");
            e.printStackTrace();
        }

        return json;
    }

    public String getClientVerificationAsString(String clientId) {
        return getClientVerificationAsGson(clientId).toString();
    }
}
