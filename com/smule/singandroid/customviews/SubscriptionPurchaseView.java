package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.network.managers.UserManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.R$styleable;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.purchases.V3BillingHelper$SkuDetailsErrorListener;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SubscriptionPurchaseView extends LinearLayout {
    @ViewById
    View f22025a;
    @ViewById
    View f22026b;
    @ViewById
    ProfileImageWithVIPBadge f22027c;
    @ViewById
    TextView f22028d;
    @ViewById
    PurchaseButton f22029e;
    @ViewById
    PurchaseButton f22030f;
    @ViewById
    PurchaseButton f22031g;
    @ViewById
    ProgressBar f22032h;
    @ViewById
    TextView f22033i;
    Mode f22034j;
    Mode f22035k;
    Context f22036l;
    private V3BillingHelper f22037m = null;
    private String f22038n;

    class C44471 implements V3BillingHelper$SkuDetailsErrorListener {
        final /* synthetic */ SubscriptionPurchaseView f22015a;

        C44471(SubscriptionPurchaseView subscriptionPurchaseView) {
            this.f22015a = subscriptionPurchaseView;
        }

        public void mo6537a() {
            this.f22015a.f22033i.setVisibility(0);
        }
    }

    public enum Mode {
        SUBSCRIPTION_PURCHASE,
        SONG_PURCHASE,
        SONG_VIP,
        AUDIO_FX_PURCHASE,
        NATIVE_ABOUT,
        NATIVE_OVERFLOW,
        PROFILE_STORAGE
    }

    public SubscriptionPurchaseView(Context context) {
        super(context);
        this.f22036l = context;
        this.f22035k = Mode.SUBSCRIPTION_PURCHASE;
    }

    public SubscriptionPurchaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23521a(context, attributeSet, 0);
    }

    public SubscriptionPurchaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23521a(context, attributeSet, i);
    }

    public void m23521a(Context context, AttributeSet attributeSet, int i) {
        this.f22036l = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SubscriptionPurchaseView_, i, 0);
        switch (obtainStyledAttributes.getInteger(0, 1)) {
            case 2:
                this.f22035k = Mode.SONG_PURCHASE;
                break;
            case 3:
                this.f22035k = Mode.SONG_VIP;
                break;
            case 4:
                this.f22035k = Mode.AUDIO_FX_PURCHASE;
                break;
            default:
                this.f22035k = Mode.SUBSCRIPTION_PURCHASE;
                break;
        }
        obtainStyledAttributes.recycle();
    }

    public void setSubsBuyContext(String str) {
        this.f22038n = str;
    }

    public void setEditModeViewMode(Mode mode) {
        this.f22035k = mode;
    }

    public void setBillingHelper(V3BillingHelper v3BillingHelper) {
        this.f22037m = v3BillingHelper;
        this.f22037m.a(this.f22029e, this.f22030f, this.f22031g, this.f22032h, new C44471(this));
        this.f22037m.a(this.f22038n);
    }

    @AfterViews
    protected void m23520a() {
        this.f22027c.setVIP(true);
        if (isInEditMode()) {
            setMode(this.f22035k);
        }
    }

    public void setMode(Mode mode) {
        this.f22034j = mode;
        this.f22025a.setVisibility(0);
        switch (this.f22034j) {
            case NATIVE_ABOUT:
            case NATIVE_OVERFLOW:
            case PROFILE_STORAGE:
            case SUBSCRIPTION_PURCHASE:
                this.f22028d.setText(C1947R.string.subscription_title_unlock_all);
                break;
            case SONG_PURCHASE:
                this.f22028d.setText(C1947R.string.subscription_title_sing_vip);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f22028d.getLayoutParams();
                marginLayoutParams.rightMargin = 0;
                this.f22028d.setLayoutParams(marginLayoutParams);
                this.f22027c.setVisibility(8);
                break;
            case SONG_VIP:
                this.f22028d.setText(C1947R.string.subscription_title_need_vip);
                break;
            case AUDIO_FX_PURCHASE:
                this.f22025a.setVisibility(8);
                this.f22026b.setVisibility(0);
                break;
        }
        if (this.f22034j != Mode.AUDIO_FX_PURCHASE && !isInEditMode()) {
            this.f22027c.setProfilePicUrl(UserManager.a().h());
        }
    }

    public void m23522a(SongbookEntry songbookEntry, PaywallType paywallType) {
        String str = null;
        switch (this.f22034j) {
            case NATIVE_ABOUT:
                str = "native_about";
                break;
            case NATIVE_OVERFLOW:
                str = "native_overflow";
                break;
            case PROFILE_STORAGE:
                str = "profile_storage";
                break;
            case SUBSCRIPTION_PURCHASE:
                str = "vip_banner";
                break;
            case SONG_PURCHASE:
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f22025a.getLayoutParams();
                marginLayoutParams.bottomMargin = getResources().getDimensionPixelOffset(C1947R.dimen.subscription_buttons_top_margin_small);
                this.f22025a.setLayoutParams(marginLayoutParams);
                str = "song";
                break;
            case SONG_VIP:
                str = "vip_song";
                break;
            case AUDIO_FX_PURCHASE:
                str = "vip_fx";
                break;
        }
        SingAnalytics.m26130b(SongbookEntry.m18752b(songbookEntry), SongbookEntry.m18749a(songbookEntry), str, paywallType);
    }
}
