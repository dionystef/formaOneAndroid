package model;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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

public class StepFinalFragment extends Fragment {

    private String dateActivityUser;
    HashMap<String, String> infos = new HashMap<>();

    private OnFragmentInteractionListener mListener;

    public static StepFinalFragment newInstance() {
        StepFinalFragment fragment = new StepFinalFragment();
        return fragment;
    }

    public StepFinalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_final, container, false);

        // variables //
        final EditText dateActivity = (EditText) view.findViewById(R.id.editDateActivity);
        Button buttonNext = (Button) view.findViewById(R.id.buttonNextStepFinal);
        Button buttonBack = (Button) view.findViewById(R.id.buttonBackStepFinal);
        infos.put("back", "off");

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateActivityUser = dateActivity.getText().toString();
                getInfo();
            }
        });

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
        public void onFragmentFinal(HashMap uri);
    }

    public void getInfo(){
        //List<String> info = new ArrayList<String>();

        if (!dateActivityUser.equals("")){
            //info.add(dateActivityUser);
            infos.put("dateActivity", dateActivityUser);
        }else{
            Toast.makeText(getActivity(),
                    "ville vide !", Toast.LENGTH_SHORT).show();
        }

        mListener.onFragmentFinal(infos);
    }

    public  void backFrag() {
        infos.put("back", "on");
        mListener.onFragmentFinal(infos);
    }

}
