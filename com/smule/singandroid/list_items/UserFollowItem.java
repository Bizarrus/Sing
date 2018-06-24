package com.smule.singandroid.list_items;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import com.smule.singandroid.utils.UIHelper;
import java.text.MessageFormat;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserFollowItem extends LinearLayout {
    private static Handler f23161t = new Handler();
    private AccountIcon f23162a;
    @ViewById
    protected TextView f23163b;
    @ViewById
    protected TextView f23164c;
    @ViewById
    protected ProfileImageWithVIPBadge f23165d;
    @ViewById
    protected ImageButton f23166e;
    @ViewById
    protected View f23167f;
    @ViewById
    protected View f23168g;
    @ViewById
    protected TextView f23169h;
    @ViewById
    protected TextView f23170i;
    protected String[] f23171j;
    @ViewById
    protected View f23172k;
    @ViewById
    protected TextView f23173l;
    @ViewById
    protected TextView f23174m;
    @ViewById
    protected ProgressBar f23175n;
    @ViewById
    protected View f23176o;
    protected int f23177p;
    protected int f23178q;
    private boolean f23179r;
    private UserFollowItemListener f23180s;

    public interface UserFollowItemListener {
        void mo6455a(SearchResultClkContext searchResultClkContext);

        void mo6456a(ProfileFragment profileFragment);

        void mo6457a(boolean z, boolean z2);
    }

    class C47041 implements OnClickListener {
        final /* synthetic */ UserFollowItem f23292a;

        C47041(UserFollowItem userFollowItem) {
            this.f23292a = userFollowItem;
        }

        public void onClick(View view) {
            this.f23292a.f23165d.performClick();
        }
    }

    class C47052 implements OnClickListener {
        final /* synthetic */ UserFollowItem f23293a;

        C47052(UserFollowItem userFollowItem) {
            this.f23293a = userFollowItem;
        }

        public void onClick(View view) {
            ProfileFragment a = ProfileFragment.m21036a(this.f23293a.getAccountIcon());
            if (this.f23293a.f23180s != null) {
                this.f23293a.f23180s.mo6455a(SearchResultClkContext.REGULAR);
                this.f23293a.f23180s.mo6456a(a);
            }
        }
    }

    public static UserFollowItem m24442c(Context context) {
        return UserFollowItem_.m24512a(context);
    }

    public UserFollowItem(Context context) {
        super(context);
        this.f23171j = new String[]{"😀", "😂", "😊", "😇", "😍", "😏", "🐱"};
        this.f23178q = 0;
    }

    public UserFollowItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f23171j = new String[]{"😀", "😂", "😊", "😇", "😍", "😏", "🐱"};
        this.f23178q = 0;
    }

    public AccountIcon getAccountIcon() {
        return this.f23162a;
    }

    public void m24448a(AccountIcon accountIcon, Activity activity, boolean z, UserFollowItemListener userFollowItemListener) {
        m24445a(accountIcon, -1, activity, z, userFollowItemListener);
    }

    public void m24445a(AccountIcon accountIcon, int i, Context context, boolean z, UserFollowItemListener userFollowItemListener) {
        m24446a(accountIcon, -1, context, z, userFollowItemListener, false);
    }

    public void m24446a(AccountIcon accountIcon, int i, Context context, boolean z, UserFollowItemListener userFollowItemListener, boolean z2) {
        m24447a(accountIcon, -1, context, z, userFollowItemListener, z2, false);
    }

    public void m24447a(AccountIcon accountIcon, int i, Context context, boolean z, UserFollowItemListener userFollowItemListener, boolean z2, boolean z3) {
        int i2 = 0;
        this.f23162a = accountIcon;
        this.f23177p = i;
        this.f23178q = 0;
        this.f23180s = userFollowItemListener;
        this.f23174m.setVisibility(8);
        PerformanceV2Util.m25939a(getResources(), this.f23163b, accountIcon);
        this.f23165d.setProfilePicUrl(this.f23162a.picUrl);
        this.f23165d.setVIP(this.f23162a.a());
        this.f23172k.setEnabled(!z);
        m24439a(this.f23162a.accountId, this.f23162a.handle, context);
        this.f23167f.setOnClickListener(new C47041(this));
        this.f23175n.setVisibility(8);
        if (z || accountIcon.d()) {
            this.f23166e.setVisibility(4);
        }
        if (z || !accountIcon.d()) {
            this.f23170i.setVisibility(8);
        } else {
            this.f23170i.setVisibility(0);
            this.f23170i.setText(this.f23171j[this.f23178q]);
        }
        OnClickListener c47052 = new C47052(this);
        this.f23165d.setOnClickListener(c47052);
        this.f23163b.setOnClickListener(c47052);
        this.f23176o.setVisibility(z2 ? 0 : 8);
        TextView textView = this.f23173l;
        if (!z3) {
            i2 = 8;
        }
        textView.setVisibility(i2);
    }

    public void m24449a(String str, boolean z) {
        this.f23164c.setText(str);
        if (z) {
            this.f23164c.setVisibility(0);
        } else {
            this.f23164c.setVisibility(8);
        }
    }

    public void setJoinersStyle(Resources resources) {
        String str = this.f23162a.handle;
        CharSequence fromHtml = Html.fromHtml(String.format(getResources().getString(C1947R.string.news_feed_join_one), new Object[]{str}));
        Object customTypefaceSpan = new CustomTypefaceSpan(getContext(), this.f23163b.getTextSize(), C1947R.color.sub_heading_dark, TypefaceUtils.m26195c(getContext()));
        StyleReplacer styleReplacer = new StyleReplacer(fromHtml.toString(), this.f23163b, new CustomTypefaceSpan(getContext(), this.f23163b.getTextSize(), C1947R.color.body_text_grey, TypefaceUtils.m26189a(getContext())), resources);
        styleReplacer.m26182a(fromHtml.toString(), fromHtml.toString(), null);
        styleReplacer.m26184a(str, str, customTypefaceSpan, null, this.f23162a.c());
        styleReplacer.m26174a();
    }

    public void setTime(long j) {
        if (j > 0) {
            this.f23174m.setVisibility(0);
            this.f23174m.setText(MiscUtils.m25887a(j, true, false, true));
            return;
        }
        this.f23174m.setVisibility(8);
    }

    protected void mo6879a(long j) {
    }

    protected void mo6880a(boolean z) {
        if (this.f23180s != null) {
            this.f23180s.mo6455a(z ? SearchResultClkContext.FOLLOW : SearchResultClkContext.UNFOLLOW);
        }
    }

    private void m24439a(long j, String str, Context context) {
        UIHelper.m26197a(getContext(), j, this.f23166e);
        final long j2 = j;
        final Context context2 = context;
        final String str2 = str;
        OnClickListener c47083 = new OnClickListener(this) {
            final /* synthetic */ UserFollowItem f23302d;

            class C47071 implements ToggleFollowStateListener {
                final /* synthetic */ C47083 f23298a;

                C47071(C47083 c47083) {
                    this.f23298a = c47083;
                }

                public void mo6399a(final boolean z, final boolean z2, final boolean z3) {
                    UserFollowItem.f23161t.post(new Runnable(this) {
                        final /* synthetic */ C47071 f23297d;

                        public void run() {
                            boolean z = true;
                            UserFollowItem userFollowItem;
                            if (z3) {
                                userFollowItem = this.f23297d.f23298a.f23302d;
                                if (z2) {
                                    z = false;
                                }
                                userFollowItem.mo6880a(z);
                                Toaster.a(context2, C1947R.string.profile_follow_limit_reached, Toaster$Duration.SHORT);
                            } else if (!z) {
                                userFollowItem = this.f23297d.f23298a.f23302d;
                                if (z2) {
                                    z = false;
                                }
                                userFollowItem.mo6880a(z);
                                Toaster.a(context2, C1947R.string.profile_update_error, Toaster$Duration.SHORT);
                            } else if (z2) {
                                this.f23297d.f23298a.f23302d.mo6880a(z2);
                                Toaster.a(context2, MessageFormat.format(context2.getString(C1947R.string.profile_follow_format), new Object[]{str2}), Toaster$Duration.SHORT);
                            } else {
                                this.f23297d.f23298a.f23302d.mo6880a(z2);
                                Toaster.a(context2, MessageFormat.format(context2.getString(C1947R.string.profile_unfollow_format), new Object[]{str2}), Toaster$Duration.SHORT);
                            }
                            UIHelper.m26197a(this.f23297d.f23298a.f23302d.getContext(), j2, this.f23297d.f23298a.f23302d.f23166e);
                            this.f23297d.f23298a.f23302d.f23175n.setVisibility(8);
                            this.f23297d.f23298a.f23302d.f23179r = false;
                            if (this.f23297d.f23298a.f23302d.f23180s != null) {
                                this.f23297d.f23298a.f23302d.f23180s.mo6457a(z, z2);
                            }
                        }
                    });
                }
            }

            public void onClick(View view) {
                this.f23302d.mo6879a(j2);
                if (!this.f23302d.f23179r && this.f23302d.f23172k.isEnabled()) {
                    if (j2 == UserManager.a().f()) {
                        this.f23302d.f23178q = (this.f23302d.f23178q + 1) % this.f23302d.f23171j.length;
                        this.f23302d.f23170i.setText(this.f23302d.f23171j[this.f23302d.f23178q]);
                        return;
                    }
                    this.f23302d.f23179r = true;
                    this.f23302d.f23166e.setVisibility(4);
                    this.f23302d.f23175n.setVisibility(0);
                    FollowManager.m18204a().m18215a(Long.valueOf(j2), new C47071(this));
                }
            }
        };
        this.f23172k.setOnClickListener(c47083);
        this.f23166e.setOnClickListener(c47083);
    }

    public void m24443a(int i, String str) {
        this.f23168g.setVisibility(i);
        this.f23169h.setText(str);
    }
}
