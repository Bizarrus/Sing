/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.list_items.UserNameListItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserNameListItem_
extends UserNameListItem
implements HasViews,
OnViewChangedListener {
    private boolean c;
    private final OnViewChangedNotifier d;

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131755804);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            UserNameListItem_.inflate((Context)this.getContext(), (int)2130903458, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

