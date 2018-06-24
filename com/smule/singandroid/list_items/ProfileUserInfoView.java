package com.smule.singandroid.list_items;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils.TruncateAt;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.hashtag.CustomLinkMovementMethod;
import com.smule.singandroid.hashtag.CustomLinkMovementMethod.TextClickedListener;
import com.smule.singandroid.hashtag.CustomTypefaceSpan;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.textviews.EllipsizingEndMarginTextView;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.UIHelper;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileUserInfoView extends LinearLayout {
    private static final String f23133k = ProfileUserInfoView.class.getName();
    final int f23134a = 3;
    @ViewById
    protected ProfileImageWithVIPBadge f23135b;
    @ViewById
    protected TextView f23136c;
    @ViewById
    protected TextView f23137d;
    @ViewById
    protected TextView f23138e;
    @ViewById
    protected View f23139f;
    @ViewById
    protected TextView f23140g;
    @ViewById
    protected TextView f23141h;
    @ViewById
    protected TextView f23142i;
    @ViewById
    protected TextView f23143j;
    private ProfileUserInfoViewListener f23144l;
    private String f23145m;
    private ProfileUserInfo f23146n;
    private String f23147o;
    private CharSequence f23148p;
    private boolean f23149q;
    private LocalizedShortNumberFormatter f23150r;

    public interface ProfileUserInfoViewListener {
        void mo6564Q();

        void mo6565R();

        void mo6566a(TextView textView);

        void mo6567a(ProfileImageWithVIPBadge profileImageWithVIPBadge);

        void mo6568b(TextView textView);

        void mo6569c(TextView textView);

        void mo6570d(TextView textView);
    }

    class C46782 implements TextClickedListener {
        final /* synthetic */ ProfileUserInfoView f23132a;

        C46782(ProfileUserInfoView profileUserInfoView) {
            this.f23132a = profileUserInfoView;
        }

        public void mo6871a() {
            this.f23132a.m24428c();
        }
    }

    public static ProfileUserInfoView m24422a(Context context) {
        return ProfileUserInfoView_.m24433b(context);
    }

    public ProfileUserInfoView(Context context) {
        super(context);
    }

    public void setListener(ProfileUserInfoViewListener profileUserInfoViewListener) {
        this.f23144l = profileUserInfoViewListener;
    }

    public void m24424a(final ProfileUserInfo profileUserInfo) {
        if (this.f23146n == null || this.f23146n.m24403a() != profileUserInfo.m24403a()) {
            FollowManager.m18204a().m18216a(Long.valueOf(profileUserInfo.m24403a()), new Runnable(this) {
                final /* synthetic */ ProfileUserInfoView f23131b;

                public void run() {
                    this.f23131b.mo6878b(profileUserInfo);
                }
            });
        } else {
            mo6878b(profileUserInfo);
        }
        this.f23146n = profileUserInfo;
        if (profileUserInfo.m24411b() && this.f23144l != null) {
            this.f23144l.mo6567a(this.f23135b);
        }
        if (profileUserInfo.m24412c() == null || !profileUserInfo.m24412c().equals(this.f23145m)) {
            this.f23145m = profileUserInfo.m24412c();
            this.f23135b.setProfilePicUrl(this.f23145m);
        }
        this.f23135b.setVIP(profileUserInfo.m24415d());
        m24425a(profileUserInfo.m24416e(), profileUserInfo.m24411b());
        if (profileUserInfo.m24417f() != -1) {
            int f = profileUserInfo.m24417f();
            this.f23137d.setText(getResources().getQuantityString(C1947R.plurals.follower_count, f, new Object[]{getLocalizedFormatter().m18999a((long) f, (long) getResources().getInteger(C1947R.integer.long_form_threshold_followers_following))}));
        }
        if (profileUserInfo.m24418g() != -1) {
            f = profileUserInfo.m24418g();
            this.f23136c.setText(getResources().getQuantityString(C1947R.plurals.following_count, f, new Object[]{getLocalizedFormatter().m18999a((long) f, (long) getResources().getInteger(C1947R.integer.long_form_threshold_followers_following))}));
        }
        if (profileUserInfo.m24420i()) {
            this.f23143j.setVisibility(0);
        } else {
            this.f23143j.setVisibility(8);
        }
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f23150r == null) {
            this.f23150r = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f23150r;
    }

    @UiThread
    protected void mo6878b(ProfileUserInfo profileUserInfo) {
        UIHelper.m26198a(getContext(), profileUserInfo.m24403a(), this.f23140g);
        if (profileUserInfo.m24403a() == UserManager.a().f()) {
            this.f23141h.setVisibility(8);
            this.f23142i.setVisibility(8);
            this.f23140g.setVisibility(8);
        } else if (!ChatUtils.a() || profileUserInfo.m24419h() == null) {
            this.f23142i.setVisibility(8);
            this.f23140g.setVisibility(0);
            this.f23141h.setVisibility(8);
        } else if (SingApplication.j().a(profileUserInfo.m24403a())) {
            this.f23142i.setVisibility(0);
            this.f23140g.setVisibility(8);
            this.f23141h.setVisibility(8);
        } else {
            this.f23141h.setVisibility(0);
            this.f23140g.setVisibility(0);
            this.f23142i.setVisibility(8);
        }
    }

    protected void m24425a(String str, boolean z) {
        this.f23148p = null;
        if (str != null) {
            str = str.trim();
            this.f23147o = str;
            setBlurbTextExpanded(this.f23147o);
            if (!z) {
                int i;
                CharSequence charSequence = "1";
                for (i = 1; i < 3; i++) {
                    charSequence = charSequence + "\nTest";
                }
                this.f23138e.setText(charSequence);
                i = LayoutUtils.m25844a(this.f23138e);
                setBlurbTextExpanded(this.f23147o);
                if (LayoutUtils.m25844a(this.f23138e) > i) {
                    this.f23148p = str.replaceAll("\\s+", " ") + EllipsizingEndMarginTextView.f24520a;
                    CharSequence spannableString = new SpannableString(this.f23148p);
                    spannableString.setSpan(new CustomTypefaceSpan(getContext()), spannableString.length() - EllipsizingEndMarginTextView.f24520a.length(), spannableString.length(), 34);
                    this.f23148p = spannableString;
                    setBlurbTextSquished(this.f23148p);
                }
            }
        }
        if (str == null || (str.isEmpty() && !z)) {
            this.f23138e.setVisibility(8);
            this.f23139f.setVisibility(4);
            return;
        }
        this.f23138e.setVisibility(0);
        this.f23139f.setVisibility(8);
    }

    protected void setBlurbTextExpanded(String str) {
        this.f23149q = false;
        this.f23138e.setMaxLines(Integer.MAX_VALUE);
        this.f23138e.setEllipsize(null);
        setupHashTagSpannable(str);
    }

    protected void setBlurbTextSquished(CharSequence charSequence) {
        this.f23149q = true;
        this.f23138e.setMaxLines(3);
        this.f23138e.setEllipsize(TruncateAt.END);
        setupHashTagSpannable(charSequence);
    }

    private void setupHashTagSpannable(CharSequence charSequence) {
        SpannableString spannableString = new SpannableString(charSequence);
        Hashtag.m24194a((MediaPlayingActivity) getContext(), spannableString);
        MovementMethod customLinkMovementMethod = new CustomLinkMovementMethod();
        customLinkMovementMethod.m24186a(new C46782(this));
        this.f23138e.setMovementMethod(customLinkMovementMethod);
        this.f23138e.setText(spannableString);
        this.f23138e.setHighlightColor(0);
    }

    @Click
    protected void m24423a() {
        if (this.f23144l != null) {
            this.f23144l.mo6564Q();
        }
    }

    @Click
    protected void m24426b() {
        if (this.f23144l != null) {
            this.f23144l.mo6565R();
        }
    }

    @Click
    protected void m24428c() {
        if (this.f23146n != null) {
            if (!(this.f23146n.m24411b() || this.f23148p == null)) {
                if (this.f23149q) {
                    setBlurbTextExpanded(this.f23147o);
                } else {
                    setBlurbTextSquished(this.f23148p);
                }
            }
            if (this.f23144l != null) {
                this.f23144l.mo6566a(this.f23138e);
            }
        }
    }

    @Click
    protected void m24429d() {
        if (this.f23144l != null) {
            this.f23144l.mo6568b(this.f23140g);
        }
    }

    @Click
    protected void m24430e() {
        if (this.f23144l != null) {
            this.f23144l.mo6569c(this.f23140g);
        }
    }

    @Click
    protected void m24431f() {
        if (this.f23144l != null) {
            this.f23144l.mo6570d(this.f23140g);
        }
    }
}
