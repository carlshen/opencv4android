package com.book.handprobe;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import gloomyfish.opencvdemo.MyApplication;
import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class LanguageUtil {
    public static final int CHINESE = 1;
    public static final int ENGLISH = 0;
    public static final String HandProbelanguage = "handprobe_language";

    public static int getLanguageIndex(Context context) {
        return context.getSharedPreferences(HandProbelanguage, 0).getInt(HandProbelanguage, 0);
    }

    public static boolean setLanguage(Context context, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(HandProbelanguage, 0).edit();
        edit.putInt(HandProbelanguage, i);
        return edit.commit();
    }

    public static String _NLS(Context context, int i, int i2) {
        return context.getResources().getStringArray(i)[i2];
    }

    public static String _NLS(Context context, int i) {
        return context.getResources().getStringArray(i)[getLanguageIndex(context)];
    }

    public static String _NLS(int i, int i2) {
        return _NLS(MyApplication.GetAppContext(), i, i2);
    }

    public static String _NLS(int i) {
        return _NLS(MyApplication.GetAppContext(), i);
    }

    public static int getLocaleIndex() {
        String language = Locale.getDefault().getLanguage();
        if (language.equalsIgnoreCase("zh")) {
            return 1;
        }
        return language.equalsIgnoreCase("en") ? 0 : 0;
    }

    public static String[] getSupportLanguages() {
        String[] stringArray = MyApplication.App().getResources().getStringArray(R.array.language_type);
        String string = MyApplication.GetAppContext().getResources().getString(R.string.language);
        if (string.isEmpty()) {
            return stringArray;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < stringArray.length; i++) {
            if (string.contains("LAN" + String.valueOf(i) + ".")) {
                arrayList.add(stringArray[i]);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static SimpleDateFormat getSimpleDateFormat(int i, boolean z) {
        Locale locale;
        StringBuilder sb = new StringBuilder();
        switch (i) {
            case 0:
                sb.append("dd/MM/yyyy");
                if (z) {
                    sb.append(" HH:mm:ss");
                }
                locale = Locale.ENGLISH;
                break;
            case 1:
                sb.append("yyyy/MM/dd");
                if (z) {
                    sb.append(" HH:mm:ss");
                }
                locale = Locale.CHINESE;
                break;
            default:
                sb.append("yyyy/MM/dd");
                if (z) {
                    sb.append(" HH:mm:ss");
                }
                locale = Locale.getDefault();
                break;
        }
        return new SimpleDateFormat(sb.toString(), locale);
    }

    public static SimpleDateFormat getDateFormat(Locale locale, boolean z) {
        if (z) {
            return (SimpleDateFormat) DateFormat.getDateTimeInstance(2, 2, locale);
        }
        return (SimpleDateFormat) DateFormat.getDateInstance(2, locale);
    }
}
