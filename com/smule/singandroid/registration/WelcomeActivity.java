/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$RegistrationNewHandleScreenType
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.registration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.TrackedActivity;
import com.smule.android.network.managers.ContactsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.registration.FindFriendsContactsFragment;
import com.smule.singandroid.registration.FindFriendsContactsFragment_;
import com.smule.singandroid.registration.FindFriendsFacebookFragment;
import com.smule.singandroid.registration.FindFriendsFacebookFragment_;
import com.smule.singandroid.registration.NewHandleDualScreenFragment;
import com.smule.singandroid.registration.NewHandleDualScreenFragment_;
import com.smule.singandroid.registration.NewHandleFragment;
import com.smule.singandroid.registration.NewHandleFragment_;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.io.Serializable;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@SuppressLint(value={"Registered"})
@EActivity
public class WelcomeActivity
extends MediaPlayingActivity
implements TrackedActivity,
FindFriendsFacebookFragment.AttachListener,
NewHandleFragment.AttachListener,
NewHandleFragment.UserUpdateListener {
    private static final String q = WelcomeActivity.class.getName();
    private FindFriendsContactsFragment A;
    private BaseFragment B;
    @ViewById
    protected Button g;
    @ViewById
    protected TextView h;
    @ViewById
    protected ImageView i;
    @InstanceState
    protected State j;
    @InstanceState
    protected State k;
    @InstanceState
    protected String l;
    @InstanceState
    protected boolean m;
    @InstanceState
    protected Analytics n;
    @InstanceState
    protected boolean o;
    @InstanceState
    protected boolean p;
    private final SingServerValues r = new SingServerValues();
    private NewHandleFragment s;
    private FindFriendsFacebookFragment z;

    private void A() {
        this.g.setVisibility(0);
        this.h.setText(2131296687);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.h.getLayoutParams();
        layoutParams.setMargins(this.getResources().getDimensionPixelSize(2131428169), 0, 0, 0);
        this.h.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.i.setVisibility(8);
        this.a(this.B);
        this.j = this.k;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void B() {
        String string2 = this.j == null ? null : this.j.name();
        RegistrationContext.a(string2, this.l, this.m, this.n.name(), this.o, this.p);
    }

    private boolean C() {
        if (this.n != null && this.n.equals(Analytics.b) && MagicFacebook.a().c() && MagicFacebook.a().i() && new SingServerValues().al()) {
            return true;
        }
        return false;
    }

    private boolean D() {
        switch (.b[this.n.ordinal()]) {
            default: {
                return false;
            }
            case 1: 
            case 2: 
        }
        return new SingServerValues().am();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void y() {
        if (this.j == null) {
            this.z();
        } else {
            switch (.a[this.j.ordinal()]) {
                default: {
                    break;
                }
                case 1: {
                    this.A();
                    break;
                }
                case 2: {
                    this.z();
                }
            }
        }
        this.B();
    }

    private void z() {
        this.j = null;
        RegistrationContext.a((Activity)this, false, this.n);
    }

    @Override
    public void A_() {
        this.g.setClickable(true);
        this.g.setEnabled(true);
    }

    @Override
    public void B_() {
        if (this.x() != SingAnalytics.RegistrationNewHandleScreenType.c) {
            this.y();
        }
    }

    @Override
    public void C_() {
        if (this.x() == SingAnalytics.RegistrationNewHandleScreenType.c) {
            this.y();
        }
    }

    @Override
    public void a(EditText editText) {
        NavigationUtils.a((EditText)editText, (Button)this.g);
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public String b() {
        return q;
    }

    @Override
    public void onBackPressed() {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.j = State.a;
        SingAppboy.a().d();
        if (object == null) {
            Intent intent = this.getIntent();
            this.l = intent.getStringExtra("param_handle");
            this.m = intent.getBooleanExtra("param_handle_prefill", true);
            object = this.getIntent().getSerializableExtra("PARAM_LOGIN_METHOD") != null ? (Object)this.getIntent().getSerializableExtra("PARAM_LOGIN_METHOD") : Analytics.a;
            this.n = object;
            this.o = intent.getBooleanExtra("SHOW_EMAIL_OPT", false);
            this.p = intent.getBooleanExtra("FACEBOOK_PHOTO_AUTO_UPLOADED", false);
            object = RegistrationContext.f();
            if (object != null) {
                this.j = State.valueOf((String)object);
            } else {
                object = this.C() || this.D() ? State.b : State.a;
                this.j = object;
            }
        }
        this.s = this.x() == SingAnalytics.RegistrationNewHandleScreenType.c ? NewHandleDualScreenFragment_.K().a(this.l).a(this.m).a((Boolean)this.o).a() : NewHandleFragment_.t().a(this.l).a(this.m).a((Boolean)this.o).a();
        if (this.p) {
            this.s.O();
        }
        if (this.C()) {
            this.k = State.c;
            this.z = FindFriendsFacebookFragment_.t().a();
            this.B = this.z;
        } else if (this.D()) {
            this.k = State.d;
            this.A = FindFriendsContactsFragment_.t().a();
            this.B = this.A;
        }
        this.B();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    protected void onStart() {
        block7 : {
            super.onStart();
            switch (.a[this.j.ordinal()]) {
                case 1: 
                case 2: {
                    this.a(this.s);
                    if (this.x() == SingAnalytics.RegistrationNewHandleScreenType.c) {
                        this.g.setVisibility(8);
                        ** break;
                    }
                    ** GOTO lbl11
                }
                case 3: {
                    this.a(this.z);
                }
lbl11: // 4 sources:
                default: {
                    break block7;
                }
                case 4: 
            }
            this.a(this.A);
        }
        if (this.x() == SingAnalytics.RegistrationNewHandleScreenType.c && this.j != State.c) {
            this.g.setVisibility(8);
        }
        if (UserManager.a().U() == false) return;
        ContactsManager.e();
    }

    @Click
    protected void t() {
        switch (.a[this.j.ordinal()]) {
            default: {
                return;
            }
            case 1: 
            case 2: {
                this.s.M();
                return;
            }
            case 3: {
                this.u();
                return;
            }
            case 4: 
        }
        this.v();
    }

    public void u() {
        SingAnalytics.O();
        this.z();
        this.B();
    }

    public void v() {
        SingAnalytics.Q();
        this.z();
        this.B();
    }

    protected SingAnalytics.RegistrationNewHandleScreenType x() {
        return SingAnalytics.RegistrationNewHandleScreenType.a((String)this.r.an());
    }

    protected static enum State {
        a,
        b,
        c,
        d;
        

        private State() {
        }
    }

}

