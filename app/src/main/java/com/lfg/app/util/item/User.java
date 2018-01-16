package com.lfg.app.util.item;

import com.lfg.app.res.STRING;
import com.lfg.app.util.volley.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LFG on 9/28/2017.
 */

public class User extends Item {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    // NAME
    // ---------
    String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // IMAGE URL
    // -------------
    String imageUrl;
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    // ============================================================================= \\
    // INIT
    // ============================================================================= \\

    public User() {}

    public User(int id, String name) {
        this(id, name, "");
    }

    public User(int id, String name, String imageUrl) {
        set(id, name, imageUrl);
    }

    public User(JSONObject obj) throws JSONException {
        set(obj);
    }



    // ============================================================================= \\
    // FUNCS
    // ============================================================================= \\

    public void set(User user) {
        set(user.getId(),
                user.getName(),
                user.getImageUrl());
    }

    void set(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void set(JSON obj) throws JSONException {
        int id = obj.getInt(""+STRING.USER.ID);
        String name = obj.getString(""+STRING.USER.NAME);
        String imageUrl = obj.getStringValue(""+STRING.USER.IMAGE_URL);

        set(id, name, imageUrl);
    }
}