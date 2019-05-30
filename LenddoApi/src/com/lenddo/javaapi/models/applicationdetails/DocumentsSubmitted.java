package com.lenddo.javaapi.models.applicationdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentsSubmitted {

    @SerializedName("binary_id")
    @Expose
    private String binaryId;
    @SerializedName("type")
    @Expose
    private String type;

    public String getBinaryId() {
        return binaryId;
    }

    public void setBinaryId(String binaryId) {
        this.binaryId = binaryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}