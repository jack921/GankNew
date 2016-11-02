package com.gank.jack.ganknew.api;

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


}
