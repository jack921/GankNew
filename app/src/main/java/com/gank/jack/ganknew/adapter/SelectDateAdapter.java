package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gank.jack.ganknew.R;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/30.
 */

public class SelectDateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<String> listDate;

    private SelectDateAdapter(Context context,List<String> listDate){
        this.context=context;
        this.listDate=listDate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DateItemView(LayoutInflater.from(context).inflate(R.layout.item_selectdate,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DateItemView dateItemView=(DateItemView)holder;
        dateItemView.selectitem_date.setText(listDate.get(position));
    }

    @Override
    public int getItemCount() {
        return listDate.size();
    }

    class DateItemView extends RecyclerView.ViewHolder{

        @Bind(R.id.selectitem_date)
        private TextView selectitem_date;

        public DateItemView(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        public void bindView(String date){
            selectitem_date.setText(date);
        }

    }

}
