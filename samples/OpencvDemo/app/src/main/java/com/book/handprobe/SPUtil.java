package com.book.handprobe;

import android.content.SharedPreferences;
import java.util.Map;

import gloomyfish.opencvdemo.MyApplication;

/* loaded from: classes2.dex */
public class SPUtil {
    public static final String FILE_NAME = "handprobe_sp";
    public static final String HideNavigationBar = "HideNavigationBar";

    public static void clearAllSP() {
        for (String str : new String[]{FILE_NAME, LanguageUtil.HandProbelanguage, "uhandprobe_ParaLoc", "uhandprobe_LedBack", "UHandProbeHospital", "train", "save_train", "wifiInfo", "app_bright", ScreenUtil.ActivityPrefer, "config"}) {
            MyApplication.GetAppContext().getSharedPreferences(str, 0).edit().clear().apply();
        }
    }

    public static boolean put(String str, Object obj) {
        SharedPreferences.Editor edit = MyApplication.GetAppContext().getSharedPreferences(FILE_NAME, 0).edit();
        if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else {
            edit.putString(str, null);
        }
        return edit.commit();
    }

    public static Object get(String str, Object obj) {
        SharedPreferences sharedPreferences = MyApplication.GetAppContext().getSharedPreferences(FILE_NAME, 0);
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        if (!(obj instanceof String)) {
            return null;
        }
        return sharedPreferences.getString(str, (String) obj);
    }

    public static boolean remove(String str) {
        return MyApplication.GetAppContext().getSharedPreferences(FILE_NAME, 0).edit().remove(str).commit();
    }

    public static Map<String, ?> getAll() {
        return MyApplication.GetAppContext().getSharedPreferences(FILE_NAME, 0).getAll();
    }

    public static boolean clear() {
        return MyApplication.GetAppContext().getSharedPreferences(FILE_NAME, 0).edit().clear().commit();
    }

    public static boolean contains(String str) {
        return MyApplication.GetAppContext().getSharedPreferences(FILE_NAME, 0).contains(str);
    }
}
