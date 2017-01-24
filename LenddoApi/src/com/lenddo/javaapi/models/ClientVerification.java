package com.lenddo.javaapi.models;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Joey Mar Antonio on 12/8/15.
 * Updated: 01/24/2017 <j.antonio> SDK-30
 */
public class ClientVerification {
    public String partner_script_id;
    public Integer updated;
    public Integer created;
    public String facebook_photo_url;
    public List<String> flags = new ArrayList<String>();
    public Verifications verifications;
    public String client_id;
    public String _id;
    public String partner_id;
    public Probes probes;
// start: SDK-30 <j.antonio> Added verified_by_facebook boolean flag
    public Boolean verified_by_facebook;
// end: SDK-30 <j.antonio>

    public static class Verifications {

        public Boolean name;
        public Boolean university;
        public Boolean employer;
// start: SDK-30 <j.antonio> Remove verifications.facebook_verified
//        public Boolean facebook_verified;
// end: SDK-30 <j.antonio>
        public Boolean birthday;
        public Object top_employer;

    }

    public static class Probes {

        public List<String> name = new ArrayList<String>();
        public University university;
        public Employer employer;
// SDK-30 <j.antonio> Remove probes.facebook_verified
//        public List<String> facebook_verified = new ArrayList<String>();
// end: SDK-30 <j.antonio>
        public List<Integer> birthday = new ArrayList<Integer>();
        public Object top_employer;

    }

    public static class Employer {

        public String employer;

    }

    public static class University {

        public String university;

    }
}
