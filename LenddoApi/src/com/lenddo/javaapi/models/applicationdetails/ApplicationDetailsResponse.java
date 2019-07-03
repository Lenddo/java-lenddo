package com.lenddo.javaapi.models.applicationdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicationDetailsResponse {

    @SerializedName("verification_data")
    @Expose
    private VerificationData verificationData;
    @SerializedName("partner_data")
    @Expose
    private PartnerData partnerData;
    @SerializedName("verification_results")
    @Expose
    private VerificationResults verificationResults;
    @SerializedName("documents_submitted")
    @Expose
    private List<DocumentsSubmitted> documentsSubmitted = null;

    public VerificationData getVerificationData() {
        return verificationData;
    }

    public void setVerificationData(VerificationData verificationData) {
        this.verificationData = verificationData;
    }

    public PartnerData getPartnerData() {
        return partnerData;
    }

    public void setPartnerData(PartnerData partnerData) {
        this.partnerData = partnerData;
    }

    public VerificationResults getVerificationResults() {
        return verificationResults;
    }

    public void setVerificationResults(VerificationResults verificationResults) {
        this.verificationResults = verificationResults;
    }

    public List<DocumentsSubmitted> getDocumentsSubmitted() {
        return documentsSubmitted;
    }

    public void setDocumentsSubmitted(List<DocumentsSubmitted> documentsSubmitted) {
        this.documentsSubmitted = documentsSubmitted;
    }

}