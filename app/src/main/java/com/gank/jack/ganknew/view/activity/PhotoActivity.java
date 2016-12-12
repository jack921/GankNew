package com.gank.jack.ganknew.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.PhotoFragmentAdapter;
import com.gank.jack.ganknew.base.ToolbarBaseActivity;
import com.gank.jack.ganknew.bean.FemaleCurrent;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.utils.ImmersiveUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 谢汉杰
 */

public class PhotoActivity extends ToolbarBaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.photo_viewpager)
    public ViewPager photoViewpager;

    private PhotoFragmentAdapter photoFragmentAdapter;
    private List<Gank> listGank;
    private int position;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
    }

    public void init(){
        setStatusBarTintColor(R.color.black);
        setToolbarAlpha(0.4f,R.color.photo_bg);
        listGank=(List<Gank>) getIntent().getSerializableExtra("listGank");
        position=getIntent().getIntExtra("position",0);
        photoViewpager.setOnPageChangeListener(this);
    }


    public void initView(){
        photoFragmentAdapter=new PhotoFragmentAdapter(getSupportFragmentManager(),listGank);
        photoViewpager.setAdapter(photoFragmentAdapter);
        photoViewpager.setCurrentItem(position);
    }

    @Override
    public void supportFinishAfterTransition() {
        Intent data = new Intent();
        data.putExtra("index",photoViewpager.getCurrentItem());
        setResult(RESULT_OK, data);
        ImmersiveUtil.exit(this);
        super.supportFinishAfterTransition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageSelected(int position) {
        FemaleCurrent femaleCurrent=new FemaleCurrent();
        femaleCurrent.current=position;
        EventBus.getDefault().post(femaleCurrent);
    }
    @Override
    public void onPageScrollStateChanged(int state) {}

}
