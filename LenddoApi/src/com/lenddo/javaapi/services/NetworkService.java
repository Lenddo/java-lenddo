package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfig;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public interface NetworkService {
    @Headers("User-Agent: java_sdk_v"+LenddoConfig.api_version)

    // POST "/ExtraApplicationData/"
    @POST(LenddoConfig.ENDPOINT_NETWORK_SENDEXTRADATA)
    Call<JsonElement> postExtraApplicationData(@Query("application_id") String application_id,
                                           @Query("partner_script_id") String partner_script_id,
                                           @Header("Date") String date,
                                           @Header("Authorization") String authorization,
                                           @Body Object body
    );
}
