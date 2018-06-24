package com.smule.singandroid.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.LeaderboardFragment.SortingMethod;
import com.smule.singandroid.list_items.LeaderboardLovedListItem;
import com.smule.singandroid.list_items.LeaderboardRecentListItem;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import java.lang.ref.WeakReference;

public class LeaderboardListAdapter extends MagicAdapter {
    static final /* synthetic */ boolean f20503c = (!LeaderboardListAdapter.class.desiredAssertionStatus());
    private static final String f20504d = LeaderboardListAdapter.class.getName();
    private PerformanceItemListener f20505e;
    private WeakReference<BaseFragment> f20506f;
    private SortingMethod f20507g;

    public LeaderboardListAdapter(BaseFragment baseFragment, MagicDataSource magicDataSource, SortingMethod sortingMethod) {
        super(magicDataSource);
        this.f20506f = new WeakReference(baseFragment);
        this.f20507g = sortingMethod;
        m18035a(baseFragment.getString(C1947R.string.promo_empty_leaderboard));
    }

    public View mo6418a(ViewGroup viewGroup, int i) {
        if (this.f20507g == SortingMethod.MOST_LOVES) {
            return LeaderboardLovedListItem.m24368a(viewGroup.getContext(), (BaseFragment) this.f20506f.get());
        }
        if (this.f20507g == SortingMethod.RECENT) {
            return LeaderboardRecentListItem.m24373a(viewGroup.getContext());
        }
        Log.e(f20504d, "Invalid leaderboard sorting method, unable to choose list item to generate");
        return null;
    }

    public void mo6419a(View view, int i, int i2) {
        if (view instanceof LeaderboardLovedListItem) {
            view = (LeaderboardLovedListItem) view;
            view.setRank(i + 1);
        } else if (view instanceof LeaderboardRecentListItem) {
            LeaderboardRecentListItem leaderboardRecentListItem = (LeaderboardRecentListItem) view;
        } else {
            Log.e(f20504d, "Invalid leaderboard view, unable to bind view");
            view = null;
        }
        PerformanceV2 performanceV2 = (PerformanceV2) m18027a(i);
        if (f20503c || view != null) {
            view.setPerformance(performanceV2);
            view.setListener(this.f20505e);
            return;
        }
        throw new AssertionError();
    }

    public void m22031a(PerformanceItemListener performanceItemListener) {
        this.f20505e = performanceItemListener;
    }

    protected void mo6463a(View view, int i, boolean z) {
    }

    public void mo6681b(View view, int i, int i2) {
    }

    public int getPositionForSection(int i) {
        return 0;
    }

    public int getSectionForPosition(int i) {
        return 0;
    }

    public Object[] getSections() {
        return new Object[0];
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }
}
