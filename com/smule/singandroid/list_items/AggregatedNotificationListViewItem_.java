package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.MoreProfilesView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class AggregatedNotificationListViewItem_ extends AggregatedNotificationListViewItem implements HasViews, OnViewChangedListener {
    private boolean f22786n = false;
    private final OnViewChangedNotifier f22787o = new OnViewChangedNotifier();

    public AggregatedNotificationListViewItem_(Context context) {
        super(context);
        m24231b();
    }

    public static AggregatedNotificationListViewItem m24230b(Context context) {
        AggregatedNotificationListViewItem aggregatedNotificationListViewItem_ = new AggregatedNotificationListViewItem_(context);
        aggregatedNotificationListViewItem_.onFinishInflate();
        return aggregatedNotificationListViewItem_;
    }

    public void onFinishInflate() {
        if (!this.f22786n) {
            this.f22786n = true;
            inflate(getContext(), C1947R.layout.aggregated_notification_list_item, this);
            this.f22787o.a(this);
        }
        super.onFinishInflate();
    }

    private void m24231b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22787o);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24232a(HasViews hasViews) {
        this.a = (ImageView) hasViews.findViewById(C1947R.id.mActionIcon);
        this.b = (ImageView) hasViews.findViewById(C1947R.id.mVideoMore);
        this.c = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic);
        this.d = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic1);
        this.e = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic2);
        this.f = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic3);
        this.g = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic4);
        this.h = hasViews.findViewById(C1947R.id.mProfilePicList);
        this.i = (MoreProfilesView) hasViews.findViewById(C1947R.id.mProfilePicMore);
        this.j = (TextView) hasViews.findViewById(C1947R.id.mTimeIcon);
        this.k = (TextView) hasViews.findViewById(C1947R.id.mTextView);
        this.l = hasViews.findViewById(C1947R.id.bottom_divider_line);
    }
}
