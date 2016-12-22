package com.gank.jack.ganknew.presenter;

import android.content.Context;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.bean.ThemeModel;
import com.gank.jack.ganknew.theme.Theme;

import java.util.List;

/**
 * @author 谢汉杰
 */

public class ThemePersenter extends BasePresenter{

    public ThemePersenter(Context context) {
        super(context);
    }

    public List<ThemeModel> getListColor(int[] colors,List<ThemeModel> listTheme,Theme theme){
        int color=getThemeString(theme.name().toString());
        for(int i=0;i<colors.length;i++){
            if(color==colors[i]){
                listTheme.add(new ThemeModel(colors[i],true));
            }else{
                listTheme.add(new ThemeModel(colors[i],false));
            }
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

    public int getThemeString(String color){
        if(color.equals(Theme.Blue.toString())){
               return  R.color.colorBluePrimary;
        }else if(color.equals(Theme.Red.toString())){
            return  R.color.colorRedPrimary;
        }else if(color.equals(Theme.Brown.toString())){
            return  R.color.colorBrownPrimary;
        }else if(color.equals(Theme.Green.toString())){
            return  R.color.colorGreenPrimary;
        }else if(color.equals(Theme.Purple.toString())){
            return  R.color.colorPurplePrimary;
        }else if(color.equals(Theme.Teal.toString())){
            return  R.color.colorTealPrimary;
        }else if(color.equals(Theme.Pink.toString())){
            return  R.color.colorPinkPrimary;
        }else if(color.equals(Theme.DeepPurple.toString())){
            return  R.color.colorBluePrimary;
        }else if(color.equals(Theme.Orange.toString())){
            return  R.color.colorOrangePrimary;
        }else if(color.equals(Theme.Indigo.toString())){
            return  R.color.colorIndigoPrimary;
        }else if(color.equals(Theme.Cyan.toString())){
            return  R.color.colorCyanPrimary;
        }else if(color.equals(Theme.LightGreen.toString())){
            return  R.color.colorLightGreenPrimary;
        }else if(color.equals(Theme.Lime.toString())){
            return  R.color.colorLimePrimary;
        }else if(color.equals(Theme.DeepOrange.toString())){
            return  R.color.colorDeepOrangePrimary;
        }else if(color.equals(Theme.BlueGrey.toString())){
            return  R.color.colorBlueGreyPrimary;
        }
        return 0;
    }

    public String getTheme(int color){
        switch(color){
            case R.color.colorBluePrimary:
                return Theme.Blue.toString();
            case R.color.colorRedPrimary:
                return Theme.Red.toString();
            case R.color.colorBrownPrimary:
                return Theme.Brown.toString();
            case R.color.colorGreenPrimary:
                return Theme.Green.toString();
            case R.color.colorPurplePrimary:
                return Theme.Purple.toString();
            case R.color.colorTealPrimary:
                return Theme.Teal.toString();
            case R.color.colorPinkPrimary:
                return Theme.Pink.toString();
            case R.color.colorDeepPurplePrimary:
                return Theme.DeepPurple.toString();
            case R.color.colorOrangePrimary:
                return Theme.Orange.toString();
            case R.color.colorIndigoPrimary:
                return Theme.Indigo.toString();
            case R.color.colorCyanPrimary:
                return Theme.Cyan.toString();
            case R.color.colorLightGreenPrimary:
                return Theme.LightGreen.toString();
            case R.color.colorLimePrimary:
                return Theme.Lime.toString();
            case R.color.colorDeepOrangePrimary:
                return Theme.DeepOrange.toString();
            case R.color.colorBlueGreyPrimary:
                return Theme.BlueGrey.toString();
        }
        return "";
    }

    public int getThemeStyle(int color){
        switch(color){
            case R.color.colorBluePrimary:
                return R.style.BlueTheme;
            case R.color.colorRedPrimary:
                return R.style.RedTheme;
            case R.color.colorBrownPrimary:
                return R.style.BrownTheme;
            case R.color.colorGreenPrimary:
                return R.style.GreenTheme;
            case R.color.colorPurplePrimary:
                return R.style.PurpleTheme;
            case R.color.colorTealPrimary:
                return R.style.TealTheme;
            case R.color.colorPinkPrimary:
                return R.style.PinkTheme;
            case R.color.colorDeepPurplePrimary:
                return R.style.DeepPurpleTheme;
            case R.color.colorOrangePrimary:
                return R.style.OrangeTheme;
            case R.color.colorIndigoPrimary:
                return R.style.IndigoTheme;
            case R.color.colorCyanPrimary:
                return R.style.CyanTheme;
            case R.color.colorLightGreenPrimary:
                return R.style.LightGreenTheme;
            case R.color.colorLimePrimary:
                return R.style.LimeTheme;
            case R.color.colorDeepOrangePrimary:
                return R.style.DeepOrangeTheme ;
            case R.color.colorBlueGreyPrimary:
                return R.style.BlueGreyTheme;
        }
        return 0;
    }


}
