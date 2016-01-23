package model;

import android.app.Activity;
import android.content.Context;
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
import java.util.Map;

public class StepOneFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String mailUser;
    private String mdpUser;
    private String verifMdpUser;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static StepOneFragment newInstance(String param1, String param2) {
        StepOneFragment fragment = new StepOneFragment();
        return fragment;
    }

    public StepOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_one, container, false);

        // variables //
        final EditText mail = (EditText) view.findViewById(R.id.editMail);
        final EditText mdp = (EditText) view.findViewById(R.id.editMdp);
        final EditText verifMdp = (EditText) view.findViewById(R.id.editVerifMdp);
        Button buttonNext = (Button) view.findViewById(R.id.buttonNextStepOne);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mailUser = mail.getText().toString();
                mdpUser = mdp.getText().toString();
                verifMdpUser = verifMdp.getText().toString();

                getInfo();

            }

        });


        // Inflate the layout for this fragment
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

        public void onFragmentOne(HashMap uri);


    }


    public void getInfo(){
        List<String> info = new ArrayList<String>();
        HashMap<String, String> infos = new HashMap<>();

        if (!mailUser.equals("")){
            //info.add(mailUser);
            infos.put("mail", mailUser);
        }else{
            Toast.makeText(getActivity(),
                    "mail vide !", Toast.LENGTH_SHORT).show();
        }

        if(!mdpUser.equals("") || !verifMdpUser.equals("")){
            if (mdpUser.equals(verifMdpUser)) {
                //info.add(mdpUser);
                infos.put("mdp", mdpUser);
            }else{
                Toast.makeText(getActivity(),
                        "mot de passe différents !", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(),
                    "mot de passe vide !", Toast.LENGTH_SHORT).show();
        }


        mListener.onFragmentOne(infos);
    }

}
