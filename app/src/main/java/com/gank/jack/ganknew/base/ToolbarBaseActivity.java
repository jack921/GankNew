package com.gank.jack.ganknew.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.gank.jack.ganknew.R;
import butterknife.Bind;
import butterknife.ButterKnife;

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

    public void setToolbarAlpha(float alpha,int color){
        setSupportActionBar(photoToolbar);
//        photoToolbar.setBackgroundColor(getResources().getColor(color));
    }

    public void setBaseSupportActionBar(Toolbar toolbar){
        setSupportActionBar(toolbar);
//        photoToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void hideOrShowToolbar() {
        photoToolbar.animate()
                .translationY(mIsHidden?0:-photoToolbar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }


}
