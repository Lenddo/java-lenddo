package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfig;
import com.lenddo.javaapi.models.ApplicationFeatures;
import com.lenddo.javaapi.models.ApplicationScorecards;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public interface LenddoScoreService {
    @Headers("User-Agent: java_sdk_v"+LenddoConfig.api_version)

    // GET "/ClientScore/{client_id}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTSCORE + "{client_id}")
    Call<ClientScore> getClientScorePOJO(@Path("client_id") String client_id,
                                         @Query("partner_script_id") String partner_script_id,
                                         @Header("Date") String date,
                                         @Header("Authorization") String authorization
    );

    // GET "/ClientScore/{client_id}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTSCORE + "{client_id}")
    Call<JsonElement> getClientScore(@Path("client_id") String client_id,
                                     @Query("partner_script_id") String partner_script_id,
                                     @Header("Date") String date,
                                     @Header("Authorization") String authorization
    );

    // GET "/ApplicationScorecards/{application_id}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONSCORECARDS + "{application_id}")
    Call<ApplicationScorecards> getApplicationScorecardPOJO(@Path("application_id") String application_id,
                                                         @Query("partner_script_id") String partner_script_id,
                                                         @Header("Date") String date,
                                                         @Header("Authorization") String authorization
    );

    // GET "/ApplicationScorecards/{applicationId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONSCORECARDS + "{application_id}")
    Call<JsonElement> getApplicationScorecard(@Path("application_id") String application_id,
                                     @Query("partner_script_id") String partner_script_id,
                                     @Header("Date") String date,
                                     @Header("Authorization") String authorization
    );


    // GET "/ClientVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTVERIFICATION + "{clientId}")
    Call<ClientVerification> getClientVerificationPOJO(@Path("clientId") String clientId,
                                                       @Query("partner_script_id") String partner_script_id,
                                                       @Header("Date") String date,
                                                       @Header("Authorization") String authorization
    );

    // GET "/ClientVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTVERIFICATION + "{clientId}")
    Call<JsonElement> getClientVerification(@Path("clientId") String clientId,
                                            @Query("partner_script_id") String partner_script_id,
                                            @Header("Date") String date,
                                            @Header("Authorization") String authorization
    );

    // GET "/ApplicationFeatures/{application_id}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONFEATURES + "{application_id}")
    Call<ApplicationFeatures> getApplicationFeaturesPOJO(@Path("application_id") String application_id,
                                                         @Query("partner_script_id") String partner_script_id,
                                                         @Header("Date") String date,
                                                         @Header("Authorization") String authorization
    );

    // GET "/ApplicationFeatures/{applicationId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONFEATURES + "{application_id}")
    Call<JsonElement> getApplicationFeatures(@Path("application_id") String application_id,
                                              @Query("partner_script_id") String partner_script_id,
                                              @Header("Date") String date,
                                              @Header("Authorization") String authorization
    );
}
