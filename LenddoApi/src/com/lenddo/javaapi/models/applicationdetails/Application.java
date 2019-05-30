package com.lenddo.javaapi.models.applicationdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Application {

    @SerializedName("application_code")
    @Expose
    private String applicationCode;

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

}