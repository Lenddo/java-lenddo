package com.lenddo.sample.whitelabel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lenddo.javaapi.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class WhitelabelSample {

    private Retrofit retrofit;
    private WhitelabelService whitelabelService;

    private static final String TAG = WhitelabelSample.class.getName();
    
    private static final String KEY = "ACCESS TOKEN FROM YOUR CHOSEN PROVIDER";

    public void postPartnerToken(
            String apiKey,
            String apiSecret,
            String applicationId,
            String partnerscriptId,
            String provider
    ) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://networkservice.lendqa.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.setWhitelabelService(retrofit.create(WhitelabelService.class));

        WhitelabelRequestBody.WLPartnerTokenRqBody.token_data td = new WhitelabelRequestBody.WLPartnerTokenRqBody.token_data();
//        td.key = "ACCESS TOKEN FROM YOUR CHOSEN PROVIDER";
        td.key = KEY;

        String date = ApiUtils.getDate();
        WhitelabelRequestBody.WLPartnerTokenRqBody body = new WhitelabelRequestBody.WLPartnerTokenRqBody();
        body.client_id = applicationId;
        body.provider = provider;
        body.token_data = td;

        RequestBody requestbody = new RequestBody(
                RequestBody.POST_METHOD,
                ApiUtils.md5(ApiUtils.convertObjectToJsonString(body)),
                date,
                RequestBody.ENDPOINT_WL_PARTNERTOKEN,
                applicationId);

        System.out.println("\nRequest body:"+ ApiUtils.convertObjectToJsonString(body)+"\n");
        System.out.println("\nMessage body:" + requestbody.toString());

        String test = com.lenddo.javaapi.utils.ApiUtils.getAuthorization(apiKey, apiSecret, requestbody.toString());
        System.out.println("\ntest: " + test);

        Call<PartnerToken> call = this.getWhitelabelService().postPartnerToken(date,
                test,
                body);

        call.enqueue(new Callback<PartnerToken>() {
            @Override
            public void onResponse(Call<PartnerToken> call, Response<PartnerToken> response) {
                PartnerToken partnerToken = response.body();
                if (partnerToken == null) {
                    partnerToken = new PartnerToken();
                }

                System.out.println("postPartnerToken: Async RAW Response => " + ApiUtils.convertObjectToJsonString(partnerToken));

                // Invoke CommitPartnerJob
                postCommitPartnerJob(
                        apiKey,
                        apiSecret,
                        applicationId,
                        partnerscriptId,
                        partnerToken.profile_id
                );
            }

            @Override
            public void onFailure(Call<PartnerToken> call, Throwable t) {
                System.out.println("Message body:\n" + t.getMessage());
            }
        });
    }

    public void postCommitPartnerJob(
            String apiKey,
            String apiSecret,
            String applicationId,
            String partnerscriptId,
            String profileId
    ) {

        JsonArray profile_ids = new JsonArray();
        profile_ids.add(profileId);
        Verification verification = new Verification();
        // at this point, you need to add details for the verification object. (name, employer, etc).
        verification.name.first = "firstname";
        verification.name.last = "lastname";

        JsonObject partner_data = new JsonObject();
        partner_data.addProperty("sample_partner_data_1", 1);
        partner_data.addProperty("sample_partner_data_2", "This is a string data");
        partner_data.addProperty("sample_partner_data_3", true);

        String date = ApiUtils.getDate();
        WhitelabelRequestBody.WLCommitPartnerJobRqBody body = new WhitelabelRequestBody.WLCommitPartnerJobRqBody();
        body.client_id = applicationId;
        body.partner_script_id = partnerscriptId;
        body.profile_ids = profile_ids;

        if (verification != null) {
            body.verification_data = verification;
        } else {
            body.verification_data = new Verification();
        }
        if (partner_data!=null) {
            body.partner_data = partner_data;
        }

        RequestBody requestbody = new RequestBody(
                RequestBody.POST_METHOD,
                ApiUtils.md5(ApiUtils.convertObjectToJsonStringNoNulls(body)),
                date,
                RequestBody.ENDPOINT_WL_COMMITPARTNERJOB,
                applicationId);

        System.out.println("\nRequest = "+ ApiUtils.convertObjectToJsonStringNoNulls(body)+"\n\n");

        Call<CommitPartnerJob> call = this.getWhitelabelService().postCommitPartnerJob(
                date,
                ApiUtils.getAuthorization(apiKey, apiSecret, requestbody.toString()),
                body);

        call.enqueue(new Callback<CommitPartnerJob>() {
            @Override
            public void onResponse(Call<CommitPartnerJob> call, Response<CommitPartnerJob> response) {
                if (response.isSuccessful()) {
                    CommitPartnerJob commitPartnerJob = response.body();
                    if (commitPartnerJob == null) {
                        commitPartnerJob = new CommitPartnerJob();
                    }

                    System.out.println("CommitPartnerJob: Async RAW Response => " + commitPartnerJob.success);
                } else {
                    try {
                        System.out.println("Message body:\n" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommitPartnerJob> call, Throwable t) {
                System.out.println("Message body:\n" + t.getMessage());
            }
        });
    }

    //region Getters and Setters
    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public WhitelabelService getWhitelabelService() {
        return whitelabelService;
    }

    public void setWhitelabelService(WhitelabelService whitelabelService) {
        this.whitelabelService = whitelabelService;
    }
    //endregion
}
