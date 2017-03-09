package com.lenddo.javaapi;

import com.lenddo.javaapi.models.ApplicationScorecards;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import com.lenddo.javaapi.services.LenddoScoreService;
import com.lenddo.javaapi.utils.ApiUtils;
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
public class LenddoScoreApi {

    private String apikey;
    private String apisecret;
    private String partner_script_id;
    private LenddoScoreService lenddoScoreService;
    private Retrofit retrofit;
    private static final String TAG = LenddoScoreApi.class.getName();

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

    /**
     * Returns the Partner Script ID.
     */
    public String getPartnerScriptID() {
        return partner_script_id;
    }

    private void setPartnerSCriptID(String ps_id) {
        this.partner_script_id = ps_id;
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
    public LenddoScoreApi(String apiKey, String apiSecret, String partner_script_id) {
        Log.i(TAG, "Initialize LenddoScoreApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        setPartnerSCriptID(partner_script_id);
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
     * Get client's score as a POJO asynchronously.
     *
     * @param applicationtId  the application id number
     * @param callback  the response handler
     */
    public void getApplicationScore(String applicationtId, final LenddoApiCallback callback) {
        Log.d(TAG,"GET /ClientScore/"+applicationtId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTSCORE,applicationtId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<ClientScore> call = getService().getClientScorePOJO(applicationtId,
                getPartnerScriptID(),
                date,
                ApiUtils.getAuthorization(getApikey(),
                        getApisecret(),
                        requestbody.toString()));

        call.enqueue(new Callback<ClientScore>() {
            @Override
            public void onResponse(Call<ClientScore> call, Response<ClientScore> response) {
                if (response.isSuccessful()) {
                    ClientScore clientScore = response.body();
                    if (clientScore == null) {
                        clientScore = new ClientScore();
                    }

                    Log.d(TAG,"ApplicationScore: Async RAW Response => " + ApiUtils.convertObjectToJsonString(clientScore));
                    callback.onResponse(clientScore);
                } else {
//                    APIError error = ErrorUtils.parseError(response, ServiceGenerator.retrofit);
//                    callback.onError(error.message());
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientScore> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    /**
     * Get ApplicationScorecards as a POJO asynchronously.
     *
     * @param application_id  the application id number
     * @param callback  the response handler
     */
    public void getApplicationScorecards(String application_id, final LenddoApiCallback callback) {
        Log.d(TAG,"GET /ApplicationScorecards/"+application_id);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_APPLICATIONSCORECARDS,application_id);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<ApplicationScorecards> call = getService().getApplicationScorecardPOJO(application_id,
                getPartnerScriptID(),
                date,
                ApiUtils.getAuthorization(getApikey(),
                        getApisecret(),
                        requestbody.toString()));

        call.enqueue(new Callback<ApplicationScorecards>() {
            @Override
            public void onResponse(Call<ApplicationScorecards> call, Response<ApplicationScorecards> response) {
                if (response.isSuccessful()) {
                    ApplicationScorecards applicationScorecards = response.body();
                    if (applicationScorecards == null) {
                        applicationScorecards = new ApplicationScorecards();
                    }

                    Log.d(TAG,"ApplicationScorecards: Async RAW Response => " + ApiUtils.convertObjectToJsonString(applicationScorecards));
                    callback.onResponse(applicationScorecards);
                } else {
//                    APIError error = ErrorUtils.parseError(response, ServiceGenerator.retrofit);
//                    callback.onError(error.message());
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApplicationScorecards> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    /**
     * Get client's verification as POJO asynchronously.
     *
     * @param applicationId  the application id number
     * @param callback  the response handler
     */
    public void getApplicationVerification(String applicationId, final LenddoApiCallback callback) {
        Log.d(TAG,"GET /ClientVerification/"+applicationId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_CLIENTVERIFICATION,applicationId);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<ClientVerification> call = getService().getClientVerificationPOJO(applicationId,
                getPartnerScriptID(),
                date,
                ApiUtils.getAuthorization(getApikey(),
                        getApisecret(),
                        requestbody.toString()));

        call.enqueue(new Callback<ClientVerification>() {
            @Override
            public void onResponse(Call<ClientVerification> call, Response<ClientVerification> response) {
                if (response.isSuccessful()) {
                    ClientVerification clientVerification = response.body();
                    if (clientVerification == null) {
                        clientVerification = new ClientVerification();
                    }
                    Log.d(TAG,"ApplicationVerification: Async RAW Response => " + ApiUtils.convertObjectToJsonString(clientVerification));
                    callback.onResponse(clientVerification);
                } else {
//                    APIError error = ErrorUtils.parseError(response, ServiceGenerator.retrofit);
//                    callback.onError(error.message());
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientVerification> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

}
