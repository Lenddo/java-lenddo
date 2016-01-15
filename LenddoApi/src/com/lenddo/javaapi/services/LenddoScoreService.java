package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfig;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public interface LenddoScoreService {
    // GET "/ClientScore/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTSCORE + "{clientId}")
    Call<ClientScore> getClientScorePOJO(@Path("clientId") String clientId,
                                         @Header("Date") String date,
                                         @Header("Authorization") String authorization
    );

    // GET "/ClientScore/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTSCORE + "{clientId}")
    Call<JsonElement> getClientScore(@Path("clientId") String clientId,
                                     @Header("Date") String date,
                                     @Header("Authorization") String authorization
    );


    // GET "/ClientVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTVERIFICATION + "{clientId}")
    Call<ClientVerification> getClientVerificationPOJO(@Path("clientId") String clientId,
                                                       @Header("Date") String date,
                                                       @Header("Authorization") String authorization
    );

    // GET "/ClientVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_SCORE_CLIENTVERIFICATION + "{clientId}")
    Call<JsonElement> getClientVerification(@Path("clientId") String clientId,
                                            @Header("Date") String date,
                                            @Header("Authorization") String authorization
    );

}
