/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$EntryPoint
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.songbook_search.SearchBaseFragment
 *  com.smule.singandroid.songbook_search.SearchFragment
 *  com.smule.singandroid.songbook_search.SearchFragment$AnalyticsResultTriplet
 *  com.smule.singandroid.songbook_search.SearchFragment$SearchDataSource
 *  com.smule.singandroid.songbook_search.SearchShowAllFragment
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SongbookEntryUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.fragments.SearchByTagFragment_;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.songbook_search.SearchBaseFragment;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.songbook_search.SearchShowAllFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class SearchByTagFragment
extends SearchBaseFragment {
    public static final String g = SearchByTagFragment.class.getName();
    @ViewById
    protected LinearLayout h;
    @ViewById
    protected TextView i;
    @ViewById
    protected MediaListView j;
    @ViewById
    protected View k;
    @ViewById
    protected TextView l;
    @ViewById
    protected TextView m;
    @InstanceState
    protected ArrayList<Object> n;
    @InstanceState
    protected String o;
    @InstanceState
    protected boolean p;
    protected SearchByTagAdapter q;
    private SearchManager r;
    private long s;
    private BookmarkDialogCallback t;

    public SearchByTagFragment() {
        this.t = new BookmarkDialogCallback(){

            @Override
            public void a(PerformanceV2 performanceV2) {
                final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)SearchByTagFragment.this.getActivity();
                new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                    @Override
                    public void run() {
                        mediaPlayingActivity.am().a((Fragment)SearchByTagFragment.this, SearchByTagFragment.this.k, SearchByTagFragment.this.l, true);
                    }
                });
                NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
            }

            @Override
            public void b(PerformanceV2 performanceV2) {
                final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)SearchByTagFragment.this.getActivity();
                new UiHandler((Activity)mediaPlayingActivity).a(new Runnable(){

                    @Override
                    public void run() {
                        mediaPlayingActivity.am().a((Fragment)SearchByTagFragment.this, SearchByTagFragment.this.k, SearchByTagFragment.this.l, false);
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

    public static SearchByTagFragment a(String string2, boolean bl) {
        SearchByTagFragment_ searchByTagFragment_ = new SearchByTagFragment_();
        searchByTagFragment_.o = string2;
        searchByTagFragment_.p = bl;
        return searchByTagFragment_;
    }

    protected void A() {
        int n = 0;
        if (this.n != null) {
            Iterator<Object> iterator = this.n.iterator();
            int n2 = 0;
            boolean bl = false;
            while (iterator.hasNext()) {
                Object object = iterator.next();
                if (object instanceof Integer && object.equals(3)) {
                    bl = true;
                    continue;
                }
                if (object instanceof Integer) continue;
                if (bl) {
                    ++n;
                    continue;
                }
                ++n2;
            }
            com.smule.android.logging.Analytics.a(Analytics.g, Analytics.c, n2, "#" + this.o, "#" + this.o, 0, null);
            if (n != 0) {
                com.smule.android.logging.Analytics.a(Analytics.j, Analytics.c, n, "#" + this.o, "#" + this.o, 0, null);
            }
        }
    }

    @AfterViews
    protected void F() {
        if (this.p) {
            this.i.setText(2131297304);
            this.h.setVisibility(0);
            return;
        }
        this.h.setVisibility(8);
        if (this.q != null) {
            this.j.setMagicAdapter(this.q);
            return;
        }
        this.q = new SearchByTagAdapter((MagicDataSource)((Object)new SearchByTagDataSource(this.n)));
        this.j.setMagicAdapter(this.q);
    }

    @Click
    protected void G() {
        this.getActivity().onBackPressed();
    }

    @Click
    protected void H() {
        ((MediaPlayingActivity)this.getActivity()).am().a(this.k);
    }

    public String a() {
        String string2 = "";
        if (!this.p) {
            string2 = this.o;
        }
        return g + "-" + string2;
    }

    public void a(SongbookEntry songbookEntry) {
        super.a(songbookEntry, Analytics.i);
    }

    public void a(CharSequence charSequence) {
        this.m.setText(charSequence);
    }

    protected boolean a(View view) {
        return view.isShown();
    }

    protected void d(int n) {
        SearchShowAllFragment searchShowAllFragment = SearchShowAllFragment.a((int)n, this.r, (String)("#" + this.o), (String)("#" + this.o), Analytics.d);
        if (this.getActivity() instanceof MediaPlayingActivity) {
            MiscUtils.a((Activity)this.getActivity(), (boolean)true);
            ((MediaPlayingActivity)this.getActivity()).a((BaseFragment)searchShowAllFragment, searchShowAllFragment.x(), 2131034121, 2131034122, 2131034120, 2131034123, true, true);
            return;
        }
        this.a((BaseFragment)searchShowAllFragment);
    }

    public boolean d() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setHasOptionsMenu(false);
    }

    public void onDestroy() {
        super.onDestroy();
        this.u();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onResume() {
        super.onResume();
        this.s();
        String string2 = this.p ? "@" : "#";
        this.a(string2 + this.o);
        MediaPlayingListItem.a((AbsListView)this.j);
    }

    public void onStart() {
        super.onStart();
        if (this.x == null || !MediaPlayerServiceController.a().c(this.x.getMediaKey())) {
            this.y = -1;
        }
    }

    public void onStop() {
        super.onStop();
        MediaPlayerServiceController.a().r();
    }

    protected MediaListView t() {
        return this.j;
    }

    public String x() {
        return g;
    }

    public void z() {
        super.z();
        MediaPlayingListItem.a((AbsListView)this.j);
    }

    private class SearchByTagAdapter
    extends MagicAdapter {
        private int d;
        private boolean e;

        public SearchByTagAdapter(MagicDataSource magicDataSource) {
            super(magicDataSource);
            this.d = -1;
            this.e = true;
        }

        private void a(View view, final int n) {
            TextView textView = (TextView)view.findViewById(2131756721);
            view.findViewById(2131756722).setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SearchByTagFragment.this.d(n);
                }
            });
            switch (n) {
                default: {
                    return;
                }
                case 0: {
                    textView.setText(2131296668);
                    return;
                }
                case 1: {
                    textView.setText(2131296721);
                    return;
                }
                case 2: {
                    textView.setText(2131296694);
                    return;
                }
                case 3: 
            }
            textView.setText(2131296712);
        }

        private void b(View object, final int n) {
            object = (ListingListItem)object;
            final SongbookEntry songbookEntry = (SongbookEntry)this.a(n);
            object.a(songbookEntry, false);
            object.setAlbumArtClickListener(new View.OnClickListener((ListingListItem)object, n){
                final /* synthetic */ ListingListItem b;
                final /* synthetic */ int c;
                {
                    this.b = listingListItem;
                    this.c = n;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View view) {
                    boolean bl = true;
                    if (songbookEntry instanceof ArrangementVersionLiteEntry) {
                        if (this.b.t()) {
                            return;
                        }
                        this.b.setAlbumArtClickedState(true);
                        if (SearchByTagFragment.this.w == null) {
                            SearchByTagFragment.this.w = new ConcurrentHashMap();
                        }
                        if (!SearchByTagFragment.this.w.containsKey(songbookEntry.c())) {
                            int n = ((ArrangementVersionLiteEntry)songbookEntry).a().removalCode;
                            if (n < 40 || n > 49) {
                                bl = false;
                            }
                            SearchByTagFragment.this.w.put(songbookEntry.c(), bl);
                            SearchByTagFragment.this.a(bl, (ArrangementVersionLiteEntry)songbookEntry, this.b);
                        } else {
                            SearchByTagFragment.this.a((Boolean)SearchByTagFragment.this.w.get(songbookEntry.c()), (ArrangementVersionLiteEntry)songbookEntry, this.b);
                        }
                    } else {
                        SearchByTagFragment.this.a(songbookEntry);
                    }
                    view = SearchByTagFragment.this.a(this.c, 0, (MagicAdapter)SearchByTagFragment.this.q);
                    com.smule.android.logging.Analytics.a(Analytics.i, Analytics.a, Analytics.b, SongbookEntryUtils.d((SongbookEntry)songbookEntry), null, view.e(), null, SongbookEntryUtils.e((SongbookEntry)songbookEntry), null, view.d(), view.f());
                }
            });
            object.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    view = SearchByTagFragment.this.a(n, 0, (MagicAdapter)SearchByTagFragment.this.q);
                    com.smule.android.logging.Analytics.a(Analytics.i, Analytics.b, Analytics.b, SongbookEntryUtils.d((SongbookEntry)songbookEntry), null, view.e(), null, SongbookEntryUtils.e((SongbookEntry)songbookEntry), null, view.d(), view.f());
                    if (!SongbookEntryUtils.c((SongbookEntry)songbookEntry)) {
                        SearchByTagFragment.this.a(UpsellManager.a((boolean)true, (SongbookEntry)songbookEntry, (String)SongbookManager.b().c(), (String)null, (UpsellType)UpsellType.a));
                        return;
                    }
                    SingAnalytics.a((SongbookEntry)songbookEntry, (String)null, (Number)view.f());
                    PreSingActivity.a((Context)SearchByTagFragment.this.getActivity()).a(PreSingActivity.StartupMode.a).a(songbookEntry).a(SongbookManager.b().d()).a();
                }
            });
        }

        private void c(View view, final int n) {
            ((UserFollowListItem)view).a((AccountIcon)this.a(n), n, (Context)SearchByTagFragment.this.getActivity(), false, new UserFollowListItem.UserFollowListItemListener(){

                @Override
                public void a(Analytics searchResultClkContext) {
                    if (!SearchByTagFragment.this.isAdded()) {
                        return;
                    }
                    searchResultClkContext = SearchByTagFragment.this.a(n, 1, SearchByTagFragment.this.q);
                    com.smule.android.logging.Analytics.a(Analytics.k, Analytics.b, Analytics.b, null, null, searchResultClkContext.e(), ((AccountIcon)SearchByTagAdapter.this.a((int)n)).accountId, null, null, searchResultClkContext.d(), searchResultClkContext.f());
                }

                @Override
                public void a(ProfileFragment profileFragment) {
                    SearchByTagFragment.this.p().a(profileFragment, profileFragment.t());
                }

                @Override
                public void a(boolean bl, boolean bl2) {
                }
            }, true);
        }

        private void d(View object, final int n) {
            object = (SearchMediaExpandableListViewItem)object;
            final com.smule.singandroid.list_items.SearchMediaExpandableListItem searchMediaExpandableListItem = (com.smule.singandroid.list_items.SearchMediaExpandableListItem)this.a(n);
            boolean bl = SearchByTagFragment.this.a(searchMediaExpandableListItem, n);
            if (bl) {
                if (MiscUtils.a((PerformanceV2)object.getPerformance())) {
                    object.b(object.t());
                }
                SearchByTagFragment.this.x = (SearchMediaExpandableListViewItem)object;
                SearchByTagFragment.this.J();
            }
            object.a((BaseFragment)((Object)SearchByTagFragment.this), searchMediaExpandableListItem, bl, new SearchMediaExpandableListViewItem.MediaListItemFeedback((SearchMediaExpandableListViewItem)object){
                final /* synthetic */ SearchMediaExpandableListViewItem c;
                {
                    this.c = searchMediaExpandableListViewItem;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void a(Analytics enum_) {
                    void var1_3;
                    Object object = SearchByTagFragment.this.a(searchMediaExpandableListItem.performanceIcon);
                    Object object2 = SearchByTagFragment.this;
                    int n3 = n;
                    int n2 = searchMediaExpandableListItem.b() ? 2 : 3;
                    SearchFragment.AnalyticsResultTriplet analyticsResultTriplet = ((SearchByTagFragment)((Object)object2)).a(n3, n2, SearchByTagFragment.this.q);
                    object2 = searchMediaExpandableListItem.itemType == SearchMediaExpandableListItem.a ? Analytics.l : Analytics.j;
                    if (enum_ == Analytics.d) {
                        Analytics searchResultClkContext = Analytics.g;
                    } else {
                        Analytics searchResultClkContext = Analytics.f;
                    }
                    Analytics searchResultClkValue = Analytics.b;
                    String string2 = SongbookEntryUtils.d((SongbookEntry)object);
                    String string3 = this.c.getPerformance().performanceKey;
                    n2 = analyticsResultTriplet.e();
                    long l = this.c.getPerformance().accountIcon.accountId;
                    String string4 = SongbookEntryUtils.e((SongbookEntry)object);
                    object = this.c.getPerformance().video ? Analytics.a : Analytics.b;
                    com.smule.android.logging.Analytics.a(object2, var1_3, searchResultClkValue, string2, string3, n2, l, string4, object, analyticsResultTriplet.d(), analyticsResultTriplet.f());
                }

                @Override
                public void a(AccountIcon object) {
                    object = ProfileFragment.a((AccountIcon)object);
                    SearchByTagFragment.this.a((BaseFragment)((Object)object));
                    this.a(Analytics.d);
                }

                @Override
                public void a(PerformanceV2 performanceV2) {
                    this.a(Analytics.f);
                    PreSingActivity.a((Context)SearchByTagFragment.this.getActivity()).a(PreSingActivity.StartupMode.b).a(searchMediaExpandableListItem.performanceIcon).a(PreSingActivity.EntryPoint.f).a();
                    SingAnalytics.a((String)PerformanceV2Util.i((PerformanceV2)performanceV2), (JoinSectionType)JoinSectionType.d, (String)PerformanceV2Util.h((PerformanceV2)performanceV2));
                }

                @Override
                public void b(PerformanceV2 performanceV2) {
                    ((MediaPlayingActivity)SearchByTagFragment.this.getActivity()).am().a(performanceV2, SearchByTagFragment.this.t, false);
                }
            }, true);
            object.a(new SearchMediaExpandableListViewItem.SearchMediaExpandableListViewItemClickCallback((SearchMediaExpandableListViewItem)object, n){
                final /* synthetic */ SearchMediaExpandableListViewItem a;
                final /* synthetic */ int b;
                {
                    this.a = searchMediaExpandableListViewItem;
                    this.b = n;
                }

                @Override
                public void a() {
                    SearchByTagFragment.this.a(this.a, this.b, (MagicAdapter)SearchByTagAdapter.this);
                }
            }, new SearchMediaExpandableListViewItem.SearchMediaExpandableListViewItemClickCallback((SearchMediaExpandableListViewItem)object, n){
                final /* synthetic */ SearchMediaExpandableListViewItem a;
                final /* synthetic */ int b;
                {
                    this.a = searchMediaExpandableListViewItem;
                    this.b = n;
                }

                @Override
                public void a() {
                    SearchByTagFragment.this.a(this.a, this.b, (MagicAdapter)SearchByTagAdapter.this);
                }
            });
        }

        @Override
        public View a(ViewGroup viewGroup) {
            if (this.a().i() == MagicDataSource.DataState.e) {
                return new View((Context)SearchByTagFragment.this.getActivity());
            }
            return super.a(viewGroup);
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            switch (n) {
                default: {
                    return SearchMediaExpandableListViewItem.a((Context)SearchByTagFragment.this.getActivity());
                }
                case 0: 
                case 1: 
                case 2: 
                case 3: {
                    return LayoutInflater.from((Context)SearchByTagFragment.this.getActivity()).inflate(2130903426, (ViewGroup)SearchByTagFragment.this.j, false);
                }
                case 4: {
                    return ListingListItem.a((Context)SearchByTagFragment.this.getActivity());
                }
                case 5: 
            }
            return UserFollowListItem.c((Context)SearchByTagFragment.this.getActivity());
        }

        @Override
        public void a(View view, int n, int n2) {
            switch (n2) {
                default: {
                    this.d(view, n);
                    return;
                }
                case 0: 
                case 1: 
                case 2: 
                case 3: {
                    this.a(view, n2);
                    return;
                }
                case 4: {
                    this.b(view, n);
                    return;
                }
                case 5: 
            }
            this.c(view, n);
        }

        @Override
        public void a(MagicDataSource.DataSourceObserver dataSourceObserver) {
            super.a(dataSourceObserver);
        }

        @Override
        public int c(int n) {
            Object object = this.a(n);
            if (object instanceof Integer) {
                return (Integer)object;
            }
            if (object instanceof SongbookEntry) {
                return 4;
            }
            if (object instanceof AccountIcon) {
                return 5;
            }
            if (object instanceof com.smule.singandroid.list_items.SearchMediaExpandableListItem) {
                if (((com.smule.singandroid.list_items.SearchMediaExpandableListItem)object).b()) {
                    return 6;
                }
                return 7;
            }
            throw new RuntimeException("Unknown search mix result item type");
        }

        @Override
        public View c(ViewGroup viewGroup) {
            viewGroup = LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903425, null);
            ((TextView)viewGroup.findViewById(2131756719)).setText(2131297299);
            return viewGroup;
        }

        @Override
        public View d(ViewGroup viewGroup) {
            viewGroup = LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903425, null);
            ((TextView)viewGroup.findViewById(2131756719)).setText((CharSequence)SearchByTagFragment.this.getResources().getString(2131297298, new Object[]{"#" + SearchByTagFragment.this.o}));
            return viewGroup;
        }

        @Override
        public int e() {
            return 8;
        }

        @Override
        public void onScroll(AbsListView absListView, int n, int n2, int n3) {
            super.onScroll(absListView, n, n2, n3);
            if (this.d != -1) {
                this.e = false;
            }
        }

    }

    class SearchByTagDataSource
    extends SearchFragment.SearchDataSource<Object, MagicDataSource.OffsetPaginationTracker> {
        private boolean n;

        SearchByTagDataSource(ArrayList<Object> arrayList) {
            super(SearchByTagDataSource.class.getName() + SearchByTagFragment.this.o, (MagicDataSource.PaginationTracker)new MagicDataSource.OffsetPaginationTracker());
            this.n = false;
            if (arrayList != null) {
                this.b = arrayList;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public Future<?> a(MagicDataSource.OffsetPaginationTracker object, int n, final MagicDataSource.FetchDataCallback<Object, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            int n2 = 0;
            SearchByTagFragment.this.s = System.currentTimeMillis();
            if (this.b == null || this.n) {
                final long l = System.currentTimeMillis();
                if (this.n) {
                    return com.smule.android.network.managers.SearchManager.a().a("#" + SearchByTagFragment.this.o, SearchManager.SearchResultType.c, 0, 25, SearchManager.SearchSortOption.a, new SearchManager.SearchResponseCallback(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void handleResponse(SearchManager object) {
                            int n = 3;
                            int n2 = 0;
                            long l3 = System.currentTimeMillis();
                            long l2 = l;
                            if (!object.a()) {
                                fetchDataCallback.a();
                                return;
                            }
                            SearchByTagFragment.this.r = object;
                            object = SearchByTagFragment.this.a(object.mRecs);
                            ArrayList<Integer> arrayList = new ArrayList<Integer>();
                            if (!object.isEmpty()) {
                                arrayList.add(3);
                                if (object.size() < 3) {
                                    n = object.size();
                                }
                                for (int i = 0; i < n; ++i) {
                                    arrayList.add((Integer)object.get(i));
                                }
                            }
                            SearchByTagFragment.this.n.addAll(arrayList);
                            n = arrayList.isEmpty() ? n2 : arrayList.size() - 1;
                            com.smule.android.logging.Analytics.a(Analytics.h, Analytics.d, n, "#" + SearchByTagFragment.this.o, "#" + SearchByTagFragment.this.o, l3 - l2, null);
                            fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(-1));
                        }
                    });
                }
                return com.smule.android.network.managers.SearchManager.a().a("#" + SearchByTagFragment.this.o, new SearchManager.SearchGlobalResponseCallback(){

                    @Override
                    public void handleResponse(SearchManager sASearchGlobalResponse) {
                        long l2 = System.currentTimeMillis() - l;
                        if (sASearchGlobalResponse.a()) {
                            List list = SearchByTagFragment.this.a(sASearchGlobalResponse.mSeeds);
                            ArrayList<Object> arrayList = new ArrayList<Object>();
                            if (!sASearchGlobalResponse.mSongs.isEmpty()) {
                                List list2 = SongbookEntryUtils.a(sASearchGlobalResponse.mSongs);
                                if (!list2.isEmpty()) {
                                    arrayList.add(0);
                                }
                                arrayList.addAll(list2);
                            }
                            if (!sASearchGlobalResponse.mAccts.isEmpty()) {
                                arrayList.add(1);
                                arrayList.addAll(sASearchGlobalResponse.mAccts);
                            }
                            if (!list.isEmpty()) {
                                arrayList.add(2);
                                arrayList.addAll(list);
                            }
                            SearchByTagFragment.this.n = arrayList;
                            int n = sASearchGlobalResponse.mAccts.size();
                            int n2 = sASearchGlobalResponse.mSongs.size();
                            int n3 = sASearchGlobalResponse.mSeeds.size();
                            com.smule.android.logging.Analytics.a(Analytics.g, Analytics.d, n + n2 + n3, "#" + SearchByTagFragment.this.o, "#" + SearchByTagFragment.this.o, l2, null);
                            SearchByTagDataSource.this.n = true;
                            if (arrayList.isEmpty()) {
                                SearchByTagDataSource.this.a(new MagicDataSource.OffsetPaginationTracker(0), 0, fetchDataCallback);
                                return;
                            }
                            fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker());
                            return;
                        }
                        com.smule.android.logging.Analytics.a(Analytics.g, Analytics.d, 0, "#" + SearchByTagFragment.this.o, "#" + SearchByTagFragment.this.o, l2, null);
                        fetchDataCallback.a();
                    }
                });
            }
            boolean bl = !this.b.contains(3);
            this.n = bl;
            object = this.b.iterator();
            int n3 = 0;
            n = 0;
            int n4 = 0;
            do {
                int n5;
                block8 : {
                    block9 : {
                        block6 : {
                            Object e;
                            block7 : {
                                if (!object.hasNext()) break block6;
                                e = object.next();
                                if (e instanceof Integer) break block7;
                                if (n3 != 0) {
                                    n5 = n3;
                                    n3 = n + 1;
                                    n = n5;
                                } else {
                                    n5 = n4 + 1;
                                    n4 = n;
                                    n = n3;
                                    n3 = n4;
                                    n4 = n5;
                                }
                                break block8;
                            }
                            if ((Integer)e != 3) break block9;
                            n5 = 1;
                            n3 = n;
                            n = n5;
                            break block8;
                        }
                        com.smule.android.logging.Analytics.a(Analytics.g, Analytics.d, n4, "#" + SearchByTagFragment.this.o, "#" + SearchByTagFragment.this.o, 0, null);
                        if (n > 0) {
                            com.smule.android.logging.Analytics.a(Analytics.h, Analytics.d, n, "#" + SearchByTagFragment.this.o, "#" + SearchByTagFragment.this.o, 0, null);
                        }
                        SearchByTagFragment.this.n = this.b;
                        object = this.b;
                        n = this.n ? n2 : -1;
                        fetchDataCallback.a((List<Object>)object, new MagicDataSource.OffsetPaginationTracker(n));
                        this.b = null;
                        return null;
                    }
                    n5 = n;
                    n = n3;
                    n3 = n5;
                }
                n5 = n3;
                n3 = n;
                n = n5;
            } while (true);
        }

        public int b() {
            return 0;
        }

    }

}

