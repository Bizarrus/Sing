package com.smule.android.network.managers;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.PointerIconCompat;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.api.RewardsAPI;
import com.smule.android.network.api.RewardsAPI$RewardCoinsRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.utils.JsonUtils;
import java.io.IOException;

public class RewardsManager {
    private static final String f17012a = RewardsManager.class.getName();
    private static RewardsManager f17013b;
    private RewardsAPI f17014c = ((RewardsAPI) MagicNetwork.a().a(RewardsAPI.class));
    private Handler f17015d = new Handler();

    public interface ClaimRewardListener {
        void mo6412a();

        void mo6413b();

        void mo6414c();

        void mo6415d();
    }

    public enum Reward {
        FB_LIKE(1, 5, "fb_like_enabled"),
        FB_LOGIN(7, 10, "fb_login_enabled");
        
        public final int f17009c;
        public final int f17010d;
        public final String f17011e;

        private Reward(int i, int i2, String str) {
            this.f17009c = i;
            this.f17010d = i2;
            this.f17011e = str;
        }
    }

    public static synchronized RewardsManager m18319a() {
        RewardsManager rewardsManager;
        synchronized (RewardsManager.class) {
            if (f17013b == null) {
                f17013b = new RewardsManager();
            }
            rewardsManager = f17013b;
        }
        return rewardsManager;
    }

    private RewardsManager() {
    }

    public int m18321a(int i) {
        try {
            NetworkResponse a = NetworkUtils.m18104a(this.f17014c.rewardCoins(new RewardsAPI$RewardCoinsRequest().setRewardType(Integer.valueOf(i))));
            if (a.b != 0) {
                MagicNetwork.a(a);
                if (PointerIconCompat.TYPE_ALIAS == a.b) {
                    return 0;
                }
            }
            String str = a.j;
            if (i == Reward.FB_LIKE.f17009c || i == Reward.FB_LOGIN.f17009c) {
                return a.c() ? 1 : -1;
            } else if (str == null) {
                return -1;
            } else {
                try {
                    JsonNode jsonNode = (JsonNode) JsonUtils.m18984a().readValue(str, JsonNode.class);
                    if (!jsonNode.has(ShareConstants.WEB_DIALOG_PARAM_DATA)) {
                        return -1;
                    }
                    jsonNode = jsonNode.get(ShareConstants.WEB_DIALOG_PARAM_DATA);
                    if (jsonNode.has("count")) {
                        return jsonNode.get("count").intValue();
                    }
                    return -1;
                } catch (IOException e) {
                    return -1;
                }
            }
        } catch (Throwable e2) {
            Log.a(f17012a, "Exception thrown when requesting coin reward:", e2);
            return -1;
        }
    }

    public void m18322a(ClaimRewardListener claimRewardListener) {
        m18323a(Reward.FB_LOGIN, claimRewardListener);
    }

    public void m18323a(final Reward reward, final ClaimRewardListener claimRewardListener) {
        if (m18324a(reward)) {
            MagicNetwork.a(new Runnable(this) {
                final /* synthetic */ RewardsManager f17005c;

                public void run() {
                    final int a = this.f17005c.m18321a(reward.f17009c);
                    this.f17005c.f17015d.post(new Runnable(this) {
                        final /* synthetic */ C35991 f17002b;

                        public void run() {
                            if (a == -1) {
                                Log.b(RewardsManager.f17012a, "Error claiming reward type: " + reward.f17009c);
                                claimRewardListener.mo6414c();
                            }
                            if (a == 1) {
                                Log.b(RewardsManager.f17012a, "Successfully claimed reward type: " + reward.f17009c);
                                this.f17002b.f17005c.m18327c(reward);
                                claimRewardListener.mo6412a();
                            }
                            if (a == 0) {
                                Log.b(RewardsManager.f17012a, "Already claimed reward type: " + reward.f17009c);
                                this.f17002b.f17005c.m18327c(reward);
                                claimRewardListener.mo6413b();
                            }
                            claimRewardListener.mo6415d();
                        }
                    });
                }
            });
            return;
        }
        Log.b(f17012a, "isRewardEnabledAndUnclaimed() says the reward is disabled OR unclaimed so calling listener and finishing");
        claimRewardListener.mo6415d();
    }

    public boolean m18324a(Reward reward) {
        return m18326b(reward) && !m18328d(reward);
    }

    public SharedPreferences m18325b() {
        return MagicNetwork.d().getApplicationContext().getSharedPreferences("RewardsManager", 0);
    }

    public boolean m18326b(Reward reward) {
        return AppSettingsManager.a().a(MagicNetwork.d().getAppUID() + ".offers", reward.f17011e, true);
    }

    public void m18327c(Reward reward) {
        m18325b().edit().putBoolean("reward_claimed_" + reward.f17009c, true).apply();
    }

    public boolean m18328d(Reward reward) {
        return m18325b().getBoolean("reward_claimed_" + reward.f17009c, false);
    }
}
