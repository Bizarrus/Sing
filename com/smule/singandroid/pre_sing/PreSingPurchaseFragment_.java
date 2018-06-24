package com.smule.singandroid.pre_sing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.smule.singandroid.customviews.OrDivider;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreSingPurchaseFragment_ extends PreSingPurchaseFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f23751A = new OnViewChangedNotifier();
    private View f23752B;

    class C47911 implements OnClickListener {
        final /* synthetic */ PreSingPurchaseFragment_ f23748a;

        C47911(PreSingPurchaseFragment_ preSingPurchaseFragment_) {
            this.f23748a = preSingPurchaseFragment_;
        }

        public void onClick(View view) {
            this.f23748a.m24830F();
        }
    }

    class C47922 implements OnClickListener {
        final /* synthetic */ PreSingPurchaseFragment_ f23749a;

        C47922(PreSingPurchaseFragment_ preSingPurchaseFragment_) {
            this.f23749a = preSingPurchaseFragment_;
        }

        public void onClick(View view) {
            this.f23749a.m24830F();
        }
    }

    class C47933 implements OnClickListener {
        final /* synthetic */ PreSingPurchaseFragment_ f23750a;

        C47933(PreSingPurchaseFragment_ preSingPurchaseFragment_) {
            this.f23750a = preSingPurchaseFragment_;
        }

        public void onClick(View view) {
            this.f23750a.m25009U();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreSingPurchaseFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23751A);
        m25015a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f23752B == null) {
            return null;
        }
        return this.f23752B.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f23752B = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f23752B == null) {
            this.f23752B = layoutInflater.inflate(C1947R.layout.pre_sing_purchase_fragment, viewGroup, false);
        }
        return this.f23752B;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f23752B = null;
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
    }

    private void m25015a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25018b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f23751A.a(this);
    }

    public void m25033a(HasViews hasViews) {
        this.e = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.join_area);
        this.g = (TextView) hasViews.findViewById(C1947R.id.or_divider_text);
        this.t = (ImageView) hasViews.findViewById(C1947R.id.top_back_button);
        this.u = (LinearLayout) hasViews.findViewById(C1947R.id.root);
        this.v = (FrameLayout) hasViews.findViewById(C1947R.id.new_subscription_purchase_view_container);
        this.w = (FrameLayout) hasViews.findViewById(C1947R.id.watch_ad_layout_top);
        this.x = (FrameLayout) hasViews.findViewById(C1947R.id.watch_ad_layout_bottom);
        this.y = (OrDivider) hasViews.findViewById(C1947R.id.top_or_divider);
        this.z = (OrDivider) hasViews.findViewById(C1947R.id.bottom_or_divider);
        View findViewById = hasViews.findViewById(C1947R.id.vip_join_area);
        if (this.f != null) {
            this.f.setOnClickListener(new C47911(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C47922(this));
        }
        if (this.t != null) {
            this.t.setOnClickListener(new C47933(this));
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

    private void m25018b(Bundle bundle) {
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

    protected void mo6908S() {
        BackgroundExecutor.a();
        super.mo6908S();
    }

    protected void mo6909T() {
        BackgroundExecutor.a();
        super.mo6909T();
    }
}
