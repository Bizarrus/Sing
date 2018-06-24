/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Point
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.support.annotation.NonNull
 *  android.support.constraint.ConstraintLayout
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.text.TextUtils
 *  android.util.Pair
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer
 *  com.mopub.nativeads.MoPubStreamAdPlacer
 *  com.mopub.nativeads.ViewBinder
 *  com.mopub.nativeads.ViewBinder$Builder
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$EntryPoint
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.songbook_search.SearchFragment
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.ShareUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$SingModulePlacement
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.ViewBinder;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.FeedListItemObject;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FeedFragment_;
import com.smule.singandroid.FindFriendsFragment;
import com.smule.singandroid.FindFriendsModule;
import com.smule.singandroid.IconWithNotificationDrawable;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.ads.SingAdSettings;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.bookmark.SongBookmarkMenuBuilder;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.datasource.RecommendedFriendsCachedDataSource;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.fragments.JoinersListFragment;
import com.smule.singandroid.list_items.FeedListFriendsNotificationView;
import com.smule.singandroid.list_items.FeedListViewItem;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.sections.feed.FeedDataSource;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.ShareUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class FeedFragment
extends BaseFragment {
    public static final String g = FeedFragment.class.getName();
    private FeedRecsysCallback A;
    private String B;
    private String C;
    private String D;
    private String E;
    private boolean F;
    private BookmarkDialogCallback G;
    protected int h = 0;
    @ViewById
    protected MediaListView i;
    @ViewById
    protected SwipeRefreshLayout j;
    @ViewById
    protected ConstraintLayout k;
    @ViewById
    protected View l;
    @ViewById
    protected TextView m;
    protected FeedAdapter n;
    @InstanceState
    protected int o = -1;
    FeedListFriendsNotificationView p;
    int q;
    int r;
    int s;
    int t;
    int u;
    MagicDataSource.DataSourceObserver v;
    private MagicNativeAdMediatorAdapter w;
    private final SingServerValues x = new SingServerValues();
    private Observer y;
    private ConcurrentHashMap<String, Boolean> z;

    public FeedFragment() {
        this.y = new Observer(){

            @Override
            public void update(Observable pair, Object object) {
                pair = (String)object;
                Log.b(FeedFragment.g, "mLoveGivenObserver - update - notification received for performance with key: " + (String)pair);
                pair = FeedFragment.this.n.b((String)pair);
                if (pair != null) {
                    FeedFragment.this.a((String)pair.second, Analytics.a, (Integer)pair.first, Analytics.a);
                }
            }
        };
        this.z = new ConcurrentHashMap();
        this.B = "";
        this.C = "";
        this.D = "";
        this.E = "";
        this.F = false;
        this.u = -1;
        this.v = new MagicDataSource.DataSourceObserverObject(){

            @Override
            public void a(MagicDataSource magicDataSource) {
                FeedFragment.this.D = FeedFragment.this.B;
                FeedFragment.this.E = FeedFragment.this.C;
            }

            @Override
            public void b(MagicDataSource magicDataSource) {
                if (FeedFragment.this.isAdded()) {
                    FeedFragment.this.a(magicDataSource.k(), magicDataSource.i());
                }
            }

            @Override
            public void c(MagicDataSource magicDataSource) {
                if (FeedFragment.this.isAdded()) {
                    FeedFragment.this.a(magicDataSource.k(), magicDataSource.i());
                }
            }
        };
        this.G = new BookmarkDialogCallback(){

            @Override
            public void a(PerformanceV2 performanceV2) {
                final MasterActivity masterActivity = (MasterActivity)FeedFragment.this.getActivity();
                new UiHandler((Activity)masterActivity).a(new Runnable(){

                    @Override
                    public void run() {
                        masterActivity.am().a(FeedFragment.this, FeedFragment.this.l, FeedFragment.this.m, true);
                    }
                });
                NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
            }

            @Override
            public void b(PerformanceV2 performanceV2) {
                final MasterActivity masterActivity = (MasterActivity)FeedFragment.this.getActivity();
                new UiHandler((Activity)masterActivity).a(new Runnable(){

                    @Override
                    public void run() {
                        masterActivity.am().a(FeedFragment.this, FeedFragment.this.l, FeedFragment.this.m, false);
                    }
                });
                NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
            }

            @Override
            public void c(PerformanceV2 performanceV2) {
                NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
            }

            @Override
            public void d(PerformanceV2 performanceV2) {
                NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
            }

        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void L() {
        Pair<String, String> pair = this.getActivity();
        if (pair != null) {
            pair = LayoutUtils.a((Context)pair) ? this.a(0, 3) : this.a(0, 2);
            if (pair != null) {
                this.A.a((String)pair.first, (String)pair.second, false);
            }
        }
    }

    private boolean M() {
        Pair<String, String> pair = this.a(this.s, this.t);
        if (pair != null) {
            String string2 = (String)pair.first;
            String string3 = (String)pair.second;
            String string4 = string2;
            String string5 = string3;
            if (this.s == 0) {
                string4 = string2;
                string5 = string3;
                if (!this.B.isEmpty()) {
                    string4 = this.B + "," + string2;
                    string5 = this.C + "," + string3;
                }
            }
            this.a(string4, string5, Analytics.a, Analytics.a);
        }
        if (pair != null) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private Pair<String, String> a(int var1_1, int var2_2) {
        var7_3 = 1;
        var6_4 = 0;
        Log.b(FeedFragment.g, "generateRecsysVwParams: " + var1_1 + ", will report " + var2_2 + " items");
        if (this.i == null) return null;
        if (this.i.getAdapter() == null) {
            return null;
        }
        var3_5 = this.i.getHeaderViewsCount();
        var4_6 = Math.min(0, var1_1 - var3_5) * -1;
        if (var4_6 > 0) {
            var4_6 = var2_2 - var4_6;
            var1_1 = 1;
            var3_5 = 0;
        } else if (var3_5 > 0) {
            var3_5 = var1_1 - 1;
            var4_6 = var2_2;
        } else {
            var3_5 = var1_1++;
            var4_6 = var2_2;
        }
        if (this.n instanceof FeedWithFindFriendsModuleAdapter) {
            var8_7 = (FeedWithFindFriendsModuleAdapter)this.n;
            var8_7.j();
        } else {
            var8_7 = null;
        }
        var2_2 = this.w != null ? this.w.getCountIncludingAds() : this.n.d();
        var5_8 = this.w != null && this.n.a().l() != false ? var2_2 - 1 : var2_2;
        Log.b(FeedFragment.g, "   recys_vw: allegedTotalRows: " + var2_2 + ", correctedTotalRows: " + var5_8 + ", firstVisibleFeedItemLookupIndex: " + var3_5 + ", rowCount: " + var4_6);
        var2_2 = var5_8 >= var3_5 + var4_6 ? var7_3 : 0;
        if (var4_6 <= 0) return null;
        if (var2_2 == 0) return null;
        var10_9 = new ArrayList<String>();
        var9_10 = new ArrayList<String>();
        var5_8 = var6_4;
        do {
            block13 : {
                block12 : {
                    if (var5_8 >= var4_6) {
                        this.q = this.s;
                        this.r = this.t;
                        var8_7 = TextUtils.join((CharSequence)",", var10_9);
                        return new Pair((Object)TextUtils.join((CharSequence)",", var9_10), var8_7);
                    }
                    var2_2 = var6_4 = var3_5 + var5_8;
                    if (this.w == null) break block12;
                    if (this.w.isAd(var6_4)) break block13;
                    var2_2 = this.w.getOriginalPosition(var6_4);
                }
                var6_4 = var2_2;
                if (var8_7 == null) ** GOTO lbl51
                if (var8_7.m(var2_2)) {
                    var8_7.k(var2_2);
                } else {
                    var6_4 = var8_7.j(var2_2);
lbl51: // 2 sources:
                    var11_11 = (FeedListItem)this.n.a(var6_4);
                    if (var11_11.recId != null) {
                        var10_9.add(String.valueOf(var1_1 + var5_8));
                        var9_10.add(var11_11.recId);
                    }
                }
            }
            ++var5_8;
        } while (true);
    }

    public static FeedFragment a() {
        return FeedFragment_.L().a();
    }

    private void a(String string2, Analytics itemClickType, int n, Analytics recSysContext) {
        if (string2 == null) {
            return;
        }
        int n2 = n;
        if (this.w != null) {
            n2 = this.w.getAdjustedPosition(n);
        }
        com.smule.android.logging.Analytics.a(string2, itemClickType, n2 + 1, recSysContext, null);
    }

    private void a(String string2, String string3, Analytics recommendationType, Analytics recSysContext) {
        com.smule.android.logging.Analytics.a(string2, string3, recommendationType, recSysContext, null);
        if (this.n instanceof FeedWithFindFriendsModuleAdapter) {
            ((FeedWithFindFriendsModuleAdapter)this.n).k();
        }
    }

    private void a(boolean bl, ArrangementVersionLiteEntry arrangementVersionLiteEntry, FeedListViewItem feedListViewItem) {
        this.z.put(arrangementVersionLiteEntry.c(), bl);
        if (bl) {
            MediaPlayerServiceController.a().b(feedListViewItem.getMediaKey());
            this.a(feedListViewItem);
            return;
        }
        com.smule.android.logging.Analytics.a("feed", this.b(arrangementVersionLiteEntry), arrangementVersionLiteEntry.i(), arrangementVersionLiteEntry.r(), null, SongbookEntry.a(arrangementVersionLiteEntry));
        feedListViewItem.j();
        this.a(arrangementVersionLiteEntry);
    }

    private String b(SongbookEntry songbookEntry) {
        if (songbookEntry.t()) {
            if ((songbookEntry = (ArrangementVersionLiteEntry)songbookEntry).b()) {
                return "-";
            }
            return songbookEntry.a.songId;
        }
        return songbookEntry.d();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(@NonNull FeedListViewItem object) {
        int n;
        FeedAdapter feedAdapter;
        if (object.c()) {
            if (object.getArrVersionLite() == null) {
                Log.e(g, "cannot do ContinuousPlay with null arrangement");
                return;
            }
            object = new MediaPlayingPlayable(SongbookEntry.a(object.getArrVersionLite()));
        } else {
            if (object.getPerformance() == null) {
                Log.e(g, "cannot do ContinuousPlay with null performance");
                return;
            }
            object = new MediaPlayingPlayable(object.getPerformance());
        }
        if ((feedAdapter = this.n) == null) {
            Log.e(g, "cannot start ContinuousPlay with a null adapter");
            return;
        }
        int n2 = feedAdapter.h();
        ArrayList<MediaPlayingPlayable> arrayList = new ArrayList<MediaPlayingPlayable>(n2);
        int n3 = -1;
        for (n = 0; n < n2; ++n) {
            FeedListItem feedListItem = (FeedListItem)feedAdapter.a(n);
            if (feedListItem == null || feedListItem.object == null) {
                Log.e(g, "cannot do ContinuousPlay with null/empty feed item");
                continue;
            }
            if (feedListItem.c()) {
                if (feedListItem.object.arrVersionLite == null) {
                    Log.e(g, "cannot do ContinuousPlay with null arrangements");
                    continue;
                }
                new MediaPlayingPlayable(SongbookEntry.a(feedListItem.object.arrVersionLite));
                continue;
            }
            if (feedListItem.object.performanceIcon == null) {
                Log.e(g, "cannot do ContinuousPlay with null performances");
                continue;
            }
            MediaPlayingPlayable mediaPlayingPlayable = new MediaPlayingPlayable(feedListItem.object.performanceIcon);
            arrayList.add(mediaPlayingPlayable);
            if (TextUtils.isEmpty((CharSequence)object.c()) || !object.c().equals(mediaPlayingPlayable.c())) continue;
            n3 = arrayList.size() - 1;
        }
        n = n3;
        if (n3 == -1) {
            Log.e(g, "playable not found in adapter, adding it to top of playlist");
            arrayList.add(0, (MediaPlayingPlayable)object);
            n = 0;
        }
        this.a(arrayList, n);
    }

    @Override
    protected void A() {
        SingAnalytics.z();
    }

    @Override
    protected boolean D() {
        return false;
    }

    @UiThread
    protected void F() {
        if (!this.isAdded()) {
            return;
        }
        this.a((AbsListView)this.i, QuickReturnListViewMenuSyncer.ActionBarHighlightMode.a, new AbsListView.OnScrollListener(){

            private void a() {
                if (FeedFragment.this.t > 0 && FeedFragment.this.u == 0 && (FeedFragment.this.s != FeedFragment.this.q || FeedFragment.this.t != FeedFragment.this.r)) {
                    FeedFragment.this.M();
                }
            }

            public void onScroll(AbsListView absListView, int n, int n2, int n3) {
                FeedFragment.this.s = n;
                FeedFragment.this.t = n2;
            }

            public void onScrollStateChanged(AbsListView absListView, int n) {
                FeedFragment.this.u = n;
                this.a();
            }
        });
    }

    protected void G() {
        if (this.x.ai()) {
            MagicPreferences.a((Context)this.getActivity(), true);
            return;
        }
        final FeedListFriendsNotificationView feedListFriendsNotificationView = FeedListFriendsNotificationView.a((Context)this.getActivity(), this.A);
        feedListFriendsNotificationView.setActionListener(new FeedListFriendsNotificationView.ActionListener(){

            @Override
            public void a() {
                FeedFragment.this.H();
                MagicPreferences.a((Context)FeedFragment.this.getActivity(), true);
            }

            @Override
            public void b() {
                FeedFragment.this.I();
                MagicPreferences.a((Context)FeedFragment.this.getActivity(), true);
            }

            @Override
            public void c() {
                FeedFragment.this.i.removeHeaderView((View)feedListFriendsNotificationView);
                FeedFragment.this.p = null;
                MagicPreferences.a((Context)FeedFragment.this.getActivity(), true);
                FeedFragment.this.B = "";
                FeedFragment.this.C = "";
            }
        });
        this.i.addHeaderView((View)feedListFriendsNotificationView);
        this.p = feedListFriendsNotificationView;
    }

    @SupposeUiThread
    protected void H() {
        this.a(FindFriendsFragment.a(FindFriendsFragment.EntryPoint.a, FindFriendsFragment.FindFriendsTab.a), FindFriendsFragment.g);
    }

    @SupposeUiThread
    protected void I() {
        this.a(FindFriendsFragment.a(FindFriendsFragment.EntryPoint.a, FindFriendsFragment.FindFriendsTab.b), FindFriendsFragment.g);
    }

    @Click
    protected void J() {
        ((MediaPlayingActivity)this.getActivity()).am().a(this.l);
    }

    @Click
    protected void K() {
        this.H();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(int n, MagicDataSource.DataState dataState) {
        boolean bl = this.x.ai();
        boolean bl2 = dataState == MagicDataSource.DataState.b || dataState == MagicDataSource.DataState.e;
        if (bl && bl2 && n == 0) {
            this.j.setVisibility(8);
            this.k.setVisibility(0);
            return;
        }
        this.j.setVisibility(0);
        this.k.setVisibility(8);
    }

    protected void a(Menu object) {
        MenuItem menuItem = object.findItem(2131756843);
        object = this.getResources().getDrawable(2130837589);
        if (!MagicPreferences.b((Context)this.getActivity())) {
            object = new IconWithNotificationDrawable((Context)this.getActivity(), this.getResources().getDimensionPixelSize(2131428182), - this.getResources().getDimensionPixelSize(2131427591), this.getResources().getDimensionPixelSize(2131427369), new Drawable[]{object});
        }
        menuItem.setIcon((Drawable)object);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            public boolean onMenuItemClick(MenuItem menuItem) {
                MagicPreferences.b((Context)FeedFragment.this.getActivity(), true);
                FeedFragment.this.H();
                return false;
            }
        });
    }

    @Override
    public void a(SongbookEntry songbookEntry) {
        Log.b(this.x(), "playPreview - called");
        BaseFragment baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(songbookEntry, false, null);
        }
    }

    protected void a(FeedListViewItem object) {
        if (!this.isAdded()) {
            return;
        }
        object.setAlbumArtClickedState(false);
        object = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296404), MessageFormat.format(this.getString(2131296403), this.getString(2131297940)), true, false);
        object.a(this.getString(2131296705), null);
        object.show();
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

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.setHasOptionsMenu(true);
        Log.b(g, "feed has social-only data enabled? " + this.x.ai());
        object = this.x.ai() ? new FeedDataSource(FeedDataSource.FeedType.b) : new FeedDataSource(FeedDataSource.FeedType.a);
        String string2 = this.x.aj();
        boolean bl = FindFriendsModule.FindFriendsModulePlacer.a(string2, SingAnalytics.SingModulePlacement.a);
        Log.b(g, "feed has Find Friends modules? " + bl);
        this.n = bl ? new FeedWithFindFriendsModuleAdapter((FeedDataSource)object, new FindFriendsModule.FindFriendsModulePlacer(string2, SingAnalytics.SingModulePlacement.a, 3, null)) : new FeedAdapter((MagicDataSource)object);
        this.n.a(this.v);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        Log.b(g, "onCreateOptionsMenu");
        menuInflater.inflate(2131820548, menu2);
        this.a(menu2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.p = null;
        if (this.w != null) {
            this.w.destroy();
            this.w = null;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 2131756839: 
        }
        SingAnalytics.a(Analytics.c);
        this.a((BaseFragment)SearchFragment.F());
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.u();
        if (this.p != null && this.p.isShown()) {
            this.p.f();
        }
        if (!MediaPlayerServiceController.a().m()) {
            this.o = -1;
        }
        MediaPlayingListItem.b((AbsListView)this.i);
        MediaPlayingListItem.a((AbsListView)this.i);
        if (this.F) {
            if (this.M()) {
                return;
            }
            if (this.s == 0) {
                this.L();
            }
        }
        this.a(this.n.d(), this.n.a().i());
    }

    @Override
    public void onStart() {
        super.onStart();
        SingAppboy.a().e();
        this.c(2131296686);
        NotificationCenter.a().a("LOVE_GIVEN", this.y);
        FeedListViewItem feedListViewItem = (FeedListViewItem)this.i.findViewWithTag((Object)this.o);
        if (feedListViewItem == null || !MediaPlayerServiceController.a().c(feedListViewItem.getMediaKey())) {
            this.o = -1;
        }
        if (FeedDataSource.a) {
            FeedDataSource.a = false;
            this.n.f();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        NotificationCenter.a().b("LOVE_GIVEN", this.y);
        MediaPlayerServiceController.a().r();
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void t() {
        this.A = new FeedRecsysCallback(){
            boolean a;
            {
                this.a = false;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void a(String string2, String string3, boolean bl) {
                synchronized (this) {
                    boolean bl2 = FeedFragment.this.isAdded();
                    if (bl2) {
                        if (bl) {
                            FeedFragment.this.B = string2;
                            FeedFragment.this.C = string3;
                        }
                        if (FeedFragment.this.D.isEmpty()) {
                            FeedFragment.this.D = string2;
                            FeedFragment.this.E = string3;
                        } else if (bl) {
                            FeedFragment.this.D = string2 + "," + FeedFragment.this.D;
                            FeedFragment.this.E = string3 + "," + FeedFragment.this.E;
                        } else {
                            FeedFragment.this.D = FeedFragment.this.D + "," + string2;
                            FeedFragment.this.E = FeedFragment.this.E + "," + string3;
                        }
                        if (this.a || MagicPreferences.a((Context)FeedFragment.this.getActivity())) {
                            FeedFragment.this.a(FeedFragment.this.D, FeedFragment.this.E, Analytics.a, Analytics.a);
                            FeedFragment.this.F = true;
                        } else {
                            this.a = true;
                        }
                    }
                    return;
                }
            }
        };
        if (!MagicPreferences.a((Context)this.getActivity())) {
            this.G();
        }
        if (MagicAdSettings.a(Analytics.c)) {
            ViewBinder viewBinder = new ViewBinder.Builder(2130903295).iconImageId(2131755975).titleId(2131755977).callToActionId(2131755981).textId(2131755982).mainImageId(2131755980).privacyInformationIconImageId(2131755983).build();
            HashMap<String, String> hashMap = SingAdSettings.a(null);
            this.w = MagicAdAdapterFactory.a().a(this.getActivity(), Analytics.c, new ViewBinder.Builder(2130903296).build(), viewBinder, (MoPubStreamAdPlacer)new MagicMoPubGhostStreamAdPlacer(this.getActivity()), hashMap, this.i, this.n, 2131755976, new View.OnClickListener(){

                public void onClick(View view) {
                    FeedFragment.this.E();
                    new NativeAdsMoreDialog(FeedFragment.this).show();
                }
            }, new Runnable(){

                @Override
                public void run() {
                    FeedFragment.this.E();
                }
            });
            if (this.w != null) {
                Log.b(g, "feed has Native Ads");
                this.w.loadAds();
            } else {
                Log.e(g, "mMagicNativeAdMediatorAdapter null");
                this.i.setMagicAdapter(this.n);
            }
        } else {
            this.i.setMagicAdapter(this.n);
        }
        this.F();
        this.j.setColorSchemeResources(new int[]{2131689905});
        this.i.setSwipeRefreshLayout(this.j);
        this.h = LayoutUtils.a((Activity)this.getActivity()).x;
    }

    @Override
    public String x() {
        return g;
    }

    @Override
    public void z() {
        super.z();
        MediaPlayingListItem.b((AbsListView)this.i);
        MediaPlayingListItem.a((AbsListView)this.i);
    }

    protected class FeedAdapter
    extends MagicAdapter {
        boolean c;
        public final int d;

        public FeedAdapter(MagicDataSource magicDataSource) {
            super(magicDataSource);
            this.c = false;
            this.d = 0;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void a(FeedListViewItem feedListViewItem) {
            if (!feedListViewItem.c()) {
                feedListViewItem.i();
                FeedFragment.this.b(feedListViewItem);
                return;
            }
            if (feedListViewItem.t()) {
                return;
            }
            feedListViewItem.setAlbumArtClickedState(true);
            if (FeedFragment.this.z == null) {
                FeedFragment.this.z = new ConcurrentHashMap();
            }
            SongbookEntry songbookEntry = SongbookEntry.a(feedListViewItem.getArrVersionLite());
            if (FeedFragment.this.z.containsKey(songbookEntry.c())) {
                FeedFragment.this.a((Boolean)FeedFragment.this.z.get(songbookEntry.c()), (ArrangementVersionLiteEntry)songbookEntry, feedListViewItem);
                return;
            }
            int n = ((ArrangementVersionLiteEntry)songbookEntry).a().removalCode;
            boolean bl = n >= 40 && n <= 49;
            FeedFragment.this.z.put(songbookEntry.c(), bl);
            FeedFragment.this.a(bl, (ArrangementVersionLiteEntry)songbookEntry, feedListViewItem);
        }

        /*
         * Enabled aggressive block sorting
         */
        private void a(final FeedListViewItem feedListViewItem, int n) {
            block9 : {
                block8 : {
                    boolean bl = true;
                    if (FeedFragment.this.getActivity() == null) break block8;
                    Object object = (MasterActivity)FeedFragment.this.getActivity();
                    boolean bl2 = object.aj() || object.ak();
                    object = feedListViewItem.getPerformance() != null ? feedListViewItem.getPerformance().performanceKey : null;
                    if (FeedFragment.this.o != n || !bl2 || !MediaPlayerServiceController.a().c((String)object)) {
                        object = (FeedListViewItem)FeedFragment.this.i.findViewWithTag((Object)FeedFragment.this.o);
                        FeedFragment.this.o = n;
                        if (object != null) {
                            if (object.getPerformance() == null || !object.a(object.getPerformance())) {
                                object.startAnimation(object.getCollapseViewAnimation());
                            }
                            object.l();
                            object.setIsExpanded(false);
                        }
                        feedListViewItem.setIsExpanded(true);
                        object = FeedFragment.g;
                        StringBuilder stringBuilder = new StringBuilder().append("View item is video ");
                        if (feedListViewItem.getPerformance() == null || !feedListViewItem.getPerformance().video) {
                            bl = false;
                        }
                        Log.b((String)object, stringBuilder.append(bl).toString());
                        if (feedListViewItem.getPerformance() != null && feedListViewItem.a(feedListViewItem.getPerformance())) {
                            this.a(feedListViewItem);
                            return;
                        }
                        object = feedListViewItem.getExpandViewAnimation();
                        object.setAnimationListener(new Animation.AnimationListener(){

                            public void onAnimationEnd(Animation animation) {
                                if (!FeedFragment.this.isAdded()) {
                                    return;
                                }
                                FeedAdapter.this.a(feedListViewItem);
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                            }
                        });
                        feedListViewItem.startAnimation((Animation)object);
                        return;
                    }
                    if (feedListViewItem.q() || !feedListViewItem.t()) break block9;
                }
                return;
            }
            feedListViewItem.u();
        }

        private boolean a(FeedListItem object, int n) {
            if (object.object != null && object.object.performanceIcon != null && (object = object.object.performanceIcon.performanceKey) != null && MediaPlayerServiceController.a().c((String)object) && FeedFragment.this.o == -1) {
                FeedFragment.this.o = n;
            }
            if (FeedFragment.this.o == n) {
                return true;
            }
            return false;
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            return FeedListViewItem.a((Context)FeedFragment.this.getActivity());
        }

        @Override
        public void a(View object, final int n, int n2) {
            final FeedListItem feedListItem = (FeedListItem)this.a(n);
            object = (FeedListViewItem)object;
            boolean bl = this.a(feedListItem, n);
            if (!bl) {
                object.k();
            }
            object.a(FeedFragment.this, feedListItem, bl, new FeedListViewItem.FeedListItemFeedback(){

                private SongbookEntry d(PerformanceV2 performanceV2) {
                    if (performanceV2.v()) {
                        ArrangementVersionLite arrangementVersionLite = new ArrangementVersionLite();
                        arrangementVersionLite.key = performanceV2.arrKey;
                        arrangementVersionLite.name = performanceV2.title;
                        arrangementVersionLite.coverUrl = performanceV2.coverUrl;
                        return SongbookEntry.a(arrangementVersionLite);
                    }
                    return SongbookEntry.a(performanceV2.arrangementVersion);
                }

                @Override
                public void a() {
                    FeedAdapter.this.i();
                }

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void a(Analytics itemClickType) {
                    if (itemClickType != Analytics.a) {
                        FeedFragment.this.a(feedListItem.recId, itemClickType, n, Analytics.a);
                    }
                    SongbookEntry songbookEntry = feedListItem.c() ? SongbookEntry.a(feedListItem.object.arrVersionLite) : this.d(feedListItem.object.performanceIcon);
                    if (itemClickType == Analytics.e) {
                        SingAnalytics.c((SongbookEntry)songbookEntry);
                    }
                }

                @Override
                public void a(AccountIcon object) {
                    this.a(Analytics.d);
                    object = ProfileFragment.a((AccountIcon)object);
                    FeedFragment.this.a((BaseFragment)((Object)object));
                }

                @Override
                public void a(ArrangementVersionLite object) {
                    this.a(Analytics.e);
                    object = SongbookEntry.a(object);
                    PreSingActivity.a((Context)FeedFragment.this.getActivity()).a(PreSingActivity.StartupMode.a).a((SongbookEntry)object).a("feed").a();
                }

                @Override
                public void a(PerformanceV2 object) {
                    this.a(Analytics.l);
                    object = JoinersListFragment.a((PerformanceV2)object);
                    FeedFragment.this.a((BaseFragment)((Object)object));
                }

                @Override
                public void b(ArrangementVersionLite arrangementVersionLite) {
                    this.a(Analytics.i);
                    ShareUtils.f((Context)FeedFragment.this.getActivity(), (ArrangementVersionLite)arrangementVersionLite);
                }

                @Override
                public void b(PerformanceV2 performanceV2) {
                    this.a(Analytics.f);
                    PreSingActivity.a((Context)FeedFragment.this.getActivity()).a(PreSingActivity.StartupMode.b).a(feedListItem.object.performanceIcon).a(PreSingActivity.EntryPoint.a).a();
                    SingAnalytics.a((String)PerformanceV2Util.i((PerformanceV2)performanceV2), (JoinSectionType)JoinSectionType.b, (String)PerformanceV2Util.h((PerformanceV2)performanceV2));
                }

                @Override
                public void c(ArrangementVersionLite arrangementVersionLite) {
                    new SongBookmarkMenuBuilder().a(arrangementVersionLite).a((Context)FeedFragment.this.getActivity()).a();
                }

                @Override
                public void c(PerformanceV2 performanceV2) {
                    ((MediaPlayingActivity)FeedFragment.this.getActivity()).am().a(performanceV2, FeedFragment.this.G, false);
                }
            });
            object.a(new FeedListViewItem.FeedListViewItemClickCallback((FeedListViewItem)object, n){
                final /* synthetic */ FeedListViewItem a;
                final /* synthetic */ int b;
                {
                    this.a = feedListViewItem;
                    this.b = n;
                }

                @Override
                public void a() {
                    if (!this.a.d()) {
                        FeedAdapter.this.a(this.a, this.b);
                    }
                }
            }, new FeedListViewItem.FeedListViewItemClickCallback((FeedListViewItem)object, n){
                final /* synthetic */ FeedListViewItem a;
                final /* synthetic */ int b;
                {
                    this.a = feedListViewItem;
                    this.b = n;
                }

                @Override
                public void a() {
                    FeedAdapter.this.a(this.a, this.b);
                }
            });
            object.setTag((Object)n);
        }

        public Pair<Integer, String> b(String object) {
            FeedDataSource feedDataSource = (FeedDataSource)this.a();
            for (int i = 0; i < feedDataSource.k(); ++i) {
                FeedListItem feedListItem = (FeedListItem)feedDataSource.a(i);
                if (feedListItem.object == null || feedListItem.object.performanceIcon == null || !feedListItem.object.performanceIcon.performanceKey.equals(object)) continue;
                object = feedListItem.object.performanceIcon;
                ++object.totalLoves;
                feedDataSource.p();
                return new Pair((Object)i, (Object)feedListItem.recId);
            }
            return null;
        }

        @Override
        public int c(int n) {
            return 0;
        }

        @Override
        public void c(MagicDataSource magicDataSource) {
            super.c(magicDataSource);
            if (this.a().k() > 0 && !this.c) {
                this.c = true;
                FeedFragment.this.L();
            }
        }

        @Override
        public int e() {
            return 1;
        }

        public int h() {
            return this.d();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void i() {
            int n = FeedFragment.this.i.getChildCount();
            int n2 = 0;
            while (n2 < n) {
                View view = FeedFragment.this.i.getChildAt(n2);
                if (view != null && view instanceof FeedListViewItem) {
                    ((FeedListViewItem)view).a(true, false, false);
                }
                ++n2;
            }
        }

    }

    public static interface FeedRecsysCallback {
        public void a(String var1, String var2, boolean var3);
    }

    protected class FeedWithFindFriendsModuleAdapter
    extends FeedAdapter {
        protected RecommendedFriendsCachedDataSource f;
        protected InitialLoaderDataSourceObserver g;
        protected boolean h;
        protected boolean i;
        public final int j;
        protected FindFriendsModule.FindFriendsModulePlacer k;
        protected ArrayList<Integer> l;

        public FeedWithFindFriendsModuleAdapter(FeedDataSource feedDataSource, FindFriendsModule.FindFriendsModulePlacer findFriendsModulePlacer) {
            super(feedDataSource);
            this.h = false;
            this.i = false;
            this.j = 1;
            this.l = new ArrayList();
            this.k = findFriendsModulePlacer;
            findFriendsModulePlacer.b();
            this.f = new RecommendedFriendsCachedDataSource(20);
            this.g = new InitialLoaderDataSourceObserver();
            this.f.a(this.g);
            if (this.f.i() == MagicDataSource.DataState.b && this.f.k() > 0) {
                this.k.a();
                this.h = true;
                if (feedDataSource.k() > 0) {
                    feedDataSource.p();
                }
                return;
            }
            this.f.o();
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            if (n == 1) {
                return FindFriendsModule.a((Context)FeedFragment.this.getActivity(), FeedFragment.this, SingAnalytics.SingModulePlacement.a, FeedFragment.this.h);
            }
            return FeedListViewItem.a((Context)FeedFragment.this.getActivity());
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void a(View view, int n, int n2) {
            if (n2 == 0) {
                super.a(view, this.k.b(n), n2);
                return;
            } else {
                if (n2 != 1) return;
                {
                    ((FindFriendsModule)view).setDisplayPosition(n);
                    return;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public int c(int n) {
            if (this.i && n == this.d() - 1 || this.k.a(n)) {
                return 1;
            }
            return 0;
        }

        @Override
        public int d() {
            int n = super.d();
            int n2 = this.k.c(n);
            this.l(n2);
            n = n2;
            if (this.i) {
                n = n2 + 1;
            }
            return n;
        }

        @Override
        public int e() {
            return 2;
        }

        @Override
        public int h() {
            return super.d();
        }

        public int j(int n) {
            return this.k.b(n);
        }

        public void j() {
            this.l.clear();
        }

        public void k() {
            Iterator<Integer> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                int n = iterator.next();
                ((FindFriendsModule)this.a((ViewGroup)FeedFragment.this.i, null, n)).b();
            }
        }

        public void k(int n) {
            this.l.add(n);
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void l(int n) {
            block3 : {
                block2 : {
                    if (this.i || !this.h) break block2;
                    MagicDataSource.DataState dataState = this.a().i();
                    Integer n2 = this.k.e();
                    boolean bl = !this.a().l();
                    if (n2 == null) return;
                    if (dataState == MagicDataSource.DataState.b && n <= n2 && bl) break block3;
                }
                return;
            }
            this.i = true;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean m(int n) {
            if (this.i && n == this.d() - 1 || this.k.a(n)) {
                return true;
            }
            return false;
        }

        protected class InitialLoaderDataSourceObserver
        implements MagicDataSource.DataSourceObserver {
            InitialLoaderDataSourceObserver() {
            }

            @Override
            public void a(MagicDataSource magicDataSource) {
            }

            @Override
            public void a(MagicDataSource magicDataSource, List<Object> list) {
            }

            @Override
            public void b(MagicDataSource magicDataSource) {
            }

            @Override
            public void c(MagicDataSource magicDataSource) {
                MagicDataSource magicDataSource2 = FeedWithFindFriendsModuleAdapter.this.a();
                MagicDataSource.DataState dataState = magicDataSource.i();
                if (!FeedWithFindFriendsModuleAdapter.this.h && dataState == MagicDataSource.DataState.b && magicDataSource.k() > 0) {
                    FeedWithFindFriendsModuleAdapter.this.k.a();
                    FeedWithFindFriendsModuleAdapter.this.h = true;
                    if (magicDataSource2.k() > 0) {
                        magicDataSource2.p();
                    }
                }
            }

            @Override
            public void d(MagicDataSource magicDataSource) {
            }
        }

    }

}

