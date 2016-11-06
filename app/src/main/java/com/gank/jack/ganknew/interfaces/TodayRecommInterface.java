package com.gank.jack.ganknew.interfaces;

import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.bean.TodayGank;

import java.util.List;

/**
 * Created by Jack on 2016/11/4.
 */

public interface TodayRecommInterface extends BaseInterface{

    //获取今天数据
    public void getToadyRecomm(List<Gank> listGank);

    //失败
    public void onError(Throwable throwable);

}
