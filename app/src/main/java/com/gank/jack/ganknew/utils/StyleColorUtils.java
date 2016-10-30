package com.gank.jack.ganknew.utils;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.theme.Theme;

/**
 * Created by Jack on 2016/10/29.
 */

public class StyleColorUtils {

    public static int getStyleColorId(String enumData) {
        int resultColor=0;
        switch (Theme.getTheme(enumData)) {
            case Blue:
                resultColor=R.style.BlueTheme;
            break;
            case Red:
                resultColor=R.style.RedTheme;
            break;
            case Brown:
                resultColor=R.style.BrownTheme;
            break;
            case Purple:
                resultColor=R.style.PurpleTheme;
            break;
            case Teal:
                resultColor=R.style.TealTheme;
            break;
            case Green:
                resultColor=R.style.GreenTheme;
            break;
            case Pink:
                resultColor=R.style.PinkTheme;
            break;
            case Orange:
                resultColor=R.style.OrangeTheme;
            break;
            case DeepPurple:
                resultColor=R.style.DeepPurpleTheme;
            break;
            case Indigo:
                resultColor=R.style.IndigoTheme;
            break;
            case Cyan:
                resultColor=R.style.CyanTheme;
            break;
            case LightGreen:
                resultColor=R.style.LightGreenTheme;
            break;
            case Lime:
                resultColor=R.style.LimeTheme;
            break;
            case DeepOrange:
                resultColor=R.style.DeepOrangeTheme;
            break;
            case BlueGrey:
                resultColor=R.style.BlueGreyTheme;
            break;
        }
        return resultColor;
    }
}
