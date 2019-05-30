package com.lenddo.javaapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicationsResponse {

    @SerializedName("application_ids")
    @Expose
    private List<String> applicationIds = null;
    @SerializedName("data_count")
    @Expose
    private Integer dataCount;
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;

    public List<String> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(List<String> applicationIds) {
        this.applicationIds = applicationIds;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

}