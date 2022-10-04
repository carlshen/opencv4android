package com.book.tools;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.book.handprobe.BaseActivity;
import com.book.handprobe.Ultrasys;

import java.util.Timer;
import java.util.TimerTask;
import org.xutils.x;

import gloomyfish.opencvdemo.MyApplication;

/* loaded from: classes2.dex */
public class HWifiReceiver extends BroadcastReceiver {
    private TimerTask mFreezeTask;
    private Timer mFreezeTimer;
    BaseActivity mMainActivity;
    float mSignalStrength = -1.0f;
    WifiInfo mWifiInfo = null;
    @SuppressLint("WifiManagerLeak")
    WifiManager mWifiManager = (WifiManager) MyApplication.GetAppContext().getSystemService(Context.WIFI_SERVICE);

    public HWifiReceiver(BaseActivity baseActivity) {
        this.mMainActivity = baseActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.wifi.RSSI_CHANGED")) {
            if (this.mWifiInfo == null) {
                return;
            }
            float probeCurStrength = ProbeWifiInfo.getProbeCurStrength();
            if (this.mSignalStrength == probeCurStrength) {
                return;
            }
            setWifiIndicatorStatus(probeCurStrength);
            this.mSignalStrength = probeCurStrength;
        } else if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                setWifiIndicatorStatus(0.0f);
                Ultrasys.Instance().SetVet(0);
            } else if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                float probeCurStrength2 = ProbeWifiInfo.getProbeCurStrength();
                if (this.mSignalStrength != probeCurStrength2) {
                    setWifiIndicatorStatus(probeCurStrength2);
                    this.mSignalStrength = probeCurStrength2;
                }
            }
            if ((NetUtil.GetIPNum() <= 1 && !networkInfo.getState().equals(NetworkInfo.State.DISCONNECTED)) || networkInfo.getType() != 1) {
                return;
            }
            handleWifiState(networkInfo.getState(), networkInfo);
        } else if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("wifi_state", 1);
            if (intExtra == 1) {
                setWifiIndicatorStatus(0.0f);
                ProbeWifiInfo.setEnabledWifiValue(false);
                Ultrasys.Instance().SetVet(0);
            } else if (intExtra != 3) {
            } else {
                ProbeWifiInfo.setEnabledWifiValue(true);
            }
        } else if (!intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") || NetUtil.GetIPNum() != 1) {
        } else {
            NetworkInfo networkInfo2 = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            int type = networkInfo2.getType();
            Log.i("onReceive", "network type: " + type);
            if (networkInfo2.getType() != 1) {
                return;
            }
            handleWifiState(networkInfo2.getState(), networkInfo2);
        }
    }

    public void setWifiInfo(WifiInfo wifiInfo) {
        this.mWifiInfo = wifiInfo;
        ProbeWifiInfo.setCurrentProbeInfo(wifiInfo);
        if (wifiInfo == null) {
            WlanProbe.Instance().NotifyProbeId(0);
        }
    }

    public void setWifiIndicatorStatus(float f) {
        if (f == 0.0f) {
            this.mSignalStrength = -1.0f;
        }
        this.mMainActivity.SetWifiStrength(f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: handprobe.application.wlan.wifi.HWifiReceiver$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$State = new int[NetworkInfo.State.values().length];

        static {
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void handleWifiState(NetworkInfo.State state, NetworkInfo networkInfo) {
        switch (AnonymousClass2.$SwitchMap$android$net$NetworkInfo$State[state.ordinal()]) {
            case 1:
                if (Ultrasys.Instance().GetProbeConType() == 4) {
                    return;
                }
                BaseActivity baseActivity = this.mMainActivity;
//                if (baseActivity.recordService == null || !baseActivity.recordService.isRunning()) {
//                    ProbeWifiInfo.setIsManualDisconnect(false);
//                    WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
//                    String ssid = connectionInfo.getSSID();
//                    if (ssid.length() >= 2) {
//                        String substring = ssid.substring(1, ssid.length() - 1);
//                        if (ProbeWifiInfo.isProbeSSID(substring)) {
//                            if (this.mFreezeTimer != null) {
//                                this.mFreezeTimer.cancel();
//                                this.mFreezeTimer = null;
//                                this.mFreezeTask = null;
//                            }
//                            if (ProbeWifiInfo.isUWLANDevice(substring)) {
//                                Ultrasys.Instance().SetProbeConType(5);
//                            } else if (ProbeWifiInfo.isWLANDevice(substring)) {
//                                Ultrasys.Instance().SetProbeConType(2);
//                            }
//                            WlanProbe.Instance().getmICmdHandler().ResetNet();
//                            int i = 0;
//                            while (true) {
//                                int i2 = i + 1;
//                                if (i < 20 && !WlanProbe.Instance().getmICmdHandler().mUdpReady) {
//                                    Tools.Sleep(100);
//                                    i = i2;
//                                }
//                            }
//                            Ultrasys.Instance().SetWlanProbeConState(true);
//                            byte[] bArr = new byte[1];
//                            if (Ultrasys.Instance().GetProbeConType() == 2) {
//                                Ultrasys.Instance().SendCmdToProbe(77, 1, bArr, 41);
//                                Ultrasys.Instance().SetDispMode(0);
//                            }
//                            if (Ultrasys.Instance().GetProbeConType() == 4 || Ultrasys.Instance().GetProbeConType() == 3 || Ultrasys.Instance().GetProbeConType() == 5) {
//                                Ultrasys.Instance().UpdateVet();
//                                Ultrasys.Instance().UpdatePrjOption();
//                            } else if (Ultrasys.Instance().GetProbeConType() == 2) {
//                                Ultrasys.Instance().SendCmdToProbe(2, 1, bArr, 1);
//                                Ultrasys.Instance().setIsGetPresetParamCmd();
//                                Ultrasys.Instance().UnFreeze(true);
//                                if (AppProc.Instance().mWifiProbeSelect.isAdded()) {
//                                    Ultrasys.Instance().SendGetCmdToProbe(101, 1, bArr);
//                                }
//                            }
//                            ProbeWifiInfo.setIsProbeConnect(true);
//                            setWifiInfo(connectionInfo);
//                            setWifiIndicatorStatus(1.0f);
//                            this.mMainActivity.mCurProbeObservable.setCurUsbProbeInfo(null);
//                            this.mMainActivity.mCurProbeObservable.notifyCurProbe();
//                            if (!AppProc.Instance().mWifiProbeSelect.isAdded() && !AppProc.Instance().mUsbProbeConnectDlgFrag.isAdded() && Ultrasys.Instance().GetProbeConType() == 5) {
//                                WlanProbe.Instance().SetAutoRun(true);
//                            } else if (Ultrasys.Instance().GetProbeConType() == 2) {
//                                WlanProbe.Instance().SetAutoRun(false);
//                            }
//                        } else if (ProbeWifiInfo.isProbePairSSID(substring)) {
//                            if (AppProc.Instance().mWifiProbeSelect.isAdded()) {
//                                WlanProbe.Instance().getmICmdHandler().StartWlanPair();
//                            }
//                        } else {
//                            WlanProbe.Instance().SetAutoRun(false);
//                            ProbeWifiInfo.setIsProbeConnect(false);
//                            setWifiInfo(null);
//                            setWifiIndicatorStatus(0.0f);
//                            Ultrasys.Instance().SetVet(0);
//                        }
//                    } else {
//                        ProbeWifiInfo.setIsProbeConnect(false);
//                        setWifiInfo(null);
//                        setWifiIndicatorStatus(0.0f);
//                        Ultrasys.Instance().SetVet(0);
//                    }
//                }
                float probeCurStrength = ProbeWifiInfo.getProbeCurStrength();
                if (this.mSignalStrength != probeCurStrength) {
                    setWifiIndicatorStatus(probeCurStrength);
                    this.mSignalStrength = probeCurStrength;
                }
//                InfoDlg.newInstance().resetInfos();
                return;
            case 2:
                if (networkInfo.getState().equals(NetworkInfo.DetailedState.SCANNING)) {
                    return;
                }
                WlanProbe.Instance().getmICmdHandler().DisConnectNet();
                if (Ultrasys.Instance().GetProbeConType() == 4 || Ultrasys.Instance().GetProbeConType() == 3) {
                    return;
                }
//                if (this.mWifiInfo != null && !AppProc.Instance().mWifiProbeSelect.isAdded() && !ProbeWifiInfo.getIsManualDisconnect()) {
//                    if (this.mFreezeTimer != null) {
//                        this.mFreezeTimer.cancel();
//                        this.mFreezeTimer = null;
//                        this.mFreezeTask = null;
//                    }
//                    this.mFreezeTimer = new Timer();
//                    this.mFreezeTimer.schedule(initFreezeTask(), 5000L);
//                    ProbeWifiInfo.Connect(this.mWifiInfo.getSSID(), "12345678");
//                } else if (this.mWifiInfo == null && this.mFreezeTimer == null) {
//                    freezeTask();
//                }
                ProbeWifiInfo.setIsProbeConnect(false);
                setWifiInfo(null);
                WlanProbe.Instance().resetWlanProbeId();
                setWifiIndicatorStatus(0.0f);
                WlanProbe.Instance().SetAutoRun(false);
                Ultrasys.Instance().SetVet(0);
//                if (AppProc.Instance().mWifiProbeSelect != null && AppProc.Instance().mWifiProbeSelect.getDialog() != null && AppProc.Instance().mWifiProbeSelect.getDialog().isShowing()) {
//                    AppProc.Instance().mWifiProbeSelect.mExamSelectFragment.Reset();
//                }
//                AppProc.Instance().FuncIdProc(181);
                Ultrasys.Instance().SetProbeConType(0);
                return;
            default:
                return;
        }
    }

    private TimerTask initFreezeTask() {
        this.mFreezeTask = new TimerTask() { // from class: handprobe.application.wlan.wifi.HWifiReceiver.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                x.task().post(new Runnable() { // from class: handprobe.application.wlan.wifi.HWifiReceiver.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HWifiReceiver.this.freezeTask();
                    }
                });
            }
        };
        return this.mFreezeTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freezeTask() {
        if (Ultrasys.Instance().GetProbeConType() == 2 || Ultrasys.Instance().GetProbeConType() == 5) {
            Ultrasys.Instance().Reset();
        }
    }
}
