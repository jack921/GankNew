package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/30.
 */


public class SelectDateActivity extends BaseActivity{

    @Bind(R.id.date_recyclerview)
    private RecyclerView dateRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdate);
        ButterKnife.bind(this);

        init();
    }

    public void init(){
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
