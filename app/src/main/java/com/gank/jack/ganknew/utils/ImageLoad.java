package com.gank.jack.ganknew.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.MyApplication;

/**
 * Created by Jack on 2016/11/6.
 */

public class ImageLoad {

    public static void displayImage(String imgUrl, ImageView imageView){
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);
    }

}
