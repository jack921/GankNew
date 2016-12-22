package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.utils.ImageLoad;
import com.gank.jack.ganknew.utils.PreUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/12/18.
 */

public class AboutActivity extends BaseActivity{

    @Bind(R.id.about_toolbar)
    public Toolbar aboutToolbar;
    @Bind(R.id.about_collapsing_toolbar)
    public CollapsingToolbarLayout aboutCollapsingToolbar;
    @Bind(R.id.developeruser_img)
    public ImageView developer_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setBaseSupportActionBar(aboutToolbar);

        init();
    }

    public void init(){
        aboutCollapsingToolbar.setTitle("");
        ImageLoad.loadCirclrImage(R.drawable.developers_img,developer_img);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about_shared:
                showToast("shared");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
