package com.smule.singandroid.upsell;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UpsellFragment_ extends UpsellFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f24628q = new OnViewChangedNotifier();
    private View f24629r;

    class C49851 implements OnClickListener {
        final /* synthetic */ UpsellFragment_ f24626a;

        C49851(UpsellFragment_ upsellFragment_) {
            this.f24626a = upsellFragment_;
        }

        public void onClick(View view) {
            this.f24626a.m25784z();
        }
    }

    class C49862 implements OnClickListener {
        final /* synthetic */ UpsellFragment_ f24627a;

        C49862(UpsellFragment_ upsellFragment_) {
            this.f24627a = upsellFragment_;
        }

        public void onClick(View view) {
            this.f24627a.m25779A();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, UpsellFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24628q);
        m25785a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f24629r == null) {
            return null;
        }
        return this.f24629r.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f24629r = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f24629r == null) {
            this.f24629r = layoutInflater.inflate(C1947R.layout.upsell_fragment, viewGroup, false);
        }
        return this.f24629r;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f24629r = null;
        this.e = null;
        this.f = null;
        this.g = null;
    }

    private void m25785a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25787b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f24628q.a(this);
    }

    public void m25789a(HasViews hasViews) {
        this.e = (LinearLayout) hasViews.findViewById(C1947R.id.root);
        this.f = (SKUSelectionView) hasViews.findViewById(C1947R.id.sku_selection_view);
        this.g = (TextView) hasViews.findViewById(C1947R.id.upsell_learn_more);
        View findViewById = hasViews.findViewById(C1947R.id.top_back_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C49851(this));
        }
        if (this.g != null) {
            this.g.setOnClickListener(new C49862(this));
        }
        m25780a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mNewSectionUID", this.h);
        bundle.putBoolean("mFromSubscriptionOnlySong", this.i);
        bundle.putParcelable("mEntry", this.j);
        bundle.putParcelable("mSong", this.k);
        bundle.putParcelable("mPerf", this.l);
        bundle.putString("mReferrerSectionId", this.m);
        bundle.putSerializable("mPromoId", this.n);
        bundle.putString("mSubToAutoClick", this.o);
        bundle.putSerializable("mUpsellType", this.p);
    }

    private void m25787b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getString("mNewSectionUID");
            this.i = bundle.getBoolean("mFromSubscriptionOnlySong");
            this.j = (SongbookEntry) bundle.getParcelable("mEntry");
            this.k = (SongV2) bundle.getParcelable("mSong");
            this.l = (PerformanceV2) bundle.getParcelable("mPerf");
            this.m = bundle.getString("mReferrerSectionId");
            this.n = (Long) bundle.getSerializable("mPromoId");
            this.o = bundle.getString("mSubToAutoClick");
            this.p = (UpsellType) bundle.getSerializable("mUpsellType");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
