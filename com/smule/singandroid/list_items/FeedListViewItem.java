package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.mopub.volley.DefaultRetryPolicy;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.FeedListItem$ActionType;
import com.smule.android.network.models.FeedListItem$SourceType;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.StringCacheManager;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.dialogs.CommentsListDialog;
import com.smule.singandroid.dialogs.CommentsListDialog.CommentsListDialogListener;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.hashtag.CustomLinkMovementMethod;
import com.smule.singandroid.hashtag.CustomLinkMovementMethod.TextClickedListener;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.ShareUtils;
import java.text.MessageFormat;
import java.util.regex.Pattern;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.HttpResponseCode;

@EViewGroup
public class FeedListViewItem extends MediaPlayingListItem {
    private static final String ag = FeedListViewItem.class.getSimpleName();
    @ViewById
    protected View f22925A;
    @ViewById
    protected ImageView f22926B;
    @ViewById
    protected RippleBackground f22927C;
    @ViewById
    protected FrameLayout f22928D;
    @ViewById
    protected TextureView f22929E;
    @ViewById
    protected View f22930F;
    @ViewById
    protected View f22931G;
    @ViewById
    protected TextView f22932H;
    @ViewById
    protected View f22933I;
    @ViewById
    protected View f22934J;
    @ViewById
    protected TextView f22935K;
    @ViewById
    protected TextView f22936L;
    @ViewById
    protected TextView f22937M;
    @ViewById
    protected TextView f22938N;
    @ViewById
    protected View f22939O;
    @ViewById
    protected View f22940P;
    @ViewById
    protected View f22941Q;
    @ViewById
    protected ImageView f22942R;
    @ViewById
    protected View f22943S;
    FeedListItem f22944T;
    protected boolean f22945U = false;
    protected int f22946V;
    protected boolean f22947W;
    @ViewById
    protected ImageView f22948a;
    protected BaseFragment aa;
    protected PerformanceV2 ab;
    protected ArrangementVersionLite ac;
    protected FeedListItemFeedback ad;
    private LocalizedShortNumberFormatter ah;
    @ViewById
    protected ProfileImageWithVIPBadge f22949b;
    @ViewById
    protected ProfileImageWithVIPBadge f22950c;
    @ViewById
    protected ProfileImageWithVIPBadge f22951d;
    @ViewById
    protected ImageView f22952e;
    @ViewById
    protected TextView f22953f;
    @ViewById
    protected ProfileImageWithVIPBadge f22954g;
    protected ImageUtils$ImageViewLoadOptimizer f22955h = new ImageUtils$ImageViewLoadOptimizer();
    @ViewById
    protected ImageView f22956i;
    @ViewById
    protected TextView f22957j;
    @ViewById
    protected View f22958k;
    @ViewById
    protected TextView f22959l;
    @ViewById
    protected TextView f22960m;
    @ViewById
    protected View f22961n;
    @ViewById
    protected TextView f22962o;
    @ViewById
    protected ImageButton f22963p;
    @ViewById
    protected ProgressBar f22964q;
    @ViewById
    protected View f22965r;
    @ViewById
    protected TextView f22966s;
    @ViewById
    protected TextView f22967t;
    @ViewById
    protected TextView f22968u;
    @ViewById
    protected TextView f22969v;
    @ViewById
    protected TextView f22970w;
    @ViewById
    protected TextView f22971x;
    @ViewById
    protected Button f22972y;
    @ViewById
    protected ImageView f22973z;

    public interface FeedListItemFeedback {
        void mo6432a();

        void mo6433a(ItemClickType itemClickType);

        void mo6434a(AccountIcon accountIcon);

        void mo6435a(ArrangementVersionLite arrangementVersionLite);

        void mo6436a(PerformanceV2 performanceV2);

        void mo6437b(ArrangementVersionLite arrangementVersionLite);

        void mo6438b(PerformanceV2 performanceV2);

        void mo6439c(PerformanceV2 performanceV2);
    }

    public interface FeedListViewItemClickCallback {
        void mo6440a();
    }

    class C46494 implements OnClickListener {
        final /* synthetic */ FeedListViewItem f22904a;

        C46494(FeedListViewItem feedListViewItem) {
            this.f22904a = feedListViewItem;
        }

        public void onClick(View view) {
            this.f22904a.ad.mo6433a(ItemClickType.SHARE);
            this.f22904a.ad.mo6437b(this.f22904a.ac);
        }
    }

    class C46505 implements OnClickListener {
        final /* synthetic */ FeedListViewItem f22905a;

        C46505(FeedListViewItem feedListViewItem) {
            this.f22905a = feedListViewItem;
        }

        public void onClick(View view) {
            this.f22905a.ad.mo6435a(this.f22905a.ac);
        }
    }

    class C46516 implements OnClickListener {
        final /* synthetic */ FeedListViewItem f22906a;

        C46516(FeedListViewItem feedListViewItem) {
            this.f22906a = feedListViewItem;
        }

        public void onClick(View view) {
            this.f22906a.ad.mo6434a(this.f22906a.ac.accountIcon);
        }
    }

    class C46549 implements TextClickedListener {
        final /* synthetic */ FeedListViewItem f22911a;

        C46549(FeedListViewItem feedListViewItem) {
            this.f22911a = feedListViewItem;
        }

