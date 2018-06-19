package com.smule.android.network.managers;

import android.content.Context;
import com.facebook.share.internal.ShareConstants;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PurchasesAPI;
import com.smule.android.network.api.PurchasesAPI$PurchaseProductType;
import com.smule.android.network.api.PurchasesAPI$PurchaseRequest;
import com.smule.android.network.api.PurchasesAPI$PurchaseRequest.Order;
import com.smule.android.network.api.PurchasesAPI$RewardProductRequest;
import com.smule.android.network.api.PurchasesAPI$SpendCoinsRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.CoinPack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class PurchasesManager {
    private static final String f16917c = PurchasesManager.class.getName();
    private static PurchasesManager f16918d;
    private final int f16919a = 4;
    private final int f16920b = 600;
    private PurchasesAPI f16921e;
    private List<CoinPack> f16922f = new ArrayList();
    private final long f16923g = 3600000;
    private long f16924h = 0;

    class C35781 implements Runnable {
        final /* synthetic */ CoinPacksListener f16899a;
        final /* synthetic */ PurchasesManager f16900b;

        public void run() {
            CoreUtil.m18079a(this.f16899a, this.f16900b.m18276b());
        }
    }

    public interface CoinPacksListener extends ResponseInterface<CoinPacksResponse> {
        void handleResponse(CoinPacksResponse coinPacksResponse);
    }

    class C35792 implements CoinPacksListener {
        final /* synthetic */ LoadPacksCompletion f16901a;
        final /* synthetic */ Runnable f16902b;
        final /* synthetic */ PurchasesManager f16903c;

        public void handleResponse(CoinPacksResponse coinPacksResponse) {
            if (coinPacksResponse.a()) {
                this.f16903c.m18270a(coinPacksResponse.mCoinPacks);
                this.f16901a.m18266a(this.f16903c.f16922f);
                return;
            }
            this.f16902b.run();
        }
    }

    class C35803 implements Comparator<CoinPack> {
        final /* synthetic */ PurchasesManager f16904a;

        C35803(PurchasesManager purchasesManager) {
            this.f16904a = purchasesManager;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m18265a((CoinPack) obj, (CoinPack) obj2);
        }

        public int m18265a(CoinPack coinPack, CoinPack coinPack2) {
            return coinPack.position - coinPack2.position;
        }
    }

    class C35814 implements Runnable {
        final /* synthetic */ NetworkResponseCallback f16905a;
        final /* synthetic */ String f16906b;
        final /* synthetic */ String f16907c;
        final /* synthetic */ String f16908d;
        final /* synthetic */ PurchasesManager f16909e;

        public void run() {
            CoreUtil.m18079a(this.f16905a, this.f16909e.m18272a(this.f16906b, this.f16907c, this.f16908d));
        }
    }

    class C35825 implements Runnable {
        final /* synthetic */ NetworkResponseCallback f16910a;
        final /* synthetic */ int f16911b;
        final /* synthetic */ String f16912c;
        final /* synthetic */ String f16913d;
        final /* synthetic */ PurchasesAPI$PurchaseProductType f16914e;
        final /* synthetic */ String f16915f;
        final /* synthetic */ PurchasesManager f16916g;

        public void run() {
            CoreUtil.m18079a(this.f16910a, NetworkUtils.m18104a(this.f16916g.f16921e.spendCoins(new PurchasesAPI$SpendCoinsRequest().setAmount(Integer.valueOf(this.f16911b)).setProductId(this.f16912c).setProductSku(this.f16913d).setProductType(this.f16914e).setNotes(this.f16915f))));
        }
    }

    public static abstract class LoadPacksCompletion {
        public abstract void m18266a(List<CoinPack> list);
    }

    private PurchasesManager() {
    }

    public static synchronized PurchasesManager m18267a() {
        PurchasesManager purchasesManager;
        synchronized (PurchasesManager.class) {
            if (f16918d == null) {
                f16918d = new PurchasesManager();
            }
            purchasesManager = f16918d;
        }
        return purchasesManager;
    }

    public void m18274a(Context context) {
        this.f16921e = (PurchasesAPI) MagicNetwork.a().a(PurchasesAPI.class);
    }

    CoinPacksResponse m18276b() {
        return CoinPacksResponse.a(NetworkUtils.m18104a(this.f16921e.retrieveCoinPacks(new SnpRequest())));
    }

    private synchronized void m18270a(List<CoinPack> list) {
        Collections.sort(list, new C35803(this));
        this.f16922f = list;
        this.f16924h = System.currentTimeMillis();
    }

    public Boolean m18273a(String str) {
        for (CoinPack coinPack : this.f16922f) {
            if (coinPack.productUid.equals(str)) {
                return Boolean.valueOf(true);
            }
        }
        return Boolean.valueOf(false);
    }

    public boolean m18275a(String str, String str2, long j, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("market", "Google Play");
        hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, str3);
        hashMap.put("signature", str4);
        String jSONObject = new JSONObject(hashMap).toString();
        boolean z = false;
        NetworkResponse networkResponse = null;
        for (int i = 0; i < 4; i++) {
            if (i > 0) {
                Log.d(f16917c, "Re-trying the coin packs purchase API call to server. Try #" + (i + 1));
            }
            networkResponse = NetworkUtils.m18104a(this.f16921e.purchase(new PurchasesAPI$PurchaseRequest().setOrder(new Order().setOrderId(str2).setProductId(str).setReceipt(jSONObject))));
            z = networkResponse.c();
            if (z) {
                break;
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
            }
        }
        Log.c(f16917c, networkResponse.j);
        if (z) {
            BalanceManager.a().b();
        } else {
            MagicNetwork.a(networkResponse);
        }
        return z;
    }

    public NetworkResponse m18272a(String str, String str2, String str3) {
        return NetworkUtils.m18104a(this.f16921e.rewardProduct(new PurchasesAPI$RewardProductRequest().setProductId(str).setProductType(str2).setNotes(str3)));
    }
}
