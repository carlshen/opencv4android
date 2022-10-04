package com.book.tools;

/* loaded from: classes2.dex */
public class CmdParser {
    private byte[] mDataBuff;
    private CmdHandler mICmdHandler;

    public CmdParser(byte[] bArr, CmdHandler cmdHandler) {
        this.mDataBuff = bArr;
        this.mICmdHandler = cmdHandler;
    }

    public CmdParser(CmdHandler cmdHandler) {
        this.mICmdHandler = cmdHandler;
        this.mDataBuff = new byte[16];
    }

    public void setDataBuff(byte[] bArr) {
        this.mDataBuff = bArr;
    }

    public void setHandler(CmdHandler cmdHandler) {
        this.mICmdHandler = cmdHandler;
    }

    public byte[] getDataBuff() {
        return this.mDataBuff;
    }

    public ICmdHandler getHandler() {
        return this.mICmdHandler;
    }

    public int excute(int i, int i2, int i3, byte[] bArr, boolean z) {
        if (this.mDataBuff == null) {
            return -1;
        }
        if (bArr == null) {
            return this.mICmdHandler.excute(i, i2, i3, this.mDataBuff, z);
        }
        return this.mICmdHandler.excute(i, i2, i3, bArr, z);
    }
}
