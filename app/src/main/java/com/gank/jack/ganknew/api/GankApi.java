package com.gank.jack.ganknew.api;


import com.gank.jack.ganknew.bean.GankModel;
import com.gank.jack.ganknew.bean.ImageType;
import com.gank.jack.ganknew.bean.TodayGank;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Jack on 2016/11/1.
 */

public interface GankApi {

    //获取特定日期网站数据
    @GET("history/content/day/{year}/{month}/{day}")
    Observable<GankModel> getHistoryData(@Path("year") String year,@Path("month")
                String month,@Path("day") String day);

    //分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
    //数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    //请求个数： 数字，大于0
    //第几页：数字，大于0
    @GET("data/{type}/{num}/{page}")
    Observable<GankModel> getGankData(@Path("type") String type,
             @Path("num") String num,@Path("page") String page);

    //每日数据： http://gank.io/api/day/年/月/日
    @GET("day/{year}/{month}/{day}")
    Observable<TodayGank> getGankDataOfDate(@Path("year") String year,
                 @Path("month") String month, @Path("day") String day);


    //搜索 API搜索 API
    @GET("search/query/{content}/category/all/count/{num}/page/{page}")
    Observable<GankModel> getSearchData(@Path("content") String context,
            @Path("num") String num,@Path("page") String page);

    //提交干货到审核区
    @Multipart
    @POST("add2gank")
    Observable<GankModel> commitToGank(@Part("url") String url,@Path("desc") String desc
            ,@Part("who") String who,@Part("type") String type,@Part("debug") String debug);

    @GET
    Observable<ImageType> GankImageInfo(@Url String url);


}
