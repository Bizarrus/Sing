package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.ui.SNPImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.R$styleable;
import com.smule.singandroid.fragments.JoinersListFragment;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileImageWithVIPBadge extends RelativeLayout {
    @ViewById
    public SNPImageView f21825a;
    @ViewById
    protected ImageView f21826b;
    @ViewById
    protected TextView f21827c;
    protected ImageUtils$ImageViewLoadOptimizer f21828d = new ImageUtils$ImageViewLoadOptimizer();
    protected int f21829e;
    protected int f21830f;
    protected int f21831g = 0;
    protected int f21832h;
    protected float f21833i;
    protected int f21834j = 0;
    protected int f21835k;
    protected boolean f21836l = true;
    protected boolean f21837m = false;
    protected String f21838n;
    protected LayoutParams f21839o;
    protected LayoutParams f21840p;
    protected LayoutParams f21841q;
    protected LocalizedShortNumberFormatter f21842r;
    private final String f21843s = ProfileImageWithVIPBadge.class.getSimpleName();
    private boolean f21844t = true;
    private int f21845u;

    class C44233 implements Runnable {
        final /* synthetic */ ProfileImageWithVIPBadge f21823a;

        C44233(ProfileImageWithVIPBadge profileImageWithVIPBadge) {
            this.f21823a = profileImageWithVIPBadge;
        }

        public void run() {
            this.f21823a.requestLayout();
        }
    }

    class ProfileImageException extends Exception {
        final /* synthetic */ ProfileImageWithVIPBadge f21824a;

        public ProfileImageException(ProfileImageWithVIPBadge profileImageWithVIPBadge, Throwable th) {
            this.f21824a = profileImageWithVIPBadge;
            super(th);
        }
    }

    public ProfileImageWithVIPBadge(Context context) {
        super(context);
        m23384a(context, null, 0);
    }

    public ProfileImageWithVIPBadge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23384a(context, attributeSet, 0);
    }

    public ProfileImageWithVIPBadge(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23384a(context, attributeSet, i);
    }

    private void m23384a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ProfileImageWithVIPBadge_, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            switch (obtainStyledAttributes.getIndex(i2)) {
                case 0:
                    try {
                        this.f21829e = obtainStyledAttributes.getInt(i2, -1);
                        break;
                    } catch (UnsupportedOperationException e) {
                        Log.e(this.f21843s, e.getMessage());
                        break;
                    }
                case 1:
                    this.f21836l = obtainStyledAttributes.getBoolean(i2, true);
                    break;
                default:
                    break;
            }
        }
        obtainStyledAttributes.recycle();
        m23388a(this.f21829e);
    }

    public void setLoadImageWithBorder(boolean z) {
        this.f21844t = z;
    }

    public void m23388a(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        this.f21829e = i;
        switch (this.f21829e) {
            case 1:
                i2 = C1947R.dimen.profile_tiny;
                i3 = C1947R.dimen.profile_tiny_vip;
                i4 = C1947R.dimen.profile_tiny_vip_margin;
                i5 = C1947R.dimen.profile_tiny_perf_count_font;
                this.f21830f = C1947R.drawable.icn_default_profile_small;
                this.f21845u = 0;
                break;
            case 2:
                i2 = C1947R.dimen.profile_small;
                i3 = C1947R.dimen.profile_small_vip;
                i4 = C1947R.dimen.profile_small_vip_margin;
                i5 = C1947R.dimen.profile_small_perf_count_font;
                this.f21830f = C1947R.drawable.icn_default_profile_small;
                this.f21845u = 0;
                break;
            case 4:
                i2 = C1947R.dimen.profile_large;
                i3 = C1947R.dimen.profile_large_vip;
                i4 = C1947R.dimen.profile_large_vip_margin;
                i5 = C1947R.dimen.profile_large_perf_count_font;
                this.f21830f = C1947R.drawable.icn_default_profile_large;
                this.f21831g = C1947R.drawable.icn_vip;
                this.f21845u = getResources().getDimensionPixelSize(C1947R.dimen.margin_extra_tiny);
                break;
            case 5:
                i2 = C1947R.dimen.profile_extra_large;
                i3 = C1947R.dimen.profile_extra_large_vip;
                i4 = C1947R.dimen.profile_extra_large_vip_margin;
                i5 = C1947R.dimen.profile_extra_large_perf_count_font;
                this.f21830f = C1947R.drawable.icn_default_profile_large;
                this.f21831g = C1947R.drawable.icn_vip;
                this.f21845u = getResources().getDimensionPixelSize(C1947R.dimen.margin_extra_tiny);
                break;
            case 6:
                i2 = C1947R.dimen.profile_middle_new_message;
                i3 = C1947R.dimen.profile_middle_vip;
                i4 = C1947R.dimen.profile_middle_new_message_vip_margin;
                i5 = C1947R.dimen.profile_middle_perf_count_font;
                this.f21830f = C1947R.drawable.icn_default_profile_medium;
                this.f21831g = C1947R.drawable.icn_vip_sm;
                this.f21845u = getResources().getDimensionPixelSize(C1947R.dimen.margin_damn_tiny);
                break;
            default:
                i2 = C1947R.dimen.profile_middle;
                i3 = C1947R.dimen.profile_middle_vip;
                i4 = C1947R.dimen.profile_middle_vip_margin;
                i5 = C1947R.dimen.profile_middle_perf_count_font;
                this.f21830f = C1947R.drawable.icn_default_profile_medium;
                this.f21831g = C1947R.drawable.icn_vip_sm;
                this.f21845u = getResources().getDimensionPixelSize(C1947R.dimen.margin_damn_tiny);
                break;
        }
        this.f21833i = getResources().getDimension(i2);
        float dimension = getResources().getDimension(i3);
        float dimension2 = getResources().getDimension(i4);
        this.f21832h = (int) getResources().getDimension(i5);
        float max = Math.max((dimension2 + dimension) - this.f21833i, 0.0f);
        this.f21835k = (int) max;
        this.f21834j = (int) (this.f21833i + max);
        this.f21839o = new LayoutParams((int) this.f21833i, (int) this.f21833i);
        this.f21839o.addRule(13);
        this.f21840p = new LayoutParams((int) this.f21833i, (int) this.f21833i);
        this.f21840p.addRule(13);
        mo6795a(max, dimension2, dimension);
    }

    protected void mo6795a(float f, float f2, float f3) {
        this.f21841q = new LayoutParams((int) f3, (int) f3);
        this.f21841q.setMargins((int) f2, (int) f2, 0, 0);
    }

    public void setProfilePicUrl(String str) {
        if (this.f21838n == null || !this.f21838n.equals(str)) {
            this.f21825a.setVisibility(0);
            this.f21827c.setVisibility(8);
            this.f21825a.setLayoutParams(this.f21839o);
            this.f21828d.m18977a(str, this.f21825a, this.f21830f, true, this.f21845u);
            this.f21838n = str;
        }
    }

    public void setImageDrawable(int i) {
        this.f21825a.setImageDrawable(null);
        this.f21825a.setBackgroundResource(i);
        this.f21825a.setVisibility(0);
        this.f21827c.setVisibility(8);
    }

    public void setVIP(boolean z) {
        int i = 0;
        if (this.f21829e == 1 || this.f21829e == 2) {
            z = false;
        }
        this.f21826b.setVisibility(z ? 0 : 8);
        SNPImageView sNPImageView = this.f21825a;
        if (!z) {
            i = this.f21825a.getVisibility();
        }
        sNPImageView.setVisibility(i);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f21842r == null) {
            this.f21842r = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f21842r;
    }

    public void setPerformanceCount(int i) {
        m23389a(i, this.f21832h);
    }

    public void m23389a(int i, int i2) {
        if (i >= 0) {
            this.f21827c.setTextSize(0, (float) i2);
            setPerformanceText(getLocalizedFormatter().m18999a((long) i, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
            return;
        }
        this.f21827c.setVisibility(8);
    }

    public void setPerformanceText(String str) {
        if (str != null) {
            this.f21827c.setVisibility(0);
            this.f21827c.setText(str);
            return;
        }
        this.f21827c.setVisibility(8);
    }

    public void m23386a() {
        this.f21827c.setVisibility(8);
    }

    public void setTextStyle(int i) {
        this.f21827c.setTextAppearance(getContext(), i);
        this.f21827c.setTextColor(-1);
    }

    public int getExtraSpace() {
        return this.f21835k;
    }

    public void setPerformanceCountColor(int i) {
        this.f21827c.setTextColor(getResources().getColor(i));
    }

    public void setAccount(AccountIcon accountIcon) {
        if (accountIcon != null) {
            setVIP(accountIcon.a());
            setProfilePicUrl(accountIcon.picUrl);
            return;
        }
        setProfilePicUrl(null);
    }

    public void m23398b() {
        setVIP(SubscriptionManager.a().b());
        setProfilePicUrl(UserManager.a().h());
    }

    public void m23397a(final BaseFragment baseFragment, final PerformanceV2 performanceV2) {
        m23394a(new OnClickListener(this) {
            final /* synthetic */ ProfileImageWithVIPBadge f21819c;

            public void onClick(View view) {
                baseFragment.mo6487a(JoinersListFragment.m23755a(performanceV2));
            }
        });
    }

    public void m23396a(final BaseFragment baseFragment, final AccountIcon accountIcon) {
        m23395a(accountIcon, new OnClickListener(this) {
            final /* synthetic */ ProfileImageWithVIPBadge f21822c;

            public void onClick(View view) {
                baseFragment.mo6487a(ProfileFragment.m21036a(accountIcon));
            }
        });
    }

    public void m23395a(AccountIcon accountIcon, OnClickListener onClickListener) {
        m23394a(onClickListener);
        setPerformanceCount(-1);
        setAccount(accountIcon);
    }

    public void m23394a(OnClickListener onClickListener) {
        this.f21825a.setOnClickListener(onClickListener);
    }

    public void setPlaysCount(int i) {
        m23391a(i, true);
    }

    public void m23391a(int i, boolean z) {
        mo6796a(i, z, null);
    }

    public void m23390a(int i, OnClickListener onClickListener) {
        mo6796a(i, true, onClickListener);
    }

    public void mo6796a(int i, boolean z, OnClickListener onClickListener) {
        m23393a(i, z, onClickListener, C1947R.drawable.solid_blue_circle, C1947R.drawable.solid_blue_circle_with_border);
    }

    private void m23385b(int i, boolean z, OnClickListener onClickListener, int i2, int i3) {
        this.f21828d.m18974a(this.f21825a);
        this.f21828d.m18973a();
        if (z) {
            i2 = i3;
        }
        setImageDrawable(i2);
        setVIP(false);
        if (i == 0) {
            this.f21827c.setClickable(false);
        } else {
            this.f21827c.setOnClickListener(onClickListener);
        }
    }

    public void m23393a(int i, boolean z, OnClickListener onClickListener, int i2, int i3) {
        m23385b(i, z, onClickListener, i2, i3);
        setPerformanceCount(i);
    }

    public void setBitmap(Bitmap bitmap) {
        ImageUtils.a(this.f21825a, bitmap, C1947R.color.white, false);
    }

    protected void onMeasure(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.f21834j, 1073741824);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            this.f21827c.setLayoutParams(this.f21840p);
            this.f21825a.setLayoutParams(this.f21839o);
            this.f21826b.setLayoutParams(this.f21841q);
            this.f21826b.setImageResource(this.f21831g);
            mo6798c();
        } catch (Throwable e) {
            MagicCrittercism.a(new ProfileImageException(this, e));
        }
    }

    protected void mo6798c() {
        if (this.f21836l && !this.f21837m && (getLayoutParams() instanceof MarginLayoutParams) && this.f21835k != 0) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin - this.f21835k, marginLayoutParams.bottomMargin - this.f21835k);
            this.f21837m = true;
            post(new C44233(this));
        }
    }
}
