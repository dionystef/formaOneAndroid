package com.dcs.formaonefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    // variables //
    Button buttonEtudiant;
    Button buttonEtablissement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // recuperation du token //
        Intent intent = getIntent();
        final HashMap<String, String> connexion = (HashMap<String, String>)intent.getSerializableExtra("connexion");

        // assignation des variables a la vue //
        buttonEtudiant      = (Button) findViewById(R.id.buttonEtudiant);
        buttonEtablissement = (Button) findViewById(R.id.buttonEtablissement);

        // bouton etablissement //
        buttonEtablissement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // arguments passés aux autres vues //
                Intent intent = new Intent(HomeActivity.this, ListEtablissementActivity.class);
                intent.putExtra("connexion", connexion);

                // demarrage de l'autre vue //
                startActivity(intent);
            }
        });

        // bouton étudiant //
        buttonEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // arguments passés aux autres vues //
                Intent intent = new Intent(HomeActivity.this, ListStudentActivity.class);
                intent.putExtra("connexion", connexion);

                // demarrage de l'autre vue //
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
