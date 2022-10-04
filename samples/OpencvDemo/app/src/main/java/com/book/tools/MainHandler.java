package com.book.tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Vector;

/* loaded from: classes2.dex */
public class MainHandler extends Handler {
    private static volatile MainHandler instance;
    public Vector<Handler> mDopplerMsgHandlerVector;

    public static MainHandler getInstance() {
        if (instance == null) {
            synchronized (MainHandler.class) {
                if (instance == null) {
                    instance = new MainHandler();
                }
            }
        }
        return instance;
    }

    private MainHandler() {
        super(Looper.getMainLooper());
        this.mDopplerMsgHandlerVector = new Vector<>();
    }

    public void InitCtoJavaMessageHandler() {
        new Thread(new Runnable() { // from class: kernel.MainHandler.1
            @Override // java.lang.Runnable
            public void run() {
                long[] jArr = new long[4];
                while (true) {
                    if (jnitojava.JavaGetMessage(jArr) != 0) {
                        for (int i = 0; i < MainHandler.this.mDopplerMsgHandlerVector.size(); i++) {
                            Message message = new Message();
                            message.what = (int) jArr[0];
                            message.arg1 = (int) jArr[1];
                            message.arg2 = (int) jArr[2];
                            MainHandler.this.mDopplerMsgHandlerVector.get(i).sendMessage(message);
                        }
                        Tools.Sleep(10);
                    }
                    Tools.Sleep(20);
                }
            }
        }).start();
    }

    public void RegistryDopplerHandler(Handler handler) {
        this.mDopplerMsgHandlerVector.add(handler);
    }

    public void UnRegistryDopplerHandler(Handler handler) {
        this.mDopplerMsgHandlerVector.remove(handler);
    }
}
