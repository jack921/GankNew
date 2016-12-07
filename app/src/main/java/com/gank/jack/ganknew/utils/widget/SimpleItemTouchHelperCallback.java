package com.gank.jack.ganknew.utils.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.gank.jack.ganknew.adapter.SortAdapter;
import com.gank.jack.ganknew.bean.Sort;
import com.gank.jack.ganknew.interfaces.OnMoveAndSortListener;
import com.gank.jack.ganknew.utils.LogUtil;

import java.util.List;

/**
 * Created by Jack on 2016/11/14.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private OnMoveAndSortListener mAdapter;
    private boolean moveStatus=true;
    private List<Sort> listSort;

    public SimpleItemTouchHelperCallback(OnMoveAndSortListener listener, List<Sort> listSort) {
        this.mAdapter = listener;
        this.listSort=listSort;
    }

    /**
     * 这个方法是用来设置我们拖动的方向以及侧滑的方向的
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags,0);
    }

    /**
     * 当我们拖动item时会回调此方法
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(target.getItemViewType()==1||target.getItemViewType()==2){
            return false;
        }
        boolean choose = false;
        if(target.getItemViewType()!=2){
            if(((SortAdapter.SortItemView)viewHolder).sortCheck.isChecked()!=
                    ((SortAdapter.SortItemView)target).sortCheck.isChecked()){
                Log.e("simpleTest11",listSort.get(viewHolder.getAdapterPosition()).choose+"");
                listSort.get(viewHolder.getAdapterPosition()).choose=
                        !((SortAdapter.SortItemView)viewHolder).sortCheck.isChecked();
                choose=listSort.get(viewHolder.getAdapterPosition()).choose;
                Log.e("simpleTest12",choose+"");
                ((SortAdapter.SortItemView)viewHolder).sortCheck
                        .setChecked(((SortAdapter.SortItemView)target).sortCheck.isChecked());
            }
        }else{
            Log.e("simpleTest21",listSort.get(viewHolder.getAdapterPosition()).choose+"");
            listSort.get(viewHolder.getAdapterPosition()).choose=
                    !((SortAdapter.SortItemView)viewHolder).sortCheck.isChecked();
            choose=listSort.get(viewHolder.getAdapterPosition()).choose;
            Log.e("simpleTest22",listSort.get(viewHolder.getAdapterPosition()).choose+"");
            ((SortAdapter.SortItemView)viewHolder).sortCheck
                    .setChecked(listSort.get(viewHolder.getAdapterPosition()).choose);
        }

        LogUtil.e("simpleTest4",choose+"");
        mAdapter.onItemMove(viewHolder,target,viewHolder.getAdapterPosition(),target.getAdapterPosition(),choose);
        return moveStatus;
    }

    /**
     * 当我们侧滑item时会回调此方法
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction){}
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

}
