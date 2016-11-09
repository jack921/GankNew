package com.gank.jack.ganknew.api;

import android.view.View;

import com.gank.jack.ganknew.bean.ImageType;
import com.gank.jack.ganknew.interfaces.IImageInfo;
import com.gank.jack.ganknew.utils.ImageLoad;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jack on 2016/11/1.
 */

public class GankApiFactory {

    public static final Object object=new Object();
    public static GankApi gankApi=null;

    public static GankApi getGankApi(){
        synchronized (object){
            if(gankApi==null){
                gankApi=new GankApiRetrofit().getGankApiObject();
            }
            return gankApi;
        }
    }

   public static void GankApiImageInfo(String imageUrl,final IImageInfo imageInfo){
       getGankApi().GankImageInfo(imageUrl)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber<ImageType>() {
                   @Override
                   public void onCompleted() {}
                   @Override
                   public void onError(Throwable e){
                       imageInfo.error();
                   }
                   @Override
                   public void onNext(ImageType imageType) {
                       imageInfo.seccess(imageType);
                   }
               });
   }

}
