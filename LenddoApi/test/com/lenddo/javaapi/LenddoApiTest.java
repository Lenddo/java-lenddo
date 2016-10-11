package com.lenddo.javaapi;

import com.lenddo.javaapi.services.LenddoScoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joey Mar Antonio on 12/9/15.
 */
public class LenddoApiTest {

    protected String apikey;
    protected String apisecret;
    protected String ps_id;
    protected LenddoApi lenddoApi;
    protected LenddoScoreService lenddoservice;

    @Before
    public void setUp() throws Exception {
        apikey = "apikey";
        apisecret = "apisecret";
        lenddoApi = new LenddoApi(apikey, apisecret, ps_id);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDebugMode() throws Exception {

    }

    @Test
    public void testGetApikey() throws Exception {
        assertEquals(lenddoApi.getApikey(), apikey);
    }

    @Test
    public void testGetApisecret() throws Exception {
        assertEquals(lenddoApi.getApisecret(), apisecret);
    }

    @Test
    public void testGetService() throws Exception {

    }

    @Test
    public void testGetClientScoreAsPojo() throws Exception {

    }

    @Test
    public void testGetClientScoreAsGson() throws Exception {

    }

    @Test
    public void testGetClientScoreAsString() throws Exception {

    }

    @Test
    public void testGetClientVerificationAsPOJO() throws Exception {

    }

    @Test
    public void testGetClientVerificationAsGson() throws Exception {

    }

    @Test
    public void testGetClientVerificationAsString() throws Exception {

    }
}
