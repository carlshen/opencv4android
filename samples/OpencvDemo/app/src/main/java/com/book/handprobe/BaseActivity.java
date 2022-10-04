package com.book.handprobe;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

//import com.anggrayudi.hiddenapi.r.Rc;
import com.book.tools.HWifiReceiver;
import com.book.tools.ProbeWifiInfo;
import com.book.tools.WlanProbe;
//import handprobe.application.app.AppProc;
//import handprobe.application.gui.MainParamView;
//import handprobe.application.gui.fragment.GenStatusBarFragment;
//import handprobe.application.gui.fragment.ImageDisplayFragment;
//import handprobe.application.gui.menu.ParamsDisplayLayout;
//import handprobe.application.preset.PresetFile;
//import handprobe.application.ultrasys.Ultrasys;
//import handprobe.application.ultrasys.parameter.FuncidMap;
//import handprobe.application.ultrasys.probe.ProbeWifiInfo;
//import handprobe.application.usb.UsbProbeManager;
//import handprobe.application.wlan.wifi.HWifiReceiver;
//import handprobe.application.wlan.wlanprobe.WlanProbe;
//import handprobe.components.BroadCast.BatteryBroadCastReciever;
//import handprobe.components.BroadCast.TimeBroadCastReciever;
//import handprobe.components.file.ReadBinAssetsFiles;
//import handprobe.components.input.touch.TouchScreen0;
//import handprobe.components.permission.PermissionManager;
//import handprobe.components.ultrasys.cine.RecordService;
//import handprobe.components.ultrasys.parameter.BDspPara;
//import handprobe.components.ultrasys.parameter.BScanPara;
//import handprobe.components.ultrasys.parameter.CDspPara;
//import handprobe.components.ultrasys.parameter.CScanPara;
//import handprobe.components.ultrasys.parameter.MDspPara;
//import handprobe.components.ultrasys.parameter.MScanPara;
//import handprobe.components.ultrasys.parameter.PWDspPara;
//import handprobe.components.ultrasys.parameter.PWScanPara;
//import handprobe.components.usb.CurProbeObservable;
//import handprobe.components.usb.HLibUsb;
//import handprobe.components.usb.HUsbReceiver;
//import handprobe.components.usb.UsbProbeInfo;
//import handprobe.components.utils.AppUtil;
//import handprobe.components.utils.LanguageUtil;
//import handprobe.components.utils.SPUtil;
//import handprobe.components.utils.ScreenUtil;
//import handprobe.components.utils.ToastUtil;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import org.xutils.x;

import gloomyfish.opencvdemo.MyApplication;
import gloomyfish.opencvdemo.R;

