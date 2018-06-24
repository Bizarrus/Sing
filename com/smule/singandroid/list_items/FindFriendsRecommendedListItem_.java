/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.FindFriendsRecommendedListItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsRecommendedListItem_
extends FindFriendsRecommendedListItem
implements HasViews,
OnViewChangedListener {
    private boolean t = false;
    private final OnViewChangedNotifier u = new OnViewChangedNotifier();

    public FindFriendsRecommendedListItem_(Context context) {
        super(context);
        this.b();
    }

    public static FindFriendsRecommendedListItem b(Context object) {
        object = new FindFriendsRecommendedListItem_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.u);
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
        if (!this.t) {
            this.t = true;
            FindFriendsRecommendedListItem_.inflate((Context)this.getContext(), (int)2130903246, (ViewGroup)this);
            this.u.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

