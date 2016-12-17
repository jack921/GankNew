package com.gank.jack.ganknew.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import com.gank.jack.ganknew.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * @author 谢汉杰
 */

public abstract class ToolbarBaseActivity extends AppCompatActivity{

    public Toolbar photoToolbar;
    protected boolean mIsHidden = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setBaseSupportActionBar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //设置状态栏颜色
    public void setStatusBarTintColor(int res){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager mSystemBarTintManager=new SystemBarTintManager(this);
            mSystemBarTintManager.setStatusBarTintEnabled(true);
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

    public void hideOrShowToolbar() {
        photoToolbar.animate()
                .translationY(mIsHidden?0:-photoToolbar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }


}
