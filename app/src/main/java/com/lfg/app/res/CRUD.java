package com.lfg.app.res;

import com.lfg.app.util.Extension.Params;
import com.lfg.app.util.volley.CRUDObj;

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
