package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.ImageView;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.Gank;
import com.gank.jack.ganknew.utils.ImageLoad;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 谢汉杰
 */

public class PhotoActivity extends BaseActivity{

    @Bind(R.id.photo_image)
    ImageView photoImage;

    private Gank gank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        gank=(Gank)getIntent().getSerializableExtra("gank");
        ImageLoad.displayImage(gank.url,photoImage);

    }

}
