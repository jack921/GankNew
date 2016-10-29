package com.gank.jack.ganknew.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jack on 2016/10/27.
 */

public class ToastUtil {
    private static Toast toast;

    public static void showToast(Context context, String msg){
        if(toast==null){
            toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }


}
