package com.gank.jack.ganknew.presenter;

import android.content.Context;

import com.gank.jack.ganknew.bean.GankModel;
import com.gank.jack.ganknew.interfaces.ClassifyTabInterface;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jack on 2016/11/11.
 */

public class ClassifyTabPersenter extends BasePresenter{

    private boolean isLast=false;

    public ClassifyTabPersenter(Context context) {
        super(context);
    }

    public void getClassifyTabData(final ClassifyTabInterface classifyTabInterface, String type,int page){
        gankApi.getGankData(type,"20",page+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankModel>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                        classifyTabInterface.error(e);
                    }
                    @Override
                    public void onNext(GankModel gankModel) {
                        if(gankModel.results==null){
                            isLast=true;
                        }
                        classifyTabInterface.getTypeGank(gankModel.results,isLast);
                    }
        });
    }




}
