package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import java.util.List;

/**
 * Created by Jack on 2016/11/30.
 */

public class SelectDateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<String> listDate;
    private OnClickLintener onClickLintener;

    public SelectDateAdapter(Context context, List<String> listDate){
        this.context=context;
        this.listDate=listDate;
    }

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DateItemView(LayoutInflater.from(context).inflate(R.layout.item_selectdate,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DateItemView dateItemView=(DateItemView)holder;
        dateItemView.bindView(listDate.get(position));
    }

    @Override
    public int getItemCount() {
        return listDate.size();
    }

    class DateItemView extends RecyclerView.ViewHolder{
        public TextView selectitem_date;

        public DateItemView(View itemView) {
            super(itemView);
            selectitem_date=(TextView)itemView.findViewById(R.id.selectitem_date);
            if(onClickLintener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickLintener.onClick(view,getAdapterPosition());
                    }
                });
            }
        }

        public void bindView(String date){
            selectitem_date.setText(date);
        }

    }

}
