package com.gank.jack.ganknew.adapter;

import com.gank.jack.ganknew.interfaces.RefreshInterface;
import android.support.v7.widget.RecyclerView;
import com.gank.jack.ganknew.utils.LogUtil;
import android.view.LayoutInflater;
import com.gank.jack.ganknew.R;
import android.content.Context;
import android.view.ViewGroup;
import android.os.Handler;
import android.view.View;
import java.util.List;

/**
 * Created by Jack on 2016/11/12.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter{
    private RefreshInterface refreshInterface;
    private boolean status=false;
    private final int NORMAL=1;
    private final int FOOTER=2;
    private List<T> listData;
    private Context context;

    public BaseRecyclerViewAdapter(Context context,List<T> ListData){
        this.listData=ListData;
        this.context=context;
    }

    public void setRefreshInterface(RefreshInterface refreshInterface) {
        this.refreshInterface = refreshInterface;
    }

    public abstract RecyclerView.ViewHolder onCreateViewHodler(ViewGroup parent, int viewType);
    public abstract void onBindHolder(RecyclerView.ViewHolder viewHolder, int position, T data);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         if(getItemViewType(position)==NORMAL){
             onBindHolder(holder,position,listData.get(position));
            if((position+1)==listData.size()&&!status){
                //滑到最低
                refreshInterface.loadMore();
            }
         }else if(getItemViewType(position)==FOOTER){}
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==FOOTER){
            return new FooterView(LayoutInflater.from(context).inflate(R.layout.item_loadmore,parent,false));
        }else{
            return onCreateViewHodler(parent,viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(status==true&&(listData.size()==position)){
            return FOOTER;
        }
        return NORMAL;
    }

    @Override
    public int getItemCount() {
        return listData.size()+(status==true?1:0);
    }

    public void startLoadMore(){
        this.status=true;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    public void stopLoadMore(){
        this.status=false;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    class FooterView extends RecyclerView.ViewHolder{
        public FooterView(View itemView) {
            super(itemView);
        }
    }

}
