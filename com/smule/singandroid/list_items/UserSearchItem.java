/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.UserSearchItem_;
import com.smule.singandroid.utils.PerformanceV2Util;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserSearchItem
extends LinearLayout {
    @ViewById
    protected ProfileImageWithVIPBadge a;
    @ViewById
    protected TextView b;
    private AccountIcon c;

    public UserSearchItem(Context context) {
        super(context);
    }

    public static UserSearchItem a(Context context) {
        return UserSearchItem_.b(context);
    }

    public void a(AccountIcon accountIcon) {
        this.c = accountIcon;
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.b, (AccountIcon)this.c);
        this.a.setProfilePicUrl(this.c.picUrl);
        this.a.setVIP(this.c.a());
    }

    public AccountIcon getAccountIcon() {
        return this.c;
    }
}

