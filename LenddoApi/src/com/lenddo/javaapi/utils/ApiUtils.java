package com.lenddo.javaapi.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class ApiUtils {

    private static final String TAG = ApiUtils.class.getName();
    private static Gson gson = null;

    public static String getDate() {
        // Tue Dec 08 11:00:00 GMT 2015
        Calendar c = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz YYYY", Locale.US);
        String nowDate = dateFormat.format(c.getTime());
        Log.d(TAG, "Date: " + nowDate);
        return nowDate;
    }

    public static String getAuthorization(String apiKey, String apiSecret, String message) {
        String authorization = "LENDDO " + apiKey + ":" + hash(message, apiSecret);
        Log.d(TAG, "Authorization: " + authorization);
        return authorization;
    }

    private static String hash(String message, String secret) {
        String hash = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA1");
            mac.init(signingKey);
            hash = DatatypeConverter.printBase64Binary(mac.doFinal(message.getBytes("UTF-8")));
            Log.d(TAG, "hash: "+hash);
            mac.reset();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG,"hash(message, secret) NoSuchAlgorithmException");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            Log.e(TAG,"hash(message, secret) InvalidKeyException");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG,"hash(message, secret) UnsupportedEncodingException");
            e.printStackTrace();
        }
        return hash;
    }

    public static String convertObjectToJsonString(Object object) {
        gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().disableHtmlEscaping().create();
        return gson.toJson(object);
    }

    public static String convertObjectToJsonStringNoNulls(Object object) {
        gson = new GsonBuilder().serializeSpecialFloatingPointValues().disableHtmlEscaping().create();
        return gson.toJson(object);
    }

    public static String md5( String input ) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes( "UTF-8" ));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append( String.format( "%02x", array[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

}
