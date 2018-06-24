package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.TextureView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.AnimatableCardView;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SearchMediaExpandableListViewItem_ extends SearchMediaExpandableListViewItem implements HasViews, OnViewChangedListener {
    private boolean f23245I = false;
    private final OnViewChangedNotifier f23246J = new OnViewChangedNotifier();

    public SearchMediaExpandableListViewItem_(Context context) {
        super(context);
        m24479h();
    }

    public static SearchMediaExpandableListViewItem m24478b(Context context) {
        SearchMediaExpandableListViewItem searchMediaExpandableListViewItem_ = new SearchMediaExpandableListViewItem_(context);
        searchMediaExpandableListViewItem_.onFinishInflate();
        return searchMediaExpandableListViewItem_;
    }

    public void onFinishInflate() {
        if (!this.f23245I) {
            this.f23245I = true;
            inflate(getContext(), C1947R.layout.search_media_expandable_list_item, this);
            this.f23246J.a(this);
        }
        super.onFinishInflate();
    }

    private void m24479h() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23246J);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24480a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.a = (RelativeLayout) hasViews.findViewById(C1947R.id.parent_layout);
        this.b = (RelativeLayout) hasViews.findViewById(C1947R.id.header);
        this.c = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.header_profile_pic);
        this.d = (ImageView) hasViews.findViewById(C1947R.id.icn_more);
        this.e = (TextView) hasViews.findViewById(C1947R.id.user_name_text_view);
        this.f = (TextView) hasViews.findViewById(C1947R.id.plus_text_view);
        this.g = (TextView) hasViews.findViewById(C1947R.id.other_joiners_text_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.body_text_view);
        this.i = (FrameLayout) hasViews.findViewById(C1947R.id.album_container);
        this.j = (RelativeLayout) hasViews.findViewById(C1947R.id.album_audio_container);
        this.k = (RelativeLayout) hasViews.findViewById(C1947R.id.video_container);
        this.l = (ImageView) hasViews.findViewById(C1947R.id.album_art_blurred_background);
        this.m = hasViews.findViewById(C1947R.id.album_art_blurred_background_dimmer);
        this.n = (RippleBackground) hasViews.findViewById(C1947R.id.album_art_ripple_background);
        this.o = (AnimatableCardView) hasViews.findViewById(C1947R.id.album_art_mask);
        this.p = (ImageView) hasViews.findViewById(C1947R.id.album_art);
        this.q = hasViews.findViewById(C1947R.id.audio_loading_container);
        this.r = (TextureView) hasViews.findViewById(C1947R.id.video_playback_view);
        this.s = hasViews.findViewById(C1947R.id.video_playback_view_mask);
        this.t = hasViews.findViewById(C1947R.id.video_loading_container);
        this.u = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.v = (TextView) hasViews.findViewById(C1947R.id.days_left_text_view);
        this.w = (TextView) hasViews.findViewById(C1947R.id.num_plays_text_view);
        this.x = (TextView) hasViews.findViewById(C1947R.id.time_icon);
        this.y = (TextView) hasViews.findViewById(C1947R.id.song_title);
        this.z = (TextView) hasViews.findViewById(C1947R.id.artist);
        this.A = (RelativeLayout) hasViews.findViewById(C1947R.id.bottom_action_view);
        this.B = hasViews.findViewById(C1947R.id.divider_line);
        m23042p();
    }
}
