/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.view.View
 *  android.view.ViewGroup
 *  com.foound.widget.AmazingListView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import com.foound.widget.AmazingListView;
import com.smule.singandroid.list_items.SongbookListView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SongbookListView_
extends SongbookListView
implements HasViews,
OnViewChangedListener {
    private boolean h = false;
    private final OnViewChangedNotifier i = new OnViewChangedNotifier();

    public SongbookListView_(Context context) {
        super(context);
        this.f();
    }

    public static SongbookListView b(Context object) {
        object = new SongbookListView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void f() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.i);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (AmazingListView)hasViews.findViewById(2131756705);
        this.b = (SwipeRefreshLayout)hasViews.findViewById(2131756704);
        this.d();
    }

    public void onFinishInflate() {
        if (!this.h) {
            this.h = true;
            SongbookListView_.inflate((Context)this.getContext(), (int)2130903422, (ViewGroup)this);
            this.i.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

