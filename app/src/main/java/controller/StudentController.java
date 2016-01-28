package controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import model.Person;
import model.Rest;

/**
 * Controller pour les vues de gestion des  étudiants
 */
public class StudentController {

    /**
     * Context de StudentActivity
     */
    protected Context context;
    protected Rest rest;
    private ArrayList<Person> listPerson = new ArrayList<>();

    /**
     * Constructeur
     * @param _context : context de StudentActivity
     */
    public StudentController(Context _context) {
        this.context = _context;
        this.rest = new Rest();
    }


    public ArrayList<Person> getList(HashMap<String, String> connexion) {
        // Ajout des données pour la requête au webservice
        HashMap<String, String> data = new HashMap<>();
        data.put("target", "person");
        data.put("action", "listContact");
        data.put("login_id", connexion.get("login_id"));
        data.put("token", connexion.get("token"));
        data.put("person_right_id", "2");

        // on fait la requete rest
        JSONObject json = rest.send("GET", data);

        Log.e("return : ", json.toString());
        // On récupère les données
        try {
            Boolean status = json.getBoolean("status");
            if (status){
                JSONArray list = json.getJSONObject("value").getJSONArray("contacts");
                for (int i = 0; i < list.length(); i++) {
                    this.listPerson.add(Person.parserJson(list.getJSONObject(i)));
                }
                return this.listPerson;
            }else{
                int error = json.getInt("error");
                switch (error) {
                    case 1:
                    case 2:
                    case 100:
                        Toast.makeText(context, json.getString("value"), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(context, "Erreur interne de l'application", Toast.LENGTH_LONG).show();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Person create(HashMap connexion, HashMap form) {
        // Ajout des données pour la requête au webservice
        form.put("target",          "person");
        form.put("action",          "create");
        form.put("login_id",        connexion.get("login_id").toString());
        form.put("token",           connexion.get("token").toString());
        form.put("person_right_id", "2");

        // on fait la requete rest
        JSONObject returnJson = rest.send("PUT", form);

        // On récupère les données
        try {
            if(returnJson != null || !returnJson.isNull("status")) {
                Boolean status = returnJson.getBoolean("status");
                if (status){
                    JSONObject value = returnJson.getJSONObject("value");
                    JSONObject person = value.getJSONObject("person");

                    return Person.parserJson(person);

                }else{
                    int error = 0;
                    try {
                        error = returnJson.getInt("error");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    switch (error) {
                        case 1:
                        case 2:
                        case 100:
                            Toast.makeText(context, returnJson.getString("value"), Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(context, "Erreur interne de l'application", Toast.LENGTH_LONG).show();
                    }
                }
            } else {

                Toast.makeText(context, "Erreur interne de l'application", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
