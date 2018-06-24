/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.CustomTypefaceSpan
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.StyleReplacer
 *  com.smule.singandroid.utils.TypefaceUtils
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.OpenCallListItem_;
import com.smule.singandroid.list_items.VideoUploadingView;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class OpenCallListItem
extends VideoUploadingView {
    private static final String a = OpenCallListItem.class.getName();
    private static long b = TimeUnit.DAYS.toSeconds(365);
    @ViewById
    protected TextView A;
    @ViewById
    protected View B;
    private ExpandedPerformanceItemListener E;
    private LocalizedShortNumberFormatter F;
    private boolean G;
    private boolean H = false;
    private boolean I;
    protected Context c;
    protected ImageUtils d = new Object(){
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
    boolean e;
    @ViewById
    protected View f;
    @ViewById
    protected TextView g;
    @ViewById
    protected TextView h;
    @ViewById
    protected TextView i;
    @ViewById
    protected TextView j;
    @ViewById
    protected ProfileImageWithVIPBadge k;
    @ViewById
    protected TextView l;
    @ViewById
    protected Button m;
    @ViewById
    protected ImageView n;
    @ViewById
    protected TextView o;
    @ViewById
    protected TextView p;
    @ViewById
    protected View q;
    @ViewById
    protected View r;
    @ViewById
    protected View s;
    @ViewById
    protected Button t;
    @ViewById
    protected View u;
    @ViewById
    protected View v;
    @ViewById
    protected View w;
    @ViewById
    protected TextView x;
    @ViewById
    protected TextView y;
    @ViewById
    protected View z;

    public OpenCallListItem(Context context) {
        super(context);
        this.c = context;
    }

    private boolean b(PerformanceV2 performanceV2) {
        long l = System.currentTimeMillis() / 1000;
        if (performanceV2.p() && performanceV2.expireAt - l > b) {
            return true;
        }
        return false;
    }

    public static OpenCallListItem c(Context context) {
        OpenCallListItem openCallListItem = OpenCallListItem_.a(context);
        openCallListItem.c = context;
        return openCallListItem;
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.F == null) {
            this.F = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.F;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setDividerVisibility(boolean bl) {
        View view = this.r;
        int n = bl ? 8 : 0;
        view.setVisibility(n);
    }

    protected void a() {
        boolean bl = false;
        if (this.D.p()) {
            this.w.setVisibility(0);
            if (this.t != null) {
                if (this.t.getVisibility() == 0) {
                    bl = true;
                }
                this.setExpireTime(bl);
            }
            return;
        }
        this.w.setVisibility(8);
    }

    public void a(Drawable drawable2, Drawable drawable3) {
        this.m.setBackground(drawable2);
        if (this.t != null) {
            this.t.setBackground(drawable3);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(PerformanceV2 performanceV2, boolean bl) {
        Object object;
        String string2;
        if (performanceV2 == null) {
            Log.e(a, "setOpenCall called with null open call");
            return;
        }
        this.setPerformance(performanceV2);
        if (this.D.f()) {
            object = this.getResources();
            int n = this.D.seed ? 2131297055 : 2131297056;
            string2 = object.getString(n);
            object = "";
        } else {
            string2 = this.getResources().getString(2131297058);
            object = this.D.origTrackPartId > 0 ? Integer.toString(this.D.origTrackPartId) : "1";
        }
        CustomTypefaceSpan customTypefaceSpan = new CustomTypefaceSpan(this.getContext(), this.i.getTextSize(), 2131689947, TypefaceUtils.c((Context)this.getContext()));
        CustomTypefaceSpan customTypefaceSpan2 = new CustomTypefaceSpan(this.getContext(), this.i.getTextSize(), 2131689547, TypefaceUtils.a((Context)this.getContext()));
        string2 = new StyleReplacer(string2, this.i, (Object)customTypefaceSpan2, this.getResources());
        string2.a("{0}", this.D.accountIcon.handle, (Object)customTypefaceSpan, null, this.D.accountIcon.c());
        string2.a("{1}", (String)object, (Object)null);
        string2.a();
        if (this.D.message != null && this.D.message.trim().length() > 0) {
            this.j.setVisibility(0);
            this.j.setText((CharSequence)("\u201c" + this.D.message.trim() + "\u201d"));
        } else {
            this.j.setVisibility(8);
        }
        this.b();
        this.k.setProfilePicUrl(performanceV2.accountIcon.picUrl);
        this.k.setVIP(performanceV2.accountIcon.a());
        if (performanceV2.p()) {
            this.m.setVisibility(0);
            this.i();
            if (performanceV2.e()) {
                this.m.setContentDescription((CharSequence)"duet");
            } else if (performanceV2.f()) {
                this.m.setContentDescription((CharSequence)"group");
            }
        } else {
            this.m.setVisibility(8);
            this.n.setVisibility(8);
        }
        this.m.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Log.b(a, "Join button clicked");
                OpenCallListItem.this.d();
            }
        });
        this.p.setText((CharSequence)MiscUtils.a((long)performanceV2.createdAt, (boolean)false, (boolean)true, (boolean)false));
        this.I = bl;
        this.setDividerVisibility(bl);
    }

    public void a(ProfileUserInfo.ColorSet colorSet) {
        Drawable drawable2 = this.getResources().getDrawable(2130838177);
        drawable2.setColorFilter(colorSet.e, PorterDuff.Mode.SRC_ATOP);
        this.t.setBackground(drawable2);
        this.m.setBackground(drawable2);
        this.t.setTextColor(-1);
        this.m.setTextColor(-1);
    }

    public void a(String string2, int n, int n2) {
        this.A.setText((CharSequence)string2);
        this.A.setCompoundDrawablesWithIntrinsicBounds(0, 0, n2, 0);
        this.B.setBackgroundColor(n);
        this.z.setVisibility(0);
        this.setDividerVisibility(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl) {
        Button button = this.t;
        int n = bl ? 0 : 8;
        button.setVisibility(n);
        this.setExpireTime(bl);
    }

    protected void b() {
        this.ae.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                OpenCallListItem.this.e();
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                OpenCallListItem.this.f();
            }
        };
        this.k.setOnClickListener(onClickListener);
        this.v.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                OpenCallListItem.this.g();
            }
        });
    }

    protected void c() {
        String string2 = this.getAlbumArtUrl();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.d.a(string2, this.ae.a, 2130837860);
            return;
        }
        this.ae.a.setImageResource(2130837860);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void c(boolean bl) {
        View view = this.f;
        int n = bl ? 0 : 8;
        view.setVisibility(n);
    }

    protected void d() {
        if (this.E != null) {
            if (this.D == null) {
                Log.d(a, "onOpenCallItemJoinCallBack - mPerformance is null!");
            }
            this.E.a(this, this.D);
        }
    }

    protected void e() {
        Log.b(a, "onExpandedPerformanceItemAlbumArtClickCallBack - called");
        if (this.E != null) {
            this.E.b(this, this.D);
        }
    }

    protected void f() {
        Log.b(a, "onExpandedPerformanceItemProfileClickCallBack - called");
        if (this.E != null) {
            this.E.c(this, this.D);
        }
    }

    protected void g() {
        Log.b(a, "onExpandedPerformanceItemMetaDataClickCallBack - called");
        if (this.E != null) {
            this.E.d(this, this.D);
        }
    }

    protected String getAlbumArtUrl() {
        if (this.D.coverUrl != null) {
            return this.D.coverUrl;
        }
        return null;
    }

    @Click
    protected void h() {
        if (this.E != null) {
            this.E.a(this.D, this.G);
        }
    }

    protected void i() {
        this.n.setVisibility(4);
    }

    public void j() {
        if (this.D != null) {
            this.a(this.D.p());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void k() {
        TextView textView = this.g;
        int n = this.D.isPrivate ? 2130837973 : 0;
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, n, 0);
    }

    @Click
    protected void l() {
        com.smule.android.network.managers.PerformanceManager.a().a(this.D.performanceKey, false, new PerformanceManager(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(PerformanceManager.PerformanceResponse performanceResponse) {
                if (performanceResponse.a()) {
                    performanceResponse = ChatShareInviteActivity.a(OpenCallListItem.this.c, OpenCallListItem.this.D, Analytics.i);
                    OpenCallListItem.this.c.startActivity((Intent)performanceResponse);
                    return;
                } else {
                    if (!performanceResponse.a.e() || !(OpenCallListItem.this.c instanceof BaseActivity)) return;
                    {
                        ((BaseActivity)OpenCallListItem.this.c).a(performanceResponse.a.f, true, null);
                        return;
                    }
                }
            }
        });
    }

    public void setExpandedPerformanceListener(ExpandedPerformanceItemListener expandedPerformanceItemListener) {
        this.E = expandedPerformanceItemListener;
    }

    protected void setExpireTime(boolean bl) {
        if (this.b(this.D)) {
            this.o.setVisibility(4);
            return;
        }
        this.o.setText((CharSequence)this.getResources().getString(2131297116, new Object[]{MiscUtils.a((long)this.D.expireAt, (boolean)bl, (boolean)false)}));
        this.o.setVisibility(0);
    }

    public void setHideBookmarkOption(boolean bl) {
        this.G = bl;
    }

    public void setIsBookmarkItem(boolean bl) {
        this.H = bl;
    }

    public void setIsSectionFree(boolean bl) {
        this.e = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void setPerformance(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            Log.e(a, "setPerformance called with null performance");
            return;
        }
        this.setTag((Object)performanceV2.performanceKey);
        this.D = performanceV2;
        this.g.setText((CharSequence)performanceV2.title);
        this.g.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        if (this.h != null) {
            this.h.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.D, (boolean)true));
        }
        this.j();
        this.c();
        this.a(this.D.performanceKey);
        int n = performanceV2.e() && performanceV2.p() ? 2130837903 : (performanceV2.f() && performanceV2.p() ? 2130837932 : 2130838010);
        this.m.setCompoundDrawablesWithIntrinsicBounds(0, 0, n, 0);
        this.m.setText((CharSequence)this.getResources().getString(2131296695));
        this.x.setText((CharSequence)this.getLocalizedFormatter().a(performanceV2.totalListens, this.getResources().getInteger(2131623962)));
        this.y.setText((CharSequence)this.getLocalizedFormatter().a(performanceV2.totalLoves, this.getResources().getInteger(2131623962)));
        this.a();
        this.ae.a(this.a(this.D), 2130838138);
    }

    public void v() {
        this.z.setVisibility(8);
        this.setDividerVisibility(this.I);
    }

    public static interface ExpandedPerformanceItemListener {
        public void a(PerformanceV2 var1, boolean var2);

        public void a(OpenCallListItem var1, PerformanceV2 var2);

        public void b(OpenCallListItem var1, PerformanceV2 var2);

        public void c(OpenCallListItem var1, PerformanceV2 var2);

        public void d(OpenCallListItem var1, PerformanceV2 var2);
    }

}

