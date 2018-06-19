package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinPack {
    @JsonProperty("coinsAmount")
    public int coinsAmount;
    @JsonProperty("currencyId")
    public int currencyId;
    @JsonProperty("googlePrice")
    public String googlePlayPrice;
    @JsonProperty("iconUrl")
    public String iconUrl;
    @JsonProperty("coinPackId")
    public String id;
    @JsonProperty("description")
    public String packDescription;
    @JsonProperty("order")
    public int position;
    @JsonProperty("sku")
    public String productUid;
    @JsonProperty("displayName")
    public String title;
}
