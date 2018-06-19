package com.smule.singandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.smule.android.datasources.FacebookFriendsDataSource;
import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.facebook.MagicFacebook$FacebookUserInfo;
import com.smule.android.facebook.MagicFacebook$FacebookUserInfoListener;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.SocialAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.FollowListener;
import com.smule.android.network.managers.RewardsManager;
import com.smule.android.network.managers.RewardsManager.ClaimRewardListener;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountIconResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconResponse;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.list_items.FBFriendListItem;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.HashSet;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FacebookFriendsFragment extends BaseFragment {
    private static final String f18620i = FacebookFriendsFragment.class.getName();
    @ViewById
    protected View f18621e;
    @ViewById
    protected View f18622f;
    @ViewById
    protected MagicListView f18623g;
    @ViewById
    protected TextView f18624h;
    private View f18625j;
    private FacebookFriendsAdapter f18626k;
    private MagicFacebook$FacebookUserInfo f18627l;
    private View f18628m;
    private boolean f18629n;

    class C38071 implements FacebookCallback<LoginResult> {
        final /* synthetic */ FacebookFriendsFragment f18609a;

        C38071(FacebookFriendsFragment facebookFriendsFragment) {
            this.f18609a = facebookFriendsFragment;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            m20008a((LoginResult) obj);
        }

        public void m20008a(LoginResult loginResult) {
            if (this.f18609a.isAdded()) {
                Log.b(FacebookFriendsFragment.f18620i, "onSuccess called; session state is open: " + loginResult);
                MagicFacebook.a().f();
                this.f18609a.m20022J();
                this.f18609a.mo6421C();
            }
        }

        public void onCancel() {
            Log.b(FacebookFriendsFragment.f18620i, "onCancel.");
            if (this.f18609a.isAdded()) {
                this.f18609a.m19862m().a(FacebookFriendsFragment.m20029z());
            }
        }

        public void onError(FacebookException facebookException) {
            Log.b(FacebookFriendsFragment.f18620i, "onError.");
            if (this.f18609a.isAdded()) {
                this.f18609a.m20031B();
                this.f18609a.f18629n = true;
            }
        }
    }

    class C38082 implements MagicFacebook$FacebookUserInfoListener {
        final /* synthetic */ FacebookFriendsFragment f18610a;

        C38082(FacebookFriendsFragment facebookFriendsFragment) {
            this.f18610a = facebookFriendsFragment;
        }

        public void mo6410a(MagicFacebook$FacebookUserInfo magicFacebook$FacebookUserInfo) {
            this.f18610a.f18627l = magicFacebook$FacebookUserInfo;
        }

        public void mo6411b(MagicFacebook$FacebookUserInfo magicFacebook$FacebookUserInfo) {
            this.f18610a.f18627l = null;
        }
    }

    class C38093 implements ClaimRewardListener {
        final /* synthetic */ FacebookFriendsFragment f18611a;

        C38093(FacebookFriendsFragment facebookFriendsFragment) {
            this.f18611a = facebookFriendsFragment;
        }

        public void mo6412a() {
            Log.b(FacebookFriendsFragment.f18620i, "rewardSuccessfullyClaimed");
        }

        public void mo6413b() {
            Log.b(FacebookFriendsFragment.f18620i, "rewardAlreadyClaimed");
        }

        public void mo6414c() {
            Log.b(FacebookFriendsFragment.f18620i, "errorClaimingReward");
        }

        public void mo6415d() {
            Log.b(FacebookFriendsFragment.f18620i, "claimRewardCompleted");
        }
    }

    class C38114 implements OnClickListener {
        final /* synthetic */ FacebookFriendsFragment f18614a;

        C38114(FacebookFriendsFragment facebookFriendsFragment) {
            this.f18614a = facebookFriendsFragment;
        }

        public void onClick(View view) {
            HashSet hashSet = new HashSet();
            FacebookFriendsDataSource facebookFriendsDataSource = (FacebookFriendsDataSource) this.f18614a.f18626k.m18026a();
            for (int i = 0; i < facebookFriendsDataSource.m17661k(); i++) {
                FacebookFriend facebookFriend = (FacebookFriend) facebookFriendsDataSource.m17641a(i);
                if (!FollowManager.m18204a().m18222a(facebookFriend.f15947e)) {
                    hashSet.add(Long.valueOf(facebookFriend.f15947e));
                }
            }
            String str = this.f18614a.f18627l != null ? SocialAPI.FOLLOWEES_UPDATE_CONTEXT_FACEBOOK : SocialAPI.FOLLOWEES_UPDATE_CONTEXT_APP;
            String str2 = this.f18614a.f18627l != null ? this.f18614a.f18627l.f15985g : null;
            final BusyDialog busyDialog = new BusyDialog(this.f18614a.getActivity(), "");
            busyDialog.m23580a(false);
            FollowManager.m18204a().m18220a(hashSet, str, str2, new FollowListener(this) {
                final /* synthetic */ C38114 f18613b;

                public void mo6416a(NetworkResponse networkResponse) {
                    if (this.f18613b.f18614a.isAdded()) {
                        if (networkResponse.c()) {
                            for (int i = 0; i < this.f18613b.f18614a.f18623g.getChildCount(); i++) {
                                View childAt = this.f18613b.f18614a.f18623g.getChildAt(i);
                                if (childAt instanceof FBFriendListItem) {
                                    ((FBFriendListItem) childAt).mo6870a(true);
                                }
                            }
                            this.f18613b.f18614a.mo6423b(false);
                        } else {
                            this.f18613b.f18614a.m19849b(SingApplication.f().getString(C1947R.string.login_error_with_servers));
                        }
                        busyDialog.dismiss();
                    }
                }
            });
        }
    }

    private class FacebookFriendsAdapter extends MagicAdapter {
        final /* synthetic */ FacebookFriendsFragment f18619c;

        public FacebookFriendsAdapter(FacebookFriendsFragment facebookFriendsFragment, MagicDataSource magicDataSource) {
            this.f18619c = facebookFriendsFragment;
            super(magicDataSource);
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return FBFriendListItem.m24259a(this.f18619c.getActivity());
        }

        public void mo6419a(View view, int i, int i2) {
            final FBFriendListItem fBFriendListItem = (FBFriendListItem) view;
            fBFriendListItem.m24261a((FacebookFriend) this.f18619c.f18626k.m18026a().m17641a(i), this.f18619c.f18627l, this.f18619c, i);
            fBFriendListItem.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FacebookFriendsAdapter f18618b;

                public void onClick(View view) {
                    FacebookFriend facebookFriend = fBFriendListItem.getFacebookFriend();
                    final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(this.f18618b.f18619c.getActivity(), (int) C1947R.string.core_loading);
                    UserManager.a().a(facebookFriend.f15947e, new UserManager$AccountIconResponseCallback(this) {
                        final /* synthetic */ C38131 f18616b;

                        public void handleResponse(AccountIconResponse accountIconResponse) {
                            busyScreenDialog.dismiss();
                            if (accountIconResponse.a()) {
                                this.f18616b.f18618b.f18619c.mo6487a(ProfileFragment.m21036a(accountIconResponse.mAccount));
                            }
                        }
                    });
                }
            });
        }

        public void mo6254c(MagicDataSource magicDataSource) {
            super.mo6254c(magicDataSource);
            this.f18619c.mo6422D();
        }
    }

    public String mo6383s() {
        return f18620i;
    }

    public static FacebookFriendsFragment m20024a() {
        FacebookFriendsFragment facebookFriendsFragment_ = new FacebookFriendsFragment_();
        facebookFriendsFragment_.setArguments(new Bundle());
        return facebookFriendsFragment_;
    }

    public static String m20029z() {
        return f18620i;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @AfterViews
    public void m20030A() {
        m19850c((int) C1947R.string.search_find);
        this.f18623g.setMagicAdapter(this.f18626k);
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.core_facebook_friends);
    }

    public void onResume() {
        super.onResume();
        if (this.f18629n) {
            m20031B();
            return;
        }
        m20020H();
        mo6422D();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18628m = null;
    }

    protected void mo6420v() {
        SingAnalytics.m26158v();
    }

    protected void m20031B() {
        this.f18623g.setVisibility(8);
        this.f18622f.setVisibility(8);
        this.f18624h.setText(getString(C1947R.string.facebook_failed_to_connect_try_again));
        this.f18624h.setVisibility(0);
    }

    private boolean m20020H() {
        if (MagicFacebook.a().c()) {
            mo6421C();
            return true;
        }
        this.f18622f.setVisibility(0);
        LoginManager.getInstance().registerCallback(m19862m().n_(), new C38071(this));
        LoginManager.getInstance().logInWithReadPermissions(getActivity(), MagicNetwork.d().getFacebookReadPermissions());
        return false;
    }

    private void m20021I() {
        if (this.f18627l == null) {
            MagicFacebook.a().a(new C38082(this));
        }
    }

    private void m20022J() {
        RewardsManager.m18319a().m18322a(new C38093(this));
    }

    @UiThread
    protected void mo6421C() {
        if (isAdded()) {
            Log.b(f18620i, "facebookConnected");
            m20021I();
            this.f18626k = new FacebookFriendsAdapter(this, new FacebookFriendsDataSource());
            this.f18623g.setMagicAdapter(this.f18626k);
            this.f18622f.setVisibility(8);
        }
    }

    @UiThread
    protected void mo6422D() {
        if (!isAdded()) {
            Log.d(f18620i, "refreshListView - Fragment is not added; aborting refresh");
        } else if (this.f18626k == null) {
            Log.b(f18620i, "mFacebookFriendsUsingSmuleAdapter was null, not ready to update follow header");
        } else {
            LayoutInflater from = LayoutInflater.from(getActivity());
            if (this.f18623g.getHeaderViewsCount() == 0) {
                this.f18628m = from.inflate(C1947R.layout.follow_list_view_header, null);
                this.f18628m.setBackgroundColor(getResources().getColor(C1947R.color.white));
                this.f18625j = this.f18628m.findViewById(C1947R.id.follow_all_textview);
                this.f18625j.setOnClickListener(new C38114(this));
                this.f18623g.setMagicAdapter(null);
                this.f18623g.addHeaderView(this.f18628m);
                this.f18623g.setMagicAdapter(this.f18626k);
            }
            ((TextView) this.f18628m.findViewById(C1947R.id.follow_list_header_textview)).setText(getString(C1947R.string.facebook_friends_to_follow).replace("{0}", Integer.toString(this.f18626k.m18026a().m17661k())));
            m20035F();
        }
    }

    @Click
    protected void m20034E() {
        ((MasterActivity) getActivity()).b(FindFriendsFragment.m20167a(), FindFriendsFragment.f18701e);
    }

    public void m20035F() {
        int i = 0;
        while (i < this.f18626k.m18026a().m17661k()) {
            if (FollowManager.m18204a().m18222a(((FacebookFriend) this.f18626k.m18026a().m17641a(i)).f15947e)) {
                i++;
            } else {
                mo6423b(true);
                return;
            }
        }
        mo6423b(false);
    }

    @UiThread
    public void mo6423b(boolean z) {
        if (isAdded() && this.f18625j != null) {
            if (z) {
                this.f18625j.setVisibility(0);
            } else {
                this.f18625j.setVisibility(8);
            }
        }
    }
}
