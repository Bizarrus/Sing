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

public final class OpenCallListItem_ extends OpenCallListItem implements HasViews, OnViewChangedListener {
    private boolean f23106a = false;
    private final OnViewChangedNotifier f23107b = new OnViewChangedNotifier();

    class C46731 implements OnClickListener {
        final /* synthetic */ OpenCallListItem_ f23104a;

        C46731(OpenCallListItem_ openCallListItem_) {
            this.f23104a = openCallListItem_;
        }

        public void onClick(View view) {
            this.f23104a.m24344g();
        }
    }

    class C46742 implements OnClickListener {
        final /* synthetic */ OpenCallListItem_ f23105a;

        C46742(OpenCallListItem_ openCallListItem_) {
            this.f23105a = openCallListItem_;
        }

        public void onClick(View view) {
            this.f23105a.m24348k();
        }
    }

    public OpenCallListItem_(Context context) {
        super(context);
        m24388w();
    }

    public static OpenCallListItem m24387a(Context context) {
        OpenCallListItem openCallListItem_ = new OpenCallListItem_(context);
        openCallListItem_.onFinishInflate();
        return openCallListItem_;
    }

    public void onFinishInflate() {
        if (!this.f23106a) {
            this.f23106a = true;
            inflate(getContext(), C1947R.layout.open_call_list_item, this);
            this.f23107b.a(this);
        }
        super.onFinishInflate();
    }

    private void m24388w() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23107b);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24389a(HasViews hasViews) {
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
        if (this.s != null) {
            this.s.setOnClickListener(new C46731(this));
        }
        if (this.t != null) {
            this.t.setOnClickListener(new C46742(this));
        }
        m23042p();
    }
}
