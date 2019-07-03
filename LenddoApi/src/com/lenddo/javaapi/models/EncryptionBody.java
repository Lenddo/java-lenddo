package com.lenddo.javaapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncryptionBody {

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("iv")
    @Expose
    private String iv;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}