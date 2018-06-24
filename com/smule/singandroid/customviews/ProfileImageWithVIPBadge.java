/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.Bitmap
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.ui.SNPImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileImageWithVIPBadge
extends RelativeLayout {
    @ViewById
    protected SNPImageView a;
    @ViewById
    protected ImageView b;
    @ViewById
    protected TextView c;
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
    protected int e;
    protected int f;
    protected int g = 0;
    protected int h;
    protected float i;
    protected int j = 0;
    protected int k;
    protected boolean l = true;
    protected boolean m = false;
    protected String n;
    protected RelativeLayout.LayoutParams o;
    protected RelativeLayout.LayoutParams p;
    protected RelativeLayout.LayoutParams q;
    protected LocalizedShortNumberFormatter r;
    private final String s = ProfileImageWithVIPBadge.class.getSimpleName();
    private int t;
    private boolean u = false;
    private boolean v = false;

    public ProfileImageWithVIPBadge(Context context) {
        super(context);
        this.a(context, null, 0);
    }

    public ProfileImageWithVIPBadge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet, 0);
    }

    public ProfileImageWithVIPBadge(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(context, attributeSet, n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Context context, AttributeSet attributeSet, int n) {
        context = context.obtainStyledAttributes(attributeSet, R.ProfileImageWithVIPBadge_, n, 0);
        int n2 = context.getIndexCount();
        n = 0;
        do {
            if (n >= n2) {
                context.recycle();
                this.a(this.e);
                return;
            }
            switch (context.getIndex(n)) {
                case 0: {
                    try {
                        this.e = context.getInt(n, -1);
                    }
                    catch (UnsupportedOperationException unsupportedOperationException) {
                        Log.e(this.s, unsupportedOperationException.getMessage());
                    }
                }
                default: {
                    break;
                }
                case 1: {
                    this.l = context.getBoolean(n, true);
                }
            }
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(int n, boolean bl, View.OnClickListener onClickListener, int n2, int n3) {
        this.d.a((ImageView)this.a);
        this.d.a();
        if (bl) {
            n2 = n3;
        }
        this.setImageDrawable(n2);
        this.setVIP(false);
        if (n == 0) {
            this.c.setClickable(false);
            return;
        }
        this.c.setOnClickListener(onClickListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d() {
        ImageView imageView = this.b;
        int n = this.u && !this.v ? 0 : 8;
        imageView.setVisibility(n);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.r == null) {
            this.r = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.r;
    }

    public void a() {
        this.c.setVisibility(8);
    }

    protected void a(float f, float f2, float f3) {
        this.q = new RelativeLayout.LayoutParams((int)f3, (int)f3);
        this.q.setMargins((int)f2, (int)f2, 0, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n) {
        int n2;
        int n3;
        int n4;
        this.e = n;
        switch (this.e) {
            default: {
                n3 = 2131427735;
                n4 = 2131427739;
                n2 = 2131427740;
                n = 2131427738;
                this.f = 2130837900;
                this.g = 2130838061;
                this.t = this.getResources().getDimensionPixelSize(2131428164);
                break;
            }
            case 1: {
                n3 = 2131427750;
                n4 = 2131427752;
                n2 = 2131427753;
                n = 2131427751;
                this.f = 2130837901;
                this.t = 0;
                break;
            }
            case 2: {
                n3 = 2131427746;
                n4 = 2131427748;
                n2 = 2131427749;
                n = 2131427747;
                this.f = 2130837901;
                this.t = 0;
                break;
            }
            case 6: {
                n3 = 2131427736;
                n4 = 2131427739;
                n2 = 2131427737;
                n = 2131427738;
                this.f = 2130837900;
                this.g = 2130838061;
                this.t = this.getResources().getDimensionPixelSize(2131428164);
                break;
            }
            case 4: 
            case 7: {
                if (this.e == 4) {
                    n2 = 2131427728;
                    n = 2131427731;
                } else {
                    n2 = 2131427755;
                    n = 2131427756;
                }
                this.f = 2130837899;
                this.g = 2130838060;
                this.t = this.getResources().getDimensionPixelSize(2131428171);
                n4 = 2131427730;
                n3 = n2;
                n2 = n;
                n = 2131427729;
                break;
            }
            case 5: {
                n3 = 2131427716;
                n4 = 2131427718;
                n2 = 2131427719;
                n = 2131427717;
                this.f = 2130837899;
                this.g = 2130838060;
                this.t = this.getResources().getDimensionPixelSize(2131428171);
                break;
            }
            case 8: {
                n2 = 2131427715;
                n = 2131427729;
                this.f = 2130837899;
                this.g = 2130838060;
                this.t = this.getResources().getDimensionPixelSize(2131428171);
                n3 = 2131427714;
                n4 = 2131427730;
                break;
            }
            case 9: {
                n2 = 2131427721;
                n = 2131427734;
                this.f = 2130837899;
                this.g = 2130838060;
                this.t = this.getResources().getDimensionPixelSize(2131428171);
                n3 = 2131427720;
                n4 = 2131427730;
            }
        }
        this.i = this.getResources().getDimension(n3);
        float f = this.getResources().getDimension(n4);
        float f2 = this.getResources().getDimension(n2);
        this.h = (int)this.getResources().getDimension(n);
        float f3 = Math.max(f2 + f - this.i, 0.0f);
        this.k = (int)f3;
        this.j = (int)(this.i + f3);
        this.o = new RelativeLayout.LayoutParams((int)this.i, (int)this.i);
        this.o.addRule(13);
        this.p = new RelativeLayout.LayoutParams((int)this.i, (int)this.i);
        this.p.addRule(13);
        this.a(f3, f2, f);
    }

    public void a(int n, int n2) {
        if (n >= 0) {
            this.c.setTextSize(0, (float)n2);
            this.setPerformanceText(this.getLocalizedFormatter().a(n, this.getResources().getInteger(2131623962)));
            return;
        }
        this.c.setVisibility(8);
    }

    public void a(int n, View.OnClickListener onClickListener) {
        this.a(n, true, onClickListener);
    }

    public void a(int n, boolean bl, View.OnClickListener onClickListener) {
        this.a(n, bl, onClickListener, 2130838205, 2130838206);
    }

    public void a(int n, boolean bl, View.OnClickListener onClickListener, int n2, int n3) {
        this.b(n, bl, onClickListener, n2, n3);
        this.setPerformanceCount(n);
    }

    public void a(View.OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void a(AccountIcon accountIcon, View.OnClickListener onClickListener) {
        this.a(onClickListener);
        this.setPerformanceCount(-1);
        this.setAccount(accountIcon);
    }

    public void a(BaseFragment baseFragment, AccountIcon accountIcon) {
        this.a(accountIcon, new View.OnClickListener(this, accountIcon, baseFragment){
            final /* synthetic */ AccountIcon a;
            final /* synthetic */ BaseFragment b;
            final /* synthetic */ ProfileImageWithVIPBadge c;
            {
                this.c = profileImageWithVIPBadge;
                this.a = accountIcon;
                this.b = baseFragment;
            }

            public void onClick(View object) {
                object = com.smule.singandroid.profile.ProfileFragment.a(this.a);
                this.b.a((BaseFragment)((Object)object));
            }
        });
    }

    public void a(BaseFragment baseFragment, PerformanceV2 performanceV2) {
        this.a(new View.OnClickListener(this, performanceV2, baseFragment){
            final /* synthetic */ PerformanceV2 a;
            final /* synthetic */ BaseFragment b;
            final /* synthetic */ ProfileImageWithVIPBadge c;
            {
                this.c = profileImageWithVIPBadge;
                this.a = performanceV2;
                this.b = baseFragment;
            }

            public void onClick(View object) {
                object = com.smule.singandroid.fragments.JoinersListFragment.a(this.a);
                this.b.a((BaseFragment)((Object)object));
            }
        });
    }

    public void a(boolean bl) {
        this.v = bl;
        this.d();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b() {
        if (!this.l || this.m || !(this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) || this.k == 0) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin - this.k, marginLayoutParams.bottomMargin - this.k);
        this.m = true;
        this.post(new Runnable(this){
            final /* synthetic */ ProfileImageWithVIPBadge a;
            {
                this.a = profileImageWithVIPBadge;
            }

            public void run() {
                this.a.requestLayout();
            }
        });
    }

    public void c() {
        this.n = "";
        this.d.a();
    }

    public int getExtraSpace() {
        return this.k;
    }

    public ImageView getImageView() {
        return this.a;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            this.c.setLayoutParams((ViewGroup.LayoutParams)this.p);
            this.a.setLayoutParams((ViewGroup.LayoutParams)this.o);
            this.b.setLayoutParams((ViewGroup.LayoutParams)this.q);
            this.b.setImageResource(this.g);
            this.b();
            return;
        }
        catch (Exception exception) {
            MagicCrittercism.a(new Exception(exception){});
            return;
        }
    }

    protected void onMeasure(int n, int n2) {
        n = View.MeasureSpec.makeMeasureSpec((int)this.j, (int)1073741824);
        super.onMeasure(n, n);
    }

    public void setAccount(AccountIcon accountIcon) {
        if (accountIcon != null) {
            this.setVIP(accountIcon.a());
            this.setProfilePicUrl(accountIcon.picUrl);
            return;
        }
        this.setProfilePicUrl(null);
    }

    public void setBitmap(Bitmap bitmap) {
        com.smule.android.utils.ImageUtils.a((ImageView)this.a, bitmap, 2131689991, false);
    }

    public void setImageDrawable(int n) {
        this.a.setImageDrawable(null);
        this.a.setBackgroundResource(n);
        this.a.setVisibility(0);
        this.c.setVisibility(8);
    }

    @Deprecated
    public void setLoadImageWithBorder(boolean bl) {
    }

    public void setPerformanceCount(int n) {
        this.a(n, this.h);
    }

    public void setPerformanceText(String string2) {
        if (string2 != null) {
            this.c.setVisibility(0);
            this.c.setText((CharSequence)string2);
            return;
        }
        this.c.setVisibility(8);
    }

    public void setProfilePicUrl(String string2) {
        if (this.n == null || !this.n.equals(string2)) {
            this.a.setVisibility(0);
            this.c.setVisibility(8);
            this.a.setLayoutParams((ViewGroup.LayoutParams)this.o);
            this.d.a(string2, (ImageView)this.a, this.f, true, this.t);
            this.n = string2;
        }
    }

    public void setTextStyle(int n) {
        this.c.setTextAppearance(this.getContext(), n);
        this.c.setTextColor(-1);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setVIP(boolean bl) {
        int n = 0;
        if (this.e == 1 || this.e == 2) {
            bl = false;
        }
        this.u = bl;
        SNPImageView sNPImageView = this.a;
        if (!this.u) {
            n = this.a.getVisibility();
        }
        sNPImageView.setVisibility(n);
        this.d();
    }

}

