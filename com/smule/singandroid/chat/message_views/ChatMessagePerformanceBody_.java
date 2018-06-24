/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.textviews.EllipsizingTextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.chat.message_views.ChatMessagePerformanceBody;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.textviews.EllipsizingTextView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMessagePerformanceBody_
extends ChatMessagePerformanceBody
implements HasViews,
OnViewChangedListener {
    private boolean w = false;
    private final OnViewChangedNotifier x = new OnViewChangedNotifier();

    public ChatMessagePerformanceBody_(Context context) {
        super(context);
        this.f();
    }

    public ChatMessagePerformanceBody_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f();
    }

    public ChatMessagePerformanceBody_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.f();
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.x);
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
        this.b = hasViews.findViewById(2131755406);
        this.c = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755407);
        this.d = (EllipsizingTextView)hasViews.findViewById(2131755408);
        this.e = hasViews.findViewById(2131755409);
        this.f = hasViews.findViewById(2131755410);
        this.g = (ViewGroup)hasViews.findViewById(2131755413);
        this.h = (Button)hasViews.findViewById(2131755414);
        this.i = hasViews.findViewById(2131755318);
        this.j = (TextView)hasViews.findViewById(2131755415);
        this.k = (TextView)hasViews.findViewById(2131755411);
        this.l = hasViews.findViewById(2131756205);
        this.m = (ProgressBar)hasViews.findViewById(2131755412);
        this.p();
        this.c();
    }

    public void onFinishInflate() {
        if (!this.w) {
            this.w = true;
            ChatMessagePerformanceBody_.inflate((Context)this.getContext(), (int)2130903109, (ViewGroup)this);
            this.x.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

