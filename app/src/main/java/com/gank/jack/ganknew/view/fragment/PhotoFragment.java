package com.gank.jack.ganknew.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoFragment extends BaseFragment{

    @Bind(R.id.photo_image)
    public ImageView photoImage;

    private PhotoViewAttacher attacher;
    private String imageUrl;
    private String gankId;
    private boolean current;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        imageUrl=bundle.getString("url");
        gankId=bundle.getString("id");
        current=bundle.getBoolean("current");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewCompat.setTransitionName(photoImage,gankId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo,container,false);
        ButterKnife.bind(this,view);

        init();
        return view;
    }

    public void init(){
        Glide.with(this).load(imageUrl)
             .asBitmap()
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .into(new SimpleTarget<Bitmap>() {
                 @Override
                 public void onResourceReady(Bitmap resource,GlideAnimation<? super Bitmap> glideAnimation) {
                     photoImage.setImageBitmap(resource);
                     attacher.update();
                     maybeStartPostponedEnterTransition();
                 }
             });
        attacher=new PhotoViewAttacher(photoImage);
    }

    private void maybeStartPostponedEnterTransition() {
        if(current){
            getActivity().supportStartPostponedEnterTransition();
        }
    }

    public View getSharedElement(){
        return photoImage;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
