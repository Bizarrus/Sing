/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.FeedListFriendsNotificationView;
import java.util.ArrayList;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FeedListFriendsNotificationView_
extends FeedListFriendsNotificationView
implements HasViews,
OnViewChangedListener {
    private boolean k = false;
    private final OnViewChangedNotifier l = new OnViewChangedNotifier();

    public FeedListFriendsNotificationView_(Context context) {
        super(context);
        this.g();
    }

    public static FeedListFriendsNotificationView a(Context object) {
        object = new FeedListFriendsNotificationView_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void g() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.l);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (ImageView)hasViews.findViewById(2131755745);
        this.b = hasViews.findViewById(2131755294);
        this.c = (TextView)hasViews.findViewById(2131755398);
        this.d = (TextView)hasViews.findViewById(2131755747);
        this.e = (ViewGroup)hasViews.findViewById(2131755240);
        this.g = (Button)hasViews.findViewById(2131755748);
        ArrayList<ProfileImageWithVIPBadge> arrayList = new ArrayList<ProfileImageWithVIPBadge>();
        ProfileImageWithVIPBadge profileImageWithVIPBadge = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755400);
        ProfileImageWithVIPBadge profileImageWithVIPBadge2 = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755401);
        ProfileImageWithVIPBadge profileImageWithVIPBadge3 = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755402);
        ProfileImageWithVIPBadge profileImageWithVIPBadge4 = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755403);
        ProfileImageWithVIPBadge profileImageWithVIPBadge5 = (ProfileImageWithVIPBadge)hasViews.findViewById(2131755404);
        hasViews = hasViews.findViewById(2131755744);
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
            this.b.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FeedListFriendsNotificationView_.this.d();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FeedListFriendsNotificationView_.this.e();
                }
            });
        }
        this.a();
    }

    public void onFinishInflate() {
        if (!this.k) {
            this.k = true;
            FeedListFriendsNotificationView_.inflate((Context)this.getContext(), (int)2130903227, (ViewGroup)this);
            this.l.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

