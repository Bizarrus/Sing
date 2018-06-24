/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  org.json.JSONObject
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PurchasesAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.BalanceManager;
import com.smule.android.network.models.CoinPack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import retrofit2.Call;

public class PurchasesManager {
    private static final String c = PurchasesManager.class.getName();
    private static PurchasesManager d;
    private final int a = 4;
    private final int b = 600;
    private com.smule.android.network.api.PurchasesAPI e;
    private List<CoinPack> f = new ArrayList<CoinPack>();
    private final long g = 3600000;
    private long h = 0;

    private PurchasesManager() {
    }

    public static PurchasesManager a() {
        synchronized (PurchasesManager.class) {
            if (d == null) {
                d = new PurchasesManager();
            }
            PurchasesManager purchasesManager = d;
            return purchasesManager;
        }
    }

    private void a(List<CoinPack> list) {
        synchronized (this) {
            Collections.sort(list, new Comparator<CoinPack>(){

                public int a(CoinPack coinPack, CoinPack coinPack2) {
                    return coinPack.position - coinPack2.position;
                }

                @Override
                public /* synthetic */ int compare(Object object, Object object2) {
                    return this.a((CoinPack)object, (CoinPack)object2);
                }
            });
            this.f = list;
            this.h = System.currentTimeMillis();
            return;
        }
    }

    public NetworkResponse a(String string2, String string3, String string4) {
        return NetworkUtils.a(this.e.rewardProduct(new SnpRequest(){
            public String notes;
            public String productId;
            public String productType;

            public PurchasesAPI setNotes(String string2) {
                this.notes = string2;
                return this;
            }

            public PurchasesAPI setProductId(String string2) {
                this.productId = string2;
                return this;
            }

            public PurchasesAPI setProductType(String string2) {
                this.productType = string2;
                return this;
            }
        }.setProductId(string2).setProductType(string3).setNotes(string4)));
    }

    public Boolean a(String string2) {
        Iterator<CoinPack> iterator = this.f.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().productUid.equals(string2)) continue;
            return true;
        }
        return false;
    }

    public void a(Context context) {
        this.e = MagicNetwork.a().a(com.smule.android.network.api.PurchasesAPI.class);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean a(String string2, String string3, long l, String object, String string4) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("market", "Google Play");
        hashMap.put("data", object);
        hashMap.put("signature", string4);
        string4 = new JSONObject(hashMap).toString();
        int n = 0;
        boolean bl = false;
        object = null;
        do {
            block9 : {
                block8 : {
                    if (n >= 4) break block8;
                    if (n > 0) {
                        Log.d(c, "Re-trying the coin packs purchase API call to server. Try #" + (n + 1));
                    }
                    if (!(bl = (object = NetworkUtils.a(this.e.purchase(new SnpRequest(){
                        public List<PurchasesAPI.Order> orders;

                        public PurchasesAPI setOrder(PurchasesAPI.Order order) {
                            if (order != null) {
                                this.orders = java.util.Arrays.asList(order);
                            }
                            return this;
                        }

                        public static class PurchasesAPI.Order {
                            public String orderId;
                            public String productId;
                            public String productType = "CNPCK";
                            public String receipt;

                            public PurchasesAPI.Order setOrderId(String string2) {
                                this.orderId = string2;
                                return this;
                            }

                            public PurchasesAPI.Order setProductId(String string2) {
                                this.productId = string2;
                                return this;
                            }

                            public PurchasesAPI.Order setReceipt(String string2) {
                                this.receipt = string2;
                                return this;
                            }
                        }

                    }.setOrder(new PurchasesAPI.Order().setOrderId(string3).setProductId(string2).setReceipt(string4))))).c())) break block9;
                }
                Log.c(c, object.j);
                if (!bl) break;
                BalanceManager.a().b();
                return bl;
            }
            try {
                Thread.sleep(600);
            }
            catch (InterruptedException interruptedException) {}
            ++n;
        } while (true);
        MagicNetwork.a((NetworkResponse)((Object)object));
        return bl;
    }

     b() {
        return .a(NetworkUtils.a(this.e.retrieveCoinPacks(new SnpRequest())));
    }

    public static interface CoinPacksListener
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static abstract class LoadPacksCompletion {
        public abstract void a(List<CoinPack> var1);
    }

}

