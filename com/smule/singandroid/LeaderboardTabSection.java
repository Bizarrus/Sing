package com.smule.singandroid;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.FrameLayout;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.adapters.LeaderboardListAdapter;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;
import java.lang.ref.WeakReference;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class LeaderboardTabSection extends FrameLayout {
    WeakReference<BaseFragment> f18820a;
    LeaderboardListAdapter f18821b;
    @ViewById
    protected SwipeRefreshLayout f18822c;
    @ViewById
    public MagicListView f18823d;
    private PerformanceItemListener f18824e;

    public LeaderboardTabSection(Context context, BaseFragment baseFragment, PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        super(context);
        this.f18824e = performanceItemListener;
        this.f18820a = new WeakReference(baseFragment);
        this.f18821b = leaderboardListAdapter;
        this.f18821b.m22031a(this.f18824e);
    }

    public static LeaderboardTabSection m20291a(Context context, BaseFragment baseFragment, PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        return LeaderboardTabSection_.m20293b(context, baseFragment, performanceItemListener, leaderboardListAdapter);
    }

    @AfterViews
    protected void m20292a() {
        this.f18822c.setColorSchemeResources(new int[]{C1947R.color.refresh_icon});
        this.f18823d.setSwipeRefreshLayout(this.f18822c);
        this.f18823d.setMagicAdapter(this.f18821b);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            ListViewMediaPlayerObserver.m25861a(this.f18823d);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isInEditMode()) {
            ListViewMediaPlayerObserver.m25862b(this.f18823d);
        }
    }
}
