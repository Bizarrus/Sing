package com.smule.singandroid.pre_sing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreSingDownloadFragment_ extends PreSingDownloadFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f23656H = new OnViewChangedNotifier();
    private View f23657I;

    class C47571 implements OnClickListener {
        final /* synthetic */ PreSingDownloadFragment_ f23651a;

        C47571(PreSingDownloadFragment_ preSingDownloadFragment_) {
            this.f23651a = preSingDownloadFragment_;
        }

        public void onClick(View view) {
            this.f23651a.m24830F();
        }
    }

    class C47582 implements OnClickListener {
        final /* synthetic */ PreSingDownloadFragment_ f23652a;

        C47582(PreSingDownloadFragment_ preSingDownloadFragment_) {
            this.f23652a = preSingDownloadFragment_;
        }

        public void onClick(View view) {
            this.f23652a.m24830F();
        }
    }

    class C47593 implements OnClickListener {
        final /* synthetic */ PreSingDownloadFragment_ f23653a;

        C47593(PreSingDownloadFragment_ preSingDownloadFragment_) {
            this.f23653a = preSingDownloadFragment_;
        }

        public void onClick(View view) {
            this.f23653a.m24870S();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreSingDownloadFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23656H);
        m24881a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f23657I == null) {
            return null;
        }
        return this.f23657I.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f23657I = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f23657I == null) {
            this.f23657I = layoutInflater.inflate(C1947R.layout.pre_sing_download_fragment, viewGroup, false);
        }
        return this.f23657I;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f23657I = null;
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
    }

    private void m24881a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24885b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f23656H.a(this);
    }

    public void m24897a(HasViews hasViews) {
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
        View findViewById = hasViews.findViewById(C1947R.id.vip_join_area);
        View findViewById2 = hasViews.findViewById(C1947R.id.app_bar_back_button);
        if (this.f != null) {
            this.f.setOnClickListener(new C47571(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C47582(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C47593(this));
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
    }

    private void m24885b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getString("mSectionId");
            this.i = (SingBundle) bundle.getParcelable("mSingBundle");
            this.j = (PerformanceV2) bundle.getParcelable("mOpenCall");
            this.k = (SongbookEntry) bundle.getParcelable("mEntry");
            this.l = bundle.getBoolean("mHasOpenCalls");
        }
    }

    protected void mo6903a(final CustomAlertDialogListener customAlertDialogListener) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ PreSingDownloadFragment_ f23655b;

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
