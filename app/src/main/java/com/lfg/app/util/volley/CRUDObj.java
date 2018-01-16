package com.lfg.app.util.volley;

import com.android.volley.Request;
import com.android.volley.Response;
import com.lfg.app.util.listener.VolleyListener;
import com.lfg.app.util.listener.VolleyListener.FailListener;
import com.lfg.app.util.listener.VolleyListener.SuccessListener;

import java.util.Map;

/**
 * Created by LFG on 04-Jan-18.
 */

public class CRUDObj {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

//    private int[] successIntegers = new int[]{1};

    private final String URL;
    private final Map<String, String> params;
    private SuccessListener successListener;
    private FailListener failListener;
    private Response.ErrorListener errorListener;



    // ============================================================================= \\
    // CREATION
    // ============================================================================= \\

    public CRUDObj(String URL, Map<String, String> params) {
        this.URL = URL;
        this.params = params;
    }



    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

//    public CRUDObj onSuccess(Response.Listener<JSONObject> successListener) {
//        this.successListener = successListener;
//        return this;
//    }

    public CRUDObj onSuccess(SuccessListener successListener) {
        this.successListener = successListener;
        return this;
    }

    public CRUDObj onFailure(FailListener failListener ) {
        this.failListener = failListener;
        return this;
    }

    public CRUDObj onError(Response.ErrorListener errorListener){
        this.errorListener = errorListener;
        return this;
    }

    public VolleyRequest request(){
        return new VolleyRequest(Request.Method.POST, URL, params,
                new VolleyListener(successListener, failListener), errorListener);
    }
}