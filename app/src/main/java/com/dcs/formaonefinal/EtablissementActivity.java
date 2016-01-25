package com.dcs.formaonefinal;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

import controller.EtablissementController;

public class EtablissementActivity extends AppCompatActivity {

    private HashMap connexion;
    private int     company_id;
    private EtablissementController etablissementCtrl = new EtablissementController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etablissement);

        // recuperation du token //
        Intent intent = getIntent();
        connexion  = (HashMap<String, String>)intent.getSerializableExtra("connexion");
        connexion.put("company_id", String.valueOf(intent.getIntExtra("company_id", -1)));

        etablissementCtrl.recupEtablissement(connexion);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_etablissement, menu);
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
