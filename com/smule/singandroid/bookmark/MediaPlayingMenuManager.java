/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.preference.PreferenceManager
 *  android.view.View
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.TranslateAnimation
 *  android.widget.TextView
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.bookmark;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.BoostManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.SimpleBarrier;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.adapters.profile.ProfileFavoritesDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.boost.BoostContext;
import com.smule.singandroid.boost.BoostPresenter;
import com.smule.singandroid.boost.LearnHowToBoostDialog;
import com.smule.singandroid.common.OptionsMenu.MenuOption;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class MediaPlayingMenuManager {
    private static final String b = MediaPlayingMenuManager.class.getSimpleName();
    SharedPreferences a;
    private final AtomicBoolean c = new AtomicBoolean();
    private final AtomicBoolean d = new AtomicBoolean();
    private boolean e;
    private boolean f;
    private boolean g;
    private WeakReference<MediaPlayingActivity> h;
    private MenuOption i;
    private MenuOption j;
    private MenuOption k;
    private MenuOption l;
    private MenuOption m;
    private MenuOption n;
    private MenuOption o;
    private MenuOption p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private OptionsMenu v;
    private boolean w = true;
    private boolean x = true;

    public MediaPlayingMenuManager(MediaPlayingActivity mediaPlayingActivity) {
        this.h = new WeakReference<MediaPlayingActivity>(mediaPlayingActivity);
        this.a = PreferenceManager.getDefaultSharedPreferences((Context)((Context)this.h.get()));
        this.f = this.a.getBoolean("BOOKMARK_BANNER_SHOWN", false);
        this.g = this.a.getBoolean("FAVORITE_BANNER_SHOWN", false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private OptionsMenu.Builder a(PerformanceV2 object, BookmarkDialogCallback bookmarkDialogCallback) {
        OptionsMenu.Builder builder = new OptionsMenu.Builder();
        this.b((PerformanceV2)object, bookmarkDialogCallback);
        if (MagicNetwork.d().getBoostEnabled() && object.r()) {
            builder.a(this.o);
        }
        if (this.t) {
            object = this.u ? this.n : this.m;
            builder.a(new MenuOption[]{object});
        }
        if (this.q) {
            object = this.r ? this.j : this.i;
            builder.a(new MenuOption[]{object});
        }
        object = this.s ? this.l : this.k;
        builder.a(new MenuOption[]{object});
        builder.a(this.p);
        return builder;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(List<String> list, List<String> list2, final PerformanceV2 performanceV2, final BookmarkDialogCallback bookmarkDialogCallback) {
        final boolean bl = !list.isEmpty();
        if (bl) {
            SingAnalytics.a((String)performanceV2.performanceKey, (String)PerformanceV2Util.f((PerformanceV2)performanceV2), (String)PerformanceV2Util.h((PerformanceV2)performanceV2), (boolean)performanceV2.video);
        } else {
            SingAnalytics.b((String)performanceV2.performanceKey, (String)PerformanceV2Util.f((PerformanceV2)performanceV2), (String)PerformanceV2Util.h((PerformanceV2)performanceV2), (boolean)performanceV2.video);
        }
        com.smule.android.network.managers.PerformanceManager.a().b(list, list2, new NetworkResponseCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(NetworkResponse object) {
                if (object.c()) {
                    if (bl) {
                        UserManager.a().j(performanceV2.performanceKey);
                        if (bookmarkDialogCallback != null) {
                            bookmarkDialogCallback.a(performanceV2);
                        }
                    } else {
                        UserManager.a().k(performanceV2.performanceKey);
                        if (bookmarkDialogCallback != null) {
                            bookmarkDialogCallback.c(performanceV2);
                        }
                    }
                    if (!((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).j()) {
                        if (MediaPlayingMenuManager.this.w) {
                            Context context = (Context)MediaPlayingMenuManager.this.h.get();
                            object = bl ? ((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).getResources().getString(2131296427) : ((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).getResources().getString(2131296433);
                            com.smule.android.utils.Toaster.a(context, (String)object);
                        } else {
                            MediaPlayingMenuManager.this.w = true;
                        }
                    }
                    MagicDataSource.a(ProfileOpenCallDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                } else if (object.b == 1015) {
                    if (!((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).j()) {
                        new TextAlertDialog((Context)MediaPlayingMenuManager.this.h.get(), 2131296432, 2131296431, true, false).show();
                    }
                } else if (object.b == 1021 || object.b == 1012) {
                    Log.d(b, "Attempting to bookmark non-joinable performance: " + performanceV2.performanceKey, new BookmarkNonSeedException("perfKey: " + performanceV2.performanceKey).fillInStackTrace());
                    if (!((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).j()) {
                        object = new TextAlertDialog((Context)MediaPlayingMenuManager.this.h.get(), "", ((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).getResources().getString(2131296430), true, false);
                        object.a(((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).getString(2131296705), "");
                        object.show();
                    }
                } else if (!((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).j()) {
                    new TextAlertDialog((Context)MediaPlayingMenuManager.this.h.get(), 2131296795, 2131296895, true, false).show();
                }
                if (MediaPlayingMenuManager.this.v != null) {
                    MediaPlayingMenuManager.this.v.b();
                }
            }
        });
    }

    private void a(boolean bl) {
        if (this.f && bl || this.g && !bl) {
            return;
        }
        if (bl) {
            this.w = false;
            com.smule.android.utils.Toaster.a((Context)this.h.get(), 2131296428);
            this.f = true;
            this.a.edit().putBoolean("BOOKMARK_BANNER_SHOWN", true).apply();
            return;
        }
        this.x = false;
        com.smule.android.utils.Toaster.a((Context)this.h.get(), 2131296429);
        this.g = true;
        this.a.edit().putBoolean("FAVORITE_BANNER_SHOWN", true).apply();
    }

    private void b(final PerformanceV2 performanceV2, final BookmarkDialogCallback bookmarkDialogCallback) {
        this.i = new MenuOption((Context)this.h.get(), 2131296669, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                MediaPlayingMenuManager.this.c(performanceV2, bookmarkDialogCallback);
            }
        });
        this.j = new MenuOption((Context)this.h.get(), 2131296670, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                MediaPlayingMenuManager.this.d(performanceV2, bookmarkDialogCallback);
            }
        });
        this.k = new MenuOption((Context)this.h.get(), 2131296684, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                MediaPlayingMenuManager.this.e(performanceV2, bookmarkDialogCallback);
            }
        });
        this.l = new MenuOption((Context)this.h.get(), 2131296730, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                MediaPlayingMenuManager.this.f(performanceV2, bookmarkDialogCallback);
            }
        });
        this.m = new MenuOption((Context)this.h.get(), 2131296434, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                com.smule.android.network.managers.BoostManager.a().a(new BoostManager(){

                    @Override
                    public void handleResponse(BoostManager.BoostAvailabilityResponse boostAvailabilityResponse) {
                        if (boostAvailabilityResponse.a()) {
                            BoostPresenter.a().a((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get(), new BoostContext(SubscriptionManager.a().b(), com.smule.android.network.managers.BoostManager.a().b(), false, performanceV2));
                        }
                    }
                });
            }

        });
        this.n = new MenuOption((Context)this.h.get(), 2131296435, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                BoostPresenter.a().a((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get(), new BoostContext(SubscriptionManager.a().b(), com.smule.android.network.managers.BoostManager.a().b(), false, performanceV2));
            }
        });
        this.o = new MenuOption((Context)this.h.get(), 2131296443, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                new LearnHowToBoostDialog((Context)MediaPlayingMenuManager.this.h.get()).a(SubscriptionManager.a().b()).a(performanceV2).show();
                SingAnalytics.J();
            }
        });
        this.p = new MenuOption((Context)this.h.get(), 2131296672, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                MediaPlayingMenuManager.this.v.b();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(List<String> list, List<String> list2, final PerformanceV2 performanceV2, final BookmarkDialogCallback bookmarkDialogCallback) {
        final boolean bl = !list.isEmpty();
        com.smule.android.network.managers.PerformanceManager.a().a(list, list2, new NetworkResponseCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(NetworkResponse object) {
                if (object.c()) {
                    if (bl) {
                        UserManager.a().g(performanceV2.performanceKey);
                        if (bookmarkDialogCallback != null) {
                            bookmarkDialogCallback.b(performanceV2);
                        }
                    } else {
                        UserManager.a().h(performanceV2.performanceKey);
                        if (bookmarkDialogCallback != null) {
                            bookmarkDialogCallback.d(performanceV2);
                        }
                    }
                    if (!((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).j()) {
                        if (MediaPlayingMenuManager.this.x) {
                            Context context = (Context)MediaPlayingMenuManager.this.h.get();
                            object = bl ? ((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).getResources().getString(2131296794) : ((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).getResources().getString(2131296798);
                            com.smule.android.utils.Toaster.a(context, (String)object);
                        } else {
                            MediaPlayingMenuManager.this.x = true;
                        }
                    }
                    MagicDataSource.a(ProfileFavoritesDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                } else if (object.b == 1015) {
                    if (!((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).j()) {
                        new TextAlertDialog((Context)MediaPlayingMenuManager.this.h.get(), 2131296797, 2131296796, true, false).show();
                    }
                } else if (!((MediaPlayingActivity)MediaPlayingMenuManager.this.h.get()).j()) {
                    new TextAlertDialog((Context)MediaPlayingMenuManager.this.h.get(), 2131296795, 2131296895, true, false).show();
                }
                if (MediaPlayingMenuManager.this.v != null) {
                    MediaPlayingMenuManager.this.v.b();
                }
            }
        });
    }

    private void c(PerformanceV2 performanceV2, BookmarkDialogCallback bookmarkDialogCallback) {
        this.a(Collections.singletonList(performanceV2.performanceKey), Collections.<String>emptyList(), performanceV2, bookmarkDialogCallback);
    }

    private void d(PerformanceV2 performanceV2, BookmarkDialogCallback bookmarkDialogCallback) {
        this.a(Collections.<String>emptyList(), Collections.singletonList(performanceV2.performanceKey), performanceV2, bookmarkDialogCallback);
    }

    private void e() {
        this.e = true;
        com.smule.android.utils.Toaster.a((Context)this.h.get(), 2131297523, Toaster.b);
        if (this.v != null) {
            this.v.b();
        }
    }

    private void e(PerformanceV2 performanceV2, BookmarkDialogCallback bookmarkDialogCallback) {
        Analytics ensemble = SingBundle.PerformanceType.a(performanceV2.ensembleType).a();
        com.smule.android.logging.Analytics.a(performanceV2.performanceKey, performanceV2.songUid, ensemble, null, PerformanceV2Util.h((PerformanceV2)performanceV2), performanceV2.video);
        this.b(Collections.singletonList(performanceV2.performanceKey), Collections.<String>emptyList(), performanceV2, bookmarkDialogCallback);
    }

    private void f(PerformanceV2 performanceV2, BookmarkDialogCallback bookmarkDialogCallback) {
        this.b(Collections.<String>emptyList(), Collections.singletonList(performanceV2.performanceKey), performanceV2, bookmarkDialogCallback);
    }

    public void a() {
        this.f = this.a.getBoolean("BOOKMARK_BANNER_SHOWN", false);
        this.g = this.a.getBoolean("FAVORITE_BANNER_SHOWN", false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final Fragment fragment, final View view, TextView textView, boolean bl) {
        if (this.f && bl || this.g && !bl || fragment == null || !fragment.isAdded()) {
            return;
        }
        if (bl) {
            textView.setText(2131296428);
            this.f = true;
            this.a.edit().putBoolean("BOOKMARK_BANNER_SHOWN", true).apply();
        } else {
            textView.setText(2131296429);
            this.g = true;
            this.a.edit().putBoolean("FAVORITE_BANNER_SHOWN", true).apply();
        }
        view.setVisibility(0);
        view.setAlpha(0.0f);
        textView = new TranslateAnimation(0.0f, 0.0f, (float)(this.h.get().getResources().getDimensionPixelSize(2131427820) * -1), 0.0f);
        textView.setDuration(500);
        textView.setFillAfter(true);
        textView.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        if (fragment == null || !fragment.isAdded()) {
                            return;
                        }
                        MediaPlayingMenuManager.this.a(view);
                    }
                }, 7000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view.setAlpha(1.0f);
            }

        });
        view.startAnimation((Animation)textView);
    }

    public void a(final View view) {
        if (view.getVisibility() != 0 || view.getAnimation() == null || !view.getAnimation().hasEnded()) {
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float)(view.getHeight() * -1));
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation((Animation)translateAnimation);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final PerformanceV2 performanceV2, BookmarkDialogCallback object, boolean bl) {
        if (this.c.get() || this.d.get()) {
            return;
        }
        this.t = performanceV2.q();
        this.u = performanceV2.boost;
        object = new SimpleBarrier(2, new Runnable((BookmarkDialogCallback)object){
            final /* synthetic */ BookmarkDialogCallback b;
            {
                this.b = bookmarkDialogCallback;
            }

            @Override
            public void run() {
                if (MediaPlayingMenuManager.this.e) {
                    return;
                }
                OptionsMenu.Builder builder = MediaPlayingMenuManager.this.a(performanceV2, this.b);
                MediaPlayingMenuManager.this.v = builder.a((Context)MediaPlayingMenuManager.this.h.get());
                MediaPlayingMenuManager.this.v.a();
            }
        });
        this.e = false;
        bl = performanceV2.p() && !performanceV2.n();
        this.q = bl;
        if (this.q) {
            if (UserManager.a().l(performanceV2.performanceKey)) {
                this.r = UserManager.a().m(performanceV2.performanceKey);
                object.a();
            } else {
                this.c.set(true);
                com.smule.android.network.managers.PerformanceManager.a().a(performanceV2.performanceKey, new PerformanceManager((SimpleBarrier)object, performanceV2){
                    final /* synthetic */ SimpleBarrier a;
                    final /* synthetic */ PerformanceV2 b;
                    {
                        this.a = simpleBarrier;
                        this.b = performanceV2;
                    }

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void handleResponse(PerformanceManager.IsBookmarkSeedResponse isBookmarkSeedResponse) {
                        MediaPlayingMenuManager.this.c.set(false);
                        if (MediaPlayingMenuManager.this.e) {
                            this.a.a();
                            return;
                        }
                        if (isBookmarkSeedResponse.a()) {
                            MediaPlayingMenuManager.this.r = isBookmarkSeedResponse.mIsBookmarkSeed;
                            if (isBookmarkSeedResponse.mIsBookmarkSeed.booleanValue()) {
                                UserManager.a().j(this.b.performanceKey);
                            } else {
                                UserManager.a().k(this.b.performanceKey);
                            }
                        } else {
                            MediaPlayingMenuManager.this.e();
                        }
                        this.a.a();
                    }
                });
            }
        } else {
            object.a();
        }
        if (UserManager.a().Q()) {
            this.s = false;
            object.a();
            return;
        }
        if (UserManager.a().i(performanceV2.performanceKey)) {
            this.s = true;
            object.a();
            return;
        }
        if (UserManager.a().f() == 0) {
            Log.e(b, "accountId is 0");
            object.a();
            this.e();
            return;
        }
        this.d.set(true);
        com.smule.android.network.managers.PerformanceManager.a().a(UserManager.a().f(), (Integer)0, (Integer)25, new PerformanceManager((SimpleBarrier)object, performanceV2){
            final /* synthetic */ SimpleBarrier a;
            final /* synthetic */ PerformanceV2 b;
            {
                this.a = simpleBarrier;
                this.b = performanceV2;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(PerformanceManager.PerformancesByPerformerResponse iterator) {
                MediaPlayingMenuManager.this.d.set(false);
                if (MediaPlayingMenuManager.this.e) {
                    this.a.a();
                    return;
                }
                if (iterator.a()) {
                    MediaPlayingMenuManager.this.s = false;
                    iterator = iterator.mPerformances.iterator();
                    while (iterator.hasNext()) {
                        if (!iterator.next().performanceKey.equals(this.b.performanceKey)) continue;
                        MediaPlayingMenuManager.this.s = true;
                        break;
                    }
                } else {
                    MediaPlayingMenuManager.this.e();
                }
                this.a.a();
            }
        });
    }

    public void b() {
        this.a(true);
    }

    public void c() {
        this.a(false);
    }

    public static class BookmarkNonSeedException
    extends Exception {
        public BookmarkNonSeedException(String string2) {
            super(string2);
        }
    }

}

