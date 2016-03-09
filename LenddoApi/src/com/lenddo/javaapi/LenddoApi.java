package com.lenddo.javaapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lenddo.javaapi.models.APIError;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import com.lenddo.javaapi.services.LenddoScoreService;
import com.lenddo.javaapi.utils.ApiUtils;
import com.lenddo.javaapi.utils.ErrorUtils;
import com.lenddo.javaapi.utils.Log;
import com.lenddo.javaapi.utils.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class LenddoApi {

    private String apikey;
    private String apisecret;
    private LenddoScoreService lenddoScoreService;
    private Retrofit retrofit;
    private static final String TAG = LenddoApi.class.getName();

    /**
     * Sets the debug mode for outputting log messages.
     *
     * @param debugMode boolean value for the debug mode settings
     */
    public static void debugMode(boolean debugMode) {
        LenddoConfig.setDebugMode(debugMode);
        if (debugMode == true) {
            Log.d(TAG, "Debug Mode set to ON.");
        } else {
            Log.i(TAG, "Debug Mode set to OFF.");
        }
    }

    /**
     * Returns the Api Key.
     */
    public String getApikey() {
        return apikey;
    }

    private void setApikey(String apikey) {
        this.apikey = apikey;
    }

    /**
     * Returns the Api Secret.
     */
    public String getApisecret() {
        return apisecret;
    }

    private void setApisecret(String apisecret) {
        this.apisecret = apisecret;
    }

    private void setService(LenddoScoreService service) {
        this.lenddoScoreService = service;
    }

    /**
     * Class constructor specifying apiKey and apiSecret.
     */
    public LenddoApi(String apiKey, String apiSecret) {
        Log.i(TAG, "Initialize LenddoApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.score_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(LenddoScoreService.class));
    }

    public LenddoScoreService getService() {
        return lenddoScoreService;
    }

    /**
     * Get client's score as an object of ClientScore class.
     *
     * @param clientId  the client id number
     */
    public ClientScore getClientScore(String clientId) {
        Log.i(TAG,"GET /ClientScore/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTSCORE,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<ClientScore> call = getService().getClientScorePOJO(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        ClientScore pojo = null;
        try {
            pojo = call.execute().body();
            Log.i(TAG,"ClientScore: RAW Response => " + ApiUtils.convertObjectToJsonString(pojo));
        } catch (IOException e) {
            Log.e(TAG,"getClientScoreAsPojo(clientId) IOException");
            e.printStackTrace();
        }

        return pojo;
    }

    /**
     * Get client's score as a JSON string asynchronously.
     *
     * @param clientId  the client id number
     * @param callback  the response handler
     */
    public void getClientScore(String clientId, final LenddoApiCallback callback) {
        Log.i(TAG,"GET /ClientScore/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTSCORE,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<ClientScore> call = getService().getClientScorePOJO(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        call.enqueue(new Callback<ClientScore>() {
            @Override
            public void onResponse(Call<ClientScore> call, Response<ClientScore> response) {
                ClientScore clientScore = response.body();
                if (clientScore == null) {
                    clientScore = new ClientScore();
                }

                Log.i(TAG,"ClientScore: Async RAW Response => " + ApiUtils.convertObjectToJsonString(clientScore));
                callback.onResponse(clientScore);
            }

            @Override
            public void onFailure(Call<ClientScore> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    /**
     * Get client's score as an instantiation of JsonObject.
     *
     * @param clientId  the client id number
     */
    public JsonObject getClientScoreAsGson(String clientId) {
        Log.i(TAG,"GET /ClientScore/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTSCORE,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<JsonElement> call = getService().getClientScore(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        JsonObject json = null;
        try {
            JsonElement element = call.execute(). body();
            if (element != null) {
                json = element.getAsJsonObject();
                Log.i(TAG,"ClientScore: RAW Response => " + json.toString());
            } else {
                json = new JsonObject();
                Log.e(TAG,"getClientScoreAsGson(clientId): returned null for client_id:"+clientId);
            }
        } catch (IOException e) {
            Log.e(TAG,"getClientScoreAsGson(clientId) IOException");
            e.printStackTrace();
        }

        return json;
    }

    /**
     * Get client's score as a JSON String.
     *
     * @param clientId  the client id number
     */
    public String getClientScoreAsString(String clientId) {
        return getClientScoreAsGson(clientId).toString();
    }

    /**
     * Get client's verification as a JSON string asynchronously.
     *
     * @param clientId  the client id number
     * @param callback  the response handler
     */
    public void getClientVerification(String clientId, final LenddoApiCallback callback) {
        Log.i(TAG,"GET /ClientVerification/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTVERIFICATION,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<ClientVerification> call = getService().getClientVerificationPOJO(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        call.enqueue(new Callback<ClientVerification>() {
            @Override
            public void onResponse(Call<ClientVerification> call, Response<ClientVerification> response) {
                if (response.isSuccess()) {
                    ClientVerification clientVerification = response.body();
                    if (clientVerification == null) {
                        clientVerification = new ClientVerification();
                    }
                    Log.i(TAG,"ClientVerification: Async RAW Response => " + ApiUtils.convertObjectToJsonString(clientVerification));
                    callback.onResponse(clientVerification);
                } else {
                    APIError error = ErrorUtils.parseError(response, retrofit);
                    Log.e(TAG,error.message());
                }
            }

            @Override
            public void onFailure(Call<ClientVerification> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    /**
     * Get client's verification as an object of ClientVerification class.
     *
     * @param clientId  the client id number
     */
    public ClientVerification getClientVerification(String clientId) {
        Log.i(TAG,"GET /ClientVerification/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTVERIFICATION,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<ClientVerification> call = getService().getClientVerificationPOJO(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        ClientVerification pojo = null;
        try {
            pojo = call.execute().body();
            Log.i(TAG,"ClientVerification: RAW Response => " + ApiUtils.convertObjectToJsonString(pojo));
        } catch (IOException e) {
            Log.e(TAG,"getClientVerificationAsPojo(clientId) IOException");
            e.printStackTrace();
        }

        return pojo;
    }


    /**
     * Get client's verification as an instantiation of JsonObject.
     *
     * @param clientId  the client id number
     */
    public JsonObject getClientVerificationAsGson(String clientId) {
        Log.i(TAG,"GET /ClientVerification/"+clientId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTVERIFICATION,clientId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<JsonElement> call = getService().getClientVerification(clientId, date, ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        JsonObject json = null;
        try {
            JsonElement element = call.execute().body();
            if (element != null) {
                json = element.getAsJsonObject();
                Log.i(TAG,"ClientVerification: RAW Response => " + json.toString());
            } else {
                json = new JsonObject();
                Log.e(TAG,"getClientVerificationAsGson(clientId): returned null for client_id:"+clientId);
            }
        } catch (IOException e) {
            Log.e(TAG,"getClientVerificationAsGson(clientId) IOException");
            e.printStackTrace();
        }

        return json;
    }

    /**
     * Get client's verification as a JSON String.
     *
     * @param clientId  the client id number
     */
    public String getClientVerificationAsString(String clientId) {
        return getClientVerificationAsGson(clientId).toString();
    }

}
