package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NotificationsListView_ extends NotificationsListView implements HasViews, OnViewChangedListener {
    private boolean f21677l = false;
    private final OnViewChangedNotifier f21678m = new OnViewChangedNotifier();

    class C44161 implements OnClickListener {
        final /* synthetic */ NotificationsListView_ f21676a;

        C44161(NotificationsListView_ notificationsListView_) {
            this.f21676a = notificationsListView_;
        }

        public void onClick(View view) {
            this.f21676a.m23304b();
        }
    }

    public NotificationsListView_(Context context) {
        super(context);
        m23306c();
    }

    public static NotificationsListView m23305a(Context context) {
        NotificationsListView notificationsListView_ = new NotificationsListView_(context);
        notificationsListView_.onFinishInflate();
        return notificationsListView_;
    }

    public void onFinishInflate() {
        if (!this.f21677l) {
            this.f21677l = true;
            inflate(getContext(), C1947R.layout.notifications_list_view, this);
            this.f21678m.a(this);
        }
        super.onFinishInflate();
    }

    private void m23306c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21678m);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23307a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.c = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.d = (SwipeRefreshLayout) hasViews.findViewById(C1947R.id.mSwipeLayout);
        this.e = (MediaListView) hasViews.findViewById(C1947R.id.notifications_listview);
        this.f = hasViews.findViewById(C1947R.id.mNotificationsLine);
        View findViewById = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C44161(this));
        }
        m23303a();
    }
}
