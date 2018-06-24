package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FeedListFriendsNotificationView_ extends FeedListFriendsNotificationView implements HasViews, OnViewChangedListener {
    private boolean f22865k = false;
    private final OnViewChangedNotifier f22866l = new OnViewChangedNotifier();

    class C46391 implements OnClickListener {
        final /* synthetic */ FeedListFriendsNotificationView_ f22863a;

        C46391(FeedListFriendsNotificationView_ feedListFriendsNotificationView_) {
            this.f22863a = feedListFriendsNotificationView_;
        }

        public void onClick(View view) {
            this.f22863a.m24277d();
        }
    }

    class C46402 implements OnClickListener {
        final /* synthetic */ FeedListFriendsNotificationView_ f22864a;

        C46402(FeedListFriendsNotificationView_ feedListFriendsNotificationView_) {
            this.f22864a = feedListFriendsNotificationView_;
        }

        public void onClick(View view) {
            this.f22864a.m24278e();
        }
    }

    public FeedListFriendsNotificationView_(Context context) {
        super(context);
        m24281g();
    }

    public static FeedListFriendsNotificationView m24280a(Context context) {
        FeedListFriendsNotificationView feedListFriendsNotificationView_ = new FeedListFriendsNotificationView_(context);
        feedListFriendsNotificationView_.onFinishInflate();
        return feedListFriendsNotificationView_;
    }

    public void onFinishInflate() {
        if (!this.f22865k) {
            this.f22865k = true;
            inflate(getContext(), C1947R.layout.feed_list_friends_notification_view, this);
            this.f22866l.a(this);
        }
        super.onFinishInflate();
    }

    private void m24281g() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22866l);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24282a(HasViews hasViews) {
        this.a = (ImageView) hasViews.findViewById(C1947R.id.header_profile_pic);
        this.b = hasViews.findViewById(C1947R.id.close_button);
        this.c = (TextView) hasViews.findViewById(C1947R.id.title_text);
        this.d = (TextView) hasViews.findViewById(C1947R.id.description_text);
        this.e = (ViewGroup) hasViews.findViewById(C1947R.id.content_view);
        this.g = (Button) hasViews.findViewById(C1947R.id.action_button);
        List arrayList = new ArrayList();
        ProfileImageWithVIPBadge profileImageWithVIPBadge = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_pic_1);
        ProfileImageWithVIPBadge profileImageWithVIPBadge2 = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_pic_2);
        ProfileImageWithVIPBadge profileImageWithVIPBadge3 = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_pic_3);
        ProfileImageWithVIPBadge profileImageWithVIPBadge4 = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_pic_4);
        ProfileImageWithVIPBadge profileImageWithVIPBadge5 = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_pic_5);
        View findViewById = hasViews.findViewById(C1947R.id.card_view);
        if (profileImageWithVIPBadge != null) {
            arrayList.add(profileImageWithVIPBadge);
        }
        if (profileImageWithVIPBadge2 != null) {
            arrayList.add(profileImageWithVIPBadge2);
        }
        if (profileImageWithVIPBadge3 != null) {
            arrayList.add(profileImageWithVIPBadge3);
        }
        if (profileImageWithVIPBadge4 != null) {
            arrayList.add(profileImageWithVIPBadge4);
        }
        if (profileImageWithVIPBadge5 != null) {
            arrayList.add(profileImageWithVIPBadge5);
        }
        this.f = arrayList;
        if (this.b != null) {
            this.b.setOnClickListener(new C46391(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C46402(this));
        }
        m24274a();
    }
}
