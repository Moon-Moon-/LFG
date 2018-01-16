package com.lfg.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lfg.app.R;

import java.io.PrintStream;
import java.util.IllegalFormatException;

public class P {
	
	
	// ====================================================================================================================================================== \\
	// VARIABLES
	// ====================================================================================================================================================== \\

    public static final PrintStream out, err;

    static {
        err = System.err;
        out = System.out;
    }
	
	
    
	// ====================================================================================================================================================== \\
	// LETS PRINT SOMETHING INTO THE CONSOLE                                                                                                                                         \\
	// ====================================================================================================================================================== \\
	

    /**
     * Prints a string followed by a newline. 
     * The string is converted to an array of bytes using
     * the encoding chosen during the construction of this stream. The bytes are
     * then written to the target stream with {@code write(int)}.
     *
     * <p>If an I/O error occurs, this stream's error state is set to {@code true}.
     *
     * @param string the string to print to the target stream.
     * @see java.io.PrintStream#write(int)
     */
	// ----------------------------------------
	public static void ln(String string) {
		out.println(string);
	}


	/**
	 * Prints the string representation
	 * of the Object o, or "null", followed by a newline.
	 */
	// ----------------------------------
	public static void ln(Object o) {
		out.println(o);
	}


	/**
	 * Prints a string to the target stream.
	 * The string is converted to an array of bytes
	 * using the encoding chosen during the construction of this stream.
	 * The bytes are then written to the target stream with write(int).
	 * <p>
	 * If an I/O error occurs, this stream's error state is set to true.
	 *
     * @param string the string to print to the target stream.
     * @see java.io.PrintStream#write(int)
	 */
	// ---------------------------------------
	public static void n(String string) {
		out.print(string);
	}


	/**
	 * Prints the string representation
	 * of the Object o, or "null".
	 */
	// ----------------------------------
	public static void n(Object o) {

		out.print(o);
	}


    /**
     * Prints a formatted string. The behavior of this
     * method is the same as this stream's {@code #format(String, Object...)} method.
     *
     * @param format the format string (see {@link java.util.Formatter#format})
     * @param args
     *            the list of arguments passed to the formatter. If there are
     *            more arguments than required by {@code format},
     *            additional arguments are ignored.
     * @throws IllegalFormatException
     *             if the format string is illegal or incompatible with the
     *             arguments, if there are not enough arguments or if any other
     *             error regarding the format string or arguments is detected.
     * @throws NullPointerException if {@code format == null}
     */
	// --------------------------------------------------
	public static void f(String format, Object... args) {
		out.printf(format, args);
	}



	// ====================================================================================================================================================== \\
	// ANDROID
	// ====================================================================================================================================================== \\


	/**
	 * Make a standard toast for a SHORT duration that just
	 * contains a text view.
	 * <p>
	 * When the view is shown to the user, appears as a floating view over the application.
	 * <p>
	 * A toast is a view containing a quick little message for the user. The toast class helps you create and show those.
	 *
	 * @param context The context to use. Usually your android.app.Application or android.app.Activity object.
	 * @param text The text to show. Can be formatted text.
	 */
	// ---------------------------------------------------------
	public static void toastShort(Context context, String text) {

		ln("SHORT TOAST: " + text);
		Toast.makeText(context, text,
				Toast.LENGTH_SHORT).show();
	}


	/**
	 * Make a standard toast for a LONG duration that just
	 * contains a text view.
	 * <p>
	 * When the view is shown to the user, appears as a floating view over the application.
	 * <p>
	 * A toast is a view containing a quick little message for the user. The toast class helps you create and show those.
	 *
	 * @param context The context to use. This is usually your android.app.Application or android.app.Activity object.
	 * @param text The text to show. Can be formatted text.
	 */
	// ----------------------------------------------------------
	public static void toastLong(Context context, String text) {

		ln("LONG TOAST: " + text);
		Toast.makeText(context, text,
				Toast.LENGTH_LONG).show();
	}


