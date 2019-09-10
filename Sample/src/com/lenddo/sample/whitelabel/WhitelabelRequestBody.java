package com.lenddo.sample.whitelabel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class WhitelabelRequestBody {

    public static class WLPartnerTokenRqBody {
        public String client_id;
        public token_data token_data;
        public String provider;

        public static class token_data {
            public String key;
            public String secret = "";
        }
    }

    public static class WLCommitPartnerJobRqBody {
        public String client_id;
        public String partner_script_id;
        public JsonArray profile_ids;
        public Verification verification_data;
        public JsonElement partner_data;
    }
}
