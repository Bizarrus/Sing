/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Looper
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.RelativeLayout
 *  android.widget.ScrollView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.ui.TouchInterceptingFrameLayout;
import com.smule.singandroid.CustomTabLayout;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.customviews.ColorThemeSelector;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.profile.ProfileUserInfo;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint(value={"InflateParams"})
public final class ProfileFragment_
extends ProfileFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier ad = new OnViewChangedNotifier();
    private View ae;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.t = (ProfileUserInfo)bundle.getParcelable("mSNPProfileUserInfo");
        this.u = (ProfileUserInfo)bundle.getParcelable("mLocalProfileUserInfo");
        this.v = (AccountIcon)bundle.getParcelable("mAccountIcon");
        this.w = bundle.getBoolean("mIsCurrentUsersProfile");
        this.x = bundle.getString("mAppId");
        this.y = bundle.getBoolean("mTakingPhotoForProfile");
        this.z = bundle.getInt("mFirstVisibleRowPosition");
        this.A = bundle.getInt("mFirstVisibleTop");
        this.B = bundle.getBoolean("mShowFutureWarning");
        this.C = bundle.getBoolean("mPreviewActive");
        this.D = bundle.getBoolean("mShouldFetchProfile");
        this.E = bundle.getBoolean("mProfilePhotoChanged");
        this.F = bundle.getBoolean("mProfilePhotoUsingDefaultPic");
        this.G = bundle.getBoolean("mCoverPhotoChanged");
        this.H = bundle.getBoolean("mColorThemeChanged");
        this.I = bundle.getBoolean("mPendingUploadChanges");
        this.J = bundle.getInt("mCurrentPerformanceType");
        this.K = (Intent)bundle.getParcelable("mEmailIntent");
        this.L = bundle.getBoolean("mIsMention");
        this.M = bundle.getInt("mPostLoadGoToTabId");
    }

    @Override
    protected void H() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ProfileFragment_.super.H();
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        ProfileFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    protected void a(@NonNull MediaPlayingViewInterface mediaPlayingViewInterface, @NonNull PerformanceV2 performanceV2, @NonNull ProfileFragment.CheckVideoStatusCallback checkVideoStatusCallback) {
        BackgroundExecutor.a();
        ProfileFragment_.super.a(mediaPlayingViewInterface, performanceV2, checkVideoStatusCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (RelativeLayout)hasViews.findViewById(2131756376);
        this.i = (TouchInterceptingFrameLayout)hasViews.findViewById(2131755396);
        this.j = (CustomTabLayout)hasViews.findViewById(2131756380);
        this.k = hasViews.findViewById(2131756381);
        this.l = (CustomViewPager)hasViews.findViewById(2131756379);
        this.m = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755044);
        this.n = (ScrollView)hasViews.findViewById(2131756377);
        this.o = (FrameLayout)hasViews.findViewById(2131756378);
        this.p = (FrameLayout)hasViews.findViewById(2131756382);
        this.q = (ColorThemeSelector)hasViews.findViewById(2131756385);
        View view = hasViews.findViewById(2131756383);
        hasViews = hasViews.findViewById(2131756384);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ProfileFragment_.this.av();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ProfileFragment_.this.aw();
                }
            });
        }
        this.J();
    }

    @Override
    protected void ac() {
        BackgroundExecutor.a();
        ProfileFragment_.super.ac();
    }

    @Override
    protected void aj() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ProfileFragment_.super.aj();
            return;
        }
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ProfileFragment_.super.aj();
            }
        }, (long)0);
    }

    @Override
    public void ap() {
        BackgroundExecutor.a();
        ProfileFragment_.super.ap();
    }

    @Override
    public void as() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ProfileFragment_.super.as();
            return;
        }
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ProfileFragment_.super.as();
            }
        }, (long)0);
    }

    @Override
    protected void c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ProfileFragment_.super.c(networkResponse);
            }
        }, (long)0);
    }

    @Override
    protected void c(PerformanceV2 performanceV2) {
        BackgroundExecutor.a();
        ProfileFragment_.super.c(performanceV2);
    }

    @Override
    protected void d(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ProfileFragment_.super.d(networkResponse);
            }
        }, (long)0);
    }

    @Override
    protected void d(PerformanceV2 performanceV2) {
        BackgroundExecutor.a();
        ProfileFragment_.super.d(performanceV2);
    }

    @Override
    public void e(final boolean bl) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ProfileFragment_.super.e(bl);
            return;
        }
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ProfileFragment_.super.e(bl);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.ae == null) {
            return null;
        }
        return this.ae.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.ad);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ae = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.ae == null) {
            this.ae = layoutInflater.inflate(2130903372, viewGroup, false);
        }
        return this.ae;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.ae = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mSNPProfileUserInfo", (Parcelable)this.t);
        bundle.putParcelable("mLocalProfileUserInfo", (Parcelable)this.u);
        bundle.putParcelable("mAccountIcon", (Parcelable)this.v);
        bundle.putBoolean("mIsCurrentUsersProfile", this.w);
        bundle.putString("mAppId", this.x);
        bundle.putBoolean("mTakingPhotoForProfile", this.y);
        bundle.putInt("mFirstVisibleRowPosition", this.z);
        bundle.putInt("mFirstVisibleTop", this.A);
        bundle.putBoolean("mShowFutureWarning", this.B);
        bundle.putBoolean("mPreviewActive", this.C);
        bundle.putBoolean("mShouldFetchProfile", this.D);
        bundle.putBoolean("mProfilePhotoChanged", this.E);
        bundle.putBoolean("mProfilePhotoUsingDefaultPic", this.F);
        bundle.putBoolean("mCoverPhotoChanged", this.G);
        bundle.putBoolean("mColorThemeChanged", this.H);
        bundle.putBoolean("mPendingUploadChanges", this.I);
        bundle.putInt("mCurrentPerformanceType", this.J);
        bundle.putParcelable("mEmailIntent", (Parcelable)this.K);
        bundle.putBoolean("mIsMention", this.L);
        bundle.putInt("mPostLoadGoToTabId", this.M);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.ad.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, ProfileFragment> {
    }

}

