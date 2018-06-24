/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.Matrix
 *  android.graphics.Rect
 *  android.view.MotionEvent
 *  android.view.View
 *  android.widget.FrameLayout
 */
package com.smule.android.ui;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ScalableFrameLayout
extends FrameLayout {
    private float a;
    private float b;
    private Matrix c;
    private boolean d;
    private ArrayList<Rect> e;
    private ArrayList<View> f;

    private Rect a(View view) {
        Rect rect = new Rect();
        int[] arrn = new int[2];
        view.getLocationInWindow(arrn);
        rect.set(arrn[0], arrn[1], arrn[0] + view.getWidth(), arrn[1] + view.getHeight());
        return rect;
    }

    private void a() {
        ArrayList<Rect> arrayList = new ArrayList<Rect>();
        Iterator<View> iterator = this.f.iterator();
        while (iterator.hasNext()) {
            arrayList.add(this.a(iterator.next()));
        }
        this.setTouchableAreas(arrayList);
    }

    private void setTouchableAreas(ArrayList<Rect> arrayList) {
        this.e.clear();
        this.e.addAll(arrayList);
    }

    public void onDraw(Canvas canvas) {
        canvas.scale(this.a, this.a, (float)(this.getWidth() / 2), this.b);
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f = 1.0f / this.a;
        this.c.setScale(f, f, (float)(this.getWidth() / 2), this.b);
        motionEvent.transform(this.c);
        this.a();
        if (this.d) {
            Iterator<Rect> iterator = this.e.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().contains((int)motionEvent.getX(), (int)motionEvent.getY())) continue;
                return super.onInterceptTouchEvent(motionEvent);
            }
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Rect rect = new Rect();
        this.getHitRect(rect);
        if (!rect.contains((int)motionEvent.getX(), (int)motionEvent.getY())) {
            rect = new Matrix();
            this.c.invert((Matrix)rect);
            motionEvent.transform((Matrix)rect);
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setConsumeTouchOnFrameLevel(boolean bl) {
        this.d = bl;
        if (!this.d) {
            this.e.clear();
        }
    }

    public /* varargs */ void setTouchableViews(View ... arrview) {
        this.f.clear();
        this.f.addAll(Arrays.asList(arrview));
    }
}

