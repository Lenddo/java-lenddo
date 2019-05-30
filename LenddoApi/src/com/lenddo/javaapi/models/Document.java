package com.lenddo.javaapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("content")
    @Expose
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}