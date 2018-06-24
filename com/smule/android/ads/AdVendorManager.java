/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.Intent
 *  android.os.AsyncTask
 *  android.os.Handler
 *  android.os.Looper
 */
package com.smule.android.ads;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.R;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.BalanceManager;
import com.smule.android.ui.dialogs.BusyScreenDialog;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdVendorManager {
    public static final long a = TimeUnit.MILLISECONDS.convert(8, TimeUnit.SECONDS);
    private static AdVendorManager h;
    List<String> b;
    List<String> c;
    private final String d = AdVendorManager.class.getName();
    private HashMap<String, AdVendor> e = new HashMap();
    private volatile boolean f;
    private volatile boolean g;

    private AdVendorManager() {
    }

    public static AdVendorManager a() {
        if (h == null) {
            h = new AdVendorManager();
        }
        return h;
    }

    private AdVendor a(Analytics earnVCVendor) {
        return this.e.get(earnVCVendor.a());
    }

    /*
     * Enabled aggressive block sorting
     */
    private List<AdVendor> a(AdVendor.AdType adType) {
        Object object = null;
        switch (.a[adType.ordinal()]) {
            case 1: {
                object = this.c;
                break;
            }
            case 2: {
                object = this.b;
            }
        }
        ArrayList<AdVendor> arrayList = new ArrayList<AdVendor>();
        if (object == null || object.isEmpty()) {
            Log.e(this.d, "Offer list was empty for: " + (Object)((Object)adType));
            return arrayList;
        }
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = ((String)object.next()).toLowerCase();
            Object object2 = com.smule.android.logging.Analytics.a(Analytics.class, string2);
            if (object2 == null) {
                Log.b(this.d, "Ad Vendor " + string2 + " does not support " + (Object)((Object)adType));
                continue;
            }
            if ((object2 = this.a(object2)) != null && object2.a(adType)) {
                arrayList.add((AdVendor)object2);
                continue;
            }
            Log.b(this.d, "Ad Vendor " + string2 + " does not support " + (Object)((Object)adType));
        }
        return arrayList;
    }

    private void a(Activity activity, AdVendor.AdType adType, List<AdVendor> list, AdVendor.ShowAdCallback showAdCallback) {
        new ShowOfferTask(activity, adType, list, showAdCallback).execute((Object[])new Void[0]);
    }

    public AdVendor a(Activity activity, Class<? extends AdVendor> class_) {
        Log.a(this.d, "initAdVendor: " + class_.getName());
        for (AdVendor adVendor : this.e.values()) {
            if (adVendor.getClass() != class_) continue;
            adVendor.c(activity);
            return adVendor;
        }
        Log.e(this.d, "AdVendor not found to activate: " + class_.getName());
        return null;
    }

    public void a(Activity activity) {
        Log.a(this.d, "preCacheRewardVideo");
        List<AdVendor> list = this.a(AdVendor.AdType.b);
        if (!list.isEmpty()) {
            list.get(0).d(activity);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Activity activity, AdVendor.AdType adType, String list, AdVendor.ShowAdCallback showAdCallback) {
        if (list == null || list.isEmpty()) {
            Log.d(this.d, "Empty ad vendor id given");
            return;
        }
        Log.a(this.d, "showOffer: " + list);
        list = list.toLowerCase(Locale.US);
        list = this.e.get(list);
        list = list != null ? Collections.singletonList(list) : new ArrayList<Object>();
        this.a(activity, adType, list, showAdCallback);
    }

    public void a(Activity activity, List<String> object) {
        Log.a(this.d, "init");
        if (this.g) {
            Log.a(this.d, "init already completed");
            return;
        }
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            AdVendor adVendor = this.e.get(string2.toLowerCase());
            if (adVendor != null) {
                adVendor.c(activity);
                continue;
            }
            Log.e(this.d, "Vendor not found to init: " + string2);
        }
        this.g = true;
    }

    public void a(Activity activity, List<String> list, List<String> list2) {
        Log.a(this.d, "initRewards");
        this.b = list;
        this.c = list2;
        this.a(activity);
    }

    public void a(Context context) {
        Log.a(this.d, "adVendorReportedEarnedCredits");
        this.f = true;
        com.smule.android.utils.Toaster.a(context, context.getString(R.string.cm_ads_earned_credits), Toaster.b);
        NotificationCenter.a().a("NOTIFICAITON_REFRESH_CREDITS_STARTED", new Object[0]);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){

            @Override
            public void run() {
                BalanceManager.a().a(new Runnable(){

                    @Override
                    public void run() {
                        AdVendorManager.this.f = false;
                        NotificationCenter.a().a("NOTIFICAITON_REFRESH_CREDITS_ENDED", new Object[0]);
                    }
                }, false);
            }

        }, a);
    }

    public void a(AdVendor adVendor) {
        this.e.put(adVendor.c(), adVendor);
    }

    public boolean a(Context context, int n, int n2, Intent intent) {
        Iterator<AdVendor> iterator = this.e.values().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().a(context, n, n2, intent)) continue;
            return true;
        }
        return false;
    }

    public boolean b() {
        return this.g;
    }

    private class ShowOfferTask
    extends AsyncTask<Void, Void, Void> {
        Activity a;
        AdVendor.AdType b;
        List<AdVendor> c;
        AdVendor.ShowAdCallback d;
        BusyScreenDialog e;
        volatile boolean f;

        public ShowOfferTask(Activity activity, AdVendor.AdType adType, List<AdVendor> list, AdVendor.ShowAdCallback showAdCallback) {
            this.f = false;
            this.a = activity;
            this.b = adType;
            this.c = list;
            this.d = showAdCallback;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        protected /* varargs */ Void a(Void ... handler) {
            if (this.c == null) {
                return null;
            }
            handler = new Handler(Looper.getMainLooper());
            Iterator<AdVendor> iterator = this.c.iterator();
            do {
                Object object;
                AdVendor adVendor;
                if (!iterator.hasNext()) return null;
                adVendor = iterator.next();
                Log.a(AdVendorManager.this.d, "ShowOfferTask attempting to show offer from: " + adVendor.c());
                object = new CountDownLatch(1);
                handler.post(new Runnable((CountDownLatch)object){
                    final /* synthetic */ CountDownLatch b;
                    {
                        this.b = countDownLatch;
                    }

                    @Override
                    public void run() {
                        adVendor.c(ShowOfferTask.this.a);
                        this.b.countDown();
                    }
                });
                try {
                    object.await(5, TimeUnit.SECONDS);
                }
                catch (InterruptedException interruptedException) {}
                object = new InnerShowAdCallback();
                adVendor.a(this.a, this.b, object, (AdVendor.ShowAdCallbackInterface)object);
                object.a();
            } while (!this.f);
            return null;
        }

        protected void a(Void void_) {
            block5 : {
                block4 : {
                    this.e.a(1000);
                    if (this.f) break block4;
                    if (this.d != null) {
                        this.d.c(null);
                    }
                    if (this.b != AdVendor.AdType.b) break block5;
                    com.smule.android.utils.Toaster.a((Context)this.a, R.string.cm_ads_reward_videos_unavailable);
                }
                return;
            }
            com.smule.android.utils.Toaster.a((Context)this.a, R.string.cm_ads_offers_unavailable);
        }

        protected /* synthetic */ Object doInBackground(Object[] arrobject) {
            return this.a((Void[])arrobject);
        }

        protected /* synthetic */ void onPostExecute(Object object) {
            this.a((Void)object);
        }

        protected void onPreExecute() {
            this.e = new BusyScreenDialog((Context)this.a);
            this.e.setCancelable(true);
            this.e.setOnCancelListener(new DialogInterface.OnCancelListener(){

                public void onCancel(DialogInterface dialogInterface) {
                    ShowOfferTask.this.f = true;
                }
            });
            this.e.show();
        }

        class InnerShowAdCallback
        implements AdVendor.ShowAdCallbackInterface {
            CountDownLatch a;

            public InnerShowAdCallback() {
                this.a = new CountDownLatch(1);
            }

            void a() {
                try {
                    this.a.await();
                    return;
                }
                catch (InterruptedException interruptedException) {
                    Log.c(AdVendorManager.this.d, "Problem waiting for CountDownLatch", interruptedException);
                    return;
                }
            }

            @Override
            public void a(AdVendor adVendor) {
                Log.b(AdVendorManager.this.d, "showAdStarted: " + adVendor);
                if (ShowOfferTask.this.d != null) {
                    ShowOfferTask.this.d.a(adVendor);
                }
                this.a(true);
            }

            void a(boolean bl) {
                ShowOfferTask.this.f = bl;
                this.a.countDown();
            }

            @Override
            public void b(AdVendor adVendor) {
                Log.b(AdVendorManager.this.d, "showAdFinished: " + adVendor);
                if (ShowOfferTask.this.d != null) {
                    ShowOfferTask.this.d.b(adVendor);
                }
                this.a(true);
                AdVendorManager.a().a((Context)ShowOfferTask.this.a);
            }

            @Override
            public void c(AdVendor adVendor) {
                Log.b(AdVendorManager.this.d, "showAdUnavailable: " + adVendor);
                this.a(false);
            }

            @Override
            public void d(AdVendor adVendor) {
                Log.b(AdVendorManager.this.d, "showAdFailed: " + adVendor);
                this.a(false);
            }

            @Override
            public void e(AdVendor adVendor) {
                Log.b(AdVendorManager.this.d, "showAdCancelled: " + adVendor);
                if (ShowOfferTask.this.d != null) {
                    ShowOfferTask.this.d.e(adVendor);
                }
                this.a(true);
            }
        }

    }

}

