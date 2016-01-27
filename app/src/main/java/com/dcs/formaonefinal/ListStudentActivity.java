package com.dcs.formaonefinal;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import controller.StudentController;
import model.Person;

public class ListStudentActivity extends Activity {

    protected ListView                  listViewStudent;
    protected HashMap<String, String>   connexion;
    protected ArrayAdapter<Person>      adapter;
    protected ArrayList<Person>         listStudent         = new ArrayList<>();
    protected StudentController         studentController   = new StudentController(this);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
