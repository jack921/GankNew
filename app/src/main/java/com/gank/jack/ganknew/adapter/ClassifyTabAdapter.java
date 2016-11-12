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
import java.util.List;

/**
 * Created by Jack on 2016/11/11.
 */

public class ClassifyTabAdapter extends BaseRecyclerViewAdapter<Gank>{
    private Context context;
    private List<Gank> listGank;
    private OnClickLintener onClickLintener;

    public ClassifyTabAdapter(Context context, List<Gank> ListData) {
        super(context, ListData);
        this.context=context;
        this.listGank=ListData;
    }

    public void addOnClickLintener(OnClickLintener onClickLintener){
        this.onClickLintener=onClickLintener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHodler(ViewGroup parent, int viewType) {
        return new ClassifyItem(LayoutInflater.from(context).inflate(R.layout.item_classfigtab_content,null));
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder viewHolder, int position, Gank data) {
        ClassifyItem classifyItem=(ClassifyItem)viewHolder;
        classifyItem.bindItem(listGank.get(position));
    }

    class ClassifyItem extends RecyclerView.ViewHolder{
        private TextView classifytab_content;
        private ImageView classifytab_image;

        public ClassifyItem(View itemView) {
            super(itemView);
            classifytab_content=(TextView)itemView.findViewById(R.id.classifytab_item_content);
            classifytab_image=(ImageView)itemView.findViewById(R.id.classifytab_item_image);

            if(onClickLintener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickLintener.onClick(v,getAdapterPosition());
                    }
                });
            }

        }

        public void bindItem(final Gank gank){
            classifytab_image.setVisibility(View.GONE);
            classifytab_content.setText(gank.desc);
            if (gank.images != null) {
                if(!gank.hasLoadImage){
                    GankApiFactory.GankApiImageInfo(gank.images.get(0) + "?imageInfo", new IImageInfo() {
                        @Override
                        public void error(){}
                        @Override
                        public void seccess(ImageType imageType) {
                            if (!imageType.format.equals("gif")) {
                                ImageLoad.displayImage(gank.images.get(0),classifytab_image);
                                classifytab_image.setVisibility(View.VISIBLE);
                                gank.hasLoadImage=true;
                            }
                        }
                    });
                }else{
                    ImageLoad.displayImage(gank.images.get(0),classifytab_image);
                    classifytab_image.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
