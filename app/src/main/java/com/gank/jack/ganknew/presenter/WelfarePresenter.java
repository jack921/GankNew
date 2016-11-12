package com.gank.jack.ganknew.presenter;

import android.content.Context;

import com.gank.jack.ganknew.bean.GankModel;
import com.gank.jack.ganknew.interfaces.WelfaceInterface;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jack on 2016/11/12.
 */

public class WelfarePresenter extends BasePresenter{

    private boolean isLast=false;

    public WelfarePresenter(Context context) {
        super(context);
    }

    public void getMeizi(final WelfaceInterface welfaceInterface,int page){
        gankApi.getGankData("福利","15",page+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankModel>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                        welfaceInterface.error(e);
                    }
                    @Override
                    public void onNext(GankModel gankModel) {
                        if(gankModel.results==null){
                            isLast=true;
                        }
                        welfaceInterface.getWelfaceData(gankModel.results,isLast);
                    }
                });
    }

}
