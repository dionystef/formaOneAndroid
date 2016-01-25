package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dionys on 25/09/15.
 */
public class Address {
    private  int id;
    private String main;
    private String secondary;
    private String zipcode;
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static Address parserJson(JSONObject addressJson) throws JSONException {

        Address adresse = new Address();

        adresse.id = Integer.parseInt(addressJson.get("id").toString());
        adresse.main = addressJson.get("main").toString();
        adresse.secondary = addressJson.get("secondary").toString();
        adresse.zipcode = addressJson.get("zipcode").toString();
        adresse.city = addressJson.get("city").toString();

        return adresse;

    }
}
