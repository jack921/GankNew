package com.gank.jack.ganknew.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.ClassifyTabFragmentAdapter;
import com.gank.jack.ganknew.api.Config;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.bean.Sort;
import com.gank.jack.ganknew.utils.SPUtils;
import com.gank.jack.ganknew.view.activity.MainActivity;
import com.gank.jack.ganknew.view.activity.TabSortActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/10/31.
 */

public class AllClassifyFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    @Bind(R.id.classify_tablayout)
    public TabLayout tabLayout;
    @Bind(R.id.classify_toolbar)
    public Toolbar toolbar;
    @Bind(R.id.classify_viewpager)
    public ViewPager viewpager;
    @Bind(R.id.classify_barlayout)
    public AppBarLayout classifyBarlayout;
    @Bind(R.id.statusbar)
    public View statusbar;
    @Bind(R.id.sort_tab)
    public RelativeLayout sortTab;
    private String[] tabTitle;
    private List<Fragment> listFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classify,container,false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this,view);
        init();
        initView();
        return view;
    }

    public void init(){
        tabTitle= Config.Aategory;
        listFragment=new ArrayList<>();
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        toolbar.setTitle(getString(R.string.type_browse));
        sortTab.setOnClickListener(this);
    }

    public void initView(){
        String tabData= SPUtils.getString(getActivity(),"TabMenu","TabGson");
        List<Sort> tempListSort=new Gson().fromJson(tabData,new TypeToken<List<Sort>>(){}.getType());
        for(int i=0;i<tempListSort.size();i++){
            if(tempListSort.get(i).normal==true&&tempListSort.get(i).choose==true){
                tabLayout.addTab(tabLayout.newTab().setText(tempListSort.get(i).title));
                Bundle bundle=new Bundle();
                bundle.putString("TabTitle",tempListSort.get(i).title);
                ClassifyTabFragment classifyTabFragment=new ClassifyTabFragment();
                classifyTabFragment.setArguments(bundle);
                listFragment.add(classifyTabFragment);
            }
        }
        ClassifyTabFragmentAdapter adapter = new ClassifyTabFragmentAdapter(
                getActivity().getSupportFragmentManager(),listFragment,tabTitle);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        classifyBarlayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(verticalOffset==0){
            statusbar.setVisibility(View.GONE);
        }else{
            statusbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sort_tab:
                startNewActivityByIntent(new Intent(getActivity(),TabSortActivity.class));
             break;
        }
    }

}
