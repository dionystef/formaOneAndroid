package controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
    private Context context;

    /**
     * on passe le context au ctlr de listEtablissement
     * @param _context
     */
    public EtablissementController(Context _context) {
        context = _context;
    }

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
    public ArrayList<Etablissement> recupListEtablissements(HashMap connexion) throws JSONException {
        // variable //
        connexion.put("action", "listCompany");
        connexion.put("target", "company");
        connexion.put("login_id", connexion.get("login_id"));
        connexion.put("token", connexion.get("token"));

        // on fait la requete rest //
        JSONObject returnJson = rest.send("GET", connexion);

        // on recup la liste des etablissements //
        try {
            Boolean status = returnJson.getBoolean("status");
            if (status){
                JSONObject value = returnJson.getJSONObject("value");

                JSONArray jsonListEtablissement = value.getJSONArray("companies");

                for (int i = 0; i < jsonListEtablissement.length(); i++) {
                    JSONObject etablissement = jsonListEtablissement.getJSONObject(i);
                    int connexion_id = etablissement.getInt("connexion_id");
                    int id = etablissement.getInt("id");
                    String name = etablissement.getString("name");
                    Etablissement etat = new Etablissement(id, name, connexion_id);
                    this.listEtablissement.add(etat);
                }

                return this.listEtablissement;

            }else{
                int error = returnJson.getInt("error");
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
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("oups...", e.toString());

            return null;
        }
        return null;
    }

    /**
     * créer un etablissement
     * @param connexion
     * @param etablissementForm
     * @return
     */
    public Etablissement createEtablissement(HashMap connexion, HashMap etablissementForm) {

        // variable //
        etablissementForm.put("action", "create");
        etablissementForm.put("target", "company");
        etablissementForm.put("login_id", connexion.get("login_id"));
        etablissementForm.put("token", connexion.get("token"));

        // on fait la requete rest //
        JSONObject returnJson = rest.send("PUT", etablissementForm);

        // on recup le token //
        try {
            Boolean status = returnJson.getBoolean("status");
            if (status){
                JSONObject value = returnJson.getJSONObject("value");
                JSONObject company = value.getJSONObject("company");

                return new Etablissement(company.getInt("id"),company.getString("name"), company.getInt("connexion_id"));

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * recup 1 établissement
     */
    public Etablissement recupEtablissement(HashMap connexion){

        // variable //
        HashMap etablissement = new HashMap();
        etablissement.put("action", "read");
        etablissement.put("target", "company");
        etablissement.put("login_id", connexion.get("login_id"));
        etablissement.put("token", connexion.get("token"));
        etablissement.put("login_id", connexion.get("login_id"));
        etablissement.put("company_id", connexion.get("company_id"));

        // on fait la requete rest //
        JSONObject returnJson = rest.send("GET", etablissement);

        return null;
    }



}
