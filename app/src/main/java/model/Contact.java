package model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dionys on 25/09/15.
 */
public class Contact {
    private int id;
    private String firstname;
    private String lastname;
    private String mail;
    private String phone;
    private Enum_contact_type contact_type;

    public Contact() {
        this.firstname = "";
        this.lastname = "";
        this.mail = "";
        this.phone = "";
        contact_type = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Enum_contact_type getContact_type() {
        return contact_type;
    }

    public void setContact_type(Enum_contact_type contact_type) {
        this.contact_type = contact_type;
    }

    public void parserJson(JSONObject contactJson, List<Enum_contact_type> listType) throws JSONException {

        this.id = Integer.parseInt(contactJson.get("id").toString());
        this.firstname = contactJson.get("firstname").toString();
        this.lastname = contactJson.get("lastname").toString();
        this.mail = contactJson.get("mail").toString();
        this.phone = contactJson.get("phone").toString();
        int contactTypeId = contactJson.getInt("enum_contact_type_id");

        for (Enum_contact_type type : listType) {
            if (type.getId() == contactTypeId) {
                this.contact_type = type;
            }
        }

    }
}
