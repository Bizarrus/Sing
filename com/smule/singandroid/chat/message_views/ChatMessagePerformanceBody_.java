package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.textviews.EllipsizingTextView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMessagePerformanceBody_ extends ChatMessagePerformanceBody implements HasViews, OnViewChangedListener {
    private boolean f21404w = false;
    private final OnViewChangedNotifier f21405x = new OnViewChangedNotifier();

    public ChatMessagePerformanceBody_(Context context) {
        super(context);
        m23064f();
    }

    public ChatMessagePerformanceBody_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23064f();
    }

    public ChatMessagePerformanceBody_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23064f();
    }

    public void onFinishInflate() {
        if (!this.f21404w) {
            this.f21404w = true;
            inflate(getContext(), C1947R.layout.chat_message_body_view_performance, this);
            this.f21405x.a(this);
        }
        super.onFinishInflate();
    }

    private void m23064f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21405x);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23065a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.q = hasViews.findViewById(C1947R.id.social_counters);
        this.r = (TextView) hasViews.findViewById(C1947R.id.play_count_text_view);
        this.s = (TextView) hasViews.findViewById(C1947R.id.loves_count_text_view);
        this.t = (TextView) hasViews.findViewById(C1947R.id.song_title_text_view);
        this.u = (TextView) hasViews.findViewById(C1947R.id.artist_name_text_view);
        this.b = hasViews.findViewById(C1947R.id.performance_message_container);
        this.c = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_pic);
        this.d = (EllipsizingTextView) hasViews.findViewById(C1947R.id.performance_message_text_view);
        this.e = hasViews.findViewById(C1947R.id.message_divider_line);
        this.f = hasViews.findViewById(C1947R.id.playable_item_container);
        this.g = (ViewGroup) hasViews.findViewById(C1947R.id.join_container);
        this.h = (Button) hasViews.findViewById(C1947R.id.join_button);
        this.i = hasViews.findViewById(C1947R.id.mMoreIcon);
        this.j = (TextView) hasViews.findViewById(C1947R.id.join_text);
        this.k = (TextView) hasViews.findViewById(C1947R.id.performance_deleted_text);
        this.l = hasViews.findViewById(C1947R.id.song_details_inner);
        this.m = (ProgressBar) hasViews.findViewById(C1947R.id.performance_progress_bar);
        m23042p();
        m23061c();
    }
}
