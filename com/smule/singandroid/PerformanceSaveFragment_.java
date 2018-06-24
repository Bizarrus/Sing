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
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.TextView
 *  android.widget.ToggleButton
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.PerformanceSaveFragment;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.customviews.BubbleTooltipViewWithDropShadow;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PerformanceSaveFragment_
extends PerformanceSaveFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier aw = new OnViewChangedNotifier();
    private View ax;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.C = (PostSingBundle)bundle.getParcelable("mPostSingBundle");
        this.D = bundle.getString("mPerformanceKey");
        this.E = bundle.getBoolean("mHasShownRateUsDialog");
        this.F = bundle.getBoolean("mPerformanceSuccessfullyCreated");
        this.G = bundle.getString("mPerformanceAlbumArtUrl");
        this.H = bundle.getBoolean("mResourceReady");
        this.I = (Long)bundle.getSerializable("mUploadedTrackResourceId");
        this.J = bundle.getString("mAlbumArtFilePath");
        this.K = bundle.getBoolean("mPerformanceIsPrivate");
        this.L = bundle.getBoolean("mUsedHeadphone");
        this.M = bundle.getBoolean("mHeadphonesHadMic");
        this.N = (PerformanceV2)bundle.getParcelable("mPerformance");
        this.O = bundle.getBoolean("mDidChangeAlbumArt");
        this.P = bundle.getString("mVocalEffectName");
        this.Q = bundle.getString("mInitialVocalEffectName");
        this.R = bundle.getString("mFinalSelectedVocalEffectSNPId");
        this.S = bundle.getInt("mSelectedVocalEffectVersion");
        this.T = bundle.getString("mUniqueSelectedFXReview");
        this.U = bundle.getString("mAdjustedSlider");
        this.V = bundle.getString("mPlayPauseCount");
        this.W = (Float)bundle.getSerializable("mMetaParam1");
        this.X = (Float)bundle.getSerializable("mMetaParam2");
        this.Y = bundle.getBoolean("mVocalEffectVIPOnly");
        this.Z = bundle.getString("mRecordingFile");
        this.aa = bundle.getBoolean("mPitchCorrectEnabled");
        this.ab = bundle.getInt("mScore");
        this.ac = bundle.getFloat("mGain");
        this.ad = (PerformanceSaveFragment.Mode)((Object)bundle.getSerializable("mCurrentMode"));
        this.ae = (SongbookEntry)bundle.getParcelable("mEntry");
        this.af = bundle.getBoolean("mIsOpenCallPrivateWhenBeginningEdit");
        this.ag = bundle.getBoolean("mIsJoin");
        this.ah = bundle.getBundle("mMetadataBundle");
        this.ai = bundle.getBoolean("mInvitedFollowers");
        this.aj = bundle.getBoolean("mPerformanceCustomizeSaveClickEventLogged");
        this.ak = (Integer)bundle.getSerializable("mOtaLatencyEstimate");
        this.al = bundle.getBoolean("mIsCollab");
        this.am = bundle.getString("mCompressedFilename");
    }

    @Override
    protected void M() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.M();
            }
        }, (long)0);
    }

    @Override
    protected void O() {
        BackgroundExecutor.a();
        PerformanceSaveFragment_.super.O();
    }

    @Override
    protected void P() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.P();
            }
        }, (long)0);
    }

    @Override
    protected void Q() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.Q();
            }
        }, (long)0);
    }

    @Override
    protected void R() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.R();
            }
        }, (long)0);
    }

    @Override
    protected void S() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.S();
            }
        }, (long)0);
    }

    @Override
    protected void T() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.T();
            }
        }, (long)0);
    }

    @Override
    protected void a(final int n, final PerformanceV2 performanceV2) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.a(n, performanceV2);
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        PerformanceSaveFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.h = (Button)hasViews.findViewById(2131756233);
        this.i = (ImageView)hasViews.findViewById(2131755294);
        this.j = (TextView)hasViews.findViewById(2131756232);
        this.k = (ViewGroup)hasViews.findViewById(2131756234);
        this.l = (EditText)hasViews.findViewById(2131756235);
        this.m = (ImageView)hasViews.findViewById(2131756237);
        this.n = (ImageView)hasViews.findViewById(2131756238);
        this.o = (EditText)hasViews.findViewById(2131755454);
        this.p = (ViewGroup)hasViews.findViewById(2131756240);
        this.q = (TextView)hasViews.findViewById(2131756241);
        this.r = (TextView)hasViews.findViewById(2131756242);
        this.s = (ToggleButton)hasViews.findViewById(2131756243);
        this.t = (ViewGroup)hasViews.findViewById(2131756245);
        this.u = (ViewGroup)hasViews.findViewById(2131756246);
        this.v = (ToggleButton)hasViews.findViewById(2131756249);
        this.w = (TextView)hasViews.findViewById(2131756248);
        this.x = hasViews.findViewById(2131756251);
        this.y = (TextView)hasViews.findViewById(2131756252);
        this.z = (TextView)hasViews.findViewById(2131756253);
        this.A = (BubbleTooltipViewWithDropShadow)hasViews.findViewById(2131756254);
        this.B = (BubbleTooltipViewWithDropShadow)hasViews.findViewById(2131756255);
        if (this.h != null) {
            this.h.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    PerformanceSaveFragment_.this.t();
                }
            });
        }
        if (this.p != null) {
            this.p.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    PerformanceSaveFragment_.this.F();
                }
            });
        }
        if (this.t != null) {
            this.t.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    PerformanceSaveFragment_.this.G();
                }
            });
        }
        if (this.i != null) {
            this.i.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    PerformanceSaveFragment_.this.H();
                }
            });
        }
        if (this.s != null) {
            this.s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    PerformanceSaveFragment_.this.a(compoundButton, bl);
                }
            });
        }
        if (this.v != null) {
            this.v.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                    PerformanceSaveFragment_.this.b(compoundButton, bl);
                }
            });
        }
        this.a();
    }

    @Override
    protected void c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.c(networkResponse);
            }
        }, (long)0);
    }

    @Override
    protected void c(final String string2) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.c(string2);
            }
        }, (long)0);
    }

    @Override
    protected void d(final NetworkResponse networkResponse) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.d(networkResponse);
            }
        }, (long)0);
    }

    @Override
    protected void d(final Runnable runnable) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment_.super.d(runnable);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.ax == null) {
            return null;
        }
        return this.ax.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.aw);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ax = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.ax == null) {
            this.ax = layoutInflater.inflate(2130903350, viewGroup, false);
        }
        return this.ax;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.ax = null;
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
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mPostSingBundle", (Parcelable)this.C);
        bundle.putString("mPerformanceKey", this.D);
        bundle.putBoolean("mHasShownRateUsDialog", this.E);
        bundle.putBoolean("mPerformanceSuccessfullyCreated", this.F);
        bundle.putString("mPerformanceAlbumArtUrl", this.G);
        bundle.putBoolean("mResourceReady", this.H);
        bundle.putSerializable("mUploadedTrackResourceId", (Serializable)this.I);
        bundle.putString("mAlbumArtFilePath", this.J);
        bundle.putBoolean("mPerformanceIsPrivate", this.K);
        bundle.putBoolean("mUsedHeadphone", this.L);
        bundle.putBoolean("mHeadphonesHadMic", this.M);
        bundle.putParcelable("mPerformance", (Parcelable)this.N);
        bundle.putBoolean("mDidChangeAlbumArt", this.O);
        bundle.putString("mVocalEffectName", this.P);
        bundle.putString("mInitialVocalEffectName", this.Q);
        bundle.putString("mFinalSelectedVocalEffectSNPId", this.R);
        bundle.putInt("mSelectedVocalEffectVersion", this.S);
        bundle.putString("mUniqueSelectedFXReview", this.T);
        bundle.putString("mAdjustedSlider", this.U);
        bundle.putString("mPlayPauseCount", this.V);
        bundle.putSerializable("mMetaParam1", (Serializable)this.W);
        bundle.putSerializable("mMetaParam2", (Serializable)this.X);
        bundle.putBoolean("mVocalEffectVIPOnly", this.Y);
        bundle.putString("mRecordingFile", this.Z);
        bundle.putBoolean("mPitchCorrectEnabled", this.aa);
        bundle.putInt("mScore", this.ab);
        bundle.putFloat("mGain", this.ac);
        bundle.putSerializable("mCurrentMode", (Serializable)((Object)this.ad));
        bundle.putParcelable("mEntry", (Parcelable)this.ae);
        bundle.putBoolean("mIsOpenCallPrivateWhenBeginningEdit", this.af);
        bundle.putBoolean("mIsJoin", this.ag);
        bundle.putBundle("mMetadataBundle", this.ah);
        bundle.putBoolean("mInvitedFollowers", this.ai);
        bundle.putBoolean("mPerformanceCustomizeSaveClickEventLogged", this.aj);
        bundle.putSerializable("mOtaLatencyEstimate", (Serializable)this.ak);
        bundle.putBoolean("mIsCollab", this.al);
        bundle.putString("mCompressedFilename", this.am);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.aw.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, PerformanceSaveFragment> {
    }

}

