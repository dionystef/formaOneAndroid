package controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Enum_contact_type;
import model.Professeur;
import model.Rest;

/**
 * Created by dionys on 25/09/15.
 */
public class ProfessorController {

    private Professeur professeur;
    private Context context;
    private List<Enum_contact_type> enumContactTypes = new ArrayList<Enum_contact_type>();
    private Rest rest = new Rest();

    /**
     * on passe le context au ctlr du prof
     * @param _context
     */
    public ProfessorController(Context _context) {
        context = _context;
    }

    /**
     * fonction init pour parser les jsons
     */
    public int init(String json) {
        try {
            /*// enum contact //
            JSONObject cJson = new JSONObject(json);
            JSONObject valueJson = cJson.getJSONObject("value");
            JSONObject typeArrayJson = new JSONObject(String.valueOf(valueJson));
            JSONArray contactJson = typeArrayJson.getJSONArray("types");

            for (int i=0; i<contactJson.length(); i++) {
                JSONObject type = contactJson.getJSONObject(i);
                enumContactTypes.add(new Enum_contact_type(type.getInt("id"), type.getString("label")));
            }*/

            // professeur //
            professeur = new Professeur(1);
            JSONObject pJson = new JSONObject(json);
            JSONObject profJson = pJson.getJSONObject("professor");

            professeur.parserJson(profJson, enumContactTypes);
            return professeur.getId();

        } catch (JSONException e) {
            Log.e("professeur: ", "error");
        }

        return 0;
    }

    /**
     * Connection
     * @param
     * @param
     * @return
     */
    public HashMap login (HashMap sendData) {

        sendData.put("action", "connexion");
        sendData.put("target", "connexion");

        // on fait la requete rest //
        JSONObject returnJson = rest.send("POST", sendData);

        // on recup le token //
        try {
            Boolean status = returnJson.getBoolean("status");
            if (status){
                JSONObject value = returnJson.getJSONObject("value");

                // constitution de la HashMap //
                HashMap<String, String> connexion = new HashMap<>();

                connexion.put("login_id", String.valueOf(value.getInt("login_id")));
                connexion.put("company_id", (value.get("company_id").equals(null) ? "" : String.valueOf(value.getInt("company_id"))));
                connexion.put("right_id", String.valueOf(value.getInt("right_id")));
                connexion.put("token", value.getString("token"));

                return connexion;

            }else{
                int error = returnJson.getInt("error");
                switch (error) {
                    case 1:
                    case 100:
                    case 101:
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

    public int souscription (HashMap sendData) {

        sendData.put("action", "subscription");
        sendData.put("target", "connexion");

        // on fait la requete rest //
        JSONObject returnJson = rest.send("POST", sendData);

        // on recup le token //
        try {
            Boolean status = returnJson.getBoolean("status");

            Log.e("profControl: ", status.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("oups...", e.toString());
        }

        return 0;
    }



}
