package com.lenddo.javaapi.models;

/**
 * Created by Joey Mar Antonio on 17/08/2017.
 */
public class PriorityDataRequestBody {
    public String partner_script_id;
    public String application_id;
    public priority_data data = new priority_data();

    public static class priority_data {
        public Object partner_data;
        public Verification verification_data = new Verification();
    }
}
