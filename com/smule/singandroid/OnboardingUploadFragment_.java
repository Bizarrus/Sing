package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OnboardingUploadFragment_ extends OnboardingUploadFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19084R = new OnViewChangedNotifier();
    private View f19085S;

    class C39121 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment_ f19077a;

        C39121(OnboardingUploadFragment_ onboardingUploadFragment_) {
            this.f19077a = onboardingUploadFragment_;
        }

        public void run() {
            super.mo6492A();
        }
    }

    class C39132 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment_ f19078a;

        C39132(OnboardingUploadFragment_ onboardingUploadFragment_) {
            this.f19078a = onboardingUploadFragment_;
        }

        public void run() {
            super.mo6493B();
        }
    }

    class C39143 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment_ f19079a;

        C39143(OnboardingUploadFragment_ onboardingUploadFragment_) {
            this.f19079a = onboardingUploadFragment_;
        }

        public void run() {
            super.mo6494C();
        }
    }

    class C39154 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment_ f19080a;

        C39154(OnboardingUploadFragment_ onboardingUploadFragment_) {
            this.f19080a = onboardingUploadFragment_;
        }

        public void run() {
            super.mo6495D();
        }
    }

    class C39165 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment_ f19081a;

        C39165(OnboardingUploadFragment_ onboardingUploadFragment_) {
            this.f19081a = onboardingUploadFragment_;
        }

        public void run() {
            super.mo6496E();
        }
    }

    class C39176 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment_ f19082a;

        C39176(OnboardingUploadFragment_ onboardingUploadFragment_) {
            this.f19082a = onboardingUploadFragment_;
        }

        public void run() {
            super.mo6497F();
        }
    }

    class C39187 implements Runnable {
        final /* synthetic */ OnboardingUploadFragment_ f19083a;

        C39187(OnboardingUploadFragment_ onboardingUploadFragment_) {
            this.f19083a = onboardingUploadFragment_;
        }

        public void run() {
            super.mo6498G();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, OnboardingUploadFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19084R);
        m20584a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f19085S == null) {
            return null;
        }
        return this.f19085S.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f19085S = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f19085S == null) {
            this.f19085S = layoutInflater.inflate(C1947R.layout.onboarding_upload_fragment, viewGroup, false);
        }
        return this.f19085S;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19085S = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
    }

    private void m20584a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20587b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f19084R.a(this);
    }

    public void m20602a(HasViews hasViews) {
        this.g = hasViews.findViewById(C1947R.id.uploading_top_bar_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.uploading_top_bar_title);
        this.i = (ProgressBar) hasViews.findViewById(C1947R.id.progress_bar);
        this.j = (TextView) hasViews.findViewById(C1947R.id.title_text_view);
        this.k = (ImageView) hasViews.findViewById(C1947R.id.album_art);
        this.l = (TextView) hasViews.findViewById(C1947R.id.title_textview);
        this.m = (TextView) hasViews.findViewById(C1947R.id.subtitle_textview);
        this.n = (TextView) hasViews.findViewById(C1947R.id.desc_textview);
        this.o = hasViews.findViewById(C1947R.id.sonic_dots_background);
        this.p = hasViews.findViewById(C1947R.id.background);
        m20579a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mPostSingBundle", this.q);
        bundle.putString("mPerformanceKey", this.r);
        bundle.putBoolean("mPerformanceSuccessfullyCreated", this.s);
        bundle.putSerializable("mUploadedTrackResourceId", this.t);
        bundle.putBoolean("mResourceReady", this.u);
        bundle.putBoolean("mPerformanceIsPrivate", this.v);
        bundle.putBoolean("mUsedHeadphone", this.w);
        bundle.putBoolean("mHeadphonesHadMic", this.x);
        bundle.putParcelable("mPerformance", this.y);
        bundle.putString("mVocalEffectName", this.z);
        bundle.putString("mInitialVocalEffectName", this.A);
        bundle.putString("mFinalSelectedVocalEffectSNPId", this.B);
        bundle.putString("mUniqueSelectedFXsReview", this.C);
        bundle.putString("mAdjustedSlider", this.D);
        bundle.putString("mPlayPauseCount", this.E);
        bundle.putSerializable("mMetaParam1", this.F);
        bundle.putSerializable("mMetaParam2", this.G);
        bundle.putBoolean("mVocalEffectVIPOnly", this.H);
        bundle.putString("mRecordingFile", this.I);
        bundle.putBoolean("mPitchCorrectEnabled", this.J);
        bundle.putInt("mScore", this.K);
        bundle.putFloat("mGain", this.L);
        bundle.putParcelable("mSong", this.M);
        bundle.putBundle("mMetaDataBundle", this.N);
        bundle.putParcelable("mEntry", this.O);
        bundle.putString("mCompressedFilename", this.P);
    }

    private void m20587b(Bundle bundle) {
        if (bundle != null) {
            this.q = (PostSingBundle) bundle.getParcelable("mPostSingBundle");
            this.r = bundle.getString("mPerformanceKey");
            this.s = bundle.getBoolean("mPerformanceSuccessfullyCreated");
            this.t = (Long) bundle.getSerializable("mUploadedTrackResourceId");
            this.u = bundle.getBoolean("mResourceReady");
            this.v = bundle.getBoolean("mPerformanceIsPrivate");
            this.w = bundle.getBoolean("mUsedHeadphone");
            this.x = bundle.getBoolean("mHeadphonesHadMic");
            this.y = (PerformanceV2) bundle.getParcelable("mPerformance");
            this.z = bundle.getString("mVocalEffectName");
            this.A = bundle.getString("mInitialVocalEffectName");
            this.B = bundle.getString("mFinalSelectedVocalEffectSNPId");
            this.C = bundle.getString("mUniqueSelectedFXsReview");
            this.D = bundle.getString("mAdjustedSlider");
            this.E = bundle.getString("mPlayPauseCount");
            this.F = (Float) bundle.getSerializable("mMetaParam1");
            this.G = (Float) bundle.getSerializable("mMetaParam2");
            this.H = bundle.getBoolean("mVocalEffectVIPOnly");
            this.I = bundle.getString("mRecordingFile");
            this.J = bundle.getBoolean("mPitchCorrectEnabled");
            this.K = bundle.getInt("mScore");
            this.L = bundle.getFloat("mGain");
            this.M = (SongV2) bundle.getParcelable("mSong");
            this.N = bundle.getBundle("mMetaDataBundle");
            this.O = (SongbookEntry) bundle.getParcelable("mEntry");
            this.P = bundle.getString("mCompressedFilename");
        }
    }

    protected void mo6492A() {
        UiThreadExecutor.a("", new C39121(this), 0);
    }

    protected void mo6493B() {
        UiThreadExecutor.a("", new C39132(this), 0);
    }

    protected void mo6494C() {
        UiThreadExecutor.a("", new C39143(this), 0);
    }

    protected void mo6495D() {
        UiThreadExecutor.a("", new C39154(this), 0);
    }

    protected void mo6496E() {
        UiThreadExecutor.a("", new C39165(this), 0);
    }

    protected void mo6497F() {
        UiThreadExecutor.a("", new C39176(this), 0);
    }

    protected void mo6498G() {
        UiThreadExecutor.a("", new C39187(this), 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
