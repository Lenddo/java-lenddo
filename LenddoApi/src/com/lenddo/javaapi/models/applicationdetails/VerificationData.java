package com.lenddo.javaapi.models.applicationdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationData {

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("work_email")
    @Expose
    private String workEmail;
    @SerializedName("phone")
    @Expose
    private Phone phone;
    @SerializedName("employer")
    @Expose
    private String employer;
    @SerializedName("employment_period")
    @Expose
    private EmploymentPeriod employmentPeriod;
    @SerializedName("mothers_maiden_name")
    @Expose
    private MothersMaidenName mothersMaidenName;
    @SerializedName("university")
    @Expose
    private String university;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("work_address")
    @Expose
    private WorkAddress workAddress;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public EmploymentPeriod getEmploymentPeriod() {
        return employmentPeriod;
    }

    public void setEmploymentPeriod(EmploymentPeriod employmentPeriod) {
        this.employmentPeriod = employmentPeriod;
    }

    public MothersMaidenName getMothersMaidenName() {
        return mothersMaidenName;
    }

    public void setMothersMaidenName(MothersMaidenName mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public WorkAddress getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(WorkAddress workAddress) {
        this.workAddress = workAddress;
    }

}