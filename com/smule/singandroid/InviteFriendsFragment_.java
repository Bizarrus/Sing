package com.smule.singandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class InviteFriendsFragment_ extends InviteFriendsFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f18797w = new OnViewChangedNotifier();
    private View f18798x;

    class C38521 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18788a;

        C38521(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18788a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18788a.m20247C();
        }
    }

    class C38532 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18789a;

        C38532(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18789a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18789a.m20248D();
        }
    }

    class C38543 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18790a;

        C38543(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18790a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18790a.mo6470a(view);
        }
    }

    class C38554 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18791a;

        C38554(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18791a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18791a.m20250b(view);
        }
    }

    class C38565 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18792a;

        C38565(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18792a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18792a.m20251c(view);
        }
    }

    class C38576 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18793a;

        C38576(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18793a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18793a.m20252d(view);
        }
    }

    class C38587 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18794a;

        C38587(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18794a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18794a.m20253e(view);
        }
    }

    class C38598 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18795a;

        C38598(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18795a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18795a.m20254f(view);
        }
    }

    class C38609 implements OnClickListener {
        final /* synthetic */ InviteFriendsFragment_ f18796a;

        C38609(InviteFriendsFragment_ inviteFriendsFragment_) {
            this.f18796a = inviteFriendsFragment_;
        }

        public void onClick(View view) {
            this.f18796a.m20255g(view);
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, InviteFriendsFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f18797w);
        m20257a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f18798x == null) {
            return null;
        }
        return this.f18798x.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18798x = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f18798x == null) {
            this.f18798x = layoutInflater.inflate(C1947R.layout.invite_friends_fragment, viewGroup, false);
        }
        return this.f18798x;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18798x = null;
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
    }

    private void m20257a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20259b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f18797w.a(this);
    }

    public void m20261a(HasViews hasViews) {
        this.f = (TextView) hasViews.findViewById(C1947R.id.share_title);
        this.g = (TextView) hasViews.findViewById(C1947R.id.username_label);
        this.h = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.user_profile_pic);
        this.i = (ViewGroup) hasViews.findViewById(C1947R.id.share_icons_container);
        this.j = hasViews.findViewById(C1947R.id.share_line);
        this.k = hasViews.findViewById(C1947R.id.share_messenger);
        this.l = hasViews.findViewById(C1947R.id.share_sms);
        this.m = hasViews.findViewById(C1947R.id.share_email);
        this.n = hasViews.findViewById(C1947R.id.share_facebook);
        this.o = (ToggleButton) hasViews.findViewById(C1947R.id.twitter_toggle);
        View findViewById = hasViews.findViewById(C1947R.id.twitter_toggle_touch);
        View findViewById2 = hasViews.findViewById(C1947R.id.share_copy);
        View findViewById3 = hasViews.findViewById(C1947R.id.share_more);
        if (this.o != null) {
            this.o.setOnClickListener(new C38521(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C38532(this));
        }
        if (this.n != null) {
            this.n.setOnClickListener(new C38543(this));
        }
        if (this.j != null) {
            this.j.setOnClickListener(new C38554(this));
        }
        if (this.k != null) {
            this.k.setOnClickListener(new C38565(this));
        }
        if (this.l != null) {
            this.l.setOnClickListener(new C38576(this));
        }
        if (this.m != null) {
            this.m.setOnClickListener(new C38587(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C38598(this));
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new C38609(this));
        }
        m20245A();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mMessengerIntent", this.p);
        bundle.putParcelable("mLineIntent", this.q);
        bundle.putParcelable("mSmsShareIntent", this.r);
        bundle.putParcelable("mEmailShareIntent", this.s);
        bundle.putParcelable("mFacebookShareIntent", this.t);
    }

    private void m20259b(Bundle bundle) {
        if (bundle != null) {
            this.p = (Intent) bundle.getParcelable("mMessengerIntent");
            this.q = (Intent) bundle.getParcelable("mLineIntent");
            this.r = (Intent) bundle.getParcelable("mSmsShareIntent");
            this.s = (Intent) bundle.getParcelable("mEmailShareIntent");
            this.t = (Intent) bundle.getParcelable("mFacebookShareIntent");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
