package com.gank.jack.ganknew.view.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.bean.TodayGank;
import com.gank.jack.ganknew.interfaces.TodayRecommInterface;
import com.gank.jack.ganknew.presenter.TodayRecommPresenter;
import com.gank.jack.ganknew.theme.Theme;
import com.gank.jack.ganknew.utils.ImageLoad;
import com.gank.jack.ganknew.utils.PreUtils;
import com.gank.jack.ganknew.utils.SPUtils;
import com.google.gson.Gson;

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
        TodayRecommInterface{

    @Bind(R.id.fab)
    public FloatingActionButton fab;
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

    public TodayRecommPresenter todayRecommPresenter;
    public  List<Gank> listGank=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recomment,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    public void initView(){
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        appBar.addOnOffsetChangedListener(this);
        todayRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        todaySwipeRefreshLayout.setOnRefreshListener(this);
        todayRecommPresenter=new TodayRecommPresenter(getActivity());
        todayRecommPresenter.getTodayRecommData(this,"2016","11","04");
    }

    @OnClick(R.id.fab)
    public void onClickFad(){
        PreUtils.changeTheme(getActivity(),R.style.BlueTheme, Theme.Blue.toString());
        PreUtils.changeColorImpl(getActivity(),getActivity().getTheme());
        collapsingToolbarLayout.setStatusBarScrimColor(Color.BLUE);
        collapsingToolbarLayout.setContentScrimColor(Color.BLUE);
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

    }

    @Override
    public void getToadyRecomm(List<Gank> listGank){
        if(listGank==null){
            return ;
        }
        this.listGank=listGank;


        if(listGank.get(listGank.size()-1).type.equals("福利")){
            ImageLoad.displayImage(listGank.get(listGank.size()-1).url,todayGankImage);
        }
    }

    //网络出错等情况时
    @Override
    public void onError(Throwable throwable) {
        showToast(getActivity().getString(R.string.net_error));
    }

}
