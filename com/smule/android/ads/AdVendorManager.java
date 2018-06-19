package com.smule.android.ads;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.C3482R;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.ads.networks.AdVendor.AdType;
import com.smule.android.ads.networks.AdVendor.ShowAdCallback;
import com.smule.android.ads.networks.AdVendor.ShowAdCallbackInterface;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.EarnVCVendor;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.BalanceManager;
import com.smule.android.ui.dialogs.BusyScreenDialog;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdVendorManager {
    public static final long f15586a = TimeUnit.MILLISECONDS.convert(8, TimeUnit.SECONDS);
    private static AdVendorManager f15587h;
    List<String> f15588b;
    List<String> f15589c;
    private final String f15590d = AdVendorManager.class.getName();
    private HashMap<String, AdVendor> f15591e = new HashMap();
    private volatile boolean f15592f;
    private volatile boolean f15593g;

    class C34841 implements Runnable {
        final /* synthetic */ AdVendorManager f15571a;

        class C34831 implements Runnable {
            final /* synthetic */ C34841 f15570a;

            C34831(C34841 c34841) {
                this.f15570a = c34841;
            }

            public void run() {
                this.f15570a.f15571a.f15592f = false;
                NotificationCenter.m19011a().m19015a("NOTIFICAITON_REFRESH_CREDITS_ENDED", new Object[0]);
            }
        }

        C34841(AdVendorManager adVendorManager) {
            this.f15571a = adVendorManager;
        }

        public void run() {
            BalanceManager.a().a(new C34831(this), false);
        }
    }

    private class ShowOfferTask extends AsyncTask<Void, Void, Void> {
        Activity f15579a;
        AdType f15580b;
        List<AdVendor> f15581c;
        ShowAdCallback f15582d;
        BusyScreenDialog f15583e;
        volatile boolean f15584f = false;
        final /* synthetic */ AdVendorManager f15585g;

        class C34861 implements OnCancelListener {
            final /* synthetic */ ShowOfferTask f15573a;

            C34861(ShowOfferTask showOfferTask) {
                this.f15573a = showOfferTask;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.f15573a.f15584f = true;
            }
        }

        class InnerShowAdCallback implements ShowAdCallbackInterface {
            CountDownLatch f15577a = new CountDownLatch(1);
            final /* synthetic */ ShowOfferTask f15578b;

            public InnerShowAdCallback(ShowOfferTask showOfferTask) {
                this.f15578b = showOfferTask;
            }

            void m17390a() {
                try {
                    this.f15577a.await();
                } catch (Throwable e) {
                    Log.c(this.f15578b.f15585g.f15590d, "Problem waiting for CountDownLatch", e);
                }
            }

            void m17392a(boolean z) {
                this.f15578b.f15584f = z;
                this.f15577a.countDown();
            }

            public void mo6207a(AdVendor adVendor) {
                Log.b(this.f15578b.f15585g.f15590d, "showAdStarted: " + adVendor);
                if (this.f15578b.f15582d != null) {
                    this.f15578b.f15582d.mo6207a(adVendor);
                }
                m17392a(true);
            }

            public void mo6208b(AdVendor adVendor) {
                Log.b(this.f15578b.f15585g.f15590d, "showAdFinished: " + adVendor);
                if (this.f15578b.f15582d != null) {
                    this.f15578b.f15582d.mo6208b(adVendor);
                }
                m17392a(true);
                AdVendorManager.m17399a().m17410a(this.f15578b.f15579a);
            }

            public void mo6209c(AdVendor adVendor) {
                Log.b(this.f15578b.f15585g.f15590d, "showAdUnavailable: " + adVendor);
                m17392a(false);
            }

            public void mo6210d(AdVendor adVendor) {
                Log.b(this.f15578b.f15585g.f15590d, "showAdFailed: " + adVendor);
                m17392a(false);
            }

            public void mo6211e(AdVendor adVendor) {
                Log.b(this.f15578b.f15585g.f15590d, "showAdCancelled: " + adVendor);
                if (this.f15578b.f15582d != null) {
                    this.f15578b.f15582d.mo6211e(adVendor);
                }
                m17392a(true);
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m17397a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m17398a((Void) obj);
        }

        public ShowOfferTask(AdVendorManager adVendorManager, Activity activity, AdType adType, List<AdVendor> list, ShowAdCallback showAdCallback) {
            this.f15585g = adVendorManager;
            this.f15579a = activity;
            this.f15580b = adType;
            this.f15581c = list;
            this.f15582d = showAdCallback;
        }

        protected void onPreExecute() {
            this.f15583e = new BusyScreenDialog(this.f15579a);
            this.f15583e.setCancelable(true);
            this.f15583e.setOnCancelListener(new C34861(this));
            this.f15583e.show();
        }

        protected void m17398a(Void voidR) {
            this.f15583e.m18884a(1000);
            if (!this.f15584f) {
                if (this.f15582d != null) {
                    this.f15582d.mo6209c(null);
                }
                if (this.f15580b == AdType.VIDEO_REWARD) {
                    Toaster.a(this.f15579a, C3482R.string.cm_ads_reward_videos_unavailable);
                } else {
                    Toaster.a(this.f15579a, C3482R.string.cm_ads_offers_unavailable);
                }
            }
        }

        protected Void m17397a(Void... voidArr) {
            if (this.f15581c != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                for (final AdVendor adVendor : this.f15581c) {
                    Log.a(this.f15585g.f15590d, "ShowOfferTask attempting to show offer from: " + adVendor.m17466c());
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    handler.post(new Runnable(this) {
                        final /* synthetic */ ShowOfferTask f15576c;

                        public void run() {
                            adVendor.m17467c(this.f15576c.f15579a);
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                    }
                    Object innerShowAdCallback = new InnerShowAdCallback(this);
                    adVendor.m17460a(this.f15579a, this.f15580b, innerShowAdCallback, (ShowAdCallbackInterface) innerShowAdCallback);
                    innerShowAdCallback.m17390a();
                    if (this.f15584f) {
                        break;
                    }
                }
            }
            return null;
        }
    }

    public static AdVendorManager m17399a() {
        if (f15587h == null) {
            f15587h = new AdVendorManager();
        }
        return f15587h;
    }

    private AdVendorManager() {
    }

    public void m17411a(AdVendor adVendor) {
        this.f15591e.put(adVendor.m17466c(), adVendor);
    }

    public void m17408a(Activity activity, List<String> list) {
        Log.a(this.f15590d, "init");
        if (this.f15593g) {
            Log.a(this.f15590d, "init already completed");
            return;
        }
        for (String str : list) {
            AdVendor adVendor = (AdVendor) this.f15591e.get(str.toLowerCase());
            if (adVendor != null) {
                adVendor.m17467c(activity);
            } else {
                Log.e(this.f15590d, "Vendor not found to init: " + str);
            }
        }
        this.f15593g = true;
    }

    public boolean m17413b() {
        return this.f15593g;
    }

    public AdVendor m17405a(Activity activity, Class<? extends AdVendor> cls) {
        Log.a(this.f15590d, "initAdVendor: " + cls.getName());
        for (AdVendor adVendor : this.f15591e.values()) {
            if (adVendor.getClass() == cls) {
                adVendor.m17467c(activity);
                return adVendor;
            }
        }
        Log.e(this.f15590d, "AdVendor not found to activate: " + cls.getName());
        return null;
    }

    public void m17410a(Context context) {
        Log.a(this.f15590d, "adVendorReportedEarnedCredits");
        this.f15592f = true;
        Toaster.a(context, context.getString(C3482R.string.cm_ads_earned_credits), Toaster$Duration.LONG);
        NotificationCenter.m19011a().m19015a("NOTIFICAITON_REFRESH_CREDITS_STARTED", new Object[0]);
        new Handler(Looper.getMainLooper()).postDelayed(new C34841(this), f15586a);
    }

    public void m17406a(Activity activity) {
        Log.a(this.f15590d, "preCacheRewardVideo");
        List a = m17402a(AdType.VIDEO_REWARD);
        if (!a.isEmpty()) {
            ((AdVendor) a.get(0)).m17469d(activity);
        }
    }

    private AdVendor m17400a(EarnVCVendor earnVCVendor) {
        return (AdVendor) this.f15591e.get(earnVCVendor.mo6235a());
    }

    public void m17409a(Activity activity, List<String> list, List<String> list2) {
        Log.a(this.f15590d, "initRewards");
        this.f15588b = list;
        this.f15589c = list2;
        m17406a(activity);
    }

    public void m17407a(Activity activity, AdType adType, String str, ShowAdCallback showAdCallback) {
        if (str == null || str.isEmpty()) {
            Log.d(this.f15590d, "Empty ad vendor id given");
            return;
        }
        Log.a(this.f15590d, "showOffer: " + str);
        AdVendor adVendor = (AdVendor) this.f15591e.get(str.toLowerCase(Locale.US));
        m17403a(activity, adType, adVendor != null ? Collections.singletonList(adVendor) : new ArrayList(), showAdCallback);
    }

    private void m17403a(Activity activity, AdType adType, List<AdVendor> list, ShowAdCallback showAdCallback) {
        new ShowOfferTask(this, activity, adType, list, showAdCallback).execute(new Void[0]);
    }

    private List<AdVendor> m17402a(AdType adType) {
        List list = null;
        switch (adType) {
            case OFFER_WALL:
                list = this.f15589c;
                break;
            case VIDEO_REWARD:
                list = this.f15588b;
                break;
        }
        List<AdVendor> arrayList = new ArrayList();
        if (r0 == null || r0.isEmpty()) {
            Log.e(this.f15590d, "Offer list was empty for: " + adType);
            return arrayList;
        }
        for (String toLowerCase : r0) {
            String toLowerCase2 = toLowerCase.toLowerCase();
            EarnVCVendor earnVCVendor = (EarnVCVendor) Analytics.m17829a(EarnVCVendor.class, toLowerCase2);
            if (earnVCVendor == null) {
                Log.b(this.f15590d, "Ad Vendor " + toLowerCase2 + " does not support " + adType);
            } else {
                AdVendor a = m17400a(earnVCVendor);
                if (a == null || !a.mo6227a(adType)) {
                    Log.b(this.f15590d, "Ad Vendor " + toLowerCase2 + " does not support " + adType);
                } else {
                    arrayList.add(a);
                }
            }
        }
        return arrayList;
    }

    public boolean m17412a(Context context, int i, int i2, Intent intent) {
        for (AdVendor a : this.f15591e.values()) {
            if (a.mo6230a(context, i, i2, intent)) {
                return true;
            }
        }
        return false;
    }
}
