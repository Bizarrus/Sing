package com.smule.singandroid.chat;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FlipAnimation extends Animation {
    private Camera f21060a;
    private View f21061b;
    private View f21062c;
    private float f21063d;
    private float f21064e;
    private boolean f21065f = true;

    public FlipAnimation(View view, View view2, int i) {
        this.f21061b = view;
        this.f21062c = view2;
        setDuration((long) i);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void m22742a() {
        this.f21065f = false;
        View view = this.f21062c;
        this.f21062c = this.f21061b;
        this.f21061b = view;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.f21063d = (float) (i / 2);
        this.f21064e = (float) (i2 / 2);
        this.f21060a = new Camera();
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = (float) (((((double) f) * 3.141592653589793d) * 180.0d) / 3.141592653589793d);
        if (f >= 0.5f) {
            f2 -= 180.0f;
            this.f21061b.setVisibility(8);
            this.f21062c.setVisibility(0);
        }
        if (this.f21065f) {
            f2 = -f2;
        }
        Matrix matrix = transformation.getMatrix();
        this.f21060a.save();
        this.f21060a.rotateY(f2);
        this.f21060a.getMatrix(matrix);
        this.f21060a.restore();
        matrix.preTranslate(-this.f21063d, -this.f21064e);
        matrix.postTranslate(this.f21063d, this.f21064e);
    }
}
