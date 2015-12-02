package model;

import android.util.Log;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dionys on 25/09/15.
 */
public class Professeur {

    private int id;
    private Contact contact;
    private Address address;

    public Professeur(int _id) {
        this.id = _id;
        this.contact = null;
        this.address = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void parserJson(JSONObject professeurJson, List<Enum_contact_type> listType) throws JSONException {

        this.contact = new Contact();
        this.address = new Address();

        this.id = professeurJson.getInt("id");

        this.contact.parserJson(professeurJson.getJSONObject("contact"), listType);
        this.address.parserJson(professeurJson.getJSONObject("address"));

    }
}
