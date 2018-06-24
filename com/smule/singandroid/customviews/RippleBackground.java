package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.R$styleable;

public class RippleBackground extends View {
    private Paint f21929a;
    private Point f21930b = new Point();
    private Point f21931c = new Point();
    private float f21932d;
    private float f21933e;
    private float f21934f;
    private int f21935g;
    private boolean f21936h;
    private float f21937i;
    private float f21938j;
    private long f21939k = 0;
    private float f21940l = 0.0f;
    private volatile boolean f21941m = false;
    private Interpolator f21942n;

    class C44401 implements OnLayoutChangeListener {
        final /* synthetic */ RippleBackground f21928a;

        C44401(RippleBackground rippleBackground) {
            this.f21928a = rippleBackground;
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f21928a.f21931c.x = i3 - i;
            this.f21928a.f21931c.y = i4 - i2;
            this.f21928a.f21930b.x = this.f21928a.f21931c.x / 2;
            this.f21928a.f21930b.y = this.f21928a.f21931c.y / 2;
        }
    }

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23475a(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23475a(context, attributeSet);
    }

    void m23475a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RippleBackground);
        int color = obtainStyledAttributes.getColor(5, getResources().getColor(C1947R.color.album_art_wave_mask));
        this.f21929a = new Paint();
        this.f21929a.setAntiAlias(true);
        this.f21929a.setColor(color);
        switch (obtainStyledAttributes.getInt(0, 2)) {
            case 1:
                this.f21929a.setStyle(Style.STROKE);
                this.f21936h = true;
                break;
            default:
                this.f21929a.setStyle(Style.FILL);
                this.f21936h = false;
                break;
        }
        this.f21942n = new DecelerateInterpolator();
        this.f21932d = obtainStyledAttributes.getFloat(2, 2.0f);
        this.f21933e = obtainStyledAttributes.getDimension(3, 0.0f) / 2.0f;
        this.f21934f = obtainStyledAttributes.getDimension(4, (float) getResources().getDimensionPixelSize(C1947R.dimen.ripple_background_end_diameter)) / 2.0f;
        this.f21935g = obtainStyledAttributes.getInteger(1, getResources().getInteger(C1947R.integer.ripple_background_amount));
        this.f21937i = obtainStyledAttributes.getDimension(6, -1.0f);
        this.f21938j = obtainStyledAttributes.getDimension(7, -1.0f);
        float f = (this.f21937i <= 0.0f || this.f21938j >= 0.0f) ? this.f21938j : this.f21937i;
        this.f21938j = f;
        obtainStyledAttributes.recycle();
        addOnLayoutChangeListener(new C44401(this));
    }

    public void m23474a() {
        if (!this.f21941m) {
            this.f21941m = true;
            this.f21939k = 0;
            this.f21940l = m23473c();
        }
        postInvalidate();
    }

    public void m23476b() {
        this.f21941m = false;
    }

    private float m23473c() {
        return ((this.f21933e / this.f21934f) * ((float) this.f21935g)) - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    protected void onDraw(Canvas canvas) {
        if (this.f21941m) {
            long drawingTime = getDrawingTime();
            if (this.f21939k == 0) {
                this.f21939k = drawingTime;
            }
            float min = Math.min(((float) (drawingTime - this.f21939k)) / (this.f21932d * 1000.0f), 1000.0f);
            this.f21939k = drawingTime;
            this.f21940l += min;
            float f = this.f21940l - ((float) ((int) this.f21940l));
            for (int i = 1; ((float) i) < Math.min((float) this.f21935g, this.f21940l); i++) {
                min = this.f21942n.getInterpolation((((float) i) + f) / ((float) this.f21935g));
                if (this.f21936h && this.f21937i > 0.0f) {
                    this.f21929a.setStrokeWidth((float) ((((double) (this.f21938j - this.f21937i)) * ((double) min)) + ((double) this.f21937i)));
                }
                this.f21929a.setAlpha((int) (255.0f * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - min)));
                canvas.drawCircle((float) this.f21930b.x, (float) this.f21930b.y, min * this.f21934f, this.f21929a);
            }
            invalidate();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f21941m = false;
    }
}
