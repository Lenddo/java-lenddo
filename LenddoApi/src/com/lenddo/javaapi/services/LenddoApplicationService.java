package com.lenddo.javaapi.services;

import com.google.gson.JsonElement;
import com.lenddo.javaapi.LenddoConfig;
import com.lenddo.javaapi.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by Jay Espinosa on 05/6/19.
 */
public interface LenddoApplicationService {
    @Headers("User-Agent: java_sdk_v"+LenddoConfig.api_version)

    // GET "/applications/partnerscripts/{partnerscript_id}"
    @GET("/applications/partnerscripts/{partner_script_id}")
    Call<EncryptedResponse> getApplications(@Path("partner_script_id") String partner_script_id,
                                       @Header("Date") String date,
                                       @Header("Authorization") String authorization
    );

    // GET "/applications/partnerscripts/{partner_script_id}/applicationids/{application_id}"
    @GET("/applications/partnerscripts/{partner_script_id}/applicationids/{application_id}")
    Call<EncryptedResponse> getApplicationDetails(@Path("partner_script_id") String partner_script_id,
                                            @Path("application_id") String application_id,
                                            @Header("Date") String date,
                                            @Header("Authorization") String authorization
    );

    // GET "/applications/partnerscripts/{partner_script_id}/applicationids/{application_id}/documents/{document_id}"
    @GET("/applications/partnerscripts/{partner_script_id}/applicationids/{application_id}/documents/{document_id}")
    Call<EncryptedResponse> getDocumentByApplicationId(@Path("partner_script_id") String partner_script_id,
                                                 @Path("application_id") String application_id,
                                                 @Path("document_id") String document_id,
                                                 @Header("Date") String date,
                                                 @Header("Authorization") String authorization
    );
}
