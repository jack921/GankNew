package com.gank.jack.ganknew.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jack on 2016/11/4.
 */

public class TodayGank extends BaseData{
    public List<String> category;
    public Result results;

    public class Result {
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("休息视频") public List<Gank> 休息视频List;
        @SerializedName("iOS") public List<Gank> iOSList;
        @SerializedName("福利") public List<Gank> 妹纸List;
        @SerializedName("拓展资源") public List<Gank> 拓展资源List;
        @SerializedName("瞎推荐") public List<Gank> 瞎推荐List;
        @SerializedName("App") public List<Gank> appList;
    }
}
