package com.dcs.formaonefinal;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class ContactActivity extends AppCompatActivity {

    protected ComponentName componentName;
    protected ContactActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        this.componentName = this.getCallingActivity();

        // Binding view
        final EditText firstname    = (EditText) findViewById(R.id.firstname);
        final EditText lastname     = (EditText) findViewById(R.id.lastname);
        final EditText phone        = (EditText) findViewById(R.id.phone);
        final EditText mail         = (EditText) findViewById(R.id.mail);
        final EditText main         = (EditText) findViewById(R.id.main);
        final EditText secondary    = (EditText) findViewById(R.id.secondary);
        final EditText zipcode      = (EditText) findViewById(R.id.zipcode);
        final EditText city         = (EditText) findViewById(R.id.city);
        final EditText begin        = (EditText) findViewById(R.id.begin);
        Button save                 = (Button)   findViewById(R.id.save);
        Button cancel               = (Button)   findViewById(R.id.cancel);

        final HashMap<String, String> contact = new HashMap<>();


        // Cancel
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Enregistrer
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.put("firstname",    firstname.getText().toString());
                contact.put("lastname",     lastname.getText().toString());
                contact.put("phone",        phone.getText().toString());
                contact.put("mail",         mail.getText().toString());
                contact.put("main",         main.getText().toString());
                contact.put("secondary",    secondary.getText().toString());
                contact.put("zipcode",      zipcode.getText().toString());
                contact.put("city",         city.getText().toString());
                contact.put("begin",        begin.getText().toString());

                // Retour
                Intent intent = new Intent(activity, componentName.getClass());
                intent.putExtra("retour", contact);
                setResult(0, intent);
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
