package com.book.handprobe;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
//import com.anggrayudi.hiddenapi.r.Rc;

import gloomyfish.opencvdemo.MyApplication;

/* loaded from: classes2.dex */
public class ScreenUtil {
    public static final String ActivityPrefer = "ActivityPrefer";
    private static final String ActivityPreferOrientation = "ActivityPreferOrientation";

    public static int getAppOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    public static int getAppOrientation() {
        return getAppOrientation(MyApplication.GetAppContext());
    }

    public static int getPreferOrientation(Context context) {
        return context.getSharedPreferences(ActivityPrefer, 0).getInt(ActivityPreferOrientation, 6);
    }

    public static int getPreferOrientation() {
        return getPreferOrientation(MyApplication.GetAppContext());
    }

    public static void loadPreferOrientation(Activity activity) {
        activity.setRequestedOrientation(getPreferOrientation(activity));
    }

    public static void KeepLandScape(Activity activity) {
        activity.setRequestedOrientation(activity.getSharedPreferences(ActivityPrefer, 0).getInt(ActivityPreferOrientation, 8));
    }

    public static int setPreferOrientation(Context context, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(ActivityPrefer, 0).edit();
        edit.putInt(ActivityPreferOrientation, i);
        if (edit.commit()) {
            return i;
        }
        return -1;
    }

    public static DisplayMetrics getScreenSize(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static DisplayMetrics getScreenSize() {
        return MyApplication.GetAppContext().getResources().getDisplayMetrics();
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (getPreferOrientation(context) == 7) {
            return Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        return Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (getPreferOrientation(context) == 7) {
            return Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        return Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static Point getRealScreenSize(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        return point;
    }

    public static Point getRealScreenSize(Window window) {
        Point point = new Point();
        window.getWindowManager().getDefaultDisplay().getRealSize(point);
        return point;
    }

    public static void FullScreen(Window window, View view) {
        window.addFlags(1024);
        window.addFlags(256);
        window.addFlags(65536);
        if (!((Boolean) SPUtil.get(SPUtil.HideNavigationBar, Boolean.TRUE)).booleanValue()) {
            window.getDecorView().setFitsSystemWindows(true);
            window.getDecorView().requestApplyInsets();
            if (view == null) {
                return;
            }
            view.setFitsSystemWindows(true);
            view.requestApplyInsets();
            return;
        }
        ((ViewGroup) window.getDecorView()).setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 5894 : 1799);
        window.getDecorView().setFitsSystemWindows(false);
        window.getDecorView().requestApplyInsets();
        if (view != null) {
            view.setFitsSystemWindows(false);
            view.requestApplyInsets();
        }
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.clearFlags(134217728);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
    }

    public static void FullScreen(Window window) {
        FullScreen(window, null);
    }

    public static void KeepFullScreen(final Window window, final View view) {
        FullScreen(window, view);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: handprobe.components.utils.ScreenUtil.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                ScreenUtil.FullScreen(window, view);
            }
        });
        window.setSoftInputMode(32);
    }

    public static void KeepFullScreen(final Dialog dialog) {
        KeepFullScreen(dialog.getWindow(), null);
        if (((Boolean) SPUtil.get(SPUtil.HideNavigationBar, Boolean.TRUE)).booleanValue() && navigationBarExist(dialog.getWindow())) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: handprobe.components.utils.ScreenUtil.2
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == 1 && i == 4) {
                        ScreenUtil.FullScreen(dialog.getWindow(), null);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public static void KeepFullScreen(Window window) {
        KeepFullScreen(window, null);
    }

    public static boolean IsFullScreen() {
        return ((Boolean) SPUtil.get(SPUtil.HideNavigationBar, Boolean.TRUE)).booleanValue();
    }

    public static boolean navigationBarExist(Window window) {
        Point realScreenSize = getRealScreenSize(window);
        DisplayMetrics screenSize = getScreenSize();
        return realScreenSize.x - screenSize.widthPixels > 0 || realScreenSize.y - screenSize.heightPixels > 0;
    }

    public static void refreshWindow(View view) {
//        ObjectAnimator.ofFloat(view, Rc.attr.rotationX, 0.0f).setDuration(0L).start();
    }
}
