package com.smule.singandroid.songbook_search;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.mopub.nativeads.MagicMoPubStreamAdPlacer;
import com.mopub.nativeads.ViewBinder;
import com.mopub.nativeads.ViewBinder.Builder;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Analytics.RecSysContext;
import com.smule.android.logging.Analytics.RecommendationType;
import com.smule.android.logging.Analytics.SearchBarExitContext;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Analytics.SearchExecuteContext;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.RecommendationManager.GetRecommendedTrendingsCallback;
import com.smule.android.network.managers.RecommendationManager.RecommendedTrendingsResponse;
import com.smule.android.network.managers.RecommendationManager.RecommendedTrendingsResponse.RecTrendingSearch;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SearchManager.SASearchAutocompleteResponse;
import com.smule.android.network.managers.SearchManager.SASearchResponse;
import com.smule.android.network.managers.SearchManager.SearchAutocompleteResponseCallback;
import com.smule.android.network.managers.SearchManager.SearchGlobalResponseCallback;
import com.smule.android.network.managers.SearchManager.SearchInstantResponseCallback;
import com.smule.android.network.managers.SearchManager.SearchResponseCallback;
import com.smule.android.network.managers.SearchManager.SearchResultType;
import com.smule.android.network.managers.SearchManager.SearchSortOption;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SAOption;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.AutocompleteUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.android.utils.WeakListener.TextWatcher;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
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
import com.smule.singandroid.models.Triplet;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FilterType;
import com.smule.singandroid.utils.SingAnalytics.SectionType;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class SearchFragment extends SearchBaseFragment implements DataSourceObserver {
    public static final String f24075e = SearchFragment.class.getName();
    @InstanceState
    protected ArrayList<String> f24076A;
    @InstanceState
    protected boolean f24077B;
    @InstanceState
    protected boolean f24078C;
    @InstanceState
    protected boolean f24079D;
    @InstanceState
    protected int f24080E = 0;
    @InstanceState
    protected int f24081F = 0;
    @ViewById
    protected View f24082G;
    @ViewById
    protected TextView f24083H;
    @ViewById
    protected View f24084I;
    protected InputAnalyticsHelper f24085J = new InputAnalyticsHelper();
    protected SearchMixResultAdapter f24086K;
    @InstanceState
    protected String f24087L = "";
    @InstanceState
    protected String f24088M = "";
    @InstanceState
    protected int f24089N;
    @InstanceState
    protected int f24090O;
    @InstanceState
    protected int f24091P;
    OnGlobalLayoutListener f24092Q = new C48652(this);
    protected String f24093R;
    AtomicBoolean f24094S;
    private long f24095T;
    private long f24096U;
    private long f24097V;
    private SASearchResponse f24098W;
    private TrendingAdapter f24099X;
    private RecentAdapter f24100Y;
    private boolean f24101Z;
    private SearchExecuteContext aa;
    private boolean ab;
    private View ac;
    private View ad;
    private boolean ae;
    private BookmarkDialogCallback af = new C48716(this);
    private boolean ag;
    private long ah;
    private TextWatcher ai = new TextWatcher(this.f24103g, new C48759(this));
    private Handler aj = new Handler();
    private Handler ak = new Handler();
    private Runnable al = new Runnable(this) {
        final /* synthetic */ SearchFragment f23967a;

        {
            this.f23967a = r1;
        }

        public void run() {
            if (this.f23967a.isAdded()) {
                this.f23967a.m25312a(null, "", false);
            }
        }
    };
    private Runnable am;
    private Runnable an = new Runnable(this) {
        final /* synthetic */ SearchFragment f23971a;

        {
            this.f23971a = r1;
        }

        public void run() {
            if (this.f23971a.isAdded()) {
                final String obj = this.f23971a.f24103g.getText().toString();
                Log.b(SearchFragment.f24075e, "Running auto-complete search with term: " + obj);
                this.f23971a.f24087L = obj;
                if (obj.length() == 0) {
                    int i;
                    if (this.f23971a.f24099X == null || this.f23971a.f24099X.getCount() == 0) {
                        i = 50;
                    } else {
                        i = 3;
                    }
                    this.f23971a.m25312a(AutocompleteUtils.m18946a(SingApplication.f(), i), "", true);
                    this.f23971a.m25256M();
                    this.f23971a.m25315b(true);
                    return;
                }
                this.f23971a.m25315b(false);
                this.f23971a.aj.postDelayed(this.f23971a.al, 1000);
                final int x = this.f23971a.d;
                this.f23971a.f24095T = System.currentTimeMillis();
                SearchManager.m18331a().m18338a(obj, new SearchAutocompleteResponseCallback(this) {
                    final /* synthetic */ AnonymousClass11 f23970c;

                    public void handleResponse(SASearchAutocompleteResponse sASearchAutocompleteResponse) {
                        if (this.f23970c.f23971a.m19843a(x)) {
                            this.f23970c.f23971a.f24096U = System.currentTimeMillis() - this.f23970c.f23971a.f24095T;
                            ArrayList arrayList = new ArrayList();
                            if (sASearchAutocompleteResponse.a()) {
                                this.f23970c.f23971a.aj.removeCallbacks(this.f23970c.f23971a.al);
                                Iterator it = sASearchAutocompleteResponse.mOptions.iterator();
                                while (it.hasNext()) {
                                    arrayList.add(((SAOption) it.next()).text);
                                }
                                this.f23970c.f23971a.m25312a(arrayList, obj, false);
                                this.f23970c.f23971a.f24085J.m25213a(arrayList);
                                this.f23970c.f23971a.f24085J.m25214a(true);
                                return;
                            }
                            Log.c(SearchFragment.f24075e, "Failed to load songbook search suggestions.");
                            this.f23970c.f23971a.f24085J.m25213a(arrayList);
                            this.f23970c.f23971a.f24085J.m25214a(true);
                        }
                    }
                });
                return;
            }
            Log.d(SearchFragment.f24075e, "mExecuteSearchRunnable - fragment not added; aborting");
        }
    };
    private MagicNativeAdMediatorAdapter ao;
    private MagicMoPubStreamAdPlacer ap;
    @ViewById
    protected RelativeLayout f24102f;
    @ViewById
    protected EditText f24103g;
    @InstanceState
    protected String f24104h;
    @InstanceState
    protected String f24105i;
    protected ListView f24106j;
    protected MagicListView f24107k;
    protected View f24108l;
    protected View f24109m;
    protected View f24110n;
    protected View f24111o;
    protected TextView f24112t;
    protected TextView f24113u;
    protected View f24114v;
    @ViewById
    protected MediaListView f24115w;
    protected View f24116x;
    protected TextView f24117y;
    @InstanceState
    protected ArrayList<Object> f24118z;

    public static abstract class SearchDataSource<T> extends MagicDataSource<T> {
        protected ArrayList<T> f22655b;
        protected boolean f22656c;
        protected int f22657k;

        private static class SearchCacheItem<T> extends CacheItem {
            ArrayList<T> f24030e;
            int f24031f;

            protected SearchCacheItem(String str, long j, long j2, ArrayList<T> arrayList) {
                super(str, j, j2);
                this.a = str;
                this.b = j;
                this.f24030e = arrayList;
            }
        }

        public SearchDataSource(String str) {
            super(str);
        }

        protected synchronized void m24120a(ArrayList<T> arrayList) {
            this.f = arrayList;
            this.f22655b = arrayList;
            mo6266f();
        }

        protected synchronized void mo6266f() {
            if (!(this.e == null || mo6245c() == 0)) {
                if (((SearchCacheItem) d.get(this.e)) == null) {
                    this.f22655b = new ArrayList();
                    this.f22655b.addAll(this.f);
                    SearchCacheItem searchCacheItem = new SearchCacheItem(this.e, System.currentTimeMillis(), mo6245c() * 1000, this.f22655b);
                    searchCacheItem.f24031f = this.i;
                    d.put(this.e, searchCacheItem);
                }
                m17658h();
            }
        }

        protected boolean mo6265e() {
            if (this.e == null) {
                this.f22656c = false;
                return false;
            }
            SearchCacheItem searchCacheItem = (SearchCacheItem) d.get(this.e);
            if (searchCacheItem != null) {
                boolean z;
                synchronized (this.e) {
                    if (m17648a((CacheItem) searchCacheItem) || searchCacheItem.f24030e == null) {
                        d.remove(this.e);
                        z = false;
                    } else {
                        this.f22655b = searchCacheItem.f24030e;
                        this.f22657k = searchCacheItem.f24031f;
                        z = true;
                    }
                }
                this.f22656c = z;
                return z;
            }
            this.f22656c = false;
            return false;
        }
    }

    class C48641 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchFragment f24001a;

        C48641(SearchFragment searchFragment) {
            this.f24001a = searchFragment;
        }

        public void onAnimationEnd(Animator animator) {
            this.f24001a.m25254K();
        }
    }

    class C48652 implements OnGlobalLayoutListener {
        final /* synthetic */ SearchFragment f24002a;

        C48652(SearchFragment searchFragment) {
            this.f24002a = searchFragment;
        }

        public void onGlobalLayout() {
            int visibility = this.f24002a.f24106j.getVisibility();
            if (((Integer) this.f24002a.f24106j.getTag()).intValue() != visibility) {
                this.f24002a.f24106j.setTag(Integer.valueOf(visibility));
                if (visibility == 0) {
                    this.f24002a.m25254K();
                }
            }
            this.f24002a.f24106j.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    class C48663 implements GetRecommendedTrendingsCallback {
        final /* synthetic */ SearchFragment f24003a;

        C48663(SearchFragment searchFragment) {
            this.f24003a = searchFragment;
        }

        public void handleResponse(RecommendedTrendingsResponse recommendedTrendingsResponse) {
            if (this.f24003a.isAdded()) {
                this.f24003a.ae = true;
                this.f24003a.f24111o.setVisibility(8);
                if (!recommendedTrendingsResponse.a() || recommendedTrendingsResponse.mTrendingSearches.size() == 0) {
                    this.f24003a.m25312a(!this.f24003a.f24101Z ? AutocompleteUtils.m18946a(SingApplication.f(), 50) : this.f24003a.f24076A, this.f24003a.f24077B ? this.f24003a.f24104h : null, this.f24003a.f24079D);
                    RecommendationManager.m18285a().m18300a(new ArrayList());
                    return;
                }
                RecommendationManager.m18285a().m18300a(recommendedTrendingsResponse.mTrendingSearches);
                this.f24003a.m25270b(recommendedTrendingsResponse.mTrendingSearches);
            }
        }
    }

    class C48674 implements OnClickListener {
        final /* synthetic */ SearchFragment f24004a;

        C48674(SearchFragment searchFragment) {
            this.f24004a = searchFragment;
        }

        public void onClick(View view) {
            this.f24004a.m25300C();
        }
    }

    class C48685 implements OnClickListener {
        final /* synthetic */ SearchFragment f24005a;

        C48685(SearchFragment searchFragment) {
            this.f24005a = searchFragment;
        }

        public void onClick(View view) {
            this.f24005a.m25317c(this.f24005a.f24105i);
            this.f24005a.m25311a(this.f24005a.f24105i, this.f24005a.f24105i, false, SearchExecuteContext.INSTEAD, 0, false);
        }
    }

    class C48716 implements BookmarkDialogCallback {
        final /* synthetic */ SearchFragment f24010a;

        C48716(SearchFragment searchFragment) {
            this.f24010a = searchFragment;
        }

        public void mo6428a(PerformanceV2 performanceV2) {
            final Activity activity = (MasterActivity) this.f24010a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C48716 f24007b;

                public void run() {
                    activity.ad().m22306a(this.f24007b.f24010a, this.f24007b.f24010a.f24082G, this.f24007b.f24010a.f24083H, true);
                }
            });
            NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", performanceV2);
        }

        public void mo6429b(PerformanceV2 performanceV2) {
            final Activity activity = (MasterActivity) this.f24010a.getActivity();
            new UiHandler(activity).m19081a(new Runnable(this) {
                final /* synthetic */ C48716 f24009b;

                public void run() {
                    activity.ad().m22306a(this.f24009b.f24010a, this.f24009b.f24010a.f24082G, this.f24009b.f24010a.f24083H, false);
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

    class C48727 implements OnTouchListener {
        final /* synthetic */ SearchFragment f24011a;

        C48727(SearchFragment searchFragment) {
            this.f24011a = searchFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f24011a.f24115w != null) {
                if (((InputMethodManager) this.f24011a.getActivity().getSystemService("input_method")).isAcceptingText() && System.currentTimeMillis() - this.f24011a.ah > 500) {
                    this.f24011a.ah = System.currentTimeMillis();
                    this.f24011a.m25262a(SearchBarExitContext.SCROLL, this.f24011a.f24088M, this.f24011a.f24103g.getText().toString());
                }
                if (motionEvent.getAction() == 1 && this.f24011a.f24106j.getVisibility() == 0) {
                    this.f24011a.m25254K();
                }
            }
            return false;
        }
    }

    class C48738 implements OnEditorActionListener {
        final /* synthetic */ SearchFragment f24012a;

        C48738(SearchFragment searchFragment) {
            this.f24012a = searchFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            String charSequence = textView.getText().toString();
            Log.b(SearchFragment.f24075e, "onQueryTextSubmit - " + charSequence);
            this.f24012a.m25257N();
            this.f24012a.m25311a(charSequence, charSequence, false, SearchExecuteContext.INPUT, -1, true);
            return true;
        }
    }

    class C48759 implements android.text.TextWatcher {
        final /* synthetic */ SearchFragment f24015a;

        C48759(SearchFragment searchFragment) {
            this.f24015a = searchFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.f24015a.isAdded()) {
                int i4;
                View view = this.f24015a.f24084I;
                if (charSequence.length() == 0) {
                    i4 = 8;
                } else {
                    i4 = 0;
                }
                view.setVisibility(i4);
                if (this.f24015a.ag) {
                    this.f24015a.ag = false;
                    return;
                }
                Log.b(SearchFragment.f24075e, "onQueryTextChange - " + charSequence);
                this.f24015a.f24085J.m25214a(false);
                this.f24015a.f24085J.m25215b(false);
                if (charSequence.equals("@")) {
                    FollowManager.m18204a().m18226c();
                }
                this.f24015a.m25257N();
                this.f24015a.m25258O();
                if (this.f24015a.a != null) {
                    this.f24015a.a.m25989a(0);
                }
                this.f24015a.f24103g.setHintTextColor(this.f24015a.getResources().getColor(C1947R.color.contextual_text));
                this.f24015a.aj.postDelayed(this.f24015a.an, (long) this.f24015a.f24089N);
                final String obj = this.f24015a.f24103g.getText().toString();
                this.f24015a.am = new Runnable(this) {
                    final /* synthetic */ C48759 f24014b;

                    public void run() {
                        if (this.f24014b.f24015a.isAdded() && obj.equals(this.f24014b.f24015a.f24103g.getText().toString())) {
                            this.f24014b.f24015a.m25311a(obj, this.f24014b.f24015a.f24103g.getText().toString(), true, SearchExecuteContext.INPUT, 0, false);
                        }
                    }
                };
                this.f24015a.ak.postDelayed(this.f24015a.am, (long) this.f24015a.f24090O);
                if (this.f24015a.f24103g.getText().toString().isEmpty() && !this.f24015a.ab) {
                    this.f24015a.m25262a(SearchBarExitContext.CLEAR, this.f24015a.f24087L, "");
                }
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    public static class AnalyticsResultTriplet extends Triplet<Integer, Integer, Integer> {
        public AnalyticsResultTriplet(Integer num, Integer num2, Integer num3) {
            super(num, num2, num3);
        }

        public int m25207d() {
            return ((Integer) m24758a()).intValue();
        }

        public int m25208e() {
            return ((Integer) m24759b()).intValue();
        }

        public int m25209f() {
            return ((Integer) m24760c()).intValue();
        }
    }

    protected static class InputAnalyticsHelper {
        private boolean f24016a;
        private boolean f24017b;
        private Runnable f24018c;
        private ArrayList<String> f24019d;

        protected InputAnalyticsHelper() {
        }

        private boolean m25210b() {
            return this.f24016a && this.f24017b;
        }

        public void m25212a(Runnable runnable) {
            this.f24018c = runnable;
        }

        public void m25214a(boolean z) {
            this.f24016a = z;
            if (!z) {
                this.f24018c = null;
            } else if (m25210b() && this.f24018c != null) {
                new Handler().post(this.f24018c);
            }
        }

        public void m25215b(boolean z) {
            this.f24017b = z;
            if (!z) {
                this.f24018c = null;
            } else if (m25210b() && this.f24018c != null) {
                new Handler().post(this.f24018c);
            }
        }

        public void m25213a(ArrayList<String> arrayList) {
            this.f24019d = arrayList;
        }

        public ArrayList<String> m25211a() {
            return this.f24019d;
        }
    }

    private abstract class SearchAdapter extends MagicAdapter {
        final /* synthetic */ SearchFragment f24024d;

        public SearchAdapter(SearchFragment searchFragment, MagicDataSource magicDataSource) {
            this.f24024d = searchFragment;
            super(magicDataSource);
        }
    }

    private class RecentAdapter extends SearchAdapter {
        final /* synthetic */ SearchFragment f24025c;
        private boolean f24026e;
        private String f24027f;
        private Set<Integer> f24028g;

        public RecentAdapter(SearchFragment searchFragment, MagicDataSource magicDataSource, boolean z, String str) {
            this.f24025c = searchFragment;
            super(searchFragment, magicDataSource);
            this.f24026e = z;
            this.f24027f = str;
        }

        public void m25221a(Set<Integer> set) {
            this.f24028g = set;
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return LayoutInflater.from(this.f24025c.getActivity()).inflate(C1947R.layout.autocomplete_item, this.f24025c.f24107k, false);
        }

        public void mo6858a(DataSourceObserver dataSourceObserver) {
            try {
                super.mo6858a(dataSourceObserver);
            } catch (IllegalStateException e) {
            }
        }

        public void mo6419a(View view, final int i, int i2) {
            final TextView textView = (TextView) view.findViewById(C1947R.id.suggestion);
            final ImageView imageView = (ImageView) view.findViewById(C1947R.id.time_icon);
            String str = (String) m18027a(i);
            view.findViewById(C1947R.id.close_button).setVisibility(8);
            CharSequence spannableStringBuilder = new SpannableStringBuilder(str);
            List<Pair> a = AutocompleteUtils.m18948a(str, this.f24027f);
            if (a != null) {
                for (Pair pair : a) {
                    spannableStringBuilder.setSpan(new StyleSpan(1), ((Integer) pair.first).intValue(), ((Integer) pair.second).intValue(), 18);
                }
            }
            if (this.f24026e || AutocompleteUtils.m18953b(SingApplication.f(), str)) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(4);
            }
            textView.setText(spannableStringBuilder);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RecentAdapter f24023d;

                public void onClick(View view) {
                    SearchClkContext searchClkContext;
                    int size;
                    long j;
                    String obj = this.f24023d.f24025c.f24103g.getText().toString();
                    if (obj.isEmpty()) {
                        obj = null;
                    }
                    String charSequence = textView.getText().toString();
                    Boolean bool = null;
                    Boolean bool2 = null;
                    Boolean bool3 = null;
                    if (this.f24023d.f24026e) {
                        searchClkContext = SearchClkContext.RECENT;
                        size = AutocompleteUtils.m18946a(SingApplication.f(), 50).size();
                        j = 0;
                    } else {
                        SearchClkContext searchClkContext2 = SearchClkContext.MIXED;
                        int d = this.f24023d.m18048d();
                        j = this.f24023d.f24025c.f24096U;
                        bool3 = this.f24023d.m18048d() >= 3 ? Boolean.valueOf(this.f24023d.f24028g.contains(Integer.valueOf(2))) : null;
                        Boolean valueOf = this.f24023d.m18048d() >= 2 ? Boolean.valueOf(this.f24023d.f24028g.contains(Integer.valueOf(1))) : null;
                        size = d;
                        bool = this.f24023d.m18048d() >= 1 ? Boolean.valueOf(this.f24023d.f24028g.contains(Integer.valueOf(0))) : null;
                        searchClkContext = searchClkContext2;
                        bool2 = valueOf;
                    }
                    Analytics.m17850a(searchClkContext, size, i, obj, j, charSequence, bool, bool2, bool3);
                    this.f24023d.f24025c.f24085J.m25214a(true);
                    this.f24023d.f24025c.f24085J.m25215b(true);
                    this.f24023d.f24025c.m25311a(textView.getText().toString(), textView.getText().toString(), false, imageView.getVisibility() == 0 ? SearchExecuteContext.RECENT : SearchExecuteContext.AUTOCOMPLETE, -1, true);
                    this.f24023d.f24025c.m25317c(textView.getText().toString());
                    this.f24023d.f24025c.f24087L = textView.getText().toString();
                    this.f24023d.f24025c.m25315b(false);
                }
            });
        }
    }

    private class RecentDataSource extends SearchDataSource<String> {
        final /* synthetic */ SearchFragment f24029a;

        public RecentDataSource(SearchFragment searchFragment, ArrayList<String> arrayList) {
            this.f24029a = searchFragment;
            super(null);
            this.b = arrayList;
            searchFragment.f24076A = this.b;
        }

        public Future<?> mo6243a(int i, int i2, FetchDataCallback<String> fetchDataCallback) {
            fetchDataCallback.mo6257a(this.b, -1);
            return null;
        }
    }

    private class SearchMixResultAdapter extends MagicAdapter {
        final /* synthetic */ SearchFragment f24053c;
        private boolean f24054d;
        private boolean f24055e;
        private int f24056f = -1;
        private long f24057g;
        private boolean f24058h = true;

        public SearchMixResultAdapter(SearchFragment searchFragment, MagicDataSource magicDataSource) {
            this.f24053c = searchFragment;
            super(magicDataSource);
        }

        public void m25241a(boolean z, boolean z2) {
            this.f24054d = z2;
            this.f24055e = z;
        }

        public void mo6252a(MagicDataSource magicDataSource, List<Object> list) {
            super.mo6252a(magicDataSource, (List) list);
            if (this != this.f24053c.f24086K) {
                Log.c(SearchFragment.f24075e, "onDataFetchFinished on stale adapter");
            } else if (magicDataSource instanceof SearchMixResultDataSource) {
                SearchMixResultDataSource searchMixResultDataSource = (SearchMixResultDataSource) magicDataSource;
                int i = searchMixResultDataSource.f24063a;
                if (!(i <= 1 || this.f24053c.ao == null || this.f24053c.ap == null)) {
                    this.f24053c.ap.setMaxSequenceNumber(1);
                    this.f24053c.ao.loadAds();
                    this.f24053c.m25319d(i);
                }
                searchMixResultDataSource.f24063a = -1;
            }
        }

        public View mo6857a(ViewGroup viewGroup) {
            if (m18026a().m17659i() == DataState.FIRST_PAGE_EMPTY) {
                return new View(this.f24053c.getActivity());
            }
            return super.mo6857a(viewGroup);
        }

        public View mo6460d(ViewGroup viewGroup) {
            if (!this.f24055e) {
                return null;
            }
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1947R.layout.songbook_search_empty_layout, null);
            TextView textView = (TextView) inflate.findViewById(C1947R.id.search_empty_textview);
            this.f24053c.f24078C = !this.f24054d;
            if (this.f24054d) {
                textView.setText(this.f24053c.getResources().getString(C1947R.string.search_mix_result_empty_text, new Object[]{this.f24053c.f24104h}));
            } else {
                textView.setText(C1947R.string.search_mix_result_error_text);
            }
            return inflate;
        }

        public View mo6464b(ViewGroup viewGroup) {
            return new View(this.f24053c.getActivity());
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
                throw new RuntimeException("Unknown search mix result item type: " + a.getClass().getName());
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
            if (this.f24056f != -1) {
                this.f24058h = false;
            }
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return LayoutInflater.from(this.f24053c.getActivity()).inflate(C1947R.layout.songbook_search_header_layout, this.f24053c.f24115w, false);
                case 4:
                    return ListingListItem.m24377a(this.f24053c.getActivity());
                case 5:
                    return UserFollowItem.m24442c(this.f24053c.getActivity());
                default:
                    return SearchMediaExpandableListViewItem.m24461a(this.f24053c.getActivity());
            }
        }

        public void mo6419a(View view, int i, int i2) {
            switch (i2) {
                case 0:
                case 1:
                case 2:
                case 3:
                    m25232a(view, i2);
                    break;
                case 4:
                    m25233b(view, i);
                    break;
                case 5:
                    m25234c(view, i);
                    break;
                default:
                    m25235d(view, i);
                    break;
            }
            if (this.f24058h && this.f24056f < i) {
                Animation animationSet = new AnimationSet(false);
                Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                alphaAnimation.setDuration(200);
                animationSet.addAnimation(alphaAnimation);
                if (this.f24057g == 0) {
                    this.f24057g = System.currentTimeMillis();
                } else {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis < this.f24057g + 50) {
                        animationSet.setStartOffset((this.f24057g + 50) - currentTimeMillis);
                        this.f24057g += 50;
                    }
                }
                this.f24056f = i;
                view.startAnimation(animationSet);
            }
        }

        private void m25232a(View view, final int i) {
            TextView textView = (TextView) view.findViewById(C1947R.id.search_header_title);
            view.findViewById(C1947R.id.search_header_button).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchMixResultAdapter f24033b;

                public void onClick(View view) {
                    this.f24033b.f24053c.m25321f(i);
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

        private void m25233b(View view, final int i) {
            final ListingListItem listingListItem = (ListingListItem) view;
            final SongbookEntry songbookEntry = (SongbookEntry) m18027a(i);
            listingListItem.m24380a(songbookEntry, false);
            listingListItem.setAlbumArtClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchMixResultAdapter f24037d;

                public void onClick(View view) {
                    boolean z = true;
                    if (!(songbookEntry instanceof ArrangementVersionLiteEntry)) {
                        this.f24037d.f24053c.mo6443a(songbookEntry);
                    } else if (!listingListItem.m23046t()) {
                        listingListItem.setAlbumArtClickedState(true);
                        if (this.f24037d.f24053c.q == null) {
                            this.f24037d.f24053c.q = new ConcurrentHashMap();
                        }
                        if (this.f24037d.f24053c.q.containsKey(songbookEntry.mo6289c())) {
                            this.f24037d.f24053c.m24136a(((Boolean) this.f24037d.f24053c.q.get(songbookEntry.mo6289c())).booleanValue(), (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                        } else {
                            int i = ((ArrangementVersionLiteEntry) songbookEntry).m18774a().removalCode;
                            if (i < 40 || i > 49) {
                                z = false;
                            }
                            this.f24037d.f24053c.q.put(songbookEntry.mo6289c(), Boolean.valueOf(z));
                            this.f24037d.f24053c.m24136a(z, (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                        }
                    } else {
                        return;
                    }
                    AnalyticsResultTriplet a = this.f24037d.f24053c.m24131a(i, 0, this.f24037d.f24053c.f24086K);
                    Analytics.m17854a(SearchTarget.f16259a, SearchResultClkContext.PREVIEW, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(songbookEntry), null, Integer.valueOf(a.m25208e()), null, SongbookEntryUtils.m26168c(songbookEntry), null, a.m25207d(), a.m25209f());
                    this.f24037d.f24053c.mo6928E();
                }
            });
            listingListItem.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchMixResultAdapter f24040c;

                public void onClick(View view) {
                    AnalyticsResultTriplet a = this.f24040c.f24053c.m24131a(i, 0, this.f24040c.f24053c.f24086K);
                    Analytics.m17854a(SearchTarget.f16259a, SearchResultClkContext.REGULAR, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(songbookEntry), null, Integer.valueOf(a.m25208e()), null, SongbookEntryUtils.m26168c(songbookEntry), null, a.m25207d(), a.m25209f());
                    this.f24040c.f24053c.mo6928E();
                    if (SongbookEntryUtils.m26166a(songbookEntry, SingServerValues.m21690k())) {
                        SingAnalytics.m26074a(songbookEntry, null, (Number) a.m24760c());
                        PreSingActivity.m24763a(this.f24040c.f24053c.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(songbookEntry).m24797a(SingServerValues.m21690k()).a();
                        return;
                    }
                    this.f24040c.f24053c.mo6487a(UpsellManager.m25791a(true, songbookEntry, SingServerValues.m21690k(), null, UpsellType.VIP_SONG));
                }
            });
        }

        private void m25234c(View view, final int i) {
            boolean z;
            String str;
            UserFollowItem userFollowItem = (UserFollowItem) view;
            AccountIcon accountIcon = (AccountIcon) m18027a(i);
            userFollowItem.m24446a(accountIcon, i, this.f24053c.getActivity(), false, new UserFollowItemListener(this) {
                final /* synthetic */ SearchMixResultAdapter f24042b;

                public void mo6457a(boolean z, boolean z2) {
                }

                public void mo6456a(ProfileFragment profileFragment) {
                    this.f24042b.f24053c.mo6929a((BaseFragment) profileFragment, profileFragment.mo6514z());
                }

                public void mo6455a(SearchResultClkContext searchResultClkContext) {
                    if (this.f24042b.f24053c.isAdded()) {
                        AnalyticsResultTriplet a = this.f24042b.f24053c.m24131a(i, 1, this.f24042b.f24053c.f24086K);
                        Analytics.m17854a(SearchTarget.USER, SearchResultClkContext.REGULAR, SearchResultClkValue.MIXED, null, null, Integer.valueOf(a.m25208e()), Long.valueOf(((AccountIcon) this.f24042b.m18027a(i)).accountId), null, null, a.m25207d(), a.m25209f());
                        this.f24042b.f24053c.mo6928E();
                    }
                }
            }, true);
            String str2 = "";
            boolean z2 = (accountIcon.firstName == null || accountIcon.firstName.isEmpty()) ? false : true;
            if (accountIcon.lastName == null || accountIcon.lastName.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            if (z2 && z) {
                str = accountIcon.firstName + " " + accountIcon.lastName;
            } else if (z2) {
                str = accountIcon.firstName;
            } else if (z) {
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

        private void m25235d(View view, final int i) {
            final SearchMediaExpandableListViewItem searchMediaExpandableListViewItem = (SearchMediaExpandableListViewItem) view;
            final SearchMediaExpandableListItem searchMediaExpandableListItem = (SearchMediaExpandableListItem) m18027a(i);
            boolean a = this.f24053c.m24138a(searchMediaExpandableListItem, i);
            if (a) {
                if (MiscUtils.m25895a(searchMediaExpandableListViewItem.getPerformance())) {
                    searchMediaExpandableListViewItem.m24468b(searchMediaExpandableListViewItem.m23046t());
                }
                this.f24053c.r = searchMediaExpandableListViewItem;
                this.f24053c.m24127D();
            }
            searchMediaExpandableListViewItem.m24465a(this.f24053c, searchMediaExpandableListItem, a, new MediaListItemFeedback(this) {
                final /* synthetic */ SearchMixResultAdapter f24046d;

                public void mo6853a(AccountIcon accountIcon) {
                    this.f24046d.f24053c.mo6487a(ProfileFragment.m21036a(accountIcon));
                    m25226a(ItemClickType.PROFILE);
                }

                public void mo6854a(PerformanceV2 performanceV2) {
                    m25226a(ItemClickType.JOIN);
                    PreSingActivity.m24763a(this.f24046d.f24053c.getActivity()).m24796a(StartupMode.OPENCALL).m24793a(searchMediaExpandableListItem.performanceIcon).a();
                    SingAnalytics.m26081a(performanceV2.video ? FilterType.f24970b : FilterType.NONE, SectionType.NETWORK);
                }

                public void mo6855b(PerformanceV2 performanceV2) {
                    ((MediaPlayingActivity) this.f24046d.f24053c.getActivity()).ad().m22308a(performanceV2, this.f24046d.f24053c.af, false);
                }

                public void m25226a(ItemClickType itemClickType) {
                    SearchResultClkContext searchResultClkContext;
                    SongbookEntry a = this.f24046d.f24053c.m24130a(searchMediaExpandableListItem.performanceIcon);
                    AnalyticsResultTriplet a2 = this.f24046d.f24053c.m24131a(i, searchMediaExpandableListItem.b() ? 2 : 3, this.f24046d.f24053c.f24086K);
                    SearchTarget searchTarget = searchMediaExpandableListItem.itemType == SearchMediaExpandableListItem$ItemType.INVITES ? SearchTarget.INVITE : SearchTarget.PERFORMANCE;
                    if (itemClickType == ItemClickType.PROFILE) {
                        searchResultClkContext = SearchResultClkContext.PROFILE;
                    } else {
                        searchResultClkContext = SearchResultClkContext.JOIN;
                    }
                    Analytics.m17854a(searchTarget, searchResultClkContext, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(a), searchMediaExpandableListViewItem.getPerformance().performanceKey, Integer.valueOf(a2.m25208e()), Long.valueOf(searchMediaExpandableListViewItem.getPerformance().accountIcon.accountId), SongbookEntryUtils.m26168c(a), searchMediaExpandableListViewItem.getPerformance().video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO, a2.m25207d(), a2.m25209f());
                    this.f24046d.f24053c.mo6928E();
                }
            }, true);
            searchMediaExpandableListViewItem.m24466a(new SearchMediaExpandableListViewItemClickCallback(this) {
                final /* synthetic */ SearchMixResultAdapter f24049c;

                public void mo6856a() {
                    this.f24049c.f24053c.m24135a(searchMediaExpandableListViewItem, i, this.f24049c);
                }
            }, new SearchMediaExpandableListViewItemClickCallback(this) {
                final /* synthetic */ SearchMixResultAdapter f24052c;

                public void mo6856a() {
                    this.f24052c.f24053c.m24135a(searchMediaExpandableListViewItem, i, this.f24052c);
                }
            });
        }
    }

    private class SearchMixResultDataSource extends SearchDataSource<Object> {
        public int f24063a;
        final /* synthetic */ SearchFragment f24064l;
        private boolean f24065m;

        public SearchMixResultDataSource(SearchFragment searchFragment, Context context, ArrayList<Object> arrayList, String str) {
            this.f24064l = searchFragment;
            String str2 = (str == null || str.isEmpty()) ? null : str + SearchMixResultDataSource.class.getName();
            super(str2);
            this.f24063a = -1;
            if (arrayList != null) {
                this.b = arrayList;
                searchFragment.f24118z = arrayList;
                searchFragment.m25255L();
            }
        }

        public int l_() {
            return 0;
        }

        public void mo6927g() {
            super.mo6927g();
        }

        public Future<?> mo6243a(int i, int i2, FetchDataCallback<Object> fetchDataCallback) {
            int i3 = -1;
            if (m17661k() == 0 && !this.b.isEmpty()) {
                this.f24064l.f24116x.setVisibility(8);
                List list = this.b;
                if (!this.b.contains(Integer.valueOf(3))) {
                    i3 = 0;
                }
                fetchDataCallback.mo6257a(list, i3);
            } else if (this.f24064l.f24104h == null || this.f24064l.f24104h.isEmpty() || this.b.contains(Integer.valueOf(3)) || this.f24065m) {
                this.f24064l.f24116x.setVisibility(8);
                fetchDataCallback.mo6257a(new ArrayList(), -1);
            } else {
                final String obj = this.f24064l.f24103g.getText().toString();
                final long currentTimeMillis = System.currentTimeMillis();
                final FetchDataCallback<Object> fetchDataCallback2 = fetchDataCallback;
                SearchManager.m18331a().m18341a(this.f24064l.f24104h, SearchResultType.RECORDING, 0, 25, SearchSortOption.POPULAR, new SearchResponseCallback(this) {
                    final /* synthetic */ SearchMixResultDataSource f24062d;

                    public void handleResponse(SASearchResponse sASearchResponse) {
                        int i = 3;
                        int i2 = 0;
                        if (this.f24062d.f24064l.isAdded()) {
                            this.f24062d.f24064l.f24116x.setVisibility(8);
                            if (sASearchResponse.a()) {
                                Integer valueOf;
                                this.f24062d.f24064l.f24098W = sASearchResponse;
                                List a = this.f24062d.f24064l.m24132a(sASearchResponse.mRecs);
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
                                this.f24062d.f24064l.f24097V = System.currentTimeMillis() - currentTimeMillis;
                                boolean z = (this.f24062d.f24064l.f24085J.m25211a() == null || this.f24062d.f24064l.f24085J.m25211a().isEmpty()) ? false : true;
                                int size = arrayList.isEmpty() ? 0 : arrayList.size() - 1;
                                SearchTarget searchTarget = SearchTarget.INSTANT_PERFORMANCE;
                                SearchExecuteContext e = this.f24062d.f24064l.aa;
                                String str = obj;
                                String str2 = z ? (String) this.f24062d.f24064l.f24085J.m25211a().get(0) : this.f24062d.f24064l.f24104h;
                                long f = this.f24062d.f24064l.f24097V;
                                if (this.f24062d.f24064l.f24077B) {
                                    if (this.f24062d.f24064l.f24076A != null) {
                                        i2 = this.f24062d.f24064l.f24076A.size();
                                    }
                                    valueOf = Integer.valueOf(i2);
                                } else {
                                    valueOf = null;
                                }
                                Analytics.m17853a(searchTarget, e, size, str, str2, f, valueOf);
                                this.f24062d.f24064l.f24118z.addAll(arrayList);
                                this.f24062d.f24064l.m25255L();
                                this.f24062d.f24065m = true;
                                Log.b(SearchFragment.f24075e, "setAd:recordings: " + arrayList.size() + " " + this.f24062d.f24064l.f24118z.size() + " " + this.f24062d.m17661k());
                                this.f24062d.f24063a = arrayList.size() + this.f24062d.m17661k();
                                fetchDataCallback2.mo6257a(arrayList, -1);
                                return;
                            }
                            this.f24062d.f24064l.m25319d(this.f24062d.f24064l.f24118z.size());
                            fetchDataCallback2.mo6256a();
                        }
                    }
                });
            }
            return null;
        }
    }

    private class TrendingAdapter extends ArrayAdapter<RecTrendingSearch> {
        final /* synthetic */ SearchFragment f24070a;
        private List<RecTrendingSearch> f24071b;
        private Context f24072c;
        private long f24073d;
        private int f24074e = 0;

        public /* synthetic */ Object getItem(int i) {
            return m25250a(i);
        }

        public TrendingAdapter(SearchFragment searchFragment, Context context, List<RecTrendingSearch> list) {
            this.f24070a = searchFragment;
            super(context, 0, list);
            this.f24072c = context;
            this.f24071b = list;
        }

        public RecTrendingSearch m25250a(int i) {
            return (RecTrendingSearch) this.f24071b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getCount() {
            return this.f24071b.size();
        }

        @NonNull
        public View getView(final int i, View view, @NonNull ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.f24072c).inflate(C1947R.layout.search_trending_item, viewGroup, false);
                view.setAlpha(i == 0 ? DefaultRetryPolicy.DEFAULT_BACKOFF_MULT : 0.0f);
            }
            final TextView textView = (TextView) view.findViewById(C1947R.id.trending_term);
            final RecTrendingSearch a = m25250a(i);
            if (a != null) {
                textView.setText(a.mTrendingTerm);
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TrendingAdapter f24069d;

                    public void onClick(View view) {
                        this.f24069d.f24070a.m25311a(textView.getText().toString(), textView.getText().toString(), false, SearchExecuteContext.TRENDING, -1, true);
                        this.f24069d.f24070a.m25317c(textView.getText().toString());
                        this.f24069d.f24070a.f24087L = textView.getText().toString();
                        this.f24069d.f24070a.m25315b(false);
                        Analytics.m17860a(a.mRecId, ItemClickType.SEARCH, i, RecSysContext.SEARCH, null);
                    }
                });
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
                if (this.f24074e < i) {
                    if (this.f24073d == 0) {
                        this.f24073d = System.currentTimeMillis();
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis < this.f24073d + 50) {
                            ofFloat.setStartDelay((this.f24073d + 50) - currentTimeMillis);
                            this.f24073d += 50;
                        }
                    }
                    this.f24074e = i;
                    ofFloat.setDuration(200);
                    ofFloat.start();
                }
            }
            return view;
        }
    }

    public void onResume() {
        super.onResume();
        mo6933p();
        MediaPlayingListItem.m23034a(this.f24115w);
        this.ab = false;
    }

    public void onStop() {
        super.onStop();
        MediaPlayerServiceController.m24641a().m24687r();
    }

    public void onDestroy() {
        super.onDestroy();
        m19866q();
        if (this.ao != null) {
            this.ao.destroy();
            this.ao = null;
            this.ap = null;
        }
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        if (i2 == 0) {
            return super.onCreateAnimator(i, z, i2);
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(getActivity(), i2);
        loadAnimator.addListener(new C48641(this));
        return loadAnimator;
    }

    @UiThread
    protected void m25305a() {
        this.f24111o.setVisibility(0);
        this.f24114v.setVisibility(8);
        List e = RecommendationManager.m18285a().m18308e();
        if (e != null) {
            this.ae = true;
            this.f24111o.setVisibility(8);
            m25270b(e);
            return;
        }
        RecommendationManager.m18285a().m18297a(new C48663(this));
    }

    private void m25270b(List<RecTrendingSearch> list) {
        int i;
        this.f24099X = new TrendingAdapter(this, getActivity(), list);
        this.f24106j.setAdapter(this.f24099X);
        m25252I();
        this.f24106j.setTag(Integer.valueOf(this.f24106j.getVisibility()));
        if (this.f24099X.getCount() == 0) {
            i = 50;
        } else {
            i = 3;
        }
        m25312a(AutocompleteUtils.m18946a(SingApplication.f(), i), "", true);
    }

    @UiThread
    protected void m25315b(boolean z) {
        if (this.f24099X != null && isAdded()) {
            this.f24106j.getViewTreeObserver().addOnGlobalLayoutListener(this.f24092Q);
            if (!z || this.f24099X.getCount() == 0) {
                this.f24109m.setVisibility(8);
                this.f24106j.setVisibility(8);
                if (this.f24100Y == null || (this.f24100Y.f24026e && ((RecentDataSource) this.f24100Y.m18026a()).b.size() == 0)) {
                    this.f24114v.setVisibility(0);
                    return;
                }
                return;
            }
            this.f24109m.setVisibility(0);
            this.f24106j.setVisibility(0);
            this.f24114v.setVisibility(8);
        } else if (this.f24111o.getVisibility() == 8 && (this.f24100Y == null || (this.f24100Y.f24026e && ((RecentDataSource) this.f24100Y.m18026a()).b.size() == 0))) {
            this.f24114v.setVisibility(0);
        } else {
            this.f24114v.setVisibility(8);
        }
    }

    private void m25252I() {
        if (this.f24099X != null) {
            int paddingTop = this.f24106j.getPaddingTop() + this.f24106j.getPaddingBottom();
            for (int i = 0; i < this.f24099X.getCount(); i++) {
                View view = this.f24099X.getView(0, null, null);
                view.setLayoutParams(new LayoutParams(-1, -2));
                view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                paddingTop += view.getMeasuredHeight();
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f24106j.getLayoutParams();
            layoutParams.height = ((layoutParams.topMargin + layoutParams.bottomMargin) + paddingTop) + (this.f24106j.getDividerHeight() * (this.f24106j.getCount() - 1));
            this.f24106j.setLayoutParams(layoutParams);
        }
    }

    private void m25253J() {
        if (this.ac == null) {
            this.ac = LayoutInflater.from(getActivity()).inflate(C1947R.layout.songbook_search_autocomplete_layout, null, false);
            this.f24108l = this.ac.findViewById(C1947R.id.recent_layout);
            this.f24107k = (MagicListView) this.ac.findViewById(C1947R.id.recent_listview);
            this.f24110n = this.ac.findViewById(C1947R.id.recent_header);
            this.f24112t = (TextView) this.ac.findViewById(C1947R.id.recent_header_button);
            this.f24109m = this.ac.findViewById(C1947R.id.trending_header);
            this.f24111o = this.ac.findViewById(C1947R.id.trending_progress_bar);
            this.f24106j = (ListView) this.ac.findViewById(C1947R.id.trending_listview);
            this.f24114v = this.ac.findViewById(C1947R.id.recent_empty_view);
            this.f24116x = this.ac.findViewById(C1947R.id.search_progress_view);
            this.f24117y = (TextView) this.ac.findViewById(C1947R.id.search_result_banner);
            this.f24112t.setOnClickListener(new C48674(this));
            m25305a();
        } else {
            this.ae = true;
        }
        this.f24115w.addHeaderView(this.ac);
        if (this.ad == null) {
            this.ad = LayoutInflater.from(getActivity()).inflate(C1947R.layout.songbook_search_instead_footer, null, false);
            this.f24113u = (TextView) this.ad.findViewById(C1947R.id.search_instead_banner);
            this.f24113u.setOnClickListener(new C48685(this));
        }
        this.f24115w.addFooterView(this.ad);
    }

    protected void m25317c(String str) {
        this.f24103g.removeTextChangedListener(this.ai);
        this.f24103g.setText(str);
        this.f24084I.setVisibility(str.isEmpty() ? 8 : 0);
        this.f24103g.addTextChangedListener(this.ai);
    }

    public static SearchFragment m25251A() {
        return new SearchFragment_();
    }

    public void mo6443a(SongbookEntry songbookEntry) {
        super.m19829a(songbookEntry, SearchTarget.f16259a);
    }

    public String mo6383s() {
        return f24075e;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f24089N = SingServerValues.m21665C();
        this.f24090O = SingServerValues.m21666D();
        this.f24091P = SingServerValues.m21667E();
    }

    @AfterViews
    protected void m25299B() {
        boolean z;
        m25253J();
        this.f24079D = this.f24118z == null;
        if (this.f24118z != null) {
            z = true;
        } else {
            z = false;
        }
        this.f24101Z = z;
        if (this.f24118z == null) {
            this.f24103g.requestFocus();
            MiscUtils.m25890a(getActivity(), this.f24103g);
        }
        if (this.f24093R != null) {
            this.f24105i = this.f24093R;
        } else if (this.f24118z == null) {
            this.f24086K = new SearchMixResultAdapter(this, new SearchMixResultDataSource(this, getActivity(), new ArrayList(), null));
            this.f24086K.m25241a(false, false);
            this.f24115w.setMagicAdapter(this.f24086K);
            this.f24113u.setVisibility(8);
        } else if (this.f24086K != null) {
            m25310a(this.f24086K, this.f24115w);
            if (this.ap != null) {
                this.ap.setMaxSequenceNumber(1);
            }
            if (this.ao != null) {
                this.ao.loadAds();
            }
            m25319d(this.f24086K.m18026a().m17661k());
        }
        this.f24115w.setOnTouchListener(new WeakListener.OnTouchListener(this.f24115w, new C48727(this)));
        if (!(this.f24105i == null || this.f24105i.isEmpty())) {
            this.ag = true;
            this.f24103g.setText(this.f24105i);
        }
        if (!this.f24077B) {
            this.f24076A = null;
        }
        if (!this.ae || (this.f24099X != null && this.f24099X.getCount() > 0)) {
            m25312a(!this.f24101Z ? AutocompleteUtils.m18946a(SingApplication.f(), 3) : this.f24076A, this.f24077B ? this.f24104h : null, this.f24079D);
        }
        this.f24103g.setOnEditorActionListener(new WeakListener.OnEditorActionListener(this.f24103g, new C48738(this)));
        this.f24103g.addTextChangedListener(this.ai);
    }

    private void m25254K() {
        if (this.f24099X != null && this.f24102f != null) {
            StringBuilder stringBuilder = new StringBuilder("");
            StringBuilder stringBuilder2 = new StringBuilder("");
            int i = 0;
            while (i < this.f24099X.getCount()) {
                if (this.f24106j.getChildAt(i) != null) {
                    if (this.f24106j.getChildAt(i).getGlobalVisibleRect(new Rect())) {
                        stringBuilder.append(((RecTrendingSearch) this.f24099X.f24071b.get(i)).mRecId);
                        stringBuilder.append(",");
                        stringBuilder2.append(i);
                        stringBuilder2.append(",");
                    }
                    i++;
                } else {
                    return;
                }
            }
            if (stringBuilder.length() != 0 && stringBuilder2.length() != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
                Analytics.m17870a(stringBuilder.toString(), stringBuilder2.toString(), RecommendationType.TRENDING, RecSysContext.SEARCH, null);
            }
        }
    }

    protected void mo6420v() {
        boolean z = false;
        if (this.f24101Z) {
            int i;
            Integer valueOf;
            this.f24088M = this.f24105i;
            SearchTarget searchTarget = SearchTarget.INSTANT_MIXED;
            SearchExecuteContext searchExecuteContext = SearchExecuteContext.BACK;
            int i2 = this.f24080E;
            String str = this.f24105i;
            String str2 = this.f24104h;
            if (this.f24077B) {
                if (this.f24076A == null) {
                    i = 0;
                } else {
                    i = this.f24076A.size();
                }
                valueOf = Integer.valueOf(i);
            } else {
                valueOf = null;
            }
            Analytics.m17853a(searchTarget, searchExecuteContext, i2, str, str2, 0, valueOf);
            if (this.f24081F != 0) {
                searchTarget = SearchTarget.INSTANT_PERFORMANCE;
                searchExecuteContext = SearchExecuteContext.BACK;
                i2 = this.f24081F;
                str = this.f24105i;
                str2 = this.f24104h;
                if (this.f24077B) {
                    if (this.f24076A == null) {
                        i = 0;
                    } else {
                        i = this.f24076A.size();
                    }
                    valueOf = Integer.valueOf(i);
                } else {
                    valueOf = null;
                }
                Analytics.m17853a(searchTarget, searchExecuteContext, i2, str, str2, 0, valueOf);
            }
            String str3 = this.f24104h;
            String str4 = this.f24105i;
            if (!this.f24077B) {
                z = true;
            }
            m25267a(str3, str4, z);
        }
    }

    private void m25255L() {
        int i = 0;
        Iterator it = this.f24118z.iterator();
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
        this.f24081F = i;
        this.f24080E = i2;
    }

    private void m25256M() {
        this.f24115w.setMagicAdapter(null);
        this.f24117y.setVisibility(8);
        this.f24113u.setVisibility(8);
        if (this.ap != null) {
            this.ap.clearAds();
        }
    }

    private void m25257N() {
        this.aj.removeCallbacks(this.an);
        this.aj.removeCallbacks(this.al);
    }

    private void m25258O() {
        if (this.am != null) {
            this.ak.removeCallbacks(this.am);
        }
    }

    protected void m25312a(ArrayList<String> arrayList, String str, boolean z) {
        String str2;
        this.f24079D = z;
        if (z) {
            this.f24076A = null;
        }
        Set hashSet = new HashSet();
        if (!TextUtils.isEmpty(str)) {
            HashSet hashSet2 = new HashSet();
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                hashSet2.add((String) it.next());
            }
            List a = AutocompleteUtils.m18947a(SingApplication.f(), str, hashSet2);
            List a2 = AutocompleteUtils.m18949a(str, hashSet2);
            if (arrayList == null || arrayList.isEmpty()) {
                this.f24076A = null;
            }
            if (arrayList.size() != 3) {
                while (arrayList.size() < 3) {
                    if (a == null || a.isEmpty()) {
                        if (a2 == null || a2.isEmpty()) {
                            break;
                        }
                        str2 = (String) a2.remove(0);
                        if (!arrayList.contains(str2)) {
                            arrayList.add(str2);
                            hashSet.add(Integer.valueOf(arrayList.size() - 1));
                        }
                    } else {
                        str2 = (String) a.remove(0);
                        if (!arrayList.contains(str2)) {
                            arrayList.add(str2);
                            hashSet.add(Integer.valueOf(arrayList.size() - 1));
                        }
                    }
                }
            } else if (a != null) {
                arrayList.remove(2);
                arrayList.add(a.get(0));
                hashSet.add(Integer.valueOf(2));
            } else if (a2 != null) {
                arrayList.remove(2);
                arrayList.add(a2.get(0));
                hashSet.add(Integer.valueOf(2));
            }
        }
        MagicAdapter wrappedAdapter;
        if (TextUtils.isEmpty(str) && z && (arrayList == null || arrayList.isEmpty())) {
            this.f24108l.setVisibility(0);
            this.f24110n.setVisibility(8);
            wrappedAdapter = this.f24107k.getWrappedAdapter();
            if (wrappedAdapter != null) {
                wrappedAdapter.m18042b((DataSourceObserver) this);
            }
            this.f24107k.setMagicAdapter(null);
            this.f24076A = null;
            this.f24100Y = null;
            this.f24107k.setVisibility(8);
            m25315b(true);
        } else if (arrayList == null || arrayList.isEmpty()) {
            this.f24108l.setVisibility(8);
            m25315b(false);
        } else {
            this.f24114v.setVisibility(8);
            this.f24110n.setVisibility(z ? 0 : 8);
            this.f24112t.setText(C1947R.string.recent_see_all);
            this.f24108l.setVisibility(0);
            wrappedAdapter = this.f24107k.getWrappedAdapter();
            if (wrappedAdapter != null) {
                wrappedAdapter.m18042b((DataSourceObserver) this);
            }
            MagicDataSource recentDataSource = new RecentDataSource(this, arrayList);
            if (str == null) {
                str2 = "";
            } else {
                str2 = str;
            }
            this.f24100Y = new RecentAdapter(this, recentDataSource, z, str2);
            this.f24100Y.m25221a(hashSet);
            this.f24100Y.mo6858a((DataSourceObserver) this);
            this.f24107k.setVisibility(0);
            this.f24107k.setMagicAdapter(this.f24100Y);
            if (TextUtils.isEmpty(str)) {
                m25315b(true);
            } else {
                m25315b(false);
            }
        }
    }

    public void mo6254c(MagicDataSource magicDataSource) {
        if (getActivity() != null) {
            LayoutUtils.m25856a(this.f24107k);
        }
    }

    public void mo6255d(MagicDataSource magicDataSource) {
    }

    public void mo6252a(MagicDataSource magicDataSource, List<Object> list) {
    }

    public void mo6251a(MagicDataSource magicDataSource) {
    }

    public void mo6253b(MagicDataSource magicDataSource) {
    }

    protected void mo6928E() {
        m25262a(SearchBarExitContext.CLICK, this.f24088M, this.f24103g.getText().toString());
    }

    private void m25262a(SearchBarExitContext searchBarExitContext, String str, String str2) {
        if (((InputMethodManager) getActivity().getSystemService("input_method")).isAcceptingText() || searchBarExitContext == SearchBarExitContext.CLEAR) {
            Analytics.m17847a(searchBarExitContext, str, str2);
            this.f24088M = str2;
        }
        if (searchBarExitContext != SearchBarExitContext.CLEAR) {
            this.f24103g.clearFocus();
            MiscUtils.m25891a(getActivity(), true);
            if (getActivity() instanceof MasterActivity) {
                ((MasterActivity) getActivity()).P();
            }
        }
    }

    private void m25267a(String str, String str2, boolean z) {
        if (z || TextUtils.isEmpty(str) || str2 == null || str2.equals(str)) {
            this.f24117y.setVisibility(8);
            this.f24113u.setVisibility(8);
            return;
        }
        this.f24117y.setVisibility(0);
        this.f24117y.setText(Html.fromHtml(getResources().getString(C1947R.string.search_result_banner_text, new Object[]{str})));
        this.f24113u.setVisibility(0);
        this.f24113u.setText(Html.fromHtml(getResources().getString(C1947R.string.search_instead_banner_text, new Object[]{str2})));
    }

    protected void m25311a(String str, String str2, boolean z, SearchExecuteContext searchExecuteContext, int i, boolean z2) {
        if (!str.equals(this.f24104h) || this.f24116x.getVisibility() != 0) {
            if (this.ap != null) {
                Log.b(f24075e, "clearAds");
                this.ap.clearAds();
            }
            this.f24104h = str;
            if (str.isEmpty()) {
                Log.b(f24075e, "doSearch - queryString is empty; aborting search");
                this.f24077B = true;
                m25256M();
                return;
            }
            m25258O();
            Log.b(f24075e, "doSearch - queryString is: " + str);
            this.r = null;
            this.s = -1;
            this.f24077B = z;
            if (!z) {
                this.f24108l.setVisibility(8);
                this.f24117y.setVisibility(8);
                this.f24113u.setVisibility(8);
                this.f24076A = null;
                MiscUtils.m25891a(getActivity(), false);
                AutocompleteUtils.m18951a(SingApplication.f(), str);
            }
            if (z2) {
                this.f24117y.setVisibility(8);
                this.f24113u.setVisibility(8);
            }
            this.aa = searchExecuteContext;
            final long currentTimeMillis = System.currentTimeMillis();
            if (searchExecuteContext == SearchExecuteContext.AUTOCOMPLETE || searchExecuteContext == SearchExecuteContext.RECENT || searchExecuteContext == SearchExecuteContext.TRENDING) {
                this.f24105i = str;
            } else {
                this.f24105i = this.f24103g.getText().toString();
            }
            if (this.f24094S != null) {
                synchronized (this.f24094S) {
                    this.f24094S.set(true);
                }
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.f24094S = atomicBoolean;
            Iterator it;
            Object obj;
            Object next;
            int i2;
            if (z) {
                m25256M();
                final MagicDataSource searchMixResultDataSource = new SearchMixResultDataSource(this, getActivity(), new ArrayList(), "INSTANT_" + this.f24104h);
                if (searchMixResultDataSource.mo6265e()) {
                    this.f24116x.setVisibility(8);
                    this.f24101Z = true;
                    this.f24086K = new SearchMixResultAdapter(this, searchMixResultDataSource);
                    this.f24086K.m25241a(true, true);
                    m25310a(this.f24086K, this.f24115w);
                    Log.b(f24075e, "setAd:instant search cached");
                    int i3 = 0;
                    int i4 = 0;
                    it = searchMixResultDataSource.b.iterator();
                    obj = null;
                    while (it.hasNext()) {
                        next = it.next();
                        if (next instanceof Integer) {
                            if (((Integer) next).intValue() == 3) {
                                i2 = 1;
                            } else {
                                next = obj;
                            }
                        } else if (obj != null) {
                            i4++;
                            next = obj;
                        } else {
                            i3++;
                            next = obj;
                        }
                        obj = next;
                    }
                    if (obj != null) {
                        m25319d(searchMixResultDataSource.b.size());
                    }
                    this.f24081F = i4;
                    this.f24080E = i3;
                    this.f24097V = 0;
                    final String str3 = str;
                    final SearchExecuteContext searchExecuteContext2 = searchExecuteContext;
                    final String str4 = str2;
                    final boolean z3 = z2;
                    this.f24085J.m25212a(new Runnable(this) {
                        final /* synthetic */ SearchFragment f23978g;

                        public void run() {
                            if (this.f23978g.f24085J.m25211a() != null) {
                                SearchFragment searchFragment = this.f23978g;
                                String str = (this.f23978g.f24085J.m25211a().isEmpty() || this.f23978g.f24086K.m18048d() == 0) ? str3 : (String) this.f23978g.f24085J.m25211a().get(0);
                                searchFragment.f24104h = str;
                                Analytics.m17853a(SearchTarget.INSTANT_MIXED, searchExecuteContext2, i3, str4, this.f23978g.f24104h, 0, Integer.valueOf(this.f23978g.f24085J.m25211a().size()));
                                if (i4 > 0) {
                                    Analytics.m17853a(SearchTarget.INSTANT_PERFORMANCE, searchExecuteContext2, i4, str4, this.f23978g.f24104h, 0, Integer.valueOf(this.f23978g.f24085J.m25211a().size()));
                                }
                                this.f23978g.m25267a(this.f23978g.f24104h, str4, z3);
                            }
                        }
                    });
                    this.f24085J.m25215b(true);
                    return;
                }
                this.f24116x.setVisibility(0);
                final String str5 = str2;
                final boolean z4 = z2;
                final SearchExecuteContext searchExecuteContext3 = searchExecuteContext;
                final String str6 = str;
                SearchManager.m18331a().m18340a(str, new SearchInstantResponseCallback(this) {
                    final /* synthetic */ SearchFragment f23992h;

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void handleResponse(com.smule.android.network.managers.SearchManager.SASearchInstantResponse r11) {
                        /*
                        r10 = this;
                        r0 = r10.f23992h;
                        r0 = r0.isAdded();
                        if (r0 != 0) goto L_0x0009;
                    L_0x0008:
                        return;
                    L_0x0009:
                        r6 = r6;
                        monitor-enter(r6);
                        r0 = r6;	 Catch:{ all -> 0x0016 }
                        r0 = r0.get();	 Catch:{ all -> 0x0016 }
                        if (r0 == 0) goto L_0x0019;
                    L_0x0014:
                        monitor-exit(r6);	 Catch:{ all -> 0x0016 }
                        goto L_0x0008;
                    L_0x0016:
                        r0 = move-exception;
                        monitor-exit(r6);	 Catch:{ all -> 0x0016 }
                        throw r0;
                    L_0x0019:
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24116x;	 Catch:{ all -> 0x0016 }
                        r1 = 8;
                        r0.setVisibility(r1);	 Catch:{ all -> 0x0016 }
                        r0 = r11.a();	 Catch:{ all -> 0x0016 }
                        if (r0 == 0) goto L_0x014d;
                    L_0x0028:
                        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0016 }
                        r1.<init>();	 Catch:{ all -> 0x0016 }
                        r0 = r11.mResultTypes;	 Catch:{ all -> 0x0016 }
                        r2 = r0.iterator();	 Catch:{ all -> 0x0016 }
                    L_0x0033:
                        r0 = r2.hasNext();	 Catch:{ all -> 0x0016 }
                        if (r0 == 0) goto L_0x00a7;
                    L_0x0039:
                        r0 = r2.next();	 Catch:{ all -> 0x0016 }
                        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0016 }
                        r3 = "SONG";
                        r3 = r0.equals(r3);	 Catch:{ all -> 0x0016 }
                        if (r3 == 0) goto L_0x0067;
                    L_0x0047:
                        r0 = r11.mSongs;	 Catch:{ all -> 0x0016 }
                        r0 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                        if (r0 != 0) goto L_0x0033;
                    L_0x004f:
                        r0 = r11.mSongs;	 Catch:{ all -> 0x0016 }
                        r0 = com.smule.singandroid.utils.SongbookEntryUtils.m26165a(r0);	 Catch:{ all -> 0x0016 }
                        r3 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                        if (r3 != 0) goto L_0x0063;
                    L_0x005b:
                        r3 = 0;
                        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x0016 }
                        r1.add(r3);	 Catch:{ all -> 0x0016 }
                    L_0x0063:
                        r1.addAll(r0);	 Catch:{ all -> 0x0016 }
                        goto L_0x0033;
                    L_0x0067:
                        r3 = "ACCOUNT";
                        r3 = r0.equals(r3);	 Catch:{ all -> 0x0016 }
                        if (r3 == 0) goto L_0x0085;
                    L_0x006f:
                        r0 = r11.mAccts;	 Catch:{ all -> 0x0016 }
                        r0 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                        if (r0 != 0) goto L_0x0033;
                    L_0x0077:
                        r0 = 1;
                        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0016 }
                        r1.add(r0);	 Catch:{ all -> 0x0016 }
                        r0 = r11.mAccts;	 Catch:{ all -> 0x0016 }
                        r1.addAll(r0);	 Catch:{ all -> 0x0016 }
                        goto L_0x0033;
                    L_0x0085:
                        r3 = "ACTIVESEED";
                        r0 = r0.equals(r3);	 Catch:{ all -> 0x0016 }
                        if (r0 == 0) goto L_0x0033;
                    L_0x008d:
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r3 = r11.mSeeds;	 Catch:{ all -> 0x0016 }
                        r0 = r0.m24132a(r3);	 Catch:{ all -> 0x0016 }
                        r3 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                        if (r3 != 0) goto L_0x0033;
                    L_0x009b:
                        r3 = 2;
                        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x0016 }
                        r1.add(r3);	 Catch:{ all -> 0x0016 }
                        r1.addAll(r0);	 Catch:{ all -> 0x0016 }
                        goto L_0x0033;
                    L_0x00a7:
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2 = 1;
                        r0.f24101Z = r2;	 Catch:{ all -> 0x0016 }
                        r0 = r7;	 Catch:{ all -> 0x0016 }
                        r0.m24120a(r1);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2 = r11.mSearchTerm;	 Catch:{ all -> 0x0016 }
                        r0.f24104h = r2;	 Catch:{ all -> 0x0016 }
                        r0 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultDataSource;	 Catch:{ all -> 0x0016 }
                        r2 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r3 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r3 = r3.getActivity();	 Catch:{ all -> 0x0016 }
                        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0016 }
                        r4.<init>();	 Catch:{ all -> 0x0016 }
                        r5 = "GLOBAL_";
                        r4 = r4.append(r5);	 Catch:{ all -> 0x0016 }
                        r5 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r5 = r5.f24104h;	 Catch:{ all -> 0x0016 }
                        r4 = r4.append(r5);	 Catch:{ all -> 0x0016 }
                        r4 = r4.toString();	 Catch:{ all -> 0x0016 }
                        r0.<init>(r2, r3, r1, r4);	 Catch:{ all -> 0x0016 }
                        r1 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultAdapter;	 Catch:{ all -> 0x0016 }
                        r3 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2.<init>(r3, r0);	 Catch:{ all -> 0x0016 }
                        r1.f24086K = r2;	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24086K;	 Catch:{ all -> 0x0016 }
                        r1 = 1;
                        r2 = 1;
                        r0.m25241a(r1, r2);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = r1.f24086K;	 Catch:{ all -> 0x0016 }
                        r2 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2 = r2.f24115w;	 Catch:{ all -> 0x0016 }
                        r0.m25310a(r1, r2);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = r1.f24104h;	 Catch:{ all -> 0x0016 }
                        r2 = r8;	 Catch:{ all -> 0x0016 }
                        r3 = r9;	 Catch:{ all -> 0x0016 }
                        r0.m25267a(r1, r2, r3);	 Catch:{ all -> 0x0016 }
                        r0 = r11.mAccts;	 Catch:{ all -> 0x0016 }
                        r0 = r0.size();	 Catch:{ all -> 0x0016 }
                        r1 = r11.mSongs;	 Catch:{ all -> 0x0016 }
                        r1 = r1.size();	 Catch:{ all -> 0x0016 }
                        r0 = r0 + r1;
                        r1 = r11.mSeeds;	 Catch:{ all -> 0x0016 }
                        r1 = r1.size();	 Catch:{ all -> 0x0016 }
                        r2 = r0 + r1;
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0016 }
                        r8 = r10;	 Catch:{ all -> 0x0016 }
                        r4 = r4 - r8;
                        r0.f24097V = r4;	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r3 = r0.f24104h;	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r4 = r0.f24097V;	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r7 = r0.f24085J;	 Catch:{ all -> 0x0016 }
                        r0 = new com.smule.singandroid.songbook_search.SearchFragment$13$1;	 Catch:{ all -> 0x0016 }
                        r1 = r10;
                        r0.<init>(r1, r2, r3, r4);	 Catch:{ all -> 0x0016 }
                        r7.m25212a(r0);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24085J;	 Catch:{ all -> 0x0016 }
                        r1 = 1;
                        r0.m25215b(r1);	 Catch:{ all -> 0x0016 }
                    L_0x014a:
                        monitor-exit(r6);	 Catch:{ all -> 0x0016 }
                        goto L_0x0008;
                    L_0x014d:
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = 1;
                        r0.f24101Z = r1;	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultAdapter;	 Catch:{ all -> 0x0016 }
                        r2 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r3 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultDataSource;	 Catch:{ all -> 0x0016 }
                        r4 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r5 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r5 = r5.getActivity();	 Catch:{ all -> 0x0016 }
                        r7 = new java.util.ArrayList;	 Catch:{ all -> 0x0016 }
                        r7.<init>();	 Catch:{ all -> 0x0016 }
                        r8 = 0;
                        r3.<init>(r4, r5, r7, r8);	 Catch:{ all -> 0x0016 }
                        r1.<init>(r2, r3);	 Catch:{ all -> 0x0016 }
                        r0.f24086K = r1;	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24086K;	 Catch:{ all -> 0x0016 }
                        r1 = 1;
                        r2 = 0;
                        r0.m25241a(r1, r2);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r1 = r1.f24086K;	 Catch:{ all -> 0x0016 }
                        r2 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2 = r2.f24115w;	 Catch:{ all -> 0x0016 }
                        r0.m25310a(r1, r2);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24117y;	 Catch:{ all -> 0x0016 }
                        r1 = 8;
                        r0.setVisibility(r1);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24113u;	 Catch:{ all -> 0x0016 }
                        r1 = 8;
                        r0.setVisibility(r1);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0016 }
                        r4 = r10;	 Catch:{ all -> 0x0016 }
                        r2 = r2 - r4;
                        r0.f24097V = r2;	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24097V;	 Catch:{ all -> 0x0016 }
                        r2 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r2 = r2.f24085J;	 Catch:{ all -> 0x0016 }
                        r3 = new com.smule.singandroid.songbook_search.SearchFragment$13$2;	 Catch:{ all -> 0x0016 }
                        r3.<init>(r10, r0);	 Catch:{ all -> 0x0016 }
                        r2.m25212a(r3);	 Catch:{ all -> 0x0016 }
                        r0 = r10.f23992h;	 Catch:{ all -> 0x0016 }
                        r0 = r0.f24085J;	 Catch:{ all -> 0x0016 }
                        r1 = 1;
                        r0.m25215b(r1);	 Catch:{ all -> 0x0016 }
                        goto L_0x014a;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.songbook_search.SearchFragment.13.handleResponse(com.smule.android.network.managers.SearchManager$SASearchInstantResponse):void");
                    }
                });
                return;
            }
            SearchBarExitContext searchBarExitContext = (searchExecuteContext == SearchExecuteContext.AUTOCOMPLETE || searchExecuteContext == SearchExecuteContext.RECENT) ? SearchBarExitContext.CLICK : SearchBarExitContext.GO;
            m25262a(searchBarExitContext, this.f24088M, this.f24103g.getText().toString());
            m25256M();
            MagicDataSource searchMixResultDataSource2 = new SearchMixResultDataSource(this, getActivity(), new ArrayList(), "GLOBAL_" + this.f24104h);
            if (searchMixResultDataSource2.mo6265e()) {
                this.f24116x.setVisibility(8);
                this.f24101Z = true;
                this.f24086K = new SearchMixResultAdapter(this, searchMixResultDataSource2);
                this.f24086K.m25241a(true, true);
                m25310a(this.f24086K, this.f24115w);
                Log.b(f24075e, "setAd:global search cached");
                this.f24085J.m25213a(null);
                m25267a(str, str2, z2);
                int i5 = 0;
                it = searchMixResultDataSource2.b.iterator();
                int i6 = 0;
                obj = null;
                while (it.hasNext()) {
                    int i7;
                    next = it.next();
                    if (next instanceof Integer) {
                        if (((Integer) next).intValue() == 3) {
                            i2 = 1;
                            i7 = i6;
                        } else {
                            next = obj;
                            i7 = i6;
                        }
                    } else if (obj != null) {
                        next = obj;
                        i7 = i6 + 1;
                    } else {
                        i5++;
                        next = obj;
                        i7 = i6;
                    }
                    i6 = i7;
                    obj = next;
                }
                if (obj != null) {
                    m25319d(searchMixResultDataSource2.b.size());
                }
                this.f24081F = i6;
                this.f24080E = i5;
                this.f24085J.m25215b(true);
                this.f24097V = 0;
                Analytics.m17853a(SearchTarget.INSTANT_MIXED, searchExecuteContext, i5, str2, str, 0, null);
                if (i6 > 0) {
                    Analytics.m17853a(SearchTarget.INSTANT_PERFORMANCE, searchExecuteContext, i6, str2, str, 0, null);
                }
                this.f24085J.m25214a(false);
                return;
            }
            this.f24116x.setVisibility(0);
            final AtomicBoolean atomicBoolean2 = atomicBoolean;
            final String str7 = str;
            final long j = currentTimeMillis;
            final SearchExecuteContext searchExecuteContext4 = searchExecuteContext;
            final String str8 = str2;
            SearchManager.m18331a().m18339a(str, new SearchGlobalResponseCallback(this) {
                final /* synthetic */ SearchFragment f23998f;

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void handleResponse(com.smule.android.network.managers.SearchManager.SASearchGlobalResponse r11) {
                    /*
                    r10 = this;
                    r0 = r10.f23998f;
                    r0 = r0.isAdded();
                    if (r0 != 0) goto L_0x0009;
                L_0x0008:
                    return;
                L_0x0009:
                    r9 = r14;
                    monitor-enter(r9);
                    r0 = r14;	 Catch:{ all -> 0x0016 }
                    r0 = r0.get();	 Catch:{ all -> 0x0016 }
                    if (r0 == 0) goto L_0x0019;
                L_0x0014:
                    monitor-exit(r9);	 Catch:{ all -> 0x0016 }
                    goto L_0x0008;
                L_0x0016:
                    r0 = move-exception;
                    monitor-exit(r9);	 Catch:{ all -> 0x0016 }
                    throw r0;
                L_0x0019:
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r1 = 1;
                    r0.f24101Z = r1;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r0 = r0.f24116x;	 Catch:{ all -> 0x0016 }
                    r1 = 8;
                    r0.setVisibility(r1);	 Catch:{ all -> 0x0016 }
                    r0 = r11.a();	 Catch:{ all -> 0x0016 }
                    if (r0 == 0) goto L_0x013c;
                L_0x002e:
                    r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0016 }
                    r1.<init>();	 Catch:{ all -> 0x0016 }
                    r0 = r11.mResultTypes;	 Catch:{ all -> 0x0016 }
                    r2 = r0.iterator();	 Catch:{ all -> 0x0016 }
                L_0x0039:
                    r0 = r2.hasNext();	 Catch:{ all -> 0x0016 }
                    if (r0 == 0) goto L_0x00ad;
                L_0x003f:
                    r0 = r2.next();	 Catch:{ all -> 0x0016 }
                    r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0016 }
                    r3 = "SONG";
                    r3 = r0.equals(r3);	 Catch:{ all -> 0x0016 }
                    if (r3 == 0) goto L_0x006d;
                L_0x004d:
                    r0 = r11.mSongs;	 Catch:{ all -> 0x0016 }
                    r0 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                    if (r0 != 0) goto L_0x0039;
                L_0x0055:
                    r0 = r11.mSongs;	 Catch:{ all -> 0x0016 }
                    r0 = com.smule.singandroid.utils.SongbookEntryUtils.m26165a(r0);	 Catch:{ all -> 0x0016 }
                    r3 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                    if (r3 != 0) goto L_0x0069;
                L_0x0061:
                    r3 = 0;
                    r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x0016 }
                    r1.add(r3);	 Catch:{ all -> 0x0016 }
                L_0x0069:
                    r1.addAll(r0);	 Catch:{ all -> 0x0016 }
                    goto L_0x0039;
                L_0x006d:
                    r3 = "ACCOUNT";
                    r3 = r0.equals(r3);	 Catch:{ all -> 0x0016 }
                    if (r3 == 0) goto L_0x008b;
                L_0x0075:
                    r0 = r11.mAccts;	 Catch:{ all -> 0x0016 }
                    r0 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                    if (r0 != 0) goto L_0x0039;
                L_0x007d:
                    r0 = 1;
                    r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0016 }
                    r1.add(r0);	 Catch:{ all -> 0x0016 }
                    r0 = r11.mAccts;	 Catch:{ all -> 0x0016 }
                    r1.addAll(r0);	 Catch:{ all -> 0x0016 }
                    goto L_0x0039;
                L_0x008b:
                    r3 = "ACTIVESEED";
                    r0 = r0.equals(r3);	 Catch:{ all -> 0x0016 }
                    if (r0 == 0) goto L_0x0039;
                L_0x0093:
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r3 = r11.mSeeds;	 Catch:{ all -> 0x0016 }
                    r0 = r0.m24132a(r3);	 Catch:{ all -> 0x0016 }
                    r3 = r0.isEmpty();	 Catch:{ all -> 0x0016 }
                    if (r3 != 0) goto L_0x0039;
                L_0x00a1:
                    r3 = 2;
                    r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x0016 }
                    r1.add(r3);	 Catch:{ all -> 0x0016 }
                    r1.addAll(r0);	 Catch:{ all -> 0x0016 }
                    goto L_0x0039;
                L_0x00ad:
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r2 = r15;	 Catch:{ all -> 0x0016 }
                    r0.f24104h = r2;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r0 = r0.f24085J;	 Catch:{ all -> 0x0016 }
                    r2 = 0;
                    r0.m25213a(r2);	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r2 = 1;
                    r0.f24101Z = r2;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r2 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultAdapter;	 Catch:{ all -> 0x0016 }
                    r3 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r4 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultDataSource;	 Catch:{ all -> 0x0016 }
                    r5 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r6 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r6 = r6.getActivity();	 Catch:{ all -> 0x0016 }
                    r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0016 }
                    r7.<init>();	 Catch:{ all -> 0x0016 }
                    r8 = "GLOBAL_";
                    r7 = r7.append(r8);	 Catch:{ all -> 0x0016 }
                    r8 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r8 = r8.f24104h;	 Catch:{ all -> 0x0016 }
                    r7 = r7.append(r8);	 Catch:{ all -> 0x0016 }
                    r7 = r7.toString();	 Catch:{ all -> 0x0016 }
                    r4.<init>(r5, r6, r1, r7);	 Catch:{ all -> 0x0016 }
                    r2.<init>(r3, r4);	 Catch:{ all -> 0x0016 }
                    r0.f24086K = r2;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r0 = r0.f24086K;	 Catch:{ all -> 0x0016 }
                    r1 = 1;
                    r2 = 1;
                    r0.m25241a(r1, r2);	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r1 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r1 = r1.f24086K;	 Catch:{ all -> 0x0016 }
                    r2 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r2 = r2.f24115w;	 Catch:{ all -> 0x0016 }
                    r0.m25310a(r1, r2);	 Catch:{ all -> 0x0016 }
                    r0 = r11.mAccts;	 Catch:{ all -> 0x0016 }
                    r0 = r0.size();	 Catch:{ all -> 0x0016 }
                    r1 = r11.mSongs;	 Catch:{ all -> 0x0016 }
                    r1 = r1.size();	 Catch:{ all -> 0x0016 }
                    r0 = r0 + r1;
                    r1 = r11.mSeeds;	 Catch:{ all -> 0x0016 }
                    r1 = r1.size();	 Catch:{ all -> 0x0016 }
                    r3 = r0 + r1;
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0016 }
                    r6 = r16;	 Catch:{ all -> 0x0016 }
                    r4 = r4 - r6;
                    r0.f24097V = r4;	 Catch:{ all -> 0x0016 }
                    r1 = com.smule.android.logging.Analytics.SearchTarget.INSTANT_MIXED;	 Catch:{ all -> 0x0016 }
                    r2 = r18;	 Catch:{ all -> 0x0016 }
                    r4 = r19;	 Catch:{ all -> 0x0016 }
                    r5 = r15;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r6 = r0.f24097V;	 Catch:{ all -> 0x0016 }
                    r8 = 0;
                    com.smule.android.logging.Analytics.m17853a(r1, r2, r3, r4, r5, r6, r8);	 Catch:{ all -> 0x0016 }
                L_0x0139:
                    monitor-exit(r9);	 Catch:{ all -> 0x0016 }
                    goto L_0x0008;
                L_0x013c:
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r1 = 1;
                    r0.f24101Z = r1;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r1 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultAdapter;	 Catch:{ all -> 0x0016 }
                    r2 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r3 = new com.smule.singandroid.songbook_search.SearchFragment$SearchMixResultDataSource;	 Catch:{ all -> 0x0016 }
                    r4 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r5 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r5 = r5.getActivity();	 Catch:{ all -> 0x0016 }
                    r6 = new java.util.ArrayList;	 Catch:{ all -> 0x0016 }
                    r6.<init>();	 Catch:{ all -> 0x0016 }
                    r7 = 0;
                    r3.<init>(r4, r5, r6, r7);	 Catch:{ all -> 0x0016 }
                    r1.<init>(r2, r3);	 Catch:{ all -> 0x0016 }
                    r0.f24086K = r1;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r0 = r0.f24086K;	 Catch:{ all -> 0x0016 }
                    r1 = 1;
                    r2 = 0;
                    r0.m25241a(r1, r2);	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r1 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r1 = r1.f24086K;	 Catch:{ all -> 0x0016 }
                    r2 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r2 = r2.f24115w;	 Catch:{ all -> 0x0016 }
                    r0.m25310a(r1, r2);	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r0 = r0.f24117y;	 Catch:{ all -> 0x0016 }
                    r1 = 8;
                    r0.setVisibility(r1);	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r0 = r0.f24113u;	 Catch:{ all -> 0x0016 }
                    r1 = 8;
                    r0.setVisibility(r1);	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0016 }
                    r4 = r16;	 Catch:{ all -> 0x0016 }
                    r2 = r2 - r4;
                    r0.f24097V = r2;	 Catch:{ all -> 0x0016 }
                    r1 = com.smule.android.logging.Analytics.SearchTarget.INSTANT_MIXED;	 Catch:{ all -> 0x0016 }
                    r2 = r18;	 Catch:{ all -> 0x0016 }
                    r3 = 0;
                    r4 = r19;	 Catch:{ all -> 0x0016 }
                    r5 = r15;	 Catch:{ all -> 0x0016 }
                    r0 = r10.f23998f;	 Catch:{ all -> 0x0016 }
                    r6 = r0.f24097V;	 Catch:{ all -> 0x0016 }
                    r8 = 0;
                    com.smule.android.logging.Analytics.m17853a(r1, r2, r3, r4, r5, r6, r8);	 Catch:{ all -> 0x0016 }
                    goto L_0x0139;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.songbook_search.SearchFragment.14.handleResponse(com.smule.android.network.managers.SearchManager$SASearchGlobalResponse):void");
                }
            });
        }
    }

    protected void m25310a(SearchMixResultAdapter searchMixResultAdapter, MediaListView mediaListView) {
        if (this.ao != null) {
            this.ao.destroy();
            this.ao = null;
            this.ap = null;
        }
        if (MagicAdSettings.m17435a(NativeAdPlacementType.SEARCH_MIXED)) {
            ViewBinder build = new Builder(C1947R.layout.native_ad_search_show_all_item_view).iconImageId(C1947R.id.search_show_all_ad_icon).titleId(C1947R.id.search_show_all_ad_title).mainImageId(C1947R.id.search_show_all_ad_main_image).textId(C1947R.id.search_show_all_ad_text).callToActionId(C1947R.id.search_show_all_ad_cta).privacyInformationIconImageId(C1947R.id.search_show_all_ad_privacy_icon).build();
            HashMap a = AdUtils.m22219a(null);
            this.ap = new MagicMoPubStreamAdPlacer(getActivity());
            this.ao = MagicAdAdapterFactory.m17426a().m17428a(getActivity(), NativeAdPlacementType.SEARCH_MIXED, new Builder(C1947R.layout.native_ad_ghost_search_show_all_item_view).build(), build, this.ap, a, mediaListView, searchMixResultAdapter, C1947R.id.icn_more, new OnClickListener(this) {
                final /* synthetic */ SearchFragment f23999a;

                {
                    this.f23999a = r1;
                }

                public void onClick(View view) {
                    this.f23999a.m19874y();
                    new NativeAdsMoreDialog(this.f23999a).show();
                }
            }, new Runnable(this) {
                final /* synthetic */ SearchFragment f24000a;

                {
                    this.f24000a = r1;
                }

                public void run() {
                    this.f24000a.m19874y();
                }
            });
            if (this.ao == null) {
                Log.e(f24075e, "mMagicNativeAdMediatorAdapter null");
                mediaListView.setMagicAdapter(searchMixResultAdapter);
                return;
            }
            return;
        }
        mediaListView.setMagicAdapter(searchMixResultAdapter);
    }

    protected void m25319d(int i) {
        if (this.ap != null) {
            Log.b(f24075e, "setAd:" + i);
            this.ap.setClientPositioning(i);
        }
    }

    protected void m25300C() {
        m25315b(false);
        if (getActivity() instanceof MediaPlayingActivity) {
            BaseFragment a = SearchHistoryFragment.m25339a(this);
            MiscUtils.m25891a(getActivity(), true);
            ((MediaPlayingActivity) getActivity()).a(a, a.mo6383s(), C1947R.animator.slide_enter_from_right, C1947R.animator.slide_exit_from_right, C1947R.animator.slide_enter_from_left, C1947R.animator.slide_exit_from_left, true, true);
            return;
        }
        mo6487a(SearchHistoryFragment.m25339a(this));
    }

    protected void m25321f(int i) {
        BaseFragment a = SearchShowAllFragment.m25381a(i, this.f24098W, this.f24104h, this.f24105i, this.aa);
        if (getActivity() instanceof MediaPlayingActivity) {
            MiscUtils.m25891a(getActivity(), true);
            ((MediaPlayingActivity) getActivity()).a(a, a.mo6383s(), C1947R.animator.slide_enter_from_right, C1947R.animator.slide_exit_from_right, C1947R.animator.slide_enter_from_left, C1947R.animator.slide_exit_from_left, true, true);
            return;
        }
        mo6487a(a);
    }

    protected MediaListView mo6862z() {
        return this.f24115w;
    }

    protected boolean mo6470a(View view) {
        return view.isShown();
    }

    public void mo6930t() {
        super.mo6930t();
        onPause();
    }

    public void mo6929a(BaseFragment baseFragment, String str) {
        this.ab = true;
        super.mo6929a(baseFragment, str);
    }

    public void mo6449u() {
        super.mo6449u();
        MediaPlayingListItem.m23034a(this.f24115w);
    }

    public void onStart() {
        super.onStart();
        if (this.r == null || !MediaPlayerServiceController.m24641a().m24662c(this.r.getMediaKey())) {
            this.s = -1;
        }
        if (this.f24093R != null) {
            m25317c(this.f24093R);
            this.f24087L = this.f24093R;
            String str = this.f24093R;
            this.f24093R = null;
            m25311a(str, str, false, SearchExecuteContext.RECENT, -1, true);
        }
    }

    @Click
    protected void m25302F() {
        this.f24103g.setText("");
        this.f24103g.requestFocus();
        MiscUtils.m25890a(getActivity(), this.f24103g);
    }

    @Click
    protected void m25303G() {
        getActivity().onBackPressed();
    }

    public boolean mo6400c() {
        m25262a(SearchBarExitContext.EXIT, this.f24088M, this.f24103g.getText().toString());
        return false;
    }

    @Click
    protected void m25304H() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f24082G);
    }
}
