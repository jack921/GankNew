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

import java.util.List;

/**
 * @author 谢汉杰
 */

public class MyCollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Gank> listGank;
    private OnClickLintener onClickLintener;

    public MyCollectAdapter(Context context,List<Gank> listGank){
        this.context=context;
        this.listGank=listGank;
    }

    public void setOnClickLintener(OnClickLintener onClickLintener){
        this.onClickLintener=onClickLintener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectItem(LayoutInflater.from(context).inflate(R.layout.item_collect_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          CollectItem collectItem=(CollectItem)holder;
          collectItem.bindView(listGank.get(position));
    }

    @Override
    public int getItemCount() {
        return listGank.size();
    }

    class CollectItem extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView name;
//      private ImageView icon;

        public CollectItem(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.collectitem_title);
            name=(TextView)itemView.findViewById(R.id.collectitem_name);
//          icon=(ImageView)itemView.findViewById(R.id.collectitem_img);
            if(onClickLintener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickLintener.onClick(v,getAdapterPosition());
                    }
                });
            }
        }

        public void bindView(Gank gank){
            if(gank==null){
                return ;
            }
            title.setText(gank.desc);
            name.setText(gank.who);
        }

    }

}
