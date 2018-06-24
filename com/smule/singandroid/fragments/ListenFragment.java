/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Point
 *  android.os.Bundle
 *  android.os.Handler
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  android.util.Pair
 *  android.view.Display
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.WindowManager
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.GridView
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  com.smule.singandroid.songbook_search.SearchFragment
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.ListViewMediaPlayerObserver
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PlaylistManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Playlist;
import com.smule.android.network.models.RecPerformanceIcon;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.cards.ListenCard;
import com.smule.singandroid.customviews.PaginatedAdapter;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.fragments.ListenFragment_;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class ListenFragment
extends BaseFragment {
    public static final String g = ListenFragment.class.getName();
    private PerformanceItemInterface.PerformanceItemListener A;
    private Runnable B;
    private Runnable C;
    @ViewById
    protected View h;
    @ViewById
    protected View i;
    @ViewById
    protected TextView j;
    @ViewById
    protected View k;
    @ViewById
    protected TextView l;
    @ViewById
    protected GridView m;
    int n;
    int o;
    int p;
    int q;
    int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private Observer v;
    private PerformancesListAdapter w;
    private ArrayList<PerformanceListItemContainer> x;
    private Playlist y;
    private int z;

    public ListenFragment() {
        this.w = new PerformancesListAdapter();
        this.x = new ArrayList();
        this.r = -1;
        this.A = new PerformanceItemInterface.PerformanceItemListener(){

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
            }

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                int n = -1;
                if (mediaPlayingViewInterface instanceof SquarePerformanceItem) {
                    n = (Integer)((SquarePerformanceItem)mediaPlayingViewInterface).getTag(2131623961);
                }
                SingAnalytics.a((Playlist)ListenFragment.this.y, (int)n, (String)PerformanceV2Util.f((PerformanceV2)performanceV2), (String)performanceV2.performanceKey, (String)PerformanceV2Util.h((PerformanceV2)performanceV2), (boolean)performanceV2.video);
                com.smule.android.logging.Analytics.a(((SquarePerformanceItem)mediaPlayingViewInterface).getRecId(), Analytics.b, n, Analytics.i, null);
                ListenFragment.this.a(performanceV2);
            }

            @Override
            public void b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                ListenFragment.this.a(performanceV2);
            }
        };
    }

    private void H() {
        float f = this.getResources().getDimensionPixelSize(2131427376);
        float f2 = LayoutUtils.a((Display)this.getActivity().getWindowManager().getDefaultDisplay()).x;
        float f3 = this.getResources().getDimensionPixelSize(2131428182);
        float f4 = Float.MAX_VALUE;
        int n = 1;
        float f5 = 0.0f;
        int n2 = 0;
        while (f4 >= f) {
            this.z = (int)f4;
            int n3 = n2 + 1;
            f4 = f3 = f2 / (3.0f + 21.0f * (float)n3);
            float f6 = 20.0f * f3;
            f3 = f5;
            f5 = f4;
            n = n2;
            n2 = n3;
            f4 = f6;
        }
        this.m.setNumColumns(n);
        this.m.setColumnWidth(this.z);
        this.m.setPadding((int)(f3 * 2.0f), (int)f3, (int)(f3 * 2.0f), 0);
        this.m.setHorizontalSpacing((int)f3);
        this.m.setVerticalSpacing((int)f3);
    }

    private void I() {
        this.h.setVisibility(4);
        this.i.setVisibility(4);
        this.k.setVisibility(4);
    }

    private void J() {
        this.w.d();
        this.m.setAdapter((ListAdapter)this.w);
        this.w.notifyDataSetChanged();
        this.a((AbsListView)this.m, QuickReturnListViewMenuSyncer.ActionBarHighlightMode.a, new AbsListView.OnScrollListener(){

            private void a() {
                if (ListenFragment.this.q > 0 && ListenFragment.this.r == 0 && (ListenFragment.this.p != ListenFragment.this.n || ListenFragment.this.q != ListenFragment.this.o)) {
                    ListenFragment.this.K();
                }
            }

            public void onScroll(AbsListView absListView, int n, int n2, int n3) {
                ListenFragment.this.p = n;
                ListenFragment.this.q = n2;
            }

            public void onScrollStateChanged(AbsListView absListView, int n) {
                ListenFragment.this.r = n;
                this.a();
            }
        });
    }

    private boolean K() {
        Pair<String, String> pair;
        if (this.p == -1) {
            this.p = 0;
        }
        if (this.q == -1) {
            this.q = Math.min(this.x.size(), 4);
        }
        if ((pair = this.a(this.p, this.q)) != null) {
            com.smule.android.logging.Analytics.a((String)pair.first, (String)pair.second, Analytics.b, Analytics.i, null);
        }
        if (pair != null) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Pair<String, String> a(int n, int n2) {
        if (this.m == null || this.m.getAdapter() == null || n2 <= 0 || this.x.size() < n + n2) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        StringBuilder stringBuilder2 = new StringBuilder("");
        int n3 = 0;
        do {
            if (n3 >= n2) {
                this.n = this.p;
                this.o = this.q;
                return new Pair((Object)stringBuilder2.toString(), (Object)stringBuilder.toString());
            }
            PerformanceListItemContainer performanceListItemContainer = this.x.get(n + n3);
            stringBuilder.append(n + n3);
            stringBuilder2.append(performanceListItemContainer.c);
            if (n3 + 1 < n2) {
                stringBuilder.append(",");
                stringBuilder2.append(",");
            }
            ++n3;
        } while (true);
    }

    public static ListenFragment a() {
        return new ListenFragment_();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(@NonNull PerformanceV2 performanceV2) {
        int n;
        PerformancesListAdapter performancesListAdapter = this.w;
        int n2 = performancesListAdapter.getCount();
        ArrayList<MediaPlayingPlayable> arrayList = new ArrayList<MediaPlayingPlayable>(n2);
        int n3 = -1;
        for (n = 0; n < n2; ++n) {
            PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer)performancesListAdapter.getItem(n);
            if (performanceListItemContainer == null || performanceListItemContainer.a() == null) {
                Log.e(g, "cannot do ContinuousPlay with null performances");
                continue;
            }
            PerformanceV2 performanceV22 = performanceListItemContainer.a();
            arrayList.add(new MediaPlayingPlayable(performanceV22));
            if (TextUtils.isEmpty((CharSequence)performanceV2.performanceKey) || !performanceV2.performanceKey.equals(performanceV22.performanceKey)) continue;
            n3 = arrayList.size() - 1;
        }
        n = n3;
        if (n3 == -1) {
            Log.e(g, "playable not found in adapter, adding it to top of playlist");
            arrayList.add(0, new MediaPlayingPlayable(performanceV2));
            n = 0;
        }
        this.a(arrayList, n);
    }

    private void a(PerformanceItemInterface performanceItemInterface, PerformanceListItemContainer performanceListItemContainer) {
        if (performanceItemInterface == null) {
            Log.e(g, "configurePerformanceItem - performanceItem is null!");
            return;
        }
        if (performanceListItemContainer == null) {
            Log.e(g, "configurePerformanceItem - performanceListItemContainer is null!");
            return;
        }
        if (performanceListItemContainer.b != null) {
            performanceItemInterface.setPerformance(performanceListItemContainer.a());
        }
        if (performanceItemInterface instanceof SquarePerformanceItem) {
            ((SquarePerformanceItem)performanceItemInterface).setRecId(performanceListItemContainer.c);
        }
        performanceItemInterface.setListener(this.A);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void A() {
        SingAnalytics.c();
        if (this.y != null) {
            SingAnalytics.a((Playlist)this.y);
        } else {
            this.B = new Runnable(){

                @Override
                public void run() {
                    if (!ListenFragment.this.isAdded()) {
                        return;
                    }
                    ListenFragment.this.B = null;
                    SingAnalytics.a((Playlist)ListenFragment.this.y);
                }
            };
        }
        if (this.t) {
            this.K();
            return;
        }
        this.C = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                if (!ListenFragment.this.isAdded() || !ListenFragment.this.t) {
                    return;
                }
                ListenFragment.this.K();
            }
        };
    }

    @Override
    protected boolean D() {
        return false;
    }

    public void F() {
        this.I();
        this.x.clear();
        this.t = false;
        this.s = false;
        this.w.f();
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void G() {
        int n = 0;
        int n2 = this.s && this.x.isEmpty() ? 1 : 0;
        View view = this.h;
        int n3 = n2 != 0 ? 4 : 0;
        view.setVisibility(n3);
        view = this.i;
        n2 = n2 != 0 ? n : 4;
        view.setVisibility(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    public void a(ArrayList<RecPerformanceIcon> iterator) {
        if (this.s) {
            return;
        }
        if (iterator != null) {
            ArrayList<PerformanceListItemContainer> arrayList = new ArrayList<PerformanceListItemContainer>();
            iterator = iterator.iterator();
            while (iterator.hasNext()) {
                arrayList.add(new PerformanceListItemContainer((RecPerformanceIcon)iterator.next()));
            }
            if (this.x == null || this.x.isEmpty()) {
                this.x = arrayList;
            } else {
                this.x.addAll(arrayList);
            }
            this.w.notifyDataSetChanged();
            if (this.C != null) {
                new Handler().post(this.C);
            }
        }
        this.G();
        int n = this.x == null ? 0 : this.x.size();
        this.d(n);
    }

    @SupposeUiThread
    public void b(ArrayList<RecPerformanceIcon> arrayList) {
        this.s = false;
        this.t = true;
        this.a(arrayList);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void d(int n) {
        View view;
        int n2 = 4;
        n = n == 0 ? 1 : 0;
        if (!this.s) {
            view = this.k;
            int n3 = n != 0 ? 0 : 4;
            view.setVisibility(n3);
        } else {
            this.k.setVisibility(4);
        }
        view = this.h;
        n = n != 0 ? n2 : 0;
        view.setVisibility(n);
        this.m.setVisibility(0);
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public boolean f() {
        return true;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    protected boolean m() {
        return true;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        Log.b(g, "onCreateOptionsMenu");
        menuInflater.inflate(2131820552, menu2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 2131756839: 
        }
        SingAnalytics.a(Analytics.b);
        this.a((BaseFragment)SearchFragment.F());
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.u();
        if (!this.s && !this.t || this.u) {
            this.F();
            this.u = false;
        }
        this.w.notifyDataSetInvalidated();
    }

    @Override
    public void onStart() {
        Observer observer;
        super.onStart();
        this.c(2131296697);
        NotificationCenter notificationCenter = NotificationCenter.a();
        this.v = observer = new Observer(){

            @Override
            public void update(Observable object, Object hashSet) {
                object = (String)((Object)hashSet);
                Log.b(ListenFragment.g, "mLoveGivenObserver - update - notification received for performance with key: " + (String)object);
                hashSet = new HashSet<String>();
                hashSet.add((String)object);
                ListenFragment.this.w.a(hashSet, true);
            }
        };
        notificationCenter.a("LOVE_GIVEN", observer);
        ListViewMediaPlayerObserver.a((AbsListView)this.m);
    }

    @Override
    public void onStop() {
        super.onStop();
        NotificationCenter.a().b("LOVE_GIVEN", this.v);
        ListViewMediaPlayerObserver.b((AbsListView)this.m);
    }

    @AfterViews
    protected void t() {
        this.j.setText(2131297199);
        this.l.setText(2131297203);
        this.r = -1;
        this.o = -1;
        this.n = -1;
        this.J();
        this.u = true;
        this.H();
    }

    @Override
    public String x() {
        return g;
    }

    private class PerformancesListAdapter
    extends PaginatedAdapter {
        public final String f;
        private boolean h;

        private PerformancesListAdapter() {
            this.f = PerformancesListAdapter.class.getSimpleName();
            this.h = false;
        }

        @Override
        public View a(int n, View object, ViewGroup object2) {
            object2 = (PerformanceListItemContainer)this.getItem(n);
            if (n >= this.e() && object2 == null) {
                Log.d(this.f, "Graceful handling of fragment/activity lifecycle issues");
                return new View((Context)ListenFragment.this.getActivity());
            }
            if (object == null || !(object instanceof ListenCard)) {
                object = ListenCard.a((Context)ListenFragment.this.getActivity());
            }
            SquarePerformanceItem squarePerformanceItem = ((ListenCard)object).getSquarePerformanceItem();
            squarePerformanceItem.getLayoutParams().width = ListenFragment.this.z;
            squarePerformanceItem.getPlayableItemView().getLayoutParams().height = ListenFragment.this.z;
            squarePerformanceItem.setTag(2131623961, (Object)n);
            ListenFragment.this.a(squarePerformanceItem, (PerformanceListItemContainer)object2);
            squarePerformanceItem.setSquarePerformanceItemListener(new SquarePerformanceItem.SquarePerformanceItemListener(){

                @Override
                public void a() {
                    Log.b(PerformancesListAdapter.this.f, "commentButtonClicked - called");
                    ((MasterActivity)ListenFragment.this.getActivity()).d("");
                }

                @Override
                public void b() {
                    Log.b(PerformancesListAdapter.this.f, "loveButtonClicked - called");
                    ((MasterActivity)ListenFragment.this.getActivity()).K();
                }
            });
            return object;
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void a(@NonNull PlaylistManager.PlaylistPerformancesGetResponse playlistPerformancesGetResponse) {
            if (playlistPerformancesGetResponse.next == -1 || playlistPerformancesGetResponse.recPerformanceIcons == null || playlistPerformancesGetResponse.recPerformanceIcons.isEmpty()) {
                this.h = true;
            }
            this.b();
            if (this.h) {
                this.c();
            } else {
                this.d();
            }
            this.notifyDataSetChanged();
            ListenFragment.this.G();
            ListenFragment.this.b(playlistPerformancesGetResponse.recPerformanceIcons);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(Collection<String> collection, boolean bl) {
            Log.b(this.f, "notifyPerformancesChanged - keys to change = " + Arrays.toString(collection.toArray()));
            GridView gridView = ListenFragment.this.m;
            int n = gridView.getChildCount();
            int n2 = 0;
            do {
                String string2;
                int n3;
                PerformanceListItemContainer performanceListItemContainer;
                if (n2 >= n) {
                    this.notifyDataSetChanged();
                    return;
                }
                View view = gridView.getChildAt(n2);
                if (view != null && (n3 = gridView.getPositionForView(view)) != -1 && (performanceListItemContainer = (PerformanceListItemContainer)this.getItem(n3)) != null && (string2 = performanceListItemContainer.a().performanceKey) != null) {
                    for (String string3 : collection) {
                        if (string3 == null || !string3.equals(string2)) continue;
                        if (bl) {
                            performanceListItemContainer.b();
                        }
                        this.getView(n3, view, (ViewGroup)gridView);
                        break;
                    }
                }
                ++n2;
            } while (true);
        }

        @Override
        public void b(int n) {
            this.c(n);
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void b(PlaylistManager.PlaylistPerformancesGetResponse playlistPerformancesGetResponse) {
            int n = 0;
            ListenFragment.this.s = false;
            ListenFragment listenFragment = ListenFragment.this;
            if (ListenFragment.this.x != null) {
                n = ListenFragment.this.x.size();
            }
            listenFragment.d(n);
            if (playlistPerformancesGetResponse != null && playlistPerformancesGetResponse.a != null && playlistPerformancesGetResponse.a.b == 1012) {
                ListenFragment.this.b(ListenFragment.this);
            }
        }

        public void c(final int n) {
            if (ListenFragment.this.s) {
                return;
            }
            ListenFragment.this.s = true;
            ListenFragment.this.G();
            if (ListenFragment.this.y == null) {
                ArrayList<Long> arrayList = new ArrayList<Long>();
                arrayList.add(Long.valueOf(new SingServerValues().K()));
                com.smule.android.network.managers.PlaylistManager.a().a(arrayList, new PlaylistManager(){

                    @Override
                    public void handleResponse(PlaylistManager.PlaylistsResponse playlistsResponse) {
                        if (!ListenFragment.this.isAdded()) {
                            return;
                        }
                        if (playlistsResponse.a() && playlistsResponse.playlists != null && !playlistsResponse.playlists.isEmpty()) {
                            ListenFragment.this.y = playlistsResponse.playlists.get(0);
                            if (ListenFragment.this.B != null) {
                                new Handler().post(ListenFragment.this.B);
                            }
                            PerformancesListAdapter.this.d(n);
                            return;
                        }
                        PerformancesListAdapter.this.b(null);
                    }
                });
                return;
            }
            this.d(n);
        }

        protected void d(int n) {
            int n2 = new SingServerValues().K();
            com.smule.android.network.managers.PlaylistManager.a().a(n2, (n - 1) * 15, 15, new PlaylistManager(){

                @Override
                public void handleResponse(PlaylistManager.PlaylistPerformancesGetResponse playlistPerformancesGetResponse) {
                    if (!ListenFragment.this.isAdded() || !ListenFragment.this.a(ListenFragment.this.e)) {
                        return;
                    }
                    if (playlistPerformancesGetResponse != null && playlistPerformancesGetResponse.a()) {
                        PerformancesListAdapter.this.a(playlistPerformancesGetResponse);
                        return;
                    }
                    PerformancesListAdapter.this.b(playlistPerformancesGetResponse);
                }
            });
        }

        public int e() {
            return 0;
        }

        public void f() {
            ListenFragment.this.w.a(0);
            ListenFragment.this.w.a();
            this.c(1);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public int getCount() {
            int n;
            int n2 = this.e();
            if (ListenFragment.this.x != null) {
                n = ListenFragment.this.x.size();
                do {
                    return n + n2;
                    break;
                } while (true);
            }
            n = 0;
            return n + n2;
        }

        public Object getItem(int n) {
            PerformanceListItemContainer performanceListItemContainer;
            PerformanceListItemContainer performanceListItemContainer2 = performanceListItemContainer = null;
            if (n >= this.e()) {
                performanceListItemContainer2 = performanceListItemContainer;
                if (n - this.e() < ListenFragment.this.x.size()) {
                    performanceListItemContainer2 = (PerformanceListItemContainer)ListenFragment.this.x.get(n - this.e());
                }
            }
            return performanceListItemContainer2;
        }

        public long getItemId(int n) {
            return 0;
        }

    }

}

