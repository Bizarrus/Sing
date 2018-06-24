package com.smule.singandroid.songbook_search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.ViewBinder.Builder;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Analytics.SearchExecuteContext;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchSortClkTarget;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SearchManager.SASearchResponse;
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
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.dialogs.SortOptionsDialog;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem.MediaListItemFeedback;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem.SearchMediaExpandableListViewItemClickCallback;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.songbook_search.SearchFragment.SearchDataSource;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class SearchShowAllFragment extends SearchBaseFragment {
    public static final String f24178e = SearchShowAllFragment.class.getName();
    public static String f24179x = "search.expanded";
    private SASearchResponse f24180A;
    private SearchShowAllAdapter f24181B;
    private SortOptionsDialog f24182C;
    private TextView f24183D;
    private boolean f24184E = true;
    private BookmarkDialogCallback f24185F = new C48961(this);
    @ViewById
    protected MediaListView f24186f;
    @ViewById
    protected TextView f24187g;
    @ViewById
    protected TextView f24188h;
    @ViewById
    protected ImageView f24189i;
    @ViewById
    protected View f24190j;
    @ViewById
    protected TextView f24191k;
    @ViewById
    protected SwipeRefreshLayout f24192l;
    protected View f24193m;
    @InstanceState
    protected int f24194n;
    @InstanceState
    protected String f24195o;
    @InstanceState
    protected String f24196t;
    @InstanceState
    protected SearchExecuteContext f24197u;
    @InstanceState
    protected SearchTarget f24198v;
    @InstanceState
    protected boolean f24199w = false;
    @InstanceState
    protected boolean f24200y;
    private MagicNativeAdMediatorAdapter f24201z;

    class C48961 implements BookmarkDialogCallback {
        final /* synthetic */ SearchShowAllFragment f24147a;

        C48961(SearchShowAllFragment searchShowAllFragment) {
            this.f24147a = searchShowAllFragment;
        }

        public void mo6428a(PerformanceV2 performanceV2) {
            final Activity activity = (MediaPlayingActivity) this.f24147a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C48961 f24144b;

                public void run() {
                    activity.ad().m22306a(this.f24144b.f24147a, this.f24144b.f24147a.f24190j, this.f24144b.f24147a.f24191k, true);
                }
            });
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
        }

        public void mo6429b(PerformanceV2 performanceV2) {
            final Activity activity = (MediaPlayingActivity) this.f24147a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C48961 f24146b;

                public void run() {
                    activity.ad().m22306a(this.f24146b.f24147a, this.f24146b.f24147a.f24190j, this.f24146b.f24147a.f24191k, false);
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

    class C48972 implements OnRefreshListener {
        final /* synthetic */ SearchShowAllFragment f24148a;

        C48972(SearchShowAllFragment searchShowAllFragment) {
            this.f24148a = searchShowAllFragment;
        }

        public void onRefresh() {
            if (this.f24148a.f24181B != null) {
                this.f24148a.s = -1;
                this.f24148a.f24181B.m18054f();
            }
        }
    }

    class C48983 implements OnClickListener {
        final /* synthetic */ SearchShowAllFragment f24149a;

        C48983(SearchShowAllFragment searchShowAllFragment) {
            this.f24149a = searchShowAllFragment;
        }

        public void onClick(View view) {
            this.f24149a.m25384b(true);
        }
    }

    class C48994 implements OnClickListener {
        final /* synthetic */ SearchShowAllFragment f24150a;

        C48994(SearchShowAllFragment searchShowAllFragment) {
            this.f24150a = searchShowAllFragment;
        }

        public void onClick(View view) {
            this.f24150a.m25384b(false);
        }
    }

    class C49005 implements OnClickListener {
        final /* synthetic */ SearchShowAllFragment f24151a;

        C49005(SearchShowAllFragment searchShowAllFragment) {
            this.f24151a = searchShowAllFragment;
        }

        public void onClick(View view) {
            this.f24151a.m19874y();
            new NativeAdsMoreDialog(this.f24151a).show();
        }
    }

    class C49016 implements Runnable {
        final /* synthetic */ SearchShowAllFragment f24152a;

        C49016(SearchShowAllFragment searchShowAllFragment) {
            this.f24152a = searchShowAllFragment;
        }

        public void run() {
            this.f24152a.m19874y();
        }
    }

    private class SearchShowAllAdapter extends MagicAdapter {
        final /* synthetic */ SearchShowAllFragment f24172c;
        private boolean f24173d;

        public SearchShowAllAdapter(SearchShowAllFragment searchShowAllFragment, MagicDataSource magicDataSource) {
            this.f24172c = searchShowAllFragment;
            super(magicDataSource);
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            switch (this.f24172c.f24194n) {
                case 0:
                    return ListingListItem.m24377a(this.f24172c.getActivity());
                case 1:
                    return UserFollowItem.m24442c(this.f24172c.getActivity());
                default:
                    return SearchMediaExpandableListViewItem.m24461a(this.f24172c.getActivity());
            }
        }

        public boolean mo6799a(View view) {
            return this.f24172c.m25401a();
        }

        public void m25367a(boolean z) {
            this.f24173d = z;
        }

        public View mo6460d(ViewGroup viewGroup) {
            if (!this.f24173d) {
                return super.mo6460d(viewGroup);
            }
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1947R.layout.songbook_search_empty_layout, null);
            TextView textView = (TextView) inflate.findViewById(C1947R.id.search_empty_textview);
            if (this.f24172c.f24194n == 3) {
                textView.setText(this.f24172c.getResources().getString(C1947R.string.search_mix_result_empty_recent_recordings_text));
            } else {
                textView.setText(this.f24172c.getResources().getString(C1947R.string.search_mix_result_empty_invites_text));
            }
            return inflate;
        }

        public View mo6464b(ViewGroup viewGroup) {
            if (this.f24172c.m25401a()) {
                return new View(this.f24172c.getActivity());
            }
            return super.mo6464b(viewGroup);
        }

        public void mo6419a(View view, int i, int i2) {
            switch (this.f24172c.f24194n) {
                case 0:
                    m25362a(view, i);
                    return;
                case 1:
                    m25363b(view, i);
                    return;
                default:
                    m25364c(view, i);
                    return;
            }
        }

        private void m25362a(View view, final int i) {
            final ListingListItem listingListItem = (ListingListItem) view;
            final SongbookEntry songbookEntry = (SongbookEntry) m18027a(i);
            listingListItem.m24380a(songbookEntry, false);
            listingListItem.setAlbumArtClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchShowAllAdapter f24156d;

                public void onClick(View view) {
                    boolean z = true;
                    if (!(songbookEntry instanceof ArrangementVersionLiteEntry)) {
                        this.f24156d.f24172c.mo6443a(songbookEntry);
                    } else if (!listingListItem.m23046t()) {
                        listingListItem.setAlbumArtClickedState(true);
                        if (this.f24156d.f24172c.q == null) {
                            this.f24156d.f24172c.q = new ConcurrentHashMap();
                        }
                        if (this.f24156d.f24172c.q.containsKey(songbookEntry.mo6289c())) {
                            this.f24156d.f24172c.m24136a(((Boolean) this.f24156d.f24172c.q.get(songbookEntry.mo6289c())).booleanValue(), (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                        } else {
                            int i = ((ArrangementVersionLiteEntry) songbookEntry).m18774a().removalCode;
                            if (i < 40 || i > 49) {
                                z = false;
                            }
                            this.f24156d.f24172c.q.put(songbookEntry.mo6289c(), Boolean.valueOf(z));
                            this.f24156d.f24172c.m24136a(z, (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                        }
                    } else {
                        return;
                    }
                    Analytics.m17854a(this.f24156d.f24172c.f24198v, SearchResultClkContext.PREVIEW, SearchResultClkValue.SEE_ALL, SongbookEntryUtils.m26167b(songbookEntry), null, Integer.valueOf(i), null, SongbookEntryUtils.m26168c(songbookEntry), null, this.f24156d.m18048d(), i);
                }
            });
            listingListItem.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchShowAllAdapter f24159c;

                public void onClick(View view) {
                    Analytics.m17854a(this.f24159c.f24172c.f24198v, SearchResultClkContext.REGULAR, SearchResultClkValue.SEE_ALL, SongbookEntryUtils.m26167b(songbookEntry), null, Integer.valueOf(i), null, SongbookEntryUtils.m26168c(songbookEntry), null, this.f24159c.m18048d(), i);
                    if (SongbookEntryUtils.m26166a(songbookEntry, SingServerValues.m21690k())) {
                        SingAnalytics.m26074a(songbookEntry, null, Integer.valueOf(i));
                        PreSingActivity.m24763a(this.f24159c.f24172c.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(songbookEntry).m24797a(SingServerValues.m21690k()).a();
                        return;
                    }
                    this.f24159c.f24172c.mo6487a(UpsellManager.m25791a(true, songbookEntry, SingServerValues.m21690k(), null, UpsellType.VIP_SONG));
                }
            });
        }

        private void m25363b(View view, final int i) {
            boolean z;
            boolean z2;
            String str;
            UserFollowItem userFollowItem = (UserFollowItem) view;
            AccountIcon accountIcon = (AccountIcon) m18027a(i);
            Context activity = this.f24172c.getActivity();
            UserFollowItemListener c49043 = new UserFollowItemListener(this) {
                final /* synthetic */ SearchShowAllAdapter f24161b;

                public void mo6457a(boolean z, boolean z2) {
                }

                public void mo6456a(ProfileFragment profileFragment) {
                    this.f24161b.f24172c.m19862m().a(profileFragment, profileFragment.mo6514z());
                }

                public void mo6455a(SearchResultClkContext searchResultClkContext) {
                    if (this.f24161b.f24172c.isAdded()) {
                        Analytics.m17854a(this.f24161b.f24172c.f24198v, searchResultClkContext, SearchResultClkValue.SEE_ALL, null, null, Integer.valueOf(i), Long.valueOf(((AccountIcon) this.f24161b.m18027a(i)).accountId), null, null, this.f24161b.m18048d(), i);
                    }
                }
            };
            if (i == 9) {
                z = true;
            } else {
                z = false;
            }
            userFollowItem.m24447a(accountIcon, i, activity, false, c49043, true, z);
            String str2 = "";
            boolean z3 = (accountIcon.firstName == null || accountIcon.firstName.isEmpty()) ? false : true;
            if (accountIcon.lastName == null || accountIcon.lastName.isEmpty()) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z3 && z2) {
                str = accountIcon.firstName + " " + accountIcon.lastName;
            } else if (z3) {
                str = accountIcon.firstName;
            } else if (z2) {
                str = accountIcon.lastName;
            } else {
                str = str2;
            }
            if (str.isEmpty()) {
                userFollowItem.m24449a("", false);
            } else {
                userFollowItem.m24449a(str, true);
            }
        }

        private void m25364c(View view, final int i) {
            boolean z;
            final SearchMediaExpandableListViewItem searchMediaExpandableListViewItem = (SearchMediaExpandableListViewItem) view;
            final SearchMediaExpandableListItem searchMediaExpandableListItem = (SearchMediaExpandableListItem) m18027a(i);
            boolean a = this.f24172c.m24138a(searchMediaExpandableListItem, i);
            if (a) {
                if (MiscUtils.m25895a(searchMediaExpandableListViewItem.getPerformance())) {
                    searchMediaExpandableListViewItem.m24468b(searchMediaExpandableListViewItem.m23046t());
                }
                this.f24172c.r = searchMediaExpandableListViewItem;
                this.f24172c.m24127D();
            }
            BaseFragment baseFragment = this.f24172c;
            MediaListItemFeedback c49054 = new MediaListItemFeedback(this) {
                final /* synthetic */ SearchShowAllAdapter f24165d;

                public void mo6853a(AccountIcon accountIcon) {
                    this.f24165d.f24172c.mo6487a(ProfileFragment.m21036a(accountIcon));
                    m25356a(ItemClickType.PROFILE);
                }

                public void mo6854a(PerformanceV2 performanceV2) {
                    m25356a(ItemClickType.JOIN);
                    PreSingActivity.m24763a(this.f24165d.f24172c.getActivity()).m24796a(StartupMode.OPENCALL).m24793a(searchMediaExpandableListItem.performanceIcon).a();
                    SingAnalytics.m26081a(performanceV2.video ? FilterType.f24970b : FilterType.NONE, SectionType.NETWORK);
                }

                public void mo6855b(PerformanceV2 performanceV2) {
                    ((MediaPlayingActivity) this.f24165d.f24172c.getActivity()).ad().m22308a(performanceV2, this.f24165d.f24172c.f24185F, false);
                }

                public void m25356a(ItemClickType itemClickType) {
                    SearchResultClkContext searchResultClkContext;
                    SongbookEntry a = this.f24165d.f24172c.m24130a(searchMediaExpandableListItem.performanceIcon);
                    if (itemClickType == ItemClickType.PROFILE) {
                        searchResultClkContext = SearchResultClkContext.PROFILE;
                    } else {
                        searchResultClkContext = SearchResultClkContext.JOIN;
                    }
                    Analytics.m17854a(this.f24165d.f24172c.f24198v, searchResultClkContext, SearchResultClkValue.SEE_ALL, SongbookEntryUtils.m26167b(a), searchMediaExpandableListViewItem.getPerformance().performanceKey, Integer.valueOf(i), Long.valueOf(searchMediaExpandableListViewItem.getPerformance().accountIcon.accountId), SongbookEntryUtils.m26168c(a), searchMediaExpandableListViewItem.getPerformance().video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO, this.f24165d.m18048d(), i);
                }
            };
            if (i != 0) {
                z = true;
            } else {
                z = false;
            }
            searchMediaExpandableListViewItem.m24465a(baseFragment, searchMediaExpandableListItem, a, c49054, z);
            searchMediaExpandableListViewItem.m24466a(new SearchMediaExpandableListViewItemClickCallback(this) {
                final /* synthetic */ SearchShowAllAdapter f24168c;

                public void mo6856a() {
                    this.f24168c.f24172c.m24135a(searchMediaExpandableListViewItem, i, this.f24168c);
                }
            }, new SearchMediaExpandableListViewItemClickCallback(this) {
                final /* synthetic */ SearchShowAllAdapter f24171c;

                public void mo6856a() {
                    this.f24171c.f24172c.m24135a(searchMediaExpandableListViewItem, i, this.f24171c);
                }
            });
        }
    }

    private class SearchShowAllDataSource extends SearchDataSource<Object> {
        final /* synthetic */ SearchShowAllFragment f24177a;

        public SearchShowAllDataSource(SearchShowAllFragment searchShowAllFragment) {
            this.f24177a = searchShowAllFragment;
            super(searchShowAllFragment.m25374G());
        }

        protected long mo6245c() {
            return TimeUnit.MINUTES.toSeconds(this.f24177a.f24184E ? 5 : 1);
        }

        public int mo6242a() {
            return 25;
        }

        public Future<?> mo6243a(int i, int i2, final FetchDataCallback<Object> fetchDataCallback) {
            if (this.c) {
                this.c = false;
                Analytics.m17853a(this.f24177a.f24198v, this.f24177a.f24197u, this.b.size(), this.f24177a.f24196t, this.f24177a.f24195o, 0, null);
                this.f24177a.m25379L();
                fetchDataCallback.mo6257a(this.b, this.k);
                this.f24177a.f24199w = false;
            } else if (this.f24177a.f24194n != 3 || this.f24177a.f24180A == null) {
                final long currentTimeMillis = System.currentTimeMillis();
                SearchManager.m18331a().m18341a(this.f24177a.f24195o, this.f24177a.m25375H(), i, i2, this.f24177a.m25376I(), new SearchResponseCallback(this) {
                    final /* synthetic */ SearchShowAllDataSource f24176c;

                    public void handleResponse(SASearchResponse sASearchResponse) {
                        if (this.f24176c.f24177a.isAdded()) {
                            this.f24176c.f24177a.f24200y = true;
                            long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                            if (sASearchResponse.a()) {
                                List arrayList = new ArrayList();
                                switch (this.f24176c.f24177a.f24194n) {
                                    case 0:
                                        arrayList.addAll(SongbookEntryUtils.m26165a(sASearchResponse.mSongs));
                                        break;
                                    case 1:
                                        arrayList.addAll(sASearchResponse.mAccts);
                                        break;
                                    case 2:
                                        arrayList.addAll(this.f24176c.f24177a.m24132a(sASearchResponse.mSeeds));
                                        break;
                                    case 3:
                                        arrayList.addAll(this.f24176c.f24177a.m24132a(sASearchResponse.mRecs));
                                        break;
                                }
                                int size = arrayList.size();
                                if (!this.f24176c.f24177a.f24199w) {
                                    size += this.f24176c.m17661k();
                                }
                                Analytics.m17853a(this.f24176c.f24177a.f24198v, this.f24176c.f24177a.f24197u, size, this.f24176c.f24177a.f24196t, this.f24176c.f24177a.f24195o, currentTimeMillis, null);
                                this.f24176c.f24177a.m25379L();
                                fetchDataCallback.mo6257a(arrayList, sASearchResponse.mNext.intValue());
                            } else {
                                Analytics.m17853a(this.f24176c.f24177a.f24198v, this.f24176c.f24177a.f24197u, 0, this.f24176c.f24177a.f24196t, this.f24176c.f24177a.f24195o, currentTimeMillis, null);
                                this.f24176c.f24177a.m25379L();
                                fetchDataCallback.mo6256a();
                            }
                            this.f24176c.f24177a.f24199w = false;
                        }
                    }
                });
            } else {
                List arrayList = new ArrayList();
                arrayList.addAll(this.f24177a.m24132a(this.f24177a.f24180A.mRecs));
                Analytics.m17853a(this.f24177a.f24198v, this.f24177a.f24197u, m17661k() + arrayList.size(), this.f24177a.f24196t, this.f24177a.f24195o, 0, Integer.valueOf(0));
                this.f24177a.m25379L();
                fetchDataCallback.mo6257a(arrayList, this.f24177a.f24180A.mNext.intValue());
                this.f24177a.f24180A = null;
                return null;
            }
            return null;
        }
    }

    protected int mo6932e(int i) {
        return this.f24201z != null ? this.f24201z.getAdjustedPosition(i) : i;
    }

    public String mo6383s() {
        return f24178e;
    }

    private String m25374G() {
        return SearchShowAllDataSource.class.getName() + Integer.toString(this.f24195o.hashCode()) + m25375H().toString() + m25376I();
    }

    protected MediaListView mo6862z() {
        return this.f24186f;
    }

    protected boolean mo6470a(View view) {
        return view.isShown();
    }

    public void mo6443a(SongbookEntry songbookEntry) {
        super.mo6443a(songbookEntry);
    }

    public static SearchShowAllFragment m25381a(int i, SASearchResponse sASearchResponse, String str, String str2, SearchExecuteContext searchExecuteContext) {
        SearchShowAllFragment searchShowAllFragment_ = new SearchShowAllFragment_();
        searchShowAllFragment_.f24180A = sASearchResponse;
        searchShowAllFragment_.f24194n = i;
        searchShowAllFragment_.f24195o = str;
        searchShowAllFragment_.f24196t = str2;
        searchShowAllFragment_.f24197u = searchExecuteContext;
        switch (i) {
            case 0:
                searchShowAllFragment_.f24198v = searchExecuteContext == SearchExecuteContext.TAG ? SearchTarget.DIRECT_SONG : SearchTarget.f16259a;
                break;
            case 1:
                searchShowAllFragment_.f24198v = searchExecuteContext == SearchExecuteContext.TAG ? SearchTarget.DIRECT_USER : SearchTarget.USER;
                break;
            case 2:
                searchShowAllFragment_.f24198v = searchExecuteContext == SearchExecuteContext.TAG ? SearchTarget.DIRECT_INVITE : SearchTarget.INVITE;
                break;
            default:
                searchShowAllFragment_.f24198v = searchExecuteContext == SearchExecuteContext.TAG ? SearchTarget.DIRECT_PERFORMANCE : SearchTarget.PERFORMANCE;
                break;
        }
        return searchShowAllFragment_;
    }

    private SearchResultType m25375H() {
        switch (this.f24194n) {
            case 0:
                return SearchResultType.f17047a;
            case 1:
                return SearchResultType.ACCOUNT;
            case 2:
                return SearchResultType.ACTIVESEED;
            case 3:
                return SearchResultType.RECORDING;
            default:
                throw new RuntimeException("Show all search result unknown type");
        }
    }

    public boolean m25401a() {
        return this.f24194n == 3 || this.f24194n == 2;
    }

    private SearchSortOption m25376I() {
        return this.f24194n == 2 ? this.f24184E ? SearchSortOption.POPULAR : SearchSortOption.EXPIRE : this.f24184E ? SearchSortOption.POPULAR : SearchSortOption.RECENT;
    }

    private SearchSortClkTarget m25377J() {
        return this.f24194n == 2 ? this.f24184E ? SearchSortClkTarget.MOST_POPULAR : SearchSortClkTarget.CLOSING_SOON : this.f24184E ? SearchSortClkTarget.MOST_POPULAR : SearchSortClkTarget.MOST_RECENT;
    }

    private void m25378K() {
        if (this.f24183D != null) {
            SearchSortOption I = m25376I();
            String string = I == SearchSortOption.POPULAR ? getResources().getString(C1947R.string.songbook_sort_option_most_popular) : I == SearchSortOption.RECENT ? getResources().getString(C1947R.string.songbook_sort_option_most_recent) : getResources().getString(C1947R.string.search_sort_closing_soon);
            this.f24183D.setText(getResources().getString(C1947R.string.songbook_search_loading_by_option, new Object[]{string}));
        }
    }

    private void m25379L() {
        if (this.f24192l != null) {
            this.f24192l.setRefreshing(false);
        }
        if (this.f24193m != null) {
            this.f24193m.setVisibility(8);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (m25401a()) {
            m19842a(true);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.f24201z != null) {
            this.f24201z.destroy();
            this.f24201z = null;
        }
    }

    private void m25384b(boolean z) {
        if (this.f24184E == z) {
            this.f24182C.dismiss();
            return;
        }
        this.f24199w = true;
        this.f24184E = z;
        this.s = -1;
        SearchSortClkTarget J = m25377J();
        SearchTarget searchTarget = (this.f24198v == SearchTarget.INVITE || this.f24198v == SearchTarget.DIRECT_INVITE) ? SearchTarget.INVITE : SearchTarget.PERFORMANCE;
        Analytics.m17852a(J, searchTarget);
        m25378K();
        if (this.f24193m != null) {
            this.f24193m.setVisibility(0);
        }
        this.f24182C.m23723a(this.f24184E);
        this.f24186f.setSelection(0);
        MagicDataSource searchShowAllDataSource = new SearchShowAllDataSource(this);
        if (searchShowAllDataSource.c) {
            this.f24181B = new SearchShowAllAdapter(this, searchShowAllDataSource);
            this.f24181B.m25367a(true);
            m25399a(this.f24181B);
        } else {
            this.f24181B.m18026a().m17653b(m25374G());
            this.f24181B.m25367a(true);
            this.f24181B.m18054f();
        }
        this.f24182C.dismiss();
    }

    protected void mo6861a(CharSequence charSequence) {
        this.f24187g.setText(charSequence);
    }

    public void m25403b(CharSequence charSequence) {
        this.f24188h.setText(charSequence);
        this.f24188h.setVisibility(0);
    }

    @AfterViews
    protected void m25393A() {
        CharSequence string;
        boolean z;
        mo6861a(this.f24195o);
        if (m25401a()) {
            View inflate = LayoutInflater.from(getActivity()).inflate(C1947R.layout.search_show_all_loading_layout, null, false);
            this.f24193m = inflate.findViewById(C1947R.id.search_showall_loading_header);
            this.f24183D = (TextView) inflate.findViewById(C1947R.id.loading_textview);
            m25378K();
            this.f24186f.addHeaderView(inflate);
        }
        switch (this.f24194n) {
            case 0:
                string = getString(C1947R.string.core_arrangements);
                break;
            case 1:
                string = getString(C1947R.string.core_singers);
                break;
            case 2:
                string = getString(C1947R.string.core_invites);
                break;
            default:
                string = getString(C1947R.string.core_recordings);
                break;
        }
        m25403b(string);
        if (this.f24181B != null) {
            z = true;
        } else {
            z = false;
        }
        this.f24200y = z;
        if (this.f24181B == null) {
            this.f24181B = new SearchShowAllAdapter(this, new SearchShowAllDataSource(this));
            this.f24181B.m25367a(true);
        }
        m25399a(this.f24181B);
        this.f24192l.setEnabled(m25401a());
        this.f24192l.setColorSchemeResources(new int[]{C1947R.color.refresh_icon});
        this.f24192l.setOnRefreshListener(new C48972(this));
        if (!m25401a()) {
            this.f24189i.setVisibility(8);
        }
    }

    protected SearchTarget mo6931a(SearchMediaExpandableListItem searchMediaExpandableListItem) {
        return this.f24198v;
    }

    protected void mo6420v() {
        if (this.f24200y) {
            Analytics.m17853a(this.f24198v, SearchExecuteContext.BACK, this.f24181B.m18048d(), this.f24196t, this.f24195o, 0, null);
        }
    }

    public void mo6933p() {
        if (getActivity() instanceof MediaPlayingActivity) {
            LayoutParams layoutParams = (LayoutParams) ((MediaPlayingActivity) getActivity()).B().getLayoutParams();
            layoutParams.height = 0;
            ((MediaPlayingActivity) getActivity()).B().setLayoutParams(layoutParams);
        }
    }

    @Click
    protected void m25394B() {
        getActivity().onBackPressed();
    }

    public void onResume() {
        super.onResume();
        MediaPlayingListItem.m23034a(this.f24186f);
        mo6933p();
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

    public void mo6449u() {
        super.mo6449u();
        MediaPlayingListItem.m23034a(this.f24186f);
    }

    @Click
    protected void m25395C() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f24190j);
    }

    @Click
    protected void m25396F() {
        if (this.f24182C == null) {
            int i;
            Activity activity = getActivity();
            if (this.f24194n == 3) {
                i = C1947R.string.songbook_sort_option_most_recent;
            } else {
                i = C1947R.string.search_sort_closing_soon;
            }
            this.f24182C = new SortOptionsDialog(activity, C1947R.string.songbook_sort_option_most_popular, i, new C48983(this), new C48994(this));
        }
        this.f24182C.show();
    }

    protected void m25399a(SearchShowAllAdapter searchShowAllAdapter) {
        if (this.f24194n == 1 || !MagicAdSettings.m17435a(NativeAdPlacementType.SEARCH_EXPANDED)) {
            this.f24186f.setMagicAdapter(searchShowAllAdapter);
            return;
        }
        this.f24201z = MagicAdAdapterFactory.m17426a().m17428a(getActivity(), NativeAdPlacementType.SEARCH_EXPANDED, new Builder(C1947R.layout.native_ad_ghost_search_show_all_item_view).build(), new Builder(C1947R.layout.native_ad_search_show_all_item_view).iconImageId(C1947R.id.search_show_all_ad_icon).titleId(C1947R.id.search_show_all_ad_title).mainImageId(C1947R.id.search_show_all_ad_main_image).textId(C1947R.id.search_show_all_ad_text).callToActionId(C1947R.id.search_show_all_ad_cta).privacyInformationIconImageId(C1947R.id.search_show_all_ad_privacy_icon).build(), new MagicMoPubGhostStreamAdPlacer(getActivity()), AdUtils.m22219a(null), this.f24186f, searchShowAllAdapter, C1947R.id.icn_more, new C49005(this), new C49016(this));
        if (this.f24201z != null) {
            this.f24201z.loadAds();
            return;
        }
        Log.e(f24178e, "mMagicNativeAdMediatorAdapter null");
        this.f24186f.setMagicAdapter(searchShowAllAdapter);
    }
}
