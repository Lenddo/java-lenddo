package com.lenddo.javaapi.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joey Mar Antonio on 1/12/16.
 */
public class ApiUtilsTest {
    protected String apiKey;
    protected String apiSecret;
    protected String message;

    @Before
    public void setUp() throws Exception {
        apiKey = "apiKey";
        apiSecret = "apiSecret";
        message = "message";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetDate() throws Exception {
        String date = ApiUtils.getDate();
        assertEquals(date.length(), 28);
    }

    @Test
    public void testGetAuthorization() throws Exception {
        String authorization = "LENDDO " + apiKey + ":";
        String auth = ApiUtils.getAuthorization(apiKey, apiSecret, message);
        int length = (7+apiKey.length()+1); //authorization.length()
        assertEquals(auth.substring(0,length),authorization);
    }
}
