package model;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by dionys on 28/09/15.
 */
public class Rest {

    public JSONObject send (String type, HashMap data){

        Connection c = new Connection(type, data);
        c.execute();
        try {
            return c.get();

        }catch (InterruptedException e){
            return null;

        }catch (ExecutionException e){
            return null;
        }
    }


}
