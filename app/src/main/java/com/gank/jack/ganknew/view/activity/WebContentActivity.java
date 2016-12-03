package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.interfaces.WebContentInterface;
import com.gank.jack.ganknew.presenter.WebContentPresenter;
import com.gank.jack.ganknew.utils.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jack on 2016/11/12.
 */


public class WebContentActivity extends BaseActivity
        implements WebContentInterface {

    @Bind(R.id.web_content)
    public WebView webView;
    @Bind(R.id.webview_pb)
    public ProgressBar webviewPb;
    @Bind(R.id.load_error)
    public LinearLayout load_error;
    @Bind(R.id.web_toolbar)
    public Toolbar webToolbar;

    private Gank gank;
    private boolean collectStatus=false;
    private WebContentPresenter webContentPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcontent);
        ButterKnife.bind(this);
        webContentPresenter=new WebContentPresenter(this);
        initWebView();
        setBaseSupportActionBar(webToolbar);
    }


    public void initWebView(){
        gank=(Gank)getIntent().getSerializableExtra("gank");
        webContentPresenter.initWebView(this,webView,webviewPb,gank,webToolbar);
    }

    @Override
    public void loadError(){
        webView.setVisibility(View.GONE);
        load_error.setVisibility(View.VISIBLE);
    }

    @Override
    public void saveResult(boolean status) {
        if(status){
            collectStatus=true;
            showSnackbar(getString(R.string.collectsuccess));
        }else{
            showSnackbar(getString(R.string.collectfail));
        }
    }

    @Override
    public void deleteResult(boolean status) {
        if(status){
            collectStatus=false;
            showSnackbar(getString(R.string.cancelcollect));
        }else{
            showSnackbar(getString(R.string.cancelcollectfail));
        }
    }

    @Override
    public void collectOptionsMenuResult(boolean status) {
        if(status){
            collectStatus=true;
        }
    }

    @OnClick(R.id.load_error)
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.load_error:
                webContentPresenter.errorReLoad(webView,load_error,gank);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webcontent_menu,menu);
        webContentPresenter.intiCollectOptionsMenu(this,getIntent().getBooleanExtra("collectTag",false),
                menu,gank._id);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_collect:
                webContentPresenter.dealCollect(this,item,collectStatus,gank);
                break;
            case R.id.action_share:
                break;
            case R.id.action_browser:
                webContentPresenter.openUrlOfBrower(this,gank.url);
                break;
            case R.id.action_copy:
                if(Utils.copy(gank.url,this)){
                    showSnackbar(getString(R.string.hascopy));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(webView.canGoBack()) {
                webView.goBack();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
