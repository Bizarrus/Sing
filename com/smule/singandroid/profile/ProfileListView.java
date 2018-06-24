/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.ListView
 *  com.millennialmedia.internal.utils.ViewUtils
 *  com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer
 *  com.mopub.nativeads.MoPubStreamAdPlacer
 *  com.mopub.nativeads.ViewBinder
 *  com.mopub.nativeads.ViewBinder$Builder
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.profile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.millennialmedia.internal.utils.ViewUtils;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.MoPubStreamAdPlacer;
import com.mopub.nativeads.ViewBinder;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.adapters.profile.ProfileArrangementDataSource;
import com.smule.singandroid.adapters.profile.ProfileFavoritesDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.adapters.profile.ProfilePerformanceDataSource;
import com.smule.singandroid.ads.SingAdSettings;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.list_items.VideoUploadingView;
import com.smule.singandroid.profile.BasePerformancesAdapter;
import com.smule.singandroid.profile.FavoritesAdapter;
import com.smule.singandroid.profile.InvitesAdapter;
import com.smule.singandroid.profile.OwnedArrangementsAdapter;
import com.smule.singandroid.profile.PerformancesAdapter;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.profile.ProfileListView_;
import com.smule.singandroid.profile.ProfileUserInfoView;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileListView
extends LinearLayout
implements MagicDataSource.DataSourceObserver {
    public static final String a = ProfileListView.class.getSimpleName();
    @ViewById
    public MediaListView b;
    @ViewById
    protected SwipeRefreshLayout c;
    BasePerformancesAdapter d;
    protected ProfileFragment e;
    protected int f;
    protected View g;
    protected View h;
    protected Observer i;
    View.OnClickListener j;
    private MagicNativeAdMediatorAdapter k;
    private Observer l;

    public ProfileListView(Context context) {
        super(context);
        this.i = new Observer(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void update(Observable object, Object object2) {
                long l;
                block3 : {
                    block2 : {
                        if (ProfileListView.this.e == null || !ProfileListView.this.e.isAdded()) break block2;
                        object2 = (Bundle)object2;
                        object = object2.getString("PERFORMANCE_KEY");
                        l = object2.getLong("FILE_UPLOAD_PROGRESS", 0);
                        object2 = (FileUploaderService.VideoUploadStatus)((Object)object2.get("FILE_UPLOAD_STATUS"));
                        if ((object = (VideoUploadingView)ProfileListView.this.b.findViewWithTag(object)) != null && object2 != null) break block3;
                    }
                    return;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable((VideoUploadingView)object, (FileUploaderService.VideoUploadStatus)((Object)object2), l){
                    final /* synthetic */ VideoUploadingView a;
                    final /* synthetic */ FileUploaderService.VideoUploadStatus b;
                    final /* synthetic */ long c;
                    {
                        this.a = videoUploadingView;
                        this.b = videoUploadStatus;
                        this.c = l;
                    }

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        block5 : {
                            block4 : {
                                if (ProfileListView.this.e == null || !ProfileListView.this.e.isAdded()) break block4;
                                this.a.setVideoStatus(this.b);
                                if (this.b == FileUploaderService.VideoUploadStatus.a) {
                                    this.a.a(this.c);
                                    return;
                                }
                                if (this.b == FileUploaderService.VideoUploadStatus.d) break block5;
                            }
                            return;
                        }
                        ProfileListView.this.e.b(this.a.getPerformance());
                    }
                });
            }

        };
        this.l = new Observer(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void update(Observable observable, Object object) {
                final int n = (Integer)object;
                if (ProfileListView.this.e == null || !ProfileListView.this.e.isAdded() || n == ProfileListView.this.e.N() || n != ProfileListView.this.f) {
                    return;
                }
                ViewUtils.getActivityForView((View)ProfileListView.this).runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        if (ProfileListView.this.e == null || !ProfileListView.this.e.isAdded()) {
                            return;
                        }
                        ProfileListView.this.e.ao();
                        ProfileListView.this.e.e(n);
                        ProfileListView.this.b.setSelection(0);
                    }
                });
            }

        };
        this.j = new View.OnClickListener(){

            public void onClick(View view) {
                ((MasterActivity)ProfileListView.this.e.getActivity()).e("suggested_songs");
            }
        };
    }

    public static ProfileListView a(Context object, ProfileFragment profileFragment, int n) {
        object = ProfileListView_.a((Context)object);
        object.e = profileFragment;
        object.f = n;
        ReferenceMonitor.a().a(object);
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void a() {
        if (this.e == null) {
            return;
        }
        var1_1 = this.g == null;
        if (var1_1) {
            this.g = HeaderAnchor.a(this.getContext(), this.e);
        }
        this.g.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, this.e.ad()));
        if (!var1_1) ** GOTO lbl11
        try {
            this.b.setHeaderView(this.g);
            return;
lbl11: // 1 sources:
            this.d.b();
            return;
        }
        catch (Exception var2_2) {
            MagicCrittercism.a(var2_2);
            return;
        }
    }

    @Override
    public void a(MagicDataSource magicDataSource) {
        this.c.setRefreshing(true);
    }

    @Override
    public void a(MagicDataSource magicDataSource, List<Object> list) {
    }

    public void b() {
        this.c.setVisibility(8);
        this.c.setProgressViewOffset(false, 0, 0);
        this.c.setOnRefreshListener(null);
    }

    @Override
    public void b(MagicDataSource magicDataSource) {
        this.c.setRefreshing(false);
    }

    public void c() {
        int n = this.getResources().getDimensionPixelOffset(2131427385);
        int n2 = this.getResources().getDimensionPixelOffset(2131428205);
        this.c.setProgressViewOffset(true, n, n2 + n);
        this.c.setVisibility(0);
        this.c.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            public void onRefresh() {
                if (ProfileListView.this.d != null) {
                    ProfileListView.this.d.f();
                }
                if (ProfileListView.this.e.L() != null) {
                    if (!ProfileListView.this.e.M()) {
                        ProfileListView.this.e.I();
                    }
                    ProfileListView.this.e.L().d();
                }
                ProfileListView.this.e.d(true);
                ProfileListView.this.e.a(true, false);
                ProfileListView.this.e.al();
            }
        });
    }

    @Override
    public void c(MagicDataSource magicDataSource) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public void d() {
        if (this.e == null || this.e.O() && !this.e.M()) {
            return;
        }
        if (this.f == 0) {
            this.d = new PerformancesAdapter(this, new ProfilePerformanceDataSource(this.e));
            this.e.a((PerformancesAdapter)this.d);
        } else if (this.f == 1) {
            this.d = new InvitesAdapter(this, new ProfileOpenCallDataSource(this.e));
            this.e.a((InvitesAdapter)this.d);
        } else if (this.f == 2) {
            this.d = new OwnedArrangementsAdapter(this, new ProfileArrangementDataSource(this.e));
            this.e.a((OwnedArrangementsAdapter)this.d);
            if (this.e.F().d() && ArrangementManager.a().b()) {
                this.d.f();
            }
        } else if (this.f == 3) {
            this.d = new FavoritesAdapter(this, new ProfileFavoritesDataSource(this.e));
            this.e.a((FavoritesAdapter)this.d);
        }
        if (this.f == 1) {
            if (MagicAdSettings.a(Analytics.e)) {
                ViewBinder viewBinder = new ViewBinder.Builder(2130903302).iconImageId(2131755993).titleId(2131755994).textId(2131755996).mainImageId(2131755995).privacyInformationIconImageId(2131755998).build();
                HashMap<String, String> hashMap = SingAdSettings.a(null);
                this.k = MagicAdAdapterFactory.a().a(this.e.getActivity(), Analytics.e, new ViewBinder.Builder(2130903298).build(), viewBinder, (MoPubStreamAdPlacer)new MagicMoPubGhostStreamAdPlacer(this.e.getActivity()), hashMap, this.b, this.d, 2131755318, new View.OnClickListener(){

                    public void onClick(View view) {
                        ProfileListView.this.e.E();
                        new NativeAdsMoreDialog(ProfileListView.this.e).show();
                    }
                }, new Runnable(){

                    @Override
                    public void run() {
                        ProfileListView.this.e.E();
                    }
                });
                if (this.k != null) {
                    this.k.loadAds();
                } else {
                    Log.e(a, "mMagicNativeAdMediatorAdapter null");
                    this.b.setMagicAdapter(this.d);
                }
            } else {
                this.b.setMagicAdapter(this.d);
            }
        } else {
            this.b.setMagicAdapter(this.d);
        }
        this.d.a(this);
    }

    @Override
    public void d(MagicDataSource magicDataSource) {
    }

    @AfterViews
    protected void e() {
        this.c.setColorSchemeResources(new int[]{2131689905});
    }

    public ListView getListView() {
        return this.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.a().a("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.i);
        NotificationCenter.a().a("PERFORMANCE_TYPE_TABS_CLICKED", this.l);
        if (this.e != null && this.e.ab()) {
            this.b();
        } else {
            this.c();
        }
        this.a();
        this.h = new View((Context)this.e.getActivity());
        this.b.addFooterView(this.h);
        this.d();
        this.e.ap();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.a().b("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.i);
        NotificationCenter.a().b("PERFORMANCE_TYPE_TABS_CLICKED", this.l);
        this.b.setHeaderView(null);
        this.b.removeFooterView(this.h);
        this.g = null;
        this.e = null;
        if (this.k != null) {
            this.k.destroy();
            this.k = null;
        }
    }

    @EViewGroup
    protected static class HeaderAnchor
    extends LinearLayout {
        private ProfileFragment a;

        public HeaderAnchor(Context context) {
            super(context);
        }

        static /* synthetic */ HeaderAnchor a(Context context, ProfileFragment profileFragment) {
            return HeaderAnchor.b(context, profileFragment);
        }

        private static HeaderAnchor b(Context object, ProfileFragment profileFragment) {
            object = ProfileListView_.HeaderAnchor_.a((Context)object);
            object.a = profileFragment;
            return object;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.a.r.dispatchTouchEvent(motionEvent);
            return true;
        }
    }

}

