package com.book.handprobe;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gloomyfish.opencvdemo.MyApplication;

/* loaded from: classes2.dex */
public class FileOperate {
    private static final String TAG = "CopyFileUtil";
    private static File excludeFile;
    private static String fileName;
    private static File sourceFile;
    private static File targetFile;
    public static final Integer IMPORT_PRESET_FILE_SELECTOR_REQUEST_CODE = 2016;
    public static final Integer EXPORT_PRESET_FILE_SELECTOR_REQUEST_CODE = 2017;
    public static final Integer EXPORT_DCM_FILE_SELECTOR_REQUEST_CODE = 2018;

    public static boolean delete(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return deleteSingleFile(str);
        }
        return deleteDirectory(str);
    }

    public static boolean deleteSingleFile(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile() || !file.delete()) {
            return false;
        }
        Log.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + str + "成功！");
        return true;
    }

    public static boolean deleteDirectory(String str) {
        File[] listFiles;
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        boolean z = true;
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                z = deleteSingleFile(file2.getAbsolutePath());
                if (!z) {
                    break;
                }
            } else {
                if (file2.isDirectory() && !(z = deleteDirectory(file2.getAbsolutePath()))) {
                    break;
                }
            }
        }
        if (!z || !file.delete()) {
            return false;
        }
        Log.e("--Method--", "Copy_Delete.deleteDirectory: 删除目录" + str + "成功！");
        return true;
    }

    private static void copyFileUsingFileChannels(File file, File file2) throws IOException {
        FileChannel channel = new FileInputStream(file).getChannel();
        try {
            FileChannel channel2 = new FileOutputStream(file2).getChannel();
            channel2.transferFrom(channel, 0L, channel.size());
            if (channel2 != null) {
                channel2.close();
            }
            if (channel == null) {
                return;
            }
            channel.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (channel != null) {
                    if (th != null) {
                        try {
                            channel.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    } else {
                        channel.close();
                    }
                }
                throw th2;
            }
        }
    }

    private static void simpleCopy(Context context, List<File> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            sourceFile = list.get(i);
            fileName = sourceFile.getName();
            targetFile = new File(str + "/" + fileName);
            if (targetFile.exists()) {
                Log.d(TAG, "simpleCopy: 存在同名文件/文件夹" + fileName);
                sameCopy(context);
            } else {
                sameCopy(context);
            }
        }
        Log.d(TAG, "simpleCopy: 复制完毕");
    }

    public static void CopyFolder(String str, String str2, String str3) {
        Context GetAppContext = MyApplication.GetAppContext();
        sourceFile = new File(str2);
        targetFile = new File(str);
        excludeFile = new File(str3);
        sameCopy(GetAppContext);
    }

    private static void sameCopy(Context context) {
        if (sourceFile.isDirectory()) {
            if (targetFile.exists()) {
                ArrayList arrayList = new ArrayList();
                Collections.addAll(arrayList, sourceFile.listFiles());
                simpleCopy(context, arrayList, targetFile.getAbsolutePath());
                return;
            } else if (targetFile.mkdirs()) {
                ArrayList arrayList2 = new ArrayList();
                Collections.addAll(arrayList2, sourceFile.listFiles());
                simpleCopy(context, arrayList2, targetFile.getAbsolutePath());
                return;
            } else {
                Log.d(TAG, "simpleCopy: 创建文件夹失败！");
                Log.d(TAG, "simpleCopy: 要么没有权限，要么目标路径有问题");
                return;
            }
        }
        try {
            if (excludeFile.getAbsolutePath().equalsIgnoreCase(sourceFile.getAbsolutePath())) {
                return;
            }
            copyFileUsingFileChannels(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SelImportPresetFolder() {
        Intent intent = new Intent();
//        intent.setClass(MyApplication.App().getApplicationContext(), FsActivity.class);
        intent.putExtra(FileSelectConstant.SELECTOR_REQUEST_CODE_KEY, FileSelectConstant.SELECTOR_MODE_FOLDER);
        MyApplication.App().GetActivity().startActivityForResult(intent, IMPORT_PRESET_FILE_SELECTOR_REQUEST_CODE.intValue());
    }

    public static void SelExportFolder(int i) {
        Intent intent = new Intent();
//        intent.setClass(MyApplication.App().getApplicationContext(), FsActivity.class);
        intent.putExtra(FileSelectConstant.SELECTOR_REQUEST_CODE_KEY, FileSelectConstant.SELECTOR_MODE_FOLDER);
        MyApplication.App().GetActivity().startActivityForResult(intent, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v3 */
    public static byte[] readFileToByteArray(String str) {
        FileInputStream fileInputStream;
        File file = new File(str);
        int exists = file.exists() ? 1: 0;
        try {
            if (exists == 0) {
                Log.e(TAG, "File doesn't exist!");
                return null;
            }
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e = e;
                fileInputStream = null;
            } catch (Exception e2) {
//                e = e2;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                exists = 0;
                try {
//                    exists.close();
                    throw th;
                } catch (IOException unused) {
                    return null;
                }
            }
            try {
                if (fileInputStream.getChannel().size() == 0) {
                    Log.d(TAG, "The FileInputStream has no content!");
                    try {
                        fileInputStream.close();
                        return null;
                    } catch (IOException unused2) {
                        return null;
                    }
                }
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                try {
                    fileInputStream.close();
                    return bArr;
                } catch (IOException unused3) {
                    return null;
                }
            } catch (FileNotFoundException e3) {
//                e = e3;
//                e.printStackTrace();
                try {
                    fileInputStream.close();
                    return null;
                } catch (IOException unused4) {
                    return null;
                }
            } catch (IOException e4) {
//                e = e4;
//                e.printStackTrace();
                try {
                    fileInputStream.close();
                    return null;
                } catch (IOException unused5) {
                    return null;
                }
            }
        } catch (Throwable th2) {
//            th = th2;
        }
        return null;
    }

    public static void writeAndFlush(String str, byte[] bArr) {
        try {
            FileChannel channel = new FileOutputStream(str).getChannel();
            channel.write(ByteBuffer.wrap(bArr));
            channel.force(true);
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        int lastIndexOf2 = str.lastIndexOf(".");
        if (lastIndexOf == -1 || lastIndexOf2 == -1) {
            return null;
        }
        return str.substring(lastIndexOf + 1, lastIndexOf2);
    }

    public static String getFileNameWithSuffix(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf != -1) {
            return str.substring(lastIndexOf + 1);
        }
        return null;
    }

    public static void CreateDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
