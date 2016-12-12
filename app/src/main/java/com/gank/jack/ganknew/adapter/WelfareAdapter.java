package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.utils.ImageLoad;
import com.gank.jack.ganknew.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2016/11/12.
 */

public class WelfareAdapter extends BaseRecyclerViewAdapter<Gank>{
    private List<Gank> listGank;
    private List<Integer> listHeight;
    private Context context;
    private OnClickLintener onClickLintener;

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }

    public WelfareAdapter(Context context, List<Gank> ListData) {
        super(context, ListData);
        this.context=context;
        listHeight=new ArrayList<>();
        this.listGank=ListData;
        for(int i=0;i<listGank.size();i++){
            listHeight.add((int)(600+Math.random()*150));
        }
    }

    public void addListData(List<Gank> listGank){
        for(int i=0;i<listGank.size();i++){
            this.listGank.add(listGank.get(i));
            listHeight.add((int)(600+Math.random()*150));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHodler(ViewGroup parent, int viewType) {
        return new WelfareView(LayoutInflater.from(context).inflate(R.layout.item_welfare_view,null));
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder viewHolder, int position, Gank data) {
        WelfareView welfareView=(WelfareView)viewHolder;
        welfareView.bindView(listGank.get(position),position);
    }

    public class WelfareView extends RecyclerView.ViewHolder{
        public ImageView welfareItem;
        public TextView welfareDate;
        public WelfareView(View itemView) {
            super(itemView);
            welfareItem=(ImageView)itemView.findViewById(R.id.welfare_item);
            welfareDate=(TextView)itemView.findViewById(R.id.welfare_date);
            if(onClickLintener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickLintener.onClick(welfareItem,getAdapterPosition());
                    }
                });
            }
        }
        public void bindView(Gank gank,int position){
            welfareDate.setText(gank.desc);
            ViewGroup.LayoutParams lp = welfareItem.getLayoutParams();
            lp.height = listHeight.get(position);
            welfareItem.setLayoutParams(lp);
            ImageLoad.displayImage(
                    gank.url+"?ImageView2/0/w/"+(ScreenUtils.getScreenWidthDp(context)/2-350),
                    welfareItem);
        }
    }

}
