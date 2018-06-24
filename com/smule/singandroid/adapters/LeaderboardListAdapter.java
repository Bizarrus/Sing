/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 */
package com.smule.singandroid.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.LeaderboardFragment;
import com.smule.singandroid.list_items.LeaderboardLovedListItem;
import com.smule.singandroid.list_items.LeaderboardRecentListItem;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import java.lang.ref.WeakReference;

public class LeaderboardListAdapter
extends MagicAdapter {
    static final /* synthetic */ boolean c;
    private static final String d;
    private PerformanceItemInterface.PerformanceItemListener e;
    private WeakReference<BaseFragment> f;
    private LeaderboardFragment.SortingMethod g;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !LeaderboardListAdapter.class.desiredAssertionStatus();
        c = bl;
        d = LeaderboardListAdapter.class.getName();
    }

    public LeaderboardListAdapter(BaseFragment baseFragment, MagicDataSource magicDataSource, LeaderboardFragment.SortingMethod sortingMethod) {
        super(magicDataSource);
        this.f = new WeakReference<BaseFragment>(baseFragment);
        this.g = sortingMethod;
        this.a(baseFragment.getString(2131297216));
    }

    @Override
    public View a(ViewGroup viewGroup, int n) {
        if (this.g == LeaderboardFragment.SortingMethod.a) {
            return LeaderboardLovedListItem.a(viewGroup.getContext(), this.f.get());
        }
        if (this.g == LeaderboardFragment.SortingMethod.b) {
            return LeaderboardRecentListItem.a(viewGroup.getContext());
        }
        Log.e(d, "Invalid leaderboard sorting method, unable to choose list item to generate");
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(View object, int n, int n2) {
        if (object instanceof LeaderboardLovedListItem) {
            object = (LeaderboardLovedListItem)object;
            object.setRank(n + 1);
        } else if (object instanceof LeaderboardRecentListItem) {
            object = (LeaderboardRecentListItem)object;
        } else {
            Log.e(d, "Invalid leaderboard view, unable to bind view");
            object = null;
        }
        PerformanceV2 performanceV2 = (PerformanceV2)this.a(n);
        if (!c && object == null) {
            throw new AssertionError();
        }
        object.setPerformance(performanceV2);
        object.setListener(this.e);
    }

    @Override
    protected void a(View view, int n, boolean bl) {
    }

    public void a(PerformanceItemInterface.PerformanceItemListener performanceItemListener) {
        this.e = performanceItemListener;
    }

    @Override
    public void b(View view, int n, int n2) {
    }

    @Override
    public int getPositionForSection(int n) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int n) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int n) {
    }
}

