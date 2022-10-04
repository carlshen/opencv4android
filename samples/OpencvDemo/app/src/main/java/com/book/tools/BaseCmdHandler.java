package com.book.tools;

import android.util.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/* loaded from: classes2.dex */
public class BaseCmdHandler implements ICmdHandler {
    public static final int FE_FPGA_PRB1_INFO = 1;
    public static final int FE_FPGA_PRB2_INFO = 2;
    public static final int FE_FPGA_PRB3_INFO = 3;
    public static final int FE_FPGA_PRB4_INFO = 4;
    public static final int FE_FPGA_Tx_Vol_Reg = 14;
    public static final int FE_FPGA_Version_Reg0 = 0;
    public static final int FPGABaseAddr_ForMem = 0;
    public static final int LOCAL_DAT_PORT = 8083;
    public static final int LOCAL_PORT = 8082;
    public static final int Mem_CtlRegBase = 130048;
    public static final int RECV_TIMEOUT = Integer.MAX_VALUE;
    public static final String REMOTE_BROADCASTER_IP = "192.168.254.255";
    public static final String REMOTE_IP = "192.168.254.1";
    public static final int REMOTE_PORT = 8081;
    public static final byte W_END = -1;
    public static final byte W_HEAD = -2;
    protected InetAddress mBroadCasterAddress;
    public byte[] mData = new byte[2048];
    protected boolean mExitPairThread = true;
    protected DatagramSocket mRecvSocket;
    protected InetAddress mRemoteAddress;
    protected DatagramSocket mSendSocket;
    protected Socket mTcpSendSocket;
    protected DatagramSocket mUDatRecvSocket;

    public int excute(int i, int i2, int i3, byte[] bArr, boolean z) {
        return 0;
    }

    @Override // handprobe.application.wlan.protocol.ICmdHandler
    public int excute(int i, int i2, int i3, byte[] bArr) {
        int i4;
        synchronized (this.mData) {
            this.mData[0] = -2;
            this.mData[1] = (byte) (i2 & 255);
            this.mData[2] = (byte) (i & 255);
            this.mData[3] = (byte) (i3 % 256);
            this.mData[4] = (byte) (i3 / 256);
            if (i == 1) {
                this.mData[3] = 1;
            }
            i4 = i3 * 2;
            char[] cArr = new char[i4];
            if (i != 2 && i != 3) {
                Tools.HexToStr(cArr, bArr, i3);
                for (int i5 = 0; i5 < i3; i5++) {
                    int i6 = i5 * 2;
                    int i7 = i6 + 5;
                    this.mData[i7] = (byte) cArr[i6];
                    this.mData[i7 + 1] = (byte) cArr[i6 + 1];
                }
            }
            for (int i8 = 0; i8 < i3; i8++) {
                this.mData[i8 + 5] = bArr[i8];
            }
        }
        if (i == 2 || i == 3) {
            this.mData[i3 + 5] = -1;
        } else {
            this.mData[i4 + 5] = -1;
        }
        if (i == 2 || i == 3) {
            this.mData[2] = 0;
            return i3 + 6;
        }
        return i4 + 6;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int NetSend(byte[] bArr, int i, InetAddress inetAddress, int i2) {
        DatagramPacket datagramPacket = new DatagramPacket(bArr, i, inetAddress, i2);
        try {
            if (this.mSendSocket == null || this.mSendSocket.isClosed()) {
                return 0;
            }
            this.mSendSocket.send(datagramPacket);
            return 0;
        } catch (IOException e) {
            Log.d("excute", e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int Broadcaster(byte[] bArr, int i, int i2) throws UnknownHostException {
        this.mBroadCasterAddress = InetAddress.getByName(NetUtil.GetBroadcasterIP(NetUtil.getLocalIPAddress()));
        DatagramPacket datagramPacket = new DatagramPacket(bArr, i, this.mBroadCasterAddress, i2);
        try {
            if (this.mSendSocket == null || this.mSendSocket.isClosed()) {
                return 0;
            }
            this.mSendSocket.send(datagramPacket);
            return 0;
        } catch (IOException e) {
            Log.d("excute", e.toString());
            e.printStackTrace();
            return -1;
        }
    }
}
