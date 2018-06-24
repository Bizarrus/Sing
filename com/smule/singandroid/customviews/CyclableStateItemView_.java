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
import com.smule.singandroid.customviews.CyclableStateItemView;
import com.smule.singandroid.customviews.IconFontView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class CyclableStateItemView_
extends CyclableStateItemView
implements HasViews,
OnViewChangedListener {
    private boolean e = false;
    private final OnViewChangedNotifier f = new OnViewChangedNotifier();

    public CyclableStateItemView_(Context context) {
        super(context);
        this.b();
    }

    public CyclableStateItemView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public CyclableStateItemView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.f);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = (ImageView)hasViews.findViewById(2131755658);
        this.c = (IconFontView)hasViews.findViewById(2131755659);
        this.d = (TextView)hasViews.findViewById(2131755660);
    }

    public void onFinishInflate() {
        if (!this.e) {
            this.e = true;
            CyclableStateItemView_.inflate((Context)this.getContext(), (int)2130903197, (ViewGroup)this);
            this.f.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

