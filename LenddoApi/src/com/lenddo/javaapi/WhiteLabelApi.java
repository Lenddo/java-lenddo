package com.lenddo.javaapi;

import com.google.gson.JsonArray;
import com.lenddo.javaapi.models.CommitPartnerJob;
import com.lenddo.javaapi.models.PartnerToken;
import com.lenddo.javaapi.models.Verification;
import com.lenddo.javaapi.models.WhitelabelRequestBody;
import com.lenddo.javaapi.services.WhitelabelService;
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
 * Created by Joey Mar Antonio on 10/13/2016.
 */
public class WhiteLabelApi {

    private String apikey;
    private String apisecret;
    private String partner_script_id;
    private WhitelabelService whitelabelService;
    private Retrofit retrofit;
    private static final String TAG = WhiteLabelApi.class.getName();

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

    private void setService(WhitelabelService service) {
        this.whitelabelService = service;
    }

    /**
     * Class constructor specifying apiKey, apiSecret and partner_script_id.
     */
    public WhiteLabelApi(String apiKey, String apiSecret, String partner_script_id) {
        Log.i(TAG, "Initialize WhiteLabelApi v" + LenddoConfig.api_version);
        Log.d(TAG,"\n\tapiKey: "+apiKey+"\n\tapiSecret: "+apiSecret);
        setApikey(apiKey);
        setApisecret(apiSecret);
        setPartnerScriptID(partner_script_id);
        retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.whitelabel_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setService(retrofit.create(WhitelabelService.class));
    }

    public WhitelabelService getService() {
        return whitelabelService;
    }


//    public void postPartnerToken(WhitelabelRequestBody.WLPartnerTokenRqBody body, LenddoApiCallback callback) {
//        postPartnerToken(body.client_id, body.provider, body.token_data, callback);
//    }
    /**
     * POST PartnerToken asynchronously.
     *
     * @param applicationtId  the application id number is the client id
     * @param callback  the response handler
     */
    public void postPartnerToken(String applicationtId, String provider, WhitelabelRequestBody.WLPartnerTokenRqBody.token_data td, final LenddoApiCallback callback) {
        Log.i(TAG,"POST /PartnerToken");
        String date = ApiUtils.getDate();
        WhitelabelRequestBody.WLPartnerTokenRqBody body = new WhitelabelRequestBody.WLPartnerTokenRqBody();
        body.client_id = applicationtId;
        body.provider = provider;
        body.token_data = td;

        RequestBody requestbody = new RequestBody(RequestBody.POST_METHOD,ApiUtils.md5(ApiUtils.convertObjectToJsonString(body)),date,RequestBody.ENDPOINT_WL_PARTNERTOKEN,applicationtId);
        System.out.println("\n"+ApiUtils.convertObjectToJsonString(body)+"\n\n");
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        System.out.println();
        Call<PartnerToken> call = getService().postPartnerToken(date,
                ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()),
                body);

        call.enqueue(new Callback<PartnerToken>() {
            @Override
            public void onResponse(Call<PartnerToken> call, Response<PartnerToken> response) {
                if (response.isSuccessful()) {
                    PartnerToken partnerToken = response.body();
                    if (partnerToken == null) {
                        partnerToken = new PartnerToken();
                    }

                    Log.i(TAG,"postPartnerToken: Async RAW Response => " + ApiUtils.convertObjectToJsonString(partnerToken));
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
            public void onFailure(Call<PartnerToken> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

//    public void postCommitPartnerJob(WhitelabelRequestBody.WLCommitPartnerJobRqBody body, LenddoApiCallback callback) {
//        postCommitPartnerJob(body.client_id, body.partner_script_id, body.profile_ids, body.verification_data, callback);
//    }
    /**
     * POST CommitPartnerJob asynchronously.
     *
     * @param applicationtId  the application id number is the client id
     * @param callback  the response handler
     */
    public void postCommitPartnerJob(String applicationtId, JsonArray profile_ids, Verification verification, final LenddoApiCallback callback) {
        Log.i(TAG,"POST /CommitPartnerJob");
        String date = ApiUtils.getDate();
        WhitelabelRequestBody.WLCommitPartnerJobRqBody body = new WhitelabelRequestBody.WLCommitPartnerJobRqBody();
        body.client_id = applicationtId;
        body.partner_script_id = getPartnerScriptID();
        body.profile_ids = profile_ids;
        if (verification!=null) {
            body.verification_data = verification;
        } else {
            body.verification_data = null;
        }

        RequestBody requestbody = new RequestBody(RequestBody.POST_METHOD,ApiUtils.md5(ApiUtils.convertObjectToJsonStringNoNulls(body)),date,RequestBody.ENDPOINT_WL_COMMITPARTNERJOB,applicationtId);
        System.out.println("\n"+ApiUtils.convertObjectToJsonStringNoNulls(body)+"\n\n");
        Log.d(TAG, "Message body:\n"+requestbody.toString());
        System.out.println();
        Call<CommitPartnerJob> call = getService().postCommitPartnerJob(date,
                ApiUtils.getAuthorization(getApikey(), getApisecret(), requestbody.toString()),
                body);

        call.enqueue(new Callback<CommitPartnerJob>() {
            @Override
            public void onResponse(Call<CommitPartnerJob> call, Response<CommitPartnerJob> response) {
                if (response.isSuccessful()) {
                    CommitPartnerJob commitPartnerJob = response.body();
                    if (commitPartnerJob == null) {
                        commitPartnerJob = new CommitPartnerJob();
                    }

                    Log.i(TAG,"CommitPartnerJob: Async RAW Response => " + ApiUtils.convertObjectToJsonStringNoNulls(commitPartnerJob));
                    callback.onResponse(commitPartnerJob);
                } else {
                    try {
                        callback.onError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommitPartnerJob> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
