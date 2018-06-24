package com.smule.singandroid.upsell;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.purchases.V3BillingHelper$V3BillingListener;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class UpsellFragment extends BaseFragment {
    private static final String f24612q = UpsellFragment.class.getName();
    @ViewById
    protected LinearLayout f24613e;
    @ViewById
    protected SKUSelectionView f24614f;
    @ViewById
    protected TextView f24615g;
    @InstanceState
    protected String f24616h = null;
    @InstanceState
    protected boolean f24617i = false;
    @InstanceState
    protected SongbookEntry f24618j;
    @InstanceState
    protected SongV2 f24619k;
    @InstanceState
    protected PerformanceV2 f24620l;
    @InstanceState
    protected String f24621m;
    @InstanceState
    protected Long f24622n;
    @InstanceState
    protected String f24623o;
    @InstanceState
    protected UpsellType f24624p;
    private V3BillingHelper f24625r = null;

    class C49831 implements V3BillingHelper$V3BillingListener {
        final /* synthetic */ UpsellFragment f24610a;

        class C49821 implements Runnable {
            final /* synthetic */ C49831 f24609a;

            C49821(C49831 c49831) {
                this.f24609a = c49831;
            }

            public void run() {
                this.f24609a.f24610a.m19840a(UpsellFragment.f24612q);
                if (this.f24609a.f24610a.f24620l != null) {
                    PreSingActivity.m24763a(this.f24609a.f24610a.getActivity()).m24796a(StartupMode.OPENCALL).m24794a(this.f24609a.f24610a.f24618j).m24792a(this.f24609a.f24610a.f24622n.longValue()).m24793a(this.f24609a.f24610a.f24620l).a();
                } else if (this.f24609a.f24610a.f24618j != null) {
                    PreSingActivity.m24763a(this.f24609a.f24610a.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(this.f24609a.f24610a.f24618j).m24797a(this.f24609a.f24610a.f24621m).m24792a(this.f24609a.f24610a.f24622n.longValue()).a();
                }
            }
        }

        C49831(UpsellFragment upsellFragment) {
            this.f24610a = upsellFragment;
        }

        public void mo6535a() {
        }

        public void mo6536a(boolean z) {
            if (z) {
                this.f24610a.m19838a(new C49821(this));
            }
        }
    }

    public static UpsellFragment m25776a(boolean z, SongbookEntry songbookEntry, String str, String str2, UpsellType upsellType) {
        UpsellFragment upsellFragment_ = new UpsellFragment_();
        Parcelable parcelable = (songbookEntry == null || !songbookEntry.m18773s()) ? null : ((ListingEntry) songbookEntry).f17626a.song;
        Bundle bundle = new Bundle();
        bundle.putBoolean("INTENT_KEY_SOURCE_VIP_SONG", z);
        bundle.putParcelable("INTENT_KEY_ENTRY", songbookEntry);
        bundle.putParcelable("INTENT_KEY_SONG", parcelable);
        bundle.putString("INTENT_KEY_SOURCE_SECTION_ID", str);
        bundle.putString("INTENT_KEY_SUB_AUTO_CLICK_ID", str2);
        bundle.putSerializable("INTENT_KEY_UPSELL_TYPE", upsellType);
        upsellFragment_.setArguments(bundle);
        return upsellFragment_;
    }

    public static UpsellFragment m25775a(boolean z, SongbookEntry songbookEntry, PerformanceV2 performanceV2, Long l, UpsellType upsellType) {
        UpsellFragment upsellFragment_ = new UpsellFragment_();
        Bundle bundle = new Bundle();
        bundle.putBoolean("INTENT_KEY_SOURCE_VIP_SONG", z);
        bundle.putParcelable("INTENT_KEY_ENTRY", songbookEntry);
        bundle.putParcelable("INTENT_KEY_PERF", performanceV2);
        bundle.putLong("INTENT_KEY_PROMO_ID", l.longValue());
        bundle.putSerializable("INTENT_KEY_UPSELL_TYPE", upsellType);
        upsellFragment_.setArguments(bundle);
        return upsellFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Bundle arguments = getArguments();
            this.f24617i = arguments.getBoolean("INTENT_KEY_SOURCE_VIP_SONG");
            this.f24618j = (SongbookEntry) arguments.getParcelable("INTENT_KEY_ENTRY");
            this.f24619k = (SongV2) arguments.getParcelable("INTENT_KEY_SONG");
            this.f24620l = (PerformanceV2) arguments.getParcelable("INTENT_KEY_PERF");
            this.f24621m = arguments.getString("INTENT_KEY_SOURCE_SECTION_ID");
            this.f24622n = Long.valueOf(arguments.getLong("INTENT_KEY_PROMO_ID", -1));
            this.f24623o = arguments.getString("INTENT_KEY_SUB_AUTO_CLICK_ID");
            this.f24624p = (UpsellType) arguments.getSerializable("INTENT_KEY_UPSELL_TYPE");
        }
    }

    public void onStart() {
        super.onStart();
        mo6933p();
        this.f24625r = m19854e();
        this.f24614f.m25751a(this.f24624p, this.f24625r);
        this.f24614f.setVisibility(0);
        this.f24625r.a(getActivity(), "vipsong", this.f24623o, new C49831(this));
    }

    public void onStop() {
        super.onStop();
        m19855f();
        m19866q();
    }

    @AfterViews
    protected void m25780a() {
        m25774C();
    }

    private void m25774C() {
        switch (this.f24624p) {
            case NATIVE_ADS_OVERFLOW:
            case NATIVE_ADS_ABOUT:
            case VIP_SONG:
            case BANNER:
                this.f24613e.setBackground(ContextCompat.getDrawable(getActivity(), C1947R.drawable.upsell_bg_sky_indigo));
                return;
            case AUDIO_FX:
            case BOOST_OVERFLOW:
            case BOOST_ABOUT:
                this.f24613e.setBackground(ContextCompat.getDrawable(getActivity(), C1947R.drawable.upsell_bg_pink_purple));
                return;
            case CUSTOM_PROFILE:
            case STORAGE:
                this.f24613e.setBackground(ContextCompat.getDrawable(getActivity(), C1947R.drawable.upsell_bg_blue_red));
                return;
            default:
                return;
        }
    }

    protected void mo6420v() {
        this.f24614f.m25750a(this.f24618j, PaywallType.HARD);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = false;
        if (this.f24625r != null) {
            z = this.f24625r.a(i, i2, intent);
        }
        if (!z) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public boolean mo6445g() {
        return false;
    }

    public String mo6383s() {
        return f24612q;
    }

    @Click
    protected void m25784z() {
        getActivity().onBackPressed();
    }

    @Click
    protected void m25779A() {
        mo6487a(new LearnMoreFragment_());
    }
}