        public void mo6871a() {
            this.f22911a.callOnClick();
        }
    }

    private class DroidSing8362Exception extends Exception {
        final /* synthetic */ FeedListViewItem f22912a;

        public DroidSing8362Exception(FeedListViewItem feedListViewItem, String str) {
            this.f22912a = feedListViewItem;
            super(str);
        }
    }

    public class ExpandAnimation extends Animation {
        final /* synthetic */ FeedListViewItem f22913a;
        private LayoutParams f22914b;
        private int f22915c;
        private int f22916d;
        private float f22917e = ((float) this.f22914b.height);
        private float f22918f;
        private float f22919g;
        private float f22920h;
        private float f22921i;
        private float f22922j;
        private float f22923k;
        private float f22924l;

        public ExpandAnimation(FeedListViewItem feedListViewItem, View view, int i, boolean z) {
            this.f22913a = feedListViewItem;
            setDuration((long) i);
            Resources resources = feedListViewItem.getResources();
            this.f22914b = (LayoutParams) feedListViewItem.f22927C.getLayoutParams();
            if (z) {
                this.f22918f = resources.getDimension(C1947R.dimen.feed_art_container_height_expanded);
                this.f22919g = (float) feedListViewItem.f22927C.getWidth();
                this.f22920h = resources.getDimension(C1947R.dimen.feed_art_collapsed_size);
                this.f22921i = (float) feedListViewItem.f22927C.getHeight();
                this.f22922j = resources.getDimension(C1947R.dimen.feed_art_collapsed_size);
                this.f22923k = 0.0f;
                this.f22924l = (float) feedListViewItem.f22946V;
                if (feedListViewItem.f22944T == null || feedListViewItem.f22944T.a() != FeedListItem$ActionType.COMMENT) {
                    this.f22915c = 1;
                    this.f22916d = 1;
                    return;
                }
                this.f22915c = 2;
                this.f22916d = 6;
                return;
            }
            this.f22918f = resources.getDimension(C1947R.dimen.feed_art_container_height_collapsed);
            this.f22919g = resources.getDimension(C1947R.dimen.feed_art_collapsed_size);
            this.f22920h = (float) feedListViewItem.f22927C.getWidth();
            this.f22921i = resources.getDimension(C1947R.dimen.feed_art_collapsed_size);
            this.f22922j = resources.getDimension(C1947R.dimen.feed_art_container_height_collapsed);
            this.f22923k = (float) feedListViewItem.f22946V;
            this.f22924l = 0.0f;
            if (feedListViewItem.f22944T == null || feedListViewItem.f22944T.a() != FeedListItem$ActionType.COMMENT) {
                this.f22915c = 1;
                this.f22916d = 1;
                return;
            }
            this.f22915c = 6;
            this.f22916d = 2;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            this.f22914b.height = m24288a(f, this.f22917e, this.f22918f);
            this.f22913a.ae.getLayoutParams().height = m24288a(f, this.f22921i, this.f22922j);
            this.f22913a.ae.getLayoutParams().width = m24288a(f, this.f22919g, this.f22920h);
            this.f22913a.f22925A.getLayoutParams().height = m24288a(f, this.f22923k, this.f22924l);
            this.f22913a.f22957j.setMaxLines(m24288a(f, (float) this.f22915c, (float) this.f22916d));
            this.f22913a.f22927C.requestLayout();
            if (((double) f) >= 1.0d) {
                this.f22913a.f22927C.m23476b();
            }
        }

        private int m24288a(float f, float f2, float f3) {
            return (int) (((f3 - f2) * f) + f2);
        }
    }

    public FeedListViewItem(Context context) {
        super(context);
    }

    public static FeedListViewItem m24290a(Context context) {
        return FeedListViewItem_.m24327b(context);
    }

    public boolean m24313c() {
        return this.f22944T.c();
    }

    public void setIsExpanded(boolean z) {
        this.f22947W = z;
    }

    public boolean m24314d() {
        return this.f22947W;
    }

    private String m24291a(FeedListItem feedListItem) {
        if (feedListItem.a() == null) {
            return null;
        }
        Resources resources = getResources();
        switch (feedListItem.a()) {
            case COMMENT:
                if (feedListItem.count == 1) {
                    return String.format(resources.getString(C1947R.string.news_feed_comment_one), new Object[]{feedListItem.subject.handle, feedListItem.content});
                }
                return resources.getQuantityString(C1947R.plurals.news_feed_comment_plural, feedListItem.count - 1, new Object[]{Integer.valueOf(feedListItem.count - 1), feedListItem.subject.handle});
            case LOVE:
                if (feedListItem.count == 1) {
                    return String.format(resources.getString(C1947R.string.news_feed_love_one), new Object[]{feedListItem.subject.handle});
                }
                return resources.getQuantityString(C1947R.plurals.news_feed_love_plural, feedListItem.count - 1, new Object[]{Integer.valueOf(feedListItem.count - 1), feedListItem.subject.handle});
            case JOIN:
                if (feedListItem.count == 1) {
                    return String.format(resources.getString(C1947R.string.news_feed_join_one), new Object[]{feedListItem.subject.handle});
                }
                return resources.getQuantityString(C1947R.plurals.news_feed_join_plural, feedListItem.count - 1, new Object[]{Integer.valueOf(feedListItem.count - 1), feedListItem.subject.handle});
            case FOLLOW:
            case OPENCALL:
            case PERFORM:
            case INVITE:
            case FAVORITE:
                return null;
            case CREATE_ARR:
                return resources.getString(C1947R.string.news_new_song_from_your_network);
            default:
                return null;
        }
    }

