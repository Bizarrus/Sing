package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SquarePerformanceItem_ extends SquarePerformanceItem implements HasViews, OnViewChangedListener {
    private boolean f23281f = false;
    private final OnViewChangedNotifier f23282g = new OnViewChangedNotifier();

    public SquarePerformanceItem_(Context context) {
        super(context);
        m24500c();
    }

    public SquarePerformanceItem_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m24500c();
    }

    public SquarePerformanceItem_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m24500c();
    }

    public void onFinishInflate() {
        if (!this.f23281f) {
            this.f23281f = true;
            inflate(getContext(), C1947R.layout.square_performance_list_item, this);
            this.f23282g.a(this);
        }
        super.onFinishInflate();
    }

    private void m24500c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23282g);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24501a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.a = (TextView) hasViews.findViewById(C1947R.id.performance_title_text_view);
        this.b = (TextView) hasViews.findViewById(C1947R.id.performance_creator_text_view);
        this.c = (TextView) hasViews.findViewById(C1947R.id.num_plays_text_view);
        this.d = (TextView) hasViews.findViewById(C1947R.id.num_loves_text_view);
        m23042p();
    }
}
