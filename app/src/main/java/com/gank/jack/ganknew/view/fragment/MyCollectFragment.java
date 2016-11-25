package com.gank.jack.ganknew.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.MyCollectAdapter;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.database.GankSQLiteImpl;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/10/31.
 */

public class MyCollectFragment extends BaseFragment {

    @Bind(R.id.collect_toolbar)
    public Toolbar collectToolbar;
    @Bind(R.id.collect_recyclerview)
    public RecyclerView collectRecyclerview;

    private MyCollectAdapter myCollectAdapter;
    private List<Gank> listGank=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collect,container,false);
        ButterKnife.bind(this,view);

        init();
        initView();
        return view;
    }

    public void init(){
        ((BaseActivity)getActivity()).setSupportActionBar(collectToolbar);
        collectToolbar.setTitle(getString(R.string.mycollect));
        collectRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void initView(){
        listGank= GankSQLiteImpl.getCollectGank();
        if(listGank!=null){
            myCollectAdapter=new MyCollectAdapter(getActivity(),listGank);
            collectRecyclerview.setAdapter(myCollectAdapter);
        }else{
            showSnackbar("text");
        }
    }


}
