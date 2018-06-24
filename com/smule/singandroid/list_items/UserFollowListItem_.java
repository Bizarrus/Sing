/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageButton
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.UserFollowListItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserFollowListItem_
extends UserFollowListItem
implements HasViews,
OnViewChangedListener {
    private boolean a = false;
    private final OnViewChangedNotifier t = new OnViewChangedNotifier();

    public UserFollowListItem_(Context context) {
        super(context);
        this.b();
    }

    public UserFollowListItem_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b();
    }

    public static UserFollowListItem a(Context object) {
        object = new UserFollowListItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.t);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.b = (TextView)hasViews.findViewById(2131755822);
        this.c = (TextView)hasViews.findViewById(2131755823);
        this.d = (TextView)hasViews.findViewById(2131755826);
        this.e = (TextView)hasViews.findViewById(2131755827);
        this.f = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755821);
        this.g = (ImageButton)hasViews.findViewById(2131755777);
        this.h = hasViews.findViewById(2131755820);
        this.i = hasViews.findViewById(2131756775);
        this.j = (TextView)hasViews.findViewById(2131755815);
        this.k = (TextView)hasViews.findViewById(2131755825);
        this.m = hasViews.findViewById(2131755824);
        this.n = (TextView)hasViews.findViewById(2131755830);
        this.o = (TextView)hasViews.findViewById(2131755293);
        this.p = (ProgressBar)hasViews.findViewById(2131755344);
        this.q = hasViews.findViewById(2131755283);
    }

    public void onFinishInflate() {
        if (!this.a) {
            this.a = true;
            UserFollowListItem_.inflate((Context)this.getContext(), (int)2130903456, (ViewGroup)this);
            this.t.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

