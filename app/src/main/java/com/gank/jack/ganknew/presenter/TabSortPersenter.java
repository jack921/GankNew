package com.gank.jack.ganknew.presenter;

import android.content.Context;
import com.gank.jack.ganknew.bean.Sort;
import org.greenrobot.eventbus.EventBus;
import com.gank.jack.ganknew.interfaces.SortTabIntetface;
import com.gank.jack.ganknew.utils.SPUtils;
import com.google.gson.Gson;
import java.util.List;

/**
 * Created by Jack on 2016/11/23.
 */

public class TabSortPersenter extends BasePresenter{

    public TabSortPersenter(Context context) {
        super(context);
    }

    public void saveTabSort(SortTabIntetface sortTabIntetface, List<Sort> listSort){
       try{
           Gson gson=new Gson();
           SPUtils.put(context,"TabMenu","TabGson",gson.toJson(listSort));
           EventBus.getDefault().post(listSort);
           sortTabIntetface.saveTabResult(true);
       }catch (Exception e){
           sortTabIntetface.saveTabResult(false);
       }
    }

}
