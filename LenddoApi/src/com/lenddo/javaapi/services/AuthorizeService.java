package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfig;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Joey Mar Antonio on 17/08/2017.
 */
public interface AuthorizeService {
    @Headers("User-Agent: java_sdk_v"+ LenddoConfig.api_version)

    // GET "/healthcheck"
    @GET(LenddoConfig.ENDPOINT_AUTHORIZE_HEALTHCHECK)
    Call<JsonElement> getAuthorizeHealthcheck(
            @Header("Date") String date,
            @Header("Authorization") String authorization
    );

    // POST "/onboarding/prioritydata"
    @POST(LenddoConfig.ENDPOINT_AUTHORIZE_ONBOARDING_PRIORITYDATA)
    Call<JsonElement> postAuthorizeOnboardingPrioritydata(
            @Header("Date") String date,
            @Header("Authorization") String authorization,
            @Body Object body
    );
}
