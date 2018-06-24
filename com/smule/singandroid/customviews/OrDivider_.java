/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
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
import android.widget.TextView;
import com.smule.singandroid.customviews.OrDivider;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OrDivider_
extends OrDivider
implements HasViews,
OnViewChangedListener {
    private boolean e = false;
    private final OnViewChangedNotifier f = new OnViewChangedNotifier();

    public OrDivider_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public OrDivider_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.f);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = hasViews.findViewById(2131756219);
        this.c = (TextView)hasViews.findViewById(2131756220);
        this.d = hasViews.findViewById(2131756221);
    }

    public void onFinishInflate() {
        if (!this.e) {
            this.e = true;
            OrDivider_.inflate((Context)this.getContext(), (int)2130903345, (ViewGroup)this);
            this.f.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

