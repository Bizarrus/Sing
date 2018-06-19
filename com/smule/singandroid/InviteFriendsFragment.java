package com.smule.singandroid;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer.Result;
import com.facebook.share.widget.AppInviteDialog;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics.SocialChannel;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.twitter.MagicTwitter.TwitterOnPostCallback;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
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
public class InviteFriendsFragment extends BaseFragment {
    public static final String f18770e = InviteFriendsFragment.class.getName();
    @ViewById
    protected TextView f18771f;
    @ViewById
    protected TextView f18772g;
    @ViewById
    protected ProfileImageWithVIPBadge f18773h;
    @ViewById
    protected ViewGroup f18774i;
    @ViewById
    protected View f18775j;
    @ViewById
    protected View f18776k;
    @ViewById
    protected View f18777l;
    @ViewById
    protected View f18778m;
    @ViewById
    protected View f18779n;
    @ViewById
    protected ToggleButton f18780o;
    @InstanceState
    protected Intent f18781p;
    @InstanceState
    protected Intent f18782q;
    @InstanceState
    protected Intent f18783r;
    @InstanceState
    protected Intent f18784s;
    @InstanceState
    protected Intent f18785t;
    protected TwitterOnPostCallback f18786u;
    protected FacebookCallback<Result> f18787v;

    class C38491 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment f18766a;

        C38491(InviteFriendsFragment inviteFriendsFragment) {
            this.f18766a = inviteFriendsFragment;
        }

        public void onClick(View view) {
            this.f18766a.m20246B();
        }
    }

    class C38502 implements TwitterOnPostCallback {
        Context f18767a = SingApplication.g().getApplicationContext();
        final /* synthetic */ InviteFriendsFragment f18768b;

        C38502(InviteFriendsFragment inviteFriendsFragment) {
            this.f18768b = inviteFriendsFragment;
        }

        public void mo6468a() {
            Toaster.a(this.f18767a, this.f18767a.getResources().getString(C1947R.string.invite_success));
        }

        public void mo6469a(Exception exception) {
            Toaster.a(this.f18767a, this.f18767a.getResources().getString(C1947R.string.invite_fail));
        }
    }

    class C38513 implements FacebookCallback<Result> {
        final /* synthetic */ InviteFriendsFragment f18769a;

        C38513(InviteFriendsFragment inviteFriendsFragment) {
            this.f18769a = inviteFriendsFragment;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            m20240a((Result) obj);
        }

        public void m20240a(Result result) {
            if (result.getPostId() == null) {
                onCancel();
            } else {
                Toaster.a(this.f18769a.getActivity(), this.f18769a.getString(C1947R.string.invite_success));
            }
        }

        public void onCancel() {
        }

        public void onError(FacebookException facebookException) {
            Toaster.a(this.f18769a.getActivity(), this.f18769a.getString(C1947R.string.invite_fail));
        }
    }

    public String mo6383s() {
        return null;
    }

    public static InviteFriendsFragment m20242a() {
        return new InviteFriendsFragment_();
    }

    public static String m20244z() {
        return f18770e;
    }

    @AfterViews
    protected void m20245A() {
        m20241E();
        m19850c((int) C1947R.string.invite_friends);
        this.f18771f.setText(getString(C1947R.string.invite_friends_title, new Object[]{getString(C1947R.string.app_name)}));
        this.f18772g.setText(UserManager.a().i());
        this.f18773h.setProfilePicUrl(UserManager.a().h());
        this.f18780o.setChecked(MiscUtils.m25899b());
    }

    public void onStart() {
        super.onStart();
        Activity activity = getActivity();
        if (activity instanceof MasterActivity) {
            ((MasterActivity) activity).B().getToolbarView().setDoneButtonOnClickListener(new C38491(this));
        }
        this.f18780o.setChecked(MiscUtils.m25899b());
    }

    public void onStop() {
        super.onStop();
        if (getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity) getActivity()).B().getToolbarView().setDoneButtonOnClickListener(null);
        }
    }

    private void m20241E() {
        if (!AppInviteDialog.canShow()) {
            this.f18779n.setVisibility(8);
        }
        this.f18781p = ShareUtils.m25997a(ShareUtils.m25999a(getActivity()));
        if (this.f18781p == null) {
            this.f18776k.setVisibility(8);
        }
        this.f18782q = ShareUtils.m26008b(ShareUtils.m25999a(getActivity()));
        if (this.f18782q == null) {
            this.f18775j.setVisibility(8);
        }
        this.f18783r = ShareUtils.m26013c(ShareUtils.m25999a(getActivity()));
        if (this.f18783r == null) {
            this.f18777l.setVisibility(8);
        }
        this.f18784s = ShareUtils.m25998a(ShareUtils.m26014c(getActivity()), ShareUtils.m26019d(getActivity()));
        if (this.f18784s == null) {
            this.f18778m.setVisibility(8);
        }
        this.f18786u = new C38502(this);
        this.f18787v = new C38513(this);
    }

    protected void m20246B() {
        Log.b(f18770e, "Twitter toggle checked: " + this.f18780o.isChecked());
        if (this.f18780o.isChecked()) {
            SingAnalytics.m26069a(SocialChannel.TWITTER);
            StatusUpdate statusUpdate = new StatusUpdate(ShareUtils.m26009b(getActivity()));
            MagicTwitter a = MiscUtils.m25882a();
            if (a != null) {
                a.m18841a(statusUpdate, this.f18786u);
            }
        }
        getActivity().onBackPressed();
    }

    @Click
    protected void m20247C() {
        if (this.f18780o.isChecked()) {
            m20243a(SocialChannel.TWITTER);
        }
        if (!MiscUtils.m25899b()) {
            this.f18780o.setChecked(false);
            startActivity(new Intent(getActivity(), TwitterOAuthActivity_.class));
        }
    }

    @Click
    protected void m20248D() {
        this.f18780o.performClick();
    }

    @Click
    protected void mo6470a(View view) {
        if (AppInviteDialog.canShow()) {
            m20243a(SocialChannel.FACEBOOK);
            MagicFacebook.a().a(getActivity(), getString(C1947R.string.facebook_app_invite_url), getString(C1947R.string.facebook_app_invite_preview_image_url));
        }
    }

    @Click
    protected void m20250b(View view) {
        if (this.f18782q != null) {
            m20243a(SocialChannel.LINE);
            startActivity(this.f18782q);
        }
    }

    @Click
    protected void m20251c(View view) {
        if (this.f18781p != null) {
            m20243a(SocialChannel.MESSENGER);
            startActivity(this.f18781p);
        }
    }

    @Click
    protected void m20252d(View view) {
        if (this.f18783r != null) {
            m20243a(SocialChannel.SMS);
            startActivity(this.f18783r);
        }
    }

    @Click
    protected void m20253e(View view) {
        if (this.f18784s != null) {
            m20243a(SocialChannel.EMAIL);
            startActivity(this.f18784s);
        }
    }

    @Click
    protected void m20254f(View view) {
        m20243a(SocialChannel.COPY_LINK);
        ((ClipboardManager) getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(ShareUtils.m26014c(getActivity()), ShareUtils.m25999a(getActivity())));
        Toaster.a(getActivity(), getString(C1947R.string.share_copy_toast));
    }

    @Click
    protected void m20255g(View view) {
        m20243a(SocialChannel.GENERIC);
        ShareUtils.m26022e(getActivity());
    }

    private void m20243a(SocialChannel socialChannel) {
        SingAnalytics.m26100a(null, socialChannel, null);
    }
}
