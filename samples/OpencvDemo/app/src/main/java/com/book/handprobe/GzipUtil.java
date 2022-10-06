package com.book.handprobe;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

/* loaded from: classes2.dex */
public class GzipUtil {
    public static File compress(File file, File file2, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            GzipCompressorOutputStream gzipCompressorOutputStream = new GzipCompressorOutputStream(bufferedOutputStream);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                gzipCompressorOutputStream.write(bArr, 0, read);
            }
            bufferedInputStream.close();
            fileInputStream.close();
            gzipCompressorOutputStream.flush();
            gzipCompressorOutputStream.close();
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        Log.i("gzip", "gzip compress finish! cost " + (currentTimeMillis2 - currentTimeMillis) + "ms");
        if (z) {
            file.delete();
        }
        return file2;
    }

    public static File uncompress(File file, File file2, boolean z) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            GzipCompressorInputStream gzipCompressorInputStream = new GzipCompressorInputStream(bufferedInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = gzipCompressorInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            gzipCompressorInputStream.close();
            bufferedInputStream.close();
            fileInputStream.close();
            bufferedOutputStream.close();
            fileOutputStream.close();
            if (z) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file2;
    }

    public static void readGzipTest(File file, int i) {
        try {
            int i2 = 0;
            while (new GzipCompressorInputStream(new BufferedInputStream(new FileInputStream(file))).read(new byte[i]) != -1) {
                i2++;
            }
            PrintStream printStream = System.out;
            printStream.println("解压后的大小:" + (((i2 * i) / 1024) / 1024) + "MB");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
