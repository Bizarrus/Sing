/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Looper
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.smule.singandroid.effectpanel.QuickSelectControlView;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class QuickSelectControlView_
extends QuickSelectControlView
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public QuickSelectControlView_(Context context) {
        super(context);
        this.c();
    }

    public QuickSelectControlView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    public QuickSelectControlView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.c();
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (FrameLayout)hasViews.findViewById(2131756426);
        this.b = (LinearLayout)hasViews.findViewById(2131756428);
    }

    @Override
    public void a(final boolean bl) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            QuickSelectControlView_.super.a(bl);
            return;
        }
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                QuickSelectControlView_.super.a(bl);
            }
        }, (long)0);
    }

    @Override
    public void b() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            QuickSelectControlView_.super.b();
            return;
        }
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                QuickSelectControlView_.super.b();
            }
        }, (long)0);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            QuickSelectControlView_.inflate((Context)this.getContext(), (int)2130903383, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

