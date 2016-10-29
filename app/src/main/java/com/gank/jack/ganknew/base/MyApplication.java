package com.gank.jack.ganknew.base;

import android.app.Application;

import com.gank.jack.ganknew.theme.util.SharedPreferencesMgr;

/**
 * Created by Jack on 2016/10/27.
 */

public class MyApplication extends Application{

    public static MyApplication myApplication;

    public static Application getContext(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesMgr.init(this, "derson");
        myApplication=this;
    }

}
