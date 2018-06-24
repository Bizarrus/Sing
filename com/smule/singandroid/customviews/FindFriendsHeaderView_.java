package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FindFriendsHeaderView_ extends FindFriendsHeaderView implements HasViews, OnViewChangedListener {
    private boolean f21554g = false;
    private final OnViewChangedNotifier f21555h = new OnViewChangedNotifier();

    class C43871 implements OnClickListener {
        final /* synthetic */ FindFriendsHeaderView_ f21551a;

        C43871(FindFriendsHeaderView_ findFriendsHeaderView_) {
            this.f21551a = findFriendsHeaderView_;
        }

        public void onClick(View view) {
            this.f21551a.m23178b();
        }
    }

    class C43882 implements OnClickListener {
        final /* synthetic */ FindFriendsHeaderView_ f21552a;

        C43882(FindFriendsHeaderView_ findFriendsHeaderView_) {
            this.f21552a = findFriendsHeaderView_;
        }

        public void onClick(View view) {
            this.f21552a.m23178b();
        }
    }

    class C43893 implements OnClickListener {
        final /* synthetic */ FindFriendsHeaderView_ f21553a;

        C43893(FindFriendsHeaderView_ findFriendsHeaderView_) {
            this.f21553a = findFriendsHeaderView_;
        }

        public void onClick(View view) {
            this.f21553a.m23179c();
        }
    }

    public FindFriendsHeaderView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23182e();
    }

    public static FindFriendsHeaderView m23181a(Context context, AttributeSet attributeSet) {
        FindFriendsHeaderView findFriendsHeaderView_ = new FindFriendsHeaderView_(context, attributeSet);
        findFriendsHeaderView_.onFinishInflate();
        return findFriendsHeaderView_;
    }

    public void onFinishInflate() {
        if (!this.f21554g) {
            this.f21554g = true;
            inflate(getContext(), C1947R.layout.find_friends_header_view, this);
            this.f21555h.a(this);
        }
        super.onFinishInflate();
    }

    private void m23182e() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21555h);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23183a(HasViews hasViews) {
        this.b = (ViewGroup) hasViews.findViewById(C1947R.id.find_friends_facebook_friends_view);
        this.c = hasViews.findViewById(C1947R.id.mFacebookConnectedView);
        this.d = hasViews.findViewById(C1947R.id.mFacebookNotConnectedView);
        this.e = hasViews.findViewById(C1947R.id.mInviteFriendsView);
        this.f = hasViews.findViewById(C1947R.id.mDivider);
        View findViewById = hasViews.findViewById(C1947R.id.mFbNotConnectedButton);
        if (this.c != null) {
            this.c.setOnClickListener(new C43871(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C43882(this));
        }
        if (this.e != null) {
            this.e.setOnClickListener(new C43893(this));
        }
    }
}
