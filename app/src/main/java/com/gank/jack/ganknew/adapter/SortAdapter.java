package com.gank.jack.ganknew.adapter;

import com.gank.jack.ganknew.interfaces.OnMoveAndSortListener;
import com.gank.jack.ganknew.interfaces.OnStartDragListener;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.gank.jack.ganknew.bean.Sort;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.content.Context;
import android.widget.TextView;
import com.gank.jack.ganknew.R;
import java.util.List;

/**
 * Created by Jack on 2016/11/14.
 */

public class SortAdapter extends RecyclerView.Adapter
        <RecyclerView.ViewHolder> implements OnMoveAndSortListener {

    private Context context;
    private List<Sort> listSort;
    public OnStartDragListener dragListener;
    public RecyclerView recyclerView;
    boolean deleteMore=false;
    boolean deleteClassify=false;

    public SortAdapter(Context context, List<Sort> listSort,OnStartDragListener listener){
        this.context=context;
        this.listSort=listSort;
        this.dragListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        recyclerView=((RecyclerView)parent);
        if(viewType==1||viewType==2){
            return new HeaderView(LayoutInflater.from(context).inflate(R.layout.item_sort_header,parent,false));
        }else{
            return new SortItemView(LayoutInflater.from(context).inflate(R.layout.item_sort_view,parent,false));
        }
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
               sortItemView.bindSortItemView(sortItemView,listSort.get(position));
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

    @Override
    public boolean onItemMove(RecyclerView.ViewHolder viewHolder,
              RecyclerView.ViewHolder target, int fromPosition, final int toPosition){
        Sort item = listSort.get(fromPosition);
        listSort.remove(fromPosition);
        listSort.add(toPosition ,item);
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    public class SortItemView extends RecyclerView.ViewHolder{
        public CheckBox sortCheck;
        public TextView sortName;
        public RelativeLayout sortMenu;

        public SortItemView(final View itemView) {
            super(itemView);
            sortCheck=(CheckBox)itemView.findViewById(R.id.sort_check);
            sortName=(TextView)itemView.findViewById(R.id.sort_name);
            sortMenu=(RelativeLayout)itemView.findViewById(R.id.sort_menu);
        }

        public void bindSortItemView(final SortItemView sortItemView,Sort sort){
            if(sort.choose==false){
                sortItemView.sortCheck.setChecked(false);
            }else{
                sortItemView.sortCheck.setChecked(true);
            }
            if(dragListener!=null){
                sortMenu.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                            dragListener.startDrag(sortItemView);
                        }
                        return false;
                    }
                });
            }
            sortCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    moveToMore(getAdapterPosition());
                }
            });
        }

    }

    public void moveToMore(int currentPosition){
        Sort sort=listSort.get(currentPosition);
        listSort.remove(currentPosition);
        if(sort.choose==true){
            if(deleteMore==true){
                deleteMore=false;
                listSort.add(new Sort("",false,true,false,false));
                notifyItemInserted(getItemCount()-1);
            }

            sort.choose=false;
            listSort.add(sort);
            notifyItemMoved(currentPosition,getItemCount()-1);

            if(listSort.get(0).classify==true&&listSort.get(1).more==true){
                deleteClassify=true;
                listSort.remove(0);
                notifyItemRemoved(0);
            }
        }else{
            int more = 0;
            for(int i=0;i<listSort.size();i++){
                if(listSort.get(i).more==true){
                    more=i;
                    break;
                }
            }
            sort.choose=true;
            listSort.add(more,sort);
            if(more==getItemCount()-2){
                deleteMore=true;
                notifyItemMoved(currentPosition,more);
                listSort.remove(getItemCount()-1);
                notifyItemRemoved(getItemCount()-1);
            }else{
                notifyItemMoved(currentPosition,more);
            }
            if(deleteClassify==true||listSort.get(0).classify!=true){
                deleteClassify=false;
                listSort.add(0,new Sort("",true,false,false,false));
                notifyItemInserted(0);
            }
        }

    }

    class HeaderView extends RecyclerView.ViewHolder{
        public TextView sortItem;
        public HeaderView(View itemView) {
            super(itemView);
            sortItem=(TextView)itemView.findViewById(R.id.sort_header_item);
        }
    }

}
