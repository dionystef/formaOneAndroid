package com.dcs.formaonefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    // variables //
    int token = 0;
    Button buttonEtudiant;
    Button buttonEtablissement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // recuperation du token //
        Intent intent = getIntent();
        token = intent.getIntExtra("token", -1);
        Log.e("HomeActivity token : ", Integer.toString(token));

        // assignation des variables a la vue //
        buttonEtudiant = (Button) findViewById(R.id.buttonEtudiant);
        buttonEtablissement = (Button) findViewById(R.id.buttonEtablissement);

        // bouton etablissement //
        buttonEtablissement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // arguments passés aux autres vues //
                Intent intent = new Intent(HomeActivity.this, ListEtablissementActivity.class);
                intent.putExtra("token", token);

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
