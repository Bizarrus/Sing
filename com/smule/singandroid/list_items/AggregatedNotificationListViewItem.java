package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification$EntityType;
import com.smule.android.network.models.Notification$Type;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.NotificationsFragment.NotificationItemInterface;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.customviews.MoreProfilesView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.NotificationScreenType;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class AggregatedNotificationListViewItem extends LinearLayout implements NotificationItemInterface {
    private static final String f22772n = AggregatedNotificationListViewItem.class.getSimpleName();
    @ViewById
    ImageView f22773a;
    @ViewById
    ImageView f22774b;
    @ViewById
    ProfileImageWithVIPBadge f22775c;
    @ViewById
    ProfileImageWithVIPBadge f22776d;
    @ViewById
    ProfileImageWithVIPBadge f22777e;
    @ViewById
    ProfileImageWithVIPBadge f22778f;
    @ViewById
    ProfileImageWithVIPBadge f22779g;
    @ViewById
    View f22780h;
    @ViewById
    MoreProfilesView f22781i;
    @ViewById
    TextView f22782j;
    @ViewById
    TextView f22783k;
    @ViewById
    protected View f22784l;
    Notification$Type f22785m;

    public AggregatedNotificationListViewItem(Context context) {
        super(context);
    }

    public static AggregatedNotificationListViewItem m24223a(Context context) {
        return AggregatedNotificationListViewItem_.m24230b(context);
    }

    public void mo6865a(final NotificationsFragment notificationsFragment, BaseFragmentResponder baseFragmentResponder, final NotificationListItem notificationListItem, NotificationScreenType notificationScreenType, Runnable runnable, boolean z, boolean z2) {
        this.f22784l.setVisibility(z2 ? 4 : 0);
        final NotificationListItem notificationListItem2 = notificationListItem;
        final NotificationScreenType notificationScreenType2 = notificationScreenType;
        final BaseFragmentResponder baseFragmentResponder2 = baseFragmentResponder;
        final NotificationsFragment notificationsFragment2 = notificationsFragment;
        final Runnable runnable2 = runnable;
        OnClickListener c46171 = new OnClickListener(this) {
            final /* synthetic */ AggregatedNotificationListViewItem f22762f;

            class C46161 implements Runnable {
                final /* synthetic */ C46171 f22756a;

                C46161(C46171 c46171) {
                    this.f22756a = c46171;
                }

                public void run() {
                    NowPlayingFragment U = ((MediaPlayingActivity) baseFragmentResponder2).U();
                    if (U != null) {
                        PerformanceV2 K = U.m23905K();
                        if (K == null || K.message == null || !K.message.equals(notificationListItem2.content)) {
                            U.m23939e("");
                            U.m23937d(true);
                        }
                    }
                }
            }

            public void onClick(View view) {
                this.f22762f.m24224a(notificationListItem2, notificationScreenType2);
                if (notificationListItem2.a() == Notification$Type.LOVE) {
                    baseFragmentResponder2.a(notificationListItem2.object.performanceIcon, true, false);
                } else if (notificationListItem2.a() == Notification$Type.COMMENT) {
                    ((MediaPlayingActivity) baseFragmentResponder2).a(notificationListItem2.object.performanceIcon, true, true, new C46161(this));
                } else {
                    notificationsFragment2.mo6929a(NotificationsFragment.m20406a(notificationListItem2.aggObject.notificationKeys, notificationListItem2.b()), notificationListItem2.aggObject.notificationKeys.toString());
                }
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        };
        OnClickListener c46182 = new OnClickListener(this) {
            final /* synthetic */ AggregatedNotificationListViewItem f22765c;

            public void onClick(View view) {
                AccountIcon accountIcon = (AccountIcon) notificationListItem.subjects.get(0);
                if (accountIcon != null) {
                    notificationsFragment.mo6487a(ProfileFragment.m21036a(accountIcon));
                }
            }
        };
        setOnClickListener(c46171);
        int a = LayoutUtils.m25845a(notificationListItem.a());
        LayoutUtils.m25857a(notificationListItem, getContext(), (BaseFragment) notificationsFragment, this.f22783k, false, c46171, c46182, null);
        if (notificationListItem.a() == Notification$Type.RENDER) {
            this.f22774b.setVisibility(0);
            LayoutUtils.m25855a(this.f22783k, true);
            LayoutUtils.m25855a(this.f22774b, true);
            this.f22780h.setVisibility(8);
            this.f22782j.setVisibility(8);
        } else {
            this.f22774b.setVisibility(8);
            LayoutUtils.m25855a(this.f22783k, false);
            LayoutUtils.m25855a(this.f22774b, false);
            this.f22780h.setVisibility(0);
            this.f22782j.setVisibility(0);
        }
        if (notificationListItem.subjects.size() > 0) {
            final AccountIcon accountIcon = (AccountIcon) notificationListItem.subjects.get(0);
            if (accountIcon != null) {
                this.f22775c.m23395a(accountIcon, new OnClickListener(this) {
                    final /* synthetic */ AggregatedNotificationListViewItem f22768c;

                    public void onClick(View view) {
                        notificationsFragment.mo6487a(ProfileFragment.m21036a(accountIcon));
                    }
                });
            }
        }
        this.f22782j.setText(MiscUtils.m25887a(notificationListItem.time, true, false, true));
        this.f22773a.setImageDrawable(getResources().getDrawable(a));
        m24225a(notificationsFragment, this.f22776d, notificationListItem, 1);
        m24225a(notificationsFragment, this.f22777e, notificationListItem, 2);
        Object obj = 1;
        if (m24227b()) {
            m24225a(notificationsFragment, this.f22778f, notificationListItem, 3);
            if (notificationListItem.count > 5) {
                m24225a(notificationsFragment, this.f22779g, notificationListItem, -1);
            } else {
                m24225a(notificationsFragment, this.f22779g, notificationListItem, 4);
                obj = null;
            }
        } else {
            if (notificationListItem.subjects.size() > 4) {
                m24225a(notificationsFragment, this.f22778f, notificationListItem, -1);
            } else {
                m24225a(notificationsFragment, this.f22778f, notificationListItem, 3);
                obj = null;
            }
            m24225a(notificationsFragment, this.f22779g, notificationListItem, -1);
        }
        this.f22781i.setVisibility(obj != null ? 0 : 8);
    }

    public void mo6864a() {
    }

    private void m24224a(NotificationListItem notificationListItem, NotificationScreenType notificationScreenType) {
        String str;
        Ensemble ensemble = null;
        if (!notificationListItem.c().equals(Notification$EntityType.PERFORMANCE) || notificationListItem.object == null) {
            str = null;
        } else {
            PerformanceV2 performanceV2 = notificationListItem.object.performanceIcon;
            str = performanceV2.performanceKey;
            if (notificationListItem.a().equals(Notification$Type.JOIN)) {
                ensemble = Analytics.m17828a(performanceV2);
            }
        }
        SingAnalytics.m26108a(str, notificationListItem.a(), ensemble, notificationScreenType);
    }

    private boolean m24227b() {
        int i = getResources().getDisplayMetrics().widthPixels;
        float dimension = getResources().getDimension(C1947R.dimen.margin_large);
        return ((float) i) > dimension + (getResources().getDimension(C1947R.dimen.notification_item_text_right_margin) + (5.0f * (getResources().getDimension(C1947R.dimen.profile_middle) + dimension)));
    }

    private void m24225a(final BaseFragment baseFragment, ProfileImageWithVIPBadge profileImageWithVIPBadge, NotificationListItem notificationListItem, int i) {
        if (i < 0 || i >= notificationListItem.subjects.size()) {
            profileImageWithVIPBadge.setVisibility(8);
            return;
        }
        final AccountIcon accountIcon = (AccountIcon) notificationListItem.subjects.get(i);
        profileImageWithVIPBadge.setVisibility(0);
        if (accountIcon != null) {
            profileImageWithVIPBadge.m23395a(accountIcon, new OnClickListener(this) {
                final /* synthetic */ AggregatedNotificationListViewItem f22771c;

                public void onClick(View view) {
                    baseFragment.mo6487a(ProfileFragment.m21036a(accountIcon));
                }
            });
        }
    }

    public void setAggregatedType(Notification$Type notification$Type) {
        this.f22785m = notification$Type;
    }

    public Notification$Type getAggregatedType() {
        return this.f22785m;
    }
}
