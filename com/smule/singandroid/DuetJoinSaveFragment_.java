package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class DuetJoinSaveFragment_ extends DuetJoinSaveFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18607Y = new OnViewChangedNotifier();
    private View f18608Z;

    class C37981 implements OnClickListener {
        final /* synthetic */ DuetJoinSaveFragment_ f18596a;

        C37981(DuetJoinSaveFragment_ duetJoinSaveFragment_) {
            this.f18596a = duetJoinSaveFragment_;
        }

        public void onClick(View view) {
            this.f18596a.m19988z();
        }
    }

    class C37992 implements OnClickListener {
        final /* synthetic */ DuetJoinSaveFragment_ f18597a;

        C37992(DuetJoinSaveFragment_ duetJoinSaveFragment_) {
            this.f18597a = duetJoinSaveFragment_;
        }

        public void onClick(View view) {
            this.f18597a.m19967D();
        }
    }

    class C38003 implements OnClickListener {
        final /* synthetic */ DuetJoinSaveFragment_ f18598a;

        C38003(DuetJoinSaveFragment_ duetJoinSaveFragment_) {
            this.f18598a = duetJoinSaveFragment_;
        }

        public void onClick(View view) {
            this.f18598a.m19970G();
        }
    }

    class C38025 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment_ f18601a;

        C38025(DuetJoinSaveFragment_ duetJoinSaveFragment_) {
            this.f18601a = duetJoinSaveFragment_;
        }

        public void run() {
            super.mo6401E();
        }
    }

    class C38047 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment_ f18604a;

        C38047(DuetJoinSaveFragment_ duetJoinSaveFragment_) {
            this.f18604a = duetJoinSaveFragment_;
        }

        public void run() {
            super.mo6402I();
        }
    }

    class C38058 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment_ f18605a;

        C38058(DuetJoinSaveFragment_ duetJoinSaveFragment_) {
            this.f18605a = duetJoinSaveFragment_;
        }

        public void run() {
            super.mo6403J();
        }
    }

    class C38069 implements Runnable {
        final /* synthetic */ DuetJoinSaveFragment_ f18606a;

        C38069(DuetJoinSaveFragment_ duetJoinSaveFragment_) {
            this.f18606a = duetJoinSaveFragment_;
        }

        public void run() {
            super.mo6404K();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, DuetJoinSaveFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18607Y);
        m19989a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18608Z == null) {
            return null;
        }
        return this.f18608Z.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18608Z = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18608Z == null) {
            this.f18608Z = layoutInflater.inflate(C1947R.layout.duet_join_save_fragment, viewGroup, false);
        }
        return this.f18608Z;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18608Z = null;
        this.f = null;
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
        this.q = null;
        this.r = null;
        this.s = null;
    }

    private void m19989a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m19994b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18607Y.a(this);
    }

    public void m20005a(HasViews hasViews) {
        this.f = (TextView) hasViews.findViewById(C1947R.id.state_title_text);
        this.g = (ProgressBar) hasViews.findViewById(C1947R.id.progress_bar);
        this.h = (TextView) hasViews.findViewById(C1947R.id.title_text_view);
        this.i = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.left_profile_image_view);
        this.j = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.right_profile_image_view);
        this.k = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.middle_profile_image_view);
        this.l = hasViews.findViewById(C1947R.id.portraits_container);
        this.m = (TextView) hasViews.findViewById(C1947R.id.detail_text_view);
        this.n = hasViews.findViewById(C1947R.id.button_and_detail_layout);
        this.o = (Button) hasViews.findViewById(C1947R.id.next_button);
        this.p = (Button) hasViews.findViewById(C1947R.id.follow_button);
        this.q = (TextureView) hasViews.findViewById(C1947R.id.video_thumbnail);
        this.r = (Button) hasViews.findViewById(C1947R.id.skip_button);
        this.s = hasViews.findViewById(C1947R.id.background);
        if (this.p != null) {
            this.p.setOnClickListener(new C37981(this));
        }
        if (this.o != null) {
            this.o.setOnClickListener(new C37992(this));
        }
        if (this.r != null) {
            this.r.setOnClickListener(new C38003(this));
        }
        m19969F();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mPostSingBundle", this.t);
        bundle.putString("mPerformanceKey", this.v);
        bundle.putBoolean("mPerformanceSuccessfullyCreated", this.w);
        bundle.putBoolean("mResourceReady", this.x);
        bundle.putSerializable("mUploadedTrackResourceId", this.y);
        bundle.putBoolean("mPerformanceIsPrivate", this.z);
        bundle.putBoolean("mUsedHeadphone", this.A);
        bundle.putBoolean("mHeadphonesHadMic", this.B);
        bundle.putParcelable("mPerformance", this.C);
        bundle.putString("mVocalEffectName", this.D);
        bundle.putString("mInitialVocalEffectName", this.E);
        bundle.putString("mFinalSelectedVocalEffectSNPId", this.F);
        bundle.putString("mUniqueSelectedFXsReview", this.G);
        bundle.putString("mAdjustedSlider", this.H);
        bundle.putString("mPlayPauseCount", this.I);
        bundle.putSerializable("mMetaParam1", this.J);
        bundle.putSerializable("mMetaParam2", this.K);
        bundle.putBoolean("mVocalEffectVIPOnly", this.L);
        bundle.putString("mRecordingFile", this.M);
        bundle.putInt("mScore", this.N);
        bundle.putFloat("mGain", this.O);
        bundle.putParcelable("mSong", this.P);
        bundle.putBoolean("mIsFollowingPartnerAlready", this.Q);
        bundle.putBoolean("mHasPressedFollowButton", this.R);
        bundle.putString("mVideoFile", this.S);
        bundle.putBundle("mMetaDataBundle", this.T);
        bundle.putBoolean("mIsVideo", this.U);
        bundle.putFloat("mJoinMaxPowerPositionSeconds", this.V);
        bundle.putString("mCompressedFilename", this.W);
    }

    private void m19994b(Bundle bundle) {
        if (bundle != null) {
            this.t = (PostSingBundle) bundle.getParcelable("mPostSingBundle");
            this.v = bundle.getString("mPerformanceKey");
            this.w = bundle.getBoolean("mPerformanceSuccessfullyCreated");
            this.x = bundle.getBoolean("mResourceReady");
            this.y = (Long) bundle.getSerializable("mUploadedTrackResourceId");
            this.z = bundle.getBoolean("mPerformanceIsPrivate");
            this.A = bundle.getBoolean("mUsedHeadphone");
            this.B = bundle.getBoolean("mHeadphonesHadMic");
            this.C = (PerformanceV2) bundle.getParcelable("mPerformance");
            this.D = bundle.getString("mVocalEffectName");
            this.E = bundle.getString("mInitialVocalEffectName");
            this.F = bundle.getString("mFinalSelectedVocalEffectSNPId");
            this.G = bundle.getString("mUniqueSelectedFXsReview");
            this.H = bundle.getString("mAdjustedSlider");
            this.I = bundle.getString("mPlayPauseCount");
            this.J = (Float) bundle.getSerializable("mMetaParam1");
            this.K = (Float) bundle.getSerializable("mMetaParam2");
            this.L = bundle.getBoolean("mVocalEffectVIPOnly");
            this.M = bundle.getString("mRecordingFile");
            this.N = bundle.getInt("mScore");
            this.O = bundle.getFloat("mGain");
            this.P = (SongV2) bundle.getParcelable("mSong");
            this.Q = bundle.getBoolean("mIsFollowingPartnerAlready");
            this.R = bundle.getBoolean("mHasPressedFollowButton");
            this.S = bundle.getString("mVideoFile");
            this.T = bundle.getBundle("mMetaDataBundle");
            this.U = bundle.getBoolean("mIsVideo");
            this.V = bundle.getFloat("mJoinMaxPowerPositionSeconds");
            this.W = bundle.getString("mCompressedFilename");
        }
    }

    protected void mo6407b(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ DuetJoinSaveFragment_ f18600b;

            public void run() {
                super.mo6407b(z);
            }
        }, 0);
    }

    protected void mo6401E() {
        UiThreadExecutor.a("", new C38025(this), 0);
    }

    protected void mo6408d(final int i) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ DuetJoinSaveFragment_ f18603b;

            public void run() {
                super.mo6408d(i);
            }
        }, 0);
    }

    protected void mo6402I() {
        UiThreadExecutor.a("", new C38047(this), 0);
    }

    protected void mo6403J() {
        UiThreadExecutor.a("", new C38058(this), 0);
    }

    protected void mo6404K() {
        UiThreadExecutor.a("", new C38069(this), 0);
    }

    protected void mo6405L() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ DuetJoinSaveFragment_ f18595a;

            {
                this.f18595a = r1;
            }

            public void run() {
                super.mo6405L();
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
