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
        String api_key = "57e9eaa7f7a579094b92dd09";
        String api_secret = "x3Usd0zEZ46MgE9UQeXs0PaeULE66RJLjMEPkKucZROScv3R9DHIWwg2MceOLZLFJvWKBBfdL4j4E7kgbJdshg==";
        String partner_script_id = "57cf81b4f7a5793778074d92";
        Credentials credentials = new Credentials(api_key, api_secret, partner_script_id);


        // Test ApplicationScore API
        String applicationId = "TEST0001C";
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
//        td.key = "EwAoA61DBAAUGCCXc8wU/zFu9QnLdZXy+YnElFkAAYfkwM7EBPT+UGCzFTxRHtDbnexIbq5Bt3+D8p6LTU8+YtYuXt3lG7TluzhPuFdVU+gD7MAuJEBIbrtvBrp9jbHF/ZU5J8mQIBcIkSo2qA9Y+UE7WgbAn0MzZcl7NECVxnEK97rzs9wKPD2RVKzckToFRSWLEX8XfutBBpu+KeqZrtwN2l0dnwoDT1ez5QTvd9FRqfb3Tur0mB6y+xooDl3u64AlZSCFTSqoTdfrr3yHjE+y7aQoDyyD6ddUcYt1Vh08Q4oku3PS2eXREWv+HuALfZtkrdemZgZOp6uTkbuXOjW+ydsB3pB8nGSAxBI598j10UyqL4L5jrJ79JQxVncDZgAACFQ3hFZq1brq+AELxTv5Pfj4KYG23t52xge3ZUfxRHPhgYaM2z4gT6hS32mRmb/WeIGeVuh6Wao8+q7RM5kcYyJQZlq+QykNJYN5wYEQIAn/3OpNwmSLRHUWOC7kERyyL/gzjtUcKU+CNg1i6yPNI7bMLGy2uBisDhy7myddFy5twxbI1e4zxWmNZbTqJoZbETlDCLylSiLAVeikUHbAsvHkn/uCKyRt1CWuJGajKgLNH6ef8s4GAkH/KO1vJ8xDfrtvjeB2x0OZm6I992RhPH7fOYvKaFwKlDeF4JkeRKW04otxLHWYh2ljYodS6ZeJ40K4EFkF44luxGkX3hWcuErTYk9iamACivbSW9mY9dboeTKcZPMcTXlCNyOPztMB1stnyzHgXbW4/IO93RYf+V3LPHq1DDMlbtGs/SF3rwaCnWIdklLtPKbfsWlUmDsJfPV5pH5hEj022c8lq1Ww4CIpyK1Q6lt+osfYzgm5ukm+AWPo9lNcK9lRRq7wrxtsRWMK8LI8E2jSSEdtWnXTlsvQkMbwAbFdFErzLNMuZWeprC1o8zM539FKldXRlo/4V9SWW1AzZhnI+WT8426wZ+CnsA4bwAgB4m7vCmzYXMdq1q7/g4jGata0M06c6FIvuN70MToriK4IE+1JUzRFA/9kFrDtIlUxLdaHeA55ix55wwYfAg==";
        td.key = "EwAoA61DBAAUGCCXc8wU/zFu9QnLdZXy+YnElFkAATR9RO6HF/ADDEIJf+icU/1VryERnEacLY1WRhAnjqyDbkUngLgIdmCkvZotCGZ6finMotVzh/lOSrZsbYKLHncz+e2hXqsn/fPZ46ZrxinihBT9JRlbHgwLLGgXOCUW9Zz/2X/aEIZr2K+WUJor7Iq141hMdH5BhxHSEeXoWWOO2SN/H+NrhjjuulSr84StEEtnv5NtskXUJZ3XaOacdMJwaEXvdnzPRB3m2C5nzgr0fvZQmDcVKr/pC3+HCIpTw1uAP7wICC4EljGfRDPM/L44lzqs36AduRJ8tgS8HEnfZ8n6m0aK0zCkL0VtGvUVHRgbW2BEj4ETiYoc5zicl0ADZgAACN9XJ2pSgM4K+AFQDD19Ri9tjmZiDrc90J/qDGBrvXlxzrM2oiIPKW5NVqTG8/bZVjn7kaqevJUABxNAHL7FmoEOvLY6waIWVsIcTkd7hB9eT8DdkoZ0JJr5MQ89r915aKPdfnmGHGmGaCMQcMimB+QSA/WCtXsdeu7IcrQRl9FEKUezw2Uomjov8V0g8mZ9PDct8BCpXp/9z7Jd3X3MTI7sjvyp+3S9qJXo7F8fDf5blpuQQJFaldRwL/1BQlUSU8/giw+LlTFlc1HGUa9UZLVKR5xoj4wKqc/YZLg/LFbDQ31jKFhDJH7LbJxw+H1FMlgAzeYSQdNrn7rbpW6dhTzueR/vdgdAOItgalLN5N3s+9cTNGIXeiM+Pm8WaLvwVxvJmDnpFwyN8l0XxE/sQByMwTJuBcbAo99BiJmeBChGIc2GBwmRUMlOpNhnAORLMXUjeWQZMduuVr2wvLXCCBNBMc4YqdRMwHC+2BBdOhGLTktLlGDvqMAekt3gTOMYYO/rnX+O3mzzK2AH0gRM3h7KHxg9QXoUUls/m8lciNhfai2vrrpW463ClD7d1BMtYg0MiYUhoKYnQIG+pfkoyTLRRN8Ek8e7Kp7qe1EPejFI5QeIz/OabgC33JyRrGzvccA63p0Yjob+DNekyBEaH+HjkdFr2bCVNKa8GHvI4iD7m3gfAg==";
//        td.secret = "";
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
        verification.name.first="Jo";

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
