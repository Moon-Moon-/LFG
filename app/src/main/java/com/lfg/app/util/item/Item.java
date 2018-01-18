package com.lfg.app.util.item;

import com.lfg.app.util.volley.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LFG on 10/5/2017.
 */

public abstract class Item {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    // ID
    // ----
    int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    // ============================================================================= \\
    // ABSTRACT FUNCS
    // ============================================================================= \\

    public abstract void set(JSON obj) throws JSONException;
    public void set(JSONObject obj) throws JSONException {
        set((JSON)obj);
    }

}
