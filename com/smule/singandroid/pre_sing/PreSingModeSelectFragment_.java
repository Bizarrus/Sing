package com.smule.singandroid.pre_sing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingBundle;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreSingModeSelectFragment_ extends PreSingModeSelectFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f23708E = new OnViewChangedNotifier();
    private View f23709F;

    class C47741 implements OnClickListener {
        final /* synthetic */ PreSingModeSelectFragment_ f23703a;

        C47741(PreSingModeSelectFragment_ preSingModeSelectFragment_) {
            this.f23703a = preSingModeSelectFragment_;
        }

        public void onClick(View view) {
            this.f23703a.m24830F();
        }
    }

    class C47752 implements OnClickListener {
        final /* synthetic */ PreSingModeSelectFragment_ f23704a;

        C47752(PreSingModeSelectFragment_ preSingModeSelectFragment_) {
            this.f23704a = preSingModeSelectFragment_;
        }

        public void onClick(View view) {
            this.f23704a.m24830F();
        }
    }

    class C47763 implements OnClickListener {
        final /* synthetic */ PreSingModeSelectFragment_ f23705a;

        C47763(PreSingModeSelectFragment_ preSingModeSelectFragment_) {
            this.f23705a = preSingModeSelectFragment_;
        }

        public void onClick(View view) {
            this.f23705a.mo6897a();
        }
    }

    class C47774 implements OnClickListener {
        final /* synthetic */ PreSingModeSelectFragment_ f23706a;

        C47774(PreSingModeSelectFragment_ preSingModeSelectFragment_) {
            this.f23706a = preSingModeSelectFragment_;
        }

        public void onClick(View view) {
            this.f23706a.m24948R();
        }
    }

    class C47785 implements OnClickListener {
        final /* synthetic */ PreSingModeSelectFragment_ f23707a;

        C47785(PreSingModeSelectFragment_ preSingModeSelectFragment_) {
            this.f23707a = preSingModeSelectFragment_;
        }

        public void onClick(View view) {
            this.f23707a.m24949S();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreSingModeSelectFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23708E);
        m24956a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f23709F == null) {
            return null;
        }
        return this.f23709F.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f23709F = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f23709F == null) {
            this.f23709F = layoutInflater.inflate(C1947R.layout.pre_sing_mode_select_fragment, viewGroup, false);
        }
        return this.f23709F;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f23709F = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
    }

    private void m24956a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24960b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f23708E.a(this);
    }

    public void m24971a(HasViews hasViews) {
        this.e = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.join_area);
        this.g = (TextView) hasViews.findViewById(C1947R.id.or_divider_text);
        this.t = (RelativeLayout) hasViews.findViewById(C1947R.id.vip_join_area);
        this.u = (TextView) hasViews.findViewById(C1947R.id.vip_join_title);
        this.v = (ImageView) hasViews.findViewById(C1947R.id.vip_join_image);
        this.w = (TextView) hasViews.findViewById(C1947R.id.vip_open_calls_label);
        this.x = (TextView) hasViews.findViewById(C1947R.id.vip_open_calls_status);
        this.y = (TextView) hasViews.findViewById(C1947R.id.vip_open_calls_status_ellipses);
        this.z = (TextView) hasViews.findViewById(C1947R.id.title_new_song);
        this.A = hasViews.findViewById(C1947R.id.solo_button);
        this.B = hasViews.findViewById(C1947R.id.duet_button);
        this.C = hasViews.findViewById(C1947R.id.group_button);
        this.D = (TextView) hasViews.findViewById(C1947R.id.vip_or_text);
        if (this.f != null) {
            this.f.setOnClickListener(new C47741(this));
        }
        if (this.t != null) {
            this.t.setOnClickListener(new C47752(this));
        }
        if (this.A != null) {
            this.A.setOnClickListener(new C47763(this));
        }
        if (this.B != null) {
            this.B.setOnClickListener(new C47774(this));
        }
        if (this.C != null) {
            this.C.setOnClickListener(new C47785(this));
        }
        mo6896E();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mSectionId", this.h);
        bundle.putParcelable("mSingBundle", this.i);
        bundle.putParcelable("mOpenCall", this.j);
        bundle.putParcelable("mEntry", this.k);
        bundle.putBoolean("mHasOpenCalls", this.l);
    }

    private void m24960b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getString("mSectionId");
            this.i = (SingBundle) bundle.getParcelable("mSingBundle");
            this.j = (PerformanceV2) bundle.getParcelable("mOpenCall");
            this.k = (SongbookEntry) bundle.getParcelable("mEntry");
            this.l = bundle.getBoolean("mHasOpenCalls");
        }
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

    protected void mo6905b(boolean z) {
        BackgroundExecutor.a();
        super.mo6905b(z);
    }
}
