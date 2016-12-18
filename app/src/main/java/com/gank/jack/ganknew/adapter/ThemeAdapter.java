package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.utils.LogUtil;

/**
 * Created by Jack on 2016/12/18.
 */

public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;

    public ThemeAdapter(Context context){
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.e("viewType",viewType+"");
        return new ThemeItem(LayoutInflater.from(context).inflate(R.layout.act_theme_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ThemeItem themeItem=(ThemeItem)holder;
        GradientDrawable myGrad = (GradientDrawable)themeItem.view.getBackground();
        myGrad.setColor(context.getResources().getColor(R.color.black ));
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ThemeItem extends RecyclerView.ViewHolder{
        public View view;

        public ThemeItem(View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.theme_item);
        }

    }

}
