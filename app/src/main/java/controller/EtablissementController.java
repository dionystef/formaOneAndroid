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
    private Rest rest = new Rest();
    private String target = "school";


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

        // variable //

        List<NameValuePair> sendData = new ArrayList<NameValuePair>();
        sendData.add(new BasicNameValuePair("action", "all"));
        sendData.add(new BasicNameValuePair("target", target));
        sendData.add(new BasicNameValuePair("section", "school"));

        //JSONObject receiveListeEtablissement =  new JSONObject(mock.listeEtablissements());
        // connexion //
        JSONObject receiveListeEtablissement =  rest.send("GET", sendData);
        Log.e("receiveListe", receiveListeEtablissement.toString());

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
