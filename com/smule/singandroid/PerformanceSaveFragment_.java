package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.PerformanceSaveFragment.Mode;
import com.smule.singandroid.customviews.BubbleTooltipViewWithDropShadow;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PerformanceSaveFragment_ extends PerformanceSaveFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier as = new OnViewChangedNotifier();
    private View at;

    class C39371 implements OnClickListener {
        final /* synthetic */ PerformanceSaveFragment_ f19242a;

        C39371(PerformanceSaveFragment_ performanceSaveFragment_) {
            this.f19242a = performanceSaveFragment_;
        }

        public void onClick(View view) {
            this.f19242a.mo6514z();
        }
    }

    class C39382 implements OnClickListener {
        final /* synthetic */ PerformanceSaveFragment_ f19243a;

        C39382(PerformanceSaveFragment_ performanceSaveFragment_) {
            this.f19243a = performanceSaveFragment_;
        }

        public void onClick(View view) {
            this.f19243a.m20743A();
        }
    }

    class C39393 implements OnClickListener {
        final /* synthetic */ PerformanceSaveFragment_ f19244a;

        C39393(PerformanceSaveFragment_ performanceSaveFragment_) {
            this.f19244a = performanceSaveFragment_;
        }

        public void onClick(View view) {
            this.f19244a.m20744B();
        }
    }

    class C39404 implements OnClickListener {
        final /* synthetic */ PerformanceSaveFragment_ f19245a;

        C39404(PerformanceSaveFragment_ performanceSaveFragment_) {
            this.f19245a = performanceSaveFragment_;
        }

        public void onClick(View view) {
            this.f19245a.m20745C();
        }
    }

    class C39415 implements OnCheckedChangeListener {
        final /* synthetic */ PerformanceSaveFragment_ f19246a;

        C39415(PerformanceSaveFragment_ performanceSaveFragment_) {
            this.f19246a = performanceSaveFragment_;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f19246a.m20766a(compoundButton, z);
        }
    }

    class C39426 implements OnCheckedChangeListener {
        final /* synthetic */ PerformanceSaveFragment_ f19247a;

        C39426(PerformanceSaveFragment_ performanceSaveFragment_) {
            this.f19247a = performanceSaveFragment_;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f19247a.m20771b(compoundButton, z);
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PerformanceSaveFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.as);
        m20781a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.at == null) {
            return null;
        }
        return this.at.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.at = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.at == null) {
            this.at = layoutInflater.inflate(C1947R.layout.performance_save_fragment, viewGroup, false);
        }
        return this.at;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.at = null;
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
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
    }

    private void m20781a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20788b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.as.a(this);
    }

    public void m20802a(HasViews hasViews) {
        this.f = (Button) hasViews.findViewById(C1947R.id.save_button);
        this.g = (ImageView) hasViews.findViewById(C1947R.id.close_button);
        this.h = (TextView) hasViews.findViewById(C1947R.id.nav_title);
        this.i = (ViewGroup) hasViews.findViewById(C1947R.id.title_container);
        this.j = (EditText) hasViews.findViewById(C1947R.id.title_edit_text);
        this.k = (ImageView) hasViews.findViewById(C1947R.id.album_art_image);
        this.l = (ImageView) hasViews.findViewById(C1947R.id.change_album_art_icon);
        this.m = (EditText) hasViews.findViewById(C1947R.id.performance_comment_edit_text);
        this.n = (ViewGroup) hasViews.findViewById(C1947R.id.privacy_switch_container);
        this.o = (TextView) hasViews.findViewById(C1947R.id.privacy_status);
        this.p = (TextView) hasViews.findViewById(C1947R.id.privacy_description);
        this.q = (ToggleButton) hasViews.findViewById(C1947R.id.privacy_switch);
        this.r = (ViewGroup) hasViews.findViewById(C1947R.id.invite_followers_container);
        this.s = (ViewGroup) hasViews.findViewById(C1947R.id.follower_invite_continer_inner);
        this.t = (ToggleButton) hasViews.findViewById(C1947R.id.follower_invite_switch);
        this.u = (TextView) hasViews.findViewById(C1947R.id.follower_invite_description);
        this.v = hasViews.findViewById(C1947R.id.delete_close_button_bar);
        this.w = (TextView) hasViews.findViewById(C1947R.id.delete_close_info);
        this.x = (TextView) hasViews.findViewById(C1947R.id.delete_close_button);
        this.y = (BubbleTooltipViewWithDropShadow) hasViews.findViewById(C1947R.id.tooltip_private);
        this.z = (BubbleTooltipViewWithDropShadow) hasViews.findViewById(C1947R.id.tooltip_invite);
        if (this.f != null) {
            this.f.setOnClickListener(new C39371(this));
        }
        if (this.n != null) {
            this.n.setOnClickListener(new C39382(this));
        }
        if (this.r != null) {
            this.r.setOnClickListener(new C39393(this));
        }
        if (this.g != null) {
            this.g.setOnClickListener(new C39404(this));
        }
        if (this.q != null) {
            this.q.setOnCheckedChangeListener(new C39415(this));
        }
        if (this.t != null) {
            this.t.setOnCheckedChangeListener(new C39426(this));
        }
        mo6510a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mPostSingBundle", this.A);
        bundle.putString("mPerformanceKey", this.B);
        bundle.putBoolean("mHasShownRateUsDialog", this.C);
        bundle.putBoolean("mPerformanceSuccessfullyCreated", this.D);
        bundle.putString("mPerformanceAlbumArtUrl", this.E);
        bundle.putBoolean("mResourceReady", this.F);
        bundle.putSerializable("mUploadedTrackResourceId", this.G);
        bundle.putString("mAlbumArtFilePath", this.H);
        bundle.putBoolean("mPerformanceIsPrivate", this.I);
        bundle.putBoolean("mUsedHeadphone", this.J);
        bundle.putBoolean("mHeadphonesHadMic", this.K);
        bundle.putParcelable("mPerformance", this.L);
        bundle.putBoolean("mDidChangeAlbumArt", this.M);
        bundle.putString("mVocalEffectName", this.N);
        bundle.putString("mInitialVocalEffectName", this.O);
        bundle.putString("mFinalSelectedVocalEffectSNPId", this.P);
        bundle.putString("mUniqueSelectedFXReview", this.Q);
        bundle.putString("mAdjustedSlider", this.R);
        bundle.putString("mPlayPauseCount", this.S);
        bundle.putSerializable("mMetaParam1", this.T);
        bundle.putSerializable("mMetaParam2", this.U);
        bundle.putBoolean("mVocalEffectVIPOnly", this.V);
        bundle.putString("mRecordingFile", this.W);
        bundle.putBoolean("mPitchCorrectEnabled", this.X);
        bundle.putInt("mScore", this.Y);
        bundle.putFloat("mGain", this.Z);
        bundle.putSerializable("mCurrentMode", this.aa);
        bundle.putParcelable("mEntry", this.ab);
        bundle.putBoolean("mIsOpenCallPrivateWhenBeginningEdit", this.ac);
        bundle.putBoolean("mIsJoin", this.ad);
        bundle.putBundle("mMetadataBundle", this.ae);
        bundle.putBoolean("mInvitedFollowers", this.af);
        bundle.putBoolean("mPerformanceCustomizeSaveClickEventLogged", this.ag);
        bundle.putBoolean("mIsCollab", this.ah);
        bundle.putString("mCompressedFilename", this.ai);
    }

    private void m20788b(Bundle bundle) {
        if (bundle != null) {
            this.A = (PostSingBundle) bundle.getParcelable("mPostSingBundle");
            this.B = bundle.getString("mPerformanceKey");
            this.C = bundle.getBoolean("mHasShownRateUsDialog");
            this.D = bundle.getBoolean("mPerformanceSuccessfullyCreated");
            this.E = bundle.getString("mPerformanceAlbumArtUrl");
            this.F = bundle.getBoolean("mResourceReady");
            this.G = (Long) bundle.getSerializable("mUploadedTrackResourceId");
            this.H = bundle.getString("mAlbumArtFilePath");
            this.I = bundle.getBoolean("mPerformanceIsPrivate");
            this.J = bundle.getBoolean("mUsedHeadphone");
            this.K = bundle.getBoolean("mHeadphonesHadMic");
            this.L = (PerformanceV2) bundle.getParcelable("mPerformance");
            this.M = bundle.getBoolean("mDidChangeAlbumArt");
            this.N = bundle.getString("mVocalEffectName");
            this.O = bundle.getString("mInitialVocalEffectName");
            this.P = bundle.getString("mFinalSelectedVocalEffectSNPId");
            this.Q = bundle.getString("mUniqueSelectedFXReview");
            this.R = bundle.getString("mAdjustedSlider");
            this.S = bundle.getString("mPlayPauseCount");
            this.T = (Float) bundle.getSerializable("mMetaParam1");
            this.U = (Float) bundle.getSerializable("mMetaParam2");
            this.V = bundle.getBoolean("mVocalEffectVIPOnly");
            this.W = bundle.getString("mRecordingFile");
            this.X = bundle.getBoolean("mPitchCorrectEnabled");
            this.Y = bundle.getInt("mScore");
            this.Z = bundle.getFloat("mGain");
            this.aa = (Mode) bundle.getSerializable("mCurrentMode");
            this.ab = (SongbookEntry) bundle.getParcelable("mEntry");
            this.ac = bundle.getBoolean("mIsOpenCallPrivateWhenBeginningEdit");
            this.ad = bundle.getBoolean("mIsJoin");
            this.ae = bundle.getBundle("mMetadataBundle");
            this.af = bundle.getBoolean("mInvitedFollowers");
            this.ag = bundle.getBoolean("mPerformanceCustomizeSaveClickEventLogged");
            this.ah = bundle.getBoolean("mIsCollab");
            this.ai = bundle.getString("mCompressedFilename");
        }
    }

    protected void mo6522c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19249b;

            public void run() {
                super.mo6522c(networkResponse);
            }
        }, 0);
    }

    protected void mo6521a(final int i, final PerformanceV2 performanceV2) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19252c;

            public void run() {
                super.mo6521a(i, performanceV2);
            }
        }, 0);
    }

    protected void mo6523c(final String str) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19254b;

            public void run() {
                super.mo6523c(str);
            }
        }, 0);
    }

    protected void mo6515H() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19235a;

            {
                this.f19235a = r1;
            }

            public void run() {
                super.mo6515H();
            }
        }, 0);
    }

    protected void mo6517K() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19236a;

            {
                this.f19236a = r1;
            }

            public void run() {
                super.mo6517K();
            }
        }, 0);
    }

    protected void mo6518L() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19237a;

            {
                this.f19237a = r1;
            }

            public void run() {
                super.mo6518L();
            }
        }, 0);
    }

    protected void mo6519M() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19238a;

            {
                this.f19238a = r1;
            }

            public void run() {
                super.mo6519M();
            }
        }, 0);
    }

    protected void mo6520N() {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19239a;

            {
                this.f19239a = r1;
            }

            public void run() {
                super.mo6520N();
            }
        }, 0);
    }

    protected void mo6524d(final Runnable runnable) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PerformanceSaveFragment_ f19241b;

            public void run() {
                super.mo6524d(runnable);
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6516J() {
        BackgroundExecutor.a();
        super.mo6516J();
    }
}
