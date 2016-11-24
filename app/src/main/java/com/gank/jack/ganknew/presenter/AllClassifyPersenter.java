package com.gank.jack.ganknew.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import com.gank.jack.ganknew.api.Config;
import com.gank.jack.ganknew.bean.Sort;
import com.gank.jack.ganknew.interfaces.AllClassifyInterface;
import com.gank.jack.ganknew.utils.SPUtils;
import com.gank.jack.ganknew.view.fragment.ClassifyTabFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：AllClassifyPersenter.java <br>
 * @author 谢汉杰
 */

public class AllClassifyPersenter extends BasePresenter{

    public AllClassifyPersenter(Context context) {
        super(context);
    }

    //有数据的时候初始化
    public void initAllTabData(AllClassifyInterface allClassifyInterface, String tabData,
                               TabLayout tabLayout, List<Fragment> listFragment){
        List<Sort> tempListSort=new Gson().fromJson(tabData,new TypeToken<List<Sort>>(){}.getType());
        for(int i=0;i<tempListSort.size();i++){
            if(tempListSort.get(i).normal==true&&tempListSort.get(i).choose==true){
                tabLayout.addTab(tabLayout.newTab().setText(tempListSort.get(i).title));
                Bundle bundle=new Bundle();
                bundle.putString("TabTitle",tempListSort.get(i).title);
                ClassifyTabFragment classifyTabFragment=new ClassifyTabFragment();
                classifyTabFragment.setArguments(bundle);
                listFragment.add(classifyTabFragment);
            }
        }
        allClassifyInterface.initViewData(tabLayout,listFragment);
    }

    //没有数据的时候初始化
    public void initNotAllTabData(AllClassifyInterface allClassifyInterface,
             TabLayout tabLayout, List<Fragment> listFragment){
        List<Sort> listSort=new ArrayList<>();

        listSort.add(new Sort("",true,false,false,false));
        for(int i=0;i<2;i++){
            listSort.add(new Sort(Config.Aategory[i],false,false,true,true));
        }
        listSort.add(new Sort("",false,true,false,false));
        for(int i=2;i<Config.Aategory.length;i++){
            listSort.add(new Sort(Config.Aategory[i],false,false,true,false));
        }
        SPUtils.put(context,"TabMenu","TabGson",new Gson().toJson(listSort));

        for(int i=0;i<listSort.size();i++){
            if(listSort.get(i).normal==true&&listSort.get(i).choose==true){
                tabLayout.addTab(tabLayout.newTab().setText(listSort.get(i).title));
                Bundle bundle=new Bundle();
                bundle.putString("TabTitle",listSort.get(i).title);
                ClassifyTabFragment classifyTabFragment=new ClassifyTabFragment();
                classifyTabFragment.setArguments(bundle);
                listFragment.add(classifyTabFragment);
            }
        }
        allClassifyInterface.initViewData(tabLayout,listFragment);
    }

    public void updatTabClassify(AllClassifyInterface allClassifyInterface,List<Sort> listSort,
                     TabLayout tabLayout, List<Fragment> listFragment){
        try{
            tabLayout.removeAllTabs();
            listFragment.clear();
            for(int i=0;i<listSort.size();i++){
                if(listSort.get(i).normal==true&&listSort.get(i).choose==true){
                    tabLayout.addTab(tabLayout.newTab().setText(listSort.get(i).title));
                    Bundle bundle=new Bundle();
                    bundle.putString("TabTitle",listSort.get(i).title);
                    ClassifyTabFragment classifyTabFragment=new ClassifyTabFragment();
                    classifyTabFragment.setArguments(bundle);
                    listFragment.add(classifyTabFragment);
                }
            }
            allClassifyInterface.updateTabClassify(true);
        }catch(Exception e){
            allClassifyInterface.updateTabClassify(false);
        }
    }

}
