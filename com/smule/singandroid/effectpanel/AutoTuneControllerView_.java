/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v7.widget.SwitchCompat
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.effectpanel.AutoTuneControllerView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AutoTuneControllerView_
extends AutoTuneControllerView
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public AutoTuneControllerView_(Context context) {
        super(context);
        this.f();
    }

    public static AutoTuneControllerView a(Context object) {
        object = new AutoTuneControllerView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (SwitchCompat)hasViews.findViewById(2131755289);
        this.b = (TextView)hasViews.findViewById(2131755290);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            AutoTuneControllerView_.inflate((Context)this.getContext(), (int)2130903082, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

