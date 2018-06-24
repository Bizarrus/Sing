/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.support.v4.content.ContextCompat
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.text.method.MovementMethod
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  com.smule.singandroid.textviews.EllipsizingEndMarginTextView
 *  com.smule.singandroid.utils.LayoutUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.customviews.CoverPhotoImageView;
import com.smule.singandroid.customviews.ProfileImageListView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.hashtag.CustomLinkMovementMethod;
import com.smule.singandroid.hashtag.CustomTypefaceSpan;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.profile.ProfileUserInfoView_;
import com.smule.singandroid.profile.Theme;
import com.smule.singandroid.textviews.EllipsizingEndMarginTextView;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileUserInfoView
extends LinearLayout
implements ProfileImageListView.ProfileImageListViewListener {
    private static final String x = ProfileUserInfoView.class.getSimpleName();
    private final float A = 0.26944444f;
    private final float B = 0.5f;
    private ProfileUserInfo.ColorSet C;
    private final int[] D = new int[]{0, 0};
    private final GradientDrawable E;
    private boolean F;
    private ProfileUserInfoViewListener G;
    private String H;
    private ProfileUserInfo I;
    private String J;
    private CharSequence K;
    private boolean L;
    private LocalizedShortNumberFormatter M;
    private int N;
    private int O;
    @ViewById
    protected ViewGroup a;
    @ViewById
    protected View b;
    @ViewById
    protected View c;
    @ViewById
    protected TextView d;
    @ViewById
    protected TextView e;
    @ViewById
    protected ProfileImageWithVIPBadge f;
    @ViewById
    protected ImageView g;
    @ViewById
    protected TextView h;
    @ViewById
    protected TextView i;
    @ViewById
    protected EllipsizingEndMarginTextView j;
    @ViewById
    protected FrameLayout k;
    @ViewById
    protected ImageView l;
    @ViewById
    protected ImageView m;
    @ViewById
    protected FrameLayout n;
    @ViewById
    protected ImageView o;
    @ViewById
    protected View p;
    @ViewById
    protected TextView q;
    @ViewById
    protected ViewGroup r;
    @ViewById
    protected CoverPhotoImageView s;
    @ViewById
    protected ImageView t;
    @ViewById
    protected ViewGroup u;
    @ViewById
    protected TextView v;
    @ViewById
    protected ProfileImageListView w;
    private final int y = 3;
    private final float z = 360.0f;

    public ProfileUserInfoView(Context context) {
        this(context, null);
    }

    public ProfileUserInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = (GradientDrawable)ContextCompat.getDrawable((Context)this.getContext(), (int)2130838164);
    }

    public static ProfileUserInfoView a(Context context) {
        return ProfileUserInfoView_.b(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(long l) {
        if (FollowManager.a().a(l)) {
            this.l.setImageResource(2130838161);
            this.m.setImageResource(2130837927);
        } else {
            this.l.setImageResource(2130838162);
            this.m.setImageResource(2130837925);
            this.a(this.m);
        }
        this.k.setVisibility(0);
    }

    private void a(ImageView imageView) {
        if (this.C != null) {
            imageView.getDrawable().mutate().setColorFilter(this.C.e, PorterDuff.Mode.MULTIPLY);
        }
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.M == null) {
            this.M = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.M;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void i() {
        if (this.C != null) {
            this.h.setTextColor(this.C.b);
            this.i.setTextColor(this.C.b);
            int n = this.j() ? -1 : this.C.d;
            this.j.setTextColor(n);
            this.d.setTextColor(n);
            this.e.setTextColor(n);
            this.v.setTextColor(n);
            this.a(this.m);
            this.a(this.t);
            this.a(this.o);
        }
    }

    private boolean j() {
        if (this.s.getVisibility() == 0 && this.C != null && this.C.a == ContextCompat.getColor((Context)this.getContext(), (int)Theme.a.a())) {
            return true;
        }
        return false;
    }

    private void setupHashTagSpannable(CharSequence charSequence) {
        charSequence = new SpannableString(charSequence);
        Hashtag.a((MediaPlayingActivity)this.getContext(), (SpannableString)charSequence);
        CustomLinkMovementMethod customLinkMovementMethod = new CustomLinkMovementMethod(this.getContext());
        customLinkMovementMethod.a(new CustomLinkMovementMethod.TextClickedListener(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a() {
                block8 : {
                    block7 : {
                        if (ProfileUserInfoView.this.I == null) {
                            return;
                        }
                        boolean bl = false;
                        if (bl) break block7;
                        if (!ProfileUserInfoView.this.I.g() && ProfileUserInfoView.this.K != null) {
                            if (ProfileUserInfoView.this.L) {
                                ProfileUserInfoView.this.setBlurbTextExpanded(ProfileUserInfoView.this.J);
                            } else {
                                ProfileUserInfoView.this.setBlurbTextSquished(ProfileUserInfoView.this.K);
                            }
                        }
                        if (ProfileUserInfoView.this.G != null) break block8;
                    }
                    return;
                }
                ProfileUserInfoView.this.G.a((TextView)ProfileUserInfoView.this.j);
            }
        });
        this.j.setMovementMethod((MovementMethod)customLinkMovementMethod);
        this.j.setText(charSequence);
        this.j.setHighlightColor(0);
    }

    @AfterViews
    protected void a() {
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                Object object = (RelativeLayout.LayoutParams)ProfileUserInfoView.this.s.getLayoutParams();
                object.height = ProfileUserInfoView.this.s.getWidth();
                ProfileUserInfoView.this.N = (int)((float)ProfileUserInfoView.this.s.getWidth() * 0.26944444f);
                ProfileUserInfoView.this.O = (int)((float)ProfileUserInfoView.this.s.getWidth() * 0.5f);
                ProfileUserInfoView.this.s.setLayoutParams((ViewGroup.LayoutParams)object);
                ProfileUserInfoView.this.r.invalidate();
                object = ProfileUserInfoView.this;
                boolean bl = ProfileUserInfoView.this.getCoverPhoto() != null;
                object.a(bl);
                ProfileUserInfoView.this.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
            }
        });
        this.w.setListener(this);
    }

    @Override
    public void a(AccountIcon accountIcon) {
        if (this.G != null) {
            this.G.b(accountIcon);
        }
    }

    public void a(final ProfileUserInfo profileUserInfo) {
        if (this.I == null || this.I.b() != profileUserInfo.b()) {
            FollowManager.a().a((Long)profileUserInfo.b(), new Runnable(){

                @Override
                public void run() {
                    ProfileUserInfoView.this.b(profileUserInfo);
                }
            });
            return;
        }
        this.b(profileUserInfo);
    }

    public void a(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.s.setVisibility(0);
            this.s.setUrl(string2);
            return;
        }
        this.s.setVisibility(4);
        this.s.setPhoto(null);
    }

    protected void a(String charSequence, boolean bl) {
        this.K = null;
        String string2 = charSequence;
        if (charSequence != null) {
            String string3;
            this.J = string3 = charSequence.trim();
            this.setBlurbTextExpanded(this.J);
            string2 = string3;
            if (!this.I.g()) {
                int n;
                charSequence = "1";
                for (n = 1; n < 3; ++n) {
                    charSequence = (String)charSequence + "\nTest";
                }
                this.j.setText(charSequence);
                n = LayoutUtils.a((TextView)this.j);
                this.setBlurbTextExpanded(this.J);
                string2 = string3;
                if (LayoutUtils.a((TextView)this.j) > n) {
                    charSequence = this.j.getEllipsis();
                    this.K = string3.replaceAll("\\s+", " ") + charSequence;
                    string2 = new SpannableString(this.K);
                    string2.setSpan((Object)new CustomTypefaceSpan(this.getContext()), string2.length() - charSequence.length(), string2.length(), 34);
                    this.K = string2;
                    this.setBlurbTextSquished(this.K);
                    string2 = string3;
                }
            }
        }
        if (TextUtils.isEmpty((CharSequence)string2) && !bl) {
            this.j.setVisibility(8);
            return;
        }
        this.j.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.a.getLayoutParams();
        layoutParams.topMargin = bl ? this.O : this.N;
        this.a.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl, ProfileUserInfo profileUserInfo) {
        int n;
        this.a(profileUserInfo);
        this.I = profileUserInfo;
        if (profileUserInfo.g() && this.G != null) {
            this.G.a(this.f);
        }
        if (profileUserInfo.i() == null || !profileUserInfo.i().equals(this.H)) {
            this.H = profileUserInfo.i();
            this.f.setProfilePicUrl(this.H);
        }
        if (!this.F && profileUserInfo.d() != null) {
            Log.b(x, "update");
            this.F = true;
            this.a(profileUserInfo.d().coverUrl);
            this.setColorTheme(this.C);
        }
        if (!profileUserInfo.j() || profileUserInfo.d() == null) {
            this.d.setText((CharSequence)profileUserInfo.p());
            this.e.setVisibility(8);
            this.u.setVisibility(8);
        } else {
            List<AccountIcon> list;
            if (!TextUtils.isEmpty((CharSequence)profileUserInfo.d().displayName)) {
                this.d.setText((CharSequence)profileUserInfo.d().displayName);
                this.e.setText((CharSequence)("@" + profileUserInfo.p()));
                this.e.setVisibility(0);
            } else {
                this.d.setText((CharSequence)profileUserInfo.p());
                this.e.setVisibility(8);
            }
            if ((list = profileUserInfo.d().mentionAccountIcons) != null && !list.isEmpty() && profileUserInfo.d().displayMentions) {
                this.u.setVisibility(0);
                this.w.setAccountIcons(list);
            } else {
                this.u.setVisibility(8);
            }
        }
        this.f.setVIP(profileUserInfo.j());
        this.a(profileUserInfo.k(), profileUserInfo.f());
        if (profileUserInfo.l() != -1) {
            n = profileUserInfo.l();
            this.i.setText((CharSequence)this.getResources().getQuantityString(2131361804, n, new Object[]{this.getLocalizedFormatter().a(n, this.getResources().getInteger(2131623963))}));
        }
        if (profileUserInfo.m() != -1) {
            n = profileUserInfo.m();
            this.h.setText((CharSequence)this.getResources().getQuantityString(2131361805, n, new Object[]{this.getLocalizedFormatter().a(n, this.getResources().getInteger(2131623963))}));
        }
        if (profileUserInfo.o()) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
        bl = bl || this.getCoverPhoto() != null;
        this.a(bl);
    }

    public void b() {
        this.a(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void b(ProfileUserInfo profileUserInfo) {
        if (profileUserInfo.b() == UserManager.a().f()) {
            boolean bl = this.G != null && this.G.ab();
            if (bl) {
                this.n.setVisibility(0);
                this.k.setVisibility(0);
                this.p.setVisibility(8);
                return;
            } else {
                this.n.setVisibility(8);
                this.k.setVisibility(8);
                if (!profileUserInfo.g()) return;
                {
                    this.p.setVisibility(0);
                    return;
                }
            }
        }
        this.p.setVisibility(8);
        if (!ChatUtils.a() || profileUserInfo.n() == null) {
            this.a(profileUserInfo.b());
            profileUserInfo = (ViewGroup.MarginLayoutParams)this.k.getLayoutParams();
            profileUserInfo.rightMargin = this.getContext().getResources().getDimensionPixelOffset(2131428173);
            this.k.setLayoutParams((ViewGroup.LayoutParams)profileUserInfo);
            this.n.setVisibility(8);
            return;
        }
        if (SingApplication.k().a(profileUserInfo.b())) {
            this.k.setVisibility(8);
            this.n.setVisibility(8);
            return;
        }
        this.a(profileUserInfo.b());
        this.n.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(boolean bl) {
        int n = bl ? 0 : 8;
        if (bl) {
            this.m.setImageResource(2130837925);
            this.a(this.m);
        }
        this.k.setVisibility(n);
        this.n.setVisibility(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void c() {
        boolean bl = this.getCoverPhoto() != null;
        this.a(bl);
    }

    @Click
    protected void d() {
        if (this.G != null) {
            this.G.Z();
        }
    }

    @Click
    protected void e() {
        if (this.G != null) {
            this.G.aa();
        }
    }

    @Click
    protected void f() {
        if (this.G != null) {
            this.G.b((View)this.k);
        }
    }

    @Click
    protected void g() {
        if (this.G != null) {
            this.G.c((View)this.n);
        }
    }

    public Bitmap getCoverPhoto() {
        if (this.s.getVisibility() == 4) {
            return null;
        }
        return this.s.getBitmap();
    }

    public View getProfileCameraButton() {
        return this.g;
    }

    public ProfileImageWithVIPBadge getProfileImage() {
        return this.f;
    }

    public void h() {
        this.F = false;
        this.H = "";
        this.f.c();
    }

    protected void setBlurbTextExpanded(String string2) {
        this.L = false;
        this.j.setMaxLines(Integer.MAX_VALUE);
        this.j.setEllipsize(null);
        this.setupHashTagSpannable(string2);
    }

    protected void setBlurbTextSquished(CharSequence charSequence) {
        this.L = true;
        this.j.setMaxLines(3);
        this.j.setEllipsize(TextUtils.TruncateAt.END);
        this.setupHashTagSpannable(charSequence);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setColorTheme(ProfileUserInfo.ColorSet colorSet) {
        if (colorSet == null || colorSet.a == 0 && colorSet.c == 0) {
            return;
        }
        Log.b(x, "setColorTheme:" + colorSet.a + " " + colorSet.b);
        this.C = colorSet;
        RelativeLayout.LayoutParams layoutParams = this.D;
        int n = this.j() ? ContextCompat.getColor((Context)this.getContext(), (int)2131689861) : colorSet.a;
        layoutParams[0] = n;
        this.r.setBackgroundColor(colorSet.c);
        this.E.setColors(this.D);
        this.b.setBackground((Drawable)this.E);
        layoutParams = (RelativeLayout.LayoutParams)this.b.getLayoutParams();
        if (this.s.getVisibility() == 4) {
            layoutParams.addRule(6, 2131756389);
        } else {
            layoutParams.addRule(6, 2131756391);
        }
        this.b.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        layoutParams = this.c;
        n = this.j() ? ContextCompat.getColor((Context)this.getContext(), (int)2131689861) : colorSet.a;
        layoutParams.setBackgroundColor(n);
        this.i();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setCoverPhoto(Bitmap bitmap) {
        this.s.setUrl(null);
        if (bitmap != null) {
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(4);
        }
        this.s.setPhoto(bitmap);
        this.setColorTheme(this.C);
    }

    public void setCoverPhotoTranslationY(float f) {
        this.s.setTranslationY(f);
    }

    public void setListener(ProfileUserInfoViewListener profileUserInfoViewListener) {
        this.G = profileUserInfoViewListener;
    }

    public void setProfilePhoto(Bitmap bitmap) {
        this.f.setBitmap(bitmap);
    }

    public static interface ProfileUserInfoViewListener {
        public void Z();

        public void a(TextView var1);

        public void a(ProfileImageWithVIPBadge var1);

        public void aa();

        public boolean ab();

        public void b(View var1);

        public void b(AccountIcon var1);

        public void c(View var1);
    }

}

