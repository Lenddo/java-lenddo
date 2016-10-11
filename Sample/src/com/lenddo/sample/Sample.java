package com.lenddo.sample;

import com.lenddo.javaapi.LenddoApi;

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
        lenddoapi.getClientScore("YOUR_CLIENT_ID", new LenddoApiCallback<ClientScore>() {
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


	lenddoapi.getClientVerification("YOUR_CLIENT_ID", new LenddoApiCallback<ClientVerification>() {
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
        System.out.println("==========================================================");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration: "+duration/1000000+"ms");
    }

}
