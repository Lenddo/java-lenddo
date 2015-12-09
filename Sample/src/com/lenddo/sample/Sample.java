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

        long startTime = System.nanoTime();
        System.out.println("Sample Main\n");
        LenddoApi lenddoapi = new LenddoApi(api_key, api_secret);
        lenddoapi.debugMode(false);
        System.out.println("==========================================================");
        lenddoapi.getClientScoreAsString("Put your CLIENT_ID here");
//        System.out.println("==========================================================");
//        lenddoapi.getClientScoreAsString("Put your CLIENT_ID here");
//        System.out.println("==========================================================");
//        lenddoapi.getClientScoreAsString("Put your CLIENT_ID here");
//        System.out.println("==========================================================");
//        lenddoapi.getClientVerificationAsString("Put your CLIENT_ID here");
//        System.out.println("==========================================================");
        lenddoapi.getClientVerificationAsString("Put your CLIENT_ID here");
        System.out.println("==========================================================");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration: "+duration/1000000+"ms");
    }

}
