package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.SearchAdapter;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.Search;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.interfaces.RefreshInterface;
import com.gank.jack.ganknew.interfaces.SearchInterface;
import com.gank.jack.ganknew.presenter.SearchPersenter;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 谢汉杰
 */

public class SearchActivity extends BaseActivity
        implements TextView.OnEditorActionListener,SearchInterface, RefreshInterface, OnClickLintener {

    @Bind(R.id.search_toolbar)
    public Toolbar searchToolbar;
    @Bind(R.id.textInputLayout)
    public TextInputLayout searchInputLayout;
    @Bind(R.id.search_edittext)
    public EditText searchEdittext;
    @Bind(R.id.search_recyclerview)
    public RecyclerView searchRecyclerview;
    @Bind(R.id.cancel_img)
    public ImageView cancelImg;

    public SearchPersenter searchPersenter;
    public SearchAdapter searchAdapter;
    public List<Search> myListSearch;
    public boolean isLast=false;
    public int page=1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        myListSearch=new ArrayList<>();
        searchPersenter=new SearchPersenter(this);
        searchEdittext.setOnEditorActionListener(this);
        searchRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter=new SearchAdapter(this,myListSearch);
        searchAdapter.setRefreshInterface(this);
        searchAdapter.setOnClickLintener(this);
        searchRecyclerview.setAdapter(searchAdapter);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId== EditorInfo.IME_ACTION_SEARCH ||
             (keyEvent!=null&&keyEvent.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
            searchPersenter.getSearchResultData(this,searchEdittext.getText().toString(),page);
            cancelImg.setVisibility(View.VISIBLE);
            searchInputLayout.setHint("加载中");
        }
        return false;
    }

    @OnClick({R.id.search_back,R.id.cancel_img})
    public void onClick(View v){
        switch(v.getId()){
            case R.id.search_back:
                finish();
                break;
            case R.id.cancel_img:
                searchPersenter.cancelSearch(cancelImg,myListSearch,searchAdapter,searchEdittext);
                page=1;
                break;
        }
    }

    @Override
    public void onClick(View v,int position){
        searchPersenter.turnToWebContent(this,myListSearch.get(position));
    }

    @Override
    public void getSearchData(List<Search> listSearch,boolean isLast){
        this.isLast=isLast;
        this.page++;
        searchInputLayout.setHint(getString(R.string.inputsearch));
        this.myListSearch.addAll(listSearch);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable throwable) {
        showSnackbar(getString(R.string.net_error));
    }

    @Override
    public void loadMore(){
        if(!isLast){
            searchPersenter.getSearchResultData(this,searchEdittext.getText().toString(),page);
        }
    }

}
