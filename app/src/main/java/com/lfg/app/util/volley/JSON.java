package com.lfg.app.util.volley;

import com.lfg.app.res.STRING;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LFG on 10/5/2017.
 */

public class JSON extends JSONObject {

    // ============================================================================= \\
    // VALUE FUNCS
    // ============================================================================= \\

    public int getIntValue(String s) {
        try {
            return getInt(s);
        } catch (JSONException e1) {
            return 0;
        }
    }

    public String getStringValue(String s) {
        try {
            return getString(s);
        } catch (JSONException e1) {
            return "";
        }
    }

    public boolean getBooleanValue(String s) {
        try {
            return getBoolean(s);
        } catch (JSONException e1) {
            return false;
        }
    }

}