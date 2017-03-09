package com.lenddo.javaapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey Mar Antonio on 12/8/15.
 */
public class ClientScore {

    public Integer score;
    public List<Object> flags = new ArrayList<Object>();
    public String client_id;
    public String partner_id;
    public String partner_script_id;
    public Integer created;
    public Integer application_created;

}
