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
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreSingDuetPartSelectFragment_ extends PreSingDuetPartSelectFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f23666w = new OnViewChangedNotifier();
    private View f23667x;

    class C47611 implements OnClickListener {
        final /* synthetic */ PreSingDuetPartSelectFragment_ f23661a;

        C47611(PreSingDuetPartSelectFragment_ preSingDuetPartSelectFragment_) {
            this.f23661a = preSingDuetPartSelectFragment_;
        }

        public void onClick(View view) {
            this.f23661a.m24830F();
        }
    }

    class C47622 implements OnClickListener {
        final /* synthetic */ PreSingDuetPartSelectFragment_ f23662a;

        C47622(PreSingDuetPartSelectFragment_ preSingDuetPartSelectFragment_) {
            this.f23662a = preSingDuetPartSelectFragment_;
        }

        public void onClick(View view) {
            this.f23662a.m24830F();
        }
    }

    class C47633 implements OnClickListener {
        final /* synthetic */ PreSingDuetPartSelectFragment_ f23663a;

        C47633(PreSingDuetPartSelectFragment_ preSingDuetPartSelectFragment_) {
            this.f23663a = preSingDuetPartSelectFragment_;
        }

        public void onClick(View view) {
            this.f23663a.mo6897a();
        }
    }

    class C47644 implements OnClickListener {
        final /* synthetic */ PreSingDuetPartSelectFragment_ f23664a;

        C47644(PreSingDuetPartSelectFragment_ preSingDuetPartSelectFragment_) {
            this.f23664a = preSingDuetPartSelectFragment_;
        }

        public void onClick(View view) {
            this.f23664a.m24899R();
        }
    }

    class C47655 implements OnClickListener {
        final /* synthetic */ PreSingDuetPartSelectFragment_ f23665a;

        C47655(PreSingDuetPartSelectFragment_ preSingDuetPartSelectFragment_) {
            this.f23665a = preSingDuetPartSelectFragment_;
        }

        public void onClick(View view) {
            this.f23665a.m24900S();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreSingDuetPartSelectFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23666w);
        m24905a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f23667x == null) {
            return null;
        }
        return this.f23667x.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f23667x = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f23667x == null) {
            this.f23667x = layoutInflater.inflate(C1947R.layout.pre_sing_duet_part_select_fragment, viewGroup, false);
        }
        return this.f23667x;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f23667x = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.v = null;
    }

    private void m24905a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24908b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f23666w.a(this);
    }

    public void m24919a(HasViews hasViews) {
        this.e = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.join_area);
        this.g = (TextView) hasViews.findViewById(C1947R.id.or_divider_text);
        this.v = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.duet_profile_own_lyrics);
        View findViewById = hasViews.findViewById(C1947R.id.vip_join_area);
        View findViewById2 = hasViews.findViewById(C1947R.id.duet_part_one_button);
        View findViewById3 = hasViews.findViewById(C1947R.id.duet_part_two_button);
        View findViewById4 = hasViews.findViewById(C1947R.id.duet_custom_button);
        if (this.f != null) {
            this.f.setOnClickListener(new C47611(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C47622(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C47633(this));
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new C47644(this));
        }
        if (findViewById4 != null) {
            findViewById4.setOnClickListener(new C47655(this));
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

    private void m24908b(Bundle bundle) {
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
