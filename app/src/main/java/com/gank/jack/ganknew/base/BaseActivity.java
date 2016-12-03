package com.gank.jack.ganknew.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.theme.Theme;
import com.gank.jack.ganknew.utils.PreUtils;
import com.gank.jack.ganknew.utils.StyleColorUtils;
import com.gank.jack.ganknew.utils.ToastUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Jack on 2016/10/27.
 */

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
    }

    public void setTheme(){
        Theme theme= PreUtils.getCurrentTheme(this);
        PreUtils.changeTheme(this,StyleColorUtils.getStyleColorId(theme.name()),theme.name());
    }

    //设置透明状态栏
    public void transparentStatusBar(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void setBaseSupportActionBar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showToast(String msg){
        ToastUtil.showToast(this,msg);
    }

    public void showSnackbar(String message){
        Snackbar.make(getCurrentFocus(),message,Snackbar.LENGTH_SHORT).show();
    }

    public void startNewActiviy(Class<?> mClass){
        Intent intent=new Intent();
        intent.setClass(this,mClass);
        startActivity(intent);
    }

    public void startNewActivityByIntent(Intent intent){
        startActivity(intent);
    }

    public void closeActivity(){
        finish();
    }

    //设置状态栏颜色
    public void setStatusBarTintColor(int res){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager mSystemBarTintManager=new SystemBarTintManager(this);
            mSystemBarTintManager.setStatusBarTintEnabled(true);
//            mSystemBarTintManager.setStatusBarTintResource(res);
            mSystemBarTintManager.setStatusBarTintColor(res);
        }
    }

    public void setTranslucentStatus(boolean on){
        Window window=getWindow();
        WindowManager.LayoutParams winParam=window.getAttributes();
        final int bits=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(on){
            winParam.flags|=bits;
        }else{
            winParam.flags&=~bits;
        }
        window.setAttributes(winParam);
    }

}
