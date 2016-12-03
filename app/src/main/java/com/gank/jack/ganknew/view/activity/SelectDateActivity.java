package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.SelectDateAdapter;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.SelectDate;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.interfaces.SelectDateInterface;
import com.gank.jack.ganknew.presenter.SelectDatePersenter;
import com.gank.jack.ganknew.utils.widget.DividerItemDecoration;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/30.
 */

public class SelectDateActivity extends BaseActivity implements SelectDateInterface, OnClickLintener {

    @Bind(R.id.date_recyclerview)
    public RecyclerView dateRecyclerView;
    @Bind(R.id.date_toolbar)
    public Toolbar dateToolbar;

    private SelectDatePersenter selectDatePersenter;
    private SelectDateAdapter adapter;
    private SelectDate selectDate;

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
        selectDatePersenter.getSelectDate(this);
    }

    @Override
    public void getSelectDate(SelectDate selectDate) {
        this.selectDate=selectDate;
        if(selectDate.results!=null&&selectDate.results.size()>0){
            adapter=new SelectDateAdapter(SelectDateActivity.this,selectDate.results);
            adapter.setOnClickLintener(this);
            dateRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onError(Throwable e) {
        showSnackbar(getString(R.string.net_error));
    }

    @Override
    public void onClick(View v, int position) {
        showSnackbar(selectDate.results.get(position));
    }

}
