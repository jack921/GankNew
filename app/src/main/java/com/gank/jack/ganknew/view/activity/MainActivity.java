package com.gank.jack.ganknew.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.view.fragment.AllClassifyFragment;
import com.gank.jack.ganknew.view.fragment.FemaleWelfareFragment;
import com.gank.jack.ganknew.view.fragment.MyCollectFragment;
import com.gank.jack.ganknew.view.fragment.TodayRecommFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.nav_view)
    public NavigationView navigationView;

    public ImageView nav_header_img;
    private AllClassifyFragment allClassifyFragment;
    private FemaleWelfareFragment femaleWelfareFragment;
    private MyCollectFragment myCollectFragment;
    private TodayRecommFragment todayRecommFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transparentStatusBar();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    //初始化
    public void initView(){
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager=getSupportFragmentManager();
        nav_header_img=(ImageView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_img);
        Glide.with(this).load(R.drawable.nav_header_img).asGif().into(nav_header_img);
        setTabFragment(0);
    }

    public void setTabFragment(int tab){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch(tab){
            case 0:
                if(todayRecommFragment==null){
                    todayRecommFragment=new TodayRecommFragment();
                    fragmentTransaction.add(R.id.context_frame,todayRecommFragment);
                }else{
                    fragmentTransaction.show(todayRecommFragment);
                }
                break;
            case 1:
                if(allClassifyFragment==null){
                    allClassifyFragment=new AllClassifyFragment();
                    fragmentTransaction.add(R.id.context_frame,allClassifyFragment);
                }else{
                    fragmentTransaction.show(allClassifyFragment);
                }
                break;
            case 2:
                if(femaleWelfareFragment==null){
                    femaleWelfareFragment=new FemaleWelfareFragment();
                    fragmentTransaction.add(R.id.context_frame,femaleWelfareFragment);
                }else{
                    fragmentTransaction.show(femaleWelfareFragment);
                }
                break;
            case 3:
                if(myCollectFragment==null){
                    myCollectFragment=new MyCollectFragment();
                    fragmentTransaction.add(R.id.context_frame,myCollectFragment);
                }else{
                    fragmentTransaction.show(myCollectFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    public void hideFragment(FragmentTransaction fragmentTransaction){
        if(todayRecommFragment!=null){
            fragmentTransaction.hide(todayRecommFragment);
        }
        if(allClassifyFragment!=null){
            fragmentTransaction.hide(allClassifyFragment);
        }
        if(femaleWelfareFragment!=null){
            fragmentTransaction.hide(femaleWelfareFragment);
        }
        if(myCollectFragment!=null){
            fragmentTransaction.hide(myCollectFragment);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_tuijian:
                setTabFragment(0);
                break;
            case R.id.nav_all:
                setTabFragment(1);
                break;
            case R.id.nav_meizi:
                setTabFragment(2);
                break;
            case R.id.nav_collect:
                setTabFragment(3);
                break;
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout))
                .closeDrawer(GravityCompat.START);
        return true;
    }

}
