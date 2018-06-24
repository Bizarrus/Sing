/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingGreyDotView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileImageWithVIPBadgeAndPendingGreyDotView_
extends ProfileImageWithVIPBadgeAndPendingGreyDotView
implements HasViews,
OnViewChangedListener {
    private boolean t = false;
    private final OnViewChangedNotifier u = new OnViewChangedNotifier();

    public ProfileImageWithVIPBadgeAndPendingGreyDotView_(Context context) {
        super(context);
        this.e();
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e();
    }

    public ProfileImageWithVIPBadgeAndPendingGreyDotView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.e();
    }

    private void e() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.u);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (SNPImageView)hasViews.findViewById(2131756412);
        this.b = (ImageView)hasViews.findViewById(2131756414);
        this.c = (TextView)hasViews.findViewById(2131756413);
        this.s = (SNPImageView)hasViews.findViewById(2131756415);
    }

    public void onFinishInflate() {
        if (!this.t) {
            this.t = true;
            ProfileImageWithVIPBadgeAndPendingGreyDotView_.inflate((Context)this.getContext(), (int)2130903376, (ViewGroup)this);
            this.u.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

