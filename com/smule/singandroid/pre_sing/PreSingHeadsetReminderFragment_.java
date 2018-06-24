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
import com.smule.singandroid.customviews.CheckmarkAnimation;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreSingHeadsetReminderFragment_ extends PreSingHeadsetReminderFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f23684B = new OnViewChangedNotifier();
    private View f23685C;

    class C47671 implements OnClickListener {
        final /* synthetic */ PreSingHeadsetReminderFragment_ f23681a;

        C47671(PreSingHeadsetReminderFragment_ preSingHeadsetReminderFragment_) {
            this.f23681a = preSingHeadsetReminderFragment_;
        }

        public void onClick(View view) {
            this.f23681a.m24830F();
        }
    }

    class C47682 implements OnClickListener {
        final /* synthetic */ PreSingHeadsetReminderFragment_ f23682a;

        C47682(PreSingHeadsetReminderFragment_ preSingHeadsetReminderFragment_) {
            this.f23682a = preSingHeadsetReminderFragment_;
        }

        public void onClick(View view) {
            this.f23682a.m24830F();
        }
    }

    class C47693 implements OnClickListener {
        final /* synthetic */ PreSingHeadsetReminderFragment_ f23683a;

        C47693(PreSingHeadsetReminderFragment_ preSingHeadsetReminderFragment_) {
            this.f23683a = preSingHeadsetReminderFragment_;
        }

        public void onClick(View view) {
            this.f23683a.m24923R();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, PreSingHeadsetReminderFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23684B);
        m24928a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f23685C == null) {
            return null;
        }
        return this.f23685C.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f23685C = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f23685C == null) {
            this.f23685C = layoutInflater.inflate(C1947R.layout.pre_sing_headset_reminder_fragment, viewGroup, false);
        }
        return this.f23685C;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f23685C = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
    }

    private void m24928a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24931b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f23684B.a(this);
    }

    public void m24942a(HasViews hasViews) {
        this.e = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.join_area);
        this.g = (TextView) hasViews.findViewById(C1947R.id.or_divider_text);
        this.t = (ImageView) hasViews.findViewById(C1947R.id.header_state_image);
        this.u = (TextView) hasViews.findViewById(C1947R.id.reminder_title);
        this.v = (TextView) hasViews.findViewById(C1947R.id.reminder_description);
        this.w = (CheckmarkAnimation) hasViews.findViewById(C1947R.id.headphones_checkmark);
        this.x = (Button) hasViews.findViewById(C1947R.id.next_button);
        View findViewById = hasViews.findViewById(C1947R.id.vip_join_area);
        if (this.f != null) {
            this.f.setOnClickListener(new C47671(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C47682(this));
        }
        if (this.x != null) {
            this.x.setOnClickListener(new C47693(this));
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

    private void m24931b(Bundle bundle) {
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
