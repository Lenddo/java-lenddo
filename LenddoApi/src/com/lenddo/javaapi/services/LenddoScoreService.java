package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfig;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public interface LenddoScoreService {
    @Headers("User-Agent: java_sdk_v"+LenddoConfig.api_version)

    // GET "/ClientScore/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTSCORE + "{clientId}")
    Call<ClientScore> getClientScorePOJO(@Path("clientId") String clientId,
                                         @Query("partner_script_id") String partner_script_id,
                                         @Header("Date") String date,
                                         @Header("Authorization") String authorization
    );

    // GET "/ClientScore/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTSCORE + "{clientId}")
    Call<JsonElement> getClientScore(@Path("clientId") String clientId,
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

}
