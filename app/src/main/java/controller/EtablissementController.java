package controller;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Etablissement;
import model.JsonOffLine;
import model.Rest;

/**
 * Created by dionys on 30/11/15.
 */
public class EtablissementController {

    // variables //
    private ArrayList<Etablissement> listEtablissement = new ArrayList<>();
    private JsonOffLine mock = new JsonOffLine();


    /**
     * création des etablissements recus en Json
     * @return
     */
    private Etablissement createEtablissement()  {

        return null;
    }

    /**
     * returne la liste de tout les etablissements
     * @return
     */
    public ArrayList<Etablissement> recupListEtablissements() throws JSONException {

        JSONObject receiveListeEtablissement =  new JSONObject(mock.listeEtablissements());
        JSONObject value = receiveListeEtablissement.getJSONObject("value");
        JSONArray jsonListEtablissement = value.getJSONArray("school");

        for (int i = 0; i < jsonListEtablissement.length(); i++) {
            JSONObject etablissement = jsonListEtablissement.getJSONObject(i);

            int id = etablissement.getInt("id");
            Log.e("etabliCtrl: ", Integer.toString(id));

            String name = etablissement.getString("name");
            Log.e("etabliCtrl: ", name);

            Etablissement etat = new Etablissement(id, name);

            this.listEtablissement.add(etat);

        }

        return listEtablissement;

    }

}
