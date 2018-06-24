/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.smule.singandroid.customviews.PlayableItemView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PlayableItemView_
extends PlayableItemView
implements HasViews,
OnViewChangedListener {
    private boolean n = false;
    private final OnViewChangedNotifier o = new OnViewChangedNotifier();

    public PlayableItemView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e();
    }

    private void e() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.o);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (ImageView)hasViews.findViewById(2131756268);
        this.b = hasViews.findViewById(2131756276);
        this.c = (ImageView)hasViews.findViewById(2131755953);
        this.d = hasViews.findViewById(2131756271);
        this.e = hasViews.findViewById(2131756272);
        this.f = (ProgressBar)hasViews.findViewById(2131756274);
        this.g = (ImageView)hasViews.findViewById(2131756273);
        this.h = hasViews.findViewById(2131756275);
        this.i = (ImageButton)hasViews.findViewById(2131756110);
        this.j = (ImageButton)hasViews.findViewById(2131756107);
        this.k = (ImageView)hasViews.findViewById(2131756269);
        this.l = (ImageView)hasViews.findViewById(2131756270);
        this.m = (ImageView)hasViews.findViewById(2131756267);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.n) {
            this.n = true;
            PlayableItemView_.inflate((Context)this.getContext(), (int)2130903356, (ViewGroup)this);
            this.o.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

