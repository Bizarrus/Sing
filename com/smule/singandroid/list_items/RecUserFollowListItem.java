/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.widget.TextView
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$RecFollowReasonType
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.List;

public abstract class RecUserFollowListItem
extends UserFollowListItem {
    private static final String t = RecUserFollowListItem.class.getName();
    protected String a;

    public RecUserFollowListItem(Context context) {
        super(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected String a(RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon) {
        if (recAccountIcon.mReasonVars == null || recAccountIcon.mReasonVars.size() == 0) {
            return null;
        }
        return recAccountIcon.mReasonVars.get(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected String a(RecommendationManager.RecommendedSingersResponse.RecAccountIcon object, Context context) {
        SingAnalytics.RecFollowReasonType recFollowReasonType = SingAnalytics.RecFollowReasonType.a((String)object.mReasonType);
        if (recFollowReasonType == null) return null;
        object = this.a((RecommendationManager.RecommendedSingersResponse.RecAccountIcon)object);
        switch (.a[recFollowReasonType.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                if (object == null) return context.getString(2131296831);
                return context.getString(2131296830, new Object[]{object});
            }
            case 2: {
                if (object == null) return null;
                return context.getString(2131296841, new Object[]{object});
            }
            case 3: {
                return context.getString(2131296839);
            }
            case 4: {
                return context.getString(2131296834);
            }
            case 5: {
                if (object != null) return context.getString(2131296835, new Object[]{object});
                return null;
            }
            case 6: {
                return context.getString(2131296838);
            }
            case 7: {
                return context.getString(2131296833);
            }
            case 8: {
                context.getString(2131296832);
            }
            case 9: {
                return context.getString(2131296837);
            }
            case 10: {
                return context.getString(2131296840);
            }
            case 11: 
        }
        return context.getString(2131296836);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a(long l) {
        if (this.a != null) {
            Analytics itemClickType = FollowManager.a().a(l) ? Analytics.h : Analytics.g;
            com.smule.android.logging.Analytics.a(this.a, itemClickType, this.r, this.getRecSysFollowContext(), null);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Context object, RecommendationManager.RecommendedSingersResponse.RecAccountIcon recAccountIcon, int n, boolean bl, boolean bl2, UserFollowListItem.UserFollowListItemListener object2) {
        block9 : {
            block8 : {
                this.a(recAccountIcon.mAccountIcon, n, (Context)object, bl, (UserFollowListItem.UserFollowListItemListener)object2);
                if (recAccountIcon == null) break block8;
                this.a = recAccountIcon.mRecId;
                if (recAccountIcon.mAccountIcon != null) break block9;
            }
            return;
        }
        if (recAccountIcon.mAccountIcon.handle != null) {
            this.b.setText((CharSequence)recAccountIcon.mAccountIcon.handle);
            PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.b, (AccountIcon)recAccountIcon.mAccountIcon);
            this.b.setVisibility(0);
        } else {
            this.b.setText((CharSequence)"");
            this.b.setVisibility(8);
        }
        object2 = recAccountIcon.mAccountIcon.blurb != null ? recAccountIcon.mAccountIcon.blurb : null;
        if (object2 != null && !object2.trim().isEmpty()) {
            this.a((String)object2, true);
        } else {
            this.a("", false);
        }
        if ((object = this.a(recAccountIcon, (Context)object)) != null) {
            this.b((String)object, true);
            return;
        }
        this.b("", false);
    }

    public void a(String string2, boolean bl) {
        this.d.setText((CharSequence)string2);
        if (bl) {
            this.d.setVisibility(0);
            return;
        }
        this.d.setVisibility(8);
    }

    @Override
    protected void a(boolean bl) {
    }

    public void b(String string2, boolean bl) {
        this.e.setText((CharSequence)string2);
        if (bl) {
            this.e.setVisibility(0);
            return;
        }
        this.e.setVisibility(8);
    }

}

