package com.lenddo.sample.whitelabel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WhitelabelService {
    @Headers("User-Agent: java_sdk_v" + LenddoConfig.api_version)
    // POST "/PartnerToken"
    @POST("/PartnerToken")
    Call<PartnerToken> postPartnerToken(@Header("Date") String date,
                                  @Header("Authorization") String authorization,
                                  @Body WhitelabelRequestBody.WLPartnerTokenRqBody body
    );

    // POST "/CommitPartnerJob/"
    @POST("/CommitPartnerJob")
    Call<CommitPartnerJob> postCommitPartnerJob(@Header("Date") String date,
                                                @Header("Authorization") String authorization,
                                                @Body WhitelabelRequestBody.WLCommitPartnerJobRqBody body
    );

}
