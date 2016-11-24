package com.gank.jack.ganknew.utils;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by Jack on 2016/11/25.
 */

public class Utils {

    // 得到剪贴板管理器
    public static boolean copy(String content,Context context) {
        try{
            ClipboardManager cmb = (ClipboardManager) context
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(content.trim());
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
