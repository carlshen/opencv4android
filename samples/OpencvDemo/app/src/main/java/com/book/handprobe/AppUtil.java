package com.book.handprobe;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Iterator;
import java.util.List;

import gloomyfish.opencvdemo.MyApplication;
import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class AppUtil {
    public static boolean isBackground(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 400) {
                    Log.i("后台", runningAppProcessInfo.processName);
                    return true;
                }
                Log.i("前台", runningAppProcessInfo.processName);
                return false;
            }
        }
        return false;
    }

    public static boolean isApplicationBroughtToBackground(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1);
        return !runningTasks.isEmpty() && !runningTasks.get(0).topActivity.getPackageName().equals(context.getPackageName());
    }

    public static boolean isBackground() {
        return isBackground(MyApplication.GetAppContext());
    }

    public static boolean restartApplication(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(launchIntentForPackage);
        return true;
    }

    public static boolean restartApplication2(Context context, long j) {
        PendingIntent activity = PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + j, activity);
            exitApp(0);
            return true;
        }
        exitApp(0);
        return false;
    }

    public static boolean restartApplication2(Context context) {
        return restartApplication2(context, 1000L);
    }

    @Deprecated
    public static void moveAppToFront(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        context.getSystemService(Context.ACTIVITY_SERVICE);
        Iterator<ActivityManager.RunningTaskInfo> it = activityManager.getRunningTasks(30).iterator();
        if (it.hasNext()) {
            ActivityManager.RunningTaskInfo next = it.next();
            if (Build.VERSION.SDK_INT < 11) {
                return;
            }
            activityManager.moveTaskToFront(next.id, 0);
        }
    }

    public static void exitApp(int i) {
        Process.killProcess(Process.myPid());
        System.exit(i);
    }

    public static synchronized String getAppName(Context context) {
        String string;
        synchronized (AppUtil.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0018 A[Catch: all -> 0x0012, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0004, B:6:0x0008, B:13:0x0018), top: B:22:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized String getAppName2(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo;
        synchronized (AppUtil.class) {
            packageManager = context.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
                applicationInfo = null;
                if (applicationInfo == null) {
                }
            }
            if (applicationInfo == null) {
                return packageManager.getApplicationLabel(applicationInfo).toString();
            }
            return "";
        }
    }

    public static synchronized String getVersionName(Context context) {
        String str;
        synchronized (AppUtil.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return str;
    }

    public static synchronized int getVersionCode(Context context) {
        int i;
        synchronized (AppUtil.class) {
            try {
                i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return i;
    }

    public static synchronized String getPackageName(Context context) {
        String str;
        synchronized (AppUtil.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return str;
    }

    public static synchronized Bitmap getBitmap(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo;
        synchronized (AppUtil.class) {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
                applicationInfo = null;
                return ((BitmapDrawable) packageManager.getApplicationIcon(applicationInfo)).getBitmap();
            }
        }
        return ((BitmapDrawable) packageManager.getApplicationIcon(applicationInfo)).getBitmap();
    }

    public static void exitAppWithDialog(Context context) {
//        new AlertDialog.Builder(context, R.style.alert_dialog_custom).setTitle(LanguageUtil._NLS(R.array.Tip)).setMessage(LanguageUtil._NLS(R.array.exit_app_msg)).setNegativeButton(LanguageUtil._NLS(R.array.cancel), new DialogInterface.OnClickListener() { // from class: handprobe.components.utils.AppUtil.2
//            @Override // android.content.DialogInterface.OnClickListener
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        }).setPositiveButton(LanguageUtil._NLS(R.array.ok), new DialogInterface.OnClickListener() { // from class: handprobe.components.utils.AppUtil.1
//            @Override // android.content.DialogInterface.OnClickListener
//            public void onClick(DialogInterface dialogInterface, int i) {
//                MyApplication.App().GetActivity().offWifi();
//                AppUtil.exitApp(0);
//            }
//        }).show();
    }

    public static boolean hasExternalStoragePermission() {
        return MyApplication.GetAppContext().checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
    }

    public static void showRequestStoragePermissionDialog(final Context context) {
//        new AlertDialog.Builder(context, R.style.alert_dialog_custom).setTitle(LanguageUtil._NLS(R.array.prompt)).setMessage(LanguageUtil._NLS(R.array.request_permission)).setNegativeButton(LanguageUtil._NLS(R.array.cancel), new DialogInterface.OnClickListener() { // from class: handprobe.components.utils.AppUtil.4
//            @Override // android.content.DialogInterface.OnClickListener
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        }).setPositiveButton(LanguageUtil._NLS(R.array.ok), new DialogInterface.OnClickListener() { // from class: handprobe.components.utils.AppUtil.3
//            @Override // android.content.DialogInterface.OnClickListener
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent();
//                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//                String str = MyApplication.App().getApplicationInfo().processName;
//                intent.setData(Uri.parse("package:" + str));
//                context.startActivity(intent);
//            }
//        }).show();
    }
}
