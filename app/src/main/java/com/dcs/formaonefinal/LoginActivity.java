package com.dcs.formaonefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import controller.ProfessorController;
import model.Connection;
import model.Rest;

public class LoginActivity extends AppCompatActivity {

    // variables de la vue //
    private Button buttonConnect;
    private EditText textLogin;
    private EditText textPassword;
    private ProfessorController profCtrl = new ProfessorController(this);
    private String response;
    private String urlTypeContact = "?target=enum&action=getContactTypes";
    private String urlProfessor ="";
    private int token = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // assignation des variables //
        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        textLogin = (EditText) findViewById(R.id.textLogin);
        textPassword = (EditText) findViewById(R.id.textPassword);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // creer une nouvelle connexion pour une demande //
                Rest rest = new Rest();
                List<NameValuePair> sendData = new ArrayList<NameValuePair>();

                // remplir le sendData //
                sendData.add(new BasicNameValuePair("action", "connect"));
                sendData.add(new BasicNameValuePair("target", "login"));
                sendData.add(new BasicNameValuePair("login", "campusid"));
                sendData.add(new BasicNameValuePair("passwd", "campusid"));

                JSONObject returnJson = rest.send("POST", sendData);
                Log.e("login returnJson: ", returnJson.toString());
                try {
                    JSONObject value = returnJson.getJSONObject("value");
                    //token = value.getInt("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("oups...", e.toString());
                }

                if (token > 0) {
                    // arguments passés aux autres vues //
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("token", token);

                    // demarrage de l'autre vue //
                    startActivity(intent);
                }else{

                }

                // on creer les class avec le json //
               // profCtrl.init(loginActivity.response);

                /*
                    // arguments passés aux autres vues //
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    //intent.putExtra("professeur", professeurId);

                    // demarrage de l'autre vue //
                    startActivity(intent);
                */

            }

        });
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
