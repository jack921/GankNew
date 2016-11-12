package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.utils.ImageLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2016/11/12.
 */

public class WelfareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Gank> listGank;
    private List<Integer> listHeight;
    private Context context;

    public WelfareAdapter(Context context,List<Gank> listGank){
        listHeight=new ArrayList<>();
        this.listGank=listGank;
        this.context=context;
        for(int i=0;i<listGank.size();i++){
            listHeight.add((int)(500+Math.random()*300));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WelfareView(LayoutInflater.from(context).inflate(R.layout.item_welfare_view,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WelfareView welfareView=(WelfareView)holder;
        welfareView.bindView(listGank.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listGank.size();
    }

    class WelfareView extends RecyclerView.ViewHolder{
        private ImageView welfareItem;
        public WelfareView(View itemView) {
            super(itemView);
            welfareItem=(ImageView)itemView.findViewById(R.id.welfare_item);
        }
        public void bindView(Gank gank,int position){
            ViewGroup.LayoutParams lp = welfareItem.getLayoutParams();
            lp.height = listHeight.get(position);
            welfareItem.setLayoutParams(lp);

            ImageLoad.displayImage(gank.url,welfareItem);
        }
    }

}
