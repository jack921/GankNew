package com.gank.jack.ganknew.utils.widget;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * 类名称：MyWebViewClient.java <br>
 * 内容摘要：首页
 * 属性描述：<br>
 * 方法描述：<br>
 * 修改备注： <br>
 * 创建时间： 2016/8/15 15:18<br>
 * 公司：深圳市华移科技股份有限公司<br>
 *
 * @author 谢汉杰
 */

public class MyWebViewClient extends WebChromeClient {

    private ProgressBar pb;

    public MyWebViewClient(ProgressBar pb){
        this.pb=pb;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        pb.setProgress(newProgress);
        if(newProgress==100){
            pb.setVisibility(View.GONE);
        }
        super.onProgressChanged(view, newProgress);
    }

}
