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
                connection.setUseCaches (false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
                wr.writeBytes (urlParameters);
                wr.flush ();
                wr.close ();
            }

            // on recupere le json //
            String temp = connection.getResponseMessage();
            JSONObject returnJson = new JSONObject(IOUtils.toString(connection.getInputStream()));
            Log.e("connection returnJson", returnJson.toString());
            return returnJson;
        } catch (Exception e) {
            Log.e("C connection returnJson", e.toString());
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

//----------------------------------------------------------------------------
            // objet de connection //
     /*       HttpURLConnection urlConnection = null;

            // creation de la connection //
            try {

                InputStream inputStream = null;

                if (this.method.equals("GET")){
                    // lire le tableau et recup les variable//

                    // transforme en url//
                    URL Connect = new URL(urlServerRest + urlCall);
                    urlConnection = (HttpURLConnection) Connect.openConnection();

                    // on passe les parametre URL //
                    urlConnection.setRequestMethod(this.method);

                    // on verifie que l'url nous renvois une bonne réponse //
                    int statutOk = urlConnection.getResponseCode();
                    if (statutOk != HttpURLConnection.HTTP_OK &&
                            statutOk != HttpURLConnection.HTTP_CREATED &&
                            statutOk != HttpURLConnection.HTTP_ACCEPTED){
                        return null;
                    }

                }else if (this.method.equals("POST")) {
                        Log.e("Post: ", "on rentre dans la methode POST");
                        URL connect = new URL(urlServerRest);
                        urlConnection = (HttpURLConnection) connect.openConnection();

                        // on passe les parametre URL //
                        urlConnection.setRequestMethod(this.method);
                        Log.e("methode: ", urlConnection.getRequestMethod());

                        // send post request //
                        urlConnection.setDoOutput(true);

                        for (NameValuePair item : this.data){
                            urlConnection.addRequestProperty(item.getName(), item.getValue());
                        }

                        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                        Log.e("DataOutputStream: ", "");
                        wr.flush();
                        wr.close();

                        inputStream = urlConnection.getInputStream();

                        int responseCode = urlConnection.getResponseCode();
                        Log.e("responseCode", String.valueOf(responseCode));
                }

                // on recupere le json //
                String temp = urlConnection.getResponseMessage();
                Log.e("reponse server: ", temp);

                String result = InputStreamOperations.InputStreamToString(inputStream);
                Log.e("result: ", result);

                //Log.e("returnJson :", IOUtils.toString(urlConnection.getInputStream()));

                JSONObject returnJson = new JSONObject(IOUtils.toString(urlConnection.getInputStream()));

                Log.e("returnJson: ", returnJson.toString());


                JsonOffLine mockLogin = new JsonOffLine();
                String mock = mockLogin.login();

                JSONObject returnJson = new JSONObject(mock);
                return returnJson;

            } catch (JSONException e) {
                Log.e("Post: ", "JSONException");
                Log.e("Post: ", e.toString());
                e.printStackTrace();
            }
            return null;
        }*/
}
