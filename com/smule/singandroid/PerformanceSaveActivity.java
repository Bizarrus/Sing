/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  com.facebook.CallbackManager
 *  com.smule.singandroid.utils.ImageToDiskUtils
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.InstanceState
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.AbsListView;
import com.facebook.CallbackManager;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.CompletedFuture;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.DuetJoinSaveFragment;
import com.smule.singandroid.PerformanceSaveFragment;
import com.smule.singandroid.PerformanceSaveFragmentFactory;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.PostSingFlowActivity;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.mediaplaying.DummyPlaybackPresenter;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.mediaplaying.PlaybackPresenter;
import com.smule.singandroid.onboarding.OnboardingUploadFragment;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;

@SuppressLint(value={"Registered"})
@EActivity
public class PerformanceSaveActivity
extends BaseActivity
implements BaseFragment {
    private static final String i = PerformanceSaveActivity.class.getName();
    @InstanceState
    protected PostSingBundle g;
    protected SingBundle h;
    private Future<PerformanceManager.PreuploadResponse> j;
    private Bitmap k;

    /*
     * Enabled aggressive block sorting
     */
    private void a(Future<PerformanceManager.PreuploadResponse> future) {
        this.j = future;
        Fragment fragment = this.getFragmentManager().findFragmentById(2131756230);
        if (fragment instanceof DuetJoinSaveFragment) {
            ((DuetJoinSaveFragment)fragment).a(future);
            return;
        } else {
            if (fragment instanceof OnboardingUploadFragment) {
                ((OnboardingUploadFragment)fragment).a(future);
                return;
            }
            if (!(fragment instanceof PerformanceSaveFragment)) return;
            {
                ((PerformanceSaveFragment)fragment).a(future);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void u() {
        PerformancesAPI uploadType;
        String string2;
        if (this.g.i != null) {
            PerformanceManager.PreuploadResponse preuploadResponse = new PerformanceManager.PreuploadResponse();
            preuploadResponse.a = NetworkResponse.a();
            preuploadResponse.mResources = this.g.i;
            this.a((Future<PerformanceManager.PreuploadResponse>)((Object)new CompletedFuture<PerformanceManager.PreuploadResponse>(preuploadResponse)));
            return;
        }
        if (this.h.k) {
            uploadType = PerformancesAPI.JOIN;
            string2 = this.g.b.j;
        } else {
            uploadType = PerformancesAPI.CREATE;
            string2 = null;
        }
        Object object = this.h.d;
        String string3 = object.d();
        String string4 = object.t() ? object.c() : null;
        if (!object.t()) {
            Log.e(i, "beginPreuploadIfNeeded() - encounter songbook entry for raven song:" + object);
            return;
        }
        PerformanceV2.CompositionType compositionType = PerformanceV2.CompositionType.b;
        switch (.a[this.h.b.ordinal()]) {
            default: {
                object = PerformancesAPI.SOLO;
                break;
            }
            case 1: {
                object = PerformancesAPI.DUET;
                break;
            }
            case 2: {
                object = PerformancesAPI.GROUP;
            }
        }
        this.a(PerformanceManager.a().a(uploadType, (Object)object, compositionType, string3, string4, string2, this.v()));
    }

    private boolean v() {
        Bundle bundle = (Bundle)this.getIntent().getParcelableExtra("OGG_FILE_METADATA_BUNDLE_EXTRA_KEY");
        if (bundle != null && !TextUtils.isEmpty((CharSequence)bundle.getString("VIDEO_FILE"))) {
            return true;
        }
        return false;
    }

    @Override
    public QuickReturnListViewMenuSyncer a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener) {
        return null;
    }

    public void a() {
        if (this.j != null) {
            this.j.cancel(true);
            this.j = null;
        }
        this.u();
    }

    @Override
    public void a(PerformanceV2 performanceV2, boolean bl, boolean bl2) {
    }

    @Override
    public void a(SongbookEntry songbookEntry, boolean bl, Analytics searchTarget) {
    }

    @Override
    public void a(BaseFragment menuToggleAction) {
    }

    @Override
    public void a(BaseFragment baseFragment) {
    }

    @Override
    public void a(BaseFragment baseFragment, String string2) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(PostSingBundle postSingBundle, PerformanceV2 performanceV2, boolean bl) {
        if (bl) {
            PostSingFlowActivity.a((Activity)this, postSingBundle, performanceV2);
        } else {
            PostSingFlowActivity.a((Activity)this, postSingBundle);
        }
        this.finish();
    }

    @Override
    public void a(NowPlayingFragment nowPlayingFragment) {
    }

    @Override
    public void a(String string2) {
    }

    @Override
    public void a(@NonNull List<MediaPlayingPlayable> list, int n) {
    }

    @Override
    public void a_(Intent intent) {
    }

    public BaseFragment b() {
        return (BaseFragment)this.getFragmentManager().findFragmentById(2131756230);
    }

    @Override
    public void b(BaseFragment baseFragment) {
        Log.b(i, "popFragment - called with fragment tag: " + baseFragment.getTag());
        this.finish();
    }

    @Override
    public boolean d() {
        return true;
    }

    @Override
    public CallbackManager h() {
        return null;
    }

    @Override
    public PlaybackPresenter i() {
        return new DummyPlaybackPresenter();
    }

    @Override
    public void m_() {
    }

    @Override
    public V3BillingHelper n_() {
        return null;
    }

    @Override
    public void o_() {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onBackPressed() {
        Object object = this.h.k ? DuetJoinSaveFragment.g : PerformanceSaveFragment.g;
        object = (BaseFragment)this.getFragmentManager().findFragmentByTag((String)object);
        if (object != null && object.d()) {
            return;
        }
        super.onBackPressed();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Log.b(i, "onCreate - no saved instance state");
            this.g = PostSingBundle.a(this.getIntent());
        } else {
            Log.b(i, "onCreate - restoring from saved instance state");
        }
        this.h = this.g.b;
        FollowManager.a().a((FollowManager.FolloweesResponseCallback)null);
        FragmentManager fragmentManager = this.getFragmentManager();
        bundle = (Bundle)this.getIntent().getParcelableExtra("OGG_FILE_METADATA_BUNDLE_EXTRA_KEY");
        if (this.h.k) {
            this.k = ImageToDiskUtils.a((Context)this, (String)"duetjoinerthumb");
            if ((DuetJoinSaveFragment)fragmentManager.findFragmentByTag(DuetJoinSaveFragment.g) != null) return;
            {
                fragmentManager = fragmentManager.beginTransaction();
                fragmentManager.add(2131756230, (Fragment)DuetJoinSaveFragment.a(this.g, bundle), DuetJoinSaveFragment.g);
                fragmentManager.commit();
                return;
            }
        } else if (this.h.o) {
            if ((OnboardingUploadFragment)fragmentManager.findFragmentByTag(OnboardingUploadFragment.h) != null) return;
            {
                fragmentManager = fragmentManager.beginTransaction();
                fragmentManager.add(2131756230, (Fragment)OnboardingUploadFragment.a(this.g, bundle), OnboardingUploadFragment.g);
                fragmentManager.commit();
                return;
            }
        } else {
            if ((PerformanceSaveFragment)fragmentManager.findFragmentByTag(PerformanceSaveFragment.g) != null) return;
            {
                fragmentManager = fragmentManager.beginTransaction();
                fragmentManager.add(2131756230, (Fragment)PerformanceSaveFragmentFactory.a(this.g, bundle), PerformanceSaveFragment.g);
                fragmentManager.commit();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        if (this.j != null && this.g.i == null) {
            try {
                PerformanceManager.PreuploadResponse preuploadResponse = this.j.get(0, TimeUnit.NANOSECONDS);
                if (preuploadResponse != null) {
                    this.g.i = preuploadResponse.mResources;
                }
            }
            catch (Exception exception) {}
        }
        super.onSaveInstanceState(bundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.u();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        if (!bl) return;
        {
            BaseFragment baseFragment = this.b();
            if (baseFragment instanceof DuetJoinSaveFragment) {
                ((DuetJoinSaveFragment)baseFragment).a();
                return;
            } else {
                if (!(baseFragment instanceof OnboardingUploadFragment)) return;
                {
                    ((OnboardingUploadFragment)baseFragment).t();
                    return;
                }
            }
        }
    }

    public Bitmap t() {
        return this.k;
    }

}

