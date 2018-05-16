package com.lenddo.javaapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lenddo.javaapi.models.NetworkRequestBody;
import com.lenddo.javaapi.services.NetworkService;
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
 * Created by Joey Mar Antonio on 07/04/2017.
 */
public class NetworkApi {

    private String apikey;
    private String apisecret;
    private String partner_script_id;
    private NetworkService networkService;
    private Retrofit retrofit;
    private static final String TAG = NetworkApi.class.getName();

    public static final String PROVIDER_FACEBOOK = "Facebook";
    public static final String PROVIDER_LINKEDIN = "LinkedIn";
    public static final String PROVIDER_YAHOO = "Yahoo";
    public static final String PROVIDER_WINDOWSLIVE = "WindowsLive";
    public static final String PROVIDER_GOOGLE = "Google";

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

    private void setService(NetworkService service) {
        this.networkService = service;
    }

    /**
     * Class constructor specifying apiKey, apiSecret.
     */
    public NetworkApi(String apiKey, String apiSecret) {
        Log.i(TAG, "Initialize NetworkApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.whitelabel_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(NetworkService.class));
    }

    /**
     * Class constructor specifying apiKey, apiSecret and partner_script_id.
     */
    public NetworkApi(String apiKey, String apiSecret, String partner_script_id) {
        Log.i(TAG, "Initialize NetworkApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        setPartnerScriptID(partner_script_id);
        retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.whitelabel_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(NetworkService.class));
    }

    /**
     * Class constructor specifying apiKey, apiSecret, partner_script_id and base_url.
     */
    public NetworkApi(String apiKey, String apiSecret, String partner_script_id, String base_url) {
        Log.i(TAG, "Initialize NetworkApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        setPartnerScriptID(partner_script_id);
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(NetworkService.class));
    }

    public NetworkService getService() {
        return networkService;
    }


    /**
     * POST ExtraApplicationData asynchronously.
     *
     * @param applicationId  the application id number is the client id
     * @param extraData  the request body
     * @param callback  the response handler
     */
    public void  postExtraApplicationData(String applicationId, JsonObject extraData, final LenddoApiCallback callback) {
        Log.d(TAG,"POST /ExtraApplicationData");
        String date = ApiUtils.getDate();
        NetworkRequestBody.SendExtraDataRqBody body = new NetworkRequestBody.SendExtraDataRqBody();
        body.application_id = applicationId;
        body.partner_script_id = partner_script_id;
        body.extra_data = extraData;

        RequestBody requestbody = new RequestBody(RequestBody.POST_METHOD,ApiUtils.md5(ApiUtils.convertObjectToJsonString(body)),
                date,RequestBody.ENDPOINT_EXTRAAPPLICATIONDATA, applicationId);
        System.out.println("\n"+ApiUtils.convertObjectToJsonString(body)+"\n\n");
        Log.d(TAG, "PSID:"+getPartnerScriptID()+"\n");
        Log.d(TAG, "BODY:"+getPartnerScriptID()+"\n");
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        System.out.println();
        Call<JsonElement> call = getService().postExtraApplicationData(applicationId,
                getPartnerScriptID(),
                date,
                ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()),
                body);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    JsonElement partnerToken = response.body();
                    if (partnerToken == null) {
                        partnerToken = new JsonObject();
                    }

                    Log.d(TAG,"postExtraApplicationData: Async RAW Response => " + ApiUtils.convertObjectToJsonString(partnerToken));
                    callback.onResponse(partnerToken);
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
     * GET MobileData asynchronously.
     *
     * @param partnerScriptId  the application id number is the client id
     * @param partnerId  the partnerId provided by LenddoEFL
     * @param callback  the response handler
     */
    public void getMobileData(String partnerScriptId, String partnerId, final LenddoApiCallback callback) {
        Log.d(TAG,"GET /MobileData");
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD,null,date,RequestBody.ENDPOINT_NETWORK_MOBILEDATA,null);
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        Call<JsonElement> call = getService().getMobileData(partnerScriptId,
                partnerId,
                date,
                ApiUtils.getAuthorization(getApikey(),
                        getApisecret(),
                        requestbody.toString()));

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    JsonElement mobileData = response.body();
                    if (mobileData == null) {
                        mobileData = new JsonObject();
                    }

                    Log.d(TAG,"MobileData: Async RAW Response => " + ApiUtils.convertObjectToJsonString(mobileData));
                    callback.onResponse(mobileData);
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
            public void onFailure(Call<JsonElement> call, Throwable t) {
                if (callback!=null) {
                    callback.onFailure(t);
                }

            }
        });
    }

}
