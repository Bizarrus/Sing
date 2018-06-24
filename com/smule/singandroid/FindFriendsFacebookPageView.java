/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.support.constraint.ConstraintLayout
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.facebook.CallbackManager
 *  com.facebook.FacebookCallback
 *  com.facebook.FacebookException
 *  com.facebook.login.LoginManager
 *  com.facebook.login.LoginResult
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.smule.android.datasources.FacebookFriendsDataSource;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.RewardsManager;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsExternalPageView;
import com.smule.singandroid.FindFriendsFacebookPageView_;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Collection;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FindFriendsFacebookPageView
extends FindFriendsExternalPageView {
    private static final String n = FindFriendsFacebookPageView.class.getName();
    @ViewById
    protected ConstraintLayout a;
    @ViewById
    protected ConstraintLayout b;
    @ViewById
    protected LinearLayout c;
    private MagicFacebook o;
    private boolean p = false;

    public FindFriendsFacebookPageView(Context context) {
        super(context);
    }

    public FindFriendsFacebookPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FindFriendsFacebookPageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public static FindFriendsFacebookPageView a(Context context, BaseFragment baseFragment, FindFriendsExternalPageView.Mode mode) {
        FindFriendsFacebookPageView findFriendsFacebookPageView = FindFriendsFacebookPageView_.a(context);
        findFriendsFacebookPageView.a(context, baseFragment, mode, 2131361801, 2131296785);
        ReferenceMonitor.a().a(findFriendsFacebookPageView);
        return findFriendsFacebookPageView;
    }

    private void getFacebookConnectReward() {
        RewardsManager.a().a(new RewardsManager.ClaimRewardListener(){

            @Override
            public void a() {
                Log.b(n, "rewardSuccessfullyClaimed");
            }

            @Override
            public void b() {
                Log.b(n, "rewardAlreadyClaimed");
            }

            @Override
            public void c() {
                Log.b(n, "errorClaimingReward");
            }

            @Override
            public void d() {
                Log.b(n, "claimRewardCompleted");
            }
        });
    }

    private boolean i() {
        Log.b(n, "connecting to Facebook...");
        if (com.smule.android.facebook.MagicFacebook.a().c() && com.smule.android.facebook.MagicFacebook.a().i()) {
            this.b();
            return true;
        }
        LoginManager.getInstance().registerCallback(this.m.p().h(), (FacebookCallback)new FacebookCallback<LoginResult>(){

            public void a(LoginResult loginResult) {
                if (!FindFriendsFacebookPageView.this.h()) {
                    return;
                }
                Log.b(n, "   FB Login onSuccess called; session state is open: " + (Object)loginResult);
                com.smule.android.facebook.MagicFacebook.a().g();
                FindFriendsFacebookPageView.this.getFacebookConnectReward();
                FindFriendsFacebookPageView.this.b();
            }

            public void onCancel() {
                Log.b(n, "   FB Login onCancel.");
                if (!FindFriendsFacebookPageView.this.h()) {
                    return;
                }
                FindFriendsFacebookPageView.this.a(FindFriendsExternalPageView.ViewState.b);
            }

            public void onError(FacebookException facebookException) {
                Log.b(n, "   FB Login onError.");
                if (!FindFriendsFacebookPageView.this.h()) {
                    return;
                }
                FindFriendsFacebookPageView.this.p = true;
                FindFriendsFacebookPageView.this.a(FindFriendsExternalPageView.ViewState.d);
            }

            public /* synthetic */ void onSuccess(Object object) {
                this.a((LoginResult)object);
            }
        });
        LoginManager.getInstance().logInWithReadPermissions(this.m.getActivity(), MagicNetwork.d().getFacebookReadPermissions());
        this.a(FindFriendsExternalPageView.ViewState.c);
        this.d();
        return false;
    }

    private void j() {
        if (this.o != null) {
            return;
        }
        com.smule.android.facebook.MagicFacebook.a().a(new MagicFacebook(){

            @Override
            public void a(MagicFacebook facebookUserInfo) {
                FindFriendsFacebookPageView.this.o = facebookUserInfo;
            }

            @Override
            public void b(MagicFacebook facebookUserInfo) {
                FindFriendsFacebookPageView.this.o = null;
            }
        });
    }

    @Override
    public void a() {
        Log.b(n, "setting up page");
        ((TextView)this.a.findViewById(2131755743)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FindFriendsFacebookPageView.this.i();
            }
        });
        if (this.p) {
            Log.b(n, "   FB login error flag, displaying login error screen to user");
            this.a(FindFriendsExternalPageView.ViewState.d);
            return;
        }
        if (com.smule.android.facebook.MagicFacebook.a().c() && com.smule.android.facebook.MagicFacebook.a().i()) {
            Log.b(n, "   FB already logged in");
            this.b();
            return;
        }
        Log.b(n, "   FB needs login, prompting user");
        this.a(FindFriendsExternalPageView.ViewState.b);
    }

    protected void a(FindFriendsExternalPageView.ViewState viewState) {
        this.f.setVisibility(8);
        this.a.setVisibility(8);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        switch (.a[viewState.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.f.setVisibility(0);
                return;
            }
            case 2: {
                this.a.setVisibility(0);
                return;
            }
            case 3: {
                this.b.setVisibility(0);
                return;
            }
            case 4: 
        }
        this.c.setVisibility(0);
    }

    @UiThread
    protected void b() {
        if (!this.h()) {
            return;
        }
        Log.b(n, "facebookConnected()");
        this.j();
        this.p = false;
        this.h = new FacebookFriendsDataSource();
        this.g = new FacebookFriendsAdapter(this.h);
        this.f.setMagicAdapter(this.g);
        this.d();
        this.a(FindFriendsExternalPageView.ViewState.a);
    }

    @Override
    public void c() {
        SingAnalytics.A();
    }

    @Override
    public String getExternalName() {
        if (this.o != null) {
            return this.o.g;
        }
        return null;
    }

    @Override
    public String getSocialContext() {
        if (this.o != null) {
            if (this.d == FindFriendsExternalPageView.Mode.a) {
                return "FB";
            }
            return "FB_ONBRD";
        }
        return "APP";
    }

    private class FacebookFriendsAdapter
    extends FindFriendsExternalPageView.ExternalFriendsAdapter {
        public FacebookFriendsAdapter(MagicDataSource magicDataSource) {
            super(magicDataSource, 2130903237, 2130903239);
        }
    }

}

