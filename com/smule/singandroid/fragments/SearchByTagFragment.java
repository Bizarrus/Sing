package com.smule.singandroid.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.SearchExecuteContext;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SearchManager.SASearchGlobalResponse;
import com.smule.android.network.managers.SearchManager.SASearchResponse;
import com.smule.android.network.managers.SearchManager.SearchGlobalResponseCallback;
import com.smule.android.network.managers.SearchManager.SearchResponseCallback;
import com.smule.android.network.managers.SearchManager.SearchResultType;
import com.smule.android.network.managers.SearchManager.SearchSortOption;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem$ItemType;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem.MediaListItemFeedback;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem.SearchMediaExpandableListViewItemClickCallback;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.songbook_search.SearchBaseFragment;
import com.smule.singandroid.songbook_search.SearchFragment.AnalyticsResultTriplet;
import com.smule.singandroid.songbook_search.SearchFragment.SearchDataSource;
import com.smule.singandroid.songbook_search.SearchShowAllFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
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

@EFragment
public class SearchByTagFragment extends SearchBaseFragment {
    public static final String f22665e = SearchByTagFragment.class.getName();
    @ViewById
    protected LinearLayout f22666f;
    @ViewById
    protected TextView f22667g;
    @ViewById
    protected MediaListView f22668h;
    @ViewById
    protected View f22669i;
    @ViewById
    protected TextView f22670j;
    @ViewById
    protected TextView f22671k;
    @InstanceState
    protected ArrayList<Object> f22672l;
    @InstanceState
    protected String f22673m;
    @InstanceState
    protected boolean f22674n;
    protected SearchByTagAdapter f22675o;
    private SASearchResponse f22676t;
    private long f22677u;
    private BookmarkDialogCallback f22678v = new C45891(this);

    class C45891 implements BookmarkDialogCallback {
        final /* synthetic */ SearchByTagFragment f22624a;

        C45891(SearchByTagFragment searchByTagFragment) {
            this.f22624a = searchByTagFragment;
        }

