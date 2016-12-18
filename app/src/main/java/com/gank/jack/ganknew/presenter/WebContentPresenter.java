package com.gank.jack.ganknew.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
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
import com.gank.jack.ganknew.bean.CollectGank;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.database.GankSQLiteImpl;
import com.gank.jack.ganknew.interfaces.WebContentInterface;
import com.gank.jack.ganknew.utils.widget.MyWebViewClient;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Jack on 2016/11/29.
 */

public class WebContentPresenter extends BasePresenter{

    public WebContentPresenter(Context context) {
        super(context);
    }

    public void initWebView(final WebContentInterface webContentInterface,
                            WebView webView, ProgressBar webviewPb, Gank gank, Toolbar webToolbar){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyWebViewClient(webviewPb));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
            @Override
            public void onReceivedError(WebView view,WebResourceRequest request, WebResourceError error) {
                webContentInterface.loadError();
            }
        });

        if(gank!=null){
            webToolbar.setTitle(gank.desc);
            webToolbar.setSubtitle(gank.who);
            webView.loadUrl(gank.url);
        }else{
            webContentInterface.loadError();
        }
    }

    public void errorReLoad(WebView webView,LinearLayout load_error,Gank gank){
        webView.setVisibility(View.VISIBLE);
        load_error.setVisibility(View.GONE);
        webView.loadUrl(gank.url);
    }

    public void intiCollectOptionsMenu(WebContentInterface webContentInterface,boolean collectTag,Menu menu,String gankId){
        if(collectTag){
            menu.getItem(0).setIcon(R.drawable.icon_yishoucang);
            webContentInterface.collectOptionsMenuResult(true);
        }else{
            if(GankSQLiteImpl.queryCollectGank(gankId)){
                menu.getItem(0).setIcon(R.drawable.icon_yishoucang);
                webContentInterface.collectOptionsMenuResult(true);
            }else{
                menu.getItem(0).setIcon(R.drawable.icon_weishoucang);
                webContentInterface.collectOptionsMenuResult(false);
            }
        }
    }

    public void openUrlOfBrower(Context context,String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    public void dealCollect(WebContentInterface webContentInterface,MenuItem item,boolean collectStatus,Gank gank){
        if(collectStatus==true){
            deleteGank(webContentInterface,item,gank);
        }else{
            saveGank(webContentInterface,item,gank);
        }
    }

    public void saveGank(WebContentInterface webContentInterface,MenuItem item,Gank gank){
        if(GankSQLiteImpl.saveCollectGank(gank)){
            item.setIcon(R.drawable.icon_yishoucang);
            EventBus.getDefault().post(new CollectGank(true,gank));
            webContentInterface.saveResult(true);
        }else{
            webContentInterface.saveResult(false);
        }
    }

    public void deleteGank(WebContentInterface webContentInterface,MenuItem item,Gank gank){
        if(GankSQLiteImpl.deleteCollectGank(gank._id)){
            item.setIcon(R.drawable.icon_weishoucang);
            EventBus.getDefault().post(new CollectGank(false,gank));
            webContentInterface.deleteResult(true);
        }else{
            webContentInterface.deleteResult(false);
        }
    }

    public void sharedGank(Gank gank){
        String textShared = gank.desc+"(" + gank.url+" )"+context.getString(R.string.from_gank);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.from_app));
        intent.putExtra(Intent.EXTRA_TEXT, textShared);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
