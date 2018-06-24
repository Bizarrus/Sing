/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v7.widget.RecyclerView
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.smule.singandroid.effectpanel.EffectPanelRecyclerView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class EffectPanelRecyclerView_
extends EffectPanelRecyclerView
implements HasViews,
OnViewChangedListener {
    private boolean b = false;
    private final OnViewChangedNotifier c = new OnViewChangedNotifier();

    public EffectPanelRecyclerView_(Context context) {
        super(context);
        this.b();
    }

    public static EffectPanelRecyclerView a(Context object) {
        object = new EffectPanelRecyclerView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.c);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (RecyclerView)hasViews.findViewById(2131755699);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.b) {
            this.b = true;
            EffectPanelRecyclerView_.inflate((Context)this.getContext(), (int)2130903222, (ViewGroup)this);
            this.c.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

