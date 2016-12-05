package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.bean.Search;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import java.util.List;

/**
 * Created by Jack on 2016/12/6.
 */

public class SearchAdapter extends BaseRecyclerViewAdapter<Search>{

    private Context context;
    private List<Search> listSearch;
    private OnClickLintener onClickLintener;

    public SearchAdapter(Context context,List<Search> listSearch){
        super(context,listSearch);
        this.context=context;
        this.listSearch=listSearch;
    }

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHodler(ViewGroup parent, int viewType) {
        return new SearchItemView(LayoutInflater.from(context).inflate(R.layout.item_today_main,parent,false));
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder viewHolder, int position, Search data) {
        SearchItemView searchItemView=(SearchItemView)viewHolder;
        searchItemView.bindItem(listSearch.get(position));
    }

    class SearchItemView extends RecyclerView.ViewHolder{
        public TextView itemText;
        public SearchItemView(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.main_item_content);
            if (onClickLintener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickLintener.onClick(view, getAdapterPosition());
                    }
                });
            }
        }

        public void bindItem(final Search search) {
            itemText.setText(search.desc+"(by "+search.who+" "+parseTime(search.publishedAt)+")");
        }
    }

    public String parseTime(String time){
        int index=time.indexOf("T");
        return time.substring(0,index);
    }


}
