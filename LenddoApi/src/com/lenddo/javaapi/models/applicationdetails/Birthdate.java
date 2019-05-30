package com.lenddo.javaapi.models.applicationdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Birthdate {

@SerializedName("day")
@Expose
private Integer day;
@SerializedName("month")
@Expose
private Integer month;
@SerializedName("year")
@Expose
private Integer year;

public Integer getDay() {
return day;
}

public void setDay(Integer day) {
this.day = day;
}

public Integer getMonth() {
return month;
}

public void setMonth(Integer month) {
this.month = month;
}

public Integer getYear() {
return year;
}

public void setYear(Integer year) {
this.year = year;
}

}