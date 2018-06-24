/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.TextureView
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.DuetJoinSaveFragment;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class DuetJoinSaveFragment_
extends DuetJoinSaveFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier ab = new OnViewChangedNotifier();
    private View ac;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.v = (PostSingBundle)bundle.getParcelable("mPostSingBundle");
        this.x = bundle.getString("mPerformanceKey");
        this.y = bundle.getBoolean("mPerformanceSuccessfullyCreated");
        this.z = bundle.getBoolean("mResourceReady");
        this.A = (Long)bundle.getSerializable("mUploadedTrackResourceId");
        this.B = bundle.getBoolean("mPerformanceIsPrivate");
        this.C = bundle.getBoolean("mUsedHeadphone");
        this.D = bundle.getBoolean("mHeadphonesHadMic");
        this.E = (PerformanceV2)bundle.getParcelable("mPerformance");
        this.F = bundle.getString("mVocalEffectName");
        this.G = bundle.getString("mInitialVocalEffectName");
        this.H = bundle.getString("mFinalSelectedVocalEffectSNPId");
        this.I = bundle.getInt("mSelectedVocalEffectVersion");
        this.J = bundle.getString("mUniqueSelectedFXsReview");
        this.K = bundle.getString("mAdjustedSlider");
        this.L = bundle.getString("mPlayPauseCount");
        this.M = (Float)bundle.getSerializable("mMetaParam1");
        this.N = (Float)bundle.getSerializable("mMetaParam2");
        this.O = bundle.getBoolean("mVocalEffectVIPOnly");
        this.P = bundle.getString("mRecordingFile");
        this.Q = bundle.getInt("mScore");
        this.R = bundle.getFloat("mGain");
        this.S = bundle.getBoolean("mIsFollowingPartnerAlready");
        this.T = bundle.getBoolean("mHasPressedFollowButton");
        this.U = bundle.getString("mVideoFile");
        this.V = bundle.getBundle("mMetaDataBundle");
        this.W = bundle.getBoolean("mIsVideo");
        this.X = bundle.getFloat("mJoinMaxPowerPositionSeconds");
        this.Y = bundle.getString("mCompressedFilename");
        this.Z = (Integer)bundle.getSerializable("mOtaLatencyEstimate");
    }

    @Override
    protected void J() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.J();
            }
        }, (long)0);
    }

    @Override
    protected void N() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.N();
            }
        }, (long)0);
    }

    @Override
    protected void O() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.O();
            }
        }, (long)0);
    }

    @Override
    protected void P() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.P();
            }
        }, (long)0);
    }

    @Override
    protected void Q() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.Q();
            }
        }, (long)0);
    }

    @Override
    protected void R() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.R();
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        DuetJoinSaveFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (TextView)hasViews.findViewById(2131755680);
        this.i = (ProgressBar)hasViews.findViewById(2131755344);
        this.j = (TextView)hasViews.findViewById(2131755228);
        this.k = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755682);
        this.l = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755683);
        this.m = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755684);
        this.n = hasViews.findViewById(2131755399);
        this.o = (TextView)hasViews.findViewById(2131755686);
        this.p = hasViews.findViewById(2131755685);
        this.q = (Button)hasViews.findViewById(2131755681);
        this.r = (Button)hasViews.findViewById(2131755247);
        this.s = (TextureView)hasViews.findViewById(2131755678);
        this.t = (Button)hasViews.findViewById(2131755687);
        this.u = hasViews.findViewById(2131755416);
        if (this.r != null) {
            this.r.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    DuetJoinSaveFragment_.this.t();
                }
            });
        }
        if (this.q != null) {
            this.q.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    DuetJoinSaveFragment_.this.I();
                }
            });
        }
        if (this.t != null) {
            this.t.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    DuetJoinSaveFragment_.this.L();
                }
            });
        }
        this.K();
    }

    @Override
    protected void c(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.c(bl);
            }
        }, (long)0);
    }

    @Override
    protected void d(final int n) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                DuetJoinSaveFragment_.super.d(n);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.ac == null) {
            return null;
        }
        return this.ac.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.ab);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ac = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.ac == null) {
            this.ac = layoutInflater.inflate(2130903216, viewGroup, false);
        }
        return this.ac;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.ac = null;
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
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mPostSingBundle", (Parcelable)this.v);
        bundle.putString("mPerformanceKey", this.x);
        bundle.putBoolean("mPerformanceSuccessfullyCreated", this.y);
        bundle.putBoolean("mResourceReady", this.z);
        bundle.putSerializable("mUploadedTrackResourceId", (Serializable)this.A);
        bundle.putBoolean("mPerformanceIsPrivate", this.B);
        bundle.putBoolean("mUsedHeadphone", this.C);
        bundle.putBoolean("mHeadphonesHadMic", this.D);
        bundle.putParcelable("mPerformance", (Parcelable)this.E);
        bundle.putString("mVocalEffectName", this.F);
        bundle.putString("mInitialVocalEffectName", this.G);
        bundle.putString("mFinalSelectedVocalEffectSNPId", this.H);
        bundle.putInt("mSelectedVocalEffectVersion", this.I);
        bundle.putString("mUniqueSelectedFXsReview", this.J);
        bundle.putString("mAdjustedSlider", this.K);
        bundle.putString("mPlayPauseCount", this.L);
        bundle.putSerializable("mMetaParam1", (Serializable)this.M);
        bundle.putSerializable("mMetaParam2", (Serializable)this.N);
        bundle.putBoolean("mVocalEffectVIPOnly", this.O);
        bundle.putString("mRecordingFile", this.P);
        bundle.putInt("mScore", this.Q);
        bundle.putFloat("mGain", this.R);
        bundle.putBoolean("mIsFollowingPartnerAlready", this.S);
        bundle.putBoolean("mHasPressedFollowButton", this.T);
        bundle.putString("mVideoFile", this.U);
        bundle.putBundle("mMetaDataBundle", this.V);
        bundle.putBoolean("mIsVideo", this.W);
        bundle.putFloat("mJoinMaxPowerPositionSeconds", this.X);
        bundle.putString("mCompressedFilename", this.Y);
        bundle.putSerializable("mOtaLatencyEstimate", (Serializable)this.Z);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.ab.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, DuetJoinSaveFragment> {
    }

}

