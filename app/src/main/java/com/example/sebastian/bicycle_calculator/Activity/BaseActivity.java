package com.example.sebastian.bicycle_calculator.Activity;

import android.annotation.TargetApi;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


import com.example.sebastian.bicycle_calculator.R;

public class BaseActivity extends AppCompatActivity {
    private static final int NAVIGATION_DRAWER_LAUNCH_DELAY = 250;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initializeNavDrawer();
    }


    public void initializeNavDrawer() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.nav_fixed_calc:
                       goToActivity(FixedCalculator.class);
                        return true;
                    case R.id.nav_garage:
                        goToActivity(Garage.class);
                        return true;
                    case R.id.nav_bicycle_creator:
                        goToActivity(BicycleCreator.class);
                        return true;
                }
                return true;
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                Log.d("mainactivity", "drawerclosed");
            }
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                Log.d("mainactivity", "draweropen");
            }
        };


    }
    private void closeNavigationDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    private void goToActivity(final Class activityClass){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Log.e("baseActivity", "nazwa" + getClass().getName());
                Log.e("activityClass", "nazwa" +activityClass.getName());
                Intent intent = new Intent(BaseActivity.this, activityClass);
                startActivity(intent);
                finish();
            }
        },NAVIGATION_DRAWER_LAUNCH_DELAY);
        closeNavigationDrawer();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}


