package com.gank.jack.ganknew.presenter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gank.jack.ganknew.adapter.SearchAdapter;
import com.gank.jack.ganknew.api.GankApi;
import com.gank.jack.ganknew.bean.GankModel;
import com.gank.jack.ganknew.bean.Search;
import com.gank.jack.ganknew.bean.SearchGank;
import com.gank.jack.ganknew.interfaces.SearchInterface;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类名称：SearchPersenter.java <br>
 * 内容摘要：首页
 * 属性描述：<br>
 * 方法描述：<br>
 * 修改备注： <br>
 * 创建时间： 2016/12/5 17:20<br>
 * 公司：深圳市华移科技股份有限公司<br>
 *
 * @author 谢汉杰
 */

public class SearchPersenter extends BasePresenter{

    public SearchPersenter(Context context) {
        super(context);
    }

    public void getSearchResultData(final SearchInterface searchInterface,
                    String searchContent, int page){
        gankApi.getSearchData(searchContent,"20",page+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchGank>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                        searchInterface.onError(e);
                    }
                    @Override
                    public void onNext(SearchGank searchModel) {
                        boolean islast=false;
                        if(searchModel.results.size()==0){
                            islast=true;
                        }
                        searchInterface.getSearchData(searchModel.results,islast);
                    }
                });
    }

    public void cancelSearch(ImageView cancel, List<Search> listSearch, SearchAdapter adapter, EditText searchEditText){
        cancel.setVisibility(View.GONE);
        listSearch.clear();
        adapter.notifyDataSetChanged();
        searchEditText.setText("");
    }

}
