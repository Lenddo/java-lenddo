package com.lenddo.javaapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey Mar Antonio on 3/9/17.
 */
public class Scorecard {

    public String scorecard_id;
    public Integer score;
    public List<Object> flags = new ArrayList<Object>();
    public List<Object> feature_values = new ArrayList<Object>();

}
