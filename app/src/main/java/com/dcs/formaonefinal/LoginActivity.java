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
import android.widget.Toast;

import java.util.HashMap;

import controller.ProfessorController;

public class LoginActivity extends AppCompatActivity {

    // variables de la vue //
    private Button buttonConnect;
    private Button buttonSubscribe;
    private EditText textLogin;
    private EditText textPassword;
    private ProfessorController profCtrl = new ProfessorController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // assignation des variables //
        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        buttonSubscribe = (Button) findViewById(R.id.buttonSouscription);
        textLogin = (EditText) findViewById(R.id.textLogin);
        textPassword = (EditText) findViewById(R.id.textPassword);

        // button connection //
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> sendData = new HashMap<String, String>();
                sendData.put("login", textLogin.getText().toString());
                sendData.put("passwd", textPassword.getText().toString());

            HashMap connexion = profCtrl.login(sendData);

             // arguments passés aux autres vues //
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("connexion", connexion);

             // demarrage de l'autre vue //
            startActivity(intent);
                }

        });

        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SubscribeActivity.class);
                startActivity(intent);
            }
        });
    }


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

}
