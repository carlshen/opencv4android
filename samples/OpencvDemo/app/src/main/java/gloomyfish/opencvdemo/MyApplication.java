package gloomyfish.opencvdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;

public class MyApplication  extends Application {
//    public static User Current_User;
    private static Context mContext;
    private DbManager.DaoConfig daoConfig;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    public MainActivity mMainActivity;
//    private HPatientMng mPatientMng;
//    public SplashActivity mSplashActivity;
//    private ThirdPartyManageActivity mTPManageActivity;
    private DbManager.DaoConfig userdaoConfig;
    private boolean mIsThirdPartyEntered = false;
    private boolean mHasTouchScreen0 = false;

    public static Context GetAppContext() {
        if (mContext != null) {
            return mContext;
        }
        throw new IllegalStateException("mContext == null");
    }

    public static MyApplication App() {
        return (MyApplication) mContext;
    }

    public DbManager.DaoConfig getDaoConfig() {
        return this.daoConfig;
    }

    public DbManager.DaoConfig getUserdaoConfig() {
        return this.userdaoConfig;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        this.userdaoConfig = new DbManager.DaoConfig().setDbDir(new File(getExternalFilesDir(null), "UserInfo/database")).setDbName("sonoiq.db").setDbVersion(1).setDbOpenListener(new DbManager.DbOpenListener() { // from class: com.Com.application.MyApplication.2
            @Override // org.xutils.DbManager.DbOpenListener
            public void onDbOpened(DbManager dbManager) {
                dbManager.getDatabase().enableWriteAheadLogging();
            }
        }).setDbUpgradeListener(new DbManager.DbUpgradeListener() { // from class: com.Com.application.MyApplication.1
            @Override // org.xutils.DbManager.DbUpgradeListener
            public void onUpgrade(DbManager dbManager, int i, int i2) {
                while (true) {
                    i++;
                    if (i <= i2) {
                        MyApplication.this.upgradeAnimalDbTo(dbManager, i);
                    } else {
                        return;
                    }
                }
            }
        });
        this.defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.Com.application.MyApplication.3
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                if (!MyApplication.this.handleException(th) && MyApplication.this.defaultUncaughtExceptionHandler != null) {
                    MyApplication.this.defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                    return;
                }
                SystemClock.sleep(3000L);
//                if (MyApplication.this.mTPManageActivity != null && MyApplication.this.mIsThirdPartyEntered) {
//                    Bundle thirdPartyExtras = MyApplication.this.getThirdPartyExtras();
//                    thirdPartyExtras.putString(ThirdPartyManageActivity.ERROR, "App crashed.");
//                    thirdPartyExtras.putInt(ThirdPartyManageActivity.ERROR, 4);
//                    MyApplication.this.returnThirdParty(thirdPartyExtras);
//                }
//                if (MyApplication.this.mSplashActivity != null) {
//                    MyApplication.this.mSplashActivity.finish();
//                }
//                if (MyApplication.this.mMainActivity != null) {
//                    MyApplication.this.mMainActivity.finish();
//                }
//                AppUtil.exitApp(1);
            }
        });
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.Com.application.MyApplication.4
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
//                if (activity instanceof SplashActivity) {
//                    MyApplication.this.mSplashActivity = (SplashActivity) activity;
//                } else if (activity instanceof BaseActivity) {
//                    MyApplication.this.mMainActivity = (BaseActivity) activity;
//                } else if (!(activity instanceof ThirdPartyManageActivity)) {
//                } else {
//                    MyApplication.this.mTPManageActivity = (ThirdPartyManageActivity) activity;
//                    MyApplication.this.mIsThirdPartyEntered = true;
//                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
//                if (activity instanceof SplashActivity) {
//                    MyApplication.this.mSplashActivity = (SplashActivity) activity;
//                } else if (activity instanceof BaseActivity) {
//                    MyApplication.this.mMainActivity = (BaseActivity) activity;
//                } else if (!(activity instanceof ThirdPartyManageActivity)) {
//                } else {
//                    MyApplication.this.mTPManageActivity = (ThirdPartyManageActivity) activity;
//                    MyApplication.this.mIsThirdPartyEntered = true;
//                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
//                if (activity instanceof SplashActivity) {
//                    MyApplication.this.mSplashActivity = null;
//                } else if (activity instanceof BaseActivity) {
//                    MyApplication.this.mMainActivity.offWifi();
//                    MyApplication.this.mMainActivity = null;
//                } else if (!(activity instanceof ThirdPartyManageActivity)) {
//                } else {
//                    MyApplication.this.mTPManageActivity = null;
//                    MyApplication.this.mIsThirdPartyEntered = false;
//                }
            }
        });
    }

    private void createDB() {
        if (this.daoConfig != null) {
            return;
        }
        String str = Environment.getExternalStorageDirectory().getAbsolutePath() + getApplicationContext().getResources().getString(R.string.patient_database);
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            return;
        }
        String str2 = getExternalFilesDir(null).getAbsolutePath() + "/PatientInfo/database";
        if (new File(str2).exists()) {
//            PodFilesSys.copyFolder(str2, str);
//            FileOperate.deleteDirectory(str2);
        }
        this.daoConfig = new DbManager.DaoConfig().setDbDir(file).setDbName("sonoiq.db").setDbVersion(1).setDbOpenListener(new DbManager.DbOpenListener() { // from class: com.Com.application.MyApplication.6
            @Override // org.xutils.DbManager.DbOpenListener
            public void onDbOpened(DbManager dbManager) {
                dbManager.getDatabase().enableWriteAheadLogging();
            }
        }).setDbUpgradeListener(new DbManager.DbUpgradeListener() { // from class: com.Com.application.MyApplication.5
            @Override // org.xutils.DbManager.DbUpgradeListener
            public void onUpgrade(DbManager dbManager, int i, int i2) {
                while (true) {
                    i++;
                    if (i <= i2) {
                        MyApplication.this.upgradeTo(dbManager, i);
                    } else {
                        return;
                    }
                }
            }
        });
    }

