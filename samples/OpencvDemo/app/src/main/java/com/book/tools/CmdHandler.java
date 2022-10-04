package com.book.tools;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.view.ViewCompat;

import com.book.handprobe.Ultrasys;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import net.bytebuddy.pool.TypePool;
import org.xutils.x;

import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class CmdHandler extends BaseCmdHandler {
    public static Queue<CmdInfo.CmdElem> mNetDataQueue = new LinkedList();
    public Thread mPackProcThread;
    private Thread mPairThread;
    public Timer mPairTimer;
    public TimerTask mPairTimerTask;
    private HObserver mProductIdObserver;
    private Thread mUsbProcThread;
    private Thread mWUsbProcThread;
    public Handler mWlanProbeHandler;
    private byte[] mRecvData = new byte[16384];
    private byte[] mDatRecvData = new byte[2048];
    private byte[] mDatRecvTmpData = new byte[2048];
    private byte[] mTcpRecvData = new byte[2880];
    public CmdInfo mCmdInfo = new CmdInfo();
    public volatile Boolean mPackProcThreadExit = false;
    public boolean TCP_PARA = true;
    public boolean mTcpReady = false;
    public boolean mUdpReady = false;
    public boolean mManualPair = false;

    private boolean varify(DatagramPacket datagramPacket) {
        return true;
    }

    public void init() {
        Log.d("CmdHandler", "init");
        try {
            this.mSendSocket = new DatagramSocket();
            this.mRemoteAddress = InetAddress.getByName(BaseCmdHandler.REMOTE_IP);
            this.mUDatRecvSocket = new DatagramSocket((int) BaseCmdHandler.LOCAL_DAT_PORT);
            this.mUDatRecvSocket.setSoTimeout(100);
            Log.i("recv socket", "ip: " + NetUtil.getIP());
        } catch (Exception e) {
            Log.d("CmdHandler", e.toString());
            e.printStackTrace();
        }
        WUsbProcThreadInit();
        UsbProcThreadInit();
    }

    @Override // handprobe.application.wlan.protocol.BaseCmdHandler
    public int excute(int i, int i2, int i3, byte[] bArr, boolean z) {
        synchronized (this.mData) {
            int excute = super.excute(i, i2, i3, bArr);
            if (z) {
                wlanBroadcaster(this.mData, excute);
            } else {
                if (Ultrasys.Instance().GetProbeConType() != 3) {
                    wlanSend(this.mData, excute);
                }
                wusbSend(this.mData, excute);
            }
        }
        return 0;
    }

    public void DisConnectNet() {
        if (this.mPackProcThread != null) {
            while (this.mPackProcThread.isAlive()) {
                this.mPackProcThreadExit = true;
                Tools.Sleep(10);
            }
        }
    }

    public void ResetNet() {
        if (this.mPackProcThread != null) {
            if (this.mPairThread != null) {
                while (this.mPairThread.isAlive()) {
                    this.mExitPairThread = true;
                }
            }
            if (this.mPackProcThread.isAlive()) {
                return;
            }
            PackProcThreadInit();
            return;
        }
        PackProcThreadInit();
    }

    public boolean CheckTCP() {
        if (Ultrasys.Instance().GetProbeConType() == 5 && !this.mTcpReady) {
            if (TcpSocketConnect(0)) {
                this.mTcpReady = true;
                return true;
            }
            this.mTcpReady = false;
        }
        return true;
    }

    public boolean IsTCPReady() {
        if (Ultrasys.Instance().GetProbeConType() == 5) {
            return this.mTcpReady;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean TcpSocketConnect(int i) {
        if (this.mTcpSendSocket != null) {
            try {
                this.mTcpSendSocket.close();
            } catch (Exception unused) {
            }
        }
        try {
            if (NetUtil.getIP() == null) {
                return true;
            }
            this.mTcpSendSocket = new Socket();
            this.mTcpSendSocket.connect(new InetSocketAddress(BaseCmdHandler.REMOTE_IP, (int) BaseCmdHandler.REMOTE_PORT), 1000);
            if (this.mTcpSendSocket.isConnected()) {
                Log.d("TCP", "connect to Server success");
                this.mTcpSendSocket.setSoTimeout(1500);
                return true;
            }
            Log.d("TCP", "connect to Server fail");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void PackProcThreadInit() {
        this.mPackProcThreadExit = false;
        this.mUdpReady = false;
        this.mPackProcThread = new Thread(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.1
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                boolean z2;
                InetAddress ip = NetUtil.getIP();
                try {
                    if (CmdHandler.this.mRecvSocket != null) {
                        CmdHandler.this.mRecvSocket.close();
                        CmdHandler.this.mRecvSocket = null;
                    }
                    if (CmdHandler.this.mUDatRecvSocket != null) {
                        CmdHandler.this.mUDatRecvSocket.close();
                        CmdHandler.this.mUDatRecvSocket = null;
                    }
                    CmdHandler.this.mTcpReady = false;
                    if (CmdHandler.this.TCP_PARA && Ultrasys.Instance().GetProbeConType() == 5) {
                        int i = 0;
                        while (!CmdHandler.this.TcpSocketConnect(0)) {
                            i++;
                            if (i > 5) {
                                Tools.ShowText((int) R.array.probe_connect_fail);
                                return;
                            }
                        }
                        CmdHandler.this.mTcpReady = true;
                        Tools.ShowText((int) R.array.ping_success);
                    } else if (Ultrasys.Instance().GetProbeConType() == 5) {
                        Tools.ShowText((int) R.array.ping_fail);
                    }
                    CmdHandler.this.mRecvSocket = new DatagramSocket((int) BaseCmdHandler.LOCAL_PORT);
                    CmdHandler.this.mRemoteAddress = InetAddress.getByName(BaseCmdHandler.REMOTE_IP);
                    CmdHandler.this.TCP_PARA = true;
                    CmdHandler.this.mSendSocket = new DatagramSocket();
                    CmdHandler.this.mUDatRecvSocket = new DatagramSocket((int) BaseCmdHandler.LOCAL_DAT_PORT);
                    CmdHandler.this.mUDatRecvSocket.setSoTimeout(100);
                    CmdHandler.this.mRecvSocket.setSoTimeout(200);
                    z = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    z = true;
                }
                if (z) {
                    try {
                        CmdHandler.this.mRecvSocket = new DatagramSocket(BaseCmdHandler.LOCAL_PORT, ip);
                        CmdHandler.this.mRemoteAddress = InetAddress.getByName(BaseCmdHandler.REMOTE_IP);
                        CmdHandler.this.mRecvSocket.setSoTimeout(200);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        CmdHandler.this.mPackProcThreadExit = true;
                    }
                }
                DatagramPacket datagramPacket = new DatagramPacket(CmdHandler.this.mRecvData, 1440);
                CmdHandler.this.mUdpReady = true;
                while (!CmdHandler.this.mPackProcThreadExit.booleanValue()) {
                    if (CmdHandler.this.mRecvSocket != null) {
                        try {
                            CmdHandler.this.mRecvSocket.receive(datagramPacket);
                            z2 = false;
                        } catch (Exception unused) {
                            z2 = true;
                        }
                        if (z2) {
                            Tools.Sleep(6);
                        }
                    }
                    if (Ultrasys.Instance().GetProbeConType() == 3 || Ultrasys.Instance().GetProbeConType() == 4) {
                        Tools.Sleep(100);
                    } else if (CmdHandler.this.mRecvSocket == null) {
                        Tools.Sleep(10);
                    } else if (datagramPacket.getLength() == 0) {
                        Tools.Sleep(3);
                    } else if (datagramPacket.getLength() < 2048) {
                        short s = (short) ((CmdHandler.this.mRecvData[2] & 255) + ((CmdHandler.this.mRecvData[3] & 255) << 8));
                        if (s <= 8189 && s != 0) {
                            CmdInfo cmdInfo = CmdHandler.this.mCmdInfo;
                            cmdInfo.getClass();
                            CmdInfo.CmdElem cmdElem = new CmdInfo.CmdElem(s);
                            cmdElem.cmdid = CmdHandler.this.mRecvData[0];
                            cmdElem.dir = CmdHandler.this.mRecvData[1];
                            cmdElem.len = s;
                            if (cmdElem.cmdid == 194) {
                                CmdHandler.this.CheckTCP();
                            }
                            if (cmdElem.cmdid == 142) {
                                cmdElem.cmdid = CmdHandler.this.mRecvData[0];
                            }
                            if (cmdElem.dir == 3) {
                                System.arraycopy(CmdHandler.this.mRecvData, 4, cmdElem.Data, 0, s);
                            } else {
                                Tools.StrToHex(CmdHandler.this.mRecvData, 4, cmdElem.Data, 0, s);
                            }
                            if (cmdElem.dir == 2) {
                                synchronized (CmdHandler.mNetDataQueue) {
                                    CmdHandler.mNetDataQueue.add(cmdElem);
                                }
                            } else {
                                synchronized (CmdHandler.mNetDataQueue) {
                                    CmdHandler.mNetDataQueue.add(cmdElem);
                                }
                                Message obtainMessage = CmdHandler.this.mWlanProbeHandler.obtainMessage();
                                obtainMessage.what = 1;
                                CmdHandler.this.mWlanProbeHandler.sendMessage(obtainMessage);
                            }
                        }
                    } else {
                        Tools.Sleep(3);
                    }
                }
                try {
                    CmdHandler.this.mTcpSendSocket.close();
                } catch (Exception unused2) {
                }
                CmdHandler.this.mTcpSendSocket = null;
            }
        });
        this.mPackProcThread.start();
    }

    private TimerTask initTimerTask() {
        this.mPairTimerTask = new TimerTask() { // from class: handprobe.application.wlan.protocol.CmdHandler.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                x.task().post(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ProbeWifiInfo.removeProbeConfigInfo("\"#yk00#\"");
                        Tools.ShowText((int) R.array.Pairing_failure);
                        CmdHandler.this.SetManualPair(false);
                    }
                });
            }
        };
        return this.mPairTimerTask;
    }

    public void StartWlanPairTimer() {
        this.mPairTimer = new Timer();
        this.mPairTimer.schedule(initTimerTask(), 8000L);
    }

    public void SetManualPair(boolean z) {
        this.mManualPair = z;
    }

    public void StartWlanPair() {
        if (this.mPairTimer != null) {
            this.mPairTimer.cancel();
        }
        if (!this.mManualPair) {
            return;
        }
        this.mManualPair = false;
        MainHandler.getInstance().post(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.3
            @Override // java.lang.Runnable
            public void run() {
                if (CmdHandler.this.mProductIdObserver == null) {
                    CmdHandler.this.mProductIdObserver = new HObserver() { // from class: handprobe.application.wlan.protocol.CmdHandler.3.1
                        @Override // java.util.Observer
                        public void update(Observable observable, Object obj) {
                            MainHandler.getInstance().postDelayed(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.3.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ProbeWifiInfo.removeProbeConfigInfo("\"#yk00#\"");
//                                    ProbeWifiInfo.saveWifiConnectPreferences(AppProc.Instance().mMyMainActivity, WlanProbe.Instance().GetProductId(), "12345678");
                                    ProbeWifiInfo.setIsManualDisconnect(true);
                                    ProbeWifiInfo.Connect(WlanProbe.Instance().GetProductId(), "12345678");
                                    Tools.ShowText((int) R.array.Processing);
                                }
                            }, 4200L);
                        }
                    };
                    WlanProbe.Instance().GetProductObservable().addObserver(CmdHandler.this.mProductIdObserver);
                }
                if (CmdHandler.this.mPairThread != null) {
                    while (CmdHandler.this.mPairThread.isAlive()) {
                        CmdHandler.this.mExitPairThread = true;
                    }
                }
                CmdHandler.this.mPairThread = new Thread(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CmdHandler.this.mExitPairThread = false;
                        try {
                            if (CmdHandler.this.mRecvSocket != null) {
                                CmdHandler.this.mRecvSocket.close();
                                CmdHandler.this.mRecvSocket = null;
                            }
                            if (CmdHandler.this.mUDatRecvSocket != null) {
                                CmdHandler.this.mUDatRecvSocket.close();
                                CmdHandler.this.mUDatRecvSocket = null;
                            }
                            CmdHandler.this.mRecvSocket = new DatagramSocket((int) BaseCmdHandler.LOCAL_PORT);
                            CmdHandler.this.mRemoteAddress = InetAddress.getByName(BaseCmdHandler.REMOTE_IP);
                            CmdHandler.this.TCP_PARA = true;
                            CmdHandler.this.mSendSocket = new DatagramSocket();
                            CmdHandler.this.mUDatRecvSocket = new DatagramSocket((int) BaseCmdHandler.LOCAL_DAT_PORT);
                            CmdHandler.this.mUDatRecvSocket.setSoTimeout(300);
                            byte[] bArr = new byte[2048];
                            byte[] bArr2 = new byte[64];
                            new Thread(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.3.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Tools.Sleep(1000);
                                    WlanProbe.Instance().SendCmd(150, 1, new byte[2], 1);
                                }
                            }).start();
                            long j = 0;
                            int i = 0;
                            while (true) {
                                if (CmdHandler.this.mExitPairThread) {
                                    break;
                                }
                                j++;
                                try {
                                    DatagramPacket datagramPacket = new DatagramPacket(bArr, 32);
                                    CmdHandler.this.mUDatRecvSocket.receive(datagramPacket);
                                    i = datagramPacket.getLength();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (j <= 10) {
                                    if (i > 0 && i < 1440) {
                                        break;
                                    }
                                } else {
                                    CmdHandler.this.mExitPairThread = true;
                                    break;
                                }
                            }
                            if (CmdHandler.this.mExitPairThread) {
                                CmdHandler.this.close();
                                Tools.ShowText((int) R.array.Pairing_failure);
                                return;
                            }
                            if (i != 0) {
                                CmdInfo cmdInfo = new CmdInfo();
                                cmdInfo.getClass();
                                CmdInfo.CmdElem cmdElem = new CmdInfo.CmdElem(i);
                                cmdElem.dir = (byte) 2;
                                cmdElem.cmdid = (byte) -106;
                                cmdElem.len = (short) i;
                                System.arraycopy(bArr, 0, cmdElem.Data, 0, i);
                                synchronized (CmdHandler.mNetDataQueue) {
                                    CmdHandler.mNetDataQueue.add(cmdElem);
                                }
                                Message obtain = Message.obtain();
                                obtain.what = 1;
                                WlanProbe.Instance().mHandler.sendMessage(obtain);
                                bArr2[0] = -2;
                                bArr2[1] = (byte) 150;
                                bArr2[2] = (byte) 2;
                                bArr2[3] = (byte) 1;
                                bArr2[4] = (byte) 0;
                                bArr2[5] = (byte) 0; //HSUsbProbe.VR_GET_EEPROM_REG3;
                                bArr2[6] = -1;
                                CmdHandler.this.mSendSocket.send(new DatagramPacket(bArr2, 7, CmdHandler.this.mRemoteAddress, (int) BaseCmdHandler.REMOTE_PORT));
                            }
                            if (CmdHandler.this.mRecvSocket != null) {
                                CmdHandler.this.mRecvSocket.close();
                                CmdHandler.this.mRecvSocket = null;
                            }
                            if (CmdHandler.this.mUDatRecvSocket != null) {
                                CmdHandler.this.mUDatRecvSocket.close();
                                CmdHandler.this.mUDatRecvSocket = null;
                            }
                            if (CmdHandler.this.mSendSocket != null) {
                                CmdHandler.this.mSendSocket.close();
                                CmdHandler.this.mSendSocket = null;
                            }
                            CmdHandler.this.mExitPairThread = true;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            CmdHandler.this.mExitPairThread = true;
                        }
                    }
                });
                CmdHandler.this.mPairThread.start();
            }
        });
    }

    public void close() {
        if (this.mRecvSocket != null) {
            this.mRecvSocket.close();
            this.mRecvSocket = null;
        }
        if (this.mSendSocket != null) {
            this.mSendSocket.close();
            this.mSendSocket = null;
        }
        if (this.mUDatRecvSocket != null) {
            this.mUDatRecvSocket.close();
            this.mUDatRecvSocket = null;
        }
        if (this.mTcpSendSocket != null) {
            try {
                this.mTcpSendSocket.close();
            } catch (Exception unused) {
            }
            this.mTcpSendSocket = null;
        }
    }

    private void WUsbProcThreadInit() {
        this.mWUsbProcThread = new Thread(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.4
            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                while (true) {
                    int i2 = 0;
                    while (true) {
                        SystemClock.sleep(100L);
                        if (Ultrasys.Instance().GetProbeConType() == 3) {
//                            if (CmdHandler.this.mRecvData != null && UsbProbeManager.Instance().mUsbInterface != null) {
//                                int ReadData = UsbProbeManager.Instance().mUsbInterface.ReadData(CmdHandler.this.mRecvData);
//                                int i3 = 0;
//                                while (i3 < ReadData) {
//                                    if (CmdHandler.this.mRecvData[i3] == -2) {
//                                        short s = (short) ((CmdHandler.this.mRecvData[i3 + 3] & 255) + ((CmdHandler.this.mRecvData[i3 + 4] & 255) << 8));
//                                        if (s > 8189 || s == 0) {
//                                            i3++;
//                                        } else {
//                                            CmdInfo cmdInfo = CmdHandler.this.mCmdInfo;
//                                            cmdInfo.getClass();
//                                            CmdInfo.CmdElem cmdElem = new CmdInfo.CmdElem(s);
//                                            int i4 = i3 + 1;
//                                            cmdElem.cmdid = CmdHandler.this.mRecvData[i4];
//                                            cmdElem.dir = CmdHandler.this.mRecvData[i3 + 2];
//                                            cmdElem.len = s;
//                                            Tools.StrToHex(CmdHandler.this.mRecvData, i3 + 5, cmdElem.Data, 0, s);
//                                            if (cmdElem.dir == 2) {
//                                                synchronized (CmdHandler.mNetDataQueue) {
//                                                    CmdHandler.mNetDataQueue.add(cmdElem);
//                                                }
//                                            } else {
//                                                synchronized (CmdHandler.mNetDataQueue) {
//                                                    CmdHandler.mNetDataQueue.add(cmdElem);
//                                                }
//                                                Message obtainMessage = CmdHandler.this.mWlanProbeHandler.obtainMessage();
//                                                obtainMessage.what = 1;
//                                                CmdHandler.this.mWlanProbeHandler.sendMessage(obtainMessage);
//                                            }
//                                            i3 = i4 + 4 + (s * 2);
//                                        }
//                                    }
//                                    i3++;
//                                }
//                                SystemClock.sleep(2L);
//                                int i5 = i + 1;
//                                if (i % 10 == 0) {
//                                    int GetCurProbeId = UsbProbeManager.Instance().mUsbInterface.GetCurProbeId();
//                                    if (i2 == GetCurProbeId || !AppProc.Instance().mUsbProbeConnectDlgFrag.isAdded()) {
//                                        GetCurProbeId = i2;
//                                    } else {
//                                        CmdInfo cmdInfo2 = CmdHandler.this.mCmdInfo;
//                                        cmdInfo2.getClass();
//                                        CmdInfo.CmdElem cmdElem2 = new CmdInfo.CmdElem(1);
//                                        cmdElem2.cmdid = FileDefinition.IDENTIFIER_HEAD;
//                                        cmdElem2.dir = (byte) 0;
//                                        cmdElem2.len = (short) 1;
//                                        cmdElem2.Data[0] = (byte) GetCurProbeId;
//                                        synchronized (CmdHandler.mNetDataQueue) {
//                                            CmdHandler.mNetDataQueue.add(cmdElem2);
//                                        }
//                                        Message obtainMessage2 = CmdHandler.this.mWlanProbeHandler.obtainMessage();
//                                        obtainMessage2.what = 1;
//                                        CmdHandler.this.mWlanProbeHandler.sendMessage(obtainMessage2);
//                                    }
//                                    i2 = GetCurProbeId;
//                                }
//                                i = i5;
//                            }
                        }
                    }
                }
            }
        });
        this.mWUsbProcThread.start();
    }

    private void UsbProcThreadInit() {
        this.mUsbProcThread = new Thread(new Runnable() { // from class: handprobe.application.wlan.protocol.CmdHandler.5
            @Override // java.lang.Runnable
            public void run() {
                int i;
                int i2;
                int i3;
                byte[] bArr = {0, 0, 0, 0};
                byte[] bArr2 = new byte[16];
                byte[] bArr3 = new byte[16];
                byte[] bArr4 = new byte[16];
                int i4 = 0;
                int i5 = 0;
//                while (true) {
//                    if (4 == Ultrasys.Instance().GetProbeConType()) {
//                        byte GetProbeReg = (byte) (UsbProbeManager.Instance().GetProbeReg(1) & 255);
//                        if ((GetProbeReg & 1) != 0) {
//                            i2 = 1;
//                            i = 1;
//                        } else {
//                            i = i4;
//                            i2 = 0;
//                        }
//                        if ((GetProbeReg & 2) != 0) {
//                            i2++;
//                            i = 2;
//                        }
//                        if ((GetProbeReg & 4) != 0) {
//                            i2++;
//                            i = 3;
//                        }
//                        if ((GetProbeReg & 8) != 0) {
//                            i2++;
//                            i = 4;
//                        }
//                        if ((GetProbeReg & 16) != 0) {
//                            i = 5;
//                            i2++;
//                        }
//                        if ((GetProbeReg & 32) != 0) {
//                            i2++;
//                            i3 = 6;
//                        } else {
//                            i3 = i;
//                        }
//                        if (i3 != 0 && i2 == 1) {
//                            CmdInfo cmdInfo = CmdHandler.this.mCmdInfo;
//                            cmdInfo.getClass();
//                            CmdInfo.CmdElem cmdElem = new CmdInfo.CmdElem(2);
//                            cmdElem.cmdid = (byte) -61;
//                            cmdElem.dir = (byte) 0;
//                            cmdElem.len = (short) 2;
//                            cmdElem.Data[0] = (byte) i3;
//                            cmdElem.Data[1] = 1;
//                            synchronized (CmdHandler.mNetDataQueue) {
//                                CmdHandler.mNetDataQueue.add(cmdElem);
//                            }
//                            Message obtainMessage = CmdHandler.this.mWlanProbeHandler.obtainMessage();
//                            obtainMessage.what = 1;
//                            CmdHandler.this.mWlanProbeHandler.sendMessage(obtainMessage);
//                            i3 = 0;
//                        }
//                        int i6 = i5 + 1;
//                        if (i5 % 6 == 0) {
//                            int GetProbeReg2 = UsbProbeManager.Instance().GetProbeReg(3);
//                            CmdInfo cmdInfo2 = CmdHandler.this.mCmdInfo;
//                            cmdInfo2.getClass();
//                            CmdInfo.CmdElem cmdElem2 = new CmdInfo.CmdElem(2);
//                            cmdElem2.cmdid = (byte) -63;
//                            cmdElem2.dir = (byte) 0;
//                            cmdElem2.len = (short) 2;
//                            cmdElem2.Data[0] = (byte) (GetProbeReg2 & 255);
//                            cmdElem2.Data[1] = (byte) ((GetProbeReg2 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
//                            synchronized (CmdHandler.mNetDataQueue) {
//                                CmdHandler.mNetDataQueue.add(cmdElem2);
//                            }
//                            int GetProbeReg3 = UsbProbeManager.Instance().GetProbeReg(2);
//                            CmdInfo cmdInfo3 = CmdHandler.this.mCmdInfo;
//                            cmdInfo3.getClass();
//                            CmdInfo.CmdElem cmdElem3 = new CmdInfo.CmdElem(2);
//                            cmdElem3.cmdid = (byte) -64;
//                            cmdElem3.dir = (byte) 0;
//                            cmdElem3.len = (short) 2;
//                            cmdElem3.Data[0] = (byte) (GetProbeReg3 & 255);
//                            cmdElem3.Data[1] = (byte) ((GetProbeReg3 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
//                            synchronized (CmdHandler.mNetDataQueue) {
//                                CmdHandler.mNetDataQueue.add(cmdElem3);
//                            }
//                            Message obtainMessage2 = CmdHandler.this.mWlanProbeHandler.obtainMessage();
//                            obtainMessage2.what = 1;
//                            CmdHandler.this.mWlanProbeHandler.sendMessage(obtainMessage2);
//                            Tools.Sleep(10);
//                        }
//                        i5 = i6;
//                        i4 = i3;
//                    }
//                    Tools.Sleep(200);
//                }
            }
        });
        this.mUsbProcThread.start();
    }

    public String GetUsbWifiVerInfo() {
        byte[] bArr = new byte[16];
//        if (4 == Ultrasys.Instance().GetProbeConType()) {
//            UsbProbeManager.Instance().Read(130064, bArr, 4);
//            return new String(new char[]{'V', (char) bArr[0], TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, (char) bArr[1], TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, (char) bArr[2]});
//        }
        return null;
    }

    public String GetUsbFpgaVerInfo() {
        byte[] bArr = new byte[16];
//        if (4 == Ultrasys.Instance().GetProbeConType()) {
//            UsbProbeManager.Instance().Read(130060, bArr, 4);
//            return String.format("%08X", Integer.valueOf((bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16) + ((bArr[3] & 255) << 24)));
//        }
        return null;
    }

    private int wlanBroadcaster(byte[] bArr, int i) {
        try {
            return Broadcaster(this.mData, i, BaseCmdHandler.LOCAL_PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int wlanSend(byte[] bArr, int i) {
        return NetSend(bArr, i, this.mRemoteAddress, BaseCmdHandler.REMOTE_PORT);
    }

    private int wusbSend(byte[] bArr, int i) {
//        if (UsbProbeManager.Instance().mUsbInterface == null) {
//            return 0;
//        }
        return 0; //UsbProbeManager.Instance().mUsbInterface.Write(bArr, i);
    }

    public int UDatRecv() {
        DatagramPacket datagramPacket = new DatagramPacket(this.mDatRecvTmpData, 1440);
        int i = 0;
        while (this.mUDatRecvSocket != null && !this.mUDatRecvSocket.isClosed()) {
            try {
                this.mUDatRecvSocket.receive(datagramPacket);
                i = datagramPacket.getLength();
                System.arraycopy(this.mDatRecvTmpData, 0, this.mDatRecvData, 0, i);
            } catch (Exception unused) {
            }
        }
        return i;
    }

    public int UDatRecv0(int i) {
        DatagramPacket datagramPacket = new DatagramPacket(this.mDatRecvTmpData, 1440);
        try {
            this.mUDatRecvSocket.setSoTimeout(i);
            if (this.mUDatRecvSocket == null || this.mUDatRecvSocket.isClosed()) {
                return 0;
            }
            this.mUDatRecvSocket.receive(datagramPacket);
            return datagramPacket.getLength();
        } catch (Exception unused) {
            return 0;
        }
    }

    public int UReadFPGA(int i, byte[] bArr, int i2) {
        synchronized (this.mData) {
            this.mData[0] = -2;
            this.mData[1] = -109;
            this.mData[2] = 0;
            this.mData[3] = (byte) 4;
            this.mData[4] = (byte) 0;
            this.mData[5] = (byte) (i & 255);
            this.mData[6] = (byte) ((65280 & i) >> 8);
            this.mData[7] = (byte) ((16711680 & i) >> 16);
            this.mData[8] = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
            DatagramPacket datagramPacket = new DatagramPacket(this.mData, 9, this.mRemoteAddress, (int) BaseCmdHandler.REMOTE_PORT);
            try {
                if (this.mSendSocket != null && !this.mSendSocket.isClosed()) {
                    this.mSendSocket.send(datagramPacket);
                }
                this.mData[0] = -2;
                this.mData[1] = -108;
                this.mData[2] = 1;
                this.mData[3] = (byte) (i2 % 256);
                this.mData[4] = (byte) (i2 / 256);
                int i3 = i2;
                while (i3 > 0) {
                    int i4 = 1400;
                    if (i3 >= 1400) {
                        this.mData[3] = (byte) 120;
                        this.mData[4] = (byte) 5;
                    } else {
                        this.mData[3] = (byte) 4;
                        this.mData[4] = (byte) 0;
                        i4 = 4;
                    }
                    try {
                        this.mSendSocket.send(new DatagramPacket(this.mData, i4, this.mRemoteAddress, (int) BaseCmdHandler.REMOTE_PORT));
                        int UDatRecv = UDatRecv();
                        if (UDatRecv > 0 && UDatRecv <= i2) {
                            System.arraycopy(this.mDatRecvData, 0, bArr, i2 - i3, UDatRecv);
                            i3 -= UDatRecv;
                        }
                        return -1;
                    } catch (Exception e) {
                        Log.d("excute", "e");
                        e.printStackTrace();
                        return -1;
                    }
                }
                return 0;
            } catch (Exception e2) {
                Log.d("excute", "e");
                e2.printStackTrace();
                return -1;
            }
        }
    }

    public int UWriteFPGA(int i, byte[] bArr, int i2) {
        synchronized (this.mData) {
            this.mData[0] = -2;
            this.mData[1] = -109;
            this.mData[2] = 0;
            char c = 4;
            this.mData[3] = (byte) 4;
            this.mData[4] = (byte) 0;
            this.mData[5] = (byte) (i & 255);
            this.mData[6] = (byte) ((65280 & i) >> 8);
            this.mData[7] = (byte) ((16711680 & i) >> 16);
            this.mData[8] = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
            DatagramPacket datagramPacket = new DatagramPacket(this.mData, 9, this.mRemoteAddress, (int) BaseCmdHandler.REMOTE_PORT);
            try {
                if (this.mSendSocket != null && !this.mSendSocket.isClosed()) {
                    this.mSendSocket.send(datagramPacket);
                }
                this.mData[0] = -2;
                this.mData[1] = -108;
                this.mData[2] = 0;
                this.mData[3] = (byte) (i2 % 256);
                this.mData[4] = (byte) (i2 / 256);
                int i3 = i2;
                while (i3 > 0) {
                    int i4 = 1400;
                    if (i3 >= 1400) {
                        this.mData[3] = (byte) 120;
                        this.mData[c] = (byte) 5;
                    } else {
                        this.mData[3] = (byte) (i3 % 256);
                        this.mData[c] = (byte) (i3 / 256);
                        i4 = i3;
                    }
                    System.arraycopy(bArr, i2 - i3, this.mData, 5, i4);
                    int i5 = i4 + 5;
                    DatagramPacket datagramPacket2 = new DatagramPacket(this.mData, i5, this.mRemoteAddress, (int) BaseCmdHandler.REMOTE_PORT);
                    try {
                        if (this.mSendSocket != null && !this.mSendSocket.isClosed()) {
                            this.mSendSocket.send(datagramPacket2);
                        }
                        Tools.Sleep((i5 / 512) + 1);
                        i3 -= i4;
                        c = 4;
                    } catch (Exception e) {
                        Log.d("excute", "e");
                        e.printStackTrace();
                        return -1;
                    }
                }
            } catch (Exception unused) {
                Log.d("excute", "e");
                return -1;
            }
        }
        return 0;
    }

    public int UReadFPGA_TCP(int i, byte[] bArr, int i2) {
        synchronized (this.mData) {
            this.mData[0] = 9;
            this.mData[1] = 0;
            this.mData[2] = -2;
            this.mData[3] = -109;
            this.mData[4] = 0;
            this.mData[5] = (byte) 4;
            this.mData[6] = (byte) 0;
            this.mData[7] = (byte) (i & 255);
            this.mData[8] = (byte) ((65280 & i) >> 8);
            this.mData[9] = (byte) ((16711680 & i) >> 16);
            this.mData[10] = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
            if (this.mTcpSendSocket != null && this.mTcpSendSocket.isConnected()) {
                try {
                    this.mTcpSendSocket.getOutputStream().write(this.mData, 0, 11);
                    this.mData[2] = -2;
                    this.mData[3] = -108;
                    this.mData[4] = 1;
                    this.mData[5] = (byte) (i2 % 256);
                    this.mData[6] = (byte) (i2 / 256);
                    if (i2 >= 1400) {
                        this.mData[5] = (byte) 120;
                        this.mData[6] = (byte) 5;
                    } else {
                        this.mData[5] = (byte) 4;
                        this.mData[6] = (byte) 0;
                    }
                    try {
                        this.mData[0] = (byte) 5;
                        this.mData[1] = (byte) 0;
                        this.mTcpSendSocket.getOutputStream().write(this.mData, 0, 7);
                        int UDatRecv_TCP = UDatRecv_TCP();
                        if (UDatRecv_TCP > 0 && UDatRecv_TCP <= i2) {
                            System.arraycopy(this.mDatRecvData, 0, bArr, i2 - i2, UDatRecv_TCP);
                        }
                        return -1;
                    } catch (Exception e) {
                        Log.d("excute", "e");
                        e.printStackTrace();
                        return -1;
                    }
                } catch (Exception unused) {
                    Log.d("excute", "e");
                    return -1;
                }
            }
            return 0;
        }
    }

    public int UDatRecv_TCP() {
        if (this.mTcpSendSocket == null || !this.mTcpSendSocket.isConnected()) {
            return 0;
        }
        try {
            this.mTcpSendSocket.getInputStream().read(this.mDatRecvTmpData, 0, 1440);
            byte b = this.mDatRecvTmpData[0];
            System.arraycopy(this.mDatRecvTmpData, 2, this.mDatRecvData, 0, b);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int UWriteFPGA_TCP(int i, byte[] bArr, int i2) {
        synchronized (this.mData) {
            this.mData[0] = 9;
            this.mData[1] = 0;
            this.mData[2] = -2;
            this.mData[3] = -109;
            this.mData[4] = 0;
            this.mData[5] = (byte) 4;
            this.mData[6] = (byte) 0;
            this.mData[7] = (byte) (i & 255);
            this.mData[8] = (byte) ((65280 & i) >> 8);
            this.mData[9] = (byte) ((16711680 & i) >> 16);
            this.mData[10] = (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24);
            try {
                this.mTcpSendSocket.getOutputStream().write(this.mData, 0, 11);
                this.mData[2] = -2;
                this.mData[3] = -108;
                this.mData[4] = 0;
                this.mData[5] = (byte) (i2 % 256);
                this.mData[6] = (byte) (i2 / 256);
                int i3 = i2;
                while (i3 > 0) {
                    int i4 = 1400;
                    if (i3 >= 1400) {
                        this.mData[5] = (byte) 120;
                        this.mData[6] = (byte) 5;
                    } else {
                        this.mData[5] = (byte) (i3 % 256);
                        this.mData[6] = (byte) (i3 / 256);
                        i4 = i3;
                    }
                    System.arraycopy(bArr, i2 - i3, this.mData, 7, i4);
                    int i5 = i4 + 5;
                    try {
                        this.mData[0] = (byte) (i5 % 256);
                        this.mData[1] = (byte) (i5 / 256);
                        this.mTcpSendSocket.getOutputStream().write(this.mData, 0, i5 + 2);
                        Tools.Sleep((i5 / 512) + 1);
                        i3 -= i4;
                    } catch (Exception e) {
                        Log.d("excute", "e");
                        e.printStackTrace();
                        return -1;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.d("excute", "e");
                return -1;
            }
        }
        return 0;
    }

    public void ClearRecvPack() {
        DatagramPacket datagramPacket = new DatagramPacket(this.mDatRecvTmpData, 1440);
        while (this.mUDatRecvSocket != null && !this.mUDatRecvSocket.isClosed()) {
            try {
                this.mUDatRecvSocket.receive(datagramPacket);
                datagramPacket.getLength();
            } catch (Exception unused) {
                return;
            }
        }
    }

    public int SendPack(int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int UDatRecv0;
        synchronized (this.mData) {
            int excute = super.excute(3, i, i2, bArr);
            if (Ultrasys.Instance().GetProbeConType() == 5) {
                wlanSend(this.mData, excute);
            }
            UDatRecv0 = UDatRecv0(i3);
            if (UDatRecv0 > 0 && UDatRecv0 < 1440) {
                System.arraycopy(this.mDatRecvTmpData, 0, bArr2, 0, UDatRecv0);
            }
        }
        return UDatRecv0;
    }

    public void SetWlanProbeHandler(Handler handler) {
        this.mWlanProbeHandler = handler;
    }

    public Thread getmPackProcThread() {
        return this.mPackProcThread;
    }

    public static DatagramSocket getRandomPort() throws SocketException {
        return new DatagramSocket(0);
    }

    public static DatagramSocket getRangePort(int[] iArr) throws IOException {
        for (int i : iArr) {
            try {
                return new DatagramSocket(i);
            } catch (IOException unused) {
            }
        }
        throw new IOException("no free port found");
    }
}
