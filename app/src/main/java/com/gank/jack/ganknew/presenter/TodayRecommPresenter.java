package com.gank.jack.ganknew.presenter;

import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.interfaces.TodayRecommInterface;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import com.gank.jack.ganknew.bean.TodayGank;
import com.gank.jack.ganknew.utils.SPUtils;
import com.google.gson.Gson;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.Observer;

/**
 * Created by Jack on 2016/11/6.
 */

public class TodayRecommPresenter extends BasePresenter{

    public TodayRecommPresenter(Context context) {
        super(context);
    }

    public void getTodayRecommData(final TodayRecommInterface todayRecommInterface,
                     String year,String month,String day){
        gankApi.getGankDataOfDate(year,month,day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<TodayGank,List<Gank>>() {
                    @Override
                    public List<Gank> call(TodayGank todayGank) {
                        SPUtils.put(context,SPUtils.TODAYDATA,
                                "today",new Gson().toJson(todayGank));
                        return addGankItem(todayGank);
                    }})
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                        todayRecommInterface.onError(e);
                    }
                    @Override
                    public void onNext(List<Gank> ganks) {
                        if(ganks==null){
                            TodayGank tempToday=new Gson().fromJson(SPUtils.getSharedPreference(SPUtils.TODAYDATA,
                                           context).getString("today",""),TodayGank.class);
                            ganks=addGankItem(tempToday);
                        }
                        todayRecommInterface.getToadyRecomm(ganks);
                    }
                });
    }

    public List<Gank> addGankItem(TodayGank todayGank){
        List<Gank> listGank=new ArrayList<>();
        if(todayGank.results.androidList!=null) listGank.addAll(todayGank.results.androidList);
        if(todayGank.results.iOSList!=null) listGank.addAll(todayGank.results.iOSList);
        if(todayGank.results.休息视频List!=null) listGank.addAll(todayGank.results.休息视频List);
        if(todayGank.results.拓展资源List!=null) listGank.addAll(todayGank.results.拓展资源List);
        if(todayGank.results.瞎推荐List!=null) listGank.addAll(todayGank.results.瞎推荐List);
        if(todayGank.results.appList!=null) listGank.addAll(todayGank.results.appList);
        if(todayGank.results.妹纸List!=null) listGank.addAll(todayGank.results.妹纸List);
        return listGank;
    }


}
