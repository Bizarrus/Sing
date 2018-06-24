/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.LinearLayout
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.managers.UserManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SubscriptionPurchaseView
extends LinearLayout {
    @ViewById
    View a;
    @ViewById
    View b;
    @ViewById
    ProfileImageWithVIPBadge c;
    @ViewById
    TextView d;
    @ViewById
    PurchaseButton e;
    @ViewById
    PurchaseButton f;
    @ViewById
    PurchaseButton g;
    @ViewById
    ProgressBar h;
    @ViewById
    TextView i;
    Mode j;
    Mode k;
    Context l;
    private com.smule.singandroid.purchases.V3BillingHelper m = null;
    private String n;

    public SubscriptionPurchaseView(Context context) {
        super(context);
        this.l = context;
        this.k = Mode.a;
    }

    public SubscriptionPurchaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a(context, attributeSet, 0);
    }

    public SubscriptionPurchaseView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.a(context, attributeSet, n);
    }

    @AfterViews
    protected void a() {
        this.c.setVIP(true);
        if (this.isInEditMode()) {
            this.setMode(this.k);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Context context, AttributeSet attributeSet, int n) {
        this.l = context;
        context = context.obtainStyledAttributes(attributeSet, R.SubscriptionPurchaseView_, n, 0);
        switch (context.getInteger(0, 1)) {
            default: {
                this.k = Mode.a;
                break;
            }
            case 2: {
                this.k = Mode.b;
                break;
            }
            case 3: {
                this.k = Mode.c;
                break;
            }
            case 4: {
                this.k = Mode.d;
            }
        }
        context.recycle();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(SongbookEntry var1_1, Analytics var2_2) {
        block9 : {
            var3_3 = null;
            switch (.a[this.j.ordinal()]) {
                case 4: {
                    var3_3 = "vip_banner";
                    ** break;
                }
                case 5: {
                    var3_3 = (ViewGroup.MarginLayoutParams)this.a.getLayoutParams();
                    var3_3.bottomMargin = this.getResources().getDimensionPixelOffset(2131427926);
                    this.a.setLayoutParams((ViewGroup.LayoutParams)var3_3);
                    var3_3 = "song";
                    ** break;
                }
                case 6: {
                    var3_3 = "vip_song";
                    ** break;
                }
                case 7: {
                    var3_3 = "vip_fx";
                    ** break;
                }
                case 2: {
                    var3_3 = "native_overflow";
                    ** break;
                }
                case 1: {
                    var3_3 = "native_about";
                }
lbl23: // 7 sources:
                default: {
                    break block9;
                }
                case 3: 
            }
            var3_3 = "profile_storage";
        }
        SingAnalytics.b((String)SongbookEntry.b(var1_1), (String)SongbookEntry.a(var1_1), (String)var3_3, var2_2);
    }

    public void setBillingHelper(com.smule.singandroid.purchases.V3BillingHelper v3BillingHelper) {
        this.m = v3BillingHelper;
        this.m.a((View)this.e, (View)this.f, (View)this.g, (View)this.h, new V3BillingHelper(){

            @Override
            public void a() {
                SubscriptionPurchaseView.this.i.setVisibility(0);
            }
        });
        this.m.a(this.n);
    }

    public void setEditModeViewMode(Mode mode) {
        this.k = mode;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void setMode(Mode var1_1) {
        block6 : {
            this.j = var1_1;
            this.a.setVisibility(0);
            switch (.a[this.j.ordinal()]) {
                case 1: 
                case 2: 
                case 3: 
                case 4: {
                    this.d.setText(2131297553);
                    ** break;
                }
                case 5: {
                    this.d.setText(2131297552);
                    var1_1 = (ViewGroup.MarginLayoutParams)this.d.getLayoutParams();
                    var1_1.rightMargin = 0;
                    this.d.setLayoutParams((ViewGroup.LayoutParams)var1_1);
                    this.c.setVisibility(8);
                    ** break;
                }
                case 6: {
                    this.d.setText(2131297551);
                }
lbl16: // 4 sources:
                default: {
                    break block6;
                }
                case 7: 
            }
            this.a.setVisibility(8);
            this.b.setVisibility(0);
        }
        if (this.j == Mode.d) return;
        if (this.isInEditMode() != false) return;
        this.c.setProfilePicUrl(UserManager.a().h());
    }

    public void setSubsBuyContext(String string2) {
        this.n = string2;
    }

    public static enum Mode {
        a,
        b,
        c,
        d,
        e,
        f,
        g;
        

        private Mode() {
        }
    }

}

