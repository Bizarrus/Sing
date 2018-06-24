/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.SeekBar
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.singandroid.effectpanel.VolumeControllerView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class VolumeControllerView_
extends VolumeControllerView
implements HasViews,
OnViewChangedListener {
    private boolean d = false;
    private final OnViewChangedNotifier e = new OnViewChangedNotifier();

    public VolumeControllerView_(Context context) {
        super(context);
        this.a();
    }

    public static VolumeControllerView a(Context object) {
        object = new VolumeControllerView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.e);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131756811);
        this.b = (TextView)hasViews.findViewById(2131756812);
        this.c = (SeekBar)hasViews.findViewById(2131756813);
    }

    public void onFinishInflate() {
        if (!this.d) {
            this.d = true;
            VolumeControllerView_.inflate((Context)this.getContext(), (int)2130903463, (ViewGroup)this);
            this.e.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

