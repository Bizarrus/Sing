package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.network.models.NotificationListItem;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemDetailsView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ActivityNotificationListViewItem_ extends ActivityNotificationListViewItem implements HasViews, OnViewChangedListener {
    private boolean f22754n = false;
    private final OnViewChangedNotifier f22755o = new OnViewChangedNotifier();

    public ActivityNotificationListViewItem_(Context context) {
        super(context);
        m24220c();
    }

    public static ActivityNotificationListViewItem m24219b(Context context) {
        ActivityNotificationListViewItem activityNotificationListViewItem_ = new ActivityNotificationListViewItem_(context);
        activityNotificationListViewItem_.onFinishInflate();
        return activityNotificationListViewItem_;
    }

    public void onFinishInflate() {
        if (!this.f22754n) {
            this.f22754n = true;
            inflate(getContext(), C1947R.layout.activity_notification_list_item, this);
            this.f22755o.a(this);
        }
        super.onFinishInflate();
    }

    private void m24220c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22755o);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24222a(HasViews hasViews) {
        this.b = (ImageView) hasViews.findViewById(C1947R.id.mActionIcon);
        this.c = (FrameLayout) hasViews.findViewById(C1947R.id.action_holder);
        this.d = (ProgressBar) hasViews.findViewById(C1947R.id.follow_progress);
        this.e = (ImageView) hasViews.findViewById(C1947R.id.follow_button);
        this.f = (TextView) hasViews.findViewById(C1947R.id.follow_time_icon);
        this.g = (ImageView) hasViews.findViewById(C1947R.id.comment_action_button);
        this.h = hasViews.findViewById(C1947R.id.bottom_divider_line);
        this.i = (PlayableItemDetailsView) hasViews.findViewById(C1947R.id.song_details);
        this.j = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic);
        this.k = (TextView) hasViews.findViewById(C1947R.id.mMessageTextView);
        this.l = (TextView) hasViews.findViewById(C1947R.id.mTimeIcon);
    }

    protected void mo6866a(NotificationListItem notificationListItem, boolean z, boolean z2, boolean z3) {
        final NotificationListItem notificationListItem2 = notificationListItem;
        final boolean z4 = z;
        final boolean z5 = z2;
        final boolean z6 = z3;
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ ActivityNotificationListViewItem_ f22753e;

            public void run() {
                super.mo6866a(notificationListItem2, z4, z5, z6);
            }
        }, 0);
    }
}
