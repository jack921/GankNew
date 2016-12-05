package com.gank.jack.ganknew.interfaces;

import com.gank.jack.ganknew.bean.Search;

import java.util.List;

/**
 * Created by Jack on 2016/12/6.
 */

public interface SearchInterface {

    //获取搜索结果
    public void getSearchData(List<Search> listSearch,boolean isLast);

    //失败
    public void onError(Throwable throwable);

}
