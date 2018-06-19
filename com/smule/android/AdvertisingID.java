package com.smule.android;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.smule.android.logging.Log;

public class AdvertisingID {
    public static final String f15527a = AdvertisingID.class.getName();
    public static String f15528b = null;
    public static Boolean f15529c = Boolean.valueOf(false);

    static class C34801 implements Runnable {
        final /* synthetic */ Context f15526a;

        public void run() {
            try {
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f15526a);
                AdvertisingID.f15529c = Boolean.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled());
                AdvertisingID.f15528b = advertisingIdInfo.getId();
                this.f15526a.getSharedPreferences("network", 0).edit().putString("AdvertisingID", AdvertisingID.f15528b).apply();
            } catch (Exception e) {
                Log.c(AdvertisingID.f15527a, e.toString());
            }
        }
    }
}
