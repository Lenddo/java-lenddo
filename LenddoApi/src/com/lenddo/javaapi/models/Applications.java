package com.lenddo.javaapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Applications {

    @SerializedName("application_ids")
    @Expose
    private List<String> applicationIds = null;
    @SerializedName("pcount")
    @Expose
    private Integer pcount;

    public List<String> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<String> applicationIds) {
        this.applicationIds = applicationIds;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

}