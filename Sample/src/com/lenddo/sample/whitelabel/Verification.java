package com.lenddo.sample.whitelabel;

import java.util.ArrayList;
import java.util.List;

public class Verification {
    public Name name = new Name();
    public String date_of_birth;
    public String employer;
    public Phone phone = new Phone();
    public Employment_period  employment_period = new Employment_period();
    public Mothers_maiden_name mothers_maiden_name = new Mothers_maiden_name();
    public List<Government_ids> government_ids = new ArrayList<Government_ids>();
    public String university;
    public String email;
    public String work_email;
    public Address address = new Address();

    public static class Name {
        public String first;
        public String middle;
        public String last;
    }

    public static class Phone {
        public String mobile;
        public String home;
    }

    public static class Employment_period {
        public String start_date;
        public String end_date;
    }

    public static class Mothers_maiden_name {
        public String first;
        public String middle;
        public String last;
    }

    public static class Government_ids {
        public String type;
        public String value;
    }

    public static class Address {
        public String line_1;
        public String line_2;
        public String city;
        public String administrative_division;
        public String country_code;
        public String postal_code;
        public double latitude;
        public double longitude;
    }

}
