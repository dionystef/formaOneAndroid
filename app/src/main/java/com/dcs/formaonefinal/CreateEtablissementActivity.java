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

import java.util.HashMap;

public class CreateEtablissementActivity extends AppCompatActivity {

    CreateEtablissementActivity detailActivity = this;

    private String addressUser;
    private String completeAddressUser;
    private String zipCodeUser;
    private String cityUser;
    private String nameEtablissement;
    private String beginEtablissement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_etablissement);

        final HashMap<String, String> createEtablissement = new HashMap<>();

        // variables //
        final EditText name            = (EditText) findViewById(R.id.editName);
        final EditText address         = (EditText) findViewById(R.id.editAddress);
        final EditText completeAddress = (EditText) findViewById(R.id.editCompleteAddressEtablissement);
        final EditText zipCode         = (EditText) findViewById(R.id.editZipCode);
        final EditText city            = (EditText) findViewById(R.id.editCityEtablissement);
        final EditText begin           = (EditText) findViewById(R.id.editDateActivity);
        Button buttonNext              = (Button)   findViewById(R.id.buttonValideEtablissement);
        Button buttonBack              = (Button)   findViewById(R.id.buttonCancelEtablissement);

        // button suivant //
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEtablissement.put("name",      name.getText().toString());
                createEtablissement.put("main",      address.getText().toString());
                createEtablissement.put("secondary", completeAddress.getText().toString());
                createEtablissement.put("zipcode",   zipCode.getText().toString());
                createEtablissement.put("city",      city.getText().toString());
                createEtablissement.put("begin",     begin.getText().toString());

                // intent de retour //
                Intent retourMain = new Intent(detailActivity, ListEtablissementActivity.class);

                // retour //
                retourMain.putExtra("retourCreateEtablissement", createEtablissement);
                setResult(0, retourMain);
                finish();
            }
        });

        // buton retour //
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_etablissement, menu);
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
