package com.smule.singandroid.upsell;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.SubscriptionPurchaseView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SubscriptionPurchaseFragment_ extends SubscriptionPurchaseFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f24607n = new OnViewChangedNotifier();
    private View f24608o;

    class C49811 implements OnClickListener {
        final /* synthetic */ SubscriptionPurchaseFragment_ f24606a;

        C49811(SubscriptionPurchaseFragment_ subscriptionPurchaseFragment_) {
            this.f24606a = subscriptionPurchaseFragment_;
        }

        public void onClick(View view) {
            this.f24606a.m25762a();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, SubscriptionPurchaseFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24607n);
        m25766a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f24608o == null) {
            return null;
        }
        return this.f24608o.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f24608o = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f24608o == null) {
            this.f24608o = layoutInflater.inflate(C1947R.layout.subscription_purchase_fragment, viewGroup, false);
        }
        return this.f24608o;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f24608o = null;
        this.e = null;
    }

    private void m25766a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25768b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f24607n.a(this);
    }

    public void m25770a(HasViews hasViews) {
        this.e = (SubscriptionPurchaseView) hasViews.findViewById(C1947R.id.new_subscription_view);
        View findViewById = hasViews.findViewById(C1947R.id.top_back_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C49811(this));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mNewSectionUID", this.f);
        bundle.putBoolean("mFromSubscriptionOnlySong", this.g);
        bundle.putParcelable("mEntry", this.h);
        bundle.putParcelable("mSong", this.i);
        bundle.putParcelable("mPerf", this.j);
        bundle.putString("mReferrerSectionId", this.k);
        bundle.putSerializable("mPromoId", this.l);
        bundle.putString("mSubToAutoClick", this.m);
    }

    private void m25768b(Bundle bundle) {
        if (bundle != null) {
            this.f = bundle.getString("mNewSectionUID");
            this.g = bundle.getBoolean("mFromSubscriptionOnlySong");
            this.h = (SongbookEntry) bundle.getParcelable("mEntry");
            this.i = (SongV2) bundle.getParcelable("mSong");
            this.j = (PerformanceV2) bundle.getParcelable("mPerf");
            this.k = bundle.getString("mReferrerSectionId");
            this.l = (Long) bundle.getSerializable("mPromoId");
            this.m = bundle.getString("mSubToAutoClick");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
