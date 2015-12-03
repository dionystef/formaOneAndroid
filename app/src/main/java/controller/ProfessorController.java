package controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import model.Enum_contact_type;
import model.Professeur;
import model.Rest;

/**
 * Created by dionys on 25/09/15.
 */
public class ProfessorController {

    private Enum_contact_type contactType;
    private Professeur professeur;
    private Context context;
    private List<Enum_contact_type> enumContactTypes = new ArrayList<Enum_contact_type>();
    private Rest rest = new Rest();
    private List<NameValuePair> sendData = new ArrayList<NameValuePair>();
    private int token = 1;
    private int idProf = 0;

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

    public int login (String login, String mdp) {

        // on remplie le tableau des data //
        sendData.add(new BasicNameValuePair("action", "connect"));
        sendData.add(new BasicNameValuePair("target", "login"));
        sendData.add(new BasicNameValuePair("login", login));
        sendData.add(new BasicNameValuePair("passwd", mdp));

        // on fait la requete rest //
        JSONObject returnJson = rest.send("POST", sendData);

        // on recup le token //
        try {
            JSONObject value = returnJson.getJSONObject("value");

            if (value.isNull("professor_id")) {

                return 0;
            }else{
                //token = value.getInt("token");
                idProf = value.getInt("professor_id");

                return token;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("oups...", e.toString());

            return 0;
        }

    }



}
