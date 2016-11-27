package com.gank.jack.ganknew.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.MyCollectAdapter;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.base.BaseFragment;
import com.gank.jack.ganknew.bean.CollectGank;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.database.GankSQLiteImpl;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.presenter.MyCollectPersenter;
import com.gank.jack.ganknew.utils.widget.DividerItemDecoration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/10/31.
 */

public class MyCollectFragment extends BaseFragment implements OnClickLintener {

    @Bind(R.id.collect_toolbar)
    public Toolbar collectToolbar;
    @Bind(R.id.collect_recyclerview)
    public RecyclerView collectRecyclerview;
    @Bind(R.id.no_collect_data_tip)
    public LinearLayout noCollectDataTip;
    private MyCollectPersenter myCollectPersenter;
    private MyCollectAdapter myCollectAdapter;
    private List<Gank> listGank=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collect,container,false);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this,view);

        init();
        initView();
        return view;
    }

    public void init(){
        ((BaseActivity)getActivity()).setSupportActionBar(collectToolbar);
        collectToolbar.setTitle(getString(R.string.mycollect));
        collectRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        collectRecyclerview.addItemDecoration(new DividerItemDecoration(
                getActivity(),DividerItemDecoration.VERTICAL_LIST));
        myCollectPersenter=new MyCollectPersenter(getActivity());
    }

    public void initView(){
        listGank= GankSQLiteImpl.getCollectGank();
        myCollectAdapter=new MyCollectAdapter(getActivity(),listGank);
        myCollectAdapter.setOnClickLintener(this);
        myCollectPersenter.initView(listGank,myCollectAdapter,
                collectRecyclerview,noCollectDataTip);
    }

    @Override
    public void onClick(View v, int position) {
        myCollectPersenter.onItemOnClick(listGank.get(position));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollectData(CollectGank collectGank){
        myCollectPersenter.updateCollectData(listGank,
                myCollectAdapter,collectRecyclerview,noCollectDataTip,collectGank);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
