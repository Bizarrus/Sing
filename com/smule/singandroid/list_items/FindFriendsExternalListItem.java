/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.magicui.ExternalFriendContainer;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.list_items.FindFriendsExternalListItem_;
import com.smule.singandroid.utils.PerformanceV2Util;
import java.text.MessageFormat;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FindFriendsExternalListItem
extends LinearLayout {
    ExternalFriendContainer a;
    @ViewById
    protected TextView b;
    @ViewById
    protected TextView c;
    @ViewById
    protected ImageView d;
    @ViewById
    protected ImageButton e;
    @ViewById
    protected ProgressBar f;
    protected ContainerDelegate g;
    private String h;

    public FindFriendsExternalListItem(Context context) {
        super(context);
    }

    public static FindFriendsExternalListItem a(Context object, String string2) {
        object = FindFriendsExternalListItem_.a((Context)object);
        object.h = string2;
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void a(View object) {
        this.f.setVisibility(0);
        this.e.setVisibility(8);
        final long l = this.a.c();
        object = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
        com.smule.android.logging.Analytics.a(object, l);
        FollowManager.a().a(l, this.g.getSocialContext(), this.g.getExternalName(), new FollowManager.ToggleFollowStateListener(){

            @Override
            public void a(boolean bl, boolean bl2, boolean bl3) {
                if (!FindFriendsExternalListItem.this.g.h()) {
                    return;
                }
                bl2 = FollowManager.a().a(l);
                FindFriendsExternalListItem.this.a(bl2);
                if (FindFriendsExternalListItem.this.g != null) {
                    FindFriendsExternalListItem.this.g.f();
                }
                if (bl) {
                    if (bl2) {
                        com.smule.android.utils.Toaster.a(FindFriendsExternalListItem.this.getContext(), MessageFormat.format(FindFriendsExternalListItem.this.getContext().getString(2131297195), FindFriendsExternalListItem.this.a.a()));
                        return;
                    }
                    com.smule.android.utils.Toaster.a(FindFriendsExternalListItem.this.getContext(), MessageFormat.format(FindFriendsExternalListItem.this.getContext().getString(2131297209), FindFriendsExternalListItem.this.a.a()));
                    return;
                }
                if (bl3) {
                    com.smule.android.utils.Toaster.a(FindFriendsExternalListItem.this.getContext(), FindFriendsExternalListItem.this.getResources().getString(2131297196));
                    return;
                }
                com.smule.android.utils.Toaster.a(FindFriendsExternalListItem.this.getContext(), 2131297210, Toaster.a);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(ExternalFriendContainer externalFriendContainer, ContainerDelegate containerDelegate, int n) {
        this.a = externalFriendContainer;
        this.c.setText((CharSequence)this.a.a());
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.b, (AccountIcon)this.a.d());
        this.g = containerDelegate;
        if (this.a.b() != null && this.a.b().length() != 0) {
            ImageUtils.a(this.a.b(), this.d, 2130837901, true);
        } else {
            this.d.setImageResource(2130837901);
        }
        this.d.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FindFriendsExternalListItem.this.callOnClick();
            }
        });
        this.a(FollowManager.a().a(this.a.c()));
    }

    @UiThread
    public void a(boolean bl) {
        this.f.setVisibility(8);
        this.e.setVisibility(0);
        this.e.setActivated(bl);
    }

    public ExternalFriendContainer getExternalFriend() {
        return this.a;
    }

    public static interface ContainerDelegate {
        public void f();

        public String getExternalName();

        public String getSocialContext();

        public boolean h();
    }

}

