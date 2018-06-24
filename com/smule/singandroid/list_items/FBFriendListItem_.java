package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FBFriendListItem_ extends FBFriendListItem implements HasViews, OnViewChangedListener {
    private boolean f22844i = false;
    private final OnViewChangedNotifier f22845j = new OnViewChangedNotifier();

    class C46341 implements OnClickListener {
        final /* synthetic */ FBFriendListItem_ f22841a;

        C46341(FBFriendListItem_ fBFriendListItem_) {
            this.f22841a = fBFriendListItem_;
        }

        public void onClick(View view) {
            this.f22841a.m24260a(view);
        }
    }

    public FBFriendListItem_(Context context) {
        super(context);
        m24263a();
    }

    public static FBFriendListItem m24265b(Context context) {
        FBFriendListItem fBFriendListItem_ = new FBFriendListItem_(context);
        fBFriendListItem_.onFinishInflate();
        return fBFriendListItem_;
    }

    public void onFinishInflate() {
        if (!this.f22844i) {
            this.f22844i = true;
            inflate(getContext(), C1947R.layout.fb_friend_list_item, this);
            this.f22845j.a(this);
        }
        super.onFinishInflate();
    }

    private void m24263a() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22845j);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24266a(HasViews hasViews) {
        this.b = (TextView) hasViews.findViewById(C1947R.id.user_name_text_view);
        this.c = (TextView) hasViews.findViewById(C1947R.id.fb_username_text_view);
        this.d = (ImageView) hasViews.findViewById(C1947R.id.facebook_friend_image_view);
        this.e = (ImageButton) hasViews.findViewById(C1947R.id.action_button);
        this.f = (ProgressBar) hasViews.findViewById(C1947R.id.action_progress);
        if (this.e != null) {
            this.e.setOnClickListener(new C46341(this));
        }
    }

    public void mo6870a(final boolean z) {
        UiThreadExecutor.a("", new Runnable(this) {
            final /* synthetic */ FBFriendListItem_ f22843b;

            public void run() {
                super.mo6870a(z);
            }
        }, 0);
    }
}
