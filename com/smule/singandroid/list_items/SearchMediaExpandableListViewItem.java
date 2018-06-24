/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorInflater
 *  android.animation.AnimatorSet
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.support.v4.content.ContextCompat
 *  android.support.v4.graphics.drawable.DrawableCompat
 *  android.util.DisplayMetrics
 *  android.view.TextureView
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  com.smule.singandroid.songbook_search.SearchShowAllFragment
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.UiHandler;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.AnimatableCardView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem_;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.songbook_search.SearchShowAllFragment;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SearchMediaExpandableListViewItem
extends MediaPlayingListItem {
    private static final String I = SearchMediaExpandableListViewItem.class.getSimpleName();
    @ViewById
    protected RelativeLayout A;
    @ViewById
    protected View B;
    ArrayList<View> C = new ArrayList();
    protected boolean D;
    PerformanceV2 E;
    MediaListItemFeedback F;
    SearchMediaExpandableListItem G;
    BaseFragment H;
    private LocalizedShortNumberFormatter J;
    private AnimatorSet K;
    private AnimatorSet L;
    @ViewById
    protected RelativeLayout a;
    @ViewById
    protected RelativeLayout b;
    @ViewById
    protected ProfileImageWithVIPBadge c;
    @ViewById
    protected ImageView d;
    @ViewById
    protected TextView e;
    @ViewById
    protected TextView f;
    @ViewById
    protected TextView g;
    @ViewById
    protected TextView h;
    @ViewById
    protected FrameLayout i;
    @ViewById
    protected RelativeLayout j;
    @ViewById
    protected RelativeLayout k;
    @ViewById
    protected ImageView l;
    @ViewById
    protected View m;
    @ViewById
    protected RippleBackground n;
    @ViewById
    protected AnimatableCardView o;
    @ViewById
    protected ImageView p;
    @ViewById
    protected View q;
    @ViewById
    protected TextureView r;
    @ViewById
    protected View s;
    @ViewById
    protected View t;
    @ViewById
    protected Button u;
    @ViewById
    protected TextView v;
    @ViewById
    protected TextView w;
    @ViewById
    protected TextView x;
    @ViewById
    protected TextView y;
    @ViewById
    protected TextView z;

    public SearchMediaExpandableListViewItem(Context context) {
        super(context);
    }

    public static SearchMediaExpandableListViewItem a(Context context) {
        return SearchMediaExpandableListViewItem_.b(context);
    }

    private void a(View view) {
        if (!this.C.contains((Object)view)) {
            this.C.add(view);
        }
    }

    private void b(final String string2) {
        if (string2 != null && !string2.equals(this.ae.getTag())) {
            this.l.setImageDrawable(ContextCompat.getDrawable((Context)this.getContext(), (int)2130837895));
            ImageUtils.a(string2, this.p, 2130837895, (ImageLoadingListener)new SimpleImageLoadingListener(){

                public void a(String string22, View view, final Bitmap bitmap) {
                    new UiHandler(view).post(new Runnable(){

                        @Override
                        public void run() {
                            Bitmap bitmap2 = ImageUtils.a(SearchMediaExpandableListViewItem.this.getContext(), bitmap, 20.0f);
                            if (bitmap2 == null) {
                                MagicCrittercism.a(new DroidSing8362Exception("Invalid image: " + string2));
                                return;
                            }
                            bitmap2 = new BitmapDrawable(SearchMediaExpandableListViewItem.this.getResources(), bitmap2);
                            SearchMediaExpandableListViewItem.this.l.setImageDrawable((Drawable)bitmap2);
                            SearchMediaExpandableListViewItem.this.ae.a.setVisibility(8);
                        }
                    });
                }

            });
        }
        this.ae.setTag((Object)string2);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.J == null) {
            this.J = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.J;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(BaseFragment baseFragment, Analytics searchTarget) {
        if (this.E == null) {
            return;
        }
        boolean bl = this.H != null && this.H instanceof SearchShowAllFragment && ((SearchShowAllFragment)this.H).a();
        ((MediaPlayingActivity)baseFragment.getActivity()).a(this.E, false, true, null, null, searchTarget, bl, 2131820557, null, -1);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(BaseFragment object, final SearchMediaExpandableListItem searchMediaExpandableListItem, boolean bl, final MediaListItemFeedback mediaListItemFeedback, boolean bl2) {
        int n = 0;
        Log.b(I, "bindToSearchMediaExpandableListViewItem : " + searchMediaExpandableListItem);
        this.E = searchMediaExpandableListItem.performanceIcon;
        this.F = mediaListItemFeedback;
        this.G = searchMediaExpandableListItem;
        this.D = bl;
        this.H = object;
        this.setDescendantFocusability(393216);
        this.a((View)this.j);
        this.a((View)this.k);
        this.a((View)this.l);
        this.a(this.n);
        this.a(this.m);
        this.a((View)this.r);
        this.a(this.s);
        this.a(this.t);
        object = new View.OnClickListener(){

            public void onClick(View view) {
                mediaListItemFeedback.a(searchMediaExpandableListItem.performanceIcon.accountIcon);
            }
        };
        this.c.setOnClickListener((View.OnClickListener)object);
        this.e.setOnClickListener((View.OnClickListener)object);
        this.setupPerformance(searchMediaExpandableListItem);
        if (bl) {
            this.setExpandedWithAnimation(false);
        } else {
            this.setCollapsedWithAnimation(false);
        }
        object = this.B;
        if (!bl2) {
            n = 8;
        }
        object.setVisibility(n);
    }

    public void a(final SearchMediaExpandableListViewItemClickCallback searchMediaExpandableListViewItemClickCallback, final SearchMediaExpandableListViewItemClickCallback searchMediaExpandableListViewItemClickCallback2) {
        this.i.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                searchMediaExpandableListViewItemClickCallback.a();
            }
        });
        this.o.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                searchMediaExpandableListViewItemClickCallback2.a();
            }
        });
    }

    public void a(boolean bl) {
        if (bl) {
            this.n.setVisibility(0);
            this.n.a();
            return;
        }
        this.n.b();
        this.n.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(boolean bl) {
        int n = 0;
        this.r.setVisibility(0);
        View view = this.s;
        if (!bl) {
            n = 8;
        }
        view.setVisibility(n);
        this.j.setVisibility(8);
        this.f();
    }

    public void c() {
        this.y.setVisibility(0);
        this.z.setVisibility(0);
        this.ae.setVisibility(0);
    }

    public void d() {
        this.y.setVisibility(8);
        this.z.setVisibility(8);
        this.ae.setVisibility(8);
    }

    public void e() {
        this.r.setVisibility(8);
        this.s.setVisibility(8);
        this.j.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void f() {
        NowPlayingFragment nowPlayingFragment;
        Log.b(I, "configureVideoSurface");
        if (!this.H.isAdded() || (nowPlayingFragment = ((MediaPlayingActivity)this.H.getActivity()).ad()) != null && nowPlayingFragment.K()) {
            return;
        }
        MediaPlayerServiceController.a().a(this.H.getActivity(), this.r, this.s, this.t);
    }

    public void g() {
        this.ae.b();
    }

    /*
     * Enabled aggressive block sorting
     */
    public AnimatorSet getCollapseAnimatorSet() {
        Context context = this.getContext();
        int n = MiscUtils.a((PerformanceV2)this.E) ? 2131034125 : 2131034124;
        context = AnimatorInflater.loadAnimator((Context)context, (int)n);
        context.setTarget((Object)this.o);
        n = (int)((float)this.getContext().getResources().getDisplayMetrics().widthPixels - this.getResources().getDimension(2131428169) * 2.0f);
        if (!MiscUtils.a((PerformanceV2)this.E)) {
            n = (int)this.getResources().getDimension(2131427832);
        }
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int[])new int[]{n, (int)this.getResources().getDimension(2131427831)});
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

            public void onAnimationUpdate(ValueAnimator object) {
                object = (Integer)object.getAnimatedValue();
                for (int i = 0; i < SearchMediaExpandableListViewItem.this.C.size(); ++i) {
                    SearchMediaExpandableListViewItem.this.C.get((int)i).getLayoutParams().height = object.intValue();
                    SearchMediaExpandableListViewItem.this.C.get(i).requestLayout();
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        if (!MiscUtils.a((PerformanceV2)this.E)) {
            animatorSet.playTogether(new Animator[]{context, valueAnimator});
        } else {
            ValueAnimator valueAnimator2 = ValueAnimator.ofFloat((float[])new float[]{0.0f, 1.0f});
            valueAnimator2.setDuration(500);
            valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

                public void onAnimationUpdate(ValueAnimator object) {
                    object = (Float)object.getAnimatedValue();
                    SearchMediaExpandableListViewItem.this.j.setAlpha(object.floatValue());
                    SearchMediaExpandableListViewItem.this.k.setAlpha(1.0f - object.floatValue());
                    SearchMediaExpandableListViewItem.this.j.requestLayout();
                    SearchMediaExpandableListViewItem.this.k.requestLayout();
                }
            });
            animatorSet.playTogether(new Animator[]{context, valueAnimator, valueAnimator2});
        }
        this.L = animatorSet;
        return animatorSet;
    }

    /*
     * Enabled aggressive block sorting
     */
    public AnimatorSet getExpandAnimatorSet() {
        Context context = this.getContext();
        int n = MiscUtils.a((PerformanceV2)this.E) ? 2131034127 : 2131034126;
        context = AnimatorInflater.loadAnimator((Context)context, (int)n);
        context.setTarget((Object)this.o);
        n = (int)((float)this.getContext().getResources().getDisplayMetrics().widthPixels - this.getResources().getDimension(2131428169) * 2.0f);
        int n2 = (int)this.getResources().getDimension(2131427831);
        if (!MiscUtils.a((PerformanceV2)this.E)) {
            n = (int)this.getResources().getDimension(2131427832);
        }
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int[])new int[]{n2, n});
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

            public void onAnimationUpdate(ValueAnimator object) {
                object = (Integer)object.getAnimatedValue();
                for (int i = 0; i < SearchMediaExpandableListViewItem.this.C.size(); ++i) {
                    SearchMediaExpandableListViewItem.this.C.get((int)i).getLayoutParams().height = object.intValue();
                    SearchMediaExpandableListViewItem.this.C.get(i).requestLayout();
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        if (!MiscUtils.a((PerformanceV2)this.E)) {
            Log.b(I, "Not a video perf. Only Play shift and expand");
            animatorSet.playTogether(new Animator[]{context, valueAnimator});
        } else {
            ValueAnimator valueAnimator2 = ValueAnimator.ofFloat((float[])new float[]{1.0f, 0.0f});
            valueAnimator2.setDuration(500);
            valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

                public void onAnimationUpdate(ValueAnimator object) {
                    object = (Float)object.getAnimatedValue();
                    SearchMediaExpandableListViewItem.this.j.setAlpha(object.floatValue());
                    SearchMediaExpandableListViewItem.this.k.setAlpha(1.0f - object.floatValue());
                    SearchMediaExpandableListViewItem.this.j.requestLayout();
                    SearchMediaExpandableListViewItem.this.k.requestLayout();
                }
            });
            Log.b(I, "It's a video perf. Play shift, expand, and fadout");
            animatorSet.playTogether(new Animator[]{context, valueAnimator, valueAnimator2});
        }
        this.K = animatorSet;
        return animatorSet;
    }

    public SearchMediaExpandableListItem getItem() {
        return this.G;
    }

    public PerformanceV2 getPerformance() {
        return this.E;
    }

    @Override
    protected void m() {
        super.m();
        this.s.setVisibility(8);
        if (this.r.getVisibility() == 0) {
            this.a(false);
            this.t.setVisibility(8);
            return;
        }
        this.a(true);
        this.q.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void n() {
        super.n();
        if (this.a(this.E)) {
            this.f();
            this.t.setVisibility(0);
        } else {
            this.q.setVisibility(0);
        }
        this.a(false);
    }

    @Override
    protected void o() {
        super.o();
        this.t.setVisibility(8);
        this.q.setVisibility(8);
        this.a(false);
    }

    @Override
    public void r_() {
        block3 : {
            block2 : {
                super.r_();
                if (!MediaPlayerServiceController.a().c(this.getMediaKey())) break block2;
                if (!this.a(this.E)) break block3;
                this.b(this.t());
            }
            return;
        }
        this.e();
    }

    public void setCollapsedWithAnimation(boolean bl) {
        if (this.L != null && this.L.isRunning()) {
            return;
        }
        if (!bl) {
            Resources resources = this.getResources();
            for (int i = 0; i < this.C.size(); ++i) {
                this.C.get((int)i).getLayoutParams().height = (int)resources.getDimension(2131427831);
            }
            this.c();
        }
        this.t.setVisibility(8);
        this.q.setVisibility(8);
        this.a(false);
        if (!bl) {
            this.j.setAlpha(1.0f);
            this.o.setAlpha(1.0f);
            this.o.setScaleX(1.0f);
            this.o.setScaleY(1.0f);
            this.o.setXFraction(0.0f);
            ((ViewGroup.MarginLayoutParams)this.o.getLayoutParams()).leftMargin = (int)this.getResources().getDimension(2131428167);
        }
        this.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setExpandedWithAnimation(boolean bl) {
        Object object;
        if (this.K != null && this.K.isRunning()) {
            return;
        }
        if (!bl) {
            object = this.getResources();
            for (int i = 0; i < this.C.size(); ++i) {
                ViewGroup.LayoutParams layoutParams = this.C.get(i).getLayoutParams();
                int n = this.a(this.E) ? (int)((float)this.getContext().getResources().getDisplayMetrics().widthPixels - this.getResources().getDimension(2131428169) * 2.0f) : (int)object.getDimension(2131427832);
                layoutParams.height = n;
            }
            this.d();
        }
        object = MediaPlayerServiceController.a().i();
        if (!this.a(this.E)) {
            this.e();
            this.o.setAlpha(1.0f);
            this.o.setScaleX(2.33f);
            this.o.setScaleY(2.33f);
            this.o.setXFraction(0.5f);
            return;
        }
        if (MediaPlayerServiceController.a().j() && object != null && object.equals(this.getMediaKey())) {
            this.b(false);
            return;
        }
        this.b(MediaPlayerServiceController.a().l());
    }

    public void setIsExpanded(boolean bl) {
        this.D = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void setupPerformance(SearchMediaExpandableListItem object) {
        int n;
        block7 : {
            block8 : {
                PerformanceV2 performanceV2;
                block5 : {
                    block6 : {
                        performanceV2 = object.performanceIcon;
                        this.ae.a(true);
                        this.ae.a(this.a(this.E), 2130838138);
                        this.c.setAccount(performanceV2.accountIcon);
                        this.e.setText((CharSequence)performanceV2.accountIcon.handle);
                        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.e, (AccountIcon)performanceV2.accountIcon);
                        if (performanceV2.message == null || performanceV2.message.isEmpty()) {
                            this.h.setVisibility(8);
                            ((RelativeLayout.LayoutParams)this.e.getLayoutParams()).addRule(15, -1);
                            ((RelativeLayout.LayoutParams)this.f.getLayoutParams()).addRule(15, -1);
                            ((RelativeLayout.LayoutParams)this.g.getLayoutParams()).addRule(15, -1);
                        } else {
                            this.h.setVisibility(0);
                            this.h.setText((CharSequence)performanceV2.message);
                            ((RelativeLayout.LayoutParams)this.e.getLayoutParams()).addRule(15, 0);
                            ((RelativeLayout.LayoutParams)this.f.getLayoutParams()).addRule(15, 0);
                            ((RelativeLayout.LayoutParams)this.g.getLayoutParams()).addRule(15, 0);
                        }
                        this.y.setText((CharSequence)performanceV2.title);
                        this.z.setText((CharSequence)performanceV2.artist);
                        this.w.setText((CharSequence)this.getResources().getQuantityString(2131361834, performanceV2.totalListens, new Object[]{this.getLocalizedFormatter().a(performanceV2.totalListens, this.getResources().getInteger(2131623962))}));
                        this.b(performanceV2.coverUrl);
                        this.a(performanceV2.performanceKey);
                        if (object.a() != SearchMediaExpandableListItem.a) break block5;
                        ((LinearLayout.LayoutParams)this.A.getLayoutParams()).height = (int)this.getResources().getDimension(2131427830);
                        this.d.setVisibility(0);
                        this.v.setVisibility(0);
                        this.u.setVisibility(0);
                        this.x.setVisibility(8);
                        this.v.setText((CharSequence)this.getResources().getString(2131296603, new Object[]{MiscUtils.a((long)object.expireAt, (boolean)true, (boolean)false)}));
                        this.u.setOnClickListener(new View.OnClickListener(){

                            public void onClick(View view) {
                                if (SearchMediaExpandableListViewItem.this.F != null) {
                                    SearchMediaExpandableListViewItem.this.F.a(SearchMediaExpandableListViewItem.this.E);
                                }
                            }
                        });
                        this.d.setOnClickListener(new View.OnClickListener(){

                            public void onClick(View view) {
                                if (SearchMediaExpandableListViewItem.this.F != null) {
                                    SearchMediaExpandableListViewItem.this.F.b(SearchMediaExpandableListViewItem.this.E);
                                }
                            }
                        });
                        if (!performanceV2.e() || !performanceV2.p()) break block6;
                        n = 2130837903;
                        this.f.setVisibility(8);
                        this.g.setVisibility(8);
                        this.u.setContentDescription((CharSequence)"duet");
                        break block7;
                    }
                    if (!performanceV2.f() || !performanceV2.p()) break block8;
                    n = 2130837932;
                    object = this.getResources().getQuantityString(2131361836, performanceV2.totalPerformers - 1, new Object[]{this.getLocalizedFormatter().a(performanceV2.totalPerformers - 1, this.getResources().getInteger(2131623962))});
                    this.f.setVisibility(0);
                    this.g.setVisibility(0);
                    this.g.setText((CharSequence)object);
                    this.u.setContentDescription((CharSequence)"group");
                    break block7;
                }
                ((LinearLayout.LayoutParams)this.A.getLayoutParams()).height = (int)this.getResources().getDimension(2131427833);
                this.d.setVisibility(8);
                this.v.setVisibility(8);
                this.u.setVisibility(8);
                this.x.setVisibility(0);
                Drawable drawable2 = DrawableCompat.wrap((Drawable)this.getResources().getDrawable(2130838033));
                DrawableCompat.setTint((Drawable)drawable2.mutate(), (int)this.getResources().getColor(2131689547));
                this.x.setCompoundDrawablesWithIntrinsicBounds(drawable2, null, null, null);
                this.x.setText((CharSequence)MiscUtils.a((long)object.createdAt, (boolean)true, (boolean)false, (boolean)false));
                if (performanceV2.totalPerformers == 1) {
                    this.f.setVisibility(8);
                    this.g.setVisibility(8);
                    return;
                }
                if (performanceV2.e() && performanceV2.recentTracks != null && performanceV2.recentTracks.size() > 0) {
                    object = performanceV2.recentTracks.get((int)0).accountIcon;
                    this.g.setOnClickListener(new View.OnClickListener((AccountIcon)object){
                        final /* synthetic */ AccountIcon a;
                        {
                            this.a = accountIcon;
                        }

                        public void onClick(View view) {
                            SearchMediaExpandableListViewItem.this.F.a(this.a);
                        }
                    });
                    this.f.setVisibility(0);
                    this.g.setVisibility(0);
                    PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.g, (AccountIcon)object);
                    return;
                }
                if (!performanceV2.f()) return;
                {
                    object = this.getResources().getQuantityString(2131361836, performanceV2.totalPerformers - 1, new Object[]{this.getLocalizedFormatter().a(performanceV2.totalPerformers - 1, this.getResources().getInteger(2131623962))});
                    this.f.setVisibility(0);
                    this.g.setVisibility(0);
                    this.g.setText((CharSequence)object);
                    return;
                }
            }
            n = 0;
        }
        this.u.setText(2131296695);
        this.u.setCompoundDrawablesWithIntrinsicBounds(0, 0, n, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void u() {
        if (this.q()) {
            this.a(false);
        } else {
            this.a(true);
        }
        super.u();
    }

    private class DroidSing8362Exception
    extends Exception {
        public DroidSing8362Exception(String string2) {
            super(string2);
        }
    }

    public static interface MediaListItemFeedback {
        public void a(AccountIcon var1);

        public void a(PerformanceV2 var1);

        public void b(PerformanceV2 var1);
    }

    public static interface SearchMediaExpandableListViewItemClickCallback {
        public void a();
    }

}

