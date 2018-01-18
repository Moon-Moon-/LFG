package com.lfg.app.util.volley;

import com.lfg.app.util.listener.VolleyListener;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by sevyr on 17/01/2018.
 */

public abstract class CRUDItem<T> extends CRUDObj {
    // ============================================================================= \\
    // CREATION
    // ============================================================================= \\

    private ItemListener itemListener;



    // ============================================================================= \\
    // CREATION
    // ============================================================================= \\

    public CRUDItem(String URL, Map<String, String> params) {
        super(URL, params);

        VolleyListener.SuccessListener successListener = new VolleyListener.SuccessListener() {
            @Override
            public void onSuccess(JSONObject json) {
                T item = getItem(json);
                if (itemListener != null) {
                    itemListener.onSuccess(item);
                }
            }
        };
        super.onSuccess(successListener);
    }



    // ============================================================================= \\
    // CREATION
    // ============================================================================= \\

    public CRUDObj onSuccess(final ItemListener<T> listener) {
        this.itemListener = listener;
        return this;
    }

    public abstract T getItem(JSONObject json);

    public interface ItemListener<T> {
        public void onSuccess(T item);
    }
}
