package com.gank.jack.ganknew.utils;

/**
 * Created by Jack on 2016/10/28.
 */

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.gank.jack.ganknew.theme.Theme;
import com.gank.jack.ganknew.theme.util.ColorUiUtil;

public class PreUtils {
    private static SharedPreferences getSharedPreferences(final Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isFirstTime(Context context, String key) {
        if (getBoolean(context, key, false)) {
            return false;
        } else {
            putBoolean(context, key, true);
            return true;
        }
    }

    public static boolean contains(Context context, String key) {
        return PreUtils.getSharedPreferences(context).contains(key);
    }

    public static int getInt(final Context context, final String key, final int defaultValue) {
        return PreUtils.getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static boolean putInt(final Context context, final String key, final int pValue) {
        final SharedPreferences.Editor editor = PreUtils.getSharedPreferences(context).edit();
        editor.putInt(key, pValue);
        return editor.commit();
    }

    public static long getLong(final Context context, final String key, final long defaultValue) {
        return PreUtils.getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static Long getLong(final Context context, final String key, final Long defaultValue) {
        if (PreUtils.getSharedPreferences(context).contains(key)) {
            return PreUtils.getSharedPreferences(context).getLong(key, 0);
        } else {
            return null;
        }
    }

    public static boolean putLong(final Context context, final String key, final long pValue) {
        final SharedPreferences.Editor editor = PreUtils.getSharedPreferences(context).edit();
        editor.putLong(key, pValue);
        return editor.commit();
    }

    public static boolean getBoolean(final Context context, final String key, final boolean defaultValue) {
        return PreUtils.getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static boolean putBoolean(final Context context, final String key, final boolean pValue) {
        final SharedPreferences.Editor editor = PreUtils.getSharedPreferences(context).edit();
        editor.putBoolean(key, pValue);
        return editor.commit();
    }

    public static String getString(final Context context, final String key, final String defaultValue) {
        return PreUtils.getSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean putString(final Context context, final String key, final String pValue) {
        final SharedPreferences.Editor editor = PreUtils.getSharedPreferences(context).edit();
        editor.putString(key, pValue);
        return editor.commit();
    }

    public static boolean remove(final Context context, final String key) {
        final SharedPreferences.Editor editor = PreUtils.getSharedPreferences(context).edit();
        editor.remove(key);
        return editor.commit();
    }

    public static Theme getCurrentTheme(Context context) {
        return Theme.valueOf(PreUtils.getString(context,"app_theme", Theme.Brown.name()));
    }

    public static void setCurrentTheme(Context context, Theme currentTheme) {
        PreUtils.putString(context,"app_theme", currentTheme.name());
    }

    public static void changeColorImpl(Activity context, final Resources.Theme theme){
        final View rootView = context.getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= 14) {
            rootView.setDrawingCacheEnabled(true);
            rootView.buildDrawingCache(true);
            final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
            if (null != localBitmap && rootView instanceof ViewGroup) {
                final View localView2 = new View(context);
                localView2.setBackgroundDrawable(new BitmapDrawable(context.getResources(), localBitmap));
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ((ViewGroup) rootView).addView(localView2, params);
                localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        ColorUiUtil.changeTheme(rootView,theme);
                        System.gc();
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ((ViewGroup) rootView).removeView(localView2);
                        localBitmap.recycle();
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {}
                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                }).start();
            }
        } else {
            ColorUiUtil.changeTheme(rootView,theme);
        }
    }

    public static void changeTheme(Activity activity,int style,String color){
        activity.setTheme(style);
        PreUtils.setCurrentTheme(activity,Theme.getTheme(color));
    }

}


