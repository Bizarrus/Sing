/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.LinearLayout
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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.customviews.PurchaseButton;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PurchaseButton_
extends PurchaseButton
implements HasViews,
OnViewChangedListener {
    private boolean g = false;
    private final OnViewChangedNotifier h = new OnViewChangedNotifier();

    public PurchaseButton_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public PurchaseButton_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.h);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.c = (LinearLayout)hasViews.findViewById(2131756418);
        this.d = (TextView)hasViews.findViewById(2131756419);
        this.e = (TextView)hasViews.findViewById(2131756420);
        this.f = (TextView)hasViews.findViewById(2131756421);
    }

    public void onFinishInflate() {
        if (!this.g) {
            this.g = true;
            PurchaseButton_.inflate((Context)this.getContext(), (int)2130903380, (ViewGroup)this);
            this.h.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

