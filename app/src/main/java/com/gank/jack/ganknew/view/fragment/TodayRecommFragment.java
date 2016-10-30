package com.gank.jack.ganknew.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.theme.Theme;
import com.gank.jack.ganknew.utils.PreUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jack on 2016/10/31.
 */

public class TodayRecommFragment extends Fragment{

    @Bind(R.id.fab)
    public FloatingActionButton fab;
    @Bind(R.id.tool_bar)
    public Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    public BaseActivity baseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recomment,container,false);
        ButterKnife.bind(this,view);

        initView();

        return view;
    }

    public void initView(){
        baseActivity=(BaseActivity)getActivity();
        baseActivity.setSupportActionBar(toolbar);
    }

    @OnClick(R.id.fab)
    public void onClickFad(){
        PreUtils.changeTheme(getActivity(),R.style.BlueTheme, Theme.Blue.toString());
        PreUtils.changeColorImpl(getActivity(),getActivity().getTheme());
        collapsingToolbarLayout.setStatusBarScrimColor(Color.BLUE);
        collapsingToolbarLayout.setContentScrimColor(Color.BLUE);
    }



}
