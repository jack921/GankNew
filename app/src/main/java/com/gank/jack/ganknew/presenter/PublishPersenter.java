package com.gank.jack.ganknew.presenter;

import android.content.Context;
import com.gank.jack.ganknew.bean.GankModel;
import com.gank.jack.ganknew.bean.PublishResult;
import com.gank.jack.ganknew.interfaces.PublishInterface;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jack on 2016/12/7.
 */

public class PublishPersenter extends BasePresenter{

    public PublishPersenter(Context context) {
        super(context);
    }

    public void publishData(final PublishInterface publishInterface,String url,String desc,String who,String type){
        gankApi.commitToGank(url,desc,who,type,"true")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PublishResult>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                        publishInterface.error(e);
                    }
                    @Override
                    public void onNext(PublishResult publishResult) {
                        publishInterface.publishResult(publishResult);
                    }
                });
    }

}
