package com.lenddo.javaapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncryptedResponse {

    @SerializedName("encryption_body")
    @Expose
    private EncryptionBody encryptionBody;

    public EncryptionBody getEncryptionBody() {
        return encryptionBody;
    }

    public void setEncryptionBody(EncryptionBody encryptionBody) {
        this.encryptionBody = encryptionBody;
    }

}