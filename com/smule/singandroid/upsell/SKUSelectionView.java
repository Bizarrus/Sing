package com.smule.singandroid.upsell;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.purchases.V3BillingHelper$SkuDetailsErrorListener;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SKUSelectionView extends LinearLayout {
    private static final String f24568m = SKUSelectionView.class.getName();
    @ViewById
    protected ImageView f24569a;
    @ViewById
    protected View f24570b;
    @ViewById
    protected View f24571c;
    @ViewById
    protected TextView f24572d;
    @ViewById
    protected TextView f24573e;
    @ViewById
    protected TextView f24574f;
    @ViewById
    protected TextView f24575g;
    @ViewById
    protected PurchaseButtonV2 f24576h;
    @ViewById
    protected PurchaseButtonV2 f24577i;
    @ViewById
    protected PurchaseButtonV2 f24578j;
    @ViewById
    protected ProgressBar f24579k;
    @ViewById
    protected TextView f24580l;
    private UpsellType f24581n;
    private V3BillingHelper f24582o;
    private Context f24583p;

    class C49751 implements V3BillingHelper$SkuDetailsErrorListener {
        final /* synthetic */ SKUSelectionView f24561a;

        C49751(SKUSelectionView sKUSelectionView) {
            this.f24561a = sKUSelectionView;
        }

        public void mo6537a() {
            this.f24561a.f24580l.setVisibility(0);
        }
    }

    public enum Mode {
        SUBSCRIPTION_PURCHASE,
        SONG_PURCHASE,
        SONG_VIP,
        AUDIO_FX_PURCHASE
    }

    public SKUSelectionView(Context context) {
        super(context);
        this.f24582o = null;
        this.f24583p = context;
    }

    public SKUSelectionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SKUSelectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f24582o = null;
        this.f24583p = context;
    }

    private void setBillingHelper(V3BillingHelper v3BillingHelper) {
        this.f24582o = v3BillingHelper;
        this.f24582o.a(this.f24576h, this.f24577i, this.f24578j, this.f24579k, new C49751(this));
        this.f24582o.a(this.f24581n.m25792a());
    }

    public void m25751a(UpsellType upsellType, V3BillingHelper v3BillingHelper) {
        this.f24581n = upsellType;
        this.f24570b.setVisibility(0);
        switch (this.f24581n) {
            case BANNER:
            case CUSTOM_PROFILE:
            case NATIVE_ADS_OVERFLOW:
            case NATIVE_ADS_ABOUT:
            case VIP_SONG:
            case STORAGE:
                this.f24574f.setText(m25749a(this.f24581n.m25793b()));
                this.f24575g.setText(m25749a(this.f24581n.m25794c()));
                setIconImageView(this.f24581n.m25796e());
                break;
            case AUDIO_FX:
                this.f24570b.setVisibility(8);
                this.f24571c.setVisibility(0);
                this.f24572d.setText(m25749a(this.f24581n.m25793b()));
                this.f24573e.setText(m25749a(this.f24581n.m25794c()));
                this.f24576h.setHasSmallerTopMargin(true);
                break;
            case PRE_ROLL:
                this.f24574f.setText(C1947R.string.subscription_title_sing_vip);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f24574f.getLayoutParams();
                marginLayoutParams.rightMargin = 0;
                this.f24574f.setLayoutParams(marginLayoutParams);
                this.f24574f.setTextSize(0, (float) getResources().getDimensionPixelSize(C1947R.dimen.font_sub_head));
                this.f24575g.setTextSize(0, (float) getResources().getDimensionPixelSize(C1947R.dimen.font_sub_head_small));
                this.f24569a.setVisibility(8);
                marginLayoutParams = (MarginLayoutParams) this.f24570b.getLayoutParams();
                marginLayoutParams.bottomMargin = getResources().getDimensionPixelOffset(C1947R.dimen.subscription_buttons_top_margin_small);
                this.f24570b.setLayoutParams(marginLayoutParams);
                break;
        }
        setButtonTextColors(this.f24581n.m25795d());
        setBillingHelper(v3BillingHelper);
    }

    private void setButtonTextColors(int i) {
        int color = ContextCompat.getColor(this.f24583p, i);
        this.f24576h.setButtonTextColor(color);
        this.f24577i.setButtonTextColor(color);
        this.f24578j.setButtonTextColor(color);
    }

    private void setIconImageView(int i) {
        this.f24569a.setBackground(ContextCompat.getDrawable(this.f24583p, i));
    }

    public void m25750a(SongbookEntry songbookEntry, PaywallType paywallType) {
        SingAnalytics.m26130b(SongbookEntry.m18752b(songbookEntry), SongbookEntry.m18749a(songbookEntry), this.f24581n.m25792a(), paywallType);
    }

    private String m25749a(String str) {
        return LocalizationManager.a().a("upsell", str);
    }
}
