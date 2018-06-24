package com.smule.singandroid.cards;

import android.content.Context;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ListenCard_ extends ListenCard implements HasViews, OnViewChangedListener {
    private boolean f20709b = false;
    private final OnViewChangedNotifier f20710c = new OnViewChangedNotifier();

    public ListenCard_(Context context) {
        super(context);
        m22313c();
    }

    public static ListenCard m22312b(Context context) {
        ListenCard listenCard_ = new ListenCard_(context);
        listenCard_.onFinishInflate();
        return listenCard_;
    }

    public void onFinishInflate() {
        if (!this.f20709b) {
            this.f20709b = true;
            inflate(getContext(), C1947R.layout.listen_card, this);
            this.f20710c.a(this);
        }
        super.onFinishInflate();
    }

    private void m22313c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f20710c);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m22314a(HasViews hasViews) {
        this.a = (SquarePerformanceItem) hasViews.findViewById(C1947R.id.mSquarePerformanceItem);
    }
}
