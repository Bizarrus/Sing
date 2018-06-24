/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 */
package com.smule.singandroid.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.networks.AdColonyAdVendor;
import com.smule.android.ads.networks.AdVendor;
import com.smule.android.ads.networks.AppLovinAdVendor;
import com.smule.android.ads.networks.FyberAdVendor;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.utils.OperationLoader;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.ads.SingAdSettings;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdVendorManagerConfig {
    static volatile boolean a = false;
    private static final long b = TimeUnit.MILLISECONDS.convert(4, TimeUnit.SECONDS);

    public static void a() {
        if (!a) {
            a = true;
            Context context = SingApplication.g();
            AdVendorManagerConfig.a(context);
            AdVendorManagerConfig.b(context);
            AdVendorManagerConfig.c();
        }
    }

    public static void a(final Activity activity) {
        if (!AdVendorManager.a().b()) {
            SingApplication.d().a("AdVendorManagerConfig.OP_AD_VENDOR_INIT", Arrays.asList("InitAppTask.OP_RELOAD_SONGBOOK", "InitAppTask.OP_USER_ACTUALLY_LOGGED_IN"), new OperationLoader.Operation(){

                @Override
                public void a(OperationLoader operationLoader) {
                    MagicNetwork.g().postDelayed(new Runnable(){

                        @Override
                        public void run() {
                            if (SingAdSettings.h((Context)activity)) {
                                List<String> list = new SingServerValues().e();
                                AdVendorManager.a().a(activity, list);
                            }
                        }
                    }, b);
                    operationLoader.a("AdVendorManagerConfig.OP_AD_VENDOR_INIT");
                    this.a(true);
                }

            }).a();
        }
    }

    private static void a(Context object) {
        object = object.getResources();
        String string2 = object.getString(2131297671);
        String string3 = object.getString(2131297673);
        object = new AdColonyAdVendor(string2, "5.7.5", string3, new String[]{string3, object.getString(2131297674), object.getString(2131297672)});
        AdVendorManager.a().a((AdVendor)object);
    }

    public static void b(Activity activity) {
        SingServerValues singServerValues = new SingServerValues();
        AdVendorManager.a().a(activity, singServerValues.f(), singServerValues.g());
    }

    private static void b(Context object) {
        object = object.getResources();
        object = new FyberAdVendor(object.getString(2131297954), object.getString(2131297955));
        AdVendorManager.a().a((AdVendor)object);
    }

    private static void c() {
        AppLovinAdVendor appLovinAdVendor = new AppLovinAdVendor();
        AdVendorManager.a().a(appLovinAdVendor);
    }

}

