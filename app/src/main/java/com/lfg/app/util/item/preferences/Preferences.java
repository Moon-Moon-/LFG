package com.lfg.app.util.item.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.lfg.app.res.STRING;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

/**
 * Created by LFG on 9/28/2017.
 */

public class Preferences {

    // ============================================================================= \\
    // FUNCS
    // ============================================================================= \\

    public static SharedPreferences get(Context c) {
        return c.getSharedPreferences(""+STRING.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }

    static SharedPreferences.Editor edit(Context c) {
        return get(c).edit();
    }

    public static SharedPreferences get(Activity a) {
        return a.getPreferences(Context.MODE_PRIVATE);
    }

    static SharedPreferences.Editor edit(Activity a) {
        return get(a).edit();
    }



    // ============================================================================= \\
    // GETS
    // ============================================================================= \\

    static int getInt(Context c, String s) {
        return get(c).getInt(s, 0);
    }

    static boolean getBoolean(Context c, String s) {
        return get(c).getBoolean(s, false);
    }

    static float getFloat(Context c, String s) {
        return get(c).getFloat(s, 0);
    }

    static long getLong(Context c, String s) {
        return get(c).getLong(s, 0);
    }

    static String getString(Context c, String s) {
        return get(c).getString(s, null);
    }

    static Set<String> getStringSet(Context c, String s) {
        return get(c).getStringSet(s, null);
    }



    // ============================================================================= \\
    // PUTS
    // ============================================================================= \\

    static void putInt(Context c, String s, int i) {
        edit(c).putInt(s, i).apply();
    }

    static void putBoolean(Context c, String s, boolean b) {
        edit(c).putBoolean(s, b).apply();
    }

    static void putFloat(Context c, String s, float f) {
        edit(c).putFloat(s, f).apply();
    }

    static void putLong(Context c, String s, long l) {
        edit(c).putLong(s, l).apply();
    }

    static void putString(Context c, String s, String sl) {
        edit(c).putString(s, sl).apply();
    }

    static void putStringSet(Context c, String s, Set<String> set) {
        edit(c).putStringSet(s, set).apply();
    }
}