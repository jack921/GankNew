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

/**
 * @author 谢汉杰
 */

public class PhotoFragment extends BaseFragment{

    @Bind(R.id.photo_image)
    public ImageView photoImage;

    private PhotoViewAttacher attacher;
    private String imageUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo,container,false);
        ButterKnife.bind(this,view);

        Bundle bundle=getArguments();
        imageUrl=bundle.getString("url");
        init();
        return view;
    }

    public void init(){
        ViewCompat.setTransitionName(photoImage,getString(R.string.transitionAnimator));
        Glide.with(this).load(imageUrl)
             .asBitmap()
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .into(new SimpleTarget<Bitmap>() {
                 @Override
                 public void onResourceReady(Bitmap resource,GlideAnimation<? super Bitmap> glideAnimation) {
                     photoImage.setImageBitmap(resource);
                     attacher.update();
                 }
             });
        attacher=new PhotoViewAttacher(photoImage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        attacher.cleanup();
        ButterKnife.unbind(this);
    }


}
