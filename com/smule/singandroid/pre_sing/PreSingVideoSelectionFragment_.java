package com.smule.singandroid.pre_sing;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.VideoFXTabIndicator;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@TargetApi(19)
public final class PreSingVideoSelectionFragment_ extends PreSingVideoSelectionFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier ag = new OnViewChangedNotifier();
    private View ah;

    class C48071 implements OnClickListener {
        final /* synthetic */ PreSingVideoSelectionFragment_ f23798a;

        C48071(PreSingVideoSelectionFragment_ preSingVideoSelectionFragment_) {
            this.f23798a = preSingVideoSelectionFragment_;
        }

        public void onClick(View view) {
            this.f23798a.m24830F();
        }
    }

    class C48082 implements OnClickListener {
        final /* synthetic */ PreSingVideoSelectionFragment_ f23799a;

        C48082(PreSingVideoSelectionFragment_ preSingVideoSelectionFragment_) {
            this.f23799a = preSingVideoSelectionFragment_;
        }

        public void onClick(View view) {
            this.f23799a.m24830F();
        }
    }

    class C48093 implements OnClickListener {
        final /* synthetic */ PreSingVideoSelectionFragment_ f23800a;

        C48093(PreSingVideoSelectionFragment_ preSingVideoSelectionFragment_) {
            this.f23800a = preSingVideoSelectionFragment_;
        }

        public void onClick(View view) {
            this.f23800a.m24870S();
        }
    }

    class C48104 implements OnClickListener {
        final /* synthetic */ PreSingVideoSelectionFragment_ f23801a;

        C48104(PreSingVideoSelectionFragment_ preSingVideoSelectionFragment_) {
            this.f23801a = preSingVideoSelectionFragment_;
        }

        public void onClick(View view) {
            this.f23801a.mo6914Z();
        }
    }

    class C48115 implements OnClickListener {
        final /* synthetic */ PreSingVideoSelectionFragment_ f23802a;

        C48115(PreSingVideoSelectionFragment_ preSingVideoSelectionFragment_) {
            this.f23802a = preSingVideoSelectionFragment_;
        }

        public void onClick(View view) {
            this.f23802a.aa();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreSingVideoSelectionFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.ag);
        m25080a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.ah == null) {
            return null;
        }
        return this.ah.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ah = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.ah == null) {
            this.ah = layoutInflater.inflate(C1947R.layout.pre_sing_video_selection_fragment, viewGroup, false);
        }
        return this.ah;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.ah = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.aa = null;
    }

    private void m25080a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25084b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.ag.a(this);
    }

    public void m25096a(HasViews hasViews) {
        this.e = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.join_area);
        this.g = (TextView) hasViews.findViewById(C1947R.id.or_divider_text);
        this.u = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.loading_profile_left);
        this.v = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.loading_profile_right);
        this.w = (TextView) hasViews.findViewById(C1947R.id.preparing_top_text);
        this.x = (TextView) hasViews.findViewById(C1947R.id.preparing_bottom_text);
        this.y = (ViewPager) hasViews.findViewById(C1947R.id.sing_tips);
        this.z = hasViews.findViewById(C1947R.id.root_view);
        this.A = (LinearLayout) hasViews.findViewById(C1947R.id.download_app_bar);
        this.B = (TextView) hasViews.findViewById(C1947R.id.app_bar_title);
        this.H = (TextView) hasViews.findViewById(C1947R.id.psvs_song_title);
        this.I = (TextView) hasViews.findViewById(C1947R.id.psvs_song_artist);
        this.J = (Button) hasViews.findViewById(C1947R.id.psvs_start);
        this.K = (SwitchCompat) hasViews.findViewById(C1947R.id.psvs_video_switch);
        this.L = (LinearLayout) hasViews.findViewById(C1947R.id.psvs_video_switch_layout);
        this.M = (FrameLayout) hasViews.findViewById(C1947R.id.psvs_camera_switch_touchable_area);
        this.N = (FrameLayout) hasViews.findViewById(C1947R.id.psvs_airbrush_switch_touchable_area);
        this.O = (ImageView) hasViews.findViewById(C1947R.id.psvs_airbrush_switch);
        this.P = (RelativeLayout) hasViews.findViewById(C1947R.id.psvs_top_container);
        this.Q = (LinearLayout) hasViews.findViewById(C1947R.id.psvs_no_video_container);
        this.R = hasViews.findViewById(C1947R.id.psvs_title_artist_alpha);
        this.S = hasViews.findViewById(C1947R.id.psvs_bottom_container_alpha);
        this.T = (LinearLayout) hasViews.findViewById(C1947R.id.psvs_duet_overlay);
        this.U = (FrameLayout) hasViews.findViewById(C1947R.id.psvs_camera_preview_container);
        this.W = hasViews.findViewById(C1947R.id.psvs_video_fx_tooltip);
        this.X = (VideoFXTabIndicator) hasViews.findViewById(C1947R.id.psvs_video_fx_indicator);
        this.Y = (FrameLayout) hasViews.findViewById(C1947R.id.psvs_curtain_layout);
        this.Z = (TextView) hasViews.findViewById(C1947R.id.psvs_switch_label_off);
        this.aa = (TextView) hasViews.findViewById(C1947R.id.psvs_switch_label_on);
        View findViewById = hasViews.findViewById(C1947R.id.vip_join_area);
        View findViewById2 = hasViews.findViewById(C1947R.id.app_bar_back_button);
        View findViewById3 = hasViews.findViewById(C1947R.id.psvs_close_button);
        View findViewById4 = hasViews.findViewById(C1947R.id.psvs_touch_capture);
        if (this.f != null) {
            this.f.setOnClickListener(new C48071(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C48082(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C48093(this));
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new C48104(this));
        }
        if (findViewById4 != null) {
            findViewById4.setOnClickListener(new C48115(this));
        }
        mo6896E();
        m24869R();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mSectionId", this.h);
        bundle.putParcelable("mSingBundle", this.i);
        bundle.putParcelable("mOpenCall", this.j);
        bundle.putParcelable("mEntry", this.k);
        bundle.putBoolean("mHasOpenCalls", this.l);
        bundle.putBoolean("mFragmentAnimatingIn", this.af);
    }

    private void m25084b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getString("mSectionId");
            this.i = (SingBundle) bundle.getParcelable("mSingBundle");
            this.j = (PerformanceV2) bundle.getParcelable("mOpenCall");
            this.k = (SongbookEntry) bundle.getParcelable("mEntry");
            this.l = bundle.getBoolean("mHasOpenCalls");
            this.af = bundle.getBoolean("mFragmentAnimatingIn");
        }
    }

    protected void mo6903a(final CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PreSingVideoSelectionFragment_ f23804b;

            public void run() {
                super.mo6903a(customAlertDialogListener);
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6898H() {
        BackgroundExecutor.a();
        super.mo6898H();
    }

    protected void mo6899J() {
        BackgroundExecutor.a();
        super.mo6899J();
    }

    protected void mo6900K() {
        BackgroundExecutor.a();
        super.mo6900K();
    }

    protected void mo6901L() {
        BackgroundExecutor.a();
        super.mo6901L();
    }

    protected void mo6902M() {
        BackgroundExecutor.a();
        super.mo6902M();
    }
}
