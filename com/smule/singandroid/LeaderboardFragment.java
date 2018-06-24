/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.support.design.widget.TabLayout
 *  android.support.design.widget.TabLayout$OnTabSelectedListener
 *  android.support.design.widget.TabLayout$Tab
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.view.View
 *  android.view.ViewGroup
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.datasources.LeaderboardDataSource;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.LeaderboardFragment_;
import com.smule.singandroid.LeaderboardTabSection;
import com.smule.singandroid.adapters.LeaderboardListAdapter;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.profile.ProfileFragment;
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
public class LeaderboardFragment
extends BaseFragment {
    public static final String g = LeaderboardFragment.class.getName();
    private static final SortingMethod[] m = new SortingMethod[]{SortingMethod.a, SortingMethod.b};
    @ViewById
    protected ViewPager h;
    @ViewById
    protected TabLayout i;
    @FragmentArg
    @InstanceState
    protected long j;
    @InstanceState
    protected String k;
    protected LeaderboardPagerAdapter l;
    private SingTabLayoutHelper n;
    private HashMap<SortingMethod, LeaderboardListAdapter> o = new HashMap();
    private PerformanceItemInterface.PerformanceItemListener p;

    public LeaderboardFragment() {
        this.p = new PerformanceItemInterface.PerformanceItemListener(){

            @Override
            public void a(MediaPlayingViewInterface object, AccountIcon accountIcon) {
                object = ProfileFragment.a(accountIcon);
                LeaderboardFragment.this.a((BaseFragment)((Object)object));
            }

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                if (mediaPlayingViewInterface instanceof SquarePerformanceItem) {
                    // empty if block
                }
                boolean bl = MiscUtils.a((PerformanceV2)performanceV2);
                LeaderboardFragment.this.a(performanceV2, bl, true);
            }

            @Override
            public void b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                MediaPlayerServiceController.a().a(performanceV2.performanceKey);
                boolean bl = MiscUtils.a((PerformanceV2)performanceV2);
                LeaderboardFragment.this.a(performanceV2, bl, true);
            }
        };
    }

    public static LeaderboardFragment a(long l) {
        return LeaderboardFragment_.F().a(l).a();
    }

    private LeaderboardListAdapter a(SortingMethod sortingMethod) {
        LeaderboardListAdapter leaderboardListAdapter;
        LeaderboardListAdapter leaderboardListAdapter2 = leaderboardListAdapter = this.o.get((Object)sortingMethod);
        if (leaderboardListAdapter == null) {
            leaderboardListAdapter2 = new LeaderboardListAdapter(this, new LeaderboardDataSource((Context)this.getActivity(), this.j, sortingMethod.a()), sortingMethod);
            this.o.put(sortingMethod, leaderboardListAdapter2);
        }
        return leaderboardListAdapter2;
    }

    private void a(TabLayout.Tab tab) {
        this.n.a(true, tab);
    }

    private void b(TabLayout.Tab tab) {
        this.n.a(false, tab);
    }

    @Override
    protected void A() {
        SingAnalytics.c((long)this.j);
    }

    @AfterViews
    public void a() {
        this.i.setSelectedTabIndicatorColor(this.getResources().getColor(2131689579));
        this.i.setSelectedTabIndicatorHeight(this.getResources().getDimensionPixelOffset(2131428171));
        this.l = new LeaderboardPagerAdapter((Context)this.getActivity());
        this.h.setAdapter((PagerAdapter)this.l);
        this.n = new SingTabLayoutHelper(this.i, this.h);
        this.n.a(true);
        this.n.a(new TabLayout.OnTabSelectedListener(){

            public void onTabReselected(TabLayout.Tab tab) {
                LeaderboardFragment.this.a(tab);
            }

            public void onTabSelected(TabLayout.Tab tab) {
                LeaderboardFragment.this.a(tab);
            }

            public void onTabUnselected(TabLayout.Tab tab) {
                LeaderboardFragment.this.b(tab);
            }
        });
        this.i.getTabAt(0).select();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.k = MagicPreferences.a((Context)this.getActivity(), this.j);
        object = this.k == null ? this.getString(2131297220) : "#" + this.k;
        this.k = object;
        this.a(BaseFragment.ActionBarHighlightMode.b);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.n = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.a((CharSequence)this.k);
    }

    @Override
    public String x() {
        return g;
    }

    private class LeaderboardPagerAdapter
    extends PagerAdapter {
        private WeakReference<Context> b;

        public LeaderboardPagerAdapter(Context context) {
            this.b = new WeakReference<Context>(context);
        }

        public int getCount() {
            return m.length;
        }

        public int getItemPosition(Object object) {
            return -2;
        }

        public CharSequence getPageTitle(int n) {
            if (n >= m.length) {
                Log.e(LeaderboardFragment.g, "Index out of bounds when trying to get tab title");
                return "";
            }
            return LeaderboardFragment.this.getResources().getString(m[n].b());
        }

        public Object instantiateItem(ViewGroup viewGroup, int n) {
            Object object = LeaderboardFragment.this.a(m[n]);
            object = LeaderboardTabSection.a(this.b.get(), LeaderboardFragment.this, LeaderboardFragment.this.p, (LeaderboardListAdapter)object);
            viewGroup.addView((View)object);
            return object;
        }

        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }

    public static enum SortingMethod {
        a("MOST_LOVES", 2131297219),
        b("RECENT", 2131296711);
        
        private final String c;
        private final int d;

        private SortingMethod(String string3, int n2) {
            this.c = string3;
            this.d = n2;
        }

        public String a() {
            return this.c;
        }

        public int b() {
            return this.d;
        }

        public String toString() {
            return this.a();
        }
    }

}

