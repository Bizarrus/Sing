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
 *  com.smule.singandroid.textviews.AutoResizeTextView
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
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.textviews.AutoResizeTextView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CustomToolbar_
extends CustomToolbar
implements HasViews,
OnViewChangedListener {
    private boolean s = false;
    private final OnViewChangedNotifier t = new OnViewChangedNotifier();

    public CustomToolbar_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d();
    }

    public CustomToolbar_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.d();
    }

    private void d() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.t);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.g = hasViews.findViewById(2131755224);
        this.h = (ImageView)hasViews.findViewById(2131755226);
        this.i = (TextView)hasViews.findViewById(2131755228);
        this.j = (TextView)hasViews.findViewById(2131755229);
        this.k = (TextView)hasViews.findViewById(2131755225);
        this.l = (ImageView)hasViews.findViewById(2131755230);
        this.m = (TextView)hasViews.findViewById(2131755231);
        this.b = (RelativeLayout)hasViews.findViewById(2131755221);
        this.c = (ImageView)hasViews.findViewById(2131755652);
        this.d = (AutoResizeTextView)hasViews.findViewById(2131755651);
        this.e = (ImageView)hasViews.findViewById(2131755322);
        this.f = (TextView)hasViews.findViewById(2131755648);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.s) {
            this.s = true;
            CustomToolbar_.inflate((Context)this.getContext(), (int)2130903195, (ViewGroup)this);
            this.t.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

