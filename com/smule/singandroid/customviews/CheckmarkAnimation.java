package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import com.smule.singandroid.C1947R;

public class CheckmarkAnimation extends View {
    private ClipDrawable f21507a;
    private int f21508b;
    private int f21509c;
    private long f21510d = 0;
    private boolean f21511e = false;
    private int f21512f;
    private Interpolator f21513g;

    public CheckmarkAnimation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23152a(context, attributeSet);
    }

    public CheckmarkAnimation(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23152a(context, attributeSet);
    }

    void m23152a(Context context, AttributeSet attributeSet) {
        this.f21513g = new AccelerateInterpolator();
        Drawable drawable = context.getResources().getDrawable(C1947R.drawable.ftux_headphones_check);
        this.f21508b = drawable.getIntrinsicWidth();
        this.f21509c = drawable.getIntrinsicHeight();
        this.f21507a = new ClipDrawable(drawable, 3, 1);
    }

    public void m23151a() {
        this.f21511e = true;
        this.f21510d = 0;
        this.f21512f = 0;
        postInvalidate();
    }

    public void m23153b() {
        this.f21511e = false;
        postInvalidate();
    }

    public void m23154c() {
        this.f21511e = false;
    }

    protected void onDraw(Canvas canvas) {
        if (this.f21511e) {
            long drawingTime = getDrawingTime();
            if (this.f21510d == 0) {
                this.f21510d = drawingTime;
            }
            float f = ((float) (drawingTime - this.f21510d)) * 50.0f;
            this.f21510d = drawingTime;
            this.f21512f += (int) f;
            this.f21507a.setLevel(this.f21512f);
            int width = (canvas.getWidth() - this.f21508b) / 2;
            int height = (canvas.getHeight() - this.f21509c) / 2;
            this.f21507a.setBounds(width, height, this.f21508b + width, this.f21509c + height);
            this.f21507a.draw(canvas);
            if (((float) this.f21512f) >= 10000.0f) {
                m23154c();
                return;
            } else {
                invalidate();
                return;
            }
        }
        canvas.drawColor(0);
    }
}
