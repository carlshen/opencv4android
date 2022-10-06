package com.book.handprobe;

import com.book.tools.Tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FrameHead {
    public static final int CINE = 1;
    public static final int FRM = 0;
    public byte[] DicomBuf;
    public byte[] HeadBuf;
    public byte[] ParaBuf;
    public byte[] ReserveBuf;
    public int mBdyMrkSize;
    public bodymark mBdymrkData;
    public int mBmpSize;
    public int mCommSize;
    public List<comment> mCommentDataList;
    public DicomInfo mDicomInfo;
    public int mFrameNum;
    public int mFrameType;
    public int mMeasSize;
    public List<measwidget> mMeaswidgets;
    public ParaSet mParaSet;
    public ParaSet mParaSet1;
    public int mSImgSize;
    public int mTImgSize;

    public void WriteDicomInfo() {
    }

    public FrameHead() {
        this.HeadBuf = new byte[128];
        this.ParaBuf = new byte[512];
        this.DicomBuf = new byte[2048];
        this.ReserveBuf = new byte[512];
        this.mFrameType = 0;
        this.mFrameNum = 0;
        this.mSImgSize = 0;
        this.mTImgSize = 0;
        this.mBmpSize = 0;
        this.mParaSet = new ParaSet();
        this.mDicomInfo = new DicomInfo();
        this.mBdyMrkSize = 0;
        this.mCommSize = 0;
        this.mMeasSize = 0;
        this.mBdymrkData = new bodymark();
        this.mCommentDataList = new ArrayList();
        this.mMeaswidgets = new ArrayList();
    }

    public FrameHead(int i, int i2, int i3, int i4) {
        this.HeadBuf = new byte[128];
        this.ParaBuf = new byte[512];
        this.DicomBuf = new byte[2048];
        this.ReserveBuf = new byte[512];
        this.mFrameType = 0;
        this.mFrameNum = 0;
        this.mSImgSize = 0;
        this.mTImgSize = 0;
        this.mBmpSize = 0;
        this.mParaSet = new ParaSet();
        this.mDicomInfo = new DicomInfo();
        this.mBdyMrkSize = 0;
        this.mCommSize = 0;
        this.mMeasSize = 0;
        this.mBdymrkData = new bodymark();
        this.mCommentDataList = new ArrayList();
        this.mMeaswidgets = new ArrayList();
        this.mFrameType = i;
        this.mSImgSize = i2;
        this.mTImgSize = i3;
        this.mBmpSize = i4;
        if (Ultrasys.Instance().GetDispMode() == 50) {
//            this.mParaSet = AppProc.Instance().mMyMainActivity.mImageDisplayFragment.getTwoBParaSet(0);
//            this.mParaSet1 = AppProc.Instance().mMyMainActivity.mImageDisplayFragment.getTwoBParaSet(1);
        } else {
            UpdatePara();
        }
        writeHead();
        writePara();
        writeDicom();
        writeOthers();
    }

    public void UpdatePara() {
        ParaSet paraSet = this.mParaSet;
        paraSet.mProbeId = Ultrasys.Instance().mProbeId;
        paraSet.mDispMode = Ultrasys.Instance().GetDispMode();
        paraSet.mLastDispMode = Ultrasys.Instance().GetLastDispMode();
        paraSet.mExamMode = Ultrasys.Instance().mCurExamMode;
//        paraSet.mFrameRate = Ultrasys.Instance().mBScanPara.mFrameRate.get();
//        paraSet.mDepth = Ultrasys.Instance().mBScanPara.mDepth.get() + 1;
//        paraSet.mBGain = Ultrasys.Instance().mBDspPara.mGain.get();
//        paraSet.mBFreq = Ultrasys.Instance().mBScanPara.mFreq.get();
//        paraSet.mBExpand = Ultrasys.Instance().mBScanPara.mBExpand.get();
//        paraSet.mBFocus = Ultrasys.Instance().mBScanPara.mFocus.get();
//        paraSet.mBDynamic = Ultrasys.Instance().mBDspPara.mDynamic.get();
//        paraSet.mBFrameCorre = Ultrasys.Instance().mBDspPara.mFrameCorre.get();
//        paraSet.mBEnhance = Ultrasys.Instance().mBDspPara.mEnhance.get();
//        paraSet.mAPower = Ultrasys.Instance().mBScanPara.mAPower.get();
//        paraSet.mTsi = Ultrasys.Instance().mBScanPara.mTsi.get();
//        paraSet.mFrameComp = Ultrasys.Instance().mBDspPara.mFrameComp.get();
//        paraSet.mBExpand = Ultrasys.Instance().mBScanPara.mBExpand.get();
//        paraSet.mUD = Ultrasys.Instance().mBDspPara.mBFlipVer.get();
//        paraSet.mLR = Ultrasys.Instance().mBDspPara.mBFlipHor.get();
//        paraSet.mCScale = Ultrasys.Instance().mCScanPara.mScale.get();
//        paraSet.mCGain = Ultrasys.Instance().mCDspPara.mGain.get();
//        paraSet.mCFreq = Ultrasys.Instance().mCScanPara.mFreq.get();
//        paraSet.mCWallFilter = Ultrasys.Instance().mCDspPara.mWallFilter.get();
//        paraSet.mCPriority = Ultrasys.Instance().mCDspPara.mPriority.get();
//        paraSet.mCSteer = Ultrasys.Instance().mCScanPara.mSteer.get();
//        paraSet.mCFrameCorre = Ultrasys.Instance().mCDspPara.mFrameCorre.get();
//        paraSet.mCColorMap = Ultrasys.Instance().mCDspPara.mVelColorMap.get();
//        paraSet.mPowerColorMap = Ultrasys.Instance().mCDspPara.mPowerColorMap.get();
//        paraSet.mCStartLine = Ultrasys.Instance().mDscCtrl.getmStartLine();
//        paraSet.mCEndLine = Ultrasys.Instance().mDscCtrl.getmEndLine();
//        paraSet.mCStartDot = Ultrasys.Instance().mDscCtrl.getmStartDot();
//        paraSet.mCEndDot = Ultrasys.Instance().mDscCtrl.getmEndDot();
//        paraSet.mPWScale = Ultrasys.Instance().mPWScanPara.mScale.get();
//        paraSet.mPWFreq = Ultrasys.Instance().mPWScanPara.mFreq.get();
//        paraSet.mPWGain = Ultrasys.Instance().mPWDspPara.mGain.get();
//        paraSet.mPWSteer = Ultrasys.Instance().mPWScanPara.mSteer.mo268GetCurValue().intValue();
//        paraSet.mPWSv = Ultrasys.Instance().mPWDspPara.mSv.get();
//        paraSet.mPWSvD = Ultrasys.Instance().mPWDspPara.mSVD.GetCuriValue();
//        paraSet.mPWBaseLine = Ultrasys.Instance().mPWDspPara.mBaseLine.get();
//        paraSet.mPWSpeed = Ultrasys.Instance().mPWScanPara.mSpeed.get();
//        paraSet.mPWWallFilter = Ultrasys.Instance().mPWDspPara.mWallFilter.get();
//        paraSet.mPWAngle = Ultrasys.Instance().mPWDspPara.mPwAngle.get0();
//        paraSet.mPwReverse = Ultrasys.Instance().mPWDspPara.mPwReverse.get();
//        paraSet.mPwVolume = Ultrasys.Instance().mPWDspPara.mPwVolume.get();
//        paraSet.mPwDynamic = Ultrasys.Instance().mPWDspPara.mPwDynamic.get();
//        paraSet.mPwAutoCalc = Ultrasys.Instance().mPWDspPara.mPwAutoCalc.get();
//        paraSet.mPwLine = Ultrasys.Instance().mPWDspPara.mPwDispLine.get();
//        paraSet.mMGain = Ultrasys.Instance().mMDspPara.mGain.get();
//        paraSet.mMSpeed = Ultrasys.Instance().mMScanPara.mSpeed.get();
//        paraSet.mMDynamic = Ultrasys.Instance().mMDspPara.mDynamic.get();
//        paraSet.mMLine = Ultrasys.Instance().mMDspPara.mDispLine.get();
//        paraSet.mImgWidth = ImageDefine.IMAGE_WIDTH;
//        paraSet.mImgHeight = 480;
//        paraSet.image_shape = Ultrasys.Instance().mDscCtrl.GetImgShape();
//        paraSet.depth = Ultrasys.Instance().mFeUltrasysCtrl.mDepthvalue;
//        paraSet.scale = Ultrasys.Instance().mFeUltrasysCtrl.mZoomScale;
//        paraSet.angle = Ultrasys.Instance().mFeUltrasysCtrl.GetScanAngle();
//        paraSet.probe_range = Ultrasys.Instance().mFeUltrasysCtrl.GetScanRange();
//        paraSet.mUD = Ultrasys.Instance().mBDspPara.mBFlipVer.get();
//        paraSet.mLR = Ultrasys.Instance().mBDspPara.mBFlipHor.get();
//        paraSet.mCColorMap = Ultrasys.Instance().mCDspPara.mVelColorMap.get();
//        paraSet.mPowerColorMap = Ultrasys.Instance().mCDspPara.mPowerColorMap.get();
    }

    public void setFrameType(int i) {
        this.mFrameType = i;
    }

    public void setmFrameNum(int i) {
        this.mFrameNum = i;
    }

    public void SetTImgSize(int i) {
        this.mTImgSize = i;
    }

    public void SetSImgSize(int i) {
        this.mSImgSize = i;
    }

    public void writePara() {
        int intInsertToByteArray = DataUtil.intInsertToByteArray(this.mParaSet.mProbeId, this.ParaBuf, 0) + 0;
        int intInsertToByteArray2 = intInsertToByteArray + DataUtil.intInsertToByteArray(this.mParaSet.mDispMode, this.ParaBuf, intInsertToByteArray);
        int intInsertToByteArray3 = intInsertToByteArray2 + DataUtil.intInsertToByteArray(this.mParaSet.mLastDispMode, this.ParaBuf, intInsertToByteArray2);
        int intInsertToByteArray4 = intInsertToByteArray3 + DataUtil.intInsertToByteArray(this.mParaSet.mExamMode, this.ParaBuf, intInsertToByteArray3);
        int intInsertToByteArray5 = intInsertToByteArray4 + DataUtil.intInsertToByteArray(this.mParaSet.mPattern, this.ParaBuf, intInsertToByteArray4);
        int intInsertToByteArray6 = intInsertToByteArray5 + DataUtil.intInsertToByteArray(this.mParaSet.mFrameRate, this.ParaBuf, intInsertToByteArray5);
        int intInsertToByteArray7 = intInsertToByteArray6 + DataUtil.intInsertToByteArray(this.mParaSet.mDepth, this.ParaBuf, intInsertToByteArray6);
        int intInsertToByteArray8 = intInsertToByteArray7 + DataUtil.intInsertToByteArray(this.mParaSet.mBGain, this.ParaBuf, intInsertToByteArray7);
        int intInsertToByteArray9 = intInsertToByteArray8 + DataUtil.intInsertToByteArray(this.mParaSet.mBFreq, this.ParaBuf, intInsertToByteArray8);
        int intInsertToByteArray10 = intInsertToByteArray9 + DataUtil.intInsertToByteArray(this.mParaSet.mBFocus, this.ParaBuf, intInsertToByteArray9);
        int intInsertToByteArray11 = intInsertToByteArray10 + DataUtil.intInsertToByteArray(this.mParaSet.mBDynamic, this.ParaBuf, intInsertToByteArray10);
        int intInsertToByteArray12 = intInsertToByteArray11 + DataUtil.intInsertToByteArray(this.mParaSet.mBFrameCorre, this.ParaBuf, intInsertToByteArray11);
        int intInsertToByteArray13 = intInsertToByteArray12 + DataUtil.intInsertToByteArray(this.mParaSet.mBEnhance, this.ParaBuf, intInsertToByteArray12);
        int intInsertToByteArray14 = intInsertToByteArray13 + DataUtil.intInsertToByteArray(this.mParaSet.mAPower, this.ParaBuf, intInsertToByteArray13);
        int intInsertToByteArray15 = intInsertToByteArray14 + DataUtil.intInsertToByteArray(this.mParaSet.mTsi, this.ParaBuf, intInsertToByteArray14);
        int intInsertToByteArray16 = intInsertToByteArray15 + DataUtil.intInsertToByteArray(this.mParaSet.mFrameComp, this.ParaBuf, intInsertToByteArray15);
        int intInsertToByteArray17 = intInsertToByteArray16 + DataUtil.intInsertToByteArray(this.mParaSet.mBExpand, this.ParaBuf, intInsertToByteArray16);
        int intInsertToByteArray18 = intInsertToByteArray17 + DataUtil.intInsertToByteArray(this.mParaSet.mCScale, this.ParaBuf, intInsertToByteArray17);
        int intInsertToByteArray19 = intInsertToByteArray18 + DataUtil.intInsertToByteArray(this.mParaSet.mCGain, this.ParaBuf, intInsertToByteArray18);
        int intInsertToByteArray20 = intInsertToByteArray19 + DataUtil.intInsertToByteArray(this.mParaSet.mCFreq, this.ParaBuf, intInsertToByteArray19);
        int intInsertToByteArray21 = intInsertToByteArray20 + DataUtil.intInsertToByteArray(this.mParaSet.mCWallFilter, this.ParaBuf, intInsertToByteArray20);
        int intInsertToByteArray22 = intInsertToByteArray21 + DataUtil.intInsertToByteArray(this.mParaSet.mCPriority, this.ParaBuf, intInsertToByteArray21);
        int intInsertToByteArray23 = intInsertToByteArray22 + DataUtil.intInsertToByteArray(this.mParaSet.mCSteer, this.ParaBuf, intInsertToByteArray22);
        int intInsertToByteArray24 = intInsertToByteArray23 + DataUtil.intInsertToByteArray(this.mParaSet.mCFrameCorre, this.ParaBuf, intInsertToByteArray23);
        int intInsertToByteArray25 = intInsertToByteArray24 + DataUtil.intInsertToByteArray(this.mParaSet.mCStartLine, this.ParaBuf, intInsertToByteArray24);
        int intInsertToByteArray26 = intInsertToByteArray25 + DataUtil.intInsertToByteArray(this.mParaSet.mCEndLine, this.ParaBuf, intInsertToByteArray25);
        int intInsertToByteArray27 = intInsertToByteArray26 + DataUtil.intInsertToByteArray(this.mParaSet.mCStartDot, this.ParaBuf, intInsertToByteArray26);
        int intInsertToByteArray28 = intInsertToByteArray27 + DataUtil.intInsertToByteArray(this.mParaSet.mCEndDot, this.ParaBuf, intInsertToByteArray27);
        int intInsertToByteArray29 = intInsertToByteArray28 + DataUtil.intInsertToByteArray(this.mParaSet.mPWScale, this.ParaBuf, intInsertToByteArray28);
        int intInsertToByteArray30 = intInsertToByteArray29 + DataUtil.intInsertToByteArray(this.mParaSet.mPWFreq, this.ParaBuf, intInsertToByteArray29);
        int intInsertToByteArray31 = intInsertToByteArray30 + DataUtil.intInsertToByteArray(this.mParaSet.mPWGain, this.ParaBuf, intInsertToByteArray30);
        int intInsertToByteArray32 = intInsertToByteArray31 + DataUtil.intInsertToByteArray(this.mParaSet.mPWSteer, this.ParaBuf, intInsertToByteArray31);
        int intInsertToByteArray33 = intInsertToByteArray32 + DataUtil.intInsertToByteArray(this.mParaSet.mPWSv, this.ParaBuf, intInsertToByteArray32);
        int intInsertToByteArray34 = intInsertToByteArray33 + DataUtil.intInsertToByteArray(this.mParaSet.mPWSvD, this.ParaBuf, intInsertToByteArray33);
        int intInsertToByteArray35 = intInsertToByteArray34 + DataUtil.intInsertToByteArray(this.mParaSet.mPWBaseLine, this.ParaBuf, intInsertToByteArray34);
        int intInsertToByteArray36 = intInsertToByteArray35 + DataUtil.intInsertToByteArray(this.mParaSet.mPWSpeed, this.ParaBuf, intInsertToByteArray35);
        int intInsertToByteArray37 = intInsertToByteArray36 + DataUtil.intInsertToByteArray(this.mParaSet.mPWWallFilter, this.ParaBuf, intInsertToByteArray36);
        int intInsertToByteArray38 = intInsertToByteArray37 + DataUtil.intInsertToByteArray(this.mParaSet.mPWAngle, this.ParaBuf, intInsertToByteArray37);
        int intInsertToByteArray39 = intInsertToByteArray38 + DataUtil.intInsertToByteArray(this.mParaSet.mPwReverse, this.ParaBuf, intInsertToByteArray38);
        int intInsertToByteArray40 = intInsertToByteArray39 + DataUtil.intInsertToByteArray(this.mParaSet.mPwVolume, this.ParaBuf, intInsertToByteArray39);
        int intInsertToByteArray41 = intInsertToByteArray40 + DataUtil.intInsertToByteArray(this.mParaSet.mPwDynamic, this.ParaBuf, intInsertToByteArray40);
        int intInsertToByteArray42 = intInsertToByteArray41 + DataUtil.intInsertToByteArray(this.mParaSet.mPwAutoCalc, this.ParaBuf, intInsertToByteArray41);
        int intInsertToByteArray43 = intInsertToByteArray42 + DataUtil.intInsertToByteArray(this.mParaSet.mPwLine, this.ParaBuf, intInsertToByteArray42);
        int intInsertToByteArray44 = intInsertToByteArray43 + DataUtil.intInsertToByteArray(this.mParaSet.mMGain, this.ParaBuf, intInsertToByteArray43);
        int intInsertToByteArray45 = intInsertToByteArray44 + DataUtil.intInsertToByteArray(this.mParaSet.mMSpeed, this.ParaBuf, intInsertToByteArray44);
        int intInsertToByteArray46 = intInsertToByteArray45 + DataUtil.intInsertToByteArray(this.mParaSet.mMDynamic, this.ParaBuf, intInsertToByteArray45);
        int intInsertToByteArray47 = intInsertToByteArray46 + DataUtil.intInsertToByteArray(this.mParaSet.mMLine, this.ParaBuf, intInsertToByteArray46);
        int intInsertToByteArray48 = intInsertToByteArray47 + DataUtil.intInsertToByteArray(this.mParaSet.mImgWidth, this.ParaBuf, intInsertToByteArray47);
        int intInsertToByteArray49 = intInsertToByteArray48 + DataUtil.intInsertToByteArray(this.mParaSet.mImgHeight, this.ParaBuf, intInsertToByteArray48);
        int intInsertToByteArray50 = intInsertToByteArray49 + DataUtil.intInsertToByteArray(this.mParaSet.image_shape, this.ParaBuf, intInsertToByteArray49);
        int floatInsertToByteArray = intInsertToByteArray50 + DataUtil.floatInsertToByteArray(this.mParaSet.depth, this.ParaBuf, intInsertToByteArray50);
        int floatInsertToByteArray2 = floatInsertToByteArray + DataUtil.floatInsertToByteArray(this.mParaSet.scale, this.ParaBuf, floatInsertToByteArray);
        int floatInsertToByteArray3 = floatInsertToByteArray2 + DataUtil.floatInsertToByteArray(this.mParaSet.angle, this.ParaBuf, floatInsertToByteArray2);
        int floatInsertToByteArray4 = floatInsertToByteArray3 + DataUtil.floatInsertToByteArray(this.mParaSet.probe_range, this.ParaBuf, floatInsertToByteArray3);
        int intInsertToByteArray51 = floatInsertToByteArray4 + DataUtil.intInsertToByteArray(this.mParaSet.mUD, this.ParaBuf, floatInsertToByteArray4);
        int intInsertToByteArray52 = intInsertToByteArray51 + DataUtil.intInsertToByteArray(this.mParaSet.mLR, this.ParaBuf, intInsertToByteArray51);
        int intInsertToByteArray53 = intInsertToByteArray52 + DataUtil.intInsertToByteArray(this.mParaSet.mCColorMap, this.ParaBuf, intInsertToByteArray52);
        int intInsertToByteArray54 = intInsertToByteArray53 + DataUtil.intInsertToByteArray(this.mParaSet.mPowerColorMap, this.ParaBuf, intInsertToByteArray53);
        if (Ultrasys.Instance().GetDispMode() == 50) {
            int intInsertToByteArray55 = intInsertToByteArray54 + DataUtil.intInsertToByteArray(this.mParaSet1.mProbeId, this.ParaBuf, intInsertToByteArray54);
            int intInsertToByteArray56 = intInsertToByteArray55 + DataUtil.intInsertToByteArray(this.mParaSet1.mDispMode, this.ParaBuf, intInsertToByteArray55);
            int intInsertToByteArray57 = intInsertToByteArray56 + DataUtil.intInsertToByteArray(this.mParaSet1.mLastDispMode, this.ParaBuf, intInsertToByteArray56);
            int intInsertToByteArray58 = intInsertToByteArray57 + DataUtil.intInsertToByteArray(this.mParaSet1.mExamMode, this.ParaBuf, intInsertToByteArray57);
            int intInsertToByteArray59 = intInsertToByteArray58 + DataUtil.intInsertToByteArray(this.mParaSet1.mPattern, this.ParaBuf, intInsertToByteArray58);
            int intInsertToByteArray60 = intInsertToByteArray59 + DataUtil.intInsertToByteArray(this.mParaSet1.mFrameRate, this.ParaBuf, intInsertToByteArray59);
            int intInsertToByteArray61 = intInsertToByteArray60 + DataUtil.intInsertToByteArray(this.mParaSet1.mDepth, this.ParaBuf, intInsertToByteArray60);
            int intInsertToByteArray62 = intInsertToByteArray61 + DataUtil.intInsertToByteArray(this.mParaSet1.mBGain, this.ParaBuf, intInsertToByteArray61);
            int intInsertToByteArray63 = intInsertToByteArray62 + DataUtil.intInsertToByteArray(this.mParaSet1.mBFreq, this.ParaBuf, intInsertToByteArray62);
            int intInsertToByteArray64 = intInsertToByteArray63 + DataUtil.intInsertToByteArray(this.mParaSet1.mBFocus, this.ParaBuf, intInsertToByteArray63);
            int intInsertToByteArray65 = intInsertToByteArray64 + DataUtil.intInsertToByteArray(this.mParaSet1.mBDynamic, this.ParaBuf, intInsertToByteArray64);
            int intInsertToByteArray66 = intInsertToByteArray65 + DataUtil.intInsertToByteArray(this.mParaSet1.mBFrameCorre, this.ParaBuf, intInsertToByteArray65);
            int intInsertToByteArray67 = intInsertToByteArray66 + DataUtil.intInsertToByteArray(this.mParaSet1.mBEnhance, this.ParaBuf, intInsertToByteArray66);
            int intInsertToByteArray68 = intInsertToByteArray67 + DataUtil.intInsertToByteArray(this.mParaSet1.mAPower, this.ParaBuf, intInsertToByteArray67);
            int intInsertToByteArray69 = intInsertToByteArray68 + DataUtil.intInsertToByteArray(this.mParaSet1.mTsi, this.ParaBuf, intInsertToByteArray68);
            int intInsertToByteArray70 = intInsertToByteArray69 + DataUtil.intInsertToByteArray(this.mParaSet1.mFrameComp, this.ParaBuf, intInsertToByteArray69);
            int intInsertToByteArray71 = intInsertToByteArray70 + DataUtil.intInsertToByteArray(this.mParaSet1.mBExpand, this.ParaBuf, intInsertToByteArray70);
            int intInsertToByteArray72 = intInsertToByteArray71 + DataUtil.intInsertToByteArray(this.mParaSet1.mCScale, this.ParaBuf, intInsertToByteArray71);
            int intInsertToByteArray73 = intInsertToByteArray72 + DataUtil.intInsertToByteArray(this.mParaSet1.mCGain, this.ParaBuf, intInsertToByteArray72);
            int intInsertToByteArray74 = intInsertToByteArray73 + DataUtil.intInsertToByteArray(this.mParaSet1.mCFreq, this.ParaBuf, intInsertToByteArray73);
            int intInsertToByteArray75 = intInsertToByteArray74 + DataUtil.intInsertToByteArray(this.mParaSet1.mCWallFilter, this.ParaBuf, intInsertToByteArray74);
            int intInsertToByteArray76 = intInsertToByteArray75 + DataUtil.intInsertToByteArray(this.mParaSet1.mCPriority, this.ParaBuf, intInsertToByteArray75);
            int intInsertToByteArray77 = intInsertToByteArray76 + DataUtil.intInsertToByteArray(this.mParaSet1.mCSteer, this.ParaBuf, intInsertToByteArray76);
            int intInsertToByteArray78 = intInsertToByteArray77 + DataUtil.intInsertToByteArray(this.mParaSet1.mCFrameCorre, this.ParaBuf, intInsertToByteArray77);
            int intInsertToByteArray79 = intInsertToByteArray78 + DataUtil.intInsertToByteArray(this.mParaSet1.mCStartLine, this.ParaBuf, intInsertToByteArray78);
            int intInsertToByteArray80 = intInsertToByteArray79 + DataUtil.intInsertToByteArray(this.mParaSet1.mCEndLine, this.ParaBuf, intInsertToByteArray79);
            int intInsertToByteArray81 = intInsertToByteArray80 + DataUtil.intInsertToByteArray(this.mParaSet1.mCStartDot, this.ParaBuf, intInsertToByteArray80);
            int intInsertToByteArray82 = intInsertToByteArray81 + DataUtil.intInsertToByteArray(this.mParaSet1.mCEndDot, this.ParaBuf, intInsertToByteArray81);
            int intInsertToByteArray83 = intInsertToByteArray82 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWScale, this.ParaBuf, intInsertToByteArray82);
            int intInsertToByteArray84 = intInsertToByteArray83 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWFreq, this.ParaBuf, intInsertToByteArray83);
            int intInsertToByteArray85 = intInsertToByteArray84 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWGain, this.ParaBuf, intInsertToByteArray84);
            int intInsertToByteArray86 = intInsertToByteArray85 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWSteer, this.ParaBuf, intInsertToByteArray85);
            int intInsertToByteArray87 = intInsertToByteArray86 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWSv, this.ParaBuf, intInsertToByteArray86);
            int intInsertToByteArray88 = intInsertToByteArray87 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWSvD, this.ParaBuf, intInsertToByteArray87);
            int intInsertToByteArray89 = intInsertToByteArray88 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWBaseLine, this.ParaBuf, intInsertToByteArray88);
            int intInsertToByteArray90 = intInsertToByteArray89 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWSpeed, this.ParaBuf, intInsertToByteArray89);
            int intInsertToByteArray91 = intInsertToByteArray90 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWWallFilter, this.ParaBuf, intInsertToByteArray90);
            int intInsertToByteArray92 = intInsertToByteArray91 + DataUtil.intInsertToByteArray(this.mParaSet1.mPWAngle, this.ParaBuf, intInsertToByteArray91);
            int intInsertToByteArray93 = intInsertToByteArray92 + DataUtil.intInsertToByteArray(this.mParaSet1.mPwReverse, this.ParaBuf, intInsertToByteArray92);
            int intInsertToByteArray94 = intInsertToByteArray93 + DataUtil.intInsertToByteArray(this.mParaSet1.mPwVolume, this.ParaBuf, intInsertToByteArray93);
            int intInsertToByteArray95 = intInsertToByteArray94 + DataUtil.intInsertToByteArray(this.mParaSet1.mPwDynamic, this.ParaBuf, intInsertToByteArray94);
            int intInsertToByteArray96 = intInsertToByteArray95 + DataUtil.intInsertToByteArray(this.mParaSet1.mPwAutoCalc, this.ParaBuf, intInsertToByteArray95);
            int intInsertToByteArray97 = intInsertToByteArray96 + DataUtil.intInsertToByteArray(this.mParaSet1.mPwLine, this.ParaBuf, intInsertToByteArray96);
            int intInsertToByteArray98 = intInsertToByteArray97 + DataUtil.intInsertToByteArray(this.mParaSet1.mMGain, this.ParaBuf, intInsertToByteArray97);
            int intInsertToByteArray99 = intInsertToByteArray98 + DataUtil.intInsertToByteArray(this.mParaSet1.mMSpeed, this.ParaBuf, intInsertToByteArray98);
            int intInsertToByteArray100 = intInsertToByteArray99 + DataUtil.intInsertToByteArray(this.mParaSet1.mMDynamic, this.ParaBuf, intInsertToByteArray99);
            int intInsertToByteArray101 = intInsertToByteArray100 + DataUtil.intInsertToByteArray(this.mParaSet1.mMLine, this.ParaBuf, intInsertToByteArray100);
            int intInsertToByteArray102 = intInsertToByteArray101 + DataUtil.intInsertToByteArray(this.mParaSet1.mImgWidth, this.ParaBuf, intInsertToByteArray101);
            int intInsertToByteArray103 = intInsertToByteArray102 + DataUtil.intInsertToByteArray(this.mParaSet1.mImgHeight, this.ParaBuf, intInsertToByteArray102);
            int intInsertToByteArray104 = intInsertToByteArray103 + DataUtil.intInsertToByteArray(this.mParaSet1.image_shape, this.ParaBuf, intInsertToByteArray103);
            int floatInsertToByteArray5 = intInsertToByteArray104 + DataUtil.floatInsertToByteArray(this.mParaSet1.depth, this.ParaBuf, intInsertToByteArray104);
            int floatInsertToByteArray6 = floatInsertToByteArray5 + DataUtil.floatInsertToByteArray(this.mParaSet1.scale, this.ParaBuf, floatInsertToByteArray5);
            int floatInsertToByteArray7 = floatInsertToByteArray6 + DataUtil.floatInsertToByteArray(this.mParaSet1.angle, this.ParaBuf, floatInsertToByteArray6);
            int floatInsertToByteArray8 = floatInsertToByteArray7 + DataUtil.floatInsertToByteArray(this.mParaSet1.probe_range, this.ParaBuf, floatInsertToByteArray7);
            int intInsertToByteArray105 = floatInsertToByteArray8 + DataUtil.intInsertToByteArray(this.mParaSet1.mUD, this.ParaBuf, floatInsertToByteArray8);
            int intInsertToByteArray106 = intInsertToByteArray105 + DataUtil.intInsertToByteArray(this.mParaSet1.mLR, this.ParaBuf, intInsertToByteArray105);
            DataUtil.intInsertToByteArray(this.mParaSet1.mPowerColorMap, this.ParaBuf, intInsertToByteArray106 + DataUtil.intInsertToByteArray(this.mParaSet1.mCColorMap, this.ParaBuf, intInsertToByteArray106));
        }
    }

    public void writeHead() {
        int intInsertToByteArray = DataUtil.intInsertToByteArray(this.mFrameType, this.HeadBuf, 0) + 0;
        int intInsertToByteArray2 = intInsertToByteArray + DataUtil.intInsertToByteArray(this.mFrameNum, this.HeadBuf, intInsertToByteArray);
        int intInsertToByteArray3 = intInsertToByteArray2 + DataUtil.intInsertToByteArray(this.mSImgSize, this.HeadBuf, intInsertToByteArray2);
        DataUtil.intInsertToByteArray(this.mBmpSize, this.HeadBuf, intInsertToByteArray3 + DataUtil.intInsertToByteArray(this.mTImgSize, this.HeadBuf, intInsertToByteArray3));
    }

    public void writeDicom() {
//        HPatient curPatient = MyApplication.App().GetPatientMng().getCurPatient();
//        if (curPatient == null) {
//            curPatient = MyApplication.App().GetPatientMng().AddNewPatient();
//        }
//        int gender = curPatient.getGender();
//        if (curPatient.getGender() > 0) {
//            gender--;
//        }
//        curPatient.getBirthday();
//        HDicomIf.UpdateDicomInfoToStream(this.DicomBuf, curPatient.getId(), "", curPatient.getFirstName(), curPatient.getLastName(), curPatient.getMidName(), 0, curPatient.getAge(), curPatient.getAgeUnit(), gender, "", "", BuildConfig.VERSION_NAME, Ultrasys.Instance().getCurProbeName(), ExamTypesId.getExamStrByExamMode(Ultrasys.Instance().mCurExamMode));
    }

    public int loadFrame(byte[] bArr) throws UnsupportedEncodingException {
        int i = 0;
        this.mFrameType = DataUtil.bytesToInt(bArr, 0);
        this.mFrameNum = DataUtil.bytesToInt(bArr, 4);
        this.mSImgSize = DataUtil.bytesToInt(bArr, 8);
        this.mTImgSize = DataUtil.bytesToInt(bArr, 12);
        this.mBmpSize = DataUtil.bytesToInt(bArr, 16);
        int length = this.HeadBuf.length;
        this.mParaSet.mProbeId = DataUtil.bytesToInt(bArr, length);
        int i2 = length + 4;
        this.mParaSet.mDispMode = DataUtil.bytesToInt(bArr, i2);
        int i3 = i2 + 4;
        this.mParaSet.mLastDispMode = DataUtil.bytesToInt(bArr, i3);
        int i4 = i3 + 4;
        this.mParaSet.mExamMode = DataUtil.bytesToInt(bArr, i4);
        int i5 = i4 + 4;
        this.mParaSet.mPattern = DataUtil.bytesToInt(bArr, i5);
        int i6 = i5 + 4;
        this.mParaSet.mFrameRate = DataUtil.bytesToInt(bArr, i6);
        int i7 = i6 + 4;
        this.mParaSet.mDepth = DataUtil.bytesToInt(bArr, i7);
        int i8 = i7 + 4;
        this.mParaSet.mBGain = DataUtil.bytesToInt(bArr, i8);
        int i9 = i8 + 4;
        this.mParaSet.mBFreq = DataUtil.bytesToInt(bArr, i9);
        int i10 = i9 + 4;
        this.mParaSet.mBFocus = DataUtil.bytesToInt(bArr, i10);
        int i11 = i10 + 4;
        this.mParaSet.mBDynamic = DataUtil.bytesToInt(bArr, i11);
        int i12 = i11 + 4;
        this.mParaSet.mBFrameCorre = DataUtil.bytesToInt(bArr, i12);
        int i13 = i12 + 4;
        this.mParaSet.mBEnhance = DataUtil.bytesToInt(bArr, i13);
        int i14 = i13 + 4;
        this.mParaSet.mAPower = DataUtil.bytesToInt(bArr, i14);
        int i15 = i14 + 4;
        this.mParaSet.mTsi = DataUtil.bytesToInt(bArr, i15);
        int i16 = i15 + 4;
        this.mParaSet.mFrameComp = DataUtil.bytesToInt(bArr, i16);
        int i17 = i16 + 4;
        this.mParaSet.mBExpand = DataUtil.bytesToInt(bArr, i17);
        int i18 = i17 + 4;
        this.mParaSet.mCScale = DataUtil.bytesToInt(bArr, i18);
        int i19 = i18 + 4;
        this.mParaSet.mCGain = DataUtil.bytesToInt(bArr, i19);
        int i20 = i19 + 4;
        this.mParaSet.mCFreq = DataUtil.bytesToInt(bArr, i20);
        int i21 = i20 + 4;
        this.mParaSet.mCWallFilter = DataUtil.bytesToInt(bArr, i21);
        int i22 = i21 + 4;
        this.mParaSet.mCPriority = DataUtil.bytesToInt(bArr, i22);
        int i23 = i22 + 4;
        this.mParaSet.mCSteer = DataUtil.bytesToInt(bArr, i23);
        int i24 = i23 + 4;
        this.mParaSet.mCFrameCorre = DataUtil.bytesToInt(bArr, i24);
        int i25 = i24 + 4;
        this.mParaSet.mCStartLine = DataUtil.bytesToInt(bArr, i25);
        int i26 = i25 + 4;
        this.mParaSet.mCEndLine = DataUtil.bytesToInt(bArr, i26);
        int i27 = i26 + 4;
        this.mParaSet.mCStartDot = DataUtil.bytesToInt(bArr, i27);
        int i28 = i27 + 4;
        this.mParaSet.mCEndDot = DataUtil.bytesToInt(bArr, i28);
        int i29 = i28 + 4;
        this.mParaSet.mPWScale = DataUtil.bytesToInt(bArr, i29);
        int i30 = i29 + 4;
        this.mParaSet.mPWFreq = DataUtil.bytesToInt(bArr, i30);
        int i31 = i30 + 4;
        this.mParaSet.mPWGain = DataUtil.bytesToInt(bArr, i31);
        int i32 = i31 + 4;
        this.mParaSet.mPWSteer = DataUtil.bytesToInt(bArr, i32);
        int i33 = i32 + 4;
        this.mParaSet.mPWSv = DataUtil.bytesToInt(bArr, i33);
        int i34 = i33 + 4;
        this.mParaSet.mPWSvD = DataUtil.bytesToInt(bArr, i34);
        int i35 = i34 + 4;
        this.mParaSet.mPWBaseLine = DataUtil.bytesToInt(bArr, i35);
        int i36 = i35 + 4;
        this.mParaSet.mPWSpeed = DataUtil.bytesToInt(bArr, i36);
        int i37 = i36 + 4;
        this.mParaSet.mPWWallFilter = DataUtil.bytesToInt(bArr, i37);
        int i38 = i37 + 4;
        this.mParaSet.mPWAngle = DataUtil.bytesToInt(bArr, i38);
        int i39 = i38 + 4;
        this.mParaSet.mPwReverse = DataUtil.bytesToInt(bArr, i39);
        int i40 = i39 + 4;
        this.mParaSet.mPwVolume = DataUtil.bytesToInt(bArr, i40);
        int i41 = i40 + 4;
        this.mParaSet.mPwDynamic = DataUtil.bytesToInt(bArr, i41);
        int i42 = i41 + 4;
        this.mParaSet.mPwAutoCalc = DataUtil.bytesToInt(bArr, i42);
        int i43 = i42 + 4;
        this.mParaSet.mPwLine = DataUtil.bytesToInt(bArr, i43);
        int i44 = i43 + 4;
        this.mParaSet.mMGain = DataUtil.bytesToInt(bArr, i44);
        int i45 = i44 + 4;
        this.mParaSet.mMSpeed = DataUtil.bytesToInt(bArr, i45);
        int i46 = i45 + 4;
        this.mParaSet.mMDynamic = DataUtil.bytesToInt(bArr, i46);
        int i47 = i46 + 4;
        this.mParaSet.mMLine = DataUtil.bytesToInt(bArr, i47);
        int i48 = i47 + 4;
        this.mParaSet.mImgWidth = DataUtil.bytesToInt(bArr, i48);
        int i49 = i48 + 4;
        this.mParaSet.mImgHeight = DataUtil.bytesToInt(bArr, i49);
        int i50 = i49 + 4;
        this.mParaSet.image_shape = DataUtil.bytesToInt(bArr, i50);
        int i51 = i50 + 4;
        this.mParaSet.depth = DataUtil.bytesToFloat(bArr, i51);
        int i52 = i51 + 4;
        this.mParaSet.scale = DataUtil.bytesToFloat(bArr, i52);
        int i53 = i52 + 4;
        this.mParaSet.angle = DataUtil.bytesToFloat(bArr, i53);
        int i54 = i53 + 4;
        this.mParaSet.probe_range = DataUtil.bytesToFloat(bArr, i54);
        int i55 = i54 + 4;
        this.mParaSet.mUD = DataUtil.bytesToInt(bArr, i55);
        int i56 = i55 + 4;
        this.mParaSet.mLR = DataUtil.bytesToInt(bArr, i56);
        int i57 = i56 + 4;
        this.mParaSet.mCColorMap = DataUtil.bytesToInt(bArr, i57);
        int i58 = i57 + 4;
        this.mParaSet.mPowerColorMap = DataUtil.bytesToInt(bArr, i58);
        int i59 = i58 + 4;
        if (this.mParaSet.mDispMode == 50) {
            this.mParaSet1 = new ParaSet();
            this.mParaSet1.mProbeId = DataUtil.bytesToInt(bArr, i59);
            int i60 = i59 + 4;
            this.mParaSet1.mDispMode = DataUtil.bytesToInt(bArr, i60);
            int i61 = i60 + 4;
            this.mParaSet1.mLastDispMode = DataUtil.bytesToInt(bArr, i61);
            int i62 = i61 + 4;
            this.mParaSet1.mExamMode = DataUtil.bytesToInt(bArr, i62);
            int i63 = i62 + 4;
            this.mParaSet1.mPattern = DataUtil.bytesToInt(bArr, i63);
            int i64 = i63 + 4;
            this.mParaSet1.mFrameRate = DataUtil.bytesToInt(bArr, i64);
            int i65 = i64 + 4;
            this.mParaSet1.mDepth = DataUtil.bytesToInt(bArr, i65);
            int i66 = i65 + 4;
            this.mParaSet1.mBGain = DataUtil.bytesToInt(bArr, i66);
            int i67 = i66 + 4;
            this.mParaSet1.mBFreq = DataUtil.bytesToInt(bArr, i67);
            int i68 = i67 + 4;
            this.mParaSet1.mBFocus = DataUtil.bytesToInt(bArr, i68);
            int i69 = i68 + 4;
            this.mParaSet1.mBDynamic = DataUtil.bytesToInt(bArr, i69);
            int i70 = i69 + 4;
            this.mParaSet1.mBFrameCorre = DataUtil.bytesToInt(bArr, i70);
            int i71 = i70 + 4;
            this.mParaSet1.mBEnhance = DataUtil.bytesToInt(bArr, i71);
            int i72 = i71 + 4;
            this.mParaSet1.mAPower = DataUtil.bytesToInt(bArr, i72);
            int i73 = i72 + 4;
            this.mParaSet1.mTsi = DataUtil.bytesToInt(bArr, i73);
            int i74 = i73 + 4;
            this.mParaSet1.mFrameComp = DataUtil.bytesToInt(bArr, i74);
            int i75 = i74 + 4;
            this.mParaSet1.mBExpand = DataUtil.bytesToInt(bArr, i75);
            int i76 = i75 + 4;
            this.mParaSet1.mCScale = DataUtil.bytesToInt(bArr, i76);
            int i77 = i76 + 4;
            this.mParaSet1.mCGain = DataUtil.bytesToInt(bArr, i77);
            int i78 = i77 + 4;
            this.mParaSet1.mCFreq = DataUtil.bytesToInt(bArr, i78);
            int i79 = i78 + 4;
            this.mParaSet1.mCWallFilter = DataUtil.bytesToInt(bArr, i79);
            int i80 = i79 + 4;
            this.mParaSet1.mCPriority = DataUtil.bytesToInt(bArr, i80);
            int i81 = i80 + 4;
            this.mParaSet1.mCSteer = DataUtil.bytesToInt(bArr, i81);
            int i82 = i81 + 4;
            this.mParaSet1.mCFrameCorre = DataUtil.bytesToInt(bArr, i82);
            int i83 = i82 + 4;
            this.mParaSet1.mCStartLine = DataUtil.bytesToInt(bArr, i83);
            int i84 = i83 + 4;
            this.mParaSet1.mCEndLine = DataUtil.bytesToInt(bArr, i84);
            int i85 = i84 + 4;
            this.mParaSet1.mCStartDot = DataUtil.bytesToInt(bArr, i85);
            int i86 = i85 + 4;
            this.mParaSet1.mCEndDot = DataUtil.bytesToInt(bArr, i86);
            int i87 = i86 + 4;
            this.mParaSet1.mPWScale = DataUtil.bytesToInt(bArr, i87);
            int i88 = i87 + 4;
            this.mParaSet1.mPWFreq = DataUtil.bytesToInt(bArr, i88);
            int i89 = i88 + 4;
            this.mParaSet1.mPWGain = DataUtil.bytesToInt(bArr, i89);
            int i90 = i89 + 4;
            this.mParaSet1.mPWSteer = DataUtil.bytesToInt(bArr, i90);
            int i91 = i90 + 4;
            this.mParaSet1.mPWSv = DataUtil.bytesToInt(bArr, i91);
            int i92 = i91 + 4;
            this.mParaSet1.mPWSvD = DataUtil.bytesToInt(bArr, i92);
            int i93 = i92 + 4;
            this.mParaSet1.mPWBaseLine = DataUtil.bytesToInt(bArr, i93);
            int i94 = i93 + 4;
            this.mParaSet1.mPWSpeed = DataUtil.bytesToInt(bArr, i94);
            int i95 = i94 + 4;
            this.mParaSet1.mPWWallFilter = DataUtil.bytesToInt(bArr, i95);
            int i96 = i95 + 4;
            this.mParaSet1.mPWAngle = DataUtil.bytesToInt(bArr, i96);
            int i97 = i96 + 4;
            this.mParaSet1.mPwReverse = DataUtil.bytesToInt(bArr, i97);
            int i98 = i97 + 4;
            this.mParaSet1.mPwVolume = DataUtil.bytesToInt(bArr, i98);
            int i99 = i98 + 4;
            this.mParaSet1.mPwDynamic = DataUtil.bytesToInt(bArr, i99);
            int i100 = i99 + 4;
            this.mParaSet1.mPwAutoCalc = DataUtil.bytesToInt(bArr, i100);
            int i101 = i100 + 4;
            this.mParaSet1.mPwLine = DataUtil.bytesToInt(bArr, i101);
            int i102 = i101 + 4;
            this.mParaSet1.mMGain = DataUtil.bytesToInt(bArr, i102);
            int i103 = i102 + 4;
            this.mParaSet1.mMSpeed = DataUtil.bytesToInt(bArr, i103);
            int i104 = i103 + 4;
            this.mParaSet1.mMDynamic = DataUtil.bytesToInt(bArr, i104);
            int i105 = i104 + 4;
            this.mParaSet1.mMLine = DataUtil.bytesToInt(bArr, i105);
            int i106 = i105 + 4;
            this.mParaSet1.mImgWidth = DataUtil.bytesToInt(bArr, i106);
            int i107 = i106 + 4;
            this.mParaSet1.mImgHeight = DataUtil.bytesToInt(bArr, i107);
            int i108 = i107 + 4;
            this.mParaSet1.image_shape = DataUtil.bytesToInt(bArr, i108);
            int i109 = i108 + 4;
            this.mParaSet1.depth = DataUtil.bytesToFloat(bArr, i109);
            int i110 = i109 + 4;
            this.mParaSet1.scale = DataUtil.bytesToFloat(bArr, i110);
            int i111 = i110 + 4;
            this.mParaSet1.angle = DataUtil.bytesToFloat(bArr, i111);
            int i112 = i111 + 4;
            this.mParaSet1.probe_range = DataUtil.bytesToFloat(bArr, i112);
            int i113 = i112 + 4;
            this.mParaSet1.mUD = DataUtil.bytesToInt(bArr, i113);
            int i114 = i113 + 4;
            this.mParaSet1.mLR = DataUtil.bytesToInt(bArr, i114);
            int i115 = i114 + 4;
            this.mParaSet1.mCColorMap = DataUtil.bytesToInt(bArr, i115);
            this.mParaSet1.mPowerColorMap = DataUtil.bytesToInt(bArr, i115 + 4);
        }
        System.arraycopy(bArr, this.HeadBuf.length + this.ParaBuf.length, this.DicomBuf, 0, this.DicomBuf.length);
        int length2 = this.HeadBuf.length + this.ParaBuf.length + this.DicomBuf.length;
        this.mBdyMrkSize = DataUtil.bytesToInt(bArr, length2);
        int i116 = length2 + 4;
        this.mCommSize = DataUtil.bytesToInt(bArr, i116);
        this.mMeasSize = DataUtil.bytesToInt(bArr, i116 + 4);
        int length3 = this.HeadBuf.length + this.ParaBuf.length + this.DicomBuf.length + this.ReserveBuf.length + this.mSImgSize + this.mTImgSize + this.mBmpSize;
        if (this.mBdyMrkSize > 0) {
            this.mBdymrkData = new bodymark();
            this.mBdymrkData.LoadBuf(bArr, length3);
        }
        int length4 = this.HeadBuf.length + this.ParaBuf.length + this.DicomBuf.length + this.ReserveBuf.length + this.mSImgSize + this.mTImgSize + this.mBmpSize + this.mBdyMrkSize;
        if (this.mCommSize > 0) {
            int bytesToInt = DataUtil.bytesToInt(bArr, length4);
            int i117 = length4 + 4;
            for (int i118 = 0; i118 < bytesToInt; i118++) {
                comment commentVar = new comment();
                i117 += commentVar.LoadBuf(bArr, i117);
                this.mCommentDataList.add(commentVar);
            }
        }
        int length5 = this.HeadBuf.length + this.ParaBuf.length + this.DicomBuf.length + this.ReserveBuf.length + this.mSImgSize + this.mTImgSize + this.mBmpSize + this.mBdyMrkSize + this.mCommSize;
        while (this.mMeasSize - i > 0) {
            measwidget measwidgetVar = new measwidget();
            int LoadBuf = measwidgetVar.LoadBuf(bArr, length5);
            i += LoadBuf;
            length5 += LoadBuf;
            this.mMeaswidgets.add(measwidgetVar);
        }
        return this.HeadBuf.length + this.ParaBuf.length + this.DicomBuf.length + this.ReserveBuf.length;
    }

    public void writeOthers() {
//        BodyMarkView bodyMarkView = MyApplication.App().mMainActivity.mImageDisplayFragment.mBodyMarkView;
//        if (bodyMarkView.HasBodyMark()) {
//            this.mBdymrkData = new bodymark();
//            this.mBdymrkData.exam_type = bodyMarkView.curExamMode;
//            this.mBdymrkData.sel_pos = bodyMarkView.curSelPos;
//            this.mBdymrkData.name = bodyMarkView.GetCurBodyMarkName();
//            this.mBdymrkData.pos_x = bodyMarkView.GetCurBodyMarkPosX();
//            this.mBdymrkData.pos_y = bodyMarkView.GetCurBodyMarkPosY();
//            this.mBdymrkData.probe_x = bodyMarkView.GetCurProbeX();
//            this.mBdymrkData.probe_y = bodyMarkView.GetCurProbeY();
//            this.mBdymrkData.probe_angle = bodyMarkView.GetCurProbeAngle();
//            this.mBdymrkData.UpateBuf();
//            this.mBdymrkData.getClass();
//            this.mBdyMrkSize = 384;
//        }
//        this.mCommentDataList.clear();
//        CommentView commentView = MyApplication.App().mMainActivity.mImageDisplayFragment.mCommentView;
//        if (commentView.HasComment()) {
//            this.mCommSize += 4;
//            for (int i = 0; i < commentView.mCommentList.size(); i++) {
//                Comment comment2 = commentView.mCommentList.get(i);
//                if (comment2 != null) {
//                    comment commentVar = new comment();
//                    commentVar.comment_type = 0;
//                    commentVar.content = comment2.getText();
//                    commentVar.pos_x = comment2.getX() / commentView.getWidth();
//                    commentVar.pos_y = comment2.getY() / commentView.getHeight();
//                    commentVar.angle = 0.0f;
//                    commentVar.UpateBuf();
//                    int i2 = this.mCommSize;
//                    commentVar.getClass();
//                    this.mCommSize = i2 + 320;
//                    this.mCommentDataList.add(commentVar);
//                }
//            }
//        }
//        if (commentView.HasArrow()) {
//            for (int i3 = 0; i3 < commentView.mArrowList.size(); i3++) {
//                Arrow arrow = commentView.mArrowList.get(i3);
//                if (arrow != null) {
//                    comment commentVar2 = new comment();
//                    commentVar2.comment_type = 1;
//                    commentVar2.pos_x = arrow.getX() / commentView.getWidth();
//                    commentVar2.pos_y = arrow.getY() / commentView.getWidth();
//                    commentVar2.angle = arrow.getAngle();
//                    commentVar2.UpateBuf();
//                    int i4 = this.mCommSize;
//                    commentVar2.getClass();
//                    this.mCommSize = i4 + 320;
//                    this.mCommentDataList.add(commentVar2);
//                }
//            }
//        }
//        for (MeasWidgetLayout measWidgetLayout : MyApplication.App().mMainActivity.mImageDisplayFragment.getMeasLaouts()) {
//            measwidget measwidgetVar = new measwidget();
//            measwidgetVar.meas_type = measWidgetLayout.getWidgetType();
//            measwidgetVar.meas_item = measWidgetLayout.getMeasId();
//            measwidgetVar.positions = measWidgetLayout.getPostions();
//            measwidgetVar.UpateBuf();
//            this.mMeasSize += measwidgetVar.buf_size;
//            this.mMeaswidgets.add(measwidgetVar);
//        }
        int intInsertToByteArray = 0 + DataUtil.intInsertToByteArray(this.mBdyMrkSize, this.ReserveBuf, 0);
        DataUtil.intInsertToByteArray(this.mMeasSize, this.ReserveBuf, intInsertToByteArray + DataUtil.intInsertToByteArray(this.mCommSize, this.ReserveBuf, intInsertToByteArray));
    }

    public void FrameNumInc() {
        this.mFrameNum++;
    }

    public byte[] GetHeadBuf() {
        return this.HeadBuf;
    }

    public byte[] GetParaBuf() {
        return this.ParaBuf;
    }

    public byte[] GetOtherBuf() {
        return this.ReserveBuf;
    }

    /* loaded from: classes2.dex */
    public class bodymark {
        public final int BODYMARK_DATA_LEN = 384;
        public byte[] buf = new byte[384];
        public int exam_type;
        public String name;
        public float pos_x;
        public float pos_y;
        public float probe_angle;
        public float probe_x;
        public float probe_y;
        public int sel_pos;

        public bodymark() {
        }

        public int writebuf(byte[] bArr, int i) {
            int intInsertToByteArray = i + DataUtil.intInsertToByteArray(this.exam_type, bArr, i);
            byte[] strToByteArray = Tools.strToByteArray(this.name);
            if (strToByteArray != null) {
                System.arraycopy(strToByteArray, 0, bArr, intInsertToByteArray, strToByteArray.length);
            }
            int i2 = intInsertToByteArray + 256;
            int intInsertToByteArray2 = i2 + DataUtil.intInsertToByteArray(this.sel_pos, bArr, i2);
            int floatInsertToByteArray = intInsertToByteArray2 + DataUtil.floatInsertToByteArray(this.pos_x, bArr, intInsertToByteArray2);
            int floatInsertToByteArray2 = floatInsertToByteArray + DataUtil.floatInsertToByteArray(this.pos_y, bArr, floatInsertToByteArray);
            int floatInsertToByteArray3 = floatInsertToByteArray2 + DataUtil.floatInsertToByteArray(this.probe_x, bArr, floatInsertToByteArray2);
            int floatInsertToByteArray4 = floatInsertToByteArray3 + DataUtil.floatInsertToByteArray(this.probe_y, bArr, floatInsertToByteArray3);
            return floatInsertToByteArray4 + DataUtil.floatInsertToByteArray(this.probe_angle, bArr, floatInsertToByteArray4);
        }

        public void UpateBuf() {
            writebuf(this.buf, 0);
        }

        public int LoadBuf(byte[] bArr, int i) throws UnsupportedEncodingException {
            this.exam_type = DataUtil.bytesToInt(bArr, i);
            int i2 = i + 4;
            this.name = Tools.byteToStr_1(bArr, i2, 256);
            int i3 = i2 + 256;
            this.sel_pos = DataUtil.bytesToInt(bArr, i3);
            int i4 = i3 + 4;
            this.pos_x = DataUtil.bytesToFloat(bArr, i4);
            int i5 = i4 + 4;
            this.pos_y = DataUtil.bytesToFloat(bArr, i5);
            int i6 = i5 + 4;
            this.probe_x = DataUtil.bytesToFloat(bArr, i6);
            int i7 = i6 + 4;
            this.probe_y = DataUtil.bytesToFloat(bArr, i7);
            this.probe_angle = DataUtil.bytesToFloat(bArr, i7 + 4);
            return 384;
        }

        public String GetName() {
            return this.name;
        }
    }

    /* loaded from: classes2.dex */
    public class comment {
        public static final int ARROW_TYPE = 1;
        public static final int STRING_TYPE = 0;
        public float angle;
        public int comment_type;
        String content;
        public float pos_x;
        public float pos_y;
        public final int COMMENT_DATA_LEN = 320;
        public byte[] buf = new byte[320];

        public comment() {
        }

        public int writebuf(byte[] bArr, int i) {
            int intInsertToByteArray = i + DataUtil.intInsertToByteArray(this.comment_type, bArr, i);
            if (this.comment_type == 0) {
                byte[] strToByteArray = Tools.strToByteArray(this.content);
                System.arraycopy(strToByteArray, 0, bArr, intInsertToByteArray, strToByteArray.length);
            }
            int i2 = intInsertToByteArray + 256;
            int floatInsertToByteArray = i2 + DataUtil.floatInsertToByteArray(this.pos_x, bArr, i2);
            int floatInsertToByteArray2 = floatInsertToByteArray + DataUtil.floatInsertToByteArray(this.pos_y, bArr, floatInsertToByteArray);
            return floatInsertToByteArray2 + DataUtil.floatInsertToByteArray(this.angle, bArr, floatInsertToByteArray2);
        }

        public void UpateBuf() {
            writebuf(this.buf, 0);
        }

        public int LoadBuf(byte[] bArr, int i) throws UnsupportedEncodingException {
            System.arraycopy(bArr, i, new byte[320], 0, 320);
            this.comment_type = DataUtil.bytesToInt(bArr, i);
            int i2 = i + 4;
            if (this.comment_type == 0) {
                this.content = Tools.byteToStr_1(bArr, i2, 256);
            }
            int i3 = i2 + 256;
            this.pos_x = DataUtil.bytesToFloat(bArr, i3);
            int i4 = i3 + 4;
            this.pos_y = DataUtil.bytesToFloat(bArr, i4);
            this.angle = DataUtil.bytesToFloat(bArr, i4 + 4);
            return 320;
        }

        public String GetConent() {
            return this.content;
        }
    }

    /* loaded from: classes2.dex */
    public class measwidget {
        public int meas_item;
        public int meas_type;
        public List<MeasureBase.MeasPosition> positions;
        int buf_size = 0;
        public byte[] buf = null;

        public measwidget() {
        }

        public int writebuf(byte[] bArr, int i) {
            int intInsertToByteArray = i + DataUtil.intInsertToByteArray(this.meas_item, bArr, i);
            int intInsertToByteArray2 = intInsertToByteArray + DataUtil.intInsertToByteArray(this.meas_type, bArr, intInsertToByteArray);
            int intInsertToByteArray3 = intInsertToByteArray2 + DataUtil.intInsertToByteArray(this.positions.size(), bArr, intInsertToByteArray2);
            for (MeasureBase.MeasPosition measPosition : this.positions) {
                int floatInsertToByteArray = intInsertToByteArray3 + DataUtil.floatInsertToByteArray(measPosition.mx, bArr, intInsertToByteArray3);
                intInsertToByteArray3 = floatInsertToByteArray + DataUtil.floatInsertToByteArray(measPosition.my, bArr, floatInsertToByteArray);
            }
            return intInsertToByteArray3;
        }

        public void UpateBuf() {
            this.buf_size = (this.positions.size() * 8) + 12;
            this.buf = new byte[this.buf_size];
            writebuf(this.buf, 0);
        }

        public int LoadBuf(byte[] bArr, int i) {
            this.meas_item = DataUtil.bytesToInt(bArr, i);
            int i2 = i + 4;
            this.meas_type = DataUtil.bytesToInt(bArr, i2);
            int i3 = i2 + 4;
            int bytesToInt = DataUtil.bytesToInt(bArr, i3);
            int i4 = i3 + 4;
            this.positions = new ArrayList();
            this.positions.clear();
            for (int i5 = 0; i5 < bytesToInt; i5++) {
                MeasureBase.MeasPosition measPosition = new MeasureBase.MeasPosition();
                measPosition.mx = DataUtil.bytesToFloat(bArr, i4);
                int i6 = i4 + 4;
                measPosition.my = DataUtil.bytesToFloat(bArr, i6);
                i4 = i6 + 4;
                this.positions.add(measPosition);
            }
            return i4 - i;
        }
    }
}
