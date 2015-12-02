package controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

/**
 * Created by dionys on 25/09/15.
 */
public class ProfessorController {

    private Enum_contact_type contactType;
    private Professeur professeur;
    private Context context;
    private List<Enum_contact_type> enumContactTypes = new ArrayList<Enum_contact_type>();

    /**
     * Json professeur
     */
    public String professorJson = "{" +
                        "professor: {" +
                        "   id: 1," +
                        "   contact:{" +
                        "       id: 1," +
                        "       firstname: steph," +
                        "       lastname: toto," +
                        "       mail: toto@steph.fr," +
                        "       phone: 0606060606," +
                        "       enum_contact_type_id: 1" +
                        "   }," +
                        "   address:{" +
                        "       id: 2," +
                        "       main: 'rue tata'," +
                        "       secondary: 'rue B'," +
                        "       zipcode: 06100," +
                        "       city: nice"+
                            "}"+
                        "}" +
                    "}";

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
}
