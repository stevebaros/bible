package me.piebridge.bible.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import me.piebridge.bible.R;
import me.piebridge.bible.Search;
import me.piebridge.bible.Settings;
import me.piebridge.bible.Versions;

/**
 * Created by thom on 16/7/24.
 */
public abstract class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int DELAY = 250;

    private DrawerLayout drawer;

    private ActionBarDrawerToggle drawerToggle;

    protected void setupDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawerToggle == null) {
            drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.install, R.string.uninstall);
            drawer.addDrawerListener(drawerToggle);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        NavigationView navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(item.getItemId());
            }
        }, DELAY);
        return true;
    }

    protected void navigate(final int itemId) {
        switch (itemId) {
            case R.string.menu_search:
                startActivity(new Intent(this, Search.class));
                break;
            case R.string.menu_settings:
                startActivity(new Intent(this, Settings.class));
                break;
            case R.string.menu_download:
                startActivity(new Intent(this, Versions.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (drawerToggle != null) {
            drawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (drawerToggle != null) {
            drawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle != null && drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
