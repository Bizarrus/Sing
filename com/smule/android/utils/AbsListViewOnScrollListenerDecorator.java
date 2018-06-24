/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 */
package com.smule.android.utils;

import android.support.annotation.Nullable;
import android.widget.AbsListView;

public abstract class AbsListViewOnScrollListenerDecorator
implements AbsListView.OnScrollListener {
    private AbsListView.OnScrollListener a;

    public AbsListViewOnScrollListenerDecorator() {
        this(null);
    }

    public AbsListViewOnScrollListenerDecorator(@Nullable AbsListView.OnScrollListener onScrollListener) {
        this.a(onScrollListener);
    }

    public void a(@Nullable AbsListView.OnScrollListener onScrollListener) {
        this.a = onScrollListener;
    }
}

