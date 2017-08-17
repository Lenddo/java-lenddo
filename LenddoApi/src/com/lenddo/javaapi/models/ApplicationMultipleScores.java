package com.lenddo.javaapi.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey Mar Antonio on 07/24/17.
 */
@XmlRootElement
public class ApplicationMultipleScores {

    public String application_id;
    public String partner_id;
    public String partner_script_id;
    public Integer application_created;
    public List<Object> flags = new ArrayList<Object>();
    public List<Scores> scores = new ArrayList<Scores>();

}