	/**
	 * Converts a dip value to its final floating point value in pixels.
	 *
	 * @param context The context to use, usually your android.app.Application or android.app.Activity object
	 * @param value The value to apply the unit to.
	 * @see {@link Activity#overridePendingTransition(int, int)}
	 */
	// ------------------------------------------------------------------
	public static int dipToPx(Context context, int value) {

	    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
	    		context.getResources().getDisplayMetrics());
	}

    public static void hideKeyboard(Context context){

        // Check if no view has focus:
        if(context instanceof Activity) {
            Activity activity = (Activity) context;
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                view.clearFocus();
            }
        }
    }



	// ====================================================================================================================================================== \\
	// ANDROID FADE ACTIVITY
	// ====================================================================================================================================================== \\


	/**
	 * Launch a new activity, with a fade animation. You will not
	 * receive any information about when the activity exits.
	 *
	 * @param context The context to use. This is usually your android.app.Application or android.app.Activity object.
	 * @param cls The component class that is to be used for the intent.
	 * @see {@link android.app.Activity#startActivity(Intent)}
	 */
	// --------------------------------------------------------------
	public static void fadeToActivity(Activity context, Class<?> cls) {

		context.startActivity(new Intent(context, cls));
		fade(context);
	}


	/**
	 * Launch a new activity, with a fade animation. You will not
	 * receive any information about when the activity exits.
	 *
	 * @param context The context to use. This is usually your android.app.Application or android.app.Activity object.
	 * @param intent The intent to start
	 * @see {@link android.app.Activity#startActivity(Intent)}
	 */
	// ---------------------------------------------------------------
	public static void fadeToActivity(Activity context, Intent intent) {

		context.startActivity(intent);
		fade(context);
	}


	/**
	 * Launch a new activity, with a fade animation, for which you would like a result
	 * when it finished.
	 * <p>
	 * When this activity exits, your onActivityResult() method will be
	 * called with the given requestCode.
	 * Using a negative requestCode is the same as calling {@link Activity#startActivity}
	 * (the activity is not launched as a sub-activity).
	 *
	 * @param context The context to use, usually your android.app.Application or android.app.Activity object
	 * @param cls The component class that is to be used for the intent.
	 * @see {@link android.app.Activity#startActivityForResult(Intent, int)}
	 */
	// ------------------------------------------------------------------------------------------
	public static void fadeToActivityForResult(Activity context, Class<?> cls, int requestCode) {

		context.startActivityForResult(new Intent(context, cls), requestCode);
		fade(context);
	}


	/**
	 * Launch a new activity, with a fade animation, for which you would like a result
	 * when it finished.
	 * <p>
	 * When this activity exits, your onActivityResult() method will be
	 * called with the given requestCode.
	 * Using a negative requestCode is the same as calling {@link Activity#startActivity}
	 * (the activity is not launched as a sub-activity).
	 *
	 * @param context The context to use, usually your android.app.Application or android.app.Activity object
	 * @param i The intent to start
	 * @see {@link android.app.Activity#startActivityForResult(Intent, int)}
	 */
	// --------------------------------------------------------------------------------------
	public static void fadeToActivityForResult(Activity context, Intent i, int requestCode) {

		context.startActivityForResult(i, requestCode);
		fade(context);
	}


	/**
	 * Call immediately after one of the flavors
	 * of {@link Activity#startActivity(Intent)} or {@link Activity#finish} to specify a
	 * fade transition animation to perform next.
	 *
	 * @param context The context to use, usually your android.app.Application or android.app.Activity object
	 * @see {@link Activity#overridePendingTransition(int, int)}
	 */
	// -------------------------------------------
	public static void fade(Activity context) {

		context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}


    /**
     * Call immediately after one of the flavors
     * of {@link Activity#startActivity(Intent)} or {@link Activity#finish} to specify a
     * fade transition animation to perform next.
     *
     * @param context The context to use, usually your android.app.Application or android.app.Activity object
     * @see {@link Activity#overridePendingTransition(int, int)}
     */
    // ----------------------------------------------
    public static void fadeFinish(Activity context) {

        context.finish();
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }



	// ====================================================================================================================================================== \\
	// ANDROID FADE CONTEXT
	// ====================================================================================================================================================== \\


	/**
	 * Launch a new activity, with a fade animation. You will not
	 * receive any information about when the activity exits.
	 *
	 * @param context The context to use. This is usually your android.app.Application or android.app.Activity object.
	 * @param cls The component class that is to be used for the intent.
	 * @see {@link android.app.Activity#startActivity(Intent)}
	 */
	// --------------------------------------------------------------
	public static void fadeToActivity(Context context, Class<?> cls) {

		context.startActivity(new Intent(context, cls));
		fade(context);
	}


	/**
	 * Launch a new activity, with a fade animation. You will not
	 * receive any information about when the activity exits.
	 *
	 * @param context The context to use. This is usually your android.app.Application or android.app.Activity object.
	 * @param intent The intent to start
	 * @see {@link android.app.Activity#startActivity(Intent)}
	 */
	// ---------------------------------------------------------------
	public static void fadeToActivity(Context context, Intent intent) {

		context.startActivity(intent);
		fade(context);
	}


	/**
	 * Launch a new activity, with a fade animation, for which you would like a result
	 * when it finished.
	 * <p>
	 * When this activity exits, your onActivityResult() method will be
	 * called with the given requestCode.
	 * Using a negative requestCode is the same as calling {@link Activity#startActivity}
	 * (the activity is not launched as a sub-activity).
	 *
	 * @param context The context to use, usually your android.app.Application or android.app.Activity object
	 * @param cls The component class that is to be used for the intent.
	 * @see {@link android.app.Activity#startActivityForResult(Intent, int)}
	 */
	// ------------------------------------------------------------------------------------------
	public static void fadeToActivityForResult(Context context, Class<?> cls, int requestCode) {

		if(context instanceof Activity)
            fadeToActivityForResult((Activity)context, cls, requestCode);
	}


	/**
	 * Launch a new activity, with a fade animation, for which you would like a result
	 * when it finished.
	 * <p>
	 * When this activity exits, your onActivityResult() method will be
	 * called with the given requestCode.
	 * Using a negative requestCode is the same as calling {@link Activity#startActivity}
	 * (the activity is not launched as a sub-activity).
	 *
	 * @param context The context to use, usually your android.app.Application or android.app.Activity object
	 * @param i The intent to start
	 * @see {@link android.app.Activity#startActivityForResult(Intent, int)}
	 */
	// --------------------------------------------------------------------------------------
	public static void fadeToActivityForResult(Context context, Intent i, int requestCode) {

		if(context instanceof Activity)
            fadeToActivityForResult((Activity)context, i, requestCode);
	}
	

	/**
	 * Call immediately after one of the flavors 
	 * of {@link Activity#startActivity(Intent)} or {@link Activity#finish} to specify a
	 * fade transition animation to perform next.
	 * 
	 * @param context The context to use, usually your android.app.Application or android.app.Activity object
	 * @see {@link Activity#overridePendingTransition(int, int)}
	 */
	// -------------------------------------------
	public static void fade(Context context) {
		if(context instanceof Activity)
			fade((Activity) context);
	}


    /**
     * Call immediately after one of the flavors
     * of {@link Activity#startActivity(Intent)} or {@link Activity#finish} to specify a
     * fade transition animation to perform next.
     *
     * @param context The context to use, usually your android.app.Application or android.app.Activity object
     * @see {@link Activity#overridePendingTransition(int, int)}
     */
    // ----------------------------------------------
    public static void fadeFinish(Context context) {

        if(context instanceof Activity)
            fadeFinish((Activity) context);
    }
	
	
	
	// ====================================================================================================================================================== \\
	// LETS TRY TO PERFORM A TASK AND CATCH ANY POSSIBLE ERRORS                                                                                                                                      \\
	// ====================================================================================================================================================== \\

	/**
	 * Causes the thread which sent this message 
	 * to sleep for the given interval of time 
	 * (given in milliseconds). 
	 * The precision is not guaranteed - the Thread may sleep more or less than requested.
	 * 
	 * @param delta The time to sleep in milliseconds.
	 * @see {@link Thread#sleep(long)}
     * @throws InterruptedException if the current thread has been interrupted.
     *            The interrupted status of the current thread will be cleared before the exception
     *            is thrown.
     * @see Thread#interrupt()
	 */
	// -------------------------------------------
	@SuppressWarnings("JavaDoc")
    public static void sleep(long delta) {
		try {
			Thread.sleep(delta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



    // ============================================================================= \\
    // STRING CHECKS
    // ============================================================================= \\


    // CHECK IF NULL
    // ====================================
    public static boolean isNull(String s){

        return s == null || (s.equals("null") || s.equals(""));
    }
    public static boolean isEmpty(String s){

        return s == null || (s.replaceAll("\\s", "")).equals("");
    }
    public static boolean isEmptyOrNull(String s){

        return s == null || s.equals("null") || (s.replaceAll("\\s", "")).equals("");
    }

    public static boolean isParsable(String input){
        boolean parsable = true;
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;
    }



    // ============================================================================= \\
    // GET VERSION
    // ============================================================================= \\

    public static String getVersion(Context context){
        String version = "";
        try {
            // Get the package info
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }



    // ============================================================================= \\
    // GOOGLE ANALYTICS
    // ============================================================================= \\

//    public static void sendAnalytics(Context context, String category, String action){
//
//        ViteeApplication app =
//                context instanceof ViteeApplication ? (ViteeApplication) context:
//                        context instanceof Activity ? (ViteeApplication) ((Activity) context).getApplication(): null;
//
//		if (app != null)
//			app.getDefaultTracker().send(new HitBuilders.EventBuilder()
//				.setCategory(category)
//				.setAction(action)
//				.build());
//    }



	// ============================================================================= \\
	// FIND SUITABLE PARENT
	// ============================================================================= \\

	public static ViewGroup findSuitableParent(View view, Class<?> cls) {

		do {
			if(cls.isInstance(view)) {
				return (ViewGroup)view;
			}

			if(view != null) {
				ViewParent parent = view.getParent();
				view = parent instanceof View ? (View) parent: null;
			}
		} while(view != null);

		return null;
	}
}