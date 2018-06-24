package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.ui.SNPImageView;

public class SNPProgressImageView extends SNPImageView {
    private int f21943c;
    private int f21944d;
    private final Paint f21945e = new Paint();
    private final Rect f21946f = new Rect();

    public SNPProgressImageView(Context context) {
        super(context);
    }

    public SNPProgressImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SNPProgressImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setMax(int i) {
        this.f21943c = i;
        invalidate();
    }

    public int getMax() {
        return this.f21943c;
    }

    public void setProgress(int i) {
        this.f21944d = i;
        invalidate();
    }

    public int getProgress() {
        return this.f21944d;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float min = Math.min(((float) this.f21944d) / ((float) this.f21943c), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        canvas.getClipBounds(this.f21946f);
        this.f21945e.setColor(-1);
        this.f21945e.setAlpha(224);
        min = (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - min) * ((float) this.f21946f.height());
        canvas.drawRect((float) this.f21946f.left, (float) this.f21946f.top, (float) this.f21946f.right, ((float) this.f21946f.top) + min, this.f21945e);
    }
}
