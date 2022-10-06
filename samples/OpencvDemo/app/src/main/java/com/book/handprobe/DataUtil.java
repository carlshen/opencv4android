package com.book.handprobe;

import android.content.Context;

/* loaded from: classes2.dex */
public class DataUtil {
    public static byte[] intToBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static byte[] shortToBytes(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)};
    }

    public static int intInsertToByteArray(int i, byte[] bArr, int i2) {
        System.arraycopy(intToBytes(i), 0, bArr, i2, 4);
        return 4;
    }

    public static int shortInsertToByteArray(short s, byte[] bArr, int i) {
        System.arraycopy(shortToBytes(s), 0, bArr, i, 2);
        return 2;
    }

    public static int bytesToInt(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static short bytesToShort(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & 255) << 8) | (bArr[i] & 255));
    }

    public static byte[] floatToBytes(float f) {
        return intToBytes(Float.floatToIntBits(f));
    }

    public static int floatInsertToByteArray(float f, byte[] bArr, int i) {
        System.arraycopy(floatToBytes(f), 0, bArr, i, 4);
        return 4;
    }

    public static float bytesToFloat(byte[] bArr, int i) {
        int i2 = ((bArr[i + 3] & 255) << 24) | (bArr[i + 0] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        return (float) (((Integer.MIN_VALUE & i2) == 0 ? 1 : -1) * ((i2 & 8388607) | 8388608) * Math.pow(2.0d, ((2139095040 & i2) >> 23) - 150));
    }

    public static int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
