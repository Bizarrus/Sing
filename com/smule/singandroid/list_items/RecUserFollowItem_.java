package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class RecUserFollowItem_ extends RecUserFollowItem implements HasViews, OnViewChangedListener {
    private boolean f23182r = false;
    private final OnViewChangedNotifier f23183s = new OnViewChangedNotifier();

    public RecUserFollowItem_(Context context) {
        super(context);
        m24456b();
    }

    public static RecUserFollowItem m24455b(Context context) {
        RecUserFollowItem recUserFollowItem_ = new RecUserFollowItem_(context);
        recUserFollowItem_.onFinishInflate();
        return recUserFollowItem_;
    }

    public void onFinishInflate() {
        if (!this.f23182r) {
            this.f23182r = true;
            inflate(getContext(), C1947R.layout.user_follow_item, this);
            this.f23183s.a(this);
        }
        super.onFinishInflate();
    }

    private void m24456b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23183s);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24457a(HasViews hasViews) {
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