/* loaded from: classes.dex */
public class BaseActivity extends FragmentActivity {
    public static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    public static final int LOCATION_REQUEST_CODE = 104;
    public static final int RECORD_REQUEST_CODE = 101;
    public static final int STORAGE_AUDIO_REQUEST_CODE = 102;
    public static final int STORAGE_REQUEST_CODE = 103;
    public static final String TAG = "BaseActivity";
    public ViewGroup biopsyLayout;
    public ViewGroup cineLayout;
    public ViewGroup imagelayout;
    @LayoutRes
    protected int layout;
    private ViewGroup mContentLayout;
//    public HLibUsb.HUsbDev mCurProbeDev;
//    public CurProbeObservable mCurProbeObservable;
    public float mDensity;
    public int mDensityDpi;
    public DrawerLayout mDrawerLayout;
    public long mExitTime;
//    public GenStatusBarFragment mGenStatusBarFragment;
    public GridView mGridView;
    public Handler mHandler;
//    public ImageDisplayFragment mImageDisplayFragment;
//    public PresetFile.LanguagePresetObservable mLanguage;
//    protected MainParamView mMainParamView;
//    public ParamsDisplayLayout mParamsDisplayLayout;
//    public PermissionManager mPermissionManager;
    BroadcastReceiver mPowerKeyInfoReceiver;
//    public PresetFile mPresetFile;
//    public List<UsbProbeInfo> mProbeList;
    public LinearLayout mRightLayout;
    public int mScreenHeightInDp;
    public int mScreenHeightInPx;
    public int mScreenWidthInDp;
    public int mScreenWidthInPx;
    protected SharedPreferences mSharedPreferences;
//    protected TimeBroadCastReciever mTimeReceiver;
//    public HUsbReceiver mUsbReceiver;
    public WifiInfo mWifiInfo;
    WifiManager mWifiManager;
    private Timer mWifiOffTimer;
    private TimerTask mWifiOffTimerTask;
    HWifiReceiver mWifiReceiver;
    public ViewGroup mainDisplayLayout;
    public ViewGroup mainmenulayout;
    public MediaProjection mediaProjection;
    public MediaProjectionManager projectionManager;
//    public RecordService recordService;
    public ViewGroup thumbnailLayout;
//    protected Ultrasys.CallBack mUltrasysCallback = null;
//    protected WlanProbe.MsgHook mWlanMsgHook = null;
    boolean IsRun = false;
    boolean mIsWlanInit = false;
//    protected BatteryBroadCastReciever mBatteryReceiver = null;
    public int mAppBrightness = 255;
    protected boolean mWifiReciverTag = false;
    protected boolean mBatteryReceiverTag = false;
    protected boolean mTimeReceiverTag = false;
    protected boolean mPowerKeyReceiverTag = false;
    protected boolean mUsbReceiverTag = false;
    public boolean mSystemWifiEnter = false;
    public boolean mIsFirstEnter = true;
    public ArrayList<Integer> mCLayoutIds = new ArrayList<>();
    public ArrayList<Integer> mBLayoutIds = new ArrayList<>();
    public ArrayList<Integer> mMLayoutIds = new ArrayList<>();
    public ArrayList<Integer> mPLayoutIds = new ArrayList<>();
    public ArrayList<Integer> mPWLayoutIds = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> mLayoutIds = new ArrayList<>();
    public int mCurDispMode = -1;
    public final String StartAction = "save.frame.data.action.start";
    public final String EndAction = "save.frame.data.action.end";
    public final String SaveDir = "savedir";
//    public final String SaveTime = Rc.id.time;
    private BroadcastReceiver mSaveFrameDataToDirBroadcastReceiver = new BroadcastReceiver() { // from class: com.Com.handprobe.BaseActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != -2030177867) {
                if (hashCode == -1072668996 && action.equals("save.frame.data.action.start")) {
                    c = 0;
                }
            } else if (action.equals("save.frame.data.action.end")) {
                c = 1;
            }
            switch (c) {
                case 0:
                    String stringExtra = intent.getStringExtra("savedir");
//                    int intExtra = intent.getIntExtra(Rc.id.time, 0);
//                    if (!TextUtils.isEmpty(stringExtra)) {
//                        File file = new File(stringExtra);
//                        if (!file.exists()) {
//                            file.mkdirs();
//                        }
//                        if (file.canWrite()) {
//                            if (Ultrasys.Instance().mImagePlayer != null && Ultrasys.Instance().mImagePlayer.executorService == null && Ultrasys.Instance().mImagePlayer.executorService2 == null) {
//                                Ultrasys.Instance().mImagePlayer.executorService = Executors.newCachedThreadPool();
//                                int availableProcessors = Runtime.getRuntime().availableProcessors();
//                                if (availableProcessors <= 0) {
//                                    availableProcessors = 8;
//                                }
//                                Ultrasys.Instance().mImagePlayer.executorService2 = Executors.newFixedThreadPool(availableProcessors * 3);
//                            }
//                            Ultrasys.Instance().mSaveFrameDataDirFile = new File(stringExtra);
//                            Ultrasys.Instance().mIsSaveFrameDataToDir = true;
//                            ToastUtil.showToastShort("start to save frame to " + stringExtra + ".");
//                        } else {
//                            ToastUtil.showToastShort(stringExtra + " isn't exist or error occured.");
//                        }
//                    }
//                    if (intExtra <= 0) {
//                        return;
//                    }
//                    Ultrasys.Instance().mSaveFrameDataTime = System.currentTimeMillis() + (intExtra * 1000);
                    return;
                case 1:
//                    if (Ultrasys.Instance().mSaveFrameDataDirFile != null) {
//                        ToastUtil.showToastShort("save frame to " + Ultrasys.Instance().mSaveFrameDataDirFile.getAbsolutePath() + " end.");
//                    }
//                    Ultrasys.Instance().mIsSaveFrameDataToDir = false;
//                    Ultrasys.Instance().mSaveFrameDataDirFile = null;
                    return;
                default:
                    return;
            }
        }
    };
    private ServiceConnection connection = new ServiceConnection() { // from class: com.Com.handprobe.BaseActivity.3
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            Point realScreenSize = ScreenUtil.getRealScreenSize(BaseActivity.this);
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            BaseActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            BaseActivity.this.recordService = ((RecordService.RecordBinder) iBinder).getRecordService();
//            BaseActivity.this.recordService.setConfig(realScreenSize.x, realScreenSize.y, displayMetrics.densityDpi);
        }
    };

    public void EnterMeas() {
    }

    public void ExitMeas() {
    }

    protected void ParaObserverInit() {
    }

    public void SetWifiStrength(float f) {
    }

    public void UpdateDispImgParam(int i, boolean z) {
    }

    protected void initMainActivity() {
    }

    public void setHasPatient(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    @SuppressLint({"ResourceType"})
    @TargetApi(21)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        obtainScreenSize();
        ScreenUtil.loadPreferOrientation(this);
        setContentView(R.layout.drawer_layout);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mDrawerLayout.setScrimColor(0);
        this.mDrawerLayout.setDrawerLockMode(1, GravityCompat.END);
        initContentLayout();
        ScreenUtil.KeepFullScreen(getWindow(), this.mContentLayout);
//        this.mPermissionManager = new PermissionManager(this);
//        this.mCurProbeObservable = new CurProbeObservable(this, null);
        initRecordService();
        initMainActivity();
        ScreenUtil.refreshWindow(this.mContentLayout);
        RegisterPara();
        initParamView();
        initApp();
        setDefaultFontScale();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("save.frame.data.action.start");
        intentFilter.addAction("save.frame.data.action.end");
        registerReceiver(this.mSaveFrameDataToDirBroadcastReceiver, intentFilter);
        Log.i("生命周期：", "onCreate()");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        UltraSysInit();
//        if (!this.mIsWlanInit) {
//            WlanProbe.Instance().Init();
//            WlanProbe.Instance().SetMsgHook(this.mWlanMsgHook);
//        }
        this.mIsWlanInit = true;
        setDefaultFontScale();
        onWifi();
        onWifiMsg();
        basRemListProbeConfigInfos();
//        if (getResources().getString(R.string.wifi_connect_auto).equals("on")) {
//            WifiConnectToLastAP();
//        }
//        if (Build.VERSION.SDK_INT >= 19 && !NotificationManagerCompat.from(this).areNotificationsEnabled()) {
//            this.mPermissionManager.showTipsDialog(0);
//        }
//        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
//        if (!this.mPermissionManager.checkPermissions(strArr)) {
//            this.mPermissionManager.requestPermission(this.mPermissionManager.getUnGrantedPermissions(strArr), 102);
//        }
//        String[] strArr2 = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
//        if (!this.mPermissionManager.checkPermissions(strArr2)) {
//            this.mPermissionManager.requestPermission(this.mPermissionManager.getUnGrantedPermissions(strArr2), 104);
//        } else if (!this.mPermissionManager.isLocationOpen()) {
//            this.mPermissionManager.showTipsDialog(3);
//        }
        Log.i("生命周期：", "onStart()");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ScreenUtil.KeepFullScreen(getWindow(), this.mContentLayout);
        registerSysReceivers();
        this.mSystemWifiEnter = false;
        if (this.mWifiOffTimer != null) {
            this.mWifiOffTimer.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        ScreenUtil.FullScreen(getWindow(), this.mContentLayout);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        ScreenUtil.FullScreen(getWindow(), this.mContentLayout);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        unRegisterSysReceivers();
        Log.i("生命周期：", "onPause()");
    }

    private TimerTask WifiOffTimeTask() {
        this.mWifiOffTimerTask = new TimerTask() { // from class: com.Com.handprobe.BaseActivity.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                x.task().post(new Runnable() { // from class: com.Com.handprobe.BaseActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
//                        WlanProbe.Instance().resetWlanProbeId();
                        BaseActivity.this.offWifi();
                    }
                });
            }
        };
        return this.mWifiOffTimerTask;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mWifiOffTimer = new Timer();
        this.mWifiOffTimer.schedule(WifiOffTimeTask(), 30000L);
