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
    public Boolean verified_by_facebook;

    public static class Verifications {

        public Boolean name;
        public Boolean university;
        public Boolean employer;
        public Boolean birthday;
        public Object top_employer;

    }

    public static class Probes {

        public List<String> name = new ArrayList<String>();
        public University university;
        public Employer employer;
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
