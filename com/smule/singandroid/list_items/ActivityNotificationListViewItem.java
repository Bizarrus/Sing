/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$NotificationScreenType
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.NotificationItemAggObject;
import com.smule.android.network.models.NotificationItemObject;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.customviews.PlayableItemDetailsView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CommentsListDialog;
import com.smule.singandroid.list_items.ActivityNotificationListViewItem_;
import com.smule.singandroid.list_items.CommentItem;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ActivityNotificationListViewItem
extends FrameLayout
implements NotificationsFragment.NotificationItemInterface,
MediaPlayingViewInterface {
    private static final String n = ActivityNotificationListViewItem.class.getSimpleName();
    protected ImageUtils a = new Object(){
        public String a;

        public void a() {
            this.a = null;
        }

        public void a(ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader.a().a(imageView);
        }

        public boolean a(String string2, ImageView imageView, int n) {
            return this.a(string2, imageView, n, false);
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl);
                this.a = string2;
                return true;
            }
            return false;
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl, int n2) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl, -1, n2, null, true);
                this.a = string2;
                return true;
            }
            return false;
        }
    };
    @ViewById
    ImageView b;
    @ViewById
    FrameLayout c;
    @ViewById
    ProgressBar d;
    @ViewById
    ImageView e;
    @ViewById
    TextView f;
    @ViewById
    ImageView g;
    @ViewById
    protected View h;
    @ViewById
    protected PlayableItemDetailsView i;
    @ViewById
    protected ProfileImageWithVIPBadge j;
    @ViewById
    protected TextView k;
    @ViewById
    protected TextView l;
    protected PerformanceV2 m;
    private boolean o;

    public ActivityNotificationListViewItem(Context context) {
        super(context);
    }

    public static ActivityNotificationListViewItem a(Context context) {
        return ActivityNotificationListViewItem_.b(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(com.smule.android.network.models.NotificationListItem notificationListItem, SingAnalytics.NotificationScreenType notificationScreenType) {
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
    private void a(final BaseFragment baseFragment, com.smule.android.network.models.NotificationListItem object) {
        PerformanceV2 performanceV2 = null;
        Object object2 = performanceV2;
        if (object != null) {
            object2 = performanceV2;
            if (!object.subjects.isEmpty()) {
                object2 = performanceV2;
                if (object.count == 1) {
                    object2 = "@" + object.subjects.get((int)0).handle + " ";
                    Log.b(n, "listItem : " + object.toString());
                }
            }
        }
        performanceV2 = this.m;
        long l = object != null ? object.time : -1;
        object = new com.smule.singandroid.dialogs.CommentsListDialog(baseFragment, performanceV2, (String)object2, l);
        object.a(new CommentsListDialog((com.smule.singandroid.dialogs.CommentsListDialog)object){
            final /* synthetic */ com.smule.singandroid.dialogs.CommentsListDialog b;
            {
                this.b = commentsListDialog;
            }

            @Override
            public void a(CommentItem object, PerformancePost performancePost) {
                object = ProfileFragment.a(performancePost.accountIcon);
                baseFragment.a((BaseFragment)((Object)object));
                this.b.dismiss();
            }
        });
        object.show();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(BaseFragment baseFragment, BaseFragment object, com.smule.android.network.models.NotificationListItem notificationListItem, SingAnalytics.NotificationScreenType object2, Runnable object3, boolean bl) {
        boolean bl2 = false;
        this.a(notificationListItem, (SingAnalytics.NotificationScreenType)object2);
        object2 = notificationListItem.a();
        object3 = notificationListItem.b();
        if (notificationListItem.object != null && !bl && !this.a((Object)object3)) {
            if (object2 == Notification.d) {
                ((MediaPlayingActivity)object).a(notificationListItem.object.performanceIcon, true, this.a((Object)object2), new Runnable(object, (Object)object3, notificationListItem){
                    final /* synthetic */ BaseFragment a;
                    final /* synthetic */ NotificationListItem b;
                    final /* synthetic */ com.smule.android.network.models.NotificationListItem c;
                    {
                        this.a = baseFragmentResponder;
                        this.b = detailedType;
                        this.c = notificationListItem;
                    }

                    @Override
                    public void run() {
                        NowPlayingFragment nowPlayingFragment = ((MediaPlayingActivity)this.a).ad();
                        if (nowPlayingFragment != null && this.b == NotificationListItem.g) {
                            nowPlayingFragment.d("@" + this.c.subjects.get((int)0).handle + " ");
                            nowPlayingFragment.g(true);
                        }
                    }
                });
                return;
            }
            if (this.b((Object)object3)) {
                this.a(baseFragment, notificationListItem);
                return;
            }
            bl = bl2;
            if (object2 != Notification.a) {
                bl = true;
            }
            object.a(notificationListItem.object.performanceIcon, true, bl);
            return;
        }
        object = object2 == Notification.j ? UserManager.a().O() : notificationListItem.subjects.get(0);
        if (object == null) return;
        baseFragment.a(ProfileFragment.a((AccountIcon)object));
    }

    private boolean a(Notification type) {
        switch (type) {
            default: {
                return false;
            }
            case g: 
        }
        return true;
    }

    private boolean a(NotificationListItem detailedType) {
        if (detailedType == NotificationListItem.k && this.o || detailedType == NotificationListItem.c || detailedType == NotificationListItem.a) {
            return true;
        }
        return false;
    }

    private boolean b(Notification type) {
        if (type == Notification.e || type == Notification.h || type == Notification.i) {
            return true;
        }
        return false;
    }

    private boolean b(NotificationListItem detailedType) {
        if (detailedType == NotificationListItem.e || detailedType == NotificationListItem.f || detailedType == NotificationListItem.g) {
            return true;
        }
        return false;
    }

    @UiThread
    protected void a(com.smule.android.network.models.NotificationListItem object, boolean bl, boolean bl2, boolean bl3) {
        block4 : {
            block6 : {
                block7 : {
                    block5 : {
                        if (!this.b(object.a())) break block4;
                        object = object.subjects.get(0);
                        boolean bl4 = FollowManager.a().a(object.accountId);
                        this.d.setVisibility(8);
                        this.e.setVisibility(0);
                        this.e.setActivated(bl4);
                        if (!bl3) break block5;
                        if (!bl) break block6;
                        if (!bl4) break block7;
                        Toaster.a(this.getContext(), MessageFormat.format(this.getContext().getString(2131297195), object.handle));
                    }
                    return;
                }
                Toaster.a(this.getContext(), MessageFormat.format(this.getContext().getString(2131297209), object.handle));
                return;
            }
            if (bl2) {
                Toaster.a(this.getContext(), this.getResources().getString(2131297196));
                return;
            }
            Toaster.a(this.getContext(), this.getContext().getString(2131296895));
            return;
        }
        this.e.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(final NotificationsFragment notificationsFragment, final BaseFragment baseFragmentResponder, final com.smule.android.network.models.NotificationListItem notificationListItem, final SingAnalytics.NotificationScreenType notificationScreenType, final Runnable runnable, boolean bl, boolean bl2) {
        this.g.setVisibility(8);
        Object object = this.h;
        int n = bl2 ? 4 : 0;
        object.setVisibility(n);
        this.o = notificationsFragment.L();
        if (notificationListItem.object != null && !this.o) {
            this.m = notificationListItem.object.performanceIcon;
            this.i.a(this.m, true);
            this.i.setVisibility(0);
            object = this.i;
            bl = notificationListItem.a() != Notification.j;
            object.a(bl);
        } else {
            this.m = null;
            this.i.setVisibility(8);
        }
        object = new View.OnClickListener(){

            public void onClick(View view) {
                ActivityNotificationListViewItem.this.a(notificationsFragment, baseFragmentResponder, notificationListItem, notificationScreenType, runnable, false);
                if (runnable != null) {
                    runnable.run();
                }
            }
        };
        this.setOnClickListener((View.OnClickListener)object);
        this.k.setMaxLines(4);
        final Notification type = notificationListItem.a();
        n = LayoutUtils.a(type);
        LayoutUtils.a((com.smule.android.network.models.NotificationListItem)notificationListItem, (Context)this.getContext(), (BaseFragment)notificationsFragment, (TextView)this.k, (boolean)true, (View.OnClickListener)object, (View.OnClickListener)new View.OnClickListener(){

            public void onClick(View view) {
                ActivityNotificationListViewItem.this.a(notificationsFragment, baseFragmentResponder, notificationListItem, notificationScreenType, runnable, true);
                if (runnable != null) {
                    runnable.run();
                }
            }
        }, (Runnable)new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                if (notificationListItem.aggObject == null) {
                    return;
                }
                if (notificationListItem.b() == NotificationListItem.e) {
                    ActivityNotificationListViewItem.this.a(notificationsFragment, notificationListItem);
                    return;
                }
                boolean bl = type == Notification.a || type == Notification.b || notificationListItem.b() == NotificationListItem.k;
                NotificationsFragment notificationsFragment2 = NotificationsFragment.a(notificationListItem.aggObject.notificationKeys, bl, notificationListItem.b());
                notificationsFragment.a(notificationsFragment2);
            }
        });
        if (this.o) {
            if (type == Notification.b) {
                notificationListItem.a(NotificationListItem.c);
            } else if (type == Notification.a) {
                notificationListItem.a(NotificationListItem.a);
            }
        }
        this.b.setImageDrawable(this.getResources().getDrawable(n));
        if (notificationListItem.subjects.size() > 0) {
            this.j.a((BaseFragment)notificationsFragment, notificationListItem.subjects.get(0));
        } else if (type == Notification.j) {
            this.j.a((BaseFragment)notificationsFragment, UserManager.a().O());
        }
        if (this.b(type)) {
            this.f.setVisibility(0);
            this.l.setVisibility(4);
            this.f.setText((CharSequence)MiscUtils.a((long)notificationListItem.time, (boolean)true, (boolean)false, (boolean)true));
            this.e.setVisibility(0);
            final long l = notificationListItem.subjects.get((int)0).accountId;
            this.c.setOnClickListener(new View.OnClickListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View object) {
                    ActivityNotificationListViewItem.this.a(notificationListItem, notificationScreenType);
                    ActivityNotificationListViewItem.this.d.setVisibility(0);
                    ActivityNotificationListViewItem.this.e.setVisibility(8);
                    object = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
                    com.smule.android.logging.Analytics.a(object, l);
                    FollowManager.a().a((Long)l, new FollowManager.ToggleFollowStateListener(){

                        @Override
                        public void a(boolean bl, boolean bl2, boolean bl3) {
                            ActivityNotificationListViewItem.this.a(notificationListItem, bl, bl3, true);
                        }
                    });
                }

            });
        } else {
            this.f.setVisibility(8);
            this.e.setVisibility(8);
            if (this.b(notificationListItem.b())) {
                this.g.setVisibility(0);
                this.f.setVisibility(0);
                this.l.setVisibility(4);
                this.f.setText((CharSequence)MiscUtils.a((long)notificationListItem.time, (boolean)true, (boolean)false, (boolean)true));
                this.g.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        ActivityNotificationListViewItem.this.a(notificationsFragment, notificationListItem);
                    }
                });
            } else {
                this.l.setVisibility(0);
                this.l.setText((CharSequence)MiscUtils.a((long)notificationListItem.time, (boolean)true, (boolean)false, (boolean)true));
            }
        }
        this.a(notificationListItem, true, false, false);
    }

    public String getMediaKey() {
        return this.i.getMediaKey();
    }

    public PerformanceV2 getPerformance() {
        return this.m;
    }

    @Override
    public void r_() {
        this.i.r_();
    }

    @Override
    public void s_() {
    }

    public void setOnAlbumArtClickListener(final View.OnClickListener onClickListener) {
        this.i.setOnAlbumArtClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
    }

    @Override
    public void z_() {
    }

}

