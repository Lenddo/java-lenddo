package com.lenddo.javaapi.models;

/**
 * Created by Joey Mar Antonio on 1/12/16.
 */
public class APIError {

    private int statusCode;
    private String message;
    private String name;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }

    public String name() {
        return name;
    }
}