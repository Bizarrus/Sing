package com.smule.singandroid.list_items;

import android.widget.CheckBox;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class UserInviteItem_ extends UserInviteItem implements HasViews, OnViewChangedListener {
    private boolean f23312f;
    private final OnViewChangedNotifier f23313g;

    public void onFinishInflate() {
        if (!this.f23312f) {
            this.f23312f = true;
            inflate(getContext(), C1947R.layout.user_invite_item, this);
            this.f23313g.a(this);
        }
        super.onFinishInflate();
    }

    public void m24518a(HasViews hasViews) {
        this.a = (TextView) hasViews.findViewById(C1947R.id.header);
        this.b = (TextView) hasViews.findViewById(C1947R.id.username);
        this.c = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_image_view);
        this.d = (CheckBox) hasViews.findViewById(C1947R.id.invite_checkbox);
        this.e = (TextView) hasViews.findViewById(C1947R.id.already_in_chat_textview);
    }
}
