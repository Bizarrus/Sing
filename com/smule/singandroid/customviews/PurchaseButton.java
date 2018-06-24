/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Paint
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PurchaseButton
extends RelativeLayout {
    boolean a;
    boolean b;
    @ViewById
    LinearLayout c;
    @ViewById
    TextView d;
    @ViewById
    TextView e;
    @ViewById
    TextView f;

    public PurchaseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PurchaseButton(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b() {
        if (this.a || !(this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.getLayoutParams();
        int n = marginLayoutParams.topMargin;
        int n2 = this.getResources().getDimensionPixelOffset(2131428167);
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin, n - n2, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        this.requestLayout();
        this.a = true;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a() {
        float f;
        float f2;
        if (this.f.getVisibility() != 0) {
            return;
        }
        float f3 = new Paint((Paint)this.d.getPaint()).measureText(this.d.getText().toString());
        int n = this.c.getWidth();
        int n2 = this.getResources().getDimensionPixelOffset(2131428182);
        float f4 = this.f.getLeft() - this.c.getLeft();
        n = f3 / 2.0f > f4 - (f = (float)n / 2.0f) - (f2 = (float)n2) ? this.c.getRight() - this.f.getLeft() + n2 : n2;
        this.d.setPadding(n2, 0, n, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(String string2, String string3, String string4) {
        this.d.setText((CharSequence)string2);
        if (!TextUtils.isEmpty((CharSequence)string4) && this.b) {
            this.f.setText((CharSequence)string4);
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(4);
        }
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            this.e.setText((CharSequence)string3);
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        this.a();
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        this.a();
    }

    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, View.MeasureSpec.makeMeasureSpec((int)(this.getResources().getDimensionPixelSize(2131427817) + this.getResources().getDimensionPixelSize(2131428167)), (int)1073741824));
        this.b();
    }

    public void setTagVisibility(boolean bl) {
        this.b = bl;
    }
}

