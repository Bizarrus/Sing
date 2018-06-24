/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$NotificationScreenType
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.NotificationItemAggObject;
import com.smule.android.network.models.NotificationItemObject;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.InviteNotificationListViewItem_;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class InviteNotificationListViewItem
extends OpenCallListItem
implements NotificationsFragment.NotificationItemInterface {
    private static final String E = InviteNotificationListViewItem.class.getSimpleName();
    private NotificationListItem F;
    private LocalizedShortNumberFormatter G;
    @ViewById
    ImageView a;
    @ViewById
    protected View b;

    public InviteNotificationListViewItem(Context context) {
        super(context);
    }

    public static InviteNotificationListViewItem a(Context context) {
        return InviteNotificationListViewItem_.b(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(NotificationListItem notificationListItem, SingAnalytics.NotificationScreenType notificationScreenType) {
        Object object;
        Object object2;
        if (notificationListItem.c().equals((Object)Notification.a)) {
            object2 = notificationListItem.object.performanceIcon;
            object = object2.performanceKey;
            object2 = notificationListItem.a().equals((Object)Notification.g) ? SingAnalytics.a((PerformanceV2)object2) : null;
        } else {
            object = notificationListItem.subjects.get((int)0).accountId;
            object2 = null;
        }
        SingAnalytics.a((Object)object, notificationListItem.a(), object2, (SingAnalytics.NotificationScreenType)notificationScreenType);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(BaseFragment baseFragment, final BaseFragment baseFragmentResponder, final NotificationListItem notificationListItem, SingAnalytics.NotificationScreenType notificationScreenType, Runnable runnable) {
        this.a(notificationListItem, notificationScreenType);
        if (notificationListItem.object != null) {
            ((NotificationsFragment)baseFragment).a(notificationListItem.object.performanceIcon);
            return;
        } else {
            if (notificationListItem.a() == Notification.d && notificationListItem.c() == Notification.b || notificationListItem.a() == Notification.e) {
                this.a(notificationListItem, notificationScreenType);
                baseFragment.a(ProfileFragment.a(notificationListItem.subjects.get(0)));
                return;
            }
            if (notificationListItem.a() != Notification.d) return;
            {
                ((MediaPlayingActivity)baseFragmentResponder).a(notificationListItem.object.performanceIcon, true, this.a(notificationListItem), new Runnable(){

                    @Override
                    public void run() {
                        NowPlayingFragment nowPlayingFragment;
                        PerformanceV2 performanceV2;
                        if (!(notificationListItem.a() != Notification.d || (nowPlayingFragment = ((MediaPlayingActivity)baseFragmentResponder).ad()) == null || (performanceV2 = nowPlayingFragment.S()) != null && performanceV2.message != null && performanceV2.message.equals(notificationListItem.content))) {
                            nowPlayingFragment.d("@" + notificationListItem.subjects.get((int)0).handle + " ");
                        }
                    }
                });
                return;
            }
        }
    }

    private boolean a(NotificationListItem notificationListItem) {
        switch (notificationListItem.a()) {
            default: {
                return false;
            }
            case g: 
        }
        return true;
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.G == null) {
            this.G = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.G;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(final NotificationsFragment notificationsFragment, BaseFragment object, final NotificationListItem notificationListItem, SingAnalytics.NotificationScreenType object2, Runnable runnable, boolean bl, boolean bl2) {
        this.F = notificationListItem;
        View view = this.b;
        int n = bl2 ? 4 : 0;
        view.setVisibility(n);
        this.w.setVisibility(8);
        if (notificationListItem.object != null) {
            this.setPerformance(notificationListItem.object.performanceIcon);
            if (notificationListItem.a() == Notification.f) {
                this.h.setVisibility(8);
            } else {
                this.h.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.D, (boolean)true));
            }
            this.u.setVisibility(0);
        } else {
            this.u.setVisibility(8);
        }
        object = new View.OnClickListener(object, notificationListItem, (SingAnalytics.NotificationScreenType)object2, runnable){
            final /* synthetic */ BaseFragment b;
            final /* synthetic */ NotificationListItem c;
            final /* synthetic */ SingAnalytics.NotificationScreenType d;
            final /* synthetic */ Runnable e;
            {
                this.b = baseFragmentResponder;
                this.c = notificationListItem;
                this.d = notificationScreenType;
                this.e = runnable;
            }

            public void onClick(View view) {
                InviteNotificationListViewItem.this.a(notificationsFragment, this.b, this.c, this.d, this.e);
                if (this.e != null) {
                    this.e.run();
                }
            }
        };
        this.setOnClickListener((View.OnClickListener)object);
        object2 = new View.OnClickListener(){

            public void onClick(View object) {
                object = notificationListItem.subjects.get(0);
                if (object != null) {
                    object = ProfileFragment.a((AccountIcon)object);
                    notificationsFragment.a((BaseFragment)((Object)object));
                }
            }
        };
        this.i.setVisibility(0);
        if (!TextUtils.isEmpty((CharSequence)notificationListItem.content)) {
            this.j.setVisibility(0);
            this.j.setMaxLines(4);
            this.j.setText((CharSequence)notificationListItem.content);
        } else {
            this.j.setVisibility(8);
        }
        n = LayoutUtils.a(notificationListItem.a());
        LayoutUtils.a((NotificationListItem)notificationListItem, (Context)this.getContext(), (BaseFragment)notificationsFragment, (TextView)this.i, (boolean)true, (View.OnClickListener)object, (View.OnClickListener)object2, (Runnable)new Runnable(){

            @Override
            public void run() {
                if (notificationListItem.aggObject == null) {
                    return;
                }
                NotificationsFragment notificationsFragment2 = NotificationsFragment.a(notificationListItem.aggObject.notificationKeys, null);
                notificationsFragment.a(notificationsFragment2, notificationListItem.aggObject.notificationKeys.toString());
            }
        });
        this.a.setImageDrawable(this.getResources().getDrawable(n));
        if (notificationListItem.subjects.size() > 0) {
            this.k.a((BaseFragment)notificationsFragment, notificationListItem.subjects.get(0));
        }
        this.p.setVisibility(0);
        this.p.setText((CharSequence)MiscUtils.a((long)notificationListItem.time, (boolean)false, (boolean)true, (boolean)false));
        this.b(bl);
        this.a(false);
        this.i();
    }

    protected void b(boolean bl) {
        if (bl) {
            this.setBackgroundResource(2130838154);
            this.u.setBackgroundResource(2130838154);
            this.v.setBackgroundResource(2130838154);
            return;
        }
        this.setBackgroundResource(2130838153);
        this.u.setBackgroundResource(2131689508);
        this.v.setBackgroundResource(2131689991);
    }

    public NotificationListItem getNotificationListItem() {
        return this.F;
    }

    @Override
    public PerformanceV2 getPerformance() {
        return this.D;
    }

    public void setOnAlbumArtClickListener(final View.OnClickListener onClickListener) {
        this.ae.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
    }

    public void setOnJoinListener(final View.OnClickListener onClickListener) {
        this.m.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
    }

    @Override
    public void z_() {
        this.b(false);
    }

}

