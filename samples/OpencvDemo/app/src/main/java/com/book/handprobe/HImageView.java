package com.book.handprobe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes2.dex */
public class HImageView extends ImageView {
    private boolean mOnSel;
    private String mPath;

    public void DbClick() {
    }

    public HImageView(Context context) {
        super(context);
        this.mOnSel = false;
    }

    public HImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnSel = false;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public String getPath() {
        return this.mPath;
    }

    public void Click() {
        this.mOnSel = !this.mOnSel;
    }

    public boolean GetSelStatus() {
        return this.mOnSel;
    }
}
