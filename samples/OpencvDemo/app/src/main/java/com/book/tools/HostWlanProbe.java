package com.book.tools;

import android.util.Log;

import com.unnamed.b.atv.model.TreeNode;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class HostWlanProbe {
    private static HostWlanProbe Inst;
    public static List<HostElem> mHostList = new LinkedList();
    public HostCmdHandler mCmdHandler;
    private boolean mConnect = false;
    private String mHostIP = "";
    public HObservable mSearchHostObservalbe = new HObservable();

    public static HostWlanProbe Instance() {
        if (Inst == null) {
            synchronized (HostWlanProbe.class) {
                if (Inst == null) {
                    Inst = new HostWlanProbe();
                }
            }
        }
        return Inst;
    }

    private HostWlanProbe() {
        Init();
    }

    public void Init() {
        if (this.mCmdHandler == null) {
            this.mCmdHandler = new HostCmdHandler();
        }
    }

    public void setConnect(boolean z) {
        this.mConnect = z;
    }

    public boolean getConnect() {
        return this.mConnect;
    }

    public void setHostIP(String str) {
        this.mHostIP = str;
    }

    public String getHostIP() {
        return this.mHostIP;
    }

    public void AddtoHostList(String str) {
        int length = str.length();
        int indexOf = str.indexOf(TreeNode.NODES_ID_SEPARATOR);
        HostElem hostElem = new HostElem();
        hostElem.hostname = str.substring(0, indexOf);
        int i = indexOf + 1;
        while (i < length) {
            int indexOf2 = str.indexOf(";", i);
            hostElem.iplist.add(str.substring(i, indexOf2));
            i = indexOf2 + 1;
        }
        int size = mHostList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (hostElem.hostname.equals(mHostList.get(i2).hostname)) {
                if (hostElem.connect) {
                    return;
                }
                mHostList.remove(i2);
            }
        }
        mHostList.add(hostElem);
        this.mSearchHostObservalbe.setChanged();
        this.mSearchHostObservalbe.notifyObservers(mHostList);
    }

    public void SendCmd(int i, int i2, byte[] bArr, int i3, boolean z) {
        this.mCmdHandler.SendCmd(i, i2, bArr, i3, z);
    }

    /* loaded from: classes2.dex */
    public class HostElem {
        private Object addview;
        public String hostname;
        private String ip;
        public List<String> iplist = new LinkedList();
        public HObservable mConnectObsable = new HObservable();
        private boolean connect = false;

        HostElem() {
        }

        public void setConnect(boolean z) throws UnknownHostException {
            this.connect = z;
            this.mConnectObsable.setChanged();
            this.mConnectObsable.notifyObservers(this);
            HostWlanProbe.this.mConnect = z;
            if (HostWlanProbe.this.mConnect) {
                HostWlanProbe.this.mHostIP = this.ip;
                HostWlanProbe.this.mCmdHandler.setRemoteAddr(HostWlanProbe.this.mHostIP);
            }
        }

        public boolean getConnect() {
            return this.connect;
        }

        public HObservable getConnectObsable() {
            return this.mConnectObsable;
        }

        public void setIp(String str) throws UnknownHostException {
            this.ip = str;
            setConnect(false);
        }

        public void setAddview(Object obj) {
            this.addview = obj;
        }

        public Object getAddview() {
            return this.addview;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class HostCmdHandler extends BaseCmdHandler {
        public HostCmdHandler() {
            try {
                this.mSendSocket = new DatagramSocket();
            } catch (Exception e) {
                Log.d("HostCmdHandler", e.toString());
                e.printStackTrace();
            }
        }

        public int wlanBroadcaster(byte[] bArr, int i) {
            try {
                return Broadcaster(this.mData, i, BaseCmdHandler.LOCAL_PORT);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return 0;
            }
        }

        public int SendCmd(final int i, final int i2, byte[] bArr, final int i3, final boolean z) {
            final byte[] bArr2 = new byte[i2];
            for (int i4 = 0; i4 < i2; i4++) {
                if (bArr != null) {
                    bArr2[i4] = bArr[i4];
                } else {
                    bArr2[i4] = 0;
                }
            }
            new Thread(new Runnable() { // from class: handprobe.application.wlan.wlanprobe.HostWlanProbe.HostCmdHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i3 == 1) {
                        HostCmdHandler.this.excute(1, i, i2, bArr2, z);
                    } else {
                        HostCmdHandler.this.excute(0, i, i2, bArr2, z);
                    }
                }
            }).start();
            return 1;
        }

        private int wlanSend(byte[] bArr, int i) {
            return NetSend(bArr, i, this.mRemoteAddress, BaseCmdHandler.LOCAL_PORT);
        }

        @Override // handprobe.application.wlan.protocol.BaseCmdHandler
        public int excute(int i, int i2, int i3, byte[] bArr, boolean z) {
            int excute = super.excute(i, i2, i3, bArr);
            if (z) {
                return wlanBroadcaster(this.mData, excute);
            }
            return wlanSend(this.mData, excute);
        }

        public void setRemoteAddr(String str) throws UnknownHostException {
            this.mRemoteAddress = InetAddress.getByName(str);
        }
    }
}
