/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.effectpanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.effectpanel.EffectPanelButtonIcon;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class EffectPanelButtonIcon_
extends EffectPanelButtonIcon
implements HasViews,
OnViewChangedListener {
    private boolean f = false;
    private final OnViewChangedNotifier g = new OnViewChangedNotifier();

    public EffectPanelButtonIcon_(Context context) {
        super(context);
        this.a();
    }

    public EffectPanelButtonIcon_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a();
    }

    public EffectPanelButtonIcon_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a();
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.g);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (ImageView)hasViews.findViewById(2131755658);
        this.b = (ImageView)hasViews.findViewById(2131755175);
        this.c = (IconFontView)hasViews.findViewById(2131755695);
        this.d = (ImageView)hasViews.findViewById(2131755694);
        this.e = (TextView)hasViews.findViewById(2131755696);
    }

    public void onFinishInflate() {
        if (!this.f) {
            this.f = true;
            EffectPanelButtonIcon_.inflate((Context)this.getContext(), (int)2130903220, (ViewGroup)this);
            this.g.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

