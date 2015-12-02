package com.dcs.formaonefinal;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONException;

import java.util.ArrayList;

import controller.EtablissementController;
import model.Etablissement;

public class ListEtablissementActivity extends AppCompatActivity {

    // variables //
    ListView listViewEtablissement;
    TextView nameEtablissemnt;
    ArrayList<Etablissement> listEtablissement = new ArrayList<>();
    EtablissementController etablissementCtrl = new EtablissementController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etablissement);

        listViewEtablissement = (ListView) findViewById(android.R.id.list);
        nameEtablissemnt = (TextView) findViewById(R.id.nameEtablissement);

        try {
            listEtablissement = etablissementCtrl.recupListEtablissements();
        } catch (JSONException e) {
            Log.e("etablissemntCtrl...", e.toString());
            e.printStackTrace();
        }

        ArrayAdapter<Etablissement> Adapter = new ArrayAdapter<Etablissement>(this, R.layout.row_etablissement, listEtablissement);
        listViewEtablissement.setAdapter(Adapter);




        listViewEtablissement.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListEtablissementActivity.this, EtablissementActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_etablissement, menu);
        return super.onCreateOptionsMenu(menu);
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
