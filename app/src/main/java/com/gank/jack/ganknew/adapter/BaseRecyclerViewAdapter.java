package com.gank.jack.ganknew.adapter;

import android.support.v7.widget.StaggeredGridLayoutManager;
import com.gank.jack.ganknew.interfaces.RefreshInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    //处理StaggeredGridLayout
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        StaggeredGridLayoutManager.LayoutParams p=(StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        if(getItemViewType(position)==FOOTER){
            p.setFullSpan(true);
        }
    }

    //处理GridLayout
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(getItemViewType(position)==FOOTER){
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
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
                notifyItemInserted(listData.size()+(status==true?1:0)-1);
            }
        });
    }

    public void stopLoadMore(){
        this.status=false;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                notifyItemInserted(listData.size()+(status==true?1:0)-1);
            }
        });
    }

    class FooterView extends RecyclerView.ViewHolder{
        public FooterView(View itemView) {
            super(itemView);
        }
    }

}
