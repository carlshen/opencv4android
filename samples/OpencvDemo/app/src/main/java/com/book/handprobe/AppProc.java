package com.book.handprobe;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.media.Image;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.FragmentManager;

import com.book.tools.FileDefinition;
import com.book.tools.HObserver;
import com.unnamed.b.atv.model.TreeNode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import gloomyfish.opencvdemo.MyApplication;
import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class AppProc {
    static AppProc Inst;
    public String mFilename;
    public BaseActivity mMyMainActivity;
    public int[] mUiBitmapBuf;
    private boolean mSavingImg = false;
    private Bitmap mCachBitmap = null;
    IntBuffer mIntBuffer = IntBuffer.allocate(491520);
    IntBuffer mZoomIntBuffer = IntBuffer.allocate(76800);
    IntBuffer mZoomTIntBuffer = IntBuffer.allocate(491520);
    String mPat_Id = "";
    private SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMdd-HH_mm_ss", Locale.getDefault());
//    public UsbProbeConnectDlgFrag mUsbProbeConnectDlgFrag = UsbProbeConnectDlgFrag.newInstance(1);
//    public PresetDialogFrag mPresetDlg = PresetDialogFrag.newInstance(1);
//    public AutoSpecCalcDlgFragment mAutoSpecCalDlg = AutoSpecCalcDlgFragment.newInstance(1);
//    public PatientInfoFragment mPatientInfoFrg = PatientInfoFragment.newInstance();
//    public ThumbnailExportFragment mThumbnailExportFrg = ThumbnailExportFragment.newInstance();
//    public AnimalInfoFragment mAnimalInfoFrg = AnimalInfoFragment.newInstance();
//    public ExamlistFragment mExamListFrg = ExamlistFragment.newInstance();
//    public WorklistFragment mWorkListFrg = WorklistFragment.newInstance();
//    public AnimalExamlistFragment mAnimalExamListFrg = AnimalExamlistFragment.newInstance();
//    public ProbeSelectDialogFragment mWifiProbeSelect = ProbeSelectDialogFragment.newInstance(1);
//    TestTemperatureDlg testTemperatureDlg = TestTemperatureDlg.newInstance();

    public void HositalSet() {
    }

    public static AppProc Instance() {
        if (Inst == null) {
            synchronized (AppProc.class) {
                if (Inst == null) {
                    Inst = new AppProc();
                }
            }
        }
        return Inst;
    }

    public void DisMissFrag() {
//        if (this.mUsbProbeConnectDlgFrag.isAdded()) {
//            this.mUsbProbeConnectDlgFrag.dismiss();
//        }
//        if (this.mPresetDlg.isAdded()) {
//            this.mPresetDlg.dismiss();
//        }
//        if (this.mWifiProbeSelect.isAdded()) {
//            this.mWifiProbeSelect.dismiss();
//        }
    }

    public static Bitmap mergeBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap.isRecycled() || bitmap2 == null || bitmap2.isRecycled()) {
            return null;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        new Canvas(copy).drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), (Paint) null);
        return copy;
    }

    public static Bitmap mergeBitmap(int i, int i2, Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap.isRecycled() || bitmap2 == null || bitmap2.isRecycled()) {
            return null;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        new Canvas(copy).drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), new Rect(i, i2, bitmap2.getWidth() + i, bitmap2.getHeight() + i2), (Paint) null);
        return copy;
    }

    public void SetActivity(BaseActivity baseActivity) {
        this.mMyMainActivity = baseActivity;
//        BiopsyHelper.Instance().Init(baseActivity);
//        ThumbnailHelper.Instance().Init(baseActivity);
//        MeasMenuHelper.Instance().Init(baseActivity);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:66:0x015f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void FuncIdProc(int i) {
        switch (i) {
            case 15:
//                this.mMyMainActivity.mImageDisplayFragment.mTgcView.OpenTGCView(true);
                return;
            case 16:
//                BiopsyHelper.Instance().switchBiopsy();
                return;
            default:
                switch (i) {
                    case 41:
                        this.mMyMainActivity.ChangeImgMode(0);
                        return;
                    case 42:
                        this.mMyMainActivity.ChangeImgMode(8);
                        return;
                    case 43:
                        this.mMyMainActivity.ChangeImgMode(55);
                        return;
                    case 44:
                        this.mMyMainActivity.ChangeImgMode(5);
                        return;
                    case 45:
                        this.mMyMainActivity.ChangeImgMode(1);
                        return;
                    default:
                        switch (i) {
                            case 151:
                                if (Ultrasys.Instance().mIsUnFreeState) {
                                    Ultrasys.Instance().Freeze();
                                }
//                                if (this.mMyMainActivity.getClass() == LianmedActivity.class) {
//                                    ((LianmedActivity) this.mMyMainActivity).mProtocal.SendMessage(0, null);
//                                    this.mMyMainActivity.moveTaskToBack(true);
//                                    return;
//                                } else if (!MeasMenuHelper.Instance().mIsMeasMenuShow) {
//                                    this.mMyMainActivity.EnterMeas();
//                                    MeasMenuHelper.Instance().enterMeasMenu();
//                                    return;
//                                } else {
//                                    MeasMenuHelper.Instance().exitMeasMenu();
//                                    return;
//                                }
                            case 152:
                                break;
                            case 153:
                                PresetEnter();
                                return;
                            default:
                                switch (i) {
                                    case 156:
                                        ProberEnter(false);
                                        return;
                                    case 157:
                                        try {
//                                            if (this.mWifiProbeSelect.isAdded()) {
//                                                return;
//                                            }
//                                            SaveImg();
                                            return;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            return;
                                        }
                                    case 158:
                                        try {
                                            SaveCine();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                        if (!this.mMyMainActivity.getSharedPreferences("save_train", 0).getBoolean("save_train_switch", false)) {
                                            return;
                                        }
//                                        FileManipulation.saveFrameData(this.mMyMainActivity);
                                        return;
                                    case 159:
//                                        OpenPatientInfo();
                                        return;
                                    case 160:
//                                        OpenExamList();
                                        return;
                                    case 161:
//                                        FinishExam();
                                        return;
                                    case 162:
                                        HositalSet();
                                        return;
                                    case 163:
//                                        OpenReview();
                                        return;
                                    default:
                                        switch (i) {
                                            case 165:
                                                if (Ultrasys.Instance().mIsUnFreeState) {
                                                    Ultrasys.Instance().Freeze();
                                                }
                                                BodyMarkEnter();
                                                return;
                                            case 166:
                                                if (Ultrasys.Instance().GetDispMode() == 0) {
                                                    return;
                                                }
//                                                GL10ImageView gL10ImageView = this.mMyMainActivity.mImageDisplayFragment.mGLImageView;
//                                                if (!gL10ImageView.ZoomStatus()) {
//                                                    this.mMyMainActivity.mImageDisplayFragment.mGLImageView.ChangeZoomWin(-1.0f);
//                                                    return;
//                                                } else {
//                                                    gL10ImageView.ExitZoom();
//                                                    return;
//                                                }
                                            case 167:
                                                break;
                                            default:
                                                switch (i) {
                                                    case 171:
                                                        if (Ultrasys.Instance().GetDispMode() != 0 && Ultrasys.Instance().GetDispMode() != 50) {
                                                            return;
                                                        }
                                                        if (Ultrasys.Instance().GetDispMode() != 50) {
                                                            Ultrasys.Instance().SetDispMode(50);
                                                            return;
                                                        } else {
//                                                            this.mMyMainActivity.mImageDisplayFragment.ChangeActImgIndex();
                                                            return;
                                                        }
                                                    case 172:
                                                        Ultrasys.Instance().SendCmdToProbe(82, 1, null, 172);
                                                        return;
                                                    default:
                                                        switch (i) {
                                                            case 181:
                                                                Ultrasys.Instance().SendCmdToProbe(2, 1, new byte[]{1}, 1);
                                                                return;
                                                            case 182:
                                                                Ultrasys.Instance().SendCmdToProbe(2, 1, new byte[]{0}, 1);
                                                                return;
                                                            default:
                                                                switch (i) {
                                                                    case 204:
//                                                                        ThumbnailHelper.Instance().switchThumbnail();
                                                                        return;
                                                                    case 205:
//                                                                        if (this.mMyMainActivity.getClass() == LianmedActivity.class) {
//                                                                            this.mMyMainActivity.moveTaskToBack(true);
//                                                                        } else {
//                                                                            AppUtil.exitApp(0);
//                                                                        }
//                                                                        ((LianmedActivity) this.mMyMainActivity).mProtocal.SendMessage(4, null);
//                                                                        if (Ultrasys.Instance().GetDispMode() == 0) {
//                                                                        }
//                                                                        break;
//                                                                    case 206:
//                                                                        OpenMenu();
                                                                        return;
                                                                    default:
                                                                        switch (i) {
                                                                            case 210:
                                                                                if (Ultrasys.Instance().GetDispMode() == 0) {
                                                                                    this.mMyMainActivity.ChangeImgMode(1);
                                                                                    return;
                                                                                } else {
                                                                                    this.mMyMainActivity.ChangeImgMode(0);
                                                                                    return;
                                                                                }
                                                                            case 211:
                                                                                if (Ultrasys.Instance().GetDispMode() == 0 || Ultrasys.Instance().GetDispMode() == 1 || Ultrasys.Instance().GetDispMode() == 8 || Ultrasys.Instance().GetDispMode() == 55) {
                                                                                    this.mMyMainActivity.ChangeImgMode(5);
                                                                                    return;
                                                                                } else {
                                                                                    Ultrasys.Instance().SendCmdToProbe(82, 1, null, 172);
                                                                                    return;
                                                                                }
                                                                            case 212:
                                                                                if (Ultrasys.Instance().GetDispMode() == 8) {
                                                                                    this.mMyMainActivity.ChangeImgMode(55);
                                                                                    return;
                                                                                } else {
                                                                                    this.mMyMainActivity.ChangeImgMode(8);
                                                                                    return;
                                                                                }
                                                                            case 213:
//                                                                                OpenWorkList();
                                                                                return;
                                                                            default:
                                                                                switch (i) {
                                                                                    case 1:
                                                                                        Ultrasys.Instance().SendCmdToProbe(2, 1, new byte[]{-1}, 1);
                                                                                        return;
                                                                                    case 19:
//                                                                                        if (!this.mMyMainActivity.mImageDisplayFragment.mTgcView.mbTgcViewOpen) {
//                                                                                            this.mMyMainActivity.mImageDisplayFragment.mTgcView.OpenTGCView(true);
//                                                                                            return;
//                                                                                        } else {
//                                                                                            this.mMyMainActivity.mImageDisplayFragment.mTgcView.OpenTGCView(false);
//                                                                                            return;
//                                                                                        }
                                                                                    case 82:
                                                                                    default:
                                                                                        return;
                                                                                    case 95:
                                                                                        AutoCalcParamDlgEnter();
                                                                                        return;
                                                                                    case 202:
//                                                                                        OpenAnimalInfo();
                                                                                        return;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                        if (Ultrasys.Instance().mIsUnFreeState) {
                            Ultrasys.Instance().Freeze();
                        }
                        CommentEnter();
                        return;
                }
        }
    }

    public boolean ZoomStatus() {
        return true; //this.mMyMainActivity.mImageDisplayFragment.mGLImageView.ZoomStatus();
    }

    public void ZoomIn() {
        x.task().autoPost(new Runnable() { // from class: handprobe.application.app.AppProc.1
            @Override // java.lang.Runnable
            public void run() {
//                AppProc.this.mMyMainActivity.mImageDisplayFragment.mGLImageView.ChangeZoomWin(-1.0f);
            }
        });
    }

    public void ZoomOut() {
        x.task().autoPost(new Runnable() { // from class: handprobe.application.app.AppProc.2
            @Override // java.lang.Runnable
            public void run() {
//                AppProc.this.mMyMainActivity.mImageDisplayFragment.mGLImageView.ChangeZoomWin(1.0f);
            }
        });
    }

    public void CommentEnter() {
//        this.mMyMainActivity.mImageDisplayFragment.setCommentMode();
    }

    public void BodyMarkEnter() {
//        this.mMyMainActivity.mImageDisplayFragment.setBodyMarkMode();
    }

    public void PresetEnter() {
        FuncIdProc(181);
        synchronized (this) {
            Ultrasys.Instance().mIsPresetDlgOpen = true;
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (!this.mPresetDlg.isAdded() && supportFragmentManager.findFragmentByTag("PresetDialogFragment") == null) {
//                this.mPresetDlg.show(supportFragmentManager, "PresetDialogFragment");
//            }
//            Object presetParam = Ultrasys.Instance().mPresetparams.getPresetParam(1);
//            if (presetParam != null && ((Integer) presetParam).intValue() >= 0) {
//                int intValue = ((Integer) presetParam).intValue();
//                if (intValue >= -1 && intValue <= 6) {
//                    ExamTypesId.initFronOldEXAM_MAP();
//                } else {
//                    ExamTypesId.initialEXAM_MAP(intValue);
//                }
//            } else {
//                ExamTypesId.initFronOldEXAM_MAP();
//            }
        }
    }

    public void UsbProbeFirstEnter() {
        synchronized (this) {
//            List<UsbProbeInfo> updateProbeList = UsbProbeManager.Instance().mUsbInterface.updateProbeList();
//            if (updateProbeList != null && UsbProbeManager.Instance().mUsbInterface.mHSUsbProbeDevList.size() == 1) {
//                this.mMyMainActivity.mCurProbeObservable.setCurUsbProbeInfo(UsbProbeManager.Instance().mUsbProbeList.get(0));
//                this.mMyMainActivity.mCurProbeObservable.notifyCurProbe();
//                UsbProbeInfo usbProbeInfo = updateProbeList.get(0);
//                int GetProbeId = usbProbeInfo.GetProbeId();
//                if (GetProbeId > 0) {
//                    if (GetProbeId > 48 && GetProbeId < 58) {
//                        usbProbeInfo.mProbeType = 4;
//                        usbProbeInfo.VerifyAuth();
//                    } else {
//                        usbProbeInfo.mProbeType = 3;
//                        usbProbeInfo.VerifyAuth();
//                    }
//                    Ultrasys.Instance().UseProbe(GetProbeId, Ultrasys.Instance().mPresetServer.mExamModePresetServer.GetProbeDefaultExamMode(GetProbeId));
//                    this.mMyMainActivity.Refresh();
//                }
//            } else {
//                ProberEnter(true);
//            }
        }
    }

    public void ProberEnter(boolean z) {
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            List<UsbProbeInfo> list = null;
//            if (UsbProbeManager.Instance().mUsbInterface != null) {
//                list = UsbProbeManager.Instance().mUsbInterface.updateProbeList();
//            }
//            if (list != null && list.size() > 0) {
//                List<UsbProbeInfo> GetProbeList = UsbProbeManager.Instance().mUsbInterface.GetProbeList();
//                if (GetProbeList != null && GetProbeList.size() != 0 && this.mMyMainActivity.mCurProbeDev == null) {
//                    BaseActivity baseActivity = this.mMyMainActivity;
//                    HLibUsb hLibUsb = UsbProbeManager.Instance().mUsbInterface.mLibUsb;
//                    hLibUsb.getClass();
//                    baseActivity.mCurProbeDev = new HLibUsb.HUsbDev(GetProbeList.get(0).mUsbInfo);
//                }
//                if (!this.mUsbProbeConnectDlgFrag.isAdded() && supportFragmentManager.findFragmentByTag("UsbProbeConnectDlgFrag") == null) {
//                    this.mUsbProbeConnectDlgFrag.show(supportFragmentManager, "UsbProbeConnectDlgFrag");
//                }
//            } else if (!this.mMyMainActivity.getResources().getString(R.string.wifi_login_show).equals("off")) {
//                if (z && Ultrasys.Instance().GetProbeConType() == 5) {
//                    return;
//                }
//                ShowWifiProbeSelect();
//            }
        }
    }

    public void EnterWifiProbe() {
        synchronized (this) {
            ShowWifiProbeSelect();
        }
    }

    private void ShowWifiProbeSelect() {
        FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//        if (!this.mWifiProbeSelect.GetInitViewState() && !this.mWifiProbeSelect.isAdded() && supportFragmentManager.findFragmentByTag("probeSelectDialogFragment") == null) {
//            this.mWifiProbeSelect.SetInitViewState(true);
//            this.mWifiProbeSelect.show(supportFragmentManager, "probeSelectDialogFragment");
//        }
    }

    public void EnterHelp() {
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            PdfDialogFragment newInstance = PdfDialogFragment.newInstance(1);
//            if (!newInstance.isAdded() && supportFragmentManager.findFragmentByTag("Pdf") == null) {
//                newInstance.show(supportFragmentManager, "Pdf");
//            }
        }
    }

    public void EnterUsbProbe() {
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (!this.mUsbProbeConnectDlgFrag.isAdded() && supportFragmentManager.findFragmentByTag("UsbProbeConnectDlgFrag") == null) {
//                List<UsbProbeInfo> updateProbeList = UsbProbeManager.Instance().mUsbInterface.updateProbeList();
//                if (updateProbeList != null && updateProbeList.size() != 0 && this.mMyMainActivity.mCurProbeDev == null) {
//                    BaseActivity baseActivity = this.mMyMainActivity;
//                    HLibUsb hLibUsb = UsbProbeManager.Instance().mUsbInterface.mLibUsb;
//                    hLibUsb.getClass();
//                    baseActivity.mCurProbeDev = new HLibUsb.HUsbDev(updateProbeList.get(0).mUsbInfo);
//                }
//                this.mUsbProbeConnectDlgFrag.show(supportFragmentManager, "UsbProbeConnectDlgFrag");
//            }
        }
    }

    public void AutoCalcParamDlgEnter() {
        synchronized (this) {
//            if (this.mAutoSpecCalDlg == null || this.mAutoSpecCalDlg.getDialog() == null || !this.mAutoSpecCalDlg.getDialog().isShowing()) {
//                FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//                if (!this.mAutoSpecCalDlg.isAdded() && supportFragmentManager.findFragmentByTag("AutoSpecCalcDlgFragment") == null) {
//                    this.mAutoSpecCalDlg.show(supportFragmentManager, "AutoSpecCalcDlgFragment");
//                }
//            }
        }
    }

    @TargetApi(21)
    public void SaveCine() throws IOException {
//        CinePlayer cinePlayer;
//        Log.i("FuncIdProc", "SaveCine");
//        RecordService recordService = this.mMyMainActivity.recordService;
//        MediaProjectionManager mediaProjectionManager = this.mMyMainActivity.projectionManager;
//        if (recordService.isRunning()) {
//            return;
//        }
//        int GetMode = Ultrasys.Instance().mDispMode.GetMode();
//        if (GetMode == 5) {
//            cinePlayer = Ultrasys.Instance().mPWCinePlayer;
//        } else if (GetMode == 9) {
//            cinePlayer = Ultrasys.Instance().mMPWCinePlayer;
//        } else {
//            cinePlayer = Ultrasys.Instance().mBCCinePlayer;
//        }
//        if (cinePlayer.getCineSrcIterator().getPageTotal() <= 0) {
//            Toast.makeText(MyApplication.GetAppContext(), LanguageUtil._NLS(R.array.no_data_to_save), 0).show();
//            return;
//        }
//        cinePlayer.stop();
//        cinePlayer.setIsRepeat(false);
//        cinePlayer.show(cinePlayer.getCineSrcIterator().first());
//        cinePlayer.addObserver(new CineObserver(recordService));
//        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
//        if (!this.mMyMainActivity.mPermissionManager.checkPermissions(strArr)) {
//            PermissionManager permissionManager = this.mMyMainActivity.mPermissionManager;
//            String[] unGrantedPermissions = this.mMyMainActivity.mPermissionManager.getUnGrantedPermissions(strArr);
//            BaseActivity baseActivity = this.mMyMainActivity;
//            permissionManager.requestPermission(unGrantedPermissions, 102);
//            return;
//        }
//        Intent createScreenCaptureIntent = mediaProjectionManager.createScreenCaptureIntent();
//        BaseActivity baseActivity2 = this.mMyMainActivity;
//        BaseActivity baseActivity3 = this.mMyMainActivity;
//        baseActivity2.startActivityForResult(createScreenCaptureIntent, 101);
    }

    /* loaded from: classes2.dex */
//    public class CineObserver implements HObserver {
//        RecordService mRecordService;
//
//        public CineObserver(RecordService recordService) {
//            this.mRecordService = recordService;
//        }
//
//        @Override // java.util.Observer
//        public void update(Observable observable, Object obj) {
//            CinePlayer cinePlayer = (CinePlayer) observable;
//            if (((ICineIterator) obj).hasNext()) {
//                return;
//            }
//            cinePlayer.stop();
//            cinePlayer.setIsRepeat(true);
//            observable.deleteObserver(this);
//            x.task().run(new Runnable() { // from class: handprobe.application.app.AppProc.CineObserver.1
//                @Override // java.lang.Runnable
//                public void run() {
//                    SystemClock.sleep(200L);
//                    try {
//                        CineObserver.this.mRecordService.stopRecord();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    }

    public void SaveImg() throws Exception {
        if (!AppUtil.hasExternalStoragePermission()) {
            AppUtil.showRequestStoragePermissionDialog(this.mMyMainActivity);
            return;
        }
        String str = "";
//        if (Ultrasys.Instance().GetVet() == 0) {
//            Exam activeExam = ((MyApplication) x.app()).getActiveExam();
//            if (activeExam != null) {
//                str = activeExam.getLastName() + activeExam.getFirstName() + activeExam.getMiddleName();
//                this.mPat_Id = activeExam.getPatientId();
//            } else if (Ultrasys.Instance().mIsUnFreeState) {
//                str = "";
//            }
//        } else {
//            AnimalExam animalActiveExam = ((MyApplication) x.app()).getAnimalActiveExam();
//            if (animalActiveExam != null) {
//                str = animalActiveExam.getAnimalName();
//                this.mPat_Id = animalActiveExam.getPatientId();
//            } else if (Ultrasys.Instance().mIsUnFreeState) {
//                str = "";
//            }
//        }
//        if (this.mMyMainActivity.getResources().getString(R.string.save_as_dicom).equals("on")) {
//            this.mFilename = CreatePicName();
//        } else {
//            String format = this.sdf.format(new Date());
//            this.mFilename = str + ((Object) format);
//        }
//        Bitmap drawingCache = this.mMyMainActivity.mImageDisplayFragment.getDrawingCache();
//        MeasResultWindow measResultWindow = (MeasResultWindow) this.mMyMainActivity.findViewById(R.id.meas_result_win);
//        if (measResultWindow.isDrawingCacheEnabled()) {
//            measResultWindow.destroyDrawingCache();
//        }
//        measResultWindow.setDrawingCacheEnabled(true);
//        measResultWindow.buildDrawingCache();
//        Bitmap drawingCache2 = measResultWindow.getDrawingCache();
//        if (this.mMyMainActivity.mParamsDisplayLayout.isDrawingCacheEnabled()) {
//            this.mMyMainActivity.mParamsDisplayLayout.destroyDrawingCache();
//        }
//        this.mMyMainActivity.mParamsDisplayLayout.setDrawingCacheEnabled(true);
//        this.mMyMainActivity.mParamsDisplayLayout.buildDrawingCache();
//        Bitmap mergeBitmap = mergeBitmap(mergeBitmap((int) this.mMyMainActivity.mParamsDisplayLayout.getX(), (int) this.mMyMainActivity.mParamsDisplayLayout.getY(), drawingCache, this.mMyMainActivity.mParamsDisplayLayout.getDrawingCache()), drawingCache2);
//        Ultrasys.Instance().mDispMode.GetMode();
//        this.mCachBitmap = Bitmap.createScaledBitmap(mergeBitmap, (int) (480.0f / (mergeBitmap.getHeight() / mergeBitmap.getWidth())), 480, true);
//        this.mIntBuffer.clear();
//        this.mCachBitmap.copyPixelsToBuffer(this.mIntBuffer);
//        this.mUiBitmapBuf = this.mIntBuffer.array();
        final Handler handler = new Handler(new Handler.Callback() { // from class: handprobe.application.app.AppProc.3
            @Override // android.os.Handler.Callback

            @SuppressLint({"SetTextI18n"})
            public boolean handleMessage(Message message) {
                File file;
                byte[] GetSFrame;
                if (AppProc.this.mCachBitmap != null) {
                    AppProc.this.mCachBitmap.copyPixelsFromBuffer(IntBuffer.wrap(AppProc.this.mUiBitmapBuf));
//                    AppProc.this.mCachBitmap = Bitmap.createScaledBitmap(AppProc.this.mCachBitmap, ImageDefine.IMAGE_WIDTH, 480, true);
                    AppProc.this.mIntBuffer.clear();
                    AppProc.this.mCachBitmap.copyPixelsToBuffer(AppProc.this.mIntBuffer);
                    AppProc.this.mUiBitmapBuf = AppProc.this.mIntBuffer.array();
                    try {
                        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + AppProc.this.mMyMainActivity.getResources().getString(R.string.pic_save_path);
                        if (AppProc.this.mMyMainActivity.getResources().getString(R.string.save_as_dicom).equals("off")) {
                            str2 = AppProc.this.AddPatIdAndExamIdPath(str2);
                        }
                        File file2 = new File(str2);
                        if (!file2.exists() && !file2.mkdirs()) {
                            return false;
                        }
                        if (AppProc.this.mMyMainActivity.getResources().getString(R.string.save_as_dicom).equals("on")) {
                            file = new File(str2 + AppProc.this.mFilename + ".dicom");
                        } else {
                            file = new File(str2 + AppProc.this.mFilename + FileDefinition.PNG_SUFFIX);
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        String str3 = "";
                        if (!AppProc.this.mPat_Id.isEmpty()) {
                            str3 = "ID:" + AppProc.this.mPat_Id;
                        }
                        AppProc.this.mCachBitmap = BmpUtil.drawTextToBitmap(MyApplication.GetAppContext(), AppProc.this.mCachBitmap, 10, 20, str3, 16.0f);
                        AppProc.this.mCachBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        Ultrasys.Instance().GetDispMode();
                        if (Ultrasys.Instance().GetDispMode() == 50) {
                            GetSFrame = ScreenImg.Instance().GetTwoBSFrame();
                        } else {
                            GetSFrame = ScreenImg.Instance().GetSFrame();
                        }
                        FileManipulation.saveFrameData(0, GetSFrame, ScreenImg.Instance().GetTFrame(), AppProc.this.mUiBitmapBuf, AppProc.this.mIntBuffer.position(), AppProc.this.mFilename, str2);
                        Toast.makeText(MyApplication.GetAppContext(), file.getAbsolutePath(), 0).show();
                        MyApplication.GetAppContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file)));
                        AppProc.this.mSavingImg = false;
                        DbManager db = ((MyApplication) x.app()).getDb();
//                        Exam activeExam2 = ((MyApplication) x.app()).getActiveExam();
//                        if (activeExam2 != null) {
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                            AppProc.this.mCachBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                            db.saveBindingId(new Image(activeExam2.getId(), file.getName(), new Date().getTime(), byteArrayOutputStream.toByteArray()));
//                            byteArrayOutputStream.close();
//                        }
//                        DbManager db2 = ((MyApplication) x.app()).getDb();
//                        AnimalExam animalActiveExam2 = ((MyApplication) x.app()).getAnimalActiveExam();
//                        if (animalActiveExam2 != null) {
//                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
//                            AppProc.this.mCachBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
//                            db2.saveBindingId(new Image(animalActiveExam2.getId(), file.getName(), new Date().getTime(), byteArrayOutputStream2.toByteArray()));
//                            byteArrayOutputStream2.close();
//                        }
//                        if (AppProc.this.mMyMainActivity.mHandler != null) {
//                            Message obtainMessage = AppProc.this.mMyMainActivity.mHandler.obtainMessage();
//                            obtainMessage.what = 106;
//                            AppProc.this.mMyMainActivity.mHandler.sendMessage(obtainMessage);
//                        }
//                        if (AppProc.this.mMyMainActivity.getClass() == LianmedActivity.class) {
//                            ((LianmedActivity) AppProc.this.mMyMainActivity).mProtocal.SendMessage(3, AppProc.this.mFilename.getBytes());
//                        }
//                        Bundle thirdPartyExtras = ((MyApplication) x.app()).getThirdPartyExtras();
//                        if (thirdPartyExtras != null && thirdPartyExtras.getString(ThirdPartyManageActivity.EXPORT_MODE) != null) {
//                            String string = thirdPartyExtras.getString(ThirdPartyManageActivity.EXPORT_MODE);
//                            String str4 = str2 + AppProc.this.mFilename + ".";
//                            if (!string.equalsIgnoreCase("jpeg") && !string.equalsIgnoreCase("jpg")) {
//                                if (string.equalsIgnoreCase("webp")) {
//                                    File file3 = new File(str4 + string);
//                                    FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
//                                    AppProc.this.mCachBitmap.compress(Bitmap.CompressFormat.WEBP, 100, fileOutputStream2);
//                                    fileOutputStream2.flush();
//                                    fileOutputStream2.close();
//                                    thirdPartyExtras.putString(ThirdPartyManageActivity.IMG_PATH, file3.getAbsolutePath());
//                                } else {
//                                    thirdPartyExtras.putString(ThirdPartyManageActivity.IMG_PATH, file.getAbsolutePath());
//                                }
//                                ((MyApplication) x.app()).returnThirdParty(thirdPartyExtras);
//                            }
//                            File file4 = new File(str4 + string);
//                            FileOutputStream fileOutputStream3 = new FileOutputStream(file4);
//                            AppProc.this.mCachBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream3);
//                            fileOutputStream3.flush();
//                            fileOutputStream3.close();
//                            thirdPartyExtras.putString(ThirdPartyManageActivity.IMG_PATH, file4.getAbsolutePath());
//                            ((MyApplication) x.app()).returnThirdParty(thirdPartyExtras);
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return false;
            }
        });
        Thread thread = new Thread(new Runnable() { // from class: handprobe.application.app.AppProc.4
            @Override // java.lang.Runnable
            public void run() {
//                switch (Ultrasys.Instance().mDispMode.GetMode()) {
//                    case 0:
//                    case 8:
//                    case 50:
//                    case 55:
//                        int[] displayImageBuff = AppProc.this.mMyMainActivity.mImageDisplayFragment.mGLImageView.getDisplayImageBuff();
//                        if (AppProc.this.mCachBitmap.getHeight() / AppProc.this.mCachBitmap.getWidth() > 0.75f) {
//                            HDscClassIf.CopyRect1(AppProc.this.mUiBitmapBuf, 0, 0, AppProc.this.mCachBitmap.getWidth(), 480, displayImageBuff, (ImageDefine.IMAGE_WIDTH - AppProc.this.mCachBitmap.getWidth()) / 2, 0, ImageDefine.IMAGE_WIDTH, 480, AppProc.this.mCachBitmap.getWidth());
//                            break;
//                        } else {
//                            HDscClassIf.CopyRect(AppProc.this.mUiBitmapBuf, (AppProc.this.mCachBitmap.getWidth() - ImageDefine.IMAGE_WIDTH) / 2, 0, AppProc.this.mCachBitmap.getWidth(), 480, displayImageBuff, 0, 0, ImageDefine.IMAGE_WIDTH, 480);
//                            break;
//                        }
//                    case 1:
//                    case 5:
//                    case 9:
//                    case 57:
//                    case 58:
//                    case 59:
//                        int[] imageBuff = AppProc.this.mMyMainActivity.mImageDisplayFragment.mGLImageViewOneDim.getImageBuff();
//                        int[] displayRevImageBuff = AppProc.this.mMyMainActivity.mImageDisplayFragment.mGLImageViewTwoDim.getDisplayRevImageBuff();
//                        AppProc.this.mZoomIntBuffer.clear();
//                        AppProc.this.mZoomTIntBuffer.clear();
//                        Bitmap.createScaledBitmap(Bitmap.createBitmap(displayRevImageBuff, (int) ImageDefine.IMAGE_WIDTH, 480, Bitmap.Config.ARGB_8888), 320, 240, true).copyPixelsToBuffer(AppProc.this.mZoomIntBuffer);
//                        AppProc.this.mCachBitmap.getHeight();
//                        AppProc.this.mCachBitmap.getWidth();
//                        HDscClassIf.CopyRect(AppProc.this.mUiBitmapBuf, (AppProc.this.mCachBitmap.getWidth() - 320) / 2, 0, AppProc.this.mCachBitmap.getWidth(), 480, AppProc.this.mZoomIntBuffer.array(), 0, 0, 320, 240);
//                        AppProc.this.mMyMainActivity.mImageDisplayFragment.mGLImageViewOneDim.getX();
//                        Bitmap.createScaledBitmap(Bitmap.createBitmap(imageBuff, (int) ImageDefine.IMAGE_WIDTH, 240, Bitmap.Config.ARGB_8888), AppProc.this.mCachBitmap.getWidth(), 240, true).copyPixelsToBuffer(AppProc.this.mZoomTIntBuffer);
//                        HDscClassIf.CopyRect(AppProc.this.mUiBitmapBuf, 0, 239, AppProc.this.mCachBitmap.getWidth(), 480, AppProc.this.mZoomTIntBuffer.array(), 0, 0, AppProc.this.mCachBitmap.getWidth(), 240);
//                        break;
//                }
                handler.sendEmptyMessage(0);
            }
        });
        if (this.mSavingImg) {
            return;
        }
        Toast.makeText(this.mMyMainActivity.getApplicationContext(), LanguageUtil._NLS(R.array.img_saving), Toast.LENGTH_SHORT).show();
        this.mSavingImg = true;
        thread.start();
    }

    public int[] VelMapConvertion(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
//        for (int i = 0; i < iArr.length; i++) {
//            if (i < 128) {
//                iArr2[i + 128] = this.mMyMainActivity.mImageDisplayFragment.mColorBar.getColorArray()[i];
//            } else {
//                iArr2[i - 128] = this.mMyMainActivity.mImageDisplayFragment.mColorBar.getColorArray()[i];
//            }
//        }
        return iArr2;
    }

    public void OpenPatientInfo() {
        if (!AppUtil.hasExternalStoragePermission()) {
            AppUtil.showRequestStoragePermissionDialog(this.mMyMainActivity);
            return;
        }
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (!this.mPatientInfoFrg.isAdded() && supportFragmentManager.findFragmentByTag("mPatientInfoFrg") == null) {
//                this.mPatientInfoFrg.show(supportFragmentManager, "mPatientInfoFrg");
//                final HPatient curPatient = MyApplication.App().GetPatientMng().getCurPatient();
//                MainHandler.getInstance().postDelayed(new Runnable() { // from class: handprobe.application.app.AppProc.5
//                    @Override // java.lang.Runnable
//                    public void run() {
//                        AppProc.this.mPatientInfoFrg.initPatientUI(curPatient);
//                    }
//                }, 1L);
//            }
        }
    }

    public void OpenThumbnailExport(ArrayList<HImageView> arrayList) {
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (!this.mThumbnailExportFrg.isAdded() && supportFragmentManager.findFragmentByTag("mThumbnailExportFrg") == null) {
//                this.mThumbnailExportFrg.SetImageList(arrayList);
//                this.mThumbnailExportFrg.show(supportFragmentManager, "mThumbnailExportFrg");
//            }
        }
    }

    public void OpenAnimalInfo() {
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (!this.mAnimalInfoFrg.isAdded() && supportFragmentManager.findFragmentByTag("mAnimalInfoFrg") == null) {
//                this.mAnimalInfoFrg.show(supportFragmentManager, "mAnimalInfoFrg");
//                final AnimalExam animalExam = MyApplication.App().GetPatientMng().getmCurAnimalExam();
//                MainHandler.getInstance().postDelayed(new Runnable() { // from class: handprobe.application.app.AppProc.6
//                    @Override // java.lang.Runnable
//                    public void run() {
//                        AppProc.this.mAnimalInfoFrg.initExam(animalExam);
//                    }
//                }, 1L);
//            }
        }
    }

    public void OpenTestTemperature() {
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (!this.testTemperatureDlg.isAdded() && supportFragmentManager.findFragmentByTag("mTestTemperatureDlg") == null) {
//                this.testTemperatureDlg.show(supportFragmentManager, "mTestTemperatureDlg");
//            }
        }
    }

    public void OpenExamList() {
        if (!AppUtil.hasExternalStoragePermission()) {
            AppUtil.showRequestStoragePermissionDialog(this.mMyMainActivity);
            return;
        }
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (MyApplication.App().mMainActivity.getResources().getString(R.string.def_vet).equals("off")) {
//                if (!this.mExamListFrg.isAdded() && supportFragmentManager.findFragmentByTag("mExamListFrg") == null) {
//                    this.mExamListFrg.show(supportFragmentManager, "mExamListFrg");
//                }
//            } else if (MyApplication.App().mMainActivity.getResources().getString(R.string.def_vet).equals("on") && !this.mAnimalExamListFrg.isAdded() && supportFragmentManager.findFragmentByTag("mAnimalListFrg") == null) {
//                this.mAnimalExamListFrg.show(supportFragmentManager, "mAnimalListFrg");
//            }
        }
    }

    public void OpenWorkList() {
        synchronized (this) {
            FragmentManager supportFragmentManager = this.mMyMainActivity.getSupportFragmentManager();
//            if (!this.mWorkListFrg.isAdded() && supportFragmentManager.findFragmentByTag("mWorkListFrg") == null) {
//                this.mWorkListFrg.show(supportFragmentManager, "mWorkListFrg");
//            }
        }
    }

    public void FinishExam() {
        if (Ultrasys.Instance().GetVet() == 0) {
//            DbManager db = ((MyApplication) x.app()).getDb();
//            try {
//                Exam activeExam = ((MyApplication) x.app()).getActiveExam();
//                if (activeExam == null) {
//                    return;
//                }
//                activeExam.setExamDate(new Date().getTime());
//                activeExam.setExamState(3);
//                db.update(activeExam, "examDate", "examState");
//                return;
//            } catch (DbException e) {
//                e.printStackTrace();
//                return;
//            }
        }
//        DbManager db2 = MyApplication.App().getDb();
//        try {
//            AnimalExam animalActiveExam = ((MyApplication) x.app()).getAnimalActiveExam();
//            if (animalActiveExam == null) {
//                return;
//            }
//            animalActiveExam.setExamDate(new Date().getTime());
//            animalActiveExam.setExamState(3);
//            db2.update(animalActiveExam, "examDate", "examState");
//        } catch (DbException e2) {
//            e2.printStackTrace();
//        }
    }

    public void ResetTgcView() {
//        this.mMyMainActivity.mImageDisplayFragment.mTgcView.TgcReset();
    }

    public int[] revImgBuff(int[] iArr) {
        if (iArr != null) {
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
//                iArr[i] = (((i2 & SupportMenu.CATEGORY_MASK) >> 16) & (-16776961)) | ((((i2 & (-16776961)) >> 0) << 16) & SupportMenu.CATEGORY_MASK) | ((-16711936) & (((i2 & MeasureWidget.DEF_COLOR) >> 8) << 8));
            }
            return iArr;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [int] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0034 -> B:17:0x0035). Please submit an issue!!! */
    public void OpenReview() {
        long j;
//        Exam activeExam;
//        if (!AppUtil.hasExternalStoragePermission()) {
//            AppUtil.showRequestStoragePermissionDialog(this.mMyMainActivity);
//            return;
//        }
//        ?? r0 = -1;
//        r0 = -1;
//        long j2 = -1;
//        long j3 = -1;
//        try {
//            activeExam = ((MyApplication) x.app()).getActiveExam();
//        } catch (DbException e) {
//            e.printStackTrace();
//            j3 = r0;
//        }
//        if (activeExam != null) {
//            j = activeExam.getId();
//        } else {
//            AnimalExam animalActiveExam = ((MyApplication) x.app()).getAnimalActiveExam();
//            if (animalActiveExam != null) {
//                j = animalActiveExam.getId();
//            }
//            j = j3;
//            j2 = j3;
//        }
//        r0 = (j > j2 ? 1 : (j == j2 ? 0 : -1));
//        if (r0 == 0) {
//            return;
//        }
//        ReviewFragment.newInstance(j).show(this.mMyMainActivity.getSupportFragmentManager(), "ReviewFragment");
    }

    public void UsbPermission(Context context, Intent intent) {
        UsbManager usbManager;
        boolean z;
        if (context != null) {
            usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
//            z = intent.getBooleanExtra(Rc.attr.permission, false);
        } else {
            usbManager = (UsbManager) this.mMyMainActivity.getApplicationContext().getSystemService(Context.USB_SERVICE);
            z = true;
        }
//        if (!z) {
//            if (this.mUsbProbeConnectDlgFrag == null || this.mUsbProbeConnectDlgFrag.mUsbProbeConnectFrag == null) {
//                return;
//            }
//            this.mUsbProbeConnectDlgFrag.showConnectStatus("", 6);
//            return;
//        }
//        UsbProbeManager.Instance().mUsbInterface.MSLock();
//        if (this.mUsbProbeConnectDlgFrag != null && this.mUsbProbeConnectDlgFrag.mUsbProbeConnectFrag != null) {
//            this.mUsbProbeConnectDlgFrag.showConnectStatus("", 5);
//        }
//        ArrayList arrayList = (ArrayList) UsbProbeManager.Instance().mUsbInterface.GetProbeList();
//        int i = 0;
//        for (int i2 = 0; i2 < arrayList.size(); i2++) {
//            HLibUsb.HUsbDev hUsbDev = ((UsbProbeInfo) arrayList.get(i2)).mUsbInfo;
//            UsbDevice usbDevice2 = hUsbDev.mDev;
//            if (usbDevice2 != null) {
//                UsbDeviceConnection openDevice = usbManager.openDevice(usbDevice2);
//                UsbProbeManager.Instance().mUsbInterface.mLibUsb.RegisterInterface(((UsbProbeInfo) arrayList.get(i2)).mUsbInfo, openDevice, UsbProbeManager.Instance().mUsbInterface.mLibUsb.SetConfig(((UsbProbeInfo) arrayList.get(i2)).mUsbInfo.mDev, 1));
//                ((UsbProbeInfo) arrayList.get(i2)).mUsbInfo.mhDev = openDevice;
//                i = UsbProbeManager.Instance().mUsbInterface.GetProbeId(hUsbDev);
//                if (i == 64) {
//                    i = 1;
//                }
//                if (i > 48 && i < 58) {
//                    ((UsbProbeInfo) arrayList.get(i2)).mProbeType = 4;
//                    if (((UsbProbeInfo) arrayList.get(i2)).mProbeId != i) {
//                        ((UsbProbeInfo) arrayList.get(i2)).VerifyAuth();
//                    }
//                } else {
//                    ((UsbProbeInfo) arrayList.get(i2)).mProbeType = 3;
//                    if (((UsbProbeInfo) arrayList.get(i2)).mProbeId != i) {
//                        ((UsbProbeInfo) arrayList.get(i2)).VerifyAuth();
//                    }
//                }
//                ((UsbProbeInfo) arrayList.get(i2)).mProbeId = i;
//                ((UsbProbeInfo) arrayList.get(i2)).mProbeName = Ultrasys.Instance().mPidInerface.getProbeNameById(i);
//                if (this.mUsbProbeConnectDlgFrag != null) {
//                    this.mUsbProbeConnectDlgFrag.showConnectStatus(((UsbProbeInfo) arrayList.get(i2)).mProbeName, 5);
//                }
//                if (this.mUsbProbeConnectDlgFrag != null && Instance().mUsbProbeConnectDlgFrag.mUsbProbeConnectFrag != null && this.mUsbProbeConnectDlgFrag.mUsbProbeConnectFrag.mProbeAdapter != null) {
//                    this.mUsbProbeConnectDlgFrag.mUsbProbeConnectFrag.mProbeAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//        if (arrayList.size() == 1 && !this.mUsbProbeConnectDlgFrag.isAdded() && ((UsbProbeInfo) arrayList.get(0)).mProbeId != 0) {
//            this.mMyMainActivity.mCurProbeObservable.setCurUsbProbeInfo(UsbProbeManager.Instance().mUsbProbeList.get(0));
//            this.mMyMainActivity.mCurProbeObservable.notifyCurProbe();
//            if (UsbProbeManager.Instance().mUsbInterface.mHSUsbProbeDevList.size() == 1) {
//                Ultrasys.Instance().UseProbe(i, Ultrasys.Instance().mPresetServer.mExamModePresetServer.GetProbeDefaultExamMode(i));
//                if (((UsbProbeInfo) arrayList.get(0)).mProbeType == 3) {
//                    ((UsbProbeInfo) arrayList.get(0)).VerifyAuth();
//                }
//                DisMissFrag();
//            } else if (UsbProbeManager.Instance().mUsbInterface.mHUsbProbeDevList.size() == 1) {
//                if (i != 0) {
//                    Ultrasys.Instance().SelectProber(i, 0);
//                    WlanProbe.Instance().mProbeIdObservable.setChanged();
//                    WlanProbe.Instance().mProbeIdObservable.notifyObservers(Integer.valueOf(i));
//                }
//                byte[] bArr = new byte[16];
//                bArr[0] = Byte.MIN_VALUE;
//                Ultrasys.Instance().SendCmdToProbe(3, 1, bArr, 0);
//                Ultrasys.Instance().SetDispMode(0);
//                Ultrasys.Instance().UnFreeze(false);
//                DisMissFrag();
//            }
//        }
//        UsbProbeManager.Instance().mUsbInterface.MSUnLock();
    }

    public void OpenSFR(HImageView hImageView) throws UnsupportedEncodingException {
        Ultrasys.Instance().Freeze();
        String fileNameAndPath = FileUtil.getFileNameAndPath(hImageView.getPath());
        String str = fileNameAndPath + FileDefinition.SFR_SUFFIX;
        String str2 = fileNameAndPath + FileDefinition.MFR_SUFFIX;
        FrameHead frameHead = new FrameHead();
        byte[] readFrameFile = FileManipulation.readFrameFile(str, frameHead);
        int length = frameHead.HeadBuf.length + frameHead.ParaBuf.length + frameHead.DicomBuf.length + frameHead.ReserveBuf.length;
        int i = frameHead.mSImgSize + length + frameHead.mTImgSize;
        if (length == 0 && (readFrameFile = FileManipulation.readFrameFile(str, frameHead)) == null) {
            return;
        }
//        HDicomIf.CommitPrepare(readFrameFile, i, frameHead.mParaSet.mImgWidth, frameHead.mParaSet.mImgHeight, frameHead.DicomBuf);
    }

    public String CreatePicName() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        Log.i("save", i + "/" + i2 + "/" + i3 + " " + i4 + TreeNode.NODES_ID_SEPARATOR + i5 + TreeNode.NODES_ID_SEPARATOR + i6);
        String str = i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6;
        Log.i("save", str + "");
        return str;
    }

    public String AddPatIdAndExamIdPath(String str) {
//        String GetPatientId = this.mPatientInfoFrg.GetPatientId();
//        if (!GetPatientId.isEmpty()) {
//            long j = this.mPatientInfoFrg.getmExamId();
//            if (j > 0) {
//                String valueOf = String.valueOf(j);
//                return str + GetPatientId + "_" + valueOf + "/";
//            }
//            return str + GetPatientId + "/";
//        }
        return str + "anonymous/";
    }

    public String AddPatIdPath(String str) {
//        String GetPatientId = this.mPatientInfoFrg.GetPatientId();
//        if (!GetPatientId.isEmpty()) {
//            return str + GetPatientId + "/";
//        }
        return str + "anonymous/";
    }

    public void DelDirByPatientId(String str) {
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + this.mMyMainActivity.getResources().getString(R.string.pic_save_path);
        if (!str.isEmpty()) {
            str2 = str2 + str + "/";
        }
        FileOperate.deleteDirectory(str2);
    }

    public void DelPatImage(String str, String str2) {
        String str3 = Environment.getExternalStorageDirectory().getAbsolutePath() + this.mMyMainActivity.getResources().getString(R.string.pic_save_path);
        if (!str.isEmpty()) {
            str3 = str3 + str + "/" + str2;
        }
        String fileNameAndPath = FileUtil.getFileNameAndPath(str3);
        String str4 = fileNameAndPath + FileDefinition.SFR_SUFFIX + FileDefinition.COMPRESS_SUFFIX;
        FileOperate.delete(str4);
        FileOperate.delete(fileNameAndPath + FileDefinition.PNG_SUFFIX);
    }

    public void OpenMenu() {
//        if (this.mMyMainActivity.mGenStatusBarFragment == null) {
//            return;
//        }
//        if (this.mMyMainActivity.mGenStatusBarFragment.mIsMenuOpen) {
//            this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.dismiss();
//            return;
//        }
//        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mMyMainActivity, (int) R.style.NoPopupAnimation);
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup = new PopupMenu(contextThemeWrapper, this.mMyMainActivity.mGenStatusBarFragment.getView());
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenuInflater().inflate(R.menu.main_pop_menu, this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu());
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(0).setTitle(LanguageUtil._NLS(R.array.probe_info));
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(1).setTitle(LanguageUtil._NLS(R.array.exam_list));
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(2).setTitle(LanguageUtil._NLS(R.array.preset));
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(3).setTitle(LanguageUtil._NLS(R.array.instruction_manual));
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(4).setTitle(LanguageUtil._NLS(R.array.exit));
//        if (MyApplication.App().HasTouchScreen0()) {
//            this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(4).setVisible(false);
//        }
//        if (this.mMyMainActivity.mGenStatusBarFragment.getView().getResources().getString(R.string.manual).equals("off")) {
//            this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(3).setVisible(false);
//        }
//        if (this.mMyMainActivity.mGenStatusBarFragment.getView().getResources().getString(R.string.main_exit).equals("off")) {
//            this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.getMenu().getItem(4).setVisible(false);
//        }
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.setOnMenuItemClickListener(this.mMyMainActivity.mGenStatusBarFragment.mMenuItemClickListener);
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.setOnDismissListener(this.mMyMainActivity.mGenStatusBarFragment.mMenuDissListener);
//        this.mMyMainActivity.mGenStatusBarFragment.mMenuPopup.show();
//        this.mMyMainActivity.mGenStatusBarFragment.mIsMenuOpen = true;
    }

    public void LianmedSendPacket(byte[] bArr) {
//        if (this.mMyMainActivity.getClass() == LianmedActivity.class) {
//            ((LianmedActivity) this.mMyMainActivity).mProtocal.SendMessage(1, bArr);
//        }
    }
}
