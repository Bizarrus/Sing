package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.utils.PerformanceV2Util;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserSearchItem extends LinearLayout {
    @ViewById
    protected ProfileImageWithVIPBadge f23318a;
    @ViewById
    protected TextView f23319b;
    private AccountIcon f23320c;

    public static UserSearchItem m24520a(Context context) {
        return UserSearchItem_.m24523b(context);
    }

    public UserSearchItem(Context context) {
        super(context);
    }

    public AccountIcon getAccountIcon() {
        return this.f23320c;
    }

    public void m24521a(AccountIcon accountIcon) {
        this.f23320c = accountIcon;
        PerformanceV2Util.m25939a(getResources(), this.f23319b, this.f23320c);
        this.f23318a.setProfilePicUrl(this.f23320c.picUrl);
        this.f23318a.setVIP(this.f23320c.a());
    }
}
