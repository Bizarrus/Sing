package com.smule.singandroid;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.share.internal.ShareConstants;
import com.mopub.common.Constants;
import com.mopub.nativeads.GooglePlayServicesAdRenderer;
import com.mopub.nativeads.MagicGhostRenderer;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.MagicMoPubStaticNativeAdRenderer;
import com.mopub.nativeads.MoPubNative.SmuleNativeAdEventListenerInterface;
import com.mopub.nativeads.MoPubNativeAdLoadedListener;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.SmuleNativeAdEventListener;
import com.mopub.nativeads.ViewBinder;
import com.mopub.nativeads.ViewBinder.Builder;
import com.mopub.nativeads.VisibilityTracker;
import com.mopub.volley.DefaultRetryPolicy;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.smule.SmuleApplication;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.vendors.mopub.MagicMoPubNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Analytics.RecSysContext;
import com.smule.android.logging.Analytics.RecommendationType;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.AnalyticsProcessor;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.PromotionManager;
import com.smule.android.network.managers.PromotionManager.BannersCallback;
import com.smule.android.network.managers.PromotionManager.BannersResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.StoreManager.StoreManagerOptionalMethodsDelegate;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.Banner;
import com.smule.android.network.models.StoreSectionV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.SongbookSectionUtils;
import com.smule.android.utils.SongbookSectionUtils.OnFetchedNonListingOwnedSongs;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.AdapterInterface;
import com.smule.singandroid.adapters.songbook.SongbookAmazingMoPubAdAdapter;
import com.smule.singandroid.adapters.songbook.SongbookCommunityAdapter;
import com.smule.singandroid.adapters.songbook.SongbookRecommendedAdapter;
import com.smule.singandroid.adapters.songbook.SongbookSongsAdapter;
import com.smule.singandroid.adapters.songbook.SongbookTrendingSongsAdapter;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.ads.OnLaunchAd;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.customviews.OverlayWithRectangularHoleImageView;
import com.smule.singandroid.customviews.PagerSlidingTabStrip;
import com.smule.singandroid.customviews.SongbookSortSelector.SortType;
import com.smule.singandroid.customviews.SongbookSortSelector.SortTypeSelectedListener;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.SongbookListView;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.models.SongbookListViewState;
import com.smule.singandroid.models.SongbookSection;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarFadeCallback;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.SongbookSectionUtil;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class SongbookFragment extends BaseFragment implements MoPubNativeAdLoadedListener, ActionBarFadeCallback {
    public static final String f20306e = SongbookFragment.class.getName();
    Runnable f20307A = new C41727(this);
    int f20308B = 0;
    List<View> f20309C = new ArrayList();
    private SectionsPagerAdapter f20310D;
    private String f20311E;
    private List<SongbookSection> f20312F = new ArrayList();
    private String f20313G;
    private String f20314H;
    private String f20315I;
    private Observer f20316J;
    private Observer f20317K;
    private boolean f20318L = false;
    private Context f20319M;
    private ConcurrentHashMap<String, Boolean> f20320N = new ConcurrentHashMap();
    private int f20321O;
    private int f20322P;
    private int f20323Q = -1;
    private int f20324R = -1;
    private Menu f20325S;
    private boolean f20326T;
    private PagerAdapter f20327U;
    private ArrayList<Banner> f20328V = new ArrayList();
    private AtomicBoolean f20329W = new AtomicBoolean(false);
    private volatile boolean f20330X = true;
    private boolean f20331Y;
    private OnClickListener f20332Z = new C41672(this);
    private OnClickListener aa = new C41683(this);
    private AnimatorListener ab = new C41694(this);
    private AnimatorListener ac = new C41705(this);
    private boolean ad = false;
    private SongbookAdapterInterface ae = new SongbookAdapterInterface();
    private InitialLoadRecommendedCallback af = new InitialLoadRecommendedCallback(this) {
        final /* synthetic */ SongbookFragment f20257a;

        {
            this.f20257a = r1;
        }

        public void mo6652a() {
            this.f20257a.m21811L();
        }
    };
    @ViewById
    protected View f20333f;
    @ViewById
    protected LinearLayout f20334g;
    @ViewById
    protected TextView f20335h;
    @ViewById
    protected FrameLayout f20336i;
    @ViewById
    protected View f20337j;
    @ViewById
    protected PagerSlidingTabStrip f20338k;
    @ViewById
    CustomViewPager f20339l;
    @ViewById
    View f20340m;
    @ViewById
    FrameLayout f20341n;
    @ViewById
    ViewPager f20342o;
    @ViewById
    LinearLayout f20343p;
    @ViewById
    OverlayWithRectangularHoleImageView f20344q;
    @ViewById
    View f20345r;
    @ViewById
    View f20346s;
    protected Integer f20347t = null;
    @InstanceState
    protected int f20348u = -1;
    @InstanceState
    protected boolean f20349v = false;
    OnLaunchAd f20350w;
    final Handler f20351x = new Handler(Looper.getMainLooper());
    final Runnable f20352y = new C41661(this);
    final int f20353z = 5000;

    class C41661 implements Runnable {
        final /* synthetic */ SongbookFragment f20256a;

        C41661(SongbookFragment songbookFragment) {
            this.f20256a = songbookFragment;
        }

        public void run() {
            if (!this.f20256a.isAdded()) {
                return;
            }
            if (this.f20256a.f20342o.getCurrentItem() == this.f20256a.f20327U.getCount() - 1) {
                this.f20256a.f20342o.setCurrentItem(0, true);
            } else {
                this.f20256a.f20342o.setCurrentItem(this.f20256a.f20342o.getCurrentItem() + 1, true);
            }
        }
    }

    public interface InitialLoadRecommendedCallback {
        void mo6652a();
    }

    class C41672 implements OnClickListener {
        final /* synthetic */ SongbookFragment f20258a;

        C41672(SongbookFragment songbookFragment) {
            this.f20258a = songbookFragment;
        }

        public void onClick(View view) {
            Analytics.m17848a(SearchClkContext.SONGBOOK);
            this.f20258a.mo6487a(SearchFragment.m25251A());
        }
    }

    class C41683 implements OnClickListener {
        final /* synthetic */ SongbookFragment f20259a;

        C41683(SongbookFragment songbookFragment) {
            this.f20259a = songbookFragment;
        }

        public void onClick(View view) {
            Analytics.m17848a(SearchClkContext.BOTTOMOFRECLIST);
            this.f20259a.mo6487a(SearchFragment.m25251A());
        }
    }

    class C41694 implements AnimatorListener {
        final /* synthetic */ SongbookFragment f20260a;

        C41694(SongbookFragment songbookFragment) {
            this.f20260a = songbookFragment;
        }

        public void onAnimationStart(Animator animator) {
            if (this.f20260a.isAdded()) {
                Log.b(SongbookFragment.f20306e, "FadeWhiteAnimator Start");
                TextView titleView = ((MasterActivity) this.f20260a.getActivity()).B().getTitleView();
                titleView.setVisibility(0);
                titleView.setAlpha(0.0f);
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f20260a.isAdded()) {
                Log.b(SongbookFragment.f20306e, "FadeWhiteAnimator End");
                MasterToolbar B = ((MasterActivity) this.f20260a.getActivity()).B();
                TextView preSearchTitleView = B.getPreSearchTitleView();
                ImageView preSearchToolbarNavigationIconView = B.getPreSearchToolbarNavigationIconView();
                TextView titleView = B.getTitleView();
                titleView.setVisibility(0);
                titleView.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                preSearchTitleView.setVisibility(8);
                preSearchToolbarNavigationIconView.setVisibility(8);
                preSearchToolbarNavigationIconView.setOnClickListener(null);
                preSearchTitleView.setOnClickListener(null);
                preSearchTitleView.animate().setListener(null);
                this.f20260a.m21816Q();
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    class C41705 implements AnimatorListener {
        final /* synthetic */ SongbookFragment f20261a;

        C41705(SongbookFragment songbookFragment) {
            this.f20261a = songbookFragment;
        }

        public void onAnimationStart(Animator animator) {
            if (this.f20261a.isAdded()) {
                Log.b(SongbookFragment.f20306e, "FadeBlueAnimator Start");
                MasterToolbar B = ((MasterActivity) this.f20261a.getActivity()).B();
                TextView preSearchTitleView = B.getPreSearchTitleView();
                ImageView preSearchToolbarNavigationIconView = B.getPreSearchToolbarNavigationIconView();
                preSearchTitleView.setVisibility(0);
                preSearchToolbarNavigationIconView.setVisibility(0);
                preSearchTitleView.setAlpha(0.0f);
                preSearchToolbarNavigationIconView.setAlpha(0.0f);
                if (this.f20261a.f20325S != null) {
                    this.f20261a.f20325S.clear();
                } else {
                    Log.e(SongbookFragment.f20306e, "DROIDSING-11192: mOptionsMenu is null");
                }
                preSearchToolbarNavigationIconView.setOnClickListener(this.f20261a.f20332Z);
                preSearchTitleView.setOnClickListener(this.f20261a.f20332Z);
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f20261a.isAdded()) {
                Log.b(SongbookFragment.f20306e, "FadeBlueAnimator End");
                MasterToolbar B = ((MasterActivity) this.f20261a.getActivity()).B();
                TextView titleView = B.getTitleView();
                TextView preSearchTitleView = B.getPreSearchTitleView();
                ImageView preSearchToolbarNavigationIconView = B.getPreSearchToolbarNavigationIconView();
                preSearchTitleView.setVisibility(0);
                preSearchToolbarNavigationIconView.setVisibility(0);
                preSearchTitleView.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                preSearchToolbarNavigationIconView.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                titleView.setVisibility(8);
                titleView.animate().setListener(null);
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    class C41727 implements Runnable {
        final /* synthetic */ SongbookFragment f20264a;

        C41727(SongbookFragment songbookFragment) {
            this.f20264a = songbookFragment;
        }

        public void run() {
            this.f20264a.m19874y();
        }
    }

    class C41749 extends UnderlineSpan {
        final /* synthetic */ SongbookFragment f20267a;

        C41749(SongbookFragment songbookFragment) {
            this.f20267a = songbookFragment;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    private class BannerPagerAdapter extends PagerAdapter {
        Context f20270a;
        LayoutInflater f20271b = ((LayoutInflater) this.f20270a.getSystemService("layout_inflater"));
        final /* synthetic */ SongbookFragment f20272c;

        public BannerPagerAdapter(SongbookFragment songbookFragment, Context context) {
            this.f20272c = songbookFragment;
            this.f20270a = context;
        }

        public int getCount() {
            return this.f20272c.f20328V.size() > 0 ? Integer.MAX_VALUE : 0;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            final int size = i % this.f20272c.f20328V.size();
            View inflate = this.f20271b.inflate(C1947R.layout.banner_item, viewGroup, false);
            ImageView imageView = (ImageView) inflate.findViewById(C1947R.id.image_view);
            ImageUtils.a(((Banner) this.f20272c.f20328V.get(size)).imgUrl, imageView, C1947R.drawable.bkg_songbook);
            imageView.setOnClickListener(new WeakListener.OnClickListener(this, new OnClickListener(this) {
                final /* synthetic */ BannerPagerAdapter f20269b;

                public void onClick(View view) {
                    Uri parse = Uri.parse(((Banner) this.f20269b.f20272c.f20328V.get(size)).destUrl);
                    String scheme = parse.getScheme();
                    SingAnalytics.m26151f(((Banner) this.f20269b.f20272c.f20328V.get(size)).name);
                    if (scheme.equals(Constants.HTTP) || scheme.equals(Constants.HTTPS)) {
                        this.f20269b.f20272c.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((Banner) this.f20269b.f20272c.f20328V.get(size)).destUrl)));
                    } else if (scheme.equals("smulesing")) {
                        try {
                            ((MasterActivity) this.f20269b.f20272c.getActivity()).a(new DeepLink(parse), true);
                        } catch (IllegalArgumentException e) {
                            Log.e(SongbookFragment.f20306e, "No match for URI: " + parse);
                        }
                    }
                }
            }));
            viewGroup.addView(inflate);
            return inflate;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((LinearLayout) obj);
        }
    }

    private class BannerPagerMoPubAdAdapter extends PagerAdapter implements SmuleNativeAdEventListenerInterface {
        LayoutInflater f20275a = ((LayoutInflater) SmuleApplication.a().getSystemService("layout_inflater"));
        final /* synthetic */ SongbookFragment f20276b;
        private String f20277c;
        private MoPubStreamAdPlacer f20278d;
        private RequestParameters f20279e = MagicMoPubNativeAdMediatorAdapter.getRequestParams(NativeAdPlacementType.SONGBOOK_LISTINGS, AdUtils.m22219a(null));

        public BannerPagerMoPubAdAdapter(SongbookFragment songbookFragment, @NonNull String str, MoPubStreamAdPlacer moPubStreamAdPlacer) {
            this.f20276b = songbookFragment;
            this.f20277c = str;
            this.f20278d = moPubStreamAdPlacer;
            moPubStreamAdPlacer.setItemCount(715827882);
            ViewBinder build = new Builder(C1947R.layout.native_ad_songbook_carousel).titleId(C1947R.id.native_title).textId(C1947R.id.native_text).iconImageId(C1947R.id.native_icon_image).callToActionId(C1947R.id.native_cta).privacyInformationIconImageId(C1947R.id.native_privacy_information_icon_image).build();
            this.f20278d.registerAdRenderer(new GooglePlayServicesAdRenderer(build, 0, null, songbookFragment.f20307A));
            this.f20278d.registerAdRenderer(new MoPubStaticNativeAdRenderer(build, songbookFragment.f20307A));
            if (this.f20278d != null) {
                this.f20278d.loadAds(this.f20277c, this.f20279e);
            }
        }

        public int getCount() {
            return this.f20276b.f20328V.size() > 0 ? this.f20278d.getAdjustedCount(715827882) : 0;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View adView;
            this.f20278d.placeAdsInRange(i, i);
            if (this.f20278d.isAd(i)) {
                adView = this.f20278d.getAdView(i, null, viewGroup);
            } else {
                final int originalPosition = this.f20278d.getOriginalPosition(i) % this.f20276b.f20328V.size();
                View inflate = this.f20275a.inflate(C1947R.layout.banner_item, viewGroup, false);
                ImageView imageView = (ImageView) inflate.findViewById(C1947R.id.image_view);
                ImageUtils.a(((Banner) this.f20276b.f20328V.get(originalPosition)).imgUrl, imageView, C1947R.drawable.bkg_songbook);
                imageView.setOnClickListener(new WeakListener.OnClickListener(this, new OnClickListener(this) {
                    final /* synthetic */ BannerPagerMoPubAdAdapter f20274b;

                    public void onClick(View view) {
                        Uri parse = Uri.parse(((Banner) this.f20274b.f20276b.f20328V.get(originalPosition)).destUrl);
                        String scheme = parse.getScheme();
                        SingAnalytics.m26151f(((Banner) this.f20274b.f20276b.f20328V.get(originalPosition)).name);
                        if (scheme.equals(Constants.HTTP) || scheme.equals(Constants.HTTPS)) {
                            this.f20274b.f20276b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((Banner) this.f20274b.f20276b.f20328V.get(originalPosition)).destUrl)));
                        } else if (scheme.equals("smulesing")) {
                            try {
                                ((MasterActivity) this.f20274b.f20276b.getActivity()).a(new DeepLink(parse), true);
                            } catch (IllegalArgumentException e) {
                                Log.e(SongbookFragment.f20306e, "No match for URI: " + parse);
                            }
                        }
                    }
                }));
                adView = inflate;
            }
            viewGroup.addView(adView);
            return adView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public void m21784a() {
            if (this.f20278d != null) {
                this.f20278d.destroy();
            }
        }

        public void setSmuleNativeAdEventListener(@NonNull SmuleNativeAdEventListener smuleNativeAdEventListener) {
            this.f20278d.setSmuleNativeAdEventListener(smuleNativeAdEventListener);
        }
    }

    private class CustomScrollListener implements OnScrollListener {
        final /* synthetic */ SongbookFragment f20280a;
        private final boolean f20281b;

        public CustomScrollListener(SongbookFragment songbookFragment, boolean z) {
            this.f20280a = songbookFragment;
            this.f20281b = z;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 0 && this.f20280a.f20322P > 0 && this.f20281b) {
                this.f20280a.m21830a(this.f20280a.f20321O, this.f20280a.f20322P);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            float max;
            if (this.f20281b) {
                this.f20280a.f20321O = i;
                this.f20280a.f20322P = i2;
            }
            int af = ((MasterActivity) this.f20280a.getActivity()).af();
            if (this.f20280a.f20341n.getVisibility() == 0) {
                if (i == 0) {
                    View childAt = absListView.getChildAt(0);
                    max = (float) Math.max(-af, childAt != null ? childAt.getTop() : 0);
                } else {
                    max = i > 0 ? (float) (-af) : 0.0f;
                }
                this.f20280a.f20340m.setTranslationY(max);
            } else {
                max = 0.0f;
            }
            this.f20280a.f20310D.m21795a((int) max);
            this.f20280a.m21820U();
            if (absListView.getChildCount() > 0 && this.f20280a.f20310D.f20291a != null) {
                this.f20280a.f20310D.f20291a.getSongsAdapter().mo6703o().m24755a(i, absListView.getChildAt(0).getTop());
            }
        }
    }

    private static class OptionalMethodsDelegate implements StoreManagerOptionalMethodsDelegate {
        private OptionalMethodsDelegate() {
        }

        public boolean mo6653a() {
            return false;
        }

        public boolean mo6654b() {
            return false;
        }

        public int mo6655c() {
            return 0;
        }

        public String mo6656d() {
            return null;
        }

        public int mo6657e() {
            return 3;
        }
    }

    class SectionsPagerAdapter extends PagerAdapter {
        SongbookListView f20291a;
        SongbookSection f20292b;
        Set<SongbookListView> f20293c = new HashSet();
        int f20294d;
        boolean f20295e;
        int f20296f = -1;
        final /* synthetic */ SongbookFragment f20297g;

        class C41771 implements OnClickListener {
            final /* synthetic */ SectionsPagerAdapter f20282a;

            C41771(SectionsPagerAdapter sectionsPagerAdapter) {
                this.f20282a = sectionsPagerAdapter;
            }

            public void onClick(View view) {
                this.f20282a.f20297g.m19874y();
                new NativeAdsMoreDialog(this.f20282a.f20297g).show();
            }
        }

        class C41814 implements Runnable {
            final /* synthetic */ SectionsPagerAdapter f20290a;

            C41814(SectionsPagerAdapter sectionsPagerAdapter) {
                this.f20290a = sectionsPagerAdapter;
            }

            public void run() {
                if (this.f20290a.f20297g.isAdded()) {
                    for (SongbookListView a : this.f20290a.f20293c) {
                        this.f20290a.m21796a(a);
                    }
                }
            }
        }

        SectionsPagerAdapter(SongbookFragment songbookFragment) {
            this.f20297g = songbookFragment;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            RequestParameters requestParameters;
            SongbookAmazingAdapter songbookAmazingAdapter = null;
            SongbookSection songbookSection = (SongbookSection) this.f20297g.f20312F.get(i);
            SongbookAmazingAdapter a = m21793a(songbookSection);
            String a2 = MagicAdSettings.m17433a(this.f20297g.getActivity(), NativeAdPlacementType.SONGBOOK_LISTINGS);
            Object obj = (a2 == null || !MagicAdSettings.m17435a(NativeAdPlacementType.SONGBOOK_LISTINGS)) ? null : 1;
            if (obj != null) {
                songbookAmazingAdapter = new SongbookAmazingMoPubAdAdapter(new MagicMoPubGhostStreamAdPlacer(this.f20297g.getActivity()), a, new VisibilityTracker(this.f20297g.getActivity()));
                HashMap hashMap = new HashMap();
                hashMap.put("songbook_id", songbookSection.f23514c);
                RequestParameters requestParams = MagicMoPubNativeAdMediatorAdapter.getRequestParams(NativeAdPlacementType.SONGBOOK_LISTINGS, AdUtils.m22219a(hashMap));
                ViewBinder build = new Builder(C1947R.layout.native_ad_songbook_list_item).titleId(C1947R.id.native_title).textId(C1947R.id.native_text).iconImageId(C1947R.id.native_icon_image).callToActionId(C1947R.id.native_cta).privacyInformationIconImageId(C1947R.id.native_privacy_information_icon_image).build();
                OnClickListener c41771 = new C41771(this);
                songbookAmazingAdapter.m22146a(new MagicGhostRenderer(new Builder(C1947R.layout.native_ad_ghost_songbook_list_item).build()));
                songbookAmazingAdapter.m22146a(new GooglePlayServicesAdRenderer(build, C1947R.id.icn_more, c41771, this.f20297g.f20307A));
                songbookAmazingAdapter.m22146a(new MagicMoPubStaticNativeAdRenderer(build, C1947R.id.icn_more, c41771, this.f20297g.f20307A));
                MagicMoPubNativeAdMediatorAdapter.attachInHouseAdListeners(songbookAmazingAdapter, NativeAdPlacementType.SONGBOOK_LISTINGS, this.f20297g.f20307A);
                requestParameters = requestParams;
            } else {
                requestParameters = null;
            }
            SongbookAmazingAdapter songbookAmazingAdapter2 = songbookAmazingAdapter != null ? songbookAmazingAdapter : a;
            final SongbookListView a3 = SongbookListView.m24481a(this.f20297g.getActivity());
            a3.setAdapter(songbookAmazingAdapter2);
            a3.getSongbookSortSelector().setSortTypeSelectedListener(new SortTypeSelectedListener(this) {
                final /* synthetic */ SectionsPagerAdapter f20284b;

                public void mo6658a(SortType sortType) {
                    SongbookAmazingAdapter songsAdapter = a3.getSongsAdapter();
                    songsAdapter.mo6694a(sortType);
                    a3.m24489c(this.f20284b.f20294d);
                    SingAnalytics.m26086a(sortType.m23492b(), songsAdapter.mo6701m().f23514c);
                }
            });
            a3.getSongbookSortSelector().setSortMenuType(a3.getSongsAdapter().mo6704p());
            a3.getSongbookSortSelector().setSortType(a3.getSongsAdapter().mo6703o().f23509a);
            m21796a(a3);
            songbookAmazingAdapter2.mo6698j();
            if (songbookAmazingAdapter != null) {
                songbookAmazingAdapter.m22149a(a2, requestParameters);
            }
            viewGroup.addView(a3);
            this.f20293c.add(a3);
            a3.m24484a(this.f20294d);
            a3.setTag("sb_item#" + i);
            return a3;
        }

        protected SongbookSongsAdapter m21793a(final SongbookSection songbookSection) {
            SongbookSongsAdapter songbookCommunityAdapter;
            String str = songbookSection.f23514c;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1614506526:
                    if (str.equals("suggested_songs")) {
                        int i = 1;
                        break;
                    }
                    break;
                case -464048725:
                    if (str.equals("my_songs")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 320269864:
                    if (str.equals("community_songs")) {
                        obj = null;
                        break;
                    }
                    break;
                case 1660761700:
                    if (str.equals("trending_songs")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    songbookCommunityAdapter = new SongbookCommunityAdapter(this.f20297g.ae);
                    break;
                case 1:
                    songbookCommunityAdapter = new SongbookRecommendedAdapter(this.f20297g.ae, this.f20297g.af, this.f20297g.f20331Y);
                    break;
                case 2:
                    songbookCommunityAdapter = new SongbookTrendingSongsAdapter(this.f20297g.ae, this.f20297g.af);
                    break;
                case 3:
                    songbookSection.m24757a();
                    final SongbookSongsAdapter songbookSongsAdapter = new SongbookSongsAdapter(this.f20297g.ae);
                    Set<String> b = EntitlementsManager.m18163a().m18181b();
                    Set c = EntitlementsManager.m18163a().m18183c();
                    int size = c.size() + b.size();
                    if (this.f20296f > 0 && this.f20296f != size) {
                        songbookSection.f23512a = SongbookSectionUtils.m19042a().m19050b();
                    }
                    this.f20296f = size;
                    if (songbookSection.f23512a.size() < size && !SongbookSectionUtils.m19042a().m19051c()) {
                        SongbookSectionUtils.m19042a().m19048a(new OnFetchedNonListingOwnedSongs(this) {
                            final /* synthetic */ SectionsPagerAdapter f20289c;

                            public void mo6651a(final List<SongbookEntry> list) {
                                this.f20289c.f20297g.f20351x.post(new Runnable(this) {
                                    final /* synthetic */ C41803 f20286b;

                                    public void run() {
                                        if (this.f20286b.f20289c.f20297g.isAdded()) {
                                            if (songbookSection.f23512a != null) {
                                                for (SongbookEntry songbookEntry : list) {
                                                    if (!songbookSection.f23513b.contains(songbookEntry.mo6289c())) {
                                                        songbookSection.f23512a.add(songbookEntry);
                                                        songbookSection.f23513b.add(songbookEntry.mo6289c());
                                                        SectionsPagerAdapter sectionsPagerAdapter = this.f20286b.f20289c;
                                                        sectionsPagerAdapter.f20296f++;
                                                    }
                                                }
                                            }
                                            songbookSongsAdapter.mo6702n();
                                        }
                                    }
                                });
                            }
                        });
                        List arrayList = new ArrayList();
                        obj = null;
                        for (String str2 : b) {
                            Object obj2;
                            if (songbookSection.f23513b.contains(str2)) {
                                obj2 = obj;
                            } else {
                                arrayList.add(str2);
                                obj2 = 1;
                            }
                            obj = obj2;
                        }
                        if (obj != null) {
                            SongbookSectionUtils.m19042a().m19049a(arrayList);
                        }
                        songbookCommunityAdapter = songbookSongsAdapter;
                        break;
                    }
                    songbookCommunityAdapter = songbookSongsAdapter;
                    break;
                    break;
                default:
                    songbookCommunityAdapter = new SongbookSongsAdapter(this.f20297g.ae);
                    break;
            }
            SongbookListViewState a = ((MasterActivity) this.f20297g.getActivity()).a(songbookSection);
            songbookCommunityAdapter.m22178b(this.f20297g.f20309C);
            songbookCommunityAdapter.m22177a(songbookSection, a);
            songbookCommunityAdapter.mo6694a(a.f23509a);
            return songbookCommunityAdapter;
        }

        public CharSequence getPageTitle(int i) {
            return ((SongbookSection) this.f20297g.f20312F.get(i)).f23515d;
        }

        public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
            if (this.f20297g.isAdded() && !this.f20295e) {
                this.f20297g.mo6666e(i);
            }
        }

        public void m21794a() {
            for (SongbookListView songbookListView : this.f20293c) {
                if (songbookListView != this.f20291a) {
                    songbookListView.f23252a.setOnScrollListener(null);
                }
            }
            this.f20297g.mo6485a(this.f20297g.f20310D.f20291a.f23252a, ActionBarHighlightMode.AFTER_SCROLL, new CustomScrollListener(this.f20297g, this.f20297g.m21821V()));
        }

        public void m21795a(int i) {
            this.f20294d = i;
            for (SongbookListView songbookListView : this.f20293c) {
                if (songbookListView != this.f20291a) {
                    songbookListView.m24487b(this.f20294d);
                }
            }
        }

        public void m21797b() {
            this.f20297g.m19848b(new C41814(this));
        }

        protected void m21796a(SongbookListView songbookListView) {
            songbookListView.m24485a(((MasterActivity) this.f20297g.getActivity()).af(), this.f20297g.getResources().getDimensionPixelSize(C1947R.dimen.row_single_height));
        }

        public int getCount() {
            return this.f20297g.f20312F.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            SongbookListView songbookListView = (SongbookListView) obj;
            if (songbookListView != null) {
                SongbookAmazingAdapter songsAdapter = songbookListView.getSongsAdapter();
                if (songsAdapter instanceof SongbookAmazingMoPubAdAdapter) {
                    ((SongbookAmazingMoPubAdAdapter) songsAdapter).m22168r();
                }
            }
            m21798b(songbookListView);
            viewGroup.removeView(songbookListView);
            this.f20293c.remove(songbookListView);
        }

        public void m21800c() {
            for (SongbookListView b : this.f20293c) {
                m21798b(b);
            }
            this.f20293c.clear();
        }

        public void m21798b(SongbookListView songbookListView) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new RuntimeException("Not on main thread!");
            }
            List<View> arrayList = new ArrayList();
            songbookListView.f23252a.reclaimViews(arrayList);
            for (View view : arrayList) {
                if (this.f20297g.f20309C.size() > 30) {
                    Log.d(SongbookFragment.f20306e, "mListItemsRecycleList.size too big, size: " + this.f20297g.f20309C.size());
                    return;
                } else if (view instanceof ListingListItem) {
                    ((ListingListItem) view).m24383d();
                    this.f20297g.f20309C.add(view);
                }
            }
        }

        public void m21799b(@NonNull SongbookSection songbookSection) {
            for (SongbookListView songsAdapter : this.f20293c) {
                SongbookAmazingAdapter songsAdapter2 = songsAdapter.getSongsAdapter();
                if (songsAdapter2 != null) {
                    SongbookSection m = songsAdapter2.mo6701m();
                    if (m != null && m.f23514c.equals(songbookSection.f23514c)) {
                        m.f23512a = songbookSection.f23512a;
                        songsAdapter2.mo6702n();
                    }
                }
            }
        }
    }

    private class SongbookAdapterInterface implements AdapterInterface {
        final /* synthetic */ SongbookFragment f20305a;

        private SongbookAdapterInterface(SongbookFragment songbookFragment) {
            this.f20305a = songbookFragment;
        }

        public Context mo6659a() {
            return this.f20305a.getActivity();
        }

        public void mo6660a(final ListingListItem listingListItem, final SongbookEntry songbookEntry, final int i) {
            if (songbookEntry == null) {
                Log.d(SongbookFragment.f20306e, "setupListItem entry was null");
                return;
            }
            songbookEntry.m18772r();
            listingListItem.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SongbookAdapterInterface f20300c;

                public void onClick(View view) {
                    if (this.f20300c.f20305a.isAdded()) {
                        SingAnalytics.m26074a(songbookEntry, this.f20300c.f20305a.m21868H(), Integer.valueOf(i));
                        if (SongbookEntryUtils.m26166a(songbookEntry, this.f20300c.f20305a.m21868H())) {
                            String a = SongbookEntryUtils.m26163a(songbookEntry);
                            if (this.f20300c.f20305a.m21821V() && a != null) {
                                Analytics.m17860a(a, ItemClickType.SING, i, RecSysContext.SONGBOOK, this.f20300c.f20305a.m21868H());
                            }
                            PreSingActivity.m24763a(this.f20300c.f20305a.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(songbookEntry).m24797a(this.f20300c.f20305a.m21868H()).a();
                            return;
                        }
                        this.f20300c.f20305a.mo6487a(UpsellManager.m25791a(true, songbookEntry, this.f20300c.f20305a.m21868H(), null, UpsellType.VIP_SONG));
                    }
                }
            });
            listingListItem.setAlbumArtClickListener(new OnClickListener(this) {
                final /* synthetic */ SongbookAdapterInterface f20304d;

                public void onClick(View view) {
                    boolean z = true;
                    String a = SongbookEntryUtils.m26163a(songbookEntry);
                    if (this.f20304d.f20305a.m21821V() && a != null) {
                        Analytics.m17860a(a, ItemClickType.LISTEN, i, RecSysContext.SONGBOOK, this.f20304d.f20305a.m21868H());
                    }
                    if (!(songbookEntry instanceof ArrangementVersionLiteEntry)) {
                        Analytics.m17867a("songbook", this.f20304d.f20305a.m21836b(songbookEntry), songbookEntry.mo6294h(), songbookEntry.m18770p(), this.f20304d.f20305a.m21868H(), SongbookEntry.m18749a(songbookEntry));
                        this.f20304d.f20305a.mo6443a(songbookEntry);
                    } else if (!listingListItem.m23046t()) {
                        listingListItem.setAlbumArtClickedState(true);
                        if (this.f20304d.f20305a.f20320N == null) {
                            this.f20304d.f20305a.f20320N = new ConcurrentHashMap();
                        }
                        if (this.f20304d.f20305a.f20320N.containsKey(songbookEntry.mo6289c())) {
                            this.f20304d.m21804a(((Boolean) this.f20304d.f20305a.f20320N.get(songbookEntry.mo6289c())).booleanValue(), (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                            return;
                        }
                        int i = ((ArrangementVersionLiteEntry) songbookEntry).m18774a().removalCode;
                        if (i < 40 || i > 49) {
                            z = false;
                        }
                        this.f20304d.f20305a.f20320N.put(songbookEntry.mo6289c(), Boolean.valueOf(z));
                        this.f20304d.m21804a(z, (ArrangementVersionLiteEntry) songbookEntry, listingListItem);
                    }
                }
            });
        }

        private void m21804a(boolean z, ArrangementVersionLiteEntry arrangementVersionLiteEntry, ListingListItem listingListItem) {
            this.f20305a.f20320N.put(arrangementVersionLiteEntry.mo6289c(), Boolean.valueOf(z));
            if (z) {
                MediaPlayerServiceController.m24641a().m24660b(arrangementVersionLiteEntry.mo6289c());
                this.f20305a.m21874a(listingListItem);
                return;
            }
            Analytics.m17867a("songbook", this.f20305a.m21836b((SongbookEntry) arrangementVersionLiteEntry), arrangementVersionLiteEntry.mo6294h(), arrangementVersionLiteEntry.m18770p(), this.f20305a.m21868H(), SongbookEntry.m18749a((SongbookEntry) arrangementVersionLiteEntry));
            this.f20305a.mo6443a((SongbookEntry) arrangementVersionLiteEntry);
        }
    }

    public static class SongbookSectionComparatorByOrder implements Comparator<SongbookSection> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m21807a((SongbookSection) obj, (SongbookSection) obj2);
        }

        public int m21807a(SongbookSection songbookSection, SongbookSection songbookSection2) {
            if (songbookSection.f23516e != songbookSection2.f23516e) {
                return songbookSection.f23516e - songbookSection2.f23516e;
            }
            if (songbookSection.f23514c.equals("my_songs")) {
                return -1;
            }
            return 1;
        }
    }

    public String mo6383s() {
        return f20306e;
    }

    public static SongbookFragment m21826a() {
        return new SongbookFragment_();
    }

    public void mo6662d(int i) {
    }

    public void mo6663z() {
        if (this.f20349v) {
            MasterToolbar B = ((MasterActivity) getActivity()).B();
            TextView titleView = B.getTitleView();
            TextView preSearchTitleView = B.getPreSearchTitleView();
            ImageView preSearchToolbarNavigationIconView = B.getPreSearchToolbarNavigationIconView();
            preSearchTitleView.animate().setDuration(250).alpha(0.0f).setListener(this.ab).start();
            preSearchToolbarNavigationIconView.animate().setDuration(250).alpha(0.0f).start();
            titleView.animate().setDuration(250).alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).start();
            this.f20349v = false;
        }
    }

    public void mo6661A() {
        if (!this.f20349v) {
            MasterToolbar B = ((MasterActivity) getActivity()).B();
            TextView titleView = B.getTitleView();
            TextView preSearchTitleView = B.getPreSearchTitleView();
            ImageView preSearchToolbarNavigationIconView = B.getPreSearchToolbarNavigationIconView();
            titleView.animate().setDuration(250).alpha(0.0f).setListener(this.ac).start();
            preSearchToolbarNavigationIconView.animate().setDuration(250).alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).start();
            preSearchTitleView.animate().setDuration(250).alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).start();
            this.f20349v = true;
        }
    }

    protected void mo6485a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        super.mo6485a(absListView, actionBarHighlightMode, onScrollListener);
        this.a.m25990a((ActionBarFadeCallback) this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        StoreManager.m18378a().m18418a(new OptionalMethodsDelegate());
        this.f20319M = getActivity().getApplicationContext();
        final SingApplication g = SingApplication.g();
        this.f20326T = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).getBoolean("SHOW_COACH_MARK_CCCP", true);
        if (g != null) {
            new AsyncTask<Void, Void, Void>(this) {
                final /* synthetic */ SongbookFragment f20263b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m21783a((Void[]) objArr);
                }

                protected Void m21783a(Void... voidArr) {
                    g.k();
                    return null;
                }
            }.execute(new Void[0]);
        }
        this.f20331Y = SingServerValues.m21705z();
    }

    public void onAdLoaded(int i) {
        if (isAdded()) {
            this.f20342o.invalidate();
            this.f20327U.notifyDataSetChanged();
        }
    }

    public void onAdRemoved(int i) {
        if (isAdded()) {
            this.f20342o.invalidate();
            this.f20327U.notifyDataSetChanged();
        }
    }

    public void onAdChanged() {
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Log.b(f20306e, "onCreateOptionsMenu - begin");
        this.f20325S = menu;
        if (menu.findItem(C1947R.id.action_search) == null && !this.f20349v) {
            menuInflater.inflate(C1947R.menu.songbook_fragment_menu, menu);
            menu.findItem(C1947R.id.action_search).getIcon().setAlpha(0);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu.findItem(C1947R.id.action_search) != null) {
            Animator ofInt = ObjectAnimator.ofInt(menu.findItem(C1947R.id.action_search).getIcon(), "alpha", new int[]{0, 255});
            ofInt.setDuration(10);
            ofInt.start();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_search:
                Analytics.m17848a(SearchClkContext.SONGBOOK);
                mo6487a(SearchFragment.m25251A());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean mo6444d() {
        return false;
    }

    public void onDestroyOptionsMenu() {
        Log.b(f20306e, "onDestroyOptionsMenu - begin");
        super.onDestroyOptionsMenu();
        this.f20325S = null;
    }

    public boolean mo6445g() {
        return true;
    }

    protected boolean mo6447j() {
        return true;
    }

    public boolean mo6446i() {
        return false;
    }

    @Click
    protected void m21862B() {
        if (!SubscriptionManager.a().b()) {
            mo6487a(UpsellManager.m25791a(false, null, this.f20314H, null, UpsellType.BANNER));
        }
    }

    @Click
    protected void m21863C() {
    }

    @AfterViews
    void m21864D() {
        Log.a(f20306e, "updateFollowingViewBinding : After views created");
        this.f20310D = new SectionsPagerAdapter(this);
        this.f20310D.m21797b();
        this.f20338k.setVisibility(0);
        this.f20347t = null;
        m19850c((int) C1947R.string.songbook_title);
        Spannable spannable = (Spannable) Html.fromHtml(getString(C1947R.string.songbook_no_search_results_description, new Object[]{getString(C1947R.string.how_to_upload_arrangement)}));
        for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
            final String url = uRLSpan.getURL();
            spannable.setSpan(new ClickableSpan(this) {
                final /* synthetic */ SongbookFragment f20266b;

                public void onClick(View view) {
                    Context activity = this.f20266b.getActivity();
                    if (activity != null) {
                        activity.startActivity(WebViewActivity.m22001a(activity, url, true, true));
                    }
                }
            }, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 0);
            spannable.setSpan(new C41749(this), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 0);
            spannable.removeSpan(uRLSpan);
        }
        this.f20335h.setText(spannable);
        this.f20335h.setLinkTextColor(getResources().getColor(C1947R.color.tab_title));
        this.f20335h.setMovementMethod(LinkMovementMethod.getInstance());
        this.f20335h.setHighlightColor(0);
        this.f20339l.setAdapter(this.f20310D);
        this.f20338k.setViewPager(this.f20339l);
        this.f20338k.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ SongbookFragment f20237a;

            {
                this.f20237a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (this.f20237a.isAdded()) {
                    SongbookSection songbookSection = (SongbookSection) this.f20237a.f20312F.get(i);
                    this.f20237a.m21875c(songbookSection.f23514c);
                    if (!"community_songs".equals(songbookSection.f23514c)) {
                        this.f20237a.m21819T();
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
                switch (i) {
                    case 0:
                        ImageLoader.a().d();
                        if ("community_songs".equals(this.f20237a.f20314H)) {
                            this.f20237a.m21820U();
                            if (this.f20237a.f20326T) {
                                this.f20237a.m21818S();
                                return;
                            }
                            return;
                        }
                        return;
                    case 1:
                        ImageLoader.a().c();
                        this.f20237a.f20310D.m21795a(this.f20237a.f20310D.f20294d);
                        return;
                    default:
                        return;
                }
            }
        });
        String a = MagicAdSettings.m17433a(getActivity(), NativeAdPlacementType.SONGBOOK_CAROUSEL);
        int i = (a == null || !MagicAdSettings.m17435a(NativeAdPlacementType.SONGBOOK_CAROUSEL)) ? 0 : 1;
        if (i != 0) {
            MoPubStreamAdPlacer moPubStreamAdPlacer = new MoPubStreamAdPlacer(getActivity());
            moPubStreamAdPlacer.setAdLoadedListener(this);
            this.f20327U = new BannerPagerMoPubAdAdapter(this, a, moPubStreamAdPlacer);
            this.f20342o.setAdapter(this.f20327U);
            MagicMoPubNativeAdMediatorAdapter.attachInHouseAdListeners((BannerPagerMoPubAdAdapter) this.f20327U, NativeAdPlacementType.SONGBOOK_CAROUSEL, this.f20307A);
        } else {
            this.f20327U = new BannerPagerAdapter(this, getActivity());
            this.f20342o.setAdapter(this.f20327U);
        }
        this.f20342o.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ SongbookFragment f20238a;

            {
                this.f20238a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                    this.f20238a.f20351x.postDelayed(this.f20238a.f20352y, 5000);
                } else {
                    this.f20238a.f20351x.removeCallbacks(this.f20238a.f20352y);
                }
            }
        });
        i = ((MasterActivity) getActivity()).af();
        this.f20342o.setLayoutParams(new LayoutParams(-1, i));
        this.f20341n.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
        m21812M();
        if (!SingApplication.d().c("InitAppTask.OP_RELOAD_SONGBOOK") || StoreManager.m18378a().m18434h()) {
            m21817R();
            SingApplication.d().b("InitAppTask.OP_RELOAD_SONGBOOK");
        } else {
            mo6665d("Load");
        }
        this.f20344q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SongbookFragment f20239a;

            {
                this.f20239a = r1;
            }

            public void onClick(View view) {
                this.f20239a.m21819T();
            }
        });
    }

    private void m21830a(int i, int i2) {
        m21831a(i, i2, false);
    }

    private void m21831a(int i, int i2, boolean z) {
        if ((this.f20321O != this.f20323Q || this.f20322P != this.f20324R || z) && this.f20310D != null && this.f20310D.f20291a != null && m21821V()) {
            int min = Math.min(0, i - this.f20310D.f20291a.f23252a.getHeaderViewsCount()) * -1;
            int headerViewsCount = i - this.f20310D.f20291a.f23252a.getHeaderViewsCount();
            if (min > 0) {
                i2 -= min;
                headerViewsCount = 0;
            }
            if (this.f20310D.f20291a.getSongsAdapter().getCount() > headerViewsCount + i2) {
                StringBuilder stringBuilder = new StringBuilder("");
                StringBuilder stringBuilder2 = new StringBuilder("");
                for (min = 0; min < i2; min++) {
                    Object item = this.f20310D.f20291a.getSongsAdapter().getItem(headerViewsCount + min);
                    if (item instanceof SongbookEntry) {
                        String a = SongbookEntryUtils.m26163a((SongbookEntry) item);
                        if (a != null) {
                            stringBuilder2.append(a);
                            stringBuilder.append(headerViewsCount + min);
                            if (min + 1 < i2) {
                                stringBuilder.append(",");
                                stringBuilder2.append(",");
                            }
                        }
                    }
                }
                this.f20323Q = this.f20321O;
                this.f20324R = this.f20322P;
                Analytics.m17870a(stringBuilder2.toString(), stringBuilder.toString(), RecommendationType.f16179c, RecSysContext.SONGBOOK, m21868H());
            }
        }
    }

    private void m21811L() {
        m21838b(false);
    }

    private void m21838b(boolean z) {
        if (this.f20310D != null && this.f20310D.f20291a != null && this.f20310D.f20291a.f23252a != null) {
            m21831a(0, this.f20310D.f20291a.f23252a.getHeaderViewsCount() + 3, z);
        }
    }

    @SupposeUiThread
    protected void mo6666e(int i) {
        if (this.f20347t == null || this.f20347t.intValue() != i) {
            this.f20310D.f20291a = (SongbookListView) this.f20339l.findViewWithTag("sb_item#" + i);
            if (this.f20310D.f20291a != null) {
                Log.a(f20306e, "setCurrentSectionIndex : Setting to " + i);
                int i2 = this.f20321O;
                int i3 = this.f20322P;
                SongbookSection songbookSection = (SongbookSection) this.f20312F.get(i);
                this.f20310D.f20292b = songbookSection;
                m21833a(songbookSection);
                this.f20310D.m21794a();
                this.f20310D.f20291a.m24487b(this.f20310D.f20294d);
                if (m21821V()) {
                    boolean z;
                    if (!"trending_songs".equals(songbookSection.f23514c)) {
                        m21823X();
                    }
                    boolean z2 = this.f20321O == 0 && this.f20322P == 0;
                    if (this.f20323Q == -1 && this.f20324R == -1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z2) {
                        m21831a(this.f20321O, this.f20322P, true);
                    } else if (z) {
                        m21811L();
                    } else if (this.ad) {
                        m21831a(i2, i3, true);
                    } else {
                        m21838b(true);
                    }
                    this.ad = false;
                }
            }
        }
    }

    void m21875c(String str) {
        if (getActivity() instanceof MasterActivity) {
            ((MasterActivity) getActivity()).c(str);
        }
    }

    String m21865E() {
        if (getActivity() instanceof MasterActivity) {
            return ((MasterActivity) getActivity()).v();
        }
        return null;
    }

    private void m21812M() {
        int i = 0;
        int i2 = (SubscriptionManager.a().b() || !this.f20328V.isEmpty()) ? 0 : 1;
        LinearLayout linearLayout = this.f20343p;
        if (i2 == 0) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        this.f20351x.removeCallbacks(this.f20352y);
        if (!this.f20328V.isEmpty()) {
            this.f20351x.postDelayed(this.f20352y, 5000);
        }
    }

    void m21866F() {
        if (isAdded() && !PromotionManager.m18256a().m18264b()) {
            PromotionManager.m18256a().m18263a(getResources().getString(C1947R.string.screen_type), new BannersCallback(this) {
                final /* synthetic */ SongbookFragment f20240a;

                {
                    this.f20240a = r1;
                }

                public void handleResponse(BannersResponse bannersResponse) {
                    if (this.f20240a.isAdded()) {
                        if (bannersResponse.a() && bannersResponse.banners.size() > 0 && (!bannersResponse.b || this.f20240a.f20330X)) {
                            this.f20240a.f20328V = bannersResponse.banners;
                            this.f20240a.f20327U.notifyDataSetChanged();
                            this.f20240a.f20330X = false;
                        }
                        this.f20240a.m21812M();
                    }
                }
            });
        }
    }

    private void m21813N() {
        final int i = this.d;
        Observer anonymousClass14 = new Observer(this) {
            final /* synthetic */ SongbookFragment f20243b;

            class C41621 implements Runnable {
                final /* synthetic */ AnonymousClass14 f20241a;

                C41621(AnonymousClass14 anonymousClass14) {
                    this.f20241a = anonymousClass14;
                }

                public void run() {
                    if (this.f20241a.f20243b.m19843a(i)) {
                        Log.b(SongbookFragment.f20306e, "App settings updated");
                        if (this.f20241a.f20243b.f20310D.f20291a != null) {
                            this.f20241a.f20243b.f20310D.f20291a.f23252a.invalidateViews();
                        }
                    }
                }
            }

            public void update(Observable observable, Object obj) {
                this.f20243b.m19848b(new C41621(this));
            }
        };
        this.f20316J = anonymousClass14;
        NotificationCenter.m19011a().m19014a("APP_SETTINGS_LOADED_EVENT", anonymousClass14);
        anonymousClass14 = new Observer(this) {
            final /* synthetic */ SongbookFragment f20246b;

            class C41631 implements Runnable {
                final /* synthetic */ AnonymousClass15 f20244a;

                C41631(AnonymousClass15 anonymousClass15) {
                    this.f20244a = anonymousClass15;
                }

                public void run() {
                    if (this.f20244a.f20246b.m19843a(i)) {
                        Log.b(SongbookFragment.f20306e, "Entitlements updated; refreshing list data sources");
                        this.f20244a.f20246b.mo6665d("ENTITLEMENTS_UPDATED_ACTION");
                    }
                }
            }

            public void update(Observable observable, Object obj) {
                if ("ENTITLEMENTS_UPDATED_ACTION".equals((String) ((Map) obj).get(ShareConstants.ACTION))) {
                    this.f20246b.m19848b(new C41631(this));
                }
            }
        };
        this.f20317K = anonymousClass14;
        NotificationCenter.m19011a().m19014a("SONGBOOK_UPDATED_EVENT", anonymousClass14);
        i = this.d;
        SingApplication.d().a("SongbookFragment.OP_UPDATE_SONGBOOK_UI", Collections.singleton("InitAppTask.OP_RELOAD_SONGBOOK"), new Runnable(this) {
            final /* synthetic */ SongbookFragment f20249b;

            class C41641 implements Runnable {
                final /* synthetic */ AnonymousClass16 f20247a;

                C41641(AnonymousClass16 anonymousClass16) {
                    this.f20247a = anonymousClass16;
                }

                public void run() {
                    if (this.f20247a.f20249b.m19843a(i)) {
                        Log.b(SongbookFragment.f20306e, "Songbook sync completed - refreshing list views");
                        this.f20247a.f20249b.mo6665d("SONGBOOK_UPDATED_EVENT");
                    }
                }
            }

            public void run() {
                this.f20249b.m19838a(new C41641(this));
            }
        }).a();
    }

    private void m21814O() {
        NotificationCenter.m19011a().m19016b("APP_SETTINGS_LOADED_EVENT", this.f20316J);
        NotificationCenter.m19011a().m19016b("SONGBOOK_UPDATED_EVENT", this.f20317K);
        SingApplication.d().a("SongbookFragment.OP_UPDATE_SONGBOOK_UI");
    }

    private int m21843f(String str) {
        for (int i = 0; i < this.f20312F.size(); i++) {
            if (((SongbookSection) this.f20312F.get(i)).f23514c.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    private boolean m21845g(String str) {
        if (str == null) {
            return false;
        }
        int f = m21843f(str);
        if (f == -1) {
            return false;
        }
        Log.a(f20306e, "setCurrentTabById : There is a valid section. Setting to " + str);
        this.f20339l.setCurrentItem(f, false);
        return true;
    }

    private void m21815P() {
        if (m21845g(this.f20315I)) {
            this.f20315I = null;
        } else if (!m21845g(m21865E()) && !m21845g(SingServerValues.m21690k())) {
            SongbookSection G = m21867G();
            if (G != null) {
                Log.a(f20306e, "restoreLastSelectedSongbookSection : Popular section found. Setting to " + G.f23517f);
                this.f20339l.setCurrentItem(G.f23517f, false);
            } else if (this.f20312F.size() > 0) {
                Log.a(f20306e, "restoreLastSelectedSongbookSection : Setting current section to 0");
                this.f20339l.setCurrentItem(0, false);
            } else {
                Log.e(f20306e, "onReceive unable to select which StoreSection to show");
            }
        }
    }

    public SongbookSection m21867G() {
        for (SongbookSection songbookSection : this.f20312F) {
            if (songbookSection.f23514c.equals("featured-sing")) {
                return songbookSection;
            }
        }
        return null;
    }

    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(32);
        if (this.f20329W.get()) {
            m21866F();
        }
        m21813N();
        m21816Q();
        this.ad = true;
    }

    private void m21816Q() {
        if (getActivity() != null) {
            getActivity().invalidateOptionsMenu();
        }
    }

    protected boolean mo6450x() {
        return false;
    }

    public void onPause() {
        super.onPause();
        this.f20351x.removeCallbacks(this.f20352y);
        m21814O();
    }

    public void onStart() {
        super.onStart();
        AnalyticsProcessor.b(f20306e);
        if (m21845g(this.f20315I)) {
            this.f20315I = null;
        }
        m19866q();
        m21822W();
    }

    public void onStop() {
        super.onStop();
        boolean z = this.a != null && this.a.m25991a();
        this.f20349v = z;
        if (this.f20349v) {
            MasterToolbar B = ((MasterActivity) getActivity()).B();
            TextView titleView = B.getTitleView();
            TextView preSearchTitleView = B.getPreSearchTitleView();
            ImageView preSearchToolbarNavigationIconView = B.getPreSearchToolbarNavigationIconView();
            preSearchTitleView.setVisibility(8);
            preSearchToolbarNavigationIconView.setVisibility(8);
            preSearchToolbarNavigationIconView.setOnClickListener(null);
            preSearchTitleView.setOnClickListener(null);
            titleView.setVisibility(0);
            titleView.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
    }

    protected void mo6420v() {
        String str;
        if (this.f20314H != null) {
            str = this.f20314H;
        } else {
            str = SingServerValues.m21690k();
        }
        SingAnalytics.m26143d(str);
    }

    private void m21833a(SongbookSection songbookSection) {
        if (songbookSection == null) {
            Log.d(f20306e, "Calling setSelectedStoreSection with a NULL item");
            return;
        }
        SingAnalytics.m26137c(songbookSection.f23514c, null);
        this.f20314H = songbookSection.f23514c;
        this.f20347t = Integer.valueOf(songbookSection.f23517f);
    }

    List<SongbookSection> m21872a(List<StoreSectionV2> list) {
        List arrayList = new ArrayList();
        for (StoreSectionV2 a : list) {
            arrayList.add(SongbookSection.m24756a(a));
        }
        return arrayList;
    }

    protected String m21868H() {
        return this.f20314H;
    }

    public void onDestroyView() {
        this.f20339l.setAdapter(null);
        this.f20342o.setAdapter(null);
        this.f20310D.m21800c();
        this.f20310D = null;
        if (this.f20327U instanceof BannerPagerMoPubAdAdapter) {
            ((BannerPagerMoPubAdAdapter) this.f20327U).m21784a();
        }
        this.f20327U = null;
        if (this.f20350w != null) {
            this.f20350w.m22258c();
        }
        super.onDestroyView();
    }

    protected void m21869I() {
        if (isAdded()) {
            Log.b(f20306e, "retriggerSongbookLoad - Re-triggering songbook load");
            if (this.f20308B >= 3) {
                TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.songbook_unable_to_load_title), getActivity().getString(C1947R.string.songbook_unable_to_load_text), true, false);
                textAlertDialog.m19806a(getActivity().getString(C1947R.string.core_ok), "");
                textAlertDialog.m19804a(new Runnable(this) {
                    final /* synthetic */ SongbookFragment f20250a;

                    {
                        this.f20250a = r1;
                    }

                    public void run() {
                        SingApplication.d().b("InitAppTask.OP_RELOAD_SONGBOOK");
                    }
                });
                textAlertDialog.show();
                this.f20308B = 0;
                return;
            }
            m19839a(new Runnable(this) {
                final /* synthetic */ SongbookFragment f20251a;

                {
                    this.f20251a = r1;
                }

                public void run() {
                    if (!StoreManager.m18378a().m18427d()) {
                        SingApplication.d().b("InitAppTask.OP_RELOAD_SONGBOOK");
                    }
                }
            }, 1000);
            this.f20308B++;
        }
    }

    @SupposeUiThread
    protected void mo6665d(String str) {
        Log.b(f20306e, "refreshListDataSources - called from caller: " + str);
        Log.b(f20306e, "refreshListDataSources - store init complete: " + StoreManager.m18378a().m18427d());
        if (StoreManager.m18378a().m18427d()) {
            boolean z;
            boolean z2;
            String e = EntitlementsManager.m18163a().m18186e();
            String i = StoreManager.m18378a().m18435i();
            if (!str.equals("ENTITLEMENTS_UPDATED_ACTION")) {
                z = this.f20311E != null && this.f20311E.equals(i);
                if (this.f20313G == null || !this.f20313G.equals(e)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (z && r3) {
                    Log.b(f20306e, "refreshListDataSources - signatures have not changed; aborting update of list views");
                    m21871K();
                    return;
                }
                Log.b(f20306e, "refreshListDataSources - signatures do not match; refreshing list views");
                if (this.f20318L) {
                    Log.b(f20306e, "refreshListDataSources - list view refresh already in progress; returning");
                    return;
                }
            }
            this.f20318L = true;
            List a = m21872a(StoreManager.m18378a().m18431f());
            final SongbookSection a2 = SongbookSectionUtil.m26169a(this.f20319M);
            a2.m24757a();
            if (a2.f23512a.size() > 0) {
                a.add(a2);
                if (str.equals("ENTITLEMENTS_UPDATED_ACTION")) {
                    this.f20310D.m21799b(a2);
                }
            }
            Set<String> b = EntitlementsManager.m18163a().m18181b();
            if (a2.f23512a.size() < EntitlementsManager.m18163a().m18183c().size() + b.size() && !SongbookSectionUtils.m19042a().m19051c()) {
                SongbookSectionUtils.m19042a().m19048a(new OnFetchedNonListingOwnedSongs(this) {
                    final /* synthetic */ SongbookFragment f20255b;

                    public void mo6651a(final List<SongbookEntry> list) {
                        this.f20255b.f20351x.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass19 f20253b;

                            public void run() {
                                if (this.f20253b.f20255b.isAdded()) {
                                    if (a2.f23512a != null) {
                                        for (SongbookEntry songbookEntry : list) {
                                            if (!a2.f23513b.contains(songbookEntry.mo6289c())) {
                                                a2.f23512a.add(songbookEntry);
                                                a2.f23513b.add(songbookEntry.mo6289c());
                                                SongbookFragment songbookFragment = this.f20253b.f20255b;
                                                songbookFragment.f20348u++;
                                            }
                                        }
                                    }
                                    this.f20253b.f20255b.f20310D.m21799b(a2);
                                }
                            }
                        });
                    }
                });
                List arrayList = new ArrayList();
                z2 = false;
                for (String str2 : b) {
                    if (a2.f23513b.contains(str2)) {
                        z = z2;
                    } else {
                        arrayList.add(str2);
                        z = true;
                    }
                    z2 = z;
                }
                if (z2) {
                    SongbookSectionUtils.m19042a().m19049a(arrayList);
                }
            }
            this.f20313G = e;
            if (SingServerValues.m21692m()) {
                a.add(SongbookSectionUtil.m26171b(this.f20319M));
            }
            if (a.size() != 0) {
                a.add(0, SongbookSectionUtil.m26172c(this.f20319M));
            }
            if (a.size() != 0) {
                a.add(0, SongbookSectionUtil.m26173d(this.f20319M));
            }
            Collections.sort(a, new SongbookSectionComparatorByOrder());
            for (int i2 = 0; i2 < a.size(); i2++) {
                ((SongbookSection) a.get(i2)).f23517f = i2;
            }
            Log.b(f20306e, "refreshListDataSources - there are " + a.size() + " store sections");
            if (a.size() > 2 && UserManager.a().z()) {
                this.f20311E = i;
            }
            this.f20312F = a;
            this.f20310D.f20295e = true;
            this.f20310D.notifyDataSetChanged();
            this.f20310D.f20295e = false;
            m21815P();
            if (this.f20312F.toArray().length > 2) {
                m21871K();
            }
            m21866F();
            mo6664J();
            this.f20318L = false;
            this.f20329W.set(true);
            Log.b(f20306e, "refreshListDataSources - done");
            return;
        }
        Log.b(f20306e, "refreshListDataSources - store init is not complete yet; returning");
        m21869I();
    }

    @UiThread
    void mo6664J() {
        if (getActivity() != null && isAdded()) {
            boolean c = AdUtils.m22228c(getActivity());
            Log.b(f20306e, "showOnLaunchAdIfFirstSongbookLoad: hasShownLaunchAd = " + c);
            if (!c) {
                AdUtils.m22221a(getActivity(), true);
                this.f20350w = new OnLaunchAd();
                this.f20350w.m22251a(getActivity(), null, new HashMap());
                this.f20350w.m22254a(getActivity());
            }
        }
    }

    public void m21880e(String str) {
        this.f20315I = str;
        m21875c(this.f20315I);
        if (isAdded() && m21845g(this.f20315I)) {
            this.f20315I = null;
        }
    }

    private void m21817R() {
        this.f20333f.setVisibility(0);
    }

    protected void m21871K() {
        this.f20333f.setVisibility(8);
        SingAnalytics.m26089a(this.f20314H != null ? this.f20314H : SingServerValues.m21690k());
    }

    protected void m21874a(ListingListItem listingListItem) {
        if (isAdded()) {
            listingListItem.setAlbumArtClickedState(false);
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.arrangement_copyright_violation_title), MessageFormat.format(getString(C1947R.string.arrangement_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}), true, false);
            textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
            textAlertDialog.show();
        }
    }

    private String m21836b(SongbookEntry songbookEntry) {
        if (!songbookEntry.m18772r()) {
            return songbookEntry.mo6290d();
        }
        ArrangementVersionLiteEntry arrangementVersionLiteEntry = (ArrangementVersionLiteEntry) songbookEntry;
        return arrangementVersionLiteEntry.m18777b() ? "-" : arrangementVersionLiteEntry.f17623a.songId;
    }

    private void m21818S() {
        this.f20344q.setVisibility(0);
        this.f20345r.setVisibility(0);
    }

    private void m21819T() {
        if (this.f20344q.getVisibility() == 0) {
            this.f20344q.setVisibility(8);
            this.f20345r.setVisibility(8);
            this.f20326T = false;
            SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("SHOW_COACH_MARK_CCCP", false).apply();
        }
    }

    private void m21820U() {
        this.f20344q.m23312a(this.f20338k.getLeft(), this.f20338k.getRight(), this.f20338k.getTop() + ((int) this.f20340m.getTranslationY()), this.f20338k.getBottom() + ((int) this.f20340m.getTranslationY()));
        this.f20345r.setY((float) (this.f20338k.getBottom() + ((int) this.f20340m.getTranslationY())));
        View currentTab = this.f20338k.getCurrentTab();
        if (currentTab != null && this.f20344q.getVisibility() != 0) {
            int[] iArr = new int[2];
            currentTab.getLocationOnScreen(iArr);
            this.f20346s.setX((float) ((currentTab.getWidth() / 2) + iArr[0]));
        }
    }

    private boolean m21821V() {
        return (this.f20310D == null || this.f20310D.f20291a == null || !this.f20310D.f20291a.getSongsAdapter().mo6705q()) ? false : true;
    }

    private void m21822W() {
        MasterToolbar B = ((MasterActivity) getActivity()).B();
        TextView titleView = B.getTitleView();
        TextView preSearchTitleView = B.getPreSearchTitleView();
        ImageView preSearchToolbarNavigationIconView = B.getPreSearchToolbarNavigationIconView();
        if (this.f20349v) {
            preSearchTitleView.setVisibility(0);
            preSearchToolbarNavigationIconView.setVisibility(0);
            preSearchToolbarNavigationIconView.setOnClickListener(this.f20332Z);
            preSearchTitleView.setOnClickListener(this.f20332Z);
            titleView.setVisibility(8);
            titleView.setAlpha(0.0f);
            return;
        }
        preSearchTitleView.setVisibility(8);
        preSearchToolbarNavigationIconView.setVisibility(8);
        preSearchToolbarNavigationIconView.setOnClickListener(null);
        preSearchTitleView.setOnClickListener(null);
        titleView.setVisibility(0);
        titleView.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    private void m21823X() {
        if (this.f20310D.f20291a.f23252a.getFooterViewsCount() < 1) {
            View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C1947R.layout.search_exposure_banner, null, false);
            inflate.setOnClickListener(this.aa);
            this.f20310D.f20291a.f23252a.addFooterView(inflate);
        }
    }
}
