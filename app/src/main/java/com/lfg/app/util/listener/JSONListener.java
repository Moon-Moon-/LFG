package com.lfg.app.util.listener;

import org.json.JSONObject;

/**
 * Created by LFG on 04-Jan-18.
 */

public interface JSONListener {
    /** Called when a response is received. */
    public void onResponse(JSONObject json);
}