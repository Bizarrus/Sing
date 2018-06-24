/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.AbsListView
 *  com.smule.singandroid.utils.ListViewMediaPlayerObserver
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;

public class MediaListView
extends MagicListView {
    public MediaListView(Context context) {
        this(context, null);
    }

    public MediaListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130772257);
    }

    public MediaListView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isInEditMode()) {
            ListViewMediaPlayerObserver.a((AbsListView)this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!this.isInEditMode()) {
            ListViewMediaPlayerObserver.b((AbsListView)this);
        }
    }
}

