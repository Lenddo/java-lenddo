package com.lenddo.sample;

import com.lenddo.javaapi.LenddoApi;
import com.lenddo.javaapi.LenddoApiCallback;
import com.lenddo.javaapi.models.ClientScore;
import com.lenddo.javaapi.models.ClientVerification;
import com.lenddo.javaapi.utils.ApiUtils;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class Sample {

    public Sample() { System.out.println("Sample Constructor"); }

    public static void main(String[] args) {

        String api_key = "Put your provided API_KEY here";
        String api_secret = "Put your provided API_SECRET here";
        String partner_script_id = "Put your provided PARTNER_SCRIPT_ID here";

        long startTime = System.nanoTime();
        System.out.println("Sample Main\n");
        LenddoApi lenddoapi = new LenddoApi(api_key, api_secret);
        lenddoapi.debugMode(false);
        System.out.println("==========================================================");



        lenddoapi.getApplicationScore("YOUR APPLICATION_ID", new LenddoApiCallback<ClientScore>() {
            @Override
            public void onResponse(ClientScore response) {
                System.out.println("score: "+response.score);
                System.out.println("flags: "+response.flags);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Connection Failure: "+t.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+ errormessage);
            }
        });


	lenddoapi.getClientVerification("YOUR_APPLICATION_ID", new LenddoApiCallback<ClientVerification>() {
              @Override
              public void onResponse(ClientVerification response) {
                  System.out.println("ClientVerification: "+ ApiUtils.convertObjectToJsonString(response));
                  System.out.println("probes: "+ ApiUtils.convertObjectToJsonString(response.probes));
                  System.out.println("probe name: "+ response.probes.name);
                  System.out.println("probe firstname: "+ response.probes.name.get(0));
              }

              @Override
              public void onFailure(Throwable t) {
                  System.out.println("Connection Failure: "+t.getMessage());
              }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+ errormessage);
            }
          });



        samplePostPartnerToken();
        //=========================================================================================
        samplePostCommitPartnerJob();
        //=========================================================================================

        System.out.println("==========================================================");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration: "+duration/1000000+"ms");
    }

    // TEST CODE FOR PARTNERTOKEN API
    private static void samplePostPartnerToken() {
	String api_key = "Your Lenddo api key";
	String api_secret = "Your Lenddo api secret";
	String partner_script_id = "Your partner script id";

        WhiteLabelApi whiteLabelApi = new WhiteLabelApi(api_key, api_secret, partner_script_id);
        WhitelabelRequestBody.WLPartnerTokenRqBody.token_data td = new WhitelabelRequestBody.WLPartnerTokenRqBody.token_data();
        td.key = "your token";
	String provider = WhiteLabelApi.PROVIDER_FACEBOOK;
        whiteLabelApi.postPartnerToken("your application id", provider, td, new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println("response="+ ApiUtils.convertObjectToJsonString(response));
		// get the profile ids from the response and use postCommitPartnerJob() to send the profile ids.
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Connection Failure: "+t.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+errormessage);
            }
        });
    }

    // TEST CODE FOR COMMITPARTNERJOB API
    private static void samplePostCommitPartnerJob() {
	String api_key = "Your Lenddo api key";
	String api_secret = "Your Lenddo api secret";
	String partner_script_id = "Your partner script id";

        WhiteLabelApi whiteLabelApi = new WhiteLabelApi(api_key, api_secret, partner_script_id);
        String application_id = "your application id";
        JsonArray profile_ids = new JsonArray();
        profile_ids.add("the resulting partner id from callback of postPartnerToken()");
        Verification verification = new Verification();

        whiteLabelApi.postCommitPartnerJob(application_id, profile_ids, verification, new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println("response="+ ApiUtils.convertObjectToJsonString(response));
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Connection Failure: "+t.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+errormessage);
            }
        });
    }
}
