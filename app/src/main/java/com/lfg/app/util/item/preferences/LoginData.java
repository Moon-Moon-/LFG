package com.lfg.app.util.item.preferences;

import android.content.Context;

import com.lfg.app.res.STRING;
import com.lfg.app.util.item.User;
import com.lfg.app.util.item.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LFG on 9/28/2017.
 */

public class LoginData extends UserProfile {

    // ============================================================================= \\
    // FUNCS
    // ============================================================================= \\

    public static void put(JSONObject obj, Context context) {
        Preferences.putString(context, obj.toString(), ""+STRING.PREFERENCE_LOGIN_JSON);
    }

    static JSONObject getJSON(Context context) throws JSONException {
        String sJSON = Preferences
                .get(context)
                .getString(""+STRING.PREFERENCE_LOGIN_JSON, null);
        if(sJSON != null)
            return new JSONObject(sJSON);
        return null;
    }

    public static User getUser(Context context) throws JSONException {
        JSONObject json = getJSON(context);
        return new User(json);
    }
}