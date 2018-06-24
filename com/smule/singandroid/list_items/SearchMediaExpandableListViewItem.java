package com.smule.singandroid.list_items;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.customviews.AnimatableCardView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.songbook_search.SearchShowAllFragment;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import java.util.ArrayList;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SearchMediaExpandableListViewItem extends MediaPlayingListItem {
    private static final String f23207I = SearchMediaExpandableListViewItem.class.getSimpleName();
    @ViewById
    protected RelativeLayout f23208A;
    @ViewById
    protected View f23209B;
    ArrayList<View> f23210C = new ArrayList();
    protected boolean f23211D;
    PerformanceV2 f23212E;
    MediaListItemFeedback f23213F;
    SearchMediaExpandableListItem f23214G;
    BaseFragment f23215H;
    private LocalizedShortNumberFormatter f23216J;
    private AnimatorSet f23217K;
    private AnimatorSet f23218L;
    @ViewById
    protected RelativeLayout f23219a;
    @ViewById
    protected RelativeLayout f23220b;
    @ViewById
    protected ProfileImageWithVIPBadge f23221c;
    @ViewById
    protected ImageView f23222d;
    @ViewById
    protected TextView f23223e;
    @ViewById
    protected TextView f23224f;
    @ViewById
    protected TextView f23225g;
    @ViewById
    protected TextView f23226h;
    @ViewById
    protected FrameLayout f23227i;
    @ViewById
    protected RelativeLayout f23228j;
    @ViewById
    protected RelativeLayout f23229k;
    @ViewById
    protected ImageView f23230l;
    @ViewById
    protected View f23231m;
    @ViewById
    protected RippleBackground f23232n;
    @ViewById
    protected AnimatableCardView f23233o;
    @ViewById
    protected ImageView f23234p;
    @ViewById
    protected View f23235q;
    @ViewById
    protected TextureView f23236r;
    @ViewById
    protected View f23237s;
    @ViewById
    protected View f23238t;
    @ViewById
    protected Button f23239u;
    @ViewById
    protected TextView f23240v;
    @ViewById
    protected TextView f23241w;
    @ViewById
    protected TextView f23242x;
    @ViewById
    protected TextView f23243y;
    @ViewById
    protected TextView f23244z;

    public interface MediaListItemFeedback {
        void mo6853a(AccountIcon accountIcon);

        void mo6854a(PerformanceV2 performanceV2);

        void mo6855b(PerformanceV2 performanceV2);
    }

    public interface SearchMediaExpandableListViewItemClickCallback {
        void mo6856a();
    }

    class C46882 implements OnClickListener {
        final /* synthetic */ SearchMediaExpandableListViewItem f23195a;

        C46882(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem) {
            this.f23195a = searchMediaExpandableListViewItem;
        }

        public void onClick(View view) {
            if (this.f23195a.f23213F != null) {
                this.f23195a.f23213F.mo6854a(this.f23195a.f23212E);
            }
        }
    }

    class C46893 implements OnClickListener {
        final /* synthetic */ SearchMediaExpandableListViewItem f23196a;

        C46893(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem) {
            this.f23196a = searchMediaExpandableListViewItem;
        }

        public void onClick(View view) {
            if (this.f23196a.f23213F != null) {
                this.f23196a.f23213F.mo6855b(this.f23196a.f23212E);
            }
        }
    }

    class C46937 implements AnimatorUpdateListener {
        final /* synthetic */ SearchMediaExpandableListViewItem f23203a;

        C46937(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem) {
            this.f23203a = searchMediaExpandableListViewItem;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Integer num = (Integer) valueAnimator.getAnimatedValue();
            for (int i = 0; i < this.f23203a.f23210C.size(); i++) {
                ((View) this.f23203a.f23210C.get(i)).getLayoutParams().height = num.intValue();
                ((View) this.f23203a.f23210C.get(i)).requestLayout();
            }
        }
    }

    class C46948 implements AnimatorUpdateListener {
        final /* synthetic */ SearchMediaExpandableListViewItem f23204a;

        C46948(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem) {
            this.f23204a = searchMediaExpandableListViewItem;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float f = (Float) valueAnimator.getAnimatedValue();
            this.f23204a.f23228j.setAlpha(f.floatValue());
            this.f23204a.f23229k.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - f.floatValue());
            this.f23204a.f23228j.requestLayout();
            this.f23204a.f23229k.requestLayout();
        }
    }

    class C46959 implements AnimatorUpdateListener {
        final /* synthetic */ SearchMediaExpandableListViewItem f23205a;

        C46959(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem) {
            this.f23205a = searchMediaExpandableListViewItem;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Integer num = (Integer) valueAnimator.getAnimatedValue();
            for (int i = 0; i < this.f23205a.f23210C.size(); i++) {
                ((View) this.f23205a.f23210C.get(i)).getLayoutParams().height = num.intValue();
                ((View) this.f23205a.f23210C.get(i)).requestLayout();
            }
        }
    }

    private class DroidSing8362Exception extends Exception {
        final /* synthetic */ SearchMediaExpandableListViewItem f23206a;

        public DroidSing8362Exception(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem, String str) {
            this.f23206a = searchMediaExpandableListViewItem;
            super(str);
        }
    }

    public SearchMediaExpandableListViewItem(Context context) {
        super(context);
    }

    public static SearchMediaExpandableListViewItem m24461a(Context context) {
        return SearchMediaExpandableListViewItem_.m24478b(context);
    }

    public SearchMediaExpandableListItem getItem() {
        return this.f23214G;
    }

    public void m24465a(BaseFragment baseFragment, final SearchMediaExpandableListItem searchMediaExpandableListItem, boolean z, final MediaListItemFeedback mediaListItemFeedback, boolean z2) {
        int i = 0;
        Log.b(f23207I, "bindToSearchMediaExpandableListViewItem : " + searchMediaExpandableListItem);
        this.f23212E = searchMediaExpandableListItem.performanceIcon;
        this.f23213F = mediaListItemFeedback;
        this.f23214G = searchMediaExpandableListItem;
        this.f23211D = z;
        this.f23215H = baseFragment;
        setDescendantFocusability(393216);
        m24462a(this.f23228j);
        m24462a(this.f23229k);
        m24462a(this.f23230l);
        m24462a(this.f23232n);
        m24462a(this.f23231m);
        m24462a(this.f23236r);
        m24462a(this.f23237s);
        m24462a(this.f23238t);
        OnClickListener c46871 = new OnClickListener(this) {
            final /* synthetic */ SearchMediaExpandableListViewItem f23194c;

            public void onClick(View view) {
                mediaListItemFeedback.mo6853a(searchMediaExpandableListItem.performanceIcon.accountIcon);
            }
        };
        this.f23221c.setOnClickListener(c46871);
        this.f23223e.setOnClickListener(c46871);
        setupPerformance(searchMediaExpandableListItem);
        if (z) {
            setExpandedWithAnimation(false);
        } else {
            setCollapsedWithAnimation(false);
        }
        View view = this.f23209B;
        if (!z2) {
            i = 8;
        }
        view.setVisibility(i);
    }

    protected void setupPerformance(SearchMediaExpandableListItem searchMediaExpandableListItem) {
        PerformanceV2 performanceV2 = searchMediaExpandableListItem.performanceIcon;
        this.ae.m23377a(true);
        this.ae.m23378a(m23037a(this.f23212E), (int) C1947R.drawable.noti_filmstrip);
        this.f23221c.setAccount(performanceV2.accountIcon);
        this.f23223e.setText(performanceV2.accountIcon.handle);
        PerformanceV2Util.m25939a(getResources(), this.f23223e, performanceV2.accountIcon);
        if (performanceV2.message == null || performanceV2.message.isEmpty()) {
            this.f23226h.setVisibility(8);
            ((LayoutParams) this.f23223e.getLayoutParams()).addRule(15, -1);
            ((LayoutParams) this.f23224f.getLayoutParams()).addRule(15, -1);
            ((LayoutParams) this.f23225g.getLayoutParams()).addRule(15, -1);
        } else {
            this.f23226h.setVisibility(0);
            this.f23226h.setText(performanceV2.message);
            ((LayoutParams) this.f23223e.getLayoutParams()).addRule(15, 0);
            ((LayoutParams) this.f23224f.getLayoutParams()).addRule(15, 0);
            ((LayoutParams) this.f23225g.getLayoutParams()).addRule(15, 0);
        }
        this.f23243y.setText(performanceV2.title);
        this.f23244z.setText(performanceV2.artist);
        this.f23241w.setText(getResources().getQuantityString(C1947R.plurals.plays_count, performanceV2.totalListens, new Object[]{getLocalizedFormatter().m18999a((long) performanceV2.totalListens, (long) getResources().getInteger(C1947R.integer.long_form_threshold))}));
        m24463b(performanceV2.coverUrl);
        m23036a(performanceV2.performanceKey);
        if (searchMediaExpandableListItem.a() == SearchMediaExpandableListItem$ItemType.INVITES) {
            int i;
            ((LinearLayout.LayoutParams) this.f23208A.getLayoutParams()).height = (int) getResources().getDimension(C1947R.dimen.search_invite_footer_height);
            this.f23222d.setVisibility(0);
            this.f23240v.setVisibility(0);
            this.f23239u.setVisibility(0);
            this.f23242x.setVisibility(8);
            this.f23240v.setText(getResources().getString(C1947R.string.chat_title_active_seed, new Object[]{MiscUtils.m25886a(searchMediaExpandableListItem.expireAt, true, false)}));
            this.f23239u.setOnClickListener(new C46882(this));
            this.f23222d.setOnClickListener(new C46893(this));
            if (performanceV2.d() && performanceV2.n()) {
                i = C1947R.drawable.icn_duet;
                this.f23224f.setVisibility(8);
                this.f23225g.setVisibility(8);
            } else if (performanceV2.e() && performanceV2.n()) {
                i = C1947R.drawable.icn_group;
                CharSequence quantityString = getResources().getQuantityString(C1947R.plurals.singer_count, performanceV2.totalPerformers - 1, new Object[]{getLocalizedFormatter().m18999a((long) (performanceV2.totalPerformers - 1), (long) getResources().getInteger(C1947R.integer.long_form_threshold))});
                this.f23224f.setVisibility(0);
                this.f23225g.setVisibility(0);
                this.f23225g.setText(quantityString);
            } else {
                i = 0;
            }
            this.f23239u.setText(C1947R.string.core_join);
            this.f23239u.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
            return;
        }
        ((LinearLayout.LayoutParams) this.f23208A.getLayoutParams()).height = (int) getResources().getDimension(C1947R.dimen.search_recording_footer_height);
        this.f23222d.setVisibility(8);
        this.f23240v.setVisibility(8);
        this.f23239u.setVisibility(8);
        this.f23242x.setVisibility(0);
        Drawable wrap = DrawableCompat.wrap(getResources().getDrawable(C1947R.drawable.icn_time_stamp));
        DrawableCompat.setTint(wrap.mutate(), getResources().getColor(C1947R.color.body_text_grey));
        this.f23242x.setCompoundDrawablesWithIntrinsicBounds(wrap, null, null, null);
        this.f23242x.setText(MiscUtils.m25887a(searchMediaExpandableListItem.createdAt, true, false, false));
        if (performanceV2.totalPerformers == 1) {
            this.f23224f.setVisibility(8);
            this.f23225g.setVisibility(8);
        } else if (performanceV2.d() && performanceV2.recentTracks != null && performanceV2.recentTracks.size() > 0) {
            final AccountIcon accountIcon = ((Track) performanceV2.recentTracks.get(0)).accountIcon;
            this.f23225g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchMediaExpandableListViewItem f23198b;

                public void onClick(View view) {
                    this.f23198b.f23213F.mo6853a(accountIcon);
                }
            });
            this.f23224f.setVisibility(0);
            this.f23225g.setVisibility(0);
            PerformanceV2Util.m25939a(getResources(), this.f23225g, accountIcon);
        } else if (performanceV2.e()) {
            CharSequence quantityString2 = getResources().getQuantityString(C1947R.plurals.singer_count, performanceV2.totalPerformers - 1, new Object[]{getLocalizedFormatter().m18999a((long) (performanceV2.totalPerformers - 1), (long) getResources().getInteger(C1947R.integer.long_form_threshold))});
            this.f23224f.setVisibility(0);
            this.f23225g.setVisibility(0);
            this.f23225g.setText(quantityString2);
        }
    }

    public void m24466a(final SearchMediaExpandableListViewItemClickCallback searchMediaExpandableListViewItemClickCallback, final SearchMediaExpandableListViewItemClickCallback searchMediaExpandableListViewItemClickCallback2) {
        this.f23227i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchMediaExpandableListViewItem f23200b;

            public void onClick(View view) {
                searchMediaExpandableListViewItemClickCallback.mo6856a();
            }
        });
        this.f23233o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchMediaExpandableListViewItem f23202b;

            public void onClick(View view) {
                searchMediaExpandableListViewItemClickCallback2.mo6856a();
            }
        });
    }

    public void setIsExpanded(boolean z) {
        this.f23211D = z;
    }

    public void setExpandedWithAnimation(boolean z) {
        if (this.f23217K == null || !this.f23217K.isRunning()) {
            if (!z) {
                Resources resources = getResources();
                for (int i = 0; i < this.f23210C.size(); i++) {
                    int dimension;
                    ViewGroup.LayoutParams layoutParams = ((View) this.f23210C.get(i)).getLayoutParams();
                    if (m23037a(this.f23212E)) {
                        dimension = (int) (((float) getContext().getResources().getDisplayMetrics().widthPixels) - (getResources().getDimension(C1947R.dimen.margin_large) * 2.0f));
                    } else {
                        dimension = (int) resources.getDimension(C1947R.dimen.search_invite_recording_list_expanded_height);
                    }
                    layoutParams.height = dimension;
                }
                m24470d();
            }
            String i2 = MediaPlayerServiceController.m24641a().m24673i();
            if (!m23037a(this.f23212E)) {
                m24471e();
                this.f23233o.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                this.f23233o.setScaleX(2.33f);
                this.f23233o.setScaleY(2.33f);
                this.f23233o.setXFraction(0.5f);
            } else if (MediaPlayerServiceController.m24641a().m24676j() && i2 != null && i2.equals(getMediaKey())) {
                m24468b(false);
            } else {
                m24468b(MediaPlayerServiceController.m24641a().m24680l());
            }
        }
    }

    public void setCollapsedWithAnimation(boolean z) {
        if (this.f23218L == null || !this.f23218L.isRunning()) {
            if (!z) {
                Resources resources = getResources();
                for (int i = 0; i < this.f23210C.size(); i++) {
                    ((View) this.f23210C.get(i)).getLayoutParams().height = (int) resources.getDimension(C1947R.dimen.search_invite_recording_list_collapsed_height);
                }
                m24469c();
            }
            this.f23238t.setVisibility(8);
            this.f23235q.setVisibility(8);
            m24467a(false);
            if (!z) {
                this.f23228j.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                this.f23233o.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                this.f23233o.setScaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                this.f23233o.setScaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                this.f23233o.setXFraction(0.0f);
                ((MarginLayoutParams) this.f23233o.getLayoutParams()).leftMargin = (int) getResources().getDimension(C1947R.dimen.margin_medium);
            }
            m24471e();
        }
    }

    public void m24467a(boolean z) {
        if (z) {
            this.f23232n.setVisibility(0);
            this.f23232n.m23474a();
            return;
        }
        this.f23232n.m23476b();
        this.f23232n.setVisibility(8);
    }

    public AnimatorSet getExpandAnimatorSet() {
        AnimatorInflater.loadAnimator(getContext(), MiscUtils.m25895a(this.f23212E) ? C1947R.animator.slide_right_down_full_fade_out : C1947R.animator.slide_right_down).setTarget(this.f23233o);
        int dimension = (int) (((float) getContext().getResources().getDisplayMetrics().widthPixels) - (getResources().getDimension(C1947R.dimen.margin_large) * 2.0f));
        int[] iArr = new int[2];
        iArr[0] = (int) getResources().getDimension(C1947R.dimen.search_invite_recording_list_collapsed_height);
        if (!MiscUtils.m25895a(this.f23212E)) {
            dimension = (int) getResources().getDimension(C1947R.dimen.search_invite_recording_list_expanded_height);
        }
        iArr[1] = dimension;
        ValueAnimator ofInt = ValueAnimator.ofInt(iArr);
        ofInt.setDuration(500);
        ofInt.addUpdateListener(new C46937(this));
        AnimatorSet animatorSet = new AnimatorSet();
        if (MiscUtils.m25895a(this.f23212E)) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f});
            ofFloat.setDuration(500);
            ofFloat.addUpdateListener(new C46948(this));
            Log.b(f23207I, "It's a video perf. Play shift, expand, and fadout");
            animatorSet.playTogether(new Animator[]{r1, ofInt, ofFloat});
        } else {
            Log.b(f23207I, "Not a video perf. Only Play shift and expand");
            animatorSet.playTogether(new Animator[]{r1, ofInt});
        }
        this.f23217K = animatorSet;
        return animatorSet;
    }

    public AnimatorSet getCollapseAnimatorSet() {
        AnimatorInflater.loadAnimator(getContext(), MiscUtils.m25895a(this.f23212E) ? C1947R.animator.slide_left_up_full_fade_in : C1947R.animator.slide_left_up).setTarget(this.f23233o);
        int dimension = (int) (((float) getContext().getResources().getDisplayMetrics().widthPixels) - (getResources().getDimension(C1947R.dimen.margin_large) * 2.0f));
        int[] iArr = new int[2];
        if (!MiscUtils.m25895a(this.f23212E)) {
            dimension = (int) getResources().getDimension(C1947R.dimen.search_invite_recording_list_expanded_height);
        }
        iArr[0] = dimension;
        iArr[1] = (int) getResources().getDimension(C1947R.dimen.search_invite_recording_list_collapsed_height);
        ValueAnimator ofInt = ValueAnimator.ofInt(iArr);
        ofInt.setDuration(500);
        ofInt.addUpdateListener(new C46959(this));
        AnimatorSet animatorSet = new AnimatorSet();
        if (MiscUtils.m25895a(this.f23212E)) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
            ofFloat.setDuration(500);
            ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
                final /* synthetic */ SearchMediaExpandableListViewItem f23187a;

                {
                    this.f23187a = r1;
                }

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Float f = (Float) valueAnimator.getAnimatedValue();
                    this.f23187a.f23228j.setAlpha(f.floatValue());
                    this.f23187a.f23229k.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - f.floatValue());
                    this.f23187a.f23228j.requestLayout();
                    this.f23187a.f23229k.requestLayout();
                }
            });
            animatorSet.playTogether(new Animator[]{r1, ofInt, ofFloat});
        } else {
            animatorSet.playTogether(new Animator[]{r1, ofInt});
        }
        this.f23218L = animatorSet;
        return animatorSet;
    }

    private void m24463b(final String str) {
        if (!(str == null || str.equals(this.ae.getTag()))) {
            this.f23230l.setImageDrawable(ContextCompat.getDrawable(getContext(), C1947R.drawable.icn_default_album_large));
            ImageUtils.a(str, this.f23234p, C1947R.drawable.icn_default_album_large, new SimpleImageLoadingListener(this) {
                final /* synthetic */ SearchMediaExpandableListViewItem f23191b;

                public void mo6155a(String str, View view, final Bitmap bitmap) {
                    new UiHandler(view).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 f23189b;

                        public void run() {
                            Bitmap a = ImageUtils.a(this.f23189b.f23191b.getContext(), bitmap, CloseButton.TEXT_SIZE_SP);
                            if (a == null) {
                                MagicCrittercism.a(new DroidSing8362Exception(this.f23189b.f23191b, "Invalid image: " + str));
                                return;
                            }
                            this.f23189b.f23191b.f23230l.setImageDrawable(new BitmapDrawable(this.f23189b.f23191b.getResources(), a));
                            this.f23189b.f23191b.ae.f21796a.setVisibility(8);
                        }
                    });
                }
            });
        }
        this.ae.setTag(str);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f23216J == null) {
            this.f23216J = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f23216J;
    }

    public PerformanceV2 getPerformance() {
        return this.f23212E;
    }

    public void m24464a(BaseFragment baseFragment, SearchTarget searchTarget) {
        if (this.f23212E != null) {
            boolean z;
            if (this.f23215H != null && (this.f23215H instanceof SearchShowAllFragment) && ((SearchShowAllFragment) this.f23215H).m25401a()) {
                z = true;
            } else {
                z = false;
            }
            ((MediaPlayingActivity) baseFragment.getActivity()).a(this.f23212E, false, true, null, null, searchTarget, z, C1947R.menu.search_show_all_menu, null);
        }
    }

    public void m24469c() {
        this.f23243y.setVisibility(0);
        this.f23244z.setVisibility(0);
        this.ae.setVisibility(0);
    }

    public void m24470d() {
        this.f23243y.setVisibility(8);
        this.f23244z.setVisibility(8);
        this.ae.setVisibility(8);
    }

    public void m24468b(boolean z) {
        int i = 0;
        this.f23236r.setVisibility(0);
        View view = this.f23237s;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        this.f23228j.setVisibility(8);
        m24472f();
    }

    public void m24471e() {
        this.f23236r.setVisibility(8);
        this.f23237s.setVisibility(8);
        this.f23228j.setVisibility(0);
    }

    public void m24472f() {
        Log.b(f23207I, "configureVideoSurface");
        if (this.f23215H.isAdded()) {
            NowPlayingFragment U = ((MediaPlayingActivity) this.f23215H.getActivity()).U();
            if (U == null || !U.m20373F()) {
                MediaPlayerServiceController.m24641a().m24650a(this.f23215H.getActivity(), this.f23236r, this.f23237s, this.f23238t);
            }
        }
    }

    public void u_() {
        super.u_();
        if (!MediaPlayerServiceController.m24641a().m24662c(getMediaKey())) {
            return;
        }
        if (m23037a(this.f23212E)) {
            m24468b(m23046t());
        } else {
            m24471e();
        }
    }

    public void m24473g() {
        this.ae.m23379b();
    }

    protected void mo6872m() {
        super.mo6872m();
        this.f23237s.setVisibility(8);
        if (this.f23236r.getVisibility() == 0) {
            m24467a(false);
            this.f23238t.setVisibility(8);
            return;
        }
        m24467a(true);
        this.f23235q.setVisibility(8);
    }

    protected void mo6873n() {
        super.mo6873n();
        if (m23037a(this.f23212E)) {
            m24472f();
            this.f23238t.setVisibility(0);
        } else {
            this.f23235q.setVisibility(0);
        }
        m24467a(false);
    }

    protected void mo6874o() {
        super.mo6874o();
        this.f23238t.setVisibility(8);
        this.f23235q.setVisibility(8);
        m24467a(false);
    }

    public void mo6881u() {
        if (m23043q()) {
            m24467a(false);
        } else {
            m24467a(true);
        }
        super.mo6881u();
    }

    private void m24462a(View view) {
        if (!this.f23210C.contains(view)) {
            this.f23210C.add(view);
        }
    }
}
