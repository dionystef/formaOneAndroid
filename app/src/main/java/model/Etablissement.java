package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dionys on 30/11/15.
 */
public class Etablissement {
    private int id;
    private String name;
    private Contact contact;
    private Address address;
    private int connexion_id;

    public Etablissement(int _id, String _name, int _connexion_id) {
        this.name = _name;
        this.id = _id;
        this.connexion_id = _connexion_id;
        this.contact = null;
        this.address = null;
    }

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


    public void parserJson(JSONObject professeurJson, List<Enum_contact_type> listType) throws JSONException {

        this.contact = new Contact();
        this.address = new Address();

        this.id = professeurJson.getInt("id");

        this.contact.parserJson(professeurJson.getJSONObject("contact"), listType);
        this.address.parserJson(professeurJson.getJSONObject("address"));

    }

    @Override
    public String toString() {
        return this.name;
    }
}