//        getResources().getString(R.string.wifi_connect_auto).equals("on");
        basRemListProbeConfigInfos();
        Log.i("再按一次返回", "stop");
        Log.i("生命周期：", "onStop()");
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        offWifi();
        basRemListProbeConfigInfos();
//        if (this.recordService != null && this.recordService.screenRecorder != null) {
//            this.recordService.screenRecorder.quit();
//            this.recordService.screenRecorder = null;
//        }
        if (this.projectionManager != null && this.connection != null) {
            unbindService(this.connection);
            this.connection = null;
        }
        WlanProbe.Instance().getmICmdHandler().close();
        Log.i("生命周期：", "finish()");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.projectionManager != null && this.connection != null) {
            unbindService(this.connection);
            this.connection = null;
        }
        WlanProbe.Instance().getmICmdHandler().close();
        offWifi();
        basRemListProbeConfigInfos();
//        if (this.recordService != null && this.recordService.screenRecorder != null) {
//            this.recordService.screenRecorder.quit();
//            this.recordService.screenRecorder = null;
//        }
        if (this.mSaveFrameDataToDirBroadcastReceiver != null) {
            unregisterReceiver(this.mSaveFrameDataToDirBroadcastReceiver);
            this.mSaveFrameDataToDirBroadcastReceiver = null;
        }
        Log.i("再按一次返回", "destroy");
        Log.i("生命周期：", "onDestroy()");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initParamView() {
//        this.mMainParamView = new MainParamView(this);
    }

    protected void initApp() {
//        this.mPresetFile = PresetFile.Instance();
//        Ultrasys.Instance().SetPresetFile(this.mPresetFile);
//        PresetFile presetFile = this.mPresetFile;
//        presetFile.getClass();
//        this.mLanguage = new PresetFile.LanguagePresetObservable();
//        AppProc.Instance().SetActivity(this);
        if (getApplicationContext().getResources().getString(R.string.def_vet).equals("on")) {
            Ultrasys.Instance().SetVet(1);
        } else {
            Ultrasys.Instance().SetVet(0);
        }
    }

    protected void initRecordService() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        }
