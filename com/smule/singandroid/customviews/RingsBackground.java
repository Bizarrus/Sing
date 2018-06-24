package com.smule.singandroid.customviews;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.animation.Interpolator;
import com.mopub.volley.DefaultRetryPolicy;

public class RingsBackground extends View {
    private Paint f21917a;
    private Point f21918b;
    private Point f21919c;
    private float f21920d;
    private float f21921e;
    private int f21922f;
    private long f21923g;
    private float f21924h;
    private float f21925i;
    private boolean f21926j;
    private Interpolator f21927k;

    class C44391 implements OnLayoutChangeListener {
        final /* synthetic */ RingsBackground f21916a;

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f21916a.f21919c.x = i3 - i;
            this.f21916a.f21919c.y = i4 - i2;
            this.f21916a.f21918b.x = this.f21916a.f21919c.x / 2;
            this.f21916a.f21918b.y = this.f21916a.f21919c.y / 2;
        }
    }

    public void setMaxAlpha(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        this.f21925i = 102.0f * f;
    }

    protected void onDraw(Canvas canvas) {
        if (this.f21926j) {
            long drawingTime = getDrawingTime();
            if (this.f21923g == 0) {
                this.f21923g = drawingTime;
            }
            float min = Math.min(((float) (drawingTime - this.f21923g)) / (this.f21920d * 1000.0f), 1000.0f);
            this.f21923g = drawingTime;
            this.f21924h += min;
            float f = this.f21924h - ((float) ((int) this.f21924h));
            for (int i = 0; ((float) i) < Math.min((float) this.f21922f, this.f21924h); i++) {
                min = this.f21927k.getInterpolation((((float) i) + f) / ((float) this.f21922f));
                if (((double) min) < 0.3d) {
                    this.f21917a.setAlpha((int) (((double) this.f21925i) * (((double) min) / 0.3d)));
                } else {
                    this.f21917a.setAlpha((int) (((double) (this.f21925i * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - min))) / 0.7d));
                }
                canvas.drawCircle((float) this.f21918b.x, (float) this.f21918b.y, min * this.f21921e, this.f21917a);
            }
            invalidate();
        }
    }
}
