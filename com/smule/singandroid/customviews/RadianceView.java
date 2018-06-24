package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;

public class RadianceView extends View {
    private ParticleGenerator f21908a;
    private int[] f21909b;
    private int f21910c;
    private final float f21911d;
    private int f21912e;
    private int f21913f;
    private boolean f21914g;
    private final int f21915h;

    public RadianceView(Context context) {
        super(context);
        this.f21909b = new int[4];
        this.f21911d = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f21913f = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        this.f21914g = false;
        this.f21915h = 6;
    }

    public RadianceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f21909b = new int[4];
        this.f21911d = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f21913f = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        this.f21914g = false;
        this.f21915h = 6;
    }

    public RadianceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21909b = new int[4];
        this.f21911d = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f21913f = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        this.f21914g = false;
        this.f21915h = 6;
    }

    public void setDrawStar(boolean z) {
        this.f21914g = z;
    }

    private void m23465b() {
        int i = 0;
        float dimension = getResources().getDimension(C1947R.dimen.singing_star_size);
        this.f21908a = new ParticleGenerator(getContext(), C1947R.drawable.star_burst, dimension, dimension, 0.0d, 360.0d, this.f21913f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.f21909b[0] = -1;
        this.f21910c = getResources().getInteger(C1947R.integer.singing_max_stars_per_second);
        this.f21912e = (int) (((float) getResources().getInteger(C1947R.integer.singing_max_stars_per_second)) * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        int i2 = (int) ((((double) this.f21910c) * 1.5d) + 0.5d);
        int[] iArr = this.f21909b;
        int length = iArr.length;
        while (i < length) {
            this.f21908a.m23344a(iArr[i], i2);
            i++;
        }
    }

    public void m23468a(float f, float f2) {
        m23465b();
        this.f21908a.m23347b();
        this.f21908a.m23345a((int) f, (int) f2, this.f21909b[0]);
    }

    public void m23466a() {
        if (this.f21908a != null) {
            this.f21908a.m23342a();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f21908a != null) {
            this.f21908a.m23346a(canvas);
        }
    }

    public void m23467a(float f) {
        if (this.f21908a != null) {
            if (this.f21914g) {
                this.f21908a.m23343a((((float) this.f21912e) * f) * 6.0f);
            } else {
                this.f21908a.m23343a(0.0f);
            }
        }
    }
}
