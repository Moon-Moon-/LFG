package com.lfg.app.activity;

/**
 * Created by LFG on 9/14/2017.
 */

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.lfg.app.R;
import com.lfg.app.base.LFGActivity;

public class MainActivity extends LFGActivity {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

    private DrawerLayout mp_drawer;
    private NavigationView mp_navDrawer;
    private ActionBarDrawerToggle mp_drawerToggle;



    // ============================================================================= \\
    // INIT
    // ============================================================================= \\

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        setSupportActionBar(m_toolbar);

        // setup nav drawer
        mp_drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mp_navDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(mp_navDrawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mp_drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // ============================================================================= \\
    // SETUP
    // ============================================================================= \\

    private void setupDrawerContent(NavigationView navigationView) {

        // setup navigation view// Inflate the header view at runtime
        View headerLayout = navigationView.inflateHeaderView(R.layout.n_header);
        ImageView userImage = headerLayout.findViewById(R.id.user_image);
        ImageView userName = headerLayout.findViewById(R.id.user_name);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mp_drawer.closeDrawers();
    }


}