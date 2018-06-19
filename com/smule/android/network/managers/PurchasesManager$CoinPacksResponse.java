package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.CoinPack;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasesManager$CoinPacksResponse extends ParsedResponse {
    @JsonProperty("coinPacks")
    public List<CoinPack> mCoinPacks;

    static PurchasesManager$CoinPacksResponse m8046a(NetworkResponse networkResponse) {
        return (PurchasesManager$CoinPacksResponse) ParsedResponse.m7676a(networkResponse, PurchasesManager$CoinPacksResponse.class);
    }
}
