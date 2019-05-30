package com.lenddo.javaapi.models.applicationdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("home")
    @Expose
    private String home;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

}