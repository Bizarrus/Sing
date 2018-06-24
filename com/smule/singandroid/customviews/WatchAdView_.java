package com.smule.singandroid.customviews;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class WatchAdView_ extends WatchAdView implements HasViews, OnViewChangedListener {
    private boolean f22094d = false;
    private final OnViewChangedNotifier f22095e = new OnViewChangedNotifier();

    public WatchAdView_(Context context) {
        super(context);
        m23553b();
    }

    public static WatchAdView m23552a(Context context) {
        WatchAdView watchAdView_ = new WatchAdView_(context);
        watchAdView_.onFinishInflate();
        return watchAdView_;
    }

    public void onFinishInflate() {
        if (!this.f22094d) {
            this.f22094d = true;
            inflate(getContext(), C1947R.layout.watch_ad_view, this);
            this.f22095e.a(this);
        }
        super.onFinishInflate();
    }

    private void m23553b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22095e);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23554a(HasViews hasViews) {
        this.a = (TextView) hasViews.findViewById(C1947R.id.watch_ad_title);
        this.b = (TextView) hasViews.findViewById(C1947R.id.watch_ad_subtitle);
        this.c = (Button) hasViews.findViewById(C1947R.id.watch_ad_button);
        m23550a();
    }
}
