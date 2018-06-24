package com.smule.singandroid.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import com.smule.android.ads.AdVendorManager;
import com.smule.android.ads.networks.AdColonyAdVendor;
import com.smule.android.ads.networks.AppLovinAdVendor;
import com.smule.android.ads.networks.FyberAdVendor;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AdVendorManagerConfig {
    static volatile boolean f20622a = false;
    private static final long f20623b = TimeUnit.MILLISECONDS.convert(4, TimeUnit.SECONDS);

    public static void m22233a() {
        if (!f20622a) {
            f20622a = true;
            Context f = SingApplication.f();
            m22235a(f);
            m22238b(f);
            m22239c();
        }
    }

    public static void m22234a(final Activity activity) {
        if (!AdVendorManager.m17399a().m17413b()) {
            SingApplication.d().a("AdVendorManagerConfig.OP_AD_VENDOR_INIT", Arrays.asList(new String[]{"InitAppTask.OP_RELOAD_SONGBOOK", "InitAppTask.OP_USER_ACTUALLY_LOGGED_IN"}), new Operation() {

                class C42281 implements Runnable {
                    final /* synthetic */ C42291 f20620a;

                    C42281(C42291 c42291) {
                        this.f20620a = c42291;
                    }

                    public void run() {
                        if (AdUtils.m22231e(activity)) {
                            AdVendorManager.m17399a().m17408a(activity, SingServerValues.m21683d());
                        }
                    }
                }

                public void m22232a(OperationLoader operationLoader) {
                    MagicNetwork.g().postDelayed(new C42281(this), AdVendorManagerConfig.f20623b);
                    operationLoader.a("AdVendorManagerConfig.OP_AD_VENDOR_INIT");
                    a(true);
                }
            }).a();
        }
    }

    private static void m22235a(Context context) {
        Resources resources = context.getResources();
        String string = resources.getString(C1947R.string.adcolony_google_get_more_smoola_zone_id);
        String string2 = resources.getString(C1947R.string.adcolony_google_post_performance_zone_id);
        String string3 = resources.getString(C1947R.string.adcolony_google_cancel_performance_zone_id);
        AdVendorManager.m17399a().m17411a(new AdColonyAdVendor(resources.getString(C1947R.string.adcolony_google_app_id), "4.4.9", string, new String[]{string, string2, string3}));
    }

    private static void m22238b(Context context) {
        Resources resources = context.getResources();
        AdVendorManager.m17399a().m17411a(new FyberAdVendor(resources.getString(C1947R.string.sponsorpay_app_id), resources.getString(C1947R.string.sponsorpay_key)));
    }

    private static void m22239c() {
        AdVendorManager.m17399a().m17411a(new AppLovinAdVendor());
    }

    public static void m22237b(Activity activity) {
        AdVendorManager.m17399a().m17409a(activity, SingServerValues.m21684e(), SingServerValues.m21685f());
    }
}
