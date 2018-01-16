package com.lfg.app.util.listener;

/**
 * Created by LFG on 04-Jan-18.
 */

import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.lfg.app.res.STRING;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyListener implements Listener<JSONObject>{

    // ============================================================================= \\
    // VARS
    // ============================================================================= \\

    private final SuccessListener successListener;
    private final FailListener failListener;



    // ============================================================================= \\
    // CREATION
    // ============================================================================= \\

    public VolleyListener(SuccessListener successListener,
                          FailListener failListener){
        this.successListener = successListener;
        this.failListener = failListener;
    }



    // ============================================================================= \\
    // OVERRIDE
    // ============================================================================= \\

    @Override
    public void onResponse(JSONObject response) {

        try {
            VolleyLog.d("Response:\n" + response.toString(5));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // SUCCESS METHODS
        int success = -1;
        try {
            success = response.getInt(""+STRING.VOLLEY.SUCCESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (success == 1) {
            successListener.onSuccess(response);
        } else {
            failListener.onFail(success, response);
        }

        // MESSAGE METHODS
        String messageVar = ""+STRING.VOLLEY.MESSAGE;
        if(response.has(messageVar))
            try {
//				P.toastLong(m_context, response.getString(TAGS.MESSAGE));
                VolleyLog.d("Message: " + response.getString(messageVar));
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }



    // ============================================================================= \\
    // LISTENERS
    // ============================================================================= \\

    public interface SuccessListener {
        /** Called when a response is received. */
        public void onSuccess(JSONObject json);
    }

    public interface FailListener {
        /** Called when a response is received. */
        public void onFail(int success, JSONObject json);
    }

}