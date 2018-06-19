package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;

public class RewardsAPI$RewardCoinsRequest extends SnpRequest {
    public Integer rewardType;

    public RewardsAPI$RewardCoinsRequest setRewardType(Integer num) {
        this.rewardType = num;
        return this;
    }
}
