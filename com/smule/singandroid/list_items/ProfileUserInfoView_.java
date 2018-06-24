package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ProfileUserInfoView_ extends ProfileUserInfoView implements HasViews, OnViewChangedListener {
    private boolean f23159k = false;
    private final OnViewChangedNotifier f23160l = new OnViewChangedNotifier();

    class C46791 implements OnClickListener {
        final /* synthetic */ ProfileUserInfoView_ f23151a;

        C46791(ProfileUserInfoView_ profileUserInfoView_) {
            this.f23151a = profileUserInfoView_;
        }

        public void onClick(View view) {
            this.f23151a.m24423a();
        }
    }

    class C46802 implements OnClickListener {
        final /* synthetic */ ProfileUserInfoView_ f23152a;

        C46802(ProfileUserInfoView_ profileUserInfoView_) {
            this.f23152a = profileUserInfoView_;
        }

        public void onClick(View view) {
            this.f23152a.m24426b();
        }
    }

    class C46813 implements OnClickListener {
        final /* synthetic */ ProfileUserInfoView_ f23153a;

        C46813(ProfileUserInfoView_ profileUserInfoView_) {
            this.f23153a = profileUserInfoView_;
        }

        public void onClick(View view) {
            this.f23153a.m24428c();
        }
    }

    class C46824 implements OnClickListener {
        final /* synthetic */ ProfileUserInfoView_ f23154a;

        C46824(ProfileUserInfoView_ profileUserInfoView_) {
            this.f23154a = profileUserInfoView_;
        }

        public void onClick(View view) {
            this.f23154a.m24429d();
        }
    }

    class C46835 implements OnClickListener {
        final /* synthetic */ ProfileUserInfoView_ f23155a;

        C46835(ProfileUserInfoView_ profileUserInfoView_) {
            this.f23155a = profileUserInfoView_;
        }

        public void onClick(View view) {
            this.f23155a.m24430e();
        }
    }

    class C46846 implements OnClickListener {
        final /* synthetic */ ProfileUserInfoView_ f23156a;

        C46846(ProfileUserInfoView_ profileUserInfoView_) {
            this.f23156a = profileUserInfoView_;
        }

        public void onClick(View view) {
            this.f23156a.m24431f();
        }
    }

    public ProfileUserInfoView_(Context context) {
        super(context);
        m24434g();
    }

    public static ProfileUserInfoView m24433b(Context context) {
        ProfileUserInfoView profileUserInfoView_ = new ProfileUserInfoView_(context);
        profileUserInfoView_.onFinishInflate();
        return profileUserInfoView_;
    }

    public void onFinishInflate() {
        if (!this.f23159k) {
            this.f23159k = true;
            inflate(getContext(), C1947R.layout.profile_user_info_view, this);
            this.f23160l.a(this);
        }
        super.onFinishInflate();
    }

    private void m24434g() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23160l);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24435a(HasViews hasViews) {
        this.b = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.user_image);
        this.c = (TextView) hasViews.findViewById(C1947R.id.following_count_textview);
        this.d = (TextView) hasViews.findViewById(C1947R.id.followers_count_textview);
        this.e = (TextView) hasViews.findViewById(C1947R.id.profile_blurb);
        this.f = hasViews.findViewById(C1947R.id.profile_blurb_placeholder);
        this.g = (TextView) hasViews.findViewById(C1947R.id.profile_follow_button);
        this.h = (TextView) hasViews.findViewById(C1947R.id.profile_message_button);
        this.i = (TextView) hasViews.findViewById(C1947R.id.profile_unblock_button);
        this.j = (TextView) hasViews.findViewById(C1947R.id.verified_text);
        View findViewById = hasViews.findViewById(C1947R.id.profile_blurb_background);
        if (this.d != null) {
            this.d.setOnClickListener(new C46791(this));
        }
        if (this.c != null) {
            this.c.setOnClickListener(new C46802(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C46813(this));
        }
        if (this.g != null) {
            this.g.setOnClickListener(new C46824(this));
        }
        if (this.h != null) {
            this.h.setOnClickListener(new C46835(this));
        }
        if (this.i != null) {
            this.i.setOnClickListener(new C46846(this));
        }
    }

    protected void mo6878b(final ProfileUserInfo profileUserInfo) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ProfileUserInfoView_ f23158b;

            public void run() {
                super.mo6878b(profileUserInfo);
            }
        }, 0);
    }
}
