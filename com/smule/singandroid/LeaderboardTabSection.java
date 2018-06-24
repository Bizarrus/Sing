/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.widget.AbsListView
 *  android.widget.FrameLayout
 *  com.smule.singandroid.utils.ListViewMediaPlayerObserver
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.LeaderboardTabSection_;
import com.smule.singandroid.adapters.LeaderboardListAdapter;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;
import java.lang.ref.WeakReference;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class LeaderboardTabSection
extends FrameLayout {
    WeakReference<BaseFragment> a;
    LeaderboardListAdapter b;
    @ViewById
    protected SwipeRefreshLayout c;
    @ViewById
    public MagicListView d;
    private PerformanceItemInterface.PerformanceItemListener e;

    public LeaderboardTabSection(Context context, BaseFragment baseFragment, PerformanceItemInterface.PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        super(context);
        this.e = performanceItemListener;
        this.a = new WeakReference<BaseFragment>(baseFragment);
        this.b = leaderboardListAdapter;
        this.b.a(this.e);
    }

    public static LeaderboardTabSection a(Context context, BaseFragment baseFragment, PerformanceItemInterface.PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        return LeaderboardTabSection_.b(context, baseFragment, performanceItemListener, leaderboardListAdapter);
    }

    @AfterViews
    protected void a() {
        this.c.setColorSchemeResources(new int[]{2131689905});
        this.d.setSwipeRefreshLayout(this.c);
        this.d.setMagicAdapter(this.b);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isInEditMode()) {
            ListViewMediaPlayerObserver.a((AbsListView)this.d);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!this.isInEditMode()) {
            ListViewMediaPlayerObserver.b((AbsListView)this.d);
        }
    }
}

