package com.dcs.formaonefinal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.StepFinalFragment;
import model.StepOneFragment;
import model.StepThreeFragment;
import model.StepTwoFragment;

public class SubscribeActivity extends AppCompatActivity implements StepTwoFragment.OnFragmentInteractionListener,
                                                                    StepOneFragment.OnFragmentInteractionListener,
                                                                    StepThreeFragment.OnFragmentInteractionListener,
                                                                    StepFinalFragment.OnFragmentInteractionListener{

    // fragment //
    StepOneFragment stephOne = new StepOneFragment();
    StepTwoFragment stepTwo = new StepTwoFragment();
    StepThreeFragment stepThree = new StepThreeFragment();
    StepFinalFragment stepFinal = new StepFinalFragment();

    // Map //
    private HashMap<String, String> infoUser = new HashMap<>();

    // get fragment manager
    FragmentManager fm = getFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);


        FragmentTransaction ft = fm.beginTransaction();
        // add
        ft.add(R.id.detailscontainer, stephOne, "stepOne");
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * reception du fragment 1
     * @param uri
     */
    @Override
    public void onFragmentOne(HashMap uri) {

        if (uri.size() == 2){
            infoUser.putAll(uri);
            showFragment(stepTwo);
        }
    }

    /**
     * reception du fragment 2
     * @param uri
     */
    @Override
    public void onFragmentTwo(HashMap uri) {

        if (uri.get("back").equals("on")) {
            onBackPressed();

        }else{
            if (uri.size() == 4) {
                infoUser.putAll(uri);

                }
                showFragment(stepThree);
            }
        }

    /**
     * reception du fragment 3
     * @param uri
     */
    @Override
    public void onFragmentThree(HashMap uri) {
        if (uri.get("back").equals("on")) {
            onBackPressed();
        }else{
            if (uri.size() == 5){
                infoUser.putAll(uri);
                showFragment(stepFinal);
            }
        }
    }

    /**
     * reception du fragment final
     * @param uri
     */
    @Override
    public void onFragmentFinal(HashMap uri) {
        if (uri.get("back").equals("on")) {
            onBackPressed();
        }else{
            if (uri.size() == 2) {
                infoUser.putAll(uri);
                infoUser.remove("back");
                for(Map.Entry<String, String> entry : infoUser.entrySet()) {
                    String cle = entry.getKey();
                    String valeur = entry.getValue();
                    Log.e(cle,": "+valeur);
                }
                finish();
            }
        }
    }

    /**
     * affiche le fragment suivant
     * @param fragment
     */
    private void showFragment(final Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        // Replace current fragment by the new one.
        ft.replace(R.id.detailscontainer, fragment);
        // Null on the back stack to return on the previous fragment when user
        // press on back button.
        ft.addToBackStack(null);

        // Commit changes.
        ft.commit();
    }

    /**
     * gestion du bouton back pour les fragments
     */
    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subscribe, menu);
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
