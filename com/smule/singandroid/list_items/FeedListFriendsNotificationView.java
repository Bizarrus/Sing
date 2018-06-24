package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.facebook.FacebookFriend;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.facebook.MagicFacebook$FindFacebookFriendsListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.RecSysContext;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.RecommendationManager.RecommendedSingersCallback;
import com.smule.android.network.managers.RecommendationManager.RecommendedSingersResponse.RecAccountIcon;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.FeedFragment.FeedRecsysCallback;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.FacebookPermissionPermittedType;
import com.smule.singandroid.utils.SingAnalytics.FeedNoticeClickType;
import com.smule.singandroid.utils.SingAnalytics.FeedNoticeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class FeedListFriendsNotificationView extends LinearLayout {
    @ViewById
    ImageView f22852a;
    @ViewById
    View f22853b;
    @ViewById
    TextView f22854c;
    @ViewById
    TextView f22855d;
    @ViewById
    ViewGroup f22856e;
    @ViewsById
    List<ProfileImageWithVIPBadge> f22857f;
    @ViewById
    Button f22858g;
    Mode f22859h;
    ActionListener f22860i;
    FeedRecsysCallback f22861j;
    private String f22862k;

    public interface ActionListener {
        void mo6425a();

        void mo6426b();

        void mo6427c();
    }

    class C46361 implements MagicFacebook$FindFacebookFriendsListener {
        final /* synthetic */ FeedListFriendsNotificationView f22846a;

        C46361(FeedListFriendsNotificationView feedListFriendsNotificationView) {
            this.f22846a = feedListFriendsNotificationView;
        }

        public void mo6241a(List<FacebookFriend> list, List<FacebookFriend> list2, boolean z) {
            CharSequence string;
            List arrayList = new ArrayList();
            for (FacebookFriend facebookFriend : list) {
                if (!facebookFriend.f15946d) {
                    arrayList.add(facebookFriend);
                }
            }
            if (arrayList.size() == 1) {
                string = this.f22846a.getResources().getString(C1947R.string.news_feed_connect_with_one, new Object[]{((FacebookFriend) arrayList.get(0)).f15944b});
            } else if (arrayList.size() == 2) {
                string = this.f22846a.getResources().getString(C1947R.string.news_feed_connect_with_two, new Object[]{((FacebookFriend) arrayList.get(0)).f15944b, ((FacebookFriend) arrayList.get(1)).f15944b});
            } else if (arrayList.size() > 2) {
                string = this.f22846a.getResources().getString(C1947R.string.news_feed_connect_with_many, new Object[]{((FacebookFriend) arrayList.get(0)).f15944b, Integer.valueOf(arrayList.size() - 1)});
            } else {
                string = null;
            }
            if (string != null) {
                this.f22846a.f22854c.setText(C1947R.string.news_feed_connect_facebook_friends_title);
                this.f22846a.f22855d.setText(string);
                this.f22846a.f22858g.setText(C1947R.string.news_feed_connect_facebook_button);
            }
        }

        public void mo6240a() {
        }
    }

    class C46372 implements RecommendedSingersCallback {
        final /* synthetic */ FeedListFriendsNotificationView f22847a;

        C46372(FeedListFriendsNotificationView feedListFriendsNotificationView) {
            this.f22847a = feedListFriendsNotificationView;
        }

        public void mo6454a(List<RecAccountIcon> list, List<RecAccountIcon> list2) {
            Collection hashSet = new HashSet(list);
            hashSet.addAll(list2);
            ArrayList arrayList = new ArrayList(hashSet);
            Collections.shuffle(arrayList);
            int min = Math.min(arrayList.size(), 5);
            StringBuilder stringBuilder = new StringBuilder("");
            StringBuilder stringBuilder2 = new StringBuilder("");
            for (int i = 0; i < min; i++) {
                RecAccountIcon recAccountIcon = (RecAccountIcon) arrayList.get(i);
                ((ProfileImageWithVIPBadge) this.f22847a.f22857f.get(i)).setAccount(recAccountIcon.mAccountIcon);
                String str = recAccountIcon.mRecId;
                if (str != null) {
                    stringBuilder.append(0);
                    stringBuilder2.append(str);
                    if (i + 1 < min) {
                        stringBuilder.append(",");
                        stringBuilder2.append(",");
                    }
                }
            }
            this.f22847a.f22862k = stringBuilder2.toString();
            if (this.f22847a.f22861j != null) {
                this.f22847a.f22861j.mo6424a(this.f22847a.f22862k, stringBuilder.toString(), true);
            }
        }
    }

    protected enum Mode {
        FIND_FB_FRIENDS,
        FIND_FRIENDS
    }

    public FeedListFriendsNotificationView(Context context) {
        super(context);
    }

    public static FeedListFriendsNotificationView m24271a(Context context, FeedRecsysCallback feedRecsysCallback) {
        FeedListFriendsNotificationView a = FeedListFriendsNotificationView_.m24280a(context);
        a.setFeedRecsysCallback(feedRecsysCallback);
        return a;
    }

    public void setActionListener(ActionListener actionListener) {
        this.f22860i = actionListener;
    }

    @AfterViews
    protected void m24274a() {
        if (MagicFacebook.a().h()) {
            m24275b();
            this.f22856e.setVisibility(8);
        } else {
            m24276c();
            this.f22856e.setVisibility(0);
        }
        ImageUtils.a("https://s.smule.com/z0/account/picture/2d/37/7cacb33a-7d29-4edf-89fc-021c3b09c02e.jpg", this.f22852a, 0, true);
    }

    private void setFeedRecsysCallback(FeedRecsysCallback feedRecsysCallback) {
        this.f22861j = feedRecsysCallback;
    }

    protected void m24275b() {
        this.f22859h = Mode.FIND_FB_FRIENDS;
        MagicFacebook.a().a(new C46361(this), false, 1000);
    }

    protected void m24276c() {
        this.f22859h = Mode.FIND_FRIENDS;
        RecommendationManager.m18285a().m18299a(new C46372(this));
    }

    @Click
    void m24277d() {
        if (this.f22860i != null) {
            SingAnalytics.m26080a(getFeedNoticeTypeForAnalytics(), FeedNoticeClickType.CANCEL);
            this.f22860i.mo6427c();
        }
    }

    @Click
    void m24278e() {
        if (this.f22860i != null) {
            SingAnalytics.m26080a(getFeedNoticeTypeForAnalytics(), FeedNoticeClickType.YES);
            Analytics.m17860a(this.f22862k, ItemClickType.PROFILE, 0, RecSysContext.FEED, null);
            if (this.f22859h == Mode.FIND_FB_FRIENDS) {
                SingAnalytics.m26078a(FeedNoticeClickType.YES, FacebookPermissionPermittedType.PERMITTED);
                this.f22860i.mo6426b();
                return;
            }
            this.f22860i.mo6425a();
        }
    }

    protected FeedNoticeType getFeedNoticeTypeForAnalytics() {
        switch (this.f22859h) {
            case FIND_FB_FRIENDS:
                return FeedNoticeType.FIND_FB_FRIENDS;
            case FIND_FRIENDS:
                return FeedNoticeType.FIND_FRIENDS;
            default:
                return null;
        }
    }

    public void m24279f() {
        SingAnalytics.m26079a(getFeedNoticeTypeForAnalytics());
    }
}
