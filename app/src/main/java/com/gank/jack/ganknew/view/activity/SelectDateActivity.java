package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.presenter.SelectDatePersenter;
import com.gank.jack.ganknew.utils.widget.DividerItemDecoration;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/30.
 */


public class SelectDateActivity extends BaseActivity{

    @Bind(R.id.date_recyclerview)
    public RecyclerView dateRecyclerView;
    @Bind(R.id.date_toolbar)
    public Toolbar dateToolbar;

    private SelectDatePersenter selectDatePersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdate);
        ButterKnife.bind(this);
        init();
        initView();
    }

    public void init(){
        dateToolbar.setTitle(getString(R.string.choosedate));
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dateRecyclerView.addItemDecoration(new DividerItemDecoration(
                this,DividerItemDecoration.VERTICAL_LIST));
    }

    public void initView(){
        setBaseSupportActionBar(dateToolbar);
        selectDatePersenter=new SelectDatePersenter(this);


    }


}
