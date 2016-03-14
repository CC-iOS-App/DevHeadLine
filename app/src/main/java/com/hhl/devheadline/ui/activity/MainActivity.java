package com.hhl.devheadline.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.hhl.devheadline.R;
import com.hhl.devheadline.ui.fragment.HomeFragment;
import com.hhl.devheadline.ui.fragment.ShareFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private HomeFragment mHomeFragment;
    private ShareFragment mShareFragment;

    /**
     * 启动MainActivity
     *
     * @param context
     */
    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //fragment Manager
        managerFragment(savedInstanceState);
    }

    /**
     * 管理Fragment
     *
     * @param savedInstanceState
     */
    private void managerFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mHomeFragment = HomeFragment.newInstance("1", "2");
            mShareFragment = ShareFragment.newInstance("3", "4");

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.container, mHomeFragment)
                    .add(R.id.container, mShareFragment).hide(mShareFragment).show(mHomeFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_feedback) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FeedbackActivity.launch(MainActivity.this);
                }
            }, 300);
        } else if (id == R.id.nav_setting) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SettingActivity.launch(MainActivity.this);
                }
            }, 300);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().hide(mHomeFragment).hide(mShareFragment).commit();

            if (id == R.id.nav_home) {
                // Handle the camera action
                fm.beginTransaction().show(mHomeFragment).commit();
            } else if (id == R.id.nav_share) {
                fm.beginTransaction().show(mShareFragment).commit();
            } else if (id == R.id.nav_create_theme) {

            } else if (id == R.id.nav_subscrier_theme) {

            } else if (id == R.id.nav_favorite) {

            }
        }
        return true;
    }

}