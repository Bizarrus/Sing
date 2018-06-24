/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.util.Pair
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsPageView;
import com.smule.singandroid.FindFriendsRecommendedPageView_;
import com.smule.singandroid.customviews.FindFriendsRecommendedListHeader;
import com.smule.singandroid.datasource.RecommendedFriendsDataSource;
import com.smule.singandroid.list_items.FindFriendsRecommendedListItem;
import com.smule.singandroid.list_items.RecUserFollowListItem;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FindFriendsRecommendedPageView
extends FindFriendsPageView {
    private static final String n = FindFriendsRecommendedPageView.class.getName();
    @ViewById
    protected MagicListView a;
    protected MagicAdapter b;
    protected RecommendedFriendsDataSource c;
    protected boolean d = false;
    protected FindFriendsRecommendedListHeader e;
    protected int f;
    protected int g;
    protected int h;
    protected int i;
    protected int j = -1;
    protected boolean k = false;

    public FindFriendsRecommendedPageView(Context context) {
        super(context);
        this.l = context;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Pair<String, String> a(int n, int n2) {
        int n3;
        int n4;
        block9 : {
            block8 : {
                n4 = 0;
                if (this.a == null) return null;
                if (this.b == null || this.c == null) break block8;
                int n5 = Math.min(0, n - this.a.getHeaderViewsCount()) * -1;
                n3 = n - this.a.getHeaderViewsCount();
                n = n2;
                if (n5 > 0) {
                    n = n2 - n5;
                    n3 = 0;
                }
                if (n <= 0) return null;
                if (this.c.k() > n3 + n) break block9;
            }
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        n2 = n4;
        do {
            if (n2 >= n) {
                this.h = n3;
                this.i = n;
                this.f = this.h;
                this.g = this.i;
                return new Pair((Object)stringBuilder2.toString(), (Object)stringBuilder.toString());
            }
            RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon = (RecommendationManager.RecommendedSingersResponse.RecAccountIcon)this.c.a(n3 + n2);
            if (recAccountIcon.mRecId != null) {
                stringBuilder.append(n3 + n2);
                stringBuilder2.append(recAccountIcon.mRecId);
                if (n2 + 1 < n) {
                    stringBuilder.append(",");
                    stringBuilder2.append(",");
                }
            }
            ++n2;
        } while (true);
    }

    public static FindFriendsRecommendedPageView a(Context context, BaseFragment baseFragment) {
        FindFriendsRecommendedPageView findFriendsRecommendedPageView = FindFriendsRecommendedPageView_.a(context);
        findFriendsRecommendedPageView.l = context;
        findFriendsRecommendedPageView.m = baseFragment;
        ReferenceMonitor.a().a((Object)findFriendsRecommendedPageView);
        return findFriendsRecommendedPageView;
    }

    private void a(View object, final int n, int n2) {
        object = (RecUserFollowListItem)((Object)object);
        final RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon = (RecommendationManager.RecommendedSingersResponse.RecAccountIcon)this.c.a(n);
        if (recAccountIcon == null) {
            Log.e(FindFriendsRecommendedPageView.n, "bindRecAccountItemView: Unable to bind, recAccountIcon is null");
            return;
        }
        object.a((Context)this.m.getActivity(), recAccountIcon, n, false, true, new UserFollowListItem.UserFollowListItemListener(){

            @Override
            public void a(Analytics searchResultClkContext) {
                com.smule.android.logging.Analytics.a(recAccountIcon.mRecId, Analytics.d, n, Analytics.c, null);
            }

            @Override
            public void a(ProfileFragment profileFragment) {
                if (FindFriendsRecommendedPageView.this.d) {
                    FindFriendsRecommendedPageView.this.m.p().a(profileFragment, profileFragment.t());
                }
            }

            @Override
            public void a(boolean bl, boolean bl2) {
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void f() {
        block3 : {
            block2 : {
                if (this.l == null || this.a == null || this.b == null || this.c == null) break block2;
                Pair<String, String> pair = LayoutUtils.a((Context)this.l) ? this.a(0, this.a.getHeaderViewsCount() + 4) : this.a(0, this.a.getHeaderViewsCount() + 3);
                if (pair != null) break block3;
            }
            return;
        }
        com.smule.android.logging.Analytics.a((String)pair.first, (String)pair.second, Analytics.d, Analytics.c, null);
    }

    @Override
    public void a() {
        this.c = new RecommendedFriendsDataSource(25);
        this.b = new MagicAdapter(this.c){

            @Override
            public View a(ViewGroup viewGroup, int n) {
                return FindFriendsRecommendedListItem.a(FindFriendsRecommendedPageView.this.l);
            }

            @Override
            public void a(View view, int n, int n2) {
                FindFriendsRecommendedPageView.this.a(view, n, n2);
            }

            @Override
            public void c(MagicDataSource magicDataSource) {
                super.c(magicDataSource);
                FindFriendsRecommendedPageView.this.e();
                if (!FindFriendsRecommendedPageView.this.k) {
                    FindFriendsRecommendedPageView.this.k = true;
                    FindFriendsRecommendedPageView.this.f();
                }
            }
        };
        this.e = FindFriendsRecommendedListHeader.a(this.l);
        this.a.setMagicAdapter(this.b);
        this.d();
        this.e();
    }

    public void a(boolean bl) {
        this.d = bl;
    }

    public void b() {
        if (this.e != null) {
            this.e.b();
        }
    }

    @Override
    public void c() {
        SingAnalytics.B();
        this.k = false;
        if (this.c != null && this.c.i() == MagicDataSource.DataState.b && this.c.k() > 0) {
            this.k = true;
            this.f();
        }
    }

    protected void d() {
        this.a.setOnScrollListener(new AbsListView.OnScrollListener(){

            private void a() {
                Pair pair;
                if (FindFriendsRecommendedPageView.this.i > 0 && FindFriendsRecommendedPageView.this.j == 0 && (FindFriendsRecommendedPageView.this.h != FindFriendsRecommendedPageView.this.f || FindFriendsRecommendedPageView.this.i != FindFriendsRecommendedPageView.this.g) && (pair = FindFriendsRecommendedPageView.this.a(FindFriendsRecommendedPageView.this.h, FindFriendsRecommendedPageView.this.i)) != null) {
                    com.smule.android.logging.Analytics.a((String)pair.first, (String)pair.second, Analytics.d, Analytics.c, null);
                }
            }

            public void onScroll(AbsListView absListView, int n, int n2, int n3) {
                FindFriendsRecommendedPageView.this.h = n;
                FindFriendsRecommendedPageView.this.i = n2;
            }

            public void onScrollStateChanged(AbsListView absListView, int n) {
                FindFriendsRecommendedPageView.this.j = n;
                this.a();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void e() {
        if (!this.h()) {
            Log.d(n, "updateHeaderView - Fragment is not added; aborting refresh");
            return;
        } else {
            if (this.b == null) {
                Log.b(n, "mRecommendedFriendsAdapter was null, not ready to update follow header");
                return;
            }
            if (this.a.getHeaderViewsCount() != 0) return;
            {
                this.a.setMagicAdapter(null);
                this.a.addHeaderView((View)this.e);
                this.a.setMagicAdapter(this.b);
                this.d();
                return;
            }
        }
    }

}

