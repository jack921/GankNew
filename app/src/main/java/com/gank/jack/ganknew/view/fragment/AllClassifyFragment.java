package com.gank.jack.ganknew.view.fragment;

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
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.ClassifyTabFragmentAdapter;
import com.gank.jack.ganknew.api.Config;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/10/31.
 */

public class AllClassifyFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener {

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
    private String[] tabTitle;
    private List<Fragment> listFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classify,container,false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this,view);

        init();
        return view;
    }

    public void init(){
        tabTitle= Config.Aategory;
        listFragment=new ArrayList<>();
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        toolbar.setTitle(getString(R.string.type_browse));
        for(int i=0;i<tabTitle.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabTitle[i]));
            Bundle bundle=new Bundle();
            bundle.putString("TabTitle",tabTitle[i]);
            ClassifyTabFragment classifyTabFragment=new ClassifyTabFragment();
            classifyTabFragment.setArguments(bundle);
            listFragment.add(classifyTabFragment);
        }
        ClassifyTabFragmentAdapter adapter = new ClassifyTabFragmentAdapter(
                getActivity().getSupportFragmentManager(),listFragment,tabTitle);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        classifyBarlayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.classify_tab,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_sort:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(verticalOffset==0){
            statusbar.setVisibility(View.GONE);
        }else{
            statusbar.setVisibility(View.VISIBLE);
        }
    }

}
