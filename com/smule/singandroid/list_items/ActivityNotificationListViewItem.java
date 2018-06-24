package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification$EntityType;
import com.smule.android.network.models.Notification$Type;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.NotificationListItem$DetailedType;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.NotificationsFragment.NotificationItemInterface;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.customviews.PlayableItemDetailsView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CommentsListDialog;
import com.smule.singandroid.dialogs.CommentsListDialog.CommentsListDialogListener;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.NotificationScreenType;
import java.text.MessageFormat;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ActivityNotificationListViewItem extends FrameLayout implements NotificationItemInterface, MediaPlayingViewInterface {
    private static final String f22734n = InviteNotificationListViewItem.class.getSimpleName();
    protected ImageUtils$ImageViewLoadOptimizer f22735a = new ImageUtils$ImageViewLoadOptimizer();
    @ViewById
    ImageView f22736b;
    @ViewById
    FrameLayout f22737c;
    @ViewById
    ProgressBar f22738d;
    @ViewById
    ImageView f22739e;
    @ViewById
    TextView f22740f;
    @ViewById
    ImageView f22741g;
    @ViewById
    protected View f22742h;
    @ViewById
    protected PlayableItemDetailsView f22743i;
    @ViewById
    protected ProfileImageWithVIPBadge f22744j;
    @ViewById
    protected TextView f22745k;
    @ViewById
    protected TextView f22746l;
    protected PerformanceV2 f22747m;
    private boolean f22748o;

    public ActivityNotificationListViewItem(Context context) {
        super(context);
    }

    public static ActivityNotificationListViewItem m24203a(Context context) {
        return ActivityNotificationListViewItem_.m24219b(context);
    }

    public void mo6865a(NotificationsFragment notificationsFragment, BaseFragmentResponder baseFragmentResponder, NotificationListItem notificationListItem, NotificationScreenType notificationScreenType, Runnable runnable, boolean z, boolean z2) {
        this.f22741g.setVisibility(8);
        this.f22742h.setVisibility(z2 ? 4 : 0);
        this.f22748o = notificationsFragment.m20428G();
        if (notificationListItem.object == null || this.f22748o) {
            this.f22747m = null;
            this.f22743i.setVisibility(8);
        } else {
            this.f22747m = notificationListItem.object.performanceIcon;
            this.f22743i.mo6775a(this.f22747m, true);
            this.f22743i.setVisibility(0);
            this.f22743i.m23049a(notificationListItem.a() != Notification$Type.RENDER);
        }
        final NotificationsFragment notificationsFragment2 = notificationsFragment;
        final BaseFragmentResponder baseFragmentResponder2 = baseFragmentResponder;
        final NotificationListItem notificationListItem2 = notificationListItem;
        final NotificationScreenType notificationScreenType2 = notificationScreenType;
        final Runnable runnable2 = runnable;
        OnClickListener c46051 = new OnClickListener(this) {
            final /* synthetic */ ActivityNotificationListViewItem f22705f;

            public void onClick(View view) {
                this.f22705f.m24206a(notificationsFragment2, baseFragmentResponder2, notificationListItem2, notificationScreenType2, runnable2, false);
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        };
        setOnClickListener(c46051);
        this.f22745k.setMaxLines(4);
        final Notification$Type a = notificationListItem.a();
        int a2 = LayoutUtils.m25845a(a);
        final NotificationsFragment notificationsFragment3 = notificationsFragment;
        final BaseFragmentResponder baseFragmentResponder3 = baseFragmentResponder;
        final NotificationListItem notificationListItem3 = notificationListItem;
        final NotificationScreenType notificationScreenType3 = notificationScreenType;
        final Runnable runnable3 = runnable;
        final NotificationListItem notificationListItem4 = notificationListItem;
        final NotificationsFragment notificationsFragment4 = notificationsFragment;
        LayoutUtils.m25857a(notificationListItem, getContext(), (BaseFragment) notificationsFragment, this.f22745k, true, c46051, (OnClickListener) new OnClickListener(this) {
            final /* synthetic */ ActivityNotificationListViewItem f22711f;

            public void onClick(View view) {
                this.f22711f.m24206a(notificationsFragment3, baseFragmentResponder3, notificationListItem3, notificationScreenType3, runnable3, true);
                if (runnable3 != null) {
                    runnable3.run();
                }
            }
        }, new Runnable(this) {
            final /* synthetic */ ActivityNotificationListViewItem f22715d;

            public void run() {
                if (notificationListItem4.aggObject != null) {
                    if (notificationListItem4.b() == NotificationListItem$DetailedType.COMMENT_AGGREGATED) {
                        this.f22715d.m24205a(notificationsFragment4, notificationListItem4);
                        return;
                    }
                    boolean z = a == Notification$Type.LOVE || a == Notification$Type.FAVORITE || notificationListItem4.b() == NotificationListItem$DetailedType.JOIN_GROUP;
                    notificationsFragment4.mo6487a(NotificationsFragment.m20407a(notificationListItem4.aggObject.notificationKeys, z, notificationListItem4.b()));
                }
            }
        });
        if (this.f22748o) {
            if (a == Notification$Type.FAVORITE) {
                notificationListItem.a(NotificationListItem$DetailedType.FAVORITE_SUPPRESSED);
            } else if (a == Notification$Type.LOVE) {
                notificationListItem.a(NotificationListItem$DetailedType.LOVE_SUPPRESSED);
            }
        }
        this.f22736b.setImageDrawable(getResources().getDrawable(a2));
        if (notificationListItem.subjects.size() > 0) {
            this.f22744j.m23396a((BaseFragment) notificationsFragment, (AccountIcon) notificationListItem.subjects.get(0));
        } else if (a == Notification$Type.RENDER) {
            this.f22744j.m23396a((BaseFragment) notificationsFragment, UserManager.a().O());
        }
        if (m24212b(a)) {
            this.f22740f.setVisibility(0);
            this.f22746l.setVisibility(4);
            this.f22740f.setText(MiscUtils.m25887a(notificationListItem.time, true, false, true));
            this.f22739e.setVisibility(0);
            final long j = ((AccountIcon) notificationListItem.subjects.get(0)).accountId;
            final NotificationListItem notificationListItem5 = notificationListItem;
            final NotificationScreenType notificationScreenType4 = notificationScreenType;
            this.f22737c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ActivityNotificationListViewItem f22720d;

                class C46081 implements ToggleFollowStateListener {
                    final /* synthetic */ C46094 f22716a;

                    C46081(C46094 c46094) {
                        this.f22716a = c46094;
                    }

                    public void mo6399a(boolean z, boolean z2, boolean z3) {
                        this.f22716a.f22720d.mo6866a(notificationListItem5, z, z3, true);
                    }
                }

                public void onClick(View view) {
                    this.f22720d.m24204a(notificationListItem5, notificationScreenType4);
                    this.f22720d.f22738d.setVisibility(0);
                    this.f22720d.f22739e.setVisibility(8);
                    FollowManager.m18204a().m18215a(Long.valueOf(j), new C46081(this));
                }
            });
        } else {
            this.f22740f.setVisibility(8);
            this.f22739e.setVisibility(8);
            if (m24213b(notificationListItem.b())) {
                this.f22741g.setVisibility(0);
                this.f22740f.setVisibility(0);
                this.f22746l.setVisibility(4);
                this.f22740f.setText(MiscUtils.m25887a(notificationListItem.time, true, false, true));
                final NotificationsFragment notificationsFragment5 = notificationsFragment;
                final NotificationListItem notificationListItem6 = notificationListItem;
                this.f22741g.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ActivityNotificationListViewItem f22723c;

                    public void onClick(View view) {
                        this.f22723c.m24205a(notificationsFragment5, notificationListItem6);
                    }
                });
            } else {
                this.f22746l.setVisibility(0);
                this.f22746l.setText(MiscUtils.m25887a(notificationListItem.time, true, false, true));
            }
        }
        mo6866a(notificationListItem, true, false, false);
    }

    public void mo6864a() {
    }

    private void m24205a(final BaseFragment baseFragment, NotificationListItem notificationListItem) {
        String str = null;
        if (!(notificationListItem == null || notificationListItem.subjects.isEmpty() || notificationListItem.count != 1)) {
            str = "@" + ((AccountIcon) notificationListItem.subjects.get(0)).handle + " ";
            Log.b(f22734n, "listItem : " + notificationListItem.toString());
        }
        final CommentsListDialog commentsListDialog = new CommentsListDialog(baseFragment, this.f22747m, str, notificationListItem != null ? notificationListItem.time : -1);
        commentsListDialog.m23658a(new CommentsListDialogListener(this) {
            final /* synthetic */ ActivityNotificationListViewItem f22726c;

            public void mo6828a(CommentItem commentItem, PerformancePost performancePost) {
                baseFragment.mo6487a(ProfileFragment.m21036a(performancePost.accountIcon));
                commentsListDialog.dismiss();
            }
        });
        commentsListDialog.show();
    }

    public void u_() {
        this.f22743i.u_();
    }

    public void mo6724b() {
    }

    public void setOnAlbumArtClickListener(final OnClickListener onClickListener) {
        this.f22743i.setOnAlbumArtClickListener(new OnClickListener(this) {
            final /* synthetic */ ActivityNotificationListViewItem f22728b;

            public void onClick(View view) {
                onClickListener.onClick(view);
            }
        });
    }

    public String getMediaKey() {
        return this.f22743i.getMediaKey();
    }

    public PerformanceV2 getPerformance() {
        return this.f22747m;
    }

    private void m24206a(BaseFragment baseFragment, final BaseFragmentResponder baseFragmentResponder, final NotificationListItem notificationListItem, NotificationScreenType notificationScreenType, Runnable runnable, boolean z) {
        boolean z2 = false;
        m24204a(notificationListItem, notificationScreenType);
        Notification$Type a = notificationListItem.a();
        final NotificationListItem$DetailedType b = notificationListItem.b();
        if (notificationListItem.object == null || z || m24211a(b)) {
            AccountIcon O = a == Notification$Type.RENDER ? UserManager.a().O() : (AccountIcon) notificationListItem.subjects.get(0);
            if (O != null) {
                baseFragment.mo6487a(ProfileFragment.m21036a(O));
            }
        } else if (a == Notification$Type.MENTION) {
            ((MediaPlayingActivity) baseFragmentResponder).a(notificationListItem.object.performanceIcon, true, m24210a(a), new Runnable(this) {
                final /* synthetic */ ActivityNotificationListViewItem f22732d;

                public void run() {
                    NowPlayingFragment U = ((MediaPlayingActivity) baseFragmentResponder).U();
                    if (U != null && b == NotificationListItem$DetailedType.MENTION_PERFORMANCE) {
                        U.m23939e("@" + ((AccountIcon) notificationListItem.subjects.get(0)).handle + " ");
                        U.m23937d(true);
                    }
                }
            });
        } else if (m24213b(b)) {
            m24205a(baseFragment, notificationListItem);
        } else {
            if (a != Notification$Type.LOVE) {
                z2 = true;
            }
            baseFragmentResponder.a(notificationListItem.object.performanceIcon, true, z2);
        }
    }

    private void m24204a(NotificationListItem notificationListItem, NotificationScreenType notificationScreenType) {
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

    private boolean m24211a(NotificationListItem$DetailedType notificationListItem$DetailedType) {
        return (notificationListItem$DetailedType == NotificationListItem$DetailedType.JOIN_GROUP && this.f22748o) || notificationListItem$DetailedType == NotificationListItem$DetailedType.FAVORITE_SUPPRESSED || notificationListItem$DetailedType == NotificationListItem$DetailedType.LOVE_SUPPRESSED;
    }

    private boolean m24213b(NotificationListItem$DetailedType notificationListItem$DetailedType) {
        return notificationListItem$DetailedType == NotificationListItem$DetailedType.COMMENT_AGGREGATED || notificationListItem$DetailedType == NotificationListItem$DetailedType.COMMENT_NON_AGGREGATED || notificationListItem$DetailedType == NotificationListItem$DetailedType.MENTION_PERFORMANCE;
    }

    private boolean m24210a(Notification$Type notification$Type) {
        switch (notification$Type) {
            case JOIN:
                return true;
            default:
                return false;
        }
    }

    private boolean m24212b(Notification$Type notification$Type) {
        return notification$Type == Notification$Type.FOLLOW || notification$Type == Notification$Type.FOLLOW_FB || notification$Type == Notification$Type.CONNECT_FB;
    }

    @UiThread
    protected void mo6866a(NotificationListItem notificationListItem, boolean z, boolean z2, boolean z3) {
        if (m24212b(notificationListItem.a())) {
            boolean a = FollowManager.m18204a().m18222a(((AccountIcon) notificationListItem.subjects.get(0)).accountId);
            this.f22738d.setVisibility(8);
            this.f22739e.setVisibility(0);
            this.f22739e.setActivated(a);
            if (!z3) {
                return;
            }
            if (z) {
                if (a) {
                    Toaster.a(getContext(), MessageFormat.format(getContext().getString(C1947R.string.profile_follow_format), new Object[]{r0.handle}));
                    return;
                }
                Toaster.a(getContext(), MessageFormat.format(getContext().getString(C1947R.string.profile_unfollow_format), new Object[]{r0.handle}));
                return;
            } else if (z2) {
                Toaster.a(getContext(), getResources().getString(C1947R.string.profile_follow_limit_reached));
                return;
            } else {
                Toaster.a(getContext(), getContext().getString(C1947R.string.login_cannot_connect_to_smule));
                return;
            }
        }
        this.f22739e.setVisibility(8);
    }
}
