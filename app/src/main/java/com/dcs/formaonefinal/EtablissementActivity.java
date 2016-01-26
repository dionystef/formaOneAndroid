package com.dcs.formaonefinal;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import controller.EtablissementController;
import model.Etablissement;

public class EtablissementActivity extends AppCompatActivity {

    private EtablissementController etablissementCtrl = new EtablissementController(this);
    private HashMap connexion;
    private int     company_id;
    private TextView nom;
    private TextView adresse;
    private TextView complement;
    private TextView zipCodeCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etablissement);

        // assignation des vues //
        nom = (TextView) findViewById(R.id.etablissementName);
        adresse = (TextView) findViewById(R.id.etablissementAdresse);
        complement = (TextView) findViewById(R.id.etablissementComplement);
        zipCodeCity = (TextView) findViewById(R.id.etablissementZipCodeCity);

        // recuperation du token //
        Intent intent = getIntent();
        connexion     = (HashMap<String, String>)intent.getSerializableExtra("connexion");

        connexion.put("company_id", String.valueOf(intent.getIntExtra("company_id", -1)));

        Etablissement etab = etablissementCtrl.recupEtablissement(connexion);

        // set les valeurs a la vue //
        nom.setText(etab.getName());
        adresse.setText(etab.getAddress().getMain());
        if (etab.getAddress().getSecondary() != ""){
            complement.setVisibility(View.VISIBLE);
            complement.setText(etab.getAddress().getSecondary());
        }
        zipCodeCity.setText(etab.getAddress().getZipcode() + "  " + etab.getAddress().getCity());


    }

    @Override
    public void onBackPressed() {
        Log.e("etab: ", "back");
        super.onBackPressed();
        // intent de retour //
        Intent retourMain = new Intent(this, ListEtablissementActivity.class);
        setResult(1, retourMain);
        finish();
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
