package com.lenddo.javaapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.lenddo.javaapi.models.ApplicationScore;
import com.lenddo.javaapi.models.Applications;
import com.lenddo.javaapi.models.EncryptedResponse;
import com.lenddo.javaapi.services.LenddoApplicationService;
import com.lenddo.javaapi.utils.ApiUtils;
import com.lenddo.javaapi.utils.DecryptionUtil;
import com.lenddo.javaapi.utils.Log;
import com.lenddo.javaapi.utils.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class LenddoApplicationApi {

    private String apikey;
    private String apisecret;
    private String partner_script_id;
    private String private_key;
    private LenddoApplicationService lenddoApplicationService;
    private Retrofit retrofit;
    private static final String TAG = LenddoApplicationApi.class.getName();

    //region Getters and Setters
    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getApisecret() {
        return apisecret;
    }

    public void setApisecret(String apisecret) {
        this.apisecret = apisecret;
    }

    public String getPartner_script_id() {
        return partner_script_id;
    }

    public void setPartner_script_id(String partner_script_id) {
        this.partner_script_id = partner_script_id;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public LenddoApplicationService getLenddoApplicationService() {
        return lenddoApplicationService;
    }

    public void setLenddoApplicationService(LenddoApplicationService lenddoApplicationService) {
        this.lenddoApplicationService = lenddoApplicationService;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    //endregion

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

    public LenddoApplicationApi(String apiKey, String apiSecret, String partner_script_id, String private_key) {
        Log.i(TAG, "Initialize LenddoApplicationApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        setPartner_script_id(partner_script_id);
        setPrivate_key(private_key);
        retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.applications_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setLenddoApplicationService(retrofit.create(LenddoApplicationService.class));
    }

    public void getApplications(String partnerScriptId, final LenddoApiCallback callback) {
        Log.d(TAG,"GET /applications/partnerscripts/" + partnerScriptId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD, null, date, RequestBody.ENDPOINT_APPLICATIONS, "");
        Log.d(TAG, "Message body:\n" + requestbody.toString());
        Call<EncryptedResponse> call = getLenddoApplicationService().getApplications(
                partnerScriptId,
                date,
                ApiUtils.getAuthorization(getApikey(),
                        getApisecret(),
                        requestbody.toString()));

        call.enqueue(new Callback<EncryptedResponse>() {
            @Override
            public void onResponse(Call<EncryptedResponse> call, Response<EncryptedResponse> response) {
                if (response.isSuccessful()) {
                    EncryptedResponse responseItem = response.body();
                    if (responseItem == null) {
                        responseItem = new EncryptedResponse();
                    }
                    Log.d(TAG,"Applications: Async RAW Response => " + ApiUtils.convertObjectToJsonString(responseItem));

                    DecryptionUtil decryptionUtil = new DecryptionUtil();
                    String decryptedText = decryptionUtil.decryptData(responseItem, private_key);
                    Log.d(TAG,"Applications: Async Decrypted Response => " + decryptedText);
                    callback.onResponse(decryptedText);
                } else {
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<EncryptedResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void getApplicationDetails(String partnerScriptId, String applicationId, LenddoApiCallback callback) {
        Log.d(TAG,"GET /applications/partnerscripts/" + partnerScriptId + "/applicationids/" + applicationId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD, null, date, RequestBody.ENDPOINT_APPLICATIONS, applicationId);
        Log.d(TAG, "Message body:\n" + requestbody.toString());
        Call<EncryptedResponse> call = getLenddoApplicationService().getApplicationDetails(
                partnerScriptId,
                applicationId,
                date,
                ApiUtils.getAuthorization(getApikey(),
                        getApisecret(),
                        requestbody.toString()));

        call.enqueue(new Callback<EncryptedResponse>() {
            @Override
            public void onResponse(Call<EncryptedResponse> call, Response<EncryptedResponse> response) {
                if (response.isSuccessful()) {
                    EncryptedResponse responseItem = response.body();
                    if (responseItem == null) {
                        responseItem = new EncryptedResponse();
                    }
                    Log.d(TAG,"Applications: Async RAW Response => " + ApiUtils.convertObjectToJsonString(responseItem));

                    DecryptionUtil decryptionUtil = new DecryptionUtil();
                    String decryptedText = decryptionUtil.decryptData(responseItem, private_key);
                    Log.d(TAG,"Applications: Async Decrypted Response => " + decryptedText);
                    callback.onResponse(decryptedText);
                } else {
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<EncryptedResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void getDocumentByApplicationId(String partnerScriptId, String applicationId, String documentId, LenddoApiCallback callback) {
        Log.d(TAG,"GET /applications/partnerscripts/" + partnerScriptId + "/applicationids/" + applicationId + "/documents/" + documentId);
        String date = ApiUtils.getDate();
        RequestBody requestbody = new RequestBody(RequestBody.GET_METHOD, null, date, RequestBody.ENDPOINT_APPLICATIONS, partnerScriptId);
        Log.d(TAG, "Message body:\n" + requestbody.toString());
        Call<EncryptedResponse> call = getLenddoApplicationService().getDocumentByApplicationId(
                partnerScriptId,
                applicationId,
                documentId,
                date,
                ApiUtils.getAuthorization(getApikey(),
                        getApisecret(),
                        requestbody.toString()));

        call.enqueue(new Callback<EncryptedResponse>() {
            @Override
            public void onResponse(Call<EncryptedResponse> call, Response<EncryptedResponse> response) {
                if (response.isSuccessful()) {
                    EncryptedResponse responseItem = response.body();
                    if (responseItem == null) {
                        responseItem = new EncryptedResponse();
                    }
                    Log.d(TAG,"Applications: Async RAW Response => " + ApiUtils.convertObjectToJsonString(responseItem));

                    DecryptionUtil decryptionUtil = new DecryptionUtil();
                    String decryptedText = decryptionUtil.decryptData(responseItem, private_key);
                    Log.d(TAG,"Applications: Async Decrypted Response => " + decryptedText);
                    callback.onResponse(decryptedText);
                } else {
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<EncryptedResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
