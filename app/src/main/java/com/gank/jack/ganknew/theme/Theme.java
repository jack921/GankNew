package com.gank.jack.ganknew.theme;

/**
 * Created by Jack on 2016/10/28.
 */

public enum Theme {
    Blue,
    Red,
    Brown,
    Purple,
    Teal,
    Green,
    Pink,
    Orange,
    DeepPurple,
    Indigo,
    Cyan,
    LightGreen,
    Lime,
    DeepOrange,
    BlueGrey;

    public static Theme getTheme(String theme){
        return valueOf(theme);
    }
}

