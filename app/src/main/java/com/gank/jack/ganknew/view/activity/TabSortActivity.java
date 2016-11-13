package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/14.
 */

public class TabSortActivity extends BaseActivity{

    @Bind(R.id.sortToolbar)
    public Toolbar toolbar;
    @Bind(R.id.sortRecyclerView)
    public RecyclerView sortRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        sortRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

}
