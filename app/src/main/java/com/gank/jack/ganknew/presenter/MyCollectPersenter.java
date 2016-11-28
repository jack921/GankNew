package com.gank.jack.ganknew.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.gank.jack.ganknew.adapter.MyCollectAdapter;
import com.gank.jack.ganknew.bean.CollectGank;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.view.activity.WebContentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2016/11/28.
 */

public class MyCollectPersenter extends BasePresenter{

    public MyCollectPersenter(Context context) {
        super(context);
    }

    public void initView(List<Gank> listGank,MyCollectAdapter myCollectAdapter,
                         RecyclerView collectRecyclerview,LinearLayout noCollectDataTip){
        myCollectAdapter=new MyCollectAdapter(context,listGank);
        collectRecyclerview.setAdapter(myCollectAdapter);
        if(listGank.size()==0){
            noCollectDataTip.setVisibility(View.VISIBLE);
            collectRecyclerview.setVisibility(View.GONE);
        }
    }

    public void onItemOnClick(Gank gank){
        Intent intent=new Intent(context,WebContentActivity.class);
        intent.putExtra("collectTag",true);
        intent.putExtra("gank",gank);
        context.startActivity(intent);
    }

    public void updateCollectData(List<Gank> listGank,MyCollectAdapter myCollectAdapter,
            RecyclerView collectRecyclerview,LinearLayout noCollectDataTip,CollectGank collectGank){
        if(noCollectDataTip.getVisibility()==View.VISIBLE){
            noCollectDataTip.setVisibility(View.GONE);
            collectRecyclerview.setVisibility(View.VISIBLE);
        }
        if(collectGank.action==true){
            listGank.add(collectGank.gank);
        }else{
            for(int i=0;i<listGank.size();i++){
                if(listGank.get(i)._id.equals(collectGank.gank._id)){
                    listGank.remove(i);
                    break;
                }
            }
        }
        myCollectAdapter.notifyDataSetChanged();
    }

}
