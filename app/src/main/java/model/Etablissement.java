package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dionys on 30/11/15.
 */
public class Etablissement {
    private int id;
    private int connexion_id;
    private String name;
    private Contact contact;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public static Etablissement parserJson(JSONObject json) throws JSONException {

        Etablissement et = new Etablissement();

        et.contact = new Contact();
        et.address = new Address();
        et.id = json.getInt("id");
        et.connexion_id = json.getInt("connexion_id");
        et.name = json.getString("name");

        if(!json.isNull("address")) {
            et.address = Address.parserJson(json.getJSONObject("address"));
        }
        return et;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
