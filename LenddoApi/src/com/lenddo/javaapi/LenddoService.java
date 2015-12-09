package com.lenddo.javaapi;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public interface LenddoService {
    // GET "/ClientScore/{clientId}"
    @GET(LenddoConfig.ENDPOINT_CLIENTSCORE + "{clientId}")
    Call<ClientScore> getClientScorePOJO(@Path("clientId") String clientId,
                                         @Header("Date") String date,
                                         @Header("Authorization") String authorization
    );

    // GET "/ClientScore/{clientId}"
    @GET(LenddoConfig.ENDPOINT_CLIENTSCORE + "{clientId}")
    Call<JsonElement> getClientScore(@Path("clientId") String clientId,
                                     @Header("Date") String date,
                                     @Header("Authorization") String authorization
    );


    // GET "/ClientVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_CLIENTVERIFICATION + "{clientId}")
    Call<ClientVerification> getClientVerificationPOJO(@Path("clientId") String clientId,
                                                       @Header("Date") String date,
                                                       @Header("Authorization") String authorization
    );

    // GET "/ClientVerification/{clientId}"
    @GET(LenddoConfig.ENDPOINT_CLIENTVERIFICATION + "{clientId}")
    Call<JsonElement> getClientVerification(@Path("clientId") String clientId,
                                            @Header("Date") String date,
                                            @Header("Authorization") String authorization
    );

}