//        if (this.projectionManager != null) {
//            bindService(new Intent(this, RecordService.class), this.connection, 1);
//        }
    }

    public void setDefaultFontScale() {
        Configuration configuration = getResources().getConfiguration();
        if (configuration.fontScale != 1.0f) {
            configuration.fontScale = 1.0f;
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }
    }

    private void RegisterPara() {
//        FuncidMap funcidMap = Ultrasys.Instance().mFuncIdMap;
//        BScanPara bScanPara = Ultrasys.Instance().mBScanPara;
//        BDspPara bDspPara = Ultrasys.Instance().mBDspPara;
//        funcidMap.SetFuncidMap(2, bScanPara.mDepth);
//        funcidMap.SetFuncidMap(4, bScanPara.mFreq);
//        funcidMap.SetFuncidMap(5, bScanPara.mFocus);
//        funcidMap.SetFuncidMap(9, bScanPara.mTsi);
//        funcidMap.SetFuncidMap(13, bScanPara.mSteer);
//        funcidMap.SetFuncidMap(12, bScanPara.mAPower);
//        funcidMap.SetFuncidMap(8, bScanPara.mBExpand);
//        funcidMap.SetFuncidMap(6, bDspPara.mDynamic);
//        funcidMap.SetFuncidMap(3, bDspPara.mGain);
//        funcidMap.SetFuncidMap(10, bDspPara.mGray);
//        funcidMap.SetFuncidMap(11, bDspPara.mEnhance);
//        funcidMap.SetFuncidMap(7, bDspPara.mFrameCorre);
//        funcidMap.SetFuncidMap(14, bDspPara.mFrameComp);
//        funcidMap.SetFuncidMap(176, bDspPara.mBFlipHor);
//        funcidMap.SetFuncidMap(177, bDspPara.mBFlipVer);
//        CScanPara cScanPara = Ultrasys.Instance().mCScanPara;
//        CDspPara cDspPara = Ultrasys.Instance().mCDspPara;
//        funcidMap.SetFuncidMap(22, cScanPara.mScale);
//        funcidMap.SetFuncidMap(23, cScanPara.mFreq);
//        funcidMap.SetFuncidMap(25, cScanPara.mSteer);
//        funcidMap.SetFuncidMap(21, cDspPara.mGain);
//        funcidMap.SetFuncidMap(26, cDspPara.mWallFilter);
//        funcidMap.SetFuncidMap(24, cDspPara.mFrameCorre);
//        funcidMap.SetFuncidMap(27, cDspPara.mPriority);
//        funcidMap.SetFuncidMap(28, cDspPara.mVelColorMap);
//        funcidMap.SetFuncidMap(29, cDspPara.mGain);
//        funcidMap.SetFuncidMap(30, cScanPara.mScale);
//        funcidMap.SetFuncidMap(31, cScanPara.mFreq);
//        funcidMap.SetFuncidMap(34, cDspPara.mWallFilter);
//        funcidMap.SetFuncidMap(35, cDspPara.mFrameCorre);
//        funcidMap.SetFuncidMap(36, cDspPara.mPriority);
//        funcidMap.SetFuncidMap(37, cDspPara.mPowerColorMap);
//        funcidMap.SetFuncidMap(32, cScanPara.mSteer);
//        PWScanPara pWScanPara = Ultrasys.Instance().mPWScanPara;
//        PWDspPara pWDspPara = Ultrasys.Instance().mPWDspPara;
//        funcidMap.SetFuncidMap(84, pWScanPara.mSteer);
//        funcidMap.SetFuncidMap(85, pWScanPara.mFreq);
//        funcidMap.SetFuncidMap(86, pWScanPara.mScale);
//        funcidMap.SetFuncidMap(87, pWDspPara.mGain);
//        funcidMap.SetFuncidMap(88, pWDspPara.mWallFilter);
//        funcidMap.SetFuncidMap(89, pWDspPara.mSv);
//        funcidMap.SetFuncidMap(90, pWDspPara.mSVD);
//        funcidMap.SetFuncidMap(71, pWDspPara.mBaseLine);
//        funcidMap.SetFuncidMap(78, pWDspPara.mPwReverse);
//        funcidMap.SetFuncidMap(81, pWDspPara.mPwDynamic);
//        funcidMap.SetFuncidMap(72, pWScanPara.mSpeed);
//        funcidMap.SetFuncidMap(80, pWDspPara.mPwVolume);
//        funcidMap.SetFuncidMap(82, pWDspPara.mPwAutoCalc);
//        funcidMap.SetFuncidMap(94, bScanPara.mAPower);
//        funcidMap.SetFuncidMap(74, pWDspPara.mPwAngle);
//        funcidMap.SetFuncidMap(73, pWDspPara.mPwQuickAngle);
//        MDspPara mDspPara = Ultrasys.Instance().mMDspPara;
//        MScanPara mScanPara = Ultrasys.Instance().mMScanPara;
//        funcidMap.SetFuncidMap(61, mDspPara.mGain);
//        funcidMap.SetFuncidMap(62, mScanPara.mSpeed);
//        funcidMap.SetFuncidMap(63, mDspPara.mDynamic);
//        funcidMap.SetFuncidMap(66, mDspPara.mLineCorre);
//        funcidMap.SetFuncidMap(64, mDspPara.mGray);
    }

    public void Refresh() {
        getWindow().setFlags(128, 128);
//        this.mImageDisplayFragment.mCommentView.Clear();
//        this.mImageDisplayFragment.setSampleWinTouchEventValid(false);
//        this.mImageDisplayFragment.mTimeScale.setFocusDepth(0.0f);
//        this.mImageDisplayFragment.SetDisplayMode(Ultrasys.Instance().mDispMode.mMode);
//        this.mMainParamView.UpdateSampleWin();
//        this.mMainParamView.UpdateProbeMark();
    }

    public void setGrayColorMaps(int[][] iArr, int[][] iArr2, int[][] iArr3) {
//        this.mImageDisplayFragment.setGrayColorMaps(iArr, iArr2, iArr3);
    }

    private void UltraSysInit() {
        if (!this.IsRun) {
//            int[][] readGreyMap = ReadBinAssetsFiles.readGreyMap(this, ReadBinAssetsFiles.mBGreymapFileName);
//            Ultrasys.Instance().InitGreyColorMaps(readGreyMap, ReadBinAssetsFiles.readColorSpectrum(this, ReadBinAssetsFiles.mVmapFileName), ReadBinAssetsFiles.readColorSpectrum(this, ReadBinAssetsFiles.mPmapFileName));
//            if (readGreyMap != null) {
//                int[][] iArr = (int[][]) Array.newInstance(int.class, 18, 256);
//                for (int i = 0; i < 18; i++) {
//                    for (int i2 = 0; i2 < 256; i2++) {
//                        iArr[i][i2] = readGreyMap[i][(256 - i2) - 1];
//                    }
//                }
//                setGrayColorMaps(iArr, Ultrasys.Instance().mCDspPara.mVelColorMap.mVelColorMap, Ultrasys.Instance().mCDspPara.mPowerColorMap.mPowerColorMap);
//            }
//            Ultrasys.Instance().ImageInit(this.mImageDisplayFragment.mGLImageView, this.mImageDisplayFragment.mGLImageViewOneDim, this.mImageDisplayFragment.mGLImageViewTwoDim);
//            Ultrasys.Instance().setCallback(this.mUltrasysCallback);
//            this.mImageDisplayFragment.initGrayMaps(Ultrasys.Instance().mBDspPara.mGray.GetCurMapForPlayer(), Ultrasys.Instance().mCDspPara.mVelColorMap.GetCurMapForPlayer(), Ultrasys.Instance().mCDspPara.mPowerColorMap.GetCurMapForPlayer());
//            this.mMainParamView.SetAutoCalcParams();
//            this.mMainParamView.SetImgMode(0);
//            ParaObserverInit();
//            if (MyApplication.App().HasTouchScreen0()) {
//                if (TouchScreen0.Instance().Init()) {
//                    UsbProbeManager.Instance().Init();
//                }
//            } else {
//                UsbProbeManager.Instance().Init();
//            }
            this.IsRun = true;
            Log.i("UltraSysInit", "执行");
        }
    }

    public void onWifi() {
        this.mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public void onWifiMsg() {
        if (!this.mWifiReciverTag) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.mWifiReciverTag = true;
            this.mWifiReceiver = new HWifiReceiver(this);
            registerReceiver(this.mWifiReceiver, intentFilter);
        }
    }

    public void WifiConnectToLastAP() {
        this.mSharedPreferences = getSharedPreferences(getResources().getString(R.string.wifi_info_preferences), 0);
        if (this.mSharedPreferences == null || !this.mSharedPreferences.contains(getResources().getString(R.string.wifi_name_preferences)) || !this.mSharedPreferences.contains(getResources().getString(R.string.wifi_pwd_preferences))) {
            return;
        }
        ProbeWifiInfo.Connect(this.mSharedPreferences.getString(getResources().getString(R.string.wifi_name_preferences), ""), this.mSharedPreferences.getString(getResources().getString(R.string.wifi_pwd_preferences), "12345678"));
    }

    public void offWifi() {
        if (this.mSystemWifiEnter) {
            return;
        }
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        this.mWifiInfo = ProbeWifiInfo.obtainCurrentWifiInfo();
        if (this.mWifiInfo != null && this.mWifiInfo.getSSID().length() != 0) {
            wifiManager.disableNetwork(this.mWifiInfo.getNetworkId());
            wifiManager.removeNetwork(this.mWifiInfo.getNetworkId());
        }
        if (this.mWifiReceiver == null || !this.mWifiReciverTag) {
            return;
        }
        this.mWifiReciverTag = false;
        unregisterReceiver(this.mWifiReceiver);
    }

    public void unRegisterSysReceivers() {
//        if (this.mBatteryReceiver != null && this.mBatteryReceiverTag) {
//            this.mBatteryReceiverTag = false;
//            unregisterReceiver(this.mBatteryReceiver);
//        }
//        if (this.mTimeReceiver != null && this.mTimeReceiverTag) {
//            this.mTimeReceiverTag = false;
//            unregisterReceiver(this.mTimeReceiver);
//        }
        if (this.mPowerKeyInfoReceiver == null || !this.mPowerKeyReceiverTag) {
            return;
        }
        this.mPowerKeyReceiverTag = false;
        unregisterReceiver(this.mPowerKeyInfoReceiver);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    @RequiresApi(api = 16)
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (MyApplication.App().HasTouchScreen0()) {
            return false;
        }
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            exit();
            return true;
        } else if (i == 100) {
            return true;
        } else {
            if (i == 102) {
//                if (!Ultrasys.Instance().mIsUnFreeState) {
//                    AppProc.Instance().FuncIdProc(182);
//                } else {
//                    AppProc.Instance().FuncIdProc(181);
//                }
                return true;
            }
            switch (i) {
                case 19:
//                    Ultrasys.Instance().mBDspPara.mGain.inc();
//                    return true;
//                case 20:
//                    Ultrasys.Instance().mBDspPara.mGain.dec();
//                    return true;
//                case 21:
//                    Ultrasys.Instance().mBScanPara.mDepth.dec();
//                    return true;
//                case 22:
//                    Ultrasys.Instance().mBScanPara.mDepth.inc();
                    return true;
                default:
                    switch (i) {
                        case 96:
//                            AppProc.Instance().FuncIdProc(157);
                            return true;
                        case 97:
                            return true;
                        default:
                            return super.onKeyDown(i, keyEvent);
                    }
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr != null) {
            if (i == 102) {
//                if (this.mPermissionManager.verifyPermissions(iArr)) {
//                    return;
//                }
//                this.mPermissionManager.showTipsDialog(2);
            } else if (i == 104) {
//                if (this.mPermissionManager.verifyPermissions(iArr)) {
//                    return;
//                }
//                this.mPermissionManager.showTipsDialog(1);
//            } else if (i != 103 || this.mPermissionManager.verifyPermissions(iArr)) {
            } else {
//                this.mPermissionManager.showTipsDialog(4);
            }
        }
    }

