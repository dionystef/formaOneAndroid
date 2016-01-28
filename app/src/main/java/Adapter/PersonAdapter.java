package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dcs.formaonefinal.R;

import java.util.ArrayList;

import model.Person;

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(Context context, ArrayList<Person> persons) {
        super(context, 0, persons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Person person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_student, parent, false);
        }
        // Lookup view for data population
        TextView firstname = (TextView) convertView.findViewById(R.id.firstname);
        TextView lastname = (TextView) convertView.findViewById(R.id.lastname);
        // Populate the data into the template view using the data object
        firstname.setText(person.getFirstname());
        lastname.setText(person.getLastname());
        // Return the completed view to render on screen
        return convertView;
    }

}
