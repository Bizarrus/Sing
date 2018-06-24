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
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileImageWithVIPBadge_
extends ProfileImageWithVIPBadge
implements HasViews,
OnViewChangedListener {
    private boolean s = false;
    private final OnViewChangedNotifier t = new OnViewChangedNotifier();

    public ProfileImageWithVIPBadge_(Context context) {
        super(context);
        this.d();
    }

    public ProfileImageWithVIPBadge_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d();
    }

    public ProfileImageWithVIPBadge_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.d();
    }

    private void d() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.t);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (SNPImageView)hasViews.findViewById(2131756412);
        this.b = (ImageView)hasViews.findViewById(2131756414);
        this.c = (TextView)hasViews.findViewById(2131756413);
    }

    public void onFinishInflate() {
        if (!this.s) {
            this.s = true;
            ProfileImageWithVIPBadge_.inflate((Context)this.getContext(), (int)2130903375, (ViewGroup)this);
            this.t.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

