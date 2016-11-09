package com.gank.jack.ganknew.presenter;

import android.content.Context;

import com.gank.jack.ganknew.api.Config;
import com.gank.jack.ganknew.api.GankApi;
import com.gank.jack.ganknew.api.GankApiFactory;

/**
 * Created by Jack on 2016/11/6.
 */

public class BasePresenter {
    public Context context;
    public static final GankApi gankApi= GankApiFactory.getGankApi();

    public BasePresenter(Context context){
        this.context=context;
    }

}
