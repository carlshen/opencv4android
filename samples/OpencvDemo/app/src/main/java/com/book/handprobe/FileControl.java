package com.book.handprobe;

import android.os.SystemClock;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

/* loaded from: classes2.dex */
public class FileControl {
    public static final byte[] loadGzfile(final File file) {
        final int[] iArr = {-1};
        final byte[][] bArr = {null};
        new Thread(new Runnable() { // from class: handprobe.components.ultrasys.FrameLineData.FileManager.FileControl.1
            /* JADX WARN: Removed duplicated region for block: B:42:0x0077 A[Catch: IOException -> 0x0073, TryCatch #2 {IOException -> 0x0073, blocks: (B:38:0x006f, B:42:0x0077, B:44:0x007c), top: B:48:0x006f }] */
            /* JADX WARN: Removed duplicated region for block: B:44:0x007c A[Catch: IOException -> 0x0073, TRY_LEAVE, TryCatch #2 {IOException -> 0x0073, blocks: (B:38:0x006f, B:42:0x0077, B:44:0x007c), top: B:48:0x006f }] */
            /* JADX WARN: Removed duplicated region for block: B:48:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                GzipCompressorInputStream gzipCompressorInputStream;
                DataInputStream dataInputStream;
                Throwable th;
                FileInputStream fileInputStream;
                IOException e;
                try {
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            gzipCompressorInputStream = new GzipCompressorInputStream(fileInputStream);
                            try {
                                dataInputStream = new DataInputStream(gzipCompressorInputStream);
                                try {
                                    try {
                                        bArr[0] = new byte[2621440];
                                        dataInputStream.read(bArr[0]);
                                        iArr[0] = 1;
                                        fileInputStream.close();
                                        gzipCompressorInputStream.close();
                                        dataInputStream.close();
                                    } catch (IOException e2) {
                                        e = e2;
                                        e.printStackTrace();
                                        if (fileInputStream != null) {
                                            fileInputStream.close();
                                        }
                                        if (gzipCompressorInputStream != null) {
                                            gzipCompressorInputStream.close();
                                        }
                                        if (dataInputStream == null) {
                                            return;
                                        }
                                        dataInputStream.close();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (gzipCompressorInputStream != null) {
                                        gzipCompressorInputStream.close();
                                    }
                                    if (dataInputStream != null) {
                                        dataInputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (IOException e4) {
                                dataInputStream = null;
                                e = e4;
                            } catch (Throwable th3) {
                                dataInputStream = null;
                                th = th3;
                                if (fileInputStream != null) {
                                }
                                if (gzipCompressorInputStream != null) {
                                }
                                if (dataInputStream != null) {
                                }
                                throw th;
                            }
                        } catch (IOException e5) {
                            dataInputStream = null;
                            e = e5;
                            gzipCompressorInputStream = null;
                        } catch (Throwable th4) {
                            dataInputStream = null;
                            th = th4;
                            gzipCompressorInputStream = null;
                        }
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                } catch (Exception e7) {
                    gzipCompressorInputStream = null;
                    dataInputStream = null;
                    fileInputStream = null;
                } catch (Throwable th5) {
                    gzipCompressorInputStream = null;
                    dataInputStream = null;
                    th = th5;
                    fileInputStream = null;
                }
            }
        }, "loadFrame").start();
        while (iArr[0] != 0) {
            if (iArr[0] == 1) {
                return bArr[0];
            }
            if (iArr[0] != -1) {
                return null;
            }
            SystemClock.sleep(5L);
        }
        return null;
    }

    public static byte[] selectGzFrameFile(String str, FrameHead frameHead) throws UnsupportedEncodingException {
        byte[] loadGzfile = loadGzfile(new File(str));
        if (loadGzfile == null) {
            return null;
        }
        frameHead.loadFrame(loadGzfile);
        return loadGzfile;
    }
}
