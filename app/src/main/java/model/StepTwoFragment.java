package model;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dcs.formaonefinal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StepTwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StepTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepTwoFragment extends Fragment {

    private String nameUser;
    private String firstnameUser;
    private String phoneUser;
    HashMap<String, String> infos = new HashMap<>();


    private OnFragmentInteractionListener mListener;

    public static StepTwoFragment newInstance(String param1, String param2) {
        StepTwoFragment fragment = new StepTwoFragment();
        return fragment;
    }

    public StepTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_two, container, false);

        // variables //
        final EditText name = (EditText) view.findViewById(R.id.editName);
        final EditText firstname = (EditText) view.findViewById(R.id.editFirstname);
        final EditText phone = (EditText) view.findViewById(R.id.editPhone);
        Button buttonNext = (Button) view.findViewById(R.id.buttonNextStepTwo);
        Button buttonBack = (Button) view.findViewById(R.id.buttonBackStepTwo);
        infos.put("back", "off");

        // button suivant //
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameUser = name.getText().toString();
                firstnameUser = firstname.getText().toString();
                phoneUser = phone.getText().toString();

                getInfo();
            }

        });

        // button precedent //
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backFrag();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        public void onFragmentTwo(HashMap uri);
    }

    public void getInfo(){
        //List<String> info = new ArrayList<String>();


        if (!nameUser.equals("")){
            //info.add(nameUser);
            infos.put("lastname", nameUser);
        }else{
            Toast.makeText(getActivity(),
                    "nom vide !", Toast.LENGTH_SHORT).show();
        }

        if (!firstnameUser.equals("")){
            //info.add(firstnameUser);
            infos.put("firstname", firstnameUser);
        }else{
            Toast.makeText(getActivity(),
                    "prénom vide !", Toast.LENGTH_SHORT).show();
        }

        if (!phoneUser.equals("")){
            //info.add(phoneUser);
            infos.put("phone", phoneUser);
        }else{
            Toast.makeText(getActivity(),
                    "téléphone vide !", Toast.LENGTH_SHORT).show();
        }

        mListener.onFragmentTwo(infos);
    }

    public  void backFrag() {
        infos.put("back", "on");
        mListener.onFragmentTwo(infos);
    }

}
