package com.lfg.app.util.item;

import com.lfg.app.res.STRING;
import com.lfg.app.util.volley.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LFG on 9/28/2017.
 */

public class UserProfile extends User {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\


    String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    String dob;
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }



    // ============================================================================= \\
    // INIT
    // ============================================================================= \\

    public UserProfile() {}
    public UserProfile(int id, String name, String imageUrl, String email, String dob) {
        set(id, name, imageUrl, email, dob);
    }
    public UserProfile(JSONObject obj) throws JSONException {
        set(obj);
    }



    // ============================================================================= \\
    // FUNCS
    // ============================================================================= \\

    public void set(UserProfile user) {
        set(user.getId(),
                user.getName(),
                user.getImageUrl(),
                user.getEmail(),
                user.getDob());
    }

    void set(int id, String name, String imageUrl, String email, String dob) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.email = email;
        this.dob = dob;
    }

    public void set(JSON obj) throws JSONException {
        int id = obj.getInt(""+STRING.USER.ID);
        String name = obj.getString(""+STRING.USER.NAME);
        String imageUrl = obj.getStringValue(""+STRING.USER.IMAGE_URL);
        String email = obj.getStringValue(""+STRING.USER.EMAIL);
        String dob = obj.getStringValue(""+STRING.USER.DOB);

        set(id, name, imageUrl, email, dob);
    }
}