/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorListenerAdapter
 *  android.animation.ObjectAnimator
 *  android.animation.StateListAnimator
 *  android.animation.TimeInterpolator
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.location.Location
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.support.annotation.ColorInt
 *  android.support.annotation.NonNull
 *  android.support.design.widget.AppBarLayout
 *  android.support.design.widget.AppBarLayout$OnOffsetChangedListener
 *  android.support.v4.content.ContextCompat
 *  android.text.SpannableString
 *  android.text.SpannableStringBuilder
 *  android.text.TextUtils
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.text.style.StyleSpan
 *  android.util.Pair
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.TextureView
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.Window
 *  android.view.animation.DecelerateInterpolator
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.ArrayAdapter
 *  android.widget.BaseAdapter
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.EditText
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$EntryPoint
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 *  com.smule.singandroid.utils.DeepLink
 *  com.smule.singandroid.utils.DeepLink$Hosts
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.ShareUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SongbookEntryUtils
 *  com.smule.singandroid.utils.SwipeDismissTouchListener
 *  com.smule.singandroid.utils.SwipeDismissTouchListener$DismissCallbacks
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.IgnoreWhen
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.mediaplaying;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.BoostManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.ui.SNPImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.LocationUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.StringCacheManager;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceSaveFragment;
import com.smule.singandroid.PerformanceSaveFragmentFactory;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.adapters.profile.ProfileFavoritesDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.boost.BoostContext;
import com.smule.singandroid.boost.BoostPresenter;
import com.smule.singandroid.boost.LearnHowToBoostDialog;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.common.MagicClickableSpan;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.SquareTextureView;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.CommentsListDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.fragments.OpenCallPerformancesListFragment;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.list_items.CommentItem;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.mediaplaying.MediaRenderTask;
import com.smule.singandroid.mediaplaying.NowPlayingFragment_;
import com.smule.singandroid.mediaplaying.PlaybackPresenter;
import com.smule.singandroid.mediaplaying.PreviewFragment;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.profile.PerformanceListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.ShareUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.SwipeDismissTouchListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.IgnoreWhen;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class NowPlayingFragment
extends MediaPlayingFragment
implements CommentsListDialog.CommentDataSourceObserver,
UserFollowListItem.UserFollowListItemListener {
    private static final String bh = NowPlayingFragment.class.getName();
    @ViewById
    protected View P;
    @ViewById
    protected TextView Q;
    @ViewById
    protected View R;
    @ViewById
    protected View S;
    @ViewById
    protected TextView T;
    @ViewById
    protected ProgressBar U;
    @ViewById
    protected SNPImageView V;
    @ViewById
    protected View W;
    @ViewById
    protected TextView X;
    @ViewById
    protected TextView Y;
    @ViewById
    protected View Z;
    @ViewById
    protected IconFontView aA;
    @ViewById
    protected IconFontView aB;
    @ViewById
    protected IconFontView aC;
    @ViewById
    protected TextView aD;
    @ViewById
    protected TextView aE;
    @ViewById
    protected TextView aF;
    @ViewById
    protected View aG;
    @ViewById
    CustomToolbar aH;
    @ViewById
    protected ListView aI;
    @ViewById
    protected TextView aJ;
    @ViewById
    protected ImageButton aK;
    @ViewById
    protected View aL;
    @ViewById
    CustomToolbar aM;
    @ViewById
    protected ListView aN;
    @ViewById
    protected TextView aO;
    @ViewById
    protected ProfileImageWithVIPBadge aP;
    @ViewById
    protected View aQ;
    @ViewById
    protected View aR;
    @ViewById
    ProgressBar aS;
    MediaRenderTask aT;
    protected ArrayList<PerformancePost> aU;
    @FragmentArg
    protected PerformanceV2 aV;
    @InstanceState
    protected boolean aW;
    @InstanceState
    protected boolean aX;
    @InstanceState
    protected String aY;
    @FragmentArg
    protected boolean aZ;
    @ViewById
    protected View aa;
    @ViewById
    protected ProfileImageWithVIPBadge ab;
    @ViewById
    protected TextView ac;
    @ViewById
    protected View ad;
    @ViewById
    protected View ae;
    @ViewById
    protected ProfileImageWithVIPBadge af;
    @ViewById
    protected TextView ag;
    @ViewById
    protected LinearLayout ah;
    @ViewById
    protected TextView ai;
    @ViewById
    protected TextView aj;
    @ViewById
    protected ImageView ak;
    @ViewById
    protected View al;
    @ViewById
    protected TextView am;
    @ViewById
    protected TextView an;
    @ViewById
    protected TextView ao;
    @ViewById
    protected TextView ap;
    @ViewById
    protected TextView aq;
    @ViewById
    protected TextView ar;
    @ViewById
    protected TextView as;
    @ViewById
    protected View at;
    @ViewById
    protected View au;
    @ViewById
    protected ToggleButton av;
    protected MediaPlayingListItem aw = null;
    @ViewById
    protected AppBarLayout ax;
    @ViewById
    protected TextView ay;
    @ViewById
    protected IconFontView az;
    private boolean bA;
    private boolean bB = false;
    private boolean bC = false;
    private Long bD = -1;
    private AppBarLayout.OnOffsetChangedListener bE;
    private Observer bF;
    private final String bG;
    private final String bH;
    private final String bI;
    private ArrayList<String> bJ;
    private ArrayList<String> bK;
    private ArrayAdapter<String> bL;
    private BaseAdapter bM;
    private BaseAdapter bN;
    @FragmentArg
    protected Analytics ba;
    @FragmentArg
    protected int bb;
    @InstanceState
    protected boolean bc;
    @InstanceState
    protected boolean bd = false;
    @ViewById
    protected View be;
    @ViewById
    protected SquareTextureView bf;
    @ViewById
    protected View bg;
    private final int bi = 25;
    private final int bj = 3;
    private Mode bk = Mode.a;
    private boolean bl;
    private int bm;
    private NowPlayingFragmentMenuSelectedCallback bn;
    private boolean bo = false;
    private boolean bp = false;
    private boolean bq = false;
    private List<Track> br;
    private boolean bs = false;
    private boolean bt = false;
    private AlertDialog bu;
    private com.smule.singandroid.dialogs.CommentsListDialog bv;
    private CommentsListDialog bw;
    private boolean bx = true;
    private LocalizedShortNumberFormatter by;
    private boolean bz;

    public NowPlayingFragment() {
        this.bE = new AppBarLayout.OnOffsetChangedListener(){
            private boolean b;

            /*
             * Enabled aggressive block sorting
             */
            public void onOffsetChanged(AppBarLayout appBarLayout, int n) {
                float f;
                if (!this.b) {
                    int n2;
                    this.b = true;
                    if (Build.VERSION.SDK_INT >= 21) {
                        NowPlayingFragment.this.ax.setStateListAnimator(null);
                    }
                    if ((n2 = NowPlayingFragment.this.be.getWidth()) > 0) {
                        f = n2;
                        NowPlayingFragment.this.be.setMinimumHeight((int)(f * 0.5625f));
                    } else {
                        Log.e(bh, "video view width should match screen width");
                    }
                }
                f = (float)(- n) / 2.0f;
                NowPlayingFragment.this.m.setTranslationY(f);
                if (NowPlayingFragment.this.a()) {
                    NowPlayingFragment.this.bg.setTranslationY(f);
                    if (NowPlayingFragment.this.bf == null || !NowPlayingFragment.this.bf.isAvailable() || NowPlayingFragment.this.x == null || !NowPlayingFragment.this.x.m()) return;
                    {
                        NowPlayingFragment.this.bf.setTranslationY(f);
                        return;
                    }
                }
                if (NowPlayingFragment.this.x == null || !NowPlayingFragment.this.x.m()) {
                    return;
                }
                NowPlayingFragment.this.V.setTranslationY(f);
            }
        };
        this.bF = new Observer(){

            @Override
            public void update(Observable object, Object object2) {
                Log.b(bh, "mBookmarkFavoriteStatusObserver - called");
                if (object2 instanceof HashMap) {
                    object = (HashMap)object2;
                    if (object.containsKey("FAVORITED_PERFORMANCE") && ((PerformanceV2)object.get((Object)"FAVORITED_PERFORMANCE")).performanceKey.equals(NowPlayingFragment.this.aV.performanceKey)) {
                        NowPlayingFragment.this.bz = true;
                        NowPlayingFragment.this.aJ();
                        NowPlayingFragment.this.b(2131296794);
                    }
                    if (object.containsKey("UNFAVORITED_PERFORMANCE") && ((PerformanceV2)object.get((Object)"UNFAVORITED_PERFORMANCE")).performanceKey.equals(NowPlayingFragment.this.aV.performanceKey)) {
                        NowPlayingFragment.this.bz = false;
                        NowPlayingFragment.this.aJ();
                        NowPlayingFragment.this.b(2131296798);
                    }
                    if (object.containsKey("BOOKMARKED_PERFORMANCE") && ((PerformanceV2)object.get((Object)"BOOKMARKED_PERFORMANCE")).performanceKey.equals(NowPlayingFragment.this.aV.performanceKey)) {
                        NowPlayingFragment.this.bA = true;
                        NowPlayingFragment.this.aK();
                        NowPlayingFragment.this.b(2131296427);
                    }
                    if (object.containsKey("UNBOOKMARKED_PERFORMANCE") && ((PerformanceV2)object.get((Object)"UNBOOKMARKED_PERFORMANCE")).performanceKey.equals(NowPlayingFragment.this.aV.performanceKey)) {
                        NowPlayingFragment.this.bA = false;
                        NowPlayingFragment.this.aK();
                        NowPlayingFragment.this.b(2131296433);
                    }
                }
            }
        };
        this.bG = "FAVORITE_ID";
        this.bH = "BOOKMARK_ID";
        this.bI = "BOOST_ID";
        this.bM = new BaseAdapter(){

            public int getCount() {
                if (NowPlayingFragment.this.aU != null) {
                    return NowPlayingFragment.this.aU.size();
                }
                return 0;
            }

            public Object getItem(int n) {
                return null;
            }

            public long getItemId(int n) {
                return 0;
            }

            public View getView(int n, View view, ViewGroup viewGroup) {
                if (NowPlayingFragment.this.aU.size() < n) {
                    Log.d(bh, "getView - mLovesArray is not large enough; returning empty view");
                    return new View((Context)NowPlayingFragment.this.getActivity());
                }
                return NowPlayingFragment.this.a(view, NowPlayingFragment.this.aU.get((int)n).accountIcon, 0, false);
            }
        };
        this.bN = new BaseAdapter(){

            public int getCount() {
                return NowPlayingFragment.this.br.size();
            }

            public Object getItem(int n) {
                return null;
            }

            public long getItemId(int n) {
                return 0;
            }

            public View getView(int n, View view, ViewGroup object) {
                if (NowPlayingFragment.this.br.size() < n) {
                    Log.d(bh, "getView - recentTracks is not large enough; returning empty view");
                    return new View((Context)NowPlayingFragment.this.getActivity());
                }
                object = (Track)NowPlayingFragment.this.br.get(n);
                return NowPlayingFragment.this.a(view, object.accountIcon, object.createdAt, true);
            }
        };
    }

    private View a(View object, AccountIcon accountIcon, long l, boolean bl) {
        if (object == null || !(object instanceof UserFollowListItem)) {
            object = UserFollowListItem.c((Context)this.getActivity());
        }
        UserFollowListItem userFollowListItem = (UserFollowListItem)((Object)object);
        userFollowListItem.a(accountIcon, this.getActivity(), false, this);
        userFollowListItem.setTime(l);
        if (bl) {
            userFollowListItem.setJoinersStyle(this.getResources());
        }
        return object;
    }

    public static NowPlayingFragment a(PerformanceV2 object, boolean bl, boolean bl2, Analytics searchTarget, boolean bl3, int n, NowPlayingFragmentMenuSelectedCallback nowPlayingFragmentMenuSelectedCallback, int n2) {
        object = NowPlayingFragment_.aj().a((PerformanceV2)object).a(bl).c(bl3).b(bl2).a(searchTarget).b(n).a(n2).a();
        object.bn = nowPlayingFragmentMenuSelectedCallback;
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(final Activity activity) {
        if (this.bl || this.aV != null && this.aV.x()) {
            ((BaseActivity)this.getActivity()).a(this.bm, true, null);
            return;
        } else {
            if (this.aV == null) return;
            {
                TextAlertDialog textAlertDialog = new TextAlertDialog((Context)activity, activity.getString(2131297027), activity.getString(2131297028));
                textAlertDialog.a(2131296713, 2131296672);
                textAlertDialog.a(new Runnable(){

                    @Override
                    public void run() {
                        Log.c(bh, "Reporting performance as abusive. Performacne key : " + NowPlayingFragment.this.aV.performanceKey);
                        final BusyDialog busyDialog = new BusyDialog(activity, activity.getString(2131297030));
                        busyDialog.show();
                        com.smule.android.network.managers.PerformanceManager.a().a(NowPlayingFragment.this.aV.performanceKey, new NetworkResponseCallback(){

                            @Override
                            public void handleResponse(com.smule.android.network.core.NetworkResponse object) {
                                busyDialog.dismiss();
                                if (!object.c()) {
                                    NowPlayingFragment.this.b(2131297029);
                                    return;
                                }
                                object = new TextAlertDialog((Context)activity, activity.getString(2131297021), activity.getString(2131297022) + "\n\n" + activity.getString(2131297528), true, false);
                                object.a(2131296705, 0);
                                object.show();
                            }
                        });
                    }

                });
                textAlertDialog.show();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(SpannableStringBuilder spannableStringBuilder, boolean bl) {
        int n = this.getResources().getColor(2131689822);
        String string2 = this.getResources().getString(2131297025, new Object[]{" "});
        Iterator<PerformancePost> iterator = this.aU.iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            final PerformancePost performancePost = iterator.next();
            int n3 = n2;
            if (performancePost.accountIcon != null) {
                n3 = n2;
                if (performancePost.accountIcon.handle != null) {
                    if (spannableStringBuilder.length() != 0) {
                        spannableStringBuilder.append((CharSequence)", ");
                    } else if (!bl) {
                        spannableStringBuilder.append((CharSequence)string2);
                    }
                    n3 = spannableStringBuilder.length();
                    spannableStringBuilder.append((CharSequence)performancePost.accountIcon.handle);
                    spannableStringBuilder.setSpan((Object)new MagicClickableSpan(n, n, false){

                        public void onClick(View view) {
                            NowPlayingFragment.this.a(NowPlayingFragment.this.ao, performancePost);
                        }
                    }, n3, performancePost.accountIcon.handle.length() + n3 - 1, 17);
                    n3 = ++n2;
                    if (n2 == 3) {
                        return;
                    }
                }
            }
            n2 = n3;
        }
    }

    private void a(View view, Mode mode) {
        this.E.a(this, this.D, true);
        this.a(view, 0.0f, 1.0f);
        this.bk = mode;
        this.A();
    }

    private void a(final TextView textView, @ColorInt int n, final PerformancePost performancePost) {
        StyleSpan styleSpan = new StyleSpan(1);
        SpannableString spannableString = new SpannableString((CharSequence)(performancePost.accountIcon.handle + " " + performancePost.message));
        spannableString.setSpan((Object)styleSpan, 0, performancePost.accountIcon.handle.length(), 17);
        spannableString.setSpan((Object)new MagicClickableSpan(n, n, false){

            public void onClick(View view) {
                NowPlayingFragment.this.a(textView, performancePost);
            }
        }, 0, performancePost.accountIcon.handle.length(), 17);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText((CharSequence)spannableString);
        textView.setVisibility(0);
    }

    private void a(TextView object, PerformancePost performancePost) {
        if (performancePost.accountIcon.d()) {
            if ((object = (MediaPlayingActivity)MiscUtils.a((View)object)) == null) {
                return;
            }
            object.aq();
            object.finish();
            performancePost = MasterActivity.a((Context)object);
            performancePost.setData(DeepLink.a((DeepLink.Hosts)DeepLink.Hosts.c, (String)null));
            object.startActivity((Intent)performancePost);
            return;
        }
        object = ProfileFragment.a(performancePost.accountIcon);
        this.a(Analytics.b);
        this.a((ProfileFragment)object);
    }

    private void a(AccountIcon accountIcon) {
        this.a(ProfileFragment.a(accountIcon));
        this.af();
    }

    static /* synthetic */ void a(NowPlayingFragment nowPlayingFragment, Activity activity) {
        nowPlayingFragment.a(activity);
    }

    private void a(String string2, int n) {
        this.f(string2);
        this.af.setPerformanceCount(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aA() {
        Log.b(bh, "loadMedia()");
        if (this.aV != null && this.aV.x() || this.bl || this.aV.w()) {
            MediaPlayerServiceController.a().b(this.aV.performanceKey);
            this.x.c();
            return;
        } else {
            boolean bl;
            Object object;
            if (this.aV != null && this.aV.a()) {
                object = new QueueItem(SongbookEntry.a(this.aV.arrangementVersion), this.aV);
                bl = this.ba != null;
                object.b(bl);
                this.x.a((QueueItem)object, this.B);
            }
            object = bh;
            StringBuilder stringBuilder = new StringBuilder().append("Is performance info fetched? ").append(this.bp).append(". Is performance is rendered: ");
            bl = this.aV != null && this.aV.a();
            Log.b((String)object, stringBuilder.append(bl).append(".").toString());
            if (!this.bp || this.aV == null || this.aV.a() || this.bq) return;
            {
                this.bq = true;
                object = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296624), this.getString(2131296625), true, false);
                object.a(this.getString(2131296705), "");
                object.a(new CustomAlertDialog.CustomAlertDialogListener(){

                    @Override
                    public void a(CustomAlertDialog customAlertDialog) {
                        if (NowPlayingFragment.this.isAdded()) {
                            NowPlayingFragment.this.a(NowPlayingFragment.this);
                        }
                    }

                    @Override
                    public void b(CustomAlertDialog customAlertDialog) {
                        if (NowPlayingFragment.this.isAdded()) {
                            NowPlayingFragment.this.a(NowPlayingFragment.this);
                        }
                    }
                });
                object.show();
                return;
            }
        }
    }

    private void aB() {
        this.a(this.aG, Mode.d);
    }

    private void aC() {
        if (!this.isAdded()) {
            return;
        }
        this.a(this.aL, Mode.c);
    }

    private void aD() {
        this.c(this.aL);
    }

    private void aE() {
        this.c(this.aG);
    }

    private boolean aF() {
        if (this.bv != null && this.bv.isShowing()) {
            return true;
        }
        return false;
    }

    private boolean aG() {
        if (this.bv == null) {
            return false;
        }
        boolean bl = this.bv.isShowing();
        this.bv.dismiss();
        this.E.a(this, this.D, false);
        this.bv.setOnDismissListener(null);
        this.bv = null;
        return bl;
    }

    private boolean aH() {
        if (this.aG()) {
            this.aI();
            return true;
        }
        return false;
    }

    private void aI() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null && view instanceof EditText) {
            this.b(view);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aJ() {
        int n;
        if (this.isAdded() && this.bu != null && this.bu.isShowing() && this.bK.get(n = this.bJ.indexOf("FAVORITE_ID")) != null) {
            ArrayList<String> arrayList = this.bK;
            String string2 = this.bz ? this.getString(2131296730) : this.getString(2131296684);
            arrayList.set(n, string2);
            this.bL.notifyDataSetChanged();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aK() {
        int n;
        if (this.isAdded() && this.bu != null && this.bu.isShowing() && (n = this.bJ.indexOf("BOOKMARK_ID")) != -1 && this.bK.get(n) != null) {
            ArrayList<String> arrayList = this.bK;
            String string2 = this.bA ? this.getString(2131296670) : this.getString(2131296669);
            arrayList.set(n, string2);
            this.bL.notifyDataSetChanged();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aL() {
        if (this.aV.q()) {
            if (!this.aV.boost) {
                com.smule.android.network.managers.BoostManager.a().a(new BoostManager(){

                    @Override
                    public void handleResponse(BoostManager.BoostAvailabilityResponse boostAvailabilityResponse) {
                        if (boostAvailabilityResponse.a()) {
                            BoostPresenter.a().a((MediaPlayingActivity)NowPlayingFragment.this.getActivity(), new BoostContext(SubscriptionManager.a().b(), com.smule.android.network.managers.BoostManager.a().b(), false, NowPlayingFragment.this.aV));
                        }
                    }
                });
                return;
            }
            BoostPresenter.a().a((MediaPlayingActivity)this.getActivity(), new BoostContext(SubscriptionManager.a().b(), com.smule.android.network.managers.BoostManager.a().b(), false, this.aV));
            return;
        } else {
            if (!this.aV.r()) return;
            {
                new LearnHowToBoostDialog((Context)this.getActivity()).a(SubscriptionManager.a().b()).a(this.aV).show();
                SingAnalytics.J();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aM() {
        if (this.bl || this.aV != null && this.aV.x()) {
            ((BaseActivity)this.getActivity()).a(this.bm, true, null);
            return;
        }
        if (this.bC) return;
        this.bC = true;
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> arrayList2 = new ArrayList<String>();
        if (this.bA) {
            SingAnalytics.b((String)this.aV.performanceKey, (String)PerformanceV2Util.f((PerformanceV2)this.aV), (String)PerformanceV2Util.h((PerformanceV2)this.aV), (boolean)this.aV.video);
            arrayList2.add(this.aV.performanceKey);
        } else {
            SingAnalytics.a((String)this.aV.performanceKey, (String)PerformanceV2Util.f((PerformanceV2)this.aV), (String)PerformanceV2Util.h((PerformanceV2)this.aV), (boolean)this.aV.video);
            arrayList.add(this.aV.performanceKey);
        }
        com.smule.android.network.managers.PerformanceManager.a().b(arrayList, arrayList2, new NetworkResponseCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(com.smule.android.network.core.NetworkResponse object) {
                if (!NowPlayingFragment.this.isAdded()) {
                    return;
                }
                NowPlayingFragment.this.bC = false;
                if (object.c()) {
                    if (NowPlayingFragment.this.bA) {
                        UserManager.a().k(NowPlayingFragment.this.aV.performanceKey);
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", NowPlayingFragment.this.aV);
                    } else {
                        UserManager.a().j(NowPlayingFragment.this.aV.performanceKey);
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", NowPlayingFragment.this.aV);
                        ((MediaPlayingActivity)NowPlayingFragment.this.getActivity()).am().a(NowPlayingFragment.this, NowPlayingFragment.this.P, NowPlayingFragment.this.Q, true);
                    }
                    MagicDataSource.a(ProfileOpenCallDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                    return;
                }
                if (object.b == 1015) {
                    new TextAlertDialog((Context)NowPlayingFragment.this.getActivity(), NowPlayingFragment.this.getString(2131296432), NowPlayingFragment.this.getString(2131296431), true, false).show();
                    return;
                }
                if (object.b != 1021 && object.b != 1012) {
                    new TextAlertDialog((Context)NowPlayingFragment.this.getActivity(), NowPlayingFragment.this.getString(2131296795), NowPlayingFragment.this.getString(2131296895), true, false).show();
                    return;
                }
                Log.d(bh, "Attempting to bookmark non-joinable performance: " + NowPlayingFragment.this.aV.performanceKey, new MediaPlayingMenuManager.BookmarkNonSeedException("perfKey: " + NowPlayingFragment.this.aV.performanceKey).fillInStackTrace());
                object = new TextAlertDialog((Context)NowPlayingFragment.this.getActivity(), "", NowPlayingFragment.this.getResources().getString(2131296430), true, false);
                object.a(NowPlayingFragment.this.getString(2131296705), "");
                object.show();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aN() {
        if (this.bl || this.aV != null && this.aV.x()) {
            ((BaseActivity)this.getActivity()).a(this.bm, true, null);
            return;
        }
        if (this.bB) return;
        this.bB = true;
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> arrayList2 = new ArrayList<String>();
        if (this.bz) {
            arrayList2.add(this.aV.performanceKey);
        } else {
            Analytics ensemble = SingBundle.PerformanceType.a(this.aV.ensembleType).a();
            com.smule.android.logging.Analytics.a(this.aV.performanceKey, null, ensemble, null, this.aQ(), this.aV.video);
            arrayList.add(this.aV.performanceKey);
        }
        com.smule.android.network.managers.PerformanceManager.a().a(arrayList, arrayList2, new NetworkResponseCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(com.smule.android.network.core.NetworkResponse networkResponse) {
                if (!NowPlayingFragment.this.isAdded()) {
                    return;
                }
                NowPlayingFragment.this.bB = false;
                if (networkResponse.c()) {
                    if (NowPlayingFragment.this.bz) {
                        UserManager.a().h(NowPlayingFragment.this.aV.performanceKey);
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", NowPlayingFragment.this.aV);
                    } else {
                        UserManager.a().g(NowPlayingFragment.this.aV.performanceKey);
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", NowPlayingFragment.this.aV);
                        ((MediaPlayingActivity)NowPlayingFragment.this.getActivity()).am().a(NowPlayingFragment.this, NowPlayingFragment.this.P, NowPlayingFragment.this.Q, false);
                    }
                    MagicDataSource.a(ProfileFavoritesDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                    return;
                }
                if (networkResponse.b == 1015) {
                    new TextAlertDialog((Context)NowPlayingFragment.this.getActivity(), NowPlayingFragment.this.getString(2131296797), NowPlayingFragment.this.getString(2131296796), true, false).show();
                    return;
                }
                new TextAlertDialog((Context)NowPlayingFragment.this.getActivity(), NowPlayingFragment.this.getString(2131296795), NowPlayingFragment.this.getString(2131296895), true, false).show();
            }
        });
    }

    private void aO() {
        if (this.bl || this.aV != null && this.aV.x()) {
            ((BaseActivity)this.getActivity()).a(this.bm, true, null);
            return;
        }
        if (this.getView() == null || this.getView().getParent() == null) {
            Log.e(bh, "Parent view was null");
            return;
        }
        PerformanceSaveFragment performanceSaveFragment = PerformanceSaveFragmentFactory.a(this.aV);
        this.x.e();
        this.h(false);
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(((ViewGroup)this.getView().getParent()).getId(), (Fragment)performanceSaveFragment, PerformanceSaveFragment.class.getName());
        fragmentTransaction.addToBackStack(PerformanceSaveFragment.class.getName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    private String aP() {
        if (this.aV != null) {
            return this.aV.title;
        }
        return "";
    }

    private String aQ() {
        return SingAnalytics.d((PerformanceV2)this.aV);
    }

    private void aR() {
        this.U.setVisibility(4);
        this.T.setText((CharSequence)this.getString(2131297024));
    }

    private void aj() {
        this.bs = false;
        if (this.bu != null && this.bu.isShowing()) {
            this.bu.dismiss();
            this.bu = null;
        }
        if (this.aT != null) {
            this.aT.a();
            this.aT = null;
        }
    }

    private LocalizedShortNumberFormatter ak() {
        if (this.by == null) {
            this.by = new LocalizedShortNumberFormatter((Context)this.getActivity());
        }
        return this.by;
    }

    private void al() {
        this.aR.setVisibility(8);
    }

    private void am() {
        this.aI.setAdapter((ListAdapter)this.bM);
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.aB();
            }
        };
        this.am.setOnClickListener(onClickListener);
        this.an.setOnClickListener(onClickListener);
        this.j(false);
        this.aP.setAccount(UserManager.a().O());
    }

    private void an() {
        if (this.aV == null) {
            return;
        }
        this.br = new ArrayList<Track>(this.aV.recentTracks);
        int n = this.br.size() - 1;
        do {
            block6 : {
                block5 : {
                    if (n < 0) break block5;
                    if (this.br.get((int)n).accountIcon.accountId != this.aV.accountIcon.accountId) break block6;
                    this.br.remove(n);
                }
                this.aN.setAdapter((ListAdapter)this.bN);
                this.aa();
                return;
            }
            --n;
        } while (true);
    }

    private void ao() {
        this.ay.setText((CharSequence)this.getResources().getQuantityString(2131361834, this.aV.totalListens, new Object[]{this.ak().a(this.aV.totalListens, this.getResources().getInteger(2131623962))}));
        this.am.setMaxWidth(this.al.getWidth() / 2 - (int)(this.getResources().getDimension(2131428169) / 2.0f));
    }

    private void ap() {
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                if (NowPlayingFragment.this.bl || NowPlayingFragment.this.aV != null && NowPlayingFragment.this.aV.x()) {
                    ((BaseActivity)NowPlayingFragment.this.getActivity()).a(NowPlayingFragment.this.bm, true, null);
                    return;
                }
                ShareUtils.a((Activity)NowPlayingFragment.this.getActivity(), (PerformanceV2)NowPlayingFragment.this.aV);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.d("");
            }
        };
        View.OnClickListener onClickListener3 = new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.ab();
            }
        };
        View.OnClickListener onClickListener4 = new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.ac();
            }
        };
        this.az.setOnClickListener(onClickListener);
        this.aF.setOnClickListener(onClickListener);
        this.aA.setOnClickListener(onClickListener2);
        this.aE.setOnClickListener(onClickListener2);
        this.aB.setOnClickListener(onClickListener3);
        this.aD.setOnClickListener(onClickListener3);
        this.aC.setOnClickListener(onClickListener4);
        this.aK.setOnClickListener(onClickListener3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void aq() {
        String string2 = MiscUtils.a((long)this.aV.createdAt, (boolean)false, (boolean)true);
        String string3 = this.aV.message;
        if (TextUtils.isEmpty((CharSequence)string2) && TextUtils.isEmpty((CharSequence)string3)) {
            Log.e(bh, "timeStamp should not be null");
            this.ah.setVisibility(8);
            this.ak.setVisibility(8);
            return;
        } else {
            this.ah.setVisibility(0);
            this.ak.setVisibility(0);
            if (!TextUtils.isEmpty((CharSequence)string3)) {
                this.ar();
            }
            if (TextUtils.isEmpty((CharSequence)string2)) return;
            {
                this.aj.setText((CharSequence)string2);
                return;
            }
        }
    }

    private void ar() {
        SpannableString spannableString = new SpannableString((CharSequence)this.aV.message);
        Hashtag.a((MediaPlayingActivity)this.getActivity(), spannableString);
        this.ai.setMovementMethod((MovementMethod)new LinkMovementMethod());
        this.ai.setText((CharSequence)spannableString);
        this.ai.setHighlightColor(0);
    }

    private void as() {
        this.V.setAllowMatchParent(true);
        ImageUtils.a(this.aV.coverUrl, (ImageView)this.V, (ImageLoadingListener)new SimpleImageLoadingListener());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void at() {
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.ac, (AccountIcon)this.aV.accountIcon);
        Object object = new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.a(NowPlayingFragment.this.aV.accountIcon);
            }
        };
        this.ab.a(this.aV.accountIcon, (View.OnClickListener)object);
        this.ac.setOnClickListener((View.OnClickListener)object);
        this.ae.setVisibility(8);
        this.ad.setVisibility(8);
        if (this.aV.recentTracks == null || this.aV.recentTracks.size() < 1) {
            return;
        }
        if (this.aV.p()) {
            if (this.aV.f()) {
                this.aw();
            } else {
                int n = this.aV.origTrackPartId == 2 ? 2131297179 : 2131297180;
                this.f(this.getString(n));
            }
        } else if (PerformanceV2Util.b((PerformanceV2)this.aV)) {
            this.a(this.getResources().getQuantityString(2131361800, this.aV.childCount, new Object[]{this.ak().a(this.aV.childCount, this.getResources().getInteger(2131623962))}), this.aV.childCount);
            this.af.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NowPlayingFragment.this.a(OpenCallPerformancesListFragment.a(NowPlayingFragment.this.aV));
                    NowPlayingFragment.this.af();
                }
            });
        } else if (this.aV.e()) {
            this.ae.setVisibility(0);
            this.ad.setVisibility(0);
            object = this.aV.recentTracks.get((int)0).accountIcon;
            PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.ag, (AccountIcon)object);
            View.OnClickListener onClickListener = new View.OnClickListener((AccountIcon)object){
                final /* synthetic */ AccountIcon a;
                {
                    this.a = accountIcon;
                }

                public void onClick(View view) {
                    NowPlayingFragment.this.a(this.a);
                }
            };
            this.af.a((AccountIcon)object, onClickListener);
            this.af.setPerformanceCount(-1);
            this.ag.setOnClickListener(onClickListener);
            this.ax();
        } else if (this.aV.f()) {
            this.aw();
        }
        this.an();
        this.au();
        object = new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.d("");
            }
        };
        this.ap.setOnClickListener((View.OnClickListener)object);
        this.aq.setOnClickListener((View.OnClickListener)object);
    }

    private void au() {
        int n = this.aV.totalComments;
        String string2 = this.getActivity().getResources().getQuantityString(2131361799, n, new Object[]{this.ak().a(n, this.getActivity().getResources().getInteger(2131623962))});
        this.aE.setText((CharSequence)this.ak().a(n, this.getActivity().getResources().getInteger(2131623962)));
        this.ap.setText((CharSequence)string2);
        if (this.aV.totalComments > 0) {
            this.aq.setVisibility(0);
            return;
        }
        this.aq.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void av() {
        if (!this.bx) return;
        {
            this.bx = false;
            if (this.bw == null) {
                Log.e(bh, "startLazyFetchingOfFullScreenData - mCommentsDataSource is null");
                return;
            } else {
                if (this.aV == null || this.aV.totalComments <= 0 || this.bw.a() >= 2 || this.bw.c()) return;
                {
                    this.bw.e();
                    return;
                }
            }
        }
    }

    private void aw() {
        this.ae.setVisibility(0);
        this.ad.setVisibility(0);
        int n = this.aV.totalPerformers - 1;
        this.af.setVIP(false);
        this.af.setPerformanceCount(n);
        this.ag.setText((CharSequence)this.getResources().getQuantityString(2131361832, n, new Object[]{this.ak().a(n, this.getResources().getInteger(2131623962))}));
        if (n > 0) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Log.c(bh, "mSecondPerformerImageView clicked!!!");
                    NowPlayingFragment.this.aC();
                }
            };
            this.af.setOnClickListener(onClickListener);
            this.ag.setOnClickListener(onClickListener);
        }
        this.ax();
    }

    private void ax() {
        int n;
        int n2 = this.aa.getWidth();
        if (this.ab.getWidth() + this.ac.getWidth() < n2) {
            ((LinearLayout.LayoutParams)this.aa.getLayoutParams()).weight = 0.0f;
        }
        if (n2 > (n = this.Z.getWidth() - this.ad.getWidth()) / 2) {
            ((LinearLayout.LayoutParams)this.aa.getLayoutParams()).weight = 0.0f;
            this.aa.getLayoutParams().width = n / 2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ay() {
        if (this.aV != null) {
            Object object;
            if (!TextUtils.isEmpty((CharSequence)this.aP())) {
                this.g.a(null, this.aV);
                this.aH.a(null, this.aV);
                this.aM.a(null, this.aV);
            }
            if (this.aV.x() || this.bl) {
                this.g.c();
                this.W.setVisibility(0);
                if (this.bl) {
                    object = MiscUtils.a((int)this.bm, (Boolean)true);
                    this.X.setText((CharSequence)object.first);
                    this.Y.setText((CharSequence)MessageFormat.format((String)object.second, this.getString(2131297940)));
                } else {
                    this.X.setText((CharSequence)this.getString(2131297074));
                    this.Y.setText((CharSequence)MessageFormat.format(this.getString(2131297075), this.getString(2131297940)));
                }
            }
            Log.b(bh, "setupTitleBar - mPerformance.isJoinable() = " + this.aV.p() + " because: seed = " + this.aV.seed + ", hasExpired = " + this.aV.k() + ", closed = " + this.aV.closed);
            CustomToolbar customToolbar = this.g;
            object = this.aV.p() ? this.getString(2131296695) : this.getString(2131296720);
            customToolbar.setRightButtonText((String)object);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void az() {
        if (!(this.aV != null && this.aV.x() || this.bl)) {
            this.t.setText((CharSequence)this.aP());
            this.u.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.aV, (boolean)true));
            this.p.setVisibility(0);
            this.p.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NowPlayingFragment.this.ab();
                }
            });
            return;
        }
        this.t.setTextColor(this.getResources().getColor(2131689832));
        this.u.setTextSize(0, (float)this.getResources().getDimensionPixelSize(2131427628));
        if (this.bl) {
            Pair pair = MiscUtils.a((int)this.bm, (Boolean)true);
            this.t.setText((CharSequence)pair.first);
            this.u.setText((CharSequence)MessageFormat.format((String)pair.second, this.getString(2131297940)));
        } else {
            this.t.setText((CharSequence)this.getString(2131297074));
            this.u.setText((CharSequence)this.getString(2131297076));
        }
        this.q.setVisibility(4);
    }

    private void b(SpannableStringBuilder spannableStringBuilder, boolean bl) {
        StyleSpan styleSpan = new StyleSpan(1);
        String string2 = this.getResources().getString(2131297025, new Object[]{" "});
        if (bl) {
            spannableStringBuilder.append((CharSequence)string2);
            spannableStringBuilder.setSpan((Object)styleSpan, 0, spannableStringBuilder.length(), 17);
            return;
        }
        spannableStringBuilder.setSpan((Object)styleSpan, string2.length(), spannableStringBuilder.length(), 17);
    }

    private void b(View view) {
        ((InputMethodManager)this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void c(View view) {
        this.E.a(this, this.D, false);
        this.a(view, 1.0f, 0.0f);
        this.bk = Mode.b;
        this.A();
    }

    private void c(boolean bl, boolean bl2) {
        if (bl) {
            Iterator<PerformancePost> iterator = this.aU.iterator();
            boolean bl3 = false;
            while (iterator.hasNext()) {
                if (iterator.next().accountIcon.accountId != UserManager.a().f()) continue;
                bl3 = true;
            }
            if (bl3) {
                Log.b(bh, "getPerformanceLoves - getLoves API indicated performance already loved; adding to cache");
                StringCacheManager.a().a(this.aV.performanceKey);
                this.aX = true;
            }
        }
        if (this.aU != null) {
            Collections.sort(this.aU, new Comparator<PerformancePost>(){

                public int a(PerformancePost performancePost, PerformancePost performancePost2) {
                    return performancePost.accountIcon.handle.toLowerCase().compareTo(performancePost2.accountIcon.handle.toLowerCase());
                }

                @Override
                public /* synthetic */ int compare(Object object, Object object2) {
                    return this.a((PerformancePost)object, (PerformancePost)object2);
                }
            });
        }
        if (bl2) {
            StringCacheManager.a().a(this.aV.performanceKey);
        }
        this.Y();
    }

    private void f(String string2) {
        this.ae.setVisibility(0);
        this.ad.setVisibility(0);
        this.ag.setText((CharSequence)string2);
        this.af.setVIP(false);
        this.ae.setClickable(false);
        this.af.setClickable(false);
        this.af.setPerformanceText(string2);
        this.ax();
    }

    private void j(boolean bl) {
        if (this.aU == null || bl) {
            com.smule.android.network.managers.PerformanceManager.a().a(this.aV.performanceKey, new PerformanceManager(){

                @Override
                public void handleResponse(PerformanceManager.PerformanceLovesResponse performanceLovesResponse) {
                    NowPlayingFragment.this.aU = new ArrayList();
                    if (!performanceLovesResponse.a()) {
                        return;
                    }
                    for (PerformancePost performancePost : performanceLovesResponse.mPerformancePosts) {
                        if (performancePost.accountIcon == null || performancePost.accountIcon.handle == null) continue;
                        NowPlayingFragment.this.aU = performanceLovesResponse.mPerformancePosts;
                    }
                    NowPlayingFragment.this.c(true, false);
                }
            });
            return;
        }
        this.Y();
    }

    static /* synthetic */ ArrayList n(NowPlayingFragment nowPlayingFragment) {
        return nowPlayingFragment.bJ;
    }

    static /* synthetic */ void o(NowPlayingFragment nowPlayingFragment) {
        nowPlayingFragment.aL();
    }

    static /* synthetic */ void p(NowPlayingFragment nowPlayingFragment) {
        nowPlayingFragment.aM();
    }

    static /* synthetic */ void q(NowPlayingFragment nowPlayingFragment) {
        nowPlayingFragment.aN();
    }

    static /* synthetic */ void r(NowPlayingFragment nowPlayingFragment) {
        nowPlayingFragment.aO();
    }

    static /* synthetic */ void s(NowPlayingFragment nowPlayingFragment) {
        nowPlayingFragment.aj();
    }

    @Override
    protected void A() {
        switch (.a[this.bk.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                SingAnalytics.f((String)PerformanceV2Util.f((PerformanceV2)this.aV), (String)SingAnalytics.d((PerformanceV2)this.aV));
                return;
            }
            case 3: 
        }
        SingAnalytics.c((String)PerformanceV2Util.f((PerformanceV2)this.aV));
    }

    public PerformanceV2 S() {
        if (this.aV != null) {
            return this.aV;
        }
        Log.d(bh, "getPerformance - returning null!");
        return null;
    }

    public String T() {
        if (this.aV != null) {
            return this.aV.performanceKey;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void U() {
        Log.b(bh, "loadPerformance() called");
        if (this.bs || this.x.c(this.aV.performanceKey)) {
            Log.b(bh, "Performance with id, " + this.aV.performanceKey + ", is already playing; no more setup required");
            return;
        }
        this.bp = false;
        this.bq = false;
        this.bt = MiscUtils.a((PerformanceV2)this.aV);
        if (this.aV == null) {
            Log.e(bh, "Something has gone terribly wrong!");
            return;
        }
        if (this.aT != null) {
            this.aT.a();
        }
        final boolean bl = this.aV.v() && this.aV.arrangementVersion == null;
        Log.b(bh, "needArrDetailsBeforePlaying? " + bl);
        if (!bl) {
            this.V();
        }
        Log.b(bh, "fetching details performance with id: " + this.aV.performanceKey);
        MediaPlayerServiceController.a().a(this.aV.performanceKey);
        final int n = this.e;
        com.smule.android.network.managers.PerformanceManager.a().a(this.aV.performanceKey, false, new PerformanceManager(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(PerformanceManager.PerformanceResponse object) {
                if (!NowPlayingFragment.this.a(n)) {
                    return;
                }
                if (object.a() && object.mPerformance != null && !object.mPerformance.x()) {
                    NowPlayingFragment.this.bp = true;
                    NowPlayingFragment.this.aV = object.mPerformance;
                    NowPlayingFragment.this.bl = false;
                    NowPlayingFragment.this.aY = NowPlayingFragment.this.aV.webUrl;
                    if (bl) {
                        NowPlayingFragment.this.V();
                    }
                    NowPlayingFragment.this.at();
                } else if (object.a.e()) {
                    NowPlayingFragment.this.bl = true;
                    NowPlayingFragment.this.bm = object.a.f;
                } else if (object.a.c() && object.mPerformance != null && object.mPerformance.x()) {
                    NowPlayingFragment.this.bl = true;
                    NowPlayingFragment.this.bm = object.mPerformance.removalCode;
                } else {
                    NowPlayingFragment.this.aR();
                    MediaPlayerServiceController.a().b(NowPlayingFragment.this.aV.performanceKey);
                }
                if (NowPlayingFragment.this.bl) {
                    MediaPlayerServiceController.a().e();
                    BaseActivity baseActivity = (BaseActivity)NowPlayingFragment.this.getActivity();
                    int n2 = NowPlayingFragment.this.bm;
                    Runnable runnable = new Runnable(){

                        @Override
                        public void run() {
                            NowPlayingFragment.this.a(NowPlayingFragment.this);
                        }
                    };
                    object = NowPlayingFragment.this.aV.o() ? new Runnable(){

                        @Override
                        public void run() {
                            NowPlayingFragment.this.a(NowPlayingFragment.this.getActivity(), NowPlayingFragment.this.aV.performanceKey);
                        }
                    } : null;
                    baseActivity.a(n2, true, runnable, (Runnable)object);
                }
                NowPlayingFragment.this.bs = true;
            }

        });
    }

    protected void V() {
        Log.b(bh, "renderMediaIfNecessary(), already rendered? " + this.aV.a());
        if (this.aV.a()) {
            this.W();
            return;
        }
        this.aT = new MediaRenderTask(new MediaRenderTask.MediaRenderTaskListener(){

            @Override
            public void a() {
                Log.d(bh, "   render FAILED:  " + NowPlayingFragment.this.aV.performanceKey);
                NowPlayingFragment.this.aR();
                MediaPlayerServiceController.a().b(NowPlayingFragment.this.aV.performanceKey);
            }

            @Override
            public void a(PerformanceV2 performanceV2) {
                Log.b(bh, "   render SUCCESS: " + performanceV2.performanceKey);
                NowPlayingFragment.this.aV = performanceV2;
                NowPlayingFragment.this.W();
            }
        });
        Toaster.a((Context)this.getActivity(), this.getResources().getString(2131297637));
        Log.b(bh, "rendering performance...");
        this.aT.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[]{this.aV.performanceKey});
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void W() {
        block11 : {
            block12 : {
                block10 : {
                    Log.b(bh, "showAndStartPerformance - shouldPlayVideo? " + this.a());
                    if (!this.isAdded()) break block10;
                    if (this.aV == null || this.aV.a()) break block11;
                    Log.d(bh, "trying to play un-rendered performance", new IllegalStateException("trying to play un-rendered performance"));
                    if (this.x != null) {
                        this.x.e();
                    }
                    if (this.getActivity() != null) break block12;
                }
                return;
            }
            ((MediaPlayingActivity)this.getActivity()).c(false);
            return;
        }
        this.az();
        this.ay();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.l.getLayoutParams();
        if (this.a()) {
            layoutParams.addRule(6, this.bf.getId());
            layoutParams.addRule(8, this.bf.getId());
            this.bf.setVisibility(0);
            this.l.setVisibility(0);
            this.bg.setVisibility(0);
            this.V.setVisibility(8);
            if (this.K()) {
                this.X();
            }
        } else {
            layoutParams.addRule(6, this.V.getId());
            layoutParams.addRule(8, this.V.getId());
            this.bf.setVisibility(8);
            this.l.setVisibility(8);
            this.bg.setVisibility(8);
            this.V.setVisibility(0);
            this.as();
        }
        if (this.aV != null && this.aV.x() || this.bl) {
            this.al();
        } else {
            this.t();
        }
        this.aA();
        this.at();
        this.aq();
        this.ao();
        this.ap();
        this.am();
        this.an();
        this.R.setVisibility(4);
        this.S.setVisibility(0);
    }

    public void X() {
        Log.b(bh, "configureVideoSurfaceIfNecessary");
        if (this.a() && this.aV != null && this.aV.c()) {
            this.x.a(this.getActivity(), this.bf, null, this.bg);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public void Y() {
        Log.b(bh, "refreshLoveViews - begin");
        if (!this.isAdded()) {
            Log.d(bh, "refreshLoveViews - fragment not added; aborting");
            return;
        }
        if (this.aV == null) {
            Log.d(bh, "refreshLoveViews - performance is null");
            return;
        }
        this.bM.notifyDataSetChanged();
        String string2 = this.getResources().getQuantityString(2131361807, this.aV.totalLoves, new Object[]{this.ak().a(this.aV.totalLoves, this.getResources().getInteger(2131623962))});
        this.aD.setText((CharSequence)this.ak().a(this.aV.totalLoves, this.getResources().getInteger(2131623962)));
        this.am.setText((CharSequence)string2);
        this.aJ.setText((CharSequence)string2);
        if (this.aV.totalLoves > 0) {
            this.an.setVisibility(0);
            string2 = new SpannableStringBuilder();
            boolean bl = this.getResources().getString(2131297025).substring(0, "%1$s".length()).equalsIgnoreCase("%1$s");
            if (this.aU != null) {
                this.a((SpannableStringBuilder)string2, bl);
            }
            if (string2.length() > 0) {
                this.b((SpannableStringBuilder)string2, bl);
                this.ao.setMovementMethod(LinkMovementMethod.getInstance());
                this.ao.setText((CharSequence)string2);
                this.ao.setVisibility(0);
            } else {
                this.ao.setVisibility(8);
            }
        } else {
            this.an.setVisibility(8);
            this.ao.setVisibility(8);
        }
        this.Z();
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void Z() {
        if (!this.isAdded()) {
            return;
        }
        if (this.aX) {
            this.p.setTextColor(ContextCompat.getColor((Context)this.getActivity(), (int)2131689500));
            this.p.setText(2131297813);
            this.aB.setTextColor(ContextCompat.getColor((Context)this.getActivity(), (int)2131689500));
            this.aB.setText(2131297813);
        } else {
            this.p.setTextColor(ContextCompat.getColor((Context)this.getActivity(), (int)2131689504));
            this.p.setText(2131297814);
            this.aB.setTextColor(ContextCompat.getColor((Context)this.getActivity(), (int)2131689546));
        }
        this.aK.setActivated(this.aX);
        this.aK.setVisibility(0);
        this.aS.setVisibility(8);
    }

    @UiThread
    protected void a(Activity activity, String string2) {
        final int n = this.e;
        NavigationUtils.a((Activity)activity, (PerformanceV2)this.aV, (Runnable)null, (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment.this.a(true, n);
            }
        }, (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment.this.a(false, n);
            }
        }, (boolean)true);
    }

    @Click
    protected void a(View view) {
        if (this.isAdded()) {
            this.P();
        }
    }

    @UiThread
    protected void a(final View view, final float f, final float f2) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{f, f2});
        objectAnimator.setInterpolator((TimeInterpolator)new DecelerateInterpolator());
        objectAnimator.setDuration(300);
        objectAnimator.setStartDelay(300);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
            }

            public void onAnimationEnd(Animator animator2) {
                if (f > f2) {
                    view.setVisibility(8);
                }
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
                if (f2 > f) {
                    view.setVisibility(0);
                }
            }
        });
    }

    @Override
    public void a(Analytics searchResultClkContext) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a(MediaPlayingFragment.AnimationDirection animationDirection, AnimatorListenerAdapter animatorListenerAdapter) {
        if (animationDirection == MediaPlayingFragment.AnimationDirection.a || animationDirection == MediaPlayingFragment.AnimationDirection.b) {
            this.bk = Mode.b;
            this.X();
            SingAppboy.a().d();
            this.av();
        } else {
            SingAppboy.a().e();
            this.bk = Mode.a;
        }
        PlaybackPresenter playbackPresenter = this.E;
        int n = this.D;
        boolean bl = this.bk == Mode.b;
        playbackPresenter.a(n, bl);
        super.a(animationDirection, animatorListenerAdapter);
    }

    @Override
    protected void a(NowPlayingFragment nowPlayingFragment) {
        this.aV = null;
        super.a(nowPlayingFragment);
    }

    @Override
    public void a(ProfileFragment profileFragment) {
        this.a(profileFragment);
        this.i(true);
    }

    public void a(Long l) {
        if (l != null) {
            this.bD = l;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final Runnable runnable, boolean bl) {
        Log.b(bh, "nowPlayingMiniBarClicked - begin : " + this.aV);
        if (this.aV == null || !this.isAdded() || this.K()) {
            return;
        }
        Object object = MasterActivity.b((Context)this.getActivity());
        if (object != null) {
            object.w();
        }
        object = bl ? MediaPlayingFragment.AnimationDirection.b : MediaPlayingFragment.AnimationDirection.a;
        this.a((MediaPlayingFragment.AnimationDirection)((Object)object), new AnimatorListenerAdapter(){

            public void onAnimationEnd(Animator animator2) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(@NonNull List<PerformancePost> list) {
        if (!this.isAdded() || this.as == null || this.ar == null) {
            return;
        }
        if (list.size() > 0) {
            this.a(this.ar, this.getResources().getColor(2131689820), list.get(0));
        } else {
            this.ar.setVisibility(8);
        }
        if (list.size() > 1) {
            this.a(this.as, this.getResources().getColor(2131689820), list.get(1));
            return;
        }
        this.as.setVisibility(8);
    }

    @UiThread
    protected void a(boolean bl, final int n) {
        if (bl) {
            if (this.x.j()) {
                Log.b(bh, "Performance was playing; halting performance");
                this.x.e();
            }
            this.a(new Runnable(){

                @Override
                public void run() {
                    if (NowPlayingFragment.this.a(n)) {
                        NowPlayingFragment.this.p().a(OpenCallPerformancesListFragment.class.getName());
                        NowPlayingFragment.this.a(NowPlayingFragment.this);
                    }
                }
            }, 400);
        }
    }

    @Override
    public void a(boolean bl, boolean bl2) {
        if (this.aL.getVisibility() == 0) {
            this.bN.notifyDataSetChanged();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public void a(final boolean bl, boolean bl2, Hashtag.HashtagCallback object) {
        if (!this.isAdded()) return;
        if (!this.J) {
            Log.b(bh, "lowerFragment - was not in full mode");
            if (object == null) return;
            {
                object.a();
                return;
            }
        }
        if (!bl2 && object != null) {
            Log.e(bh, "if you set a callback, lowerAll should be true");
        }
        Log.b(bh, "lowerFragment - begin");
        final AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter((Hashtag.HashtagCallback)object){
            final /* synthetic */ Hashtag.HashtagCallback a;
            {
                this.a = hashtagCallback;
            }

            public void onAnimationEnd(Animator animator2) {
                if (this.a != null) {
                    this.a.a();
                }
            }

            public void onAnimationStart(Animator animator2) {
            }
        };
        if (this.aF()) {
            Log.d(bh, "lowerFragment - mCommentsListDialog is visible, so fading that view out and aborting lowering the fragment");
            if (bl2) {
                this.bv.setOnDismissListener(new DialogInterface.OnDismissListener(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void onDismiss(DialogInterface object) {
                        NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                        object = bl ? MediaPlayingFragment.AnimationDirection.e : MediaPlayingFragment.AnimationDirection.c;
                        nowPlayingFragment.a((MediaPlayingFragment.AnimationDirection)((Object)object), animatorListenerAdapter);
                    }
                });
            }
            this.aH();
            return;
        }
        if (this.aG.getVisibility() == 0) {
            Log.d(bh, "lowerFragment - mLovesView is visible, so fading that view out and aborting lowering the fragment");
            this.aE();
            if (!bl2) return;
        }
        if (this.aL.getVisibility() == 0) {
            Log.d(bh, "lowerFragment - mJoinersView is visible, so fading that view out and aborting lowering the fragment");
            this.aD();
            if (!bl2) {
                return;
            }
        }
        object = bl ? MediaPlayingFragment.AnimationDirection.e : MediaPlayingFragment.AnimationDirection.c;
        this.a((MediaPlayingFragment.AnimationDirection)((Object)object), animatorListenerAdapter);
    }

    @Override
    public void a(boolean bl, boolean bl2, MediaPlayingPlayable object) {
        boolean bl3 = false;
        super.a(bl, bl2, (MediaPlayingPlayable)object);
        if (!this.isAdded()) {
            Log.e(bh, "showContinuousPlaySpecifics should not be called on a detached fragment");
            return;
        }
        if (object != null) {
            bl = MagicPreferences.h(this.av.getContext());
            this.av.setChecked(bl);
            this.av.setVisibility(0);
            this.av.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    MagicPreferences.e(compoundButton.getContext(), bl);
                    NowPlayingFragment.this.E.a(bl);
                }
            });
            this.at.setVisibility(0);
            this.au.setVisibility(0);
            if (object.a() != null) {
                object = object.a();
                PerformanceListItem performanceListItem = PerformanceListItem.a((Context)this.getActivity(), false, false);
                bl = bl3;
                if (object != null) {
                    bl = bl3;
                    if (object.video) {
                        bl = true;
                    }
                }
                performanceListItem.setRecordingType(bl);
                performanceListItem.setPerformance((PerformanceV2)object);
                performanceListItem.setListener(new PerformanceItemInterface.PerformanceItemListener(){

                    @Override
                    public void a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
                        if (NowPlayingFragment.this.isAdded()) {
                            NowPlayingFragment.this.E.a(NowPlayingFragment.this.D, PlaybackPresenter.ClickSource.d);
                        }
                    }

                    @Override
                    public void a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                        if (NowPlayingFragment.this.isAdded()) {
                            NowPlayingFragment.this.E.a(NowPlayingFragment.this.D, PlaybackPresenter.ClickSource.d);
                        }
                    }

                    @Override
                    public void b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                        if (NowPlayingFragment.this.isAdded()) {
                            NowPlayingFragment.this.E.a(NowPlayingFragment.this.D, PlaybackPresenter.ClickSource.d);
                        }
                    }
                });
                performanceListItem.setIsFirstElement(true);
                this.aw = performanceListItem;
                object = this.getView().findViewById(2131756148);
                performanceListItem = (ViewGroup)object.getParent();
                ViewGroup.LayoutParams layoutParams = object.getLayoutParams();
                this.aw.setLayoutParams(layoutParams);
                performanceListItem.removeView((View)object);
                performanceListItem.addView((View)this.aw);
                return;
            }
            Log.e(bh, "Unsupported item type");
            return;
        }
        this.at.setVisibility(8);
        this.au.setVisibility(8);
    }

    @Override
    protected boolean a() {
        return this.bt;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean a(int n, KeyEvent keyEvent) {
        block8 : {
            block7 : {
                if (!this.isAdded()) break block7;
                if (n == 4 && this.bd) {
                    Log.b(bh, "onFragmentKeyDown - closing all fragments.");
                    this.b(true, true);
                    this.bd = false;
                    return true;
                }
                if (n == 4 && this.aL.getVisibility() == 0) {
                    this.aD();
                    return true;
                }
                if (n == 4 && this.aG.getVisibility() == 0) {
                    this.aE();
                    return true;
                }
                if (n == 4 && this.bv != null && this.aH()) {
                    return true;
                }
                Log.b(bh, "onFragmentKeyDown - mIsInFullMode: " + this.J);
                if (n == 4 && this.J) break block8;
            }
            return false;
        }
        this.af();
        return true;
    }

    @UiThread
    public void aa() {
        Log.b(bh, "refreshJoinersViews - begin");
        if (!this.isAdded()) {
            Log.d(bh, "refreshJoinersViews - fragment not added; aborting");
            return;
        }
        if (this.aV == null) {
            Log.d(bh, "refreshJoinersViews - performance is null");
            return;
        }
        this.bN.notifyDataSetChanged();
        if (this.br.size() > 0) {
            String string2 = this.getResources().getQuantityString(2131361837, this.br.size(), new Object[]{this.ak().a(this.br.size())});
            this.aO.setText((CharSequence)string2);
        }
        this.Z();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void ab() {
        if (this.bl || this.aV != null && this.aV.x()) {
            ((BaseActivity)this.getActivity()).a(this.bm, true, null);
            return;
        }
        if (!this.isAdded()) return;
        {
            Log.b(bh, "executePerformanceLove - called");
            if (this.aX) {
                Log.b(bh, "executePerformanceLove - already loved; aborting");
                this.Y();
                return;
            }
        }
        if (this.bo) {
            Log.b(bh, "executePerformanceLove - in process of loving; aborting");
            this.Y();
            return;
        }
        this.bo = true;
        this.aK.setVisibility(8);
        this.aS.setVisibility(0);
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                Location location = LocationUtils.a();
                com.smule.android.network.managers.PerformanceManager.a().a(NowPlayingFragment.this.aV.performanceKey, (float)location.getLatitude(), (float)location.getLongitude(), new NetworkResponseCallback(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void handleResponse(com.smule.android.network.core.NetworkResponse object) {
                        if (!NowPlayingFragment.this.isAdded()) {
                            return;
                        }
                        NowPlayingFragment.this.aX = true;
                        NowPlayingFragment.this.aS.setVisibility(8);
                        if (object != null && object.a == NetworkResponse.a) {
                            NowPlayingFragment.this.c(NowPlayingFragment.this.aV.performanceKey);
                            SingAnalytics.a((String)NowPlayingFragment.this.aV.performanceKey, (String)PerformanceV2Util.f((PerformanceV2)NowPlayingFragment.this.aV), SingAnalytics.a((PerformanceV2)NowPlayingFragment.this.aV), (String)NowPlayingFragment.this.aQ(), (boolean)NowPlayingFragment.this.aV.video);
                            object = NowPlayingFragment.this.aV;
                            ++object.totalLoves;
                            object = new PerformancePost();
                            object.accountIcon = UserManager.a().O();
                            object.time = System.currentTimeMillis();
                            if (NowPlayingFragment.this.aU != null) {
                                NowPlayingFragment.this.aU.add((PerformancePost)object);
                            }
                            NowPlayingFragment.this.c(false, true);
                        } else if (object.e()) {
                            NowPlayingFragment.this.aK.setVisibility(8);
                            NowPlayingFragment.this.aS.setVisibility(8);
                            ((BaseActivity)NowPlayingFragment.this.getActivity()).a(object.f, true, null);
                        } else {
                            NowPlayingFragment.this.aK.setVisibility(0);
                        }
                        NowPlayingFragment.this.bo = false;
                    }
                });
            }

        });
    }

    /*
     * Enabled aggressive block sorting
     */
    @IgnoreWhen
    @UiThread
    protected void ac() {
        void var3_5;
        Object object;
        void var3_7;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.aV.q()) {
            int n = this.aV.boost ? 2131296435 : 2131296434;
            object = this.getString(n);
        } else {
            object = MagicNetwork.d().getBoostEnabled() && this.aV.r() ? this.getString(2131296443) : null;
        }
        hashMap.put("BOOST_ID", (String)object);
        if (this.bA) {
            String string2 = this.getString(2131296670);
        } else {
            String string3 = this.getString(2131296669);
        }
        hashMap.put("BOOKMARK_ID", (String)var3_5);
        if (this.bz) {
            String string4 = this.getString(2131296730);
        } else {
            String string5 = this.getString(2131296684);
        }
        hashMap.put("FAVORITE_ID", (String)var3_7);
        hashMap.put("EDIT_ID", this.getString(2131296680));
        hashMap.put("INVITE_ID", this.getString(2131296693));
        hashMap.put("CANCEL_ID", this.getString(2131296672));
        hashMap.put("DELETE_ID", this.getString(2131296677));
        hashMap.put("REPORT_ABUSE_ID", this.getString(2131297026));
        hashMap.put("SONG_INFO_ID", this.getString(2131297031));
        ArrayList<String> arrayList = new ArrayList<String>();
        if (object != null) {
            arrayList.add("BOOST_ID");
        }
        if (this.aV != null && !this.aV.x() && !this.bl) {
            if (this.aV.p() && !this.aV.n()) {
                arrayList.add("BOOKMARK_ID");
            }
            arrayList.add("FAVORITE_ID");
        }
        if (this.aV != null && this.aV.m() && !this.aV.x() && !this.bl) {
            Log.b(bh, "onShareButton - open call and is able to still invite people");
            arrayList.add("EDIT_ID");
            arrayList.add("INVITE_ID");
        } else if (this.aV != null && this.aV.n() && !this.aV.x() && !this.bl) {
            Log.b(bh, "onShareButton - performance belongs to me");
            arrayList.add("EDIT_ID");
        }
        if (PerformanceV2Util.a((PerformanceV2)this.aV)) {
            Log.b(bh, "onShareButton - adding delete option");
            arrayList.add("DELETE_ID");
        }
        if (this.aV != null && this.aV.accountIcon != null && !this.aV.accountIcon.d()) {
            arrayList.add("REPORT_ABUSE_ID");
        }
        if (this.aV.v()) {
            arrayList.add("SONG_INFO_ID");
        }
        arrayList.add("CANCEL_ID");
        if (arrayList.size() == 0 || arrayList.size() == 1 && arrayList.contains("CANCEL_ID")) {
            Log.b(bh, "onShareButton - engage regular share mode");
            ShareUtils.a((Activity)this.getActivity(), (PerformanceV2)this.aV);
            return;
        }
        this.bJ = arrayList;
        this.bK = new ArrayList();
        for (String string6 : this.bJ) {
            this.bK.add((String)hashMap.get(string6));
        }
        Log.b(bh, "onShareButton - size of OptionsList is: " + this.bJ.size());
        object = (MediaPlayingActivity)this.getActivity();
        if (object == null) {
            MagicCrittercism.a(new NullPointerException("Activity was null").fillInStackTrace());
            return;
        }
        this.bL = new ArrayAdapter<String>((Context)object, 2130903079, this.bK){};
        this.bu = new AlertDialog.Builder((Context)object).setAdapter(this.bL, new DialogInterface.OnClickListener((MediaPlayingActivity)object){
            final /* synthetic */ MediaPlayingActivity a;
            {
                this.a = mediaPlayingActivity;
            }

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public void onClick(DialogInterface var1_1, int var2_2) {
                block28 : {
                    Log.b(NowPlayingFragment.ai(), "onShareButton - onClick - which is: " + var2_2 + ", and size of optionsList is: " + NowPlayingFragment.n(NowPlayingFragment.this).size());
                    var1_1 = (String)NowPlayingFragment.n(NowPlayingFragment.this).get(var2_2);
                    switch (var1_1.hashCode()) {
                        case -1499336873: {
                            if (!var1_1.equals("BOOST_ID")) ** GOTO lbl36
                            var2_2 = 0;
                            break block28;
                        }
                        case 1384662212: {
                            if (!var1_1.equals("BOOKMARK_ID")) ** GOTO lbl36
                            var2_2 = 1;
                            break block28;
                        }
                        case 230293150: {
                            if (!var1_1.equals("FAVORITE_ID")) ** GOTO lbl36
                            var2_2 = 2;
                            break block28;
                        }
                        case -1169960080: {
                            if (!var1_1.equals("EDIT_ID")) ** GOTO lbl36
                            var2_2 = 3;
                            break block28;
                        }
                        case 885621265: {
                            if (!var1_1.equals("INVITE_ID")) ** GOTO lbl36
                            var2_2 = 4;
                            break block28;
                        }
                        case -1867050961: {
                            if (!var1_1.equals("DELETE_ID")) ** GOTO lbl36
                            var2_2 = 5;
                            break block28;
                        }
                        case 165217535: {
                            if (!var1_1.equals("REPORT_ABUSE_ID")) ** GOTO lbl36
                            var2_2 = 6;
                            break block28;
                        }
                        case 701978914: {
                            if (!var1_1.equals("SONG_INFO_ID")) ** GOTO lbl36
                            var2_2 = 7;
                            break block28;
                        }
lbl36: // 9 sources:
                        default: {
                            ** GOTO lbl-1000
                        }
                        case -1031765760: 
                    }
                    if (var1_1.equals("CANCEL_ID")) {
                        var2_2 = 8;
                    } else lbl-1000: // 2 sources:
                    {
                        var2_2 = -1;
                    }
                }
                switch (var2_2) {
                    default: {
                        return;
                    }
                    case 0: {
                        NowPlayingFragment.o(NowPlayingFragment.this);
                        return;
                    }
                    case 1: {
                        NowPlayingFragment.p(NowPlayingFragment.this);
                        return;
                    }
                    case 2: {
                        NowPlayingFragment.q(NowPlayingFragment.this);
                        return;
                    }
                    case 3: {
                        NowPlayingFragment.r(NowPlayingFragment.this);
                        return;
                    }
                    case 4: {
                        if (NowPlayingFragment.d(NowPlayingFragment.this) || NowPlayingFragment.this.aV != null && NowPlayingFragment.this.aV.x()) {
                            ((BaseActivity)NowPlayingFragment.this.getActivity()).a(NowPlayingFragment.e(NowPlayingFragment.this), true, null);
                            return;
                        }
                        var1_1 = ChatShareInviteActivity.a((Context)this.a, NowPlayingFragment.this.aV, Analytics.i);
                        NowPlayingFragment.this.startActivity((Intent)var1_1);
                        return;
                    }
                    case 5: {
                        NowPlayingFragment.this.a((Activity)this.a, NowPlayingFragment.this.aV.performanceKey);
                        return;
                    }
                    case 6: {
                        NowPlayingFragment.a(NowPlayingFragment.this, (Activity)this.a);
                        return;
                    }
                    case 7: 
                }
                if (NowPlayingFragment.d(NowPlayingFragment.this) || NowPlayingFragment.this.aV != null && NowPlayingFragment.this.aV.x()) {
                    ((BaseActivity)NowPlayingFragment.this.getActivity()).a(NowPlayingFragment.e(NowPlayingFragment.this), true, null);
                    return;
                }
                if (NowPlayingFragment.this.aV != null && NowPlayingFragment.this.aV.w()) {
                    ((BaseActivity)NowPlayingFragment.this.getActivity()).a(NowPlayingFragment.e(NowPlayingFragment.this), true, null);
                    return;
                }
                if (NowPlayingFragment.this.aV.v() && NowPlayingFragment.this.aV.arrangementVersion.arrangement.removalCode != 0) {
                    ((BaseActivity)NowPlayingFragment.this.getActivity()).a(NowPlayingFragment.this.aV.arrangementVersion.arrangement.removalCode, false, null);
                    return;
                }
                if (NowPlayingFragment.this.a() && NowPlayingFragment.this.x != null && NowPlayingFragment.this.x.j()) {
                    NowPlayingFragment.this.x.e();
                }
                this.a.a(SongbookEntry.a(NowPlayingFragment.this.aV.arrangementVersion), true, null);
                NowPlayingFragment.s(NowPlayingFragment.this);
            }
        }).create();
        this.bu.getListView().setDividerHeight(0);
        this.bu.getListView().setSelector(17170445);
        this.bu.show();
        SingAnalytics.b((String)PerformanceV2Util.f((PerformanceV2)this.aV));
    }

    @Click
    protected void ad() {
        ((MediaPlayingActivity)this.getActivity()).am().a(this.P);
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    public void ae() {
        this.ax.addOnOffsetChangedListener(this.bE);
        if (this.aV != null) {
            this.aX = StringCacheManager.a().b(this.aV.performanceKey);
            this.Z();
        }
        this.R.setVisibility(0);
        this.S.setVisibility(4);
        this.g.setLeftButtonOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.af();
            }
        });
        if (this.aV != null) {
            CustomToolbar customToolbar = this.g;
            String string2 = this.aV.p() ? this.getString(2131296695) : this.getString(2131296720);
            customToolbar.setRightButtonText(string2);
        } else {
            this.g.setRightButtonText("");
        }
        this.g.setRightButtonOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                if (NowPlayingFragment.this.aV == null || NowPlayingFragment.this.aV.x() || NowPlayingFragment.this.aV.w()) {
                    return;
                }
                final int n = NowPlayingFragment.this.e;
                object = new BusyDialog(NowPlayingFragment.this.getActivity(), 2131296698);
                object.show();
                com.smule.android.network.managers.PerformanceManager.a().a(NowPlayingFragment.this.aV.performanceKey, true, new PerformanceManager((BusyDialog)((Object)object)){
                    final /* synthetic */ BusyDialog b;
                    {
                        this.b = busyDialog;
                    }

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void handleResponse(PerformanceManager.PerformanceResponse object) {
                        if (!NowPlayingFragment.this.a(n) || !this.b.isShowing()) {
                            return;
                        }
                        this.b.dismiss();
                        if (!object.a()) {
                            object = new TextAlertDialog((Context)NowPlayingFragment.this.getActivity(), NowPlayingFragment.this.getString(2131297023));
                            object.a(0, 2131296705);
                            object.show();
                            return;
                        }
                        boolean bl = NowPlayingFragment.this.aV.v() && NowPlayingFragment.this.aV.arrangementVersion.arrangement.removalCode != 0;
                        if (bl || object.mRestricted) {
                            ((BaseActivity)NowPlayingFragment.this.getActivity()).a(NowPlayingFragment.this.aV.arrangementVersion.arrangement.removalCode, false, null);
                            return;
                        }
                        object = NowPlayingFragment.this.getActivity() instanceof PreSingActivity ? ((PreSingActivity)NowPlayingFragment.this.getActivity()).u() : null;
                        if (object == null) {
                            object = SongbookEntry.a(NowPlayingFragment.this.aV.arrangementVersion);
                        }
                        Analytics ensemble = SingBundle.PerformanceType.a(NowPlayingFragment.this.aV.ensembleType).a();
                        if (NowPlayingFragment.this.ba != null) {
                            Analytics searchTarget = NowPlayingFragment.this.ba;
                            Analytics searchResultClkContext = NowPlayingFragment.this.aV.p() ? Analytics.f : Analytics.e;
                            Analytics searchResultClkValue = Analytics.c;
                            String string2 = PerformanceV2Util.f((PerformanceV2)NowPlayingFragment.this.aV);
                            String string3 = NowPlayingFragment.this.aV.performanceKey;
                            long l = NowPlayingFragment.this.aV.accountIcon.accountId;
                            String string4 = PerformanceV2Util.h((PerformanceV2)NowPlayingFragment.this.aV);
                            Analytics videoStatusType = NowPlayingFragment.this.aV.video ? Analytics.a : Analytics.b;
                            com.smule.android.logging.Analytics.a(searchTarget, searchResultClkContext, searchResultClkValue, string2, string3, 0, l, string4, videoStatusType, 1, 0);
                        }
                        if (!NowPlayingFragment.this.aV.p()) {
                            SingAnalytics.c((SongbookEntry)object);
                            SingAnalytics.a((String)NowPlayingFragment.this.aV.performanceKey, null, (String)PerformanceV2Util.f((PerformanceV2)NowPlayingFragment.this.aV), (String)NowPlayingFragment.this.aQ(), ensemble, (String)null);
                            PreSingActivity.a((Context)NowPlayingFragment.this.getActivity()).a(PreSingActivity.StartupMode.a).a((SongbookEntry)object).a(NowPlayingFragment.this.bD.longValue()).a();
                            return;
                        }
                        if (!SongbookEntryUtils.c((SongbookEntry)object)) {
                            NowPlayingFragment.this.a(UpsellManager.a((boolean)true, (SongbookEntry)object, (PerformanceV2)NowPlayingFragment.this.aV, (Long)NowPlayingFragment.this.bD, (UpsellType)UpsellType.a));
                            return;
                        }
                        PreSingActivity.a((Context)NowPlayingFragment.this.getActivity()).a(PreSingActivity.StartupMode.b).a((SongbookEntry)object).a(NowPlayingFragment.this.bD.longValue()).a(NowPlayingFragment.this.aV).a(PreSingActivity.EntryPoint.i).a();
                    }
                });
            }

        });
        this.aH.setLeftButtonOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.aE();
            }
        });
        this.aM.setLeftButtonOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.aD();
            }
        });
        this.y.setOnTouchListener((View.OnTouchListener)new SwipeDismissTouchListener(this.y, (Object)null, new SwipeDismissTouchListener.DismissCallbacks(){

            public void a(View view, Object object) {
                if (NowPlayingFragment.this.getActivity() != null) {
                    ((MediaPlayingActivity)NowPlayingFragment.this.getActivity()).c(false);
                }
            }

            public boolean a(Object object) {
                return true;
            }
        }, new View.OnClickListener(){

            public void onClick(View view) {
                NowPlayingFragment.this.ag();
            }
        }));
        if (this.aV != null) {
            if (UserManager.a().Q()) {
                this.bz = false;
            } else if (!UserManager.a().P()) {
                this.bz = UserManager.a().i(this.aV.performanceKey);
            } else if (UserManager.a().f() == 0) {
                Log.e(bh, "accountId is 0");
            } else {
                com.smule.android.network.managers.PerformanceManager.a().a(UserManager.a().f(), (Integer)0, (Integer)25, new PerformanceManager(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void handleResponse(PerformanceManager.PerformancesByPerformerResponse performancesByPerformerResponse) {
                        if (!NowPlayingFragment.this.isAdded() || !performancesByPerformerResponse.a()) {
                            return;
                        }
                        Iterator<PerformanceV2> iterator = performancesByPerformerResponse.mPerformances.iterator();
                        do {
                            if (!iterator.hasNext()) {
                                NowPlayingFragment.this.bz = UserManager.a().i(NowPlayingFragment.this.aV.performanceKey);
                                UserManager.a().c(performancesByPerformerResponse.mPerformances.isEmpty());
                                NowPlayingFragment.this.aJ();
                                return;
                            }
                            PerformanceV2 performanceV2 = iterator.next();
                            UserManager.a().g(performanceV2.performanceKey);
                        } while (true);
                    }
                });
            }
            if (UserManager.a().l(this.aV.performanceKey)) {
                this.bA = UserManager.a().m(this.aV.performanceKey);
                this.aK();
            } else {
                com.smule.android.network.managers.PerformanceManager.a().a(this.aV.performanceKey, new PerformanceManager(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void handleResponse(PerformanceManager.IsBookmarkSeedResponse isBookmarkSeedResponse) {
                        if (!NowPlayingFragment.this.isAdded() || !isBookmarkSeedResponse.a()) {
                            return;
                        }
                        NowPlayingFragment.this.bA = isBookmarkSeedResponse.mIsBookmarkSeed;
                        if (NowPlayingFragment.this.bA) {
                            UserManager.a().j(NowPlayingFragment.this.aV.performanceKey);
                        } else {
                            UserManager.a().k(NowPlayingFragment.this.aV.performanceKey);
                        }
                        NowPlayingFragment.this.aK();
                    }
                });
            }
        }
        this.az();
    }

    public void af() {
        this.h(true);
    }

    public void ag() {
        this.a((Runnable)null, false);
    }

    public Long ah() {
        return this.bD;
    }

    @UiThread
    public void b(boolean bl, boolean bl2) {
        this.a(bl, bl2, (Hashtag.HashtagCallback)null);
    }

    @UiThread
    protected void c(String string2) {
        Log.b(bh, "executePerformanceLove - posting notification LOVE_GIVEN for performance with key: " + string2);
        NotificationCenter.a().a("LOVE_GIVEN", (Object)string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void d(MediaPlayerServiceController object, String object2) {
        super.d((MediaPlayerServiceController)object, (String)object2);
        if (this.aV != null && object2 != null && this.aV.performanceKey.equals(object2)) {
            if (this.bk == Mode.b) {
                SingAppboy.a().d();
            } else {
                SingAppboy.a().e();
            }
            String string2 = this.aV.performanceKey;
            object = !this.aV.closed ? Analytics.b : Analytics.c;
            String string3 = PerformanceV2Util.f((PerformanceV2)this.aV);
            Analytics ensemble = SingBundle.PerformanceType.a(this.aV.ensembleType).a();
            String string4 = this.aQ();
            object2 = this.aV.video ? Analytics.a : Analytics.b;
            SingAnalytics.a((String)string2, object, (String)string3, ensemble, (String)null, (String)string4, object2, (boolean)this.aV.boost);
            com.smule.android.network.managers.PerformanceManager.a().c(this.aV.performanceKey, null);
        }
    }

    public void d(String string2) {
        if (!this.isAdded()) {
            return;
        }
        if (this.aV.x() || this.bl) {
            Log.b(bh, "showCommentsView - Performance is deleted or disabled.");
            ((BaseActivity)this.getActivity()).a(this.bm, true, null);
            return;
        }
        this.getActivity().getWindow().setSoftInputMode(32);
        this.bv = new com.smule.singandroid.dialogs.CommentsListDialog((BaseFragment)this, this.aV, string2, this.bw);
        this.bv.a(new CommentsListDialog(){

            @Override
            public void a(CommentItem commentItem, PerformancePost performancePost) {
                NowPlayingFragment.this.a(ProfileFragment.a(performancePost.accountIcon));
                NowPlayingFragment.this.aH();
            }
        });
        this.bv.setOnDismissListener(new DialogInterface.OnDismissListener(){

            public void onDismiss(DialogInterface dialogInterface) {
                if (!NowPlayingFragment.this.isAdded()) {
                    return;
                }
                NowPlayingFragment.this.E.a(NowPlayingFragment.this, NowPlayingFragment.this.D, false);
                if (dialogInterface instanceof com.smule.singandroid.dialogs.CommentsListDialog) {
                    NowPlayingFragment.this.au();
                    if (NowPlayingFragment.this.isResumed() && NowPlayingFragment.this.bw != null) {
                        NowPlayingFragment.this.bw.a(NowPlayingFragment.this);
                    }
                    NowPlayingFragment.this.A();
                }
                NowPlayingFragment.this.J = true;
            }
        });
        this.E.a(this, this.D, true);
        this.bv.show();
    }

    @Override
    protected void e(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        super.e(mediaPlayerServiceController, string2);
        this.aR();
    }

    public void e(final String string2) {
        this.a(MediaPlayingFragment.AnimationDirection.a, new AnimatorListenerAdapter(){

            public void onAnimationEnd(Animator animator2) {
                NowPlayingFragment.this.d(string2);
            }
        });
        this.bd = false;
    }

    @Override
    protected void f(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        super.f(mediaPlayerServiceController, string2);
        if (this.a() && this.getActivity() != null && this.i != null) {
            this.i.setText(2131297831);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected boolean f(int n) {
        final PerformanceV2 performanceV2 = this.S();
        if (performanceV2 == null || performanceV2.performanceKey == null) {
            Log.e(bh, "logListen: performance is null so unable to report listen");
            return true;
        }
        int n2 = performanceV2.v() && performanceV2.arrangementVersion != null ? performanceV2.arrangementVersion.length : -1;
        if (n2 == -1) {
            if ((float)n / (float)this.x.f() <= 0.2f) return false;
            n = 1;
        } else {
            if ((float)n / (float)(n2 * 1000) <= 0.2f) {
                return false;
            }
            n = 1;
        }
        if (n == 0) {
            return false;
        }
        Log.b(bh, "logListen: beginning logic to call v2/performance/listen");
        Location location = LocationUtils.a();
        final float f = location != null ? (float)location.getLatitude() : Float.NaN;
        final float f2 = location != null ? (float)location.getLongitude() : Float.NaN;
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                com.smule.android.network.managers.PerformanceManager.a().a(performanceV2.performanceKey, f, f2);
                Log.b(bh, "logListen: done reporting via v2/performance/listen API");
            }
        });
        return true;
    }

    public void g(boolean bl) {
        this.bd = bl;
    }

    public void h(boolean bl) {
        this.b(bl, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public void i(boolean bl) {
        Log.b(bh, "lowerFragmentRetainState - begin");
        if (this.aF()) {
            this.aI();
        }
        MediaPlayingFragment.AnimationDirection animationDirection = bl ? MediaPlayingFragment.AnimationDirection.e : MediaPlayingFragment.AnimationDirection.c;
        this.a(animationDirection, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onActivityResult(int n, int n2, Intent object) {
        super.onActivityResult(n, n2, (Intent)object);
        if (n2 != -1) {
            Log.e(bh, "Bad result code, " + n2 + ", returned for request code: " + n);
            return;
        }
        if (object == null) {
            Log.d(bh, "No data returned to onActivityResult");
            return;
        }
        n = object.getIntExtra("CHANGE_MADE_CODE", -1);
        if (n == 6801) {
            Log.b(bh, "Result code indicates open call was closed; closing NowPlayingFragment");
            this.a(this);
            return;
        }
        if (n == 6802) {
            Log.b(bh, "Result code indicates performance was deleted; closing NowPlayingFragment");
            this.a(this);
            return;
        }
        if (n != 6803 && n != 6804) return;
        if ((object = object.getExtras()) != null && object.containsKey("CHANGE_MADE_PERFORMANCE")) {
            if ((object = (PerformanceV2)object.getParcelable("CHANGE_MADE_PERFORMANCE")) != null && this.p() != null) {
                this.E.a(this.D, MediaPlayingPlayable.a((PerformanceV2)object));
                return;
            }
            if (object == null) {
                this.a(this);
            }
        }
        this.aV = null;
        this.bl = false;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.J = false;
        this.b_(this.aZ);
        if (this.bw != null) {
            Log.e(bh, "onCreate - CommentsDataSource should be null");
        }
        if (this.aV != null && !TextUtils.isEmpty((CharSequence)this.aV.performanceKey)) {
            this.bw = new Object(this.aV.performanceKey){
                CommentsListDialog.CommentDataSourceObserver a;
                com.smule.android.network.managers.PerformanceManager$PerformancePostsResponseCallback b;
                private final String c;
                private List<PerformancePost> d = new ArrayList<PerformancePost>();
                private int e;
                private boolean f = false;
                private boolean g = false;
                {
                    this.c = string2;
                }

                private void f() {
                    if (this.a != null) {
                        this.a.a(new ArrayList<PerformancePost>(this.d));
                    }
                }

                public int a() {
                    return this.d.size();
                }

                public void a(CommentsListDialog.CommentDataSourceObserver commentDataSourceObserver) {
                    this.a = commentDataSourceObserver;
                    this.f();
                }

                public void b(CommentsListDialog.CommentDataSourceObserver commentDataSourceObserver) {
                    if (this.a == commentDataSourceObserver) {
                        this.a = null;
                    }
                }

                public boolean b() {
                    return this.f;
                }

                public boolean c() {
                    return this.g;
                }

                public void d() {
                    this.e = 0;
                    this.g = false;
                    this.f = false;
                    this.b = null;
                    this.d.clear();
                }

                public void e() {
                    if (this.f) {
                        return;
                    }
                    this.f = true;
                    this.b = new com.smule.android.network.managers.PerformanceManager$PerformancePostsResponseCallback(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        @android.support.annotation.UiThread
                        @Override
                        public void handleResponse(com.smule.android.network.managers.PerformanceManager$PerformancePostsResponse performancePostsResponse) {
                            if (this != CommentsDataSource.this.b) {
                                return;
                            }
                            CommentsDataSource.this.f = false;
                            if (!performancePostsResponse.a()) {
                                Log.c(b, "could not load comments");
                                return;
                            }
                            CommentsListDialog commentsDataSource = CommentsDataSource.this;
                            int n = performancePostsResponse.mNext == null ? CommentsDataSource.this.e + 25 : performancePostsResponse.mNext;
                            commentsDataSource.e = n;
                            if (CommentsDataSource.this.e == -1 || performancePostsResponse.mPerformancePosts.size() <= 0) {
                                CommentsDataSource.this.g = true;
                            }
                            CommentsDataSource.this.d.addAll(performancePostsResponse.mPerformancePosts);
                            CommentsDataSource.this.f();
                        }
                    };
                    com.smule.android.network.managers.PerformanceManager.a().a(this.c, (Integer)this.e, (Integer)25, this.b);
                }

                public static interface CommentsListDialog.CommentDataSourceObserver {
                    public void a(@NonNull List<PerformancePost> var1);
                }

            };
            return;
        }
        Log.e(bh, "setupCommentsDataSource - performance is null or unidentifiable");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        menuInflater.inflate(this.bb, menu2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.ax.removeOnOffsetChangedListener(this.bE);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.bn != null) {
            return this.bn.a(menuItem);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onPause() {
        if (this.bw != null) {
            this.bw.b(this);
        } else {
            Log.e(bh, "onPause - mCommentsDataSource is null");
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.K()) {
            this.X();
        }
        if (this.getActivity() != null) {
            this.getActivity().getWindow().setSoftInputMode(35);
        }
        if (this.bw != null) {
            this.bw.a(this);
            return;
        }
        Log.e(bh, "onResume - mCommentsDataSource is null");
    }

    @Override
    public void onStart() {
        super.onStart();
        PreviewFragment previewFragment = ((MediaPlayingActivity)this.getActivity()).ae();
        if (!(this.aV == null || previewFragment != null && previewFragment.isVisible())) {
            this.U();
        }
        NotificationCenter.a().a("PERFORMANCE_UPDATED_NOTIFICATION", this.bF);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.aj();
        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", this.bF);
    }

    @Override
    public String x() {
        return bh;
    }

    static enum Mode {
        a,
        b,
        c,
        d;
        

        private Mode() {
        }
    }

    public static interface NowPlayingFragmentMenuSelectedCallback {
        public boolean a(MenuItem var1);
    }

}