//    public final HPatientMng GetPatientMng() {
//        if (this.mPatientMng == null) {
//            this.mPatientMng = new HPatientMng(getDb());
//        }
//        return this.mPatientMng;
//    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeTo(DbManager dbManager, int i) {
        switch (i) {
            case 2:
            case 3:
                return;
            default:
                throw new IllegalStateException("Don't know how to upgrade to version " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeAnimalDbTo(DbManager dbManager, int i) {
        switch (i) {
            case 2:
            case 3:
                return;
            default:
                throw new IllegalStateException("Don't know how to upgrade to version " + i);
        }
    }

    public DbManager getDb() throws DbException {
        createDB();
        return x.getDb(this.daoConfig);
    }

//    public Exam getActiveExam() throws DbException {
//        return (Exam) getDb().selector(Exam.class).where("examState", "=", 1).orderBy("id", true).findFirst();
//    }
//
//    public AnimalExam getAnimalActiveExam() throws DbException {
//        return (AnimalExam) getDb().selector(AnimalExam.class).where("examState", "=", 1).orderBy("id", true).findFirst();
//    }

    public DbManager getUserDb() throws DbException {
        return x.getDb(this.userdaoConfig);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
//        LogUtil.file("onLowMemory");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.Com.application.MyApplication$7] */
    public boolean handleException(Throwable th) {
        if (th == null) {
            return false;
        }
        Log.e("crash log", "********************************crash reason**************************************");
        Log.e("crash log", th.getLocalizedMessage());
        th.printStackTrace();
//        LogUtil.file(th);
//        new Thread() { // from class: com.Com.application.MyApplication.7
//            @Override // java.lang.Thread, java.lang.Runnable
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(MyApplication.mContext, LanguageUtil._NLS(R.array.app_crashed), 1).show();
//                Looper.loop();
//            }
//        }.start();
        return true;
    }

    public MainActivity GetActivity() {
        return this.mMainActivity;
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
    }

    public boolean isThirdParaty() {
        return this.mIsThirdPartyEntered;
    }

//    public Bundle getThirdPartyExtras() {
//        if (this.mTPManageActivity == null || !this.mIsThirdPartyEntered || this.mTPManageActivity.getIntent() == null) {
//            return null;
//        }
//        return this.mTPManageActivity.getIntent().getExtras();
//    }
//
//    public void returnThirdParty(Bundle bundle) {
//        Intent intent = new Intent();
//        if (this.mMainActivity != null) {
//            this.mMainActivity.moveTaskToBack(true);
//            this.mMainActivity.setIntent(null);
//        }
//        if (this.mTPManageActivity != null) {
//            this.mTPManageActivity.returnThirdParty(intent, bundle);
//        }
//    }

    public boolean HasTouchScreen0() {
        return this.mHasTouchScreen0;
    }
}
