package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.ThemeAdapter;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.utils.ToastUtil;
import com.gank.jack.ganknew.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/12/18.
 */


public class ThemeActivity extends BaseActivity{

    @Bind(R.id.about_toolbar)
    public Toolbar aboutToolbar;
    @Bind(R.id.theme_recyclerview)
    public RecyclerView themeRecyclerview;

    public ThemeAdapter themeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        ButterKnife.bind(this);
        setBaseSupportActionBar(aboutToolbar);

        initView();
    }

    public void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        themeRecyclerview.setLayoutManager(linearLayoutManager);
        themeAdapter=new ThemeAdapter(this);
        themeRecyclerview.setAdapter(themeAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.theme_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_sure:
                showToast("sure");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
