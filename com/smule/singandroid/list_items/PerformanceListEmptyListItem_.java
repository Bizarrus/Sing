package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PerformanceListEmptyListItem_ extends PerformanceListEmptyListItem implements HasViews, OnViewChangedListener {
    private boolean f23115d = false;
    private final OnViewChangedNotifier f23116e = new OnViewChangedNotifier();

    public PerformanceListEmptyListItem_(Context context) {
        super(context);
        m24398b();
    }

    public PerformanceListEmptyListItem_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m24398b();
    }

    public static PerformanceListEmptyListItem m24397b(Context context) {
        PerformanceListEmptyListItem performanceListEmptyListItem_ = new PerformanceListEmptyListItem_(context);
        performanceListEmptyListItem_.onFinishInflate();
        return performanceListEmptyListItem_;
    }

    public void onFinishInflate() {
        if (!this.f23115d) {
            this.f23115d = true;
            inflate(getContext(), C1947R.layout.performance_list_empty_list_item, this);
            this.f23116e.a(this);
        }
        super.onFinishInflate();
    }

    private void m24398b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23116e);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24399a(HasViews hasViews) {
        this.a = (ImageView) hasViews.findViewById(C1947R.id.mIconImageView);
        this.b = (TextView) hasViews.findViewById(C1947R.id.mTextView);
        this.c = (TextView) hasViews.findViewById(C1947R.id.mActionButton);
    }
}
