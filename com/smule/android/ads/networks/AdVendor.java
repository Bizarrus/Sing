package com.smule.android.ads.networks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Analytics.EarnVCVendor;
import com.smule.android.logging.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class AdVendor {
    private final String f15629a = (AdVendor.class.getName() + " - " + getClass().getSimpleName());
    private volatile boolean f15630b;
    private volatile CountDownLatch f15631c;
    private volatile AdCacheStatus f15632d = AdCacheStatus.NONE;

    public interface ShowAdCallbackInterface {
        void mo6207a(AdVendor adVendor);

        void mo6208b(AdVendor adVendor);

        void mo6209c(AdVendor adVendor);

        void mo6210d(AdVendor adVendor);

        void mo6211e(AdVendor adVendor);
    }

    private enum AdCacheStatus {
        NONE,
        CACHING,
        READY
    }

    public enum AdType {
        OFFER_WALL,
        VIDEO_REWARD
    }

    public static class ShowAdCallback implements ShowAdCallbackInterface {
        public void mo6207a(AdVendor adVendor) {
        }

        public void mo6208b(AdVendor adVendor) {
        }

        public void mo6209c(AdVendor adVendor) {
        }

        public void mo6210d(AdVendor adVendor) {
        }

        public void mo6211e(AdVendor adVendor) {
        }
    }

    public abstract EarnVCVendor mo6224a();

    protected abstract void mo6225a(Activity activity);

    public abstract boolean mo6227a(AdType adType);

    public abstract void mo6228b(Activity activity);

    public String m17466c() {
        EarnVCVendor a = mo6224a();
        return a != null ? a.mo6235a() : null;
    }

    public String toString() {
        return super.toString();
    }

    public final void m17467c(Activity activity) {
        if (!this.f15630b) {
            Log.b(this.f15629a, "initializing ad vendor SDK: " + m17466c());
            mo6225a(activity);
            this.f15630b = true;
        }
    }

    public void m17460a(final Activity activity, AdType adType, Object obj, final ShowAdCallbackInterface showAdCallbackInterface) {
        if (mo6227a(adType)) {
            Log.b(this.f15629a, "showOfferType: " + adType);
            switch (adType) {
                case OFFER_WALL:
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AdVendor f15642c;

                        public void run() {
                            this.f15642c.mo6229a(activity, showAdCallbackInterface);
                        }
                    });
                    return;
                case VIDEO_REWARD:
                    m17457b(activity, obj, showAdCallbackInterface);
                    return;
                default:
                    return;
            }
        }
        Log.e(this.f15629a, "This class does not support adType: " + adType);
        showAdCallbackInterface.mo6210d(this);
    }

    private void m17457b(final Activity activity, final Object obj, final ShowAdCallbackInterface showAdCallbackInterface) {
        Log.a(this.f15629a, "showRewardVideoInternal");
        m17469d(activity);
        if (this.f15632d == AdCacheStatus.CACHING) {
            try {
                this.f15631c.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.f15632d != AdCacheStatus.READY) {
            Log.b(this.f15629a, "Reward video pre-cache timed out");
            showAdCallbackInterface.mo6209c(this);
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ AdVendor f15646d;

            public void run() {
                this.f15646d.mo6226a(activity, obj, showAdCallbackInterface);
                this.f15646d.m17456b();
            }
        });
    }

    public final void m17469d(final Activity activity) {
        Log.b(this.f15629a, "preCacheRewardVideo");
        if (this.f15632d == AdCacheStatus.NONE) {
            this.f15632d = AdCacheStatus.CACHING;
            this.f15631c = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ AdVendor f15648b;

                public void run() {
                    this.f15648b.m17467c(activity);
                    this.f15648b.mo6228b(activity);
                }
            });
        }
    }

    protected final void m17468d() {
        Log.b(this.f15629a, "preCacheRewardVideoFinished");
        AdCacheStatus adCacheStatus = this.f15632d;
        this.f15632d = AdCacheStatus.READY;
        if (adCacheStatus == AdCacheStatus.CACHING) {
            this.f15631c.countDown();
        }
    }

    protected final void m17470e() {
        Log.b(this.f15629a, "preCacheRewardVideoFailed");
        AdCacheStatus adCacheStatus = this.f15632d;
        this.f15632d = AdCacheStatus.NONE;
        if (adCacheStatus == AdCacheStatus.CACHING) {
            this.f15631c.countDown();
        }
    }

    private void m17456b() {
        Log.b(this.f15629a, "preCacheRewardVideoWatched");
        this.f15632d = AdCacheStatus.NONE;
    }

    protected void mo6229a(Activity activity, ShowAdCallbackInterface showAdCallbackInterface) {
    }

    protected void mo6226a(Activity activity, Object obj, ShowAdCallbackInterface showAdCallbackInterface) {
    }

    public boolean mo6230a(Context context, int i, int i2, Intent intent) {
        return false;
    }
}
