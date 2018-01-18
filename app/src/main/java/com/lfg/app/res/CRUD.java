package com.lfg.app.res;

import com.lfg.app.util.Extension.Params;
import com.lfg.app.util.item.User;
import com.lfg.app.util.listener.VolleyListener;
import com.lfg.app.util.volley.CRUDItem;
import com.lfg.app.util.volley.CRUDObj;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by LFG on 04-Jan-18.
 */

public class CRUD {

    public static CRUDObj getUser(String userId) {
        Params params = new Params();
        params.put(""+STRING.USER.ID, userId);
        return new CRUDObj(""+URL.USER_GET, params);
    }

}
