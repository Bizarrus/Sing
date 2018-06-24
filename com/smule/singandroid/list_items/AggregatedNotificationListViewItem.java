/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$NotificationScreenType
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.NotificationItemAggObject;
import com.smule.android.network.models.NotificationItemObject;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.customviews.MoreProfilesView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.AggregatedNotificationListViewItem_;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class AggregatedNotificationListViewItem
extends LinearLayout
implements NotificationsFragment.NotificationItemInterface {
    private static final String n = AggregatedNotificationListViewItem.class.getSimpleName();
    @ViewById
    ImageView a;
    @ViewById
    ImageView b;
    @ViewById
    ProfileImageWithVIPBadge c;
    @ViewById
    ProfileImageWithVIPBadge d;
    @ViewById
    ProfileImageWithVIPBadge e;
    @ViewById
    ProfileImageWithVIPBadge f;
    @ViewById
    ProfileImageWithVIPBadge g;
    @ViewById
    View h;
    @ViewById
    MoreProfilesView i;
    @ViewById
    TextView j;
    @ViewById
    TextView k;
    @ViewById
    protected View l;
    Notification m;

    public AggregatedNotificationListViewItem(Context context) {
        super(context);
    }

    public static AggregatedNotificationListViewItem a(Context context) {
        return AggregatedNotificationListViewItem_.b(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(com.smule.android.network.models.NotificationListItem notificationListItem, SingAnalytics.NotificationScreenType notificationScreenType) {
        String string2;
        Analytics ensemble = null;
        if (notificationListItem.c().equals((Object)Notification.a) && notificationListItem.object != null) {
            String string3;
            PerformanceV2 performanceV2 = notificationListItem.object.performanceIcon;
            string2 = string3 = performanceV2.performanceKey;
            if (notificationListItem.a().equals((Object)Notification.g)) {
                ensemble = SingAnalytics.a((PerformanceV2)performanceV2);
                string2 = string3;
            }
        } else {
            string2 = null;
        }
        SingAnalytics.a((String)string2, notificationListItem.a(), ensemble, (SingAnalytics.NotificationScreenType)notificationScreenType);
    }

    private void a(BaseFragment baseFragment, ProfileImageWithVIPBadge profileImageWithVIPBadge, com.smule.android.network.models.NotificationListItem object, int n) {
        if (n >= 0 && n < object.subjects.size()) {
            object = object.subjects.get(n);
            profileImageWithVIPBadge.setVisibility(0);
            if (object != null) {
                profileImageWithVIPBadge.a((AccountIcon)object, new View.OnClickListener((AccountIcon)object, baseFragment){
                    final /* synthetic */ AccountIcon a;
                    final /* synthetic */ BaseFragment b;
                    {
                        this.a = accountIcon;
                        this.b = baseFragment;
                    }

                    public void onClick(View object) {
                        object = ProfileFragment.a(this.a);
                        this.b.a((BaseFragment)((Object)object));
                    }
                });
            }
            return;
        }
        profileImageWithVIPBadge.setVisibility(8);
    }

    private boolean b() {
        float f;
        float f2;
        int n = this.getResources().getDisplayMetrics().widthPixels;
        float f3 = this.getResources().getDimension(2131428169);
        if ((float)n > f3 + ((f = this.getResources().getDimension(2131427618)) + 5.0f * ((f2 = this.getResources().getDimension(2131427735)) + f3))) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(NotificationsFragment object, BaseFragment object2, final com.smule.android.network.models.NotificationListItem notificationListItem, SingAnalytics.NotificationScreenType object3, Runnable runnable, boolean bl, boolean bl2) {
        View view = this.l;
        int n = bl2 ? 4 : 0;
        view.setVisibility(n);
        object2 = new View.OnClickListener((SingAnalytics.NotificationScreenType)object3, object2, (NotificationsFragment)((Object)object), runnable){
            final /* synthetic */ SingAnalytics.NotificationScreenType b;
            final /* synthetic */ BaseFragment c;
            final /* synthetic */ NotificationsFragment d;
            final /* synthetic */ Runnable e;
            {
                this.b = notificationScreenType;
                this.c = baseFragmentResponder;
                this.d = notificationsFragment;
                this.e = runnable;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                AggregatedNotificationListViewItem.this.a(notificationListItem, this.b);
                if (notificationListItem.a() == Notification.a) {
                    this.c.a(notificationListItem.object.performanceIcon, true, false);
                } else if (notificationListItem.a() == Notification.c) {
                    ((MediaPlayingActivity)this.c).a(notificationListItem.object.performanceIcon, true, true, new Runnable(){

                        @Override
                        public void run() {
                            PerformanceV2 performanceV2;
                            NowPlayingFragment nowPlayingFragment = ((MediaPlayingActivity)1.this.c).ad();
                            if (!(nowPlayingFragment == null || (performanceV2 = nowPlayingFragment.S()) != null && performanceV2.message != null && performanceV2.message.equals(notificationListItem.content))) {
                                nowPlayingFragment.d("");
                                nowPlayingFragment.g(true);
                            }
                        }
                    });
                } else {
                    object = NotificationsFragment.a(notificationListItem.aggObject.notificationKeys, notificationListItem.b());
                    this.d.a((BaseFragment)((Object)object), notificationListItem.aggObject.notificationKeys.toString());
                }
                if (this.e != null) {
                    this.e.run();
                }
            }

        };
        object3 = new View.OnClickListener((NotificationsFragment)((Object)object)){
            final /* synthetic */ NotificationsFragment b;
            {
                this.b = notificationsFragment;
            }

            public void onClick(View object) {
                object = notificationListItem.subjects.get(0);
                if (object != null) {
                    object = ProfileFragment.a((AccountIcon)object);
                    this.b.a((BaseFragment)((Object)object));
                }
            }
        };
        this.setOnClickListener((View.OnClickListener)object2);
        n = LayoutUtils.a(notificationListItem.a());
        LayoutUtils.a((com.smule.android.network.models.NotificationListItem)notificationListItem, (Context)this.getContext(), (BaseFragment)((Object)object), (TextView)this.k, (boolean)false, (View.OnClickListener)object2, (View.OnClickListener)object3, (Runnable)null);
        if (notificationListItem.a() == Notification.j) {
            this.b.setVisibility(0);
            LayoutUtils.a((View)this.k, (boolean)true);
            LayoutUtils.a((View)this.b, (boolean)true);
            this.h.setVisibility(8);
            this.j.setVisibility(8);
        } else {
            this.b.setVisibility(8);
            LayoutUtils.a((View)this.k, (boolean)false);
            LayoutUtils.a((View)this.b, (boolean)false);
            this.h.setVisibility(0);
            this.j.setVisibility(0);
        }
        if (notificationListItem.subjects.size() > 0 && (object2 = notificationListItem.subjects.get(0)) != null) {
            this.c.a((AccountIcon)object2, new View.OnClickListener((AccountIcon)object2, (NotificationsFragment)((Object)object)){
                final /* synthetic */ AccountIcon a;
                final /* synthetic */ NotificationsFragment b;
                {
                    this.a = accountIcon;
                    this.b = notificationsFragment;
                }

                public void onClick(View object) {
                    object = ProfileFragment.a(this.a);
                    this.b.a((BaseFragment)((Object)object));
                }
            });
        }
        this.j.setText((CharSequence)MiscUtils.a((long)notificationListItem.time, (boolean)true, (boolean)false, (boolean)true));
        this.a.setImageDrawable(this.getResources().getDrawable(n));
        this.a((BaseFragment)((Object)object), this.d, notificationListItem, 1);
        this.a((BaseFragment)((Object)object), this.e, notificationListItem, 2);
        int n2 = 1;
        n = 1;
        if (this.b()) {
            this.a((BaseFragment)((Object)object), this.f, notificationListItem, 3);
            if (notificationListItem.count > 5) {
                this.a((BaseFragment)((Object)object), this.g, notificationListItem, -1);
            } else {
                this.a((BaseFragment)((Object)object), this.g, notificationListItem, 4);
                n = 0;
            }
        } else {
            if (notificationListItem.subjects.size() > 4) {
                this.a((BaseFragment)((Object)object), this.f, notificationListItem, -1);
                n = n2;
            } else {
                this.a((BaseFragment)((Object)object), this.f, notificationListItem, 3);
                n = 0;
            }
            this.a((BaseFragment)((Object)object), this.g, notificationListItem, -1);
        }
        object = this.i;
        n = n != 0 ? 0 : 8;
        object.setVisibility(n);
    }

    public Notification getAggregatedType() {
        return this.m;
    }

    public void setAggregatedType(Notification type) {
        this.m = type;
    }

    @Override
    public void z_() {
    }

}

