/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.smule.singandroid.customviews.WatchAdView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class WatchAdView_
extends WatchAdView
implements HasViews,
OnViewChangedListener {
    private boolean d = false;
    private final OnViewChangedNotifier e = new OnViewChangedNotifier();

    public WatchAdView_(Context context) {
        super(context);
        this.b();
    }

    public static WatchAdView a(Context object) {
        object = new WatchAdView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.e);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131756814);
        this.b = (TextView)hasViews.findViewById(2131756815);
        this.c = (Button)hasViews.findViewById(2131756816);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.d) {
            this.d = true;
            WatchAdView_.inflate((Context)this.getContext(), (int)2130903464, (ViewGroup)this);
            this.e.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

