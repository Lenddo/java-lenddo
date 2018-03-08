package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfig;
import com.lenddo.javaapi.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public interface LenddoScoreService {
    @Headers("User-Agent: java_sdk_v"+LenddoConfig.api_version)

    // GET "/ApplicationScore/{client_id}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONSCORE + "{client_id}")
    Call<ApplicationScore> getApplicationScorePOJO(@Path("client_id") String client_id,
                                                   @Query("partner_script_id") String partner_script_id,
                                                   @Header("Date") String date,
                                                   @Header("Authorization") String authorization
    );

    // GET "/ApplicationScore/{client_id}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONSCORE + "{client_id}")
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


    // GET "/ApplicationVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONVERIFICATION + "{clientId}")
    Call<ApplicationVerification> getApplicationVerificationPOJO(@Path("clientId") String clientId,
                                                                 @Query("partner_script_id") String partner_script_id,
                                                                 @Header("Date") String date,
                                                                 @Header("Authorization") String authorization
    );

    // GET "/ApplicationVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONVERIFICATION + "{clientId}")
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

    // GET "/ApplicationMultipleScores/{application_id}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONMULTIPLESCORES + "{application_id}")
    Call<ApplicationMultipleScores> getApplicationMultipleScoresPOJO(@Path("application_id") String application_id,
                                                               @Query("partner_script_id") String partner_script_id,
                                                               @Header("Date") String date,
                                                               @Header("Authorization") String authorization
    );

    // GET "/ApplicationMultipleScores/{applicationId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_APPLICATIONMULTIPLESCORES + "{application_id}")
    Call<JsonElement> getApplicationMultipleScores(@Path("application_id") String application_id,
                                             @Query("partner_script_id") String partner_script_id,
                                             @Header("Date") String date,
                                             @Header("Authorization") String authorization
    );
}
