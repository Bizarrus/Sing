/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$FacebookPermissionPermittedType
 *  com.smule.singandroid.utils.SingAnalytics$FeedNoticeClickType
 *  com.smule.singandroid.utils.SingAnalytics$FeedNoticeType
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.annotations.ViewsById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.FeedFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.FeedListFriendsNotificationView_;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class FeedListFriendsNotificationView
extends LinearLayout {
    @ViewById
    ImageView a;
    @ViewById
    View b;
    @ViewById
    TextView c;
    @ViewById
    TextView d;
    @ViewById
    ViewGroup e;
    @ViewsById
    List<ProfileImageWithVIPBadge> f;
    @ViewById
    Button g;
    Mode h;
    ActionListener i;
    FeedFragment.FeedRecsysCallback j;
    private String k;

    public FeedListFriendsNotificationView(Context context) {
        super(context);
    }

    public static FeedListFriendsNotificationView a(Context object, FeedFragment.FeedRecsysCallback feedRecsysCallback) {
        object = FeedListFriendsNotificationView_.a((Context)object);
        FeedListFriendsNotificationView.super.setFeedRecsysCallback(feedRecsysCallback);
        return object;
    }

    private void setFeedRecsysCallback(FeedFragment.FeedRecsysCallback feedRecsysCallback) {
        this.j = feedRecsysCallback;
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void a() {
        if (com.smule.android.facebook.MagicFacebook.a().i()) {
            this.b();
            this.e.setVisibility(8);
        } else {
            this.c();
            this.e.setVisibility(0);
        }
        ImageUtils.a("https://s.smule.com/z0/account/picture/2d/37/7cacb33a-7d29-4edf-89fc-021c3b09c02e.jpg", this.a, 0, true);
    }

    protected void b() {
        this.h = Mode.a;
        com.smule.android.facebook.MagicFacebook.a().a(new MagicFacebook(){

            @Override
            public void a() {
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(List<FacebookFriend> object, List<FacebookFriend> list, boolean bl) {
                list = new ArrayList<FacebookFriend>();
                object = object.iterator();
                while (object.hasNext()) {
                    FacebookFriend facebookFriend = (FacebookFriend)object.next();
                    if (facebookFriend.d) continue;
                    list.add(facebookFriend);
                }
                if (list.size() == 1) {
                    object = FeedListFriendsNotificationView.this.getResources().getString(2131296989, new Object[]{list.get((int)0).b});
                } else if (list.size() == 2) {
                    object = FeedListFriendsNotificationView.this.getResources().getString(2131296990, new Object[]{list.get((int)0).b, list.get((int)1).b});
                } else {
                    if (list.size() <= 2) {
                        return;
                    }
                    object = FeedListFriendsNotificationView.this.getResources().getString(2131296988, new Object[]{list.get((int)0).b, list.size() - 1});
                }
                if (object != null) {
                    FeedListFriendsNotificationView.this.c.setText(2131296984);
                    FeedListFriendsNotificationView.this.d.setText((CharSequence)object);
                    FeedListFriendsNotificationView.this.g.setText(2131296983);
                }
            }
        }, false, 1000);
    }

    protected void c() {
        this.h = Mode.b;
        com.smule.android.network.managers.RecommendationManager.a().a(new RecommendationManager(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(List<RecommendationManager.RecommendedSingersResponse.RecAccountIcon> collection, List<RecommendationManager.RecommendedSingersResponse.RecAccountIcon> object) {
                StringBuilder stringBuilder;
                HashSet<RecommendationManager.RecommendedSingersResponse.RecAccountIcon> hashSet = new HashSet<RecommendationManager.RecommendedSingersResponse.RecAccountIcon>(collection);
                hashSet.addAll((Collection<RecommendationManager.RecommendedSingersResponse.RecAccountIcon>)((Object)stringBuilder));
                ArrayList<RecommendationManager.RecommendedSingersResponse.RecAccountIcon> arrayList = new ArrayList<RecommendationManager.RecommendedSingersResponse.RecAccountIcon>(hashSet);
                Collections.shuffle(arrayList);
                int n = Math.min(arrayList.size(), 5);
                stringBuilder = new StringBuilder("");
                StringBuilder stringBuilder2 = new StringBuilder("");
                for (int i = 0; i < n; ++i) {
                    Object object2 = arrayList.get(i);
                    FeedListFriendsNotificationView.this.f.get(i).setAccount(object2.mAccountIcon);
                    object2 = object2.mRecId;
                    if (object2 == null) continue;
                    stringBuilder.append(0);
                    stringBuilder2.append((String)object2);
                    if (i + 1 >= n) continue;
                    stringBuilder.append(",");
                    stringBuilder2.append(",");
                }
                FeedListFriendsNotificationView.this.k = stringBuilder2.toString();
                if (FeedListFriendsNotificationView.this.j != null) {
                    FeedListFriendsNotificationView.this.j.a(FeedListFriendsNotificationView.this.k, stringBuilder.toString(), true);
                }
            }
        });
    }

    @Click
    void d() {
        if (this.i != null) {
            SingAnalytics.a((SingAnalytics.FeedNoticeType)this.getFeedNoticeTypeForAnalytics(), (SingAnalytics.FeedNoticeClickType)SingAnalytics.FeedNoticeClickType.b);
            this.i.c();
        }
    }

    @Click
    void e() {
        block3 : {
            block2 : {
                if (this.i == null) break block2;
                SingAnalytics.a((SingAnalytics.FeedNoticeType)this.getFeedNoticeTypeForAnalytics(), (SingAnalytics.FeedNoticeClickType)SingAnalytics.FeedNoticeClickType.a);
                com.smule.android.logging.Analytics.a(this.k, Analytics.d, 0, Analytics.a, null);
                if (this.h != Mode.a) break block3;
                SingAnalytics.a((SingAnalytics.FeedNoticeClickType)SingAnalytics.FeedNoticeClickType.a, (SingAnalytics.FacebookPermissionPermittedType)SingAnalytics.FacebookPermissionPermittedType.a);
                this.i.b();
            }
            return;
        }
        this.i.a();
    }

    public void f() {
        SingAnalytics.a((SingAnalytics.FeedNoticeType)this.getFeedNoticeTypeForAnalytics());
    }

    protected SingAnalytics.FeedNoticeType getFeedNoticeTypeForAnalytics() {
        switch (.a[this.h.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return SingAnalytics.FeedNoticeType.a;
            }
            case 2: 
        }
        return SingAnalytics.FeedNoticeType.b;
    }

    public void setActionListener(ActionListener actionListener) {
        this.i = actionListener;
    }

    public static interface ActionListener {
        public void a();

        public void b();

        public void c();
    }

    protected static enum Mode {
        a,
        b;
        

        private Mode() {
        }
    }

}

