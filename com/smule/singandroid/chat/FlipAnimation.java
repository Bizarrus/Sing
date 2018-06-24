/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Camera
 *  android.graphics.Matrix
 *  android.view.View
 *  android.view.animation.AccelerateDecelerateInterpolator
 *  android.view.animation.Animation
 *  android.view.animation.Interpolator
 *  android.view.animation.Transformation
 */
package com.smule.singandroid.chat;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

public class FlipAnimation
extends Animation {
    private Camera a;
    private View b;
    private View c;
    private float d;
    private float e;
    private boolean f = true;

    public FlipAnimation(View view, View view2, int n) {
        this.b = view;
        this.c = view2;
        this.setDuration((long)n);
        this.setFillAfter(false);
        this.setInterpolator((Interpolator)new AccelerateDecelerateInterpolator());
    }

    public void a() {
        this.f = false;
        View view = this.c;
        this.c = this.b;
        this.b = view;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2;
        float f3 = f2 = (float)((double)f * 3.141592653589793 * 180.0 / 3.141592653589793);
        if (f >= 0.5f) {
            f3 = f2 - 180.0f;
            this.b.setVisibility(8);
            this.c.setVisibility(0);
        }
        f = f3;
        if (this.f) {
            f = - f3;
        }
        transformation = transformation.getMatrix();
        this.a.save();
        this.a.rotateY(f);
        this.a.getMatrix((Matrix)transformation);
        this.a.restore();
        transformation.preTranslate(- this.d, - this.e);
        transformation.postTranslate(this.d, this.e);
    }

    public void initialize(int n, int n2, int n3, int n4) {
        super.initialize(n, n2, n3, n4);
        this.d = n / 2;
        this.e = n2 / 2;
        this.a = new Camera();
    }
}

