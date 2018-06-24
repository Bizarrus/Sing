package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.VideoUploadStatusView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LeaderboardRecentListItem_ extends LeaderboardRecentListItem implements HasViews, OnViewChangedListener {
    private boolean f23069a = false;
    private final OnViewChangedNotifier f23070b = new OnViewChangedNotifier();

    public LeaderboardRecentListItem_(Context context) {
        super(context);
        m24375f();
    }

    public static LeaderboardRecentListItem m24374b(Context context) {
        LeaderboardRecentListItem leaderboardRecentListItem_ = new LeaderboardRecentListItem_(context);
        leaderboardRecentListItem_.onFinishInflate();
        return leaderboardRecentListItem_;
    }

    public void onFinishInflate() {
        if (!this.f23069a) {
            this.f23069a = true;
            inflate(getContext(), C1947R.layout.leaderboard_recent_list_item, this);
            this.f23070b.a(this);
        }
        super.onFinishInflate();
    }

    private void m24375f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23070b);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24376a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.C = (VideoUploadStatusView) hasViews.findViewById(C1947R.id.video_status_view);
        this.c = hasViews.findViewById(C1947R.id.content_view);
        this.d = hasViews.findViewById(C1947R.id.metadata_view);
        this.e = (TextView) hasViews.findViewById(C1947R.id.perf_list_item_title);
        this.f = (TextView) hasViews.findViewById(C1947R.id.perf_list_item_artist);
        this.g = (TextView) hasViews.findViewById(C1947R.id.perf_list_item_love_count_text_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.perf_list_item_play_count_text_view);
        this.i = (TextView) hasViews.findViewById(C1947R.id.perf_list_item_clock_text);
        this.j = hasViews.findViewById(C1947R.id.header);
        this.k = (TextView) hasViews.findViewById(C1947R.id.mHeaderTextView);
        this.l = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mUserProfileImageView);
        this.m = (TextView) hasViews.findViewById(C1947R.id.mJoinsCountTextView);
        this.n = hasViews.findViewById(C1947R.id.mTopSeparatorView);
        m23042p();
    }
}
