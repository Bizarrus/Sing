/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.singflow;

import android.content.Context;
import android.view.ViewGroup;
import com.smule.singandroid.singflow.ContinueWithAudioCoachmark;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ContinueWithAudioCoachmark_
extends ContinueWithAudioCoachmark
implements HasViews,
OnViewChangedListener {
    private boolean a = false;
    private final OnViewChangedNotifier b = new OnViewChangedNotifier();

    public ContinueWithAudioCoachmark_(Context context, int n) {
        super(context, n);
        this.b();
    }

    public static ContinueWithAudioCoachmark a(Context object, int n) {
        object = new ContinueWithAudioCoachmark_((Context)object, n);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.b);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a();
    }

    public void onFinishInflate() {
        if (!this.a) {
            this.a = true;
            ContinueWithAudioCoachmark_.inflate((Context)this.getContext(), (int)2130903189, (ViewGroup)this);
            this.b.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

