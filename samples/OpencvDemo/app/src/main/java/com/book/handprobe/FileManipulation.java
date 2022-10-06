package com.book.handprobe;

import android.content.Context;
import android.widget.Toast;

import com.book.tools.Tools;
import com.book.tools.WlanProbe;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Handler;

import gloomyfish.opencvdemo.R;

/* loaded from: classes2.dex */
public class FileManipulation {
    public static void saveFrameData(Context context) {
    }

    public static byte[] readFrameFile(String str, FrameHead frameHead) throws UnsupportedEncodingException {
        return FileControl.selectGzFrameFile(str + FileDefinition.COMPRESS_SUFFIX, frameHead);
    }

    public static void saveFrameData(final int i, final byte[] bArr, final byte[] bArr2, final int[] iArr, final int i2, final String str, final String str2) {
        new Thread(new Runnable() { // from class: handprobe.components.ultrasys.FrameLineData.FileManager.FileManipulation.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v6, types: [android.os.Handler] */
            /* JADX WARN: Type inference failed for: r1v15 */
            /* JADX WARN: Type inference failed for: r1v16 */
            /* JADX WARN: Type inference failed for: r1v4, types: [java.io.File] */
            /* JADX WARN: Type inference failed for: r1v5 */
            /* JADX WARN: Type inference failed for: r1v7, types: [handprobe.components.ultrasys.FrameLineData.FileManager.FileManipulation$1$1, java.lang.Runnable] */
            /* JADX WARN: Type inference failed for: r2v10, types: [java.io.FileOutputStream] */
            /* JADX WARN: Type inference failed for: r2v11 */
            /* JADX WARN: Type inference failed for: r2v16 */
            /* JADX WARN: Type inference failed for: r2v17 */
            /* JADX WARN: Type inference failed for: r2v2 */
            /* JADX WARN: Type inference failed for: r3v17 */
            /* JADX WARN: Type inference failed for: r3v18 */
            /* JADX WARN: Type inference failed for: r3v2 */
            /* JADX WARN: Type inference failed for: r3v8, types: [java.io.BufferedOutputStream] */
            /* JADX WARN: Type inference failed for: r3v9 */
            /* JADX WARN: Type inference failed for: r4v3 */
            /* JADX WARN: Type inference failed for: r4v5, types: [java.io.DataOutputStream] */
            /* JADX WARN: Type inference failed for: r4v8 */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x012f -> B:63:0x0132). Please submit an issue!!! */
            @Override // java.lang.Runnable
            public void run() {
                BufferedOutputStream bufferedOutputStream;
                FileOutputStream fileOutputStream;
                File r1;
                File file;
                FileOutputStream fileOutputStream2;
                BufferedOutputStream bufferedOutputStream2;
                BufferedOutputStream bufferedOutputStream3;
                DataOutputStream dataOutputStream;
                IOException e;
                FileOutputStream fileOutputStream3 = null;
                File r4;
                if (bArr != null) {
                    File file2 = new File(str2);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (i == 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        String str3 = FileDefinition.SFR_SUFFIX;
                        sb.append(str3);
                        String sb2 = sb.toString();
                        r1 = new File(file2, sb2);
//                        fileOutputStream = sb2;
//                        bufferedOutputStream = str3;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str);
                        String str4 = FileDefinition.MFR_SUFFIX;
                        sb3.append(str4);
                        String sb4 = sb3.toString();
                        r1 = new File(file2, sb4);
//                        fileOutputStream = sb4;
//                        bufferedOutputStream = str4;
                    }
                    try {
                        try {
                            try {
                                fileOutputStream3 = new FileOutputStream((File) r1);
                            } catch (Throwable th) {
                                th = th;
                                try {
//                                    r4.close();
//                                    bufferedOutputStream.close();
//                                    fileOutputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                throw th;
                            }
                        } catch (IOException e3) {
                            bufferedOutputStream3 = null;
                            dataOutputStream = null;
                            e = e3;
                            fileOutputStream3 = null;
                        } catch (Throwable th2) {
                            fileOutputStream2 = null;
                            bufferedOutputStream2 = null;
                        }
                        try {
                            bufferedOutputStream3 = new BufferedOutputStream(fileOutputStream3);
                            try {
                                dataOutputStream = new DataOutputStream(bufferedOutputStream3);
                                try {
                                    FrameHead frameHead = new FrameHead(i, bArr.length, bArr2 != null ? bArr2.length : 0, i2 * 4);
                                    dataOutputStream.write(frameHead.HeadBuf);
                                    dataOutputStream.write(frameHead.ParaBuf);
                                    dataOutputStream.write(frameHead.DicomBuf);
                                    dataOutputStream.write(frameHead.ReserveBuf);
                                    dataOutputStream.write(bArr);
                                    if (bArr2 != null) {
                                        dataOutputStream.write(bArr2);
                                    }
                                    if (iArr != null) {
                                        byte[] bArr3 = new byte[i2 * 4];
//                                        HDscClassIf.IntArrayToByteArray(bArr3, iArr, i2);
                                        dataOutputStream.write(bArr3);
                                    }
                                    if (frameHead.mBdyMrkSize > 0) {
                                        dataOutputStream.write(frameHead.mBdymrkData.buf);
                                    }
                                    if (frameHead.mCommSize > 0) {
                                        byte[] bArr4 = new byte[4];
                                        Tools.IntToByte(frameHead.mCommentDataList.size(), bArr4);
                                        dataOutputStream.write(bArr4);
                                        for (int i3 = 0; i3 < frameHead.mCommentDataList.size(); i3++) {
                                            dataOutputStream.write(frameHead.mCommentDataList.get(i3).buf);
                                        }
                                    }
                                    if (frameHead.mMeasSize > 0) {
                                        for (int i4 = 0; i4 < frameHead.mMeaswidgets.size(); i4++) {
                                            dataOutputStream.write(frameHead.mMeaswidgets.get(i4).buf);
                                        }
                                    }
                                    dataOutputStream.close();
                                    bufferedOutputStream3.close();
                                    fileOutputStream3.close();
                                    file = r1;
                                } catch (IOException e4) {
                                    e = e4;
                                    e.printStackTrace();
                                    dataOutputStream.close();
                                    bufferedOutputStream3.close();
                                    fileOutputStream3.close();
                                    file = r1;
                                    GzipUtil.compress(file, new File(file.getParentFile().getAbsolutePath(), file.getName() + FileDefinition.COMPRESS_SUFFIX), true);
//                                    Handler r0 = WlanProbe.Instance().mHandler;
//                                    r1 = new Runnable() { // from class: handprobe.components.ultrasys.FrameLineData.FileManager.FileManipulation.1.1
//                                        @Override // java.lang.Runnable
//                                        public void run() {
//                                            Toast makeText = Toast.makeText(AppProc.Instance().mMyMainActivity, LanguageUtil._NLS(R.array.save_data_finished), 1);
//                                            makeText.setGravity(17, 0, 0);
//                                            makeText.show();
//                                        }
//                                    };
//                                    r0.post(r1);
                                }
                            } catch (IOException e5) {
                                dataOutputStream = null;
                                e = e5;
                            } catch (Throwable th3) {
                                r4 = null;
                                fileOutputStream = fileOutputStream3;
                                bufferedOutputStream = bufferedOutputStream3;
//                                r4.close();
                                bufferedOutputStream.close();
                                fileOutputStream.close();
                            }
                        } catch (IOException e6) {
                            dataOutputStream = null;
                            e = e6;
                            bufferedOutputStream3 = null;
                        } catch (Throwable th4) {
//                            th = th4;
                            bufferedOutputStream2 = null;
                            fileOutputStream2 = fileOutputStream3;
//                            r4 = bufferedOutputStream2;
                            fileOutputStream = fileOutputStream2;
                            bufferedOutputStream = bufferedOutputStream2;
//                            th = th;
//                            r4.close();
                            bufferedOutputStream.close();
                            fileOutputStream.close();
//                            throw th;
                        }
                    } catch (IOException e7) {
                        e7.printStackTrace();
                        file = r1;
                    }
//                    GzipUtil.compress(file, new File(file.getParentFile().getAbsolutePath(), file.getName() + FileDefinition.COMPRESS_SUFFIX), true);
//                    ?? r02 = WlanProbe.Instance().mHandler;
//                    r1 = new Runnable() { // from class: handprobe.components.ultrasys.FrameLineData.FileManager.FileManipulation.1.1
//                        @Override // java.lang.Runnable
//                        public void run() {
//                            Toast makeText = Toast.makeText(AppProc.Instance().mMyMainActivity, LanguageUtil._NLS(R.array.save_data_finished), 1);
//                            makeText.setGravity(17, 0, 0);
//                            makeText.show();
//                        }
//                    };
//                    r02.post(r1);
                }
            }
        }, "saveFrameDataThread").start();
    }
}
