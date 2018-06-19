package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SettingsFragment_ extends SettingsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19880P = new OnViewChangedNotifier();
    private View f19881Q;

    class C40781 implements OnClickListener {
        final /* synthetic */ SettingsFragment_ f19870a;

        C40781(SettingsFragment_ settingsFragment_) {
            this.f19870a = settingsFragment_;
        }

        public void onClick(View view) {
            this.f19870a.m21401C();
        }
    }

    class C40792 implements OnClickListener {
        final /* synthetic */ SettingsFragment_ f19871a;

        C40792(SettingsFragment_ settingsFragment_) {
            this.f19871a = settingsFragment_;
        }

        public void onClick(View view) {
            this.f19871a.m21402D();
        }
    }

    class C40803 implements OnClickListener {
        final /* synthetic */ SettingsFragment_ f19872a;

        C40803(SettingsFragment_ settingsFragment_) {
            this.f19872a = settingsFragment_;
        }

        public void onClick(View view) {
            this.f19872a.m21403E();
        }
    }

    class C40814 implements OnClickListener {
        final /* synthetic */ SettingsFragment_ f19873a;

        C40814(SettingsFragment_ settingsFragment_) {
            this.f19873a = settingsFragment_;
        }

        public void onClick(View view) {
            this.f19873a.m21404F();
        }
    }

    class C40825 implements OnClickListener {
        final /* synthetic */ SettingsFragment_ f19874a;

        C40825(SettingsFragment_ settingsFragment_) {
            this.f19874a = settingsFragment_;
        }

        public void onClick(View view) {
            this.f19874a.m21405G();
        }
    }

    class C40836 implements OnClickListener {
        final /* synthetic */ SettingsFragment_ f19875a;

        C40836(SettingsFragment_ settingsFragment_) {
            this.f19875a = settingsFragment_;
        }

        public void onClick(View view) {
            this.f19875a.m21406H();
        }
    }

    class C40847 implements OnClickListener {
        final /* synthetic */ SettingsFragment_ f19876a;

        C40847(SettingsFragment_ settingsFragment_) {
            this.f19876a = settingsFragment_;
        }

        public void onClick(View view) {
            this.f19876a.m21407I();
        }
    }

    class C40869 implements Runnable {
        final /* synthetic */ SettingsFragment_ f19879a;

        C40869(SettingsFragment_ settingsFragment_) {
            this.f19879a = settingsFragment_;
        }

        public void run() {
            super.mo6601J();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, SettingsFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19880P);
        m21416a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f19881Q == null) {
            return null;
        }
        return this.f19881Q.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f19881Q = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f19881Q == null) {
            this.f19881Q = layoutInflater.inflate(C1947R.layout.settings_fragment, viewGroup, false);
        }
        return this.f19881Q;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19881Q = null;
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
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
    }

    private void m21416a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f19880P.a(this);
    }

    public void m21427a(HasViews hasViews) {
        this.f = (LinearLayout) hasViews.findViewById(C1947R.id.root);
        this.g = (EditText) hasViews.findViewById(C1947R.id.username_field);
        this.h = (EditText) hasViews.findViewById(C1947R.id.email_field);
        this.i = (EditText) hasViews.findViewById(C1947R.id.password_field);
        this.j = (EditText) hasViews.findViewById(C1947R.id.password_confirmation_field);
        this.k = (EditText) hasViews.findViewById(C1947R.id.firstname_field);
        this.l = (EditText) hasViews.findViewById(C1947R.id.lastname_field);
        this.m = (TextView) hasViews.findViewById(C1947R.id.profile_save_button);
        this.n = (EditText) hasViews.findViewById(C1947R.id.user_blurb_field);
        this.o = (EditText) hasViews.findViewById(C1947R.id.password_field_dummy);
        this.p = (EditText) hasViews.findViewById(C1947R.id.password_confirmation_field_dummy);
        this.q = hasViews.findViewById(C1947R.id.username_checkmark);
        this.r = hasViews.findViewById(C1947R.id.email_checkmark);
        this.s = hasViews.findViewById(C1947R.id.password_checkmark);
        this.t = hasViews.findViewById(C1947R.id.password_confirmation_checkmark);
        this.u = hasViews.findViewById(C1947R.id.firstname_checkmark);
        this.v = hasViews.findViewById(C1947R.id.lastname_checkmark);
        this.w = (ToggleButton) hasViews.findViewById(C1947R.id.newsletter_switch);
        this.x = (TextView) hasViews.findViewById(C1947R.id.profile_newsletter_details);
        this.y = (TextView) hasViews.findViewById(C1947R.id.facebook_account_textview);
        this.z = (ToggleButton) hasViews.findViewById(C1947R.id.facebook_switch);
        this.A = (ProgressBar) hasViews.findViewById(C1947R.id.facebook_spinner);
        this.B = (ViewGroup) hasViews.findViewById(C1947R.id.twitter_container);
        this.C = (TextView) hasViews.findViewById(C1947R.id.twitter_account_textview);
        this.D = (ToggleButton) hasViews.findViewById(C1947R.id.twitter_switch);
        this.E = hasViews.findViewById(C1947R.id.chat_settings);
        this.F = (ToggleButton) hasViews.findViewById(C1947R.id.chat_notifications_switch);
        this.G = (ToggleButton) hasViews.findViewById(C1947R.id.chat_read_receipts_switch);
        this.H = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfileImageWithVIPBadge);
        this.I = hasViews.findViewById(C1947R.id.audio_settings_layout);
        this.J = (ToggleButton) hasViews.findViewById(C1947R.id.rtm_switch);
        this.K = (TextView) hasViews.findViewById(C1947R.id.info_header);
        this.L = (TextView) hasViews.findViewById(C1947R.id.build_info);
        this.M = hasViews.findViewById(C1947R.id.submit_focus_dummy);
        View findViewById = hasViews.findViewById(C1947R.id.chat_blocked_list);
        View findViewById2 = hasViews.findViewById(C1947R.id.profilePrivacyPolicy);
        View findViewById3 = hasViews.findViewById(C1947R.id.profileTermsOfService);
        View findViewById4 = hasViews.findViewById(C1947R.id.av_survey_report_technical_difficulty);
        if (this.z != null) {
            this.z.setOnClickListener(new C40781(this));
        }
        if (this.D != null) {
            this.D.setOnClickListener(new C40792(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C40803(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C40814(this));
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new C40825(this));
        }
        if (this.m != null) {
            this.m.setOnClickListener(new C40836(this));
        }
        if (findViewById4 != null) {
            findViewById4.setOnClickListener(new C40847(this));
        }
        mo6514z();
    }

    protected void mo6522c(final NetworkResponse networkResponse) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SettingsFragment_ f19878b;

            public void run() {
                super.mo6522c(networkResponse);
            }
        }, 0);
    }

    protected void mo6601J() {
        UiThreadExecutor.a("", new C40869(this), 0);
    }

    protected void mo6604a(boolean z, boolean z2, NetworkResponse networkResponse, boolean z3, int i, NetworkResponse networkResponse2, boolean z4, int i2) {
        final boolean z5 = z;
        final boolean z6 = z2;
        final NetworkResponse networkResponse3 = networkResponse;
        final boolean z7 = z3;
        final int i3 = i;
        final NetworkResponse networkResponse4 = networkResponse2;
        final boolean z8 = z4;
        final int i4 = i2;
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SettingsFragment_ f19865i;

            public void run() {
                super.mo6604a(z5, z6, networkResponse3, z7, i3, networkResponse4, z8, i4);
            }
        }, 0);
    }

    protected void mo6603a(final NetworkResponse networkResponse, final Boolean bool, final int i) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ SettingsFragment_ f19869d;

            public void run() {
                super.mo6603a(networkResponse, bool, i);
            }
        }, 0);
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }

    protected void mo6602a(int i, int i2, int i3, Boolean bool) {
        BackgroundExecutor.a();
        super.mo6602a(i, i2, i3, bool);
    }
}
