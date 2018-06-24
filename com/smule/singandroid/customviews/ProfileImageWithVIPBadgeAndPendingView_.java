/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileImageWithVIPBadgeAndPendingView_
extends ProfileImageWithVIPBadgeAndPendingView
implements HasViews,
OnViewChangedListener {
    private boolean u = false;
    private final OnViewChangedNotifier v = new OnViewChangedNotifier();

    public ProfileImageWithVIPBadgeAndPendingView_(Context context) {
        super(context);
        this.d();
    }

    public ProfileImageWithVIPBadgeAndPendingView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d();
    }

    public ProfileImageWithVIPBadgeAndPendingView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.d();
    }

    private void d() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.v);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (SNPImageView)hasViews.findViewById(2131756412);
        this.b = (ImageView)hasViews.findViewById(2131756414);
        this.c = (TextView)hasViews.findViewById(2131756413);
        this.s = (RelativeLayout)hasViews.findViewById(2131756415);
        this.t = (ImageView)hasViews.findViewById(2131756416);
    }

    public void onFinishInflate() {
        if (!this.u) {
            this.u = true;
            ProfileImageWithVIPBadgeAndPendingView_.inflate((Context)this.getContext(), (int)2130903377, (ViewGroup)this);
            this.v.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

