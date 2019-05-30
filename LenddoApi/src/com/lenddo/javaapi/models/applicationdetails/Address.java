package com.lenddo.javaapi.models.applicationdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("line_1")
    @Expose
    private String line1;
    @SerializedName("line_2")
    @Expose
    private String line2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("administrative_division")
    @Expose
    private String administrativeDivision;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdministrativeDivision() {
        return administrativeDivision;
    }

    public void setAdministrativeDivision(String administrativeDivision) {
        this.administrativeDivision = administrativeDivision;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}