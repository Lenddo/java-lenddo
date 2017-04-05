package com.lenddo.javaapi;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.services.LenddoOnboardingService;
import com.lenddo.javaapi.utils.Log;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;


import java.io.IOException;

/**
 * Created by Joey Mar Antonio on 1/12/16.
 */
public class LenddoConfiguredSession {

    private String partnerScriptId;
    private String applicationId;
    private VerificationData verificationData;
    private RequestBody requestBody;
    private LenddoOnboardingService service;

    public LenddoConfiguredSession(String partnerScriptId, String applicationId, VerificationData verificationData) {
        this.partnerScriptId = partnerScriptId;
        this.applicationId = applicationId;
        this.verificationData = verificationData;
        initRequestBody(partnerScriptId,applicationId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LenddoConfig.onboarding_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(LenddoOnboardingService.class);
    }

    public LenddoConfiguredSession(String partnerScriptId, String applicationId, VerificationData verificationData, String base_url) {
        this.partnerScriptId = partnerScriptId;
        this.applicationId = applicationId;
        this.verificationData = verificationData;
        initRequestBody(partnerScriptId,applicationId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(LenddoOnboardingService.class);
    }

    private void initRequestBody(String ps_id, String c_id) {
        this.requestBody = new RequestBody();
        this.requestBody.ps_id = ps_id;
        this.requestBody.c_id = c_id;
        this.requestBody.v_d = new VerificationData();
    }

    public String getPartnerScriptId() {
        return partnerScriptId;
    }

    @Deprecated
    public String getClientId() {
        return getApplicationId();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public VerificationData getVerificationData() {
        return verificationData;
    }

    public String getShortURL() {
        String short_url = "";
        Call<JsonElement> call = service.getShortURL(requestBody);
        try {
            short_url = call.execute().body().getAsJsonObject().get("short_url").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("SHORT_URL", short_url);
        return short_url;
    }

    public static class RequestBody {
        public String ps_id;
        public String c_id;
        public VerificationData v_d;
    }
    public static class VerificationData {
        public String firstname;
        public String middlename;
        public String lastname;
        public String birthdate;
        public String employer;
        public String email;
        public String mobilephone;
        public String university;
    }
}
