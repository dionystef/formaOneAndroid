package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Person {
    protected int       id;
    protected String    firstname;
    protected String    lastname;
    protected String    mail;
    protected String    phone;

    public static Person parserJson(JSONObject json) throws JSONException {
        Person p    = new Person();
        p.id        = json.getInt("id");
        p.firstname = json.getString("firstname");
        p.lastname  = json.getString("lastname");
        p.phone     = json.getString("phone");
        p.mail      = json.getString("mail");
        return p;
    }
}
