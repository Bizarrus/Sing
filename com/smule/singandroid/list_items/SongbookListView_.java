package com.smule.singandroid.list_items;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import com.foound.widget.AmazingListView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SongbookListView_ extends SongbookListView implements HasViews, OnViewChangedListener {
    private boolean f23264h = false;
    private final OnViewChangedNotifier f23265i = new OnViewChangedNotifier();

    public SongbookListView_(Context context) {
        super(context);
        m24493f();
    }

    public static SongbookListView m24492b(Context context) {
        SongbookListView songbookListView_ = new SongbookListView_(context);
        songbookListView_.onFinishInflate();
        return songbookListView_;
    }

    public void onFinishInflate() {
        if (!this.f23264h) {
            this.f23264h = true;
            inflate(getContext(), C1947R.layout.songbook_list_view, this);
            this.f23265i.a(this);
        }
        super.onFinishInflate();
    }

    private void m24493f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23265i);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24494a(HasViews hasViews) {
        this.a = (AmazingListView) hasViews.findViewById(C1947R.id.mListingsListView);
        this.b = (SwipeRefreshLayout) hasViews.findViewById(C1947R.id.mSwipeRefreshLayout);
        m24490d();
    }
}
