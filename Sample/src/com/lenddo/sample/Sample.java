package com.lenddo.sample;

import com.google.gson.JsonArray;
import com.lenddo.javaapi.LenddoApiCallback;
import com.lenddo.javaapi.LenddoScoreApi;
import com.lenddo.javaapi.WhiteLabelApi;
import com.lenddo.javaapi.models.*;
import com.lenddo.javaapi.utils.ApiUtils;

/**
 * Created by Joey Mar Antonio on 1/18/16.
 */
public class Sample {
    public Sample () {}

    public static class Credentials {
        public String api_key;
        public String api_secret;
        public String partner_script_id;

        Credentials (String api_key, String api_secret, String partner_script_id) {
            this.api_key = api_key;
            this.api_secret = api_secret;
            this.partner_script_id = partner_script_id;
        }
    }

    public static void main(String[] args) {
        // Enter your credentials here:
        String api_key = "YOUR LENDDO PROVIDED API KEY";
        String api_secret = "YOUR LENDDO PROVIDED API SECRET";
        String partner_script_id = "YOUR LENDDO PROVIDED PARTNER SCRIPT ID";
        Credentials credentials = new Credentials(api_key, api_secret, partner_script_id);


        // Test ApplicationScore API
        String applicationId = "YOUR APPLICATION ID";
        getApplicationScore(credentials, applicationId);

        // Test ApplicationVerification API
        getApplicationVerification(credentials, applicationId);

        // Test Whitelable API
        String provider = WhiteLabelApi.PROVIDER_WINDOWSLIVE;
        samplePostPartnerToken(credentials, applicationId, provider);
    }



    // TEST CODE FOR GETTING APPLICATION SCORE
    private static void getApplicationScore(Credentials credentials, String applicationId) {
        LenddoScoreApi lenddoapi = new LenddoScoreApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        // Set this to true to see debug messages during debug build.
        lenddoapi.debugMode(true);

        lenddoapi.getApplicationScore(applicationId, new LenddoApiCallback<ClientScore>() {
            @Override
            public void onResponse(ClientScore clientScore) {
                System.out.println("Resulting application score: "+ clientScore.score);
                System.out.println("Resulting application flags: "+ clientScore.flags);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Network Connection Failed: "+ throwable.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+ errormessage);
            }
        });
    }

    // TEST CODE FOR GETTING APPLICATION VERIFICATION
    private static void getApplicationVerification(Credentials credentials, String applicationId) {
        LenddoScoreApi lenddoapi = new LenddoScoreApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        // Set this to true to see debug messages during debug build.
        lenddoapi.debugMode(true);

        lenddoapi.getApplicationVerification(applicationId, new LenddoApiCallback<ClientVerification>() {
            @Override
            public void onResponse(ClientVerification response) {
                // Sample responses
                System.out.println("ApplicationVerification: "+ ApiUtils.convertObjectToJsonString(response));
                System.out.println("probes: "+ ApiUtils.convertObjectToJsonString(response.probes));
                System.out.println("probe name: "+ response.probes.name);
                System.out.println("probe firstname: "+ response.probes.name.get(0));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Network Connection Failed: "+ throwable.getMessage());
            }

            @Override
            public void onError(String errormessage) {
                System.out.println("Returned error: "+ errormessage);
            }
        });
    }

    // TEST CODE FOR PARTNERTOKEN API
    private static void samplePostPartnerToken(final Credentials credentials, final String applicationId, String provider) {
        WhiteLabelApi whiteLabelApi = new WhiteLabelApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        WhitelabelRequestBody.WLPartnerTokenRqBody.token_data td = new WhitelabelRequestBody.WLPartnerTokenRqBody.token_data();
        // add a token in the td.key and a secret in td.secret
        td.key = "ACCESS TOKEN FROM YOUR CHOSEN PROVIDER";
//        td.secret = "SECRET FROM YOUR CHOSEN PROVIDER (IF APPLICABLE)";
        whiteLabelApi.postPartnerToken(applicationId, provider, td, new LenddoApiCallback<PartnerToken>() {
            @Override
            public void onResponse(PartnerToken response) {
                System.out.println("response="+ response.profile_id);
                // get the profile ids from the response and use postCommitPartnerJob() to send the profile ids.
                samplePostCommitPartnerJob(credentials, applicationId, response.profile_id);
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
    private static void samplePostCommitPartnerJob(Credentials credentials, String applicationId, String profileId) {
        WhiteLabelApi whiteLabelApi = new WhiteLabelApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        JsonArray profile_ids = new JsonArray();
        profile_ids.add(profileId);
        Verification verification = new Verification();
        // at this point, you need to add details for the verification object. (name, employer, etc).
        verification.name.first="firstname";
        verification.name.last="lastname";

        whiteLabelApi.postCommitPartnerJob(applicationId, profile_ids, verification, new LenddoApiCallback() {
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
