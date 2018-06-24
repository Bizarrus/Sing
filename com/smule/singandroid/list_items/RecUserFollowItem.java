package com.smule.singandroid.list_items;

import android.content.Context;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.RecSysContext;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.RecommendationManager.RecommendedSingersResponse.RecAccountIcon;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class RecUserFollowItem extends UserFollowItem {
    protected String f23181a;

    public RecUserFollowItem(Context context) {
        super(context);
    }

    public static RecUserFollowItem m24451a(Context context) {
        return RecUserFollowItem_.m24455b(context);
    }

    public void m24453a(RecAccountIcon recAccountIcon, int i, Context context, boolean z, boolean z2, UserFollowItemListener userFollowItemListener) {
        m24445a(recAccountIcon.mAccountIcon, i, context, z, userFollowItemListener);
        this.f23181a = recAccountIcon.mRecId;
        String str = recAccountIcon.mRecommendationType;
        if (str != null && str.equals("NEW")) {
            m24449a(context.getString(C1947R.string.recommended_singer_new), z2);
        } else if (str != null && str.equals("SUGGESTED")) {
            m24449a(context.getString(C1947R.string.recommended_singer_suggested), z2);
        }
    }

    protected void mo6880a(boolean z) {
    }

    protected void mo6879a(long j) {
        if (this.f23181a != null) {
            ItemClickType itemClickType;
            if (FollowManager.m18204a().m18222a(j)) {
                itemClickType = ItemClickType.UNFOLLOW;
            } else {
                itemClickType = ItemClickType.FOLLOW;
            }
            Analytics.m17860a(this.f23181a, itemClickType, this.p, RecSysContext.FINDFRIENDS, null);
        }
    }
}
