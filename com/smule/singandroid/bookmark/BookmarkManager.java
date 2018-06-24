package com.smule.singandroid.bookmark;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$IsBookmarkSeedResponseCallback;
import com.smule.android.network.managers.PerformanceManager$PerformancesByPerformerResponseCallback;
import com.smule.android.network.managers.PerformanceManager.IsBookmarkSeedResponse;
import com.smule.android.network.managers.PerformanceManager.PerformancesByPerformerResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BookmarkDialog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BookmarkManager {
    private static final String f20698b = BookmarkManager.class.getSimpleName();
    SharedPreferences f20699a = PreferenceManager.getDefaultSharedPreferences(this.f20706i);
    private boolean f20700c;
    private boolean f20701d;
    private boolean f20702e;
    private boolean f20703f = this.f20699a.getBoolean("BOOKMARK_BANNER_SHOWN", false);
    private boolean f20704g = this.f20699a.getBoolean("FAVORITE_BANNER_SHOWN", false);
    private BookmarkDialog f20705h;
    private BaseActivity f20706i;

    class C42394 implements OnDismissListener {
        final /* synthetic */ BookmarkManager f20691a;

        C42394(BookmarkManager bookmarkManager) {
            this.f20691a = bookmarkManager;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            this.f20691a.f20705h = null;
        }
    }

    public BookmarkManager(BaseActivity baseActivity) {
        this.f20706i = baseActivity;
    }

    public void m22305a() {
        this.f20703f = this.f20699a.getBoolean("BOOKMARK_BANNER_SHOWN", false);
        this.f20704g = this.f20699a.getBoolean("FAVORITE_BANNER_SHOWN", false);
    }

    public void m22308a(final PerformanceV2 performanceV2, final BookmarkDialogCallback bookmarkDialogCallback, final boolean z) {
        if (!this.f20700c && !this.f20701d && this.f20705h == null) {
            final Map hashMap = new HashMap();
            this.f20702e = false;
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ BookmarkManager f20680d;

                class C42351 implements OnDismissListener {
                    final /* synthetic */ C42361 f20676a;

                    C42351(C42361 c42361) {
                        this.f20676a = c42361;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                        this.f20676a.f20680d.f20705h = null;
                    }
                }

                public void run() {
                    if ((this.f20680d.f20701d || this.f20680d.f20700c) && this.f20680d.f20705h == null && !this.f20680d.f20702e) {
                        this.f20680d.f20705h = new BookmarkDialog(this.f20680d.f20706i, true, true, performanceV2, true, bookmarkDialogCallback, z);
                        this.f20680d.f20705h.setOnDismissListener(new C42351(this));
                        this.f20680d.f20705h.show();
                    }
                }
            }, 500);
            if (!performanceV2.n()) {
                hashMap.put(Integer.valueOf(0), Boolean.valueOf(false));
                m22295a(hashMap, performanceV2, bookmarkDialogCallback, z);
            } else if (UserManager.a().l(performanceV2.performanceKey)) {
                hashMap.put(Integer.valueOf(0), Boolean.valueOf(UserManager.a().m(performanceV2.performanceKey)));
                m22295a(hashMap, performanceV2, bookmarkDialogCallback, z);
            } else {
                this.f20700c = true;
                final PerformanceV2 performanceV22 = performanceV2;
                final BookmarkDialogCallback bookmarkDialogCallback2 = bookmarkDialogCallback;
                final boolean z2 = z;
                PerformanceManager.a().a(performanceV2.performanceKey, new PerformanceManager$IsBookmarkSeedResponseCallback(this) {
                    final /* synthetic */ BookmarkManager f20685e;

                    public void handleResponse(IsBookmarkSeedResponse isBookmarkSeedResponse) {
                        if (!this.f20685e.f20702e) {
                            this.f20685e.f20700c = false;
                            if (isBookmarkSeedResponse.a()) {
                                hashMap.put(Integer.valueOf(0), isBookmarkSeedResponse.mIsBookmarkSeed);
                                this.f20685e.m22295a(hashMap, performanceV22, bookmarkDialogCallback2, z2);
                                if (isBookmarkSeedResponse.mIsBookmarkSeed.booleanValue()) {
                                    UserManager.a().j(performanceV22.performanceKey);
                                    return;
                                } else {
                                    UserManager.a().k(performanceV22.performanceKey);
                                    return;
                                }
                            }
                            this.f20685e.m22298b();
                        }
                    }
                });
            }
            if (UserManager.a().Q()) {
                hashMap.put(Integer.valueOf(1), Boolean.valueOf(false));
                m22295a(hashMap, performanceV2, bookmarkDialogCallback, z);
            } else if (!UserManager.a().P()) {
                hashMap.put(Integer.valueOf(1), Boolean.valueOf(UserManager.a().i(performanceV2.performanceKey)));
                m22295a(hashMap, performanceV2, bookmarkDialogCallback, z);
            } else if (UserManager.a().f() == 0) {
                Log.e(f20698b, "accountId is 0");
                m22298b();
            } else {
                this.f20701d = true;
                final PerformanceV2 performanceV23 = performanceV2;
                final Map map = hashMap;
                final BookmarkDialogCallback bookmarkDialogCallback3 = bookmarkDialogCallback;
                final boolean z3 = z;
                PerformanceManager.a().a(UserManager.a().f(), Integer.valueOf(0), Integer.valueOf(25), new PerformanceManager$PerformancesByPerformerResponseCallback(this) {
                    final /* synthetic */ BookmarkManager f20690e;

                    public void handleResponse(PerformancesByPerformerResponse performancesByPerformerResponse) {
                        if (!this.f20690e.f20702e) {
                            this.f20690e.f20701d = false;
                            if (performancesByPerformerResponse.a()) {
                                boolean z;
                                Iterator it = performancesByPerformerResponse.mPerformances.iterator();
                                while (it.hasNext()) {
                                    if (((PerformanceV2) it.next()).performanceKey.equals(performanceV23.performanceKey)) {
                                        z = true;
                                        break;
                                    }
                                }
                                z = false;
                                map.put(Integer.valueOf(1), Boolean.valueOf(z));
                                this.f20690e.m22295a(map, performanceV23, bookmarkDialogCallback3, z3);
                                return;
                            }
                            this.f20690e.m22298b();
                        }
                    }
                });
            }
        }
    }

    private void m22298b() {
        this.f20702e = true;
        Toaster.a(this.f20706i, C1947R.string.songbook_error_connecting_to_network, Toaster$Duration.LONG);
        if (this.f20705h != null) {
            this.f20705h.dismiss();
            this.f20705h = null;
        }
    }

    private void m22295a(Map<Integer, Boolean> map, PerformanceV2 performanceV2, BookmarkDialogCallback bookmarkDialogCallback, boolean z) {
        if (map.size() != 2) {
            return;
        }
        if (this.f20705h != null) {
            this.f20705h.m23570a(((Boolean) map.get(Integer.valueOf(0))).booleanValue(), ((Boolean) map.get(Integer.valueOf(1))).booleanValue(), performanceV2);
            return;
        }
        this.f20705h = new BookmarkDialog(this.f20706i, ((Boolean) map.get(Integer.valueOf(0))).booleanValue(), ((Boolean) map.get(Integer.valueOf(1))).booleanValue(), performanceV2, false, bookmarkDialogCallback, z);
        this.f20705h.setOnDismissListener(new C42394(this));
        this.f20705h.show();
    }

    public void m22306a(final Fragment fragment, final View view, TextView textView, boolean z) {
        if (!this.f20703f || !z) {
            if ((!this.f20704g || z) && fragment != null && fragment.isAdded()) {
                if (z) {
                    textView.setText(C1947R.string.bookmark_banner_bookmark_invites);
                    this.f20703f = true;
                    this.f20699a.edit().putBoolean("BOOKMARK_BANNER_SHOWN", true).apply();
                } else {
                    textView.setText(C1947R.string.bookmark_banner_favorites);
                    this.f20704g = true;
                    this.f20699a.edit().putBoolean("FAVORITE_BANNER_SHOWN", true).apply();
                }
                view.setVisibility(0);
                view.setAlpha(0.0f);
                Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (this.f20706i.getResources().getDimensionPixelSize(C1947R.dimen.row_triple_height) * -1), 0.0f);
                translateAnimation.setDuration(500);
                translateAnimation.setFillAfter(true);
                translateAnimation.setAnimationListener(new AnimationListener(this) {
                    final /* synthetic */ BookmarkManager f20695c;

                    class C42401 implements Runnable {
                        final /* synthetic */ C42415 f20692a;

                        C42401(C42415 c42415) {
                            this.f20692a = c42415;
                        }

                        public void run() {
                            if (fragment != null && fragment.isAdded()) {
                                this.f20692a.f20695c.m22307a(view);
                            }
                        }
                    }

                    public void onAnimationStart(Animation animation) {
                        view.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                    }

                    public void onAnimationEnd(Animation animation) {
                        new Handler().postDelayed(new C42401(this), 7000);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                view.startAnimation(translateAnimation);
            }
        }
    }

    public void m22307a(final View view) {
        if (view.getVisibility() == 0 && view.getAnimation() != null && view.getAnimation().hasEnded()) {
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (view.getHeight() * -1));
            translateAnimation.setDuration(500);
            translateAnimation.setFillAfter(true);
            translateAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ BookmarkManager f20697b;

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    view.clearAnimation();
                    view.setVisibility(8);
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(translateAnimation);
        }
    }
}