//    public MainParamView getMainParamView() {
//        return this.mMainParamView;
//    }

    public void exit() {
        if (System.currentTimeMillis() - this.mExitTime > 2000) {
            Toast.makeText(getApplicationContext(), LanguageUtil._NLS(R.array.touch_again_exit_string), Toast.LENGTH_SHORT).show();
            this.mExitTime = System.currentTimeMillis();
            return;
        }
//        Bundle thirdPartyExtras = ((MyApplication) x.app()).getThirdPartyExtras();
//        if (thirdPartyExtras != null) {
//            ((MyApplication) x.app()).returnThirdParty(thirdPartyExtras);
//        }
        finish();
//        AppUtil.exitApp(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    @android.annotation.TargetApi(21)
    public void onActivityResult(int r8, int r9, android.content.Intent r10) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        super.onActivityResult(r8, r9, r10);
        throw new UnsupportedOperationException("Method not decompiled: com.Com.handprobe.BaseActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    public void Sleep(int i) {
        SystemClock.sleep(i);
    }

    public void obtainScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Point realScreenSize = ScreenUtil.getRealScreenSize(this);
        if (ScreenUtil.getPreferOrientation(this) == 7) {
            if (((Boolean) SPUtil.get(SPUtil.HideNavigationBar, Boolean.TRUE)).booleanValue()) {
                this.mScreenWidthInPx = Math.min(realScreenSize.x, realScreenSize.y);
                this.mScreenHeightInPx = Math.max(realScreenSize.x, realScreenSize.y);
            } else {
                this.mScreenWidthInPx = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
                this.mScreenHeightInPx = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
        } else if (((Boolean) SPUtil.get(SPUtil.HideNavigationBar, Boolean.TRUE)).booleanValue()) {
            this.mScreenWidthInPx = Math.max(realScreenSize.x, realScreenSize.y);
            this.mScreenHeightInPx = Math.min(realScreenSize.x, realScreenSize.y);
        } else {
            this.mScreenWidthInPx = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
            this.mScreenHeightInPx = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        this.mDensity = displayMetrics.density;
        this.mDensityDpi = displayMetrics.densityDpi;
        this.mScreenWidthInDp = px2dip(this, this.mScreenWidthInPx);
        this.mScreenHeightInDp = px2dip(this, this.mScreenHeightInPx);
    }

    public float changeAppBrightness(int i) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (i == -1) {
            attributes.screenBrightness = -1.0f;
        } else {
            if (i <= 0) {
                i = 1;
            }
            attributes.screenBrightness = i / 255.0f;
        }
        window.setAttributes(attributes);
        return attributes.screenBrightness;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void initContentLayout() {
        this.mContentLayout = (ViewGroup) findViewById(R.id.content_drawer_layout);
        this.mContentLayout.removeAllViews();
        this.mContentLayout.addView(getLayoutInflater().inflate(this.layout, (ViewGroup) null));
    }

    public void initRightLayout(int i) {
        this.mRightLayout = (LinearLayout) findViewById(R.id.right_layout);
        ViewGroup.LayoutParams layoutParams = this.mRightLayout.getLayoutParams();
        layoutParams.height = this.mScreenHeightInPx;
        layoutParams.width = ((this.mScreenWidthInPx > this.mScreenHeightInPx ? this.mScreenWidthInPx : this.mScreenHeightInPx) * 3) / 16;
        this.mRightLayout.setLayoutParams(layoutParams);
        View inflate = getLayoutInflater().inflate(i, (ViewGroup) null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
//        this.mGridView = (GridView) inflate.findViewById(R.id.gridview);
        this.mRightLayout.addView(inflate);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public void setCurAppBrightness(int i) {
        this.mAppBrightness = i;
    }

    public void ChangeImgMode(int i) {
        if (!Ultrasys.Instance().mIsUnFreeState) {
            return;
        }
//        if (i == 5) {
//            Ultrasys.Instance().SendCmdToProbe(getResources().getInteger(R.integer.W_PW_CMD), 1, null, 44);
//        } else if (i != 8) {
//            if (i != 50) {
//                if (i != 55) {
//                    switch (i) {
//                        case 0:
//                            break;
//                        case 1:
//                            Ultrasys.Instance().SendCmdToProbe(getResources().getInteger(R.integer.W_M_CMD), 1, null, 45);
//                            return;
//                        default:
//                            return;
//                    }
//                } else {
//                    Ultrasys.Instance().SendCmdToProbe(getResources().getInteger(R.integer.W_POWER_CMD), 1, null, 43);
//                    return;
//                }
//            }
//            Ultrasys.Instance().SendCmdToProbe(getResources().getInteger(R.integer.W_B_CMD), 1, null, 41);
//        } else {
//            Ultrasys.Instance().SendCmdToProbe(getResources().getInteger(R.integer.W_C_CMD), 1, null, 42);
//        }
    }

    public void registerSysReceivers() {
        if (!this.mPowerKeyReceiverTag) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
            this.mPowerKeyReceiverTag = true;
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            this.mPowerKeyInfoReceiver = new BroadcastReceiver() { // from class: com.Com.handprobe.BaseActivity.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        Log.i("电源键监听：", "关闭");
                    } else if (!"android.intent.action.SCREEN_ON".equals(action)) {
                    } else {
                        Log.i("电源键监听：", "打开");
                    }
                }
            };
            registerReceiver(this.mPowerKeyInfoReceiver, intentFilter);
        }
        if (!this.mUsbReceiverTag) {
//            this.mUsbReceiver = new HUsbReceiver(this);
            this.mUsbReceiverTag = true;
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            intentFilter2.addAction(ACTION_USB_PERMISSION);
//            registerReceiver(this.mUsbReceiver, intentFilter2);
        }
    }

    public void basRemListProbeConfigInfos() {
        ProbeWifiInfo.removeListProbeConfigInfos(this.mWifiManager);
    }

    public void EnterSysWifi(boolean z) {
        this.mSystemWifiEnter = z;
    }
}
