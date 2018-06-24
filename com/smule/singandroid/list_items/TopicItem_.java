package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.SquareAlbumGrid;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TopicItem_ extends TopicItem implements HasViews, OnViewChangedListener {
    private boolean f23290g = false;
    private final OnViewChangedNotifier f23291h = new OnViewChangedNotifier();

    public TopicItem_(Context context) {
        super(context);
        m24509c();
    }

    public static TopicItem m24508b(Context context) {
        TopicItem topicItem_ = new TopicItem_(context);
        topicItem_.onFinishInflate();
        return topicItem_;
    }

    public void onFinishInflate() {
        if (!this.f23290g) {
            this.f23290g = true;
            inflate(getContext(), C1947R.layout.album_grid_item, this);
            this.f23291h.a(this);
        }
        super.onFinishInflate();
    }

    private void m24509c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23291h);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24510a(HasViews hasViews) {
        this.a = (SquareAlbumGrid) hasViews.findViewById(C1947R.id.album_art_grid);
        this.b = (ImageView) hasViews.findViewById(C1947R.id.albums_loading_image);
        this.c = (TextView) hasViews.findViewById(C1947R.id.topic_name);
        this.d = hasViews.findViewById(C1947R.id.test_mask);
        this.e = hasViews.findViewById(C1947R.id.check_image);
        m24506b();
    }
}
