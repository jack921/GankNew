package com.gank.jack.ganknew.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.TodayRecommAdapter;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.bean.DateResult;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.bean.SelectDate;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.interfaces.TodayRecommInterface;
import com.gank.jack.ganknew.presenter.TodayRecommPresenter;
import com.gank.jack.ganknew.view.activity.WebContentActivity;
import com.nightonke.boommenu.BoomMenuButton;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jack on 2016/10/31.
 */

public class TodayRecommFragment extends BaseFragment implements
        AppBarLayout.OnOffsetChangedListener,
        SwipeRefreshLayout.OnRefreshListener,
        TodayRecommInterface, OnClickLintener{

    @Bind(R.id.tool_bar)
    public Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.app_bar)
    public AppBarLayout appBar;
    @Bind(R.id.todaySwipeRefreshLayout)
    public SwipeRefreshLayout todaySwipeRefreshLayout;
    @Bind(R.id.today_recyclerview)
    public RecyclerView todayRecyclerview;
    @Bind(R.id.todayGankImage)
    public ImageView todayGankImage;
    @Bind(R.id.today_fab)
    public BoomMenuButton boomMenuButton;

    public TodayRecommPresenter todayRecommPresenter;
    public TodayRecommAdapter todayRecommAdapter;
    public  List<Gank> listGank=null;
    public DateResult dateResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recomment,container,false);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this,view);
        initView();
        initBoomMenuButton();
        return view;
    }

    public void initBoomMenuButton(){
        todayRecommPresenter.initBoomMenu(getActivity(),boomMenuButton);
    }

    public void initView(){
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        appBar.addOnOffsetChangedListener(this);
        todayRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        todaySwipeRefreshLayout.setOnRefreshListener(this);
        todayRecommPresenter=new TodayRecommPresenter(getActivity());
        dateResult=todayRecommPresenter.getTodayRecomm();
        todayRecommPresenter.getTodayRecommData(this,dateResult.year,dateResult.month,dateResult.day);
        listGank=new ArrayList<>();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset>= 0) {
            todaySwipeRefreshLayout.setEnabled(true);
        } else {
            todaySwipeRefreshLayout.setEnabled(false);
        }
    }

    @Override
    public void onRefresh() {
        todayRecommPresenter.getTodayRecommData(this,dateResult.year,dateResult.month,dateResult.day);
    }

    @Override
    public void getToadyRecomm(List<Gank> listGanks){
        initBoomMenuButton();
        if(todayRecommPresenter.initData(this,this.listGank,listGanks,
               todayRecommPresenter,todayGankImage,collapsingToolbarLayout,dateResult)){
           return;
        }
        this.listGank=todayRecommPresenter.addHeadItem(this.listGank);
        todayRecommAdapter=new TodayRecommAdapter(getActivity(),this.listGank);
        todayRecommAdapter.setOnClickLintener(this);
        todayRecyclerview.setAdapter(todayRecommAdapter);
        todaySwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getSelectDate(SelectDate selectDate) {
        dateResult=todayRecommPresenter.formatStringDate(selectDate.results.get(0));
        todayRecommPresenter.getTodayRecommData(this,
                dateResult.year,dateResult.month,dateResult.day);
    }

    //网络出错等情况时
    @Override
    public void onError(Throwable throwable) {
        showToast(getActivity().getString(R.string.net_error));
    }

    @Override
    public void onClick(View v, int position) {
        Intent intent=new Intent(getActivity(),WebContentActivity.class);
        intent.putExtra("collectTag",false);
        intent.putExtra("gank",listGank.get(position));
        startNewActivityByIntent(intent);
    }

    @OnClick(R.id.today_fab)
    public void viewOnClick(View v){
        switch(v.getId()){
            case R.id.today_fab:
//                PreUtils.changeTheme(getActivity(),R.style.BlueTheme, Theme.Blue.toString());
//                PreUtils.changeColorImpl(getActivity(),getActivity().getTheme());
//                collapsingToolbarLayout.setStatusBarScrimColor(Color.BLUE);
//                collapsingToolbarLayout.setContentScrimColor(Color.BLUE);
//                startActivity(new Intent(getActivity(), SelectDateActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdateTodayData(DateResult dateResult){
        if(dateResult.day!=null){
            todayRecommPresenter.getTodayRecommData(this,dateResult.year,dateResult.month,dateResult.day);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
