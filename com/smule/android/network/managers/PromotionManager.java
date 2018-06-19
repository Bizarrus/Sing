package com.smule.android.network.managers;

import com.smule.android.network.api.PromotionAPI;
import com.smule.android.network.api.PromotionAPI$GetBannersRequest;
import com.smule.android.network.api.PromotionAPI$GetLeaderboardRequest;
import com.smule.android.network.api.PromotionAPI$GetPromotionRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.utils.NotificationCenter;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class PromotionManager {
    public static final long f16895a = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
    private static PromotionManager f16896b;
    private PromotionAPI f16897c = ((PromotionAPI) MagicNetwork.a().a(PromotionAPI.class));
    private final AtomicBoolean f16898d = new AtomicBoolean();

    class C35741 implements Observer {
        final /* synthetic */ PromotionManager f16882a;

        C35741(PromotionManager promotionManager) {
            this.f16882a = promotionManager;
        }

        public void update(Observable observable, Object obj) {
            if (SubscriptionManager.a().b()) {
                MagicNetwork.e().edit().putLong("BANNERS_CACHE_TIMESTAMP", 0).apply();
            }
        }
    }

    public interface BannersCallback extends ResponseInterface<BannersResponse> {
        void handleResponse(BannersResponse bannersResponse);
    }

    public interface PromotionCallback extends ResponseInterface<PromotionResponse> {
        void handleResponse(PromotionResponse promotionResponse);
    }

    public static PromotionManager m18256a() {
        if (f16896b == null) {
            f16896b = new PromotionManager();
        }
        return f16896b;
    }

    private PromotionManager() {
        NotificationCenter.m19011a().m19014a("SUBSCRIPTION_UPDATED_NOTIFICATION", new C35741(this));
    }

    public boolean m18264b() {
        return this.f16898d.get();
    }

    public BannersResponse m18259a(String str) {
        return BannersResponse.a(NetworkUtils.m18104a(this.f16897c.getBanners(new PromotionAPI$GetBannersRequest().setScreenType(str))));
    }

    public Future<?> m18263a(final String str, final BannersCallback bannersCallback) {
        this.f16898d.set(true);
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ PromotionManager f16885c;

            public void run() {
                BannersResponse a;
                long j = MagicNetwork.e().getLong("BANNERS_CACHE_TIMESTAMP", 0);
                String appVersion = MagicNetwork.d().getAppVersion();
                String string = MagicNetwork.e().getString("BANNERS_CACHE_APP_VERSION", appVersion);
                if (j + PromotionManager.f16895a < System.currentTimeMillis() || !string.equals(appVersion)) {
                    a = this.f16885c.m18259a(str);
                    if (a != null && a.a()) {
                        MagicNetwork.e().edit().putString("BANNERS_CACHE", a.a.j).putLong("BANNERS_CACHE_TIMESTAMP", System.currentTimeMillis()).putString("BANNERS_CACHE_APP_VERSION", appVersion).apply();
                        this.f16885c.f16898d.set(false);
                        a.b = false;
                        CoreUtil.m18079a(bannersCallback, a);
                        return;
                    }
                }
                NetworkResponse networkResponse = new NetworkResponse(MagicNetwork.e().getString("BANNERS_CACHE", null));
                networkResponse.a = NetworkResponse$Status.OK;
                a = (BannersResponse) ParsedResponse.a(networkResponse, BannersResponse.class);
                a.b = true;
                this.f16885c.f16898d.set(false);
                CoreUtil.m18079a(bannersCallback, a);
            }
        });
    }

    public PromotionResponse m18260a(long j) {
        return PromotionResponse.a(NetworkUtils.m18104a(this.f16897c.getPromotion(new PromotionAPI$GetPromotionRequest().setPromoId(Long.valueOf(j)))));
    }

    public Future<?> m18261a(final long j, final PromotionCallback promotionCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ PromotionManager f16888c;

            public void run() {
                if (promotionCallback != null) {
                    CoreUtil.m18079a(promotionCallback, this.f16888c.m18260a(j));
                }
            }
        });
    }

    public PerformancesResponse m18258a(long j, String str, int i, int i2) {
        return PerformancesResponse.a(NetworkUtils.m18104a(this.f16897c.getLeaderboard(new PromotionAPI$GetLeaderboardRequest().setPromoId(Long.valueOf(j)).setOrderBy(str).setOffset(i).setLimit(i2))));
    }

    public Future<?> m18262a(long j, String str, int i, int i2, PerformanceManager$PerformancesResponseCallback performanceManager$PerformancesResponseCallback) {
        final PerformanceManager$PerformancesResponseCallback performanceManager$PerformancesResponseCallback2 = performanceManager$PerformancesResponseCallback;
        final long j2 = j;
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ PromotionManager f16894f;

            public void run() {
                if (performanceManager$PerformancesResponseCallback2 != null) {
                    CoreUtil.m18079a(performanceManager$PerformancesResponseCallback2, this.f16894f.m18258a(j2, str2, i3, i4));
                }
            }
        });
    }
}
