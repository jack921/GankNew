package com.gank.jack.ganknew.view.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.ThemeAdapter;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.ThemeModel;
import com.gank.jack.ganknew.interfaces.OnClickLintener;
import com.gank.jack.ganknew.presenter.ThemePersenter;
import com.gank.jack.ganknew.utils.PreUtils;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/12/18.
 */


public class ThemeActivity extends BaseActivity implements OnClickLintener {

    @Bind(R.id.about_toolbar)
    public Toolbar aboutToolbar;
    @Bind(R.id.theme_recyclerview)
    public RecyclerView themeRecyclerview;
    @Bind(R.id.theme_header)
    public RelativeLayout themeHeader;
    @Bind(R.id.theme_statusbar)
    public RelativeLayout themeStatusbar;
    @Bind(R.id.fab_theme_bg)
    public RelativeLayout fabThemeBg;

    public ThemeAdapter themeAdapter;
    private ThemePersenter themePersenter;
    private int currentPosition;
    private List<ThemeModel> listTheme=new ArrayList<>();

    private int[] themeColor=new int[]{R.color.colorBluePrimary,R.color.colorRedPrimary
        ,R.color.colorBrownPrimary,R.color.colorGreenPrimary,R.color.colorPurplePrimary
        ,R.color.colorTealPrimary,R.color.colorPinkPrimary,R.color.colorDeepPurplePrimary
        ,R.color.colorOrangePrimary,R.color.colorIndigoPrimary,R.color.colorCyanPrimary
        ,R.color.colorLightGreenPrimary,R.color.colorLimePrimary,R.color.colorDeepOrangePrimary
        ,R.color.colorBlueGreyPrimary};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        ButterKnife.bind(this);
        setBaseSupportActionBar(aboutToolbar);
        init();
        initView();
    }

    public void init(){
        themePersenter=new ThemePersenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        themeRecyclerview.setLayoutManager(linearLayoutManager);
        themeAdapter=new ThemeAdapter(this,listTheme);
        themeAdapter.setOnClickLintener(this);
        themeRecyclerview.setAdapter(themeAdapter);
    }

    public void initView(){
        themePersenter.getListColor(themeColor,listTheme,theme);
        themeAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.theme_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_sure:
                PreUtils.changeTheme(ThemeActivity.this,themePersenter.getThemeStyle(
                        listTheme.get(currentPosition).color),
                        themePersenter.getTheme(listTheme.get(currentPosition).color));
                PreUtils.changeColorImpl(ThemeActivity.this,ThemeActivity.this.getTheme());
                EventBus.getDefault().post(listTheme.get(currentPosition));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v, int position) {
        currentPosition=position;
        themeHeader.setBackgroundColor(getResources().getColor(listTheme.get(position).color));
        themeStatusbar.setBackgroundColor(getResources().getColor(listTheme.get(position).color));
        ((GradientDrawable)fabThemeBg.getBackground()).setColor(
                getResources().getColor(listTheme.get(position).color));
        themePersenter.selectListColor(position,listTheme);
        themeAdapter.notifyDataSetChanged();
    }


}
