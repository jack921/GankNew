package com.gank.jack.ganknew.presenter;

import android.content.Context;

import com.gank.jack.ganknew.bean.ThemeModel;

import java.util.List;

/**
 * 类名称：ThemePersenter.java <br>
 * 内容摘要：首页
 * 属性描述：<br>
 * 方法描述：<br>
 * 修改备注： <br>
 * 创建时间： 2016/12/20 09:48<br>
 * 公司：深圳市华移科技股份有限公司<br>
 *
 * @author 谢汉杰
 */

public class ThemePersenter extends BasePresenter{

    public ThemePersenter(Context context) {
        super(context);
    }

    public List<ThemeModel> getListColor(int[] colors,List<ThemeModel> listTheme){
        for(int i=0;i<colors.length;i++){
            listTheme.add(new ThemeModel(colors[i],false));
        }
        return listTheme;
    }

    public void selectListColor(int position,List<ThemeModel> modelList){
        for(int i=0;i<modelList.size();i++){
            if(i==position){
                modelList.get(i).focus=true;
            }else{
                modelList.get(i).focus=false;
            }
        }
    }

}
