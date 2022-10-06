package com.book.handprobe;

import android.text.format.DateFormat;
import java.io.File;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class FileUtil {
    public static File renameFileAddTail(File file, String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }
        String absolutePath = file.getParentFile().getAbsolutePath();
        File file2 = new File(absolutePath, file.getName() + str);
        return file.renameTo(file2) ? file2 : file;
    }

    public static File renameFileAddHead(File file, String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }
        String absolutePath = file.getParentFile().getAbsolutePath();
        File file2 = new File(absolutePath, str + file.getName());
        return file.renameTo(file2) ? file2 : file;
    }

    public static File renameFileWithInfo(File file, String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }
        File file2 = new File(file.getParentFile().getAbsolutePath(), str);
        return file.renameTo(file2) ? file2 : file;
    }

    public static String formatNowDate() {
        return DateFormat.format("yyyyMMddTHHmmss", Calendar.getInstance()).toString();
    }

    public static String getFileName(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        int lastIndexOf2 = str.lastIndexOf(".");
        if (lastIndexOf == -1 || lastIndexOf2 == -1) {
            return null;
        }
        return str.substring(lastIndexOf + 1, lastIndexOf2);
    }

    public static String getFileNameAndPath(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf != -1) {
            return str.substring(0, lastIndexOf);
        }
        return null;
    }

    public static boolean emptyDirectory(String str) {
        File file = new File(str);
        file.exists();
        if (file.isFile()) {
            if (!file.delete()) {
                return false;
            }
        } else {
            for (File file2 : file.listFiles()) {
                emptyDirectory(file2.getAbsolutePath());
            }
        }
        return true;
    }
}
