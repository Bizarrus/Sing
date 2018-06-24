/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.customviews.QuickSelectControlItemView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class QuickSelectControlItemView_
extends QuickSelectControlItemView
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public QuickSelectControlItemView_(Context context) {
        super(context);
        this.a();
    }

    public static QuickSelectControlItemView a(Context object) {
        object = new QuickSelectControlItemView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (IconFontView)hasViews.findViewById(2131756425);
        this.b = (IconFontView)hasViews.findViewById(2131755659);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            QuickSelectControlItemView_.inflate((Context)this.getContext(), (int)2130903382, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

