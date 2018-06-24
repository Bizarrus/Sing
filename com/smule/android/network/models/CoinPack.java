/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CoinPack {
    @JsonProperty(value="coinsAmount")
    public int coinsAmount;
    @JsonProperty(value="currencyId")
    public int currencyId;
    @JsonProperty(value="googlePrice")
    public String googlePlayPrice;
    @JsonProperty(value="iconUrl")
    public String iconUrl;
    @JsonProperty(value="coinPackId")
    public String id;
    @JsonProperty(value="description")
    public String packDescription;
    @JsonProperty(value="order")
    public int position;
    @JsonProperty(value="sku")
    public String productUid;
    @JsonProperty(value="displayName")
    public String title;
}

