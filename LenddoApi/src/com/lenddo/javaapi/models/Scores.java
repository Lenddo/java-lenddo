package com.lenddo.javaapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey Mar Antonio on 07/24/17.
 */
public class Scores {

    public String model_id;
    public Integer score;
    public List<Object> feature_values = new ArrayList<Object>();
    public List<Object> flags = new ArrayList<Object>();
    public Integer created;

}
