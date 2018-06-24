/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
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
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileImageWithVIPBadgeAndPendingView
extends ProfileImageWithVIPBadge {
    @ViewById
    RelativeLayout s;
    @ViewById
    ImageView t;

    public ProfileImageWithVIPBadgeAndPendingView(Context context) {
        super(context);
    }

    public ProfileImageWithVIPBadgeAndPendingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProfileImageWithVIPBadgeAndPendingView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void b() {
        if (!this.l || this.m || !(this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || this.k == 0) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin - this.k, marginLayoutParams.bottomMargin - this.k + this.getResources().getDimensionPixelSize(2131428177));
        this.m = true;
        this.post(new Runnable(){

            @Override
            public void run() {
                ProfileImageWithVIPBadgeAndPendingView.this.requestLayout();
            }
        });
    }

    @Override
    protected void onMeasure(int n, int n2) {
        this.c.setLayoutParams((ViewGroup.LayoutParams)this.p);
        this.a.setLayoutParams((ViewGroup.LayoutParams)this.o);
        this.t.setLayoutParams((ViewGroup.LayoutParams)this.o);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int)this.j, (int)1073741824), View.MeasureSpec.makeMeasureSpec((int)this.j, (int)1073741824));
        this.b();
    }

    public void setPending(boolean bl) {
        if (bl) {
            this.s.setVisibility(0);
            return;
        }
        this.s.setVisibility(8);
    }

}

