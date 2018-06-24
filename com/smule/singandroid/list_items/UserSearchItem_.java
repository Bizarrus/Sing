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
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.UserSearchItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserSearchItem_
extends UserSearchItem
implements HasViews,
OnViewChangedListener {
    private boolean c = false;
    private final OnViewChangedNotifier d = new OnViewChangedNotifier();

    public UserSearchItem_(Context context) {
        super(context);
        this.a();
    }

    private void a() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.d);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public static UserSearchItem b(Context object) {
        object = new UserSearchItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    public void a(HasViews hasViews) {
        this.a = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755821);
        this.b = (TextView)hasViews.findViewById(2131755822);
    }

    public void onFinishInflate() {
        if (!this.c) {
            this.c = true;
            UserSearchItem_.inflate((Context)this.getContext(), (int)2130903459, (ViewGroup)this);
            this.d.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

