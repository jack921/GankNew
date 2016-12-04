package com.gank.jack.ganknew.presenter;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.TodayRecommAdapter;
import com.gank.jack.ganknew.bean.DateResult;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.bean.SelectDate;
import com.gank.jack.ganknew.interfaces.SelectDateInterface;
import com.gank.jack.ganknew.interfaces.TodayRecommInterface;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import com.gank.jack.ganknew.bean.TodayGank;
import com.gank.jack.ganknew.utils.ImageLoad;
import com.gank.jack.ganknew.utils.SPUtils;
import com.gank.jack.ganknew.view.fragment.TodayRecommFragment;
import com.google.gson.Gson;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    public boolean initData(TodayRecommFragment todayRecommFragment, List<Gank> listGank,List<Gank> listGanks,
            TodayRecommPresenter todayRecommPresenter, ImageView todayGankImage,
            CollapsingToolbarLayout collapsingToolbarLayout,DateResult dateResult){
        if(listGanks.size()==0){
            todayRecommPresenter.getSelectDate(todayRecommFragment);
            return true;
        }
        if(listGank!=null) listGank.clear();
        listGank.addAll(listGanks);
        if(listGank.get(listGank.size()-1).type.equals("福利")){
            ImageLoad.displayImage(listGank.get(listGank.size()-1).url,todayGankImage);
        }
        collapsingToolbarLayout.setTitle(dateResult.year+"年"+dateResult.month+"月"+dateResult.day+"日");
        return false;
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

    public List<Gank> addHeadItem(List<Gank> tempListGank){
        List<Gank> resultListGank=new ArrayList<>();
        String headerStatus="";
        for(int i=0;i<tempListGank.size();i++){
            Gank gank=tempListGank.get(i);
            String header = gank.type;
            if(!gank.type.equals(headerStatus)&&!header.equals("福利")){
                Gank gankHeader = gank.clone();
                headerStatus = header;
                gankHeader.isHeader = true;
                resultListGank.add(gankHeader);
            }
            if(!gank.type.equals("福利")){
                gank.isHeader=false;
                resultListGank.add(gank);
            }
        }
        return resultListGank;
    }

    public DateResult getTodayRecomm(){
        Calendar calendar=Calendar.getInstance();
        String year=(calendar.get(Calendar.YEAR)+1)+"";
        int tempMonth=calendar.get(Calendar.MONTH);
        int tempDay=calendar.get(Calendar.DAY_OF_MONTH);
        String month=tempMonth>10?tempMonth+"":"0"+tempMonth;
        String day=tempDay>10?tempDay+"":"0"+tempDay;
        return new DateResult(year,month,day);
    }

    public void getSelectDate(final TodayRecommInterface todayRecommInterface){
        gankApi.getAllSelectDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SelectDate>() {
                    @Override
                    public void onCompleted(){}
                    @Override
                    public void onError(Throwable e) {
                        todayRecommInterface.onError(e);
                    }
                    @Override
                    public void onNext(SelectDate selectDate){
                        todayRecommInterface.getSelectDate(selectDate);
                    }
                });
    }

    public DateResult formatStringDate(String date){
        int firstIndex=date.indexOf("-");
        int lastIndex=date.lastIndexOf("-");
        String year=date.substring(0,firstIndex);
        String month=date.substring(firstIndex+1,lastIndex);
        String day=date.substring(lastIndex+1,date.length());
        return new DateResult(year,month,day);
    }

    public void initBoomMenu(Context context,BoomMenuButton boomMenuButton){
        Drawable[] drawables = new Drawable[3];
        int[][] colors = new int[3][2];
        String[] string=new String[]{"日期","发布","查找"};
        int[] drawablesResource = new int[]{
                R.drawable.refresh,
                R.drawable.refresh,
                R.drawable.refresh
        };
        for (int i = 0; i < 3; i++)
            drawables[i] = ContextCompat.getDrawable(context, drawablesResource[i]);
        for (int i = 0; i < 3; i++) {
            colors[i][1] = ContextCompat.getColor(context, R.color.colorAccent);
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }
        new BoomMenuButton.Builder()
                .subButtons(drawables,colors,string)
                .button(ButtonType.CIRCLE)
                .boom(BoomType.HORIZONTAL_THROW_2)
                .place(PlaceType.CIRCLE_3_1)
                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .init(boomMenuButton);
    }

}
