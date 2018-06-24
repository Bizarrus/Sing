package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserFollowItem_ extends UserFollowItem implements HasViews, OnViewChangedListener {
    private boolean f23303a = false;
    private final OnViewChangedNotifier f23304r = new OnViewChangedNotifier();

    public UserFollowItem_(Context context) {
        super(context);
        m24513b();
    }

    public UserFollowItem_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m24513b();
    }

    public static UserFollowItem m24512a(Context context) {
        UserFollowItem userFollowItem_ = new UserFollowItem_(context);
        userFollowItem_.onFinishInflate();
        return userFollowItem_;
    }

    public void onFinishInflate() {
        if (!this.f23303a) {
            this.f23303a = true;
            inflate(getContext(), C1947R.layout.user_follow_item, this);
            this.f23304r.a(this);
        }
        super.onFinishInflate();
    }

    private void m24513b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23304r);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24514a(HasViews hasViews) {
        this.b = (TextView) hasViews.findViewById(C1947R.id.mUsernameTextView);
        this.c = (TextView) hasViews.findViewById(C1947R.id.mUsernameSubtextView);
        this.d = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mUserImageView);
        this.e = (ImageButton) hasViews.findViewById(C1947R.id.mFollowButton);
        this.f = hasViews.findViewById(C1947R.id.mMainLayout);
        this.g = hasViews.findViewById(C1947R.id.mFollowListViewHeader);
        this.h = (TextView) hasViews.findViewById(C1947R.id.follow_list_header_textview);
        this.i = (TextView) hasViews.findViewById(C1947R.id.smiles);
        this.k = hasViews.findViewById(C1947R.id.right_view);
        this.l = (TextView) hasViews.findViewById(C1947R.id.suggestion_footer);
        this.m = (TextView) hasViews.findViewById(C1947R.id.time_icon);
        this.n = (ProgressBar) hasViews.findViewById(C1947R.id.progress_bar);
        this.o = hasViews.findViewById(C1947R.id.divider_line);
    }
}
