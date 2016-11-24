package com.gank.jack.ganknew.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.ClassifyTabAdapter;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.interfaces.ClassifyTabInterface;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.interfaces.RefreshInterface;
import com.gank.jack.ganknew.presenter.ClassifyTabPersenter;
import com.gank.jack.ganknew.view.activity.WebContentActivity;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/10.
 */

public class ClassifyTabFragment extends BaseFragment implements ClassifyTabInterface, RefreshInterface, SwipeRefreshLayout.OnRefreshListener, OnClickLintener {

    @Bind(R.id.classifgtab_recyclerview)
    public RecyclerView tabRecylerView;
    @Bind(R.id.classTabSwipe)
    public SwipeRefreshLayout swipeRefreshLayout;

    private ClassifyTabPersenter classifyTabPersenter;
    private ClassifyTabAdapter classifyTabAdapter;
    private String tabTitle;
    private int page=1;
    private List<Gank> tabListGank;
    private boolean isLast=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classifgtab,container,false);
        ButterKnife.bind(this,view);
        tabTitle=getArguments().getString("TabTitle");
        init();
        return view;
    }

    public void init(){
        tabListGank=new ArrayList<>();
        tabRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        classifyTabPersenter=new ClassifyTabPersenter(getActivity());
        classifyTabPersenter.getClassifyTabData(this,tabTitle,page);
        classifyTabAdapter=new ClassifyTabAdapter(getActivity(),tabListGank);
        tabRecylerView.setAdapter(classifyTabAdapter);
        classifyTabAdapter.setRefreshInterface(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        classifyTabAdapter.addOnClickLintener(this);
    }

    @Override
    public void getTypeGank(List<Gank> listGank, boolean isLast) {
        this.isLast=isLast;
        tabListGank.addAll(listGank);
        classifyTabAdapter.stopLoadMore();
        swipeRefreshLayout.setRefreshing(false);
        page++;
    }

    @Override
    public void error(Throwable throwable) {
        showToast(getActivity().getString(R.string.net_error));
    }

    @Override
    public void loadMore() {
        if(!isLast){
            classifyTabAdapter.startLoadMore();
            classifyTabPersenter.getClassifyTabData(this,tabTitle,page);
        }
    }

    @Override
    public void onRefresh() {
        page=1;
        tabListGank.clear();
        classifyTabPersenter.getClassifyTabData(this,tabTitle,page);
    }

    @Override
    public void onClick(View v, int position) {
        Intent intent=new Intent(getActivity(),WebContentActivity.class);
        intent.putExtra("gank",tabListGank.get(position));
        startActivity(intent);
    }

}
