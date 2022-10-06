package com.book.handprobe;


/* loaded from: classes2.dex */
public class ScreenImg {
    private static ScreenImg Inst;
    byte[] mTImgBuf = new byte[614400];
    int mTImgLen = 0;
    byte[] mSImgBuf = new byte[1048576];
    int mSImgLen = 0;
    byte[] mTImgBuf1 = new byte[614400];
    int mTImgLen1 = 0;
    byte[] mSImgBuf1 = new byte[1048576];
    int mSImgLen1 = 0;

    public void reset() {
    }

    public static ScreenImg Instance() {
        if (Inst == null) {
            Inst = new ScreenImg();
        }
        return Inst;
    }

    public void CreateSFrame(byte[] bArr, int i) {
        synchronized (this.mSImgBuf) {
            System.arraycopy(bArr, 0, this.mSImgBuf, 0, i);
            this.mSImgLen = i;
        }
    }

    public void CreateTFrame(int[] iArr, int i) {
        synchronized (this.mTImgBuf) {
//            HDscClassIf.IntArrayToByteArray(this.mTImgBuf, iArr, i);
            this.mTImgLen = i * 4;
        }
    }

    public void CreateTwoBFrame(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            synchronized (this.mSImgBuf) {
                System.arraycopy(bArr, 0, this.mSImgBuf, 0, i);
                this.mSImgLen = i;
            }
        } else if (i2 != 1) {
        } else {
            synchronized (this.mSImgBuf1) {
                System.arraycopy(bArr, 0, this.mSImgBuf1, 0, i);
                this.mSImgLen1 = i;
            }
        }
    }

    public byte[] GetSFrame() {
        byte[] bArr = new byte[this.mSImgLen];
        synchronized (this.mSImgBuf) {
            System.arraycopy(this.mSImgBuf, 0, bArr, 0, this.mSImgLen);
        }
        return bArr;
    }

    public byte[] GetTwoBSFrame() {
        byte[] bArr = new byte[this.mSImgLen + this.mSImgLen1 + 8];
        DataUtil.intInsertToByteArray(this.mSImgLen, bArr, 0);
        DataUtil.intInsertToByteArray(this.mSImgLen1, bArr, 4);
        synchronized (this.mSImgBuf) {
            System.arraycopy(this.mSImgBuf, 0, bArr, 8, this.mSImgLen);
        }
        synchronized (this.mSImgBuf1) {
            System.arraycopy(this.mSImgBuf1, 0, bArr, this.mSImgLen + 8, this.mSImgLen1);
        }
        return bArr;
    }

    public byte[] GetTFrame() {
        byte[] bArr = new byte[this.mTImgLen];
        synchronized (this.mTImgBuf) {
            System.arraycopy(this.mTImgBuf, 0, bArr, 0, this.mTImgLen);
        }
        return bArr;
    }
}
