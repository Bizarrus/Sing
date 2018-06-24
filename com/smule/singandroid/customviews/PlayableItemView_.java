package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PlayableItemView_ extends PlayableItemView implements HasViews, OnViewChangedListener {
    private boolean f21815n = false;
    private final OnViewChangedNotifier f21816o = new OnViewChangedNotifier();

    public PlayableItemView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23382e();
    }

    public void onFinishInflate() {
        if (!this.f21815n) {
            this.f21815n = true;
            inflate(getContext(), C1947R.layout.playable_item_layout, this);
            this.f21816o.a(this);
        }
        super.onFinishInflate();
    }

    private void m23382e() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21816o);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23383a(HasViews hasViews) {
        this.a = (ImageView) hasViews.findViewById(C1947R.id.album_art_image_view);
        this.b = hasViews.findViewById(C1947R.id.album_art_overlay_view);
        this.c = (ImageView) hasViews.findViewById(C1947R.id.album_art_play_pause_button);
        this.d = hasViews.findViewById(C1947R.id.album_art_play_pause_button_container);
        this.e = hasViews.findViewById(C1947R.id.comment_love_button_offset);
        this.f = (ProgressBar) hasViews.findViewById(C1947R.id.album_art_progress_spinner);
        this.g = (ImageView) hasViews.findViewById(C1947R.id.album_art_play_button_overlay);
        this.h = hasViews.findViewById(C1947R.id.comment_love_button_view);
        this.i = (ImageButton) hasViews.findViewById(C1947R.id.comment_button);
        this.j = (ImageButton) hasViews.findViewById(C1947R.id.love_button);
        this.k = (ImageView) hasViews.findViewById(C1947R.id.right_filmstrip);
        this.l = (ImageView) hasViews.findViewById(C1947R.id.left_filmstrip);
        this.m = (ImageView) hasViews.findViewById(C1947R.id.album_art_now_playing_overlay_image);
        m23375a();
    }
}
