/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.Paint
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.View$MeasureSpec
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class MoreProfilesView
extends View {
    Paint a;
    Paint b;
    float c;

    public MoreProfilesView(Context context) {
        super(context);
        this.a();
    }

    public MoreProfilesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
    }

    public MoreProfilesView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a();
    }

    private void a() {
        this.a = new Paint();
        this.a.setColor(Color.rgb((int)214, (int)214, (int)214));
        this.b = new Paint();
        this.b.setColor(-1);
        this.c = TypedValue.applyDimension((int)1, (float)3.0f, (DisplayMetrics)this.getResources().getDisplayMetrics());
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int n = this.getMeasuredWidth();
        int n2 = this.getMeasuredHeight();
        float f = (float)Math.min(n, n2) / 2.0f;
        canvas.drawCircle((float)(n / 2), (float)(n2 / 2), f, this.a);
        canvas.drawCircle((float)(n / 2), (float)(n2 / 2), this.c, this.b);
        canvas.drawCircle((float)(n / 2) - 2.0f * f / 5.0f, (float)(n2 / 2), this.c, this.b);
        canvas.drawCircle((float)(n / 2) + f * 2.0f / 5.0f, (float)(n2 / 2), this.c, this.b);
    }

    protected void onMeasure(int n, int n2) {
        n = MoreProfilesView.resolveSizeAndState((int)(this.getPaddingLeft() + this.getPaddingRight() + this.getSuggestedMinimumWidth()), (int)n, (int)1);
        View.MeasureSpec.getSize((int)n);
        this.getPaddingBottom();
        this.getPaddingTop();
        this.setMeasuredDimension(n, MoreProfilesView.resolveSizeAndState((int)View.MeasureSpec.getSize((int)n), (int)n2, (int)0));
    }
}

