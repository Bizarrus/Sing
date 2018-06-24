/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.onboarding;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.onboarding.OnboardingUploadFragment;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingUploadFragment_
extends OnboardingUploadFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier U = new OnViewChangedNotifier();
    private View V;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.s = (PostSingBundle)bundle.getParcelable("mPostSingBundle");
        this.t = bundle.getString("mPerformanceKey");
        this.u = bundle.getBoolean("mPerformanceSuccessfullyCreated");
        this.v = (Long)bundle.getSerializable("mUploadedTrackResourceId");
        this.w = bundle.getBoolean("mResourceReady");
        this.x = bundle.getBoolean("mPerformanceIsPrivate");
        this.y = bundle.getBoolean("mUsedHeadphone");
        this.z = bundle.getBoolean("mHeadphonesHadMic");
        this.A = (PerformanceV2)bundle.getParcelable("mPerformance");
        this.B = bundle.getString("mVocalEffectName");
        this.C = bundle.getString("mInitialVocalEffectName");
        this.D = bundle.getString("mFinalSelectedVocalEffectSNPId");
        this.E = bundle.getInt("mSelectedVocalEffectVersion");
        this.F = bundle.getString("mUniqueSelectedFXsReview");
        this.G = bundle.getString("mAdjustedSlider");
        this.H = bundle.getString("mPlayPauseCount");
        this.I = (Float)bundle.getSerializable("mMetaParam1");
        this.J = (Float)bundle.getSerializable("mMetaParam2");
        this.K = bundle.getBoolean("mVocalEffectVIPOnly");
        this.L = bundle.getString("mRecordingFile");
        this.M = bundle.getBoolean("mPitchCorrectEnabled");
        this.N = bundle.getInt("mScore");
        this.O = bundle.getFloat("mGain");
        this.P = bundle.getBundle("mMetaDataBundle");
        this.Q = (SongbookEntry)bundle.getParcelable("mEntry");
        this.R = bundle.getString("mCompressedFilename");
        this.S = (Integer)bundle.getSerializable("mOtaLatencyEstimate");
    }

    @Override
    protected void F() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment_.super.F();
            }
        }, (long)0);
    }

    @Override
    protected void G() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment_.super.G();
            }
        }, (long)0);
    }

    @Override
    protected void H() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment_.super.H();
            }
        }, (long)0);
    }

    @Override
    protected void I() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment_.super.I();
            }
        }, (long)0);
    }

    @Override
    protected void J() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment_.super.J();
            }
        }, (long)0);
    }

    @Override
    protected void K() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment_.super.K();
            }
        }, (long)0);
    }

    @Override
    protected void L() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                OnboardingUploadFragment_.super.L();
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        OnboardingUploadFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.i = hasViews.findViewById(2131756196);
        this.j = (TextView)hasViews.findViewById(2131756197);
        this.k = (ProgressBar)hasViews.findViewById(2131755344);
        this.l = (TextView)hasViews.findViewById(2131755228);
        this.m = (ImageView)hasViews.findViewById(2131755281);
        this.n = (TextView)hasViews.findViewById(2131756198);
        this.o = (TextView)hasViews.findViewById(2131756199);
        this.p = (TextView)hasViews.findViewById(2131756201);
        this.q = hasViews.findViewById(2131756200);
        this.r = hasViews.findViewById(2131755416);
        this.a();
    }

    public View findViewById(int n) {
        if (this.V == null) {
            return null;
        }
        return this.V.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.U);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.V = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.V == null) {
            this.V = layoutInflater.inflate(2130903340, viewGroup, false);
        }
        return this.V;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.V = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mPostSingBundle", (Parcelable)this.s);
        bundle.putString("mPerformanceKey", this.t);
        bundle.putBoolean("mPerformanceSuccessfullyCreated", this.u);
        bundle.putSerializable("mUploadedTrackResourceId", (Serializable)this.v);
        bundle.putBoolean("mResourceReady", this.w);
        bundle.putBoolean("mPerformanceIsPrivate", this.x);
        bundle.putBoolean("mUsedHeadphone", this.y);
        bundle.putBoolean("mHeadphonesHadMic", this.z);
        bundle.putParcelable("mPerformance", (Parcelable)this.A);
        bundle.putString("mVocalEffectName", this.B);
        bundle.putString("mInitialVocalEffectName", this.C);
        bundle.putString("mFinalSelectedVocalEffectSNPId", this.D);
        bundle.putInt("mSelectedVocalEffectVersion", this.E);
        bundle.putString("mUniqueSelectedFXsReview", this.F);
        bundle.putString("mAdjustedSlider", this.G);
        bundle.putString("mPlayPauseCount", this.H);
        bundle.putSerializable("mMetaParam1", (Serializable)this.I);
        bundle.putSerializable("mMetaParam2", (Serializable)this.J);
        bundle.putBoolean("mVocalEffectVIPOnly", this.K);
        bundle.putString("mRecordingFile", this.L);
        bundle.putBoolean("mPitchCorrectEnabled", this.M);
        bundle.putInt("mScore", this.N);
        bundle.putFloat("mGain", this.O);
        bundle.putBundle("mMetaDataBundle", this.P);
        bundle.putParcelable("mEntry", (Parcelable)this.Q);
        bundle.putString("mCompressedFilename", this.R);
        bundle.putSerializable("mOtaLatencyEstimate", (Serializable)this.S);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.U.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, OnboardingUploadFragment> {
    }

}

