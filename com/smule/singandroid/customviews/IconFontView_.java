/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.smule.singandroid.customviews.IconFontView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class IconFontView_
extends IconFontView
implements HasViews,
OnViewChangedListener {
    private boolean a = false;
    private final OnViewChangedNotifier b = new OnViewChangedNotifier();

    public IconFontView_(Context context) {
        super(context);
        this.b();
    }

    public IconFontView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public IconFontView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b();
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
            this.b.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

