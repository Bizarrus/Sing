/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Button
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.smule.singandroid.customviews.SingCta;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SingCta_
extends SingCta
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public SingCta_(Context context) {
        super(context);
        this.c();
    }

    public SingCta_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public SingCta_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (Button)hasViews.findViewById(2131756649);
        this.b = (Button)hasViews.findViewById(2131756650);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            SingCta_.inflate((Context)this.getContext(), (int)2130903410, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

