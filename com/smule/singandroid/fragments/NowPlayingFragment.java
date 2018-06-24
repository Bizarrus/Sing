package com.smule.singandroid.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Analytics.PerformanceStatus;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI$RenderType;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$IsBookmarkSeedResponseCallback;
import com.smule.android.network.managers.PerformanceManager$PerformanceLovesResponseCallback;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager$PerformancesByPerformerResponseCallback;
import com.smule.android.network.managers.PerformanceManager.IsBookmarkSeedResponse;
import com.smule.android.network.managers.PerformanceManager.PerformanceLovesResponse;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.LocationUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.StringCacheManager;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.MediaPlayingFragment;
import com.smule.singandroid.PerformanceSaveFragment;
import com.smule.singandroid.PerformanceSaveFragmentFactory;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.adapters.profile.ProfileFavoritesDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.SquareTextureView;
import com.smule.singandroid.dialogs.BookmarkDialog.BookmarkNonSeedException;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.CommentsListDialog;
import com.smule.singandroid.dialogs.CommentsListDialog.CommentsListDialogListener;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.hashtag.Hashtag.HashtagCallback;
import com.smule.singandroid.list_items.CommentItem;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.FractionalRelativeLayout;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.ShareUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.SwipeDismissTouchListener;
import com.smule.singandroid.utils.SwipeDismissTouchListener.DismissCallbacks;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.IgnoreWhen;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class NowPlayingFragment extends MediaPlayingFragment implements UserFollowItemListener {
    private static final String aP = NowPlayingFragment.class.getName();
    @ViewById
    protected View f22493G;
    @ViewById
    protected TextView f22494H;
    @ViewById
    protected View f22495I;
    @ViewById
    protected View f22496J;
    @ViewById
    protected TextView f22497K;
    @ViewById
    protected ProgressBar f22498L;
    @ViewById
    protected Button f22499M;
    @ViewById
    ImageView f22500N;
    @ViewById
    protected ImageView f22501O;
    @ViewById
    protected View f22502P;
    @ViewById
    protected TextView f22503Q;
    @ViewById
    protected TextView f22504R;
    @ViewById
    protected View f22505S;
    @ViewById
    protected View f22506T;
    @ViewById
    protected ProfileImageWithVIPBadge f22507U;
    @ViewById
    protected TextView f22508V;
    @ViewById
    protected View f22509W;
    @ViewById
    protected View f22510X;
    @ViewById
    protected ProfileImageWithVIPBadge f22511Y;
    @ViewById
    protected TextView f22512Z;
    @InstanceState
    protected boolean aA;
    @InstanceState
    protected String aB;
    @InstanceState
    protected ListingV2 aC;
    @FragmentArg
    protected boolean aD;
    @FragmentArg
    protected SearchTarget aE;
    @FragmentArg
    protected int aF;
    @InstanceState
    protected boolean aG;
    @InstanceState
    protected boolean aH = false;
    @ViewById
    protected View aI;
    @ViewById
    protected SquareTextureView aJ;
    @ViewById
    protected View aK;
    @ViewById
    protected View aL;
    @ViewById
    protected View aM;
    @ViewById
    protected LinearLayout aN;
    protected OnGlobalLayoutListener aO = new OnGlobalLayoutListener(this, new C45495(this));
    private final int aQ = 25;
    private Mode aR = Mode.MINIMIZED;
    private boolean aS;
    private int aT;
    private NowPlayingFragmentMenuSelectedCallback aU;
    private int aV = 0;
    private int aW = 0;
    private int aX = 0;
    private boolean aY = false;
    private boolean aZ = false;
    @ViewById
    protected TextView aa;
    @ViewById
    protected View ab;
    @ViewById
    protected TextView ac;
    @ViewById
    protected TextView ad;
    @ViewById
    protected TextView ae;
    @ViewById
    protected TextView af;
    @ViewById
    protected ImageButton ag;
    @ViewById
    protected ImageButton ah;
    @ViewById
    protected ImageButton ai;
    @ViewById
    protected ImageButton aj;
    @ViewById
    protected View ak;
    @ViewById
    CustomToolbar al;
    @ViewById
    protected ListView am;
    @ViewById
    protected TextView an;
    @ViewById
    protected ImageButton ao;
    @ViewById
    protected View ap;
    @ViewById
    CustomToolbar aq;
    @ViewById
    protected ListView ar;
    @ViewById
    protected TextView as;
    @ViewById
    protected ProfileImageWithVIPBadge at;
    @ViewById
    protected View au;
    @ViewById
    protected View av;
    @ViewById
    ProgressBar aw;
    protected ArrayList<PerformancePost> ax;
    @FragmentArg
    protected PerformanceV2 ay;
    @InstanceState
    protected boolean az;
    private boolean ba = false;
    private List<Track> bb;
    private boolean bc = false;
    private boolean bd = false;
    private AlertDialog be;
    private CommentsListDialog bf;
    private LocalizedShortNumberFormatter bg;
    private boolean bh;
    private boolean bi;
    private boolean bj = false;
    private boolean bk = false;
    private Long bl = Long.valueOf(-1);
    private boolean bm = false;
    private boolean bn = false;
    private AnimatorListener bo = new C45411(this);
    private Observer bp = new Observer(this) {
        final /* synthetic */ NowPlayingFragment f22437a;

        {
            this.f22437a = r1;
        }

        public void update(Observable observable, Object obj) {
            Log.b(NowPlayingFragment.aP, "mBookmarkFavoriteStatusObserver - called");
            if (obj instanceof HashMap) {
                HashMap hashMap = (HashMap) obj;
                if (hashMap.containsKey("FAVORITED_PERFORMANCE") && ((PerformanceV2) hashMap.get("FAVORITED_PERFORMANCE")).performanceKey.equals(this.f22437a.ay.performanceKey)) {
                    this.f22437a.bh = true;
                    this.f22437a.az();
                    this.f22437a.m19846b((int) C1947R.string.favorite_added);
                }
                if (hashMap.containsKey("UNFAVORITED_PERFORMANCE") && ((PerformanceV2) hashMap.get("UNFAVORITED_PERFORMANCE")).performanceKey.equals(this.f22437a.ay.performanceKey)) {
                    this.f22437a.bh = false;
                    this.f22437a.az();
                    this.f22437a.m19846b((int) C1947R.string.favorite_removed);
                }
                if (hashMap.containsKey("BOOKMARKED_PERFORMANCE") && ((PerformanceV2) hashMap.get("BOOKMARKED_PERFORMANCE")).performanceKey.equals(this.f22437a.ay.performanceKey)) {
                    this.f22437a.bi = true;
                    this.f22437a.aA();
                    this.f22437a.m19846b((int) C1947R.string.bookmark_added);
                }
                if (hashMap.containsKey("UNBOOKMARKED_PERFORMANCE") && ((PerformanceV2) hashMap.get("UNBOOKMARKED_PERFORMANCE")).performanceKey.equals(this.f22437a.ay.performanceKey)) {
                    this.f22437a.bi = false;
                    this.f22437a.aA();
                    this.f22437a.m19846b((int) C1947R.string.bookmark_removed);
                }
            }
        }
    };
    private final String bq = "FAVORITE_ID";
    private final String br = "BOOKMARK_ID";
    private ArrayList<String> bs;
    private ArrayList<String> bt;
    private ArrayAdapter<String> bu;
    private BaseAdapter bv = new BaseAdapter(this) {
        final /* synthetic */ NowPlayingFragment f22457a;

        {
            this.f22457a = r1;
        }

        public int getCount() {
            return this.f22457a.ax != null ? this.f22457a.ax.size() : 0;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (this.f22457a.ax.size() < i) {
                Log.d(NowPlayingFragment.aP, "getView - mLovesArray is not large enough; returning empty view");
                return new View(this.f22457a.getActivity());
            }
            return this.f22457a.m23851a(view, ((PerformancePost) this.f22457a.ax.get(i)).accountIcon, 0, false);
        }
    };
    private BaseAdapter bw = new BaseAdapter(this) {
        final /* synthetic */ NowPlayingFragment f22458a;

        {
            this.f22458a = r1;
        }

        public int getCount() {
            return this.f22458a.bb.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (this.f22458a.bb.size() < i) {
                Log.d(NowPlayingFragment.aP, "getView - recentTracks is not large enough; returning empty view");
                return new View(this.f22458a.getActivity());
            }
            Track track = (Track) this.f22458a.bb.get(i);
            return this.f22458a.m23851a(view, track.accountIcon, track.createdAt, true);
        }
    };

    public interface NowPlayingFragmentMenuSelectedCallback {
        boolean mo6745a(MenuItem menuItem);
    }

    class C45411 implements AnimatorListener {
        final /* synthetic */ NowPlayingFragment f22424a;

        C45411(NowPlayingFragment nowPlayingFragment) {
            this.f22424a = nowPlayingFragment;
        }

        public void onAnimationStart(Animator animator) {
            this.f22424a.bn = true;
        }

        public void onAnimationEnd(Animator animator) {
            this.f22424a.bn = false;
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    class C45453 implements NetworkResponseCallback {
        final /* synthetic */ NowPlayingFragment f22460a;

        C45453(NowPlayingFragment nowPlayingFragment) {
            this.f22460a = nowPlayingFragment;
        }

        public void handleResponse(NetworkResponse networkResponse) {
        }
    }

    class C45495 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ NowPlayingFragment f22483a;

        C45495(NowPlayingFragment nowPlayingFragment) {
            this.f22483a = nowPlayingFragment;
        }

        public void onGlobalLayout() {
            if (this.f22483a.isAdded()) {
                LayoutUtils.m25859b(this.f22483a.t, this.f22483a.aO);
                this.f22483a.aV = -this.f22483a.aN.getHeight();
                this.f22483a.aW = (this.f22483a.aV + this.f22483a.getResources().getDimensionPixelSize(C1947R.dimen.profile_large_vip)) + this.f22483a.getResources().getDimensionPixelSize(C1947R.dimen.margin_small);
                this.f22483a.aX = this.f22483a.aW / 2;
                int top = ((this.f22483a.aM.getTop() - this.f22483a.aV) - this.f22483a.getResources().getDimensionPixelSize(C1947R.dimen.margin_small)) - this.f22483a.aJ.getTop();
                if (this.f22483a.bm) {
                    this.f22483a.aJ.setY(0.0f);
                } else {
                    this.f22483a.aJ.setTranslationY((float) this.f22483a.aX);
                }
                int width = this.f22483a.aJ.getWidth();
                if (width > top) {
                    this.f22483a.aJ.setWidth(top);
                    this.f22483a.aJ.requestLayout();
                    return;
                }
                this.f22483a.aX = this.f22483a.aX - ((top - width) / 2);
            }
        }
    }

    class C45506 implements OnDismissListener {
        final /* synthetic */ NowPlayingFragment f22484a;

        C45506(NowPlayingFragment nowPlayingFragment) {
            this.f22484a = nowPlayingFragment;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (this.f22484a.isAdded()) {
                if (this.f22484a.q != null) {
                    this.f22484a.q.m24665e();
                }
                if (this.f22484a.getActivity() != null) {
                    ((MediaPlayingActivity) this.f22484a.getActivity()).c(false);
                }
            }
        }
    }

    class C45517 implements OnCancelListener {
        final /* synthetic */ NowPlayingFragment f22485a;

        C45517(NowPlayingFragment nowPlayingFragment) {
            this.f22485a = nowPlayingFragment;
        }

        public void onCancel(DialogInterface dialogInterface) {
            if (this.f22485a.isAdded()) {
                if (this.f22485a.q != null) {
                    this.f22485a.q.m24665e();
                }
                if (this.f22485a.getActivity() != null) {
                    ((MediaPlayingActivity) this.f22485a.getActivity()).c(false);
                }
            }
        }
    }

    class C45528 implements OnClickListener {
        final /* synthetic */ NowPlayingFragment f22486a;

        C45528(NowPlayingFragment nowPlayingFragment) {
            this.f22486a = nowPlayingFragment;
        }

        public void onClick(View view) {
            this.f22486a.ar();
        }
    }

    class C45539 implements OnClickListener {
        final /* synthetic */ NowPlayingFragment f22487a;

        C45539(NowPlayingFragment nowPlayingFragment) {
            this.f22487a = nowPlayingFragment;
        }

        public void onClick(View view) {
            if (this.f22487a.aS || (this.f22487a.ay != null && this.f22487a.ay.t())) {
                ((BaseActivity) this.f22487a.getActivity()).a(this.f22487a.aT, true, null);
            } else {
                ShareUtils.m26005a(this.f22487a.getActivity(), this.f22487a.ay);
            }
        }
    }

    enum Mode {
        MINIMIZED,
        MAXIMIZED,
        JOINERS_VIEW,
        LOVES_VIEW
    }

    public static NowPlayingFragment m23853a(PerformanceV2 performanceV2, boolean z, boolean z2, SearchTarget searchTarget, boolean z3, int i, NowPlayingFragmentMenuSelectedCallback nowPlayingFragmentMenuSelectedCallback) {
        NowPlayingFragment a = NowPlayingFragment_.aa().m23949a(performanceV2).m23950a(z).m23952c(z3).m23951b(z2).m23948a(searchTarget).m23947a(i).m23946a();
        a.aU = nowPlayingFragmentMenuSelectedCallback;
        return a;
    }

    public String mo6383s() {
        return aP;
    }

    public PerformanceV2 m23905K() {
        if (this.ay != null) {
            return this.ay;
        }
        Log.d(aP, "getPerformance - returning null!");
        return null;
    }

    public String m23906L() {
        return this.ay != null ? this.ay.performanceKey : null;
    }

    protected boolean mo6543a() {
        return this.bd;
    }

    private void aa() {
        if (this.aC != null) {
            Log.b(aP, "setListingAndProductInfo: listing and song are already not null; aborting");
            return;
        }
        String str = null;
        if (this.ay != null) {
            str = this.ay.songUid;
        } else {
            Log.e(aP, "setListingAndProductInfo: unable to fetch the song UID for playing song");
        }
        if (str != null) {
            boolean z;
            this.aC = StoreManager.m18378a().m18428e(str);
            if (this.aC == null || !this.aC.a()) {
                z = false;
            } else {
                z = true;
            }
            this.aG = z;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.A = false;
        m19842a(this.aD);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(this.aF, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.aU != null) {
            return this.aU.mo6745a(menuItem);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onStart() {
        super.onStart();
        if (this.ay != null) {
            m23908N();
        }
        NotificationCenter.m19011a().m19014a("PERFORMANCE_UPDATED_NOTIFICATION", this.bp);
    }

    public void onResume() {
        super.onResume();
        if (m20373F()) {
            m23910P();
        }
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(35);
        }
    }

    public void onStop() {
        super.onStop();
        ab();
        NotificationCenter.m19011a().m19016b("PERFORMANCE_UPDATED_NOTIFICATION", this.bp);
    }

    public void onDestroyView() {
        super.onDestroyView();
        LayoutUtils.m25859b(this.t, this.aO);
    }

    private void ab() {
        this.bc = false;
        if (this.be != null && this.be.isShowing()) {
            this.be.dismiss();
            this.be = null;
        }
    }

    @Click
    protected void m23907M() {
        if (!mo6543a()) {
            return;
        }
        if ((this.ay == null || !this.ay.t()) && !this.bn && !this.aS && this.aM != null && this.aN != null && this.f22505S != null && this.aJ != null) {
            boolean z;
            if (this.bm) {
                this.aM.animate().setListener(this.bo).yBy((float) this.aW);
                this.f22505S.animate().setListener(this.bo).yBy((float) this.aV);
                this.aN.animate().setListener(this.bo).yBy((float) this.aV);
                this.aJ.animate().setListener(this.bo).yBy((float) this.aX);
            } else {
                this.aM.animate().setListener(this.bo).yBy((float) (-this.aW));
                this.f22505S.animate().setListener(this.bo).yBy((float) (-this.aV));
                this.aN.animate().setListener(this.bo).yBy((float) (-this.aV));
                this.aJ.animate().setListener(this.bo).yBy((float) (-this.aX));
            }
            if (this.bm) {
                z = false;
            } else {
                z = true;
            }
            this.bm = z;
        }
    }

    private LocalizedShortNumberFormatter ac() {
        if (this.bg == null) {
            this.bg = new LocalizedShortNumberFormatter(getActivity());
        }
        return this.bg;
    }

    protected void mo6420v() {
        switch (this.aR) {
            case MAXIMIZED:
                SingAnalytics.m26149e(PerformanceV2Util.m25947e(this.ay), SingAnalytics.m26140d(this.ay));
                return;
            case LOVES_VIEW:
                SingAnalytics.m26135c(PerformanceV2Util.m25947e(this.ay));
                return;
            default:
                return;
        }
    }

    protected boolean mo6833f(int i) {
        final PerformanceV2 K = m23905K();
        if (K == null || K.performanceKey == null) {
            Log.e(aP, "logListen: performance is null so unable to report listen");
            return true;
        }
        int i2;
        if (K.s()) {
            if (!(this.aC == null || this.aC.song == null)) {
                i2 = this.aC.song.length;
            }
            i2 = -1;
        } else {
            if (K.arrangementVersion != null) {
                i2 = K.arrangementVersion.length;
            }
            i2 = -1;
        }
        boolean z = i2 == -1 ? ((float) i) / ((float) this.q.m24667f()) > 0.2f : ((float) i) / ((float) (i2 * 1000)) > 0.2f;
        if (!z) {
            return false;
        }
        float latitude;
        float longitude;
        Log.b(aP, "logListen: beginning logic to call v2/performance/listen");
        Location a = LocationUtils.m19000a();
        if (a != null) {
            latitude = (float) a.getLatitude();
        } else {
            latitude = Float.NaN;
        }
        if (a != null) {
            longitude = (float) a.getLongitude();
        } else {
            longitude = Float.NaN;
        }
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ NowPlayingFragment f22442d;

            public void run() {
                PerformanceManager.a().a(K.performanceKey, latitude, longitude);
                Log.b(NowPlayingFragment.aP, "logListen: done reporting via v2/performance/listen API");
            }
        });
        return true;
    }

    public void m23908N() {
        boolean z = true;
        Log.b(aP, "loadPerformance() called");
        if (this.bc || this.q.m24662c(this.ay.performanceKey)) {
            Log.b(aP, "Performance with id, " + this.ay.performanceKey + ", is already playing; no more setup required");
            return;
        }
        this.aZ = false;
        this.ba = false;
        this.bd = MiscUtils.m25895a(this.ay);
        if (this.ay == null) {
            Log.e(aP, "Something has gone terribly wrong!");
            return;
        }
        boolean c;
        if (mo6543a()) {
            c = this.ay.c();
        } else {
            c = this.ay.b();
        }
        if (!c) {
            Log.b(aP, "Triggering performance render : " + this.ay + " / " + this.ay.b() + " / " + this.ay.c());
            PerformanceManager.a().a(this.ay.performanceKey, PerformancesAPI$RenderType.MAIN, new C45453(this));
        }
        boolean z2 = this.ay.r() && this.ay.arrangementVersion == null;
        if (!c || z2) {
            z = false;
        }
        if (z) {
            Log.b(aP, "Performance with id, " + this.ay.performanceKey + ", is already rendered; starting playback before fetching details");
            mo6834O();
        }
        Log.b(aP, "Fetching performance with id: " + this.ay.performanceKey);
        MediaPlayerServiceController.m24641a().m24654a(this.ay.performanceKey);
        final int i = this.d;
        PerformanceManager.a().a(this.ay.performanceKey, new PerformanceManager$PerformanceResponseCallback(this) {
            final /* synthetic */ NowPlayingFragment f22479c;

            class C45461 implements Runnable {
                final /* synthetic */ C45484 f22461a;

                C45461(C45484 c45484) {
                    this.f22461a = c45484;
                }

                public void run() {
                    this.f22461a.f22479c.mo6830a(this.f22461a.f22479c);
                }
            }

            class C45472 implements Runnable {
                final /* synthetic */ C45484 f22462a;

                C45472(C45484 c45484) {
                    this.f22462a = c45484;
                }

                public void run() {
                    this.f22462a.f22479c.mo6839a(this.f22462a.f22479c.getActivity(), this.f22462a.f22479c.ay.performanceKey);
                }
            }

            public void handleResponse(PerformanceResponse performanceResponse) {
                if (this.f22479c.m19843a(i)) {
                    if (performanceResponse.a() && performanceResponse.mPerformance != null && !performanceResponse.mPerformance.t()) {
                        this.f22479c.aZ = true;
                        this.f22479c.ay = performanceResponse.mPerformance;
                        this.f22479c.aS = false;
                        this.f22479c.aB = this.f22479c.ay.webUrl;
                        if (!z) {
                            this.f22479c.mo6834O();
                        }
                        this.f22479c.al();
                    } else if (performanceResponse.a.e()) {
                        this.f22479c.aS = true;
                        this.f22479c.aT = performanceResponse.a.f;
                    } else if (performanceResponse.a.c() && performanceResponse.mPerformance != null && performanceResponse.mPerformance.t()) {
                        this.f22479c.aS = true;
                        this.f22479c.aT = performanceResponse.mPerformance.removalCode;
                    } else {
                        this.f22479c.aG();
                        MediaPlayerServiceController.m24641a().m24660b(this.f22479c.ay.performanceKey);
                    }
                    if (this.f22479c.aS) {
                        Runnable c45472;
                        MediaPlayerServiceController.m24641a().m24665e();
                        BaseActivity baseActivity = (BaseActivity) this.f22479c.getActivity();
                        int e = this.f22479c.aT;
                        Runnable c45461 = new C45461(this);
                        if (this.f22479c.ay.m()) {
                            c45472 = new C45472(this);
                        } else {
                            c45472 = null;
                        }
                        baseActivity.a(e, true, c45461, c45472);
                    }
                    this.f22479c.bc = true;
                }
            }
        });
    }

    @UiThread
    protected void mo6834O() {
        if (!isAdded()) {
            return;
        }
        if (this.ay == null || this.ay.a()) {
            aa();
            ap();
            ao();
            Log.b(aP, "showAndStartPerformance - shouldPlayVideo? " + mo6543a());
            if (mo6543a()) {
                this.aJ.setVisibility(0);
                this.aK.setVisibility(0);
                this.aL.setVisibility(0);
                this.aI.setVisibility(8);
                if (m20373F()) {
                    m23910P();
                }
            } else {
                this.aJ.setVisibility(8);
                this.aK.setVisibility(8);
                this.aL.setVisibility(8);
                this.aI.setVisibility(0);
                ak();
            }
            if ((this.ay == null || !this.ay.t()) && !this.aS) {
                m20394z();
            } else {
                ad();
            }
            aq();
            al();
            ai();
            ag();
            ah();
            ae();
            af();
            this.f22495I.setVisibility(4);
            this.f22496J.setVisibility(0);
            LayoutUtils.m25854a(this.t, this.aO);
            return;
        }
        TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.recording_not_ready), getString(C1947R.string.recording_not_ready_detail), true, false);
        textAlertDialog.setOnDismissListener(new C45506(this));
        textAlertDialog.setOnCancelListener(new C45517(this));
        textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
        textAlertDialog.show();
    }

    private void ad() {
        this.av.setVisibility(8);
        ((LayoutParams) this.aN.getLayoutParams()).setMargins(0, 0, 0, 0);
    }

    public void m23910P() {
        Log.b(aP, "configureVideoSurfaceIfNecessary");
        if (mo6543a() && this.ay != null && this.ay.c()) {
            this.q.m24650a(getActivity(), this.aJ, this.aK, this.aL);
        }
    }

    private void ae() {
        this.am.setAdapter(this.bv);
        this.ac.setOnClickListener(new C45528(this));
        m23882g(false);
        this.at.setAccount(UserManager.a().O());
    }

    private void af() {
        if (this.ay != null) {
            this.bb = new ArrayList(this.ay.recentTracks);
            for (int size = this.bb.size() - 1; size >= 0; size--) {
                if (((Track) this.bb.get(size)).accountIcon.accountId == this.ay.accountIcon.accountId) {
                    this.bb.remove(size);
                    break;
                }
            }
            this.ar.setAdapter(this.bw);
            mo6837S();
        }
    }

    private void ag() {
        this.ae.setText(getResources().getQuantityString(C1947R.plurals.plays_count, this.ay.totalListens, new Object[]{ac().m18999a((long) this.ay.totalListens, (long) getResources().getInteger(C1947R.integer.long_form_threshold))}));
        this.af.setText(MiscUtils.m25886a((long) this.ay.createdAt, false, true));
        this.ac.setMaxWidth((this.ab.getWidth() / 2) - ((int) (getResources().getDimension(C1947R.dimen.margin_large) / 2.0f)));
    }

    private void ah() {
        this.ag.setOnClickListener(new C45539(this));
        this.ah.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22413a;

            {
                this.f22413a = r1;
            }

            public void onClick(View view) {
                this.f22413a.m23881g("");
            }
        });
        this.ai.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22414a;

            {
                this.f22414a = r1;
            }

            public void onClick(View view) {
                this.f22414a.m23914T();
            }
        });
        this.ao.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22415a;

            {
                this.f22415a = r1;
            }

            public void onClick(View view) {
                this.f22415a.m23914T();
            }
        });
        this.aj.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22416a;

            {
                this.f22416a = r1;
            }

            public void onClick(View view) {
                this.f22416a.mo6838U();
            }
        });
    }

    private void ai() {
        if (this.ay.message == null || this.ay.message.length() <= 0) {
            this.aa.setVisibility(8);
            return;
        }
        this.aa.setVisibility(0);
        aj();
    }

    private void aj() {
        SpannableString spannableString = new SpannableString(this.ay.message);
        Hashtag.m24194a((MediaPlayingActivity) getActivity(), spannableString);
        this.aa.setMovementMethod(new LinkMovementMethod());
        this.aa.setText(spannableString);
        this.aa.setHighlightColor(0);
    }

    private void ak() {
        ImageUtils.a(this.ay.coverUrl, this.f22501O, new SimpleImageLoadingListener(this) {
            final /* synthetic */ NowPlayingFragment f22417a;

            {
                this.f22417a = r1;
            }

            public void mo6155a(String str, View view, Bitmap bitmap) {
                if (this.f22417a.isAdded() && bitmap != null) {
                    int width = (int) (((double) bitmap.getWidth()) * 0.025d);
                    int height = (int) (((double) bitmap.getHeight()) * 0.025d);
                    if (width > 0 && height > 0) {
                        this.f22417a.f22500N.setImageDrawable(new BitmapDrawable(this.f22417a.getResources(), Bitmap.createScaledBitmap(bitmap, width, height, true)));
                    }
                }
            }
        });
    }

    private void al() {
        PerformanceV2Util.m25939a(getResources(), this.f22508V, this.ay.accountIcon);
        OnClickListener anonymousClass15 = new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22418a;

            {
                this.f22418a = r1;
            }

            public void onClick(View view) {
                this.f22418a.m23857a(this.f22418a.ay.accountIcon);
            }
        };
        this.f22507U.m23395a(this.ay.accountIcon, anonymousClass15);
        this.f22508V.setOnClickListener(anonymousClass15);
        this.f22510X.setVisibility(8);
        this.f22509W.setVisibility(8);
        if (this.ay.recentTracks != null && this.ay.recentTracks.size() >= 1) {
            if (this.ay.n()) {
                if (this.ay.e()) {
                    an();
                } else {
                    m23878f(getString(this.ay.origTrackPartId == 2 ? C1947R.string.pre_singing_part1 : C1947R.string.pre_singing_part2));
                }
            } else if (PerformanceV2Util.m25944b(this.ay)) {
                m23862a(getResources().getQuantityString(C1947R.plurals.duet_count, this.ay.childCount, new Object[]{ac().m18999a((long) this.ay.childCount, (long) getResources().getInteger(C1947R.integer.long_form_threshold))}), this.ay.childCount);
                this.f22511Y.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ NowPlayingFragment f22419a;

                    {
                        this.f22419a = r1;
                    }

                    public void onClick(View view) {
                        this.f22419a.mo6487a(OpenCallPerformancesListFragment.m24071a(this.f22419a.ay));
                        this.f22419a.m23918X();
                    }
                });
            } else if (this.ay.d()) {
                this.f22510X.setVisibility(0);
                this.f22509W.setVisibility(0);
                final AccountIcon accountIcon = ((Track) this.ay.recentTracks.get(0)).accountIcon;
                PerformanceV2Util.m25939a(getResources(), this.f22512Z, accountIcon);
                OnClickListener anonymousClass17 = new OnClickListener(this) {
                    final /* synthetic */ NowPlayingFragment f22421b;

                    public void onClick(View view) {
                        this.f22421b.m23857a(accountIcon);
                    }
                };
                this.f22511Y.m23395a(accountIcon, anonymousClass17);
                this.f22511Y.setPerformanceCount(-1);
                this.f22512Z.setOnClickListener(anonymousClass17);
            } else if (this.ay.e()) {
                an();
            }
            af();
            am();
            this.ad.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NowPlayingFragment f22422a;

                {
                    this.f22422a = r1;
                }

                public void onClick(View view) {
                    this.f22422a.m23881g("");
                }
            });
        }
    }

    private void am() {
        if (this.ay.totalComments > 0) {
            int i = this.ay.totalComments;
            this.ad.setText(getActivity().getResources().getQuantityString(C1947R.plurals.comments_count, i, new Object[]{ac().m18999a((long) i, (long) getActivity().getResources().getInteger(C1947R.integer.long_form_threshold))}));
            return;
        }
        this.ad.setText(C1947R.string.now_playing_be_the_first_to_comment);
    }

    private void m23857a(AccountIcon accountIcon) {
        mo6487a(ProfileFragment.m21036a(accountIcon));
        m23918X();
    }

    private void m23878f(String str) {
        this.f22510X.setVisibility(0);
        this.f22509W.setVisibility(0);
        this.f22512Z.setText(str);
        this.f22511Y.setVIP(false);
        this.f22510X.setClickable(false);
        this.f22511Y.setClickable(false);
        this.f22511Y.setPerformanceText(str);
    }

    private void m23862a(String str, int i) {
        m23878f(str);
        this.f22511Y.setPerformanceCount(i);
    }

    private void an() {
        this.f22510X.setVisibility(0);
        this.f22509W.setVisibility(0);
        int i = this.ay.totalPerformers - 1;
        this.f22511Y.setVIP(false);
        this.f22511Y.setPerformanceCount(i);
        this.f22512Z.setText(getResources().getQuantityString(C1947R.plurals.other_count, i, new Object[]{ac().m18999a((long) i, (long) getResources().getInteger(C1947R.integer.long_form_threshold))}));
        if (i > 0) {
            OnClickListener anonymousClass19 = new OnClickListener(this) {
                final /* synthetic */ NowPlayingFragment f22423a;

                {
                    this.f22423a = r1;
                }

                public void onClick(View view) {
                    Log.c(NowPlayingFragment.aP, "mSecondPerformerImageView clicked!!!");
                    this.f22423a.as();
                }
            };
            this.f22511Y.setOnClickListener(anonymousClass19);
            this.f22512Z.setOnClickListener(anonymousClass19);
        }
    }

    private void ao() {
        if (this.ay != null) {
            if (!TextUtils.isEmpty(aE())) {
                this.e.mo6782a(null, this.ay);
                this.al.mo6782a(null, this.ay);
                this.aq.mo6782a(null, this.ay);
            }
            if (this.ay.t() || this.aS) {
                this.e.m23172c();
                this.f22502P.setVisibility(0);
                if (this.aS) {
                    Pair a = MiscUtils.m25880a(this.aT, Boolean.valueOf(true));
                    this.f22503Q.setText((CharSequence) a.first);
                    this.f22504R.setText(MessageFormat.format((String) a.second, new Object[]{getString(C1947R.string.performance_copyright_violation_email)}));
                } else {
                    this.f22503Q.setText(getString(C1947R.string.performance_copyright_violation));
                    this.f22504R.setText(MessageFormat.format(getString(C1947R.string.performance_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}));
                }
            }
            Log.b(aP, "setupTitleBar - mPerformance.isJoinable() = " + this.ay.n() + " because: seed = " + this.ay.seed + ", hasExpired = " + this.ay.j() + ", closed = " + this.ay.closed);
            this.e.setRightButtonText(this.ay.n() ? getString(C1947R.string.core_join) : getString(C1947R.string.core_sing));
        }
    }

    private void ap() {
        if (this.ay != null) {
            ImageUtils.a(this.ay.coverUrl, this.j);
        }
        if ((this.ay == null || !this.ay.t()) && !this.aS) {
            this.m.setText(aE());
            this.n.setText(PerformanceV2Util.m25933a(getResources(), this.ay, true));
            this.f22499M.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NowPlayingFragment f22425a;

                {
                    this.f22425a = r1;
                }

                public void onClick(View view) {
                    this.f22425a.m23914T();
                }
            });
            return;
        }
        this.m.setTextColor(getResources().getColor(C1947R.color.pale_red));
        this.n.setTextSize(0, (float) getResources().getDimensionPixelSize(C1947R.dimen.now_playing_minibar_subtitle_text_size_copyright_infringement));
        if (this.aS) {
            Pair a = MiscUtils.m25880a(this.aT, Boolean.valueOf(true));
            this.m.setText((CharSequence) a.first);
            this.n.setText(MessageFormat.format((String) a.second, new Object[]{getString(C1947R.string.performance_copyright_violation_email)}));
        } else {
            this.m.setText(getString(C1947R.string.performance_copyright_violation));
            this.n.setText(getString(C1947R.string.performance_copyright_violation_detail_short));
        }
        this.l.setVisibility(4);
    }

    private void aq() {
        if ((this.ay == null || !this.ay.t()) && !this.aS) {
            boolean z;
            if (this.ay != null && this.ay.b()) {
                QueueItem queueItem = new QueueItem(this.ay.s() ? SongbookEntry.m18746a(this.aC) : SongbookEntry.m18743a(this.ay.arrangementVersion), this.ay);
                if (this.aE != null) {
                    z = true;
                } else {
                    z = false;
                }
                queueItem.m24732b(z);
                this.q.m24653a(queueItem, this.u);
            }
            String str = aP;
            StringBuilder append = new StringBuilder().append("Performance info fetched: ").append(this.aZ).append(". Performance is rendered: ");
            if (this.ay == null || !this.ay.b()) {
                z = false;
            } else {
                z = true;
            }
            Log.b(str, append.append(z).append(".").toString());
            if (this.aZ && this.ay != null && !this.ay.b() && !this.ba) {
                this.ba = true;
                TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.client_render_progess_title), getString(C1947R.string.client_render_progress_body), true, false);
                textAlertDialog.m19806a(getString(C1947R.string.core_ok), "");
                textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
                    final /* synthetic */ NowPlayingFragment f22426a;

                    {
                        this.f22426a = r1;
                    }

                    public void mo6385a(CustomAlertDialog customAlertDialog) {
                        if (this.f22426a.isAdded()) {
                            this.f22426a.mo6830a(this.f22426a);
                        }
                    }

                    public void mo6386b(CustomAlertDialog customAlertDialog) {
                        if (this.f22426a.isAdded()) {
                            this.f22426a.mo6830a(this.f22426a);
                        }
                    }
                });
                textAlertDialog.show();
                return;
            }
            return;
        }
        MediaPlayerServiceController.m24641a().m24660b(this.ay.performanceKey);
        this.q.m24661c();
    }

    private void m23882g(boolean z) {
        if (this.ax == null || z) {
            PerformanceManager.a().a(this.ay.performanceKey, new PerformanceManager$PerformanceLovesResponseCallback(this) {
                final /* synthetic */ NowPlayingFragment f22427a;

                {
                    this.f22427a = r1;
                }

                public void handleResponse(PerformanceLovesResponse performanceLovesResponse) {
                    this.f22427a.ax = new ArrayList();
                    if (performanceLovesResponse.a()) {
                        Iterator it = performanceLovesResponse.mPerformancePosts.iterator();
                        while (it.hasNext()) {
                            PerformancePost performancePost = (PerformancePost) it.next();
                            if (!(performancePost.accountIcon == null || performancePost.accountIcon.handle == null)) {
                                this.f22427a.ax = performanceLovesResponse.mPerformancePosts;
                            }
                        }
                        this.f22427a.m23870c(true, false);
                    }
                }
            });
        } else {
            mo6835Q();
        }
    }

    private void m23870c(boolean z, boolean z2) {
        if (z) {
            Iterator it = this.ax.iterator();
            boolean z3 = false;
            while (it.hasNext()) {
                boolean z4;
                if (((PerformancePost) it.next()).accountIcon.accountId == UserManager.a().f()) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                z3 = z4;
            }
            if (z3) {
                Log.b(aP, "getPerformanceLoves - getLoves API indicated performance already loved; adding to cache");
                StringCacheManager.a().a(this.ay.performanceKey);
                this.aA = true;
            }
        }
        if (this.ax != null) {
            Collections.sort(this.ax, new Comparator<PerformancePost>(this) {
                final /* synthetic */ NowPlayingFragment f22428a;

                {
                    this.f22428a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return m23835a((PerformancePost) obj, (PerformancePost) obj2);
                }

                public int m23835a(PerformancePost performancePost, PerformancePost performancePost2) {
                    return performancePost.accountIcon.handle.toLowerCase().compareTo(performancePost2.accountIcon.handle.toLowerCase());
                }
            });
        }
        if (z2) {
            StringCacheManager.a().a(this.ay.performanceKey);
        }
        mo6835Q();
    }

    @UiThread
    public void mo6835Q() {
        Log.b(aP, "refreshLoveViews - begin");
        if (!isAdded()) {
            Log.d(aP, "refreshLoveViews - fragment not added; aborting");
        } else if (this.ay == null) {
            Log.d(aP, "refreshLoveViews - performance is null");
        } else {
            this.bv.notifyDataSetChanged();
            if (this.ay.totalLoves > 0) {
                CharSequence quantityString = getResources().getQuantityString(C1947R.plurals.love_count, this.ay.totalLoves, new Object[]{ac().m18999a((long) this.ay.totalLoves, (long) getResources().getInteger(C1947R.integer.long_form_threshold))});
                this.ac.setText(quantityString);
                this.an.setText(quantityString);
            } else {
                this.ac.setText(C1947R.string.now_playing_be_the_first_to_love);
                this.an.setText(C1947R.string.now_playing_be_the_first_to_love);
            }
            mo6836R();
        }
    }

    @UiThread
    protected void mo6836R() {
        if (isAdded()) {
            if (this.aA) {
                ImageUtils.a(this.f22499M, getResources().getDrawable(C1947R.drawable.icn_love_blue));
                this.ai.setImageResource(C1947R.drawable.icn_love_blue);
            } else {
                ImageUtils.a(this.f22499M, getResources().getDrawable(C1947R.drawable.icn_love));
                this.ai.setImageResource(C1947R.drawable.icn_love);
            }
            this.ao.setActivated(this.aA);
            this.ao.setVisibility(0);
            this.aw.setVisibility(8);
        }
    }

    @UiThread
    public void mo6837S() {
        Log.b(aP, "refreshJoinersViews - begin");
        if (!isAdded()) {
            Log.d(aP, "refreshJoinersViews - fragment not added; aborting");
        } else if (this.ay == null) {
            Log.d(aP, "refreshJoinersViews - performance is null");
        } else {
            this.bw.notifyDataSetChanged();
            if (this.bb.size() > 0) {
                this.as.setText(getResources().getQuantityString(C1947R.plurals.singers_joined_count, this.bb.size(), new Object[]{ac().m18998a((long) this.bb.size())}));
            }
            mo6836R();
        }
    }

    public void m23914T() {
        if (this.aS || (this.ay != null && this.ay.t())) {
            ((BaseActivity) getActivity()).a(this.aT, true, null);
        } else if (isAdded()) {
            Log.b(aP, "executePerformanceLove - called");
            if (this.aA) {
                Log.b(aP, "executePerformanceLove - already loved; aborting");
                mo6835Q();
            } else if (this.aY) {
                Log.b(aP, "executePerformanceLove - in process of loving; aborting");
                mo6835Q();
            } else {
                this.aY = true;
                this.ao.setVisibility(8);
                this.aw.setVisibility(0);
                MagicNetwork.a(new Runnable(this) {
                    final /* synthetic */ NowPlayingFragment f22430a;

                    class C45421 implements NetworkResponseCallback {
                        final /* synthetic */ AnonymousClass24 f22429a;

                        C45421(AnonymousClass24 anonymousClass24) {
                            this.f22429a = anonymousClass24;
                        }

                        public void handleResponse(NetworkResponse networkResponse) {
                            if (this.f22429a.f22430a.isAdded()) {
                                this.f22429a.f22430a.aA = true;
                                this.f22429a.f22430a.aw.setVisibility(8);
                                if (networkResponse != null && networkResponse.a == NetworkResponse$Status.OK) {
                                    this.f22429a.f22430a.mo6844c(this.f22429a.f22430a.ay.performanceKey);
                                    SingAnalytics.m26112a(this.f22429a.f22430a.ay.performanceKey, PerformanceV2Util.m25947e(this.f22429a.f22430a.ay), Analytics.m17828a(this.f22429a.f22430a.ay), this.f22429a.f22430a.aF(), this.f22429a.f22430a.ay.video);
                                    PerformanceV2 performanceV2 = this.f22429a.f22430a.ay;
                                    performanceV2.totalLoves++;
                                    PerformancePost performancePost = new PerformancePost();
                                    performancePost.accountIcon = UserManager.a().O();
                                    performancePost.time = System.currentTimeMillis();
                                    if (this.f22429a.f22430a.ax != null) {
                                        this.f22429a.f22430a.ax.add(performancePost);
                                    }
                                    this.f22429a.f22430a.m23870c(false, true);
                                } else if (networkResponse.e()) {
                                    this.f22429a.f22430a.ao.setVisibility(8);
                                    this.f22429a.f22430a.aw.setVisibility(8);
                                    ((BaseActivity) this.f22429a.f22430a.getActivity()).a(networkResponse.f, true, null);
                                } else {
                                    this.f22429a.f22430a.ao.setVisibility(0);
                                }
                                this.f22429a.f22430a.aY = false;
                            }
                        }
                    }

                    {
                        this.f22430a = r1;
                    }

                    public void run() {
                        Location a = LocationUtils.m19000a();
                        PerformanceManager.a().a(this.f22430a.ay.performanceKey, (float) a.getLatitude(), (float) a.getLongitude(), new C45421(this));
                    }
                });
            }
        }
    }

    @UiThread
    protected void mo6844c(String str) {
        Log.b(aP, "executePerformanceLove - posting notification LOVE_GIVEN for performance with key: " + str);
        NotificationCenter.m19011a().m19012a("LOVE_GIVEN", (Object) str);
    }

    private void ar() {
        m23856a(this.ak, Mode.LOVES_VIEW);
    }

    private void m23881g(String str) {
        if (!isAdded()) {
            return;
        }
        if (this.ay.t() || this.aS) {
            Log.b(aP, "showCommentsView - Performance is deleted or disabled.");
            ((BaseActivity) getActivity()).a(this.aT, true, null);
            return;
        }
        getActivity().getWindow().setSoftInputMode(32);
        this.bf = new CommentsListDialog(this, this.ay, str);
        this.bf.m23658a(new CommentsListDialogListener(this) {
            final /* synthetic */ NowPlayingFragment f22431a;

            {
                this.f22431a = r1;
            }

            public void mo6828a(CommentItem commentItem, PerformancePost performancePost) {
                this.f22431a.mo6456a(ProfileFragment.m21036a(performancePost.accountIcon));
                this.f22431a.aw();
            }
        });
        this.bf.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ NowPlayingFragment f22432a;

            {
                this.f22432a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                if (this.f22432a.isAdded() && (dialogInterface instanceof CommentsListDialog)) {
                    this.f22432a.am();
                    this.f22432a.mo6420v();
                }
            }
        });
        this.bf.show();
    }

    private void as() {
        if (isAdded()) {
            m23856a(this.ap, Mode.JOINERS_VIEW);
            FractionalRelativeLayout fractionalRelativeLayout = (FractionalRelativeLayout) getView();
            if (fractionalRelativeLayout != null) {
                fractionalRelativeLayout.m25829a("fadeInJoinersView", false);
            }
        }
    }

    private void at() {
        m23865b(this.ap);
    }

    private void au() {
        m23865b(this.ak);
    }

    private boolean av() {
        return this.bf != null && this.bf.isShowing();
    }

    private boolean aw() {
        if (this.bf == null) {
            return false;
        }
        boolean isShowing = this.bf.isShowing();
        this.bf.dismiss();
        this.bf.setOnDismissListener(null);
        this.bf = null;
        return isShowing;
    }

    private boolean ax() {
        if (!aw()) {
            return false;
        }
        ay();
        return true;
    }

    private void ay() {
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null && (currentFocus instanceof EditText)) {
            m23855a(currentFocus);
        }
    }

    private void m23855a(View view) {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void m23856a(View view, Mode mode) {
        mo6840a(view, 0.0f, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        this.aR = mode;
        mo6420v();
    }

    private void m23865b(View view) {
        mo6840a(view, (float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
        this.aR = Mode.MAXIMIZED;
        mo6420v();
    }

    @UiThread
    protected void mo6840a(final View view, final float f, final float f2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", new float[]{f, f2});
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.setDuration(300);
        ofFloat.setStartDelay(300);
        ofFloat.start();
        ofFloat.addListener(new AnimatorListener(this) {
            final /* synthetic */ NowPlayingFragment f22436d;

            public void onAnimationStart(Animator animator) {
                if (f2 > f) {
                    view.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (f > f2) {
                    view.setVisibility(8);
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    private void az() {
        if (isAdded() && this.be != null && this.be.isShowing()) {
            int indexOf = this.bs.indexOf("FAVORITE_ID");
            if (this.bt.get(indexOf) != null) {
                this.bt.set(indexOf, this.bh ? getString(C1947R.string.core_unfavorite) : getString(C1947R.string.core_favorite));
                this.bu.notifyDataSetChanged();
            }
        }
    }

    private void aA() {
        if (isAdded() && this.be != null && this.be.isShowing()) {
            int indexOf = this.bs.indexOf("BOOKMARK_ID");
            if (indexOf != -1 && this.bt.get(indexOf) != null) {
                this.bt.set(indexOf, this.bi ? getString(C1947R.string.core_bookmark_remove) : getString(C1947R.string.core_bookmark_invite));
                this.bu.notifyDataSetChanged();
            }
        }
    }

    @IgnoreWhen
    @UiThread
    protected void mo6838U() {
        Object string;
        String str = "EDIT_ID";
        str = "INVITE_ID";
        str = "CANCEL_ID";
        str = "DELETE_ID";
        str = "REPORT_ABUSE_ID";
        str = "SONG_INFO_ID";
        HashMap hashMap = new HashMap();
        hashMap.put("BOOKMARK_ID", this.bi ? getString(C1947R.string.core_bookmark_remove) : getString(C1947R.string.core_bookmark_invite));
        String str2 = "FAVORITE_ID";
        if (this.bh) {
            string = getString(C1947R.string.core_unfavorite);
        } else {
            string = getString(C1947R.string.core_favorite);
        }
        hashMap.put(str2, string);
        hashMap.put("EDIT_ID", getString(C1947R.string.core_edit));
        hashMap.put("INVITE_ID", getString(C1947R.string.core_invite_friends));
        hashMap.put("CANCEL_ID", getString(C1947R.string.core_cancel));
        hashMap.put("DELETE_ID", getString(C1947R.string.core_delete));
        hashMap.put("REPORT_ABUSE_ID", getString(C1947R.string.now_playing_report_abuse));
        hashMap.put("SONG_INFO_ID", getString(C1947R.string.now_playing_song_info));
        ArrayList arrayList = new ArrayList();
        if (!(this.ay == null || this.ay.t() || this.aS)) {
            if (this.ay.n() && !this.ay.l()) {
                arrayList.add("BOOKMARK_ID");
            }
            arrayList.add("FAVORITE_ID");
        }
        if (this.ay != null && this.ay.k() && !this.ay.t() && !this.aS) {
            Log.b(aP, "onShareButton - open call and is able to still invite people");
            arrayList.add("EDIT_ID");
            arrayList.add("INVITE_ID");
        } else if (!(this.ay == null || !this.ay.l() || this.ay.t() || this.aS)) {
            Log.b(aP, "onShareButton - performance belongs to me");
            arrayList.add("EDIT_ID");
        }
        if (PerformanceV2Util.m25940a(this.ay)) {
            Log.b(aP, "onShareButton - adding delete option");
            arrayList.add("DELETE_ID");
        }
        if (!(this.ay == null || this.ay.accountIcon == null || this.ay.accountIcon.d())) {
            arrayList.add("REPORT_ABUSE_ID");
        }
        if (this.ay.r() || StoreManager.m18378a().m18428e(this.ay.songUid) != null) {
            arrayList.add("SONG_INFO_ID");
        }
        arrayList.add("CANCEL_ID");
        if (arrayList.size() == 0 || (arrayList.size() == 1 && arrayList.contains("CANCEL_ID"))) {
            Log.b(aP, "onShareButton - engage regular share mode");
            ShareUtils.m26005a(getActivity(), this.ay);
            return;
        }
        this.bs = arrayList;
        this.bt = new ArrayList();
        Iterator it = this.bs.iterator();
        while (it.hasNext()) {
            this.bt.add(hashMap.get((String) it.next()));
        }
        Log.b(aP, "onShareButton - size of OptionsList is: " + this.bs.size());
        final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) getActivity();
        if (mediaPlayingActivity == null) {
            MagicCrittercism.a(new NullPointerException("Activity was null").fillInStackTrace());
            return;
        }
        this.bu = new ArrayAdapter<String>(this, mediaPlayingActivity, C1947R.layout.alert_dialog_list_item, this.bt) {
            final /* synthetic */ NowPlayingFragment f22438a;
        };
        this.be = new Builder(mediaPlayingActivity).setAdapter(this.bu, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22444b;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.content.DialogInterface r7, int r8) {
                /*
                r6 = this;
                r1 = 0;
                r5 = 0;
                r3 = 1;
                r0 = com.smule.singandroid.fragments.NowPlayingFragment.aP;
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r4 = "onShareButton - onClick - which is: ";
                r2 = r2.append(r4);
                r2 = r2.append(r8);
                r4 = ", and size of optionsList is: ";
                r2 = r2.append(r4);
                r4 = r6.f22444b;
                r4 = r4.bs;
                r4 = r4.size();
                r2 = r2.append(r4);
                r2 = r2.toString();
                com.smule.android.logging.Log.b(r0, r2);
                r0 = r6.f22444b;
                r0 = r0.bs;
                r0 = r0.get(r8);
                r0 = (java.lang.String) r0;
                r2 = -1;
                r4 = r0.hashCode();
                switch(r4) {
                    case -1867050961: goto L_0x0072;
                    case -1169960080: goto L_0x005e;
                    case -1031765760: goto L_0x0090;
                    case 165217535: goto L_0x007c;
                    case 230293150: goto L_0x0054;
                    case 701978914: goto L_0x0086;
                    case 885621265: goto L_0x0068;
                    case 1384662212: goto L_0x004a;
                    default: goto L_0x0045;
                };
            L_0x0045:
                r0 = r2;
            L_0x0046:
                switch(r0) {
                    case 0: goto L_0x009a;
                    case 1: goto L_0x00a0;
                    case 2: goto L_0x00a6;
                    case 3: goto L_0x00ac;
                    case 4: goto L_0x00ea;
                    case 5: goto L_0x00f9;
                    case 6: goto L_0x0102;
                    default: goto L_0x0049;
                };
            L_0x0049:
                return;
            L_0x004a:
                r4 = "BOOKMARK_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x0052:
                r0 = r1;
                goto L_0x0046;
            L_0x0054:
                r4 = "FAVORITE_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x005c:
                r0 = r3;
                goto L_0x0046;
            L_0x005e:
                r4 = "EDIT_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x0066:
                r0 = 2;
                goto L_0x0046;
            L_0x0068:
                r4 = "INVITE_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x0070:
                r0 = 3;
                goto L_0x0046;
            L_0x0072:
                r4 = "DELETE_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x007a:
                r0 = 4;
                goto L_0x0046;
            L_0x007c:
                r4 = "REPORT_ABUSE_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x0084:
                r0 = 5;
                goto L_0x0046;
            L_0x0086:
                r4 = "SONG_INFO_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x008e:
                r0 = 6;
                goto L_0x0046;
            L_0x0090:
                r4 = "CANCEL_ID";
                r0 = r0.equals(r4);
                if (r0 == 0) goto L_0x0045;
            L_0x0098:
                r0 = 7;
                goto L_0x0046;
            L_0x009a:
                r0 = r6.f22444b;
                r0.aB();
                goto L_0x0049;
            L_0x00a0:
                r0 = r6.f22444b;
                r0.aC();
                goto L_0x0049;
            L_0x00a6:
                r0 = r6.f22444b;
                r0.aD();
                goto L_0x0049;
            L_0x00ac:
                r0 = r6.f22444b;
                r0 = r0.aS;
                if (r0 != 0) goto L_0x00c4;
            L_0x00b4:
                r0 = r6.f22444b;
                r0 = r0.ay;
                if (r0 == 0) goto L_0x00d7;
            L_0x00ba:
                r0 = r6.f22444b;
                r0 = r0.ay;
                r0 = r0.t();
                if (r0 == 0) goto L_0x00d7;
            L_0x00c4:
                r0 = r6.f22444b;
                r0 = r0.getActivity();
                r0 = (com.smule.singandroid.BaseActivity) r0;
                r1 = r6.f22444b;
                r1 = r1.aT;
                r0.a(r1, r3, r5);
                goto L_0x0049;
            L_0x00d7:
                r0 = r0;
                r1 = r6.f22444b;
                r1 = r1.ay;
                r2 = com.smule.android.logging.Analytics.SearchClkContext.SHAREMESSAGE;
                r0 = com.smule.singandroid.chat.ChatShareInviteActivity.m22648a(r0, r1, r2);
                r1 = r6.f22444b;
                r1.startActivity(r0);
                goto L_0x0049;
            L_0x00ea:
                r0 = r6.f22444b;
                r1 = r0;
                r2 = r6.f22444b;
                r2 = r2.ay;
                r2 = r2.performanceKey;
                r0.mo6839a(r1, r2);
                goto L_0x0049;
            L_0x00f9:
                r0 = r6.f22444b;
                r1 = r0;
                r0.m23854a(r1);
                goto L_0x0049;
            L_0x0102:
                r0 = r6.f22444b;
                r0 = r0.aS;
                if (r0 != 0) goto L_0x011a;
            L_0x010a:
                r0 = r6.f22444b;
                r0 = r0.ay;
                if (r0 == 0) goto L_0x012d;
            L_0x0110:
                r0 = r6.f22444b;
                r0 = r0.ay;
                r0 = r0.t();
                if (r0 == 0) goto L_0x012d;
            L_0x011a:
                r0 = r6.f22444b;
                r0 = r0.getActivity();
                r0 = (com.smule.singandroid.BaseActivity) r0;
                r1 = r6.f22444b;
                r1 = r1.aT;
                r0.a(r1, r3, r5);
                goto L_0x0049;
            L_0x012d:
                r0 = r6.f22444b;
                r0 = r0.ay;
                r0 = r0.r();
                if (r0 == 0) goto L_0x015a;
            L_0x0137:
                r0 = r6.f22444b;
                r0 = r0.ay;
                r0 = r0.arrangementVersion;
                r0 = r0.arrangement;
                r0 = r0.removalCode;
                if (r0 == 0) goto L_0x015a;
            L_0x0143:
                r0 = r6.f22444b;
                r0 = r0.getActivity();
                r0 = (com.smule.singandroid.BaseActivity) r0;
                r2 = r6.f22444b;
                r2 = r2.ay;
                r2 = r2.arrangementVersion;
                r2 = r2.arrangement;
                r2 = r2.removalCode;
                r0.a(r2, r1, r5);
                goto L_0x0049;
            L_0x015a:
                r0 = r6.f22444b;
                r0 = r0.mo6543a();
                if (r0 == 0) goto L_0x017f;
            L_0x0162:
                r0 = r6.f22444b;
                r0 = r0.q;
                if (r0 == 0) goto L_0x017f;
            L_0x016a:
                r0 = r6.f22444b;
                r0 = r0.q;
                r0 = r0.m24676j();
                if (r0 == 0) goto L_0x017f;
            L_0x0176:
                r0 = r6.f22444b;
                r0 = r0.q;
                r0.m24665e();
            L_0x017f:
                r1 = r0;
                r0 = r6.f22444b;
                r0 = r0.ay;
                r0 = r0.s();
                if (r0 == 0) goto L_0x019d;
            L_0x018b:
                r0 = r6.f22444b;
                r0 = r0.aC;
                r0 = com.smule.android.songbook.SongbookEntry.m18746a(r0);
            L_0x0193:
                r1.a(r0, r3, r5);
                r0 = r6.f22444b;
                r0.ab();
                goto L_0x0049;
            L_0x019d:
                r0 = r6.f22444b;
                r0 = r0.ay;
                r0 = r0.arrangementVersion;
                r0 = com.smule.android.songbook.SongbookEntry.m18743a(r0);
                goto L_0x0193;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.fragments.NowPlayingFragment.30.onClick(android.content.DialogInterface, int):void");
            }
        }).create();
        this.be.getListView().setDividerHeight(0);
        this.be.getListView().setSelector(17170445);
        this.be.show();
        SingAnalytics.m26127b(PerformanceV2Util.m25947e(this.ay));
    }

    private void aB() {
        if (this.aS || (this.ay != null && this.ay.t())) {
            ((BaseActivity) getActivity()).a(this.aT, true, null);
        } else if (!this.bk) {
            this.bk = true;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (this.bi) {
                SingAnalytics.m26138c(this.ay.performanceKey, PerformanceV2Util.m25947e(this.ay), PerformanceV2Util.m25950h(this.ay), this.ay.video);
                arrayList2.add(this.ay.performanceKey);
            } else {
                SingAnalytics.m26131b(this.ay.performanceKey, PerformanceV2Util.m25947e(this.ay), PerformanceV2Util.m25950h(this.ay), this.ay.video);
                arrayList.add(this.ay.performanceKey);
            }
            PerformanceManager.a().a(arrayList, arrayList2, new NetworkResponseCallback(this) {
                final /* synthetic */ NowPlayingFragment f22445a;

                {
                    this.f22445a = r1;
                }

                public void handleResponse(NetworkResponse networkResponse) {
                    if (this.f22445a.isAdded()) {
                        this.f22445a.bk = false;
                        if (networkResponse.c()) {
                            if (this.f22445a.bi) {
                                UserManager.a().k(this.f22445a.ay.performanceKey);
                                NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", this.f22445a.ay);
                            } else {
                                UserManager.a().j(this.f22445a.ay.performanceKey);
                                NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", this.f22445a.ay);
                                ((MediaPlayingActivity) this.f22445a.getActivity()).ad().m22306a(this.f22445a, this.f22445a.f22493G, this.f22445a.f22494H, true);
                            }
                            MagicDataSource.m17632a(ProfileOpenCallDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                        } else if (networkResponse.b == PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW) {
                            new TextAlertDialog(this.f22445a.getActivity(), this.f22445a.getString(C1947R.string.bookmark_max_reached_title), this.f22445a.getString(C1947R.string.bookmark_max_reached_subtitle), true, false).show();
                        } else if (networkResponse.b == PointerIconCompat.TYPE_GRABBING || networkResponse.b == PointerIconCompat.TYPE_NO_DROP) {
                            Log.d(NowPlayingFragment.aP, "Attempting to bookmark non-joinable performance: " + this.f22445a.ay.performanceKey, new BookmarkNonSeedException("perfKey: " + this.f22445a.ay.performanceKey).fillInStackTrace());
                            TextAlertDialog textAlertDialog = new TextAlertDialog(this.f22445a.getActivity(), "", this.f22445a.getResources().getString(C1947R.string.bookmark_error_expired), true, false);
                            textAlertDialog.m19806a(this.f22445a.getString(C1947R.string.core_ok), "");
                            textAlertDialog.show();
                        } else {
                            new TextAlertDialog(this.f22445a.getActivity(), this.f22445a.getString(C1947R.string.favorite_error_title), this.f22445a.getString(C1947R.string.login_cannot_connect_to_smule), true, false).show();
                        }
                    }
                }
            });
        }
    }

    private void aC() {
        if (this.aS || (this.ay != null && this.ay.t())) {
            ((BaseActivity) getActivity()).a(this.aT, true, null);
        } else if (!this.bj) {
            this.bj = true;
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            if (this.bh) {
                arrayList2.add(this.ay.performanceKey);
            } else {
                arrayList.add(this.ay.performanceKey);
            }
            PerformanceManager.a().a(arrayList, arrayList2, new NetworkResponseCallback(this) {
                final /* synthetic */ NowPlayingFragment f22446a;

                {
                    this.f22446a = r1;
                }

                public void handleResponse(NetworkResponse networkResponse) {
                    if (this.f22446a.isAdded()) {
                        this.f22446a.bj = false;
                        if (networkResponse.c()) {
                            if (this.f22446a.bh) {
                                UserManager.a().h(this.f22446a.ay.performanceKey);
                                NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", this.f22446a.ay);
                            } else {
                                UserManager.a().g(this.f22446a.ay.performanceKey);
                                NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", this.f22446a.ay);
                                ((MediaPlayingActivity) this.f22446a.getActivity()).ad().m22306a(this.f22446a, this.f22446a.f22493G, this.f22446a.f22494H, false);
                            }
                            MagicDataSource.m17632a(ProfileFavoritesDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                        } else if (networkResponse.b == PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW) {
                            new TextAlertDialog(this.f22446a.getActivity(), this.f22446a.getString(C1947R.string.favorite_max_reached_title), this.f22446a.getString(C1947R.string.favorite_max_reached_subtitle), true, false).show();
                        } else {
                            new TextAlertDialog(this.f22446a.getActivity(), this.f22446a.getString(C1947R.string.favorite_error_title), this.f22446a.getString(C1947R.string.login_cannot_connect_to_smule), true, false).show();
                        }
                    }
                }
            });
        }
    }

    @Click
    protected void m23916V() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f22493G);
    }

    private void m23854a(final Activity activity) {
        if (this.aS || (this.ay != null && this.ay.t())) {
            ((BaseActivity) getActivity()).a(this.aT, true, null);
        } else if (this.ay != null) {
            TextAlertDialog textAlertDialog = new TextAlertDialog((Context) activity, activity.getString(C1947R.string.now_playing_report_abuse_confirmation), activity.getString(C1947R.string.now_playing_report_abuse_details));
            textAlertDialog.m19800a((int) C1947R.string.core_report, (int) C1947R.string.core_cancel);
            textAlertDialog.m19804a(new Runnable(this) {
                final /* synthetic */ NowPlayingFragment f22450b;

                public void run() {
                    Log.c(NowPlayingFragment.aP, "Reporting performance as abusive. Performacne key : " + this.f22450b.ay.performanceKey);
                    final BusyDialog busyDialog = new BusyDialog(activity, activity.getString(C1947R.string.now_playing_reporting_performance));
                    busyDialog.show();
                    PerformanceManager.a().a(this.f22450b.ay.performanceKey, new NetworkResponseCallback(this) {
                        final /* synthetic */ AnonymousClass33 f22448b;

                        public void handleResponse(NetworkResponse networkResponse) {
                            busyDialog.dismiss();
                            if (networkResponse.c()) {
                                TextAlertDialog textAlertDialog = new TextAlertDialog(activity, activity.getString(C1947R.string.now_playing_flagged_for_abuse), activity.getString(C1947R.string.now_playing_flagged_for_abuse_details) + "\n\n" + activity.getString(C1947R.string.songbook_report_song_copyright), true, false);
                                textAlertDialog.m19800a((int) C1947R.string.core_ok, 0);
                                textAlertDialog.show();
                                return;
                            }
                            this.f22448b.f22450b.m19846b((int) C1947R.string.now_playing_report_failed);
                        }
                    });
                }
            });
            textAlertDialog.show();
        }
    }

    private void aD() {
        if (this.aS || (this.ay != null && this.ay.t())) {
            ((BaseActivity) getActivity()).a(this.aT, true, null);
        } else if (getView() == null || getView().getParent() == null) {
            Log.e(aP, "Parent view was null");
        } else {
            Fragment a = PerformanceSaveFragmentFactory.m20779a(this.ay);
            this.q.m24665e();
            m23940e(false);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(((ViewGroup) getView().getParent()).getId(), a, PerformanceSaveFragment.class.getName());
            beginTransaction.addToBackStack(PerformanceSaveFragment.class.getName());
            beginTransaction.commitAllowingStateLoss();
        }
    }

    @UiThread
    protected void mo6839a(Activity activity, String str) {
        final int i = this.d;
        NavigationUtils.m25907a(activity, this.ay, null, new Runnable(this) {
            final /* synthetic */ NowPlayingFragment f22452b;

            public void run() {
                this.f22452b.mo6841a(true, i);
            }
        }, new Runnable(this) {
            final /* synthetic */ NowPlayingFragment f22454b;

            public void run() {
                this.f22454b.mo6841a(false, i);
            }
        }, true);
    }

    @UiThread
    protected void mo6841a(boolean z, final int i) {
        if (z) {
            if (this.q.m24676j()) {
                Log.b(aP, "Performance was playing; halting performance");
                this.q.m24665e();
            }
            m19839a(new Runnable(this) {
                final /* synthetic */ NowPlayingFragment f22456b;

                public void run() {
                    if (this.f22456b.m19843a(i)) {
                        this.f22456b.m19862m().a(OpenCallPerformancesListFragment.class.getName());
                        this.f22456b.mo6830a(this.f22456b);
                    }
                }
            }, 400);
        }
    }

    private View m23851a(View view, AccountIcon accountIcon, long j, boolean z) {
        View c;
        if (view == null || !(view instanceof UserFollowItem)) {
            c = UserFollowItem.m24442c(getActivity());
        } else {
            c = view;
        }
        UserFollowItem userFollowItem = (UserFollowItem) c;
        userFollowItem.m24448a(accountIcon, getActivity(), false, this);
        userFollowItem.setTime(j);
        if (z) {
            userFollowItem.setJoinersStyle(getResources());
        }
        return c;
    }

    private String aE() {
        if (this.ay != null) {
            return this.ay.title;
        }
        return "";
    }

    private String aF() {
        return SingAnalytics.m26140d(this.ay);
    }

    @AfterViews
    public void m23917W() {
        if (this.ay != null) {
            this.aA = StringCacheManager.a().b(this.ay.performanceKey);
            mo6836R();
        }
        aa();
        this.f22495I.setVisibility(0);
        this.f22496J.setVisibility(4);
        this.e.setLeftButtonOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22459a;

            {
                this.f22459a = r1;
            }

            public void onClick(View view) {
                this.f22459a.m23918X();
            }
        });
        if (this.ay != null) {
            this.e.setRightButtonText(this.ay.n() ? getString(C1947R.string.core_join) : getString(C1947R.string.core_sing));
        } else {
            this.e.setRightButtonText("");
        }
        this.e.setRightButtonOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22463a;

            {
                this.f22463a = r1;
            }

            public void onClick(View view) {
                if (this.f22463a.ay != null && !this.f22463a.ay.t()) {
                    if (!this.f22463a.ay.r() || this.f22463a.ay.arrangementVersion.arrangement.removalCode == 0) {
                        SongbookEntry s;
                        SongbookEntry songbookEntry;
                        if (this.f22463a.aC == null && this.f22463a.ay.songUid != null) {
                            Log.d(NowPlayingFragment.aP, "mSingButton onClickListener - mListing is null; attempting to get listing from performance object");
                            this.f22463a.aC = StoreManager.m18378a().m18428e(this.f22463a.ay.songUid);
                        }
                        if (this.f22463a.aC == null && !this.f22463a.ay.r()) {
                            if (PerformanceV2Util.m25948f(this.f22463a.ay) == null) {
                                PreSingActivity.m24763a(this.f22463a.getActivity()).m24796a(StartupMode.DEFAULT).a();
                                Log.e(NowPlayingFragment.aP, "mSingButton onClickListener - song is still null; aborting rest of flow");
                                return;
                            }
                            this.f22463a.aC = new ListingV2(PerformanceV2Util.m25948f(this.f22463a.ay));
                        }
                        if (this.f22463a.getActivity() instanceof PreSingActivity) {
                            s = ((PreSingActivity) this.f22463a.getActivity()).m24788s();
                        } else {
                            s = null;
                        }
                        if (s == null) {
                            if (this.f22463a.ay.s()) {
                                s = SongbookEntry.m18746a(this.f22463a.aC);
                            } else {
                                s = SongbookEntry.m18743a(this.f22463a.ay.arrangementVersion);
                            }
                            songbookEntry = s;
                        } else {
                            songbookEntry = s;
                        }
                        Ensemble a = PerformanceType.m21630a(this.f22463a.ay.ensembleType).m21631a();
                        if (this.f22463a.aE != null) {
                            Analytics.m17854a(this.f22463a.aE, this.f22463a.ay.n() ? SearchResultClkContext.JOIN : SearchResultClkContext.SING, SearchResultClkValue.NOWPLAYING, PerformanceV2Util.m25947e(this.f22463a.ay), this.f22463a.ay.performanceKey, Integer.valueOf(0), Long.valueOf(this.f22463a.ay.accountIcon.accountId), PerformanceV2Util.m25950h(this.f22463a.ay), this.f22463a.ay.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO, 1, 0);
                        }
                        if (!this.f22463a.ay.n()) {
                            Analytics.m17864a(this.f22463a.ay.performanceKey, null, PerformanceV2Util.m25947e(this.f22463a.ay), this.f22463a.aF(), a, null);
                            PreSingActivity.m24763a(this.f22463a.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(songbookEntry).m24792a(this.f22463a.bl.longValue()).a();
                            return;
                        } else if (SongbookEntryUtils.m26166a(songbookEntry, null)) {
                            PreSingActivity.m24763a(this.f22463a.getActivity()).m24796a(StartupMode.OPENCALL).m24794a(songbookEntry).m24792a(this.f22463a.bl.longValue()).m24793a(this.f22463a.ay).a();
                            return;
                        } else {
                            this.f22463a.mo6487a(UpsellManager.m25790a(true, songbookEntry, this.f22463a.ay, this.f22463a.bl, UpsellType.VIP_SONG));
                            return;
                        }
                    }
                    ((BaseActivity) this.f22463a.getActivity()).a(this.f22463a.ay.arrangementVersion.arrangement.removalCode, false, null);
                }
            }
        });
        this.al.setLeftButtonOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22464a;

            {
                this.f22464a = r1;
            }

            public void onClick(View view) {
                this.f22464a.au();
            }
        });
        this.aq.setLeftButtonOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22465a;

            {
                this.f22465a = r1;
            }

            public void onClick(View view) {
                this.f22465a.at();
            }
        });
        this.r.setOnTouchListener(new SwipeDismissTouchListener(this.r, null, new DismissCallbacks(this) {
            final /* synthetic */ NowPlayingFragment f22466a;

            {
                this.f22466a = r1;
            }

            public boolean mo6540a(Object obj) {
                return true;
            }

            public void mo6539a(View view, Object obj) {
                if (this.f22466a.getActivity() != null) {
                    ((MediaPlayingActivity) this.f22466a.getActivity()).c(false);
                }
            }
        }, new OnClickListener(this) {
            final /* synthetic */ NowPlayingFragment f22467a;

            {
                this.f22467a = r1;
            }

            public void onClick(View view) {
                this.f22467a.m23919Y();
            }
        }));
        if (this.ay != null) {
            if (UserManager.a().Q()) {
                this.bh = false;
            } else if (!UserManager.a().P()) {
                this.bh = UserManager.a().i(this.ay.performanceKey);
            } else if (UserManager.a().f() == 0) {
                Log.e(aP, "accountId is 0");
            } else {
                PerformanceManager.a().a(UserManager.a().f(), Integer.valueOf(0), Integer.valueOf(25), new PerformanceManager$PerformancesByPerformerResponseCallback(this) {
                    final /* synthetic */ NowPlayingFragment f22468a;

                    {
                        this.f22468a = r1;
                    }

                    public void handleResponse(PerformancesByPerformerResponse performancesByPerformerResponse) {
                        if (this.f22468a.isAdded() && performancesByPerformerResponse.a()) {
                            Iterator it = performancesByPerformerResponse.mPerformances.iterator();
                            while (it.hasNext()) {
                                UserManager.a().g(((PerformanceV2) it.next()).performanceKey);
                            }
                            this.f22468a.bh = UserManager.a().i(this.f22468a.ay.performanceKey);
                            UserManager.a().c(performancesByPerformerResponse.mPerformances.isEmpty());
                            this.f22468a.az();
                        }
                    }
                });
            }
            if (UserManager.a().l(this.ay.performanceKey)) {
                this.bi = UserManager.a().m(this.ay.performanceKey);
                aA();
            } else {
                PerformanceManager.a().a(this.ay.performanceKey, new PerformanceManager$IsBookmarkSeedResponseCallback(this) {
                    final /* synthetic */ NowPlayingFragment f22469a;

                    {
                        this.f22469a = r1;
                    }

                    public void handleResponse(IsBookmarkSeedResponse isBookmarkSeedResponse) {
                        if (this.f22469a.isAdded() && isBookmarkSeedResponse.a()) {
                            this.f22469a.bi = isBookmarkSeedResponse.mIsBookmarkSeed.booleanValue();
                            if (this.f22469a.bi) {
                                UserManager.a().j(this.f22469a.ay.performanceKey);
                            } else {
                                UserManager.a().k(this.f22469a.ay.performanceKey);
                            }
                            this.f22469a.aA();
                        }
                    }
                });
            }
        }
        ap();
    }

    public boolean mo6544a(int i, KeyEvent keyEvent) {
        if (!isAdded()) {
            return false;
        }
        if (i == 4 && this.aH) {
            Log.b(aP, "onFragmentKeyDown - closing all fragments.");
            mo6843b(true, true);
            this.aH = false;
            return true;
        } else if (i == 4 && this.ap.getVisibility() == 0) {
            at();
            return true;
        } else if (i == 4 && this.ak.getVisibility() == 0) {
            au();
            return true;
        } else if (i == 4 && this.bf != null && ax()) {
            return true;
        } else {
            Log.b(aP, "onFragmentKeyDown - mIsInFullMode: " + this.A);
            if (i != 4 || !this.A) {
                return false;
            }
            m23918X();
            return true;
        }
    }

    private void aG() {
        this.f22498L.setVisibility(4);
        this.f22497K.setText(getString(C1947R.string.now_playing_load_error));
    }

    public void m23937d(boolean z) {
        this.aH = z;
    }

    public void m23918X() {
        m23940e(true);
    }

    public void m23940e(boolean z) {
        mo6843b(z, false);
    }

    @UiThread
    public void mo6843b(boolean z, boolean z2) {
        mo6842a(z, z2, null);
    }

    @UiThread
    public void mo6842a(final boolean z, boolean z2, final HashtagCallback hashtagCallback) {
        if (!isAdded()) {
            return;
        }
        if (this.A) {
            if (!(z2 || hashtagCallback == null)) {
                Log.e(aP, "if you set a callback, lowerAll should be true");
            }
            Log.b(aP, "lowerFragment - begin");
            final AnimatorListenerAdapter anonymousClass47 = new AnimatorListenerAdapter(this) {
                final /* synthetic */ NowPlayingFragment f22471b;

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    if (hashtagCallback != null) {
                        hashtagCallback.mo6863a();
                    }
                }
            };
            if (av()) {
                Log.d(aP, "lowerFragment - mCommentsListDialog is visible, so fading that view out and aborting lowering the fragment");
                if (z2) {
                    this.bf.setOnDismissListener(new OnDismissListener(this) {
                        final /* synthetic */ NowPlayingFragment f22474c;

                        public void onDismiss(DialogInterface dialogInterface) {
                            this.f22474c.mo6829a(z ? AnimationDirection.LOWER_SHOW_BOTTOM_MENU : AnimationDirection.LOWER, anonymousClass47);
                        }
                    });
                }
                ax();
                return;
            }
            if (this.ak.getVisibility() == 0) {
                Log.d(aP, "lowerFragment - mLovesView is visible, so fading that view out and aborting lowering the fragment");
                au();
                if (!z2) {
                    return;
                }
            }
            if (this.ap.getVisibility() == 0) {
                Log.d(aP, "lowerFragment - mJoinersView is visible, so fading that view out and aborting lowering the fragment");
                at();
                if (!z2) {
                    return;
                }
            }
            mo6829a(z ? AnimationDirection.LOWER_SHOW_BOTTOM_MENU : AnimationDirection.LOWER, anonymousClass47);
            return;
        }
        Log.b(aP, "lowerFragment - was not in full mode");
        if (hashtagCallback != null) {
            hashtagCallback.mo6863a();
        }
    }

    @UiThread
    public void mo6845f(boolean z) {
        Log.b(aP, "lowerFragmentRetainState - begin");
        if (av()) {
            ay();
        }
        mo6829a(z ? AnimationDirection.LOWER_SHOW_BOTTOM_MENU : AnimationDirection.LOWER, null);
    }

    public void m23936d(final String str) {
        mo6829a(AnimationDirection.RAISE, new AnimatorListenerAdapter(this) {
            final /* synthetic */ NowPlayingFragment f22476b;

            public void onAnimationEnd(Animator animator) {
                this.f22476b.m23881g(str);
            }
        });
        this.aH = false;
    }

    public void m23939e(String str) {
        m23881g(str);
    }

    public void m23919Y() {
        m23935d(null);
    }

    public void m23935d(final Runnable runnable) {
        Log.b(aP, "nowPlayingMiniBarClicked - begin : " + this.ay);
        if (this.ay != null && isAdded() && !m20373F()) {
            MasterActivity b = MasterActivity.b(getActivity());
            if (b != null) {
                b.u();
            }
            mo6829a(AnimationDirection.RAISE, new AnimatorListenerAdapter(this) {
                final /* synthetic */ NowPlayingFragment f22481b;

                public void onAnimationEnd(Animator animator) {
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
        }
    }

    protected void mo6829a(AnimationDirection animationDirection, AnimatorListenerAdapter animatorListenerAdapter) {
        if (animationDirection == AnimationDirection.RAISE) {
            this.aR = Mode.MAXIMIZED;
            m23910P();
        } else {
            this.aR = Mode.MINIMIZED;
        }
        super.mo6829a(animationDirection, animatorListenerAdapter);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            Log.e(aP, "Bad result code, " + i2 + ", returned for request code: " + i);
        } else if (intent == null) {
            Log.d(aP, "No data returned to onActivityResult");
        } else {
            int intExtra = intent.getIntExtra("CHANGE_MADE_CODE", -1);
            if (intExtra == 6801) {
                Log.b(aP, "Result code indicates open call was closed; closing NowPlayingFragment");
                mo6830a(this);
            } else if (intExtra == 6802) {
                Log.b(aP, "Result code indicates performance was deleted; closing NowPlayingFragment");
                mo6830a(this);
            } else if (intExtra == 6803 || intExtra == 6804) {
                Bundle extras = intent.getExtras();
                if (extras != null && extras.containsKey("CHANGE_MADE_PERFORMANCE")) {
                    PerformanceV2 performanceV2 = (PerformanceV2) extras.getParcelable("CHANGE_MADE_PERFORMANCE");
                    if (performanceV2 != null && m19862m() != null) {
                        m19862m().a(performanceV2, true, true);
                        return;
                    } else if (performanceV2 == null) {
                        mo6830a(this);
                    }
                }
                this.ay = null;
                this.aS = false;
            }
        }
    }

    protected void mo6830a(NowPlayingFragment nowPlayingFragment) {
        this.ay = null;
        super.mo6830a(nowPlayingFragment);
    }

    public void mo6457a(boolean z, boolean z2) {
        if (this.ap.getVisibility() == 0) {
            this.bw.notifyDataSetChanged();
        }
    }

    public void mo6456a(ProfileFragment profileFragment) {
        mo6487a((BaseFragment) profileFragment);
        mo6845f(true);
    }

    public void mo6455a(SearchResultClkContext searchResultClkContext) {
    }

    protected void mo6831d(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        super.mo6831d(mediaPlayerServiceController, str);
        if (this.ay != null && str != null && this.ay.performanceKey.equals(str)) {
            SingAnalytics.m26098a(this.ay.performanceKey, !this.ay.closed ? PerformanceStatus.ACTIVE : PerformanceStatus.CLOSED, PerformanceV2Util.m25947e(this.ay), PerformanceType.m21630a(this.ay.ensembleType).m21631a(), null, aF(), this.ay.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO);
            PerformanceManager.a().c(this.ay.performanceKey, null);
        }
    }

    protected void mo6545e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        super.mo6545e(mediaPlayerServiceController, str);
        aG();
    }

    protected void mo6832f(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        super.mo6832f(mediaPlayerServiceController, str);
        if (mo6543a() && getActivity() != null && this.g != null) {
            this.g.setImageDrawable(getResources().getDrawable(C1947R.drawable.btn_playingbar_play_gray));
        }
    }

    public void m23926a(Long l) {
        if (l != null) {
            this.bl = l;
        }
    }
}
