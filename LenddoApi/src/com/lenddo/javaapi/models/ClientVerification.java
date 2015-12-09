package com.lenddo.javaapi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class ClientVerification {
    public Integer updated;
    public Integer created;
    public String facebookPhotoUrl;
    public List<String> flags = new ArrayList<String>();
    public Verifications verifications;
    public String clientId;
    public String _id;
    public String partnerId;
    public Probes probes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public static class Verifications {

        public Boolean name;
        public Boolean university;
        public Boolean employer;
        public Boolean facebookVerified;
        public Boolean birthday;
        public Object topEmployer;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public static class Probes {

        public List<String> name = new ArrayList<String>();
        public University university;
        public Employer employer;
        public List<String> facebookVerified = new ArrayList<String>();
        public List<Integer> birthday = new ArrayList<Integer>();
        public Object topEmployer;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public static class Employer {

        public String employer;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public static class University {

        public String university;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}
