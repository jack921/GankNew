package com.gank.jack.ganknew.api;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.MyApplication;
import com.gank.jack.ganknew.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jack on 2016/11/1.
 */

public class GankApiRetrofit {

    public static GankApi gankApi;

    //api的baseUrl
    public static final String GANK_BASE_URL= MyApplication.getContext().getString(R.string.base_api);


    public GankApi getGankApiObject(){
        return gankApi;
    }

    GankApiRetrofit(){
        File httpCacheDirectory=new File(MyApplication.getContext().getCacheDir(),"responses");
        int cashSize=10*1024*1024;
        Cache cache=new Cache(httpCacheDirectory,cashSize);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GANK_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        gankApi=retrofit.create(GankApi.class);
    }

    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if (!NetUtils.checkNetWorkIsAvailable(MyApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();

            }
            Response originalResponse = chain.proceed(request);
            if (NetUtils.checkNetWorkIsAvailable(MyApplication.getContext())) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };


}
