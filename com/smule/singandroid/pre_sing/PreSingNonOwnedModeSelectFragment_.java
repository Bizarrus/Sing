package com.smule.singandroid.pre_sing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class PreSingNonOwnedModeSelectFragment_ extends PreSingNonOwnedModeSelectFragment implements HasViews, OnViewChangedListener {
    private View f23726A;
    private final OnViewChangedNotifier f23727z = new OnViewChangedNotifier();

    class C47851 implements OnClickListener {
        final /* synthetic */ PreSingNonOwnedModeSelectFragment_ f23724a;

        C47851(PreSingNonOwnedModeSelectFragment_ preSingNonOwnedModeSelectFragment_) {
            this.f23724a = preSingNonOwnedModeSelectFragment_;
        }

        public void onClick(View view) {
            this.f23724a.m24830F();
        }
    }

    class C47862 implements OnClickListener {
        final /* synthetic */ PreSingNonOwnedModeSelectFragment_ f23725a;

        C47862(PreSingNonOwnedModeSelectFragment_ preSingNonOwnedModeSelectFragment_) {
            this.f23725a = preSingNonOwnedModeSelectFragment_;
        }

        public void onClick(View view) {
            this.f23725a.m24830F();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreSingNonOwnedModeSelectFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23727z);
        m24981a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f23726A == null) {
            return null;
        }
        return this.f23726A.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f23726A = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f23726A == null) {
            this.f23726A = layoutInflater.inflate(C1947R.layout.pre_sing_non_owned_mode_select_fragment, viewGroup, false);
        }
        return this.f23726A;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f23726A = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
    }

    private void m24981a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24984b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f23727z.a(this);
    }

    public void m24995a(HasViews hasViews) {
        this.e = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.join_area);
        this.g = (TextView) hasViews.findViewById(C1947R.id.or_divider_text);
        this.t = hasViews.findViewById(C1947R.id.pre_singing_solo_container);
        this.u = hasViews.findViewById(C1947R.id.pre_singing_duet_container);
        this.v = hasViews.findViewById(C1947R.id.pre_singing_group_container);
        this.w = (TextView) hasViews.findViewById(C1947R.id.open_calls_status);
        this.x = (TextView) hasViews.findViewById(C1947R.id.open_calls_status_ellipses);
        this.y = (TextView) hasViews.findViewById(C1947R.id.pre_singing_create);
        View findViewById = hasViews.findViewById(C1947R.id.vip_join_area);
        if (this.f != null) {
            this.f.setOnClickListener(new C47851(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C47862(this));
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

    private void m24984b(Bundle bundle) {
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
}
