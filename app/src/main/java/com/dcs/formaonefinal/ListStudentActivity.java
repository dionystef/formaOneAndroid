package com.dcs.formaonefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import controller.StudentController;
import model.Etablissement;
import model.Person;

public class ListStudentActivity extends AppCompatActivity {

    protected ListView                  listViewStudent;
    protected HashMap<String, String>   connexion;
    protected ArrayAdapter<Person>      adapter;
    protected ArrayList<Person>         listStudent             = new ArrayList<>();
    protected StudentController         studentController       = new StudentController(this);
    private  final static int           listStudentToContact    = 0;
    protected ListStudentActivity       activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        listViewStudent = (ListView) findViewById(android.R.id.list);

        // Récupération des informations de connexion au webservice
        Intent intent = getIntent();
        connexion = (HashMap<String, String>)intent.getSerializableExtra("connexion");

        listStudent = studentController.getList(connexion);
        if(listStudent != null) {
            adapter = new ArrayAdapter<>(this, R.layout.row_student, listStudent);
            listViewStudent.setAdapter(adapter);
        }

        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Person person = listStudent.get(position);

                // demarrage de l'activité détails des étudiants //
                /*Intent intent = new Intent(ListEtablissementActivity.this, EtablissementActivity.class);
                intent.putExtra("company_id", item.getId());
                intent.putExtra("connexion", connexion);
                startActivityForResult(intent, listEtabToEtab);*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_student, menu);
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
            Intent createStudent = new Intent(getApplication(), ContactActivity.class);
            startActivityForResult(createStudent, listStudentToContact);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // reponse de l'autre activité //
        super.onActivityResult(requestCode, resultCode, data);

        // Ajout d'un étudiant
        if (requestCode == 0 && data != null) {
            if (resultCode == 0) {
                //Etablissement temp = etablissementCtrl.createEtablissement(connexion, (HashMap<String, String>) data.getSerializableExtra("retourCreateEtablissement"));
                //Adapter.add(temp);
                //listViewEtablissement.setAdapter(Adapter);
            }
        }
    }
}
