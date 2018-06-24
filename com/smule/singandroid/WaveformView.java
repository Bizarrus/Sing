package com.smule.singandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.android.utils.NotificationCenter;
import java.util.Arrays;

public class WaveformView extends View {
    private static final String f20457a = WaveformView.class.getName();
    private float[] f20458b;
    private float[] f20459c;
    private float[] f20460d;
    private float[] f20461e;
    private Bitmap f20462f;
    private Canvas f20463g;
    private Paint f20464h;
    private int f20465i = 0;
    private Paint f20466j;
    private float f20467k = 0.0f;
    private float f20468l;
    private float f20469m;
    private float f20470n;
    private int f20471o = -16777216;
    private int f20472p = -7829368;
    private int f20473q = -16776961;
    private boolean f20474r = true;
    private boolean f20475s = false;

    public WaveformView(Context context) {
        super(context);
        m21996a();
        this.f20468l = 0.0f;
        this.f20469m = 0.0f;
        this.f20470n = 0.5f;
    }

    public WaveformView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m21996a();
        this.f20468l = 0.0f;
        this.f20469m = 0.0f;
        this.f20470n = 0.5f;
    }

    public WaveformView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m21996a();
        this.f20468l = 0.0f;
        this.f20469m = 0.0f;
        this.f20470n = 0.5f;
    }

    public void setTouchable(boolean z) {
        this.f20474r = z;
    }

    public void setForegroundVolume(float f) {
        this.f20470n = f;
    }

    public void setTotalDurationSec(float f) {
        this.f20468l = f;
    }

    public float getTotalDurationSec() {
        return this.f20468l;
    }

    public void setCurrentPositionSec(float f) {
        if (this.f20475s) {
            Log.b(f20457a, "ignoring position change while dragging");
            return;
        }
        this.f20467k = m21995a(f);
        postInvalidate();
    }

    public float getCurrentPositionSec() {
        return m21999b(this.f20467k);
    }

    public float m21995a(float f) {
        if (f == 0.0f || getWidth() == 0) {
            return 0.0f;
        }
        return Math.min(Math.max(0.0f, (f / this.f20468l) * ((float) getWidth())), (float) getWidth());
    }

    public float m21999b(float f) {
        if (f == 0.0f || getWidth() == 0) {
            return 0.0f;
        }
        float width = (f / ((float) getWidth())) * this.f20468l;
        Log.b(f20457a, "x = " + f + "; m_totalDurationSec = " + this.f20468l + "; returning = " + Math.min(Math.max(0.0f, width), this.f20468l));
        return Math.min(Math.max(0.0f, width), this.f20468l);
    }

    protected void m21996a() {
        this.f20466j = new Paint();
        this.f20466j.setAntiAlias(true);
        this.f20466j.setDither(true);
        this.f20466j.setColor(-16777216);
        this.f20466j.setStyle(Style.STROKE);
        this.f20466j.setStrokeJoin(Join.ROUND);
        this.f20466j.setStrokeCap(Cap.SQUARE);
        this.f20466j.setStrokeWidth(3.0f);
        this.f20464h = new Paint(4);
        this.f20464h.setFilterBitmap(true);
        this.f20464h.setStyle(Style.STROKE);
        this.f20464h.setStrokeCap(Cap.SQUARE);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.f20462f == null || ((float) i2) > ((float) this.f20465i) * 1.25f) {
            m22000b();
            return;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.f20462f, i, i2, false);
        if (createScaledBitmap != this.f20462f) {
            this.f20462f.recycle();
            this.f20462f = createScaledBitmap;
        }
    }

    public void m22000b() {
        if (getWidth() != 0 && getHeight() != 0 && this.f20458b != null && this.f20459c != null) {
            if (this.f20462f != null) {
                this.f20462f.recycle();
            }
            this.f20462f = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            this.f20463g = new Canvas(this.f20462f);
            this.f20462f.eraseColor(-1);
            this.f20465i = this.f20462f.getHeight();
            this.f20464h.setStrokeWidth(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f20464h.setColor(this.f20471o);
            if (this.f20460d == null || this.f20461e.length != getWidth()) {
                this.f20460d = m21998a(this.f20458b, getWidth());
            }
            if (this.f20461e == null || this.f20461e.length != getWidth()) {
                this.f20461e = m21998a(this.f20459c, getWidth());
            }
            float f = 2.0f * this.f20470n;
            float height = 0.5f * ((float) getHeight());
            if (this.f20460d != null && this.f20461e != null) {
                for (int i = 0; i < this.f20460d.length; i++) {
                    this.f20463g.drawLine((float) i, ((this.f20460d[i] * f) - -1.0f) * height, (float) i, ((this.f20461e[i] * f) - -1.0f) * height, this.f20464h);
                }
            }
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m21989a(canvas);
        m21991b(canvas);
    }

    private void m21989a(Canvas canvas) {
        if (this.f20462f == null) {
            Log.d(f20457a, "Background not created");
        } else {
            canvas.drawBitmap(this.f20462f, 0.0f, 0.0f, this.f20464h);
        }
    }

    private void m21991b(Canvas canvas) {
        float height = (float) (getHeight() / 2);
        this.f20466j.setStyle(Style.STROKE);
        this.f20466j.setColor(this.f20472p);
        this.f20466j.setAlpha(191);
        this.f20466j.setStrokeWidth(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        canvas.drawLine(this.f20467k, height, (float) getWidth(), height, this.f20466j);
        canvas.drawLine(this.f20467k, 0.0f, this.f20467k, (float) getHeight(), this.f20466j);
        this.f20466j.setColor(this.f20473q);
        this.f20466j.setAlpha(255);
        this.f20466j.setStrokeWidth(3.0f);
        canvas.drawLine(0.0f, height, this.f20467k, height, this.f20466j);
        this.f20466j.setStyle(Style.FILL);
        canvas.drawCircle(this.f20467k, height, (float) (getHeight() / 20), this.f20466j);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f20474r) {
            return false;
        }
        float c = m21992c(motionEvent.getX());
        float d = m21994d(motionEvent.getY());
        switch (motionEvent.getAction()) {
            case 0:
                m21988a(c, d);
                invalidate();
                this.f20475s = true;
                return true;
            case 1:
                m21993c();
                invalidate();
                this.f20475s = false;
                return true;
            case 2:
                m21990b(c, d);
                invalidate();
                return true;
            default:
                return true;
        }
    }

    private void m21988a(float f, float f2) {
        this.f20467k = f;
    }

    private void m21990b(float f, float f2) {
        if (Math.abs(f - this.f20467k) >= 4.0f) {
            this.f20467k = f;
        }
    }

    private void m21993c() {
        NotificationCenter.m19011a().m19012a("USER_MODIFIED_SEEK_TIME_EVENT", new Float(m21999b(this.f20467k)));
    }

    private float m21992c(float f) {
        return Math.min(Math.max(0.0f, f), (float) getWidth());
    }

    private float m21994d(float f) {
        return Math.min(Math.max(0.0f, f), (float) getHeight());
    }

    public void setWaveformColor(int i) {
        this.f20471o = i;
        postInvalidate();
    }

    public void setSeekColor(int i) {
        this.f20473q = i;
        postInvalidate();
    }

    public float[] m21998a(float[] fArr, int i) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == i) {
            return Arrays.copyOf(fArr, fArr.length);
        }
        int length = fArr.length;
        float[] fArr2 = new float[i];
        float f = ((float) length) / ((float) i);
        for (int i2 = 0; i2 < i; i2++) {
            float f2 = ((float) i2) * f;
            int i3 = (int) f2;
            float f3 = f2 - ((float) i3);
            if (f2 <= 0.0f) {
                fArr2[i2] = fArr[0];
            } else if (i3 >= length - 1) {
                fArr2[i2] = fArr[length - 1];
            } else {
                fArr2[i2] = ((DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - f3) * fArr[i3]) + (fArr[i3 + 1] * f3);
            }
        }
        return fArr2;
    }

    public void m21997a(short[] sArr, int i, int i2) {
        if (sArr != null && sArr.length != 0) {
            int length = sArr.length;
            if (length % 2 != 0) {
                Log.b(f20457a, "Number of short values should be even; decrementing by one");
                length--;
            }
            int i3 = length / 2;
            this.f20458b = new float[i3];
            this.f20459c = new float[i3];
            for (length = 0; length < i3; length++) {
                this.f20458b[length] = ((float) sArr[length * 2]) / 32767.0f;
                this.f20459c[length] = ((float) sArr[(length * 2) + 1]) / 32767.0f;
            }
            m22000b();
            postInvalidate();
        }
    }
}
