package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.mopub.volley.DefaultRetryPolicy;

public class VisualizerView extends View {
    static final float f22062a = ((float) Math.sqrt(2.0d));
    static final float f22063b = (((2.0f * f22062a) / 7.0f) - 0.14285715f);
    static final float f22064c = (((4.0f * f22062a) / 7.0f) - 0.2857143f);
    static final float f22065d = (f22062a / 2.0f);
    static final float f22066e = (((3.0f * f22062a) / 7.0f) + 0.2857143f);
    static final float[][] f22067f;
    private static final String f22068g = VisualizerView.class.getName();
    private double f22069h;
    private float f22070i;
    private final Paint f22071j = new Paint();
    private final Path f22072k = new Path();
    private float f22073l;
    private float f22074m;
    private float f22075n;

    static {
        r0 = new float[8][];
        r0[0] = new float[]{f22063b, f22064c, f22065d};
        r0[1] = new float[]{f22066e, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT};
        r0[2] = new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, f22066e, f22065d};
        r0[3] = new float[]{f22064c, f22063b, 0.0f};
        r0[4] = new float[]{-f22063b, -f22064c, -f22065d};
        r0[5] = new float[]{-f22066e, -1.0f, -1.0f};
        r0[6] = new float[]{-1.0f, -f22066e, -f22065d};
        r0[7] = new float[]{-f22064c, -f22063b, 0.0f};
        f22067f = r0;
    }

    public VisualizerView(Context context) {
        super(context);
        m23540a(null, 0);
    }

    public VisualizerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23540a(attributeSet, 0);
    }

    public VisualizerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23540a(attributeSet, i);
    }

    public void m23541a(double d, float f) {
        this.f22069h = d;
        this.f22070i = f;
        invalidate();
    }

    private void m23540a(AttributeSet attributeSet, int i) {
        this.f22071j.setAntiAlias(true);
        this.f22071j.setColor(-1);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f22073l = (float) (i / 3);
        this.f22074m = this.f22073l / 24.0f;
        this.f22075n = this.f22074m * 3.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        m23538a(this.f22072k, ((float) height) / 2.0f, (float) width, m23537a(this.f22070i, -30.0f, -6.0f, 0.05f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) * (((float) height) * 0.5f));
        canvas.drawPath(this.f22072k, this.f22071j);
    }

    private void m23538a(Path path, float f, float f2, float f3) {
        path.reset();
        float f4 = (((float) ((this.f22069h % 0.5d) / 0.5d)) * this.f22073l) - this.f22073l;
        path.moveTo(f4, f);
        int i = 0;
        int i2 = 0;
        while (i < 32) {
            if (f4 > (-this.f22075n) && f4 < f2) {
                m23539a(path, f4, this.f22074m, f2, f, f3, f22067f[i2]);
            }
            float f5 = f4 + this.f22075n;
            i++;
            i2 = (i2 + 1) % 8;
            f4 = f5;
        }
        path.lineTo(f4, f);
    }

    private float m23537a(float f, float f2, float f3, float f4, float f5) {
        return Math.min(Math.max((((f - f2) * (f5 - f4)) / (f3 - f2)) + f4, f4), f5);
    }

    private static float m23536a(float f, float f2, float f3) {
        if (f < 0.0f || f > f3) {
            return 0.0f;
        }
        return (float) ((((double) f2) * 0.5d) * (1.0d - Math.cos((double) ((6.2831855f * f) / f3))));
    }

    private static void m23539a(Path path, float f, float f2, float f3, float f4, float f5, float[] fArr) {
        float f6 = f + f2;
        float f7 = f6 + f2;
        float f8 = f7 + f2;
        path.cubicTo(f6, f4 + m23536a(f6, fArr[0] * f5, f3), f7, f4 + m23536a(f7, fArr[1] * f5, f3), f8, f4 + m23536a(f8, fArr[2] * f5, f3));
    }
}
