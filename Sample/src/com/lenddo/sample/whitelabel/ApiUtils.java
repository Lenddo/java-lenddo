package com.lenddo.sample.whitelabel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ApiUtils {

    private static final String TAG = ApiUtils.class.getName();
    private static Gson gson = null;

    public static String getDate() {
        // Tue Dec 08 11:00:00 GMT 2015
        Calendar c = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String nowDate = dateFormat.format(c.getTime());
        System.out.println( "Date: " + nowDate);
        return nowDate;
    }

    public static String getAuthorization(String apiKey, String apiSecret, String message) {
        String authorization = "LENDDO " + apiKey + ":" + hash(message, apiSecret);
        System.out.println( "Authorization: " + authorization);
        return authorization;
    }

    private static String hash(String message, String secret) {
        String hash = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA1");
            mac.init(signingKey);
            hash = Base64.encodeBytes(mac.doFinal(message.getBytes("UTF-8")));
            System.out.println( "hash: "+hash);
            mac.reset();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("hash(message, secret) NoSuchAlgorithmException");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("hash(message, secret) InvalidKeyException");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("hash(message, secret) UnsupportedEncodingException");
            e.printStackTrace();
        }
        return hash;
    }

    public static String convertObjectToJsonString(Object object) {
        gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().disableHtmlEscaping().create();
        return gson.toJson(object);
    }

    public static String convertObjectToPrettyJsonString(Object object) {
        gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().disableHtmlEscaping().create();
        return prettyJSON(gson.toJson(object));
    }

    public static String convertObjectToJsonStringNoNulls(Object object) {
        gson = new GsonBuilder().serializeSpecialFloatingPointValues().disableHtmlEscaping().create();
        return gson.toJson(object);
    }

    public static String md5( String input ) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes( "UTF-8" ));
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(String.format("%02x", anArray));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    public static String prettyJSON(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        return gson.toJson(je);
    }

    public static String convertObjectToXML(Object object) {
        StringWriter sw = new StringWriter();
        String xmlString = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(object, sw);
            xmlString = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (xmlString == null) {
            return "";
        } else {
            return xmlString;
        }
    }
}
