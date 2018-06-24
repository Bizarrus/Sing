/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.effectpanel.EffectPanelJoinerVideoTab;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class EffectPanelJoinerVideoTab_
extends EffectPanelJoinerVideoTab
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public EffectPanelJoinerVideoTab_(Context context) {
        super(context);
        this.a();
    }

    public static EffectPanelJoinerVideoTab a(Context object) {
        object = new EffectPanelJoinerVideoTab_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (ProgressBar)hasViews.findViewById(2131755697);
        this.b = (TextView)hasViews.findViewById(2131755698);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            EffectPanelJoinerVideoTab_.inflate((Context)this.getContext(), (int)2130903221, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

