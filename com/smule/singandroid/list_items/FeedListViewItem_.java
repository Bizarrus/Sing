package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FeedListViewItem_ extends FeedListViewItem implements HasViews, OnViewChangedListener {
    private boolean ag = false;
    private final OnViewChangedNotifier ah = new OnViewChangedNotifier();

    class C46551 implements OnClickListener {
        final /* synthetic */ FeedListViewItem_ f22974a;

        C46551(FeedListViewItem_ feedListViewItem_) {
            this.f22974a = feedListViewItem_;
        }

        public void onClick(View view) {
            this.f22974a.m24315e();
        }
    }

    public FeedListViewItem_(Context context) {
        super(context);
        m24328v();
    }

    public static FeedListViewItem m24327b(Context context) {
        FeedListViewItem feedListViewItem_ = new FeedListViewItem_(context);
        feedListViewItem_.onFinishInflate();
        return feedListViewItem_;
    }

    public void onFinishInflate() {
        if (!this.ag) {
            this.ag = true;
            inflate(getContext(), C1947R.layout.feed_list_view_item, this);
            this.ah.a(this);
        }
        super.onFinishInflate();
    }

    private void m24328v() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.ah);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24329a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.a = (ImageView) hasViews.findViewById(C1947R.id.mActionIcon);
        this.b = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mSoloPerformerProfilePic);
        this.c = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic1);
        this.d = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mProfilePic2);
        this.e = (ImageView) hasViews.findViewById(C1947R.id.mMoreIcon);
        this.f = (TextView) hasViews.findViewById(C1947R.id.mTimeIcon);
        this.g = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mHeaderProfilePic);
        this.i = (ImageView) hasViews.findViewById(C1947R.id.mHeaderIcon);
        this.j = (TextView) hasViews.findViewById(C1947R.id.mFeedTypeText);
        this.k = hasViews.findViewById(C1947R.id.mOpenCallView);
        this.l = (TextView) hasViews.findViewById(C1947R.id.mPerformer1);
        this.m = (TextView) hasViews.findViewById(C1947R.id.mPerformer2);
        this.n = hasViews.findViewById(C1947R.id.mSoloView);
        this.o = (TextView) hasViews.findViewById(C1947R.id.mSoloPerformer);
        this.p = (ImageButton) hasViews.findViewById(C1947R.id.mFollowButton);
        this.q = (ProgressBar) hasViews.findViewById(C1947R.id.mFollowProgress);
        this.r = hasViews.findViewById(C1947R.id.details_container);
        this.s = (TextView) hasViews.findViewById(C1947R.id.mSongNameText);
        this.t = (TextView) hasViews.findViewById(C1947R.id.mBodyText);
        this.u = (TextView) hasViews.findViewById(C1947R.id.mNumPlaysText);
        this.v = (TextView) hasViews.findViewById(C1947R.id.mLovesText);
        this.w = (TextView) hasViews.findViewById(C1947R.id.mCommentsText);
        this.x = (TextView) hasViews.findViewById(C1947R.id.mShareText);
        this.y = (Button) hasViews.findViewById(C1947R.id.mJoinButton);
        this.z = (ImageView) hasViews.findViewById(C1947R.id.mVipOnlyImageView);
        this.A = hasViews.findViewById(C1947R.id.mBottomActionView);
        this.B = (ImageView) hasViews.findViewById(C1947R.id.mBlurredAlbumArt);
        this.C = (RippleBackground) hasViews.findViewById(C1947R.id.mArtOuterContainer);
        this.D = (FrameLayout) hasViews.findViewById(C1947R.id.album_audio_container);
        this.E = (TextureView) hasViews.findViewById(C1947R.id.mVideoPlaybackView);
        this.F = hasViews.findViewById(C1947R.id.mVideoPlaybackMask);
        this.G = hasViews.findViewById(C1947R.id.mVideoLoadingContainer);
        this.H = (TextView) hasViews.findViewById(C1947R.id.item_tag);
        this.I = hasViews.findViewById(C1947R.id.item_tag_view);
        this.J = hasViews.findViewById(C1947R.id.arrangement_detail_container);
        this.K = (TextView) hasViews.findViewById(C1947R.id.arrangement_song_title_view);
        this.L = (TextView) hasViews.findViewById(C1947R.id.arrangement_artist_view);
        this.M = (TextView) hasViews.findViewById(C1947R.id.arrangement_rating_view);
        this.N = (TextView) hasViews.findViewById(C1947R.id.arrangement_lyrics_view);
        this.O = hasViews.findViewById(C1947R.id.arrangement_bottom_action_view);
        this.P = hasViews.findViewById(C1947R.id.arrangement_sing_button);
        this.Q = hasViews.findViewById(C1947R.id.arrangement_share_view);
        this.R = (ImageView) hasViews.findViewById(C1947R.id.video_play_icon);
        this.S = hasViews.findViewById(C1947R.id.album_container);
        if (this.e != null) {
            this.e.setOnClickListener(new C46551(this));
        }
        m23042p();
    }

    public void mo6875a(final boolean z, final boolean z2, final boolean z3) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ FeedListViewItem_ f22978d;

            public void run() {
                super.mo6875a(z, z2, z3);
            }
        }, 0);
    }
}
