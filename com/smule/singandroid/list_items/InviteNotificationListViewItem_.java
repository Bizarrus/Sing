package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.VideoUploadStatusView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class InviteNotificationListViewItem_ extends InviteNotificationListViewItem implements HasViews, OnViewChangedListener {
    private boolean f23041E = false;
    private final OnViewChangedNotifier f23042F = new OnViewChangedNotifier();

    class C46641 implements OnClickListener {
        final /* synthetic */ InviteNotificationListViewItem_ f23039a;

        C46641(InviteNotificationListViewItem_ inviteNotificationListViewItem_) {
            this.f23039a = inviteNotificationListViewItem_;
        }

        public void onClick(View view) {
            this.f23039a.m24344g();
        }
    }

    class C46652 implements OnClickListener {
        final /* synthetic */ InviteNotificationListViewItem_ f23040a;

        C46652(InviteNotificationListViewItem_ inviteNotificationListViewItem_) {
            this.f23040a = inviteNotificationListViewItem_;
        }

        public void onClick(View view) {
            this.f23040a.m24348k();
        }
    }

    public InviteNotificationListViewItem_(Context context) {
        super(context);
        m24359w();
    }

    public static InviteNotificationListViewItem m24358b(Context context) {
        InviteNotificationListViewItem inviteNotificationListViewItem_ = new InviteNotificationListViewItem_(context);
        inviteNotificationListViewItem_.onFinishInflate();
        return inviteNotificationListViewItem_;
    }

    public void onFinishInflate() {
        if (!this.f23041E) {
            this.f23041E = true;
            inflate(getContext(), C1947R.layout.invite_notification_list_item, this);
            this.f23042F.a(this);
        }
        super.onFinishInflate();
    }

    private void m24359w() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23042F);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24360a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.C = (VideoUploadStatusView) hasViews.findViewById(C1947R.id.video_status_view);
        this.f = hasViews.findViewById(C1947R.id.top_padding);
        this.g = (TextView) hasViews.findViewById(C1947R.id.mSongTitleTextView);
        this.h = (TextView) hasViews.findViewById(C1947R.id.mArtistNameTextView);
        this.i = (TextView) hasViews.findViewById(C1947R.id.mUserSangPartTextView);
        this.j = (TextView) hasViews.findViewById(C1947R.id.mMessageTextView);
        this.k = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic);
        this.l = (TextView) hasViews.findViewById(C1947R.id.mUsernameTextView);
        this.m = (Button) hasViews.findViewById(C1947R.id.mJoinButton);
        this.n = (ImageView) hasViews.findViewById(C1947R.id.mVipOnlyImageView);
        this.o = (TextView) hasViews.findViewById(C1947R.id.mExpireTimeTextView);
        this.p = (TextView) hasViews.findViewById(C1947R.id.mTimeIcon);
        this.q = hasViews.findViewById(C1947R.id.mMetadataView);
        this.r = hasViews.findViewById(C1947R.id.mOpenCallDividerLine);
        this.s = hasViews.findViewById(C1947R.id.mMoreIcon);
        this.t = (Button) hasViews.findViewById(C1947R.id.invite_singers_button);
        this.u = hasViews.findViewById(C1947R.id.song_details);
        this.v = hasViews.findViewById(C1947R.id.song_details_inner);
        this.w = hasViews.findViewById(C1947R.id.join_row);
        this.x = (TextView) hasViews.findViewById(C1947R.id.play_count_text_view);
        this.y = (TextView) hasViews.findViewById(C1947R.id.loves_count_text_view);
        this.z = hasViews.findViewById(C1947R.id.header);
        this.A = (TextView) hasViews.findViewById(C1947R.id.mHeaderTextView);
        this.B = hasViews.findViewById(C1947R.id.mHeaderBackground);
        this.a = (ImageView) hasViews.findViewById(C1947R.id.mActionIcon);
        this.b = hasViews.findViewById(C1947R.id.bottom_divider_line);
        if (this.s != null) {
            this.s.setOnClickListener(new C46641(this));
        }
        if (this.t != null) {
            this.t.setOnClickListener(new C46652(this));
        }
        m23042p();
    }
}
