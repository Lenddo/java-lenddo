package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfiguredSession;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Joey Mar Antonio on 1/12/16.
 */
public interface LenddoOnboardingService {

    // POST "/onboarding/ConfiguredSession"
    @POST("/onboarding/ConfiguredSession")
    Call<JsonElement> getShortURL(@Body LenddoConfiguredSession.RequestBody body);

}
