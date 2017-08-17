package com.lenddo.javaapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lenddo.javaapi.models.NetworkRequestBody;
import com.lenddo.javaapi.models.PriorityDataRequestBody;
import com.lenddo.javaapi.models.Verification;
import com.lenddo.javaapi.services.AuthorizeService;
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
 * Created by Joey Mar Antonio on 17/08/2017.
 */
public class AuthorizeApi {

    private String apikey;
    private String apisecret;
    private String partner_script_id;
    private AuthorizeService authorizeService;
    private Retrofit retrofit;
    private static final String TAG = AuthorizeApi.class.getName();

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

    private void setPartnerScriptID(String ps_id) {
        this.partner_script_id = ps_id;
    }

    private void setApisecret(String apisecret) {
        this.apisecret = apisecret;
    }

    private void setService(AuthorizeService service) {
        this.authorizeService = service;
    }

    /**
     * Class constructor specifying apiKey, apiSecret and partner_script_id.
     */
    public AuthorizeApi(String apiKey, String apiSecret, String partner_script_id) {
        Log.i(TAG, "Initialize AuthorizeApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        setPartnerScriptID(partner_script_id);
        retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.authorize_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(AuthorizeService.class));
    }

    /**
     * Class constructor specifying apiKey, apiSecret, partner_script_id and base_url.
     */
    public AuthorizeApi(String apiKey, String apiSecret, String partner_script_id, String base_url) {
        Log.i(TAG, "Initialize AuthorizeApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        setPartnerScriptID(partner_script_id);
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(AuthorizeService.class));
    }

    public AuthorizeService getService() {
        return authorizeService;
    }


    /**
     * GET Healthcheck asynchronously.
     *
     * @param applicationId  the application id number is the client id
     * @param callback  the response handler
     */
    public void getAuthorizeHealthcheck(String applicationId, final LenddoApiCallback callback) {
        Log.d(TAG,"GET /Healthcheck");
        String date = ApiUtils.getDate();
//        NetworkRequestBody.SendExtraDataRqBody body = new NetworkRequestBody.SendExtraDataRqBody();
//        body.application_id = applicationId;
//        body.partner_script_id = partner_script_id;
//        body.extra_data = application;

        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,
                date,RequestBody.ENDPOINT_AUTHORIZE_HEALTHCHECK, applicationId);
        Log.d(TAG, "PSID:"+getPartnerScriptID()+"\n");
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        System.out.println();
        Call<JsonElement> call = getService().getAuthorizeHealthcheck(
                date,
                ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()));

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    JsonElement healthcheck = response.body();
                    if (healthcheck == null) {
                        healthcheck = new JsonObject();
                    }

                    Log.d(TAG,"getAuthorizeHealthcheck: Async RAW Response => " + ApiUtils.convertObjectToJsonString(healthcheck));
                    callback.onResponse(healthcheck);
                } else {
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    /**
     * POST OnboardingPrioritydata asynchronously.
     *
     * @param applicationId  the application id number is the client id
     * @param body  the request body
     * @param callback  the response handler
     */
    public void  postAuthorizeOnboardingPrioritydata(String applicationId, PriorityDataRequestBody body, final LenddoApiCallback callback) {
        Log.d(TAG,"POST /onboarding/prioritydata");
        String date = ApiUtils.getDate();
        if (body.application_id == null) {
            body.application_id = applicationId;
        }
        if (body.partner_script_id == null) {
            body.partner_script_id = partner_script_id;
        }

        RequestBody requestbody = new RequestBody(RequestBody.POST_METHOD,
                ApiUtils.md5(ApiUtils.convertObjectToJsonStringNoNulls(body)),
                date,RequestBody.ENDPOINT_AUTHORIZE_ONBOARDING_PRIORITYDATA, applicationId);
        System.out.println("\n"+ApiUtils.convertObjectToJsonStringNoNulls(body)+"\n");
        Log.d(TAG, "PSID:"+getPartnerScriptID());
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        System.out.println();
        Call<JsonElement> call = getService().postAuthorizeOnboardingPrioritydata(
                date,
                ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()),
                body);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    JsonElement jsonResponse = response.body();
                    if (jsonResponse == null) {
                        jsonResponse = new JsonObject();
                    }

                    Log.d(TAG,"postExtraApplicationData: Async RAW Response => " + ApiUtils.convertObjectToJsonString(jsonResponse));
                    callback.onResponse(jsonResponse);
                } else {
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
