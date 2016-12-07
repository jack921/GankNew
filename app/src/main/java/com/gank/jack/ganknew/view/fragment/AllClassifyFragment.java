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
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.ClassifyTabFragmentAdapter;
import com.gank.jack.ganknew.api.Config;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.bean.Sort;
import com.gank.jack.ganknew.interfaces.AllClassifyInterface;
import com.gank.jack.ganknew.presenter.AllClassifyPersenter;
import com.gank.jack.ganknew.utils.SPUtils;
import com.gank.jack.ganknew.view.activity.MainActivity;
import com.gank.jack.ganknew.view.activity.TabSortActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/10/31.
 */

public class AllClassifyFragment extends BaseFragment
        implements View.OnClickListener,AllClassifyInterface {

    @Bind(R.id.classify_tablayout)
    public TabLayout tabLayout;
    @Bind(R.id.classify_toolbar)
    public Toolbar toolbar;
    @Bind(R.id.classify_viewpager)
    public ViewPager viewpager;
    @Bind(R.id.classify_barlayout)
    public AppBarLayout classifyBarlayout;
    @Bind(R.id.sort_tab)
    public RelativeLayout sortTab;

    private String[] tabTitle;
    private List<Fragment> listFragment;
    private ClassifyTabFragmentAdapter adapter;
    private AllClassifyPersenter allClassifyPersenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classify,container,false);
        EventBus.getDefault().register(this);
        setHasOptionsMenu(true);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    public void init(){
        tabTitle= Config.Aategory;
        allClassifyPersenter=new AllClassifyPersenter(getActivity());
        listFragment=new ArrayList<>();
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        toolbar.setTitle(getString(R.string.type_browse));
        sortTab.setOnClickListener(this);

        String tabData= SPUtils.getString(getActivity(),"TabMenu","TabGson");
        if(tabData!=null){
            allClassifyPersenter.initAllTabData(this,tabData,tabLayout,listFragment);
        }else{
            allClassifyPersenter.initNotAllTabData(this,tabLayout,listFragment);
        }
    }

    @Override
    public void initViewData(TabLayout tabLayout, List<Fragment> listFragment,List<String> listTitle) {
        adapter = new ClassifyTabFragmentAdapter(getActivity()
                .getSupportFragmentManager(),listFragment,listTitle);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sort_tab:
                startNewActivityByIntent(new Intent(getActivity(),TabSortActivity.class));
             break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateTab(List<Sort> listSort){
        allClassifyPersenter.updatTabClassify(this,listSort,tabLayout,listFragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
