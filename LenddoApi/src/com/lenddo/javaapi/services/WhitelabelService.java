package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lenddo.javaapi.LenddoConfig;
import com.lenddo.javaapi.models.CommitPartnerJob;
import com.lenddo.javaapi.models.PartnerToken;
import com.lenddo.javaapi.models.WhitelabelRequestBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Joey Mar Antonio on 1/13/15.
 */
public interface WhitelabelService {
    @Headers("User-Agent: java_sdk_v"+LenddoConfig.api_version)

    // POST "/PartnerToken"
    @POST(LenddoConfig.ENDPOINT_WL_PARTNERTOKEN)
    Call<PartnerToken> postPartnerToken(@Header("Date") String date,
                                        @Header("Authorization") String authorization,
                                        @Body WhitelabelRequestBody.WLPartnerTokenRqBody body
    );

    // POST "/CommitPartnerJob/"
    @POST(LenddoConfig.ENDPOINT_WL_COMMITPARTNERJOB)
    Call<CommitPartnerJob> postCommitPartnerJob(@Header("Date") String date,
                                                @Header("Authorization") String authorization,
                                                @Body WhitelabelRequestBody.WLCommitPartnerJobRqBody body
    );

}
