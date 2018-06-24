package com.smule.singandroid.list_items;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification$EntityType;
import com.smule.android.network.models.Notification$Type;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.NotificationsFragment.NotificationItemInterface;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.NotificationScreenType;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class InviteNotificationListViewItem extends OpenCallListItem implements NotificationItemInterface {
    private static final String f23034E = InviteNotificationListViewItem.class.getSimpleName();
    private NotificationListItem f23035F;
    private LocalizedShortNumberFormatter f23036G;
    @ViewById
    ImageView f23037a;
    @ViewById
    protected View f23038b;

    public InviteNotificationListViewItem(Context context) {
        super(context);
    }

    public static InviteNotificationListViewItem m24350a(Context context) {
        return InviteNotificationListViewItem_.m24358b(context);
    }

    public void mo6865a(final NotificationsFragment notificationsFragment, BaseFragmentResponder baseFragmentResponder, final NotificationListItem notificationListItem, NotificationScreenType notificationScreenType, Runnable runnable, boolean z, boolean z2) {
        this.f23035F = notificationListItem;
        this.f23038b.setVisibility(z2 ? 4 : 0);
        this.w.setVisibility(8);
        if (notificationListItem.object != null) {
            setPerformance(notificationListItem.object.performanceIcon);
            if (notificationListItem.a() == Notification$Type.INVITE) {
                this.h.setVisibility(8);
            } else {
                this.h.setText(PerformanceV2Util.m25933a(getResources(), this.D, true));
            }
            this.u.setVisibility(0);
        } else {
            this.u.setVisibility(8);
        }
        final NotificationsFragment notificationsFragment2 = notificationsFragment;
        final BaseFragmentResponder baseFragmentResponder2 = baseFragmentResponder;
        final NotificationListItem notificationListItem2 = notificationListItem;
        final NotificationScreenType notificationScreenType2 = notificationScreenType;
        final Runnable runnable2 = runnable;
        OnClickListener c46571 = new OnClickListener(this) {
            final /* synthetic */ InviteNotificationListViewItem f22984f;

            public void onClick(View view) {
                this.f22984f.m24352a(notificationsFragment2, baseFragmentResponder2, notificationListItem2, notificationScreenType2, runnable2);
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        };
        setOnClickListener(c46571);
        OnClickListener c46582 = new OnClickListener(this) {
            final /* synthetic */ InviteNotificationListViewItem f22987c;

            public void onClick(View view) {
                AccountIcon accountIcon = (AccountIcon) notificationListItem.subjects.get(0);
                if (accountIcon != null) {
                    notificationsFragment.mo6487a(ProfileFragment.m21036a(accountIcon));
                }
            }
        };
        this.i.setVisibility(0);
        if (TextUtils.isEmpty(notificationListItem.content)) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
            this.j.setMaxLines(4);
            this.j.setText(notificationListItem.content);
        }
        int a = LayoutUtils.m25845a(notificationListItem.a());
        LayoutUtils.m25857a(notificationListItem, getContext(), (BaseFragment) notificationsFragment, this.i, true, c46571, c46582, new Runnable(this) {
            final /* synthetic */ InviteNotificationListViewItem f22990c;

            public void run() {
                if (notificationListItem.aggObject != null) {
                    notificationsFragment.mo6929a(NotificationsFragment.m20406a(notificationListItem.aggObject.notificationKeys, null), notificationListItem.aggObject.notificationKeys.toString());
                }
            }
        });
        this.f23037a.setImageDrawable(getResources().getDrawable(a));
        if (notificationListItem.subjects.size() > 0) {
            this.k.m23396a((BaseFragment) notificationsFragment, (AccountIcon) notificationListItem.subjects.get(0));
        }
        this.p.setVisibility(0);
        this.p.setText(MiscUtils.m25887a(notificationListItem.time, false, true, false));
        m24357a(z);
        m24340c(false);
        m24345h();
    }

    protected void m24357a(boolean z) {
        if (z) {
            setBackgroundResource(C1947R.drawable.notifications_new_item_selector);
            this.u.setBackgroundResource(C1947R.drawable.notifications_new_item_selector);
            this.v.setBackgroundResource(C1947R.drawable.notifications_new_item_selector);
            return;
        }
        setBackgroundResource(C1947R.drawable.notifications_item_selector);
        this.u.setBackgroundResource(C1947R.color.background_grey_light);
        this.v.setBackgroundResource(C1947R.color.white);
    }

    public void mo6864a() {
        m24357a(false);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f23036G == null) {
            this.f23036G = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f23036G;
    }

    private boolean m24354a(NotificationListItem notificationListItem) {
        switch (notificationListItem.a()) {
            case JOIN:
                return true;
            default:
                return false;
        }
    }

    public void setOnAlbumArtClickListener(final OnClickListener onClickListener) {
        this.ae.f21796a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ InviteNotificationListViewItem f22992b;

            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
    }

    public void setOnJoinListener(final OnClickListener onClickListener) {
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ InviteNotificationListViewItem f22994b;

            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
    }

    public PerformanceV2 getPerformance() {
        return this.D;
    }

    private void m24352a(BaseFragment baseFragment, final BaseFragmentResponder baseFragmentResponder, final NotificationListItem notificationListItem, NotificationScreenType notificationScreenType, Runnable runnable) {
        m24351a(notificationListItem, notificationScreenType);
        if (notificationListItem.object != null) {
            baseFragmentResponder.a(notificationListItem.object.performanceIcon, true, true);
        } else if ((notificationListItem.a() == Notification$Type.MENTION && notificationListItem.c() == Notification$EntityType.ACCOUNT) || notificationListItem.a() == Notification$Type.FOLLOW) {
            m24351a(notificationListItem, notificationScreenType);
            baseFragment.mo6487a(ProfileFragment.m21036a((AccountIcon) notificationListItem.subjects.get(0)));
        } else if (notificationListItem.a() == Notification$Type.MENTION) {
            ((MediaPlayingActivity) baseFragmentResponder).a(notificationListItem.object.performanceIcon, true, m24354a(notificationListItem), new Runnable(this) {
                final /* synthetic */ InviteNotificationListViewItem f22997c;

                public void run() {
                    if (notificationListItem.a() == Notification$Type.MENTION) {
                        NowPlayingFragment U = ((MediaPlayingActivity) baseFragmentResponder).U();
                        if (U != null) {
                            PerformanceV2 K = U.m23905K();
                            if (K == null || K.message == null || !K.message.equals(notificationListItem.content)) {
                                U.m23939e("@" + ((AccountIcon) notificationListItem.subjects.get(0)).handle + " ");
                            }
                        }
                    }
                }
            });
        }
    }

    private void m24351a(NotificationListItem notificationListItem, NotificationScreenType notificationScreenType) {
        Ensemble a;
        Object obj;
        if (notificationListItem.c().equals(Notification$EntityType.PERFORMANCE)) {
            PerformanceV2 performanceV2 = notificationListItem.object.performanceIcon;
            String str = performanceV2.performanceKey;
            if (notificationListItem.a().equals(Notification$Type.JOIN)) {
                a = Analytics.m17828a(performanceV2);
            } else {
                a = null;
            }
            obj = str;
        } else {
            Long valueOf = Long.valueOf(((AccountIcon) notificationListItem.subjects.get(0)).accountId);
            a = null;
        }
        SingAnalytics.m26088a(obj, notificationListItem.a(), a, notificationScreenType);
    }

    public NotificationListItem getNotificationListItem() {
        return this.f23035F;
    }
}
