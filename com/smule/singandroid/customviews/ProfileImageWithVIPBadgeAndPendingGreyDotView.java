/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ui.SNPImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileImageWithVIPBadgeAndPendingGreyDotView
extends ProfileImageWithVIPBadge {
    @ViewById
    SNPImageView s;

    public ProfileImageWithVIPBadgeAndPendingGreyDotView(Context context) {
        super(context);
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    @Override
    protected void a(float f, float f2, float f3) {
        this.q = new RelativeLayout.LayoutParams((int)f3, (int)f3);
        this.q.setMargins((int)(f + f2), (int)f, 0, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(int n, boolean bl, View.OnClickListener onClickListener) {
        this.d.a((ImageView)this.a);
        this.d.a();
        int n2 = bl ? 2130838207 : 2130838208;
        this.a.setImageDrawable(this.getResources().getDrawable(n2));
        this.setVIP(false);
        if (n == 0) {
            this.c.setClickable(false);
        } else {
            this.c.setOnClickListener(onClickListener);
        }
        this.setPerformanceCount(n);
    }

    public void b(boolean bl) {
        if (bl) {
            this.s.setVisibility(0);
            this.setVIP(true);
            return;
        }
        this.s.setVisibility(8);
        this.setVIP(false);
    }

    protected void d() {
        if (this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams && this.k != 0) {
            ((ViewGroup.MarginLayoutParams)this.getLayoutParams()).setMargins(0, 0, 0, 0);
        }
    }

    @Override
    protected void onMeasure(int n, int n2) {
        this.d();
        this.c.setLayoutParams((ViewGroup.LayoutParams)this.p);
        this.a.setLayoutParams((ViewGroup.LayoutParams)this.o);
        this.s.setLayoutParams((ViewGroup.LayoutParams)this.o);
        this.b.setLayoutParams((ViewGroup.LayoutParams)this.q);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int)this.j, (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)this.j, (int)1073741824));
        this.b();
    }
}

