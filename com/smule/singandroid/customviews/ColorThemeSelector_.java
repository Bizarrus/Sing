/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.LinearLayout
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smule.singandroid.customviews.ColorThemeSelector;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ColorThemeSelector_
extends ColorThemeSelector
implements HasViews,
OnViewChangedListener {
    private boolean b = false;
    private final OnViewChangedNotifier c = new OnViewChangedNotifier();

    public ColorThemeSelector_(Context context) {
        super(context);
        this.b();
    }

    public ColorThemeSelector_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public ColorThemeSelector_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.c);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (LinearLayout)hasViews.findViewById(2131755467);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.b) {
            this.b = true;
            ColorThemeSelector_.inflate((Context)this.getContext(), (int)2130903123, (ViewGroup)this);
            this.c.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

