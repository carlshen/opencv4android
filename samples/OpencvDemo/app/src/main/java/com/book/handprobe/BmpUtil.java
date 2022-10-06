package com.book.handprobe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/* loaded from: classes2.dex */
public class BmpUtil {
    static Bitmap bitmap;

    public static Bitmap drawTextToBitmap(Context context, Bitmap bitmap2, int i, int i2, String str, float f) {
        try {
            float f2 = context.getResources().getDisplayMetrics().density;
            bitmap = bitmap2;
            Bitmap.Config config = bitmap.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }
            bitmap = bitmap.copy(config, true);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(1);
            paint.setDither(true);
            paint.setFilterBitmap(true);
            paint.setColor(-3355444);
            paint.setTextSize(f);
            paint.setShadowLayer(1.0f, 0.0f, 1.0f, -12303292);
            paint.getTextBounds(str, 0, str.length(), new Rect());
            canvas.drawText(str, i, i2, paint);
            return bitmap;
        } catch (Exception unused) {
            return null;
        }
    }
}
