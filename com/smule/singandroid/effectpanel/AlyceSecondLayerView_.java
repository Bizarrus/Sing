/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.smule.singandroid.customviews.CyclableStateItemView;
import com.smule.singandroid.effectpanel.AlyceSecondLayerView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AlyceSecondLayerView_
extends AlyceSecondLayerView
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public AlyceSecondLayerView_(Context context) {
        super(context);
        this.a();
    }

    public AlyceSecondLayerView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
    }

    public AlyceSecondLayerView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a();
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (CyclableStateItemView)hasViews.findViewById(2131755278);
        this.b = (CyclableStateItemView)hasViews.findViewById(2131755279);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            AlyceSecondLayerView_.inflate((Context)this.getContext(), (int)2130903080, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

