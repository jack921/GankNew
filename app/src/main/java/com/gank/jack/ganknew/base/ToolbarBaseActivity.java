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

    public AppBarLayout appBarLayout;
    public Toolbar photoToolbar;
    protected boolean mIsHidden = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setToolbarAlpha(float alpha,int color){
        setSupportActionBar(photoToolbar);
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


}
