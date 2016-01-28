package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Person {
    protected int       id;
    protected String    firstname;
    protected String    lastname;
    protected String    mail;
    protected String    phone;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public static Person parserJson(JSONObject json) throws JSONException {
        Person person    = new Person();
        person.id        = json.getInt("id");
        JSONObject contact = json.getJSONObject("contact");
        person.firstname = contact.getString("firstname");
        person.lastname  = contact.getString("lastname");
        person.phone     = contact.getString("phone");
        person.mail      = contact.getString("mail");
        return person;
    }
}
