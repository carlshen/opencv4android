package com.book.tools;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
//import com.anggrayudi.hiddenapi.r.Rc;
import androidx.core.view.ViewCompat;

import com.book.handprobe.LanguageUtil;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.xutils.x;

import gloomyfish.opencvdemo.MyApplication;

/* loaded from: classes2.dex */
public class Tools {
    public static boolean IsHexStr(byte b) {
        return b == 48 || b == 49 || b == 50 || b == 51 || b == 52 || b == 53 || b == 54 || b == 55 || b == 56 || b == 57 || b == 65 || b == 66 || b == 67 || b == 68 || b == 69 || b == 70;
    }

    public static byte AsiicToHex(byte[] bArr) {
        byte b;
        if (bArr != null) {
            if (bArr[0] >= 65 && bArr[0] <= 70) {
                b = (byte) (((bArr[0] + 10) - 65) + 0);
            } else {
                b = (byte) ((bArr[0] - 48) + 0);
            }
            if (bArr[1] >= 65 && bArr[1] <= 70) {
                return (byte) (b + (((bArr[1] + 10) - 65) * 16));
            }
            return (byte) (b + ((bArr[1] - 48) * 16));
        }
        return (byte) 0;
    }

    public static void HexToAsiic(char[] cArr, byte b) {
        if (cArr != null) {
            int i = b & IntersectionPtg.sid;
            int i2 = (b & FileDefinition.IDENTIFIER_HEAD) >> 4;
            if (i >= 10 && i <= 15) {
                cArr[0] = (char) ((i - 10) + 65);
            } else {
                cArr[0] = (char) ((i - 0) + 48);
            }
            if (i2 >= 10 && i2 <= 15) {
                cArr[1] = (char) ((i2 - 10) + 65);
            } else {
                cArr[1] = (char) ((i2 - 0) + 48);
            }
        }
    }

