package model;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dionys on 27/09/15.
 */
public class Connection extends AsyncTask<String, Integer, JSONObject> {

    private String urlServerRest;
    private String urlCall;
    private String method;
    private String response;
    private HashMap<String, String> data;

    public Connection(String _method, HashMap _data) {
        this.urlServerRest = "http://www.formaone-webservice.devcomputingsystem.fr/index.php/rest";
        this.method = _method;
        this.urlCall = "";
        this.data = _data;
        this.response = "";
    }

    @Override
        protected JSONObject doInBackground(String... params) {

        URL url;
        HttpURLConnection connection = null;
        String urlParameters = "";
        boolean first = true;

        for(Map.Entry<String, String> entry : data.entrySet()) {
            String cle = entry.getKey();
            String valeur = entry.getValue();

            if(first) {
                urlParameters += cle + "=" + valeur;
                first = false;
            } else {
                urlParameters += "&" + cle + "=" + valeur;
            }
        }


        try {
            //Create connection
            if (method.equals("GET") ){
                url = new URL(urlServerRest+"?"+urlParameters);

            }else{
                url = new URL(urlServerRest);
            }
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(method);

            Log.e("urlServerRest", urlParameters);
            //Send request
            if (!method.equals("GET")){
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close ();
            }

            // on recupere le json //
            JSONObject returnJson = new JSONObject(IOUtils.toString(connection.getInputStream()));

            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
