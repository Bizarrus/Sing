/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.util.AttributeSet
 *  android.view.View
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.smule.singandroid.customviews.ParticleGenerator;

public class RadianceView
extends View {
    private ParticleGenerator a;
    private int[] b = new int[4];
    private int c;
    private final float d = 1.0f;
    private int e;
    private int f = 1500;
    private boolean g = false;
    private final int h = 6;

    public RadianceView(Context context) {
        super(context);
    }

    public RadianceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadianceView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    private void b() {
        float f = this.getResources().getDimension(2131427885);
        this.a = new ParticleGenerator(this.getContext(), 2130838212, f, f, 0.0, 360.0, this.f, 1.0f);
        this.b[0] = -1;
        this.c = this.getResources().getInteger(2131623973);
        this.e = (int)((float)this.getResources().getInteger(2131623973) * 1.0f);
        int n = (int)((double)this.c * 1.5 + 0.5);
        for (int n2 : this.b) {
            this.a.a(n2, n);
        }
    }

    public void a() {
        if (this.a == null) {
            return;
        }
        this.a.a();
    }

    public void a(float f) {
        if (this.a == null) {
            return;
        }
        if (this.g) {
            this.a.a((float)this.e * f * 6.0f);
            return;
        }
        this.a.a(0.0f);
    }

    public void a(float f, float f2) {
        this.b();
        this.a.b();
        this.a.a((int)f, (int)f2, this.b[0]);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a == null) {
            return;
        }
        this.a.a(canvas);
    }

    public void setDrawStar(boolean bl) {
        this.g = bl;
    }
}