    public static void StrToHex(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[2];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            bArr3[0] = bArr[i3];
            bArr3[1] = bArr[i3 + 1];
            bArr2[i2] = AsiicToHex(bArr3);
        }
    }

    public static void StrToHex(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        byte[] bArr3 = new byte[2];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 * 2;
            bArr3[0] = bArr[i5 + i];
            bArr3[1] = bArr[i5 + 1 + i];
            bArr2[i4 + i2] = AsiicToHex(bArr3);
        }
    }

    public static void HexToStr(char[] cArr, byte[] bArr, int i) {
        char[] cArr2 = new char[2];
        for (int i2 = 0; i2 < i; i2++) {
            HexToAsiic(cArr2, bArr[i2]);
            int i3 = i2 * 2;
            cArr[i3] = cArr2[0];
            cArr[i3 + 1] = cArr2[1];
        }
    }

    public static byte AsiicToHex_r(byte[] bArr) {
        byte b;
        if (bArr != null) {
            if (bArr[1] >= 65 && bArr[1] <= 70) {
                b = (byte) (((bArr[1] + 10) - 65) + 0);
            } else {
                b = (byte) ((bArr[1] - 48) + 0);
            }
            if (bArr[0] >= 65 && bArr[0] <= 70) {
                return (byte) (b + (((bArr[0] + 10) - 65) * 16));
            }
            return (byte) (b + ((bArr[0] - 48) * 16));
        }
        return (byte) 0;
    }

    public static void HexToAsiic_r(char[] cArr, byte b) {
        if (cArr != null) {
            int i = b & IntersectionPtg.sid;
            int i2 = (b & FileDefinition.IDENTIFIER_HEAD) >> 4;
            if (i >= 10 && i <= 15) {
                cArr[1] = (char) ((i - 10) + 65);
            } else {
                cArr[1] = (char) ((i - 0) + 48);
            }
            if (i2 >= 10 && i2 <= 15) {
                cArr[0] = (char) ((i2 - 10) + 65);
            } else {
                cArr[0] = (char) ((i2 - 0) + 48);
            }
        }
    }

    public static void StrToHex_r(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[2];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            bArr3[0] = bArr[i3];
            bArr3[1] = bArr[i3 + 1];
            bArr2[i2] = AsiicToHex_r(bArr3);
        }
    }

    public static void StrToHex_r(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        byte[] bArr3 = new byte[2];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 * 2;
            bArr3[0] = bArr[i5 + i];
            bArr3[1] = bArr[i5 + 1 + i];
            bArr2[i4 + i2] = AsiicToHex_r(bArr3);
        }
    }

    public static void HexToStr_r(char[] cArr, byte[] bArr, int i) {
        char[] cArr2 = new char[2];
        for (int i2 = 0; i2 < i; i2++) {
            HexToAsiic_r(cArr2, bArr[i2]);
            int i3 = i2 * 2;
            cArr[i3] = cArr2[0];
            cArr[i3 + 1] = cArr2[1];
        }
    }

    public static void FloatToByte(float f, byte[] bArr) {
        int floatToIntBits = Float.floatToIntBits(f);
        bArr[0] = (byte) (floatToIntBits & 255);
        bArr[1] = (byte) ((65280 & floatToIntBits) >> 8);
        bArr[2] = (byte) ((16711680 & floatToIntBits) >> 16);
        bArr[3] = (byte) ((floatToIntBits & ViewCompat.MEASURED_STATE_MASK) >> 24);
    }

    public static void IntToByte(int i, byte[] bArr) {
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((65280 & i) >> 8);
        bArr[2] = (byte) ((16711680 & i) >> 16);
        bArr[3] = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
    }

    public static int bytesToInt(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public static float B2F(byte[] bArr) {
        int i = 1;
        int i2 = ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
        if ((Integer.MIN_VALUE & i2) != 0) {
            i = -1;
        }
        return (float) (i * ((i2 & 8388607) | 8388608) * Math.pow(2.0d, ((2139095040 & i2) >> 23) - 150));
    }

    public static byte[] IntAryToByteAry(int[] iArr) {
        if (iArr != null) {
            byte[] bArr = new byte[iArr.length * 4];
            for (int i = 0; i < iArr.length; i++) {
                byte[] bArr2 = new byte[4];
                IntToByte(iArr[i], bArr2);
                int i2 = i * 4;
                bArr[i2] = bArr2[0];
                bArr[i2 + 1] = bArr2[1];
                bArr[i2 + 2] = bArr2[2];
                bArr[i2 + 3] = bArr2[3];
            }
            return bArr;
        }
        return null;
    }

    public static int[] ByteAryToIntAry(byte[] bArr) {
        if (bArr != null) {
            int[] iArr = new int[bArr.length / 4];
            for (int i = 0; i < bArr.length / 4; i++) {
                int i2 = i * 4;
                iArr[i] = ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
            }
            return iArr;
        }
        return null;
    }

    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }

    public static String byteToStr(byte[] bArr) throws UnsupportedEncodingException {
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                if (i >= bArr.length) {
                    i = i2;
                    break;
                } else if (bArr[i] == 0) {
                    break;
                } else {
                    i2++;
                    i++;
                }
            } catch (Exception unused) {
                return "";
            }
        }
        return new String(bArr, 0, i, "UTF-8");
    }

    public static String byteToStr_1(byte[] bArr, int i, int i2) throws UnsupportedEncodingException {
        int i3 = i;
        int i4 = 0;
        while (true) {
            if (i3 >= i + i2) {
                break;
            }
            try {
                if (bArr[i3] == 0) {
                    i4 = i3 - i;
                    break;
                }
                i4++;
                i3++;
            } catch (Exception unused) {
                return "";
            }
        }
        return new String(bArr, i, i4, "UTF-8");
    }

    public static void Sleep(int i) {
        try {
            Thread.sleep(i, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean SemaphoreTryAcquire(Semaphore semaphore, long j) {
        try {
            return semaphore.tryAcquire(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("timeout");
        }
    }

    public static void ShowText(final int i) {
        ((MyApplication) x.app()).GetActivity().runOnUiThread(new Runnable() { // from class: handprobe.application.wlan.protocol.Tools.1
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MyApplication.GetAppContext(), LanguageUtil._NLS(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void ShowText(final int i, final int i2) {
        ((MyApplication) x.app()).GetActivity().runOnUiThread(new Runnable() { // from class: handprobe.application.wlan.protocol.Tools.2
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MyApplication.GetAppContext(), LanguageUtil._NLS(i), i2).show();
            }
        });
    }

    public static void ShowText(final String str) {
        ((MyApplication) x.app()).GetActivity().runOnUiThread(new Runnable() { // from class: handprobe.application.wlan.protocol.Tools.3
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MyApplication.GetAppContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void ShowText(final String str, final int i) {
        ((MyApplication) x.app()).GetActivity().runOnUiThread(new Runnable() { // from class: handprobe.application.wlan.protocol.Tools.4
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(MyApplication.GetAppContext(), str, i).show();
            }
        });
    }

    public static boolean Ping(int i, String str) {
        new StringBuffer();
        try {
            Runtime runtime = Runtime.getRuntime();
            StringBuilder sb = new StringBuilder();
            sb.append("ping -c 1 -w 5 ");
            sb.append(str);
            return runtime.exec(sb.toString()).waitFor() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static int hash_BPHash_forDicom(byte[] bArr) {
//        return HDscClassIf.hash_BPHash_forDicom(bArr);
//    }
//
//    public static int hash_FNVHash_forPw(byte[] bArr) {
//        return HDscClassIf.hash_FNVHash_forPw(bArr);
//    }
//
//    public static int hash_BKDRHash_forColor(byte[] bArr) {
//        return HDscClassIf.hash_BKDRHash_forColor(bArr);
//    }
//
//    public static int hash_JSHash_forHuman(byte[] bArr) {
//        return HDscClassIf.hash_JSHash_forHuman(bArr);
//    }
//
//    public static int hash_DJBHash_forB(byte[] bArr) {
//        return HDscClassIf.hash_DJBHash_forB(bArr);
//    }
//
//    public static void Strcat(byte[] bArr, byte[] bArr2) {
//        HDscClassIf.Strcat_s(bArr, bArr2);
//    }

    public static byte[] getBytes(char[] cArr) {
        Charset forName = Charset.forName("UTF-8");
        CharBuffer allocate = CharBuffer.allocate(cArr.length);
        allocate.put(cArr);
        allocate.flip();
        return forName.encode(allocate).array();
    }

    public static char[] getChars(byte[] bArr) {
        Charset forName = Charset.forName("UTF-8");
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
        allocate.put(bArr).flip();
        return forName.decode(allocate).array();
    }

    public static byte[] charToByte(char c) {
        return new byte[]{(byte) ((65280 & c) >> 8), (byte) (c & 255)};
    }

    public static char byteToChar(byte[] bArr) {
        return (char) ((bArr[1] & 255) | ((bArr[0] & 255) << 8));
    }

    public static boolean IsHexFormat(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!IsHexStr(bArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public static float GetMaxValue(float[] fArr) {
        if (fArr != null) {
            float f = fArr[0];
            for (int i = 0; i < fArr.length; i++) {
                if (fArr[i] > f) {
                    f = fArr[i];
                }
            }
            return f;
        }
        return 0.0f;
    }

    public static float GetMinValue(float[] fArr) {
        if (fArr != null) {
            float f = fArr[0];
            for (int i = 0; i < fArr.length; i++) {
                if (fArr[i] < f) {
                    f = fArr[i];
                }
            }
            return f;
        }
        return 0.0f;
    }

    public static int StringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean switchToNextInputMethodAndSubtype(IBinder iBinder) {
//        ((InputMethodManager) MyApplication.GetAppContext().getSystemService(Rc.layout.input_method)).toggleSoftInput(0, 2);
        return true;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    }

    public static String GetLastName(String str) {
        int indexOf = str.indexOf(",");
        return indexOf <= 0 ? str : str.substring(0, indexOf);
    }

    public static String GetFirstName(String str) {
        int indexOf = str.indexOf(",");
        int lastIndexOf = str.lastIndexOf(",");
        if (indexOf <= 0) {
            return "";
        }
        if (lastIndexOf == indexOf) {
            return str.substring(indexOf + 1, str.length());
        }
        return str.substring(indexOf + 1, lastIndexOf);
    }

    public static String GetMiddleName(String str) {
        int indexOf = str.indexOf(",");
        int lastIndexOf = str.lastIndexOf(",");
        return (indexOf > 0 && lastIndexOf != indexOf) ? str.substring(lastIndexOf + 1, str.length()) : "";
    }

    public static String GetYear(String str) {
        int indexOf = str.indexOf("/");
        return indexOf <= 0 ? "" : str.substring(0, indexOf);
    }

    public static String GetMonth(String str) {
        int indexOf = str.indexOf("/");
        int lastIndexOf = str.lastIndexOf("/");
        if (indexOf <= 0) {
            return "";
        }
        if (lastIndexOf == indexOf) {
            return str.substring(indexOf + 1, str.length());
        }
        return str.substring(indexOf + 1, lastIndexOf);
    }

    public static String GetDay(String str) {
        int indexOf = str.indexOf("/");
        int lastIndexOf = str.lastIndexOf("/");
        return (indexOf > 0 && lastIndexOf != indexOf) ? str.substring(lastIndexOf + 1, str.length()) : "";
    }

    public static long getDateTime(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3);
        return calendar.getTimeInMillis();
    }
}
