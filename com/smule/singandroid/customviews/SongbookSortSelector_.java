/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Spinner
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import com.smule.singandroid.customviews.SongbookSortSelector;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SongbookSortSelector_
extends SongbookSortSelector
implements HasViews,
OnViewChangedListener {
    private boolean e = false;
    private final OnViewChangedNotifier f = new OnViewChangedNotifier();

    public SongbookSortSelector_(Context context) {
        super(context);
        this.c();
    }

    public SongbookSortSelector_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c();
    }

    private void c() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.f);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (Spinner)hasViews.findViewById(2131756729);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.e) {
            this.e = true;
            SongbookSortSelector_.inflate((Context)this.getContext(), (int)2130903432, (ViewGroup)this);
            this.f.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

