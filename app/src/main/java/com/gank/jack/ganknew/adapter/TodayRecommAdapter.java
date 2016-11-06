package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.bean.TodayGank;

import java.util.List;

/**
 * Created by Jack on 2016/11/6.
 */

public class TodayRecommAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Gank> listGank;

    public TodayRecommAdapter(Context context, List<Gank> listGank){
        this.context=context;
        this.listGank=addHeadItem(listGank);
    }

    public List<Gank> addHeadItem(List<Gank> tempListGank){
        String headerStatus="";
        for(int i=0;i<tempListGank.size();i++){
            Gank gank=tempListGank.get(i);
            String header = gank.type;
            if(!gank.type.equals(headerStatus)){
                Gank gankHeader = gank.clone();
                headerStatus = header;
                gankHeader.isHeader = true;
                listGank.add(gankHeader);
            }
            gank.isHeader=false;
            listGank.add(gank);
        }
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