        public void mo6428a(PerformanceV2 performanceV2) {
            final Activity activity = (MediaPlayingActivity) this.f22624a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C45891 f22621b;

                public void run() {
                    activity.ad().m22306a(this.f22621b.f22624a, this.f22621b.f22624a.f22669i, this.f22621b.f22624a.f22670j, true);
                }
            });
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
        }

        public void mo6429b(PerformanceV2 performanceV2) {
            final Activity activity = (MediaPlayingActivity) this.f22624a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C45891 f22623b;

                public void run() {
                    activity.ad().m22306a(this.f22623b.f22624a, this.f22623b.f22624a.f22669i, this.f22623b.f22624a.f22670j, false);
                }
            });
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", performanceV2);
        }

        public void mo6430c(PerformanceV2 performanceV2) {
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", performanceV2);
        }

        public void mo6431d(PerformanceV2 performanceV2) {
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", performanceV2);
        }
    }

    private class SearchByTagAdapter extends MagicAdapter {
        final /* synthetic */ SearchByTagFragment f22646c;
        private int f22647d = -1;
        private boolean f22648e = true;

        public SearchByTagAdapter(SearchByTagFragment searchByTagFragment, MagicDataSource magicDataSource) {
            this.f22646c = searchByTagFragment;
            super(magicDataSource);
        }

        public View mo6857a(ViewGroup viewGroup) {
            if (m18026a().m17659i() == DataState.FIRST_PAGE_EMPTY) {
                return new View(this.f22646c.getActivity());
            }
            return super.mo6857a(viewGroup);
        }

        public View mo6460d(ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1947R.layout.songbook_search_empty_layout, null);
            ((TextView) inflate.findViewById(C1947R.id.search_empty_textview)).setText(this.f22646c.getResources().getString(C1947R.string.search_mix_result_empty_text, new Object[]{"#" + this.f22646c.f22673m}));
            return inflate;
        }

        public View mo6459c(ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1947R.layout.songbook_search_empty_layout, null);
            ((TextView) inflate.findViewById(C1947R.id.search_empty_textview)).setText(C1947R.string.search_mix_result_error_text);
            return inflate;
        }

        public int mo6441c(int i) {
            Object a = m18027a(i);
            if (a instanceof Integer) {
                return ((Integer) a).intValue();
            }
            if (a instanceof SongbookEntry) {
                return 4;
            }
            if (a instanceof AccountIcon) {
                return 5;
            }
            if (a instanceof SearchMediaExpandableListItem) {
                return ((SearchMediaExpandableListItem) a).b() ? 6 : 7;
            } else {
                throw new RuntimeException("Unknown search mix result item type");
            }
        }

        public int mo6442e() {
            return 8;
        }

        public void mo6858a(DataSourceObserver dataSourceObserver) {
            super.mo6858a(dataSourceObserver);
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            super.onScroll(absListView, i, i2, i3);
            if (this.f22647d != -1) {
                this.f22648e = false;
            }
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return LayoutInflater.from(this.f22646c.getActivity()).inflate(C1947R.layout.songbook_search_header_layout, this.f22646c.f22668h, false);
                case 4:
                    return ListingListItem.m24377a(this.f22646c.getActivity());
                case 5:
                    return UserFollowItem.m24442c(this.f22646c.getActivity());
                default:
                    return SearchMediaExpandableListViewItem.m24461a(this.f22646c.getActivity());
            }
        }

        public void mo6419a(View view, int i, int i2) {
            switch (i2) {
                case 0:
                case 1:
                case 2:
                case 3:
                    m24108a(view, i2);
                    return;
                case 4:
                    m24109b(view, i);
                    return;
                case 5:
                    m24110c(view, i);
                    return;
                default:
                    m24111d(view, i);
                    return;
            }
        }

        private void m24108a(View view, final int i) {
            TextView textView = (TextView) view.findViewById(C1947R.id.search_header_title);
            view.findViewById(C1947R.id.search_header_button).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchByTagAdapter f22626b;

                public void onClick(View view) {
                    this.f22626b.f22646c.m24174d(i);
                }
            });
            switch (i) {
                case 0:
                    textView.setText(C1947R.string.core_arrangements);
                    return;
                case 1:
                    textView.setText(C1947R.string.core_singers);
                    return;
                case 2:
                    textView.setText(C1947R.string.core_invites);
                    return;
                case 3:
                    textView.setText(C1947R.string.core_recordings);
                    return;
                default:
                    return;
            }
        }

        private void m24109b(View view, final int i) {
            final ListingListItem listingListItem = (ListingListItem) view;
            final SongbookEntry songbookEntry = (SongbookEntry) m18027a(i);
            listingListItem.m24380a(songbookEntry, false);
            listingListItem.setAlbumArtClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchByTagAdapter f22630d;

                public void onClick(View view) {
                    boolean z = true;
                    if (!(songbookEntry instanceof ArrangementVersionLiteEntry)) {
                        this.f22630d.f22646c.mo6443a(songbookEntry);
                    } else if (!listingListItem.m23046t()) {
                        listingListItem.setAlbumArtClickedState(true);
                        if (this.f22630d.f22646c.q == null) {
                            this.f22630d.f22646c.q = new ConcurrentHashMap();
                        }
                        if (this.f22630d.f22646c.q.containsKey(songbookEntry.mo6289c())) {
                            this.f22630d.f22646c.m24136a(((Boolean) this.f22630d.f22646c.q.get(songbookEntry.mo6289c())).booleanValue(), (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                        } else {
                            int i = ((ArrangementVersionLiteEntry) songbookEntry).m18774a().removalCode;
                            if (i < 40 || i > 49) {
                                z = false;
                            }
                            this.f22630d.f22646c.q.put(songbookEntry.mo6289c(), Boolean.valueOf(z));
                            this.f22630d.f22646c.m24136a(z, (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                        }
                    } else {
                        return;
                    }
                    AnalyticsResultTriplet a = this.f22630d.f22646c.m24131a(i, 0, this.f22630d.f22646c.f22675o);
                    Analytics.m17854a(SearchTarget.DIRECT_SONG, SearchResultClkContext.PREVIEW, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(songbookEntry), null, Integer.valueOf(a.m25208e()), null, SongbookEntryUtils.m26168c(songbookEntry), null, a.m25207d(), a.m25209f());
                }
            });
            listingListItem.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchByTagAdapter f22633c;

                public void onClick(View view) {
                    AnalyticsResultTriplet b = this.f22633c.f22646c.m24131a(i, 0, this.f22633c.f22646c.f22675o);
                    Analytics.m17854a(SearchTarget.DIRECT_SONG, SearchResultClkContext.REGULAR, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(songbookEntry), null, Integer.valueOf(b.m25208e()), null, SongbookEntryUtils.m26168c(songbookEntry), null, b.m25207d(), b.m25209f());
                    if (SongbookEntryUtils.m26166a(songbookEntry, SingServerValues.m21690k())) {
                        SingAnalytics.m26074a(songbookEntry, null, Integer.valueOf(b.m25209f()));
                        PreSingActivity.m24763a(this.f22633c.f22646c.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(songbookEntry).m24797a(SingServerValues.m21690k()).a();
                        return;
                    }
                    this.f22633c.f22646c.mo6487a(UpsellManager.m25791a(true, songbookEntry, SingServerValues.m21690k(), null, UpsellType.VIP_SONG));
                }
            });
        }

        private void m24110c(View view, final int i) {
            ((UserFollowItem) view).m24446a((AccountIcon) m18027a(i), i, this.f22646c.getActivity(), false, new UserFollowItemListener(this) {
                final /* synthetic */ SearchByTagAdapter f22635b;

                public void mo6457a(boolean z, boolean z2) {
                }

                public void mo6456a(ProfileFragment profileFragment) {
                    this.f22635b.f22646c.m19862m().a(profileFragment, profileFragment.mo6514z());
                }

                public void mo6455a(SearchResultClkContext searchResultClkContext) {
                    if (this.f22635b.f22646c.isAdded()) {
                        AnalyticsResultTriplet c = this.f22635b.f22646c.m24131a(i, 1, this.f22635b.f22646c.f22675o);
                        Analytics.m17854a(SearchTarget.DIRECT_USER, SearchResultClkContext.REGULAR, SearchResultClkValue.MIXED, null, null, Integer.valueOf(c.m25208e()), Long.valueOf(((AccountIcon) this.f22635b.m18027a(i)).accountId), null, null, c.m25207d(), c.m25209f());
                    }
                }
            }, true);
        }

        private void m24111d(View view, final int i) {
            final SearchMediaExpandableListViewItem searchMediaExpandableListViewItem = (SearchMediaExpandableListViewItem) view;
            final SearchMediaExpandableListItem searchMediaExpandableListItem = (SearchMediaExpandableListItem) m18027a(i);
            boolean a = this.f22646c.m24138a(searchMediaExpandableListItem, i);
            if (a) {
                if (MiscUtils.m25895a(searchMediaExpandableListViewItem.getPerformance())) {
                    searchMediaExpandableListViewItem.m24468b(searchMediaExpandableListViewItem.m23046t());
                }
                this.f22646c.r = searchMediaExpandableListViewItem;
                this.f22646c.m24127D();
            }
            searchMediaExpandableListViewItem.m24465a(this.f22646c, searchMediaExpandableListItem, a, new MediaListItemFeedback(this) {
                final /* synthetic */ SearchByTagAdapter f22639d;

                public void mo6853a(AccountIcon accountIcon) {
                    this.f22639d.f22646c.mo6487a(ProfileFragment.m21036a(accountIcon));
                    m24101a(ItemClickType.PROFILE);
                }

                public void mo6854a(PerformanceV2 performanceV2) {
                    m24101a(ItemClickType.JOIN);
                    PreSingActivity.m24763a(this.f22639d.f22646c.getActivity()).m24796a(StartupMode.OPENCALL).m24793a(searchMediaExpandableListItem.performanceIcon).a();
                    SingAnalytics.m26081a(performanceV2.video ? FilterType.f24970b : FilterType.NONE, SectionType.NETWORK);
                }

                public void mo6855b(PerformanceV2 performanceV2) {
                    ((MediaPlayingActivity) this.f22639d.f22646c.getActivity()).ad().m22308a(performanceV2, this.f22639d.f22646c.f22678v, false);
                }

                public void m24101a(ItemClickType itemClickType) {
                    SearchResultClkContext searchResultClkContext;
                    SongbookEntry a = this.f22639d.f22646c.m24130a(searchMediaExpandableListItem.performanceIcon);
                    AnalyticsResultTriplet d = this.f22639d.f22646c.m24131a(i, searchMediaExpandableListItem.b() ? 2 : 3, this.f22639d.f22646c.f22675o);
                    SearchTarget searchTarget = searchMediaExpandableListItem.itemType == SearchMediaExpandableListItem$ItemType.f23184a ? SearchTarget.DIRECT_INVITE : SearchTarget.DIRECT_PERFORMANCE;
                    if (itemClickType == ItemClickType.PROFILE) {
                        searchResultClkContext = SearchResultClkContext.PROFILE;
                    } else {
                        searchResultClkContext = SearchResultClkContext.JOIN;
                    }
                    Analytics.m17854a(searchTarget, searchResultClkContext, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(a), searchMediaExpandableListViewItem.getPerformance().performanceKey, Integer.valueOf(d.m25208e()), Long.valueOf(searchMediaExpandableListViewItem.getPerformance().accountIcon.accountId), SongbookEntryUtils.m26168c(a), searchMediaExpandableListViewItem.getPerformance().video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO, d.m25207d(), d.m25209f());
                }
            }, true);
            searchMediaExpandableListViewItem.m24466a(new SearchMediaExpandableListViewItemClickCallback(this) {
                final /* synthetic */ SearchByTagAdapter f22642c;

                public void mo6856a() {
                    this.f22642c.f22646c.m24135a(searchMediaExpandableListViewItem, i, this.f22642c);
                }
            }, new SearchMediaExpandableListViewItemClickCallback(this) {
                final /* synthetic */ SearchByTagAdapter f22645c;

                public void mo6856a() {
                    this.f22645c.f22646c.m24135a(searchMediaExpandableListViewItem, i, this.f22645c);
                }
            });
        }
    }

    class SearchByTagDataSource extends SearchDataSource<Object> {
        final /* synthetic */ SearchByTagFragment f22658a;
        private boolean f22659l = false;

        public SearchByTagDataSource(SearchByTagFragment searchByTagFragment, ArrayList<Object> arrayList) {
            this.f22658a = searchByTagFragment;
            super(SearchByTagDataSource.class.getName() + searchByTagFragment.f22673m);
            if (arrayList != null) {
                this.b = arrayList;
            }
        }

        public int l_() {
            return 0;
        }

        public Future<?> mo6243a(int i, int i2, final FetchDataCallback<Object> fetchDataCallback) {
            int i3 = 0;
            this.f22658a.f22677u = System.currentTimeMillis();
            if (this.b == null || this.f22659l) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (!this.f22659l) {
                    return SearchManager.m18331a().m18339a("#" + this.f22658a.f22673m, new SearchGlobalResponseCallback(this) {
                        final /* synthetic */ SearchByTagDataSource f22654c;

                        public void handleResponse(SASearchGlobalResponse sASearchGlobalResponse) {
                            long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                            if (sASearchGlobalResponse.a()) {
                                Collection b = this.f22654c.f22658a.m24132a(sASearchGlobalResponse.mSeeds);
                                List arrayList = new ArrayList();
                                if (!sASearchGlobalResponse.mSongs.isEmpty()) {
                                    Collection a = SongbookEntryUtils.m26165a(sASearchGlobalResponse.mSongs);
                                    if (!a.isEmpty()) {
                                        arrayList.add(Integer.valueOf(0));
                                    }
                                    arrayList.addAll(a);
                                }
                                if (!sASearchGlobalResponse.mAccts.isEmpty()) {
                                    arrayList.add(Integer.valueOf(1));
                                    arrayList.addAll(sASearchGlobalResponse.mAccts);
                                }
                                if (!b.isEmpty()) {
                                    arrayList.add(Integer.valueOf(2));
                                    arrayList.addAll(b);
                                }
                                this.f22654c.f22658a.f22672l = arrayList;
                                Analytics.m17853a(SearchTarget.DIRECT_INSTANT_MIXED, SearchExecuteContext.TAG, (sASearchGlobalResponse.mAccts.size() + sASearchGlobalResponse.mSongs.size()) + sASearchGlobalResponse.mSeeds.size(), "#" + this.f22654c.f22658a.f22673m, "#" + this.f22654c.f22658a.f22673m, currentTimeMillis, null);
                                this.f22654c.f22659l = true;
                                if (arrayList.isEmpty()) {
                                    this.f22654c.mo6243a(0, 0, fetchDataCallback);
                                    return;
                                } else {
                                    fetchDataCallback.mo6257a(arrayList, 0);
                                    return;
                                }
                            }
                            Analytics.m17853a(SearchTarget.DIRECT_INSTANT_MIXED, SearchExecuteContext.TAG, 0, "#" + this.f22654c.f22658a.f22673m, "#" + this.f22654c.f22658a.f22673m, currentTimeMillis, null);
                            fetchDataCallback.mo6256a();
                        }
                    });
                }
                return SearchManager.m18331a().m18341a("#" + this.f22658a.f22673m, SearchResultType.RECORDING, 0, 25, SearchSortOption.POPULAR, new SearchResponseCallback(this) {
                    final /* synthetic */ SearchByTagDataSource f22651c;

                    public void handleResponse(SASearchResponse sASearchResponse) {
                        int i = 3;
                        int i2 = 0;
                        long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                        if (sASearchResponse.a()) {
                            this.f22651c.f22658a.f22676t = sASearchResponse;
                            List a = this.f22651c.f22658a.m24132a(sASearchResponse.mRecs);
                            Object arrayList = new ArrayList();
                            if (!a.isEmpty()) {
                                arrayList.add(Integer.valueOf(3));
                                if (a.size() < 3) {
                                    i = a.size();
                                }
                                for (int i3 = 0; i3 < i; i3++) {
                                    arrayList.add(a.get(i3));
                                }
                            }
                            this.f22651c.f22658a.f22672l.addAll(arrayList);
                            if (!arrayList.isEmpty()) {
                                i2 = arrayList.size() - 1;
                            }
                            Analytics.m17853a(SearchTarget.DIRECT_INSTANT_PERFORMANCE, SearchExecuteContext.TAG, i2, "#" + this.f22651c.f22658a.f22673m, "#" + this.f22651c.f22658a.f22673m, currentTimeMillis, null);
                            fetchDataCallback.mo6257a(arrayList, -1);
                            return;
                        }
                        fetchDataCallback.mo6256a();
                    }
                });
            }
            this.f22659l = !this.b.contains(Integer.valueOf(3));
            Iterator it = this.b.iterator();
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (it.hasNext()) {
                int i7;
                Object next = it.next();
                if (next instanceof Integer) {
                    if (((Integer) next).intValue() == 3) {
                        i7 = 1;
                        i4 = i5;
                    } else {
                        i7 = i4;
                        i4 = i5;
                    }
                } else if (i4 != 0) {
                    i7 = i4;
                    i4 = i5 + 1;
                } else {
                    i6++;
                    i7 = i4;
                    i4 = i5;
                }
                i5 = i4;
                i4 = i7;
            }
            Analytics.m17853a(SearchTarget.DIRECT_INSTANT_MIXED, SearchExecuteContext.TAG, i6, "#" + this.f22658a.f22673m, "#" + this.f22658a.f22673m, 0, null);
            if (i5 > 0) {
                Analytics.m17853a(SearchTarget.DIRECT_INSTANT_PERFORMANCE, SearchExecuteContext.TAG, i5, "#" + this.f22658a.f22673m, "#" + this.f22658a.f22673m, 0, null);
            }
            this.f22658a.f22672l = this.b;
            List list = this.b;
            if (!this.f22659l) {
                i3 = -1;
            }
            fetchDataCallback.mo6257a(list, i3);
            this.b = null;
            return null;
        }
    }

    public String mo6383s() {
        return f22665e;
    }

    public String m24169a() {
        String str = "";
        if (!this.f22674n) {
            str = this.f22673m;
        }
        return f22665e + "-" + str;
    }

    public static SearchByTagFragment m24145a(String str, boolean z) {
        SearchByTagFragment searchByTagFragment_ = new SearchByTagFragment_();
        searchByTagFragment_.f22673m = str;
        searchByTagFragment_.f22674n = z;
        return searchByTagFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(false);
    }

    protected MediaListView mo6862z() {
        return this.f22668h;
    }

    protected boolean mo6470a(View view) {
        return view.isShown();
    }

    public void mo6443a(SongbookEntry songbookEntry) {
        super.m19829a(songbookEntry, SearchTarget.DIRECT_SONG);
    }

    @AfterViews
    protected void m24166A() {
        if (this.f22674n) {
            this.f22667g.setText(C1947R.string.search_username_doesnt_exist);
            this.f22666f.setVisibility(0);
            return;
        }
        this.f22666f.setVisibility(8);
        if (this.f22675o != null) {
            this.f22668h.setMagicAdapter(this.f22675o);
            return;
        }
        this.f22675o = new SearchByTagAdapter(this, new SearchByTagDataSource(this, this.f22672l));
        this.f22668h.setMagicAdapter(this.f22675o);
    }

    protected void mo6420v() {
        int i = 0;
        if (this.f22672l != null) {
            Iterator it = this.f22672l.iterator();
            int i2 = 0;
            int i3 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                if ((next instanceof Integer) && next.equals(Integer.valueOf(3))) {
                    i3 = 1;
                } else if (!(next instanceof Integer)) {
                    if (i3 != 0) {
                        i++;
                    } else {
                        i2++;
                    }
                }
            }
            Analytics.m17853a(SearchTarget.DIRECT_INSTANT_MIXED, SearchExecuteContext.BACK, i2, "#" + this.f22673m, "#" + this.f22673m, 0, null);
            if (i != 0) {
                Analytics.m17853a(SearchTarget.DIRECT_PERFORMANCE, SearchExecuteContext.BACK, i, "#" + this.f22673m, "#" + this.f22673m, 0, null);
            }
        }
    }

    public void mo6861a(CharSequence charSequence) {
        this.f22671k.setText(charSequence);
    }

    @Click
    protected void m24167B() {
        getActivity().onBackPressed();
    }

    public void onResume() {
        super.onResume();
        mo6933p();
        mo6861a((this.f22674n ? "@" : "#") + this.f22673m);
        MediaPlayingListItem.m23034a(this.f22668h);
    }

    public void onStart() {
        super.onStart();
        if (this.r == null || !MediaPlayerServiceController.m24641a().m24662c(this.r.getMediaKey())) {
            this.s = -1;
        }
    }

    public void onStop() {
        super.onStop();
        MediaPlayerServiceController.m24641a().m24687r();
    }

    public void onDestroy() {
        super.onDestroy();
        m19866q();
    }

    protected void m24174d(int i) {
        BaseFragment a = SearchShowAllFragment.m25381a(i, this.f22676t, "#" + this.f22673m, "#" + this.f22673m, SearchExecuteContext.TAG);
        if (getActivity() instanceof MediaPlayingActivity) {
            MiscUtils.m25891a(getActivity(), true);
            ((MediaPlayingActivity) getActivity()).a(a, a.mo6383s(), C1947R.animator.slide_enter_from_right, C1947R.animator.slide_exit_from_right, C1947R.animator.slide_enter_from_left, C1947R.animator.slide_exit_from_left, true, true);
            return;
        }
        mo6487a(a);
    }

    public void mo6449u() {
        super.mo6449u();
        MediaPlayingListItem.m23034a(this.f22668h);
    }

    public boolean mo6400c() {
        return false;
    }

    @Click
    protected void m24168C() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f22669i);
    }
}
