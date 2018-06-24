package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserSearchItem_ extends UserSearchItem implements HasViews, OnViewChangedListener {
    private boolean f23321c = false;
    private final OnViewChangedNotifier f23322d = new OnViewChangedNotifier();

    public UserSearchItem_(Context context) {
        super(context);
        m24522a();
    }

    public static UserSearchItem m24523b(Context context) {
        UserSearchItem userSearchItem_ = new UserSearchItem_(context);
        userSearchItem_.onFinishInflate();
        return userSearchItem_;
    }

    public void onFinishInflate() {
        if (!this.f23321c) {
            this.f23321c = true;
            inflate(getContext(), C1947R.layout.user_search_item, this);
            this.f23322d.a(this);
        }
        super.onFinishInflate();
    }

    private void m24522a() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23322d);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24524a(HasViews hasViews) {
        this.a = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.mUserImageView);
        this.b = (TextView) hasViews.findViewById(C1947R.id.mUsernameTextView);
    }
}
