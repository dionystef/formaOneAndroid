package model;

import android.app.Activity;
import android.net.Uri;
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


public class StepThreeFragment extends Fragment {

    private String addressUser;
    private String completeAddressUser;
    private String zipCodeUser;
    private String cityUser;
    HashMap<String, String> infos = new HashMap<>();

    private OnFragmentInteractionListener mListener;


    public static StepThreeFragment newInstance() {
        StepThreeFragment fragment = new StepThreeFragment();
        return fragment;
    }

    public StepThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_three, container, false);

        // variables //
        final EditText address = (EditText) view.findViewById(R.id.editAddress);
        final EditText completeAddress = (EditText) view.findViewById(R.id.editCompleteAddress);
        final EditText zipCode = (EditText) view.findViewById(R.id.editZipCode);
        final EditText city = (EditText) view.findViewById(R.id.editCity);
        Button buttonNext = (Button) view.findViewById(R.id.buttonNextStepThree);
        Button buttonBack = (Button) view.findViewById(R.id.buttonBackStepThree);
        infos.put("back", "off");

        // button suivant //
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressUser = address.getText().toString();
                completeAddressUser = completeAddress.getText().toString();
                zipCodeUser = zipCode.getText().toString();
                cityUser = city.getText().toString();

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
        public void onFragmentThree(HashMap uri);
    }

    public void getInfo(){
        //List<String> info = new ArrayList<String>();

        if (!addressUser.equals("")){
            //info.add(addressUser);
            infos.put("main", addressUser);
        }else{
            Toast.makeText(getActivity(),
                    "adresse vide !", Toast.LENGTH_SHORT).show();
        }

        if (!completeAddressUser.equals("")){
            //info.add(completeAddressUser);
            infos.put("secondary", completeAddressUser);
        }else{
            //info.add("");
            infos.put("secondary", "");
        }

        if (!zipCodeUser.equals("")){
            //info.add(zipCodeUser);
            infos.put("zipcode", zipCodeUser);
        }else{
            Toast.makeText(getActivity(),
                    "code postal vide !", Toast.LENGTH_SHORT).show();
        }

        if (!cityUser.equals("")){
            //info.add(cityUser);
            infos.put("city", cityUser);
        }else{
            Toast.makeText(getActivity(),
                    "ville vide !", Toast.LENGTH_SHORT).show();
        }

        mListener.onFragmentThree(infos);
    }

    public  void backFrag() {
        infos.put("back", "on");
        mListener.onFragmentThree(infos);
    }

}
