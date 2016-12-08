package com.gank.jack.ganknew.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.utils.ImageLoad;
import com.gank.jack.ganknew.utils.widget.GlideRequestListenerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author 谢汉杰
 */

public class PhotoActivity extends BaseActivity{

    @Bind(R.id.photo_image)
    ImageView photoImage;

    private PhotoViewAttacher attacher;
    private Gank gank;

    public void startPostponedEnterTransition(){
        supportStartPostponedEnterTransition();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(photoImage,getString(R.string.transitionAnimator));

        gank=(Gank)getIntent().getSerializableExtra("gank");
        attacher=new PhotoViewAttacher(photoImage);
        Glide.with(this).load(gank.url)
             .asBitmap()
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .into(new SimpleTarget<Bitmap>() {
                 @Override
                 public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                     photoImage.setImageBitmap(resource);
                     attacher.update();
                 }
             });

        }


}