    private String m24299b(FeedListItem feedListItem) {
        switch (feedListItem.b()) {
            case MYNW:
                return getResources().getString(C1947R.string.news_new_from_your_network);
            default:
                return getResources().getString(C1947R.string.news_recommended_for_you);
        }
    }

    private int m24303c(FeedListItem feedListItem) {
        switch (feedListItem.b()) {
            case MYNW:
                switch (feedListItem.a()) {
                    case CREATE_ARR:
                        return C1947R.drawable.icn_feed_song;
                    default:
                        return C1947R.drawable.icn_feed_new;
                }
            default:
                return C1947R.drawable.icn_feed_recommended_for_you;
        }
    }

    private void m24304v() {
        LayoutParams layoutParams = (LayoutParams) this.f22956i.getLayoutParams();
        if (this.f22953f.getVisibility() == 0) {
            layoutParams.topMargin = 0;
            this.f22957j.setPadding(0, 0, 0, 0);
        } else {
            layoutParams.topMargin = getResources().getDimensionPixelSize(C1947R.dimen.margin_small);
            this.f22957j.setPadding(0, getResources().getDimensionPixelSize(C1947R.dimen.margin_small), 0, 0);
        }
        this.f22956i.setLayoutParams(layoutParams);
    }

    public void m24307a(final BaseFragment baseFragment, final FeedListItem feedListItem, boolean z, FeedListItemFeedback feedListItemFeedback) {
        boolean z2;
        Log.b(ag, "bindToFeedItem : " + feedListItem);
        this.ab = null;
        this.ad = feedListItemFeedback;
        this.ac = null;
        this.f22944T = feedListItem;
        this.f22947W = z;
        this.aa = baseFragment;
        setDescendantFocusability(393216);
        if (feedListItem.actionTime > 0) {
            this.f22953f.setVisibility(0);
            this.f22953f.setText(MiscUtils.m25887a(feedListItem.actionTime, false, true, false));
        } else if (feedListItem.object == null || feedListItem.object.performanceIcon == null) {
            this.f22953f.setVisibility(8);
        } else {
            this.f22953f.setVisibility(0);
            this.f22953f.setText(MiscUtils.m25887a((long) feedListItem.object.performanceIcon.createdAt, false, true, false));
        }
        String a = m24291a(feedListItem);
        if (a != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (a == null) {
            if (feedListItem.b() != FeedListItem$SourceType.MYNW) {
                this.f22953f.setVisibility(8);
            }
            a = m24299b(feedListItem);
        }
        a = a.replaceAll(Pattern.quote("<b>"), "<font color=\"#3d3839\"><b>").replaceAll(Pattern.quote("</b>"), "</b></font>");
        if (!(feedListItem == null || feedListItem.subject == null || !feedListItem.subject.c() || !z2 || this.f22944T.c())) {
            a = "*" + a;
        }
        Spanned fromHtml = Html.fromHtml(a);
        CharSequence spannableString = new SpannableString(fromHtml);
        if (!z2 || this.f22944T.c()) {
            this.f22956i.setImageDrawable(getResources().getDrawable(m24303c(feedListItem)));
            this.f22956i.setVisibility(0);
            this.f22954g.setVisibility(8);
            if (feedListItem.b() == FeedListItem$SourceType.MYNW && feedListItem.a() == FeedListItem$ActionType.CREATE_ARR) {
                this.f22952e.setVisibility(8);
            } else {
                this.f22952e.setVisibility(0);
            }
        } else {
            OnClickListener c46451 = new OnClickListener(this) {
                final /* synthetic */ FeedListViewItem f22894c;

                public void onClick(View view) {
                    baseFragment.mo6487a(ProfileFragment.m21036a(feedListItem.subject));
                }
            };
            m24292a((SpannableString) spannableString, fromHtml, feedListItem.subject.handle, c46451);
            this.f22954g.setVisibility(0);
            this.f22954g.setAccount(feedListItem.subject);
            this.f22954g.setOnClickListener(c46451);
            this.f22956i.setVisibility(8);
            this.f22952e.setVisibility(0);
            if (feedListItem.subject.c()) {
                spannableString.setSpan(PerformanceV2Util.m25937a(getResources()), 0, 1, 17);
            }
        }
        m24298a(spannableString, this.f22957j, true);
        this.f22963p.setVisibility(8);
        this.f22964q.setVisibility(8);
        if (feedListItem.object != null) {
            if (feedListItem.c()) {
                this.ac = feedListItem.object.arrVersionLite;
                m24297a(baseFragment, feedListItem);
            } else {
                this.ab = feedListItem.object.performanceIcon;
                m24301b(baseFragment, feedListItem);
            }
            this.ae.m23378a(m23037a(this.ab), (int) C1947R.drawable.feed_filmstrip);
        } else {
            this.f22966s.setText("");
            this.f22967t.setText("");
            this.f22968u.setText("");
            this.f22950c.setVisibility(8);
            this.f22969v.setOnClickListener(null);
        }
        this.ae.m23377a(true);
        if (z) {
            m24317g();
        } else {
            m24318h();
        }
        m24304v();
    }

    @Click
    protected void m24315e() {
        if (this.ad != null) {
            this.ad.mo6439c(this.ab);
        }
    }

    private void m24292a(SpannableString spannableString, Spanned spanned, String str, final OnClickListener onClickListener) {
        C46462 c46462 = new ClickableSpan(this) {
            final /* synthetic */ FeedListViewItem f22900b;

            public void onClick(View view) {
                onClickListener.onClick(view);
            }

            public void updateDrawState(TextPaint textPaint) {
            }
        };
        int indexOf = spanned.toString().indexOf(str);
        if (indexOf > -1) {
            spannableString.setSpan(c46462, indexOf, str.length() + indexOf, 33);
        }
    }

    public void m24316f() {
        Log.b(ag, "configureVideoSurface");
        if (this.aa.isAdded()) {
            NowPlayingFragment U = ((MediaPlayingActivity) this.aa.getActivity()).U();
            if (U == null || !U.m20373F()) {
                MediaPlayerServiceController.m24641a().m24650a(this.aa.getActivity(), this.f22929E, this.f22930F, this.f22931G);
            }
        }
    }

    protected void m24317g() {
        Resources resources = getResources();
        this.f22927C.getLayoutParams().height = (int) resources.getDimension(C1947R.dimen.feed_art_container_height_expanded);
        this.ae.getLayoutParams().height = (int) resources.getDimension(C1947R.dimen.feed_art_collapsed_size);
        this.ae.getLayoutParams().width = (int) resources.getDimension(C1947R.dimen.feed_art_collapsed_size);
        if (m24313c()) {
            this.f22939O.getLayoutParams().height = this.f22946V;
        } else {
            this.f22925A.getLayoutParams().height = this.f22946V;
            this.f22967t.setMaxLines(1000);
        }
        String i = MediaPlayerServiceController.m24641a().m24673i();
        if (MediaPlayerServiceController.m24641a().m24676j() && i != null && i.equals(getMediaKey())) {
            if (m23037a(this.ab)) {
                m24310a(false);
            } else {
                m24321k();
                this.f22927C.m23474a();
                if (this.ac != null) {
                    this.ae.f21808m.setVisibility(0);
                }
            }
        } else if (m23037a(this.ab)) {
            m24310a(MediaPlayerServiceController.m24641a().m24680l());
        } else {
            m24321k();
            this.f22927C.m23476b();
            if (this.ac != null) {
                this.ae.f21808m.setVisibility(8);
            }
        }
        if (this.f22944T == null || this.f22944T.a() != FeedListItem$ActionType.COMMENT) {
            this.f22957j.setMaxLines(1);
        } else {
            this.f22957j.setMaxLines(6);
        }
        this.f22933I.setVisibility(8);
    }

    protected void m24318h() {
        Resources resources = getResources();
        this.f22927C.getLayoutParams().height = (int) resources.getDimension(C1947R.dimen.feed_art_container_height_collapsed);
        this.ae.getLayoutParams().height = -1;
        this.ae.getLayoutParams().width = -1;
        this.f22929E.setVisibility(8);
        this.f22930F.setVisibility(8);
        this.f22931G.setVisibility(8);
        this.ae.f21808m.setVisibility(8);
        if (m24313c()) {
            this.f22939O.getLayoutParams().height = 0;
        } else {
            this.f22925A.getLayoutParams().height = 0;
            this.f22967t.setMaxLines(2);
        }
        this.f22927C.m23476b();
        if (this.f22944T == null || this.f22944T.a() != FeedListItem$ActionType.COMMENT) {
            this.f22957j.setMaxLines(1);
        } else {
            this.f22957j.setMaxLines(2);
        }
        if (this.ab != null && this.ab.n()) {
            this.f22933I.setVisibility(0);
            this.f22932H.setText(MessageFormat.format(getResources().getString(C1947R.string.news_feed_join_time_left), new Object[]{MiscUtils.m25886a(this.ab.expireAt, false, false)}));
        } else if (this.f22944T == null || !this.f22944T.c()) {
            this.f22933I.setVisibility(8);
        } else {
            this.f22933I.setVisibility(0);
            this.f22932H.setText(getResources().getString(C1947R.string.news_sing_it_now));
        }
        m24321k();
    }

    public void m24319i() {
        this.ad.mo6433a(ItemClickType.LISTEN);
    }

    public void m24320j() {
        this.ad.mo6433a(ItemClickType.PREVIEW);
    }

    private void m24297a(BaseFragment baseFragment, FeedListItem feedListItem) {
        final long j = this.ac.accountIcon.accountId;
        this.f22963p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22903b;

            class C46471 implements ToggleFollowStateListener {
                final /* synthetic */ C46483 f22901a;

                C46471(C46483 c46483) {
                    this.f22901a = c46483;
                }

                public void mo6399a(boolean z, boolean z2, boolean z3) {
                    this.f22901a.f22903b.mo6875a(z, z3, true);
                    if (this.f22901a.f22903b.ad != null) {
                        this.f22901a.f22903b.ad.mo6432a();
                    }
                }
            }

            public void onClick(View view) {
                this.f22903b.ad.mo6433a(FollowManager.m18204a().m18222a(j) ? ItemClickType.UNFOLLOW : ItemClickType.FOLLOW);
                this.f22903b.f22964q.setVisibility(0);
                this.f22903b.f22963p.setVisibility(8);
                FollowManager.m18204a().m18215a(Long.valueOf(j), new C46471(this));
            }
        });
        mo6875a(true, false, false);
        this.f22965r.setVisibility(8);
        this.f22967t.setVisibility(8);
        this.f22925A.setVisibility(8);
        this.f22952e.setVisibility(8);
        this.f22934J.setVisibility(0);
        m24305w();
        this.f22939O.setVisibility(0);
        this.f22941Q.setOnClickListener(new C46494(this));
        this.f22940P.setOnClickListener(new C46505(this));
        this.f22939O.measure(-1, -2);
        this.f22946V = this.f22939O.getMeasuredHeight();
        m24293a(this.ac.accountIcon, new C46516(this));
        m24302b(this.ac.coverUrl);
        m23036a(this.ac.key);
    }

    private void m24305w() {
        Drawable drawable;
        int color;
        this.f22935K.setText(this.ac.c());
        String str = (this.ac.name == null || this.ac.name.length() <= 0) ? "" : this.ac.artist;
        if (str.length() > 0) {
            this.f22936L.setText(this.ac.artist);
        } else {
            this.f22936L.setVisibility(8);
        }
        if (this.ac.lyrics) {
            this.f22938N.setVisibility(8);
        } else {
            this.f22938N.setText(getResources().getString(C1947R.string.preview_no_lyrics));
            this.f22938N.setVisibility(0);
        }
        if (this.ac.rating == null || this.ac.totalVotes < 3) {
            this.f22937M.setText(C1947R.string.songbook_no_ratings);
            drawable = getResources().getDrawable(C1947R.drawable.icn_thumbs_up_gray);
            color = getContext().getResources().getColor(C1947R.color.body_text_light_grey);
        } else {
            this.f22937M.setText(((int) (this.ac.rating.floatValue() * 100.0f)) + "% (" + getLocalizedFormatter().m18998a((long) this.ac.totalVotes) + ")");
            if (this.ac.highlyRated) {
                drawable = getResources().getDrawable(C1947R.drawable.icn_thumbs_up_green);
                color = getContext().getResources().getColor(C1947R.color.cccp_highly_rated);
            } else {
                drawable = getResources().getDrawable(C1947R.drawable.icn_thumbs_up_gray);
                color = getContext().getResources().getColor(C1947R.color.body_text_light_grey);
            }
        }
        this.f22937M.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        this.f22937M.setTextColor(color);
        this.f22937M.setVisibility(0);
    }

    public void m24309a(final FeedListViewItemClickCallback feedListViewItemClickCallback, final FeedListViewItemClickCallback feedListViewItemClickCallback2) {
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22908b;

            public void onClick(View view) {
                feedListViewItemClickCallback.mo6440a();
            }
        });
        this.f22943S.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22910b;

            public void onClick(View view) {
                feedListViewItemClickCallback2.mo6440a();
            }
        });
    }

    private void m24298a(CharSequence charSequence, TextView textView, boolean z) {
        SpannableString spannableString;
        if (charSequence instanceof SpannableString) {
            spannableString = (SpannableString) charSequence;
        } else {
            spannableString = new SpannableString(charSequence);
        }
        if (charSequence instanceof Spanned) {
            TextUtils.copySpansFrom((Spanned) charSequence, 0, charSequence.length(), null, spannableString, 0);
        }
        Hashtag.m24195a((MediaPlayingActivity) getContext(), spannableString, z ? "fonts/ProximaNova-Bold.ttf" : "fonts/ProximaNova-Semibold.ttf");
        MovementMethod customLinkMovementMethod = new CustomLinkMovementMethod();
        customLinkMovementMethod.m24186a(new C46549(this));
        textView.setText(spannableString, BufferType.SPANNABLE);
        textView.setMovementMethod(customLinkMovementMethod);
        textView.setHighlightColor(0);
    }

    protected void m24308a(final BaseFragment baseFragment, final PerformanceV2 performanceV2) {
        this.ab = performanceV2;
        this.f22965r.setVisibility(0);
        this.f22967t.setVisibility(0);
        this.f22925A.setVisibility(0);
        this.f22934J.setVisibility(8);
        this.f22939O.setVisibility(8);
        String str = performanceV2.performanceKey;
        this.f22945U = StringCacheManager.a().b(performanceV2.performanceKey);
        this.f22966s.setText(performanceV2.title);
        if (performanceV2.message == null || performanceV2.message.isEmpty()) {
            this.f22967t.setVisibility(8);
        } else {
            this.f22967t.setVisibility(0);
            m24298a(performanceV2.message, this.f22967t, false);
        }
        this.f22968u.setText(getResources().getQuantityString(C1947R.plurals.news_feed_num_plays, performanceV2.totalListens, new Object[]{getLocalizedFormatter().m18999a((long) performanceV2.totalListens, (long) getResources().getInteger(C1947R.integer.long_form_threshold))}));
        m24300b(performanceV2);
        this.f22970w.setText(getLocalizedFormatter().m18999a((long) performanceV2.totalComments, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        OnClickListener anonymousClass10 = new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22868b;

            public void onClick(View view) {
                this.f22868b.ad.mo6434a(performanceV2.accountIcon);
            }
        };
        PerformanceV2Util.m25939a(getResources(), this.f22962o, performanceV2.accountIcon);
        final long j = performanceV2.accountIcon.accountId;
        this.f22963p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22871b;

            class C46411 implements ToggleFollowStateListener {
                final /* synthetic */ AnonymousClass11 f22869a;

                C46411(AnonymousClass11 anonymousClass11) {
                    this.f22869a = anonymousClass11;
                }

                public void mo6399a(boolean z, boolean z2, boolean z3) {
                    this.f22869a.f22871b.mo6875a(z, z3, true);
                    if (this.f22869a.f22871b.ad != null) {
                        this.f22869a.f22871b.ad.mo6432a();
                    }
                }
            }

            public void onClick(View view) {
                this.f22871b.ad.mo6433a(FollowManager.m18204a().m18222a(j) ? ItemClickType.UNFOLLOW : ItemClickType.FOLLOW);
                this.f22871b.f22964q.setVisibility(0);
                this.f22871b.f22963p.setVisibility(8);
                FollowManager.m18204a().m18215a(Long.valueOf(j), new C46411(this));
            }
        });
        mo6875a(true, false, false);
        this.f22969v.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22875b;

            public void onClick(View view) {
                if (!this.f22875b.f22945U) {
                    this.f22875b.f22969v.setActivated(true);
                    this.f22875b.ad.mo6433a(ItemClickType.LOVE);
                    final MasterActivity masterActivity = (MasterActivity) baseFragment.getActivity();
                    if (masterActivity.U() != null) {
                        masterActivity.J();
                    } else {
                        masterActivity.a(this.f22875b.ab, true, false, new Runnable(this) {
                            final /* synthetic */ AnonymousClass12 f22873b;

                            public void run() {
                                if (baseFragment.isAdded()) {
                                    masterActivity.J();
                                }
                            }
                        });
                    }
                }
            }
        });
        m24294a(performanceV2, anonymousClass10);
        m24302b(performanceV2.coverUrl);
        m23036a(str);
        setupShare(baseFragment);
        setupComment(baseFragment);
    }

    private void m24301b(BaseFragment baseFragment, FeedListItem feedListItem) {
        m24308a(baseFragment, feedListItem.object.performanceIcon);
    }

    private void m24294a(PerformanceV2 performanceV2, OnClickListener onClickListener) {
        this.f22951d.setVisibility(8);
        this.f22960m.setVisibility(8);
        if (this.ab.n()) {
            if (this.ab.e()) {
                setupGroup(performanceV2);
            } else {
                setupDuetSeed(onClickListener);
            }
            setupJoinButton(this.ab);
        } else {
            if (PerformanceV2Util.m25945c(this.ab)) {
                setupDuetSeed(onClickListener);
            } else if (this.ab.d()) {
                this.f22959l.setOnClickListener(onClickListener);
                this.f22950c.setVisibility(0);
                this.f22951d.setVisibility(0);
                final AccountIcon accountIcon = ((Track) this.ab.recentTracks.get(0)).accountIcon;
                OnClickListener anonymousClass13 = new OnClickListener(this) {
                    final /* synthetic */ FeedListViewItem f22877b;

                    public void onClick(View view) {
                        this.f22877b.ad.mo6434a(accountIcon);
                    }
                };
                this.f22950c.m23395a(performanceV2.accountIcon, onClickListener);
                this.f22951d.m23395a(accountIcon, anonymousClass13);
                this.f22960m.setOnClickListener(anonymousClass13);
                m24295a(this.ab, accountIcon);
            } else if (this.ab.e()) {
                setupGroup(performanceV2);
                if (performanceV2.totalPerformers > 1) {
                    OnClickListener anonymousClass14 = new OnClickListener(this) {
                        final /* synthetic */ FeedListViewItem f22878a;

                        {
                            this.f22878a = r1;
                        }

                        public void onClick(View view) {
                            this.f22878a.ad.mo6436a(this.f22878a.ab);
                        }
                    };
                    this.f22951d.setOnClickListener(anonymousClass14);
                    this.f22960m.setOnClickListener(anonymousClass14);
                } else {
                    this.f22951d.m23394a(null);
                    this.f22951d.setOnClickListener(null);
                    this.f22960m.setOnClickListener(null);
                }
            } else {
                this.f22951d.setVisibility(8);
                m24293a(performanceV2.accountIcon, onClickListener);
            }
            setupJoinButton(this.ab);
        }
        this.f22925A.measure(-1, -2);
        this.f22946V = this.f22925A.getMeasuredHeight();
    }

    private void m24302b(final String str) {
        if (!(str == null || str.equals(this.ae.getTag()))) {
            ImageUtils.a(str, this.ae.f21796a, C1947R.drawable.icn_default_album_large, new SimpleImageLoadingListener(this) {
                final /* synthetic */ FeedListViewItem f22880b;

                public void mo6155a(String str, View view, Bitmap bitmap) {
                    Drawable drawable = null;
                    int width = (int) (((float) bitmap.getWidth()) * 0.025f);
                    int height = (int) (((float) bitmap.getHeight()) * 0.025f);
                    if (width <= 0) {
                        width = bitmap.getWidth();
                    }
                    if (height <= 0) {
                        height = bitmap.getHeight();
                    }
                    if (width > 0 && height > 0) {
                        drawable = new BitmapDrawable(this.f22880b.getResources(), Bitmap.createScaledBitmap(bitmap, width, height, true));
                    }
                    if (drawable == null) {
                        MagicCrittercism.a(new DroidSing8362Exception(this.f22880b, "Invalid image: " + str));
                    }
                    this.f22880b.f22926B.setImageDrawable(drawable);
                }
            });
        }
        this.ae.setTag(str);
    }

    public void m24306a(BaseFragment baseFragment) {
        if (m24313c()) {
            SongbookEntry a = SongbookEntry.m18744a(this.ac);
            m24321k();
            baseFragment.mo6443a(a);
            return;
        }
        if (m23037a(this.ab)) {
            m24310a(true);
        } else {
            m24321k();
        }
        this.f22930F.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        ((MediaPlayingActivity) baseFragment.getActivity()).a(this.ab, false, true);
    }

    public void m24310a(boolean z) {
        int i = 0;
        this.f22929E.setVisibility(0);
        View view = this.f22930F;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        this.f22928D.setVisibility(8);
        m24316f();
    }

    public void m24321k() {
        this.f22929E.setVisibility(8);
        this.f22930F.setVisibility(8);
        this.f22928D.setVisibility(0);
    }

    public void m24322l() {
        this.ae.m23379b();
    }

    public ExpandAnimation getExpandViewAnimation() {
        return new ExpandAnimation(this, this, HttpResponseCode.INTERNAL_SERVER_ERROR, true);
    }

    public Animation getCollapseViewAnimation() {
        return new ExpandAnimation(this, this, HttpResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    private void setupJoinButton(final PerformanceV2 performanceV2) {
        if (performanceV2.n() || !PerformanceV2Util.m25941a(performanceV2.songUid)) {
            int i;
            if (performanceV2.d() && performanceV2.n()) {
                i = C1947R.drawable.icn_duet;
            } else if (performanceV2.e() && performanceV2.n()) {
                i = C1947R.drawable.icn_group;
            } else {
                this.f22972y.setVisibility(8);
                this.f22973z.setVisibility(8);
                return;
            }
            this.f22972y.setText(C1947R.string.core_join);
            this.f22972y.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
            this.f22972y.setVisibility(0);
            this.f22972y.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedListViewItem f22882b;

                public void onClick(View view) {
                    this.f22882b.ad.mo6438b(performanceV2);
                }
            });
            if (this.ab.songUid != null) {
                ListingV2 e = StoreManager.m18378a().m18428e(this.ab.songUid);
                if (e == null || !e.a()) {
                    this.f22973z.setVisibility(4);
                    return;
                } else {
                    this.f22973z.setVisibility(0);
                    return;
                }
            }
            this.f22973z.setVisibility(4);
            return;
        }
        this.f22972y.setVisibility(8);
        this.f22973z.setVisibility(8);
    }

    private void setupShare(final BaseFragment baseFragment) {
        this.f22971x.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22884b;

            public void onClick(View view) {
                this.f22884b.ad.mo6433a(ItemClickType.SHARE);
                ShareUtils.m26005a(baseFragment.getActivity(), this.f22884b.ab);
            }
        });
    }

    private void setupComment(final BaseFragment baseFragment) {
        this.f22970w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22889b;

            class C46442 implements OnDismissListener {
                final /* synthetic */ AnonymousClass18 f22887a;

                C46442(AnonymousClass18 anonymousClass18) {
                    this.f22887a = anonymousClass18;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    if (baseFragment.isAdded()) {
                        this.f22887a.f22889b.f22970w.setText(this.f22887a.f22889b.getLocalizedFormatter().m18999a((long) this.f22887a.f22889b.ab.totalComments, (long) this.f22887a.f22889b.getResources().getInteger(C1947R.integer.long_form_threshold)));
                    }
                }
            }

            public void onClick(View view) {
                this.f22889b.ad.mo6433a(ItemClickType.COMMENT);
                String str = "";
                MasterActivity masterActivity = (MasterActivity) baseFragment.getActivity();
                if (masterActivity.U() == null) {
                    final CommentsListDialog commentsListDialog = new CommentsListDialog(baseFragment, this.f22889b.ab, "");
                    commentsListDialog.m23658a(new CommentsListDialogListener(this) {
                        final /* synthetic */ AnonymousClass18 f22886b;

                        public void mo6828a(CommentItem commentItem, PerformancePost performancePost) {
                            if (baseFragment.isAdded()) {
                                baseFragment.mo6487a(ProfileFragment.m21036a(performancePost.accountIcon));
                                commentsListDialog.dismiss();
                            }
                        }
                    });
                    commentsListDialog.setOnDismissListener(new C46442(this));
                    commentsListDialog.show();
                    return;
                }
                masterActivity.d("");
            }
        });
    }

    private void m24293a(AccountIcon accountIcon, OnClickListener onClickListener) {
        this.f22961n.setVisibility(0);
        PerformanceV2Util.m25939a(getResources(), this.f22962o, accountIcon);
        this.f22958k.setVisibility(8);
        this.f22949b.m23395a(accountIcon, onClickListener);
        this.f22962o.setOnClickListener(onClickListener);
    }

    private void m24296a(PerformanceV2 performanceV2, String str) {
        this.f22961n.setVisibility(8);
        this.f22958k.setVisibility(0);
        this.f22960m.setVisibility(0);
        PerformanceV2Util.m25939a(getResources(), this.f22959l, performanceV2.accountIcon);
        this.f22960m.setText(str);
    }

    private void m24295a(PerformanceV2 performanceV2, AccountIcon accountIcon) {
        this.f22961n.setVisibility(8);
        this.f22958k.setVisibility(0);
        this.f22960m.setVisibility(0);
        PerformanceV2Util.m25939a(getResources(), this.f22959l, performanceV2.accountIcon);
        PerformanceV2Util.m25939a(getResources(), this.f22960m, accountIcon);
    }

    private void setupDuetSeed(OnClickListener onClickListener) {
        m24293a(this.ab.accountIcon, onClickListener);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.ah == null) {
            this.ah = new LocalizedShortNumberFormatter(getContext());
        }
        return this.ah;
    }

    private void setupGroup(final PerformanceV2 performanceV2) {
        OnClickListener anonymousClass19 = new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22891b;

            public void onClick(View view) {
                this.f22891b.ad.mo6434a(performanceV2.accountIcon);
            }
        };
        this.f22950c.m23395a(performanceV2.accountIcon, anonymousClass19);
        this.f22959l.setOnClickListener(anonymousClass19);
        this.f22950c.setVisibility(0);
        this.f22951d.setVisibility(0);
        this.f22951d.m23390a(performanceV2.totalPerformers - 1, new OnClickListener(this) {
            final /* synthetic */ FeedListViewItem f22896b;

            public void onClick(View view) {
                this.f22896b.ad.mo6436a(performanceV2);
            }
        });
        m24296a(performanceV2, getResources().getQuantityString(C1947R.plurals.other_count, performanceV2.totalPerformers - 1, new Object[]{getLocalizedFormatter().m18998a((long) (performanceV2.totalPerformers - 1)), Integer.valueOf(getResources().getInteger(C1947R.integer.long_form_threshold))}));
    }

    private void m24300b(PerformanceV2 performanceV2) {
        this.f22969v.setText(getLocalizedFormatter().m18999a((long) performanceV2.totalLoves, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        this.f22969v.setActivated(this.f22945U);
    }

    @UiThread
    public void mo6875a(boolean z, boolean z2, boolean z3) {
        long j = m24313c() ? this.ac.accountIcon.accountId : this.ab.accountIcon.accountId;
        String str = m24313c() ? this.ac.accountIcon.handle : this.ab.accountIcon.handle;
        if (j == UserManager.a().f()) {
            this.f22964q.setVisibility(8);
            this.f22963p.setVisibility(8);
            return;
        }
        boolean a = FollowManager.m18204a().m18222a(j);
        this.f22964q.setVisibility(8);
        this.f22963p.setVisibility(0);
        this.f22963p.setActivated(a);
        if (!z3) {
            return;
        }
        if (z) {
            if (a) {
                Toaster.a(getContext(), MessageFormat.format(getContext().getString(C1947R.string.profile_follow_format), new Object[]{str}));
            } else {
                Toaster.a(getContext(), MessageFormat.format(getContext().getString(C1947R.string.profile_unfollow_format), new Object[]{str}));
            }
        } else if (z2) {
            Toaster.a(getContext(), getContext().getString(C1947R.string.profile_follow_limit_reached));
        } else {
            Toaster.a(getContext(), getContext().getString(C1947R.string.login_cannot_connect_to_smule));
        }
    }

    public void u_() {
        super.u_();
        if (!m24313c()) {
            this.f22945U = StringCacheManager.a().b(this.ab.performanceKey);
            m24300b(this.ab);
        }
        if (this.f22947W) {
            m24317g();
        } else {
            m24318h();
        }
    }

    public void mo6724b() {
        MediaPlayerServiceController a = MediaPlayerServiceController.m24641a();
        String mediaKey = getMediaKey();
        String i = a.m24673i();
        String h = a.m24671h();
        if (mediaKey == null || !(mediaKey.equals(i) || mediaKey.equals(h))) {
            this.f22947W = false;
        }
    }

    public ArrangementVersionLite getArrVersionLite() {
        return this.ac != null ? this.ac : null;
    }

    public PerformanceV2 getPerformance() {
        return this.ab != null ? this.ab : null;
    }

    protected void mo6872m() {
        super.mo6872m();
        if (this.f22929E.getVisibility() == 0) {
            this.f22942R.setVisibility(m23043q() ? 4 : 0);
            this.f22931G.setVisibility(8);
            this.f22930F.setVisibility(8);
            this.f22930F.setAlpha(0.296f);
            return;
        }
        this.f22942R.setVisibility(8);
        this.f22931G.setVisibility(8);
        this.f22930F.setVisibility(8);
    }

    protected void mo6873n() {
        super.mo6873n();
        this.f22931G.setVisibility(0);
        this.f22942R.setVisibility(8);
    }

    protected void mo6874o() {
        super.mo6874o();
        if (this.f22929E.getVisibility() == 0) {
            this.f22942R.setVisibility(0);
            this.f22931G.setVisibility(8);
            this.f22930F.setVisibility(8);
            return;
        }
        this.f22942R.setVisibility(8);
        this.f22931G.setVisibility(8);
    }
}
