package com.lenddo.javaapi.models;

/**
 * Created by Joey Mar Antonio on 10/15/16.
 */
public class Verification {
    public Name name = new Name();
    public String date_of_birth;
    public String employer;
    public Phone phone = new Phone();
    public String university;
    public String email;

    public static class Name {
        public String first;
        public String middle;
        public String last;
    }

    public static class Phone {
        public String mobile;
    }
}
