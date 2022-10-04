package com.book.tools;

import com.book.handprobe.Ultrasys;

/* loaded from: classes2.dex */
public class jnitojava {
    public static native int JavaGetMessage(long[] jArr);

    public static native void SetJniEvn();

//    public static boolean UsbWriteDwords(int i, byte[] bArr, int i2) {
//        UsbProbeManager.Instance().Write(i, bArr, i2);
//        return true;
//    }
//
//    public static boolean UsbReadDwords(int i, byte[] bArr, int i2) {
//        UsbProbeManager.Instance().Read(i, bArr, i2);
//        return true;
//    }
//
//    public static int WUsbReadImg(byte[] bArr, int i) {
//        return UsbProbeManager.Instance().ReadEncImg(bArr, i);
//    }

    public static void UpdateMITI(float[] fArr) {
        Ultrasys.Instance().setMITIData(fArr);
    }

    public static int GetPattern(int i, int i2) {
        return Ultrasys.Instance().GetPattern(i, i2);
    }

    public static void SetBFrameCorr(int i) {
        if (Ultrasys.Instance().GetProbeConType() == 4 || Ultrasys.Instance().GetProbeConType() == 5) {
//            Ultrasys.Instance().mDscCtrl.SetBFrameCorrCoef(i);
        }
    }

    public static void SetCFrameCorr(int i) {
        if (Ultrasys.Instance().GetProbeConType() == 4 || Ultrasys.Instance().GetProbeConType() == 5) {
//            Ultrasys.Instance().mDscCtrl.SetCFrameCorrCoef(i);
        }
    }
}
