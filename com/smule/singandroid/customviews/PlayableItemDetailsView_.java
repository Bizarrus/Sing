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
import com.smule.singandroid.customviews.PlayableItemDetailsView;
import com.smule.singandroid.customviews.PlayableItemView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PlayableItemDetailsView_
extends PlayableItemDetailsView
implements HasViews,
OnViewChangedListener {
    private boolean a = false;
    private final OnViewChangedNotifier b = new OnViewChangedNotifier();

    public PlayableItemDetailsView_(Context context) {
        super(context);
        this.c();
    }

    public PlayableItemDetailsView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public PlayableItemDetailsView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.b);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.ae = (PlayableItemView)hasViews.findViewById(2131755320);
        this.q = hasViews.findViewById(2131755324);
        this.r = (TextView)hasViews.findViewById(2131755325);
        this.s = (TextView)hasViews.findViewById(2131755326);
        this.t = (TextView)hasViews.findViewById(2131756265);
        this.u = (TextView)hasViews.findViewById(2131756266);
        this.p();
    }

    public void onFinishInflate() {
        if (!this.a) {
            this.a = true;
            PlayableItemDetailsView_.inflate((Context)this.getContext(), (int)2130903355, (ViewGroup)this);
            this.b.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

