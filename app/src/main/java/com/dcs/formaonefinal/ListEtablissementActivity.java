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
import java.util.HashMap;

import controller.EtablissementController;
import model.Etablissement;

public class ListEtablissementActivity extends AppCompatActivity {

    // variables //
    ListView listViewEtablissement;
    TextView nameEtablissemnt;
    ArrayList<Etablissement> listEtablissement = new ArrayList<>();
    EtablissementController etablissementCtrl = new EtablissementController(this);
    public  final static int mainToDetail = 0;
    private ArrayAdapter<Etablissement> Adapter;
    private HashMap<String, String> connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etablissement);

        listViewEtablissement = (ListView) findViewById(android.R.id.list);
        nameEtablissemnt = (TextView) findViewById(R.id.nameEtablissement);

        // recuperation du token //
        Intent intent = getIntent();
        connexion = (HashMap<String, String>)intent.getSerializableExtra("connexion");

        try {
            listEtablissement = etablissementCtrl.recupListEtablissements(connexion);
        } catch (JSONException e) {
            Log.e("etablissemntCtrl...", e.toString());
            e.printStackTrace();
        }

        Adapter = new ArrayAdapter<Etablissement>(this, R.layout.row_etablissement, listEtablissement);
        listViewEtablissement.setAdapter(Adapter);


        listViewEtablissement.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Etablissement item = (Etablissement) listEtablissement.get(position);

                // demarrage de l'activité etablissement //
                Intent intent = new Intent(ListEtablissementActivity.this, EtablissementActivity.class);
                intent.putExtra("company_id", item.getId());
                intent.putExtra("connexion", connexion);
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
            Intent detail = new Intent(getApplication(), CreateEtablissementActivity.class);
            startActivityForResult(detail, mainToDetail);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // reponse de l'autre activité //
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && data != null) {
            if (resultCode == 0) {
                Etablissement temp = etablissementCtrl.createEtablissement(connexion, (HashMap<String, String>) data.getSerializableExtra("retourCreateEtablissement"));
                Adapter.add(temp);
                listViewEtablissement.setAdapter(Adapter);
            }
        }

    }
}
