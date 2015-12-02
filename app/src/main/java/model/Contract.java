package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dionys on 29/11/15.
 */
public class Contract {
    private int id;
    private int school_id;
    private String begin;
    private String end;
    private String dateinsert;
    private String dateupdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDateinsert() {
        return dateinsert;
    }

    public void setDateinsert(String dateinsert) {
        this.dateinsert = dateinsert;
    }

    public String getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(String dateupdate) {
        this.dateupdate = dateupdate;
    }


    public void parserJson(JSONObject contactJson, List<Enum_contact_type> listType) throws JSONException {



    }

}
