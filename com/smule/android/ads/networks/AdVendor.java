/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Handler
 *  android.os.Looper
 */
package com.smule.android.ads.networks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class AdVendor {
    private final String a;
    private volatile boolean b;
    private volatile CountDownLatch c;
    private volatile AdCacheStatus d = AdCacheStatus.a;

    public AdVendor() {
        this.a = AdVendor.class.getName() + " - " + this.getClass().getSimpleName();
    }

    private void b() {
        Log.b(this.a, "preCacheRewardVideoWatched");
        this.d = AdCacheStatus.a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b(final Activity activity, final Object object, final ShowAdCallbackInterface showAdCallbackInterface) {
        Log.a(this.a, "showRewardVideoInternal");
        this.d(activity);
        if (this.d == AdCacheStatus.b) {
            try {
                this.c.await(10, TimeUnit.SECONDS);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (this.d != AdCacheStatus.c) {
            Log.b(this.a, "Reward video pre-cache timed out");
            showAdCallbackInterface.c(this);
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable(){

            @Override
            public void run() {
                AdVendor.this.a(activity, object, showAdCallbackInterface);
                AdVendor.this.b();
            }
        });
    }

    public abstract Analytics a();

    protected abstract void a(Activity var1);

    public void a(final Activity activity, AdType adType, Object object, final ShowAdCallbackInterface showAdCallbackInterface) {
        if (!this.a(adType)) {
            Log.e(this.a, "This class does not support adType: " + (Object)((Object)adType));
            showAdCallbackInterface.d(this);
            return;
        }
        Log.b(this.a, "showOfferType: " + (Object)((Object)adType));
        switch (.a[adType.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                new Handler(Looper.getMainLooper()).post(new Runnable(){

                    @Override
                    public void run() {
                        AdVendor.this.a(activity, showAdCallbackInterface);
                    }
                });
                return;
            }
            case 2: 
        }
        this.b(activity, object, showAdCallbackInterface);
    }

    protected void a(Activity activity, ShowAdCallbackInterface showAdCallbackInterface) {
    }

    protected void a(Activity activity, Object object, ShowAdCallbackInterface showAdCallbackInterface) {
    }

    public boolean a(Context context, int n, int n2, Intent intent) {
        return false;
    }

    public abstract boolean a(AdType var1);

    public abstract void b(Activity var1);

    public String c() {
        Analytics earnVCVendor = this.a();
        if (earnVCVendor != null) {
            return earnVCVendor.a();
        }
        return null;
    }

    public final void c(Activity activity) {
        if (!this.b) {
            Log.b(this.a, "initializing ad vendor SDK: " + this.c());
            this.a(activity);
            this.b = true;
        }
    }

    protected final void d() {
        Log.b(this.a, "preCacheRewardVideoFinished");
        AdCacheStatus adCacheStatus = this.d;
        this.d = AdCacheStatus.c;
        if (adCacheStatus == AdCacheStatus.b) {
            this.c.countDown();
        }
    }

    public final void d(final Activity activity) {
        Log.b(this.a, "preCacheRewardVideo");
        if (this.d == AdCacheStatus.a) {
            this.d = AdCacheStatus.b;
            this.c = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable(){

                @Override
                public void run() {
                    AdVendor.this.c(activity);
                    AdVendor.this.b(activity);
                }
            });
        }
    }

    protected final void e() {
        Log.b(this.a, "preCacheRewardVideoFailed");
        AdCacheStatus adCacheStatus = this.d;
        this.d = AdCacheStatus.a;
        if (adCacheStatus == AdCacheStatus.b) {
            this.c.countDown();
        }
    }

    public String toString() {
        return super.toString();
    }

    private static enum AdCacheStatus {
        a,
        b,
        c;
        

        private AdCacheStatus() {
        }
    }

    public static enum AdType {
        a,
        b;
        

        private AdType() {
        }
    }

    public static class ShowAdCallback
    implements ShowAdCallbackInterface {
        @Override
        public void a(AdVendor adVendor) {
        }

        @Override
        public void b(AdVendor adVendor) {
        }

        @Override
        public void c(AdVendor adVendor) {
        }

        @Override
        public void d(AdVendor adVendor) {
        }

        @Override
        public void e(AdVendor adVendor) {
        }
    }

    public static interface ShowAdCallbackInterface {
        public void a(AdVendor var1);

        public void b(AdVendor var1);

        public void c(AdVendor var1);

        public void d(AdVendor var1);

        public void e(AdVendor var1);
    }

}

