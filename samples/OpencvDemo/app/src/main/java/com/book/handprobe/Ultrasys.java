package com.book.handprobe;

import android.os.Build;
import android.util.Log;

import com.book.tools.HObserver;
import com.book.tools.HostWlanProbe;
import com.book.tools.Tools;
import com.book.tools.WlanProbe;

import java.io.File;
import java.util.Arrays;
import java.util.Observable;
import java.util.Vector;
import org.xutils.x;

import gloomyfish.opencvdemo.MyApplication;
import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class Ultrasys {
    public static final int BC_CINE_BUFFER_SIZE = 104857600;
    public static final int B_PAGE_SIZE = 131072;
    public static final int CONNECT_ERROR = -1;
    public static final int C_PAGE_SIZE = 327680;
    public static final int MAX_DISPLAY_INTERVAL = 16;
    public static final int MPW_CINE_BUFFER_SIZE = 2457600;
    public static final int M_PAGE_SIZE = 3072;
    public static final int PW_CINE_BUFFER_SIZE = 11264000;
    public static final int PW_PAGE_SIZE = 5632;
    public static final int STR_FORMAT_ERROR = -3;
    public static final int STR_lEN_ERROR = -2;
    private static final String TAG = "Ultrasys";
    public static final int WRITE_AUTH_SUCCESS = 0;
    int mApower_lv;
//    public AudioPlayer mAudioPlayer;
//    public AutoCalcResultParams mAutoCalcResultParams;
//    public CinePartitionBuffer mBCCineBuffer;
//    public ICineIterator mBCCineIterator;
//    public CinePlayer mBCCinePlayer;
//    public SimDataBuffer mBCOneSimDataBuffer;
//    public SimDataBuffer mBCSimDataBuffer;
//    public BDspPara mBDspPara;
    int mBDynmic_lv;
    int mBEffect_lv;
    int mBEnhance_lv;
    int mBFocus_lv;
    int mBFrameCompSwitch;
    int mBFrameCorr_lv;
    public int mBFramerate;
    int mBFreq_lv;
    public int mBGain_lv;
//    public ImagePlayer.BGrayMapObserver mBGrayMapObserver;
//    public SimDataBuffer mBMOneSimDataBuffer;
//    public SimDataBuffer mBMSimDataBuffer;
//    public SimDataBuffer mBOneSimDataBuffer;
//    public BScanPara mBScanPara;
//    public SimDataBuffer mBSimDataBuffer;
    int mBSteer;
    public int mBThiSwitch;
    int mCColorMap_lv;
//    public CDspPara mCDspPara;
    int mCFreq_lv;
    int mCGain_lv;
    int mCPackSize_lv;
    int mCPersistence_lv;
    int mCPriority_lv;
    int mCScale_lv;
//    public CScanPara mCScanPara;
    public int mCSteer;
    int mCSteer_lv;
    int mCWallFilter_lv;
    private CallBack mCallBack;
    public boolean mChangeProbe;
    public int mCheckCount;
//    public ImageDisplayFragment.BGrayMapObserver mCineBGrayMapObserver;
//    public ImageDisplayFragment.PowerColorMapObserver mCinePowerColorMapObserver;
//    public ImageDisplayFragment.VelColorMapObserver mCineVelColorMapObserver;
    public boolean mColorAuth;
    public int mCurChannel;
    public int mCurDispMode;
    public int mCurExamMode;
    public int mCurPatternId;
//    public PresetData mCurPresetData;
    public int mCurSaveImgMode;
    int mDepth_lv;
    public boolean mDicomAuth;
//    public DicomPresetPara mDicomPresetPara;
//    public DisableProbeBtnPreset mDisableProbeBtnPreset;
//    public DispMode mDispMode;
//    public DispSurface mDispSurface;
//    public DscCtrl mDscCtrl;
//    public DscPara mDscPara;
    private int mExpandLevel;
    int mExpandSwitch;
//    public FeUltrasysCtrl mFeUltrasysCtrl;
    public HObserver mFlipObserver;
    public boolean mFreezeStatus;
//    public FuncidMap mFuncIdMap;
    int mHorFlip;
    public boolean mHumanVetAuth;
//    public ImagePlayer mImagePlayer;
//    private InspectionObjectType mInspectionObjectType;
    private boolean mIsCmdExecuting;
    private boolean mIsFirstEnter;
    protected boolean mIsGetPresetParamCmd;
    public boolean mIsPresetDlgOpen;
    public boolean mIsSaveFrameDataToDir;
    public boolean mIsSetFe;
    public boolean mIsUnFreeState;
    public boolean mIsUnFreezing;
    public int mIsVet;
//    public MBCinePlayer mMBCinePlayer;
//    public MCineRollBuffer mMCineRollBuffer;
    public final int[] mMDSpeed;
    int mMDispLine;
//    public MDspPara mMDspPara;
    int mMDynamic;
    int mMEffect_lv;
    int mMGain_lv;
//    public MITIData mMITIData;
//    public MITIData.MITIDataObservable mMITIDataObservable;
//    public PresetParams.MITIObservable mMITIObservable;
//    public CinePartitionBuffer mMPWCineBuffer;
//    public ICineIterator mMPWCineIterator;
//    public CinePlayer mMPWCinePlayer;
//    public MScanPara mMScanPara;
    public int mMSpeedLevel;
    int mMSpeed_lv;
//    public MeasAutoCalc mMeasAutoCalc;
//    private MeasurePreset mMeasurePreset;
    public boolean mNoExpand;
    int mPWBaseline_lv;
//    public CinePartitionBuffer mPWCineBuffer;
//    public ICineIterator mPWCineIterator;
//    public CinePlayer mPWCinePlayer;
    int mPWDoplerFrq_lv;
//    public PWDspPara mPWDspPara;
    int mPWDynamic_lv;
    int mPWFilter_lv;
    int mPWGain_lv;
//    public SimDataBuffer mPWOneSimDataBuffer;
    int mPWReverse_lv;
    int mPWSVDepth_lv;
    int mPWSVSize_lv;
    int mPWScale_lv;
//    public PWScanPara mPWScanPara;
//    public SimDataBuffer mPWSimDataBuffer;
    int mPWSpeed_lv;
    int mPWSteer_lv;
    int mPWVolume_lv;
//    public PidInterface mPidInerface;
//    public ImagePlayer.PowerColorMapObserver mPowerColorMapObserver;
    int mPowerColorMap_lv;
    int mPowerFreq_lv;
    int mPowerGain_lv;
    int mPowerPackSize_lv;
    int mPowerPersistence_lv;
    int mPowerPriority_lv;
    int mPowerScale_lv;
    int mPowerSteer_lv;
    int mPowerWallFilter_lv;
//    private PresetFile mPresetFile;
//    public PresetServer mPresetServer;
//    public PresetParams mPresetparams;
    public boolean mProbeDisabled;
    public int mProbeId;
    public int mProbeTest;
    int mPwAngle_lv;
    public boolean mPwAuth;
    public int mPwSpeedLevel;
    public boolean mReUpdateFpgaPara;
    public File mSaveFrameDataDirFile;
    public long mSaveFrameDataTime;
    int mThiFrameCorr_lv;
    public int mThiGain_lv;
    int mTsi_lv;
    public boolean mUseProbeStatus;
//    public ImagePlayer.VelColorMapObserver mVelColorMapObserver;
    int mVerFlip;

    /* loaded from: classes2.dex */
    public interface CallBack {
        void OnFreeze();

        void OnModeChange(int i);

        void OnProbeChange();

        void OnSetPresetParam();

        void OnSetVet();

        void OnUnFreeze();

        void OnUsbProbeStateChange(boolean z);

        void OnUseProbe(int i, int i2);

        void ReSendSampleWinCmd();

        void SetCDispDotRange(int i, int i2, int i3);

        void SetCDispLineRange(int i, int i2, int i3);

        void SetCSampWinSteer(float f);

        void SetMDispLine(int i, int i2);

        void SetPWAngle(int i);

        void SetPWDispLine(int i, int i2);

        void SetPWFreq(int i);

        void SetPWGatePos(float f, int i);

        void SetPWGateSize(float f);

        void SetPWSampleGateSteer(float f);

        void UpdateSampleWin();

        void onFlip(Observable observable, Object obj);
    }

    public void SetDualActive(int i) {
    }

    public static final Ultrasys Instance() {
        return handler.instance;
    }

    public void setBLR(int i) {
//        this.mBDspPara.mBFlipHor.set(i);
    }

    public void setBUD(int i) {
//        this.mBDspPara.mBFlipVer.set(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class handler {
        private static final Ultrasys instance = new Ultrasys();

        private handler() {
        }
    }

    private Ultrasys() {
        this.mIsPresetDlgOpen = false;
        this.mMDSpeed = new int[]{4, 8, 16};
        this.mMSpeedLevel = 1;
        this.mPwSpeedLevel = 1;
        this.mCurExamMode = -1;
        this.mCurPatternId = 0;
        this.mProbeId = 0;
        this.mChangeProbe = false;
        this.mCurSaveImgMode = -1;
        this.mCSteer = 0;
//        this.mMITIData = null;
//        this.mCurPresetData = new PresetData();
        this.mIsGetPresetParamCmd = false;
        this.mCurDispMode = 0;
        this.mIsFirstEnter = true;
//        this.mBScanPara = null;
//        this.mBDspPara = null;
//        this.mMScanPara = null;
//        this.mMDspPara = null;
//        this.mCScanPara = null;
//        this.mCDspPara = null;
//        this.mPWScanPara = null;
//        this.mPWDspPara = null;
//        this.mDscPara = null;
//        this.mFuncIdMap = null;
//        this.mMeasAutoCalc = null;
//        this.mFeUltrasysCtrl = null;
//        this.mDispMode = null;
        this.mFreezeStatus = false;
        this.mUseProbeStatus = false;
        this.mIsCmdExecuting = false;
        this.mIsVet = 0;
        this.mIsUnFreeState = false;
//        this.mInspectionObjectType = new InspectionObjectType();
        this.mIsSetFe = true;
        this.mIsUnFreezing = false;
        this.mCheckCount = 0;
        this.mReUpdateFpgaPara = true;
        this.mIsSaveFrameDataToDir = false;
        this.mDicomAuth = false;
        this.mColorAuth = true;
        this.mPwAuth = true;
        this.mNoExpand = false;
        this.mHumanVetAuth = false;
        this.mProbeDisabled = false;
        this.mProbeTest = 0;
        PIDInit();
//        MainHandler.getInstance().InitCtoJavaMessageHandler();
//        HSysctrlIf.DopplerAppInit();
//        HDicomIf.Init();
//        HSysctrlIf.UltrasysInit();
//        this.mDscCtrl = new DscCtrl();
//        this.mAudioPlayer = new AudioPlayer();
//        this.mMITIData = new MITIData();
//        this.mPresetparams = new PresetParams();
//        PresetParams presetParams = this.mPresetparams;
//        presetParams.getClass();
//        this.mMITIObservable = new PresetParams.MITIObservable();
//        MITIData mITIData = this.mMITIData;
//        mITIData.getClass();
//        this.mMITIDataObservable = new MITIData.MITIDataObservable();
//        this.mAutoCalcResultParams = new AutoCalcResultParams();
//        this.mDispMode = new DispMode();
//        this.mDscPara = new DscPara();
//        this.mBScanPara = new BScanPara();
//        this.mBDspPara = new BDspPara();
//        this.mMScanPara = new MScanPara();
//        this.mMDspPara = new MDspPara();
//        this.mCScanPara = new CScanPara();
//        this.mCDspPara = new CDspPara();
//        this.mPWScanPara = new PWScanPara();
//        this.mPWDspPara = new PWDspPara();
//        this.mBGrayMapObserver = new ImagePlayer.BGrayMapObserver();
//        this.mVelColorMapObserver = new ImagePlayer.VelColorMapObserver();
//        this.mPowerColorMapObserver = new ImagePlayer.PowerColorMapObserver();
//        this.mCineBGrayMapObserver = new ImageDisplayFragment.BGrayMapObserver();
//        this.mCineVelColorMapObserver = new ImageDisplayFragment.VelColorMapObserver();
//        this.mCinePowerColorMapObserver = new ImageDisplayFragment.PowerColorMapObserver();
//        this.mBSimDataBuffer = new SimDataBuffer(400);
//        this.mBCSimDataBuffer = new SimDataBuffer(200);
//        this.mPWSimDataBuffer = new SimDataBuffer(2000);
//        this.mBMSimDataBuffer = new SimDataBuffer(1000);
//        this.mBOneSimDataBuffer = new SimDataBuffer(2);
//        this.mBCOneSimDataBuffer = new SimDataBuffer(2);
//        this.mPWOneSimDataBuffer = new SimDataBuffer(2);
//        this.mBMOneSimDataBuffer = new SimDataBuffer(2);
//        this.mBCCineBuffer = new CinePartitionBuffer(BC_CINE_BUFFER_SIZE, 131072);
//        this.mBCCinePlayer = new CinePlayer();
//        this.mMPWCineBuffer = new CinePartitionBuffer(MPW_CINE_BUFFER_SIZE, M_PAGE_SIZE);
//        this.mMPWCinePlayer = new CinePlayer();
//        this.mPWCineBuffer = new CinePartitionBuffer(PW_CINE_BUFFER_SIZE, 5632);
//        this.mPWCinePlayer = new CinePlayer();
//        this.mMBCinePlayer = new MBCinePlayer();
//        this.mMCineRollBuffer = new MCineRollBuffer();
//        this.mFuncIdMap = new FuncidMap();
//        this.mPresetServer = new PresetServer();
//        this.mPresetServer.setSupportProbeIds(this.mPidInerface.getSupportProbeIds());
    }

    public void Init() {
//        this.mMeasAutoCalc = new MeasAutoCalc();
//        this.mDscCtrl.SwitchPwTraceSmooth(1);
//        this.mCScanPara.mScale.initTableStr();
//        this.mPWScanPara.mScale.initTableStr();
        ParamInit();
//        this.mPresetServer.initFromFile(true);
    }

    public void ReLoadPreset() {
//        PresetFile.reloadPreset();
    }

    public void Sleep(int i) {
        try {
            Thread.sleep(i, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public void SetPresetFile(PresetFile presetFile) {
//        this.mPresetFile = presetFile;
//        this.mPresetparams.setPresetParam(130, Integer.valueOf(this.mPresetFile.getLanguage()));
//    }

    public String getPresetFilePath() {
        return ""; //PresetFile.PRESET_FILE;
    }

    protected void ParamInit() {
        String[] strArr = {LanguageUtil._NLS(R.array.Tsi_Gen), LanguageUtil._NLS(R.array.Tsi_Muscle), LanguageUtil._NLS(R.array.Tsi_Fat), LanguageUtil._NLS(R.array.Tsi_Fluid)};
        String[] strArr2 = {LanguageUtil._NLS(R.array.Turn_Off), LanguageUtil._NLS(R.array.Turn_On)};
//        this.mBScanPara.mBExpand.setBExpandTable(strArr2);
//        this.mBScanPara.mTsi.setTsiTable(strArr);
//        this.mBDspPara.mFrameComp.setFrameCompTable(strArr2);
//        this.mPWDspPara.mPwReverse.setStrTable(strArr2);
//        this.mPWDspPara.mPwAutoCalc.setStrTable(strArr2);
        AddBParaView();
        AddCParaView();
        AddPowerParaView();
//        HFeParamIf.SetPWFilterWindowWidth(0);
//        HFeParamIf.SetPWReadDotCount(256);
//        HBeParamIf.SetPWColorMap(0, this.mBDspPara.mGray.GetGrayMap(), 256);
//        HBeParamIf.SetPWDisplayRect(0, 0, ImageDefine.IMAGE_WIDTH, 240);
//        this.mBCCineIterator = this.mBCCineBuffer.createIterator();
//        this.mBCCineBuffer.setPageSize(131072);
//        this.mBCCinePlayer.setIsRepeat(true);
//        this.mBCCinePlayer.setCineSrcIterator(this.mBCCineIterator);
//        this.mBCCinePlayer.initCineState();
//        this.mBCCinePlayer.setPageRate(15);
//        this.mMPWCineIterator = this.mMPWCineBuffer.createIterator();
//        this.mMPWCineBuffer.setPageSize(M_PAGE_SIZE);
//        this.mMPWCinePlayer.setIsRepeat(true);
//        this.mMPWCinePlayer.setCineSrcIterator(this.mMPWCineIterator);
//        this.mMPWCinePlayer.initCineState();
//        this.mMPWCinePlayer.setPageRate(60);
//        this.mPWCineIterator = this.mPWCineBuffer.createIterator();
//        this.mPWCineBuffer.setPageSize(5632);
//        this.mPWCinePlayer.setIsRepeat(true);
//        this.mPWCinePlayer.setCineSrcIterator(this.mPWCineIterator);
//        this.mPWCinePlayer.initCineState();
//        this.mPWCinePlayer.setPageRate(60);
//        this.mMBCinePlayer.setIsRepeat(true);
//        this.mMBCinePlayer.setCineSrcIterator(this.mBCCineIterator);
//        this.mMBCinePlayer.setBCineBuffer(this.mBCCineBuffer);
//        this.mMBCinePlayer.setMCineBuffer(this.mMPWCineBuffer);
//        this.mMCineRollBuffer.setICineBuffer(this.mMPWCineBuffer);
//        this.mMBCinePlayer.initCineState();
//        this.mMBCinePlayer.setPageRate(60);
//        this.mMPWCinePlayer.addObserver(this.mMBCinePlayer);
    }

    public void AddBParaView() {
//        this.mBScanPara.mDepth.addObserver(this.mDscPara);
//        this.mBDspPara.mGray.addObserver(this.mBGrayMapObserver);
//        this.mBDspPara.mGray.addObserver(this.mCineBGrayMapObserver);
//        this.mFlipObserver = new HObserver() { // from class: handprobe.application.ultrasys.Ultrasys.1
//            @Override // java.util.Observer
//            public void update(Observable observable, Object obj) {
//                if (Ultrasys.this.mCallBack != null) {
//                    Ultrasys.this.mCallBack.onFlip(observable, obj);
//                }
//            }
//        };
//        this.mBDspPara.mBFlipHor.addObserver(this.mFlipObserver);
//        this.mBDspPara.mBFlipVer.addObserver(this.mFlipObserver);
    }

    public void AddCParaView() {
//        this.mCDspPara.mVelColorMap.addObserver(this.mVelColorMapObserver);
    }

    public void AddPowerParaView() {
//        this.mCDspPara.mPowerColorMap.addObserver(this.mPowerColorMapObserver);
    }

//    public void ImageInit(GL10ImageView gL10ImageView, GL10ImageView gL10ImageView2, GL10ImageView gL10ImageView3) {
//        if (this.mDispSurface != null) {
//            return;
//        }
//        this.mDispSurface = new DispSurface(gL10ImageView, gL10ImageView2, gL10ImageView3);
//        this.mImagePlayer = new ImagePlayer(this.mDispSurface, "图像接收中");
//        this.mImagePlayer.setCineBuffer(Instance().mBCCineBuffer);
//        this.mImagePlayer.setMCineBuffer(Instance().mMPWCineBuffer);
//        this.mImagePlayer.setPWCineBuffer(Instance().mPWCineBuffer);
//        this.mImagePlayer.init();
//        this.mImagePlayer.play();
//    }

    public void PIDInit() {
//        HPidIf.InitPidSys();
//        this.mPidInerface = new PidInterface();
//        this.mPidInerface.SupportProbeInit1();
//        this.mPidInerface.SupportProbeInit2();
//        this.mPidInerface.SupportProbeInit3();
//        this.mFeUltrasysCtrl = new FeUltrasysCtrl(this);
//        this.mFeUltrasysCtrl.SetCurPID(this.mPidInerface.mCurProbePID);
    }

    public void setCallback(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public boolean ExitPowerSave() {
        if (this.mUseProbeStatus) {
            return true;
        }
        byte[] bArr = {0};
//        for (int i = 0; i <= 4; i++) {
//            if (GetProbeConType() == 4) {
//                int GetProbeReg = UsbProbeManager.Instance().GetProbeReg(0);
//                this.mReUpdateFpgaPara = true;
//                if (GetProbeReg != 61166) {
//                    byte[] bArr2 = new byte[8];
//                    bArr2[0] = 2;
//                    bArr2[1] = 0;
//                    UsbProbeManager.Instance().mUsbInterface.Write(bArr2, 1);
//                    Sleep(50);
//                    if ((UsbProbeManager.Instance().GetProbeReg(0) & 255) == 22) {
//                        HSysctrlIf.SetProbeChNum(16);
//                    } else {
//                        HSysctrlIf.SetProbeChNum(32);
//                    }
//                    return true;
//                } else if (GetProbeReg != 61166) {
//                    continue;
//                } else if (i == 4) {
//                    return false;
//                } else {
//                    Sleep(ImageDefine.T_UPLOAD_LINE_COUNT);
//                }
//            } else if (GetProbeConType() != 5) {
//                return true;
//            } else {
//                byte[] bArr3 = new byte[16];
//                int ReadFPGAParam = HSysctrlIf.ReadFPGAParam(14);
//                if (ReadFPGAParam != 0 && ReadFPGAParam != -286331154 && ReadFPGAParam != -1) {
//                    this.mReUpdateFpgaPara = false;
//                    return true;
//                }
//                this.mReUpdateFpgaPara = true;
//                if (ReadFPGAParam != -286331154) {
//                    WlanProbe.Instance().SendCmd(2, 1, bArr, 0);
//                    Sleep(50);
//                    if ((HSysctrlIf.ReadFPGAParam(0) & 255) == 22) {
//                        HSysctrlIf.SetProbeChNum(16);
//                    } else {
//                        HSysctrlIf.SetProbeChNum(32);
//                    }
//                    return true;
//                } else if (ReadFPGAParam != -286331154) {
//                    continue;
//                } else if (i == 4) {
//                    return false;
//                } else {
//                    Sleep(ImageDefine.T_UPLOAD_LINE_COUNT);
//                }
//            }
//        }
        return false;
    }

    public void Freeze() {
        this.mIsUnFreeState = false;
        if (this.mFreezeStatus) {
            return;
        }
        this.mFreezeStatus = true;
//        this.mImagePlayer.pause();
        int GetProbeConType = GetProbeConType();
        if (GetProbeConType != 0) {
            switch (GetProbeConType) {
                case 4:
                    Instance().SendCmdToProbe(130, 1, new byte[]{1}, 0);
//                    HSysctrlIf.DoStopScan();
                    break;
                case 5:
                    Instance().SendCmdToProbe(130, 1, new byte[]{1}, 0);
//                    HSysctrlIf.DoStopScan();
                    break;
            }
        }
//        int i = this.mDispMode.mMode;
//        if (i == 5) {
//            this.mPWCinePlayer.setPageRate(this.mBFramerate);
//            this.mPWCinePlayer.show(this.mPWCinePlayer.getCineSrcIterator().getPageTotal());
//        } else if (i == 9) {
//            this.mBCCinePlayer.setPageRate(this.mBFramerate);
//            this.mMBCinePlayer.rebuildRefMap();
//            this.mMCineRollBuffer.create();
//            this.mMCineRollBuffer.setCurrentLast();
//            this.mMPWCinePlayer.setPageRate(this.mBFramerate);
//            this.mMPWCinePlayer.show(this.mMPWCinePlayer.getCineSrcIterator().getPageTotal());
//        } else {
//            this.mBCCinePlayer.setPageRate(this.mBFramerate);
//            this.mBCCinePlayer.show(this.mBCCinePlayer.getCineSrcIterator().getPageTotal());
//        }
//        if (this.mCallBack == null) {
//            return;
//        }
//        MainHandler.getInstance().post(new Runnable() { // from class: handprobe.application.ultrasys.Ultrasys.2
//            @Override // java.lang.Runnable
//            public void run() {
//                Ultrasys.this.mCallBack.OnFreeze();
//            }
//        });
    }

    public void UnFreeze(boolean z) {
        UpdateVet();
        if (!this.mProbeDisabled && !this.mIsUnFreezing) {
            if (GetProbeConType() == 5) {
                if (WlanProbe.Instance().mProbeId.intValue() == 0 && this.mCheckCount < 4) {
                    this.mCheckCount++;
                    if (this.mCheckCount == 4) {
                        Tools.ShowText((int) R.array.probeid_obt_fail);
                    } else if (this.mCheckCount == 1) {
                        Tools.ShowText((int) R.array.Processing);
                    }
                    Instance().SendGetCmdToProbe(1, 1, new byte[]{0});
//                    MainHandler.getInstance().postDelayed(new Runnable() { // from class: handprobe.application.ultrasys.Ultrasys.3
//                        @Override // java.lang.Runnable
//                        public void run() {
//                            Ultrasys.this.UnFreeze(false);
//                        }
//                    }, 1000L);
                    return;
                }
                this.mCheckCount = 0;
            }
            if (WlanProbe.Instance().mProbeId.intValue() == 0) {
                return;
            }
            this.mIsUnFreezing = true;
            if (!this.mIsSetFe) {
//                ParaCtrl.Instance().pararestore();
                ResumSetFe();
            }
            CheckProbeChange(WlanProbe.Instance().mProbeId.intValue(), true);
            ExitPowerSave();
            int GetProbeConType = GetProbeConType();
            if (GetProbeConType != 0) {
                switch (GetProbeConType) {
                    case 4:
//                        int GetCurProbeId = UsbProbeManager.Instance().mUsbInterface.GetCurProbeId();
//                        if (GetCurProbeId > 0 && this.mProbeId != GetCurProbeId) {
//                            SelectProber(GetCurProbeId, GetPattern(GetCurProbeId, this.mPresetServer.mExamModePresetServer.GetProbeDefaultExamMode(this.mProbeId)));
//                        }
                        break;
                    case 5:
//                        if (this.mChangeProbe) {
//                            Instance().UseProbe(this.mProbeId, this.mPresetServer.mExamModePresetServer.GetProbeDefaultExamMode(this.mProbeId));
//                            HSysctrlIf.SetFreezeSwitch(false);
//                            break;
//                        } else if (this.mReUpdateFpgaPara) {
//                            HSysctrlIf.SetStopCount(1);
//                            HSysctrlIf.SetFreezeSwitch(false);
//                            break;
//                        } else {
//                            Instance().SendCmdToProbe(130, 1, new byte[]{0}, 0);
//                            HSysctrlIf.SetStopCount(0);
//                            HSysctrlIf.DoStartScan();
//                            break;
//                        }
                }
            }
            this.mIsUnFreeState = true;
//            this.mMeasAutoCalc.Reset();
//            MeasMenuHelper.Instance().exitMeasMenu();
            clearSimDataBuf();
            CineReset();
            this.mFreezeStatus = false;
            PwReset();
//            this.mDscCtrl.MPreReset(0);
            if (z) {
                SendCmdToProbe(83, 1, null, 0);
            }
            UpdateDicomAuth();
            if (this.mCallBack != null) {
                this.mCallBack.OnUnFreeze();
            }
//            this.mImagePlayer.play();
            this.mIsUnFreezing = false;
        }
    }

    public void CineReset() {
//        this.mBCCinePlayer.stop();
//        this.mBCCineBuffer.reset();
//        this.mMPWCinePlayer.stop();
//        this.mMPWCineBuffer.reset();
//        this.mPWCinePlayer.stop();
//        this.mPWCineBuffer.reset();
    }

    public void clearSimDataBuf() {
//        this.mImagePlayer.ClearBuffer();
    }

    public void Reset() {
        SetProbeConType(0);
        if (this.mFreezeStatus) {
            return;
        }
        Freeze();
    }

    public int GetProbeId() {
        return this.mProbeId;
    }

    public int getPatternId() {
        return this.mCurPatternId;
    }

    public String getCurProbeName() {
        return ""; //this.mPidInerface.getProbeNameJava(this.mProbeId);
    }

    public boolean SelectProber(int i, int i2) {
        if (this.mProbeId == i && i2 == this.mCurPatternId) {
            return true;
        }
        boolean SelectProbe = false; //this.mPidInerface.SelectProbe(i, i2);
//        if (SelectProbe) {
//            this.mProbeId = i;
//            this.mChangeProbe = true;
//            this.mPresetparams.setPresetParam(1, Integer.valueOf(this.mProbeId));
//            if (GetProbeConType() == 4 || GetProbeConType() == 5) {
//                this.mPresetparams.setSelectProbeId(i, -1);
//                if (this.mIsSetFe) {
//                    HSysctrlIf.SetProbePod(i, this.mCurExamMode, i2, 0);
//                    this.mPidInerface.mCurProbePID.mLineDensity = HSysctrlIf.GetBDispDensity();
//                }
//            } else if (this.mIsSetFe) {
//                HSysctrlIf.SetProbePod(i, this.mCurExamMode, i2, 0);
//            }
//            this.mCurPatternId = i2;
//            this.mFeUltrasysCtrl.SetCurPID(this.mPidInerface.mCurProbePID);
//            this.mBScanPara.mDepth.SetInterVal(this.mPidInerface.mCurProbePID.mDepthInterval);
//            this.mBScanPara.mFreq.SetFreqTable(this.mPidInerface.mCurProbePID.mBFreq);
//            this.mCScanPara.mFreq.SetFreqTable(this.mPidInerface.mCurProbePID.mCFreq);
//            this.mPWScanPara.mFreq.SetFreqTable(this.mPidInerface.mCurProbePID.mPWFreq);
//            if (this.mPidInerface.mCurProbePID.mProbeType == 1 || this.mPidInerface.mCurProbePID.mProbeType == 2) {
//                this.mDscCtrl.SetBImgShape(0);
//                this.mDscCtrl.SetCImgShape(0);
//            } else if (this.mPidInerface.mCurProbePID.mProbeType == 0) {
//                this.mDscCtrl.SetBImgShape(1);
//                this.mDscCtrl.SetCImgShape(1);
//            }
//            this.mDscCtrl.DscTableLock();
//            this.mFeUltrasysCtrl.UpdateAll();
//            this.mDscCtrl.DscTableUnLock();
//            this.mDscCtrl.GetDscCoefTable();
//            setIsGetPresetParamCmd();
//            if (this.mCallBack != null) {
//                this.mCallBack.OnProbeChange();
//            }
//        }
        return SelectProbe;
    }

    public boolean SetProbeId(int i, int i2) {
        if (this.mProbeId == i && i2 == this.mCurPatternId) {
            return true;
        }
        boolean SelectProbe = false; //this.mPidInerface.SelectProbe(i, i2);
//        if (SelectProbe) {
//            this.mProbeId = i;
//            this.mPresetparams.setPresetParam(1, Integer.valueOf(i));
//            if (GetProbeConType() == 4 || GetProbeConType() == 5) {
//                this.mPresetparams.setSelectProbeId(i, -1);
//                if (this.mIsSetFe) {
//                    HSysctrlIf.SetProbePod(i, this.mCurExamMode, i2, 0);
//                    this.mPidInerface.mCurProbePID.mLineDensity = HSysctrlIf.GetBDispDensity();
//                }
//            } else if (this.mIsSetFe) {
//                HSysctrlIf.SetProbePod(i, this.mCurExamMode, i2, 0);
//            }
//            this.mCurPatternId = i2;
//            this.mFeUltrasysCtrl.SetCurPID(this.mPidInerface.mCurProbePID);
//            this.mBScanPara.mDepth.SetInterVal(this.mPidInerface.mCurProbePID.mDepthInterval);
//            this.mBScanPara.mFreq.SetFreqTable(this.mPidInerface.mCurProbePID.mBFreq);
//            this.mCScanPara.mFreq.SetFreqTable(this.mPidInerface.mCurProbePID.mCFreq);
//            this.mPWScanPara.mFreq.SetFreqTable(this.mPidInerface.mCurProbePID.mPWFreq);
//            if (this.mPidInerface.mCurProbePID.mProbeType == 1 || this.mPidInerface.mCurProbePID.mProbeType == 2) {
//                this.mDscCtrl.SetBImgShape(0);
//                this.mDscCtrl.SetCImgShape(0);
//            } else if (this.mPidInerface.mCurProbePID.mProbeType == 0) {
//                this.mDscCtrl.SetBImgShape(1);
//                this.mDscCtrl.SetCImgShape(1);
//            }
//            this.mDscCtrl.DscTableLock();
//            this.mFeUltrasysCtrl.UpdateAll();
//            this.mDscCtrl.DscTableUnLock();
//            this.mDscCtrl.GetDscCoefTable();
//            setIsGetPresetParamCmd();
//            if (this.mCallBack != null) {
//                this.mCallBack.OnProbeChange();
//            }
//        }
        return SelectProbe;
    }

    public void UpdateDispMode(int i) {
        if (this.mCallBack != null) {
            this.mCallBack.OnModeChange(i);
        }
    }

    public void SetDispMode(int i) {
//        if (this.mDispMode.SetMode(i)) {
//            this.mCurDispMode = i;
//            if (this.mCallBack != null) {
//                this.mCallBack.OnModeChange(i);
//            }
//            this.mDispSurface.SetImgMode(i);
//        }
//        if (!this.mIsSetFe) {
//            return;
//        }
//        int GetMode = this.mDispMode.GetMode();
//        if (GetMode != 0) {
//            if (GetMode != 5) {
//                if (GetMode != 16) {
//                    if (GetMode != 50) {
//                        if (GetMode != 55) {
//                            switch (GetMode) {
//                                case 8:
//                                    break;
//                                case 9:
//                                    CineReset();
//                                    this.mDscCtrl.MPreReset(0);
//                                    HSysctrlIf.SetScanMode(9);
//                                    return;
//                                default:
//                                    switch (GetMode) {
//                                        case 57:
//                                            clearSimDataBuf();
//                                            PwReset();
//                                            HSysctrlIf.SetScanMode(0);
//                                            return;
//                                        case 58:
//                                            PwReset();
//                                            HSysctrlIf.SetScanMode(8);
//                                            return;
//                                        case 59:
//                                            PwReset();
//                                            HSysctrlIf.SetScanMode(55);
//                                            return;
//                                        default:
//                                            HSysctrlIf.SetScanMode(i);
//                                            return;
//                                    }
//                            }
//                        } else {
//                            HSysctrlIf.SetScanMode(8);
//                            return;
//                        }
//                    }
//                }
//                HSysctrlIf.SetScanMode(8);
//                return;
//            }
//            PwReset();
//            HSysctrlIf.SetScanMode(5);
//            return;
//        }
        clearSimDataBuf();
//        HSysctrlIf.SetScanMode(0);
    }

    public void SetSaveImgDispMode(int i) {
//        this.mDispMode.mMode = i;
//        this.mDispSurface.SetImgMode(i);
//        if (this.mCallBack != null) {
//            this.mCallBack.OnModeChange(i);
//        }
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetScanMode(i);
//        }
    }

    public int GetSaveImgDispMode() {
        return this.mCurSaveImgMode;
    }

    public int GetDispMode() {
        return 0; //this.mDispMode.GetMode();
    }

    public int GetLastDispMode() {
        return 0; //this.mDispMode.GetLastMode();
    }

    public void SetBGain(int i) {
        if (this.mBThiSwitch > 0) {
            this.mThiGain_lv = i;
        } else {
            this.mBGain_lv = i;
        }
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetBGainLevel(i);
//        }
//        Instance().mBDspPara.mGain.set(i);
    }

    public void SetBFreq(int i) {
        this.mBFreq_lv = i;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetBFreq(i);
//        }
//        Instance().mBScanPara.mFreq.set(i);
    }

    public void SetDepth(int i) {
        if (i < 0) {
            return;
        }
        int i2 = this.mDepth_lv;
        this.mDepth_lv = i;
        UpdateDepth(i);
//        this.mBScanPara.mDepth.set(i - 1);
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetDepthLevel(i);
//        }
        if ((GetProbeConType() == 4 || GetProbeConType() == 5) && this.mCallBack != null) {
            this.mCallBack.ReSendSampleWinCmd();
        }
        CineReset();
    }

    public void UpdateDepth(int i) {
//        this.mFeUltrasysCtrl.UpdateDepth(i);
    }

    public void SetTsi(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetTSILevel(i);
//        }
//        this.mBScanPara.mTsi.set(i);
//        this.mFeUltrasysCtrl.UpdateTSI(i);
    }

    public void SetBFocus(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetBFocus(i);
//        }
//        this.mBScanPara.mFocus.set(i);
//        this.mFeUltrasysCtrl.SetBFocusPos(i);
    }

    public void UpdateBFocus(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetBFocus(i);
//        }
//        this.mBScanPara.mFocus.set(i);
//        this.mFeUltrasysCtrl.UpdateBFocusPos(i);
    }

    public int GetBFocus(int i) {
//        int GetCurDepthMaxFocus = this.mFeUltrasysCtrl.GetCurDepthMaxFocus();
        return i; // i > GetCurDepthMaxFocus ? GetCurDepthMaxFocus : i;
    }

    public int UpdateBFocus() {
//        int GetCurDepthMaxFocus = (int) ((this.mFeUltrasysCtrl.GetCurDepthMaxFocus() * this.mFeUltrasysCtrl.GetBFocusScale()) + 0.5f);
//        UpdateBFocus(GetCurDepthMaxFocus);
        return 0; //GetCurDepthMaxFocus;
    }

    public void SetBSteer(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetBSteer(i);
//        }
//        this.mBScanPara.mSteer.set(i);
    }

    public void UpdateBFramerate() {
//        this.mBScanPara.mFrameRate.set(this.mBFramerate);
    }

    public void SetBFramerate(int i) {
        this.mBFramerate = i;
        UpdateBFramerate();
    }

    public void SetAPower(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetAPowerLevel(i);
//        }
//        this.mBScanPara.mAPower.set(i);
    }

    public void SetBExpand(int i) {
        if (GetNoExpand()) {
            return;
        }
        this.mExpandLevel = i;
        this.mExpandSwitch = i;
//        if (i != 0) {
//            if (this.mIsSetFe) {
//                HSysctrlIf.SetBExpand(true);
//            }
//            this.mFeUltrasysCtrl.SetBExpand(true);
//            this.mDscCtrl.SetExpand(true);
//        } else {
//            if (this.mIsSetFe) {
//                HSysctrlIf.SetBExpand(false);
//            }
//            this.mFeUltrasysCtrl.SetBExpand(false);
//            this.mDscCtrl.SetExpand(false);
//        }
//        this.mBScanPara.mBExpand.set(i);
        if (this.mCallBack == null) {
            return;
        }
        this.mCallBack.UpdateSampleWin();
    }

    public int getExpandLevel() {
        return this.mExpandLevel;
    }

    public void SetBEnhance(int i) {
//        HSysctrlIf.SetBImgEnhanceLevel(i);
//        this.mBDspPara.mEnhance.set(i);
//        this.mDscCtrl.SetBEnhanceLevel(i);
    }

    public void SetBDynamic(int i) {
        this.mBDynmic_lv = i;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetBDynamicLevel(i);
//        }
//        this.mBDspPara.mDynamic.set(i);
    }

    public void SetBFrameCorre(int i) {
//        this.mFeUltrasysCtrl.SetBFrameCorreLevel(i);
//        this.mBDspPara.mFrameCorre.set(i);
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetBFrameCorrLevel(i);
//        }
    }

    public void SetBFrameComp(int i) {
        boolean z = true;
//        if (this.mIsSetFe) {
//            if (i <= 0) {
//                z = false;
//            }
//            HSysctrlIf.SetBSpaceCompSwitch(z);
//        }
//        this.mBDspPara.mFrameComp.set(i);
//        this.mDscCtrl.SetBSpaceComp(i);
    }

    public void SetBGrayMap(int i) {
        if (i <= 0) {
            i = 1;
        }
        if (i > 18) {
            i = 18;
        }
//        this.mBDspPara.mGray.set(i);
    }

    public void SetCLineRange(int i, int i2) {
//        this.mDscCtrl.SetCDispLineRange(i, i2);
//        if (this.mCallBack != null) {
//            this.mCallBack.SetCDispLineRange(i, i2, this.mDscCtrl.mCTotalLine);
//        }
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCDispLineRange(i, i2);
//        }
    }

    public void SetCDotRange(int i, int i2) {
//        if (i2 > this.mDscCtrl.mBDotPerLine) {
//            i2 = this.mDscCtrl.mBDotPerLine;
//        }
//        this.mDscCtrl.SetCDispDotRange(i, i2);
//        if (this.mCallBack != null) {
//            this.mCallBack.SetCDispDotRange(i, i2, this.mDscCtrl.mBDotPerLine);
//        }
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCDispPointRange(i, i2);
//        }
    }

    public void SetCScale(int i) {
        this.mCScale_lv = i;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCScaleLevel(i);
//        }
//        this.mFeUltrasysCtrl.SetCScaleLevel(i);
//        this.mCScanPara.mScale.set(i);
    }

    public void SetCFocus(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCFocus(i);
//        }
    }

    public void SetCFreq(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCFreq(i);
//        }
//        this.mCScanPara.mFreq.set(i);
    }

    public void SetCDispDensity(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCDispDensity(i);
//        }
    }

    public void SetCSteer(byte b) {
        if ((GetProbeConType() == 5 || GetProbeConType() == 4) && b != this.mPWSteer_lv) {
            SetPWSteer(b);
        }
        if (b > 0) {
            b = 2;
        } else if (b < 0) {
            b = -2;
        }
        this.mCSteer = b;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCSteer(b);
//        }
//        this.mCScanPara.mSteer.set((b - (-2)) / 2);
//        float SetCSteerLevel = this.mFeUltrasysCtrl.SetCSteerLevel(this.mCScanPara.mSteer.get());
//        if (this.mCallBack != null) {
//            this.mCallBack.SetCSampWinSteer(SetCSteerLevel);
//        }
    }

    public int GetCSteer() {
        return this.mCSteer;
    }

    public void SetCPackSize(byte b) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCPktSizeLevel(b);
//        }
    }

    public void SetCFlowStatus(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCFlowState(i);
//        }
    }

    public void SetColorMode(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetColorMode(i);
//        }
    }

    public void SetCTDiMode(int i, int i2) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCTDIMode(i, i2);
//        }
    }

    public void SetCFocusRatio(float f) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCFocusRatio(f);
//        }
    }

    public void SetCSmoothlevel(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCSmoothLevel(i);
//        }
    }

    public void SetCPriority(int i) {
//        this.mCDspPara.mPriority.set(i);
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCPriority(0);
//        }
    }

    public void SetCFrameCorre(int i) {
//        this.mFeUltrasysCtrl.SetCFrameCorreLevel(i);
//        this.mCDspPara.mFrameCorre.set(i);
//        if (Instance().GetProbeConType() == 2 || Instance().GetProbeConType() == 3) {
//            HSysctrlIf.CalcScanFrame();
//        }
//        HSysctrlIf.SetCFrameCorrLevel(i);
    }

    public void SetCWallFilter(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCWallFilterLevel(i);
//        }
//        this.mCDspPara.mWallFilter.set(i);
    }

    public void SetCGain(int i) {
        this.mCGain_lv = i;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetCGainLevel(i);
//        }
//        this.mCDspPara.mGain.set(i);
    }

    public void SetVelColorMap(int i) {
        if (i <= 0) {
            i = 0;
        }
        if (i > 6) {
            i = 6;
        }
//        this.mCDspPara.mVelColorMap.set(i);
    }

    public void SetPowerColorMap(int i) {
        if (i <= 0) {
            i = 0;
        }
        if (i > 4) {
            i = 4;
        }
//        this.mCDspPara.mPowerColorMap.set(i);
    }

    public void SetMDispLine(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetMLine(i);
//        }
//        this.mMDspPara.mDispLine.set(i);
//        if (this.mCallBack != null) {
//            this.mCallBack.SetMDispLine(i, this.mDscCtrl.mBTotalLine);
//        }
    }

    public void SetMGain(int i) {
        this.mMGain_lv = i;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetMGainLevel(i);
//        }
//        this.mMDspPara.mGain.set(i);
    }

    public void SetMDynamic(int i) {
        this.mMDynamic = i;
//        this.mMDspPara.mDynamic.set(i);
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetMDynamicLevel(i);
//        }
    }

    public void SetMSpeed(int i) {
        this.mMSpeed_lv = i;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetMDESpeed(i);
//        }
//        if (this.mDispMode.GetMode() == 5) {
//            SetPwSpeed(i);
//        } else {
//            this.mMSpeedLevel = i;
//            this.mMScanPara.mSpeed.set(this.mMSpeedLevel);
//            if (this.mMSpeedLevel > 0 && this.mMSpeedLevel <= 3) {
//                CineReset();
//                this.mDscCtrl.MPreReset(this.mMDSpeed[this.mMSpeedLevel - 1]);
//            }
//        }
//        this.mDispSurface.ResetMDDispBuff();
    }

    public void SetMGrayMap(int i) {
        if (i <= 0) {
            i = 1;
        }
        if (i > 18) {
            i = 18;
        }
//        this.mMDspPara.mGray.set(i);
    }

    public void SetPWSteer(byte b) {
        this.mPWSteer_lv = b;
        if (b > 0) {
            b = 2;
        } else if (b < 0) {
            b = -2;
        }
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetPwSteer(b);
//        }
//        this.mPWScanPara.mSteer.set((b - (-2)) / 2);
//        float SetPWSteerLevel = this.mFeUltrasysCtrl.SetPWSteerLevel(this.mPWScanPara.mSteer.get());
//        if (this.mCallBack != null) {
//            this.mCallBack.SetPWSampleGateSteer(SetPWSteerLevel);
//        }
    }

    public void SetPwFreq(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetPwFreq(i);
//        }
//        this.mFeUltrasysCtrl.SetPwFrq(i);
//        this.mPWScanPara.mFreq.set(i);
//        this.mPWScanPara.mScale.CalcScaleTable();
//        this.mPWScanPara.mScale.initTableStr();
//        if (this.mCallBack != null) {
//            this.mCallBack.SetPWFreq(i);
//        }
    }

    public void SetPwScale(int i) {
        this.mPWScale_lv = i;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetPwScale(i);
//            HSysctrlIf.SetCScaleLevel(i);
//        }
//        this.mFeUltrasysCtrl.SetPwScale(i);
//        this.mPWScanPara.mScale.set(i);
        PwReset();
    }

    public void SetPwGain(int i) {
        this.mPWGain_lv = i;
//        this.mFeUltrasysCtrl.SetPwGain(i);
//        this.mPWDspPara.mGain.set(i);
    }

    public void SetPwWallFilter(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetPwFilter(i);
//        }
//        this.mPWDspPara.mWallFilter.set(i);
    }

    public void SetPwGateSize(int i) {
        float f = i / 100.0f;
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetPwGateSize(f);
//        }
//        this.mPWDspPara.mSv.SetValue(10.0f * f);
//        this.mFeUltrasysCtrl.SetPwGateSize(f);
//        if (this.mCallBack != null) {
//            this.mCallBack.SetPWGateSize(f);
//        }
    }

    public void SetPwGateSize(float f) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetPwGateSize(f);
//        }
//        this.mPWDspPara.mSv.SetValue(10.0f * f);
//        this.mFeUltrasysCtrl.SetPwGateSize(f);
//        if (this.mCallBack != null) {
//            this.mCallBack.SetPWGateSize(f);
//        }
    }

    public void SetPwGatePos(float f) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetPwGatePos(f);
//        }
//        this.mPWDspPara.mSVD.SetValue(10.0f * f);
//        this.mFeUltrasysCtrl.SetPwGatePos(f);
//        if (this.mCallBack != null) {
//            this.mCallBack.SetPWGatePos(f, this.mDscCtrl.mBDotPerLine);
//        }
    }

    public void SetPwDispLine(int i) {
//        if (this.mIsSetFe) {
//            HSysctrlIf.SetDLine(i);
//        }
//        this.mPWDspPara.mPwDispLine.set(i);
//        if (this.mCallBack != null) {
//            this.mCallBack.SetPWDispLine(i, this.mDscCtrl.mBTotalLine);
//        }
    }

    public void SetPwBaseLine(int i) {
//        this.mPWDspPara.mBaseLine.set(i);
//        this.mPWScanPara.mScale.SetBaseline(i);
//        if (this.mPWBaseline_lv != i) {
//            PwReset();
//        }
        this.mPWBaseline_lv = i;
    }

    public void SetPwReserse(int i) {
//        this.mPWDspPara.mPwReverse.set(i);
//        this.mPWScanPara.mScale.SetReverse(i);
        if (this.mPWReverse_lv != i) {
            PwReset();
        }
        this.mPWReverse_lv = i;
    }

    public void SetPwAutoCalc(int i) {
//        this.mPWDspPara.mPwAutoCalc.set(i);
    }

    public void SetPwDynamic(int i) {
//        this.mPWDspPara.mPwDynamic.set(i);
//        this.mFeUltrasysCtrl.SetPwCompress(i);
    }

    public void SetPwVolume(int i) {
//        this.mPWDspPara.mPwVolume.set(i);
//        this.mFeUltrasysCtrl.SetPwVolume(i);
    }

    public void SetPwSpeed(int i) {
        this.mPwSpeedLevel = i;
//        this.mPWScanPara.mSpeed.set(this.mPwSpeedLevel);
        if (this.mPwSpeedLevel <= 0 || this.mPwSpeedLevel > 3) {
            return;
        }
        PwReset();
    }

    public void SetPwAngle(int i) {
//        this.mPWDspPara.mPwAngle.set(this.mPWDspPara.mPwAngle.ValueToTable(i));
//        this.mPWScanPara.mScale.SetAngle(i);
        if (this.mCallBack != null) {
            this.mCallBack.SetPWAngle(i);
        }
    }

    public void PwReset() {
//        this.mDispSurface.ResetMDDispBuff();
//        if (this.mPwSpeedLevel - 1 >= 0 && this.mPwSpeedLevel < 3) {
//            this.mDscCtrl.PwPreReset(this.mMDSpeed[this.mPwSpeedLevel - 1]);
//        }
//        this.mDscCtrl.PwPostReset();
//        UsbProbeManager.Instance().ClearPwQueue();
//        this.mMeasAutoCalc.Reset();
        CineReset();
    }

    public void SendCmdToHost(int i, int i2, byte[] bArr, int i3, boolean z) {
        if (GetProbeConType() != 0) {
            return;
        }
        HostWlanProbe.Instance().SendCmd(i, i2, bArr, i3, z);
    }

    public void SendCmdToProbe(int i, int i2, final byte[] bArr, final int i3) {
        int GetProbeConType = GetProbeConType();
        int i4 = 0;
        if (GetProbeConType == 0) {
            WlanProbe.Instance().SendCmd(i, i2, bArr, 0);
            return;
        }
        switch (GetProbeConType) {
            case 2:
                WlanProbe.Instance().SendCmd(i, i2, bArr, 0);
                return;
            case 3:
                WlanProbe.Instance().SendCmd(i, i2, bArr, 0);
                return;
            case 4:
                if (i == 152 || i == 100 || i == 144 || i == 169 || i == 130 || i == 159 || i == 160 || i == 158 || i == 3) {
                    byte[] bArr2 = new byte[8];
                    bArr2[0] = (byte) i;
                    while (i4 < i2) {
                        int i5 = i4 + 1;
                        bArr2[i5] = bArr[i4];
                        i4 = i5;
                    }
//                    UsbProbeManager.Instance().mUsbInterface.Write(bArr2, i2);
                    return;
                }
                x.task().autoPost(new Runnable() { // from class: handprobe.application.ultrasys.Ultrasys.4
                    @Override // java.lang.Runnable
                    public void run() {
//                        UsbProbeManager.Instance().ParaCmdProc(i3, bArr);
                    }
                });
                return;
            case 5:
                if (i == 152 || i == 153 || i == 154 || i == 100 || i == 144 || i == 3 || i == 169 || i == 130) {
                    WlanProbe.Instance().SendCmd(i, i2, bArr, 0);
                    return;
                } else if (i == 159 || i == 160 || i == 158 || i == 162) {
                    WlanProbe.Instance().SendCmd(i, i2, bArr, 2);
                    return;
                } else {
//                    UsbProbeManager.Instance().ParaCmdProc(i3, bArr);
                    return;
                }
            default:
                return;
        }
    }

    public void SendGetCmdToProbe(int i, int i2, byte[] bArr) {
        int GetProbeConType = GetProbeConType();
        Log.i("connType", GetProbeConType + "");
        if (GetProbeConType == 0) {
            WlanProbe.Instance().SendCmd(i, i2, bArr, 1);
            return;
        }
        switch (GetProbeConType) {
            case 2:
                WlanProbe.Instance().SendCmd(i, i2, bArr, 1);
                return;
            case 3:
                WlanProbe.Instance().SendCmd(i, i2, bArr, 1);
                return;
            case 4:
                if (i == 98) {
//                    UsbProbeManager.Instance().UpdateDicomAuth();
                    return;
                } else if (i != 149) {
                    return;
                } else {
//                    UsbProbeManager.Instance().UpdateDicomAuth();
                    return;
                }
            case 5:
                if (i > 3 && i < 103 && i != 98 && i != 100 && i != 161 && i != 144) {
                    return;
                }
                WlanProbe.Instance().SendCmd(i, i2, bArr, 1);
                return;
            default:
                WlanProbe.Instance().SendCmd(i, i2, bArr, 1);
                return;
        }
    }

//    public ParaBase GetParaObject(int i) {
//        return this.mFuncIdMap.GetParaObject(i);
//    }

    public float GetZoomScale() {
//        if (this.mFeUltrasysCtrl.mZoomScale == 0.0f) {
//            return 1.0f;
//        }
        return 1.0f; //this.mFeUltrasysCtrl.mZoomScale;
    }

    public int FindExamIndex(int i) {
//        for (int i2 = 0; i2 < ExamTypesId.EXAM_MAP.length; i2++) {
//            if (ExamTypesId.EXAM_MAP[i2][1] == i) {
//                return i2;
//            }
//        }
        return 255;
    }

    public String FindExamString(int i) {
//        for (int i2 = 0; i2 < ExamTypesId.EXAM_MAP.length; i2++) {
//            if (ExamTypesId.EXAM_MAP[i2][1] == i) {
//                return LanguageUtil._NLS(ExamTypesId.EXAM_MAP[i2][0]);
//            }
//        }
        return "";
    }

    public void SetExamType(int i, int i2) {
        if (i2 != this.mCurPatternId) {
            SelectProber(this.mProbeId, i2);
        }
        if (i < 0) {
            return;
        }
//        this.mPresetparams.setPresetParam(0, Integer.valueOf(i));
        this.mCurExamMode = i;
//        this.mPresetparams.mExamTypeObservable.notifyExamType(this.mCurExamMode);
    }

    public void GetExamImgPara(int i) {
        SendGetCmdToProbe(86, 1, new byte[]{(byte) i});
    }

    public void SetPresetParam(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
//        while (i2 <= 129) {
//            if ((i2 == 25) | (i2 == 24) | (i2 <= 79 && i2 >= 74) | (i2 <= 87 && i2 >= 83)) {
//                this.mCurPresetData.setPresetParamItem(i2, new byte[]{bArr[i3], bArr[i3 + 1], bArr[i3 + 2], bArr[i3 + 3]});
//                i3 += 4;
//            } else {
//                this.mCurPresetData.setPresetParamItem(i2, new byte[]{bArr[i3], bArr[i3 + 1]});
//                i3 = i2 == 82 ? i3 + 4 : i3 + 2;
//            }
//            i2++;
//        }
//        this.mPresetparams.setPresetData(this.mCurPresetData);
//        this.mPresetparams.getPresetData().setCurrentByteData(bArr);
//        if (Instance().mIsPresetDlgOpen) {
//            this.mPresetparams.mPresetDataObservable.notifyPresetParam();
//        }
        if (this.mIsGetPresetParamCmd && this.mCallBack != null) {
            this.mCallBack.OnSetPresetParam();
        }
        this.mIsGetPresetParamCmd = false;
    }

    public void setMITISData(byte[] bArr, int i) {
        int i2 = i / 4;
        float[] fArr = new float[i2];
        int i3 = 0;
//        for (int i4 = 0; i4 < i2; i4++) {
//            fArr[i4] = Tools.B2F(new byte[]{bArr[i3], bArr[i3 + 1], bArr[i3 + 2], bArr[i3 + 3]});
//            i3 += 4;
//            this.mMITIData.setMITIData(i4, fArr[i4]);
//        }
//        if (this.mIsGetPresetParamCmd) {
//            Instance().GetExamImgPara(this.mCurExamMode);
//        } else if (this.mMITIData.setTIContent()) {
//            this.mMITIDataObservable.notifyMITI();
//        }
//        this.mBScanPara.mMiti.SetMITI(fArr);
    }

    public void setMITIData(float[] fArr) {
//        this.mMITIData.setMITIData(0, fArr[0]);
//        this.mMITIData.setMITIData(1, fArr[1]);
//        this.mMITIData.setMITIData(2, fArr[2]);
//        this.mMITIData.setMITIData(3, fArr[3]);
//        if (this.mMITIData.setTIContent()) {
//            this.mMITIDataObservable.notifyMITI();
//        }
//        this.mBScanPara.mMiti.SetMITI(fArr);
    }

    public void setIsGetPresetParamCmd() {
        this.mIsGetPresetParamCmd = true;
    }

    public void InitGreyColorMaps(int[][] iArr, int[][] iArr2, int[][] iArr3) {
//        Instance().mBDspPara.mGray.InitGrayMapForPlayer(iArr);
//        Instance().mMDspPara.mGray.InitGrayMapForPlayer(iArr);
//        this.mCDspPara.mVelColorMap.mVelColorMap[0] = iArr2[1];
//        this.mCDspPara.mVelColorMap.mVelColorMap[1] = iArr2[3];
//        this.mCDspPara.mVelColorMap.mVelColorMap[2] = iArr2[4];
//        this.mCDspPara.mVelColorMap.mVelColorMap[3] = iArr2[5];
//        this.mCDspPara.mVelColorMap.mVelColorMap[4] = iArr2[6];
//        this.mCDspPara.mVelColorMap.mVelColorMap[5] = iArr2[9];
//        for (int i = 0; i < 6; i++) {
//            for (int i2 = 0; i2 < 128; i2++) {
//                int i3 = this.mCDspPara.mVelColorMap.mVelColorMap[i][i2];
//                int i4 = 255 - i2;
//                this.mCDspPara.mVelColorMap.mVelColorMap[i][i2] = this.mCDspPara.mVelColorMap.mVelColorMap[i][i4] | ViewCompat.MEASURED_STATE_MASK;
//                this.mCDspPara.mVelColorMap.mVelColorMap[i][i4] = i3 | ViewCompat.MEASURED_STATE_MASK;
//            }
//        }
//        for (int i5 = 0; i5 < 4; i5++) {
//            if (i5 < 4) {
//                this.mCDspPara.mPowerColorMap.mPowerColorMap[i5] = iArr3[i5];
//            }
//        }
//        for (int i6 = 0; i6 < 4; i6++) {
//            for (int i7 = 0; i7 < 256; i7++) {
//                this.mCDspPara.mPowerColorMap.mPowerColorMap[i6][i7] = this.mCDspPara.mPowerColorMap.mPowerColorMap[i6][i7] | ViewCompat.MEASURED_STATE_MASK;
//            }
//        }
//        Instance().mCDspPara.mVelColorMap.InitVMapForPlayer(this.mCDspPara.mVelColorMap.mVelColorMap);
//        Instance().mCDspPara.mPowerColorMap.InitPMapForPlayer(this.mCDspPara.mPowerColorMap.mPowerColorMap);
    }

    public String[] InitBGrayMapStr() {
        String[] strArr = new String[18];
//        for (int i = 0; i < 18; i++) {
//            strArr[i] = LanguageUtil._NLS(R.array.gray_map) + String.valueOf(this.mBDspPara.mGray.mTable[i] + 1);
//        }
        return strArr;
    }

    public void closeBCompInCPMode() {
//        if (this.mDscCtrl.mSpaceComp == 1) {
//            this.mDscCtrl.SetBSpaceComp(0);
//        }
    }

    public void UseBPara() {
        SetDepth(this.mDepth_lv);
        SetBFreq(this.mBFreq_lv);
        SetBDynamic(this.mBDynmic_lv);
        CineReset();
    }

    public void UseMPara() {
        SetMDynamic(this.mMDynamic);
        SetMGain(this.mMGain_lv);
        SetMSpeed(this.mMSpeed_lv);
    }

    public void UsePWPara() {
        SetPwScale(this.mPWScale_lv);
        SetPwGain(this.mPWGain_lv);
        SetPwGateSize(this.mPWSVSize_lv);
        SetPwAngle(this.mPwAngle_lv);
    }

    public void UseColorPara() {
        SetCGain(this.mCGain_lv);
        SetCPriority(this.mCPriority_lv);
        SetCFrameCorre(this.mCPersistence_lv);
        SetCFlowStatus(0);
        SetColorMode(1);
        SetCTDiMode(0, GetPattern(GetProbeId(), 0));
        SetCFocusRatio(0.5f);
        SetCDispDensity(0);
        SetCPackSize((byte) this.mCPackSize_lv);
        SetCFocus(0);
        SetCWallFilter(this.mCWallFilter_lv);
        SetCSteer((byte) this.mCSteer_lv);
        SetCScale(this.mCScale_lv);
        SetCFreq(this.mCFreq_lv);
        SetCSmoothlevel(0);
        SetCFocus(5);
        SetVelColorMap(this.mCColorMap_lv);
    }

    public void UsePowerPara() {
        SetCGain(this.mPowerGain_lv);
        SetCPriority(this.mPowerPriority_lv);
        SetCFrameCorre(this.mPowerPersistence_lv);
        SetCFlowStatus(0);
        SetColorMode(2);
        SetCTDiMode(0, GetPattern(GetProbeId(), 0));
        SetCFocusRatio(0.5f);
        SetCDispDensity(0);
        SetCPackSize((byte) this.mPowerPackSize_lv);
        SetCFocus(0);
        SetCWallFilter(this.mPowerWallFilter_lv);
        SetCSteer((byte) this.mPowerSteer_lv);
        SetCScale(this.mPowerScale_lv);
        SetCFreq(this.mPowerFreq_lv);
        SetCSmoothlevel(0);
        SetCFocus(5);
        SetPowerColorMap(this.mPowerColorMap_lv);
    }

    public void UpdateCPara() {
//        this.mCGain_lv = this.mCDspPara.mGain.get();
//        this.mCWallFilter_lv = this.mCDspPara.mWallFilter.get();
//        this.mCPriority_lv = this.mCDspPara.mPriority.get();
//        this.mCFreq_lv = this.mCScanPara.mFreq.get();
//        this.mCPersistence_lv = this.mCDspPara.mFrameCorre.get();
//        this.mCScale_lv = this.mCScanPara.mScale.get();
//        this.mCSteer_lv = GetCSteer();
//        this.mCColorMap_lv = this.mCDspPara.mVelColorMap.get();
    }

    public void UpdatePowerPara() {
//        this.mPowerGain_lv = this.mCDspPara.mGain.get();
//        this.mPowerWallFilter_lv = this.mCDspPara.mWallFilter.get();
//        this.mPowerPriority_lv = this.mCDspPara.mPriority.get();
//        this.mPowerFreq_lv = this.mCScanPara.mFreq.get();
//        this.mPowerPersistence_lv = this.mCDspPara.mFrameCorre.get();
//        this.mPowerScale_lv = this.mCScanPara.mScale.get();
//        this.mPowerSteer_lv = GetCSteer();
//        this.mPowerColorMap_lv = this.mCDspPara.mPowerColorMap.get();
    }

    public boolean UseProbe(int i, int i2) {
//        ExamImgParaPerMode.ProbeParameter probeParameterById;
        WlanProbe.Instance().UploadDualProbeActive(i);
        UpdateVet();
        if (this.mProbeDisabled) {
            return false;
        }
        this.mFreezeStatus = true;
        ExitPowerSave();
        ResumSetFe();
        if (i <= 0) {
            i = this.mProbeId;
        }
        if (i <= 0) {
            return false;
        }
        int GetPattern = GetPattern(i, i2);
        SelectProber(i, GetPattern);
        SetExamType(i2, GetPattern);
//        int GetBTotalLine = this.mFeUltrasysCtrl.GetBTotalLine();
//        ExamImgParaPerMode examImgParaByExamId = this.mPresetServer.mExamImgPresetServer.getExamImgParaByExamId(i2 == -1 ? this.mPresetServer.mExamModePresetServer.GetProbeDefaultExamMode(i) : i2);
//        if (examImgParaByExamId == null || (probeParameterById = examImgParaByExamId.getProbeParameterById(i)) == null) {
//            return false;
//        }
//        this.mUseProbeStatus = true;
//        this.mDepth_lv = probeParameterById.mProbePara.nBDepth;
//        this.mBGain_lv = probeParameterById.mProbePara.nBGain;
//        this.mBThiSwitch = probeParameterById.mProbePara.nTHISwitchOff;
//        this.mBFreq_lv = probeParameterById.mProbePara.nBProbeFrq;
//        this.mExpandSwitch = probeParameterById.mProbePara.nTrapeziumImaging;
//        this.mBSteer = probeParameterById.mProbePara.nBSteer;
//        this.mBEnhance_lv = probeParameterById.mProbePara.nBImgEnhance;
//        this.mBFrameCorr_lv = probeParameterById.mProbePara.nBFrameCorre;
//        this.mBDynmic_lv = probeParameterById.mProbePara.nBDynamic;
//        this.mBFrameCompSwitch = probeParameterById.mProbePara.nBFrameComp;
//        this.mTsi_lv = examImgParaByExamId.mOnlyModeSenPara.nTSI;
//        this.mApower_lv = examImgParaByExamId.mOnlyModeSenPara.APower;
//        this.mBEffect_lv = probeParameterById.mProbePara.nBPostEffect;
//        this.mBFocus_lv = probeParameterById.mProbePara.nFocusPos;
//        this.mThiGain_lv = probeParameterById.mProbePara.nThiGain;
        if (this.mThiGain_lv <= 0) {
            this.mThiGain_lv = 32;
        }
////        this.mThiFrameCorr_lv = probeParameterById.mProbePara.nTHIFrameCorre;
////        this.mHorFlip = probeParameterById.mProbePara.nHRev;
////        this.mVerFlip = probeParameterById.mProbePara.nVRev;
////        this.mMSpeed_lv = examImgParaByExamId.mOnlyModeSenPara.nMSpeed;
////        int i3 = GetBTotalLine / 2;
////        this.mMDispLine = i3;
////        this.mMGain_lv = probeParameterById.mProbePara.nMGain;
////        this.mMDynamic = probeParameterById.mProbePara.nMDynamic;
////        this.mMEffect_lv = probeParameterById.mProbePara.nMPostEffect;
////        this.mCGain_lv = probeParameterById.mProbePara.nCGain;
////        this.mCWallFilter_lv = probeParameterById.mProbePara.nCWallFilter;
////        this.mCPriority_lv = probeParameterById.mProbePara.nCPriority;
////        this.mCFreq_lv = probeParameterById.mProbePara.nCProbeFrq;
////        this.mCPersistence_lv = probeParameterById.mProbePara.nCPersistence;
////        this.mCScale_lv = probeParameterById.mProbePara.nCScale;
////        short s = probeParameterById.mProbePara.nCSteer;
//        this.mCSteer = s;
//        this.mCSteer_lv = s;
//        this.mCPackSize_lv = probeParameterById.mProbePara.nCPackSize;
//        this.mCColorMap_lv = probeParameterById.mProbePara.nCMap + 1;
//        this.mPowerGain_lv = probeParameterById.mProbePara.nPowerGain;
//        this.mPowerWallFilter_lv = probeParameterById.mProbePara.nPowerWallFilter;
//        this.mPowerPriority_lv = probeParameterById.mProbePara.nPowerPriority;
//        this.mPowerFreq_lv = probeParameterById.mProbePara.nCProbeFrq;
//        this.mPowerPersistence_lv = probeParameterById.mProbePara.nPowerPersistence;
//        this.mPowerScale_lv = probeParameterById.mProbePara.nPowerScale;
//        this.mPowerSteer_lv = probeParameterById.mProbePara.nCSteer;
//        this.mPowerPackSize_lv = probeParameterById.mProbePara.nPowerPackSize;
//        this.mPowerColorMap_lv = probeParameterById.mProbePara.nCPower + 1;
//        this.mPWSteer_lv = probeParameterById.mProbePara.nPWSteer;
//        this.mPWScale_lv = probeParameterById.mProbePara.nPWScale;
//        this.mPWDoplerFrq_lv = probeParameterById.mProbePara.nPWDoplerFrq;
//        this.mPWGain_lv = probeParameterById.mProbePara.nPWGain;
//        this.mPWFilter_lv = probeParameterById.mProbePara.nPWFilter;
//        this.mPWSVDepth_lv = probeParameterById.mProbePara.nPWSVDepth;
//        if (probeParameterById.mProbePara.nPWSVSize == 0) {
//            probeParameterById.mProbePara.nPWSVSize = (short) 5;
//        }
//        this.mPWSVSize_lv = probeParameterById.mProbePara.nPWSVSize;
//        this.mPWBaseline_lv = examImgParaByExamId.mOnlyModeSenPara.nPWBaseLinePos;
//        this.mPWDynamic_lv = examImgParaByExamId.mOnlyModeSenPara.nPWDynamic;
//        this.mPWReverse_lv = examImgParaByExamId.mOnlyModeSenPara.nPWReverse;
//        this.mPWSpeed_lv = examImgParaByExamId.mOnlyModeSenPara.nPWScanSpeed;
//        this.mPWVolume_lv = examImgParaByExamId.mOnlyModeSenPara.nDVolume;
//        this.mPwAngle_lv = examImgParaByExamId.mOnlyModeSenPara.nPWAdjustAngle;
//        int[] iArr = {50, 50, 50, 50, 50, 50, 50, 50};
//        HSysctrlIf.SetProbePod(i, i2, GetPattern, 0);
//        HSysctrlIf.ParaChangeStart(0);
//        this.mDscCtrl.DscTableLock();
//        SetDispMode(0);
//        HSysctrlIf.StopScan();
//        SetBFreq(this.mBFreq_lv);
//        HSysctrlIf.SetScanMode(0);
//        HSysctrlIf.SetBTHISwitch(this.mBThiSwitch > 0);
//        HSysctrlIf.SetBDTgc(iArr);
//        HSysctrlIf.SetBDispPointRange(0, 479);
//        SetBExpand(this.mExpandSwitch);
//        SetBSteer(this.mBSteer);
//        HSysctrlIf.SetBDispDensity(1);
//        SetDepth(this.mDepth_lv);
//        if (this.mBThiSwitch > 0) {
//            SetBGain(this.mThiGain_lv);
//        } else {
//            SetBGain(this.mBGain_lv);
//        }
//        this.mDscCtrl.DscTableUnLock();
//        SetTsi(this.mTsi_lv);
//        SetAPower(this.mApower_lv);
//        HSysctrlIf.SetScanMode(0);
//        SetBEnhance(this.mBEnhance_lv);
//        SetBFreq(this.mBFreq_lv);
//        SetBFrameCorre(this.mBFrameCorr_lv);
//        SetBDynamic(this.mBDynmic_lv);
//        HSysctrlIf.SetBDispLineRange(0, GetBTotalLine - 1);
//        SetBFrameComp(this.mBFrameCompSwitch);
//        SetBFocus(this.mBFocus_lv);
//        SetBGrayMap(this.mBEffect_lv);
//        setBLR(this.mHorFlip);
//        setBUD(this.mVerFlip);
//        SetMGain(this.mMGain_lv);
//        SetMDynamic(this.mMDynamic);
//        SetMSpeed(this.mMSpeed_lv + 1);
//        SetMDispLine(this.mMDispLine);
//        SetMGrayMap(this.mMEffect_lv);
//        int GetBTotalDot = this.mFeUltrasysCtrl.GetBTotalDot();
//        SetCDotRange(GetBTotalDot / 4, (GetBTotalDot * 3) / 4);
//        SetCLineRange(GetBTotalLine / 4, (GetBTotalLine * 3) / 4);
//        UseColorPara();
//        SetPowerColorMap(this.mPowerColorMap_lv);
//        SetPWSteer((byte) this.mPWSteer_lv);
//        SetPwWallFilter(this.mPWFilter_lv);
//        SetPwFreq(this.mPWDoplerFrq_lv);
//        SetPwGain(this.mPWGain_lv);
//        SetPwGatePos((this.mPidInerface.mCurProbePID.mDepthInterval * (this.mDepth_lv + 2)) / 2.0f);
//        SetPwGateSize(this.mPWSVSize_lv);
//        SetPwBaseLine(this.mPWBaseline_lv);
//        SetPwAngle(this.mPwAngle_lv);
//        SetPwAutoCalc(0);
//        SetPwDynamic(this.mPWDynamic_lv);
//        SetPwDispLine(i3 - 1);
//        SetPwScale(this.mPWScale_lv);
//        SetPwReserse(this.mPWReverse_lv);
//        SetPwSpeed(this.mPWSpeed_lv);
//        SetPwVolume(this.mPWVolume_lv);
//        if (this.mCallBack != null) {
//            this.mCallBack.OnUseProbe(i, i2);
//        }
//        this.mChangeProbe = false;
//        HSysctrlIf.ParaChangeEnd(0);
//        HSysctrlIf.StartScan();
//        UnFreeze(false);
//        this.mUseProbeStatus = false;
        return true;
    }

    public int GetPattern(int i, int i2) {
        return 0; //this.mPresetServer.mEOPPreset.GetPattern(i, i2);
    }

    public int GetProbeConType() {
        return 0; //ConnectType.Instance().GetConnType();
    }

    public void SetProbeConType(int i) {
//        ConnectType.Instance().SetConnType(i);
//        if (Build.VERSION.SDK_INT >= 28 && AppProc.Instance().mWifiProbeSelect != null) {
//            if (i == 2 || i == 5) {
//                AppProc.Instance().mWifiProbeSelect.SetFreshButtonVisible(false);
//            } else {
//                AppProc.Instance().mWifiProbeSelect.SetFreshButtonVisible(true);
//            }
//        }
//        if (AppProc.Instance().mWifiProbeSelect != null) {
//            if (i == 2 || i == 5) {
//                AppProc.Instance().mWifiProbeSelect.SetFreshSearchButtonVisible(false);
//            } else {
//                AppProc.Instance().mWifiProbeSelect.SetFreshSearchButtonVisible(true);
//            }
//        }
    }

    public void SetWlanProbeConState(boolean z) {
        if (z) {
            byte[] bArr = new byte[32];
            bArr[0] = -124;
            SendCmdToProbe(3, 1, bArr, 0);
            return;
        }
        byte[] bArr2 = new byte[32];
        bArr2[0] = -123;
        SendCmdToProbe(3, 1, bArr2, 0);
    }

    public void SetUsbProbeConState(boolean z) {
        if (z) {
            byte[] bArr = new byte[32];
            bArr[0] = -127;
            Instance().SendCmdToProbe(3, 1, bArr, 0);
        } else {
            byte[] bArr2 = new byte[32];
            bArr2[0] = -126;
            Instance().SendCmdToProbe(3, 1, bArr2, 0);
        }
        if (this.mCallBack != null) {
            this.mCallBack.OnUsbProbeStateChange(z);
        }
    }

    public boolean IsCmdExecuting() {
        if (GetProbeConType() == 4 || GetProbeConType() == 5) {
            return false;
        }
        return this.mIsCmdExecuting;
    }

    public void SetCmdExecuting(boolean z) {
        this.mIsCmdExecuting = z;
    }

    public void SetInspectionType(int i) {
//        this.mInspectionObjectType.SetInspectionObType(i);
    }

    public int GetInspectionType() {
        return 0; //this.mInspectionObjectType.GetInspectionObType();
    }

    public void SetVet(int i) {
        if (MyApplication.App().mMainActivity.getResources().getString(R.string.def_vet).equals("off") && i > 0) {
            if (!Instance().GetHumanVetAuth()) {
                this.mProbeDisabled = true;
                return;
            }
            this.mProbeDisabled = false;
        }
//        if (i != this.mIsVet) {
//            PresetFile.setPresetFilePath(i);
//        }
//        this.mIsVet = i;
//        this.mInspectionObjectType.SetInspectionObType(i);
//        if (this.mIsFirstEnter && this.mIsVet != 0 && this.mCallBack != null) {
//            this.mCallBack.OnSetVet();
//        }
        this.mIsFirstEnter = false;
    }

    public int GetVet() {
        return 0; //this.mInspectionObjectType.GetInspectionObType();
    }

    public int GetMUploadLineCount() {
//        if (GetProbeConType() == 5 || GetProbeConType() == 4) {
//            return HSysctrlIf.GetMUploadLineCount();
//        }
        return 4;
    }

    public void SendExamMode(int i) {
        Instance().SendCmdToProbe(76, 1, new byte[]{(byte) (i & 255)}, 0);
    }

    public void StopSetFe() {
        this.mIsSetFe = false;
    }

    public void ResumSetFe() {
        this.mIsSetFe = true;
    }

    public static void SetProbeBattery(Integer num) {
        WlanProbe.Instance().GetBattObservable().setChanged();
        WlanProbe.Instance().GetBattObservable().notifyObservers(num);
    }

    public void ClearDispSurface() {
//        Arrays.fill(this.mDispSurface.mSurfaceView.getImageBuff(), 0);
//        this.mDispSurface.mSurfaceView.requestRender();
    }

    public void CheckProbeChange(int i, boolean z) {
        int GetCurProbeId;
//        if (z && GetProbeConType() == 3 && (GetCurProbeId = UsbProbeManager.Instance().mUsbInterface.GetCurProbeId()) > 0) {
//            i = GetCurProbeId;
//        }
        if (i <= 0 || this.mProbeId == i) {
            return;
        }
        int i2 = 0;
        if (Instance().GetProbeConType() == 5 || Instance().GetProbeConType() == 4) {
//            int GetProbeDefaultExamMode = Instance().mPresetServer.mExamModePresetServer.GetProbeDefaultExamMode(i);
//            int GetPattern = Instance().GetPattern(i, GetProbeDefaultExamMode);
//            Instance().SetExamType(GetProbeDefaultExamMode, GetPattern);
//            i2 = GetPattern;
        }
        if (Instance().GetProbeConType() == 0) {
            return;
        }
        Instance().SelectProber(i, i2);
    }

    public int PWSpeedTime() {
        return this.mMDSpeed[this.mPwSpeedLevel - 1];
    }

    public void PWDispReset() {
        if (this.mPwSpeedLevel - 1 >= 0) {
//            this.mDscCtrl.PwPreReset(this.mMDSpeed[this.mPwSpeedLevel - 1]);
        }
    }

    public void ProbeTempratureTest() {
//        AppProc.Instance().OpenTestTemperature();
    }

    public void APowerTest() {
//        HSysctrlIf.SetAPtest(1);
    }

//    public void SetDicomPresetPara(DicomPresetPara dicomPresetPara) {
//        this.mDicomPresetPara = dicomPresetPara;
//        if (this.mDicomPresetPara.mDicomLocalPreset != null) {
//            HDicomIf.SetLocalPreset(this.mDicomPresetPara.mDicomLocalPreset.AETitle, this.mDicomPresetPara.mDicomLocalPreset.portNum, this.mDicomPresetPara.mDicomLocalPreset.packageSize);
//        }
//    }

//    public DicomPresetPara GetDicomPresetPara() {
//        return this.mDicomPresetPara;
//    }

    public void UpdateDicomAuth() {
        SetDicomAuth(false);
        Instance().SendGetCmdToProbe(98, 1, new byte[]{0});
    }

    public void UpdateVet() {
        Instance().SendGetCmdToProbe(100, 1, new byte[]{0});
    }

    public void UpdatePrjOption() {
        SetNoExpand(false);
        Instance().SendGetCmdToProbe(161, 1, new byte[]{0});
    }

    public boolean GetHumanVetAuth() {
        return this.mHumanVetAuth;
    }

    public void SetHumanVetAuth(boolean z) {
        this.mHumanVetAuth = z;
    }

    public boolean GetDicomAuth() {
        return this.mDicomAuth;
    }

    public void SetDicomAuth(boolean z) {
        this.mDicomAuth = z;
    }

    public boolean GetPwAuth() {
        return this.mPwAuth;
    }

    public void SetPwAuth(boolean z) {
        this.mPwAuth = z;
        if (GetDispMode() == 0) {
            UpdateDispMode(0);
        }
    }

    public boolean GetColorAuth() {
        return this.mColorAuth;
    }

    public void SetColorAuth(boolean z) {
        this.mColorAuth = z;
        if (GetDispMode() == 0) {
            UpdateDispMode(0);
        }
    }

    public void SetNoExpand(boolean z) {
        this.mNoExpand = z;
    }

    public boolean GetNoExpand() {
        return this.mNoExpand;
    }

    public int getDefaultExamModeIndex(int i) {
//        int intValue = this.mPresetServer.mExamModePresetServer.getExamModePresetData().mProbeDefaultModeMap.get(Integer.valueOf(i)).intValue();
//        Vector<Integer> vector = this.mPresetServer.mExamModePresetServer.getExamModePresetData().mProbeSupportModeMap.get(Integer.valueOf(i));
//        if (vector != null) {
//            int i2 = 0;
//            for (int i3 = 0; i3 < vector.size(); i3++) {
//                if (vector.get(i3).intValue() == intValue) {
//                    i2 = i3;
//                }
//            }
//            return i2;
//        }
        return 0;
    }

    public int WriteDicomAuth(String str) {
        byte[] bytes = str.getBytes();
        if (bytes.length != 8) {
            return -2;
        }
        if (!Tools.IsHexFormat(bytes, 8)) {
            return -3;
        }
        byte[] bArr = new byte[4];
        Tools.StrToHex_r(bytes, bArr, 4);
        byte[] bArr2 = {bArr[3], bArr[2], bArr[1], bArr[0]};
        if (GetProbeConType() != 5 && GetProbeConType() != 4) {
            return -1;
        }
        Instance().SendCmdToProbe(158, 4, bArr2, 0);
        return 0;
    }

    public int WriteColorAuth(String str) {
        byte[] bytes = str.getBytes();
        if (bytes.length != 8) {
            return -2;
        }
        if (!Tools.IsHexFormat(bytes, 8)) {
            return -3;
        }
        byte[] bArr = new byte[4];
        Tools.StrToHex_r(bytes, bArr, 4);
        byte[] bArr2 = {bArr[3], bArr[2], bArr[1], bArr[0]};
        if (GetProbeConType() != 5 && GetProbeConType() != 4) {
            return -1;
        }
        Instance().SendCmdToProbe(160, 4, bArr2, 0);
        return 0;
    }

    public int WritePwAuth(String str) {
        byte[] bytes = str.getBytes();
        if (bytes.length != 8) {
            return -2;
        }
        if (!Tools.IsHexFormat(bytes, 8)) {
            return -3;
        }
        byte[] bArr = new byte[4];
        Tools.StrToHex_r(bytes, bArr, 4);
        byte[] bArr2 = {bArr[3], bArr[2], bArr[1], bArr[0]};
        if (GetProbeConType() != 5 && GetProbeConType() != 4) {
            return -1;
        }
        Instance().SendCmdToProbe(159, 4, bArr2, 0);
        return 0;
    }

    public int WriteHumanVetAuth(String str) {
        byte[] bytes = str.getBytes();
        if (bytes.length != 8) {
            return -2;
        }
        if (!Tools.IsHexFormat(bytes, 8)) {
            return -3;
        }
        byte[] bArr = new byte[4];
        Tools.StrToHex_r(bytes, bArr, 4);
        byte[] bArr2 = {bArr[3], bArr[2], bArr[1], bArr[0]};
        if (GetProbeConType() != 5 && GetProbeConType() != 4) {
            return -1;
        }
        Instance().SendCmdToProbe(168, 4, bArr2, 0);
        return 0;
    }

    public void ProbeTest() {
        byte[] bArr = new byte[32];
        bArr[0] = -125;
        Instance().SendCmdToProbe(3, 1, bArr, 0);
        this.mProbeTest++;
    }

    public int GetProbeTest() {
        return this.mProbeTest;
    }

//    public MeasurePreset getmMeasurePreset() {
//        if (this.mMeasurePreset == null) {
//            MeasurePreset.initMeasurePresetPara();
//        }
//        return this.mMeasurePreset;
//    }

//    public void setmMeasurePreset(MeasurePreset measurePreset) {
//        this.mMeasurePreset = measurePreset;
//    }

//    public DisableProbeBtnPreset getmDisableProbeBtnPreset() {
//        if (this.mDisableProbeBtnPreset == null) {
//            DisableProbeBtnPreset.initDisableProbeBtnPreset();
//        }
//        return this.mDisableProbeBtnPreset;
//    }

//    public void setmDisableProbeBtnPreset(DisableProbeBtnPreset disableProbeBtnPreset) {
//        this.mDisableProbeBtnPreset = disableProbeBtnPreset;
//    }

//    public void setTwoBImageIndex(int i) {
//        this.mImagePlayer.mTwoBImageIndex = i;
//    }

    public void SetDualProbeActive(int i) {
//        HSysctrlIf.SetProbePosition(i);
    }

    public void SetProbePcbType(int i) {
//        HSysctrlIf.SetProbePcbType(i);
    }
}
