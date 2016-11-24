package com.gank.jack.ganknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.api.GankApiFactory;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.bean.ImageType;
import com.gank.jack.ganknew.interfaces.IImageInfo;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.utils.ImageLoad;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2016/11/6.
 */

public class TodayRecommAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Gank> listGank;
    private final int HEADER=0;
    private final int NORMAL=1;
    private OnClickLintener onClickLintener;

    public TodayRecommAdapter(Context context, List<Gank> listGank){
        this.context=context;
        this.listGank=addHeadItem(listGank);
    }

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }

    public List<Gank> addHeadItem(List<Gank> tempListGank){
        List<Gank> resultListGank=new ArrayList<>();
        String headerStatus="";
        for(int i=0;i<tempListGank.size();i++){
            Gank gank=tempListGank.get(i);
            String header = gank.type;
            if(!gank.type.equals(headerStatus)&&!header.equals("福利")){
                Gank gankHeader = gank.clone();
                headerStatus = header;
                gankHeader.isHeader = true;
                resultListGank.add(gankHeader);
            }
            if(!gank.type.equals("福利")){
                gank.isHeader=false;
                resultListGank.add(gank);
            }
        }
        return resultListGank;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(getItemViewType(viewType)== HEADER){
            return new HeaderView(LayoutInflater.from(context).inflate(R.layout.item_today_header,parent,false));
        }else{
            return new ItemView(LayoutInflater.from(context).inflate(R.layout.item_today_main,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position)==HEADER){
            HeaderView headerView=(HeaderView)holder;
            headerView.bindItem(listGank.get(position).type);
        }else if(getItemViewType(position)==NORMAL){
            final ItemView itemView=(ItemView)holder;
            itemView.bindItem(listGank.get(position));
        }
    }

    @Override
    public int getItemCount(){
        return listGank.size();
    }

    @Override
    public int getItemViewType(int position){
        if(listGank.get(position).isHeader){
           return HEADER;
        }else{
           return NORMAL;
        }
    }

    class HeaderView extends RecyclerView.ViewHolder{
        public TextView header;
        public HeaderView(View itemView) {
            super(itemView);
            header=(TextView)itemView.findViewById(R.id.main_header);
        }
        public void bindItem(String title){
            header.setText(title);
        }
    }

    class ItemView extends RecyclerView.ViewHolder {
        public TextView itemText;
        public ImageView itemImage;

        public ItemView(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.main_item_content);
            itemImage = (ImageView) itemView.findViewById(R.id.main_item_image);
            if(onClickLintener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickLintener.onClick(view,getAdapterPosition());
                    }
                });
            }
        }

        public void bindItem(final Gank gank) {
            itemImage.setVisibility(View.GONE);
            itemText.setText(gank.desc);
            if (gank.images != null) {
              if(!gank.hasLoadImage){
                  GankApiFactory.GankApiImageInfo(gank.images.get(0) + "?imageInfo", new IImageInfo() {
                      @Override
                      public void error(){}
                      @Override
                      public void seccess(ImageType imageType) {
                          if (!imageType.format.equals("gif")) {
                              ImageLoad.displayImage(gank.images.get(0),itemImage);
                              itemImage.setVisibility(View.VISIBLE);
                              gank.hasLoadImage=true;
                          }
                      }
                  });
              }else{
                  ImageLoad.displayImage(gank.images.get(0),itemImage);
                  itemImage.setVisibility(View.VISIBLE);
              }
            }
        }

    }


}
