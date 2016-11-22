package com.gank.jack.ganknew.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jack on 2016/11/14.
 */

public interface onMoveAndSortListener {
    boolean onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target
            ,int fromPosition , int toPosition);
}
