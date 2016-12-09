package com.gank.jack.ganknew.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;

import com.gank.jack.ganknew.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 谢汉杰
 */

public abstract class ToolbarBaseActivity extends BaseActivity{

    @Bind(R.id.photo_app_bar_layout)
    public AppBarLayout appBarLayout;
    @Bind(R.id.photo_toolbar)
    public Toolbar photoToolbar;

    protected boolean mIsHidden = false;

    abstract protected int setContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
        ButterKnife.bind(this);
    }

    public void setToolbarAlpha(float alpha,int color){
        setBaseSupportActionBar(photoToolbar);
        photoToolbar.setBackgroundColor(getResources().getColor(color));
        appBarLayout.setAlpha(alpha);
    }

    protected void hideOrShowToolbar() {
        appBarLayout.animate()
                .translationY(mIsHidden?0:-appBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
