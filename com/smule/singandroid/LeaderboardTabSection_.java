/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.LeaderboardTabSection;
import com.smule.singandroid.adapters.LeaderboardListAdapter;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaderboardTabSection_
extends LeaderboardTabSection
implements HasViews,
OnViewChangedListener {
    private boolean e = false;
    private final OnViewChangedNotifier f = new OnViewChangedNotifier();

    public LeaderboardTabSection_(Context context, BaseFragment baseFragment, PerformanceItemInterface.PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        super(context, baseFragment, performanceItemListener, leaderboardListAdapter);
        this.b();
    }

    public static LeaderboardTabSection b(Context object, BaseFragment baseFragment, PerformanceItemInterface.PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        object = new LeaderboardTabSection_((Context)object, baseFragment, performanceItemListener, leaderboardListAdapter);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.f);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.c = (SwipeRefreshLayout)hasViews.findViewById(2131755902);
        this.d = (MagicListView)hasViews.findViewById(2131755903);
        this.a();
    }

    public void onFinishInflate() {
        if (!this.e) {
            this.e = true;
            LeaderboardTabSection_.inflate((Context)this.getContext(), (int)2130903265, (ViewGroup)this);
            this.f.a((HasViews)this);
        }
        super.onFinishInflate();
    }
}

