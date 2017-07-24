package com.lenddo.javaapi.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Joey Mar Antonio on 12/8/15.
 * Updated: 01/24/2017 <j.antonio> SDK-30
 */
@XmlRootElement
public class ClientVerification {
    public String partner_script_id;
    public Integer updated;
    public Integer application_created;
    public Integer created;
    public List<String> duplicate_profiles = new ArrayList<String>();
    public String facebook_photo_url;
    public List<String> flags = new ArrayList<String>();
    public Verifications verifications;
    public String client_id;
    public String application_id;
    public String partner_id;
    public Probes probes;
    public Boolean verified_by_facebook;

    public static class Verifications {

        public Boolean name;
        public Boolean university;
        public Boolean employer;
        public Boolean phone;
        public Boolean birthday;
        public Boolean email;
        public Object top_employer;

    }

    public static class Probes {

        public List<String> name = new ArrayList<String>();
        public University university;
        public Employer employer;
        public String phone;
        public List<Integer> birthday = new ArrayList<Integer>();
        public String email;
        public Object top_employer;

    }

    public static class Employer {

        public String employer;

    }

    public static class University {

        public String university;

    }
}
