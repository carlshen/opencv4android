package com.book.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;

import com.book.handprobe.LanguageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.xutils.x;

import gloomyfish.opencvdemo.MyApplication;
import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class ProbeWifiInfo {
    protected static WifiInfo mCurrentWifiInfo = null;
    protected static boolean mIsProbeConnect = false;
    private static Lock mWifiLock = new ReentrantLock();
    protected static boolean mEnabledWifi = false;
    public static HObservable mCurWifiInfoObserval = new HObservable();
    private static boolean mIsManualDisconnect = false;

    public static int calSummary(int i) {
        int i2 = 0;
        if (i >= 1) {
            for (int i3 = 1; i3 <= i; i3++) {
                i2 += i3;
            }
        }
        return i2;
    }

    public static WifiInfo getCurrentWifiInfo() {
        return mCurrentWifiInfo;
    }

    public static WifiInfo obtainCurrentWifiInfo() {
        @SuppressLint("WifiManagerLeak") WifiInfo connectionInfo = ((WifiManager) MyApplication.GetAppContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
        if (connectionInfo != null) {
            String ssid = connectionInfo.getSSID();
            if (ssid.length() < 2) {
                return null;
            }
            if (!isProbeSSID(ssid.substring(1, ssid.length() - 1)) && !isProbePairSSID(ssid.substring(1, ssid.length() - 1))) {
                return null;
            }
            return connectionInfo;
        }
        return null;
    }

    public static void Lock() {
        mWifiLock.lock();
    }

    public static void UnLock() {
        mWifiLock.unlock();
    }

    public static void setCurrentProbeInfo(WifiInfo wifiInfo) {
        mCurrentWifiInfo = wifiInfo;
        mCurWifiInfoObserval.setChanged();
        mCurWifiInfoObserval.notifyObservers();
    }

    public static int obtainScanProbeResults(List<ScanResult> list, List<ScanResult> list2, WifiManager wifiManager) {
        List<ScanResult> scanResults;
        if (list == null) {
            return 0;
        }
        list.clear();
        if (!OpenWifi(wifiManager) || (scanResults = wifiManager.getScanResults()) == null) {
            return 0;
        }
        for (int i = 0; i < scanResults.size(); i++) {
            ScanResult scanResult = scanResults.get(i);
            if (!scanResult.capabilities.contains("[IBSS]")) {
                if (isProbeSSID(scanResult.SSID)) {
                    list.add(scanResult);
                }
                if (isProbePairSSID(scanResult.SSID)) {
                    list2.add(scanResult);
                }
            }
        }
        return list.size();
    }

    public static List<WifiConfiguration> obtainListProbeConfigInfos() {
        @SuppressLint({"MissingPermission", "WifiManagerLeak"}) List<WifiConfiguration> configuredNetworks = ((WifiManager) MyApplication.GetAppContext().getSystemService(Context.WIFI_SERVICE)).getConfiguredNetworks();
        ArrayList arrayList = null;
        if (configuredNetworks == null) {
            return null;
        }
        for (int i = 0; i < configuredNetworks.size(); i++) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(i);
            String str = wifiConfiguration.SSID;
            if (isProbeSSID(str.substring(1, str.length() - 1)) || isProbePairSSID(str.substring(1, str.length() - 1))) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(wifiConfiguration);
            }
        }
        return arrayList;
    }

    @SuppressLint("MissingPermission")
    public static void removeProbeConfigInfo(String str) {
        List<WifiConfiguration> configuredNetworks;
        @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) MyApplication.GetAppContext().getSystemService(Context.WIFI_SERVICE);
        if (str == null || (configuredNetworks = wifiManager.getConfiguredNetworks()) == null) {
            return;
        }
        for (int i = 0; i < configuredNetworks.size(); i++) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(i);
            if (wifiConfiguration.SSID.equals(str) && wifiConfiguration.networkId >= 0) {
                wifiManager.removeNetwork(wifiConfiguration.networkId);
                wifiManager.saveConfiguration();
            }
        }
    }

    public static boolean removeListProbeConfigInfos(WifiManager wifiManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return true;
        }
        OpenWifi(wifiManager);
        @SuppressLint("MissingPermission") List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return false;
        }
        for (int i = 0; i < configuredNetworks.size(); i++) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(i);
            String str = wifiConfiguration.SSID;
            if (isProbeSSID(str.substring(1, str.length() - 1)) && wifiConfiguration.networkId >= 0) {
                wifiManager.removeNetwork(wifiConfiguration.networkId);
                wifiManager.saveConfiguration();
            }
        }
        return true;
    }

    public static boolean isProbeSSID(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.charAt(0) == '*' && str.charAt(str.length() - 1) == '*') {
            return true;
        }
        return str.charAt(0) == '+' && str.charAt(str.length() - 1) == '+';
    }

    public static boolean isProbePairSSID(String str) {
        return !TextUtils.isEmpty(str) && str.charAt(0) == '#' && str.charAt(str.length() - 1) == '#';
    }

    public static boolean isUWLANDevice(String str) {
        return !TextUtils.isEmpty(str) && str.charAt(0) == '+' && str.charAt(str.length() - 1) == '+';
    }

    public static boolean isWLANDevice(String str) {
        return !TextUtils.isEmpty(str) && str.charAt(0) == '*' && str.charAt(str.length() - 1) == '*';
    }

    public static boolean OpenWifi(WifiManager wifiManager) {
        boolean isWifiEnabled = wifiManager.isWifiEnabled();
        if (!wifiManager.isWifiEnabled()) {
            x.task().autoPost(new Runnable() { // from class: handprobe.application.ultrasys.probe.ProbeWifiInfo.1
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(MyApplication.GetAppContext(), LanguageUtil._NLS(R.array.wifi_enabled), Toast.LENGTH_LONG).show();
                }
            });
            wifiManager.setWifiEnabled(true);
            return wifiManager.isWifiEnabled();
        }
        return isWifiEnabled;
    }

    public static synchronized boolean Connect(String str, String str2) {
        boolean z;
        synchronized (ProbeWifiInfo.class) {
            z = false;
            @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) MyApplication.GetAppContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager.getWifiState() == 3) {
                WifiConfiguration wifiConfig = getWifiConfig(str);
                if (wifiConfig != null) {
                    int i = wifiConfig.networkId;
                    Lock();
                    z = wifiManager.enableNetwork(i, true);
                    wifiManager.reconnect();
                    UnLock();
                } else {
                    int SetWifiConfig = SetWifiConfig(str, str2, wifiManager);
                    Lock();
                    z = wifiManager.enableNetwork(SetWifiConfig, true);
                    UnLock();
                }
            }
            if (!z) {
                wifiManager.disconnect();
                wifiManager.startScan();
                wifiManager.reconnect();
            }
        }
        return z;
    }

    public static float getProbeCurStrength() {
        WifiInfo obtainCurrentWifiInfo = obtainCurrentWifiInfo();
        if (obtainCurrentWifiInfo == null || obtainCurrentWifiInfo.getSSID().length() == 0) {
            return 0.0f;
        }
        String ssid = obtainCurrentWifiInfo.getSSID();
        switch (isProbeSSID(ssid.substring(1, ssid.length() - 1)) ? WifiManager.calculateSignalLevel(obtainCurrentWifiInfo.getRssi(), 5) : -1) {
            case 0:
                return 0.0f;
            case 1:
                return 0.24f;
            case 2:
                return 0.49f;
            case 3:
                return 0.74f;
            case 4:
                return 1.0f;
            default:
                return 0.0f;
        }
    }

    public static void setIsProbeConnect(boolean z) {
        mIsProbeConnect = z;
    }

    public static WifiConfiguration getWifiConfig(String str) {
        List<WifiConfiguration> obtainListProbeConfigInfos = obtainListProbeConfigInfos();
        String str2 = "\"" + str + "\"";
        if (obtainListProbeConfigInfos == null) {
            return null;
        }
        for (int i = 0; i < obtainListProbeConfigInfos.size(); i++) {
            if (obtainListProbeConfigInfos.get(i).SSID.equals(str2)) {
                return obtainListProbeConfigInfos.get(i);
            }
        }
        return null;
    }

    public static int SetWifiConfig(String str, String str2, WifiManager wifiManager) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        obtainScanProbeResults(arrayList, arrayList2, wifiManager);
        int i = -1;
        if (arrayList.size() == 0 && arrayList2.size() == 0) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= arrayList.size()) {
                break;
            }
            ScanResult scanResult = (ScanResult) arrayList.get(i2);
            if (scanResult.SSID.equals(str)) {
                WifiConfiguration wifiConfiguration = new WifiConfiguration();
                wifiConfiguration.allowedAuthAlgorithms.clear();
                wifiConfiguration.allowedGroupCiphers.clear();
                wifiConfiguration.allowedKeyManagement.clear();
                wifiConfiguration.allowedPairwiseCiphers.clear();
                wifiConfiguration.allowedProtocols.clear();
                wifiConfiguration.SSID = "\"" + scanResult.SSID + "\"";
                if (scanResult.capabilities.contains("WPA")) {
                    wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
                    wifiConfiguration.hiddenSSID = true;
                    wifiConfiguration.allowedAuthAlgorithms.set(0);
                    wifiConfiguration.allowedGroupCiphers.set(2);
                    wifiConfiguration.allowedKeyManagement.set(1);
                    wifiConfiguration.allowedPairwiseCiphers.set(1);
                    wifiConfiguration.allowedGroupCiphers.set(3);
                    wifiConfiguration.allowedPairwiseCiphers.set(2);
                    wifiConfiguration.status = 2;
                } else if (scanResult.capabilities.contains("WEP")) {
                    wifiConfiguration.wepKeys[0] = "\"".concat(str2).concat("\"");
                    wifiConfiguration.hiddenSSID = true;
                    wifiConfiguration.allowedAuthAlgorithms.set(1);
                    wifiConfiguration.allowedGroupCiphers.set(0);
                    wifiConfiguration.allowedGroupCiphers.set(1);
                    wifiConfiguration.allowedKeyManagement.set(0);
                    wifiConfiguration.wepTxKeyIndex = 0;
                } else {
                    wifiConfiguration.preSharedKey = null;
                    wifiConfiguration.wepKeys[0] = "\"\"";
                    wifiConfiguration.wepTxKeyIndex = 0;
                    wifiConfiguration.allowedKeyManagement.set(0);
                    wifiConfiguration.allowedProtocols.set(1);
                    wifiConfiguration.allowedProtocols.set(0);
                    wifiConfiguration.allowedAuthAlgorithms.clear();
                    wifiConfiguration.allowedPairwiseCiphers.set(2);
                    wifiConfiguration.allowedPairwiseCiphers.set(1);
                    wifiConfiguration.allowedGroupCiphers.set(0);
                    wifiConfiguration.allowedGroupCiphers.set(1);
                    wifiConfiguration.allowedGroupCiphers.set(3);
                    wifiConfiguration.allowedGroupCiphers.set(2);
                    if (getHexKey(str2)) {
                        wifiConfiguration.wepKeys[0] = str2;
                    } else {
                        wifiConfiguration.wepKeys[0] = "\"".concat(str2).concat("\"");
                    }
                    wifiConfiguration.wepTxKeyIndex = 0;
                }
                i = wifiManager.addNetwork(wifiConfiguration);
                wifiManager.saveConfiguration();
            } else {
                i2++;
            }
        }
        if (arrayList2.size() == 0) {
            return i;
        }
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            ScanResult scanResult2 = (ScanResult) arrayList2.get(i3);
            if (scanResult2.SSID.equals(str)) {
                WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
                wifiConfiguration2.allowedAuthAlgorithms.clear();
                wifiConfiguration2.allowedGroupCiphers.clear();
                wifiConfiguration2.allowedKeyManagement.clear();
                wifiConfiguration2.allowedPairwiseCiphers.clear();
                wifiConfiguration2.allowedProtocols.clear();
                wifiConfiguration2.SSID = "\"" + scanResult2.SSID + "\"";
                if (scanResult2.capabilities.contains("WPA")) {
                    wifiConfiguration2.preSharedKey = "\"" + str2 + "\"";
                    wifiConfiguration2.hiddenSSID = true;
                    wifiConfiguration2.allowedAuthAlgorithms.set(0);
                    wifiConfiguration2.allowedGroupCiphers.set(2);
                    wifiConfiguration2.allowedKeyManagement.set(1);
                    wifiConfiguration2.allowedPairwiseCiphers.set(1);
                    wifiConfiguration2.allowedGroupCiphers.set(3);
                    wifiConfiguration2.allowedPairwiseCiphers.set(2);
                    wifiConfiguration2.status = 2;
                } else if (scanResult2.capabilities.contains("WEP")) {
                    wifiConfiguration2.wepKeys[0] = "\"".concat(str2).concat("\"");
                    wifiConfiguration2.hiddenSSID = true;
                    wifiConfiguration2.allowedAuthAlgorithms.set(1);
                    wifiConfiguration2.allowedGroupCiphers.set(0);
                    wifiConfiguration2.allowedGroupCiphers.set(1);
                    wifiConfiguration2.allowedKeyManagement.set(0);
                    wifiConfiguration2.wepTxKeyIndex = 0;
                } else {
                    wifiConfiguration2.preSharedKey = null;
                    wifiConfiguration2.wepKeys[0] = "\"\"";
                    wifiConfiguration2.wepTxKeyIndex = 0;
                    wifiConfiguration2.allowedKeyManagement.set(0);
                    wifiConfiguration2.allowedProtocols.set(1);
                    wifiConfiguration2.allowedProtocols.set(0);
                    wifiConfiguration2.allowedAuthAlgorithms.clear();
                    wifiConfiguration2.allowedPairwiseCiphers.set(2);
                    wifiConfiguration2.allowedPairwiseCiphers.set(1);
                    wifiConfiguration2.allowedGroupCiphers.set(0);
                    wifiConfiguration2.allowedGroupCiphers.set(1);
                    wifiConfiguration2.allowedGroupCiphers.set(3);
                    wifiConfiguration2.allowedGroupCiphers.set(2);
                    if (getHexKey(str2)) {
                        wifiConfiguration2.wepKeys[0] = str2;
                    } else {
                        wifiConfiguration2.wepKeys[0] = "\"".concat(str2).concat("\"");
                    }
                    wifiConfiguration2.wepTxKeyIndex = 0;
                }
                int addNetwork = wifiManager.addNetwork(wifiConfiguration2);
                wifiManager.saveConfiguration();
                return addNetwork;
            }
        }
        return i;
    }

    private static boolean getHexKey(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length != 10 && length != 26 && length != 58) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'f') && (charAt < 'A' || charAt > 'F'))) {
                return false;
            }
        }
        return true;
    }

    public static WifiConfiguration getConfigureInfoBySSID(String str) {
        List<WifiConfiguration> obtainListProbeConfigInfos = obtainListProbeConfigInfos();
        if (obtainListProbeConfigInfos == null) {
            return null;
        }
        for (int i = 0; i < obtainListProbeConfigInfos.size(); i++) {
            String str2 = obtainListProbeConfigInfos.get(i).SSID;
            if (str2.equals("\"" + str + "\"")) {
                return obtainListProbeConfigInfos.get(i);
            }
        }
        return null;
    }

    public static float[] calChannelOverLapping(List<ScanResult> list) {
        int i;
        float[] fArr = new float[14];
        if (list == null || list.size() == 0) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ScanResult scanResult = list.get(i2);
            int channelByFrequency = getChannelByFrequency(scanResult.frequency);
            if (channelByFrequency < 0) {
                channelByFrequency = 0;
            }
            if (channelByFrequency <= 14) {
                int pow = (int) Math.pow(2.0d, 2);
                if (Build.VERSION.SDK_INT >= 23 && (i = scanResult.channelWidth) > 0 && i < 4) {
                    pow = (int) Math.pow(2.0d, i + 2);
                }
                float[] calInfluences = calInfluences(pow);
                int i3 = 1;
                if (channelByFrequency == 0) {
                    while (i3 < pow / 2) {
                        int i4 = channelByFrequency + i3;
                        if (i4 < 13 && i4 >= 0) {
                            int i5 = (channelByFrequency - 1) + i3;
                            fArr[i5] = fArr[i5] + calInfluences[i3];
                        }
                        i3++;
                    }
                } else {
                    int i6 = channelByFrequency - 1;
                    fArr[i6] = fArr[i6] + calInfluences[0];
                    while (i3 < pow) {
                        int i7 = i6 + i3;
                        if (i7 < 13 && i7 >= 0) {
                            fArr[i7] = fArr[i7] + calInfluences[i3];
                        } else if (i7 == 14) {
                            fArr[13] = fArr[13] + calInfluences[i3];
                        }
                        int i8 = i6 - i3;
                        if (i8 < 13 && i8 >= 0) {
                            fArr[i8] = fArr[i8] + calInfluences[i3];
                        } else if (i8 == 14) {
                            fArr[13] = fArr[13] + calInfluences[i3];
                        }
                        i3++;
                    }
                }
            }
        }
        return fArr;
    }

    public static float[] calChannelOverLappingForFiveG(List<ScanResult> list) {
        int i;
        float[] fArr = new float[14];
        if (list == null || list.size() == 0) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ScanResult scanResult = list.get(i2);
            int channelByFrequency = getChannelByFrequency(scanResult.frequency);
            if (channelByFrequency <= 60 && channelByFrequency >= 34) {
                if (channelByFrequency < 0) {
                    channelByFrequency = 0;
                }
                int i3 = channelByFrequency - 33;
                int pow = (int) Math.pow(2.0d, 2);
                if (Build.VERSION.SDK_INT >= 23 && (i = scanResult.channelWidth) > 0 && i < 4) {
                    pow = (int) Math.pow(2.0d, i + 2);
                }
                float[] calInfluences = calInfluences(pow);
                int i4 = 1;
                if (i3 == 0) {
                    while (i4 < pow / 2) {
                        int i5 = i3 + i4;
                        if (i5 < 13 && i5 >= 0) {
                            int i6 = (i3 - 1) + i4;
                            fArr[i6] = fArr[i6] + calInfluences[i4];
                        }
                        i4++;
                    }
                } else {
                    int i7 = i3 - 1;
                    fArr[i7] = fArr[i7] + calInfluences[0];
                    while (i4 < pow) {
                        int i8 = i7 + i4;
                        if (i8 < 13 && i8 >= 0) {
                            fArr[i8] = fArr[i8] + calInfluences[i4];
                        } else if (i8 == 14) {
                            fArr[13] = fArr[13] + calInfluences[i4];
                        }
                        int i9 = i7 - i4;
                        if (i9 < 13 && i9 >= 0) {
                            fArr[i9] = fArr[i9] + calInfluences[i4];
                        } else if (i9 == 14) {
                            fArr[13] = fArr[13] + calInfluences[i4];
                        }
                        i4++;
                    }
                }
            }
        }
        return fArr;
    }

    public static float[] calMinMaxInOverlapping(float[] fArr) {
        float[] fArr2 = new float[2];
        if (fArr != null) {
            float f = fArr[0];
            float f2 = fArr[0];
            float f3 = f;
            for (int i = 1; i < fArr.length; i++) {
                if (f3 > fArr[i]) {
                    f3 = fArr[i];
                }
                if (f2 < fArr[i]) {
                    f2 = fArr[i];
                }
            }
            fArr2[0] = f3;
            fArr2[1] = f2;
            return fArr2;
        }
        return null;
    }

    public static int[] calRecommendBusyChannel(float[] fArr) {
        int[] iArr = new int[5];
        if (fArr != null) {
            float f = fArr[0];
            int[] iArr2 = new int[fArr.length];
            int[] iArr3 = new int[fArr.length];
            for (int i = 0; i < iArr3.length; i++) {
                iArr3[i] = i;
            }
            int i2 = 0;
            while (i2 < fArr.length) {
                float f2 = fArr[i2];
                int i3 = i2 + 1;
                int i4 = iArr3[i2];
                float f3 = f2;
                for (int i5 = i3; i5 < fArr.length; i5++) {
                    if (f3 > fArr[i5]) {
                        float f4 = fArr[i5];
                        fArr[i5] = f3;
                        int i6 = iArr3[i5];
                        iArr3[i5] = i4;
                        iArr3[i2] = i6;
                        i4 = i6;
                        f3 = f4;
                    }
                }
                i2 = i3;
            }
            for (int i7 = 0; i7 < iArr.length - 1; i7++) {
                if (i7 < iArr2.length) {
                    iArr[i7] = iArr3[i7] + 1;
                } else {
                    iArr[i7] = 0;
                }
            }
            iArr[iArr.length - 1] = iArr3[iArr3.length - 1] + 1;
            return iArr;
        }
        return null;
    }

    public static float[] calInfluences(int i) {
        float[] fArr = new float[i];
        int calSummary = calSummary(i);
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = calSummary(i - i2) / calSummary;
        }
        return fArr;
    }

    public static int getChannelByFrequency(int i) {
        if (i < 2412 || i > 2484) {
            if (i >= 5170 && i <= 5825) {
                return ((i - 5170) / 10) + 34;
            }
            return -1;
        }
        return ((i - 2412) / 5) + 1;
    }

    public static boolean saveWifiConnectPreferences(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(context.getResources().getString(R.string.wifi_info_preferences), 0).edit();
        edit.putString(context.getResources().getString(R.string.wifi_name_preferences), str);
        edit.putString(context.getResources().getString(R.string.wifi_pwd_preferences), str2);
        return edit.commit();
    }

    public static void setEnabledWifiValue(boolean z) {
        mEnabledWifi = z;
    }

    public static void Sleep(int i) {
        try {
            Thread.sleep(i, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void disconnectWifi(Context context) {
        WifiInfo currentWifiInfo = getCurrentWifiInfo();
        if (currentWifiInfo != null) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifiManager.removeNetwork(currentWifiInfo.getNetworkId());
            wifiManager.saveConfiguration();
            wifiManager.disableNetwork(currentWifiInfo.getNetworkId());
            wifiManager.disconnect();
            setIsProbeConnect(false);
        }
    }

    public static void setIsManualDisconnect(boolean z) {
        mIsManualDisconnect = z;
    }

    public static boolean getIsManualDisconnect() {
        return mIsManualDisconnect;
    }
}
