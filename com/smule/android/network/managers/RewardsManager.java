/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Handler
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.RewardsAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.utils.JsonUtils;
import java.io.IOException;
import retrofit2.Call;

public class RewardsManager {
    private static final String a = RewardsManager.class.getName();
    private static RewardsManager b;
    private com.smule.android.network.api.RewardsAPI c = MagicNetwork.a().a(com.smule.android.network.api.RewardsAPI.class);
    private Handler d = new Handler();

    private RewardsManager() {
    }

    public static RewardsManager a() {
        synchronized (RewardsManager.class) {
            if (b == null) {
                b = new RewardsManager();
            }
            RewardsManager rewardsManager = b;
            return rewardsManager;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int a(int n) {
        NetworkResponse networkResponse;
        block6 : {
            try {
                networkResponse = NetworkUtils.a(this.c.rewardCoins(new SnpRequest(){
                    public Integer rewardType;

                    public RewardsAPI setRewardType(Integer n) {
                        this.rewardType = n;
                        return this;
                    }
                }.setRewardType(n)));
                if (networkResponse.b == 0) break block6;
                MagicNetwork.a(networkResponse);
                if (1010 != networkResponse.b) break block6;
                return 0;
            }
            catch (Exception exception) {
                Log.a(a, "Exception thrown when requesting coin reward:", exception);
                return -1;
            }
        }
        String string2 = networkResponse.j;
        if (n == Reward.a.c || n == Reward.b.c) {
            boolean bl = networkResponse.c();
            if (!bl) return -1;
            return 1;
        }
        if (string2 == null) return -1;
        networkResponse = JsonUtils.a();
        try {
            string2 = (JsonNode)networkResponse.readValue(string2, JsonNode.class);
            if (!string2.has("data")) return -1;
            if (!(string2 = string2.get("data")).has("count")) return -1;
            return string2.get("count").intValue();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return -1;
    }

    public void a(ClaimRewardListener claimRewardListener) {
        this.a(Reward.b, claimRewardListener);
    }

    public void a(final Reward reward, final ClaimRewardListener claimRewardListener) {
        if (!this.a(reward)) {
            Log.b(a, "isRewardEnabledAndUnclaimed() says the reward is disabled OR unclaimed so calling listener and finishing");
            claimRewardListener.d();
            return;
        }
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                final int n = RewardsManager.this.a(reward.c);
                RewardsManager.this.d.post(new Runnable(){

                    @Override
                    public void run() {
                        if (n == -1) {
                            Log.b(a, "Error claiming reward type: " + reward.c);
                            claimRewardListener.c();
                        }
                        if (n == 1) {
                            Log.b(a, "Successfully claimed reward type: " + reward.c);
                            RewardsManager.this.c(reward);
                            claimRewardListener.a();
                        }
                        if (n == 0) {
                            Log.b(a, "Already claimed reward type: " + reward.c);
                            RewardsManager.this.c(reward);
                            claimRewardListener.b();
                        }
                        claimRewardListener.d();
                    }
                });
            }

        });
    }

    public boolean a(Reward reward) {
        if (this.b(reward) && !this.d(reward)) {
            return true;
        }
        return false;
    }

    public SharedPreferences b() {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("RewardsManager", 0);
    }

    public boolean b(Reward reward) {
        return AppSettingsManager.a().a(MagicNetwork.d().getAppUID() + ".offers", reward.e, true);
    }

    public void c(Reward reward) {
        this.b().edit().putBoolean("reward_claimed_" + reward.c, true).apply();
    }

    public boolean d(Reward reward) {
        return this.b().getBoolean("reward_claimed_" + reward.c, false);
    }

    public static interface ClaimRewardListener {
        public void a();

        public void b();

        public void c();

        public void d();
    }

    public static enum Reward {
        a(1, 5, "fb_like_enabled"),
        b(7, 10, "fb_login_enabled");
        
        public final int c;
        public final int d;
        public final String e;

        private Reward(int n2, int n3, String string3) {
            this.c = n2;
            this.d = n3;
            this.e = string3;
        }
    }

}

