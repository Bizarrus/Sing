/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Rect
 *  android.util.AttributeSet
 *  android.view.MotionEvent
 *  android.view.View
 *  android.widget.FrameLayout
 */
package com.smule.android.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class TouchInterceptingFrameLayout
extends FrameLayout {
    private boolean a = false;
    private ArrayList<Rect> b = new ArrayList();
    private ArrayList<View> c = new ArrayList();

    public TouchInterceptingFrameLayout(Context context) {
        this(context, null);
    }

    public TouchInterceptingFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TouchInterceptingFrameLayout(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.setWillNotDraw(false);
    }

    private Rect a(View view) {
        Rect rect = new Rect();
        int[] arrn = new int[2];
        view.getLocationOnScreen(arrn);
        rect.set(arrn[0], arrn[1], arrn[0] + view.getWidth(), arrn[1] + view.getHeight());
        return rect;
    }

    private void a() {
        ArrayList<Rect> arrayList = new ArrayList<Rect>();
        Iterator<View> iterator = this.c.iterator();
        while (iterator.hasNext()) {
            arrayList.add(this.a(iterator.next()));
        }
        this.setTouchableAreas(arrayList);
    }

    private void setTouchableAreas(ArrayList<Rect> arrayList) {
        this.b.clear();
        this.b.addAll(arrayList);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.a();
        if (this.a) {
            Iterator<Rect> iterator = this.b.iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) continue;
                return super.onInterceptTouchEvent(motionEvent);
            }
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Rect rect = new Rect();
        this.getHitRect(rect);
        if (!rect.contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setConsumeTouchOnFrameLevel(boolean bl) {
        this.a = bl;
        if (!this.a) {
            this.b.clear();
        }
    }

    public /* varargs */ void setTouchableViews(View ... arrview) {
        this.c.clear();
        this.c.addAll(Arrays.asList(arrview));
    }
}

