package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.MagicFacebook$FacebookUserInfo;
import com.smule.android.network.api.SocialAPI;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.FacebookFriendsFragment;
import com.smule.singandroid.utils.PerformanceV2Util;
import java.text.MessageFormat;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FBFriendListItem extends LinearLayout {
    FacebookFriend f22833a;
    @ViewById
    protected TextView f22834b;
    @ViewById
    protected TextView f22835c;
    @ViewById
    protected ImageView f22836d;
    @ViewById
    protected ImageButton f22837e;
    @ViewById
    protected ProgressBar f22838f;
    MagicFacebook$FacebookUserInfo f22839g;
    FacebookFriendsFragment f22840h;

    class C46321 implements OnClickListener {
        final /* synthetic */ FBFriendListItem f22831a;

        C46321(FBFriendListItem fBFriendListItem) {
            this.f22831a = fBFriendListItem;
        }

        public void onClick(View view) {
            this.f22831a.callOnClick();
        }
    }

    class C46332 implements ToggleFollowStateListener {
        final /* synthetic */ FBFriendListItem f22832a;

        C46332(FBFriendListItem fBFriendListItem) {
            this.f22832a = fBFriendListItem;
        }

        public void mo6399a(boolean z, boolean z2, boolean z3) {
            if (this.f22832a.f22840h.isAdded()) {
                boolean a = FollowManager.m18204a().m18222a(this.f22832a.f22833a.f15947e);
                this.f22832a.mo6870a(a);
                if (this.f22832a.f22840h != null) {
                    this.f22832a.f22840h.m20035F();
                }
                if (z) {
                    if (a) {
                        Toaster.a(this.f22832a.getContext(), MessageFormat.format(this.f22832a.getContext().getString(C1947R.string.profile_follow_format), new Object[]{this.f22832a.f22833a.f15944b}));
                        return;
                    }
                    Toaster.a(this.f22832a.getContext(), MessageFormat.format(this.f22832a.getContext().getString(C1947R.string.profile_unfollow_format), new Object[]{this.f22832a.f22833a.f15944b}));
                } else if (z3) {
                    Toaster.a(this.f22832a.getContext(), this.f22832a.getResources().getString(C1947R.string.profile_follow_limit_reached));
                } else {
                    Toaster.a(this.f22832a.getContext(), C1947R.string.profile_update_error, Toaster$Duration.SHORT);
                }
            }
        }
    }

    public static FBFriendListItem m24259a(Context context) {
        return FBFriendListItem_.m24265b(context);
    }

    public FBFriendListItem(Context context) {
        super(context);
    }

    public FacebookFriend getFacebookFriend() {
        return this.f22833a;
    }

    public void m24261a(FacebookFriend facebookFriend, MagicFacebook$FacebookUserInfo magicFacebook$FacebookUserInfo, FacebookFriendsFragment facebookFriendsFragment, int i) {
        this.f22833a = facebookFriend;
        this.f22839g = magicFacebook$FacebookUserInfo;
        this.f22835c.setText(this.f22833a.f15944b);
        PerformanceV2Util.m25939a(getResources(), this.f22834b, facebookFriend.f15948f);
        this.f22840h = facebookFriendsFragment;
        if (this.f22833a.f15945c == null || this.f22833a.f15945c.length() == 0) {
            this.f22836d.setImageResource(C1947R.drawable.icn_default_profile_small);
        } else {
            ImageUtils.a(this.f22833a.f15945c, this.f22836d, C1947R.drawable.icn_default_profile_small, true);
        }
        this.f22836d.setOnClickListener(new C46321(this));
        mo6870a(FollowManager.m18204a().m18222a(this.f22833a.f15947e));
    }

    @Click
    protected void m24260a(View view) {
        this.f22838f.setVisibility(0);
        this.f22837e.setVisibility(8);
        FollowManager.m18204a().m18217a(Long.valueOf(this.f22833a.f15947e), this.f22839g != null ? SocialAPI.FOLLOWEES_UPDATE_CONTEXT_FACEBOOK : SocialAPI.FOLLOWEES_UPDATE_CONTEXT_APP, this.f22839g != null ? this.f22839g.f15985g : null, new C46332(this));
    }

    @UiThread
    public void mo6870a(boolean z) {
        this.f22838f.setVisibility(8);
        this.f22837e.setVisibility(0);
        this.f22837e.setActivated(z);
    }
}
