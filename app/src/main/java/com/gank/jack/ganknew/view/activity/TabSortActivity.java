package com.gank.jack.ganknew.view.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.SortAdapter;
import com.gank.jack.ganknew.api.Config;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.Sort;
import com.gank.jack.ganknew.interfaces.onCheckBoxLintener;
import com.gank.jack.ganknew.interfaces.onStartDragListener;
import com.gank.jack.ganknew.utils.ToastUtil;
import com.gank.jack.ganknew.utils.widget.DividerItemDecoration;
import com.gank.jack.ganknew.utils.widget.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/14.
 */

public class TabSortActivity extends BaseActivity implements onCheckBoxLintener,onStartDragListener {

    @Bind(R.id.sortToolbar)
    public Toolbar toolbar;
    @Bind(R.id.sortRecyclerView)
    public RecyclerView sortRecyclerView;
    @Bind(R.id.test)
    public RelativeLayout test;

    private ItemTouchHelper mItemTouchHelper;
    private SortAdapter sortAdapter;
    List<Sort> listSort=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        setSupportActionBar(toolbar);
        listSort=new ArrayList<>();

        sortRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sortRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        for(int i=0;i< Config.Aategory.length-5;i++){
            listSort.add(new Sort(Config.Aategory[i],false,false,true));
        }
        listSort.add(new Sort("",false,true,false));
        for(int i=Config.Aategory.length-3;i<Config.Aategory.length;i++){
            listSort.add(new Sort(Config.Aategory[i],false,false,true));
        }

        sortAdapter=new SortAdapter(this,listSort,this);
        sortRecyclerView.setAdapter(sortAdapter);
        sortAdapter.addOnCheckBoxLintener(this);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(sortAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(sortRecyclerView);
    }

    @Override
    public void onCheckedChanged(ViewGroup view, boolean isChecked,
                  RecyclerView.ViewHolder sortViewHolder) {
        checkAnimator(view,sortViewHolder);

    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public void checkAnimator(ViewGroup viewGroup,RecyclerView.ViewHolder sortViewHolder){

    }

}
