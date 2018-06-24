package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PlayableItemDetailsView_ extends PlayableItemDetailsView implements HasViews, OnViewChangedListener {
    private boolean f21794a = false;
    private final OnViewChangedNotifier f21795b = new OnViewChangedNotifier();

    public PlayableItemDetailsView_(Context context) {
        super(context);
        m23373c();
    }

    public PlayableItemDetailsView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23373c();
    }

    public PlayableItemDetailsView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23373c();
    }

    public void onFinishInflate() {
        if (!this.f21794a) {
            this.f21794a = true;
            inflate(getContext(), C1947R.layout.playable_item_details_layout, this);
            this.f21795b.a(this);
        }
        super.onFinishInflate();
    }

    private void m23373c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21795b);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23374a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.q = hasViews.findViewById(C1947R.id.social_counters);
        this.r = (TextView) hasViews.findViewById(C1947R.id.play_count_text_view);
        this.s = (TextView) hasViews.findViewById(C1947R.id.loves_count_text_view);
        this.t = (TextView) hasViews.findViewById(C1947R.id.song_title_text_view);
        this.u = (TextView) hasViews.findViewById(C1947R.id.artist_name_text_view);
        m23042p();
    }
}
