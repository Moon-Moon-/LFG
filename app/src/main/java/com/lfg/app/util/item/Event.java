package com.lfg.app.util.item;

import com.lfg.app.res.STRING;
import com.lfg.app.util.volley.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LFG on 10/11/2017.
 */

public class Event extends Item {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    // NAME
    // ---------
    String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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

    public Event() {}

    public Event(int id, String title) {
        this(id, title, "");
    }

    public Event(int id, String title, String imageUrl) {
        set(id, title, imageUrl);
    }

    public Event(JSONObject obj) throws JSONException {
        set(obj);
    }



    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    public void set(Event event) {
        set(event.getId(),
                event.getTitle(),
                event.getImageUrl());
    }

    void set(int id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public void set(JSON obj) throws JSONException {
        int id = obj.getInt(""+STRING.EVENT.ID);
        String title = obj.getString(""+STRING.EVENT.TITLE);
        String imageUrl = obj.getStringValue(""+STRING.EVENT.IMAGE_URL);

        set(id, title, imageUrl);
    }
}
