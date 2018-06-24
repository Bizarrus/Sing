package com.smule.singandroid.pre_sing;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mopub.mobileads.MoPubErrorCode;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter$AdImpressionResult;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter$EventListener;
import com.smule.android.ads.fullScreenAds.MagicFullScreenAdMediatorAdapter$RewardResult;
import com.smule.android.ads.vendors.mopub.MagicMoPubFullScreenAdMediatorAdapter;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingBundle.Builder;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.SingServerValues.PaywallAdButtonPosition;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.ads.AdVendorManagerConfig;
import com.smule.singandroid.customviews.OrDivider;
import com.smule.singandroid.customviews.SubscriptionPurchaseView;
import com.smule.singandroid.customviews.SubscriptionPurchaseView.Mode;
import com.smule.singandroid.customviews.SubscriptionPurchaseView_;
import com.smule.singandroid.customviews.WatchAdView;
import com.smule.singandroid.customviews.WatchAdView_;
import com.smule.singandroid.dialogs.AdLoadingDialog;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.purchases.V3BillingHelper$V3BillingListener;
import com.smule.singandroid.upsell.SKUSelectionView;
import com.smule.singandroid.upsell.SKUSelectionView_;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PreSingPurchaseFragment extends PreSingBaseFragment implements MagicFullScreenAdMediatorAdapter$EventListener {
    private static final String f23732A = PreSingPurchaseFragment.class.getName();
    private View f23733B;
    private AdLoadingDialog f23734C;
    private BusyDialog f23735D;
    private V3BillingHelper f23736E;
    private WatchAdView f23737F;
    private boolean f23738G;
    private boolean f23739H;
    private OnClickListener f23740I = new C47904(this);
    @ViewById
    protected ImageView f23741t;
    @ViewById
    protected LinearLayout f23742u;
    @ViewById
    protected FrameLayout f23743v;
    @ViewById
    protected FrameLayout f23744w;
    @ViewById
    protected FrameLayout f23745x;
    @ViewById
    protected OrDivider f23746y;
    @ViewById
    protected OrDivider f23747z;

    class C47871 implements V3BillingHelper$V3BillingListener {
        final /* synthetic */ PreSingPurchaseFragment f23728a;

        C47871(PreSingPurchaseFragment preSingPurchaseFragment) {
            this.f23728a = preSingPurchaseFragment;
        }

        public void mo6535a() {
            Log.b(PreSingPurchaseFragment.f23732A, "onReportStart called");
        }

        public void mo6536a(boolean z) {
            Log.b(PreSingPurchaseFragment.f23732A, "onReportEnd called; success: " + z);
            if (z && SubscriptionManager.a().b()) {
                this.f23728a.m25006R();
            }
        }
    }

    class C47882 implements OnCancelListener {
        final /* synthetic */ PreSingPurchaseFragment f23729a;

        C47882(PreSingPurchaseFragment preSingPurchaseFragment) {
            this.f23729a = preSingPurchaseFragment;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.f23729a.m25000X();
        }
    }

    class C47893 implements OnClickListener {
        final /* synthetic */ PreSingPurchaseFragment f23730a;

        C47893(PreSingPurchaseFragment preSingPurchaseFragment) {
            this.f23730a = preSingPurchaseFragment;
        }

        public void onClick(View view) {
            Log.b(PreSingPurchaseFragment.f23732A, "pre-sing 'watch ad' button clicked");
            MagicFullScreenAdMediatorAdapter b = this.f23730a.m25001Y();
            if (b != null) {
                this.f23730a.f23734C.show();
                b.logAdRewardClick();
                if (!b.showAd()) {
                    b.loadAndShowAd(AdUtils.m22225b(null));
                }
            }
        }
    }

    class C47904 implements OnClickListener {
        final /* synthetic */ PreSingPurchaseFragment f23731a;

        C47904(PreSingPurchaseFragment preSingPurchaseFragment) {
            this.f23731a = preSingPurchaseFragment;
        }

        public void onClick(View view) {
            this.f23731a.m25000X();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!(this.i.f20149n || this.i.f20147l)) {
            this.f23736E = m19854e();
            this.f23736E.a(getActivity(), "withcredits", null, new C47871(this));
        }
        if (SingServerValues.m21676N()) {
            this.f23733B = SKUSelectionView_.m25752a(getActivity());
            return;
        }
        this.f23733B = SubscriptionPurchaseView_.m23523a(getActivity());
        ((SubscriptionPurchaseView) this.f23733B).setEditModeViewMode(Mode.SONG_PURCHASE);
    }

    protected void mo6896E() {
        super.mo6896E();
        if (SingServerValues.m21676N()) {
            m24999W();
            ((SKUSelectionView) this.f23733B).m25751a(UpsellType.PRE_ROLL, this.f23736E);
        } else {
            ((SubscriptionPurchaseView) this.f23733B).setMode(Mode.SONG_PURCHASE);
            ((SubscriptionPurchaseView) this.f23733B).setSubsBuyContext(UpsellType.PRE_ROLL.m25792a());
            ((SubscriptionPurchaseView) this.f23733B).setBillingHelper(this.f23736E);
        }
        if (this.f23743v.getChildCount() == 0) {
            this.f23743v.addView(this.f23733B);
        }
        if (!this.f23738G) {
            this.f23737F = WatchAdView_.m23552a(getActivity());
            if (SingServerValues.m21702w() == PaywallAdButtonPosition.TOP) {
                if (this.f23744w.getChildCount() < 1) {
                    this.f23744w.addView(this.f23737F);
                }
                this.f23745x.setVisibility(8);
                this.f23747z.setVisibility(8);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f23733B.getLayoutParams();
                marginLayoutParams.topMargin = getResources().getDimensionPixelOffset(C1947R.dimen.ad_view_bottom_top_margin);
                this.f23733B.setLayoutParams(marginLayoutParams);
            } else {
                if (this.f23745x.getChildCount() < 1) {
                    this.f23745x.addView(this.f23737F);
                }
                this.f23744w.setVisibility(8);
                this.f23746y.setVisibility(8);
            }
            this.f23734C = new AdLoadingDialog(getActivity(), this.f23740I);
            this.f23734C.setOnCancelListener(new C47882(this));
            String str = "softPaywall";
            str = SingServerValues.m21674L();
            this.f23737F.setTitleText(LocalizationManager.a().a("softPaywall", "ad_title", str));
            this.f23737F.setSubtitleText(LocalizationManager.a().a("softPaywall", "ad_subtitle", str));
            this.f23737F.setButtonText(LocalizationManager.a().a("softPaywall", "ad_button", str));
            this.f23737F.m23551a(new C47893(this));
            this.f23738G = true;
        }
    }

    private void m24999W() {
        this.f23741t.setImageDrawable(ContextCompat.getDrawable(getActivity(), C1947R.drawable.btn_back_arrow_whitegray));
        this.f23742u.setBackground(ContextCompat.getDrawable(getActivity(), C1947R.drawable.upsell_bg_sky_indigo));
        this.f23746y.m23308a();
        this.f23746y.setAlpha(0.6f);
        this.f23747z.m23308a();
        this.f23747z.setAlpha(0.6f);
        this.f23743v.setPadding(getResources().getDimensionPixelOffset(C1947R.dimen.margin_larger), getResources().getDimensionPixelOffset(C1947R.dimen.ad_view_top_top_margin), getResources().getDimensionPixelOffset(C1947R.dimen.margin_larger), getResources().getDimensionPixelOffset(C1947R.dimen.ad_view_top_top_margin));
    }

    private void m25000X() {
        mo6909T();
        MagicFullScreenAdMediatorAdapter Y = m25001Y();
        if (Y == null) {
            Log.e(f23732A, "user clicked cancel on fullscreen ad UX timeout, but ad mediator adapter is null");
        } else {
            Y.cancelAd();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        MagicFullScreenAdMediatorAdapter Y = m25001Y();
        if (Y != null) {
            Y.registerListener(this);
        }
    }

    public void onStart() {
        super.onStart();
        AdVendorManagerConfig.m22237b(getActivity());
    }

    public void onResume() {
        super.onResume();
        boolean z = getActivity().getSharedPreferences(MagicMoPubFullScreenAdMediatorAdapter.FILE_NAME, 0).getBoolean(MagicMoPubFullScreenAdMediatorAdapter.class.getSimpleName(), false);
        if (this.f23739H || z) {
            m25006R();
            this.f23739H = false;
            return;
        }
        if (this.f23736E != null) {
            this.f23736E.a();
        }
        mo6933p();
    }

    public void onPause() {
        super.onPause();
        if (this.f23736E != null) {
            this.f23736E.b();
        }
    }

    public void onStop() {
        super.onStop();
        mo6908S();
    }

    public void onDestroyView() {
        super.onDestroyView();
        m19855f();
    }

    public void onDestroy() {
        super.onDestroy();
        MagicFullScreenAdMediatorAdapter Y = m25001Y();
        if (Y != null) {
            Y.unregisterListener(this);
        }
        m19866q();
    }

    public String mo6383s() {
        return f23732A;
    }

    protected void m25006R() {
        if (isAdded()) {
            getActivity().getSharedPreferences(MagicMoPubFullScreenAdMediatorAdapter.FILE_NAME, 0).edit().clear().apply();
            PreSingBaseFragment.m24812a(getActivity());
            Builder builder = new Builder(this.i);
            builder.m21625d(true);
            this.i = builder.m21621a();
            if (this.i.f20152q) {
                Log.b(f23732A, "purchaseSuccessfulResetPreSing - part state and purchase state completed; playing product");
                mo6900K();
                return;
            }
            m24845a(ViewPhase.DUET_PART_SELECT);
        }
    }

    protected void mo6420v() {
        String str = "";
        if (this.k != null) {
            str = this.k.mo6289c();
        }
        SingAnalytics.m26148e(str);
        if (SingServerValues.m21676N()) {
            ((SKUSelectionView) this.f23733B).m25750a(this.k, PaywallType.SOFT);
        } else {
            ((SubscriptionPurchaseView) this.f23733B).m23522a(this.k, PaywallType.SOFT);
        }
    }

    @SupposeUiThread
    protected void mo6908S() {
        if (this.f23735D != null) {
            this.f23735D.dismiss();
            this.f23735D = null;
        }
    }

    @SupposeUiThread
    protected void mo6909T() {
        if (this.f23734C != null && this.f23734C.isShowing()) {
            this.f23734C.dismiss();
        }
    }

    public void mo6897a() {
    }

    public void mo6906a(MagicFullScreenAdMediatorAdapter$AdImpressionResult magicFullScreenAdMediatorAdapter$AdImpressionResult, MoPubErrorCode moPubErrorCode) {
        Log.b(f23732A, "onAdImpression: " + magicFullScreenAdMediatorAdapter$AdImpressionResult + ", MoPubErrorCode: " + (moPubErrorCode == null ? null : moPubErrorCode.toString()));
        mo6909T();
        if (magicFullScreenAdMediatorAdapter$AdImpressionResult == MagicFullScreenAdMediatorAdapter$AdImpressionResult.SUCCESS || !isAdded() || !isResumed()) {
            return;
        }
        if (moPubErrorCode == null || moPubErrorCode != MoPubErrorCode.NO_CONNECTION) {
            m25006R();
        } else {
            Toaster.a(getActivity(), C1947R.string.songbook_error_connecting_to_network);
        }
    }

    public void mo6907a(MagicFullScreenAdMediatorAdapter$RewardResult magicFullScreenAdMediatorAdapter$RewardResult) {
        Log.b(f23732A, "onRewardCompletion: " + magicFullScreenAdMediatorAdapter$RewardResult);
        this.f23739H = magicFullScreenAdMediatorAdapter$RewardResult == MagicFullScreenAdMediatorAdapter$RewardResult.SUCCESS;
        mo6909T();
        PreSingActivity preSingActivity = (PreSingActivity) getActivity();
        if (preSingActivity == null || !preSingActivity.f()) {
            Log.b(f23732A, "onRewardCompletion: defer moving to next phase");
        } else if (this.f23739H) {
            m25006R();
        }
    }

    private MagicFullScreenAdMediatorAdapter m25001Y() {
        PreSingActivity preSingActivity = (PreSingActivity) getActivity();
        if (preSingActivity == null) {
            Log.b(f23732A, "fullscreen ad mediator adapter can't be retrieved because activity is null");
            return null;
        }
        MagicFullScreenAdMediatorAdapter r = preSingActivity.m24787r();
        if (r != null) {
            return r;
        }
        Log.e(f23732A, "fullscreen ad mediator adapter from activity is NULL");
        return r;
    }

    @Click
    protected void m25009U() {
        getActivity().onBackPressed();
    }
}
