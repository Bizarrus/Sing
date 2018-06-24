/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.text.Html
 *  android.text.Spannable
 *  android.text.SpannableString
 *  android.text.Spanned
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.text.method.MovementMethod
 *  android.text.style.ClickableSpan
 *  android.view.TextureView
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  android.widget.TextView$BufferType
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.ShareUtils
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.FeedListItemObject;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.StringCacheManager;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CommentsListDialog;
import com.smule.singandroid.hashtag.CustomLinkMovementMethod;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.list_items.CommentItem;
import com.smule.singandroid.list_items.FeedListViewItem_;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.ShareUtils;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FeedListViewItem
extends MediaPlayingListItem {
    private static final String ag = FeedListViewItem.class.getSimpleName();
    @ViewById
    protected View A;
    @ViewById
    protected ImageView B;
    @ViewById
    protected RippleBackground C;
    @ViewById
    protected FrameLayout D;
    @ViewById
    protected TextureView E;
    @ViewById
    protected View F;
    @ViewById
    protected View G;
    @ViewById
    protected TextView H;
    @ViewById
    protected View I;
    @ViewById
    protected View J;
    @ViewById
    protected TextView K;
    @ViewById
    protected TextView L;
    @ViewById
    protected TextView M;
    @ViewById
    protected TextView N;
    @ViewById
    protected View O;
    @ViewById
    protected View P;
    @ViewById
    protected View Q;
    @ViewById
    protected ImageView R;
    @ViewById
    protected View S;
    com.smule.android.network.models.FeedListItem T;
    protected boolean U = false;
    protected int V;
    protected boolean W;
    @ViewById
    protected ImageView a;
    protected BaseFragment aa;
    protected PerformanceV2 ab;
    protected ArrangementVersionLite ac;
    protected FeedListItemFeedback ad;
    private LocalizedShortNumberFormatter ah;
    @ViewById
    protected ProfileImageWithVIPBadge b;
    @ViewById
    protected ProfileImageWithVIPBadge c;
    @ViewById
    protected ProfileImageWithVIPBadge d;
    @ViewById
    protected ImageView e;
    @ViewById
    protected TextView f;
    @ViewById
    protected ProfileImageWithVIPBadge g;
    protected ImageUtils h = new Object(){
        public String a;

        public void a() {
            this.a = null;
        }

        public void a(ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader.a().a(imageView);
        }

        public boolean a(String string2, ImageView imageView, int n) {
            return this.a(string2, imageView, n, false);
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl);
                this.a = string2;
                return true;
            }
            return false;
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl, int n2) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl, -1, n2, null, true);
                this.a = string2;
                return true;
            }
            return false;
        }
    };
    @ViewById
    protected ImageView i;
    @ViewById
    protected TextView j;
    @ViewById
    protected View k;
    @ViewById
    protected TextView l;
    @ViewById
    protected TextView m;
    @ViewById
    protected View n;
    @ViewById
    protected TextView o;
    @ViewById
    protected ImageButton p;
    @ViewById
    protected ProgressBar q;
    @ViewById
    protected View r;
    @ViewById
    protected TextView s;
    @ViewById
    protected TextView t;
    @ViewById
    protected TextView u;
    @ViewById
    protected TextView v;
    @ViewById
    protected TextView w;
    @ViewById
    protected TextView x;
    @ViewById
    protected Button y;
    @ViewById
    protected ImageView z;

    public FeedListViewItem(Context context) {
        super(context);
    }

    public static FeedListViewItem a(Context context) {
        return FeedListViewItem_.b(context);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String a(com.smule.android.network.models.FeedListItem feedListItem) {
        if (feedListItem.a() == null) {
            do {
                return null;
                break;
            } while (true);
        }
        Resources resources = this.getResources();
        switch (.a[feedListItem.a().ordinal()]) {
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                return null;
            }
            default: {
                return null;
            }
            case 1: {
                if (feedListItem.count != 1) return resources.getQuantityString(2131361817, feedListItem.count - 1, new Object[]{feedListItem.count - 1, feedListItem.subject.handle});
                return String.format(resources.getString(2131296982), feedListItem.subject.handle, feedListItem.content);
            }
            case 2: {
                if (feedListItem.count != 1) return resources.getQuantityString(2131361819, feedListItem.count - 1, new Object[]{feedListItem.count - 1, feedListItem.subject.handle});
                return String.format(resources.getString(2131296993), feedListItem.subject.handle);
            }
            case 3: {
                if (feedListItem.count != 1) return resources.getQuantityString(2131361818, feedListItem.count - 1, new Object[]{feedListItem.count - 1, feedListItem.subject.handle});
                return String.format(resources.getString(2131296991), feedListItem.subject.handle);
            }
            case 9: 
        }
        return resources.getString(2131297011);
    }

    private void a(SpannableString spannableString, Spanned spanned, String string2, View.OnClickListener object) {
        object = new ClickableSpan((View.OnClickListener)object){
            final /* synthetic */ View.OnClickListener a;
            {
                this.a = onClickListener;
            }

            public void onClick(View view) {
                this.a.onClick(view);
            }

            public void updateDrawState(TextPaint textPaint) {
            }
        };
        int n = spanned.toString().indexOf(string2);
        if (n > -1) {
            spannableString.setSpan(object, n, string2.length() + n, 33);
        }
    }

    private void a(AccountIcon accountIcon, View.OnClickListener onClickListener) {
        this.n.setVisibility(0);
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.o, (AccountIcon)accountIcon);
        this.k.setVisibility(8);
        this.b.a(accountIcon, onClickListener);
        this.o.setOnClickListener(onClickListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(PerformanceV2 object, View.OnClickListener onClickListener) {
        this.d.setVisibility(8);
        this.m.setVisibility(8);
        if (this.ab.p()) {
            if (this.ab.f()) {
                this.setupGroup((PerformanceV2)object);
            } else {
                this.setupDuetSeed(onClickListener);
            }
            this.setupJoinButton(this.ab);
        } else {
            if (PerformanceV2Util.c((PerformanceV2)this.ab)) {
                this.setupDuetSeed(onClickListener);
            } else if (this.ab.e()) {
                this.l.setOnClickListener(onClickListener);
                this.c.setVisibility(0);
                this.d.setVisibility(0);
                final AccountIcon accountIcon = this.ab.recentTracks.get((int)0).accountIcon;
                View.OnClickListener onClickListener2 = new View.OnClickListener(){

                    public void onClick(View view) {
                        FeedListViewItem.this.ad.a(accountIcon);
                    }
                };
                this.c.a(object.accountIcon, onClickListener);
                this.d.a(accountIcon, onClickListener2);
                this.m.setOnClickListener(onClickListener2);
                this.a(this.ab, accountIcon);
            } else if (this.ab.f()) {
                this.setupGroup((PerformanceV2)object);
                if (object.totalPerformers > 1) {
                    object = new View.OnClickListener(){

                        public void onClick(View view) {
                            FeedListViewItem.this.ad.a(FeedListViewItem.this.ab);
                        }
                    };
                    this.d.setOnClickListener((View.OnClickListener)object);
                    this.m.setOnClickListener((View.OnClickListener)object);
                } else {
                    this.d.a(null);
                    this.d.setOnClickListener(null);
                    this.m.setOnClickListener(null);
                }
            } else {
                this.d.setVisibility(8);
                this.a(object.accountIcon, onClickListener);
            }
            this.setupJoinButton(this.ab);
        }
        this.A.measure(-1, -2);
        this.V = this.A.getMeasuredHeight();
    }

    private void a(PerformanceV2 performanceV2, AccountIcon accountIcon) {
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.m.setVisibility(0);
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.l, (AccountIcon)performanceV2.accountIcon);
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.m, (AccountIcon)accountIcon);
    }

    private void a(PerformanceV2 performanceV2, String string2) {
        this.n.setVisibility(8);
        this.k.setVisibility(0);
        this.m.setVisibility(0);
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.l, (AccountIcon)performanceV2.accountIcon);
        this.m.setText((CharSequence)string2);
    }

    private void a(BaseFragment object, com.smule.android.network.models.FeedListItem feedListItem) {
        final long l = this.ac.accountIcon.accountId;
        this.p.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                boolean bl = FollowManager.a().a(l);
                FeedListItemFeedback feedListItemFeedback = FeedListViewItem.this.ad;
                object = bl ? Analytics.h : Analytics.g;
                feedListItemFeedback.a(object);
                FeedListViewItem.this.q.setVisibility(0);
                FeedListViewItem.this.p.setVisibility(8);
                object = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
                com.smule.android.logging.Analytics.a(object, l);
                FollowManager.a().a((Long)l, new FollowManager.ToggleFollowStateListener(){

                    @Override
                    public void a(boolean bl, boolean bl2, boolean bl3) {
                        FeedListViewItem.this.a(bl, bl3, true);
                        if (FeedListViewItem.this.ad != null) {
                            FeedListViewItem.this.ad.a();
                        }
                    }
                });
            }

        });
        this.a(true, false, false);
        this.r.setVisibility(8);
        this.t.setVisibility(8);
        this.A.setVisibility(8);
        this.J.setVisibility(0);
        this.w();
        this.O.setVisibility(0);
        this.Q.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FeedListViewItem.this.ad.a(Analytics.i);
                FeedListViewItem.this.ad.b(FeedListViewItem.this.ac);
            }
        });
        this.P.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FeedListViewItem.this.ad.a(FeedListViewItem.this.ac);
            }
        });
        this.O.measure(-1, -2);
        this.V = this.O.getMeasuredHeight();
        object = new View.OnClickListener(){

            public void onClick(View view) {
                FeedListViewItem.this.ad.a(FeedListViewItem.this.ac.accountIcon);
            }
        };
        this.a(this.ac.accountIcon, (View.OnClickListener)object);
        this.b(this.ac.coverUrl);
        this.a(this.ac.key);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(CharSequence object, TextView textView, boolean bl) {
        SpannableString spannableString = object instanceof SpannableString ? (SpannableString)object : new SpannableString((CharSequence)object);
        if (object instanceof Spanned) {
            TextUtils.copySpansFrom((Spanned)((Spanned)object), (int)0, (int)object.length(), (Class)null, (Spannable)spannableString, (int)0);
        }
        MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)this.getContext();
        object = bl ? "fonts/ProximaNova-Bold.ttf" : "fonts/ProximaNova-Semibold.ttf";
        Hashtag.a(mediaPlayingActivity, spannableString, (String)object);
        object = new CustomLinkMovementMethod(this.getContext());
        object.a(new CustomLinkMovementMethod.TextClickedListener(){

            @Override
            public void a() {
                FeedListViewItem.this.callOnClick();
            }
        });
        textView.setText((CharSequence)spannableString, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod((MovementMethod)object);
        textView.setHighlightColor(0);
    }

    private String b(com.smule.android.network.models.FeedListItem feedListItem) {
        switch (feedListItem.b()) {
            default: {
                return this.getResources().getString(2131297015);
            }
            case a: 
        }
        return this.getResources().getString(2131297010);
    }

    private void b(PerformanceV2 performanceV2) {
        this.v.setText((CharSequence)this.getLocalizedFormatter().a(performanceV2.totalLoves, this.getResources().getInteger(2131623962)));
        this.v.setActivated(this.U);
    }

    private void b(BaseFragment baseFragment, com.smule.android.network.models.FeedListItem feedListItem) {
        this.a(baseFragment, feedListItem.object.performanceIcon);
    }

    private void b(final String string2) {
        if (string2 != null && !string2.equals(this.ae.getTag())) {
            com.smule.android.utils.ImageUtils.a(string2, this.ae.a, 2130837895, (ImageLoadingListener)new SimpleImageLoadingListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void a(String string22, View view, Bitmap bitmap) {
                    view = null;
                    int n = (int)((float)bitmap.getWidth() * 0.025f);
                    int n2 = (int)((float)bitmap.getHeight() * 0.025f);
                    if (n <= 0) {
                        n = bitmap.getWidth();
                    }
                    if (n2 <= 0) {
                        n2 = bitmap.getHeight();
                    }
                    string22 = view;
                    if (n > 0) {
                        string22 = view;
                        if (n2 > 0) {
                            string22 = Bitmap.createScaledBitmap((Bitmap)bitmap, (int)n, (int)n2, (boolean)true);
                            string22 = new BitmapDrawable(FeedListViewItem.this.getResources(), (Bitmap)string22);
                        }
                    }
                    if (string22 == null) {
                        MagicCrittercism.a(new DroidSing8362Exception("Invalid image: " + string2));
                    }
                    FeedListViewItem.this.B.setImageDrawable((Drawable)string22);
                }
            });
        }
        this.ae.setTag((Object)string2);
    }

    private int c(com.smule.android.network.models.FeedListItem feedListItem) {
        switch (feedListItem.b()) {
            default: {
                return 2130837919;
            }
            case a: 
        }
        switch (.a[feedListItem.a().ordinal()]) {
            default: {
                return 2130837918;
            }
            case 9: 
        }
        return 2130837920;
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.ah == null) {
            this.ah = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.ah;
    }

    private void setupComment(final BaseFragment baseFragment) {
        this.w.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                FeedListViewItem.this.ad.a(Analytics.c);
                object = (MasterActivity)baseFragment.getActivity();
                if (object.ad() == null) {
                    object = new com.smule.singandroid.dialogs.CommentsListDialog(baseFragment, FeedListViewItem.this.ab, "", null);
                    object.a(new CommentsListDialog((com.smule.singandroid.dialogs.CommentsListDialog)object){
                        final /* synthetic */ com.smule.singandroid.dialogs.CommentsListDialog a;
                        {
                            this.a = commentsListDialog;
                        }

                        @Override
                        public void a(CommentItem object, PerformancePost performancePost) {
                            if (!baseFragment.isAdded()) {
                                return;
                            }
                            object = ProfileFragment.a(performancePost.accountIcon);
                            baseFragment.a((BaseFragment)((Object)object));
                            this.a.dismiss();
                        }
                    });
                    object.setOnDismissListener(new DialogInterface.OnDismissListener(){

                        public void onDismiss(DialogInterface dialogInterface) {
                            if (!baseFragment.isAdded()) {
                                return;
                            }
                            FeedListViewItem.this.w.setText((CharSequence)FeedListViewItem.this.getLocalizedFormatter().a(FeedListViewItem.this.ab.totalComments, FeedListViewItem.this.getResources().getInteger(2131623962)));
                        }
                    });
                    object.show();
                    return;
                }
                object.d("");
            }

        });
    }

    private void setupDuetSeed(View.OnClickListener onClickListener) {
        this.a(this.ab.accountIcon, onClickListener);
    }

    private void setupGroup(final PerformanceV2 performanceV2) {
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                FeedListViewItem.this.ad.a(performanceV2.accountIcon);
            }
        };
        this.c.a(performanceV2.accountIcon, onClickListener);
        this.l.setOnClickListener(onClickListener);
        this.c.setVisibility(0);
        this.d.setVisibility(0);
        this.d.a(performanceV2.totalPerformers - 1, new View.OnClickListener(){

            public void onClick(View view) {
                FeedListViewItem.this.ad.a(performanceV2);
            }
        });
        this.a(performanceV2, this.getResources().getQuantityString(2131361832, performanceV2.totalPerformers - 1, new Object[]{this.getLocalizedFormatter().a(performanceV2.totalPerformers - 1), this.getResources().getInteger(2131623962)}));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setupJoinButton(final PerformanceV2 performanceV2) {
        block6 : {
            int n;
            block5 : {
                block4 : {
                    if (!performanceV2.p() && PerformanceV2Util.a((String)performanceV2.arrKey)) {
                        this.y.setVisibility(8);
                        this.z.setVisibility(8);
                        return;
                    }
                    if (!performanceV2.e() || !performanceV2.p()) break block4;
                    n = 2130837903;
                    break block5;
                }
                if (!performanceV2.f() || !performanceV2.p()) break block6;
                n = 2130837932;
            }
            this.y.setText(2131296695);
            this.y.setCompoundDrawablesWithIntrinsicBounds(0, 0, n, 0);
            this.y.setVisibility(0);
            this.y.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FeedListViewItem.this.ad.b(performanceV2);
                }
            });
            this.z.setVisibility(4);
            return;
        }
        this.y.setVisibility(8);
        this.z.setVisibility(8);
    }

    private void setupShare(final BaseFragment baseFragment) {
        this.x.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                FeedListViewItem.this.ad.a(Analytics.i);
                object = new BusyScreenDialog(FeedListViewItem.this.getContext(), "", "");
                object.show();
                com.smule.android.network.managers.PerformanceManager.a().a(FeedListViewItem.this.ab.performanceKey, false, new PerformanceManager((BusyScreenDialog)((Object)object)){
                    final /* synthetic */ BusyScreenDialog a;
                    {
                        this.a = busyScreenDialog;
                    }

                    @Override
                    public void handleResponse(PerformanceManager.PerformanceResponse performanceResponse) {
                        this.a.dismiss();
                        if (performanceResponse.a() && performanceResponse.mPerformance != null) {
                            ShareUtils.a((Activity)baseFragment.getActivity(), (PerformanceV2)performanceResponse.mPerformance);
                            return;
                        }
                        ShareUtils.a((Activity)baseFragment.getActivity(), (PerformanceV2)FeedListViewItem.this.ab);
                    }
                });
            }

        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void v() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)this.i.getLayoutParams();
        if (this.f.getVisibility() == 0) {
            layoutParams.topMargin = 0;
            this.j.setPadding(0, 0, 0, 0);
        } else {
            layoutParams.topMargin = this.getResources().getDimensionPixelSize(2131428182);
            this.j.setPadding(0, this.getResources().getDimensionPixelSize(2131428182), 0, 0);
        }
        this.i.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void w() {
        int n;
        this.K.setText((CharSequence)this.ac.c());
        String string2 = this.ac.name != null && this.ac.name.length() > 0 ? this.ac.artist : "";
        if (string2.length() > 0) {
            this.L.setText((CharSequence)this.ac.artist);
        } else {
            this.L.setVisibility(8);
        }
        if (this.ac.lyrics) {
            this.N.setVisibility(8);
        } else {
            this.N.setText((CharSequence)this.getResources().getString(2131297190));
            this.N.setVisibility(0);
        }
        if (this.ac.rating != null && this.ac.totalVotes >= 3) {
            string2 = "" + (int)(this.ac.rating.floatValue() * 100.0f) + "% (" + this.getLocalizedFormatter().a(this.ac.totalVotes) + ")";
            this.M.setText((CharSequence)string2);
            if (this.ac.highlyRated) {
                string2 = this.getResources().getDrawable(2130838032);
                n = this.getContext().getResources().getColor(2131689589);
            } else {
                string2 = this.getResources().getDrawable(2130838031);
                n = this.getContext().getResources().getColor(2131689548);
            }
        } else {
            this.M.setText(2131297524);
            string2 = this.getResources().getDrawable(2130838031);
            n = this.getContext().getResources().getColor(2131689548);
        }
        this.M.setCompoundDrawablesWithIntrinsicBounds((Drawable)string2, null, null, null);
        this.M.setTextColor(n);
        this.M.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean x() {
        Object object = MediaPlayerServiceController.a();
        String string2 = this.getMediaKey();
        String string3 = object.i();
        object = object.h();
        if (string2 == null) {
            Log.e(ag, "Item should always have a media key");
            return false;
        } else {
            if (!string2.equals(string3) && !string2.equals(object)) return false;
            return true;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final BaseFragment baseFragment, final com.smule.android.network.models.FeedListItem feedListItem, boolean bl, FeedListItemFeedback object) {
        String string2;
        Log.b(ag, "bindToFeedItem : " + feedListItem);
        this.ab = null;
        this.ad = object;
        this.ac = null;
        this.T = feedListItem;
        this.W = bl;
        this.aa = baseFragment;
        this.setDescendantFocusability(393216);
        if (feedListItem.actionTime > 0) {
            this.f.setVisibility(0);
            this.f.setText((CharSequence)MiscUtils.a((long)feedListItem.actionTime, (boolean)false, (boolean)true, (boolean)false));
        } else if (feedListItem.object != null && feedListItem.object.performanceIcon != null) {
            this.f.setVisibility(0);
            this.f.setText((CharSequence)MiscUtils.a((long)feedListItem.object.performanceIcon.createdAt, (boolean)false, (boolean)true, (boolean)false));
        } else {
            this.f.setVisibility(8);
        }
        boolean bl2 = (string2 = this.a(feedListItem)) != null;
        object = string2;
        if (string2 == null) {
            if (feedListItem.b() != FeedListItem.a) {
                this.f.setVisibility(8);
            }
            object = this.b(feedListItem);
        }
        string2 = object.replaceAll(Pattern.quote("<b>"), "<font color=\"#3d3839\"><b>").replaceAll(Pattern.quote("</b>"), "</b></font>");
        object = string2;
        if (feedListItem != null) {
            object = string2;
            if (feedListItem.subject != null) {
                object = string2;
                if (feedListItem.subject.c()) {
                    object = string2;
                    if (bl2) {
                        object = string2;
                        if (!this.T.c()) {
                            object = "*" + string2;
                        }
                    }
                }
            }
        }
        object = Html.fromHtml((String)object);
        string2 = new SpannableString((CharSequence)object);
        if (bl2 && !this.T.c()) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View object) {
                    object = ProfileFragment.a(feedListItem.subject);
                    baseFragment.a((BaseFragment)((Object)object));
                }
            };
            this.a((SpannableString)string2, (Spanned)object, feedListItem.subject.handle, onClickListener);
            this.g.setVisibility(0);
            this.g.setAccount(feedListItem.subject);
            this.g.setOnClickListener(onClickListener);
            this.i.setVisibility(8);
            if (feedListItem.subject.c()) {
                string2.setSpan((Object)PerformanceV2Util.a((Resources)this.getResources()), 0, 1, 17);
            }
        } else {
            this.i.setImageDrawable(this.getResources().getDrawable(this.c(feedListItem)));
            this.i.setVisibility(0);
            this.g.setVisibility(8);
        }
        this.a(string2, this.j, true);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        if (feedListItem.object != null) {
            if (feedListItem.c()) {
                this.ac = feedListItem.object.arrVersionLite;
                this.a(baseFragment, feedListItem);
            } else {
                this.ab = feedListItem.object.performanceIcon;
                this.b(baseFragment, feedListItem);
            }
            this.ae.a(this.a(this.ab), 2130837818);
        } else {
            this.s.setText((CharSequence)"");
            this.t.setText((CharSequence)"");
            this.u.setText((CharSequence)"");
            this.c.setVisibility(8);
            this.v.setOnClickListener(null);
        }
        this.ae.a(true);
        if (bl) {
            this.g();
        } else {
            this.h();
        }
        this.v();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(final BaseFragment baseFragment, final PerformanceV2 performanceV2) {
        this.ab = performanceV2;
        this.r.setVisibility(0);
        this.t.setVisibility(0);
        this.A.setVisibility(0);
        this.J.setVisibility(8);
        this.O.setVisibility(8);
        String string2 = performanceV2.performanceKey;
        this.U = StringCacheManager.a().b(performanceV2.performanceKey);
        this.s.setText((CharSequence)performanceV2.title);
        if (performanceV2.message == null || performanceV2.message.isEmpty()) {
            this.t.setVisibility(8);
        } else {
            this.t.setVisibility(0);
            this.a(performanceV2.message, this.t, false);
        }
        this.u.setText((CharSequence)this.getResources().getQuantityString(2131361820, performanceV2.totalListens, new Object[]{this.getLocalizedFormatter().a(performanceV2.totalListens, this.getResources().getInteger(2131623962))}));
        this.b(performanceV2);
        this.w.setText((CharSequence)this.getLocalizedFormatter().a(performanceV2.totalComments, this.getResources().getInteger(2131623962)));
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                FeedListViewItem.this.ad.a(performanceV2.accountIcon);
            }
        };
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.o, (AccountIcon)performanceV2.accountIcon);
        final long l = performanceV2.accountIcon.accountId;
        this.p.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                boolean bl = FollowManager.a().a(l);
                FeedListItemFeedback feedListItemFeedback = FeedListViewItem.this.ad;
                object = bl ? Analytics.h : Analytics.g;
                feedListItemFeedback.a(object);
                FeedListViewItem.this.q.setVisibility(0);
                FeedListViewItem.this.p.setVisibility(8);
                object = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
                com.smule.android.logging.Analytics.a(object, l);
                FollowManager.a().a((Long)l, new FollowManager.ToggleFollowStateListener(){

                    @Override
                    public void a(boolean bl, boolean bl2, boolean bl3) {
                        FeedListViewItem.this.a(bl, bl3, true);
                        if (FeedListViewItem.this.ad != null) {
                            FeedListViewItem.this.ad.a();
                        }
                    }
                });
            }

        });
        this.a(true, false, false);
        this.v.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                if (FeedListViewItem.this.U) {
                    return;
                }
                FeedListViewItem.this.v.setActivated(true);
                FeedListViewItem.this.ad.a(Analytics.a);
                object = (MasterActivity)baseFragment.getActivity();
                if (object.ad() != null) {
                    object.K();
                    return;
                }
                object.a(FeedListViewItem.this.ab, true, false, new Runnable((MasterActivity)object){
                    final /* synthetic */ MasterActivity a;
                    {
                        this.a = masterActivity;
                    }

                    @Override
                    public void run() {
                        if (!baseFragment.isAdded()) {
                            return;
                        }
                        this.a.K();
                    }
                });
            }

        });
        this.a(performanceV2, onClickListener);
        this.b(performanceV2.coverUrl);
        this.a(string2);
        this.setupShare(baseFragment);
        this.setupComment(baseFragment);
    }

    public void a(final FeedListViewItemClickCallback feedListViewItemClickCallback, final FeedListViewItemClickCallback feedListViewItemClickCallback2) {
        this.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                feedListViewItemClickCallback.a();
            }
        });
        this.S.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                feedListViewItemClickCallback2.a();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl) {
        int n = 0;
        this.E.setVisibility(0);
        View view = this.F;
        if (!bl) {
            n = 8;
        }
        view.setVisibility(n);
        this.D.setVisibility(8);
        this.f();
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public void a(boolean bl, boolean bl2, boolean bl3) {
        long l = this.c() ? this.ac.accountIcon.accountId : this.ab.accountIcon.accountId;
        String string2 = this.c() ? this.ac.accountIcon.handle : this.ab.accountIcon.handle;
        if (l == UserManager.a().f()) {
            this.q.setVisibility(8);
            this.p.setVisibility(8);
            return;
        }
        boolean bl4 = FollowManager.a().a(l);
        this.q.setVisibility(8);
        this.p.setVisibility(0);
        this.p.setActivated(bl4);
        if (!bl3) return;
        if (bl) {
            if (bl4) {
                Toaster.a(this.getContext(), MessageFormat.format(this.getContext().getString(2131297195), string2));
                return;
            }
            Toaster.a(this.getContext(), MessageFormat.format(this.getContext().getString(2131297209), string2));
            return;
        }
        if (bl2) {
            Toaster.a(this.getContext(), this.getContext().getString(2131297196));
            return;
        }
        Toaster.a(this.getContext(), this.getContext().getString(2131296895));
    }

    public boolean c() {
        return this.T.c();
    }

    public boolean d() {
        return this.W;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void e() {
        if (this.ad == null) return;
        {
            if (this.ac != null) {
                this.ad.c(this.ac);
                return;
            } else {
                if (this.ab == null) return;
                {
                    this.ad.c(this.ab);
                    return;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void f() {
        NowPlayingFragment nowPlayingFragment;
        Log.b(ag, "configureVideoSurface");
        if (!this.aa.isAdded() || (nowPlayingFragment = ((MediaPlayingActivity)this.aa.getActivity()).ad()) != null && nowPlayingFragment.K()) {
            return;
        }
        MediaPlayerServiceController.a().a(this.aa.getActivity(), this.E, this.F, this.G);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void g() {
        Object object = this.getResources();
        this.C.getLayoutParams().height = (int)object.getDimension(2131427529);
        this.ae.getLayoutParams().height = (int)object.getDimension(2131427527);
        this.ae.getLayoutParams().width = (int)object.getDimension(2131427527);
        if (this.c()) {
            this.O.getLayoutParams().height = this.V;
        } else {
            this.A.getLayoutParams().height = this.V;
            this.t.setMaxLines(1000);
        }
        object = MediaPlayerServiceController.a().i();
        if (MediaPlayerServiceController.a().j() && object != null && object.equals(this.getMediaKey())) {
            if (this.a(this.ab)) {
                this.a(false);
            } else {
                this.k();
                this.C.a();
                if (this.ac != null) {
                    this.ae.m.setVisibility(0);
                }
            }
        } else if (this.a(this.ab)) {
            this.a(MediaPlayerServiceController.a().l());
        } else {
            this.k();
            this.C.b();
            if (this.ac != null) {
                this.ae.m.setVisibility(8);
            }
        }
        if (this.T != null && this.T.a() == FeedListItem.a) {
            this.j.setMaxLines(6);
        } else {
            this.j.setMaxLines(1);
        }
        this.I.setVisibility(8);
    }

    public ArrangementVersionLite getArrVersionLite() {
        if (this.ac != null) {
            return this.ac;
        }
        return null;
    }

    public Animation getCollapseViewAnimation() {
        return new ExpandAnimation((View)this, 500, false);
    }

    public ExpandAnimation getExpandViewAnimation() {
        return new ExpandAnimation((View)this, 500, true);
    }

    public PerformanceV2 getPerformance() {
        if (this.ab != null) {
            return this.ab;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void h() {
        Resources resources = this.getResources();
        this.C.getLayoutParams().height = (int)resources.getDimension(2131427528);
        this.ae.getLayoutParams().height = -1;
        this.ae.getLayoutParams().width = -1;
        this.E.setVisibility(8);
        this.F.setVisibility(8);
        this.G.setVisibility(8);
        this.ae.m.setVisibility(8);
        if (this.c()) {
            this.O.getLayoutParams().height = 0;
        } else {
            this.A.getLayoutParams().height = 0;
            this.t.setMaxLines(2);
        }
        this.C.b();
        if (this.T != null && this.T.a() == FeedListItem.a) {
            this.j.setMaxLines(2);
        } else {
            this.j.setMaxLines(1);
        }
        if (this.ab != null && this.ab.p()) {
            this.I.setVisibility(0);
            this.H.setText((CharSequence)MessageFormat.format(this.getResources().getString(2131296992), MiscUtils.a((long)this.ab.expireAt, (boolean)false, (boolean)false)));
        } else if (this.T != null && this.T.c()) {
            this.I.setVisibility(0);
            this.H.setText((CharSequence)this.getResources().getString(2131297016));
        } else {
            this.I.setVisibility(8);
        }
        this.k();
    }

    public void i() {
        this.ad.a(Analytics.b);
    }

    public void j() {
        this.ad.a(Analytics.j);
    }

    public void k() {
        this.E.setVisibility(8);
        this.F.setVisibility(8);
        this.D.setVisibility(0);
        this.F.setAlpha(1.0f);
    }

    public void l() {
        this.ae.b();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void m() {
        super.m();
        if (this.E.getVisibility() != 0) {
            this.R.setVisibility(8);
            this.G.setVisibility(8);
            this.F.setVisibility(8);
            return;
        }
        ImageView imageView = this.R;
        int n = this.q() ? 4 : 0;
        imageView.setVisibility(n);
        this.G.setVisibility(8);
        this.F.setVisibility(8);
        this.F.setAlpha(0.296f);
    }

    @Override
    protected void n() {
        super.n();
        this.G.setVisibility(0);
        this.R.setVisibility(8);
    }

    @Override
    protected void o() {
        super.o();
        if (this.E.getVisibility() == 0) {
            this.R.setVisibility(0);
            this.G.setVisibility(8);
            this.F.setVisibility(8);
            return;
        }
        this.R.setVisibility(8);
        this.G.setVisibility(8);
    }

    @Override
    public void r_() {
        super.r_();
        if (!this.c()) {
            this.U = StringCacheManager.a().b(this.ab.performanceKey);
            this.b(this.ab);
        }
        this.W = this.x();
        if (this.W) {
            this.g();
            return;
        }
        this.h();
    }

    @Override
    public void s_() {
        Object object = MediaPlayerServiceController.a();
        String string2 = this.getMediaKey();
        String string3 = object.i();
        object = object.h();
        if (string2 == null || !string2.equals(string3) && !string2.equals(object)) {
            this.W = false;
        }
    }

    public void setIsExpanded(boolean bl) {
        this.W = bl;
    }

    private class DroidSing8362Exception
    extends Exception {
        public DroidSing8362Exception(String string2) {
            super(string2);
        }
    }

    public class ExpandAnimation
    extends Animation {
        private FrameLayout.LayoutParams b;
        private int c;
        private int d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;
        private float j;
        private float k;
        private float l;

        public ExpandAnimation(View view, int n, boolean bl) {
            this.setDuration((long)n);
            view = FeedListViewItem.this.getResources();
            this.b = (FrameLayout.LayoutParams)FeedListViewItem.this.C.getLayoutParams();
            this.e = this.b.height;
            if (bl) {
                this.f = view.getDimension(2131427529);
                this.g = FeedListViewItem.this.C.getWidth();
                this.h = view.getDimension(2131427527);
                this.i = FeedListViewItem.this.C.getHeight();
                this.j = view.getDimension(2131427527);
                this.k = 0.0f;
                this.l = FeedListViewItem.this.V;
                if (FeedListViewItem.this.T != null && FeedListViewItem.this.T.a() == FeedListItem.a) {
                    this.c = 2;
                    this.d = 6;
                    return;
                }
                this.c = 1;
                this.d = 1;
                return;
            }
            this.f = view.getDimension(2131427528);
            this.g = view.getDimension(2131427527);
            this.h = FeedListViewItem.this.C.getWidth();
            this.i = view.getDimension(2131427527);
            this.j = view.getDimension(2131427528);
            this.k = FeedListViewItem.this.V;
            this.l = 0.0f;
            if (FeedListViewItem.this.T != null && FeedListViewItem.this.T.a() == FeedListItem.a) {
                this.c = 6;
                this.d = 2;
                return;
            }
            this.c = 1;
            this.d = 1;
        }

        private int a(float f, float f2, float f3) {
            return (int)((f3 - f2) * f + f2);
        }

        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            this.b.height = this.a(f, this.e, this.f);
            FeedListViewItem.this.ae.getLayoutParams().height = this.a(f, this.i, this.j);
            FeedListViewItem.this.ae.getLayoutParams().width = this.a(f, this.g, this.h);
            FeedListViewItem.this.A.getLayoutParams().height = this.a(f, this.k, this.l);
            FeedListViewItem.this.j.setMaxLines(this.a(f, this.c, this.d));
            FeedListViewItem.this.C.requestLayout();
            if ((double)f >= 1.0) {
                FeedListViewItem.this.C.b();
            }
        }
    }

    public static interface FeedListItemFeedback {
        public void a();

        public void a(Analytics var1);

        public void a(AccountIcon var1);

        public void a(ArrangementVersionLite var1);

        public void a(PerformanceV2 var1);

        public void b(ArrangementVersionLite var1);

        public void b(PerformanceV2 var1);

        public void c(ArrangementVersionLite var1);

        public void c(PerformanceV2 var1);
    }

    public static interface FeedListViewItemClickCallback {
        public void a();
    }

}

