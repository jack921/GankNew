package com.gank.jack.ganknew.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.utils.Utils;
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

    private Gank gank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcontent);
        ButterKnife.bind(this);

        initWebView();
        initToolbar();
    }

    public void initToolbar(){
        load_error.setOnClickListener(this);
        setSupportActionBar(webToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initWebView(){
        gank=(Gank)getIntent().getSerializableExtra("gank");
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

        if(gank!=null){
            webToolbar.setTitle(gank.desc);
            webToolbar.setSubtitle(gank.who);
            webView.loadUrl(gank.url);
        }else{
            loadError();
        }

    }

    public void loadError(){
        webView.setVisibility(View.GONE);
        load_error.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.load_error:
                webView.setVisibility(View.VISIBLE);
                load_error.setVisibility(View.GONE);
                webView.loadUrl(gank.url);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webcontent_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_collect:

                break;
            case R.id.action_share:

                break;
            case R.id.action_browser:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(gank.url);
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.action_copy:
                if(Utils.copy(gank.url,this)){
                    Snackbar.make(webToolbar,getString(R.string.hascopy),Snackbar.LENGTH_SHORT).show();
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
