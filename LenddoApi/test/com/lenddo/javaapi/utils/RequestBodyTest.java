package com.lenddo.javaapi.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joey Mar Antonio on 1/11/16.
 */
public class RequestBodyTest {

    protected RequestBody requestBody;
    protected int method;
    protected String body;
    protected String date;
    protected int endpoint;
    protected String clientId;

    @Before
    public void setUp() throws Exception {
        method = RequestBody.GET_METHOD;
        body = "body string";
        date = "date string";
        endpoint = RequestBody.ENDPOINT_CLIENTSCORE;
        clientId = "clientid";
        requestBody = new RequestBody(method, body, date, endpoint, clientId);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetMethod() throws Exception {
        assertEquals(requestBody.getMethod(), "GET");
    }

    @Test
    public void testGetBody() throws Exception {
        assertEquals(requestBody.getBody(), body);
    }

    @Test
    public void testGetDate() throws Exception {
        assertEquals(requestBody.getDate(), date);
    }

    @Test
    public void testGetRequest() throws Exception {
        String request = requestBody.getMethod()+"\n"+requestBody.getBody()+"\n"+requestBody.getDate()+"\n"+requestBody.getPath();
        assertEquals(requestBody.getRequest(), request);
    }

    @Test
    public void testGetClientId() throws Exception {
        assertEquals(requestBody.getClientId(), clientId);
    }
}