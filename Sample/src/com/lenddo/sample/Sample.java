package com.lenddo.sample;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lenddo.javaapi.*;
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
        String api_key = "API KEY";
        String partner_script_id = "PARTNERSCRIPT ID";
        String private_key = "Sample\\private.pem";
        String api_secret = "API SECRET";
        String document_id = "DOCUMENT ID";

        double pageSize = 100;
        double pageNumber = 1;

        // Format 2019-05-30 20:00
        String startDate = "START DATE";
        String endDate = "END DATE";

        Credentials credentials = new Credentials(api_key, api_secret, partner_script_id);

        // Test ApplicationScore API
        String applicationId = "APPLICATION ID";
        getApplicationScore(credentials, applicationId);

        // Test ApplicationScorecards API
        getApplicationScorecards(credentials, applicationId);

        // Test ApplicationVerification API
        getApplicationVerification(credentials, applicationId);

        // Test Whitelable API
        String provider = WhiteLabelApi.PROVIDER_WINDOWSLIVE;
        samplePostPartnerToken(credentials, applicationId, provider);

        // For invoking application endpoints, set to qa or prod
        LenddoConfig.setApplicationMode("prod");

        // Test Applications API
        sampleGetApplications(credentials, private_key);
        sampleGetApplicationsWithFilter(credentials, private_key, pageSize, pageNumber, startDate, endDate);
        sampleGetApplicationDetails(credentials, private_key, applicationId);
        sampleGetDocumentDetails(credentials, private_key, applicationId, document_id);
    }


    // TEST CODE FOR GETTING AUTHORIZE HEALTHCHECK
    private static void sampleGetAuthorizeHealtcheck(Credentials credentials, String applicationId) {
        AuthorizeApi authorizeApi = new AuthorizeApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        // Set this to true to see debug messages during debug build.
        authorizeApi.debugMode(true);

        authorizeApi.getAuthorizeHealthcheck(applicationId, new LenddoApiCallback<JsonElement>() {
            @Override
            public void onResponse(JsonElement response) {
                System.out.println("Resulting healthcheck: "+ response.toString());
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

    // TEST CODE FOR POST AUTHORIZE ONBOARDING PRIORITYDATA
    private static void samplePostAuthorizeOnboardingPriorityData(Credentials credentials, String applicationId, PriorityDataRequestBody body) {
        AuthorizeApi authorizeApi = new AuthorizeApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        AuthorizeApi.debugMode(true);
        if (body == null) {
            body = new PriorityDataRequestBody();
        }
        authorizeApi.postAuthorizeOnboardingPrioritydata(applicationId, body, new LenddoApiCallback() {
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

    // TEST CODE FOR GETTING APPLICATION SCORE
    private static void getApplicationScore(Credentials credentials, String applicationId) {
        LenddoScoreApi lenddoapi = new LenddoScoreApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        // Set this to true to see debug messages during debug build.
        lenddoapi.debugMode(true);

        lenddoapi.getApplicationScore(applicationId, new LenddoApiCallback<ApplicationScore>() {
            @Override
            public void onResponse(ApplicationScore applicationScore) {
                System.out.println("Resulting application score: "+ applicationScore.score);
                System.out.println("Resulting application flags: "+ applicationScore.flags);
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

    // TEST CODE FOR GETTING APPLICATION MULTIPLE SCORE
    private static void getApplicationMultipleScores(Credentials credentials, String applicationId) {
        LenddoScoreApi lenddoapi = new LenddoScoreApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        // Set this to true to see debug messages during debug build.
        lenddoapi.debugMode(true);

        lenddoapi.getApplicationMultipleScores(applicationId, new LenddoApiCallback<ApplicationMultipleScores>() {
            @Override
            public void onResponse(ApplicationMultipleScores applicationMultipleScores) {
                System.out.println("Result:\n"+ ApiUtils.convertObjectToPrettyJsonString(applicationMultipleScores));
                System.out.println("XML Result:\n"+ ApiUtils.convertObjectToXML(applicationMultipleScores));
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

        lenddoapi.getApplicationVerification(applicationId, new LenddoApiCallback<ApplicationVerification>() {
            @Override
            public void onResponse(ApplicationVerification response) {
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

    // TEST CODE FOR GETTING APPLICATION SCORE CARDS
    private static void getApplicationScorecards(Credentials credentials, String applicationId) {
        LenddoScoreApi lenddoapi = new LenddoScoreApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        // Set this to true to see debug messages during debug build.
        lenddoapi.debugMode(true);

        lenddoapi.getApplicationScorecards(applicationId, new LenddoApiCallback<ApplicationScorecards>() {
            @Override
            public void onResponse(ApplicationScorecards applicationScorecards) {
                System.out.println("Resulting application score: "+ applicationScorecards.scorecards);
                System.out.println("Resulting application flags: "+ applicationScorecards.flags);
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

        JsonObject partner_data = new JsonObject();
        partner_data.addProperty("sample_partner_data_1", 1);
        partner_data.addProperty("sample_partner_data_2", "This is a string data");
        partner_data.addProperty("sample_partner_data_3", true);

        whiteLabelApi.postCommitPartnerJob(applicationId, profile_ids, verification, partner_data, new LenddoApiCallback() {
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

    // TEST CODE FOR SEND EXTRA PARTNER DATA
    private static void samplePostExtraPartnerData(Credentials credentials, String applicationId, JsonObject extraData) {
        NetworkApi networkApi = new NetworkApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id);
        NetworkApi.debugMode(true);
        networkApi.postExtraApplicationData(applicationId, extraData, new LenddoApiCallback() {
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

    // TEST CODE FOR APPLICATIONS
    private static void sampleGetApplications(Credentials credentials, String privateKey) {
        LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
        LenddoApplicationApi.debugMode(true);
        lenddoApplicationApi.getApplications(credentials.partner_script_id, new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println("response=" + ApiUtils.convertObjectToJsonString(response));
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

    private static void sampleGetApplicationsWithFilter(Credentials credentials,
                                                        String privateKey,
                                                        double pageSize,
                                                        double pageNumber,
                                                        String startDate,
                                                        String endDate) {
        LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
        LenddoApplicationApi.debugMode(true);
        lenddoApplicationApi.getApplicationsWithFilter(
                credentials.partner_script_id,
                pageSize,
                pageNumber,
                startDate,
                endDate,
                new LenddoApiCallback() {
            @Override
            public void onResponse(Object response) {
                System.out.println("response=" + ApiUtils.convertObjectToJsonString(response));
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

    private static void sampleGetApplicationDetails(Credentials credentials, String privateKey, String applicationId) {
        LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
        LenddoApplicationApi.debugMode(true);
        lenddoApplicationApi.getApplicationDetails(credentials.partner_script_id, applicationId, new LenddoApiCallback() {
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

    private static void sampleGetDocumentDetails(Credentials credentials, String privateKey, String applicationId, String documentId) {
        LenddoApplicationApi lenddoApplicationApi = new LenddoApplicationApi(credentials.api_key, credentials.api_secret, credentials.partner_script_id, privateKey);
        LenddoApplicationApi.debugMode(true);
        lenddoApplicationApi.getDocumentDetails(credentials.partner_script_id, applicationId, documentId, new LenddoApiCallback() {
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
