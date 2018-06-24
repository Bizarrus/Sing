package com.smule.singandroid.upsell;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.SubscriptionPurchaseView;
import com.smule.singandroid.customviews.SubscriptionPurchaseView.Mode;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.purchases.V3BillingHelper$V3BillingListener;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class SubscriptionPurchaseFragment extends BaseFragment {
    private static final String f24594n = SubscriptionPurchaseFragment.class.getName();
    @ViewById
    protected SubscriptionPurchaseView f24595e;
    @InstanceState
    protected String f24596f = null;
    @InstanceState
    protected boolean f24597g = false;
    @InstanceState
    protected SongbookEntry f24598h;
    @InstanceState
    protected SongV2 f24599i;
    @InstanceState
    protected PerformanceV2 f24600j;
    @InstanceState
    protected String f24601k;
    @InstanceState
    protected Long f24602l;
    @InstanceState
    protected String f24603m;
    private V3BillingHelper f24604o = null;
    private UpsellType f24605p;

    class C49791 implements V3BillingHelper$V3BillingListener {
        final /* synthetic */ SubscriptionPurchaseFragment f24592a;

        class C49781 implements Runnable {
            final /* synthetic */ C49791 f24591a;

            C49781(C49791 c49791) {
                this.f24591a = c49791;
            }

            public void run() {
                this.f24591a.f24592a.m19840a(SubscriptionPurchaseFragment.f24594n);
                if (this.f24591a.f24592a.f24600j != null) {
                    PreSingActivity.m24763a(this.f24591a.f24592a.getActivity()).m24796a(StartupMode.OPENCALL).m24794a(this.f24591a.f24592a.f24598h).m24792a(this.f24591a.f24592a.f24602l.longValue()).m24793a(this.f24591a.f24592a.f24600j).a();
                } else if (this.f24591a.f24592a.f24598h != null) {
                    PreSingActivity.m24763a(this.f24591a.f24592a.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(this.f24591a.f24592a.f24598h).m24797a(this.f24591a.f24592a.f24601k).m24792a(this.f24591a.f24592a.f24602l.longValue()).a();
                }
            }
        }

        C49791(SubscriptionPurchaseFragment subscriptionPurchaseFragment) {
            this.f24592a = subscriptionPurchaseFragment;
        }

        public void mo6535a() {
        }

        public void mo6536a(boolean z) {
            if (z) {
                this.f24592a.m19838a(new C49781(this));
            }
        }
    }

    public static SubscriptionPurchaseFragment m25758a(boolean z, SongbookEntry songbookEntry, String str, String str2, UpsellType upsellType) {
        SubscriptionPurchaseFragment subscriptionPurchaseFragment_ = new SubscriptionPurchaseFragment_();
        Bundle bundle = new Bundle();
        Parcelable parcelable = (songbookEntry == null || !songbookEntry.m18773s()) ? null : ((ListingEntry) songbookEntry).f17626a.song;
        bundle.putBoolean("INTENT_KEY_SOURCE_VIP_SONG", z);
        bundle.putParcelable("INTENT_KEY_ENTRY", songbookEntry);
        bundle.putParcelable("INTENT_KEY_SONG", parcelable);
        bundle.putString("INTENT_KEY_SOURCE_SECTION_ID", str);
        bundle.putString("INTENT_KEY_SUB_AUTO_CLICK_ID", str2);
        bundle.putSerializable("INTENT_KEY_UPSELL_TYPE", upsellType);
        subscriptionPurchaseFragment_.setArguments(bundle);
        return subscriptionPurchaseFragment_;
    }

    public static SubscriptionPurchaseFragment m25757a(boolean z, SongbookEntry songbookEntry, PerformanceV2 performanceV2, Long l, UpsellType upsellType) {
        SubscriptionPurchaseFragment subscriptionPurchaseFragment_ = new SubscriptionPurchaseFragment_();
        Bundle bundle = new Bundle();
        bundle.putBoolean("INTENT_KEY_SOURCE_VIP_SONG", z);
        bundle.putParcelable("INTENT_KEY_ENTRY", songbookEntry);
        bundle.putParcelable("INTENT_KEY_PERF", performanceV2);
        bundle.putLong("INTENT_KEY_PROMO_ID", l.longValue());
        bundle.putSerializable("INTENT_KEY_UPSELL_TYPE", upsellType);
        subscriptionPurchaseFragment_.setArguments(bundle);
        return subscriptionPurchaseFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Bundle arguments = getArguments();
            this.f24597g = arguments.getBoolean("INTENT_KEY_SOURCE_VIP_SONG");
            this.f24598h = (SongbookEntry) arguments.getParcelable("INTENT_KEY_ENTRY");
            this.f24599i = (SongV2) arguments.getParcelable("INTENT_KEY_SONG");
            this.f24600j = (PerformanceV2) arguments.getParcelable("INTENT_KEY_PERF");
            this.f24601k = arguments.getString("INTENT_KEY_SOURCE_SECTION_ID");
            this.f24602l = Long.valueOf(arguments.getLong("INTENT_KEY_PROMO_ID", -1));
            this.f24603m = arguments.getString("INTENT_KEY_SUB_AUTO_CLICK_ID");
            this.f24605p = (UpsellType) arguments.getSerializable("INTENT_KEY_UPSELL_TYPE");
            if (this.f24598h != null && !this.f24598h.mo6293g() && this.f24605p == UpsellType.VIP_SONG) {
                this.f24605p = UpsellType.PRE_ROLL;
            }
        }
    }

    public void onPause() {
        super.onPause();
        this.f24604o.b();
    }

    public void onResume() {
        super.onResume();
        this.f24604o.a();
        mo6933p();
    }

    public void onDestroy() {
        super.onDestroy();
        m19866q();
    }

    public void onStart() {
        Mode mode;
        super.onStart();
        this.f24604o = m19854e();
        if (!this.f24597g) {
            switch (this.f24605p) {
                case NATIVE_ADS_ABOUT:
                    mode = Mode.NATIVE_ABOUT;
                    break;
                case NATIVE_ADS_OVERFLOW:
                    mode = Mode.NATIVE_OVERFLOW;
                    break;
                case STORAGE:
                    mode = Mode.PROFILE_STORAGE;
                    break;
                default:
                    mode = Mode.SUBSCRIPTION_PURCHASE;
                    break;
            }
        }
        mode = Mode.SONG_VIP;
        this.f24595e.setMode(mode);
        this.f24595e.setSubsBuyContext(this.f24605p.m25792a());
        this.f24595e.setBillingHelper(this.f24604o);
        this.f24595e.setVisibility(0);
        this.f24604o.a(getActivity(), "vipsong", this.f24603m, new C49791(this));
    }

    public void onStop() {
        super.onStop();
        m19855f();
    }

    protected void mo6420v() {
        this.f24595e.m23522a(this.f24598h, PaywallType.HARD);
    }

    public String mo6383s() {
        return f24594n;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = false;
        if (this.f24604o != null) {
            z = this.f24604o.a(i, i2, intent);
        }
        if (!z) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public boolean mo6445g() {
        return false;
    }

    @Click
    protected void m25762a() {
        getActivity().onBackPressed();
    }
}
