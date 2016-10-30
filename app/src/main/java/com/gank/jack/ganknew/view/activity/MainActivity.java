package com.gank.jack.ganknew.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.theme.Theme;
import com.gank.jack.ganknew.theme.widget.ColorTextView;
import com.gank.jack.ganknew.utils.PreUtils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarLayout appBarLayout=null;
    private ColorTextView textView=null;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);
        textView=(ColorTextView)findViewById(R.id.app_bar_text);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.main_content);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              PreUtils.changeTheme(MainActivity.this,R.style.BlueTheme,Theme.Blue.toString());
              PreUtils.changeColorImpl(MainActivity.this,MainActivity.this.getTheme());
              collapsingToolbarLayout.setStatusBarScrimColor(Color.BLUE);
              collapsingToolbarLayout.setContentScrimColor(Color.BLUE);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
            startNewActivityByIntent(new Intent(this,TextActivity.class));
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
