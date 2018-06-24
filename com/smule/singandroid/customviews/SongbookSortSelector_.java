package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SongbookSortSelector_ extends SongbookSortSelector implements HasViews, OnViewChangedListener {
    private boolean f21990e = false;
    private final OnViewChangedNotifier f21991f = new OnViewChangedNotifier();

    public SongbookSortSelector_(Context context) {
        super(context);
        m23500c();
    }

    public SongbookSortSelector_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23500c();
    }

    public void onFinishInflate() {
        if (!this.f21990e) {
            this.f21990e = true;
            inflate(getContext(), C1947R.layout.songbook_sort_selector, this);
            this.f21991f.a(this);
        }
        super.onFinishInflate();
    }

    private void m23500c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21991f);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23501a(HasViews hasViews) {
        this.a = (Spinner) hasViews.findViewById(C1947R.id.mCurrentSpinner);
        m23497a();
    }
}
