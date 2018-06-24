/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Typeface
 *  android.net.Uri
 *  android.os.Handler
 *  android.text.Html
 *  android.text.Spanned
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageButton
 *  android.widget.LinearLayout
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.utils.CustomTypefaceSpan
 *  com.smule.singandroid.utils.DeepLink
 *  com.smule.singandroid.utils.DeepLink$Hosts
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.StyleReplacer
 *  com.smule.singandroid.utils.TypefaceUtils
 *  com.smule.singandroid.utils.UIHelper
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.UserFollowListItem_;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.sections.feed.FeedDataSource;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import com.smule.singandroid.utils.UIHelper;
import java.text.MessageFormat;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserFollowListItem
extends LinearLayout {
    private static Handler v = new Handler();
    private AccountIcon a;
    @ViewById
    protected TextView b;
    @ViewById
    protected TextView c;
    @ViewById
    protected TextView d;
    @ViewById
    protected TextView e;
    @ViewById
    protected ProfileImageWithVIPBadge f;
    @ViewById
    protected ImageButton g;
    @ViewById
    protected View h;
    @ViewById
    protected View i;
    @ViewById
    protected TextView j;
    @ViewById
    protected TextView k;
    protected String[] l = new String[]{"\ud83d\ude00", "\ud83d\ude02", "\ud83d\ude0a", "\ud83d\ude07", "\ud83d\ude0d", "\ud83d\ude0f", "\ud83d\udc31"};
    @ViewById
    protected View m;
    @ViewById
    protected TextView n;
    @ViewById
    protected TextView o;
    @ViewById
    protected ProgressBar p;
    @ViewById
    protected View q;
    protected int r;
    protected int s = 0;
    private boolean t;
    private UserFollowListItemListener u;

    public UserFollowListItem(Context context) {
        super(context);
    }

    public UserFollowListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void a(final long l, String object, final Context context) {
        UIHelper.a((Context)this.getContext(), (long)l, (ImageButton)this.g);
        object = new View.OnClickListener((String)object){
            final /* synthetic */ String c;
            {
                this.c = string2;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                UserFollowListItem.this.a(l);
                object = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
                com.smule.android.logging.Analytics.a(object, l);
                if (UserFollowListItem.this.t || !UserFollowListItem.this.m.isEnabled()) {
                    return;
                }
                if (l == UserManager.a().f()) {
                    UserFollowListItem.this.s = (UserFollowListItem.this.s + 1) % UserFollowListItem.this.l.length;
                    UserFollowListItem.this.k.setText((CharSequence)UserFollowListItem.this.l[UserFollowListItem.this.s]);
                    return;
                }
                UserFollowListItem.this.t = true;
                UserFollowListItem.this.g.setVisibility(4);
                UserFollowListItem.this.p.setVisibility(0);
                FollowManager.a().a(l, UserFollowListItem.this.getFollowContext(), null, new FollowManager.ToggleFollowStateListener(){

                    @Override
                    public void a(final boolean bl, final boolean bl2, final boolean bl3) {
                        v.post(new Runnable(){

                            /*
                             * Enabled aggressive block sorting
                             */
                            @Override
                            public void run() {
                                boolean bl4 = true;
                                boolean bl22 = true;
                                if (bl3) {
                                    UserFollowListItem userFollowListItem = UserFollowListItem.this;
                                    if (bl2) {
                                        bl22 = false;
                                    }
                                    userFollowListItem.a(bl22);
                                    com.smule.android.utils.Toaster.a(context, 2131297196, Toaster.a);
                                } else if (!bl) {
                                    UserFollowListItem userFollowListItem = UserFollowListItem.this;
                                    bl22 = !bl2 ? bl4 : false;
                                    userFollowListItem.a(bl22);
                                    com.smule.android.utils.Toaster.a(context, 2131297210, Toaster.a);
                                } else if (bl2) {
                                    FeedDataSource.a = true;
                                    UserFollowListItem.this.a(bl2);
                                    com.smule.android.utils.Toaster.a(context, MessageFormat.format(context.getString(2131297195), 3.this.c), Toaster.a);
                                } else {
                                    FeedDataSource.a = true;
                                    UserFollowListItem.this.a(bl2);
                                    com.smule.android.utils.Toaster.a(context, MessageFormat.format(context.getString(2131297209), 3.this.c), Toaster.a);
                                }
                                UIHelper.a((Context)UserFollowListItem.this.getContext(), (long)l, (ImageButton)UserFollowListItem.this.g);
                                UserFollowListItem.this.p.setVisibility(8);
                                UserFollowListItem.this.t = false;
                                if (UserFollowListItem.this.u != null) {
                                    UserFollowListItem.this.u.a(bl, bl2);
                                }
                            }
                        });
                    }

                });
            }

        };
        this.m.setOnClickListener((View.OnClickListener)object);
        this.g.setOnClickListener((View.OnClickListener)object);
    }

    public static UserFollowListItem c(Context context) {
        return UserFollowListItem_.a(context);
    }

    public void a(int n, String string2) {
        if (this.i != null) {
            this.i.setVisibility(n);
        }
        if (this.j != null) {
            this.j.setText((CharSequence)string2);
        }
    }

    protected void a(long l) {
    }

    public void a(AccountIcon accountIcon, int n, Context context, boolean bl, UserFollowListItemListener userFollowListItemListener) {
        this.a(accountIcon, -1, context, bl, userFollowListItemListener, false);
    }

    public void a(AccountIcon accountIcon, int n, Context context, boolean bl, UserFollowListItemListener userFollowListItemListener, boolean bl2) {
        this.a(accountIcon, -1, context, bl, userFollowListItemListener, bl2, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(AccountIcon object, int n, Context context, boolean bl, UserFollowListItemListener userFollowListItemListener, boolean bl2, boolean bl3) {
        int n2 = 0;
        this.a = object;
        this.r = n;
        this.s = 0;
        this.u = userFollowListItemListener;
        this.o.setVisibility(8);
        PerformanceV2Util.a((Resources)this.getResources(), (TextView)this.b, (AccountIcon)object);
        this.f.setProfilePicUrl(this.a.picUrl);
        this.f.setVIP(this.a.a());
        userFollowListItemListener = this.m;
        boolean bl4 = !bl;
        userFollowListItemListener.setEnabled(bl4);
        this.a(this.a.accountId, this.a.handle, context);
        this.h.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                UserFollowListItem.this.f.performClick();
            }
        });
        this.p.setVisibility(8);
        if (bl || object.d()) {
            this.g.setVisibility(4);
        }
        if (!bl && object.d()) {
            this.k.setVisibility(0);
            this.k.setText((CharSequence)this.l[this.s]);
        } else {
            this.k.setVisibility(8);
        }
        object = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                object = UserFollowListItem.this.getAccountIcon();
                if (object.d()) {
                    object = (MediaPlayingActivity)MiscUtils.a((View)UserFollowListItem.this);
                    if (object == null) return;
                    {
                        object.aq();
                        object.finish();
                        Intent intent = MasterActivity.a((Context)object);
                        intent.setData(DeepLink.a((DeepLink.Hosts)DeepLink.Hosts.c, (String)null));
                        object.startActivity(intent);
                        return;
                    }
                }
                object = ProfileFragment.a((AccountIcon)object);
                if (UserFollowListItem.this.u == null) {
                    return;
                }
                UserFollowListItem.this.u.a(Analytics.b);
                UserFollowListItem.this.u.a((ProfileFragment)object);
            }
        };
        this.f.setOnClickListener((View.OnClickListener)object);
        this.b.setOnClickListener((View.OnClickListener)object);
        if (this.q != null) {
            object = this.q;
            n = bl2 ? 0 : 8;
            object.setVisibility(n);
        }
        if (this.n != null) {
            object = this.n;
            n = bl3 ? n2 : 8;
            object.setVisibility(n);
        }
    }

    public void a(AccountIcon accountIcon, Activity activity, boolean bl, UserFollowListItemListener userFollowListItemListener) {
        this.a(accountIcon, -1, (Context)activity, bl, userFollowListItemListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(boolean bl) {
        if (this.u != null) {
            UserFollowListItemListener userFollowListItemListener = this.u;
            Analytics searchResultClkContext = bl ? Analytics.c : Analytics.d;
            userFollowListItemListener.a(searchResultClkContext);
        }
    }

    public void c(String string2, boolean bl) {
        this.c.setText((CharSequence)string2);
        if (bl) {
            this.c.setVisibility(0);
            return;
        }
        this.c.setVisibility(8);
    }

    public AccountIcon getAccountIcon() {
        return this.a;
    }

    protected String getFollowContext() {
        return "APP";
    }

    protected Analytics getRecSysFollowContext() {
        return Analytics.c;
    }

    protected void setBlurbTextExpanded(String string2) {
        this.d.setMaxLines(Integer.MAX_VALUE);
        this.d.setEllipsize(null);
    }

    public void setJoinersStyle(Resources resources) {
        String string2 = this.a.handle;
        Spanned spanned = Html.fromHtml((String)String.format(this.getResources().getString(2131296991), string2));
        CustomTypefaceSpan customTypefaceSpan = new CustomTypefaceSpan(this.getContext(), this.b.getTextSize(), 2131689947, TypefaceUtils.c((Context)this.getContext()));
        CustomTypefaceSpan customTypefaceSpan2 = new CustomTypefaceSpan(this.getContext(), this.b.getTextSize(), 2131689547, TypefaceUtils.a((Context)this.getContext()));
        resources = new StyleReplacer(spanned.toString(), this.b, (Object)customTypefaceSpan2, resources);
        resources.a(spanned.toString(), spanned.toString(), (Object)null);
        resources.a(string2, string2, (Object)customTypefaceSpan, null, this.a.c());
        resources.a();
    }

    public void setTime(long l) {
        if (l > 0) {
            this.o.setVisibility(0);
            this.o.setText((CharSequence)MiscUtils.a((long)l, (boolean)true, (boolean)false, (boolean)true));
            return;
        }
        this.o.setVisibility(8);
    }

    public static interface UserFollowListItemListener {
        public void a(Analytics var1);

        public void a(ProfileFragment var1);

        public void a(boolean var1, boolean var2);
    }

}

