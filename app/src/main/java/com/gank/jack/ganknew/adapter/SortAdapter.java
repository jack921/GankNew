package com.gank.jack.ganknew.adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.bean.Sort;
import com.gank.jack.ganknew.interfaces.onCheckBoxLintener;
import com.gank.jack.ganknew.interfaces.onMoveAndSortListener;
import com.gank.jack.ganknew.interfaces.onStartDragListener;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jack on 2016/11/14.
 */

public class SortAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements onMoveAndSortListener {
    private Context context;
    private List<Sort> listSort;
    public onCheckBoxLintener onCheckBoxLintener;
    public onStartDragListener dragListener;
    public  RecyclerView recyclerView;

    public SortAdapter(Context context, List<Sort> listSort,onStartDragListener listener){
        this.context=context;
        this.listSort=listSort;
        this.dragListener = listener;
    }

    public void addOnCheckBoxLintener(onCheckBoxLintener onCheckBoxLintener) {
        this.onCheckBoxLintener = onCheckBoxLintener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        recyclerView=((RecyclerView)parent);
        if(viewType==1||viewType==2){
            return new HeaderView(LayoutInflater.from(context).inflate(R.layout.item_sort_header,parent,false));
        }else{
            View sortView=LayoutInflater.from(context).inflate(R.layout.item_sort_view,parent,false);
            CheckBox sortCheck=(CheckBox)sortView.findViewById(R.id.sort_check);
            final SortItemView sortItemView=new SortItemView(sortView);
            return sortItemView;
        }
    }

    public void moveToMore(SortItemView viewHolder,int currentPosition){
        int position = processItemRemoveAdd(currentPosition);
        notifyItemMoved(currentPosition,getItemCount()-1);
    }

    public int processItemRemoveAdd(int currentPosition){
        Sort sort=listSort.get(currentPosition);
        listSort.remove(currentPosition);
        listSort.add(sort);
        return 1;
    }

    PathMeasure mPathMeasure=null;
    final float[] mCurrentPosition = new float[2];

    /**
     * 开始增删动画
     */
    private void startAnimation(RecyclerView recyclerView, final View currentView, final int currentPosition,
                                float targetX, float targetY) {
        currentView.bringToFront();
        Path path = new Path();
        path.moveTo(currentView.getLeft(), currentView.getTop());
        path.lineTo(targetX, targetY);
        mPathMeasure = new PathMeasure(path, false);
        //属性动画实现
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,targetY);
        valueAnimator.setDuration(5000);
        // 匀速插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                currentView.setTranslationX(mCurrentPosition[0]);
                currentView.setTranslationY(mCurrentPosition[1]);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}
            @Override
            public void onAnimationEnd(Animator animation) {
                //默认recyclerviewe的动画
//                notifyItemMoved(currentPosition,getItemCount()-1);
            }
            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        valueAnimator.start();
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
           if(getItemViewType(position)==1){
               HeaderView headerView=(HeaderView)holder;
               headerView.sortItem.setText("分类");
           }else if(getItemViewType(position)==2){
               HeaderView headerView=(HeaderView)holder;
               headerView.sortItem.setText("更多");
           }else{
               final SortItemView sortItemView=(SortItemView)holder;
               sortItemView.sortName.setText(listSort.get(position).title);
               if(dragListener!=null){
                   sortItemView.sortMenu.setOnTouchListener(new View.OnTouchListener() {
                       @Override
                       public boolean onTouch(View v, MotionEvent event) {
                           if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                               dragListener.startDrag(holder);
                           }
                           return false;
                       }
                   });
               }
               if(onCheckBoxLintener!=null){
                   sortItemView.sortCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                       @Override
                       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                           RecyclerView.LayoutManager manager=recyclerView.getLayoutManager();
                           int targetX;
                           int targetY;
                           int prePosition=getItemCount();
                           View preView=manager.findViewByPosition(prePosition-1);
                           targetX=preView.getLeft();
                           targetY=preView.getTop()+preView.getHeight();
                           startAnimation(recyclerView,holder.itemView,position,targetX,targetY);
                       }
                   });
               }


           }
    }

    @Override
    public int getItemCount() {
        return listSort.size();
    }

    @Override
    public int getItemViewType(int position) {
       if(listSort.get(position).classify==true){
            return 1;
        }else if(listSort.get(position).more==true){
            return 2;
        }else{
            return 3;
       }
    }

    class SortItemView extends RecyclerView.ViewHolder{
        public CheckBox sortCheck;
        public TextView sortName;
        public RelativeLayout sortMenu;
        public SortItemView(final View itemView) {
            super(itemView);
            sortCheck=(CheckBox)itemView.findViewById(R.id.sort_check);
            sortName=(TextView)itemView.findViewById(R.id.sort_name);
            sortMenu=(RelativeLayout)itemView.findViewById(R.id.sort_menu);
//            if(onCheckBoxLintener!=null){
//                sortCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        onCheckBoxLintener.onCheckedChanged(itemView,isChecked,getAdapterPosition());
//                    }
//                });
//            }
        }
    }

    class HeaderView extends RecyclerView.ViewHolder{
        public TextView sortItem;
        public HeaderView(View itemView) {
            super(itemView);
            sortItem=(TextView)itemView.findViewById(R.id.sort_header_item);
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(listSort, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

}
