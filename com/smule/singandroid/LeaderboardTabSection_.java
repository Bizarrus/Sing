package com.smule.singandroid;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.adapters.LeaderboardListAdapter;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaderboardTabSection_ extends LeaderboardTabSection implements HasViews, OnViewChangedListener {
    private boolean f18825e = false;
    private final OnViewChangedNotifier f18826f = new OnViewChangedNotifier();

    public LeaderboardTabSection_(Context context, BaseFragment baseFragment, PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        super(context, baseFragment, performanceItemListener, leaderboardListAdapter);
        m20294b();
    }

    public void onFinishInflate() {
        if (!this.f18825e) {
            this.f18825e = true;
            inflate(getContext(), C1947R.layout.leaderboard_tab_section, this);
            this.f18826f.a(this);
        }
        super.onFinishInflate();
    }

    private void m20294b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18826f);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public static LeaderboardTabSection m20293b(Context context, BaseFragment baseFragment, PerformanceItemListener performanceItemListener, LeaderboardListAdapter leaderboardListAdapter) {
        LeaderboardTabSection leaderboardTabSection_ = new LeaderboardTabSection_(context, baseFragment, performanceItemListener, leaderboardListAdapter);
        leaderboardTabSection_.onFinishInflate();
        return leaderboardTabSection_;
    }

    public void m20295a(HasViews hasViews) {
        this.c = (SwipeRefreshLayout) hasViews.findViewById(C1947R.id.swipe_layout);
        this.d = (MagicListView) hasViews.findViewById(C1947R.id.performance_list_view);
        m20292a();
    }
}
