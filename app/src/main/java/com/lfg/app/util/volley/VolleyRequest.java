package com.lfg.app.util.volley;

/**
 * Created by LFG on 04-Jan-18.
 */


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VolleyRequest extends Request<JSONObject> {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    private final Listener<JSONObject> listener;
    private final Map<String, String> params;


    // ERROR LISTENER
    // --------------------------------------------------------------
    final static Response.ErrorListener ERROR_LISTENER = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d("Error: " + error.getMessage(), "VT");
        }
    };




    // ============================================================================= \\
    // CONSTRUCTORS
    // ============================================================================= \\

    public VolleyRequest(int method, String url, Map<String, String> params,
                         Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = responseListener;
        this.params = params;
    }



    // ============================================================================= \\
    // METHODS
    // ============================================================================= \\

    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map headers = new HashMap();
//        headers.put("appId", "MYAPP_ID_HERE");
//        return headers;
        return new HashMap<>();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JSONException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }
}