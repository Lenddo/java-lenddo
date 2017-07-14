package com.lenddo.javaapi.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by Joey Mar Antonio on 10/13/16.
 */
public class NetworkRequestBody {

    public static class SendExtraDataRqBody {
        public String partner_script_id;
        public String application_id;
        public JsonObject extra_data;

        public static class application {
            public String extra_data;
        }
    }
}
