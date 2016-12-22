package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.bean.ThemeModel;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import java.util.List;

/**
 * Created by Jack on 2016/12/18.
 */

public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<ThemeModel> listTheme;
    private OnClickLintener onClickLintener;

    public ThemeAdapter(Context context,List<ThemeModel> listTheme){
        this.context=context;
        this.listTheme=listTheme;
    }

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemeItem(LayoutInflater.from(context).inflate(R.layout.act_theme_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ThemeItem themeItem=(ThemeItem)holder;
        GradientDrawable myGrad = (GradientDrawable)themeItem.view.getBackground();
        myGrad.setColor(context.getResources().getColor(listTheme.get(position).color));
        if(listTheme.get(position).focus){
            themeItem.themeFocus.setVisibility(View.VISIBLE);
        }else{
            themeItem.themeFocus.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listTheme.size();
    }

    class ThemeItem extends RecyclerView.ViewHolder{
        public View view;
        public ImageView themeFocus;

        public ThemeItem(View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.theme_item);
            themeFocus=(ImageView)itemView.findViewById(R.id.theme_item_focus);
            if(onClickLintener!=null){
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickLintener.onClick(view,getAdapterPosition());
                    }
                });
            }
        }

    }

}
