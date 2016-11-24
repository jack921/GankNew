package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.utils.widget.MyWebViewClient;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/12.
 */


public class WebContentActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.web_content)
    public WebView webView;
    @Bind(R.id.webview_pb)
    public ProgressBar webviewPb;
    @Bind(R.id.load_error)
    public LinearLayout load_error;
    @Bind(R.id.web_toolbar)
    public Toolbar webToolbar;

    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcontent);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        setSupportActionBar(webToolbar);
        load_error.setOnClickListener(this);
        url=getIntent().getStringExtra("url");
        if(url!=null){
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            webView.setWebChromeClient(new MyWebViewClient(webviewPb));
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url){
                    return false;
                }
                @Override
                public void onReceivedError(WebView view,
                          WebResourceRequest request,WebResourceError error) {
                    loadError();
                }
            });
            webView.loadUrl(url);
        }else{
            loadError();
        }

    }

    public void loadError(){
        webView.setVisibility(View.GONE);
        load_error.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.load_error:
                webView.setVisibility(View.VISIBLE);
                load_error.setVisibility(View.GONE);
                webView.loadUrl(url);
                break;
        }
    }


}
