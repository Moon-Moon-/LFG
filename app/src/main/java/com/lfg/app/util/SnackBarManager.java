package com.lfg.app.util;

/**
 * Created by sevyr on 17/01/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.lfg.app.R;

public class SnackBarManager {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    private static Snackbar m_snackBar;
    private static boolean m_isDismissing = false;



    // ============================================================================= \\
    // SHOW (ACTIVITY)
    // ============================================================================= \\

    public static void showError(Context context, String error){
        showError(context, error, Snackbar.LENGTH_LONG);
    }

    public static void showError(Context context, String error, int length){
        showError(context, error, length, null);
    }

    public static void showError(Context context, String error, View.OnClickListener listener){
        showError(context, error, Snackbar.LENGTH_LONG, "RETRY", listener);
    }

    public static void showError(Context context, String error, int length, View.OnClickListener listener){
        showError(context, error, length, "RETRY", listener);
    }

    public static void showError(Context context, String error, int length, String retryText, View.OnClickListener listener){
        if (context instanceof Activity) {
//            if (((Activity) context).findViewById(R.id.coordinatorLayout) != null)
//                showError(((Activity) context).findViewById(R.id.coordinatorLayout), error, length, retryText, listener);
//            else
                if (((Activity) context).findViewById(android.R.id.content) != null)
                showError(((Activity) context).findViewById(android.R.id.content), error, length, retryText, listener);
        }
    }



    // ============================================================================= \\
    // SHOW (VIEW)
    // ============================================================================= \\

    public static void showError(View view, String error){
        showError(view, error, Snackbar.LENGTH_LONG);
    }

    public static void showError(View view, String error, int length){
        showError(view, error, length, null);
    }

    public static void showError(View view, String error, View.OnClickListener listener){
        showError(view, error, Snackbar.LENGTH_LONG, "RETRY", listener);
    }

    public static void showError(View view, String error, int length, View.OnClickListener listener){
        showError(view, error, length, "RETRY", listener);
    }

    public static void showError(View view, String error, int length, String retryText, View.OnClickListener listener){

        m_snackBar = Snackbar.make(view, error, length)
                .setActionTextColor(ContextCompat.getColor(view.getContext(), R.color.material_amber600));

        if (listener != null)
            m_snackBar.setAction(retryText, listener);

        m_snackBar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                m_isDismissing = false;
            }
        });

        setTextColor(m_snackBar, Color.WHITE);

        P.ln("SNACKBAR: " + error);
        m_snackBar.show();
    }



    // ============================================================================= \\
    // DISMISS
    // ============================================================================= \\

    public static void dismiss(){

        if(m_snackBar != null)
            if (m_snackBar.isShown())
                if (!m_isDismissing){

                    m_isDismissing = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            m_snackBar.dismiss();
                        }
                    }, 0);
                }
    }



    // ============================================================================= \\
    // TEXT COLOR
    // ============================================================================= \\

    public static void setTextColor(@ColorInt int color) {
        setTextColor(m_snackBar, color);
    }
    private static void setTextColor(Snackbar snack, @ColorInt int color) {
        if (snack != null) {

            View view = snack.getView();
            TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            if (tv != null)
                tv.setTextColor(color);
        }
    }
}