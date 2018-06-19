package com.smule.singandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.datasources.LeaderboardDataSource;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.BaseFragment.ActionBarHighlightMode;
import com.smule.singandroid.adapters.LeaderboardListAdapter;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class LeaderboardFragment extends BaseFragment {
    public static final String f18808e = LeaderboardFragment.class.getName();
    private static final SortingMethod[] f18809k = new SortingMethod[]{SortingMethod.MOST_LOVES, SortingMethod.RECENT};
    @ViewById
    protected ViewPager f18810f;
    @ViewById
    protected TabLayout f18811g;
    @FragmentArg
    @InstanceState
    protected long f18812h;
    @InstanceState
    protected String f18813i;
    protected LeaderboardPagerAdapter f18814j;
    private SingTabLayoutHelper f18815l;
    private HashMap<SortingMethod, LeaderboardListAdapter> f18816m = new HashMap();
    private PerformanceItemListener f18817n = new C38622(this);

    class C38611 implements OnTabSelectedListener {
        final /* synthetic */ LeaderboardFragment f18799a;

        C38611(LeaderboardFragment leaderboardFragment) {
            this.f18799a = leaderboardFragment;
        }

        public void onTabSelected(Tab tab) {
            this.f18799a.m20274a(tab);
        }

        public void onTabUnselected(Tab tab) {
            this.f18799a.m20276b(tab);
        }

        public void onTabReselected(Tab tab) {
            this.f18799a.m20274a(tab);
        }
    }

    class C38622 implements PerformanceItemListener {
        final /* synthetic */ LeaderboardFragment f18800a;

        C38622(LeaderboardFragment leaderboardFragment) {
            this.f18800a = leaderboardFragment;
        }

        public void mo6472a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
            if (mediaPlayingViewInterface instanceof SquarePerformanceItem) {
                this.f18800a.mo6486a(performanceV2, MiscUtils.m25895a(performanceV2), true);
            } else {
                this.f18800a.mo6486a(performanceV2, MiscUtils.m25895a(performanceV2), true);
            }
        }

        public void mo6471a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
            this.f18800a.mo6487a(ProfileFragment.m21036a(accountIcon));
        }

        public void mo6473b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
            MediaPlayerServiceController.m24641a().m24654a(performanceV2.performanceKey);
            this.f18800a.mo6486a(performanceV2, MiscUtils.m25895a(performanceV2), true);
        }
    }

    private class LeaderboardPagerAdapter extends PagerAdapter {
        final /* synthetic */ LeaderboardFragment f18801a;
        private WeakReference<Context> f18802b;

        public LeaderboardPagerAdapter(LeaderboardFragment leaderboardFragment, Context context) {
            this.f18801a = leaderboardFragment;
            this.f18802b = new WeakReference(context);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View a = LeaderboardTabSection.m20291a((Context) this.f18802b.get(), this.f18801a, this.f18801a.f18817n, this.f18801a.m20271a(LeaderboardFragment.f18809k[i]));
            viewGroup.addView(a);
            return a;
        }

        public int getCount() {
            return LeaderboardFragment.f18809k.length;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view.equals(obj);
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public CharSequence getPageTitle(int i) {
            if (i < LeaderboardFragment.f18809k.length) {
                return this.f18801a.getResources().getString(LeaderboardFragment.f18809k[i].m20269b());
            }
            Log.e(LeaderboardFragment.f18808e, "Index out of bounds when trying to get tab title");
            return "";
        }
    }

    public enum SortingMethod {
        MOST_LOVES("MOST_LOVES", C1947R.string.promo_most_loved),
        RECENT("RECENT", C1947R.string.core_recent);
        
        private final String f18806c;
        private final int f18807d;

        private SortingMethod(String str, int i) {
            this.f18806c = str;
            this.f18807d = i;
        }

        public String toString() {
            return m20268a();
        }

        public String m20268a() {
            return this.f18806c;
        }

        public int m20269b() {
            return this.f18807d;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f18813i = MagicPreferences.m20299a(getActivity(), this.f18812h);
        this.f18813i = this.f18813i == null ? getString(C1947R.string.promo_promotion) : "#" + this.f18813i;
        m19831a(ActionBarHighlightMode.ALWAYS);
    }

    public void onResume() {
        super.onResume();
        mo6861a(this.f18813i);
    }

    @AfterViews
    public void m20279a() {
        this.f18811g.setSelectedTabIndicatorColor(getResources().getColor(C1947R.color.button_text_inverse));
        this.f18811g.setSelectedTabIndicatorHeight(getResources().getDimensionPixelOffset(C1947R.dimen.margin_extra_tiny));
        this.f18814j = new LeaderboardPagerAdapter(this, getActivity());
        this.f18810f.setAdapter(this.f18814j);
        this.f18815l = new SingTabLayoutHelper(this.f18811g, this.f18810f);
        this.f18815l.m11744a(true);
        this.f18815l.m11740a(new C38611(this));
        this.f18811g.getTabAt(0).select();
    }

    private void m20274a(Tab tab) {
        this.f18815l.m23479a(true, tab);
    }

    private void m20276b(Tab tab) {
        this.f18815l.m23479a(false, tab);
    }

    private LeaderboardListAdapter m20271a(SortingMethod sortingMethod) {
        LeaderboardListAdapter leaderboardListAdapter = (LeaderboardListAdapter) this.f18816m.get(sortingMethod);
        if (leaderboardListAdapter != null) {
            return leaderboardListAdapter;
        }
        leaderboardListAdapter = new LeaderboardListAdapter(this, new LeaderboardDataSource(getActivity(), this.f18812h, sortingMethod.m20268a()), sortingMethod);
        this.f18816m.put(sortingMethod, leaderboardListAdapter);
        return leaderboardListAdapter;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18815l = null;
    }

    protected void mo6420v() {
        SingAnalytics.m26132c(this.f18812h);
    }

    public static LeaderboardFragment m20270a(long j) {
        return LeaderboardFragment_.m20284A().m20283a(j).m20282a();
    }

    public String mo6383s() {
        return f18808e;
    }
}
