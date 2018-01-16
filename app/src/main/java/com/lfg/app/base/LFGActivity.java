package com.lfg.app.base;

/**
 * Created by LFG on 8/10/2017.
 */

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lfg.app.BuildConfig;
import com.lfg.app.R;
import com.lfg.app.util.P;


@SuppressLint("Registered")
public class LFGActivity extends AppCompatActivity {
	
	// ============================================================================= \\
	// VARIABLES
	// ============================================================================= \\

	public Toolbar m_toolbar;

	
	// ============================================================================= \\
	// CREATION
	// ============================================================================= \\

	// ON CREATE
	// =================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		m_toolbar = (Toolbar) findViewById(R.id.toolbar);

		if (BuildConfig.DEBUG) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll()
					.build();
			StrictMode.setThreadPolicy(policy);
		}

		//colorize();
	}
	
	// STOP ON FINISH
    // ==================
	@Override
	public void finish(){
		finishTask();
	}
	public void finishTask(){
		super.finish();
		P.fade(this);
	}


	// ============================================================================= \\
	// COLORIZE
	// ============================================================================= \\

	public void colorize() {
		int color = ContextCompat.getColor(this, R.color.lfg_bar_color);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
	}
}
