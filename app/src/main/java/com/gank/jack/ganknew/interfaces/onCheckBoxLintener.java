package com.gank.jack.ganknew.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Jack on 2016/11/15.
 */

public interface onCheckBoxLintener {
    public void onCheckedChanged(ViewGroup parent, boolean isChecked,
            RecyclerView.ViewHolder SortViewHolder);
}
