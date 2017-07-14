package com.lenddo.javaapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey Mar Antonio on 06/23/17.
 */
public class ApplicationFeatures {

    public String application_id;
    public String partner_id;
    public String partner_script_id;
    public Integer created;
    public Integer application_created;
    public List<Object> feature_values = new ArrayList<Object>();

}
