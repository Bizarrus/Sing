/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.ShareUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$ShareExtClkContext
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 *  twitter4j.StatusUpdate
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.InviteFriendsFragment_;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.TwitterOAuthActivity_;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.ShareUtils;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import twitter4j.StatusUpdate;

@EFragment
public class InviteFriendsFragment
extends BaseFragment {
    public static final String g = InviteFriendsFragment.class.getName();
    @ViewById
    protected TextView h;
    @ViewById
    protected TextView i;
    @ViewById
    protected ProfileImageWithVIPBadge j;
    @ViewById
    protected ViewGroup k;
    @ViewById
    protected View l;
    @ViewById
    protected View m;
    @ViewById
    protected View n;
    @ViewById
    protected View o;
    @ViewById
    protected View p;
    @ViewById
    protected ToggleButton q;
    @InstanceState
    protected Intent r;
    @InstanceState
    protected Intent s;
    @InstanceState
    protected Intent t;
    @InstanceState
    protected Intent u;
    @InstanceState
    protected Intent v;
    protected MagicTwitter.TwitterOnPostCallback w;

    private void J() {
        this.r = ShareUtils.c((String)ShareUtils.a((Context)this.getActivity()));
        if (this.r == null) {
            this.m.setVisibility(8);
        }
        this.s = ShareUtils.d((String)ShareUtils.a((Context)this.getActivity()));
        if (this.s == null) {
            this.l.setVisibility(8);
        }
        this.t = ShareUtils.b((String)ShareUtils.a((Context)this.getActivity()));
        if (this.t == null) {
            this.n.setVisibility(8);
        }
        this.u = ShareUtils.e((String)ShareUtils.a((Context)this.getActivity()));
        if (this.u == null) {
            this.o.setVisibility(8);
        }
        this.v = ShareUtils.a((String)ShareUtils.c((Context)this.getActivity()), (String)ShareUtils.d((Context)this.getActivity()));
        if (this.v == null) {
            this.p.setVisibility(8);
        }
        this.w = new MagicTwitter.TwitterOnPostCallback(){
            Context a;
            {
                this.a = SingApplication.h().getApplicationContext();
            }

            @Override
            public void a() {
                String string2 = this.a.getResources().getString(2131296871);
                Toaster.a(this.a, string2);
            }

            @Override
            public void a(Exception object) {
                object = this.a.getResources().getString(2131296865);
                Toaster.a(this.a, (String)object);
            }
        };
    }

    public static InviteFriendsFragment a() {
        return new InviteFriendsFragment_();
    }

    private void a(Analytics socialChannel) {
        SingAnalytics.a((String)null, (SingAnalytics.ShareExtClkContext)SingAnalytics.ShareExtClkContext.a, socialChannel, null);
    }

    public static String t() {
        return g;
    }

    @AfterViews
    protected void F() {
        this.J();
        this.c(2131296868);
        this.h.setText((CharSequence)this.getString(2131296869, new Object[]{this.getString(2131296402)}));
        this.i.setText((CharSequence)UserManager.a().i());
        this.j.setProfilePicUrl(UserManager.a().h());
        this.q.setChecked(MiscUtils.b());
    }

    protected void G() {
        Log.b(g, "Twitter toggle checked: " + this.q.isChecked());
        if (this.q.isChecked()) {
            SingAnalytics.a(Analytics.e);
            StatusUpdate statusUpdate = new StatusUpdate(ShareUtils.b((Context)this.getActivity()));
            MagicTwitter magicTwitter = MiscUtils.a();
            if (magicTwitter != null) {
                magicTwitter.a(statusUpdate, this.w);
            }
        }
        this.getActivity().onBackPressed();
    }

    @Click
    protected void H() {
        if (this.q.isChecked()) {
            this.a(Analytics.e);
        }
        if (!MiscUtils.b()) {
            this.q.setChecked(false);
            this.startActivity(new Intent((Context)this.getActivity(), TwitterOAuthActivity_.class));
        }
    }

    @Click
    protected void I() {
        this.q.performClick();
    }

    @Click
    protected void a(View view) {
        if (this.s != null) {
            this.a(Analytics.g);
            this.startActivity(this.s);
        }
    }

    @Click
    protected void b(View view) {
        if (this.r != null) {
            this.a(Analytics.h);
            this.startActivity(this.r);
        }
    }

    @Click
    protected void c(View view) {
        if (this.t != null) {
            this.a(Analytics.o);
            this.startActivity(this.t);
        }
    }

    @Click
    protected void d(View view) {
        if (this.u != null) {
            this.a(Analytics.i);
            this.startActivity(this.u);
        }
    }

    @Click
    protected void e(View view) {
        if (this.v != null) {
            this.a(Analytics.j);
            this.startActivity(this.v);
        }
    }

    @Click
    protected void f(View view) {
        this.a(Analytics.m);
        ((ClipboardManager)this.getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)ShareUtils.c((Context)this.getActivity()), (CharSequence)ShareUtils.a((Context)this.getActivity())));
        Toaster.a((Context)this.getActivity(), this.getString(2131297386));
    }

    @Override
    public boolean f() {
        return false;
    }

    @Click
    protected void g(View view) {
        this.a(Analytics.n);
        ShareUtils.e((Context)this.getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        Activity activity = this.getActivity();
        if (activity instanceof MasterActivity) {
            ((MasterActivity)activity).U().getToolbarView().setDoneButtonOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InviteFriendsFragment.this.G();
                }
            });
        }
        this.q.setChecked(MiscUtils.b());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).U().getToolbarView().setDoneButtonOnClickListener(null);
        }
    }

    @Override
    public String x() {
        return null;
    }

}

