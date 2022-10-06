package com.book.tools;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.core.view.ViewCompat;

import com.book.handprobe.AppProc;
import com.book.handprobe.Ultrasys;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gloomyfish.opencvdemo.MyApplication;
import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class WlanProbe {
    public static final int DUAL_PROBE_MODE = 1;
    private static WlanProbe Inst = null;
    public static final int SINGLE_PROBE_MODE = 0;
    public static final int SUPPORT_PROBE_ID_NUM = 34;
    static int lastMode;
    public float[][] TempRes;
    public Integer mBattLevel;
    public HObservable mBattLevelObservable;
    public HObservable mBoardSnObservable;
    private CmdParser mCmdParser;
    public HObservable mEncModeObservable;
    @SuppressLint({"HandlerLeak"})
    public Handler mHandler;
    public CmdHandler mICmdHandler;
    private int mId;
    public Handler mMenuMsgHandle;
    private MsgHook mMsgHook;
    public HObservable mProbeBoardVerObservable;
    public HObservable mProbeFpgaVerObservable;
    public String mProbeFwVer;
    public HObservable mProbeFwVerObservable;
    public HObservable mProbeIdObservable;
    public HObservable mProbePidVerObservable;
    public HObservable mProbeSnObservable;
    public HObservable mProbeStyleObservable;
    public HObservable mProductIdObservable;
    String mSaveSsid;
    private byte[] mSendData;
    public HObservable mTempObservable;
    public Float mTemperature;
    public HObservable mVetObservable;
    public HObservable mWifiFwVerObservable;
    ExecutorService m_CmdSendExec;
    public String m_ProductIdStr;
    public static Queue<CmdInfo.CmdElem> mSendCmdQueue = new LinkedList();
    static int[][] ProbeIdVolMap = (int[][]) Array.newInstance(int.class, 34, 3);
    public final int TEMP_RES_TABLE_NUM = 27;
    public final int BAT_MIN_VOL = 860;
    public final int BAT_AVG_VOL = HFuncId.FUNCID_Sp_Alt;
    public final int BAT_MAX_VOL = 1070;
    public final int TWO_BATT_MAX_VOL = 7960;
    public final int TWO_BATT_MIN_VOL = 6600;
    public final int TWO_BATT_MIN2_VOL = 6900;
    public final int res_scale = 7;
    public final int BAT2_MIN_VOL = 942;
    public final int BAT2_MIN2_VOL = 985;
    public final int BAT2_MAX_VOL = 1137;
    private int mLen = 0;
    private int mRtn = 0;
    private int mDir = 0;
    public CmdInfo mCmdInfo = new CmdInfo();
    public boolean m_AutoRun = false;
    private byte[] mRecvPackBuf = new byte[1440];
    public final String BACK_STRING = "recv success";
    public int mMcuSize = 0;
    public int mVet = 0;
    public Integer mProbeId = -1;
    public int mDualProbeId0 = -1;
    public int mDualProbeId1 = -1;
    public int mDualActive = 0;
    public int mProbeStyle = 0;

    /* loaded from: classes2.dex */
    public interface MsgHook {
        void OnMessage(CmdInfo.CmdElem cmdElem);
    }

    public static int getProbeImgRes(int i) {
        if (i != 11) {
//            switch (i) {
//                case 1:
//                    return R.drawable.c5_2fs;
//                case 2:
//                    return R.drawable.c5_2ks;
//                case 3:
//                    return R.drawable.l11_4ks;
//                case 4:
//                    return R.drawable.c8_5ks;
//                case 5:
//                    return R.drawable.l11_4gs;
//                case 6:
//                    return R.drawable.e10_4ks;
//                case 7:
//                    return R.drawable.l11_4kc;
//                case 8:
//                    return R.drawable.c5_2kc;
//                default:
//                    switch (i) {
//                        case 15:
//                            return R.drawable.c5_2ks;
//                        case 16:
//                            return R.drawable.c8_5kc;
//                        case 17:
//                            return R.drawable.l10_5ks;
//                        case 18:
//                            return R.drawable.l11_4gc;
//                        case 19:
//                            return R.drawable.p4_2fs;
//                        case 20:
//                            return R.drawable.p4_2ks;
//                        case 21:
//                            return R.drawable.l11_4ks;
//                        default:
//                            switch (i) {
//                                case 33:
//                                    return R.drawable.c5_2fs;
//                                case 34:
//                                    return R.drawable.c5_2ks;
//                                case 35:
//                                    return R.drawable.l11_4ks;
//                                case 36:
//                                    return R.drawable.c8_5ks;
//                                case 37:
//                                    return R.drawable.l11_4gs;
//                                case 38:
//                                    return R.drawable.e10_4ks;
//                                case 39:
//                                    return R.drawable.l10_5ks;
//                                default:
//                                    return R.drawable.noneprobe;
//                            }
//                    }
//            }
        }
        return 0; //R.drawable.c5_2fc;
    }

    public void SendWlanMessageBytes(byte b, byte[] bArr, int i) {
    }

    public static WlanProbe Instance() {
        if (Inst == null) {
            synchronized (WlanProbe.class) {
                if (Inst == null) {
                    Inst = new WlanProbe();
                }
            }
        }
        return Inst;
    }

    private WlanProbe() {
        ProbeIdVolMap[0][0] = 10;
        ProbeIdVolMap[0][1] = 45;
        ProbeIdVolMap[0][2] = 1;
        ProbeIdVolMap[1][0] = 46;
        ProbeIdVolMap[1][1] = 100;
        ProbeIdVolMap[1][2] = 2;
        ProbeIdVolMap[2][0] = 101;
        ProbeIdVolMap[2][1] = 150;
        ProbeIdVolMap[2][2] = 3;
        ProbeIdVolMap[3][0] = 151;
        ProbeIdVolMap[3][1] = 199;
        ProbeIdVolMap[3][2] = 4;
        ProbeIdVolMap[4][0] = 200;
        ProbeIdVolMap[4][1] = 250;
        ProbeIdVolMap[4][2] = 5;
        ProbeIdVolMap[5][0] = 251;
        ProbeIdVolMap[5][1] = 310;
        ProbeIdVolMap[5][2] = 6;
        ProbeIdVolMap[6][0] = 311;
        ProbeIdVolMap[6][1] = 354;
        ProbeIdVolMap[6][2] = 2;
        ProbeIdVolMap[7][0] = 355;
        ProbeIdVolMap[7][1] = 410;
        ProbeIdVolMap[7][2] = 3;
        ProbeIdVolMap[8][0] = 411;
        ProbeIdVolMap[8][1] = 454;
        ProbeIdVolMap[8][2] = 4;
        ProbeIdVolMap[9][0] = 455;
        ProbeIdVolMap[9][1] = 510;
        ProbeIdVolMap[9][2] = 5;
        ProbeIdVolMap[10][0] = 511;
        ProbeIdVolMap[10][1] = 560;
        ProbeIdVolMap[10][2] = 6;
        ProbeIdVolMap[11][0] = 2010;
        ProbeIdVolMap[11][1] = 2055;
        ProbeIdVolMap[11][2] = 33;
        ProbeIdVolMap[12][0] = 2056;
        ProbeIdVolMap[12][1] = 2095;
        ProbeIdVolMap[12][2] = 34;
        ProbeIdVolMap[13][0] = 2096;
        ProbeIdVolMap[13][1] = 2135;
        ProbeIdVolMap[13][2] = 35;
        ProbeIdVolMap[14][0] = 2136;
        ProbeIdVolMap[14][1] = 2180;
        ProbeIdVolMap[14][2] = 36;
        ProbeIdVolMap[15][0] = 2181;
        ProbeIdVolMap[15][1] = 2225;
        ProbeIdVolMap[15][2] = 37;
        ProbeIdVolMap[16][0] = 2226;
        ProbeIdVolMap[16][1] = 2275;
        ProbeIdVolMap[16][2] = 38;
        ProbeIdVolMap[17][0] = 2276;
        ProbeIdVolMap[17][1] = 2315;
        ProbeIdVolMap[17][2] = 39;
        ProbeIdVolMap[18][0] = 2316;
        ProbeIdVolMap[18][1] = 2365;
        ProbeIdVolMap[18][2] = 40;
        ProbeIdVolMap[19][0] = 2366;
        ProbeIdVolMap[19][1] = 2415;
        ProbeIdVolMap[19][2] = 41;
        ProbeIdVolMap[20][0] = 2416;
        ProbeIdVolMap[20][1] = 2470;
        ProbeIdVolMap[20][2] = 42;
        ProbeIdVolMap[21][0] = 2471;
        ProbeIdVolMap[21][1] = 2525;
        ProbeIdVolMap[21][2] = 43;
        ProbeIdVolMap[22][0] = 2526;
        ProbeIdVolMap[22][1] = 2580;
        ProbeIdVolMap[22][2] = 44;
        ProbeIdVolMap[23][0] = 2581;
        ProbeIdVolMap[23][1] = 2640;
        ProbeIdVolMap[23][2] = 29;
        ProbeIdVolMap[24][0] = 2641;
        ProbeIdVolMap[24][1] = 2690;
        ProbeIdVolMap[24][2] = 46;
        ProbeIdVolMap[25][0] = 2691;
        ProbeIdVolMap[25][1] = 2750;
        ProbeIdVolMap[25][2] = 15;
        ProbeIdVolMap[26][0] = 2751;
        ProbeIdVolMap[26][1] = 2815;
        ProbeIdVolMap[26][2] = 48;
        ProbeIdVolMap[27][0] = 2816;
        ProbeIdVolMap[27][1] = 2885;
        ProbeIdVolMap[27][2] = 49;
        ProbeIdVolMap[28][0] = 2886;
        ProbeIdVolMap[28][1] = 2945;
        ProbeIdVolMap[28][2] = 50;
        ProbeIdVolMap[29][0] = 2946;
        ProbeIdVolMap[29][1] = 3010;
        ProbeIdVolMap[29][2] = 19;
        ProbeIdVolMap[30][0] = 3011;
        ProbeIdVolMap[30][1] = 3175;
        ProbeIdVolMap[30][2] = 52;
        ProbeIdVolMap[31][0] = 3176;
        ProbeIdVolMap[31][1] = 3260;
        ProbeIdVolMap[31][2] = 21;
        ProbeIdVolMap[32][0] = 3261;
        ProbeIdVolMap[32][1] = 3325;
        ProbeIdVolMap[32][2] = 0;
        ProbeIdVolMap[33][0] = 3326;
        ProbeIdVolMap[33][1] = 3400;
        ProbeIdVolMap[33][2] = 0;
        this.TempRes = (float[][]) Array.newInstance(float.class, 27, 2);
        this.TempRes[0][0] = -40.0f;
        this.TempRes[0][1] = 95.652f;
        this.TempRes[1][0] = 0.0f;
        this.TempRes[1][1] = 27.219f;
        this.TempRes[2][0] = 5.0f;
        this.TempRes[2][1] = 22.021f;
        this.TempRes[3][0] = 10.0f;
        this.TempRes[3][1] = 17.926f;
        this.TempRes[4][0] = 15.0f;
        this.TempRes[4][1] = 14.674f;
        this.TempRes[5][0] = 20.0f;
        this.TempRes[5][1] = 12.081f;
        this.TempRes[6][0] = 25.0f;
        this.TempRes[6][1] = 10.0f;
        this.TempRes[7][0] = 30.0f;
        this.TempRes[7][1] = 8.315f;
        this.TempRes[8][0] = 35.0f;
        this.TempRes[8][1] = 6.948f;
        this.TempRes[9][0] = 40.0f;
        this.TempRes[9][1] = 5.834f;
        this.TempRes[10][0] = 45.0f;
        this.TempRes[10][1] = 4.917f;
        this.TempRes[11][0] = 50.0f;
        this.TempRes[11][1] = 4.161f;
        this.TempRes[12][0] = 55.0f;
        this.TempRes[12][1] = 3.535f;
        this.TempRes[13][0] = 60.0f;
        this.TempRes[13][1] = 3.014f;
        this.TempRes[14][0] = 65.0f;
        this.TempRes[14][1] = 2.586f;
        this.TempRes[15][0] = 70.0f;
        this.TempRes[15][1] = 2.228f;
        this.TempRes[16][0] = 75.0f;
        this.TempRes[16][1] = 1.925f;
        this.TempRes[17][0] = 80.0f;
        this.TempRes[17][1] = 1.669f;
        this.TempRes[18][0] = 85.0f;
        this.TempRes[18][1] = 1.452f;
        this.TempRes[19][0] = 90.0f;
        this.TempRes[19][1] = 1.268f;
        this.TempRes[20][0] = 95.0f;
        this.TempRes[20][1] = 1.11f;
        this.TempRes[21][0] = 100.0f;
        this.TempRes[21][1] = 0.974f;
        this.TempRes[22][0] = 105.0f;
        this.TempRes[22][1] = 0.858f;
        this.TempRes[23][0] = 110.0f;
        this.TempRes[23][1] = 0.758f;
        this.TempRes[24][0] = 115.0f;
        this.TempRes[24][1] = 0.672f;
        this.TempRes[25][0] = 120.0f;
        this.TempRes[25][1] = 0.596f;
        this.TempRes[26][0] = 125.0f;
        this.TempRes[26][1] = 0.531f;
        this.mHandler = new Handler() { // from class: handprobe.application.wlan.wlanprobe.WlanProbe.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:205:0x074c A[Catch: all -> 0x09bf, TryCatch #0 {, blocks: (B:7:0x0009, B:9:0x0013, B:11:0x0015, B:12:0x0017, B:14:0x001b, B:15:0x001e, B:16:0x0021, B:17:0x0024, B:19:0x002e, B:20:0x0031, B:21:0x0034, B:23:0x0038, B:24:0x003b, B:25:0x003e, B:26:0x0041, B:27:0x0044, B:29:0x0049, B:30:0x0059, B:32:0x0061, B:34:0x0070, B:36:0x0075, B:38:0x0083, B:41:0x0094, B:43:0x00a3, B:45:0x00a8, B:48:0x00b5, B:42:0x009c, B:39:0x008b, B:49:0x00ba, B:33:0x0069, B:50:0x00ca, B:51:0x00f8, B:52:0x0106, B:53:0x0114, B:54:0x0123, B:55:0x0132, B:57:0x0141, B:58:0x014a, B:59:0x0153, B:61:0x0159, B:63:0x017d, B:66:0x01ab, B:68:0x01bc, B:71:0x01c9, B:73:0x01cf, B:75:0x01d5, B:77:0x01ee, B:76:0x01e2, B:79:0x0209, B:81:0x0213, B:88:0x0236, B:83:0x021d, B:85:0x0225, B:87:0x022f, B:78:0x01fa, B:64:0x018a, B:65:0x0197, B:203:0x073c, B:205:0x074c, B:89:0x024a, B:91:0x0254, B:92:0x025a, B:93:0x0263, B:95:0x028c, B:97:0x0291, B:100:0x0299, B:101:0x02aa, B:104:0x02b5, B:106:0x02c0, B:108:0x02cf, B:109:0x02d4, B:111:0x02d6, B:105:0x02b9, B:113:0x02ec, B:115:0x02f1, B:117:0x02f7, B:119:0x02fc, B:121:0x030f, B:122:0x033a, B:123:0x0355, B:124:0x035e, B:125:0x0369, B:126:0x0374, B:127:0x037f, B:129:0x0389, B:130:0x0394, B:131:0x03a7, B:132:0x03b2, B:133:0x03bd, B:134:0x03c6, B:135:0x03cf, B:137:0x03d9, B:138:0x03df, B:139:0x03e8, B:140:0x03f7, B:141:0x03fe, B:142:0x040d, B:144:0x0412, B:145:0x0421, B:147:0x0426, B:148:0x0435, B:149:0x0444, B:150:0x0453, B:151:0x0462, B:152:0x0471, B:153:0x0481, B:154:0x0487, B:155:0x0496, B:156:0x04a5, B:157:0x04b4, B:159:0x04b9, B:160:0x04dc, B:161:0x04eb, B:162:0x04fa, B:163:0x0509, B:164:0x0518, B:165:0x0527, B:166:0x0536, B:167:0x0545, B:169:0x054a, B:170:0x056f, B:172:0x0574, B:173:0x059d, B:174:0x05ad, B:175:0x05bc, B:176:0x05cb, B:177:0x05da, B:178:0x05e0, B:179:0x05ef, B:180:0x05fe, B:181:0x0604, B:182:0x0613, B:183:0x0622, B:184:0x0631, B:185:0x0640, B:186:0x064f, B:187:0x065e, B:188:0x066d, B:189:0x067c, B:190:0x068b, B:191:0x069a, B:192:0x06a9, B:193:0x06b8, B:194:0x06c7, B:195:0x06d6, B:196:0x06e5, B:197:0x06f4, B:199:0x06f9, B:201:0x0713, B:202:0x072a, B:252:0x09ac, B:254:0x09b4, B:206:0x0768, B:209:0x0779, B:211:0x077e, B:213:0x07a5, B:216:0x07b2, B:218:0x07b8, B:220:0x07d3, B:222:0x07dd, B:229:0x0800, B:224:0x07e7, B:226:0x07ef, B:228:0x07f9, B:219:0x07c4, B:230:0x081b, B:232:0x0820, B:233:0x0850, B:235:0x0855, B:236:0x089f, B:237:0x08aa, B:239:0x08b2, B:240:0x08bb, B:242:0x08c3, B:243:0x08cc, B:244:0x08e2, B:245:0x08f8, B:246:0x0916, B:247:0x092d, B:248:0x0942, B:249:0x0958, B:250:0x0982, B:251:0x0997), top: B:258:0x0009 }] */
            @Override // android.os.Handler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void handleMessage(Message message) {
                int i;
                if (message.what != 1) {
                    return;
                }
                synchronized (CmdHandler.mNetDataQueue) {
                    while (true) {
                        CmdInfo.CmdElem poll = CmdHandler.mNetDataQueue.poll();
                        if (poll == null) {
                            return;
                        }
                        byte b = poll.cmdid;
                        switch (b) {
                            case -115:
                                String str = new String(poll.Data);
                                WlanProbe.this.mProbeFwVerObservable.setChanged();
                                WlanProbe.this.mProbeFwVerObservable.notifyObservers(str);
                                break;
                            case -114:
                                String byteToStr = null;
                                try {
                                    byteToStr = Tools.byteToStr(poll.Data);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                WlanProbe.this.mWifiFwVerObservable.setChanged();
                                WlanProbe.this.mWifiFwVerObservable.notifyObservers(byteToStr);
                                break;
                            default:
                                int i2 = 0;
                                boolean z = false;
                                int i3 = 0;
                                boolean z2 = false;
                                switch (b) {
                                    case -112:
                                        byte b2 = poll.Data[0];
                                        Ultrasys.Instance().mCurChannel = b2;
//                                        AppProc.Instance().mWifiProbeSelect.setCurSelectChannel(b2);
                                        Log.i("D_WIFI_CHANNEL", "channel = " + ((int) b2));
                                        break;
                                    case -111:
                                        String str2 = new String(poll.Data);
                                        WlanProbe.this.mProbePidVerObservable.setChanged();
                                        WlanProbe.this.mProbePidVerObservable.notifyObservers(str2);
                                        break;
                                    case -110:
                                        String byteToStr2 = null;
                                        try {
                                            byteToStr2 = Tools.byteToStr(poll.Data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        WlanProbe.this.mProbeFpgaVerObservable.setChanged();
                                        WlanProbe.this.mProbeFpgaVerObservable.notifyObservers(byteToStr2);
                                        break;
                                    default:
                                        switch (b) {
                                            case -107:
                                                String str3 = new String(poll.Data);
                                                WlanProbe.this.mProbeSnObservable.setChanged();
                                                WlanProbe.this.mProbeSnObservable.notifyObservers(str3);
                                                break;
                                            case -106:
                                                try {
                                                    WlanProbe.this.m_ProductIdStr = Tools.byteToStr(poll.Data);
                                                } catch (UnsupportedEncodingException e) {
                                                    e.printStackTrace();
                                                }
                                                WlanProbe.this.mProductIdObservable.setChanged();
                                                WlanProbe.this.mProductIdObservable.notifyObservers(WlanProbe.this.m_ProductIdStr);
                                                break;
                                            default:
                                                switch (b) {
                                                    case -101:
                                                        String byteToStr3 = null;
                                                        try {
                                                            byteToStr3 = Tools.byteToStr(poll.Data);
                                                        } catch (UnsupportedEncodingException e) {
                                                            e.printStackTrace();
                                                        }
                                                        WlanProbe.this.mProbeBoardVerObservable.setChanged();
                                                        WlanProbe.this.mProbeBoardVerObservable.notifyObservers(byteToStr3);
                                                        break;
                                                    case -100:
                                                        String byteToStr4 = null;
                                                        try {
                                                            byteToStr4 = Tools.byteToStr(poll.Data);
                                                        } catch (UnsupportedEncodingException e) {
                                                            e.printStackTrace();
                                                        }
                                                        WlanProbe.this.mBoardSnObservable.setChanged();
                                                        WlanProbe.this.mBoardSnObservable.notifyObservers(byteToStr4);
                                                        break;
                                                    default:
                                                        switch (b) {
                                                            case -95:
                                                                if (((byte) (poll.Data[0] & 1)) > 0) {
                                                                    Ultrasys.Instance().SetNoExpand(true);
                                                                    break;
                                                                } else if (((byte) (poll.Data[0] & 1)) == 0) {
                                                                    Ultrasys.Instance().SetNoExpand(false);
                                                                    break;
                                                                }
                                                                break;
                                                            case -94:
                                                                AppProc.Instance().LianmedSendPacket(poll.Data);
                                                                break;
                                                            default:
                                                                switch (b) {
                                                                    case -64:
                                                                        if (poll.Data.length >= 2) {
                                                                            WlanProbe.this.mTemperature = Float.valueOf(WlanProbe.this.GetTemperature((poll.Data[0] & 255) | ((poll.Data[1] & 255) << 8)));
                                                                            WlanProbe.this.mTempObservable.setChanged();
                                                                            WlanProbe.this.mTempObservable.notifyObservers(WlanProbe.this.mTemperature);
                                                                            Log.i("Wlanprobe", "温度: " + WlanProbe.this.mTemperature);
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case -63:
                                                                        if (poll.Data.length >= 2) {
                                                                            WlanProbe.this.mBattLevel = Integer.valueOf(WlanProbe.this.GetBatLevel1((poll.Data[0] & 255) | ((poll.Data[1] & 255) << 8)));
                                                                            WlanProbe.this.mBattLevelObservable.setChanged();
                                                                            WlanProbe.this.mBattLevelObservable.notifyObservers(WlanProbe.this.mBattLevel);
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case -62:
                                                                        WlanProbe.this.SetProbeStyle(0);
                                                                        if (Ultrasys.Instance().GetProbeConType() != 0 && poll.Data.length >= 2) {
                                                                            WlanProbe.this.mProbeId = Integer.valueOf(WlanProbe.CalProbeId((poll.Data[0] & 255) | ((poll.Data[1] & 255) << 8)));
//                                                                            if (!AppProc.Instance().mWifiProbeSelect.isAdded() && !AppProc.Instance().mUsbProbeConnectDlgFrag.isAdded()) {
//                                                                                if (WlanProbe.this.m_AutoRun) {
//                                                                                    WlanProbe.this.m_AutoRun = false;
//                                                                                    Ultrasys.Instance().UnFreeze(true);
//                                                                                }
//                                                                                if ((Ultrasys.Instance().GetProbeConType() != 5 || Ultrasys.Instance().GetProbeConType() == 4) && Ultrasys.Instance().mIsUnFreeState && WlanProbe.this.mProbeId.intValue() == 0) {
//                                                                                    AppProc.Instance().FuncIdProc(181);
//                                                                                }
//                                                                                WlanProbe.this.mProbeIdObservable.setChanged();
//                                                                                WlanProbe.this.mProbeIdObservable.notifyObservers(WlanProbe.this.mProbeId);
//                                                                                Ultrasys.Instance().UpdateBFramerate();
//                                                                                break;
//                                                                            }
                                                                            Ultrasys.Instance().CheckProbeChange(WlanProbe.this.mProbeId.intValue(), false);
                                                                            if (Ultrasys.Instance().GetProbeConType() != 5) {
                                                                            }
                                                                            AppProc.Instance().FuncIdProc(181);
                                                                            WlanProbe.this.mProbeIdObservable.setChanged();
                                                                            WlanProbe.this.mProbeIdObservable.notifyObservers(WlanProbe.this.mProbeId);
                                                                            Ultrasys.Instance().UpdateBFramerate();
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (b) {
                                                                            case -60:
                                                                                break;
                                                                            case -59:
                                                                                if (poll.Data.length >= 2) {
                                                                                    int i4 = ((poll.Data[0] & 255) | ((poll.Data[1] & 255) << 8)) - 48;
                                                                                    if (WlanProbe.this.mProbeId.intValue() != i4) {
                                                                                        WlanProbe.this.mProbeId = Integer.valueOf(i4);
                                                                                        Ultrasys.Instance().SelectProber(WlanProbe.this.mProbeId.intValue(), 0);
                                                                                    }
                                                                                    WlanProbe.this.mProbeIdObservable.setChanged();
                                                                                    WlanProbe.this.mProbeIdObservable.notifyObservers(WlanProbe.this.mProbeId);
                                                                                }
                                                                                i = poll.Data[0] & 255;
                                                                                if (WlanProbe.this.mProbeId.intValue() != i) {
                                                                                    WlanProbe.this.mProbeId = Integer.valueOf(i);
                                                                                    WlanProbe.this.mProbeIdObservable.setChanged();
                                                                                    WlanProbe.this.mProbeIdObservable.notifyObservers(WlanProbe.this.mProbeId);
                                                                                    break;
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (b) {
                                                                                    case 6:
                                                                                        Ultrasys.Instance().SetBGain(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 7:
                                                                                        Ultrasys.Instance().SetBFreq(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 8:
                                                                                        Ultrasys.Instance().SetTsi(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 9:
                                                                                        Ultrasys.Instance().SetAPower(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 10:
                                                                                        Ultrasys.Instance().SetBDynamic(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 11:
                                                                                        Ultrasys.Instance().SetBExpand(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 12:
                                                                                        Ultrasys.Instance().SetBFocus(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 13:
                                                                                        Ultrasys.Instance().SetBEnhance(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 14:
                                                                                        Ultrasys.Instance().SetBFrameComp(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 15:
                                                                                        Ultrasys.Instance().SetBSteer(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 16:
                                                                                        Ultrasys.Instance().SetBFrameCorre(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 17:
                                                                                        Ultrasys.Instance().SetBGrayMap(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 18:
                                                                                        Ultrasys.Instance().setBLR(poll.Data[0] & 255);
                                                                                        break;
                                                                                    case 19:
                                                                                        Ultrasys.Instance().setBUD(poll.Data[0] & 255);
                                                                                        break;
                                                                                    default:
                                                                                        switch (b) {
                                                                                            case 25:
                                                                                                Ultrasys.Instance().SetCGain(poll.Data[0] & 255);
                                                                                                break;
                                                                                            case 26:
                                                                                                Ultrasys.Instance().SetCScale(poll.Data[0] & 255);
                                                                                                break;
                                                                                            case 27:
                                                                                                byte b3 = poll.Data[0];
                                                                                                break;
                                                                                            case 28:
                                                                                                Ultrasys.Instance().SetCFreq(poll.Data[0] & 255);
                                                                                                break;
                                                                                            case 29:
                                                                                                Ultrasys.Instance().SetCWallFilter(poll.Data[0] & 255);
                                                                                                break;
                                                                                            case 30:
                                                                                                byte b4 = poll.Data[0];
                                                                                                break;
                                                                                            case 31:
                                                                                                Ultrasys.Instance().SetCFrameCorre(poll.Data[0] & 255);
                                                                                                break;
                                                                                            case 32:
                                                                                                Ultrasys.Instance().SetCPriority(poll.Data[0] & 255);
                                                                                                break;
                                                                                            case 33:
                                                                                                Ultrasys.Instance().SetVelColorMap(poll.Data[0] & 255);
                                                                                                break;
                                                                                            case 34:
                                                                                                Ultrasys.Instance().SetCSteer((byte) (poll.Data[0] & 255));
                                                                                                break;
                                                                                            case 35:
                                                                                                if (poll.Data.length >= 4) {
                                                                                                    Ultrasys.Instance().SetCLineRange(((poll.Data[0] & 255) | ((poll.Data[1] & 255) << 8)) / 16, ((poll.Data[2] & 255) | ((poll.Data[3] & 255) << 8)) / 16);
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                            case 36:
                                                                                                if (poll.Data.length >= 4) {
                                                                                                    Ultrasys.Instance().SetCDotRange((poll.Data[0] & 255) | ((poll.Data[1] & 255) << 8), (poll.Data[2] & 255) | ((poll.Data[3] & 255) << 8));
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                            default:
                                                                                                switch (b) {
                                                                                                    case 45:
                                                                                                        Ultrasys.Instance().SetMGain(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 46:
                                                                                                        Ultrasys.Instance().SetMSpeed(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 47:
                                                                                                        Ultrasys.Instance().SetMDynamic(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 48:
                                                                                                    case 50:
                                                                                                    case 51:
                                                                                                    case 55:
                                                                                                    case 57:
                                                                                                    case 58:
                                                                                                    case 60:
                                                                                                    case 64:
                                                                                                    case 74:
                                                                                                    case 81:
                                                                                                        break;
                                                                                                    case 49:
                                                                                                        Ultrasys.Instance().SetMGrayMap(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 52:
                                                                                                        Ultrasys.Instance().SetMDispLine(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 53:
                                                                                                        Ultrasys.Instance().SetPwBaseLine(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 54:
                                                                                                        Ultrasys.Instance().SetPwSpeed(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 56:
                                                                                                        if (poll.Data.length >= 4) {
//                                                                                                            Ultrasys.Instance().SetPwAngle((poll.Data[0] & 255) | (poll.Data[1] << 8) | (poll.Data[2] << 16) | (poll.Data[3] << HSUsbProbe.VR_USB_SET_AUTH));
                                                                                                            break;
                                                                                                        }
                                                                                                        break;
                                                                                                    case 59:
                                                                                                        Ultrasys.Instance().SetPwReserse(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 61:
                                                                                                        Ultrasys.Instance().SetPwVolume(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 62:
                                                                                                        Ultrasys.Instance().SetPwDynamic(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 63:
                                                                                                        byte b5 = poll.Data[0];
                                                                                                        break;
                                                                                                    case 65:
                                                                                                        Ultrasys.Instance().SetPWSteer((byte) (poll.Data[0] & 255));
                                                                                                        break;
                                                                                                    case 66:
                                                                                                        Ultrasys.Instance().SetPwFreq(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 67:
                                                                                                        Ultrasys.Instance().SetPwScale(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 68:
                                                                                                        Ultrasys.Instance().SetPwGain(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 69:
                                                                                                        Ultrasys.Instance().SetPwWallFilter(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 70:
                                                                                                        if (poll.Data.length >= 4) {
                                                                                                            Ultrasys.Instance().SetPwGateSize(Tools.B2F(poll.Data));
                                                                                                            break;
                                                                                                        }
                                                                                                        break;
                                                                                                    case 71:
                                                                                                        if (poll.Data.length >= 4) {
                                                                                                            Ultrasys.Instance().SetPwGatePos(Tools.B2F(poll.Data));
                                                                                                            break;
                                                                                                        }
                                                                                                        break;
                                                                                                    case 72:
                                                                                                        Ultrasys.Instance().SetPwDispLine(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 73:
                                                                                                        "12345678".toCharArray();
                                                                                                        break;
                                                                                                    case 75:
                                                                                                        Ultrasys.Instance().SetAPower(poll.Data[0] & 255);
                                                                                                        break;
                                                                                                    case 76:
                                                                                                        int i5 = poll.Data[0] & 255;
                                                                                                        if (poll.len == 2) {
                                                                                                            i2 = poll.Data[1] & 255;
                                                                                                        }
                                                                                                        Ultrasys.Instance().SetExamType(i5, i2);
                                                                                                        break;
                                                                                                    case 77:
                                                                                                        Ultrasys.Instance().SetDispMode(0);
                                                                                                        break;
                                                                                                    case 78:
                                                                                                        Ultrasys.Instance().SetDispMode(8);
                                                                                                        break;
                                                                                                    case 79:
                                                                                                        Ultrasys.Instance().SetDispMode(9);
                                                                                                        break;
                                                                                                    case 80:
                                                                                                        Ultrasys.Instance().SetDispMode(55);
                                                                                                        break;
                                                                                                    case 82:
                                                                                                        if (Ultrasys.Instance().GetDispMode() == 5) {
                                                                                                            Ultrasys.Instance().SetDispMode(WlanProbe.lastMode);
                                                                                                            break;
                                                                                                        } else {
                                                                                                            WlanProbe.lastMode = Ultrasys.Instance().GetDispMode();
                                                                                                            Ultrasys.Instance().SetDispMode(5);
                                                                                                            break;
                                                                                                        }
                                                                                                    default:
                                                                                                        switch (b) {
                                                                                                            case 89:
                                                                                                                Ultrasys.Instance().SetDispMode(57);
                                                                                                                break;
                                                                                                            case 90:
                                                                                                                Ultrasys.Instance().SetDispMode(58);
                                                                                                                break;
                                                                                                            case 91:
                                                                                                                Ultrasys.Instance().SetDispMode(59);
                                                                                                                break;
                                                                                                            case 92:
                                                                                                                Ultrasys.Instance().SetDispMode(5);
                                                                                                                break;
                                                                                                            default:
                                                                                                                switch (b) {
                                                                                                                    case 100:
                                                                                                                        byte[] bArr = poll.Data;
                                                                                                                        int i6 = bArr[0] & 255;
                                                                                                                        WlanProbe.this.mVet = i6;
                                                                                                                        Ultrasys.Instance().mProbeDisabled = false;
                                                                                                                        if (MyApplication.App().mMainActivity.getResources().getString(R.string.def_vet).equals("off")) {
                                                                                                                            if (poll.Data.length > 1) {
                                                                                                                                if ((bArr[1] & 255) > 0 && i6 > 0) {
                                                                                                                                    byte[] bArr2 = new byte[32];
                                                                                                                                    bArr2[0] = (byte) 0;
                                                                                                                                    Ultrasys.Instance().SendCmdToProbe(100, 1, bArr2, 0);
                                                                                                                                    i6 = 0;
                                                                                                                                }
                                                                                                                                Ultrasys Instance = Ultrasys.Instance();
                                                                                                                                if ((bArr[1] & 255) > 0) {
                                                                                                                                    z2 = true;
                                                                                                                                }
                                                                                                                                Instance.SetHumanVetAuth(z2);
                                                                                                                            } else {
                                                                                                                                Ultrasys.Instance().SetHumanVetAuth(false);
                                                                                                                            }
                                                                                                                            Ultrasys.Instance().SetVet(i6);
                                                                                                                            if (Ultrasys.Instance().mProbeDisabled) {
                                                                                                                                WlanProbe.this.confirmProbeError();
                                                                                                                                return;
                                                                                                                            }
                                                                                                                            WlanProbe.this.mVetObservable.setChanged();
                                                                                                                            WlanProbe.this.mVetObservable.notifyObservers(Integer.valueOf(i6));
                                                                                                                        } else {
                                                                                                                            if (i6 == 0) {
                                                                                                                                if (poll.Data.length > 1 && (bArr[1] & 255) > 0) {
                                                                                                                                    WlanProbe.this.mVet = 1;
                                                                                                                                }
                                                                                                                                i6 = 1;
                                                                                                                            }
//                                                                                                                            Ultrasys.Instance().mPresetServer.initFromFile(false);
//                                                                                                                            if (i6 != Ultrasys.Instance().GetVet()) {
//                                                                                                                                int GetProbeDefaultExamMode = Ultrasys.Instance().mPresetServer.mExamModePresetServer.GetProbeDefaultExamMode(WlanProbe.this.mProbeId.intValue());
//                                                                                                                                Ultrasys.Instance().SetExamType(GetProbeDefaultExamMode, Ultrasys.Instance().GetPattern(WlanProbe.this.mProbeId.intValue(), GetProbeDefaultExamMode));
//                                                                                                                            }
                                                                                                                            Ultrasys.Instance().SetVet(i6);
                                                                                                                            WlanProbe.this.mVetObservable.setChanged();
                                                                                                                            WlanProbe.this.mVetObservable.notifyObservers(Integer.valueOf(i6));
                                                                                                                        }
                                                                                                                        break;
                                                                                                                    case 101:
                                                                                                                        int i7 = poll.Data[0] & 255;
                                                                                                                        if (poll.len == 2) {
                                                                                                                            i3 = poll.Data[1] & 255;
                                                                                                                        }
                                                                                                                        Ultrasys.Instance().SetExamType(i7, i3);
                                                                                                                        break;
                                                                                                                    default:
                                                                                                                        switch (b) {
                                                                                                                            case -56:
                                                                                                                                if (WlanProbe.this.mDualProbeId0 < 0) {
                                                                                                                                    WlanProbe.this.mDualProbeId0 = poll.Data[0] & 255;
                                                                                                                                    WlanProbe.this.mDualProbeId1 = poll.Data[1] & Byte.MAX_VALUE;
                                                                                                                                    WlanProbe.this.mDualActive = poll.Data[2] & 255;
                                                                                                                                    if (WlanProbe.this.mDualActive == 0) {
                                                                                                                                        WlanProbe.this.mProbeId = Integer.valueOf(WlanProbe.this.mDualProbeId0);
                                                                                                                                    } else {
                                                                                                                                        WlanProbe.this.mProbeId = Integer.valueOf(WlanProbe.this.mDualProbeId1);
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    WlanProbe.this.mDualProbeId0 = poll.Data[0] & 255;
                                                                                                                                    WlanProbe.this.mDualProbeId1 = poll.Data[1] & Byte.MAX_VALUE;
                                                                                                                                }
                                                                                                                                WlanProbe.this.SetProbeStyle(1);
//                                                                                                                                if (!AppProc.Instance().mWifiProbeSelect.isAdded() && !AppProc.Instance().mUsbProbeConnectDlgFrag.isAdded()) {
//                                                                                                                                    if (WlanProbe.this.m_AutoRun) {
//                                                                                                                                        if (WlanProbe.this.mDualActive == 0) {
//                                                                                                                                            WlanProbe.this.mProbeId = Integer.valueOf(WlanProbe.this.mDualProbeId0);
//                                                                                                                                        } else {
//                                                                                                                                            WlanProbe.this.mProbeId = Integer.valueOf(WlanProbe.this.mDualProbeId1);
//                                                                                                                                        }
//                                                                                                                                        WlanProbe.this.m_AutoRun = false;
//                                                                                                                                        Ultrasys.Instance().UnFreeze(true);
//                                                                                                                                    }
//                                                                                                                                    if ((Ultrasys.Instance().GetProbeConType() != 5 || Ultrasys.Instance().GetProbeConType() == 4) && Ultrasys.Instance().mIsUnFreeState && WlanProbe.this.mProbeId.intValue() == 0) {
//                                                                                                                                        AppProc.Instance().FuncIdProc(181);
//                                                                                                                                    }
//                                                                                                                                    WlanProbe.this.mProbeIdObservable.setChanged();
//                                                                                                                                    WlanProbe.this.mProbeIdObservable.notifyObservers(WlanProbe.this.mProbeId);
//                                                                                                                                    break;
//                                                                                                                                }
                                                                                                                                Ultrasys.Instance().CheckProbeChange(WlanProbe.this.mProbeId.intValue(), false);
                                                                                                                                if (Ultrasys.Instance().GetProbeConType() != 5) {
                                                                                                                                }
                                                                                                                                AppProc.Instance().FuncIdProc(181);
                                                                                                                                WlanProbe.this.mProbeIdObservable.setChanged();
                                                                                                                                WlanProbe.this.mProbeIdObservable.notifyObservers(WlanProbe.this.mProbeId);
                                                                                                                                break;
                                                                                                                            case -16:
                                                                                                                                i = poll.Data[0] & 255;
                                                                                                                                if (WlanProbe.this.mProbeId.intValue() != i) {
                                                                                                                                }
                                                                                                                                break;
                                                                                                                            case 2:
                                                                                                                                int i8 = poll.Data[0] & 255;
                                                                                                                                Ultrasys.Instance().SetCmdExecuting(false);
                                                                                                                                if (i8 > 0) {
                                                                                                                                    Ultrasys.Instance().Freeze();
                                                                                                                                    break;
                                                                                                                                } else {
                                                                                                                                    Ultrasys.Instance().UnFreeze(true);
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                            case 4:
                                                                                                                                Ultrasys.Instance().SetDepth(poll.Data[0] & 255);
                                                                                                                                break;
                                                                                                                            case 43:
                                                                                                                                Ultrasys.Instance().SetPowerColorMap(poll.Data[0] & 255);
                                                                                                                                break;
                                                                                                                            case 86:
                                                                                                                                Ultrasys.Instance().SetPresetParam(poll.Data, poll.Data.length);
                                                                                                                                break;
                                                                                                                            case 94:
                                                                                                                                Ultrasys.Instance().setMITISData(poll.Data, poll.Data.length);
                                                                                                                                break;
                                                                                                                            case 96:
                                                                                                                                Integer valueOf = Integer.valueOf(poll.Data[0]);
                                                                                                                                Log.i("W_ENCMODE_CMD", "encMode = " + valueOf);
                                                                                                                                WlanProbe.this.mEncModeObservable.setChanged();
                                                                                                                                WlanProbe.this.mEncModeObservable.notifyObservers(valueOf);
                                                                                                                                break;
                                                                                                                            case 98:
                                                                                                                                if ((poll.Data[0] & 255) == 0) {
                                                                                                                                    Ultrasys.Instance().SetDicomAuth(false);
                                                                                                                                } else {
                                                                                                                                    Ultrasys.Instance().SetDicomAuth(true);
                                                                                                                                }
                                                                                                                                if (poll.Data.length >= 3) {
//                                                                                                                                    boolean z3 = poll.Data[1] & 255;
//                                                                                                                                    boolean z4 = poll.Data[2] & 255;
//                                                                                                                                    if (z3 == 0) {
//                                                                                                                                        Ultrasys.Instance().SetPwAuth(false);
//                                                                                                                                    } else {
//                                                                                                                                        Ultrasys.Instance().SetPwAuth(true);
//                                                                                                                                    }
//                                                                                                                                    if (z4 == 0) {
//                                                                                                                                        Ultrasys.Instance().SetColorAuth(false);
//                                                                                                                                    } else {
//                                                                                                                                        Ultrasys.Instance().SetColorAuth(true);
//                                                                                                                                    }
//                                                                                                                                    if (poll.Data.length >= 4) {
//                                                                                                                                        int i9 = poll.Data[3] & 255;
//                                                                                                                                        Ultrasys Instance2 = Ultrasys.Instance();
//                                                                                                                                        if (i9 > 0) {
//                                                                                                                                            z = true;
//                                                                                                                                        }
//                                                                                                                                        Instance2.SetHumanVetAuth(z);
//                                                                                                                                        break;
//                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    Ultrasys.Instance().SetPwAuth(true);
                                                                                                                                    Ultrasys.Instance().SetColorAuth(true);
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                                break;
                                                                                                                            case 103:
                                                                                                                                HostWlanProbe.Instance().AddtoHostList(new String(poll.Data));
                                                                                                                                break;
                                                                                                                        }
                                                                                                                }
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                        if (WlanProbe.this.mMsgHook != null) {
                            WlanProbe.this.mMsgHook.OnMessage(poll);
                        }
                    }
                }
            }
        };
        this.mTempObservable = new HObservable();
        this.mProbeIdObservable = new HObservable();
        this.mBattLevelObservable = new HObservable();
        this.mProbeBoardVerObservable = new HObservable();
        this.mProbeFwVerObservable = new HObservable();
        this.mProbePidVerObservable = new HObservable();
        this.mProbeFpgaVerObservable = new HObservable();
        this.mProbeSnObservable = new HObservable();
        this.mProductIdObservable = new HObservable();
        this.mBoardSnObservable = new HObservable();
        this.mWifiFwVerObservable = new HObservable();
        this.mEncModeObservable = new HObservable();
        this.mVetObservable = new HObservable();
        this.mProbeStyleObservable = new HObservable();
    }

    public void Init() {
        if (this.mICmdHandler == null) {
            this.mICmdHandler = new CmdHandler();
            this.mICmdHandler.SetWlanProbeHandler(this.mHandler);
        }
        this.mICmdHandler.init();
        this.mCmdParser = new CmdParser(this.mICmdHandler);
        this.m_CmdSendExec = Executors.newSingleThreadExecutor();
    }

    public void SetMsgHook(MsgHook msgHook) {
        this.mMsgHook = msgHook;
    }

    public static int CalProbeId(int i) {
        for (int i2 = 0; i2 < 34; i2++) {
            if (i >= ProbeIdVolMap[i2][0] && i < ProbeIdVolMap[i2][1]) {
                return ProbeIdVolMap[i2][2];
            }
        }
        return 0;
    }

    public int SendCmd(final int i, final int i2, byte[] bArr, final int i3) {
        if ((Ultrasys.Instance().GetDispMode() == 8 || Ultrasys.Instance().GetDispMode() == 58) && i == 78) {
            return 1;
        }
        if ((Ultrasys.Instance().GetDispMode() == 55 || Ultrasys.Instance().GetDispMode() == 59) && i == 80) {
            return 1;
        }
        if ((Ultrasys.Instance().GetDispMode() == 58 || Ultrasys.Instance().GetDispMode() == 59 || Ultrasys.Instance().GetDispMode() == 57 || Ultrasys.Instance().GetDispMode() == 5) && i == 81) {
            return 1;
        }
        final byte[] bArr2 = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            if (bArr != null) {
                bArr2[i4] = bArr[i4];
            } else {
                bArr2[i4] = 0;
            }
        }
        if (Ultrasys.Instance().GetProbeConType() != 3) {
            this.m_CmdSendExec.execute(new Runnable() { // from class: handprobe.application.wlan.wlanprobe.WlanProbe.2
                @Override // java.lang.Runnable
                public void run() {
                    if (i3 == 1) {
                        WlanProbe.this.mCmdParser.excute(1, i, i2, bArr2, false);
                    } else {
                        WlanProbe.this.mCmdParser.excute(i3, i, i2, bArr2, false);
                    }
                }
            });
        } else if (i3 == 1) {
            this.mCmdParser.excute(1, i, i2, bArr2, false);
        } else {
            this.mCmdParser.excute(0, i, i2, bArr2, false);
        }
        return 1;
    }

    public int SyncSendCmd(int i, int i2, byte[] bArr, int i3) {
        byte[] bArr2 = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            if (bArr != null) {
                bArr2[i4] = bArr[i4];
            } else {
                bArr2[i4] = 0;
            }
        }
        if (i3 == 1) {
            this.mCmdParser.excute(1, i, i2, bArr2, false);
        } else {
            this.mCmdParser.excute(0, i, i2, bArr2, false);
        }
        return 1;
    }

    public void SetMenuMsgHander(Handler handler) {
        this.mMenuMsgHandle = handler;
    }

    public HObservable GetTempObservable() {
        return this.mTempObservable;
    }

    public HObservable GetBattObservable() {
        return this.mBattLevelObservable;
    }

    public HObservable GetProbeBoardVerObservable() {
        return this.mProbeBoardVerObservable;
    }

    public HObservable GetProbeFwVerObservable() {
        return this.mProbeFwVerObservable;
    }

    public HObservable GetProbePidVerObservable() {
        return this.mProbePidVerObservable;
    }

    public HObservable GetProbeFpgaVerObservable() {
        return this.mProbeFpgaVerObservable;
    }

    public HObservable GetProbeSnObservable() {
        return this.mProbeSnObservable;
    }

    public HObservable GetProductObservable() {
        return this.mProductIdObservable;
    }

    public HObservable GetBoardSnObservable() {
        return this.mBoardSnObservable;
    }

    public HObservable GetWifiFwVerObservable() {
        return this.mWifiFwVerObservable;
    }

    public HObservable GetEncModeObservable() {
        return this.mEncModeObservable;
    }

    public HObservable GetProbeIdObservable() {
        return this.mProbeIdObservable;
    }

    public float GetTemperature(int i) {
        float f;
        float f2;
        if (Ultrasys.Instance().GetProbeConType() == 5 || Ultrasys.Instance().GetProbeConType() == 4) {
            f = 100.0f;
            f2 = 2700.0f;
        } else {
            f = 10.0f;
            f2 = 1900.0f;
        }
        float f3 = i;
        float f4 = (f * f3) / (f2 - f3);
        for (int i2 = 0; i2 < 27; i2++) {
            if (f4 > this.TempRes[i2][1] && i2 > 0) {
                this.mTemperature = Float.valueOf(this.TempRes[i2][0] - (((f4 - this.TempRes[i2][1]) * 5.0f) / (this.TempRes[i2 - 1][1] - this.TempRes[i2][1])));
                return this.mTemperature.floatValue();
            }
        }
        return 0.0f;
    }

    int GetBatLevel1(int i) {
        int i2;
        int i3;
        if (Ultrasys.Instance().GetProbeConType() == 4 || Ultrasys.Instance().GetProbeConType() == 5) {
            i2 = 1137;
            i3 = 942;
        } else {
            i2 = 1070;
            i3 = 860;
        }
        if (i > i2) {
            return 100;
        }
        if (i >= i3) {
            return ((i - i3) * 100) / (i2 - i3);
        }
        return 2;
    }

    public CmdParser getCmdParser() {
        return this.mCmdParser;
    }

    public CmdHandler getmICmdHandler() {
        return this.mICmdHandler;
    }

    public void resetWlanProbeId() {
        this.mProbeId = -1;
        this.mDualProbeId0 = -1;
        this.mDualProbeId1 = -1;
        Ultrasys.Instance().mProbeId = -1;
    }

    public void NotifyProbeId(int i) {
        if (this.mProbeIdObservable != null) {
            this.mProbeIdObservable.setChanged();
            this.mProbeIdObservable.notifyObservers(Integer.valueOf(i));
        }
    }

    public void ResetProbeId() {
        this.mProbeId = 0;
    }

    public void SendWlanMessageIntToStr(byte b, int i) {
        char[] cArr = new char[8];
        Tools.HexToStr(cArr, new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24)}, 4);
        byte[] bArr = new byte[8];
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2] = (byte) cArr[7 - i2];
        }
        SendWlanMessage(b, bArr, 8);
    }

    public void SendWlanMessage(byte b, byte[] bArr, int i) {
        CmdInfo cmdInfo = this.mCmdInfo;
        cmdInfo.getClass();
        CmdInfo.CmdElem cmdElem = new CmdInfo.CmdElem(i);
        cmdElem.cmdid = b;
        cmdElem.dir = (byte) 0;
        cmdElem.len = (short) i;
        for (int i2 = 0; i2 < i; i2++) {
            cmdElem.Data[i2] = bArr[i2];
        }
        synchronized (CmdHandler.mNetDataQueue) {
            CmdHandler.mNetDataQueue.add(cmdElem);
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void SetAutoRun(boolean z) {
        this.m_AutoRun = z;
    }

    public String GetProductId() {
        return this.m_ProductIdStr;
    }

    int USendPack(int i, byte[] bArr, int i2, boolean z) throws UnsupportedEncodingException {
        if (z) {
            this.mICmdHandler.ClearRecvPack();
        }
        switch (i) {
            case 132:
            case 133:
            case 134:
                break;
            default:
                switch (i) {
                    case 163:
                    case 164:
                        break;
                    case 165:
                    case 166:
                        byte[] bArr2 = new byte[4];
                        Tools.IntToByte(i2, bArr2);
                        int SendPack = this.mICmdHandler.SendPack(i, bArr2, 4, this.mRecvPackBuf, 2000);
                        if (SendPack != i2) {
                            return 0;
                        }
                        System.arraycopy(this.mRecvPackBuf, 0, bArr, 0, SendPack);
                        return SendPack;
                    case 167:
                        int SendPack2 = this.mICmdHandler.SendPack(i, bArr, i2, this.mRecvPackBuf, 2000);
                        if (SendPack2 >= 4 && SendPack2 < 1440) {
                            return Tools.bytesToInt(this.mRecvPackBuf);
                        }
                        return 0;
                    default:
                        return 0;
                }
        }
        if (i == 134) {
            this.mMcuSize = Tools.bytesToInt(bArr);
        }
        int SendPack3 = this.mICmdHandler.SendPack(i, bArr, i2, this.mRecvPackBuf, i == 132 ? 2000 + (this.mMcuSize / 100) : 2000);
        if (SendPack3 <= 0 || SendPack3 >= 1440) {
            return 0;
        }
        byte[] bArr3 = new byte[SendPack3];
        System.arraycopy(this.mRecvPackBuf, 0, bArr3, 0, SendPack3);
        return Tools.byteToStr(bArr3).equals("recv success") ? 1 : 0;
    }

//    public int UWriteFile(String str, byte[] bArr, HProgressDialog hProgressDialog) {
//        if (bArr == null) {
//            bArr = FileOperate.readFileToByteArray(str);
//        }
//        if (bArr == null) {
//            return 0;
//        }
//        int length = bArr.length;
//        String fileNameWithSuffix = FileOperate.getFileNameWithSuffix(str);
//        if (USendPack(163, fileNameWithSuffix.getBytes(), fileNameWithSuffix.length(), true) == 0) {
//            return 0;
//        }
//        byte[] bArr2 = new byte[4];
//        Tools.IntToByte(length, bArr2);
//        if (USendPack(134, bArr2, 4, false) == 0) {
//            return 0;
//        }
//        byte[] bArr3 = new byte[1440];
//        int i = ((length + 1400) - 1) / 1400;
//        for (int i2 = 0; i2 < i; i2++) {
//            if (hProgressDialog != null) {
//                hProgressDialog.SetProcess((i2 * 100) / i);
//            }
//            if (i2 == i - 1) {
//                int i3 = length % 1400;
//                if (i3 == 0) {
//                    i3 = 1400;
//                }
//                System.arraycopy(bArr, i2 * 1400, bArr3, 0, i3);
//                if (USendPack(133, bArr3, i3, false) == 0) {
//                    return 0;
//                }
//            } else {
//                System.arraycopy(bArr, i2 * 1400, bArr3, 0, 1400);
//                if (USendPack(132, bArr3, 1400, false) == 0) {
//                    return 0;
//                }
//            }
//        }
//        return 1;
//    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0076 -> B:41:0x0089). Please submit an issue!!! */
    public int UReadFile(String str, String str2) throws UnsupportedEncodingException {
        FileChannel fileChannel = null;
        int i = 1;
        if (USendPack(164, str.getBytes(), str.length(), true) == 0) {
            return 0;
        }
        byte[] bArr = new byte[4];
        Tools.IntToByte(0, bArr);
        int USendPack = USendPack(167, bArr, 4, false);
        if (USendPack == 0) {
            return 0;
        }
        FileChannel fileChannel2 = null;
        try {
            try {
                try {
                    fileChannel = new FileOutputStream(str2).getChannel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
//                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            fileChannel = fileChannel2;
        }
        try {
            int i2 = ((USendPack + 1400) - 1) / 1400;
            for (int i3 = 0; i3 < i2; i3++) {
                if (i3 == i2 - 1) {
                    int i4 = USendPack % 1400 != 0 ? USendPack % 1400 : 1400;
                    byte[] bArr2 = new byte[i4];
                    if (USendPack(166, bArr2, i4, false) == 0) {
                        i = 0;
                        break;
                    }
                    fileChannel.write(ByteBuffer.wrap(bArr2));
                    fileChannel.force(true);
                } else {
                    byte[] bArr3 = new byte[1400];
                    if (USendPack(165, bArr3, 1400, false) == 0) {
                        i = 0;
                        break;
                    }
                    fileChannel.write(ByteBuffer.wrap(bArr3));
                    fileChannel.force(true);
                }
            }
            fileChannel.close();
        } catch (IOException e3) {
//            e = e3;
            fileChannel2 = fileChannel;
//            e.printStackTrace();
//            fileChannel2.close();
            return i;
        } catch (Throwable th2) {
//            th = th2;
            try {
                fileChannel.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
//            throw th;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void confirmProbeError() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(MyApplication.App().mMainActivity);
//        builder.setTitle(LanguageUtil._NLS(R.array.prompt));
//        builder.setMessage(LanguageUtil._NLS(R.array.probe_n_support));
//        builder.setPositiveButton(LanguageUtil._NLS(R.array.ok), new DialogInterface.OnClickListener() { // from class: handprobe.application.wlan.wlanprobe.WlanProbe.3
//            @Override // android.content.DialogInterface.OnClickListener
//            public void onClick(DialogInterface dialogInterface, int i) {
//            }
//        });
//        builder.show();
    }

    public void SetProbeStyle(int i) {
        if (this.mProbeStyle != i) {
            this.mProbeStyleObservable.setChanged();
            this.mProbeStyleObservable.notifyObservers(Integer.valueOf(i));
        }
        this.mProbeStyle = i;
    }

    public void SetDualProbeActive(int i) {
        if (i == 0) {
            this.mProbeId = Integer.valueOf(this.mDualProbeId0);
        } else if (i == 1) {
            this.mProbeId = Integer.valueOf(this.mDualProbeId1);
        }
        byte[] bArr = new byte[4];
        bArr[0] = (byte) i;
        Ultrasys.Instance().SendCmdToProbe(169, 1, bArr, 0);
        Ultrasys.Instance().SetDualProbeActive(i);
//        if (AppProc.Instance().mWifiProbeSelect.isAdded() || AppProc.Instance().mUsbProbeConnectDlgFrag.isAdded()) {
//            Ultrasys.Instance().CheckProbeChange(this.mProbeId.intValue(), false);
//        }
    }

    public void UploadDualProbeActive(int i) {
        Ultrasys.Instance().SetDualProbeActive(0);
        Ultrasys.Instance().SetProbePcbType(0);
        if (this.mProbeStyle == 1) {
            int i2 = (this.mProbeId.intValue() != this.mDualProbeId0 && this.mProbeId.intValue() == this.mDualProbeId1) ? 1 : 0;
            byte[] bArr = new byte[4];
            this.mDualActive = i2;
            bArr[0] = -1;
            Ultrasys.Instance().SendCmdToProbe(169, 1, bArr, 0);
            Ultrasys.Instance().SetDualProbeActive(i2);
            Ultrasys.Instance().SetProbePcbType(1);
        } else if (Ultrasys.Instance().GetProbeConType() != 4 || i <= 48 || i >= 58) {
        } else {
            Ultrasys.Instance().SetProbePcbType(1);
        }
    }

    public void EnterNetWorkState() {
        this.mSaveSsid = new String("");
        if (Ultrasys.Instance().GetProbeConType() == 5 || Ultrasys.Instance().GetProbeConType() == 2) {
            @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) MyApplication.GetAppContext().getSystemService(Context.WIFI_SERVICE);
            this.mSaveSsid = wifiManager.getConnectionInfo().getSSID();
            wifiManager.disconnect();
        }
    }

    public void ExitNetWorkState() {
        if (!this.mSaveSsid.equals("")) {
            ProbeWifiInfo.Connect(this.mSaveSsid, "12345678");
        }
    }
}
