package com.gank.jack.ganknew.view.activity;

import com.gank.jack.ganknew.utils.widget.SimpleItemTouchHelperCallback;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.CompoundButton;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.SortAdapter;
import com.gank.jack.ganknew.api.Config;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.Sort;
import com.gank.jack.ganknew.interfaces.onCheckBoxLintener;
import com.gank.jack.ganknew.interfaces.onStartDragListener;
import com.gank.jack.ganknew.utils.PreUtils;
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
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        sortRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//       sortRecyclerView.addItemDecoration(new DividerItemDecoration(
//                                  this,DividerItemDecoration.VERTICAL_LIST));

        listSort=new ArrayList<>();
        String tabData=PreUtils.getString(this,"TabMenu","");
        //Sort(String title, boolean classify,boolean more, boolean normal,boolean choose)
        if(!tabData.equals("")){
            listSort.add(new Sort("",false,true,false,false));
            for(int i=0;i< Config.Aategory.length;i++){
                listSort.add(new Sort(Config.Aategory[i],false,false,true,false));
            }
        }else{
            listSort.add(new Sort("",true,false,false,false));
            for(int i=0;i< Config.Aategory.length-3;i++){
                listSort.add(new Sort(Config.Aategory[i],false,false,true,true));
            }
            listSort.add(new Sort("",false,true,false,false));
            for(int i=Config.Aategory.length-3;i< Config.Aategory.length;i++){
                listSort.add(new Sort(Config.Aategory[i],false,false,true,false));
            }
        }

        sortAdapter=new SortAdapter(this,listSort,this);
        sortRecyclerView.setAdapter(sortAdapter);
        sortAdapter.addOnCheckBoxLintener(this);
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(sortAdapter,listSort);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(sortRecyclerView);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView,int position) {
//            if(listSort.get(position).choose==true){
//                listSort.get(position).choose=false;
//                buttonView.setChecked(false);
//            }else{
//                buttonView.setChecked(true);
//            }
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }


}
